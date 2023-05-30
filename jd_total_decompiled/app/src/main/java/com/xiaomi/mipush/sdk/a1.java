package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import com.jingdong.manto.sdk.api.IMantoServerRequester;

/* loaded from: classes11.dex */
public class a1 {
    private static int a = -1;

    public static y a(Context context) {
        return c(context) ? y.HUAWEI : e(context) ? y.OPPO : f(context) ? y.VIVO : y.OTHER;
    }

    private static boolean b() {
        try {
            String str = (String) com.xiaomi.push.k0.g("android.os.SystemProperties", IMantoServerRequester.GET, "ro.build.hw_emui_api_level", "");
            if (!TextUtils.isEmpty(str)) {
                if (Integer.parseInt(str) >= 9) {
                    return true;
                }
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
        }
        return false;
    }

    public static boolean c(Context context) {
        try {
            if (context.getPackageManager().getServiceInfo(new ComponentName("com.huawei.hwid", "com.huawei.hms.core.service.HMSCoreService"), 128) != null) {
                if (b()) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static boolean d(Context context) {
        Object e2 = com.xiaomi.push.k0.e(com.xiaomi.push.k0.g("com.google.android.gms.common.GoogleApiAvailability", "getInstance", new Object[0]), "isGooglePlayServicesAvailable", context);
        Object f2 = com.xiaomi.push.k0.f("com.google.android.gms.common.ConnectionResult", "SUCCESS");
        if (f2 == null || !(f2 instanceof Integer)) {
            g.j.a.a.a.c.B("google service is not avaliable");
            a = 0;
            return false;
        }
        int intValue = ((Integer) Integer.class.cast(f2)).intValue();
        if (e2 != null) {
            if (e2 instanceof Integer) {
                a = ((Integer) Integer.class.cast(e2)).intValue() == intValue ? 1 : 0;
            } else {
                a = 0;
                g.j.a.a.a.c.B("google service is not avaliable");
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("is google service can be used");
        sb.append(a > 0);
        g.j.a.a.a.c.B(sb.toString());
        return a > 0;
    }

    public static boolean e(Context context) {
        boolean z = false;
        Object g2 = com.xiaomi.push.k0.g("com.xiaomi.assemble.control.COSPushManager", "isSupportPush", context);
        if (g2 != null && (g2 instanceof Boolean)) {
            z = ((Boolean) Boolean.class.cast(g2)).booleanValue();
        }
        g.j.a.a.a.c.B("color os push  is avaliable ? :" + z);
        return z;
    }

    public static boolean f(Context context) {
        boolean z = false;
        Object g2 = com.xiaomi.push.k0.g("com.xiaomi.assemble.control.FTOSPushManager", "isSupportPush", context);
        if (g2 != null && (g2 instanceof Boolean)) {
            z = ((Boolean) Boolean.class.cast(g2)).booleanValue();
        }
        g.j.a.a.a.c.B("fun touch os push  is avaliable ? :" + z);
        return z;
    }
}
