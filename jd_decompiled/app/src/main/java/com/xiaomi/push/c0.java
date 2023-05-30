package com.xiaomi.push;

import java.io.InputStream;
import java.util.Vector;

/* loaded from: classes11.dex */
public final class c0 {
    private final byte[] a;
    private int b;

    /* renamed from: c */
    private int f18485c;
    private int d;

    /* renamed from: e */
    private final InputStream f18486e;

    /* renamed from: f */
    private int f18487f;

    /* renamed from: g */
    private int f18488g;

    /* renamed from: h */
    private int f18489h;

    /* renamed from: i */
    private int f18490i;

    /* renamed from: j */
    private int f18491j;

    /* renamed from: k */
    private int f18492k;

    private c0(InputStream inputStream) {
        this.f18489h = Integer.MAX_VALUE;
        this.f18491j = 64;
        this.f18492k = 67108864;
        this.a = new byte[4096];
        this.b = 0;
        this.d = 0;
        this.f18486e = inputStream;
    }

    private c0(byte[] bArr, int i2, int i3) {
        this.f18489h = Integer.MAX_VALUE;
        this.f18491j = 64;
        this.f18492k = 67108864;
        this.a = bArr;
        this.b = i3 + i2;
        this.d = i2;
        this.f18486e = null;
    }

    public static c0 f(InputStream inputStream) {
        return new c0(inputStream);
    }

    public static c0 g(byte[] bArr, int i2, int i3) {
        return new c0(bArr, i2, i3);
    }

