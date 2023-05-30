package com.cmic.sso.sdk.c.b;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class f extends g {
    private b a;
    private a b;

    /* loaded from: classes.dex */
    public static class a {
        private JSONObject a;

        public JSONObject a() {
            return this.a;
        }

        public void a(JSONObject jSONObject) {
            this.a = jSONObject;
        }
    }

    /* loaded from: classes.dex */
    public static class b extends g {
        private String a;
        private String b;

        /* renamed from: c  reason: collision with root package name */
        private String f1009c;
        private String d;

        /* renamed from: e  reason: collision with root package name */
        private String f1010e;

        @Override // com.cmic.sso.sdk.c.b.g
        protected String a_(String str) {
            return this.f1010e + this.d + this.f1009c + this.b + "@Fdiwmxy7CBDDQNUI";
        }

        @Override // com.cmic.sso.sdk.c.b.g
        public JSONObject b() {
            return null;
        }

        public void b(String str) {
            this.f1010e = str;
        }

        public String c() {
            return this.f1010e;
        }

        public String d() {
            return this.a;
        }

        public String e() {
            return this.b;
        }

        public String f() {
            return this.f1009c;
        }

        @Override // com.cmic.sso.sdk.c.b.g
        public String a() {
            return this.d;
        }

        public void c(String str) {
            this.d = str;
        }

        public void d(String str) {
            this.a = str;
        }

        public void e(String str) {
            this.b = str;
        }

        public void f(String str) {
            this.f1009c = str;
        }
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    protected String a_(String str) {
        return null;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject2.put("sign", this.a.d());
            jSONObject2.put("msgid", this.a.e());
            jSONObject2.put("systemtime", this.a.f());
            jSONObject2.put("appid", this.a.a());
            jSONObject2.put("version", this.a.c());
            jSONObject.put("header", jSONObject2);
            jSONObject3.put("log", this.b.a());
            jSONObject.put("body", jSONObject3);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    public String a() {
        return this.a.d;
    }
}
