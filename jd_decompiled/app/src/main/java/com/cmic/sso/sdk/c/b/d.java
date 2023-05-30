package com.cmic.sso.sdk.c.b;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d extends g {
    private final String a;
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private final String f1004c;
    private String d = "authz";

    /* renamed from: e  reason: collision with root package name */
    private String f1005e;

    public d(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.f1004c = str3;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    public String a() {
        return this.a;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    protected String a_(String str) {
        return null;
    }

    public void b(String str) {
        this.d = str;
    }

    public void c(String str) {
        this.f1005e = str;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ver", this.b);
            jSONObject.put("data", this.f1004c);
            jSONObject.put("userCapaid", this.f1005e);
            jSONObject.put("funcType", this.d);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
