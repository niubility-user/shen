package com.jd.verify.model;

import org.json.JSONArray;

/* loaded from: classes18.dex */
public class b {
    int a;
    int b;

    /* renamed from: c  reason: collision with root package name */
    int f7199c;
    int d;

    /* renamed from: e  reason: collision with root package name */
    public int f7200e;

    public b(int i2, int i3, int i4, int i5, int i6) {
        this.a = i2;
        this.b = i3;
        this.f7199c = i4;
        this.d = i5;
        this.f7200e = i6;
    }

    public int a() {
        return this.f7199c;
    }

    public int b() {
        return this.d;
    }

    public JSONArray c() {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(this.a);
        jSONArray.put(this.b);
        jSONArray.put(this.f7199c);
        jSONArray.put(this.d);
        jSONArray.put(this.f7200e);
        return jSONArray;
    }

    public JSONArray d() {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(this.f7199c);
        jSONArray.put(this.d);
        jSONArray.put(this.f7200e);
        return jSONArray;
    }
}
