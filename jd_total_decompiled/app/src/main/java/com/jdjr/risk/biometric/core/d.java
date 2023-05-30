package com.jdjr.risk.biometric.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jdjr.risk.device.c.ac;
import com.jdjr.risk.util.constant.RiskType;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class d {
    private static d b;
    private ConcurrentHashMap<String, String> a = new ConcurrentHashMap<>();

    private d() {
    }

    public static d a() {
        if (b == null) {
            synchronized (d.class) {
                if (b == null) {
                    b = new d();
                }
            }
        }
        return b;
    }

    public JSONObject a(Context context, RiskType riskType) {
        String b2;
        try {
            if (this.a.containsKey(riskType.a())) {
                b2 = this.a.get(riskType.a());
            } else {
                b2 = com.jdjr.risk.util.a.d.b(context, riskType.a(), "");
                this.a.put(riskType.a(), b2);
            }
            if (TextUtils.isEmpty(b2)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(b2);
            String optString = jSONObject.optString("asr", "");
            long optLong = jSONObject.optLong("actTime", 0L);
            long optLong2 = jSONObject.optLong("useTime", 0L);
            String a = ac.a();
            if (!TextUtils.equals(TextUtils.isEmpty(a) ? "" : com.jdjr.risk.a.b.a.e(a), optString) || optLong + optLong2 <= System.currentTimeMillis()) {
                return null;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("policy");
            if (optJSONObject != null) {
                return optJSONObject;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public void a(Context context, RiskType riskType, long j2, JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("actTime", System.currentTimeMillis());
            jSONObject2.put("useTime", j2);
            jSONObject2.put("policy", jSONObject);
            jSONObject2.put("asr", com.jdjr.risk.a.b.a.e(str));
            this.a.put(riskType.a(), jSONObject2.toString());
            SharedPreferences.Editor edit = com.jdjr.risk.util.a.d.a(context).edit();
            edit.putString(riskType.a(), jSONObject2.toString());
            edit.apply();
        } catch (Throwable unused) {
        }
    }
}
