package com.wjlogin.onekey.sdk.util;

import jpbury.t;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class a {
    public static JSONObject a(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", str);
            jSONObject.put("msg", str2);
            jSONObject.put("operateType", str3);
            jSONObject.put(t.f20145j, str4);
        } catch (Exception e2) {
            if (LogUtil.enableLog) {
                e2.printStackTrace();
            }
        }
        return jSONObject;
    }

    public static JSONObject b(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", str);
            jSONObject.put("accessCode", str2);
            jSONObject.put("securityPhone", str3);
            jSONObject.put("operateType", str4);
        } catch (Exception e2) {
            if (LogUtil.enableLog) {
                e2.printStackTrace();
            }
        }
        return jSONObject;
    }

    public static JSONObject a(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", str);
            jSONObject.put("msg", str2);
            jSONObject.put("operateType", str3);
        } catch (Exception e2) {
            if (LogUtil.enableLog) {
                e2.printStackTrace();
            }
        }
        return jSONObject;
    }
}
