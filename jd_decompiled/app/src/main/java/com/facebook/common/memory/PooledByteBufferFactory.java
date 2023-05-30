package com.facebook.common.memory;

import java.io.InputStream;

/* loaded from: classes.dex */
public interface PooledByteBufferFactory {
    PooledByteBuffer newByteBuffer(int i2);

    PooledByteBuffer newByteBuffer(InputStream inputStream);

    PooledByteBuffer newByteBuffer(InputStream inputStream, int i2);

    PooledByteBuffer newByteBuffer(byte[] bArr);

    PooledByteBufferOutputStream newOutputStream();

    PooledByteBufferOutputStream newOutputStream(int i2);
}
