package com.xiaomi.push;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: classes11.dex */
public final class y0 {
    private final byte[] a;
    private final int b;

    /* renamed from: c */
    private int f19322c;
    private final OutputStream d;

    /* loaded from: classes11.dex */
    public static class a extends IOException {
        a() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    private y0(OutputStream outputStream, byte[] bArr) {
        this.d = outputStream;
        this.a = bArr;
        this.f19322c = 0;
        this.b = bArr.length;
    }

    private y0(byte[] bArr, int i2, int i3) {
        this.d = null;
        this.a = bArr;
        this.f19322c = i2;
        this.b = i2 + i3;
    }

    public static int G(int i2) {
        return V(i2);
    }

    public static int H(int i2, int i3) {
        return P(i2) + G(i3);
    }

    public static int I(int i2, long j2) {
        return P(i2) + J(j2);
    }

    public static int J(long j2) {
        return Q(j2);
    }

    public static int P(int i2) {
        return V(z3.b(i2, 0));
    }

    public static int Q(long j2) {
        if (((-128) & j2) == 0) {
            return 1;
        }
        if (((-16384) & j2) == 0) {
            return 2;
        }
        if (((-2097152) & j2) == 0) {
            return 3;
        }
        if (((-268435456) & j2) == 0) {
            return 4;
        }
        if (((-34359738368L) & j2) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j2) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j2) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j2) == 0) {
            return 8;
        }
        return (j2 & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    private void R() {
        OutputStream outputStream = this.d;
        if (outputStream == null) {
            throw new a();
        }
        outputStream.write(this.a, 0, this.f19322c);
        this.f19322c = 0;
    }

    public static int V(int i2) {
        if ((i2 & (-128)) == 0) {
            return 1;
        }
        if ((i2 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i2) == 0) {
            return 3;
        }
        return (i2 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int b(int i2) {
        if (i2 >= 0) {
            return V(i2);
        }
        return 10;
    }

    public static int c(int i2, int i3) {
        return P(i2) + b(i3);
    }

    public static int d(int i2, long j2) {
        return P(i2) + i(j2);
    }

    public static int e(int i2, com.xiaomi.push.a aVar) {
        return P(i2) + j(aVar);
    }

    public static int f(int i2, o2 o2Var) {
        return P(i2) + k(o2Var);
    }

    public static int g(int i2, String str) {
        return P(i2) + l(str);
    }

    public static int h(int i2, boolean z) {
        return P(i2) + m(z);
    }

    public static int i(long j2) {
        return Q(j2);
    }

    public static int j(com.xiaomi.push.a aVar) {
        return V(aVar.a()) + aVar.a();
    }

    public static int k(o2 o2Var) {
        int i2 = o2Var.i();
        return V(i2) + i2;
    }

    public static int l(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return V(bytes.length) + bytes.length;
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    public static int m(boolean z) {
        return 1;
    }

    public static y0 n(OutputStream outputStream) {
        return o(outputStream, 4096);
    }

    public static y0 o(OutputStream outputStream, int i2) {
        return new y0(outputStream, new byte[i2]);
    }

    public static y0 p(byte[] bArr, int i2, int i3) {
        return new y0(bArr, i2, i3);
    }

    public void A(com.xiaomi.push.a aVar) {
        byte[] d = aVar.d();
        W(d.length);
        E(d);
    }

    public void B(o2 o2Var) {
        W(o2Var.a());
        o2Var.e(this);
    }

    public void C(String str) {
        byte[] bytes = str.getBytes("UTF-8");
        W(bytes.length);
        E(bytes);
    }

    public void D(boolean z) {
        S(z ? 1 : 0);
    }

    public void E(byte[] bArr) {
        F(bArr, 0, bArr.length);
    }

    public void F(byte[] bArr, int i2, int i3) {
        int i4 = this.b;
        int i5 = this.f19322c;
        if (i4 - i5 >= i3) {
            System.arraycopy(bArr, i2, this.a, i5, i3);
            this.f19322c += i3;
            return;
        }
        int i6 = i4 - i5;
        System.arraycopy(bArr, i2, this.a, i5, i6);
        int i7 = i2 + i6;
        int i8 = i3 - i6;
        this.f19322c = this.b;
        R();
        if (i8 > this.b) {
            this.d.write(bArr, i7, i8);
            return;
        }
        System.arraycopy(bArr, i7, this.a, 0, i8);
        this.f19322c = i8;
    }

    public void K() {
        if (a() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void L(int i2) {
        W(i2);
    }

    public void M(int i2, int i3) {
        T(i2, 0);
        L(i3);
    }

    public void N(int i2, long j2) {
        T(i2, 0);
        O(j2);
    }

    public void O(long j2) {
        U(j2);
    }

    public void S(int i2) {
        r((byte) i2);
    }

    public void T(int i2, int i3) {
        W(z3.b(i2, i3));
    }

    public void U(long j2) {
        while (((-128) & j2) != 0) {
            S((((int) j2) & 127) | 128);
            j2 >>>= 7;
        }
        S((int) j2);
    }

    public void W(int i2) {
        while ((i2 & (-128)) != 0) {
            S((i2 & 127) | 128);
            i2 >>>= 7;
        }
        S(i2);
    }

    public int a() {
        if (this.d == null) {
            return this.b - this.f19322c;
        }
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
    }

    public void q() {
        if (this.d != null) {
            R();
        }
    }

    public void r(byte b) {
        if (this.f19322c == this.b) {
            R();
        }
        byte[] bArr = this.a;
        int i2 = this.f19322c;
        this.f19322c = i2 + 1;
        bArr[i2] = b;
    }

    public void s(int i2) {
        if (i2 >= 0) {
            W(i2);
        } else {
            U(i2);
        }
    }

    public void t(int i2, int i3) {
        T(i2, 0);
        s(i3);
    }

    public void u(int i2, long j2) {
        T(i2, 0);
        z(j2);
    }

    public void v(int i2, com.xiaomi.push.a aVar) {
        T(i2, 2);
        A(aVar);
    }

    public void w(int i2, o2 o2Var) {
        T(i2, 2);
        B(o2Var);
    }

    public void x(int i2, String str) {
        T(i2, 2);
        C(str);
    }

    public void y(int i2, boolean z) {
        T(i2, 0);
        D(z);
    }

    public void z(long j2) {
        U(j2);
    }
}
