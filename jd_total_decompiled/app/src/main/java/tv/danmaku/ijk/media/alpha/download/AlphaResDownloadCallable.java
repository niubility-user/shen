package tv.danmaku.ijk.media.alpha.download;

import android.os.Environment;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import tv.danmaku.ijk.media.alpha.IAlphaListener;
import tv.danmaku.ijk.media.domain.entity.AlphaAnimBean;
import tv.danmaku.ijk.media.utils.DebugLog;
import tv.danmaku.ijk.media.utils.PlayerToolsUtil;

/* loaded from: classes11.dex */
public class AlphaResDownloadCallable implements Callable<String> {
    private static final String TAG = "AlphaResDownloadCallable";
    private final AlphaAnimBean alphaAnimBean;
    private final AlphaDownloadConfig downloadConfig;
    private final IAlphaListener.OnEventListener eventListener;

    public AlphaResDownloadCallable(AlphaAnimBean alphaAnimBean) {
        this(alphaAnimBean, null);
    }

    private boolean checkDownloadFile(File file) {
        if (file != null) {
            String fileMD5 = PlayerToolsUtil.getFileMD5(file);
            if (fileMD5 == null || !fileMD5.equals(this.alphaAnimBean.getMd5())) {
                file.deleteOnExit();
                return false;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0173 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01ae A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x017d A[Catch: IOException -> 0x0144, TRY_ENTER, TRY_LEAVE, TryCatch #10 {IOException -> 0x0144, blocks: (B:59:0x0140, B:90:0x017d), top: B:116:0x0071 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x019f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x0145 -> B:115:0x0180). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String downloadFromNet() {
        String str;
        BufferedInputStream bufferedInputStream;
        FileOutputStream fileOutputStream;
        File file;
        String contentType;
        AlphaAnimBean alphaAnimBean = this.alphaAnimBean;
        String str2 = "";
        if (alphaAnimBean != null && !TextUtils.isEmpty(alphaAnimBean.getDownloadUrl()) && !this.alphaAnimBean.isExpired()) {
            AlphaDownloadConfig alphaDownloadConfig = this.downloadConfig;
            if (alphaDownloadConfig != null && !TextUtils.isEmpty(alphaDownloadConfig.getCacheDirPath())) {
                str = this.downloadConfig.getCacheDirPath() + this.alphaAnimBean.getAnimName();
            } else {
                str = Environment.getExternalStorageState() + "/JDAlphaVideo/" + this.alphaAnimBean.getAnimName();
            }
            IAlphaListener.OnEventListener onEventListener = this.eventListener;
            if (onEventListener != null) {
                onEventListener.onEvent(10);
            }
            long currentTimeMillis = System.currentTimeMillis();
            HttpURLConnection httpURLConnection = null;
            try {
                try {
                    file = new File(str);
                } catch (Exception e2) {
                    e = e2;
                    bufferedInputStream = null;
                    fileOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = null;
                    fileOutputStream = null;
                }
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            if (generateAndCheckFile(file)) {
                IAlphaListener.OnEventListener onEventListener2 = this.eventListener;
                if (onEventListener2 != null) {
                    onEventListener2.onEvent(12);
                }
                return file.getAbsolutePath();
            }
            File file2 = new File(str);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(this.alphaAnimBean.getDownloadUrl()).openConnection();
            try {
                httpURLConnection2.setConnectTimeout(5000);
                httpURLConnection2.setReadTimeout(5000);
                contentType = httpURLConnection2.getContentType();
            } catch (Exception e4) {
                e = e4;
                bufferedInputStream = null;
                fileOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
                fileOutputStream = null;
            }
            if (TextUtils.isEmpty(contentType)) {
                DebugLog.e(TAG, "video mime is empty: " + contentType);
                IAlphaListener.OnEventListener onEventListener3 = this.eventListener;
                if (onEventListener3 != null) {
                    onEventListener3.onEvent(13);
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return "";
            }
            int responseCode = httpURLConnection2.getResponseCode();
            DebugLog.i(TAG, "response Code = " + responseCode);
            bufferedInputStream = new BufferedInputStream(httpURLConnection2.getInputStream());
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Exception e5) {
                e = e5;
                fileOutputStream = null;
                httpURLConnection = httpURLConnection2;
                e = e;
                try {
                    e.printStackTrace();
                    if (httpURLConnection != null) {
                    }
                    if (bufferedInputStream != null) {
                    }
                    if (fileOutputStream != null) {
                    }
                    DebugLog.i(TAG, "download time = " + (System.currentTimeMillis() - currentTimeMillis));
                    return str2;
                } catch (Throwable th3) {
                    th = th3;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
            }
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                    fileOutputStream.flush();
                }
                bufferedInputStream.close();
                fileOutputStream.close();
                if (checkDownloadFile(file2)) {
                    IAlphaListener.OnEventListener onEventListener4 = this.eventListener;
                    if (onEventListener4 != null) {
                        onEventListener4.onEvent(11);
                    }
                    str2 = file2.getAbsolutePath();
                } else {
                    IAlphaListener.OnEventListener onEventListener5 = this.eventListener;
                    if (onEventListener5 != null) {
                        onEventListener5.onEvent(13);
                    }
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                try {
                    bufferedInputStream.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
                fileOutputStream.close();
            } catch (Exception e9) {
                httpURLConnection = httpURLConnection2;
                e = e9;
                e.printStackTrace();
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e10) {
                        e10.printStackTrace();
                    }
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                DebugLog.i(TAG, "download time = " + (System.currentTimeMillis() - currentTimeMillis));
                return str2;
            } catch (Throwable th5) {
                th = th5;
                httpURLConnection = httpURLConnection2;
                th = th;
                if (httpURLConnection != null) {
                }
                if (bufferedInputStream != null) {
                }
                if (fileOutputStream != null) {
                }
                throw th;
            }
            DebugLog.i(TAG, "download time = " + (System.currentTimeMillis() - currentTimeMillis));
            return str2;
        }
        DebugLog.e(TAG, "download can not start");
        return "";
    }

    private boolean generateAndCheckFile(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        String fileMD5 = PlayerToolsUtil.getFileMD5(file);
        if (fileMD5 == null || !fileMD5.equals(this.alphaAnimBean.getMd5())) {
            file.deleteOnExit();
            return false;
        }
        return true;
    }

    public AlphaResDownloadCallable(AlphaAnimBean alphaAnimBean, AlphaDownloadConfig alphaDownloadConfig) {
        this(alphaAnimBean, alphaDownloadConfig, null);
    }

    @Override // java.util.concurrent.Callable
    public String call() {
        return downloadFromNet();
    }

    public AlphaResDownloadCallable(AlphaAnimBean alphaAnimBean, AlphaDownloadConfig alphaDownloadConfig, IAlphaListener.OnEventListener onEventListener) {
        this.alphaAnimBean = alphaAnimBean;
        this.downloadConfig = alphaDownloadConfig;
        this.eventListener = onEventListener;
    }
}
