package com.meizu.cloud.pushsdk.e.d;

import java.io.Closeable;
import java.io.InputStream;

/* loaded from: classes14.dex */
public abstract class l implements Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        m.f(g());
    }

    public final InputStream f() {
        return g().d();
    }

    public abstract com.meizu.cloud.pushsdk.e.h.d g();
}
