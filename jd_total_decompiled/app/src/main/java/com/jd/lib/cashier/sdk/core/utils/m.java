package com.jd.lib.cashier.sdk.core.utils;

/* loaded from: classes14.dex */
public class m {

    /* renamed from: f  reason: collision with root package name */
    private static volatile m f3094f;
    private volatile String a = "";
    private volatile String b = "";

    /* renamed from: c  reason: collision with root package name */
    private volatile String f3095c = "";
    private volatile String d = "";

    /* renamed from: e  reason: collision with root package name */
    private volatile boolean f3096e;

    private m() {
    }

    public static m f() {
        if (f3094f == null) {
            synchronized (m.class) {
                if (f3094f == null) {
                    f3094f = new m();
                }
            }
        }
        return f3094f;
    }

    public void a() {
        this.f3096e = false;
    }

    public void b() {
        this.d = "";
    }

    public void c() {
        this.a = "";
    }

    public void d() {
        this.f3095c = "";
    }

    public void e() {
        this.b = "";
    }

    public boolean g() {
        return this.f3096e;
    }

    public String h() {
        return this.d;
    }

    public String i() {
        return this.a;
    }

    public String j() {
        return this.f3095c;
    }

    public String k() {
        return this.b;
    }

    public synchronized void l(boolean z) {
        this.f3096e = z;
    }

    public synchronized void m(String str) {
        this.d = str;
    }

    public synchronized void n(String str) {
        this.a = str;
    }

    public synchronized void o(String str) {
        this.f3095c = str;
    }

    public synchronized void p(String str) {
        this.b = str;
    }
}
