package com.jd.stat.security.jma.b;

import android.text.TextUtils;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class i {
    static final b a = new b();
    static final f b = new d();

    /* renamed from: c  reason: collision with root package name */
    static final f f7086c = new e();
    static final f d = new g();

    public static void a(JSONObject jSONObject) {
        a.c(jSONObject);
    }

    public static void b(JSONObject jSONObject) {
        d.c(jSONObject);
    }

    public static void c(JSONObject jSONObject) {
        if (h.c()) {
            f7086c.c(jSONObject);
        }
    }

    public static void d(JSONObject jSONObject) {
        if (h.b()) {
            b.c(jSONObject);
        }
    }

    public static void a(List<JSONObject> list) {
        a.a(list);
    }

    public static void b(JSONObject jSONObject, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            d(jSONObject);
        } else if (h.a(str, str2)) {
            d dVar = new d();
            dVar.b(str);
            dVar.a(str2);
            dVar.c(jSONObject);
        }
    }

    public static void a() {
        ((g) d).d();
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            c(jSONObject);
        } else if (h.b(str, str2)) {
            e eVar = new e();
            eVar.b(str);
            eVar.a(str2);
            eVar.c(jSONObject);
        }
    }
}
