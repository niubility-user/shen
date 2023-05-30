package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class z8 {

    /* renamed from: h  reason: collision with root package name */
    public static final int f17558h = 1;

    /* renamed from: i  reason: collision with root package name */
    public static final int f17559i = 2;

    /* renamed from: j  reason: collision with root package name */
    public static final int f17560j = 3;

    /* renamed from: k  reason: collision with root package name */
    public static final int f17561k = 4;

    /* renamed from: l  reason: collision with root package name */
    public static final int f17562l = 5;

    /* renamed from: m  reason: collision with root package name */
    public static final int f17563m = 6;

    /* renamed from: n  reason: collision with root package name */
    public static final int f17564n = 100;
    public static final int o = 101;
    public static final int p = 102;
    public static final int q = 103;
    public static final int r = 104;
    public static final int s = 105;
    public static final int t = 10000;
    public static final int u = 108;
    public static final int v = 109;
    public static final int w = 110;
    public static final int x = 120;
    public int a;
    public double[] b;

    /* renamed from: c  reason: collision with root package name */
    public long f17565c;
    public boolean d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f17566e;

    /* renamed from: f  reason: collision with root package name */
    public Runnable f17567f;

    /* renamed from: g  reason: collision with root package name */
    private i8 f17568g;
    public static final z8 y = new z8(1, null);
    public static final z8 z = new z8(2, null, true);
    public static final z8 A = new z8(104, null);

    public z8() {
        this.f17565c = -1L;
        this.d = false;
    }

    public z8(int i2, double[] dArr) {
        this(i2, dArr, false);
    }

    public z8(int i2, double[] dArr, boolean z2) {
        this.f17565c = -1L;
        this.d = false;
        this.a = i2;
        this.b = dArr;
        this.f17566e = z2;
    }

    public z8(Runnable runnable) {
        this.f17565c = -1L;
        this.d = false;
        this.a = 6;
        this.f17567f = runnable;
    }

    public long a() {
        long j2 = this.f17565c;
        return j2 >= 0 ? j2 : this.a >= 100 ? 20L : 0L;
    }

    public void a(i8 i8Var) {
        this.f17568g = i8Var;
    }

    public boolean a(c9 c9Var) {
        i8 i8Var = this.f17568g;
        if (i8Var != null) {
            i8Var.onStart();
        }
        boolean d = d();
        c9Var.a(this);
        return d;
    }

    public void b() {
        i8 i8Var = this.f17568g;
        if (i8Var != null) {
            i8Var.onCancel();
        }
    }

    public void c() {
        i8 i8Var = this.f17568g;
        if (i8Var != null) {
            i8Var.onFinish();
        }
    }

    public boolean d() {
        return true;
    }
}
