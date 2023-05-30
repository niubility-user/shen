package com.jd.stat.common;

import android.os.Build;
import com.jd.dynamic.DYConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class r {
    public static Object a() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                jSONObject.put("32abis", a(Build.SUPPORTED_32_BIT_ABIS));
                jSONObject.put("64abis", a(Build.SUPPORTED_64_BIT_ABIS));
                jSONObject.put("abis", a(Build.SUPPORTED_ABIS));
            }
            return jSONObject;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static JSONArray a(String[] strArr) {
        JSONArray jSONArray = new JSONArray();
        for (String str : strArr) {
            jSONArray.put(str);
        }
        return jSONArray;
    }

    public static JSONArray a(String str) {
        return a(str.split(DYConstants.DY_REGEX_COMMA));
    }
}
