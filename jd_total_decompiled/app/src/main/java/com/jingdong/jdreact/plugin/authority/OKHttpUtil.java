package com.jingdong.jdreact.plugin.authority;

import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* loaded from: classes13.dex */
public class OKHttpUtil {

    /* loaded from: classes13.dex */
    public interface DownloadListener {
        void onDownloadFailed();

        void onDownloadStart();

        void onDownloadSuccess(String str);

        void onDownloading(long j2, long j3);
    }

    public static void download(String str, final String str2, final DownloadListener downloadListener) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Request build = new Request.Builder().url(str).build();
        if (downloadListener != null) {
            downloadListener.onDownloadStart();
        }
        new OkHttpClient().newCall(build).enqueue(new Callback() { // from class: com.jingdong.jdreact.plugin.authority.OKHttpUtil.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                DownloadListener downloadListener2 = DownloadListener.this;
                if (downloadListener2 != null) {
                    downloadListener2.onDownloadFailed();
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:34:0x0073  */
            /* JADX WARN: Removed duplicated region for block: B:36:0x0078  */
            /* JADX WARN: Type inference failed for: r0v0, types: [java.io.FileOutputStream, java.io.InputStream] */
            /* JADX WARN: Type inference failed for: r0v1, types: [java.io.InputStream] */
            /* JADX WARN: Type inference failed for: r0v2 */
            /* JADX WARN: Type inference failed for: r0v3, types: [java.io.InputStream] */
            /* JADX WARN: Type inference failed for: r0v4 */
            @Override // okhttp3.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onResponse(Call call, Response response) throws IOException {
                FileOutputStream fileOutputStream;
                byte[] bArr = new byte[2048];
                ?? r0 = 0;
                r0 = 0;
                try {
                    try {
                    } catch (Exception unused) {
                        fileOutputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        if (0 != 0) {
                        }
                        if (0 != 0) {
                        }
                        throw th;
                    }
                    if (!OKHttpUtil.prepareSaveFile(str2)) {
                        DownloadListener downloadListener2 = DownloadListener.this;
                        if (downloadListener2 != null) {
                            downloadListener2.onDownloadFailed();
                            return;
                        }
                        return;
                    }
                    fileOutputStream = new FileOutputStream(new File(str2));
                    try {
                        r0 = response.body().byteStream();
                        long contentLength = response.body().contentLength();
                        long j2 = 0;
                        while (true) {
                            int read = r0.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                            j2 += read;
                            DownloadListener downloadListener3 = DownloadListener.this;
                            if (downloadListener3 != null) {
                                downloadListener3.onDownloading(contentLength, j2);
                            }
                        }
                        fileOutputStream.flush();
                        DownloadListener downloadListener4 = DownloadListener.this;
                        if (downloadListener4 != null) {
                            downloadListener4.onDownloadSuccess(str2);
                        }
                        if (r0 != 0) {
                            r0.close();
                        }
                    } catch (Exception unused2) {
                        DownloadListener downloadListener5 = DownloadListener.this;
                        if (downloadListener5 != null) {
                            downloadListener5.onDownloadFailed();
                        }
                        if (r0 != 0) {
                            r0.close();
                        }
                        if (fileOutputStream == null) {
                            return;
                        }
                        fileOutputStream.close();
                    }
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th = th2;
                    if (0 != 0) {
                        r0.close();
                    }
                    if (0 != 0) {
                        r0.close();
                    }
                    throw th;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean prepareSaveFile(String str) {
        int lastIndexOf;
        if (TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf(File.separator)) == -1) {
            return false;
        }
        File file = new File(str.substring(0, lastIndexOf));
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(str);
        if (file2.exists()) {
            file2.delete();
            return true;
        }
        return true;
    }

    public static void sendGetRequest(String str, Callback callback) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        new OkHttpClient().newCall(new Request.Builder().url(str).build()).enqueue(callback);
    }

    public static void sendPostRequest(String str, RequestBody requestBody, Callback callback) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        new OkHttpClient().newCall(new Request.Builder().url(str).post(requestBody).build()).enqueue(callback);
    }

    public static void sendRequest(OkHttpClient okHttpClient, String str, boolean z, RequestBody requestBody, Callback callback) {
        Request build;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (z) {
            build = new Request.Builder().url(str).post(requestBody).build();
        } else {
            build = new Request.Builder().url(str).build();
        }
        if (okHttpClient != null) {
            okHttpClient.newCall(build).enqueue(callback);
        }
    }
}
