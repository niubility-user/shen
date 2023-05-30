package com.cmic.sso.sdk.c.b;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b extends g {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f1000c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f1001e;

    /* renamed from: f  reason: collision with root package name */
    private String f1002f;

    /* renamed from: g  reason: collision with root package name */
    private String f1003g;

    @Override // com.cmic.sso.sdk.c.b.g
    public String a() {
        return this.f1002f;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    protected String a_(String str) {
        return this.a + this.f1001e + this.f1002f + "iYm0HAnkxQtpvN44";
    }

    @Override // com.cmic.sso.sdk.c.b.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", this.a);
            jSONObject.put("apptype", this.b);
            jSONObject.put("phone_ID", this.f1000c);
            jSONObject.put("certflag", this.d);
            jSONObject.put("sdkversion", this.f1001e);
            jSONObject.put("appid", this.f1002f);
            jSONObject.put("expandparams", "");
            jSONObject.put("sign", this.f1003g);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void c(String str) {
        this.b = str;
    }

    public void d(String str) {
        this.f1000c = str;
    }

    public void e(String str) {
        this.d = str;
    }

    public void f(String str) {
        this.f1001e = str;
    }

    public void g(String str) {
        this.f1002f = str;
    }

    public void h(String str) {
        this.f1003g = str;
    }

    public void b(String str) {
        this.a = str;
    }
}
