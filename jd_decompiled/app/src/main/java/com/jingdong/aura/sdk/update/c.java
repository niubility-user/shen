package com.jingdong.aura.sdk.update;

import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class c {
    public static String a(String str, int i2, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bundleName", str);
            jSONObject.put("bundleVersion", i2);
            jSONObject.put("msg", str2);
            jSONObject.put("from", str3);
        } catch (Throwable unused) {
            com.jingdong.aura.sdk.update.b.c.b("format error");
        }
        return jSONObject.toString();
    }
}
