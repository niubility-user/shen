package com.jingdong.common.jdreactFramework.utils;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class Base64InputStream extends InputStream {
    private int[] buffer;
    private int bufferCounter = 0;
    private boolean eof = false;
    private InputStream inputStream;

    public Base64InputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private void acquire() throws IOException {
        int i2;
        char[] cArr = new char[4];
        int i3 = 0;
        do {
            int read = this.inputStream.read();
            i2 = 1;
            if (read == -1) {
                if (i3 == 0) {
                    this.buffer = new int[0];
                    this.eof = true;
                    return;
                }
                throw new IOException("Bad base64 stream");
            }
            char c2 = (char) read;
            if (Shared.chars.indexOf(c2) != -1 || c2 == Shared.pad) {
                cArr[i3] = c2;
                i3++;
            } else if (c2 != '\r' && c2 != '\n') {
                throw new IOException("Bad base64 stream");
            }
        } while (i3 < 4);
        boolean z = false;
        for (int i4 = 0; i4 < 4; i4++) {
            if (cArr[i4] != Shared.pad) {
                if (z) {
                    throw new IOException("Bad base64 stream");
                }
            } else if (!z) {
                z = true;
            }
        }
        if (cArr[3] != Shared.pad) {
            i2 = 3;
        } else if (this.inputStream.read() == -1) {
            this.eof = true;
            if (cArr[2] != Shared.pad) {
                i2 = 2;
            }
        } else {
            throw new IOException("Bad base64 stream");
        }
        int i5 = 0;
        for (int i6 = 0; i6 < 4; i6++) {
            if (cArr[i6] != Shared.pad) {
                i5 |= Shared.chars.indexOf(cArr[i6]) << ((3 - i6) * 6);
            }
        }
        this.buffer = new int[i2];
        for (int i7 = 0; i7 < i2; i7++) {
            this.buffer[i7] = (i5 >>> ((2 - i7) * 8)) & 255;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.inputStream.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int[] iArr = this.buffer;
        if (iArr == null || this.bufferCounter == iArr.length) {
            if (this.eof) {
                return -1;
            }
            acquire();
            if (this.buffer.length == 0) {
                this.buffer = null;
                return -1;
            }
            this.bufferCounter = 0;
        }
        int[] iArr2 = this.buffer;
        int i2 = this.bufferCounter;
        this.bufferCounter = i2 + 1;
        return iArr2[i2];
    }
}
