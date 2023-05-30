package com.jdjr.risk.device.entity;

import com.jd.libs.hybrid.HybridSDK;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes18.dex */
public class c extends a {
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f7360c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f7361e;

    /* renamed from: f  reason: collision with root package name */
    private String f7362f;

    /* renamed from: g  reason: collision with root package name */
    private String f7363g;

    /* renamed from: h  reason: collision with root package name */
    private String f7364h;

    /* renamed from: i  reason: collision with root package name */
    private String f7365i;

    /* renamed from: j  reason: collision with root package name */
    private String f7366j;

    /* renamed from: k  reason: collision with root package name */
    private String f7367k;

    /* renamed from: l  reason: collision with root package name */
    private String f7368l;

    /* renamed from: m  reason: collision with root package name */
    private String f7369m;

    /* renamed from: n  reason: collision with root package name */
    private String f7370n;
    private String o;
    private int p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private int w;

    public void a(int i2) {
        this.w = i2;
        this.a.put("brightness_c", Integer.valueOf(i2));
    }

    public void a(String str) {
        this.b = str;
        this.a.put("os", str);
    }

    public void b(int i2) {
        this.p = i2;
        this.a.put("brightnessMode", String.valueOf(i2));
    }

    public void b(String str) {
        this.f7360c = str;
        this.a.put(HybridSDK.OS_VERSION, str);
    }

    public void c(String str) {
        this.d = str;
        this.a.put("p_model", str);
    }

    public void d(String str) {
        this.f7361e = str;
        this.a.put("p_brand", str);
    }

    public void e(String str) {
        this.f7362f = str;
        this.a.put("p_device", str);
    }

    public void f(String str) {
        this.f7363g = str;
        this.a.put("p_manuf", str);
    }

    public void g(String str) {
        this.f7364h = str;
        this.a.put("time_zone", str);
    }

    public void h(String str) {
        this.f7365i = str;
        this.a.put("country", str);
    }

    public void i(String str) {
        this.f7366j = str;
        this.a.put(IjkMediaMeta.IJKM_KEY_LANGUAGE, str);
    }

    public void j(String str) {
        this.f7367k = str;
        this.a.put("durationTime", str);
    }

    public void k(String str) {
        this.f7368l = str;
        this.a.put("p_name", str);
    }

    public void l(String str) {
        this.f7369m = str;
        this.a.put("brightness", str);
    }

    public void m(String str) {
        this.f7370n = str;
        this.a.put("currency", str);
    }

    public void n(String str) {
        this.q = str;
        this.a.put("p_name_c", str);
    }

    public void o(String str) {
        this.r = str;
        this.a.put("sdk_version_c", str);
    }

    public void p(String str) {
        this.s = str;
        this.a.put("language_c", str);
    }

    public void q(String str) {
        this.t = str;
        this.a.put("fontScale", str);
    }

    public void r(String str) {
        this.u = str;
        this.a.put("fontListMd5", str);
    }

    public void s(String str) {
        this.v = str;
        this.a.put("wallpaper", str);
    }

    public void t(String str) {
        this.o = str;
        this.a.put("bootloader", str);
    }
}
