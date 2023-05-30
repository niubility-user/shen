package com.jingdong.common.XView2.strategy.downloader;

import android.text.TextUtils;
import com.jd.hybrid.downloader.d;
import com.jd.hybrid.downloader.h;
import com.jd.hybrid.downloader.i;
import com.jd.hybrid.downloader.m.b;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.entity.PreDownLoadResourceEntity;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.strategy.downloader.entity.XViewFileEntity;
import com.jingdong.common.XView2.strategy.downloader.storage.XViewFileStore;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public final class XViewCache implements h {
    private static XViewCache instance;
    private Map<String, i> fileObservers;
    private List<XViewFileEntity> mPreDownLoadResourceEntityList;
    private boolean available = false;
    private List<String> retryArray = new ArrayList();
    Comparator<XViewFileEntity> comparator = new Comparator<XViewFileEntity>() { // from class: com.jingdong.common.XView2.strategy.downloader.XViewCache.1
        {
            XViewCache.this = this;
        }

        @Override // java.util.Comparator
        public int compare(XViewFileEntity xViewFileEntity, XViewFileEntity xViewFileEntity2) {
            if (xViewFileEntity != null && xViewFileEntity2 != null) {
                int i2 = xViewFileEntity.project_priority;
                int i3 = xViewFileEntity2.project_priority;
                if (i2 < i3) {
                    return 1;
                }
                if (i2 - i3 != 0 && i2 > i3) {
                    return -1;
                }
            }
            return 0;
        }
    };
    private XViewFileStore mXViewFileStore = new XViewFileStore();

    private XViewCache() {
    }

    private void addDownloader(final List<XViewFileEntity> list) {
        final XViewDownloadClient xViewDownloadClient = XViewDownloadClient.getInstance();
        if (xViewDownloadClient != null) {
            new Thread(new Runnable() { // from class: com.jingdong.common.XView2.strategy.downloader.XViewCache.2
                {
                    XViewCache.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep((SwitchQueryFetcher.getSwitchBooleanValue("XViewSleepMillis", true) ? 3 : 5) * 1000);
                        for (XViewFileEntity xViewFileEntity : list) {
                            if (XViewCache.this.needDownload(xViewFileEntity)) {
                                xViewDownloadClient.addDownloader(XViewCache.this.getDownloader(xViewFileEntity));
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }, "XView-download-delay").start();
        }
    }

    private void deleteFile(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private void downloadById(String str) {
        XViewDownloadClient xViewDownloadClient;
        XViewFileEntity originalFile = this.mXViewFileStore.getOriginalFile(str);
        if (originalFile == null || originalFile == null || !needDownload(originalFile) || (xViewDownloadClient = XViewDownloadClient.getInstance()) == null) {
            return;
        }
        xViewDownloadClient.addDownloader(getDownloader(originalFile));
    }

    public d getDownloader(XViewFileEntity xViewFileEntity) {
        StringBuilder sb = new StringBuilder();
        sb.append(XView2Constants.CACHE_FILE_ROOT);
        String str = File.separator;
        sb.append(str);
        sb.append("file");
        sb.append(str);
        sb.append(xViewFileEntity.layerId);
        String sb2 = sb.toString();
        String md5 = Md5Encrypt.md5(xViewFileEntity.id);
        xViewFileEntity.suffix = XView2Utils.getFileSuffixFromPath(xViewFileEntity.url);
        d dVar = new d("XView:" + xViewFileEntity.id, xViewFileEntity.url, sb2, md5 + xViewFileEntity.suffix, false);
        dVar.o(TextUtils.isEmpty(String.valueOf(xViewFileEntity.project_priority)) ? 0 : xViewFileEntity.project_priority);
        dVar.n(xViewFileEntity.id);
        XViewCacheCallback xViewCacheCallback = new XViewCacheCallback(this);
        try {
            xViewCacheCallback.setEntity(xViewFileEntity.publicClone());
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
        }
        dVar.l(xViewCacheCallback);
        xViewFileEntity.status = 2;
        return dVar;
    }

    private b getFileById(String str) {
        XViewFileEntity fileById = this.mXViewFileStore.getFileById(str);
        if (fileById != null && (fileById.getStatus() == 0 || fileById.getStatus() == -1)) {
            downloadById(fileById.getId());
        }
        if (fileById != null) {
            int status = fileById.getStatus();
            if (status == -1) {
                XViewLogPrint.JDXLog.d(XView2Constants.TAG, "download failed, retry download, id = " + fileById.getId());
            } else if (status != 0) {
                XViewLogPrint.JDXLog.d(XView2Constants.TAG, "status =" + fileById.getStatus());
            } else {
                XViewLogPrint.JDXLog.d(XView2Constants.TAG, "getFiles --> File not downloaded, download now");
            }
        }
        return fileById;
    }

    public static XViewCache getInstance() {
        if (instance == null) {
            synchronized (XViewCache.class) {
                if (instance == null) {
                    instance = new XViewCache();
                }
            }
        }
        return instance;
    }

    public boolean needDownload(XViewFileEntity xViewFileEntity) {
        if ((xViewFileEntity.filePath == null || !new File(xViewFileEntity.filePath).exists()) && xViewFileEntity.status != 2) {
            xViewFileEntity.filePath = null;
            return true;
        }
        return false;
    }

    public void clear(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        XViewFileEntity fileById = this.mXViewFileStore.getFileById(str2);
        if (fileById instanceof XViewFileEntity) {
            if (!TextUtils.isEmpty(fileById.oldFilePath)) {
                deleteFile(fileById.oldFilePath);
            }
            if (!TextUtils.isEmpty(fileById.filePath)) {
                deleteFile(fileById.filePath);
            }
            fileById.status = 0;
            fileById.old_version_code = 0;
            fileById.oldFilePath = null;
            fileById.filePath = null;
        }
    }

    public void downLoadByEntity(XViewFileEntity xViewFileEntity) {
        XViewDownloadClient xViewDownloadClient;
        if (xViewFileEntity == null || !needDownload(xViewFileEntity) || (xViewDownloadClient = XViewDownloadClient.getInstance()) == null) {
            return;
        }
        xViewDownloadClient.addDownloader(getDownloader(xViewFileEntity));
    }

    public synchronized boolean getFile(String str, String str2, i iVar) {
        if (this.available && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            b fileById = getFileById(str2);
            if (fileById == null || iVar == null) {
                return false;
            }
            if (fileById.getStatus() != 0 && fileById.getStatus() != 2) {
                iVar.update(fileById);
                return true;
            }
            if (this.fileObservers == null) {
                this.fileObservers = new HashMap();
            }
            this.fileObservers.put(str2, iVar);
            return true;
        }
        return false;
    }

    public b getFiles(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getFileById(str);
    }

    public synchronized void logout(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Map<String, i> map = this.fileObservers;
            if (map != null) {
                map.remove(str2);
            }
        }
    }

    @Override // com.jd.hybrid.downloader.h
    public synchronized void notifyChange(b bVar) {
        XViewFileStore xViewFileStore = this.mXViewFileStore;
        if (xViewFileStore != null && (bVar instanceof XViewFileEntity)) {
            xViewFileStore.update((XViewFileEntity) bVar);
        }
    }

    public synchronized void observe(String str, String str2, i iVar) {
        if (this.available) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (this.fileObservers == null) {
                    this.fileObservers = new HashMap();
                }
                this.fileObservers.put(str2, iVar);
            }
        }
    }

    public void preDownLoadCache(ArrayList<XViewConfigEntity.LayersEntity> arrayList) {
        if (XView2Utils.isListEmpty(arrayList)) {
            return;
        }
        if (this.mPreDownLoadResourceEntityList == null) {
            this.mPreDownLoadResourceEntityList = new ArrayList();
        }
        this.mPreDownLoadResourceEntityList.clear();
        Iterator<XViewConfigEntity.LayersEntity> it = arrayList.iterator();
        while (it.hasNext()) {
            XViewConfigEntity.LayersEntity next = it.next();
            Iterator<PreDownLoadResourceEntity> it2 = next.preDownLoadList.iterator();
            boolean z = false;
            int i2 = 0;
            while (it2.hasNext()) {
                PreDownLoadResourceEntity next2 = it2.next();
                int i3 = next2.type;
                if ((i3 == 1 || i3 == 2 || i3 == 4) && (XView2Utils.isConvertBool(next2.canPreload) || XView2Utils.isConvertBool(next2.popAfterDownload))) {
                    XViewFileEntity xViewFileEntity = new XViewFileEntity();
                    xViewFileEntity.url = next2.url;
                    xViewFileEntity.project_priority = next2.priority;
                    xViewFileEntity.id = next2.layerId + next2.url;
                    xViewFileEntity.expiredTime = next.rule.endTime;
                    xViewFileEntity.layerId = next2.layerId;
                    xViewFileEntity.name = next.name;
                    xViewFileEntity.targetId = next.targetId;
                    this.mPreDownLoadResourceEntityList.add(xViewFileEntity);
                    int i4 = next2.priority;
                    if (i4 > i2) {
                        i2 = i4;
                    }
                    z = true;
                }
                if (next2.type == 3) {
                    z = true;
                }
            }
            if (!TextUtils.isEmpty(next.renderData.url) && z) {
                XViewFileEntity xViewFileEntity2 = new XViewFileEntity();
                xViewFileEntity2.url = next.renderData.url;
                xViewFileEntity2.project_priority = i2 + 1;
                xViewFileEntity2.id = next.layerId + next.renderData.url;
                xViewFileEntity2.expiredTime = next.rule.endTime;
                xViewFileEntity2.layerId = next.layerId;
                xViewFileEntity2.name = next.name;
                xViewFileEntity2.targetId = next.targetId;
                xViewFileEntity2.isDsl = true;
                this.mPreDownLoadResourceEntityList.add(xViewFileEntity2);
            }
        }
        Collections.sort(this.mPreDownLoadResourceEntityList, this.comparator);
        if (XView2Utils.isListEmpty(this.mPreDownLoadResourceEntityList)) {
            return;
        }
        XViewFileStore xViewFileStore = this.mXViewFileStore;
        if (xViewFileStore != null) {
            xViewFileStore.parseXViewFileEntityList(this.mPreDownLoadResourceEntityList);
        }
        addDownloader(this.mPreDownLoadResourceEntityList);
    }

    public void retryDownLoader(XViewFileEntity xViewFileEntity) {
        XViewDownloadClient xViewDownloadClient;
        if (TextUtils.isEmpty(xViewFileEntity.url)) {
            return;
        }
        String str = xViewFileEntity.id;
        if (this.retryArray.contains(str) || (xViewDownloadClient = XViewDownloadClient.getInstance()) == null) {
            return;
        }
        this.retryArray.add(str);
        xViewDownloadClient.addDownloader(getDownloader(xViewFileEntity));
    }

    public b getFiles(XViewFileEntity xViewFileEntity) {
        if (xViewFileEntity == null) {
            return null;
        }
        return getFileById(xViewFileEntity.id);
    }
}
