package com.jdjr.risk.device.entity;

/* loaded from: classes18.dex */
public class i extends a {
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f7417c;
    private String d;

    public void a(String str) {
        this.b = str;
        this.a.put("fine_location_permission", str);
    }

    public void b(String str) {
        this.f7417c = str;
        this.a.put("location_permission", str);
    }

    public void c(String str) {
        this.d = str;
        this.a.put("phone_permission", str);
    }
}
