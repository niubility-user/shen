package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.tencent.mapsdk.internal.j4;
import com.xiaomi.push.p9;
import com.xiaomi.push.y7;

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

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0048, code lost:
        if (r11 != 0) goto L45;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.HashMap<java.lang.String, java.lang.String> e(android.content.Context r11, com.xiaomi.mipush.sdk.r0 r12) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            int[] r1 = com.xiaomi.mipush.sdk.x0.a
            int r2 = r12.ordinal()
            r1 = r1[r2]
            r2 = 0
            java.lang.String r3 = "package_name"
            java.lang.String r4 = "token"
            java.lang.String r5 = "brand"
            java.lang.String r6 = "~"
            java.lang.String r7 = ":"
            r8 = 1
            if (r1 == r8) goto L9b
            r9 = 2
            java.lang.String r10 = "version"
            if (r1 == r9) goto L73
            r9 = 3
            if (r1 == r9) goto L52
            r9 = 4
            if (r1 == r9) goto L28
            goto Le6
        L28:
            com.xiaomi.push.s9$a r1 = new com.xiaomi.push.s9$a
            r1.<init>(r7, r6)
            com.xiaomi.mipush.sdk.y r2 = com.xiaomi.mipush.sdk.y.VIVO
            java.lang.String r2 = r2.name()
            r1.a(r5, r2)
            java.lang.String r12 = c(r11, r12, r8)
            r1.a(r4, r12)
            java.lang.String r11 = r11.getPackageName()
            r1.a(r3, r11)
            int r11 = a()
            if (r11 == 0) goto L6e
        L4a:
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r1.a(r10, r11)
            goto L6e
        L52:
            com.xiaomi.push.s9$a r1 = new com.xiaomi.push.s9$a
            r1.<init>(r7, r6)
            com.xiaomi.mipush.sdk.y r2 = com.xiaomi.mipush.sdk.y.OPPO
            java.lang.String r2 = r2.name()
            r1.a(r5, r2)
            java.lang.String r12 = c(r11, r12, r8)
            r1.a(r4, r12)
            java.lang.String r11 = r11.getPackageName()
            r1.a(r3, r11)
        L6e:
            java.lang.String r2 = r1.toString()
            goto Le6
        L73:
            com.xiaomi.push.s9$a r1 = new com.xiaomi.push.s9$a
            r1.<init>(r7, r6)
            com.xiaomi.mipush.sdk.y r2 = com.xiaomi.mipush.sdk.y.FCM
            java.lang.String r2 = r2.name()
            r1.a(r5, r2)
            r2 = 0
            java.lang.String r12 = c(r11, r12, r2)
            r1.a(r4, r12)
            java.lang.String r11 = r11.getPackageName()
            r1.a(r3, r11)
            int r11 = a()
            if (r11 == 0) goto L97
            goto L4a
        L97:
            r11 = 50300(0xc47c, float:7.0485E-41)
            goto L4a
        L9b:
            android.content.pm.PackageManager r1 = r11.getPackageManager()     // Catch: java.lang.Exception -> Laa
            java.lang.String r9 = r11.getPackageName()     // Catch: java.lang.Exception -> Laa
            r10 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r2 = r1.getApplicationInfo(r9, r10)     // Catch: java.lang.Exception -> Laa
            goto Lb2
        Laa:
            r1 = move-exception
            java.lang.String r1 = r1.toString()
            g.j.a.a.a.c.D(r1)
        Lb2:
            r1 = -1
            if (r2 == 0) goto Lbd
            android.os.Bundle r1 = r2.metaData
            java.lang.String r2 = "com.huawei.hms.client.appid"
            int r1 = r1.getInt(r2)
        Lbd:
            com.xiaomi.push.s9$a r2 = new com.xiaomi.push.s9$a
            r2.<init>(r7, r6)
            com.xiaomi.mipush.sdk.y r6 = com.xiaomi.mipush.sdk.y.HUAWEI
            java.lang.String r6 = r6.name()
            r2.a(r5, r6)
            java.lang.String r12 = c(r11, r12, r8)
            r2.a(r4, r12)
            java.lang.String r11 = r11.getPackageName()
            r2.a(r3, r11)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r1)
            java.lang.String r12 = "app_id"
            r2.a(r12, r11)
            java.lang.String r2 = r2.toString()
        Le6:
            java.lang.String r11 = "RegInfo"
            r0.put(r11, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.v0.e(android.content.Context, com.xiaomi.mipush.sdk.r0):java.util.HashMap");
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
