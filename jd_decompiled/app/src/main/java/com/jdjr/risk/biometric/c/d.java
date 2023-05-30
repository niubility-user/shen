package com.jdjr.risk.biometric.c;

import android.content.Context;
import android.text.TextUtils;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdjr.risk.util.httputil.HttpInfoConstants;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class d extends b {
    private static String a(Context context, String str, String str2) {
        JSONObject optJSONObject;
        String str3 = HttpInfoConstants.FAIL_NULL_RESULT_STR;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            if (TextUtils.isEmpty(optString) || !TextUtils.equals("1", optString) || (optJSONObject = jSONObject.optJSONObject("data")) == null) {
                return HttpInfoConstants.FAIL_NULL_RESULT_STR;
            }
            String optString2 = optJSONObject.optString("ccoToken");
            str3 = optJSONObject.optString("token");
            BiometricManager.getInstance().a().a(context, str3, optJSONObject.optLong("tokenTime", System.currentTimeMillis()), 1800000L, optString2, String.valueOf(jSONObject.optLong("time")));
            return str3;
        } catch (Exception unused) {
            return str3;
        }
    }

    public static String a(Context context, String str, JSONObject jSONObject, String str2) {
        return a(context, b.b(context, str, jSONObject, str2), str2);
    }
}
