package com.meizu.cloud.pushsdk.e.h;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* loaded from: classes14.dex */
public interface l extends Closeable, Flushable {
    void c(b bVar, long j2) throws IOException;

    @Override // java.lang.AutoCloseable, com.meizu.cloud.pushsdk.e.h.l
    void close() throws IOException;

    void flush() throws IOException;
}
