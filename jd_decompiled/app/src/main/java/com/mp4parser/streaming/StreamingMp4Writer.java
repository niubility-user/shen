package com.mp4parser.streaming;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes14.dex */
public interface StreamingMp4Writer extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    void write() throws IOException;
}
