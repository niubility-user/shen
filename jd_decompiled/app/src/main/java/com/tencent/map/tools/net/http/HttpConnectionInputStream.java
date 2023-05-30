package com.tencent.map.tools.net.http;

import com.tencent.mapsdk.internal.ga;
import java.io.Closeable;
import java.io.InputStream;
import java.net.HttpURLConnection;

/* loaded from: classes9.dex */
public class HttpConnectionInputStream extends InputStream {
    private final HttpURLConnection mConnection;
    private final InputStream mProxyInputStream;

    public HttpConnectionInputStream(HttpURLConnection httpURLConnection) {
        this.mConnection = httpURLConnection;
        this.mProxyInputStream = httpURLConnection.getInputStream();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        ga.a((Closeable) this.mProxyInputStream);
        this.mConnection.disconnect();
    }

    @Override // java.io.InputStream
    public int read() {
        return this.mProxyInputStream.read();
    }
}
