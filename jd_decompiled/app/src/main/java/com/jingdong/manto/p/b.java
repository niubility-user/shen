package com.jingdong.manto.p;

import com.jingdong.manto.utils.MantoLog;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes16.dex */
public final class b implements X509TrustManager {
    private LinkedList<X509TrustManager> a = new LinkedList<>();
    private LinkedList<X509TrustManager> b = new LinkedList<>();

    /* renamed from: c  reason: collision with root package name */
    KeyStore f13913c;
    private X509Certificate[] d;

    public b() {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            this.f13913c = keyStore;
            keyStore.load(null, null);
        } catch (Exception unused) {
            MantoLog.e("MantoX509TrustManager", "load keystore failed");
        }
    }

    private void b() {
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<X509TrustManager> it = this.a.iterator();
        while (it.hasNext()) {
            X509Certificate[] acceptedIssuers = it.next().getAcceptedIssuers();
            if (acceptedIssuers != null) {
                arrayList.addAll(Arrays.asList(acceptedIssuers));
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        Iterator<X509TrustManager> it2 = this.b.iterator();
        while (it2.hasNext()) {
            X509Certificate[] acceptedIssuers2 = it2.next().getAcceptedIssuers();
            if (acceptedIssuers2 != null) {
                arrayList.addAll(Arrays.asList(acceptedIssuers2));
            }
        }
        long currentTimeMillis3 = System.currentTimeMillis();
        X509Certificate[] x509CertificateArr = new X509Certificate[arrayList.size()];
        this.d = x509CertificateArr;
        this.d = (X509Certificate[]) arrayList.toArray(x509CertificateArr);
        MantoLog.i("MantoX509TrustManager", String.format("init certificate: %d, %d, %d", Long.valueOf(currentTimeMillis2 - currentTimeMillis), Long.valueOf(currentTimeMillis3 - currentTimeMillis2), Long.valueOf(System.currentTimeMillis() - currentTimeMillis3)));
    }

    private void c() {
        if (this.f13913c != null) {
            try {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(this.f13913c);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                int i2 = 0;
                while (trustManagers != null) {
                    if (i2 >= trustManagers.length) {
                        return;
                    }
                    this.b.add((X509TrustManager) trustManagers[i2]);
                    i2++;
                }
            } catch (Exception e2) {
                MantoLog.e("MantoX509TrustManager", "init local trust manager failed:", e2);
            }
        }
    }

    public final void a() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(this.f13913c);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            int i2 = 0;
            while (trustManagers != null) {
                if (i2 >= trustManagers.length) {
                    break;
                }
                this.a.add((X509TrustManager) trustManagers[i2]);
                i2++;
            }
        } catch (Exception e2) {
            MantoLog.e("MantoX509TrustManager", "init trust managers failed:", e2);
        }
        c();
        b();
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        boolean z;
        Iterator<X509TrustManager> it = this.a.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            try {
                it.next().checkServerTrusted(x509CertificateArr, str);
                z = true;
                break;
            } catch (CertificateException unused) {
            }
        }
        if (z) {
            return;
        }
        Iterator<X509TrustManager> it2 = this.b.iterator();
        while (it2.hasNext()) {
            try {
                it2.next().checkServerTrusted(x509CertificateArr, str);
                return;
            } catch (CertificateException unused2) {
            }
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public final X509Certificate[] getAcceptedIssuers() {
        return this.d;
    }
}
