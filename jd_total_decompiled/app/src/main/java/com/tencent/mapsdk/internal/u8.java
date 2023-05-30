package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class u8 implements Cloneable {

    /* renamed from: f */
    private static final x8<Integer> f17305f = new p8();

    /* renamed from: g */
    private static final x8<Number> f17306g = new n8();
    public int a;
    public Class<?> b;

    /* renamed from: c */
    public s8 f17307c;
    private x8 d;

    /* renamed from: e */
    private Object f17308e;

    /* loaded from: classes9.dex */
    public static class b extends u8 {

        /* renamed from: h */
        public o8 f17309h;

        /* renamed from: i */
        public double f17310i;

        public b(int i2, o8 o8Var) {
            super(i2);
            this.b = Float.TYPE;
            this.f17307c = o8Var;
            this.f17309h = o8Var;
        }

        public b(int i2, double... dArr) {
            super(i2);
            a(dArr);
        }

        @Override // com.tencent.mapsdk.internal.u8
        public void a(float f2) {
            this.f17310i = this.f17309h.b(f2);
        }

        @Override // com.tencent.mapsdk.internal.u8
        public void a(g8 g8Var) {
            if (g8Var != null) {
                g8Var.a(this.a, Double.valueOf(this.f17310i));
            }
        }

        @Override // com.tencent.mapsdk.internal.u8
        public void a(double... dArr) {
            super.a(dArr);
            this.f17309h = (o8) this.f17307c;
        }

        @Override // com.tencent.mapsdk.internal.u8
        public Object b() {
            return Double.valueOf(this.f17310i);
        }

        @Override // com.tencent.mapsdk.internal.u8
        /* renamed from: e */
        public b clone() {
            b bVar = (b) super.clone();
            bVar.f17309h = (o8) bVar.f17307c;
            return bVar;
        }
    }

    /* loaded from: classes9.dex */
    public static class c extends u8 {

        /* renamed from: h */
        public q8 f17311h;

        /* renamed from: i */
        public int f17312i;

        public c(int i2, q8 q8Var) {
            super(i2);
            this.b = Integer.TYPE;
            this.f17307c = q8Var;
            this.f17311h = q8Var;
        }

        public c(int i2, int... iArr) {
            super(i2);
            a(iArr);
        }

        @Override // com.tencent.mapsdk.internal.u8
        public void a(float f2) {
            this.f17312i = this.f17311h.b(f2);
        }

        @Override // com.tencent.mapsdk.internal.u8
        public void a(g8 g8Var) {
            if (g8Var != null) {
                g8Var.a(this.a, Integer.valueOf(this.f17312i));
            }
        }

        @Override // com.tencent.mapsdk.internal.u8
        public void a(int... iArr) {
            super.a(iArr);
            this.f17311h = (q8) this.f17307c;
        }

        @Override // com.tencent.mapsdk.internal.u8
        public Object b() {
            return Integer.valueOf(this.f17312i);
        }

        @Override // com.tencent.mapsdk.internal.u8
        /* renamed from: e */
        public c clone() {
            c cVar = (c) super.clone();
            cVar.f17311h = (q8) cVar.f17307c;
            return cVar;
        }
    }

    private u8(int i2) {
        this.f17307c = null;
        this.a = i2;
    }

    public static u8 a(int i2, x8<Object> x8Var, Object... objArr) {
        u8 u8Var = new u8(i2);
        u8Var.a(objArr);
        u8Var.a(x8Var);
        return u8Var;
    }

    public static u8 a(int i2, double... dArr) {
        return new b(i2, dArr);
    }

    public static u8 a(int i2, int... iArr) {
        return new c(i2, iArr);
    }

    public static u8 a(int i2, r8... r8VarArr) {
        s8 a2 = s8.a(r8VarArr);
        u8 u8Var = new u8(i2);
        u8Var.f17307c = a2;
        return u8Var;
    }

    @Override // 
    /* renamed from: a */
    public u8 clone() {
        try {
            u8 u8Var = (u8) super.clone();
            u8Var.a = this.a;
            u8Var.f17307c = this.f17307c.clone();
            u8Var.d = this.d;
            return u8Var;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void a(float f2) {
        this.f17308e = this.f17307c.a(f2);
    }

    public void a(int i2) {
        this.a = i2;
    }

    public void a(g8 g8Var) {
        if (g8Var != null) {
            g8Var.a(this.a, b());
        }
    }

    public void a(x8 x8Var) {
        this.d = x8Var;
        this.f17307c.a(x8Var);
    }

    public void a(double... dArr) {
        this.b = Double.TYPE;
        this.f17307c = s8.a(dArr);
    }

    public void a(int... iArr) {
        this.b = Integer.TYPE;
        this.f17307c = s8.a(iArr);
    }

    public void a(r8... r8VarArr) {
        int length = r8VarArr.length;
        r8[] r8VarArr2 = new r8[Math.max(length, 2)];
        for (int i2 = 0; i2 < length; i2++) {
            r8VarArr2[i2] = r8VarArr[i2];
        }
        this.f17307c = new s8(r8VarArr2);
    }

    public void a(Object... objArr) {
        this.b = objArr[0].getClass();
        this.f17307c = s8.a(objArr);
    }

    public Object b() {
        return this.f17308e;
    }

    public int c() {
        return this.a;
    }

    public void d() {
        if (this.d == null) {
            Class<?> cls = this.b;
            this.d = cls == Integer.class ? f17305f : cls == Double.class ? f17306g : null;
        }
        x8 x8Var = this.d;
        if (x8Var != null) {
            this.f17307c.a(x8Var);
        }
    }

    public String toString() {
        return this.a + ": " + this.f17307c.toString();
    }
}
