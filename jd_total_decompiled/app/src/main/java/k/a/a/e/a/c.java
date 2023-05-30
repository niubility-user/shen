package k.a.a.e.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/* loaded from: classes11.dex */
abstract class c extends InputStream {

    /* renamed from: g  reason: collision with root package name */
    private b f20210g;

    /* renamed from: h  reason: collision with root package name */
    protected byte[] f20211h = new byte[1];

    public c(b bVar) {
        this.f20210g = bVar;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f20210g.close();
    }

    public void f(InputStream inputStream) throws IOException {
        this.f20210g.g(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] g() {
        return this.f20210g.i();
    }

    public void h(PushbackInputStream pushbackInputStream) throws IOException {
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.f20211h) == -1) {
            return -1;
        }
        return this.f20211h[0];
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        return this.f20210g.read(bArr, i2, i3);
    }
}
