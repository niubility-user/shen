package com.jd.libs.hybrid.preload.okhttp;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes16.dex */
public class SSLSocketClient {
    private static TrustManager[] a() {
        return new TrustManager[]{new X509TrustManager() { // from class: com.jd.libs.hybrid.preload.okhttp.SSLSocketClient.1
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
    }

    public static HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() { // from class: com.jd.libs.hybrid.preload.okhttp.SSLSocketClient.2
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        };
    }

    public static SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, a(), new SecureRandom());
            return sSLContext.getSocketFactory();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
}
