package com.jdjr.risk.device.entity;

import com.unionpay.tsmservice.data.Constant;

/* loaded from: classes18.dex */
public class l extends a {
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f7428c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f7429e;

    /* renamed from: f  reason: collision with root package name */
    private String f7430f;

    /* renamed from: g  reason: collision with root package name */
    private String f7431g;

    /* renamed from: h  reason: collision with root package name */
    private String f7432h;

    /* renamed from: i  reason: collision with root package name */
    private String f7433i;

    /* renamed from: j  reason: collision with root package name */
    private int f7434j;

    /* renamed from: k  reason: collision with root package name */
    private int f7435k;

    /* renamed from: l  reason: collision with root package name */
    private int f7436l;

    /* renamed from: m  reason: collision with root package name */
    private int f7437m;

    /* renamed from: n  reason: collision with root package name */
    private int f7438n;
    private boolean o;
    private int p;

    public void a(int i2) {
        this.f7435k = i2;
        this.a.put("headsetOn", String.valueOf(i2));
    }

    public void a(String str) {
        this.b = str;
        this.a.put("wifiable", str);
    }

    public void a(boolean z) {
        this.o = z;
        this.a.put("multiTouch", Boolean.valueOf(z));
    }

    public void b(int i2) {
        this.f7436l = i2;
        this.a.put("batteryHealth", Integer.valueOf(i2));
    }

    public void b(String str) {
        this.f7428c = str;
        this.a.put("debug", str);
    }

    public void c(int i2) {
        this.f7437m = i2;
        this.a.put("batteryStatus", Integer.valueOf(i2));
    }

    public void c(String str) {
        this.d = str;
        this.a.put("adb_enabled", str);
    }

    public void d(int i2) {
        this.f7438n = i2;
        this.a.put("batteryVoltage", Integer.valueOf(i2));
    }

    public void d(String str) {
        this.f7429e = str;
        this.a.put("is_charging", str);
    }

    public void e(int i2) {
        this.p = i2;
        this.a.put(Constant.KEY_NFC_ENABLE, Integer.valueOf(i2));
    }

    public void e(String str) {
        this.f7430f = str;
        this.a.put("battery_level", str);
    }

    public void f(int i2) {
        this.f7434j = i2;
        this.a.put("batteryLevel", Integer.valueOf(i2));
    }

    public void f(String str) {
        this.f7431g = str;
        this.a.put("headphones_attached", str);
    }

    public void g(String str) {
        this.f7432h = str;
        this.a.put("isSupportTouchID", str);
    }

    public void h(String str) {
        this.f7433i = str;
        this.a.put("NFC", str);
    }
}
