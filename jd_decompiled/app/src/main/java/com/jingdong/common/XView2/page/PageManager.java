package com.jingdong.common.XView2.page;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.entity.PageInfoEntity;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class PageManager {
    private static PageManager instance;
    private PageInfoEntity pageInfo;
    private File pageInfoFile = PageFile.getPageFile().report.file();

    public PageManager() {
        PageInfoEntity loadFile = loadFile();
        this.pageInfo = loadFile;
        if (loadFile == null) {
            this.pageInfo = new PageInfoEntity();
        }
    }

    private void flushFile(String str) {
        FileOutputStream fileOutputStream;
        IOException e2;
        String jSONString;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                jSONString = JDJSON.toJSONString(this.pageInfo);
                fileOutputStream = new FileOutputStream(this.pageInfoFile);
            } catch (IOException e3) {
                fileOutputStream = null;
                e2 = e3;
            } catch (Throwable th) {
                th = th;
                XView2Utils.closeQuietly(fileOutputStream2);
                throw th;
            }
            try {
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "flushFile " + this.pageInfoFile.getPath() + " str:" + jSONString);
                fileOutputStream.write(jSONString.getBytes());
            } catch (IOException e4) {
                e2 = e4;
                XView2Utils.reportXView2Error("flushFile", "NXViewException", str, "\u4e0a\u4f20\u8ba1\u6b21\u6587\u4ef6\u5931\u8d25");
                e2.printStackTrace();
                XView2Utils.closeQuietly(fileOutputStream);
            }
            XView2Utils.closeQuietly(fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            XView2Utils.closeQuietly(fileOutputStream2);
            throw th;
        }
    }

    public static PageManager getInstance() {
        PageManager pageManager = instance;
        if (pageManager == null) {
            PageManager pageManager2 = new PageManager();
            instance = pageManager2;
            return pageManager2;
        }
        return pageManager;
    }

    private ArrayList<PageInfoEntity.PageBasicInfoEntity> getPageBasicInfo() {
        return XView2Utils.isListEmpty(this.pageInfo.pages) ? new ArrayList<>(this.pageInfo.pages) : this.pageInfo.pages;
    }

    private boolean isHasLayerID(String str) {
        Iterator<PageInfoEntity.PageBasicInfoEntity> it = getPageBasicInfo().iterator();
        while (it.hasNext()) {
            PageInfoEntity.PageBasicInfoEntity next = it.next();
            if (!TextUtils.isEmpty(next.layerId) && next.layerId.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private PageInfoEntity loadFile() {
        FileInputStream fileInputStream;
        Throwable th;
        try {
            fileInputStream = new FileInputStream(this.pageInfoFile);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                String str = new String(bArr);
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "loadFile " + this.pageInfoFile.getPath() + " str:" + str);
                return (PageInfoEntity) JDJSON.parseObject(str, PageInfoEntity.class);
            } catch (Throwable th2) {
                th = th2;
                try {
                    XView2Utils.reportXView2Error("loadFile", "NXViewException", "", "\u52a0\u8f7d\u8ba1\u6b21\u6587\u4ef6\u5931\u8d25");
                    th.printStackTrace();
                    XView2Utils.closeQuietly(fileInputStream);
                    return new PageInfoEntity();
                } finally {
                    XView2Utils.closeQuietly(fileInputStream);
                }
            }
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
        }
    }

    public int getDayTimes(String str) {
        Iterator<PageInfoEntity.PageBasicInfoEntity> it = getPageBasicInfo().iterator();
        while (it.hasNext()) {
            PageInfoEntity.PageBasicInfoEntity next = it.next();
            if (!TextUtils.isEmpty(next.layerId) && next.layerId.equals(str)) {
                return next.dayTimes;
            }
        }
        return 0;
    }

    public long getLastRealTime(String str) {
        Iterator<PageInfoEntity.PageBasicInfoEntity> it = getPageBasicInfo().iterator();
        while (it.hasNext()) {
            PageInfoEntity.PageBasicInfoEntity next = it.next();
            if (!TextUtils.isEmpty(next.layerId) && next.layerId.equals(str)) {
                return next.lastRealTime;
            }
        }
        return 0L;
    }

    public int getShowedTimes(String str) {
        Iterator<PageInfoEntity.PageBasicInfoEntity> it = getPageBasicInfo().iterator();
        while (it.hasNext()) {
            PageInfoEntity.PageBasicInfoEntity next = it.next();
            if (!TextUtils.isEmpty(next.layerId) && next.layerId.equals(str)) {
                return next.showedTimes;
            }
        }
        return 0;
    }

    public int getTodayTimes(String str) {
        long lastRealTime = getInstance().getLastRealTime(str);
        int totalTimes = getInstance().getTotalTimes(str);
        int showedTimes = getInstance().getShowedTimes(str);
        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "CommonUtils.isToday(new Date(lastRealTime))" + XView2Utils.isToday(new Date(lastRealTime)));
        if (!XView2Utils.isToday(new Date(lastRealTime))) {
            getInstance().savePageInfo(str, 0, totalTimes, showedTimes, 0L);
            return 0;
        }
        return getInstance().getDayTimes(str);
    }

    public int getTotalTimes(String str) {
        Iterator<PageInfoEntity.PageBasicInfoEntity> it = getPageBasicInfo().iterator();
        while (it.hasNext()) {
            PageInfoEntity.PageBasicInfoEntity next = it.next();
            if (!TextUtils.isEmpty(next.layerId) && next.layerId.equals(str)) {
                return next.times;
            }
        }
        return 0;
    }

    public void savePageInfo(String str, int i2, int i3, int i4, long j2) {
        if (isHasLayerID(str)) {
            Iterator<PageInfoEntity.PageBasicInfoEntity> it = getPageBasicInfo().iterator();
            while (it.hasNext()) {
                PageInfoEntity.PageBasicInfoEntity next = it.next();
                if (!TextUtils.isEmpty(next.layerId) && next.layerId.equals(str)) {
                    next.dayTimes = i2;
                    next.lastRealTime = j2;
                    next.times = i3;
                    next.showedTimes = i4;
                }
            }
        } else {
            PageInfoEntity.PageBasicInfoEntity pageBasicInfoEntity = new PageInfoEntity.PageBasicInfoEntity();
            pageBasicInfoEntity.dayTimes = i2;
            pageBasicInfoEntity.layerId = str;
            pageBasicInfoEntity.lastRealTime = j2;
            pageBasicInfoEntity.times = i3;
            pageBasicInfoEntity.showedTimes = 0;
            this.pageInfo.pages.add(pageBasicInfoEntity);
        }
        flushFile(str);
    }

    public void updatePageInfo(String str, int i2, int i3) {
        int showedTimes = getInstance().getShowedTimes(str);
        getInstance().savePageInfo(str, getInstance().getDayTimes(str) + 1, i2, showedTimes + 1, System.currentTimeMillis());
    }
}
