package com.jd.jdsec.a.k;

import android.content.Context;
import android.os.Debug;
import android.provider.Settings;

/* loaded from: classes13.dex */
public class b {
    private static long a;

    public static boolean a() {
        try {
            return Debug.isDebuggerConnected();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static long c(Context context) {
        try {
            if (a()) {
                a |= 1;
            }
            if (b(context)) {
                a |= 2;
            }
            if (d(context)) {
                a |= 4;
            }
            if ("com.android.shell".equals(com.jd.jdsec.c.h.a.d())) {
                a |= 8;
            }
        } catch (Exception e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.RiskLabel", "\u6539\u673a\u68c0\u6d4b\u6a21\u5757\u5f02\u5e38" + e2.getMessage());
        }
        return a;
    }

    public static boolean d(Context context) {
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0) > 0;
        } catch (Exception unused) {
            return false;
        }
    }
}
