package com.jdjr.risk.biometric.c;

import android.content.Context;
import android.os.Build;
import com.jd.libs.hybrid.HybridSDK;
import com.jdjr.risk.assist.info.BuildConfig;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.connect.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class b {
    public static JSONObject a(Context context) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = null;
        try {
            jSONObject = new JSONObject();
        } catch (Throwable unused) {
        }
        try {
            jSONObject.put("appVersion", BaseInfo.getAppVersionName());
            jSONObject.put("sdk_version", BuildConfig.BIOVersionName);
            jSONObject.put(Constants.PARAM_PLATFORM, "android");
            jSONObject.put("os", "android");
            jSONObject.put(HybridSDK.OS_VERSION, Build.VERSION.RELEASE);
            jSONObject.put("p_model", com.jdjr.risk.device.c.g.b());
            jSONObject.put("p_brand", com.jdjr.risk.device.c.g.h());
            return jSONObject;
        } catch (Throwable unused2) {
            jSONObject2 = jSONObject;
            return jSONObject2;
        }
    }

    public static JSONObject a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", str);
            jSONObject.put("visaType", "1");
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public static void a(JSONObject jSONObject, String str, String str2, String str3, String str4, String str5) {
        if (str != null) {
            try {
                if (!str.equals("")) {
                    jSONObject.put("appId", str);
                }
            } catch (JSONException unused) {
                return;
            }
        }
        if (str2 != null && !str2.equals("")) {
            jSONObject.put("bizId", str2);
        }
        if (str3 != null && !str3.equals("")) {
            jSONObject.put("startTime", str3);
        }
        if (str4 != null && !str4.equals("")) {
            jSONObject.put("endTime", str4);
        }
        if (str5 == null || str5.equals("")) {
            return;
        }
        jSONObject.put("pin", str5);
    }

    public static String b(Context context, String str, JSONObject jSONObject, String str2) {
        return com.jdjr.risk.util.httputil.c.a(str, a(com.jdjr.risk.util.b.e.a(context, jSONObject.toString().getBytes())).toString(), 3000, 5000);
    }

    public static String b(String str, JSONObject jSONObject) {
        return com.jdjr.risk.util.httputil.c.a(str, jSONObject.toString(), 3000, 5000);
    }
}
