package k.a.a.e.a;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes11.dex */
class j extends InputStream {

    /* renamed from: g  reason: collision with root package name */
    private InputStream f20227g;

    /* renamed from: h  reason: collision with root package name */
    private long f20228h = 0;

    /* renamed from: i  reason: collision with root package name */
    private byte[] f20229i = new byte[1];

    /* renamed from: j  reason: collision with root package name */
    private long f20230j;

    public j(InputStream inputStream, long j2) {
        this.f20227g = inputStream;
        this.f20230j = j2;
    }

    private int g(byte[] bArr, int i2) throws IOException {
        int length = bArr.length - i2;
        int i3 = 0;
        for (int i4 = 0; i2 < bArr.length && i3 != -1 && i4 < 15; i4++) {
            i3 += this.f20227g.read(bArr, i2, length);
            if (i3 > 0) {
                i2 += i3;
                length -= i3;
            }
        }
        return i2;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f20227g.close();
    }

    public int f(byte[] bArr) throws IOException {
        int read = this.f20227g.read(bArr);
        if (read == bArr.length || (read = g(bArr, read)) == bArr.length) {
            return read;
        }
        throw new IOException("Cannot read fully into byte buffer");
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.f20229i) == -1) {
            return -1;
        }
        return this.f20229i[0];
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        long j2 = this.f20230j;
        if (j2 != -1) {
            long j3 = this.f20228h;
            if (j3 >= j2) {
                return -1;
            }
            if (i3 > j2 - j3) {
                i3 = (int) (j2 - j3);
            }
        }
        int read = this.f20227g.read(bArr, i2, i3);
        if (read > 0) {
            this.f20228h += read;
        }
        return read;
    }
}
