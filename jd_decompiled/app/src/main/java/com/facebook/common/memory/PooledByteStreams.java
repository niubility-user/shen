package com.facebook.common.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class PooledByteStreams {
    private static final int DEFAULT_TEMP_BUF_SIZE = 16384;
    private final ByteArrayPool mByteArrayPool;
    private final int mTempBufSize;

    public PooledByteStreams(ByteArrayPool byteArrayPool) {
        this(byteArrayPool, 16384);
    }

    @VisibleForTesting
    public PooledByteStreams(ByteArrayPool byteArrayPool, int i2) {
        Preconditions.checkArgument(i2 > 0);
        this.mTempBufSize = i2;
        this.mByteArrayPool = byteArrayPool;
    }

    public long copy(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = this.mByteArrayPool.get(this.mTempBufSize);
        long j2 = 0;
        while (true) {
            try {
                int read = inputStream.read(bArr, 0, this.mTempBufSize);
                if (read == -1) {
                    return j2;
                }
                outputStream.write(bArr, 0, read);
                j2 += read;
            } finally {
                this.mByteArrayPool.release(bArr);
            }
        }
    }

    public long copy(InputStream inputStream, OutputStream outputStream, long j2) {
        long j3 = 0;
        Preconditions.checkState(j2 > 0);
        byte[] bArr = this.mByteArrayPool.get(this.mTempBufSize);
        while (j3 < j2) {
            try {
                int read = inputStream.read(bArr, 0, (int) Math.min(this.mTempBufSize, j2 - j3));
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
                j3 += read;
            } finally {
                this.mByteArrayPool.release(bArr);
            }
        }
        return j3;
    }
}
