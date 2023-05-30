package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.os.SystemClock;

/* loaded from: classes11.dex */
public class e {
    private static long a;
    private static volatile boolean b;

    private static void a(Context context) {
        a c2 = s0.d(context).c(r0.ASSEMBLE_PUSH_FTOS);
        if (c2 != null) {
            g.j.a.a.a.c.o("ASSEMBLE_PUSH :  register fun touch os when network change!");
            c2.a();
        }
    }

    public static void b(Context context) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (c()) {
            long j2 = a;
            if (j2 <= 0 || j2 + 300000 <= elapsedRealtime) {
                a = elapsedRealtime;
                a(context);
            }
        }
    }

    public static boolean c() {
        return b;
    }
}
