package com.jd.lib.cashier.sdk.core.utils;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes14.dex */
public class g0 {
    public static boolean a(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return !activity.isDestroyed();
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        return (fragmentManager == null || fragmentManager.isDestroyed()) ? false : true;
    }

    private static String b(long j2) {
        long[] g2 = g(j2);
        String str = "";
        if (j2 <= 0) {
            return "";
        }
        if (j2 < 60000) {
            return "1\u5206\u949f";
        }
        if (g2[0] > 0) {
            str = g2[0] + "\u5c0f\u65f6";
        }
        return str + g2[1] + "\u5206\u949f";
    }

    public static String c(String str, long j2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            double parseDouble = Double.parseDouble(str) * 1000.0d;
            double elapsedRealtime = SystemClock.elapsedRealtime() - j2;
            Double.isNaN(elapsedRealtime);
            return b((long) (parseDouble - elapsedRealtime));
        } catch (Exception e2) {
            r.d("CashierUtil", e2.getMessage());
            return "";
        }
    }

    public static String d(String str, String str2, String str3, String str4, String str5) {
        return v.a(str + ";" + str3 + ";" + str4 + ";" + str5 + ";" + str2, "GBK");
    }

    public static boolean e() {
        try {
            return y.s();
        } catch (Exception unused) {
            return false;
        }
    }

    public static <T> void f(List<T> list) {
        if (list != null) {
            try {
                if (list.isEmpty() || !list.contains(null)) {
                    return;
                }
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (it.next() == null) {
                        it.remove();
                    }
                }
            } catch (Exception e2) {
                r.d("CashierUtil", e2.getMessage());
            }
        }
    }

    private static long[] g(long j2) {
        long j3 = j2 / 1000;
        long j4 = (j3 / 60) / 60;
        long j5 = j4 * 60 * 60;
        long j6 = ((j2 - (j5 * 1000)) / 1000) / 60;
        long j7 = (j3 - j5) - (60 * j6);
        if (j4 < 0) {
            j4 = 0;
        }
        if (j6 < 0) {
            j6 = 0;
        }
        if (j7 < 0) {
            j7 = 0;
        }
        return new long[]{j4, j6, j7};
    }
}
