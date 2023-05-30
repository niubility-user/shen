package com.jdjr.risk.device.entity;

/* loaded from: classes18.dex */
public class b extends a {
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f7355c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f7356e;

    /* renamed from: f  reason: collision with root package name */
    private String f7357f;

    /* renamed from: g  reason: collision with root package name */
    private String f7358g;

    /* renamed from: h  reason: collision with root package name */
    private String f7359h;

    public void a(String str) {
        this.b = str;
        this.a.put("package_name", str);
    }

    public void b(String str) {
        this.f7355c = str;
        this.a.put("appName", str);
    }

    public void c(String str) {
        this.d = str;
        this.a.put("appVersion", str);
    }

    public void d(String str) {
        this.f7356e = str;
        this.a.put("client_version", str);
    }

    public void e(String str) {
        this.f7357f = str;
        this.a.put("sig_hash", str);
    }

    public void f(String str) {
        this.f7358g = str;
        this.a.put("channel", str);
    }

    public void g(String str) {
        this.f7359h = str;
        this.a.put("integrity", str);
    }
}
