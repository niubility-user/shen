package com.jdjr.risk.biometric.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.jdjr.risk.assist.info.BuildConfig;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdjr.risk.util.httputil.HttpInfoConstants;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.common.web.managers.PerformanceManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class c extends b {
    public static com.jdjr.risk.biometric.a.a a(Context context, String str, String str2) {
        try {
            String str3 = System.currentTimeMillis() + "";
            String b = BiometricManager.getInstance().a().b(context);
            String e2 = BiometricManager.getInstance().a().e(context);
            JSONObject a = b.a(context);
            String packageName = context.getPackageName();
            String str4 = System.currentTimeMillis() + "";
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("deviceInfo", a);
            b.a(jSONObject, packageName, str, str3, str4, str2);
            jSONObject.put("token", b);
            jSONObject.put("cuid", e2);
            String b2 = b.b(context, com.jdjr.risk.util.httputil.a.e(), jSONObject, str);
            if (TextUtils.isEmpty(b2) || b2.startsWith(HttpInfoConstants.HTTPT_RSULT_PRE)) {
                return null;
            }
            JSONObject jSONObject2 = new JSONObject(b2);
            if (TextUtils.equals(jSONObject2.optString("code"), "1")) {
                return a(context, jSONObject2.optJSONObject("data"), str);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static com.jdjr.risk.biometric.a.a a(Context context, JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                int optInt = jSONObject.optInt(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL);
                int optInt2 = jSONObject.optInt("sBasic");
                JSONObject optJSONObject = jSONObject.optJSONObject("basic");
                int optInt3 = jSONObject.optInt("sIdt");
                JSONObject optJSONObject2 = jSONObject.optJSONObject("idt");
                int optInt4 = jSONObject.optInt("sNet");
                JSONObject optJSONObject3 = jSONObject.optJSONObject("net");
                int optInt5 = jSONObject.optInt("sDvst");
                JSONObject optJSONObject4 = jSONObject.optJSONObject("dvst");
                int optInt6 = jSONObject.optInt("sSec");
                JSONObject optJSONObject5 = jSONObject.optJSONObject("sec");
                int optInt7 = jSONObject.optInt("sHdwr");
                JSONObject optJSONObject6 = jSONObject.optJSONObject("hdwr");
                int optInt8 = jSONObject.optInt("sCpu");
                JSONObject optJSONObject7 = jSONObject.optJSONObject(PerformanceManager.CUP);
                int optInt9 = jSONObject.optInt("sApp");
                JSONObject optJSONObject8 = jSONObject.optJSONObject("app");
                int optInt10 = jSONObject.optInt("sSdk");
                JSONObject optJSONObject9 = jSONObject.optJSONObject("sdk");
                int optInt11 = jSONObject.optInt("sSys");
                JSONObject optJSONObject10 = jSONObject.optJSONObject(NotificationCompat.CATEGORY_SYSTEM);
                int optInt12 = jSONObject.optInt("sAlst");
                JSONObject optJSONObject11 = jSONObject.optJSONObject("alst");
                int optInt13 = jSONObject.optInt("sPerm");
                JSONObject optJSONObject12 = jSONObject.optJSONObject("perm");
                int optInt14 = jSONObject.optInt("sOther");
                JSONObject optJSONObject13 = jSONObject.optJSONObject("other");
                int optInt15 = jSONObject.optInt("sensor");
                JSONObject optJSONObject14 = jSONObject.optJSONObject("notSwitchInfo");
                String optString = jSONObject.optString("realTime");
                com.jdjr.risk.biometric.a.a aVar = new com.jdjr.risk.biometric.a.a(str, optInt, optInt2, optJSONObject, optInt3, optJSONObject2, optInt4, optJSONObject3, optInt5, optJSONObject4, optInt6, optJSONObject5, optInt7, optJSONObject6, optInt8, optJSONObject7, optInt9, optJSONObject8, optInt10, optJSONObject9, optInt11, optJSONObject10, optInt12, optJSONObject11, optInt13, optJSONObject12, optInt14, optJSONObject13, optInt15, optJSONObject14, optString);
                try {
                    String str2 = "0";
                    String str3 = aVar.A() ? "0" : "1";
                    if (!aVar.B()) {
                        str2 = "1";
                    }
                    SharedPreferences.Editor edit = com.jdcn.risk.cpp.a.a(context).edit();
                    edit.putString("verSwitch", str3);
                    edit.putString("cutCo", str2);
                    edit.apply();
                    long E = aVar.E();
                    if (E != 0) {
                        com.jdjr.risk.util.a.d.a(context).edit().putLong("isolateTime", E).apply();
                    }
                    b(context, optString);
                } catch (Throwable unused) {
                }
                return aVar;
            } catch (Throwable unused2) {
                return null;
            }
        }
        return null;
    }

    public static List<String> a(Context context, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appId", context.getPackageName());
            jSONObject.put("bizId", str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("sdk_version", BuildConfig.BIOVersionName);
            jSONObject.put("deviceInfo", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("data", com.jdjr.risk.util.b.e.a(context, jSONObject.toString().getBytes()));
            jSONObject3.put("visaType", "1");
            String b = b.b(com.jdjr.risk.util.httputil.a.h(), jSONObject3);
            if (TextUtils.isEmpty(b) || b.startsWith(HttpInfoConstants.HTTPT_RSULT_PRE)) {
                return null;
            }
            JSONObject jSONObject4 = new JSONObject(b);
            if (TextUtils.equals(jSONObject4.optString("code"), "1")) {
                JSONArray jSONArray = new JSONArray(com.jdjr.risk.util.b.b.a(com.jdjr.risk.util.b.a.a(jSONObject4.optString("data").getBytes("UTF-8")), (int) (jSONObject4.optLong("time") % 255), "UTF-8"));
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    try {
                        arrayList.add(jSONArray.getString(i2));
                    } catch (Throwable unused) {
                    }
                }
                return arrayList;
            }
            return null;
        } catch (Throwable unused2) {
            return null;
        }
    }

    private static void b(Context context, String str) {
        String str2;
        try {
            if (str.length() > 0) {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject(str);
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    JSONArray jSONArray = jSONObject2.getJSONArray(next);
                    if (jSONArray.length() > 0) {
                        JSONObject jSONObject3 = new JSONObject();
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            jSONObject3.put((String) jSONArray.get(i2), 1);
                        }
                        jSONObject.put(next, jSONObject3);
                    }
                }
                str2 = jSONObject.toString();
            } else {
                str2 = "";
            }
            com.jdjr.risk.biometric.core.b.a(context).a(context, str2);
        } catch (JSONException unused) {
        }
    }
}
