package com.unicom.xiaowo.login.c;

import android.net.Network;
import android.os.Build;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.unicom.xiaowo.login.d.e;
import com.unicom.xiaowo.login.d.f;
import com.unionpay.tsmservice.data.ResultCode;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class b {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class a implements X509TrustManager {
        public a() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            for (X509Certificate x509Certificate : x509CertificateArr) {
                x509Certificate.checkValidity();
            }
            if (!x509CertificateArr[0].getSubjectDN().getName().contains("wostore.cn") && !x509CertificateArr[0].getSubjectDN().getName().contains("10010.com")) {
                throw new CertificateException("bad certificate");
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public String a(String str, HashMap<String, String> hashMap, Network network) {
        String str2;
        HttpsURLConnection httpsURLConnection;
        e.a("url:" + str);
        try {
            URL url = new URL(str);
            StringBuilder sb = new StringBuilder();
            sb.append("https://");
            sb.append(url.getHost());
            str2 = sb.toString();
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            str2 = "";
        }
        f.c(str);
        try {
            SSLContext sSLContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
            sSLContext.init(null, new TrustManager[]{new a()}, new SecureRandom());
            URL url2 = new URL(str);
            if (network != null && Build.VERSION.SDK_INT >= 21) {
                httpsURLConnection = (HttpsURLConnection) network.openConnection(url2);
            } else {
                httpsURLConnection = (HttpsURLConnection) url2.openConnection();
            }
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(false);
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setInstanceFollowRedirects(false);
            httpsURLConnection.setReadTimeout(30000);
            httpsURLConnection.setConnectTimeout(30000);
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(new HostnameVerifier() { // from class: com.unicom.xiaowo.login.c.b.1
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str3, SSLSession sSLSession) {
                    if (TextUtils.isEmpty(str3) || !(str3.endsWith("wostore.cn") || str3.endsWith("10010.com"))) {
                        HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
                        return defaultHostnameVerifier.verify("wostore.cn", sSLSession) || defaultHostnameVerifier.verify("10010.com", sSLSession);
                    }
                    return true;
                }
            });
            if (hashMap != null) {
                for (String str3 : hashMap.keySet()) {
                    httpsURLConnection.setRequestProperty(str3, hashMap.get(str3));
                }
            }
            if (str.startsWith("https://opencloud.wostore.cn/openapi/netauth/precheck/wp?")) {
                e.a("Keep-Alive");
                httpsURLConnection.addRequestProperty("Connection", "Keep-Alive");
            } else {
                httpsURLConnection.addRequestProperty("Connection", "close");
            }
            httpsURLConnection.connect();
            if (httpsURLConnection.getResponseCode() == 200) {
                String a2 = a(httpsURLConnection.getInputStream());
                if (TextUtils.isEmpty(a2)) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("code", 10012);
                    jSONObject.put("msg", ResultCode.ERROR_INTERFACE_APP_DOWNLOAD_APPLY);
                    jSONObject.put("data", str2);
                    return jSONObject.toString();
                }
                return a2;
            } else if (httpsURLConnection.getResponseCode() == 302) {
                String headerField = httpsURLConnection.getHeaderField(HttpHeaders.LOCATION);
                if (!TextUtils.isEmpty(headerField)) {
                    if (headerField.startsWith("https")) {
                        return a(headerField, null, network);
                    }
                    return b(headerField, null, network);
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("code", 10013);
                jSONObject2.put("msg", "\u65e0\u8df3\u8f6c\u5730\u5740");
                jSONObject2.put("data", str2);
                return jSONObject2.toString();
            } else {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("code", 10010);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("https\u72b6\u6001\u7801");
                sb2.append(httpsURLConnection.getResponseCode());
                jSONObject3.put("msg", sb2.toString());
                jSONObject3.put("data", str2);
                return jSONObject3.toString();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            try {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("code", 10011);
                StringBuilder sb3 = new StringBuilder();
                sb3.append("https\u5f02\u5e38");
                sb3.append(e3.getMessage());
                jSONObject4.put("msg", sb3.toString());
                jSONObject4.put("data", str2);
                return jSONObject4.toString();
            } catch (Exception unused) {
                return null;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0184  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String b(java.lang.String r9, java.util.HashMap<java.lang.String, java.lang.String> r10, android.net.Network r11) {
        /*
            Method dump skipped, instructions count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.xiaowo.login.c.b.b(java.lang.String, java.util.HashMap, android.net.Network):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            java.lang.String r0 = ""
            java.lang.String r1 = "TLS"
            r2 = 0
            javax.net.ssl.SSLContext r1 = javax.net.ssl.SSLContext.getInstance(r1)     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r3 = 1
            javax.net.ssl.TrustManager[] r4 = new javax.net.ssl.TrustManager[r3]     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            com.unicom.xiaowo.login.c.b$a r5 = new com.unicom.xiaowo.login.c.b$a     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r5.<init>()     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r6 = 0
            r4[r6] = r5     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            java.security.SecureRandom r5 = new java.security.SecureRandom     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r5.<init>()     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r1.init(r2, r4, r5)     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            java.net.URL r4 = new java.net.URL     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r4.<init>(r8)     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            java.net.URLConnection r8 = r4.openConnection()     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            javax.net.ssl.HttpsURLConnection r8 = (javax.net.ssl.HttpsURLConnection) r8     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r8.setDoInput(r3)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r8.setDoOutput(r3)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r8.setUseCaches(r6)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r8.setInstanceFollowRedirects(r3)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r2 = 30000(0x7530, float:4.2039E-41)
            r8.setReadTimeout(r2)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r8.setConnectTimeout(r2)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            java.lang.String r2 = "POST"
            r8.setRequestMethod(r2)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            javax.net.ssl.SSLSocketFactory r1 = r1.getSocketFactory()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r8.setSSLSocketFactory(r1)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            com.unicom.xiaowo.login.c.b$2 r1 = new com.unicom.xiaowo.login.c.b$2     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r1.<init>()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r8.setHostnameVerifier(r1)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            java.lang.String r1 = "Connection"
            java.lang.String r2 = "close"
            r8.addRequestProperty(r1, r2)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            java.lang.String r1 = "Content-Type"
            java.lang.String r2 = "application/json;charset=UTF-8"
            r8.addRequestProperty(r1, r2)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r8.connect()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            java.io.DataOutputStream r1 = new java.io.DataOutputStream     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            java.io.OutputStream r2 = r8.getOutputStream()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            if (r9 == 0) goto L74
            java.lang.String r2 = "UTF-8"
            byte[] r9 = r9.getBytes(r2)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r1.write(r9)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
        L74:
            r1.flush()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r1.close()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            int r9 = r8.getResponseCode()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r1 = 200(0xc8, float:2.8E-43)
            if (r9 != r1) goto L8a
            java.io.InputStream r9 = r8.getInputStream()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            java.lang.String r0 = r7.a(r9)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
        L8a:
            if (r8 == 0) goto L8f
            r8.disconnect()
        L8f:
            return r0
        L90:
            r9 = move-exception
            r2 = r8
            goto La4
        L93:
            r9 = move-exception
            r2 = r8
            goto L9a
        L96:
            r8 = move-exception
            goto La5
        L98:
            r8 = move-exception
            r9 = r8
        L9a:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> La3
            if (r2 == 0) goto La2
            r2.disconnect()
        La2:
            return r0
        La3:
            r9 = move-exception
        La4:
            r8 = r9
        La5:
            if (r2 == 0) goto Laa
            r2.disconnect()
        Laa:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.xiaowo.login.c.b.a(java.lang.String, java.lang.String):java.lang.String");
    }

    private String a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Exception unused) {
            byteArrayOutputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            String str = new String(byteArrayOutputStream.toByteArray());
            try {
                byteArrayOutputStream.close();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception unused2) {
            }
            return str;
        } catch (Exception unused3) {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Exception unused4) {
                    return null;
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            if (byteArrayOutputStream2 != null) {
                try {
                    byteArrayOutputStream2.close();
                } catch (Exception unused5) {
                    throw th;
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }
}
