package com.xiaomi.push;

import cn.com.union.fido.bean.uafclient.ErrorCode;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* loaded from: classes11.dex */
public class u8 extends y8 {

    /* renamed from: l  reason: collision with root package name */
    private static final e9 f19268l = new e9();
    protected int b;

    /* renamed from: c  reason: collision with root package name */
    protected boolean f19269c;
    private byte[] d;

    /* renamed from: e  reason: collision with root package name */
    private byte[] f19270e;

    /* renamed from: f  reason: collision with root package name */
    private byte[] f19271f;

    /* renamed from: g  reason: collision with root package name */
    private byte[] f19272g;

    /* renamed from: h  reason: collision with root package name */
    private byte[] f19273h;

    /* renamed from: i  reason: collision with root package name */
    private byte[] f19274i;

    /* renamed from: j  reason: collision with root package name */
    private byte[] f19275j;

    /* renamed from: k  reason: collision with root package name */
    private byte[] f19276k;

    /* loaded from: classes11.dex */
    public static class a implements b9 {
        protected int a;

        /* renamed from: a  reason: collision with other field name */
        protected boolean f250a;
        protected boolean b;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i2) {
            this.f250a = false;
            this.b = true;
            this.f250a = z;
            this.b = z2;
            this.a = i2;
        }

