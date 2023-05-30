package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.tencent.mapsdk.internal.j4;
import com.xiaomi.push.p9;
import com.xiaomi.push.s9;
import com.xiaomi.push.y7;
import java.util.HashMap;

/* loaded from: classes11.dex */
public class v0 {
    public static int a() {
        Integer num = (Integer) com.xiaomi.push.k0.f("com.xiaomi.assemble.control.AssembleConstants", "ASSEMBLE_VERSION_CODE");
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    static String b(Context context, r0 r0Var) {
        return c(context, r0Var, false);
    }

    protected static synchronized String c(Context context, r0 r0Var, boolean z) {
        synchronized (v0.class) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
            if (z) {
                String string = sharedPreferences.getString("syncingToken", "");
                if (!TextUtils.isEmpty(string)) {
                    return string;
                }
            }
            String d = d(r0Var);
            if (TextUtils.isEmpty(d)) {
                return "";
            }
            return sharedPreferences.getString(d, "");
        }
    }

    public static String d(r0 r0Var) {
        int i2 = x0.a[r0Var.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        return null;
                    }
                    return "ftos_push_token";
                }
                return "cos_push_token";
            }
            return "fcm_push_token_v2";
        }
        return "hms_push_token";
    }

    /* JADX WARN: Code restructure failed: missing block: B:76:0x0048, code lost:
        if (r11 != 0) goto L77;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static HashMap<String, String> e(Context context, r0 r0Var) {
        s9.a aVar;
        int a;
        HashMap<String, String> hashMap = new HashMap<>();
        int i2 = x0.a[r0Var.ordinal()];
        String str = null;
        ApplicationInfo applicationInfo = null;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    aVar = new s9.a(":", "~");
                    aVar.a(JDNetCacheManager.BRAND_BIZKEY, y.OPPO.name());
                    aVar.a("token", c(context, r0Var, true));
                    aVar.a("package_name", context.getPackageName());
                } else if (i2 == 4) {
                    aVar = new s9.a(":", "~");
                    aVar.a(JDNetCacheManager.BRAND_BIZKEY, y.VIVO.name());
                    aVar.a("token", c(context, r0Var, true));
                    aVar.a("package_name", context.getPackageName());
                    a = a();
                }
                str = aVar.toString();
            } else {
                aVar = new s9.a(":", "~");
                aVar.a(JDNetCacheManager.BRAND_BIZKEY, y.FCM.name());
                aVar.a("token", c(context, r0Var, false));
                aVar.a("package_name", context.getPackageName());
                a = a();
                if (a == 0) {
                    a = 50300;
                }
            }
            aVar.a("version", Integer.valueOf(a));
            str = aVar.toString();
        } else {
            try {
                applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            } catch (Exception e2) {
                g.j.a.a.a.c.D(e2.toString());
            }
            int i3 = applicationInfo != null ? applicationInfo.metaData.getInt("com.huawei.hms.client.appid") : -1;
            s9.a aVar2 = new s9.a(":", "~");
            aVar2.a(JDNetCacheManager.BRAND_BIZKEY, y.HUAWEI.name());
            aVar2.a("token", c(context, r0Var, true));
            aVar2.a("package_name", context.getPackageName());
            aVar2.a("app_id", Integer.valueOf(i3));
            str = aVar2.toString();
        }
        hashMap.put("RegInfo", str);
        return hashMap;
    }

    public static void f(Context context) {
        boolean z = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        String d = d(r0.ASSEMBLE_PUSH_HUAWEI);
        String d2 = d(r0.ASSEMBLE_PUSH_FCM);
        if (!TextUtils.isEmpty(sharedPreferences.getString(d, "")) && TextUtils.isEmpty(sharedPreferences.getString(d2, ""))) {
            z = true;
        }
        if (z) {
            f0.h(context).p(2, d);
        }
    }

    public static boolean g(Context context, r0 r0Var) {
        if (y0.c(r0Var) != null) {
            return com.xiaomi.push.service.b0.d(context).m(y0.c(r0Var).a(), true);
        }
        return false;
    }

    public static boolean h(r0 r0Var) {
        return r0Var == r0.ASSEMBLE_PUSH_FTOS || r0Var == r0.ASSEMBLE_PUSH_FCM;
    }

    public static boolean i(y7 y7Var, r0 r0Var) {
        if (y7Var == null || y7Var.m185a() == null || y7Var.m185a().m121a() == null) {
            return false;
        }
        return (r0Var == r0.ASSEMBLE_PUSH_FCM ? "FCM" : "").equalsIgnoreCase(y7Var.m185a().m121a().get("assemble_push_type"));
    }

    public static byte[] j(Context context, y7 y7Var, r0 r0Var) {
        if (i(y7Var, r0Var)) {
            return com.xiaomi.push.o0.c(b(context, r0Var));
        }
        return null;
    }

    public static String k(r0 r0Var) {
        return d(r0Var) + j4.t;
    }

    public static void l(Context context) {
        s0.d(context).a();
    }

    public static void m(Context context, r0 r0Var, String str) {
        com.xiaomi.push.i.b(context).g(new w0(str, context, r0Var));
    }

    public static void n(Context context) {
        s0.d(context).unregister();
    }

    public static synchronized void p(Context context, r0 r0Var, String str) {
        synchronized (v0.class) {
            String d = d(r0Var);
            if (TextUtils.isEmpty(d)) {
                g.j.a.a.a.c.o("ASSEMBLE_PUSH : can not find the key of token used in sp file");
                return;
            }
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putString(d, str).putString("last_check_token", o0.c(context).q());
            if (h(r0Var)) {
                edit.putInt(k(r0Var), a());
            }
            edit.putString("syncingToken", "");
            p9.a(edit);
            g.j.a.a.a.c.o("ASSEMBLE_PUSH : update sp file success!  " + str);
        }
    }
}
