package com.jingdong.jdreact.plugin.authority;

import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

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
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onResponse(okhttp3.Call r9, okhttp3.Response r10) throws java.io.IOException {
                /*
                    r8 = this;
                    r9 = 2048(0x800, float:2.87E-42)
                    byte[] r9 = new byte[r9]
                    r0 = 0
                    java.lang.String r1 = r2     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5d
                    boolean r1 = com.jingdong.jdreact.plugin.authority.OKHttpUtil.access$000(r1)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5d
                    if (r1 != 0) goto L15
                    com.jingdong.jdreact.plugin.authority.OKHttpUtil$DownloadListener r9 = com.jingdong.jdreact.plugin.authority.OKHttpUtil.DownloadListener.this     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5d
                    if (r9 == 0) goto L14
                    r9.onDownloadFailed()     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5d
                L14:
                    return
                L15:
                    java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5d
                    java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5d
                    java.lang.String r3 = r2     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5d
                    r2.<init>(r3)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5d
                    r1.<init>(r2)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5d
                    okhttp3.ResponseBody r2 = r10.body()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L70
                    java.io.InputStream r0 = r2.byteStream()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L70
                    okhttp3.ResponseBody r10 = r10.body()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L70
                    long r2 = r10.contentLength()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L70
                    r4 = 0
                L33:
                    int r10 = r0.read(r9)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L70
                    r6 = -1
                    if (r10 == r6) goto L48
                    r6 = 0
                    r1.write(r9, r6, r10)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L70
                    long r6 = (long) r10     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L70
                    long r4 = r4 + r6
                    com.jingdong.jdreact.plugin.authority.OKHttpUtil$DownloadListener r10 = com.jingdong.jdreact.plugin.authority.OKHttpUtil.DownloadListener.this     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L70
                    if (r10 == 0) goto L33
                    r10.onDownloading(r2, r4)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L70
                    goto L33
                L48:
                    r1.flush()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L70
                    com.jingdong.jdreact.plugin.authority.OKHttpUtil$DownloadListener r9 = com.jingdong.jdreact.plugin.authority.OKHttpUtil.DownloadListener.this     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L70
                    if (r9 == 0) goto L54
                    java.lang.String r10 = r2     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L70
                    r9.onDownloadSuccess(r10)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L70
                L54:
                    if (r0 == 0) goto L6c
                    r0.close()
                    goto L6c
                L5a:
                    r9 = move-exception
                    r1 = r0
                    goto L71
                L5d:
                    r1 = r0
                L5e:
                    com.jingdong.jdreact.plugin.authority.OKHttpUtil$DownloadListener r9 = com.jingdong.jdreact.plugin.authority.OKHttpUtil.DownloadListener.this     // Catch: java.lang.Throwable -> L70
                    if (r9 == 0) goto L65
                    r9.onDownloadFailed()     // Catch: java.lang.Throwable -> L70
                L65:
                    if (r0 == 0) goto L6a
                    r0.close()
                L6a:
                    if (r1 == 0) goto L6f
                L6c:
                    r1.close()
                L6f:
                    return
                L70:
                    r9 = move-exception
                L71:
                    if (r0 == 0) goto L76
                    r0.close()
                L76:
                    if (r1 == 0) goto L7b
                    r1.close()
                L7b:
                    goto L7d
                L7c:
                    throw r9
                L7d:
                    goto L7c
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.authority.OKHttpUtil.AnonymousClass1.onResponse(okhttp3.Call, okhttp3.Response):void");
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
