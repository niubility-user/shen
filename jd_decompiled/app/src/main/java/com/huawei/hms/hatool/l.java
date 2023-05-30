package com.huawei.hms.hatool;

import org.json.JSONObject;

/* loaded from: classes12.dex */
public class l extends t {
    private String b = "";

    /* renamed from: c  reason: collision with root package name */
    private String f1390c = "";
    private String d = "";

    /* renamed from: e  reason: collision with root package name */
    private String f1391e = "";

    /* renamed from: f  reason: collision with root package name */
    protected String f1392f = "";

    /* renamed from: g  reason: collision with root package name */
    private String f1393g;

    @Override // com.huawei.hms.hatool.o1
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("androidid", this.a);
        jSONObject.put("oaid", this.f1393g);
        jSONObject.put("uuid", this.f1392f);
        jSONObject.put("upid", this.f1391e);
        jSONObject.put("imei", this.b);
        jSONObject.put("sn", this.f1390c);
        jSONObject.put("udid", this.d);
        return jSONObject;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.f1393g = str;
    }

    public void d(String str) {
        this.f1390c = str;
    }

    public void e(String str) {
        this.d = str;
    }

    public void f(String str) {
        this.f1391e = str;
    }

    public void g(String str) {
        this.f1392f = str;
    }
}
