package com.jingdong.manto.m.t0.d;

/* loaded from: classes15.dex */
public class a {

    /* renamed from: j  reason: collision with root package name */
    public static a f13617j = new C0622a().a();

    /* renamed from: k  reason: collision with root package name */
    public static boolean f13618k = false;

    /* renamed from: l  reason: collision with root package name */
    public static boolean f13619l = true;

    /* renamed from: m  reason: collision with root package name */
    public static boolean f13620m = true;
    public boolean a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public boolean f13621c;
    public long d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f13622e;

    /* renamed from: f  reason: collision with root package name */
    public boolean f13623f;

    /* renamed from: g  reason: collision with root package name */
    public String f13624g;

    /* renamed from: h  reason: collision with root package name */
    public int f13625h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f13626i;

    /* renamed from: com.jingdong.manto.m.t0.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public static class C0622a {
        public boolean a = a.f13618k;
        public int b = 0;

        /* renamed from: c  reason: collision with root package name */
        public boolean f13627c = false;
        long d = 20000;

        /* renamed from: e  reason: collision with root package name */
        public boolean f13628e = a.f13619l;

        /* renamed from: f  reason: collision with root package name */
        public boolean f13629f = a.f13620m;

        /* renamed from: g  reason: collision with root package name */
        public String f13630g = "medium";

        /* renamed from: h  reason: collision with root package name */
        public int f13631h = 10;

        /* renamed from: i  reason: collision with root package name */
        public boolean f13632i = true;

        public final a a() {
            return new a(this);
        }
    }

    public a(C0622a c0622a) {
        this.b = c0622a.b;
        this.f13621c = c0622a.f13627c;
        this.d = c0622a.d;
        this.a = c0622a.a;
        this.f13622e = c0622a.f13628e;
        this.f13623f = c0622a.f13629f;
        this.f13624g = c0622a.f13630g;
        this.f13625h = c0622a.f13631h;
        this.f13626i = c0622a.f13632i;
    }

    public static void a(a aVar) {
        f13617j = aVar;
    }

    public final String toString() {
        return "BleConfig{interval=" + this.b + ", allowDuplicatesKey=" + this.f13621c + ", actionTimeOutTime=" + this.d + ", debug=" + this.a + ", mainThread=" + this.f13622e + ", serial=" + this.f13623f + ", mode='" + this.f13624g + "', actionDelayTime=" + this.f13625h + '}';
    }
}
