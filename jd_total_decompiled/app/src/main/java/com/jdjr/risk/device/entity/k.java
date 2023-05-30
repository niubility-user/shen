package com.jdjr.risk.device.entity;

import com.jingdong.common.jump.OpenAppConstant;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class k extends a {
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f7418c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f7419e;

    /* renamed from: f  reason: collision with root package name */
    private int f7420f;

    /* renamed from: g  reason: collision with root package name */
    private JSONObject f7421g;

    /* renamed from: h  reason: collision with root package name */
    private JSONObject f7422h;

    /* renamed from: i  reason: collision with root package name */
    private String f7423i;

    /* renamed from: j  reason: collision with root package name */
    private String f7424j;

    /* renamed from: k  reason: collision with root package name */
    private String f7425k;

    /* renamed from: l  reason: collision with root package name */
    private String f7426l;

    /* renamed from: m  reason: collision with root package name */
    private String f7427m;

    public void a(int i2) {
        this.f7420f = i2;
        this.a.put("root", String.valueOf(i2));
    }

    public void a(String str) {
        this.b = str;
        this.a.put("is_root", str);
    }

    public void a(JSONObject jSONObject) {
        this.f7421g = jSONObject;
        this.a.put("hook", String.valueOf(jSONObject));
    }

    public void b(String str) {
        this.f7418c = str;
        this.a.put("hook_name", str);
    }

    public void b(JSONObject jSONObject) {
        this.f7422h = jSONObject;
        this.a.put("emulator", jSONObject.toString());
    }

    public void c(String str) {
        this.d = str;
        this.a.put("is_virtual", str);
    }

    public void d(String str) {
        this.f7419e = str;
        this.a.put("is_virtualApk", str);
    }

    public void e(String str) {
        this.f7426l = str;
        this.a.put(OpenAppConstant.HOST_1, str);
    }

    public void f(String str) {
        this.f7427m = str;
        this.a.put("virtualApk", str);
    }

    public void g(String str) {
        this.f7423i = str;
        this.a.put("dk", str);
    }

    public void h(String str) {
        this.f7424j = str;
        this.a.put("rootAll", str);
    }

    public void i(String str) {
        this.f7425k = str;
        this.a.put("hookAll", str);
    }
}
