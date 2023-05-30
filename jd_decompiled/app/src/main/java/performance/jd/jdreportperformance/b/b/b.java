package performance.jd.jdreportperformance.b.b;

import android.text.TextUtils;

/* loaded from: classes.dex */
public class b {
    private static int a;

    public static void a(int i2) {
        a = i2;
    }

    public static void b(String str, String str2) {
        if (a < 2 || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        String str3 = "PerformanceReporter-" + str;
    }

    public static void a(String str, String str2) {
        if (a == 0 || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        String str3 = "PerformanceReporterC-" + str;
    }
}
