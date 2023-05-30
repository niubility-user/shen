package com.unionpay.a;

import java.io.IOException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;

/* loaded from: classes11.dex */
public class a {
    private SSLContext a = null;
    private String b;

    public a(String str) {
        this.b = str;
    }

    private static SSLContext a(String str) {
        try {
            SSLContext sSLContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
            sSLContext.init(null, new TrustManager[]{new b(str)}, null);
            return sSLContext;
        } catch (Exception e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public final SSLContext a() {
        if (this.a == null) {
            this.a = a(this.b);
        }
        return this.a;
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(a.class);
    }

    public int hashCode() {
        return a.class.hashCode();
    }
}