    private boolean n(boolean z) {
        int i2 = this.d;
        int i3 = this.b;
        if (i2 >= i3) {
            int i4 = this.f18488g;
            if (i4 + i3 == this.f18489h) {
                if (z) {
                    throw n1.a();
                }
                return false;
            }
            this.f18488g = i4 + i3;
            this.d = 0;
            InputStream inputStream = this.f18486e;
            int read = inputStream == null ? -1 : inputStream.read(this.a);
            this.b = read;
            if (read == 0 || read < -1) {
                throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + this.b + "\nThe InputStream implementation is buggy.");
            } else if (read == -1) {
                this.b = 0;
                if (z) {
                    throw n1.a();
                }
                return false;
            } else {
                r();
                int i5 = this.f18488g + this.b + this.f18485c;
                if (i5 > this.f18492k || i5 < 0) {
                    throw n1.h();
                }
                return true;
            }
        }
        throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
    }

    private void r() {
        int i2 = this.b + this.f18485c;
        this.b = i2;
        int i3 = this.f18488g + i2;
        int i4 = this.f18489h;
        if (i3 <= i4) {
            this.f18485c = 0;
            return;
        }
        int i5 = i3 - i4;
        this.f18485c = i5;
        this.b = i2 - i5;
    }

    public byte a() {
        if (this.d == this.b) {
            n(true);
        }
        byte[] bArr = this.a;
        int i2 = this.d;
        this.d = i2 + 1;
        return bArr[i2];
    }

    public int b() {
        if (t()) {
            this.f18487f = 0;
            return 0;
        }
        int x = x();
        this.f18487f = x;
        if (x != 0) {
            return x;
        }
        throw n1.d();
    }

    public int c(int i2) {
        if (i2 >= 0) {
            int i3 = i2 + this.f18488g + this.d;
            int i4 = this.f18489h;
            if (i3 <= i4) {
                this.f18489h = i3;
                r();
                return i4;
            }
            throw n1.a();
        }
        throw n1.b();
    }

    public long d() {
        return v();
    }

    public a e() {
        int x = x();
        int i2 = this.b;
        int i3 = this.d;
        if (x > i2 - i3 || x <= 0) {
            return a.b(o(x));
        }
        a c2 = a.c(this.a, i3, x);
        this.d += x;
        return c2;
    }

    public String h() {
        int x = x();
        if (x > this.b - this.d || x <= 0) {
            return new String(o(x), "UTF-8");
        }
        String str = new String(this.a, this.d, x, "UTF-8");
        this.d += x;
        return str;
    }

    public void i() {
        int b;
        do {
            b = b();
            if (b == 0) {
                return;
            }
        } while (m(b));
    }

    public void j(int i2) {
        if (this.f18487f != i2) {
            throw n1.e();
        }
    }

    public void k(o2 o2Var) {
        int x = x();
        if (this.f18490i >= this.f18491j) {
            throw n1.g();
        }
        int c2 = c(x);
        this.f18490i++;
        o2Var.b(this);
        j(0);
        this.f18490i--;
        s(c2);
    }

    public boolean l() {
        return x() != 0;
    }

    public boolean m(int i2) {
        int a = z3.a(i2);
        if (a == 0) {
            p();
            return true;
        } else if (a == 1) {
            y();
            return true;
        } else if (a == 2) {
            w(x());
            return true;
        } else if (a == 3) {
            i();
            j(z3.b(z3.c(i2), 4));
            return true;
        } else if (a != 4) {
            if (a == 5) {
                z();
                return true;
            }
            throw n1.f();
        } else {
            return false;
        }
    }

    public byte[] o(int i2) {
        if (i2 < 0) {
            throw n1.b();
        }
        int i3 = this.f18488g;
        int i4 = this.d;
        int i5 = i3 + i4 + i2;
        int i6 = this.f18489h;
        if (i5 > i6) {
            w((i6 - i3) - i4);
            throw n1.a();
        }
        int i7 = this.b;
        if (i2 <= i7 - i4) {
            byte[] bArr = new byte[i2];
            System.arraycopy(this.a, i4, bArr, 0, i2);
            this.d += i2;
            return bArr;
        } else if (i2 >= 4096) {
            this.f18488g = i3 + i7;
            this.d = 0;
            this.b = 0;
            int i8 = i7 - i4;
            int i9 = i2 - i8;
            Vector vector = new Vector();
            while (i9 > 0) {
                int min = Math.min(i9, 4096);
                byte[] bArr2 = new byte[min];
                int i10 = 0;
                while (i10 < min) {
                    InputStream inputStream = this.f18486e;
                    int read = inputStream == null ? -1 : inputStream.read(bArr2, i10, min - i10);
                    if (read == -1) {
                        throw n1.a();
                    }
                    this.f18488g += read;
                    i10 += read;
                }
                i9 -= min;
                vector.addElement(bArr2);
            }
            byte[] bArr3 = new byte[i2];
            System.arraycopy(this.a, i4, bArr3, 0, i8);
            for (int i11 = 0; i11 < vector.size(); i11++) {
                byte[] bArr4 = (byte[]) vector.elementAt(i11);
                System.arraycopy(bArr4, 0, bArr3, i8, bArr4.length);
                i8 += bArr4.length;
            }
            return bArr3;
        } else {
            byte[] bArr5 = new byte[i2];
            int i12 = i7 - i4;
            System.arraycopy(this.a, i4, bArr5, 0, i12);
            this.d = this.b;
            while (true) {
                n(true);
                int i13 = i2 - i12;
                int i14 = this.b;
                if (i13 <= i14) {
                    System.arraycopy(this.a, 0, bArr5, i12, i13);
                    this.d = i13;
                    return bArr5;
                }
                System.arraycopy(this.a, 0, bArr5, i12, i14);
                int i15 = this.b;
                i12 += i15;
                this.d = i15;
            }
        }
    }

    public int p() {
        return x();
    }

    public long q() {
        return v();
    }

    public void s(int i2) {
        this.f18489h = i2;
        r();
    }

    public boolean t() {
        return this.d == this.b && !n(false);
    }

    public int u() {
        return x();
    }

    public long v() {
        long j2 = 0;
        for (int i2 = 0; i2 < 64; i2 += 7) {
            j2 |= (r3 & Byte.MAX_VALUE) << i2;
            if ((a() & 128) == 0) {
                return j2;
            }
        }
        throw n1.c();
    }

    public void w(int i2) {
        if (i2 < 0) {
            throw n1.b();
        }
        int i3 = this.f18488g;
        int i4 = this.d;
        int i5 = i3 + i4 + i2;
        int i6 = this.f18489h;
        if (i5 > i6) {
            w((i6 - i3) - i4);
            throw n1.a();
        }
        int i7 = this.b;
        if (i2 <= i7 - i4) {
            this.d = i4 + i2;
            return;
        }
        int i8 = i7 - i4;
        this.f18488g = i3 + i7;
        this.d = 0;
        this.b = 0;
        while (i8 < i2) {
            InputStream inputStream = this.f18486e;
            int skip = inputStream == null ? -1 : (int) inputStream.skip(i2 - i8);
            if (skip <= 0) {
                throw n1.a();
            }
            i8 += skip;
            this.f18488g += skip;
        }
    }

    public int x() {
        int i2;
        byte a = a();
        if (a >= 0) {
            return a;
        }
        int i3 = a & Byte.MAX_VALUE;
        byte a2 = a();
        if (a2 >= 0) {
            i2 = a2 << 7;
        } else {
            i3 |= (a2 & Byte.MAX_VALUE) << 7;
            byte a3 = a();
            if (a3 >= 0) {
                i2 = a3 << 14;
            } else {
                i3 |= (a3 & Byte.MAX_VALUE) << 14;
                byte a4 = a();
                if (a4 < 0) {
                    int i4 = i3 | ((a4 & Byte.MAX_VALUE) << 21);
                    byte a5 = a();
                    int i5 = i4 | (a5 << 28);
                    if (a5 < 0) {
                        for (int i6 = 0; i6 < 5; i6++) {
                            if (a() >= 0) {
                                return i5;
                            }
                        }
                        throw n1.c();
                    }
                    return i5;
                }
                i2 = a4 << 21;
            }
        }
        return i3 | i2;
    }

    public long y() {
        byte a = a();
        return ((a() & 255) << 8) | (a & 255) | ((a() & 255) << 16) | ((a() & 255) << 24) | ((a() & 255) << 32) | ((a() & 255) << 40) | ((a() & 255) << 48) | ((a() & 255) << 56);
    }

    public int z() {
        return (a() & 255) | ((a() & 255) << 8) | ((a() & 255) << 16) | ((a() & 255) << 24);
    }
}
