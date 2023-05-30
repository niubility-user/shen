package com.googlecode.mp4parser.authoring.tracks;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes12.dex */
public class CleanInputStream extends FilterInputStream {
    int prev;
    int prevprev;

    public CleanInputStream(InputStream inputStream) {
        super(inputStream);
        this.prevprev = -1;
        this.prev = -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = super.read();
        if (read == 3 && this.prevprev == 0 && this.prev == 0) {
            this.prevprev = -1;
            this.prev = -1;
            read = super.read();
        }
        this.prevprev = this.prev;
        this.prev = read;
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        bArr.getClass();
        if (i2 < 0 || i3 < 0 || i3 > bArr.length - i2) {
            throw new IndexOutOfBoundsException();
        }
        if (i3 == 0) {
            return 0;
        }
        int read = read();
        if (read == -1) {
            return -1;
        }
        bArr[i2] = (byte) read;
        int i4 = 1;
        while (true) {
            if (i4 < i3) {
                try {
                    int read2 = read();
                    if (read2 == -1) {
                        break;
                    }
                    bArr[i2 + i4] = (byte) read2;
                    i4++;
                } catch (IOException unused) {
                }
            }
            return i4;
        }
        return i4;
    }
}
