package com.jdjr.risk.device.a;

import android.content.Context;
import android.text.TextUtils;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdjr.risk.device.c.z;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class c {
    public static void a(Context context, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            long currentTimeMillis = System.currentTimeMillis();
            String b = BiometricManager.getInstance().a().b(context);
            String a = z.a(context, str2, b);
            if (TextUtils.isEmpty(a)) {
                return;
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            jSONObject.put("secData", a);
            com.jdjr.risk.biometric.c.b.a(jSONObject, context.getPackageName(), str, String.valueOf(currentTimeMillis), String.valueOf(currentTimeMillis2), str2);
            String e2 = BiometricManager.getInstance().a().e(context);
            jSONObject.put("token", b);
            jSONObject.put("cuid", e2);
            com.jdjr.risk.biometric.c.a.a(context, com.jdjr.risk.util.httputil.a.g(), jSONObject, str);
        } catch (Throwable unused) {
        }
    }
}
