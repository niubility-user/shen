package i.a;

import androidx.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes11.dex */
public class a extends InputStream {
    @NonNull

    /* renamed from: g  reason: collision with root package name */
    private final InputStream f19703g;

    /* renamed from: h  reason: collision with root package name */
    private InterfaceC0843a f19704h;

    /* renamed from: i.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public interface InterfaceC0843a {
        void a();
    }

    public a(InputStream inputStream, InterfaceC0843a interfaceC0843a) {
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream cannot be null.");
        }
        this.f19703g = inputStream;
        this.f19704h = interfaceC0843a;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.f19703g.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f19703g.close();
        InterfaceC0843a interfaceC0843a = this.f19704h;
        if (interfaceC0843a != null) {
            interfaceC0843a.a();
            this.f19704h = null;
        }
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i2) {
        this.f19703g.mark(i2);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f19703g.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.f19703g.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return this.f19703g.read(bArr);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        return this.f19703g.read(bArr, i2, i3);
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.f19703g.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j2) throws IOException {
        return this.f19703g.skip(j2);
    }
}
