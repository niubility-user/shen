package com.jdjr.risk.biometric.b;

import org.json.JSONObject;

/* loaded from: classes18.dex */
public class a {
    public static JSONObject a(int i2, int i3, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 42);
            jSONObject.put("operation", i2);
            jSONObject.put("success", i3);
            jSONObject.put("token", str);
            jSONObject.put("regCode", str2);
        } catch (Exception unused) {
        }
        return jSONObject;
    }
}
