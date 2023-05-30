package com.huawei.secure.android.common.ssl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import com.huawei.secure.android.common.ssl.g.f;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/* loaded from: classes12.dex */
public class d {
    private static final String a = "SecureX509SingleInstance";
    private static volatile e b;

    private d() {
    }

    @SuppressLint({"NewApi"})
    public static e a(Context context) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        long currentTimeMillis = System.currentTimeMillis();
        if (context != null) {
            com.huawei.secure.android.common.ssl.g.c.b(context);
            if (b == null) {
                synchronized (d.class) {
                    if (b == null) {
                        InputStream n2 = com.huawei.secure.android.common.ssl.g.a.n(context);
                        if (n2 == null) {
                            f.e(a, "get assets bks");
                            n2 = context.getAssets().open("hmsrootcas.bks");
                        } else {
                            f.e(a, "get files bks");
                        }
                        b = new e(n2, "");
                        new com.huawei.secure.android.common.ssl.g.d().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, context);
                    }
                }
            }
            f.b(a, "SecureX509TrustManager getInstance: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            return b;
        }
        throw new NullPointerException("context is null");
    }
}
