package k.a.a.e.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import k.a.a.c.a;

/* loaded from: classes11.dex */
public class k extends InputStream {

    /* renamed from: g  reason: collision with root package name */
    private PushbackInputStream f20231g;

    /* renamed from: h  reason: collision with root package name */
    private c f20232h;

    /* renamed from: j  reason: collision with root package name */
    private char[] f20234j;

    /* renamed from: k  reason: collision with root package name */
    private k.a.a.f.i f20235k;

    /* renamed from: m  reason: collision with root package name */
    private byte[] f20237m;
    private Charset o;

    /* renamed from: i  reason: collision with root package name */
    private k.a.a.d.a f20233i = new k.a.a.d.a();

    /* renamed from: l  reason: collision with root package name */
    private CRC32 f20236l = new CRC32();

    /* renamed from: n  reason: collision with root package name */
    private boolean f20238n = false;

    public k(InputStream inputStream, char[] cArr, Charset charset) {
        charset = charset == null ? k.a.a.i.d.b : charset;
        this.f20231g = new PushbackInputStream(inputStream, 4096);
        this.f20234j = cArr;
        this.o = charset;
    }

    private boolean f(List<k.a.a.f.g> list) {
        if (list == null) {
            return false;
        }
        Iterator<k.a.a.f.g> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().c() == k.a.a.d.b.ZIP64_EXTRA_FIELD_SIGNATURE.getValue()) {
                return true;
            }
        }
        return false;
    }

    private void g() throws IOException {
        this.f20232h.h(this.f20231g);
        this.f20232h.f(this.f20231g);
        p();
        s();
        r();
    }

    private long h(k.a.a.f.i iVar) {
        if (k.a.a.i.g.e(iVar).equals(k.a.a.f.o.c.STORE)) {
            return iVar.m();
        }
        if (!iVar.o() || this.f20238n) {
            return iVar.c() - i(iVar);
        }
        return -1L;
    }

    private int i(k.a.a.f.i iVar) {
        if (iVar.q()) {
            if (iVar.g().equals(k.a.a.f.o.d.AES)) {
                return iVar.b().b().getSaltLength() + 12;
            }
            return iVar.g().equals(k.a.a.f.o.d.ZIP_STANDARD) ? 12 : 0;
        }
        return 0;
    }

    private b k(j jVar, k.a.a.f.i iVar) throws IOException {
        if (!iVar.q()) {
            return new e(jVar, iVar, this.f20234j);
        }
        if (iVar.g() == k.a.a.f.o.d.AES) {
            return new a(jVar, iVar, this.f20234j);
        }
        if (iVar.g() == k.a.a.f.o.d.ZIP_STANDARD) {
            return new l(jVar, iVar, this.f20234j);
        }
        throw new k.a.a.c.a(String.format("Entry [%s] Strong Encryption not supported", iVar.j()), a.EnumC0855a.UNSUPPORTED_ENCRYPTION);
    }

    private c l(b bVar, k.a.a.f.i iVar) {
        if (k.a.a.i.g.e(iVar) == k.a.a.f.o.c.DEFLATE) {
            return new d(bVar);
        }
        return new i(bVar);
    }

    private c m(k.a.a.f.i iVar) throws IOException {
        return l(k(new j(this.f20231g, h(iVar)), iVar), iVar);
    }

    private boolean n(k.a.a.f.i iVar) {
        return iVar.q() && k.a.a.f.o.d.ZIP_STANDARD.equals(iVar.g());
    }

    private boolean o(String str) {
        return str.endsWith("/") || str.endsWith("\\");
    }

    private void p() throws IOException {
        if (!this.f20235k.o() || this.f20238n) {
            return;
        }
        k.a.a.f.d i2 = this.f20233i.i(this.f20231g, f(this.f20235k.h()));
        this.f20235k.t(i2.b());
        this.f20235k.I(i2.d());
        this.f20235k.v(i2.c());
    }

    private void q() throws IOException {
        if (this.f20235k.p() || this.f20235k.c() == 0) {
            return;
        }
        if (this.f20237m == null) {
            this.f20237m = new byte[512];
        }
        do {
        } while (read(this.f20237m) != -1);
    }

    private void r() {
        this.f20235k = null;
        this.f20236l.reset();
    }

    private void s() throws IOException {
        if ((this.f20235k.g() == k.a.a.f.o.d.AES && this.f20235k.b().c().equals(k.a.a.f.o.b.TWO)) || this.f20235k.e() == this.f20236l.getValue()) {
            return;
        }
        a.EnumC0855a enumC0855a = a.EnumC0855a.CHECKSUM_MISMATCH;
        if (n(this.f20235k)) {
            enumC0855a = a.EnumC0855a.WRONG_PASSWORD;
        }
        throw new k.a.a.c.a("Reached end of entry, but crc verification failed for " + this.f20235k.j(), enumC0855a);
    }

    private void t(k.a.a.f.i iVar) throws IOException {
        if (o(iVar.j()) || iVar.d() != k.a.a.f.o.c.STORE || iVar.m() >= 0) {
            return;
        }
        throw new IOException("Invalid local file header for: " + iVar.j() + ". Uncompressed size has to be set for entry of compression type store which is not a directory");
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        c cVar = this.f20232h;
        if (cVar != null) {
            cVar.close();
        }
    }

    public k.a.a.f.i j(k.a.a.f.h hVar) throws IOException {
        if (this.f20235k != null) {
            q();
        }
        k.a.a.f.i o = this.f20233i.o(this.f20231g, this.o);
        this.f20235k = o;
        if (o == null) {
            return null;
        }
        t(o);
        this.f20236l.reset();
        if (hVar != null) {
            this.f20235k.v(hVar.e());
            this.f20235k.t(hVar.c());
            this.f20235k.I(hVar.m());
            this.f20238n = true;
        } else {
            this.f20238n = false;
        }
        this.f20232h = m(this.f20235k);
        return this.f20235k;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr) == -1) {
            return -1;
        }
        return bArr[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        if (i3 >= 0) {
            if (i3 == 0) {
                return 0;
            }
            if (this.f20235k == null) {
                return -1;
            }
            try {
                int read = this.f20232h.read(bArr, i2, i3);
                if (read == -1) {
                    g();
                } else {
                    this.f20236l.update(bArr, i2, read);
                }
                return read;
            } catch (IOException e2) {
                if (e2.getCause() != null && (e2.getCause() instanceof DataFormatException) && n(this.f20235k)) {
                    throw new k.a.a.c.a(e2.getMessage(), e2.getCause(), a.EnumC0855a.WRONG_PASSWORD);
                }
                throw e2;
            }
        }
        throw new IllegalArgumentException("Negative read length");
    }
}
