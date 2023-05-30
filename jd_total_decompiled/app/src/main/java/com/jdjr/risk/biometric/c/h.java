package com.jdjr.risk.biometric.c;

import android.content.Context;
import android.text.TextUtils;
import com.jdjr.risk.assist.info.BuildConfig;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdjr.risk.device.entity.o;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class h extends b {
    public static int a(Context context, String str, String str2) {
        try {
            List<String> a = c.a(context, str);
            if (a != null) {
                long currentTimeMillis = System.currentTimeMillis();
                JSONObject a2 = a.size() > 0 ? o.a(context, a) : new JSONObject();
                a2.put("isGuest", com.jdjr.risk.biometric.core.e.a());
                a2.put("sdk_version", BuildConfig.BIOVersionName);
                long currentTimeMillis2 = System.currentTimeMillis();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("deviceInfo", a2);
                b.a(jSONObject, context.getPackageName(), str, String.valueOf(currentTimeMillis), String.valueOf(currentTimeMillis2), str2);
                String a3 = BiometricManager.getInstance().a().a(context);
                String e2 = BiometricManager.getInstance().a().e(context);
                jSONObject.put("token", a3);
                jSONObject.put("cuid", e2);
                return a(context, b.b(context, com.jdjr.risk.util.httputil.a.c(), jSONObject, str), str, str2, a);
            }
            return 903;
        } catch (Throwable unused) {
            return 903;
        }
    }

    private static int a(Context context, String str, String str2, String str3, List<String> list) {
        JSONObject optJSONObject;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            if (TextUtils.isEmpty(optString) || !TextUtils.equals("1", optString) || (optJSONObject = jSONObject.optJSONObject("data")) == null) {
                return 903;
            }
            BiometricManager.getInstance().a().a(context, optJSONObject.optString("token"), optJSONObject.optLong("tokenTime", System.currentTimeMillis()), 1800000L, optJSONObject.optString("ccoToken"), String.valueOf(jSONObject.optLong("time")));
            return 900;
        } catch (Throwable unused) {
            return 903;
        }
    }
}
