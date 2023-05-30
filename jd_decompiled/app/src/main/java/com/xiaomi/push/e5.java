package com.xiaomi.push;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* loaded from: classes11.dex */
public class e5 {

    /* renamed from: g  reason: collision with root package name */
    private static String f18565g = r6.a(5) + "-";

    /* renamed from: h  reason: collision with root package name */
    private static long f18566h = 0;

    /* renamed from: i  reason: collision with root package name */
    private static final byte[] f18567i = new byte[0];
    private b3 a;
    private short b;

    /* renamed from: c  reason: collision with root package name */
    private byte[] f18568c;
    String d;

    /* renamed from: e  reason: collision with root package name */
    int f18569e;

    /* renamed from: f  reason: collision with root package name */
    private final long f18570f;

    public e5() {
        this.b = (short) 2;
        this.f18568c = f18567i;
        this.d = null;
        this.f18570f = System.currentTimeMillis();
        this.a = new b3();
        this.f18569e = 1;
    }

    e5(b3 b3Var, short s, byte[] bArr) {
        this.b = (short) 2;
        this.f18568c = f18567i;
        this.d = null;
        this.f18570f = System.currentTimeMillis();
        this.a = b3Var;
        this.b = s;
        this.f18568c = bArr;
        this.f18569e = 2;
    }

    public static synchronized String C() {
        String sb;
        synchronized (e5.class) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(f18565g);
            long j2 = f18566h;
            f18566h = 1 + j2;
            sb2.append(Long.toString(j2));
            sb = sb2.toString();
        }
        return sb;
    }

    @Deprecated
    public static e5 c(g6 g6Var, String str) {
        int i2;
        e5 e5Var = new e5();
        try {
            i2 = Integer.parseInt(g6Var.m());
        } catch (Exception e2) {
            g.j.a.a.a.c.o("Blob parse chid err " + e2.getMessage());
            i2 = 1;
        }
        e5Var.h(i2);
        e5Var.k(g6Var.l());
        e5Var.B(g6Var.q());
        e5Var.v(g6Var.s());
        e5Var.l("XMLMSG", null);
        try {
            e5Var.n(g6Var.f().getBytes("utf8"), str);
            if (TextUtils.isEmpty(str)) {
                e5Var.m((short) 3);
            } else {
                e5Var.m((short) 2);
                e5Var.l("SECMSG", null);
            }
        } catch (UnsupportedEncodingException e3) {
            g.j.a.a.a.c.o("Blob setPayload err\uff1a " + e3.getMessage());
        }
        return e5Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static e5 d(ByteBuffer byteBuffer) {
        try {
            ByteBuffer slice = byteBuffer.slice();
            short s = slice.getShort(0);
            short s2 = slice.getShort(2);
            int i2 = slice.getInt(4);
            b3 b3Var = new b3();
            b3Var.d(slice.array(), slice.arrayOffset() + 8, s2);
            byte[] bArr = new byte[i2];
            slice.position(s2 + 8);
            slice.get(bArr, 0, i2);
            return new e5(b3Var, s, bArr);
        } catch (Exception e2) {
            g.j.a.a.a.c.o("read Blob err :" + e2.getMessage());
            throw new IOException("Malformed Input");
        }
    }

    public void A(long j2) {
        this.a.A(j2);
    }

    public void B(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int indexOf = str.indexOf(DYConstants.DY_REGEX_AT);
        try {
            long parseLong = Long.parseLong(str.substring(0, indexOf));
            int indexOf2 = str.indexOf("/", indexOf);
            String substring = str.substring(indexOf + 1, indexOf2);
            String substring2 = str.substring(indexOf2 + 1);
            this.a.m(parseLong);
            this.a.o(substring);
            this.a.u(substring2);
        } catch (Exception e2) {
            g.j.a.a.a.c.o("Blob parse user err " + e2.getMessage());
        }
    }

    public String D() {
        String L = this.a.L();
        if ("ID_NOT_AVAILABLE".equals(L)) {
            return null;
        }
        if (this.a.R()) {
            return L;
        }
        String C = C();
        this.a.K(C);
        return C;
    }

    public String E() {
        return this.d;
    }

    public String F() {
        if (this.a.w()) {
            return Long.toString(this.a.j()) + DYConstants.DY_REGEX_AT + this.a.p() + "/" + this.a.v();
        }
        return null;
    }

    public int a() {
        return this.a.x();
    }

    public long b() {
        return this.f18570f;
    }

    public String e() {
        return this.a.C();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuffer f(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            byteBuffer = ByteBuffer.allocate(x());
        }
        byteBuffer.putShort(this.b);
        byteBuffer.putShort((short) this.a.a());
        byteBuffer.putInt(this.f18568c.length);
        int position = byteBuffer.position();
        this.a.f(byteBuffer.array(), byteBuffer.arrayOffset() + position, this.a.a());
        byteBuffer.position(position + this.a.a());
        byteBuffer.put(this.f18568c);
        return byteBuffer;
    }

    public short g() {
        return this.b;
    }

    public void h(int i2) {
        this.a.l(i2);
    }

    public void i(long j2) {
        this.a.m(j2);
    }

    public void j(long j2, String str, String str2) {
        if (j2 != 0) {
            this.a.m(j2);
        }
        if (!TextUtils.isEmpty(str)) {
            this.a.o(str);
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.a.u(str2);
    }

    public void k(String str) {
        this.a.K(str);
    }

    public void l(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("command should not be empty");
        }
        this.a.B(str);
        this.a.k();
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.a.G(str2);
    }

    public void m(short s) {
        this.b = s;
    }

    public void n(byte[] bArr, String str) {
        if (TextUtils.isEmpty(str)) {
            this.a.z(0);
            this.f18568c = bArr;
            return;
        }
        this.a.z(1);
        this.f18568c = com.xiaomi.push.service.r0.i(com.xiaomi.push.service.r0.g(str, D()), bArr);
    }

    public boolean o() {
        return this.a.U();
    }

    public byte[] p() {
        return f5.a(this, this.f18568c);
    }

    public byte[] q(String str) {
        if (this.a.J() == 1) {
            return f5.a(this, com.xiaomi.push.service.r0.i(com.xiaomi.push.service.r0.g(str, D()), this.f18568c));
        }
        if (this.a.J() == 0) {
            return f5.a(this, this.f18568c);
        }
        g.j.a.a.a.c.o("unknow cipher = " + this.a.J());
        return f5.a(this, this.f18568c);
    }

    public int r() {
        return this.a.N();
    }

    public long s() {
        return this.a.r();
    }

    public String t() {
        return this.a.H();
    }

    public String toString() {
        return "Blob [chid=" + a() + "; Id=" + com.xiaomi.push.service.f0.b(D()) + "; cmd=" + e() + "; type=" + ((int) g()) + "; from=" + F() + " ]";
    }

    public void u(long j2) {
        this.a.t(j2);
    }

    public void v(String str) {
        this.d = str;
    }

    public boolean w() {
        return this.a.W();
    }

    public int x() {
        return this.a.i() + 8 + this.f18568c.length;
    }

    public long y() {
        return this.a.j();
    }

    public String z() {
        return this.a.P();
    }
}
