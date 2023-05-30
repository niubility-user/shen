package com.jd.verify.View.gif;

import android.graphics.Bitmap;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* loaded from: classes18.dex */
public class b extends Thread {
    private boolean A;
    private byte[] B;
    private int C;
    private int D;
    private int E;
    private boolean F;
    private int G;
    private int H;
    private short[] I;
    private byte[] J;
    private byte[] K;
    private byte[] L;
    private c M;
    private int N;
    private a O;
    private byte[] P;
    private int Q;
    private InputStream a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    public int f7147c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f7148e;

    /* renamed from: f  reason: collision with root package name */
    private int f7149f;

    /* renamed from: g  reason: collision with root package name */
    private int[] f7150g;

    /* renamed from: h  reason: collision with root package name */
    private int[] f7151h;

    /* renamed from: i  reason: collision with root package name */
    private int[] f7152i;

    /* renamed from: j  reason: collision with root package name */
    private int f7153j;

    /* renamed from: k  reason: collision with root package name */
    private int f7154k;

    /* renamed from: l  reason: collision with root package name */
    private int f7155l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f7156m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f7157n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private Bitmap x;
    private Bitmap y;
    private c z;

    public b(byte[] bArr, a aVar) {
        this.z = null;
        this.A = false;
        this.B = new byte[256];
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = false;
        this.G = 0;
        this.O = null;
        this.P = null;
        this.Q = 0;
        this.P = bArr;
        this.O = aVar;
    }

    private void g() {
        this.b = 0;
        this.N = 0;
        this.M = null;
        this.f7150g = null;
        this.f7151h = null;
    }

    private int i() {
        try {
            return this.a.read();
        } catch (Exception unused) {
            this.b = 1;
            return 0;
        }
    }

