package com.cmic.sso.sdk.auth;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class c {
    public static JSONObject a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", str);
            jSONObject.put("desc", str2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject b(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", str);
            jSONObject.put("desc", str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject a(String str, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
        String b;
        String[] strArr = {"\u672a\u77e5", "\u79fb\u52a8", "\u8054\u901a", "\u7535\u4fe1"};
        try {
            b = aVar.b("operatortype", "0");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!"0".equals(b) && !TextUtils.isEmpty(b)) {
            jSONObject.put("operatorType", strArr[Integer.parseInt(b)]);
            return jSONObject;
        }
        if ("103000".equals(str)) {
            jSONObject.put("operatorType", strArr[1]);
        } else {
            jSONObject.put("operatorType", strArr[0]);
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", "103000");
            jSONObject.put("desc", DYConstants.DY_TRUE);
            jSONObject.put("securityphone", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject a(String str, String str2, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
        String str3;
        String str4;
        String str5;
        String str6 = "0";
        JSONObject jSONObject2 = new JSONObject();
        try {
            int parseInt = Integer.parseInt(aVar.b("authType", "0"));
            int c2 = aVar.c("networktype");
            if (parseInt == 3) {
                if (c2 == 3) {
                    str4 = "WIFI\u4e0b\u7f51\u5173\u9274\u6743";
                    str5 = "1";
                } else {
                    str4 = "\u7f51\u5173\u9274\u6743";
                    str5 = "2";
                }
                String str7 = str5;
                str3 = str4;
                str6 = str7;
            } else {
                str3 = "\u5176\u4ed6";
            }
            jSONObject2.put("resultCode", str);
            jSONObject2.put("authType", str6);
            jSONObject2.put("authTypeDes", str3);
            if ("103000".equals(str)) {
                if (1 == aVar.c("logintype")) {
                    jSONObject2.put("openId", aVar.b("openId"));
                    jSONObject2.put("securityphone", aVar.b("securityphone"));
                }
                jSONObject2.put("token", jSONObject.optString("token"));
                jSONObject2.put("tokenExpiresIn", jSONObject.optString("tokenExpiresIn"));
            } else {
                jSONObject2.put("desc", str2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        com.cmic.sso.sdk.e.c.b("AuthnResult", "\u8fd4\u56de\u53c2\u6570:" + jSONObject2.toString());
        return jSONObject2;
    }
}
