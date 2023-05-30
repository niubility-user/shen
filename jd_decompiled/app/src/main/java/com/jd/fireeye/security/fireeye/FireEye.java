package com.jd.fireeye.security.fireeye;

import org.json.JSONObject;

/* loaded from: classes13.dex */
public class FireEye {
    public static void reportFireEye(JSONObject jSONObject) {
        reportFireEye(jSONObject, null);
    }

    public static void reportFireEyeEvent(JSONObject jSONObject, FireEyeCallback fireEyeCallback) {
        a.b().b(jSONObject, fireEyeCallback);
    }

    public static void reportFireEye(JSONObject jSONObject, FireEyeCallback fireEyeCallback) {
        a.b().a(jSONObject, fireEyeCallback);
    }

    public static void reportFireEyeEvent(JSONObject jSONObject) {
        reportFireEyeEvent(jSONObject, null);
    }
}
