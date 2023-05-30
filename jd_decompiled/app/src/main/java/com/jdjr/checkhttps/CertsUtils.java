package com.jdjr.checkhttps;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes18.dex */
public class CertsUtils {
    private static final String TAG = "CertsUtils";

    /* JADX WARN: Removed duplicated region for block: B:37:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.security.cert.X509Certificate getCertByContent(java.lang.String r3) {
        /*
            r0 = 0
            java.lang.String r1 = "X.509"
            java.security.cert.CertificateFactory r1 = java.security.cert.CertificateFactory.getInstance(r1)     // Catch: java.lang.Throwable -> L23 java.security.cert.CertificateException -> L25
            r2 = 2
            byte[] r3 = android.util.Base64.decode(r3, r2)     // Catch: java.lang.Throwable -> L23 java.security.cert.CertificateException -> L25
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L23 java.security.cert.CertificateException -> L25
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L23 java.security.cert.CertificateException -> L25
            java.security.cert.Certificate r3 = r1.generateCertificate(r2)     // Catch: java.security.cert.CertificateException -> L21 java.lang.Throwable -> L35
            java.security.cert.X509Certificate r3 = (java.security.cert.X509Certificate) r3     // Catch: java.security.cert.CertificateException -> L21 java.lang.Throwable -> L35
            r2.close()     // Catch: java.io.IOException -> L1b
            goto L1f
        L1b:
            r0 = move-exception
            r0.printStackTrace()
        L1f:
            r0 = r3
            goto L34
        L21:
            r3 = move-exception
            goto L27
        L23:
            r3 = move-exception
            goto L37
        L25:
            r3 = move-exception
            r2 = r0
        L27:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L35
            if (r2 == 0) goto L34
            r2.close()     // Catch: java.io.IOException -> L30
            goto L34
        L30:
            r3 = move-exception
            r3.printStackTrace()
        L34:
            return r0
        L35:
            r3 = move-exception
            r0 = r2
        L37:
            if (r0 == 0) goto L41
            r0.close()     // Catch: java.io.IOException -> L3d
            goto L41
        L3d:
            r0 = move-exception
            r0.printStackTrace()
        L41:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.checkhttps.CertsUtils.getCertByContent(java.lang.String):java.security.cert.X509Certificate");
    }

    public static String getCertsContent(Context context) {
        try {
            InputStream open = context.getAssets().open("rootcert/secure_root.cer");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.close();
                    open.close();
                    return byteArrayOutputStream.toString();
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
