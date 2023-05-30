package com.jingdong.app.mall.p;

import org.json.JSONObject;

/* loaded from: classes4.dex */
public class b {
    public int a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f11460c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f11461e;

    public b(JSONObject jSONObject) {
        f(jSONObject.optInt("sort"));
        h(jSONObject.optString("title"));
        g(jSONObject.optString("subTitle"));
        j(jSONObject.optString("url"));
        i(jSONObject.optString("type"));
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.f11460c;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.f11461e;
    }

    public String e() {
        return this.d;
    }

    public void f(int i2) {
        this.a = i2;
    }

    public void g(String str) {
        this.f11460c = str;
    }

    public void h(String str) {
        this.b = str;
    }

    public void i(String str) {
        this.f11461e = str;
    }

    public void j(String str) {
        this.d = str;
    }
}
