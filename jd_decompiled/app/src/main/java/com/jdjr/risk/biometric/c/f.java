package com.jdjr.risk.biometric.c;

import android.content.Context;
import android.text.TextUtils;
import com.jdjr.risk.biometric.core.BiometricManager;
import java.util.LinkedHashMap;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class f extends b {
    private static int a(Context context, String str) {
        try {
            String optString = new JSONObject(str).optString("code");
            if (TextUtils.isEmpty(optString)) {
                return 903;
            }
            return TextUtils.equals("1", optString) ? 900 : 903;
        } catch (Throwable unused) {
            return 903;
        }
    }

    public static int a(Context context, String str, String str2, JSONObject jSONObject) {
        try {
            String str3 = System.currentTimeMillis() + "";
            LinkedHashMap<String, Object> a = new com.jdjr.risk.device.b.g().a(context, jSONObject);
            String str4 = System.currentTimeMillis() + "";
            a.put("isGuest", com.jdjr.risk.biometric.core.e.a());
            JSONObject jSONObject2 = new JSONObject(a);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("deviceInfo", jSONObject2);
            b.a(jSONObject3, context.getPackageName(), str, str3, str4, str2);
            String c2 = BiometricManager.getInstance().a().c(context);
            String e2 = BiometricManager.getInstance().a().e(context);
            jSONObject3.put("token", c2);
            jSONObject3.put("cuid", e2);
            return a(context, b.b(context, com.jdjr.risk.util.httputil.a.d(), jSONObject3, str));
        } catch (Throwable unused) {
            return 903;
        }
    }
}
