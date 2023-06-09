package com.eclipsesource.v8;

import java.io.Closeable;

/* loaded from: classes.dex */
public interface Releasable extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void release();
}
