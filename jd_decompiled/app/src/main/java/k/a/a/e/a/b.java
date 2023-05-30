package k.a.a.e.a;

import java.io.IOException;
import java.io.InputStream;
import k.a.a.b.c;

/* loaded from: classes11.dex */
abstract class b<T extends k.a.a.b.c> extends InputStream {

    /* renamed from: g  reason: collision with root package name */
    private j f20205g;

    /* renamed from: h  reason: collision with root package name */
    private T f20206h;

    /* renamed from: i  reason: collision with root package name */
    private byte[] f20207i;

    /* renamed from: j  reason: collision with root package name */
    private byte[] f20208j = new byte[1];

    /* renamed from: k  reason: collision with root package name */
    private k.a.a.f.i f20209k;

    public b(j jVar, k.a.a.f.i iVar, char[] cArr) throws IOException, k.a.a.c.a {
        this.f20205g = jVar;
        this.f20206h = k(iVar, cArr);
        this.f20209k = iVar;
        if (k.a.a.i.g.e(iVar).equals(k.a.a.f.o.c.DEFLATE)) {
            this.f20207i = new byte[4096];
        }
    }

    private void f(byte[] bArr, int i2) {
        byte[] bArr2 = this.f20207i;
        if (bArr2 != null) {
            System.arraycopy(bArr, 0, bArr2, 0, i2);
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f20205g.close();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g(InputStream inputStream) throws IOException {
    }

    public T h() {
        return this.f20206h;
    }

    public byte[] i() {
        return this.f20207i;
    }

    public k.a.a.f.i j() {
        return this.f20209k;
    }

    protected abstract T k(k.a.a.f.i iVar, char[] cArr) throws IOException, k.a.a.c.a;

    /* JADX INFO: Access modifiers changed from: protected */
    public int l(byte[] bArr) throws IOException {
        return this.f20205g.f(bArr);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.f20208j) == -1) {
            return -1;
        }
        return this.f20208j[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        int h2 = k.a.a.i.g.h(this.f20205g, bArr, i2, i3);
        if (h2 > 0) {
            f(bArr, h2);
            this.f20206h.a(bArr, i2, h2);
        }
        return h2;
    }
}
