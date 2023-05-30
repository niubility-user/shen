package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.jingdong.common.database.table.NavigationBarTable;

/* loaded from: classes11.dex */
public class b2 {
    private static int a = 0;
    private static boolean b = true;

    private static int a(Context context) {
        if (a <= 0) {
            a = a8.l(context);
        }
        return a;
    }

    private static int b(boolean z) {
        return z ? 1 : 0;
    }

    private static SharedPreferences c(Context context) {
        return context.getSharedPreferences("sp_power_stats", 0);
    }

    private static z1 d(Context context) {
        SharedPreferences c2 = c(context);
        z1 z1Var = new z1();
        z1Var.c(c2.getInt("off_up_count", 0));
        z1Var.g(c2.getInt("off_down_count", 0));
        z1Var.k(c2.getInt("off_ping_count", 0));
        z1Var.o(c2.getInt("off_pong_count", 0));
        z1Var.d(c2.getLong("off_duration", 0L));
        z1Var.r(c2.getInt("on_up_count", 0));
        z1Var.t(c2.getInt("on_down_count", 0));
        z1Var.v(c2.getInt("on_ping_count", 0));
        z1Var.x(c2.getInt("on_pong_count", 0));
        z1Var.h(c2.getLong("on_duration", 0L));
        z1Var.l(c2.getLong(NavigationBarTable.FIELD_START_TIME, 0L));
        z1Var.p(c2.getLong(NavigationBarTable.FIELD_END_TIME, 0L));
        z1Var.z(c2.getInt("xmsf_vc", 0));
        z1Var.B(c2.getInt("android_vc", 0));
        return z1Var;
    }

    private static void e(Context context, long j2, int i2) {
        y1.c("upload");
        new a2().a(context, d(context));
        j(context, j2, i2);
    }

    private static void f(Context context, long j2, long j3, int i2, int i3) {
        if (j2 > 0) {
            if (i(context) || i2 >= 1073741823 || j3 - j2 >= 86400000) {
                c(context).edit().putLong(NavigationBarTable.FIELD_END_TIME, j3).apply();
                e(context, j3, i3);
            }
        }
    }

    public static void g(Context context, long j2, boolean z) {
        i.b(context).g(new c2(context, j2, z));
    }

    private static void h(Context context, SharedPreferences sharedPreferences, long j2, int i2) {
        y1.c("recordInit");
        sharedPreferences.edit().putLong(NavigationBarTable.FIELD_START_TIME, j2).putInt("current_screen_state", i2).putLong("current_screen_state_start_time", j2).putInt("xmsf_vc", a(context)).putInt("android_vc", Build.VERSION.SDK_INT).apply();
    }

    private static boolean i(Context context) {
        boolean z = false;
        if (b) {
            b = false;
            SharedPreferences c2 = c(context);
            int i2 = c2.getInt("xmsf_vc", 0);
            int i3 = c2.getInt("android_vc", 0);
            if (i2 != 0 && i3 != 0 && (i2 != a(context) || i3 != Build.VERSION.SDK_INT)) {
                z = true;
            }
        }
        y1.c("isVcChanged = " + z);
        return z;
    }

    private static void j(Context context, long j2, int i2) {
        y1.c("reset");
        c(context).edit().clear().putLong(NavigationBarTable.FIELD_START_TIME, j2).putInt("current_screen_state", i2).putLong("current_screen_state_start_time", j2).putInt("xmsf_vc", a(context)).putInt("android_vc", Build.VERSION.SDK_INT).apply();
    }

    public static void k(Context context, long j2, boolean z) {
        i.b(context).g(new d2(context, j2, z));
    }

    public static void l(Context context, long j2, boolean z) {
        i.b(context).g(new e2(context, j2, z));
    }

    public static void m(Context context, long j2, boolean z) {
        i.b(context).g(new f2(context, j2, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void r(Context context, long j2, boolean z) {
        int i2;
        SharedPreferences.Editor putInt;
        synchronized (b2.class) {
            y1.c("recordSendMsg start");
            int b2 = b(z);
            SharedPreferences c2 = c(context);
            long j3 = c2.getLong(NavigationBarTable.FIELD_START_TIME, 0L);
            if (j3 <= 0) {
                h(context, c2, j2, b2);
            }
            if (b2 == 1) {
                i2 = c2.getInt("on_up_count", 0) + 1;
                putInt = c2.edit().putInt("on_up_count", i2);
            } else {
                i2 = c2.getInt("off_up_count", 0) + 1;
                putInt = c2.edit().putInt("off_up_count", i2);
            }
            putInt.apply();
            f(context, j3, j2, i2, b2);
            y1.c("recordSendMsg complete");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void s(Context context, long j2, boolean z) {
        int i2;
        SharedPreferences.Editor putInt;
        synchronized (b2.class) {
            y1.c("recordReceiveMsg start");
            int b2 = b(z);
            SharedPreferences c2 = c(context);
            long j3 = c2.getLong(NavigationBarTable.FIELD_START_TIME, 0L);
            if (j3 <= 0) {
                h(context, c2, j2, b2);
            }
            if (b2 == 1) {
                i2 = c2.getInt("on_down_count", 0) + 1;
                putInt = c2.edit().putInt("on_down_count", i2);
            } else {
                i2 = c2.getInt("off_down_count", 0) + 1;
                putInt = c2.edit().putInt("off_down_count", i2);
            }
            putInt.apply();
            f(context, j3, j2, i2, b2);
            y1.c("recordReceiveMsg complete");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void t(Context context, long j2, boolean z) {
        int i2;
        SharedPreferences.Editor putInt;
        synchronized (b2.class) {
            y1.c("recordPing start");
            int b2 = b(z);
            SharedPreferences c2 = c(context);
            long j3 = c2.getLong(NavigationBarTable.FIELD_START_TIME, 0L);
            if (j3 <= 0) {
                h(context, c2, j2, b2);
            }
            if (b2 == 1) {
                i2 = c2.getInt("on_ping_count", 0) + 1;
                putInt = c2.edit().putInt("on_ping_count", i2);
            } else {
                i2 = c2.getInt("off_ping_count", 0) + 1;
                putInt = c2.edit().putInt("off_ping_count", i2);
            }
            putInt.apply();
            f(context, j3, j2, i2, b2);
            y1.c("recordPing complete");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void u(Context context, long j2, boolean z) {
        int i2;
        SharedPreferences.Editor putInt;
        synchronized (b2.class) {
            y1.c("recordPong start");
            int b2 = b(z);
            SharedPreferences c2 = c(context);
            long j3 = c2.getLong(NavigationBarTable.FIELD_START_TIME, 0L);
            if (j3 <= 0) {
                h(context, c2, j2, b2);
            }
            if (b2 == 1) {
                i2 = c2.getInt("on_pong_count", 0) + 1;
                putInt = c2.edit().putInt("on_pong_count", i2);
            } else {
                i2 = c2.getInt("off_pong_count", 0) + 1;
                putInt = c2.edit().putInt("off_pong_count", i2);
            }
            putInt.apply();
            f(context, j3, j2, i2, b2);
            y1.c("recordPong complete");
        }
    }
}
