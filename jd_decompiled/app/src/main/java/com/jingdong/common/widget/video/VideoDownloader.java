package com.jingdong.common.widget.video;

import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.io.File;

/* loaded from: classes12.dex */
public class VideoDownloader {
    private static String SUBDIR_STR = "ijkSVideoCache";
    private static String SUFFIX_STR = ".mp4";
    private static final String TAG = "VideoDownloader";
    private static final long VIDEO_FILE_EXPIRED_TIME = 259200000;

    /* loaded from: classes12.dex */
    public interface DownloadListener {
        void fail();

        void success(String str, File file);
    }

    /* loaded from: classes12.dex */
    private static class FileDownloader {
        private DownloadListener mListener;
        private String mUrl;

        protected FileDownloader(DownloadListener downloadListener) {
            this.mListener = downloadListener;
        }

        protected void start(String str, String str2, String str3) {
            this.mUrl = str3;
            FileGuider fileGuider = new FileGuider();
            fileGuider.setSpace(1);
            fileGuider.setChildDirName(str);
            fileGuider.setImmutable(false);
            fileGuider.setFileName(str2);
            fileGuider.setMode(1);
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setUrl(str3);
            httpSetting.setSavePath(fileGuider);
            httpSetting.setCacheMode(0);
            httpSetting.setType(500);
            httpSetting.setBreakpointTransmission(true);
            httpSetting.setAttempts(1);
            httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.widget.video.VideoDownloader.FileDownloader.1
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    File saveFile = httpResponse.getSaveFile();
                    if (FileDownloader.this.mListener != null) {
                        if (saveFile != null) {
                            FileDownloader.this.mListener.success(FileDownloader.this.mUrl, saveFile);
                        } else {
                            FileDownloader.this.mListener.fail();
                        }
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    if (FileDownloader.this.mListener != null) {
                        FileDownloader.this.mListener.fail();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    private static void clearExpiredVideoFile(final File file) {
        new Thread(new Runnable() { // from class: com.jingdong.common.widget.video.VideoDownloader.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    VideoDownloader.clearExpiredVideoFileRun(file);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void clearExpiredVideoFileRun(File file) {
        File[] listFiles;
        if (file == null || !file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        for (File file2 : listFiles) {
            if (Log.D) {
                Log.d(TAG, "current:" + currentTimeMillis + " last:" + file2.lastModified() + " hold:" + (currentTimeMillis - file2.lastModified()) + " most:259200000");
            }
            if (file2 != null && file2.exists() && currentTimeMillis - file2.lastModified() > 259200000) {
                if (Log.D) {
                    Log.d(TAG, "delete:" + file2.getAbsolutePath());
                }
                file2.delete();
            }
        }
    }

    public static void downLoadVideo(String str, DownloadListener downloadListener) {
        clearExpiredVideoFile(getVideoDirectory());
        new FileDownloader(downloadListener).start("jingdong" + SUBDIR_STR, getFileName(str), str);
    }

    private static String getFileName(String str) {
        return Md5Encrypt.md5(str) + SUFFIX_STR;
    }

    private static File getVideoDirectory() {
        return FileService.getInternalDirectory(SUBDIR_STR, 1, true);
    }

    public static File getVideoFile(String str) {
        File file = new File(getVideoDirectory() + File.separator + getFileName(str));
        if (!file.exists() || file.length() <= 1000) {
            return null;
        }
        return file;
    }
}
