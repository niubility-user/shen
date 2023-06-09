package com.facebook.imagepipeline.memory;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface MemoryChunk {
    void close();

    void copy(int i2, MemoryChunk memoryChunk, int i3, int i4);

    @Nullable
    ByteBuffer getByteBuffer();

    long getNativePtr();

    int getSize();

    long getUniqueId();

    boolean isClosed();

    byte read(int i2);

    int read(int i2, byte[] bArr, int i3, int i4);

    int write(int i2, byte[] bArr, int i3, int i4);
}
