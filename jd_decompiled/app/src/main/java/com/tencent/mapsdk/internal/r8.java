package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;

/* loaded from: classes9.dex */
public abstract class r8 implements Cloneable {
    public float a;
    public Class<?> b;

    /* renamed from: c  reason: collision with root package name */
    private Interpolator f17193c = null;
    public boolean d = false;

    /* loaded from: classes9.dex */
    public static class a extends r8 {

        /* renamed from: e  reason: collision with root package name */
        public double f17194e;

        public a(float f2) {
            this.a = f2;
            this.b = Double.TYPE;
        }

        public a(float f2, double d) {
            this.a = f2;
            this.f17194e = d;
            this.b = Double.TYPE;
            this.d = true;
        }

        @Override // com.tencent.mapsdk.internal.r8
        public void a(Object obj) {
            if (obj == null || obj.getClass() != Double.class) {
                return;
            }
            this.f17194e = ((Double) obj).doubleValue();
            this.d = true;
        }

        @Override // com.tencent.mapsdk.internal.r8
        public Object e() {
            return Double.valueOf(this.f17194e);
        }

        @Override // com.tencent.mapsdk.internal.r8
        /* renamed from: g  reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a(b(), this.f17194e);
            aVar.a(c());
            return aVar;
        }

        public double h() {
            return this.f17194e;
        }
    }

    /* loaded from: classes9.dex */
    public static class b extends r8 {

        /* renamed from: e  reason: collision with root package name */
        public int f17195e;

        public b(float f2) {
            this.a = f2;
            this.b = Integer.TYPE;
        }

        public b(float f2, int i2) {
            this.a = f2;
            this.f17195e = i2;
            this.b = Integer.TYPE;
            this.d = true;
        }

        @Override // com.tencent.mapsdk.internal.r8
        public void a(Object obj) {
            if (obj == null || obj.getClass() != Integer.class) {
                return;
            }
            this.f17195e = ((Integer) obj).intValue();
            this.d = true;
        }

        @Override // com.tencent.mapsdk.internal.r8
        public Object e() {
            return Integer.valueOf(this.f17195e);
        }

        @Override // com.tencent.mapsdk.internal.r8
        /* renamed from: g  reason: merged with bridge method [inline-methods] */
        public b clone() {
            b bVar = new b(b(), this.f17195e);
            bVar.a(c());
            return bVar;
        }

        public int h() {
            return this.f17195e;
        }
    }

    /* loaded from: classes9.dex */
    public static class c extends r8 {

        /* renamed from: e  reason: collision with root package name */
        public Object f17196e;

        public c(float f2, Object obj) {
            this.a = f2;
            this.f17196e = obj;
            boolean z = obj != null;
            this.d = z;
            this.b = z ? obj.getClass() : Object.class;
        }

        @Override // com.tencent.mapsdk.internal.r8
        public void a(Object obj) {
            this.f17196e = obj;
            this.d = obj != null;
        }

        @Override // com.tencent.mapsdk.internal.r8
        public Object e() {
            return this.f17196e;
        }

        @Override // com.tencent.mapsdk.internal.r8
        /* renamed from: g  reason: merged with bridge method [inline-methods] */
        public c clone() {
            c cVar = new c(b(), this.f17196e);
            cVar.a(c());
            return cVar;
        }
    }

    public static r8 a(float f2) {
        return new a(f2);
    }

    public static r8 a(float f2, double d) {
        return new a(f2, d);
    }

    public static r8 a(float f2, int i2) {
        return new b(f2, i2);
    }

    public static r8 a(float f2, Object obj) {
        return new c(f2, obj);
    }

    public static r8 b(float f2) {
        return new b(f2);
    }

    public static r8 c(float f2) {
        return new c(f2, null);
    }

    @Override // 
    /* renamed from: a */
    public abstract r8 clone();

    public void a(Interpolator interpolator) {
        this.f17193c = interpolator;
    }

    public abstract void a(Object obj);

    public float b() {
        return this.a;
    }

    public Interpolator c() {
        return this.f17193c;
    }

    public Class d() {
        return this.b;
    }

    public void d(float f2) {
        this.a = f2;
    }

    public abstract Object e();

    public boolean f() {
        return this.d;
    }
}
