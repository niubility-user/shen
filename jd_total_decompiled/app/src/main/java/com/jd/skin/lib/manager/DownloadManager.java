package com.jd.skin.lib.manager;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jd.skin.lib.Utils.FileUtils;
import com.jd.skin.lib.inter.OnDownloadPicListener;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes18.dex */
public class DownloadManager {
    private static DownloadManager mInstance;

    /* loaded from: classes18.dex */
    public class DownloadTask implements Runnable {
        private String code;
        private File file;
        private String imageMd5;
        private boolean isNeedMd5;
        private String localPath;
        private OnDownloadPicListener mDownloadPicListener;
        private String path;
        private String tag;

        public DownloadTask(String str, String str2, String str3, OnDownloadPicListener onDownloadPicListener, String str4, String str5, boolean z) {
            DownloadManager.this = r1;
            this.code = str;
            this.path = str2;
            this.localPath = str3;
            this.mDownloadPicListener = onDownloadPicListener;
            this.tag = str4;
            this.imageMd5 = str5;
            this.isNeedMd5 = z;
        }

        private void downFailed() {
            if (this.file.exists()) {
                this.file.delete();
            }
            if (this.mDownloadPicListener != null) {
                if (OKLog.D) {
                    OKLog.d("JDSkinSDK", "Image down faile-->");
                }
                this.mDownloadPicListener.onDownloadComplet(false, this.code, this.path, this.localPath, this.tag);
            }
        }

        private void downSuccess() {
            if (this.mDownloadPicListener != null) {
                if (OKLog.D) {
                    OKLog.d("JDSkinSDK", "Image down success-->");
                }
                this.mDownloadPicListener.onDownloadComplet(true, this.code, this.path, this.localPath, this.tag);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.file = new File(this.localPath);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.path).openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT, "*/*");
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_CHARSET, "utf-8");
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    int contentLength = httpURLConnection.getContentLength();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    FileOutputStream fileOutputStream = new FileOutputStream(this.file, false);
                    byte[] bArr = new byte[10240];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                        fileOutputStream.flush();
                    }
                    inputStream.close();
                    fileOutputStream.close();
                    if (!TextUtils.isEmpty(this.imageMd5) && this.isNeedMd5) {
                        String md5 = FileUtils.getMD5(this.file.getAbsolutePath());
                        if (OKLog.D) {
                            OKLog.d("JDSkinSDK", "ImageMd5-->" + this.imageMd5 + "--" + md5);
                        }
                        if (md5 != null) {
                            if (this.imageMd5.equals(md5)) {
                                downSuccess();
                                return;
                            } else {
                                downFailed();
                                return;
                            }
                        } else if (this.file.exists() && this.file.length() == contentLength) {
                            downSuccess();
                            return;
                        } else {
                            downFailed();
                            return;
                        }
                    }
                    if (OKLog.D) {
                        OKLog.d("JDSkinSDK", "fileLength-->" + contentLength + "--" + this.file.length());
                    }
                    if (this.file.exists() && this.file.length() == contentLength) {
                        downSuccess();
                        return;
                    } else {
                        downFailed();
                        return;
                    }
                }
                ExceptionReporter.reportExceptionToBugly(new Exception("PaaS_skin_picture_down_failed_" + this.code + CartConstant.KEY_YB_INFO_LINK + this.tag + CartConstant.KEY_YB_INFO_LINK + responseCode));
                downFailed();
            } catch (Exception unused) {
                downFailed();
            }
        }
    }

    private DownloadManager() {
    }

    public static DownloadManager getInstance() {
        if (mInstance == null) {
            synchronized (DownloadManager.class) {
                if (mInstance == null) {
                    mInstance = new DownloadManager();
                }
            }
        }
        return mInstance;
    }

    public void downloadSingle(String str, String str2, String str3, OnDownloadPicListener onDownloadPicListener, String str4, String str5, boolean z) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            ThreadPoolManager.getDownloadPool().execute(new DownloadTask(str, str2, str3, onDownloadPicListener, str4, str5, z));
        } else if (onDownloadPicListener != null) {
            onDownloadPicListener.onDownloadComplet(false, str, str2, str3, str4);
        }
    }
}
