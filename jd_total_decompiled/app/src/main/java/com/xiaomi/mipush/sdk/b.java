package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.os.SystemClock;

/* loaded from: classes11.dex */
public class b {
    private static volatile boolean a;
    private static long b;

    public static void a(Context context) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (b()) {
            long j2 = b;
            if (j2 <= 0 || j2 + 300000 <= elapsedRealtime) {
                b = elapsedRealtime;
                c(context);
            }
        }
    }

    public static boolean b() {
        return a;
    }

    public static void c(Context context) {
        a c2 = s0.d(context).c(r0.ASSEMBLE_PUSH_COS);
        if (c2 != null) {
            g.j.a.a.a.c.o("ASSEMBLE_PUSH :  register cos when network change!");
            c2.a();
        }
    }
}
