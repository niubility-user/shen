package com.jd.stat.security.jma.a;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class e {
    static final g a = new c();
    static final g b = new d();

    /* renamed from: c */
    static final g f7074c = new i();
    static final g d = new a();

    /* renamed from: e */
    static final g f7075e = new h();

    public static JSONObject a() {
        return a.a(com.jd.stat.security.c.a);
    }

    public static JSONObject b() {
        return b.a(com.jd.stat.security.c.a);
    }

    public static JSONObject c() {
        return f7074c.a(com.jd.stat.security.c.a);
    }

    public static JSONObject d() {
        return d.a(com.jd.stat.security.c.a);
    }

    public static h e() {
        return (h) f7075e;
    }

    public static JSONObject a(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return a();
        }
        c cVar = new c();
        cVar.a(str);
        cVar.b(str2);
        return cVar.a(com.jd.stat.security.c.a);
    }

    public static JSONObject b(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return b();
        }
        d dVar = new d();
        dVar.a(str);
        dVar.b(str2);
        return dVar.a(com.jd.stat.security.c.a);
    }

    public static JSONObject a(JSONObject jSONObject) {
        return f7075e.a(jSONObject).a(com.jd.stat.security.c.a);
    }
}
