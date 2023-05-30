package com.jingdong.jdexreport.c;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* loaded from: classes12.dex */
public class a implements HostnameVerifier {
    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
        return Boolean.valueOf(defaultHostnameVerifier.verify("ex.m.jd.com", sSLSession) || defaultHostnameVerifier.verify("ex.m.jd.care", sSLSession)).booleanValue();
    }
}
