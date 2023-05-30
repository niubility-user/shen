package com.jd.fireeye.a.d;

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

    public static String b(Context context) {
        try {
            if (a()) {
                a |= 1;
            }
            if (a(context)) {
                a |= 2;
            }
            if (c(context)) {
                a |= 4;
            }
        } catch (Exception unused) {
        }
        return String.valueOf(a);
    }

    public static boolean c(Context context) {
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0) > 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean a(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception unused) {
            return false;
        }
    }
}
