package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.jingdong.common.jdmiaosha.utils.cache.Final;

/* loaded from: classes11.dex */
public class f {
    private static boolean a;

    public static boolean a() {
        return a;
    }

    public static void b(Context context) {
        a c2 = s0.d(context).c(r0.ASSEMBLE_PUSH_HUAWEI);
        if (c2 != null) {
            c2.a();
        }
    }

    public static synchronized void c(Context context) {
        synchronized (f.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_connect_time", System.currentTimeMillis()).commit();
        }
    }

    public static synchronized boolean d(Context context) {
        boolean z;
        synchronized (f.class) {
            z = Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_connect_time", -1L)) > Final.FIVE_SECOND;
        }
        return z;
    }
}
