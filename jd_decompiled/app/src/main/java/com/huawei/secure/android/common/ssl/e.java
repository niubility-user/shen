package com.huawei.secure.android.common.ssl;

import com.huawei.secure.android.common.ssl.g.f;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes12.dex */
public class e implements X509TrustManager {
    private static final String b = e.class.getSimpleName();
    protected List<X509TrustManager> a = new ArrayList();

    public e(InputStream inputStream, String str) throws IllegalArgumentException {
        a(inputStream, str);
    }

    private void a(InputStream inputStream, String str) {
        if (inputStream != null && str != null) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                try {
                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
                    KeyStore keyStore = KeyStore.getInstance("bks");
                    keyStore.load(inputStream, str.toCharArray());
                    trustManagerFactory.init(keyStore);
                    TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                    for (int i2 = 0; i2 < trustManagers.length; i2++) {
                        if (trustManagers[i2] instanceof X509TrustManager) {
                            this.a.add((X509TrustManager) trustManagers[i2]);
                        }
                    }
                    com.huawei.secure.android.common.ssl.g.e.b(inputStream);
                } catch (IOException | NegativeArraySizeException | KeyStoreException | NoSuchAlgorithmException | CertificateException e2) {
                    f.d(b, "loadInputStream: exception : " + e2.getMessage());
                }
                f.b(b, "loadInputStream: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
                return;
            } finally {
                com.huawei.secure.android.common.ssl.g.e.b(inputStream);
            }
        }
        throw new IllegalArgumentException("inputstream or trustPwd is null");
    }

    public void b(X509Certificate[] x509CertificateArr) {
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        f.e(b, "checkClientTrusted: ");
        Iterator<X509TrustManager> it = this.a.iterator();
        while (it.hasNext()) {
            try {
                it.next().checkServerTrusted(x509CertificateArr, str);
                return;
            } catch (CertificateException e2) {
                f.d(b, "checkServerTrusted CertificateException" + e2.getMessage());
            }
        }
        throw new CertificateException("checkServerTrusted CertificateException");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        b(x509CertificateArr);
        f.e(b, "checkServerTrusted begin ,server ca chain size is : " + x509CertificateArr.length + " ,auth type is : " + str);
        long currentTimeMillis = System.currentTimeMillis();
        for (X509Certificate x509Certificate : x509CertificateArr) {
            String str2 = b;
            f.b(str2, "server ca chain: getSubjectDN is :" + x509Certificate.getSubjectDN());
            f.b(str2, "IssuerDN :" + x509Certificate.getIssuerDN());
            f.b(str2, "SerialNumber : " + x509Certificate.getSerialNumber());
        }
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                String str3 = b;
                f.e(str3, "check server i : " + i2);
                X509TrustManager x509TrustManager = this.a.get(i2);
                X509Certificate[] acceptedIssuers = x509TrustManager.getAcceptedIssuers();
                if (acceptedIssuers != null) {
                    f.e(str3, "client root ca size is : " + acceptedIssuers.length);
                    for (X509Certificate x509Certificate2 : acceptedIssuers) {
                        f.b(b, "client root ca getIssuerDN :" + x509Certificate2.getIssuerDN());
                    }
                }
                x509TrustManager.checkServerTrusted(x509CertificateArr, str);
                f.e(b, "checkServerTrusted succeed ,root ca issuer is : " + x509CertificateArr[x509CertificateArr.length - 1].getIssuerDN());
                return;
            } catch (CertificateException e2) {
                String str4 = b;
                f.d(str4, "checkServerTrusted error :" + e2.getMessage() + " , time : " + i2);
                if (i2 == size - 1) {
                    if (x509CertificateArr != null && x509CertificateArr.length > 0) {
                        f.d(str4, "root ca issuer : " + x509CertificateArr[x509CertificateArr.length - 1].getIssuerDN());
                    }
                    throw e2;
                }
            }
        }
        f.b(b, "checkServerTrusted: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<X509TrustManager> it = this.a.iterator();
            while (it.hasNext()) {
                arrayList.addAll(Arrays.asList(it.next().getAcceptedIssuers()));
            }
            return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
        } catch (Exception e2) {
            f.d(b, "getAcceptedIssuers exception : " + e2.getMessage());
            return new X509Certificate[0];
        }
    }
}
