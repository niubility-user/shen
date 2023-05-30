package com.jd.stat.security.jma.a;

import org.json.JSONObject;

/* loaded from: classes18.dex */
public abstract class b implements g {
    protected JSONObject a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f7073c;

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.f7073c;
    }

    public String a() {
        return this.b;
    }

    public void b(String str) {
        this.f7073c = str;
    }

    @Override // com.jd.stat.security.jma.a.g
    public g a(JSONObject jSONObject) {
        this.a = jSONObject;
        return this;
    }
}
