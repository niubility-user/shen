package com.huawei.hms.hatool;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class b1 implements o1 {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f1354c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f1355e;

    /* renamed from: f  reason: collision with root package name */
    private String f1356f;

    @Override // com.huawei.hms.hatool.o1
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", this.a);
        jSONObject.put("eventtime", this.d);
        jSONObject.put("event", this.b);
        jSONObject.put("event_session_name", this.f1355e);
        jSONObject.put("first_session_event", this.f1356f);
        if (TextUtils.isEmpty(this.f1354c)) {
            return null;
        }
        jSONObject.put("properties", new JSONObject(this.f1354c));
        return jSONObject;
    }

    public void a(String str) {
        this.f1354c = str;
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.b = jSONObject.optString("event");
        this.f1354c = jSONObject.optString("properties");
        this.f1354c = n.a(this.f1354c, o0.d().a());
        this.a = jSONObject.optString("type");
        this.d = jSONObject.optString("eventtime");
        this.f1355e = jSONObject.optString("event_session_name");
        this.f1356f = jSONObject.optString("first_session_event");
    }

    public String b() {
        return this.d;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.a;
    }

    public void c(String str) {
        this.d = str;
    }

    public JSONObject d() {
        JSONObject a = a();
        a.put("properties", n.b(this.f1354c, o0.d().a()));
        return a;
    }

    public void d(String str) {
        this.a = str;
    }

    public void e(String str) {
        this.f1356f = str;
    }

    public void f(String str) {
        this.f1355e = str;
    }
}
