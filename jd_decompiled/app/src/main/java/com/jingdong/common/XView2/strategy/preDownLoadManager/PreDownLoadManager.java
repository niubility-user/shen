package com.jingdong.common.XView2.strategy.preDownLoadManager;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.strategy.downloader.XViewCache;
import com.jingdong.common.XView2.utils.BaseRunnable;
import com.jingdong.common.XView2.utils.XView2SubThreadCtrl;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.XView2VideoDownloadCommonUtil;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class PreDownLoadManager implements IPreDownLoad {
    public static final String VIDEO_ID = "VIDEO_ID";
    public static final String VIDEO_SKU_BG_DIR = "XView2Video";
    public static final String VIDEO_SKU_PATH = "XVIEW2_PATH";
    public static final String VIDEO_SKU_SIZE = "XVIEW2_SIZE";
    public static final String VIDEO_SKU_SUFFIX = ".mp4";
    public static final String VIDEO_URL = "VIDEO_URL";
    public ArrayList<XViewConfigEntity.LayersEntity> mNeedCacheArrayList;
    private XViewConfigEntity mXViewConfigEntity;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class XView2Instance {
        public static final PreDownLoadManager INSTANCE = new PreDownLoadManager();

        private XView2Instance() {
        }
    }

    private static boolean checkFileExists(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        String md5 = Md5Encrypt.md5(str3);
        String videoPathNameById = XView2VideoDownloadCommonUtil.getVideoPathNameById(str, str2, md5);
        return str4.equals(isLegalFile(!TextUtils.isEmpty(videoPathNameById) ? new File(videoPathNameById) : null, md5, str5, str6, str7, str8));
    }

    public static PreDownLoadManager getManager() {
        return XView2Instance.INSTANCE;
    }

    public static String isLegalFile(File file, String str, String str2, String str3, String str4, String str5) {
        if (file != null && file.exists()) {
            SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
            String string = jdSharedPreferences.getString(str2 + str, null);
            long j2 = jdSharedPreferences.getLong(str3 + str, 0L);
            String string2 = jdSharedPreferences.getString(str4 + str, null);
            String string3 = jdSharedPreferences.getString(str5 + str, null);
            if (j2 > 0 && j2 == file.length() && !TextUtils.isEmpty(string) && string.equals(file.getAbsolutePath()) && str.equals(string2)) {
                return string3;
            }
        }
        return null;
    }

    public static void saveXView2DownloadOkState(File file, String str, String str2) {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putString(VIDEO_SKU_PATH + str, file.getAbsolutePath());
        edit.putLong(VIDEO_SKU_SIZE + str, file.length());
        edit.putString(VIDEO_ID + str, str);
        edit.putString(VIDEO_URL + str, str2);
        edit.apply();
    }

    @Override // com.jingdong.common.XView2.strategy.preDownLoadManager.IPreDownLoad
    public void downLoadImage(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (isImageLocalSuccess(str)) {
                return;
            }
            JDImageUtils.loadImageToDiskCache(str, new JDImageLoadingListener() { // from class: com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager.1
                {
                    PreDownLoadManager.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str2, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                    XViewLogPrint.JDXLog.e(XView2Constants.TAG, "downloadImg onLoadingComplete " + str);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    if (jDFailReason != null) {
                        XView2Utils.reportXView2Error("downLoadImage", "NXViewException", str, str + "\u56fe\u7247\u9884\u52a0\u8f7d\u5931\u8d25\uff0c\u5931\u8d25\u539f\u56e0\u4e3a" + jDFailReason.getType());
                        return;
                    }
                    XView2Utils.reportXView2Error("downLoadImage", "NXViewException", str, str + "\u56fe\u7247\u9884\u52a0\u8f7d\u5931\u8d25");
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str2, View view) {
                }
            });
        } catch (Exception e2) {
            XView2Utils.reportXView2Error("downLoadImage", "NXViewException", str, e2.getMessage());
        }
    }

    @Override // com.jingdong.common.XView2.strategy.preDownLoadManager.IPreDownLoad
    public void downLoadVideo(final String str, final String str2, int i2) {
        if (checkFileExists(VIDEO_SKU_BG_DIR, VIDEO_SKU_SUFFIX, str2 + str, str, VIDEO_SKU_PATH, VIDEO_SKU_SIZE, VIDEO_ID, VIDEO_URL)) {
            return;
        }
        if (i2 > 0) {
            XView2VideoDownloadCommonUtil.VIDEO_FILE_EXPIRED_TIME = i2 * 86400000;
        }
        XView2SubThreadCtrl.addLongTimeTask(new BaseRunnable() { // from class: com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager.2
            {
                PreDownLoadManager.this = this;
            }

            @Override // com.jingdong.common.XView2.utils.BaseRunnable
            protected void safeRun() {
                final String md5 = Md5Encrypt.md5(str2 + str);
                XView2VideoDownloadCommonUtil.downloadVideo(PreDownLoadManager.VIDEO_SKU_BG_DIR, PreDownLoadManager.VIDEO_SKU_SUFFIX, md5, str, new XView2VideoDownloadCommonUtil.BaseDownLoadListener() { // from class: com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager.2.1
                    {
                        AnonymousClass2.this = this;
                    }

                    @Override // com.jingdong.common.XView2.utils.XView2VideoDownloadCommonUtil.BaseDownLoadListener
                    public void onEnd(boolean z, String str3) {
                        if (z) {
                            File file = new File(str3);
                            if (!file.exists() || file.length() <= 0) {
                                return;
                            }
                            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "downloadVideo success " + str3 + md5);
                            PreDownLoadManager.saveXView2DownloadOkState(file, md5, str);
                            return;
                        }
                        XView2Utils.reportXView2Error("downLoadVideo", "NXViewException", str, "strPath\u4e3a\u7a7a");
                    }

                    @Override // com.jingdong.common.XView2.utils.XView2VideoDownloadCommonUtil.BaseDownLoadListener
                    public void onError(String str3) {
                        XView2Utils.reportXView2Error("downLoadVideo", "NXViewException", str, str3);
                    }
                });
            }
        });
    }

    public boolean isImageLocalSuccess(String str) {
        File imageDiskCacheFile;
        if (!TextUtils.isEmpty(str) && (imageDiskCacheFile = JDImageUtils.getImageDiskCacheFile(str)) != null && imageDiskCacheFile.exists() && imageDiskCacheFile.length() > 0) {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u56fe\u7247\u8bf7\u6c42\u5df2\u7ecf\u7f13\u5b58\u6210\u529f");
            return true;
        }
        return false;
    }

    public void preDownLoadCache(XViewConfigEntity xViewConfigEntity) {
        XViewConfigEntity.RenderDataEntity renderDataEntity;
        if (xViewConfigEntity == null) {
            return;
        }
        this.mXViewConfigEntity = xViewConfigEntity;
        XView2VideoDownloadCommonUtil.clearExpiredVideoFile(XView2VideoDownloadCommonUtil.getInternalDirectory(VIDEO_SKU_BG_DIR));
        XViewConfigEntity xViewConfigEntity2 = this.mXViewConfigEntity;
        if (xViewConfigEntity2 == null || XView2Utils.isListEmpty(xViewConfigEntity2.targets)) {
            return;
        }
        Iterator<XViewConfigEntity.TargetsEntity> it = this.mXViewConfigEntity.targets.iterator();
        while (it.hasNext()) {
            Iterator<XViewConfigEntity.LayersEntity> it2 = it.next().layers.iterator();
            while (it2.hasNext()) {
                XViewConfigEntity.LayersEntity next = it2.next();
                XViewConfigEntity.RuleEntity ruleEntity = next.rule;
                if (ruleEntity != null && XView2Utils.isConvertBool(ruleEntity.loadRequiredBefore) && XView2Utils.isWithInTime(next) && !TextUtils.isEmpty(next.layerId) && (renderDataEntity = next.renderData) != null && !TextUtils.isEmpty(renderDataEntity.url)) {
                    Iterator<XViewConfigEntity.ControlEntity> it3 = next.controls.iterator();
                    while (it3.hasNext()) {
                        XViewConfigEntity.ControlEntity next2 = it3.next();
                        if (!"1".equals(next2.type) && XView2Utils.isConvertBool(next2.bufEnable)) {
                            downLoadImage(next2.img);
                        }
                    }
                    if (XView2Utils.isConvertBool(next.renderData.bufEnable)) {
                        if (next.renderType == 1) {
                            downLoadImage(next.renderData.url);
                        }
                        if (next.renderType == 3) {
                            XViewConfigEntity.RenderDataEntity renderDataEntity2 = next.renderData;
                            downLoadVideo(renderDataEntity2.url, next.layerId, renderDataEntity2.bufDays);
                        }
                    }
                }
            }
        }
    }

    public void preDownLoadXViewCache(XViewConfigEntity xViewConfigEntity) {
        if (xViewConfigEntity == null) {
            return;
        }
        if (this.mNeedCacheArrayList == null) {
            this.mNeedCacheArrayList = new ArrayList<>();
        }
        this.mNeedCacheArrayList.clear();
        if (SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_PREDOWNLOAD, false)) {
            if (xViewConfigEntity != null && !XView2Utils.isListEmpty(xViewConfigEntity.targets)) {
                Iterator<XViewConfigEntity.TargetsEntity> it = xViewConfigEntity.targets.iterator();
                while (it.hasNext()) {
                    Iterator<XViewConfigEntity.LayersEntity> it2 = it.next().layers.iterator();
                    while (it2.hasNext()) {
                        XViewConfigEntity.LayersEntity next = it2.next();
                        if (next.renderType == 6 && !XView2Utils.isListEmpty(next.preDownLoadList)) {
                            this.mNeedCacheArrayList.add(next);
                        }
                    }
                }
            }
            XViewCache.getInstance().preDownLoadCache(this.mNeedCacheArrayList);
        }
    }
}
