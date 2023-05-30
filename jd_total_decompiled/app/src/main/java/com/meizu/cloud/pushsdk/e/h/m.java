package com.meizu.cloud.pushsdk.e.h;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes14.dex */
public interface m extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable, com.meizu.cloud.pushsdk.e.h.l
    void close() throws IOException;

    long d(b bVar, long j2) throws IOException;
}
