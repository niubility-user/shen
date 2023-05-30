package com.xiaomi.push;

import com.xiaomi.push.u8;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* loaded from: classes11.dex */
public class f9 extends u8 {

    /* renamed from: m  reason: collision with root package name */
    private static int f18635m = 10000;

    /* renamed from: n  reason: collision with root package name */
    private static int f18636n = 10000;
    private static int o = 10000;
    private static int p = 10485760;
    private static int q = 104857600;

    /* loaded from: classes11.dex */
    public static class a extends u8.a {
        public a() {
            super(false, true);
        }

        public a(boolean z, boolean z2, int i2) {
            super(z, z2, i2);
        }

        @Override // com.xiaomi.push.u8.a, com.xiaomi.push.b9
        public y8 a(j9 j9Var) {
            f9 f9Var = new f9(j9Var, ((u8.a) this).f250a, this.b);
            int i2 = ((u8.a) this).a;
            if (i2 != 0) {
                f9Var.L(i2);
            }
            return f9Var;
        }
    }

    public f9(j9 j9Var, boolean z, boolean z2) {
        super(j9Var, z, z2);
    }

    @Override // com.xiaomi.push.u8, com.xiaomi.push.y8
    public w8 f() {
        byte a2 = a();
        int c2 = c();
        if (c2 <= f18636n) {
            return new w8(a2, c2);
        }
        throw new z8(3, "Thrift list size " + c2 + " out of range!");
    }

    @Override // com.xiaomi.push.u8, com.xiaomi.push.y8
    public x8 g() {
        byte a2 = a();
        byte a3 = a();
        int c2 = c();
        if (c2 <= f18635m) {
            return new x8(a2, a3, c2);
        }
        throw new z8(3, "Thrift map size " + c2 + " out of range!");
    }

    @Override // com.xiaomi.push.u8, com.xiaomi.push.y8
    public d9 h() {
        byte a2 = a();
        int c2 = c();
        if (c2 <= o) {
            return new d9(a2, c2);
        }
        throw new z8(3, "Thrift set size " + c2 + " out of range!");
    }

    @Override // com.xiaomi.push.u8, com.xiaomi.push.y8
    public String j() {
        int c2 = c();
        if (c2 > p) {
            throw new z8(3, "Thrift string size " + c2 + " out of range!");
        } else if (this.a.f() >= c2) {
            try {
                String str = new String(this.a.e(), this.a.a(), c2, "UTF-8");
                this.a.c(c2);
                return str;
            } catch (UnsupportedEncodingException unused) {
                throw new s8("JVM DOES NOT SUPPORT UTF-8");
            }
        } else {
            return K(c2);
        }
    }

    @Override // com.xiaomi.push.u8, com.xiaomi.push.y8
    public ByteBuffer k() {
        int c2 = c();
        if (c2 > q) {
            throw new z8(3, "Thrift binary size " + c2 + " out of range!");
        }
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
}
