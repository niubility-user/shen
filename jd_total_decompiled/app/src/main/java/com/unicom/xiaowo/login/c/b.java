package com.unicom.xiaowo.login.c;

import android.net.Network;
import android.os.Build;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.unicom.xiaowo.login.d.e;
import com.unicom.xiaowo.login.d.f;
import com.unionpay.tsmservice.data.ResultCode;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
    */
    public String b(String str, HashMap<String, String> hashMap, Network network) {
        String str2;
        Exception e2;
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str);
            StringBuilder sb = new StringBuilder();
            sb.append("http://");
            sb.append(url.getHost());
            str2 = sb.toString();
        } catch (MalformedURLException e3) {
            e3.printStackTrace();
            str2 = str;
        }
        f.c(str);
        e.a(str);
        ?? r4 = 0;
        try {
            try {
                URL url2 = new URL(str);
                if (network != null && Build.VERSION.SDK_INT >= 21) {
                    httpURLConnection = (HttpURLConnection) network.openConnection(url2);
                } else {
                    httpURLConnection = (HttpURLConnection) url2.openConnection();
                }
            } catch (Exception e4) {
                e2 = e4;
                httpURLConnection = null;
            } catch (Throwable th) {
                th = th;
                if (r4 != 0) {
                }
                throw th;
            }
            try {
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.setReadTimeout(30000);
                httpURLConnection.setConnectTimeout(30000);
                httpURLConnection.setRequestMethod("GET");
                if (hashMap != null) {
                    for (String str3 : hashMap.keySet()) {
                        httpURLConnection.setRequestProperty(str3, hashMap.get(str3));
                    }
                }
                httpURLConnection.addRequestProperty("Connection", "close");
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    String a2 = a(httpURLConnection.getInputStream());
                    if (!TextUtils.isEmpty(a2)) {
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return a2;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("code", 10012);
                    jSONObject.put("msg", ResultCode.ERROR_INTERFACE_APP_DOWNLOAD_APPLY);
                    jSONObject.put("data", str2);
                    String jSONObject2 = jSONObject.toString();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return jSONObject2;
                } else if (httpURLConnection.getResponseCode() == 302) {
                    String headerField = httpURLConnection.getHeaderField(HttpHeaders.LOCATION);
                    if (!TextUtils.isEmpty(headerField)) {
                        if (headerField.startsWith("https")) {
                            String a3 = a(headerField, null, network);
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            return a3;
                        }
                        String b = b(headerField, null, network);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return b;
                    }
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("code", 10013);
                    jSONObject3.put("msg", "\u65e0\u8df3\u8f6c\u5730\u5740");
                    jSONObject3.put("data", str2);
                    String jSONObject4 = jSONObject3.toString();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return jSONObject4;
                } else {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("code", 10010);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("https\u72b6\u6001\u7801");
                    sb2.append(httpURLConnection.getResponseCode());
                    jSONObject5.put("msg", sb2.toString());
                    jSONObject5.put("data", str2);
                    String jSONObject6 = jSONObject5.toString();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return jSONObject6;
                }
            } catch (Exception e5) {
                e2 = e5;
                e2.printStackTrace();
                try {
                    JSONObject jSONObject7 = new JSONObject();
                    jSONObject7.put("code", 10024);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("http\u5f02\u5e38");
                    sb3.append(e2.getMessage());
                    jSONObject7.put("msg", sb3.toString());
                    jSONObject7.put("data", str2);
                    String jSONObject8 = jSONObject7.toString();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return jSONObject8;
                } catch (Exception unused) {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                }
            }
        } catch (Throwable th2) {
            r4 = str;
            th = th2;
            if (r4 != 0) {
                r4.disconnect();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String a(String str, String str2) {
        Exception e2;
        Throwable th;
        SSLContext sSLContext;
        HttpsURLConnection httpsURLConnection;
        HttpsURLConnection httpsURLConnection2 = null;
        try {
            sSLContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
            sSLContext.init(null, new TrustManager[]{new a()}, new SecureRandom());
            httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
        } catch (Exception e3) {
            e2 = e3;
        } catch (Throwable th2) {
            th = th2;
            if (httpsURLConnection2 != null) {
            }
            throw th;
        }
        try {
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setInstanceFollowRedirects(true);
            httpsURLConnection.setReadTimeout(30000);
            httpsURLConnection.setConnectTimeout(30000);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(new HostnameVerifier() { // from class: com.unicom.xiaowo.login.c.b.2
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str3, SSLSession sSLSession) {
                    if (TextUtils.isEmpty(str3) || !(str3.endsWith("wostore.cn") || str3.endsWith("10010.com"))) {
                        HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
                        return defaultHostnameVerifier.verify("wostore.cn", sSLSession) || defaultHostnameVerifier.verify("10010.com", sSLSession);
                    }
                    return true;
                }
            });
            httpsURLConnection.addRequestProperty("Connection", "close");
            httpsURLConnection.addRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
            httpsURLConnection.connect();
            DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
            if (str2 != null) {
                dataOutputStream.write(str2.getBytes("UTF-8"));
            }
            dataOutputStream.flush();
            dataOutputStream.close();
            String a2 = httpsURLConnection.getResponseCode() == 200 ? a(httpsURLConnection.getInputStream()) : "";
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
            return a2;
        } catch (Exception e4) {
            e2 = e4;
            httpsURLConnection2 = httpsURLConnection;
            try {
                e2.printStackTrace();
                if (httpsURLConnection2 != null) {
                    httpsURLConnection2.disconnect();
                }
                return "";
            } catch (Throwable th3) {
                th = th3;
                th = th;
                if (httpsURLConnection2 != null) {
                    httpsURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            httpsURLConnection2 = httpsURLConnection;
            th = th;
            if (httpsURLConnection2 != null) {
            }
            throw th;
        }
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
