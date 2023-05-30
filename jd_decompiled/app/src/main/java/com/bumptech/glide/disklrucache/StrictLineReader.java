package com.bumptech.glide.disklrucache;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
class StrictLineReader implements Closeable {
    private static final byte CR = 13;
    private static final byte LF = 10;
    private byte[] buf;
    private final Charset charset;
    private int end;
    private final InputStream in;
    private int pos;

    public StrictLineReader(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    private void fillBuf() throws IOException {
        InputStream inputStream = this.in;
        byte[] bArr = this.buf;
        int read = inputStream.read(bArr, 0, bArr.length);
        if (read != -1) {
            this.pos = 0;
            this.end = read;
            return;
        }
        throw new EOFException();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.in) {
            if (this.buf != null) {
                this.buf = null;
                this.in.close();
            }
        }
    }

    public boolean hasUnterminatedLine() {
        return this.end == -1;
    }

    public String readLine() throws IOException {
        int i2;
        byte[] bArr;
        int i3;
        synchronized (this.in) {
            if (this.buf != null) {
                if (this.pos >= this.end) {
                    fillBuf();
                }
                for (int i4 = this.pos; i4 != this.end; i4++) {
                    byte[] bArr2 = this.buf;
                    if (bArr2[i4] == 10) {
                        if (i4 != this.pos) {
                            i3 = i4 - 1;
                            if (bArr2[i3] == 13) {
                                byte[] bArr3 = this.buf;
                                int i5 = this.pos;
                                String str = new String(bArr3, i5, i3 - i5, this.charset.name());
                                this.pos = i4 + 1;
                                return str;
                            }
                        }
                        i3 = i4;
                        byte[] bArr32 = this.buf;
                        int i52 = this.pos;
                        String str2 = new String(bArr32, i52, i3 - i52, this.charset.name());
                        this.pos = i4 + 1;
                        return str2;
                    }
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((this.end - this.pos) + 80) { // from class: com.bumptech.glide.disklrucache.StrictLineReader.1
                    @Override // java.io.ByteArrayOutputStream
                    public String toString() {
                        int i6 = ((ByteArrayOutputStream) this).count;
                        if (i6 > 0 && ((ByteArrayOutputStream) this).buf[i6 - 1] == 13) {
                            i6--;
                        }
                        try {
                            return new String(((ByteArrayOutputStream) this).buf, 0, i6, StrictLineReader.this.charset.name());
                        } catch (UnsupportedEncodingException e2) {
                            throw new AssertionError(e2);
                        }
                    }
                };
                loop1: while (true) {
                    byte[] bArr4 = this.buf;
                    int i6 = this.pos;
                    byteArrayOutputStream.write(bArr4, i6, this.end - i6);
                    this.end = -1;
                    fillBuf();
                    i2 = this.pos;
                    while (i2 != this.end) {
                        bArr = this.buf;
                        if (bArr[i2] == 10) {
                            break loop1;
                        }
                        i2++;
                    }
                }
                int i7 = this.pos;
                if (i2 != i7) {
                    byteArrayOutputStream.write(bArr, i7, i2 - i7);
                }
                this.pos = i2 + 1;
                return byteArrayOutputStream.toString();
            }
            throw new IOException("LineReader is closed");
        }
    }

    public StrictLineReader(InputStream inputStream, int i2, Charset charset) {
        if (inputStream == null || charset == null) {
            throw null;
        }
        if (i2 >= 0) {
            if (charset.equals(Util.US_ASCII)) {
                this.in = inputStream;
                this.charset = charset;
                this.buf = new byte[i2];
                return;
            }
            throw new IllegalArgumentException("Unsupported encoding");
        }
        throw new IllegalArgumentException("capacity <= 0");
    }
}
