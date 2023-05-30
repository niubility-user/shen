package com.jdjr.risk.device.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdjr.risk.util.b.e;
import com.jdjr.risk.util.httputil.HttpInfoConstants;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class a {
    private static long a;
    private static String b;

    /* renamed from: c  reason: collision with root package name */
    private static volatile AtomicBoolean f7319c = new AtomicBoolean(false);

    private static String a(Context context, JSONObject jSONObject) {
        List<PackageInfo> a2;
        try {
            if (com.jdjr.risk.device.c.d.b(context) || (a2 = com.jdjr.risk.device.c.d.a(context)) == null || a2.size() <= 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            Iterator<PackageInfo> it = a2.iterator();
            while (it.hasNext()) {
                String optString = jSONObject.optString(it.next().packageName);
                if (!TextUtils.isEmpty(optString)) {
                    sb.append(optString);
                    sb.append(DYConstants.DY_REGEX_COMMA);
                }
            }
            String sb2 = sb.toString();
            return sb2.endsWith(DYConstants.DY_REGEX_COMMA) ? sb2.substring(0, sb2.length() - 1) : sb2;
        } catch (Throwable unused) {
            return "";
        }
    }

    private static JSONObject a(Context context, String str) {
        try {
            byte[] a2 = com.jdjr.risk.util.a.a.a(context).a(context, str);
            if (a2 != null) {
                JSONObject jSONObject = new JSONObject(new String(a2));
                long optLong = jSONObject.optLong("expireTime");
                if (TextUtils.isEmpty(jSONObject.toString()) || System.currentTimeMillis() >= optLong) {
                    return null;
                }
                return jSONObject.optJSONObject("appMap");
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static JSONObject a(Context context, String str, String str2) {
        byte[] a2;
        JSONObject jSONObject = null;
        try {
            String b2 = e.b(context, str2);
            if (!TextUtils.isEmpty(b2)) {
                JSONObject jSONObject2 = new JSONObject(b2);
                jSONObject = jSONObject2.optJSONObject("appMap");
                if ((jSONObject == null || TextUtils.isEmpty(jSONObject.toString())) && (a2 = com.jdjr.risk.util.a.a.a(context).a(context, str)) != null) {
                    jSONObject = new JSONObject(new String(a2)).optJSONObject("appMap");
                    jSONObject2.put("appMap", jSONObject);
                }
                com.jdjr.risk.util.a.a.a(context).a(context, str, jSONObject2);
                if (jSONObject != null && !TextUtils.isEmpty(jSONObject.toString())) {
                    b(context, jSONObject.toString());
                }
            }
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    private static JSONObject a(Context context, String str, String str2, String str3) {
        try {
            JSONObject a2 = a(context, str);
            if (a2 == null) {
                try {
                    a(context);
                    return a(context, str, str2, str3, b);
                } catch (Throwable unused) {
                    return a2;
                }
            }
            return a2;
        } catch (Throwable unused2) {
            return null;
        }
    }

    private static JSONObject a(Context context, String str, String str2, String str3, String str4) {
        try {
            String packageName = context.getPackageName();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appId", packageName);
            jSONObject.put("bizId", str);
            jSONObject.put("pin", str2);
            jSONObject.put("token", str3);
            jSONObject.put("appMd5", str4);
            String a2 = e.a(context, jSONObject.toString());
            if (TextUtils.isEmpty(a2)) {
                return null;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("data", a2);
            String a3 = com.jdjr.risk.biometric.c.a.a(com.jdjr.risk.util.httputil.a.i(), jSONObject2);
            if (a3.startsWith(HttpInfoConstants.HTTPT_RSULT_PRE)) {
                return null;
            }
            JSONObject jSONObject3 = new JSONObject(a3);
            if ("1".equals(jSONObject3.optString("code", "0"))) {
                String optString = jSONObject3.optString("data");
                if (TextUtils.isEmpty(optString)) {
                    return null;
                }
                return a(context, str, optString);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static void a(Context context) {
        try {
            if (TextUtils.isEmpty(b)) {
                b = com.jdjr.risk.util.a.d.a(context).getString("app", "");
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context, String str, String str2, com.jdjr.risk.biometric.a.a aVar) {
        if (context == null || aVar == null) {
            return;
        }
        try {
            if (aVar.F() && a(context, aVar)) {
                String b2 = BiometricManager.getInstance().a().b(context);
                JSONObject a2 = a(context, str, str2, b2);
                if (a2 != null) {
                    a(context, str, str2, b2, a2);
                }
                f7319c.set(false);
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(Context context, String str, String str2, String str3, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            long currentTimeMillis = System.currentTimeMillis();
            String a2 = a(context, jSONObject);
            long currentTimeMillis2 = System.currentTimeMillis();
            if (!TextUtils.isEmpty(a2)) {
                jSONObject2.put("appNumber", a2);
                com.jdjr.risk.biometric.c.b.a(jSONObject2, context.getPackageName(), str, String.valueOf(currentTimeMillis), String.valueOf(currentTimeMillis2), str2);
                jSONObject2.put("token", str3);
                jSONObject2.put("cuid", BiometricManager.getInstance().a().e(context));
                if (com.jdjr.risk.biometric.c.a.a(com.jdjr.risk.util.httputil.a.g(), com.jdjr.risk.biometric.c.b.a(e.a(context, jSONObject2.toString().getBytes()))).startsWith(HttpInfoConstants.HTTPT_RSULT_PRE)) {
                    return;
                }
            }
            b(context);
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
        if (java.lang.System.currentTimeMillis() > (com.jdjr.risk.device.a.a.a + com.jdjr.risk.util.constant.a.d)) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean a(Context context, com.jdjr.risk.biometric.a.a aVar) {
        try {
            boolean z = true;
            if (f7319c.compareAndSet(false, true)) {
                if (a == 0) {
                    a = com.jdjr.risk.util.a.d.a(context).getLong("bioValid", -1L);
                }
                if (aVar.G() > 0) {
                    if (System.currentTimeMillis() > a + aVar.G()) {
                    }
                    z = false;
                }
                if (!z) {
                    try {
                        f7319c.set(false);
                    } catch (Throwable unused) {
                    }
                }
                return z;
            }
            return false;
        } catch (Throwable unused2) {
            return false;
        }
    }

    private static void b(Context context) {
        try {
            a = System.currentTimeMillis();
            SharedPreferences.Editor edit = com.jdjr.risk.util.a.d.a(context).edit();
            edit.putLong("bioValid", a);
            edit.apply();
        } catch (Exception unused) {
        }
    }

    private static void b(Context context, String str) {
        try {
            b = e.a(str);
            SharedPreferences.Editor edit = com.jdjr.risk.util.a.d.a(context).edit();
            edit.putString("app", b);
            edit.apply();
        } catch (Exception unused) {
        }
    }
}
