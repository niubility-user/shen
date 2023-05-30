package k.a.a.e.a;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* loaded from: classes11.dex */
public class d extends c {

    /* renamed from: i  reason: collision with root package name */
    private Inflater f20212i;

    /* renamed from: j  reason: collision with root package name */
    private byte[] f20213j;

    /* renamed from: k  reason: collision with root package name */
    private byte[] f20214k;

    /* renamed from: l  reason: collision with root package name */
    private int f20215l;

    public d(b bVar) {
        super(bVar);
        this.f20214k = new byte[1];
        this.f20212i = new Inflater(true);
        this.f20213j = new byte[4096];
    }

    private void i() throws IOException {
        byte[] bArr = this.f20213j;
        int read = super.read(bArr, 0, bArr.length);
        this.f20215l = read;
        if (read != -1) {
            this.f20212i.setInput(this.f20213j, 0, read);
            return;
        }
        throw new EOFException("Unexpected end of input stream");
    }

    @Override // k.a.a.e.a.c, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Inflater inflater = this.f20212i;
        if (inflater != null) {
            inflater.end();
        }
        super.close();
    }

    @Override // k.a.a.e.a.c
    public void f(InputStream inputStream) throws IOException {
        Inflater inflater = this.f20212i;
        if (inflater != null) {
            inflater.end();
            this.f20212i = null;
        }
        super.f(inputStream);
    }

    @Override // k.a.a.e.a.c
    public void h(PushbackInputStream pushbackInputStream) throws IOException {
        int remaining = this.f20212i.getRemaining();
        if (remaining > 0) {
            pushbackInputStream.unread(g(), this.f20215l - remaining, remaining);
        }
    }

    @Override // k.a.a.e.a.c, java.io.InputStream
    public int read() throws IOException {
        if (read(this.f20214k) == -1) {
            return -1;
        }
        return this.f20214k[0];
    }

    @Override // k.a.a.e.a.c, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // k.a.a.e.a.c, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        while (true) {
            try {
                int inflate = this.f20212i.inflate(bArr, i2, i3);
                if (inflate == 0) {
                    if (!this.f20212i.finished() && !this.f20212i.needsDictionary()) {
                        if (this.f20212i.needsInput()) {
                            i();
                        }
                    }
                    return -1;
                }
                return inflate;
            } catch (DataFormatException e2) {
                throw new IOException(e2);
            }
        }
    }
}
