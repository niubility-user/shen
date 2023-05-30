package com.unionpay.a;

import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.text.TextUtils;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes11.dex */
public final class b implements X509TrustManager {
    private X509TrustManager a;
    private X509TrustManagerExtensions b;

    /* renamed from: c  reason: collision with root package name */
    private String f18142c;

    public b(String str) {
        this.b = null;
        this.f18142c = str;
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length == 0) {
            throw new NoSuchAlgorithmException("no trust manager found");
        }
        this.a = (X509TrustManager) trustManagers[0];
        if (Build.VERSION.SDK_INT > 23) {
            this.b = new X509TrustManagerExtensions(this.a);
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        X509TrustManager x509TrustManager = this.a;
        if (x509TrustManager != null) {
            x509TrustManager.checkClientTrusted(x509CertificateArr, str);
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        boolean z;
        X509TrustManagerExtensions x509TrustManagerExtensions;
        if (Build.VERSION.SDK_INT <= 23 || (x509TrustManagerExtensions = this.b) == null) {
            X509TrustManager x509TrustManager = this.a;
            if (x509TrustManager != null) {
                x509TrustManager.checkServerTrusted(x509CertificateArr, str);
            }
        } else {
            x509TrustManagerExtensions.checkServerTrusted(x509CertificateArr, str, "");
        }
        boolean z2 = false;
        try {
            X500Principal issuerX500Principal = x509CertificateArr[0].getIssuerX500Principal();
            ArrayList arrayList = new ArrayList(0);
            arrayList.add(".*(GeoTrust|VeriSign|Symantec|GlobalSign|Entrust|Thawte|DigiCert).*");
            if (!TextUtils.isEmpty(this.f18142c)) {
                arrayList.add(this.f18142c);
            }
            int i2 = 0;
            while (true) {
                if (i2 >= arrayList.size()) {
                    z = false;
                    break;
                } else if (Pattern.compile((String) arrayList.get(i2), 2).matcher(issuerX500Principal.getName()).matches()) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (!z) {
                throw new CertificateException();
            }
            X500Principal subjectX500Principal = x509CertificateArr[0].getSubjectX500Principal();
            ArrayList arrayList2 = new ArrayList(0);
            arrayList2.add(".*CN=.*(\\.95516\\.com|\\.chinaunionpay\\.com|\\.unionpay\\.com|\\.unionpaysecure\\.com|\\.95516\\.net)(,.*|$)");
            int i3 = 0;
            while (true) {
                if (i3 >= arrayList2.size()) {
                    break;
                } else if (Pattern.compile((String) arrayList2.get(i3), 2).matcher(subjectX500Principal.getName()).matches()) {
                    z2 = true;
                    break;
                } else {
                    i3++;
                }
            }
            if (!z2) {
                throw new CertificateException();
            }
        } catch (Exception unused) {
            throw new CertificateException();
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public final X509Certificate[] getAcceptedIssuers() {
        return this.a.getAcceptedIssuers();
    }
}
