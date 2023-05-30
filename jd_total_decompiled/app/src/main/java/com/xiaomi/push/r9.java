package com.xiaomi.push;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.sdk.api.IMantoServerRequester;

/* loaded from: classes11.dex */
public class r9 {
    private static Context a;
    private static String b;

    public static int a() {
        try {
            Class<?> c2 = c(null, "miui.os.Build");
            if (c2.getField("IS_STABLE_VERSION").getBoolean(null)) {
                return 3;
            }
            return c2.getField("IS_DEVELOPMENT_VERSION").getBoolean(null) ? 2 : 1;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static Context b() {
        return a;
    }

    public static Class<?> c(Context context, String str) {
        if (str == null || str.trim().length() == 0) {
            throw new ClassNotFoundException("class is empty");
        }
        boolean z = context != null;
        if (z && Build.VERSION.SDK_INT >= 29) {
            try {
                return context.getClassLoader().loadClass(str);
            } catch (Throwable unused) {
            }
        }
        try {
            return Class.forName(str);
        } catch (Throwable th) {
            g.j.a.a.a.c.o(String.format("loadClass fail hasContext= %s, errMsg = %s", Boolean.valueOf(z), th.getLocalizedMessage()));
            throw new ClassNotFoundException("loadClass fail ", th);
        }
    }

    public static synchronized String d() {
        synchronized (r9.class) {
            String str = b;
            if (str != null) {
                return str;
            }
            String str2 = Build.VERSION.INCREMENTAL;
            if (a() <= 0) {
                String h2 = h();
                if (TextUtils.isEmpty(h2)) {
                    h2 = j();
                    if (TextUtils.isEmpty(h2)) {
                        h2 = k();
                        if (TextUtils.isEmpty(h2)) {
                            str2 = String.valueOf(q9.a("ro.product.brand", "Android") + CartConstant.KEY_YB_INFO_LINK + str2);
                        }
                    }
                }
                str2 = h2;
            }
            b = str2;
            return str2;
        }
    }

    public static void e(Context context) {
        a = context.getApplicationContext();
    }

    public static boolean f() {
        return TextUtils.equals((String) k0.g("android.os.SystemProperties", IMantoServerRequester.GET, "sys.boot_completed"), "1");
    }

    public static boolean g(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
            return false;
        }
    }

    private static String h() {
        String a2 = q9.a("ro.build.version.emui", "");
        b = a2;
        return a2;
    }

    public static boolean i() {
        try {
            return c(null, "miui.os.Build").getField("IS_GLOBAL_BUILD").getBoolean(Boolean.FALSE);
        } catch (ClassNotFoundException unused) {
            g.j.a.a.a.c.D("miui.os.Build ClassNotFound");
            return false;
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
            return false;
        }
    }

    private static String j() {
        String a2 = q9.a("ro.build.version.opporom", "");
        if (!TextUtils.isEmpty(a2) && !a2.startsWith("ColorOS_")) {
            b = "ColorOS_" + a2;
        }
        return b;
    }

    private static String k() {
        String a2 = q9.a("ro.vivo.os.version", "");
        if (!TextUtils.isEmpty(a2) && !a2.startsWith("FuntouchOS_")) {
            b = "FuntouchOS_" + a2;
        }
        return b;
    }
}
