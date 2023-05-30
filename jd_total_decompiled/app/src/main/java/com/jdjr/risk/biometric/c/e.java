package com.jdjr.risk.biometric.c;

import android.content.Context;
import android.text.TextUtils;
import com.jdjr.risk.device.c.ac;
import com.jdjr.risk.util.constant.RiskType;
import com.jdjr.risk.util.httputil.HttpInfoConstants;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class e {
    private static String a(Context context, RiskType riskType, String str, String str2) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        String str3 = HttpInfoConstants.FAIL_ERROR_PARAM_STR;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if ("1".equals(jSONObject.optString("code", "0")) && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
                JSONObject optJSONObject3 = optJSONObject.optJSONObject(riskType.a());
                if ("1".equals(optJSONObject3.optString("code", "0")) && (optJSONObject2 = optJSONObject3.optJSONObject("policy")) != null) {
                    str3 = optJSONObject2.toString();
                    String optString = optJSONObject3.optString("useTime", "");
                    if (!TextUtils.isEmpty(optString)) {
                        com.jdjr.risk.biometric.core.d.a().a(context, riskType, Long.valueOf(optString).longValue(), optJSONObject2, str2);
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return str3;
    }

    public static String a(Context context, String str, RiskType riskType, String str2) {
        try {
            String packageName = context.getPackageName();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appId", packageName);
            jSONObject.put("bizId", str);
            jSONObject.put("pin", str2);
            jSONObject.put("riskType", riskType.a());
            String b = com.jdjr.risk.device.c.g.b();
            String h2 = com.jdjr.risk.device.c.g.h();
            String a = ac.a();
            if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(h2)) {
                jSONObject.put(CustomThemeConstance.NAVI_MODEL, b);
                jSONObject.put(JDNetCacheManager.BRAND_BIZKEY, h2);
                jSONObject.put("romName", a);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("data", com.jdjr.risk.util.b.e.a(context, jSONObject.toString().getBytes()));
            jSONObject2.put("visaType", "1");
            jSONObject2.put("visa", "");
            jSONObject2.put("aks", "1");
            jSONObject2.put("riskType", riskType.a());
            String a2 = com.jdjr.risk.util.httputil.c.a(com.jdjr.risk.util.httputil.a.b(), jSONObject2.toString(), 3000, 5000);
            return !a2.startsWith(HttpInfoConstants.HTTPT_RSULT_PRE) ? a(context, riskType, a2, a) : HttpInfoConstants.FAIL_ERROR_PARAM_STR;
        } catch (Throwable unused) {
            return HttpInfoConstants.FAIL_ERROR_PARAM_STR;
        }
    }
}
