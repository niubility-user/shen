package com.jdjr.risk.tracker;

import android.content.Context;
import android.os.AsyncTask;
import com.jdjr.risk.biometric.core.BiometricManager;

/* loaded from: classes18.dex */
public class c {
    private static int a = -1;
    public static String b = "";

    public static void a(Context context, String str, String str2, String str3, String str4) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new b(context, str, str2, str3, str4));
    }

    public static String b(Context context) {
        String cacheTokenByBizId;
        String str = "";
        if (a != 1) {
            try {
                cacheTokenByBizId = BiometricManager.getInstance().getCacheTokenByBizId(context.getApplicationContext(), "trackSDK", "");
            } catch (Throwable unused) {
            }
            try {
                a = 0;
                return (cacheTokenByBizId.startsWith("1") || cacheTokenByBizId.startsWith("2")) ? "" : cacheTokenByBizId.startsWith("3") ? "" : cacheTokenByBizId;
            } catch (Throwable unused2) {
                str = cacheTokenByBizId;
                a = 1;
                return str;
            }
        }
        return "";
    }

    public static void b(String str) {
        String b2 = com.jdjr.risk.tracker.util.c.b(str);
        if (b2 == null || com.jdjr.risk.tracker.util.b.a("https://mllog.jd.com/mlog/batch/unite/v.do", b2).equals("")) {
            return;
        }
        com.jdjr.risk.tracker.util.c.a(str);
    }
}
