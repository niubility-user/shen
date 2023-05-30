package k.a.a.e.a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* loaded from: classes11.dex */
public abstract class h extends InputStream {

    /* renamed from: g  reason: collision with root package name */
    protected RandomAccessFile f20222g;

    /* renamed from: h  reason: collision with root package name */
    protected File f20223h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f20224i;

    /* renamed from: j  reason: collision with root package name */
    private int f20225j;

    /* renamed from: k  reason: collision with root package name */
    private byte[] f20226k = new byte[1];

    public h(File file, boolean z, int i2) throws FileNotFoundException {
        this.f20225j = 0;
        this.f20222g = new RandomAccessFile(file, k.a.a.f.o.e.READ.getValue());
        this.f20223h = file;
        this.f20224i = z;
        if (z) {
            this.f20225j = i2;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        RandomAccessFile randomAccessFile = this.f20222g;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
    }

    protected abstract File f(int i2) throws IOException;

    protected void g(int i2) throws IOException {
        File f2 = f(i2);
        if (f2.exists()) {
            this.f20222g.close();
            this.f20222g = new RandomAccessFile(f2, k.a.a.f.o.e.READ.getValue());
            return;
        }
        throw new FileNotFoundException("zip split file does not exist: " + f2);
    }

    public void h(k.a.a.f.h hVar) throws IOException {
        if (this.f20224i && this.f20225j != hVar.L()) {
            g(hVar.L());
            this.f20225j = hVar.L();
        }
        this.f20222g.seek(hVar.N());
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.f20226k) == -1) {
            return -1;
        }
        return this.f20226k[0];
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        int read = this.f20222g.read(bArr, i2, i3);
        if ((read != i3 || read == -1) && this.f20224i) {
            g(this.f20225j + 1);
            this.f20225j++;
            if (read < 0) {
                read = 0;
            }
            int read2 = this.f20222g.read(bArr, read, i3 - read);
            return read2 > 0 ? read + read2 : read;
        }
        return read;
    }
}
