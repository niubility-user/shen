package com.cmic.sso.sdk.c.b;

import android.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class e extends g {
    private a a;
    private byte[] b;

    /* renamed from: c  reason: collision with root package name */
    private String f1006c;
    private byte[] d;

    /* renamed from: e  reason: collision with root package name */
    private String f1007e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f1008f = false;

    public void a(boolean z) {
        this.f1008f = z;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    protected String a_(String str) {
        return null;
    }

    public void b(String str) {
        this.f1007e = str;
    }

    public void c(String str) {
        this.f1006c = str;
    }

    public void a(byte[] bArr) {
        this.b = bArr;
    }

    public void b(byte[] bArr) {
        this.d = bArr;
    }

    public a c() {
        return this.a;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        if (this.f1008f) {
            try {
                jSONObject.put("encrypted", this.f1006c);
                jSONObject.put("encryptedIV", Base64.encodeToString(this.d, 0));
                jSONObject.put("reqdata", com.cmic.sso.sdk.e.a.a(this.b, this.a.toString(), this.d));
                jSONObject.put("securityreinforce", this.f1007e);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    public String a() {
        return this.a.a();
    }
}
