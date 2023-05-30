package com.jingdong.app.mall.bundle.jdbrotli;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class BrotliInputStream extends InputStream {
    public static final int DEFAULT_INTERNAL_BUFFER_SIZE = 256;
    private byte[] buffer;
    private int bufferOffset;
    private int remainingBufferBytes;
    private final State state;

    public BrotliInputStream(InputStream inputStream) throws IOException {
        this(inputStream, 256);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Decode.close(this.state);
    }

    public void enableEagerOutput() {
        Decode.enableEagerOutput(this.state);
    }

    public void enableLargeWindow() {
        Decode.enableLargeWindow(this.state);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.bufferOffset >= this.remainingBufferBytes) {
            byte[] bArr = this.buffer;
            int read = read(bArr, 0, bArr.length);
            this.remainingBufferBytes = read;
            this.bufferOffset = 0;
            if (read == -1) {
                return -1;
            }
        }
        byte[] bArr2 = this.buffer;
        int i2 = this.bufferOffset;
        this.bufferOffset = i2 + 1;
        return bArr2[i2] & 255;
    }

    public BrotliInputStream(InputStream inputStream, int i2) throws IOException {
        State state = new State();
        this.state = state;
        if (i2 <= 0) {
            throw new IllegalArgumentException("Bad buffer size:" + i2);
        } else if (inputStream != null) {
            this.buffer = new byte[i2];
            this.remainingBufferBytes = 0;
            this.bufferOffset = 0;
            try {
                Decode.initState(state, inputStream);
            } catch (BrotliRuntimeException e2) {
                throw new IOException("Brotli decoder initialization failed", e2);
            }
        } else {
            throw new IllegalArgumentException("source is null");
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        if (i2 < 0) {
            throw new IllegalArgumentException("Bad offset: " + i2);
        } else if (i3 >= 0) {
            int i4 = i2 + i3;
            if (i4 > bArr.length) {
                throw new IllegalArgumentException("Buffer overflow: " + i4 + " > " + bArr.length);
            } else if (i3 == 0) {
                return 0;
            } else {
                int max = Math.max(this.remainingBufferBytes - this.bufferOffset, 0);
                if (max != 0) {
                    max = Math.min(max, i3);
                    System.arraycopy(this.buffer, this.bufferOffset, bArr, i2, max);
                    this.bufferOffset += max;
                    i2 += max;
                    i3 -= max;
                    if (i3 == 0) {
                        return max;
                    }
                }
                try {
                    State state = this.state;
                    state.output = bArr;
                    state.outputOffset = i2;
                    state.outputLength = i3;
                    state.outputUsed = 0;
                    Decode.decompress(state);
                    int i5 = this.state.outputUsed;
                    if (i5 == 0) {
                        return -1;
                    }
                    return i5 + max;
                } catch (BrotliRuntimeException e2) {
                    throw new IOException("Brotli stream decoding failed", e2);
                }
            }
        } else {
            throw new IllegalArgumentException("Bad length: " + i3);
        }
    }
}
