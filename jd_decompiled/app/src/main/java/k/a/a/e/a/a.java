package k.a.a.e.a;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* loaded from: classes11.dex */
class a extends b<k.a.a.b.a> {

    /* renamed from: l  reason: collision with root package name */
    private byte[] f20202l;

    /* renamed from: m  reason: collision with root package name */
    private byte[] f20203m;

    /* renamed from: n  reason: collision with root package name */
    private int f20204n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;

    public a(j jVar, k.a.a.f.i iVar, char[] cArr) throws IOException {
        super(jVar, iVar, cArr);
        this.f20202l = new byte[1];
        this.f20203m = new byte[16];
        this.f20204n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
    }

    private void m(byte[] bArr, int i2) {
        int i3 = this.p;
        int i4 = this.o;
        if (i3 >= i4) {
            i3 = i4;
        }
        this.s = i3;
        System.arraycopy(this.f20203m, this.f20204n, bArr, i2, i3);
        q(this.s);
        n(this.s);
        int i5 = this.r;
        int i6 = this.s;
        this.r = i5 + i6;
        this.p -= i6;
        this.q += i6;
    }

    private void n(int i2) {
        int i3 = this.o - i2;
        this.o = i3;
        if (i3 <= 0) {
            this.o = 0;
        }
    }

    private byte[] o() throws IOException {
        byte[] bArr = new byte[2];
        l(bArr);
        return bArr;
    }

    private byte[] p(k.a.a.f.i iVar) throws IOException {
        if (iVar.b() != null) {
            byte[] bArr = new byte[iVar.b().b().getSaltLength()];
            l(bArr);
            return bArr;
        }
        throw new IOException("invalid aes extra data record");
    }

    private void q(int i2) {
        int i3 = this.f20204n + i2;
        this.f20204n = i3;
        if (i3 >= 15) {
            this.f20204n = 15;
        }
    }

    private void t(byte[] bArr) throws IOException {
        if (j().o() && k.a.a.f.o.c.DEFLATE.equals(k.a.a.i.g.e(j()))) {
            return;
        }
        byte[] bArr2 = new byte[10];
        System.arraycopy(h().b(), 0, bArr2, 0, 10);
        if (!Arrays.equals(bArr, bArr2)) {
            throw new IOException("Reached end of data for this entry, but aes verification failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k.a.a.e.a.b
    public void g(InputStream inputStream) throws IOException {
        t(s(inputStream));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k.a.a.e.a.b
    /* renamed from: r  reason: merged with bridge method [inline-methods] */
    public k.a.a.b.a k(k.a.a.f.i iVar, char[] cArr) throws IOException {
        return new k.a.a.b.a(iVar.b(), cArr, p(iVar), o());
    }

    @Override // k.a.a.e.a.b, java.io.InputStream
    public int read() throws IOException {
        if (read(this.f20202l) == -1) {
            return -1;
        }
        return this.f20202l[0];
    }

    protected byte[] s(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[10];
        if (k.a.a.i.g.g(inputStream, bArr) == 10) {
            return bArr;
        }
        throw new k.a.a.c.a("Invalid AES Mac bytes. Could not read sufficient data");
    }

    @Override // k.a.a.e.a.b, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // k.a.a.e.a.b, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        this.p = i3;
        this.q = i2;
        this.r = 0;
        if (this.o != 0) {
            m(bArr, i2);
            int i4 = this.r;
            if (i4 == i3) {
                return i4;
            }
        }
        if (this.p < 16) {
            byte[] bArr2 = this.f20203m;
            int read = super.read(bArr2, 0, bArr2.length);
            this.t = read;
            this.f20204n = 0;
            if (read == -1) {
                this.o = 0;
                int i5 = this.r;
                if (i5 > 0) {
                    return i5;
                }
                return -1;
            }
            this.o = read;
            m(bArr, this.q);
            int i6 = this.r;
            if (i6 == i3) {
                return i6;
            }
        }
        int i7 = this.q;
        int i8 = this.p;
        int read2 = super.read(bArr, i7, i8 - (i8 % 16));
        if (read2 == -1) {
            int i9 = this.r;
            if (i9 > 0) {
                return i9;
            }
            return -1;
        }
        return read2 + this.r;
    }
}
