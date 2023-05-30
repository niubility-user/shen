package com.huawei.hms.hatool;

import android.content.Context;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public final class g0 {

    /* renamed from: c */
    private static g0 f1365c;
    private Context a;
    private final Object b = new Object();

    private g0() {
    }

    public static g0 a() {
        if (f1365c == null) {
            b();
        }
        return f1365c;
    }

    private JSONObject a(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            } catch (JSONException unused) {
                v.b("hmsSdk", "Exception occured when transferring bundle to json");
            }
        }
        return jSONObject;
    }

    private static synchronized void b() {
        synchronized (g0.class) {
            if (f1365c == null) {
                f1365c = new g0();
            }
        }
    }

    public void a(Context context) {
        synchronized (this.b) {
            if (this.a != null) {
                return;
            }
            this.a = context;
            e.a().a(context);
        }
    }

    public void a(String str, int i2) {
        e.a().a(str, i2);
    }

    public void a(String str, int i2, String str2, LinkedHashMap<String, String> linkedHashMap) {
        e.a().a(str, i2, str2, a(linkedHashMap));
    }

    public void a(String str, Context context, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("_constants", str3);
            e.a().a(str, 0, str2, jSONObject);
        } catch (JSONException unused) {
            v.f("hmsSdk", "onEvent():JSON structure Exception!");
        }
    }

    public void b(String str, int i2, String str2, LinkedHashMap<String, String> linkedHashMap) {
        e.a().a(str, i2, str2, a(linkedHashMap), System.currentTimeMillis());
    }
}