    private int j() {
        int i2 = i();
        this.C = i2;
        int i3 = 0;
        if (i2 > 0) {
            while (true) {
                try {
                    int i4 = this.C;
                    if (i3 >= i4) {
                        break;
                    }
                    int read = this.a.read(this.B, i3, i4 - i3);
                    if (read == -1) {
                        break;
                    }
                    i3 += read;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (i3 < this.C) {
                this.b = 1;
            }
        }
        return i3;
    }

    private int k() {
        this.a = new ByteArrayInputStream(this.P);
        this.P = null;
        return s();
    }

    private void l() {
        boolean z = false;
        while (!z && !b()) {
            int i2 = i();
            if (i2 != 0) {
                if (i2 == 33) {
                    int i3 = i();
                    if (i3 == 249) {
                        m();
                    } else if (i3 != 255) {
                        v();
                    } else {
                        j();
                        String str = "";
                        for (int i4 = 0; i4 < 11; i4++) {
                            str = str + ((char) this.B[i4]);
                        }
                        if (str.equals("NETSCAPE2.0")) {
                            q();
                        } else {
                            v();
                        }
                    }
                } else if (i2 == 44) {
                    o();
                } else if (i2 != 59) {
                    this.b = 1;
                } else {
                    z = true;
                }
            }
        }
    }

    private void m() {
        i();
        int i2 = i();
        int i3 = (i2 & 28) >> 2;
        this.D = i3;
        if (i3 == 0) {
            this.D = 1;
        }
        this.F = (i2 & 1) != 0;
        this.G = r() * 10;
        this.H = i();
        i();
    }

    private void n() {
        String str = "";
        for (int i2 = 0; i2 < 6; i2++) {
            str = str + ((char) i());
        }
        if (!str.startsWith("GIF")) {
            this.b = 1;
            return;
        }
        p();
        if (!this.f7148e || b()) {
            return;
        }
        int[] c2 = c(this.f7149f);
        this.f7150g = c2;
        this.f7154k = c2[this.f7153j];
    }

    private void o() {
        this.p = r();
        this.q = r();
        this.r = r();
        this.s = r();
        int i2 = i();
        int i3 = 0;
        boolean z = (i2 & 128) != 0;
        this.f7156m = z;
        this.f7157n = (i2 & 64) != 0;
        int i4 = 2 << (i2 & 7);
        this.o = i4;
        if (z) {
            int[] c2 = c(i4);
            this.f7151h = c2;
            this.f7152i = c2;
        } else {
            this.f7152i = this.f7150g;
            if (this.f7153j == this.H) {
                this.f7154k = 0;
            }
        }
        if (this.F) {
            int[] iArr = this.f7152i;
            int i5 = this.H;
            int i6 = iArr[i5];
            iArr[i5] = 0;
            i3 = i6;
        }
        if (this.f7152i == null) {
            this.b = 1;
        }
        if (b()) {
            return;
        }
        a();
        v();
        if (b()) {
            return;
        }
        this.N++;
        this.x = Bitmap.createBitmap(this.f7147c, this.d, Bitmap.Config.ARGB_4444);
        u();
        c cVar = this.M;
        if (cVar == null) {
            c cVar2 = new c(this.x, this.G);
            this.M = cVar2;
            this.z = cVar2;
        } else {
            while (true) {
                c cVar3 = cVar.f7158c;
                if (cVar3 == null) {
                    break;
                }
                cVar = cVar3;
            }
            cVar.f7158c = new c(this.x, this.G);
        }
        if (this.F) {
            this.f7152i[this.H] = i3;
        }
        t();
        this.O.a(true, this.N);
    }

    private void p() {
        this.f7147c = r();
        this.d = r();
        int i2 = i();
        this.f7148e = (i2 & 128) != 0;
        this.f7149f = 2 << (i2 & 7);
        this.f7153j = i();
        i();
    }

    private void q() {
        do {
            j();
            byte[] bArr = this.B;
            if (bArr[0] == 1) {
                byte b = bArr[1];
                byte b2 = bArr[2];
            }
            if (this.C <= 0) {
                return;
            }
        } while (!b());
    }

    private int r() {
        return i() | (i() << 8);
    }

    private int s() {
        g();
        if (this.a != null) {
            n();
            if (!b()) {
                l();
                if (this.N < 0) {
                    this.b = 1;
                    this.O.a(false, -1);
                } else {
                    this.b = -1;
                    this.O.a(true, -1);
                }
            }
            try {
                this.a.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            this.b = 2;
            this.O.a(false, -1);
        }
        return this.b;
    }

    private void t() {
        this.E = this.D;
        this.t = this.p;
        this.u = this.q;
        this.v = this.r;
        this.w = this.s;
        this.y = this.x;
        this.f7155l = this.f7154k;
        this.D = 0;
        this.F = false;
        this.G = 0;
        this.f7151h = null;
    }

    private void u() {
        int i2;
        int[] iArr = new int[this.f7147c * this.d];
        int i3 = this.E;
        int i4 = 0;
        if (i3 > 0) {
            if (i3 == 3) {
                int i5 = this.N - 2;
                if (i5 > 0) {
                    this.y = b(i5 - 1);
                } else {
                    this.y = null;
                }
            }
            Bitmap bitmap = this.y;
            if (bitmap != null) {
                int i6 = this.f7147c;
                bitmap.getPixels(iArr, 0, i6, 0, 0, i6, this.d);
                if (this.E == 2) {
                    int i7 = !this.F ? this.f7155l : 0;
                    for (int i8 = 0; i8 < this.w; i8++) {
                        int i9 = ((this.u + i8) * this.f7147c) + this.t;
                        int i10 = this.v + i9;
                        while (i9 < i10) {
                            iArr[i9] = i7;
                            i9++;
                        }
                    }
                }
            }
        }
        int i11 = 8;
        int i12 = 0;
        int i13 = 1;
        while (true) {
            int i14 = this.s;
            if (i4 < i14) {
                if (this.f7157n) {
                    if (i12 >= i14) {
                        i13++;
                        if (i13 == 2) {
                            i12 = 4;
                        } else if (i13 == 3) {
                            i11 = 4;
                            i12 = 2;
                        } else if (i13 == 4) {
                            i11 = 2;
                            i12 = 1;
                        }
                    }
                    i2 = i12 + i11;
                } else {
                    i2 = i12;
                    i12 = i4;
                }
                int i15 = i12 + this.q;
                if (i15 < this.d) {
                    int i16 = this.f7147c;
                    int i17 = i15 * i16;
                    int i18 = this.p + i17;
                    int i19 = this.r;
                    int i20 = i18 + i19;
                    int i21 = i17 + i16;
                    if (i21 < i20) {
                        i20 = i21;
                    }
                    int i22 = i19 * i4;
                    while (i18 < i20) {
                        int i23 = i22 + 1;
                        int i24 = this.f7152i[this.L[i22] & 255];
                        if (i24 != 0) {
                            iArr[i18] = i24;
                        }
                        i18++;
                        i22 = i23;
                    }
                }
                i4++;
                i12 = i2;
            } else {
                this.x = Bitmap.createBitmap(iArr, this.f7147c, this.d, Bitmap.Config.ARGB_4444);
                return;
            }
        }
    }

    private void v() {
        do {
            j();
            if (this.C <= 0) {
                return;
            }
        } while (!b());
    }

    public c a(int i2) {
        c cVar = this.M;
        int i3 = 0;
        while (cVar != null) {
            if (i3 == i2) {
                return cVar;
            }
            cVar = cVar.f7158c;
            i3++;
        }
        return null;
    }

    public Bitmap b(int i2) {
        c a = a(i2);
        if (a == null) {
            return null;
        }
        return a.a;
    }

    public void c() {
        c cVar = this.M;
        while (cVar != null) {
            cVar.a = null;
            cVar = this.M.f7158c;
            this.M = cVar;
        }
        InputStream inputStream = this.a;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
            this.a = null;
        }
        this.P = null;
    }

    public int d() {
        return this.Q;
    }

    public int e() {
        return this.N;
    }

    public Bitmap f() {
        return b(0);
    }

    public c h() {
        if (!this.A) {
            this.A = true;
            return this.M;
        }
        if (this.b == 0) {
            c cVar = this.z.f7158c;
            if (cVar != null) {
                this.Q++;
                this.z = cVar;
            }
        } else {
            this.Q++;
            c cVar2 = this.z.f7158c;
            this.z = cVar2;
            if (cVar2 == null) {
                this.Q = 0;
                this.z = this.M;
            }
        }
        return this.z;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (this.a != null) {
            s();
        } else if (this.P != null) {
            k();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a() {
        int i2;
        short s;
        int i3 = this.r * this.s;
        byte[] bArr = this.L;
        if (bArr == null || bArr.length < i3) {
            this.L = new byte[i3];
        }
        if (this.I == null) {
            this.I = new short[4096];
        }
        if (this.J == null) {
            this.J = new byte[4096];
        }
        if (this.K == null) {
            this.K = new byte[4097];
        }
        int i4 = i();
        int i5 = 1 << i4;
        int i6 = i5 + 1;
        int i7 = i5 + 2;
        int i8 = i4 + 1;
        int i9 = (1 << i8) - 1;
        for (int i10 = 0; i10 < i5; i10++) {
            this.I[i10] = 0;
            this.J[i10] = (byte) i10;
        }
        int i11 = i8;
        int i12 = i7;
        int i13 = i9;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = -1;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        while (i15 < i3) {
            if (i16 != 0) {
                i2 = i8;
            } else if (i18 >= i11) {
                int i23 = i19 & i13;
                i19 >>= i11;
                i18 -= i11;
                if (i23 > i12 || i23 == i6) {
                    break;
                } else if (i23 == i5) {
                    i11 = i8;
                    i12 = i7;
                    i13 = i9;
                    i17 = -1;
                } else if (i17 == -1) {
                    this.K[i16] = this.J[i23];
                    i14 = i23;
                    i17 = i14;
                    i16++;
                } else {
                    if (i23 == i12) {
                        this.K[i16] = (byte) i14;
                        s = i17;
                        i16++;
                    } else {
                        s = i23;
                    }
                    while (s > i5) {
                        this.K[i16] = this.J[s];
                        s = this.I[s];
                        i16++;
                        i8 = i8;
                    }
                    i2 = i8;
                    byte[] bArr2 = this.J;
                    i14 = bArr2[s] & 255;
                    if (i12 >= 4096) {
                        break;
                    }
                    int i24 = i16 + 1;
                    byte b = (byte) i14;
                    this.K[i16] = b;
                    this.I[i12] = (short) i17;
                    bArr2[i12] = b;
                    i12++;
                    if ((i12 & i13) == 0 && i12 < 4096) {
                        i11++;
                        i13 += i12;
                    }
                    i16 = i24;
                    i17 = i23;
                }
            } else {
                if (i20 == 0) {
                    i20 = j();
                    if (i20 <= 0) {
                        break;
                    }
                    i21 = 0;
                }
                i19 += (this.B[i21] & 255) << i18;
                i18 += 8;
                i21++;
                i20--;
            }
            i16--;
            this.L[i22] = this.K[i16];
            i15++;
            i22++;
            i8 = i2;
        }
        for (int i25 = i22; i25 < i3; i25++) {
            this.L[i25] = 0;
        }
    }

    private boolean b() {
        return this.b != 0;
    }

    private int[] c(int i2) {
        int i3;
        int i4 = i2 * 3;
        byte[] bArr = new byte[i4];
        try {
            i3 = this.a.read(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            i3 = 0;
        }
        if (i3 < i4) {
            this.b = 1;
            return null;
        }
        int[] iArr = new int[256];
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = i5 + 1;
            int i8 = i7 + 1;
            iArr[i6] = ((bArr[i5] & 255) << 16) | (-16777216) | ((bArr[i7] & 255) << 8) | (bArr[i8] & 255);
            i5 = i8 + 1;
        }
        return iArr;
    }

    public b(InputStream inputStream, a aVar) {
        this.z = null;
        this.A = false;
        this.B = new byte[256];
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = false;
        this.G = 0;
        this.O = null;
        this.P = null;
        this.Q = 0;
        this.a = inputStream;
        this.O = aVar;
    }
}