        @Override // com.xiaomi.push.b9
        public y8 a(j9 j9Var) {
            u8 u8Var = new u8(j9Var, this.f250a, this.b);
            int i2 = this.a;
            if (i2 != 0) {
                u8Var.L(i2);
            }
            return u8Var;
        }
    }

    public u8(j9 j9Var, boolean z, boolean z2) {
        super(j9Var);
        this.f19269c = false;
        this.d = new byte[1];
        this.f19270e = new byte[2];
        this.f19271f = new byte[4];
        this.f19272g = new byte[8];
        this.f19273h = new byte[1];
        this.f19274i = new byte[2];
        this.f19275j = new byte[4];
        this.f19276k = new byte[8];
    }

    private int J(byte[] bArr, int i2, int i3) {
        M(i3);
        return this.a.g(bArr, i2, i3);
    }

    @Override // com.xiaomi.push.y8
    public void A() {
        n((byte) 0);
    }

    @Override // com.xiaomi.push.y8
    public void B() {
    }

    @Override // com.xiaomi.push.y8
    public void C() {
    }

    @Override // com.xiaomi.push.y8
    public void D() {
    }

    @Override // com.xiaomi.push.y8
    public void E() {
    }

    @Override // com.xiaomi.push.y8
    public void F() {
    }

    @Override // com.xiaomi.push.y8
    public void G() {
    }

    @Override // com.xiaomi.push.y8
    public void H() {
    }

    public String K(int i2) {
        try {
            M(i2);
            byte[] bArr = new byte[i2];
            this.a.g(bArr, 0, i2);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new s8("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void L(int i2) {
        this.b = i2;
        this.f19269c = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void M(int i2) {
        if (i2 < 0) {
            throw new s8("Negative length: " + i2);
        } else if (this.f19269c) {
            int i3 = this.b - i2;
            this.b = i3;
            if (i3 >= 0) {
                return;
            }
            throw new s8("Message length exceeded: " + i2);
        }
    }

    @Override // com.xiaomi.push.y8
    public byte a() {
        if (this.a.f() < 1) {
            J(this.f19273h, 0, 1);
            return this.f19273h[0];
        }
        byte b = this.a.e()[this.a.a()];
        this.a.c(1);
        return b;
    }

    @Override // com.xiaomi.push.y8
    public double b() {
        return Double.longBitsToDouble(d());
    }

    @Override // com.xiaomi.push.y8
    public int c() {
        byte[] bArr = this.f19275j;
        int i2 = 0;
        if (this.a.f() >= 4) {
            bArr = this.a.e();
            i2 = this.a.a();
            this.a.c(4);
        } else {
            J(this.f19275j, 0, 4);
        }
        return (bArr[i2 + 3] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
    }

    @Override // com.xiaomi.push.y8
    public long d() {
        byte[] bArr = this.f19276k;
        int i2 = 0;
        if (this.a.f() >= 8) {
            bArr = this.a.e();
            i2 = this.a.a();
            this.a.c(8);
        } else {
            J(this.f19276k, 0, 8);
        }
        return (bArr[i2 + 7] & 255) | ((bArr[i2] & 255) << 56) | ((bArr[i2 + 1] & 255) << 48) | ((bArr[i2 + 2] & 255) << 40) | ((bArr[i2 + 3] & 255) << 32) | ((bArr[i2 + 4] & 255) << 24) | ((bArr[i2 + 5] & 255) << 16) | ((bArr[i2 + 6] & 255) << 8);
    }

    @Override // com.xiaomi.push.y8
    public v8 e() {
        byte a2 = a();
        return new v8("", a2, a2 == 0 ? (short) 0 : l());
    }

    @Override // com.xiaomi.push.y8
    public w8 f() {
        return new w8(a(), c());
    }

    @Override // com.xiaomi.push.y8
    public x8 g() {
        return new x8(a(), a(), c());
    }

    @Override // com.xiaomi.push.y8
    public d9 h() {
        return new d9(a(), c());
    }

    @Override // com.xiaomi.push.y8
    public e9 i() {
        return f19268l;
    }

    @Override // com.xiaomi.push.y8
    public String j() {
        int c2 = c();
        if (this.a.f() >= c2) {
            try {
                String str = new String(this.a.e(), this.a.a(), c2, "UTF-8");
                this.a.c(c2);
                return str;
            } catch (UnsupportedEncodingException unused) {
                throw new s8("JVM DOES NOT SUPPORT UTF-8");
            }
        }
        return K(c2);
    }

    @Override // com.xiaomi.push.y8
    public ByteBuffer k() {
        int c2 = c();
        M(c2);
        if (this.a.f() >= c2) {
            ByteBuffer wrap = ByteBuffer.wrap(this.a.e(), this.a.a(), c2);
            this.a.c(c2);
            return wrap;
        }
        byte[] bArr = new byte[c2];
        this.a.g(bArr, 0, c2);
        return ByteBuffer.wrap(bArr);
    }

    @Override // com.xiaomi.push.y8
    public short l() {
        byte[] bArr = this.f19274i;
        int i2 = 0;
        if (this.a.f() >= 2) {
            bArr = this.a.e();
            i2 = this.a.a();
            this.a.c(2);
        } else {
            J(this.f19274i, 0, 2);
        }
        return (short) ((bArr[i2 + 1] & 255) | ((bArr[i2] & 255) << 8));
    }

    @Override // com.xiaomi.push.y8
    public void m() {
    }

    @Override // com.xiaomi.push.y8
    public void n(byte b) {
        byte[] bArr = this.d;
        bArr[0] = b;
        this.a.d(bArr, 0, 1);
    }

    @Override // com.xiaomi.push.y8
    public void o(int i2) {
        byte[] bArr = this.f19271f;
        bArr[0] = (byte) ((i2 >> 24) & 255);
        bArr[1] = (byte) ((i2 >> 16) & 255);
        bArr[2] = (byte) ((i2 >> 8) & 255);
        bArr[3] = (byte) (i2 & 255);
        this.a.d(bArr, 0, 4);
    }

    @Override // com.xiaomi.push.y8
    public void p(long j2) {
        byte[] bArr = this.f19272g;
        bArr[0] = (byte) ((j2 >> 56) & 255);
        bArr[1] = (byte) ((j2 >> 48) & 255);
        bArr[2] = (byte) ((j2 >> 40) & 255);
        bArr[3] = (byte) ((j2 >> 32) & 255);
        bArr[4] = (byte) ((j2 >> 24) & 255);
        bArr[5] = (byte) ((j2 >> 16) & 255);
        bArr[6] = (byte) ((j2 >> 8) & 255);
        bArr[7] = (byte) (j2 & 255);
        this.a.d(bArr, 0, 8);
    }

    @Override // com.xiaomi.push.y8
    public void q(v8 v8Var) {
        n(v8Var.b);
        w(v8Var.f19282c);
    }

    @Override // com.xiaomi.push.y8
    public void r(w8 w8Var) {
        n(w8Var.a);
        o(w8Var.b);
    }

    @Override // com.xiaomi.push.y8
    public void s(x8 x8Var) {
        n(x8Var.a);
        n(x8Var.b);
        o(x8Var.f19321c);
    }

    @Override // com.xiaomi.push.y8
    public void t(e9 e9Var) {
    }

    @Override // com.xiaomi.push.y8
    public void u(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            o(bytes.length);
            this.a.d(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new s8("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.xiaomi.push.y8
    public void v(ByteBuffer byteBuffer) {
        int limit = (byteBuffer.limit() - byteBuffer.position()) - byteBuffer.arrayOffset();
        o(limit);
        this.a.d(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    @Override // com.xiaomi.push.y8
    public void w(short s) {
        byte[] bArr = this.f19270e;
        bArr[0] = (byte) ((s >> 8) & 255);
        bArr[1] = (byte) (s & ErrorCode.UNKNOWN);
        this.a.d(bArr, 0, 2);
    }

    @Override // com.xiaomi.push.y8
    public void x(boolean z) {
        n(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.xiaomi.push.y8
    public boolean y() {
        return a() == 1;
    }

    @Override // com.xiaomi.push.y8
    public void z() {
    }
}
