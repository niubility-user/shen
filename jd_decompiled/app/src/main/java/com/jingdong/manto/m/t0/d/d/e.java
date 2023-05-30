package com.jingdong.manto.m.t0.d.d;

import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes15.dex */
public class e {
    public static final e d = new e(0, "");

    /* renamed from: e  reason: collision with root package name */
    public static final e f13664e = new e(-1, "fail:internal error");

    /* renamed from: f  reason: collision with root package name */
    public static final e f13665f = new e(10000, "fail:not init");

    /* renamed from: g  reason: collision with root package name */
    public static final e f13666g = new e(10001, "fail:not available");

    /* renamed from: h  reason: collision with root package name */
    public static final e f13667h = new e(10002, "fail:no device");

    /* renamed from: i  reason: collision with root package name */
    public static final e f13668i = new e(10004, "fail:no service");

    /* renamed from: j  reason: collision with root package name */
    public static final e f13669j = new e(10005, "fail:no characteristic");

    /* renamed from: k  reason: collision with root package name */
    public static final e f13670k = new e(10006, "fail:no connection");

    /* renamed from: l  reason: collision with root package name */
    public static final e f13671l = new e(10007, "fail:property not support");

    /* renamed from: m  reason: collision with root package name */
    public static final e f13672m = new e(10008, "fail:system error");

    /* renamed from: n  reason: collision with root package name */
    public static final e f13673n = new e(10008, "fail:not found service");
    public static final e o = new e(10008, "fail:no descriptor");
    public static final e p = new e(10008, "fail:fail to set descriptor");
    public static final e q = new e(10008, "fail:fail to write descriptor");
    public static final e r = new e(10009, "fail:system not support");
    public static final e s = new e(10010, "fail:already connect");
    public static final e t = new e(10011, "fail:need pin");
    public static final e u = new e(10012, "fail:operate time out");
    public static final e v = new e(10013, "fail:invalid data");
    public static final e w = new e((int) R2.drawable.libpdstyleinfoview_network_ry_bg, "fail:no bluetooth permission");
    public int a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f13674c;

    public e(int i2, String str) {
        this.a = i2;
        this.b = str;
        this.f13674c = null;
    }

    private e(String str, Object obj) {
        this.a = 0;
        this.b = str;
        this.f13674c = obj;
    }

    public static e a(Object obj) {
        return new e("", obj);
    }

    public final String toString() {
        return "Result{errCode=" + this.a + ", errMsg='" + this.b + "', retObj=" + this.f13674c + '}';
    }
}
