package com.jingdong.jdpush_new.j;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.messagecenter.MIPushMsg;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.meizu.cloud.pushsdk.PushManager;
import com.vivo.push.BuildConfig;
import java.util.Date;
import java.util.UUID;
import jd.wjlogin_sdk.util.MD5;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class c {
    private static String a;

    public static void a(Context context, int i2, String str, String str2, short s) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("CLIENTID", str);
                jSONObject.put("DEVTOKEN", str2);
                jSONObject.put("DEVMODLE", i2);
                v(context, jSONObject.toString(), s);
                com.jingdong.jdpush_new.connect.g.c().b(context, com.jingdong.jdpush_new.e.b.a().b(context, s, jSONObject.toString()), i2, d(context));
                return;
            } catch (JSONException e2) {
                g.e("PushCommonUtil", "\u7ed1\u5b9a\u6216\u8005\u89e3\u7ed1\u5931\u8d25", e2);
                return;
            }
        }
        g.d("PushCommonUtil", "clientId\u4e3a\u7a7a ");
    }

    public static String b(Context context) {
        return "1".equals(f(context)) ? "01a6e1b9fde54cdbb7276300c063820a" : "Appid101";
    }

    public static String c() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    public static String d(Context context) {
        return String.valueOf(h(context));
    }

    public static String e(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            return packageInfo != null ? packageInfo.versionName : "";
        } catch (PackageManager.NameNotFoundException unused) {
            g.c(str + " package not found");
            return "";
        } catch (Throwable th) {
            g.g(th);
            return "";
        }
    }

    public static String f(Context context) {
        Object k2 = k(context, "DEBUG");
        return k2 == null ? "0" : k2.toString();
    }

    public static String g() {
        int a2 = l.a();
        if (a2 == 2) {
            return l.c();
        }
        if (a2 == 1) {
            return l.d();
        }
        if (a2 == 3) {
            return BaseInfo.getOSName();
        }
        if (a2 == 6) {
            return l.e("ro.build.version.opporom");
        }
        return a2 == 8 ? l.e("ro.build.version.opporom") : "";
    }

    public static int h(Context context) {
        return "1".equals(f(context)) ? 102 : 101;
    }

    public static String i(Context context) {
        if (TextUtils.isEmpty(a)) {
            com.jingdong.jdpush_new.g.c.a a2 = com.jingdong.jdpush_new.d.c.b().a();
            if (a2 == null) {
                a2 = com.jingdong.jdpush_new.e.a.a(context);
            }
            a = a2.c();
        }
        return a;
    }

    public static String j() {
        try {
            return System.getProperty("os.version");
        } catch (Exception e2) {
            g.f("get kernel version error", e2);
            return "";
        }
    }

    public static Object k(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Bundle bundle = applicationInfo != null ? applicationInfo.metaData : null;
            if (bundle != null) {
                return bundle.get(str);
            }
            return null;
        } catch (Error | Exception e2) {
            g.e("PushCommonUtil", "getApplicationInfo\u5f02\u5e38\u4e86", e2);
            return null;
        }
    }

    public static String l(Context context) {
        return BaseInfo.getAppPackageName();
    }

    public static String m() {
        return "4.1.1";
    }

    public static String n() {
        try {
            int a2 = l.a();
            if (a2 != 1) {
                if (a2 == 2) {
                    Object k2 = k(JdSdk.getInstance().getApplication(), "com.huawei.hms.client.service.name:push");
                    String obj = k2 != null ? k2.toString() : "";
                    int indexOf = obj.indexOf(":");
                    return (indexOf <= 0 || obj.endsWith(":")) ? "error1" : obj.substring(indexOf + 1);
                } else if (a2 != 3) {
                    if (a2 != 6) {
                        if (a2 != 8) {
                            if (a2 != 12) {
                                return "";
                            }
                            Object k3 = k(JdSdk.getInstance().getApplication(), "com.hihonor.push.sdk_version");
                            return k3 != null ? k3.toString() : "error1";
                        }
                        return BuildConfig.VERSION_NAME;
                    }
                    return "3.1.0";
                } else {
                    return PushManager.TAG;
                }
            }
            return "5_3_0-C".replace(CartConstant.KEY_YB_INFO_LINK, OrderISVUtil.MONEY_DECIMAL);
        } catch (Exception e2) {
            g.g(e2);
            return "error";
        }
    }

    public static String o(Context context) {
        return BaseInfo.getAppVersionName();
    }

    public static boolean p() {
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            return "harmony".equals(cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]));
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String q(Context context) {
        String str;
        String androidId = BaseInfo.getAndroidId();
        if (!TextUtils.isEmpty(androidId) && !androidId.matches("0+")) {
            StringBuilder sb = new StringBuilder();
            sb.append("JDa");
            sb.append(MD5.encrypt32(androidId + System.currentTimeMillis()));
            str = sb.toString();
        } else {
            str = "JDr" + UUID.randomUUID().toString().replace("-", "");
        }
        g.d("PushCommonUtil", "make jd channel dt : " + str);
        return str;
    }

    public static boolean r(Context context) {
        com.jingdong.jdpush_new.g.c.a h2 = com.jingdong.jdpush_new.f.a.k(context).h(d(context));
        if (h2 != null) {
            return h2.f() == null || !h2.h().equals(o(context)) || !h2.i().equals(c()) || System.currentTimeMillis() - Long.parseLong(h2.f()) > 432000000;
        }
        g.d("PushCommonUtil", "appInfo == null");
        return true;
    }

    public static void s(Context context, int i2, String str, String str2, short s) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(MIPushMsg.MSG_SEQ, str);
                jSONObject.put(MIPushMsg.ECHO, str2);
                jSONObject.put("DEVMODLE", i2);
                t(context, jSONObject.toString(), s, str);
                com.jingdong.jdpush_new.connect.g.c().b(context, com.jingdong.jdpush_new.e.b.a().b(context, s, jSONObject.toString()), i2, d(context));
                return;
            } catch (JSONException unused) {
                g.d("PushCommonUtil", "recordOpenPush\u5931\u8d25");
                return;
            }
        }
        g.d("PushCommonUtil", "clientId\u4e3a\u7a7a \u6253\u5f00\u56de\u6267\u5931\u8d25");
    }

    private static void t(Context context, String str, short s, String str2) {
        try {
            String d = d(context);
            com.jingdong.jdpush_new.g.c.d dVar = new com.jingdong.jdpush_new.g.c.d();
            dVar.h(d);
            dVar.i(String.valueOf((int) s));
            dVar.k(str);
            dVar.m("1");
            dVar.l(str2);
            com.jingdong.jdpush_new.f.f.m(context).d(dVar);
        } catch (Exception e2) {
            g.g(e2);
        }
    }

    public static void u(Context context, int i2, String str, short s, int i3) {
        int i4 = i2 * 1000;
        com.jingdong.jdpush_new.mta.b.b().l(i4 + 300);
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("DEVTOKEN", str);
                jSONObject.put("DEVMODLE", i2);
                jSONObject.put("OPEN_PUSH", i3);
                if (i2 != 0) {
                    v(context, jSONObject.toString(), s);
                }
                com.jingdong.jdpush_new.g.b b = com.jingdong.jdpush_new.e.b.a().b(context, s, jSONObject.toString());
                com.jingdong.jdpush_new.mta.b.b().l(i4 + 310);
                com.jingdong.jdpush_new.connect.g.c().b(context, b, i2, d(context));
                return;
            } catch (JSONException unused) {
                g.d("PushCommonUtil", "\u6ce8\u518cDT\u5931\u8d25");
                return;
            }
        }
        g.d("PushCommonUtil", com.jd.lib.push.c.d.a(i2) + " deviceToken\u4e3a\u7a7a \u6ce8\u518c\u5931\u8d25");
    }

    private static void v(Context context, String str, short s) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String d = d(context);
            if (TextUtils.isEmpty(d)) {
                return;
            }
            com.jingdong.jdpush_new.g.c.b bVar = new com.jingdong.jdpush_new.g.c.b();
            if (s == 2104) {
                bVar.o(d + jSONObject.optInt("DEVMODLE") + String.valueOf((int) s));
            } else if (s == 2108) {
                bVar.o(d + jSONObject.optInt("DEVMODLE"));
            } else if (s == 2110) {
                bVar.o(d + jSONObject.optInt("DEVMODLE"));
            }
            bVar.m(b(context));
            bVar.t(o(context));
            bVar.u(c());
            bVar.s(String.valueOf(new Date().getTime()));
            bVar.l(d);
            bVar.n(String.valueOf((int) s));
            bVar.q("1");
            bVar.p(str);
            bVar.r(0);
            com.jingdong.jdpush_new.f.d.k(context).n(bVar);
        } catch (Exception e2) {
            g.d("PushCommonUtil", "\u5b58\u50a8\u6570\u636e\u5931\u8d25" + e2.toString());
        }
    }
}
