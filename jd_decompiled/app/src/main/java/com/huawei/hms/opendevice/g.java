package com.huawei.hms.opendevice;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes12.dex */
public abstract class g {

    /* loaded from: classes12.dex */
    private enum a {
        GET("GET"),
        POST("POST");
        
        private String a;

        a(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.util.Map<java.lang.String, java.lang.String>, java.util.Map] */
    /* JADX WARN: Type inference failed for: r10v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v24 */
    /* JADX WARN: Type inference failed for: r10v25 */
    /* JADX WARN: Type inference failed for: r10v26 */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r7v15, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v16, types: [java.io.OutputStream, java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v10, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v30 */
    /* JADX WARN: Type inference failed for: r9v31 */
    /* JADX WARN: Type inference failed for: r9v32 */
    public static String a(Context context, String str, String str2, Map<String, String> map) {
        HttpsURLConnection httpsURLConnection;
        InputStream inputStream;
        InputStream inputStream2;
        HttpsURLConnection httpsURLConnection2;
        Throwable th;
        ?? r8;
        BufferedInputStream bufferedInputStream;
        InputStream inputStream3 = null;
        if (str2 == 0 || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        int i2 = -1;
        try {
            try {
                httpsURLConnection2 = a((Context) context, (String) str, (Map<String, String>) map, a.POST.a());
                if (httpsURLConnection2 == null) {
                    com.huawei.secure.android.common.util.a.c(null);
                    com.huawei.secure.android.common.util.a.b(null);
                    com.huawei.secure.android.common.util.a.b(null);
                    p.a(httpsURLConnection2);
                    HMSLog.i("PushHttpsClient", "close connection");
                    return null;
                }
                try {
                    r8 = new BufferedOutputStream(httpsURLConnection2.getOutputStream());
                    try {
                        r8.write(str2.getBytes("UTF-8"));
                        r8.flush();
                        i2 = httpsURLConnection2.getResponseCode();
                        HMSLog.d("PushHttpsClient", "https post response code: " + i2);
                        if (i2 >= 400) {
                            inputStream = httpsURLConnection2.getErrorStream();
                        } else {
                            inputStream = httpsURLConnection2.getInputStream();
                        }
                        try {
                            bufferedInputStream = new BufferedInputStream(inputStream);
                            try {
                                String a2 = p.a((InputStream) bufferedInputStream);
                                com.huawei.secure.android.common.util.a.c(r8);
                                com.huawei.secure.android.common.util.a.b(inputStream);
                                com.huawei.secure.android.common.util.a.b(bufferedInputStream);
                                p.a(httpsURLConnection2);
                                HMSLog.i("PushHttpsClient", "close connection");
                                return a2;
                            } catch (IOException unused) {
                                HMSLog.w("PushHttpsClient", "https execute encounter IOException - http code:" + i2);
                                context = httpsURLConnection2;
                                str = r8;
                                str2 = inputStream;
                                map = bufferedInputStream;
                                com.huawei.secure.android.common.util.a.c(str);
                                com.huawei.secure.android.common.util.a.b(str2);
                                com.huawei.secure.android.common.util.a.b(map);
                                p.a((HttpsURLConnection) context);
                                HMSLog.i("PushHttpsClient", "close connection");
                                return null;
                            } catch (RuntimeException unused2) {
                                HMSLog.w("PushHttpsClient", "https execute encounter RuntimeException - http code:" + i2);
                                context = httpsURLConnection2;
                                str = r8;
                                str2 = inputStream;
                                map = bufferedInputStream;
                                com.huawei.secure.android.common.util.a.c(str);
                                com.huawei.secure.android.common.util.a.b(str2);
                                com.huawei.secure.android.common.util.a.b(map);
                                p.a((HttpsURLConnection) context);
                                HMSLog.i("PushHttpsClient", "close connection");
                                return null;
                            } catch (Exception unused3) {
                                HMSLog.w("PushHttpsClient", "https execute encounter unknown exception - http code:" + i2);
                                context = httpsURLConnection2;
                                str = r8;
                                str2 = inputStream;
                                map = bufferedInputStream;
                                com.huawei.secure.android.common.util.a.c(str);
                                com.huawei.secure.android.common.util.a.b(str2);
                                com.huawei.secure.android.common.util.a.b(map);
                                p.a((HttpsURLConnection) context);
                                HMSLog.i("PushHttpsClient", "close connection");
                                return null;
                            } catch (Throwable th2) {
                                inputStream3 = bufferedInputStream;
                                th = th2;
                                com.huawei.secure.android.common.util.a.c(r8);
                                com.huawei.secure.android.common.util.a.b(inputStream);
                                com.huawei.secure.android.common.util.a.b(inputStream3);
                                p.a(httpsURLConnection2);
                                HMSLog.i("PushHttpsClient", "close connection");
                                throw th;
                            }
                        } catch (IOException unused4) {
                            bufferedInputStream = null;
                            HMSLog.w("PushHttpsClient", "https execute encounter IOException - http code:" + i2);
                            context = httpsURLConnection2;
                            str = r8;
                            str2 = inputStream;
                            map = bufferedInputStream;
                            com.huawei.secure.android.common.util.a.c(str);
                            com.huawei.secure.android.common.util.a.b(str2);
                            com.huawei.secure.android.common.util.a.b(map);
                            p.a((HttpsURLConnection) context);
                            HMSLog.i("PushHttpsClient", "close connection");
                            return null;
                        } catch (RuntimeException unused5) {
                            bufferedInputStream = null;
                            HMSLog.w("PushHttpsClient", "https execute encounter RuntimeException - http code:" + i2);
                            context = httpsURLConnection2;
                            str = r8;
                            str2 = inputStream;
                            map = bufferedInputStream;
                            com.huawei.secure.android.common.util.a.c(str);
                            com.huawei.secure.android.common.util.a.b(str2);
                            com.huawei.secure.android.common.util.a.b(map);
                            p.a((HttpsURLConnection) context);
                            HMSLog.i("PushHttpsClient", "close connection");
                            return null;
                        } catch (Exception unused6) {
                            bufferedInputStream = null;
                            HMSLog.w("PushHttpsClient", "https execute encounter unknown exception - http code:" + i2);
                            context = httpsURLConnection2;
                            str = r8;
                            str2 = inputStream;
                            map = bufferedInputStream;
                            com.huawei.secure.android.common.util.a.c(str);
                            com.huawei.secure.android.common.util.a.b(str2);
                            com.huawei.secure.android.common.util.a.b(map);
                            p.a((HttpsURLConnection) context);
                            HMSLog.i("PushHttpsClient", "close connection");
                            return null;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (IOException unused7) {
                        inputStream = null;
                    } catch (RuntimeException unused8) {
                        inputStream = null;
                    } catch (Exception unused9) {
                        inputStream = null;
                    } catch (Throwable th4) {
                        th = th4;
                        inputStream = null;
                    }
                } catch (IOException unused10) {
                    r8 = 0;
                    inputStream = null;
                    bufferedInputStream = null;
                    HMSLog.w("PushHttpsClient", "https execute encounter IOException - http code:" + i2);
                    context = httpsURLConnection2;
                    str = r8;
                    str2 = inputStream;
                    map = bufferedInputStream;
                    com.huawei.secure.android.common.util.a.c(str);
                    com.huawei.secure.android.common.util.a.b(str2);
                    com.huawei.secure.android.common.util.a.b(map);
                    p.a((HttpsURLConnection) context);
                    HMSLog.i("PushHttpsClient", "close connection");
                    return null;
                } catch (RuntimeException unused11) {
                    r8 = 0;
                    inputStream = null;
                    bufferedInputStream = null;
                    HMSLog.w("PushHttpsClient", "https execute encounter RuntimeException - http code:" + i2);
                    context = httpsURLConnection2;
                    str = r8;
                    str2 = inputStream;
                    map = bufferedInputStream;
                    com.huawei.secure.android.common.util.a.c(str);
                    com.huawei.secure.android.common.util.a.b(str2);
                    com.huawei.secure.android.common.util.a.b(map);
                    p.a((HttpsURLConnection) context);
                    HMSLog.i("PushHttpsClient", "close connection");
                    return null;
                } catch (Exception unused12) {
                    r8 = 0;
                    inputStream = null;
                    bufferedInputStream = null;
                    HMSLog.w("PushHttpsClient", "https execute encounter unknown exception - http code:" + i2);
                    context = httpsURLConnection2;
                    str = r8;
                    str2 = inputStream;
                    map = bufferedInputStream;
                    com.huawei.secure.android.common.util.a.c(str);
                    com.huawei.secure.android.common.util.a.b(str2);
                    com.huawei.secure.android.common.util.a.b(map);
                    p.a((HttpsURLConnection) context);
                    HMSLog.i("PushHttpsClient", "close connection");
                    return null;
                } catch (Throwable th5) {
                    inputStream = null;
                    inputStream2 = null;
                    httpsURLConnection = httpsURLConnection2;
                    th = th5;
                    InputStream inputStream4 = inputStream2;
                    th = th;
                    httpsURLConnection2 = httpsURLConnection;
                    r8 = inputStream3;
                    inputStream3 = inputStream4;
                    com.huawei.secure.android.common.util.a.c(r8);
                    com.huawei.secure.android.common.util.a.b(inputStream);
                    com.huawei.secure.android.common.util.a.b(inputStream3);
                    p.a(httpsURLConnection2);
                    HMSLog.i("PushHttpsClient", "close connection");
                    throw th;
                }
            } catch (IOException unused13) {
                httpsURLConnection2 = null;
            } catch (RuntimeException unused14) {
                httpsURLConnection2 = null;
            } catch (Exception unused15) {
                httpsURLConnection2 = null;
            } catch (Throwable th6) {
                th = th6;
                httpsURLConnection = null;
                inputStream = null;
                inputStream2 = null;
            }
        } catch (Throwable th7) {
            InputStream inputStream5 = str;
            httpsURLConnection = context;
            th = th7;
            inputStream3 = inputStream5;
            inputStream = str2;
            inputStream2 = map;
        }
    }

    private static HttpsURLConnection a(Context context, String str, Map<String, String> map, String str2) throws Exception {
        URLConnection openConnection = new URL(str).openConnection();
        if (openConnection == null) {
            HMSLog.e("PushHttpsClient", "urlConnection is null");
            return null;
        } else if (openConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
            try {
                com.huawei.secure.android.common.ssl.b b = com.huawei.secure.android.common.ssl.b.b(context);
                if (b != null) {
                    httpsURLConnection.setSSLSocketFactory(b);
                    httpsURLConnection.setHostnameVerifier(com.huawei.secure.android.common.ssl.b.f1533i);
                    httpsURLConnection.setRequestMethod(str2);
                    httpsURLConnection.setConnectTimeout(15000);
                    httpsURLConnection.setReadTimeout(15000);
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setRequestProperty("Content-type", "application/json; charset=UTF-8");
                    httpsURLConnection.setRequestProperty("Connection", "close");
                    if (map != null && map.size() >= 1) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            String key = entry.getKey();
                            if (!TextUtils.isEmpty(key)) {
                                httpsURLConnection.setRequestProperty(key, URLEncoder.encode(entry.getValue() == null ? "" : entry.getValue(), "UTF-8"));
                            }
                        }
                    }
                    return httpsURLConnection;
                }
                HMSLog.e("PushHttpsClient", "No ssl socket factory set.");
                return null;
            } catch (IOException | IllegalAccessException | IllegalArgumentException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException e2) {
                HMSLog.e("PushHttpsClient", "Failed to new TLSSocketFactory instance." + e2.getMessage());
                throw new IOException("Failed to create SSLSocketFactory.");
            }
        } else {
            HMSLog.e("PushHttpsClient", "current request is http not allow connection");
            return null;
        }
    }
}
