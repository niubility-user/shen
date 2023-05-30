package com.jingdong.common.XView2.utils;

import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes5.dex */
public class XView2VideoDownloadCommonUtil {
    public static final long DAY = 86400000;
    public static final long FIVE_MINUTE = 300000;
    public static final long HOUR = 3600000;
    public static final long ONE_MINUTE = 60000;
    public static final long ONE_SECOND = 1000;
    private static final String TAG = "XView2";
    public static final long TEN_MINUTE = 600000;
    public static long VIDEO_FILE_EXPIRED_TIME = 604800000;
    private static CopyOnWriteArrayList<String> mDownloadsList = new CopyOnWriteArrayList<>();

    /* loaded from: classes5.dex */
    public static abstract class BaseDownLoadListener {
        public void onEnd(boolean z, String str) {
        }

        public void onError(String str) {
        }
    }

    public static void clearExpiredVideoFile(final File file) {
        XView2SubThreadCtrl.addLongTimeTask(new BaseRunnable() { // from class: com.jingdong.common.XView2.utils.XView2VideoDownloadCommonUtil.2
            @Override // com.jingdong.common.XView2.utils.BaseRunnable
            protected void safeRun() {
                try {
                    XView2VideoDownloadCommonUtil.clearExpiredVideoFileRun(file);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void clearExpiredVideoFileRun(File file) {
        File[] listFiles;
        int i2;
        if (file == null || !file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        char c2 = 0;
        int i3 = 0;
        while (i3 < listFiles.length) {
            File file2 = listFiles[i3];
            if (Log.D) {
                Object[] objArr = new Object[7];
                objArr[c2] = "current1:";
                objArr[1] = Long.valueOf(currentTimeMillis);
                objArr[2] = " last:";
                StringBuilder sb = new StringBuilder();
                i2 = i3;
                sb.append(file2.lastModified());
                sb.append(" hold:");
                objArr[3] = sb.toString();
                objArr[4] = Long.valueOf(currentTimeMillis - file2.lastModified());
                objArr[5] = " most:";
                objArr[6] = Long.valueOf(VIDEO_FILE_EXPIRED_TIME);
                OKLog.e("XView2", objArr);
            } else {
                i2 = i3;
            }
            if (file2 != null && file2.exists() && currentTimeMillis - file2.lastModified() > VIDEO_FILE_EXPIRED_TIME) {
                if (Log.D) {
                    OKLog.e("XView2", "current2:", Long.valueOf(currentTimeMillis), " last:", file2.lastModified() + " hold:", Long.valueOf(currentTimeMillis - file2.lastModified()), " most:", Long.valueOf(VIDEO_FILE_EXPIRED_TIME));
                }
                file2.delete();
            }
            i3 = i2 + 1;
            c2 = 0;
        }
    }

    public static void downloadImg(Map.Entry<String, Boolean> entry, HttpGroup.HttpTaskListener httpTaskListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(entry.getKey());
        httpSetting.setConnectTimeout(5000);
        httpSetting.setAttempts(1);
        httpSetting.setCacheMode(0);
        httpSetting.setType(5000);
        httpSetting.setListener(httpTaskListener);
        httpSetting.setNeedShareImage(false);
        httpSetting.setLocalFileCacheTime(2592000000L);
        HttpGroupUtils.getHttpGroupaAsynPool(5000).add(httpSetting);
    }

    public static void downloadVideo(String str, String str2, String str3, String str4, final BaseDownLoadListener baseDownLoadListener) {
        if (Log.D) {
            Log.d("XView2", "downloadVideo ===>>> " + str3 + " : " + str4);
        }
        clearExpiredVideoFile(getInternalDirectory(str));
        final String str5 = str3 + CartConstant.KEY_YB_INFO_LINK + str4;
        if (mDownloadsList.contains(str5)) {
            return;
        }
        String format = String.format("jingdong" + str, new Object[0]);
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setChildDirName(format);
        fileGuider.setImmutable(false);
        fileGuider.setFileName(Md5Encrypt.md5(str3) + str2);
        fileGuider.setMode(1);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setSavePath(fileGuider);
        httpSetting.setUrl(str4);
        httpSetting.setCacheMode(0);
        if (Log.D) {
            Log.d("XView2", "httpSetting ");
        }
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.XView2.utils.XView2VideoDownloadCommonUtil.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (Log.D) {
                    Log.d("XView2", "onEnd /" + httpResponse);
                }
                if (httpResponse != null) {
                    XView2VideoDownloadCommonUtil.mDownloadsList.remove(str5);
                    File saveFile = httpResponse.getSaveFile();
                    if (saveFile == null) {
                        BaseDownLoadListener baseDownLoadListener2 = baseDownLoadListener;
                        if (baseDownLoadListener2 != null) {
                            baseDownLoadListener2.onEnd(false, null);
                            return;
                        }
                        return;
                    }
                    if (Log.D) {
                        Log.d("XView2", "onEnd ===>>> " + saveFile.getAbsolutePath());
                    }
                    BaseDownLoadListener baseDownLoadListener3 = baseDownLoadListener;
                    if (baseDownLoadListener3 != null) {
                        baseDownLoadListener3.onEnd(true, saveFile.getAbsolutePath());
                        return;
                    }
                    return;
                }
                BaseDownLoadListener baseDownLoadListener4 = baseDownLoadListener;
                if (baseDownLoadListener4 != null) {
                    baseDownLoadListener4.onEnd(false, null);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (Log.D) {
                    Log.d("XView2", "onError ===>>> " + httpError.getException() + "/" + httpError.getMessage());
                }
                BaseDownLoadListener baseDownLoadListener2 = baseDownLoadListener;
                if (baseDownLoadListener2 != null && httpError != null) {
                    baseDownLoadListener2.onError(httpError.toString());
                }
                XView2VideoDownloadCommonUtil.mDownloadsList.remove(str5);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                if (Log.D) {
                    Log.d("XView2", "onReady /" + httpSettingParams);
                }
                XView2VideoDownloadCommonUtil.mDownloadsList.add(str5);
            }
        });
        httpSetting.setType(500);
        httpSetting.setBreakpointTransmission(false);
        httpSetting.setAttempts(1);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static File getInternalDirectory(String str) {
        return FileService.getInternalDirectory(str, 1, true);
    }

    public static String getVideoPathNameById(String str, String str2, String str3) {
        File internalDirectory = getInternalDirectory(str);
        if (internalDirectory == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(internalDirectory.getPath());
        stringBuffer.append("/");
        stringBuffer.append(Md5Encrypt.md5(str3));
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }
}
