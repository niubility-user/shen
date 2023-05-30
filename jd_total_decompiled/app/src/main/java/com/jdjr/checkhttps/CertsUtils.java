package com.jdjr.checkhttps;

import android.content.Context;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* loaded from: classes18.dex */
public class CertsUtils {
    private static final String TAG = "CertsUtils";

    /* JADX WARN: Removed duplicated region for block: B:37:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static X509Certificate getCertByContent(String str) {
        ByteArrayInputStream byteArrayInputStream;
        CertificateFactory certificateFactory;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            try {
                certificateFactory = CertificateFactory.getInstance("X.509");
                byteArrayInputStream = new ByteArrayInputStream(Base64.decode(str, 2));
            } catch (CertificateException e2) {
                e = e2;
                byteArrayInputStream = null;
            } catch (Throwable th) {
                th = th;
                if (byteArrayInputStream2 != null) {
                }
                throw th;
            }
            try {
                X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
                try {
                    byteArrayInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return x509Certificate;
            } catch (CertificateException e4) {
                e = e4;
                e.printStackTrace();
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                        return null;
                    } catch (IOException e5) {
                        e5.printStackTrace();
                        return null;
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayInputStream2 = byteArrayInputStream;
            if (byteArrayInputStream2 != null) {
                try {
                    byteArrayInputStream2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
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
