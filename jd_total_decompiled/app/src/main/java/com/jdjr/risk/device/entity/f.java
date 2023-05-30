package com.jdjr.risk.device.entity;

/* loaded from: classes18.dex */
public class f extends a {
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f7387c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f7388e;

    /* renamed from: f  reason: collision with root package name */
    private String f7389f;

    /* renamed from: g  reason: collision with root package name */
    private String f7390g;

    /* renamed from: h  reason: collision with root package name */
    private String f7391h;

    /* renamed from: i  reason: collision with root package name */
    private String f7392i;

    /* renamed from: j  reason: collision with root package name */
    private String f7393j = "";

    /* renamed from: k  reason: collision with root package name */
    private String f7394k;

    public void a(String str) {
        this.b = str;
        this.a.put("imei", str);
    }

    public void b(String str) {
        this.f7387c = str;
        this.a.put("imei2", str);
    }

    public void c(String str) {
        this.d = str;
        this.a.put("serialno", str);
    }

    public void d(String str) {
        this.f7388e = str;
        this.a.put("android_id", str);
    }

    public void e(String str) {
        this.f7389f = str;
        this.a.put("oaId", str);
    }

    public void f(String str) {
        this.f7390g = str;
        this.a.put("imsi", str);
    }

    public void g(String str) {
        this.f7391h = str;
        this.a.put("imsi2", str);
    }

    public void h(String str) {
        this.f7392i = str;
        this.a.put("advertisingId", str);
    }

    public void i(String str) {
        this.f7394k = str;
        this.a.put("simSerialNumber", str);
    }

    public void j(String str) {
        this.f7393j = str;
        this.a.put("commonId", str);
    }
}
