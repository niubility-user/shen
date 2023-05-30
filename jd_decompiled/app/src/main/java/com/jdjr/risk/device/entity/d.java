package com.jdjr.risk.device.entity;

/* loaded from: classes18.dex */
public class d extends a {
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f7371c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f7372e;

    /* renamed from: f  reason: collision with root package name */
    private String f7373f;

    /* renamed from: g  reason: collision with root package name */
    private String f7374g;

    /* renamed from: h  reason: collision with root package name */
    private String f7375h;

    public void a(String str) {
        this.b = str;
        this.a.put("p_cpuabi", str);
    }

    public void b(String str) {
        this.f7371c = str;
        this.a.put("cpu_usage", str);
    }

    public void c(String str) {
        this.d = str;
        this.a.put("cpu_num", str);
    }

    public void d(String str) {
        this.f7372e = str;
        this.a.put("cpu_freq", str);
    }

    public void e(String str) {
        this.f7374g = str;
        this.a.put("cpuName", str);
    }

    public void f(String str) {
        this.f7373f = str;
        this.a.put("p_cpuabi_c", str);
    }

    public void g(String str) {
        this.f7375h = str;
        this.a.put("cpuMaxFreq", str);
    }
}
