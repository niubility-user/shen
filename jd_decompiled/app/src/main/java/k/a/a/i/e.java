package k.a.a.i;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* loaded from: classes11.dex */
public class e {
    private byte[] a = new byte[2];
    private byte[] b = new byte[4];

    /* renamed from: c  reason: collision with root package name */
    private byte[] f20284c = new byte[8];

    private void a(InputStream inputStream, byte[] bArr, int i2) throws IOException {
        if (g.h(inputStream, bArr, 0, i2) != i2) {
            throw new k.a.a.c.a("Could not fill buffer");
        }
    }

    private void n(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = 0;
        }
    }

    public int b(InputStream inputStream) throws IOException {
        a(inputStream, this.b, 4);
        return d(this.b);
    }

    public int c(RandomAccessFile randomAccessFile) throws IOException {
        randomAccessFile.readFully(this.b);
        return d(this.b);
    }

    public int d(byte[] bArr) {
        return e(bArr, 0);
    }

    public int e(byte[] bArr, int i2) {
        return ((((bArr[i2 + 3] & 255) << 8) | (bArr[i2 + 2] & 255)) << 16) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8);
    }

    public long f(InputStream inputStream) throws IOException {
        byte[] bArr = this.f20284c;
        a(inputStream, bArr, bArr.length);
        return j(this.f20284c, 0);
    }

    public long g(InputStream inputStream, int i2) throws IOException {
        n(this.f20284c);
        a(inputStream, this.f20284c, i2);
        return j(this.f20284c, 0);
    }

    public long h(RandomAccessFile randomAccessFile) throws IOException {
        randomAccessFile.readFully(this.f20284c);
        return j(this.f20284c, 0);
    }

    public long i(RandomAccessFile randomAccessFile, int i2) throws IOException {
        n(this.f20284c);
        randomAccessFile.readFully(this.f20284c, 0, i2);
        return j(this.f20284c, 0);
    }

    public long j(byte[] bArr, int i2) {
        if (bArr.length - i2 < 8) {
            n(this.f20284c);
        }
        System.arraycopy(bArr, i2, this.f20284c, 0, bArr.length < 8 ? bArr.length - i2 : 8);
        byte[] bArr2 = this.f20284c;
        return ((((((((((((((0 | (bArr2[7] & 255)) << 8) | (bArr2[6] & 255)) << 8) | (bArr2[5] & 255)) << 8) | (bArr2[4] & 255)) << 8) | (bArr2[3] & 255)) << 8) | (bArr2[2] & 255)) << 8) | (bArr2[1] & 255)) << 8) | (bArr2[0] & 255);
    }

    public int k(InputStream inputStream) throws IOException {
        byte[] bArr = this.a;
        a(inputStream, bArr, bArr.length);
        return m(this.a, 0);
    }

    public int l(RandomAccessFile randomAccessFile) throws IOException {
        randomAccessFile.readFully(this.a);
        return m(this.a, 0);
    }

    public int m(byte[] bArr, int i2) {
        return ((bArr[i2 + 1] & 255) << 8) | (bArr[i2] & 255);
    }
}
