package com.unionpay.utils;

import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public final class i {
    public static Object a(JSONArray jSONArray, int i2) {
        if (jSONArray != null && i2 < jSONArray.length() && i2 >= 0) {
            try {
                return jSONArray.get(i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static String a(JSONObject jSONObject, String str) {
        if (c(jSONObject, str)) {
            try {
                return jSONObject.getString(str);
            } catch (Exception unused) {
                j.c("uppay", i.class.toString() + " get " + str + " failed!!");
            }
        }
        return "";
    }

    public static JSONArray b(JSONObject jSONObject, String str) {
        if (c(jSONObject, str)) {
            try {
                return jSONObject.getJSONArray(str);
            } catch (Exception unused) {
                j.c("uppay", i.class.toString() + " get " + str + " failed!!");
            }
        }
        return null;
    }

    private static boolean c(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }
}
