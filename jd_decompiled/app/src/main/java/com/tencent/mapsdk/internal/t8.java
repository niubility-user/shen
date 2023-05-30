package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public final class t8 extends y8 {
    public int L;

    public t8(g8 g8Var) {
        super(g8Var);
    }

    private t8(g8 g8Var, int i2) {
        super(g8Var);
        d(i2);
    }

    public static t8 a(g8 g8Var, int i2, x8 x8Var, Object[] objArr) {
        t8 t8Var = new t8(g8Var, i2);
        t8Var.a(objArr);
        t8Var.a(x8Var);
        return t8Var;
    }

    public static t8 a(g8 g8Var, int i2, double... dArr) {
        t8 t8Var = new t8(g8Var, i2);
        t8Var.a(dArr);
        return t8Var;
    }

    public static t8 a(g8 g8Var, int i2, int... iArr) {
        t8 t8Var = new t8(g8Var, i2);
        t8Var.a(iArr);
        return t8Var;
    }

    public static t8 b(g8 g8Var, u8... u8VarArr) {
        t8 t8Var = new t8(g8Var);
        t8Var.a(u8VarArr);
        return t8Var;
    }

    @Override // com.tencent.mapsdk.internal.y8, com.tencent.mapsdk.internal.j8
    /* renamed from: E  reason: merged with bridge method [inline-methods] */
    public t8 clone() {
        return (t8) super.clone();
    }

    public int F() {
        return this.L;
    }

    public g8 G() {
        return this.t;
    }

    @Override // com.tencent.mapsdk.internal.y8
    public void a(double... dArr) {
        u8[] u8VarArr = this.r;
        if (u8VarArr == null || u8VarArr.length == 0) {
            a(u8.a(this.L, dArr));
        } else {
            super.a(dArr);
        }
    }

    @Override // com.tencent.mapsdk.internal.y8
    public void a(int... iArr) {
        u8[] u8VarArr = this.r;
        if (u8VarArr == null || u8VarArr.length == 0) {
            a(u8.a(this.L, iArr));
        } else {
            super.a(iArr);
        }
    }

    @Override // com.tencent.mapsdk.internal.y8
    public void a(Object... objArr) {
        u8[] u8VarArr = this.r;
        if (u8VarArr == null || u8VarArr.length == 0) {
            a(u8.a(this.L, null, objArr));
        } else {
            super.a(objArr);
        }
    }

    public void d(int i2) {
        u8[] u8VarArr = this.r;
        if (u8VarArr != null) {
            u8 u8Var = u8VarArr[0];
            this.s.remove(Integer.valueOf(u8Var.c()));
            this.s.put(Integer.valueOf(i2), u8Var);
        }
        this.L = i2;
    }

    @Override // com.tencent.mapsdk.internal.y8, com.tencent.mapsdk.internal.j8
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    public t8 a(long j2) {
        super.a(j2);
        return this;
    }

    @Override // com.tencent.mapsdk.internal.y8
    public String toString() {
        String str = "ObjectAnimator@" + Integer.toHexString(hashCode()) + ", target " + this.t;
        if (this.r != null) {
            for (int i2 = 0; i2 < this.r.length; i2++) {
                str = str + "\n    " + this.r[i2].toString();
            }
        }
        return str;
    }
}
