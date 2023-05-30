package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.jingdong.common.database.table.PushMessageTable;
import com.jingdong.jdsdk.constant.CartConstant;
import com.xiaomi.push.a8;
import com.xiaomi.push.f4;
import com.xiaomi.push.i;
import com.xiaomi.push.s9;
import java.util.List;
import java.util.Map;

/* loaded from: classes11.dex */
public class h1 {
    private static int a(Map<String, String> map) {
        return Math.max(0, s9.a(map.get("notification_top_period"), 0));
    }

    @TargetApi(19)
    private static Notification b(Notification notification, int i2, String str, y yVar) {
        if (notification != null) {
            if (!str.equals(notification.extras.getString(PushMessageTable.TB_CLOUMN_MESSAGE_ID))) {
                notification = null;
            }
            return notification;
        }
        List<StatusBarNotification> z = yVar.z();
        if (z != null) {
            for (StatusBarNotification statusBarNotification : z) {
                Notification notification2 = statusBarNotification.getNotification();
                String string = notification2.extras.getString(PushMessageTable.TB_CLOUMN_MESSAGE_ID);
                if (i2 == statusBarNotification.getId() && str.equals(string)) {
                    return notification2;
                }
            }
            return null;
        }
        return null;
    }

    private static i.a c(Context context, String str, int i2, String str2, Notification notification) {
        return new i1(i2, str2, context, str, notification);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(19)
    public static void e(Context context, String str, int i2, String str2, Notification notification) {
        if (a8.j(context) && notification != null && notification.extras.getBoolean("mipush_n_top_flag", false)) {
            k(context, str, i2, str2, notification);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(Context context, Map<String, String> map, f4 f4Var, long j2) {
        if (map == null || f4Var == null || !a8.j(context) || !g(map)) {
            return;
        }
        int a = a(map);
        int h2 = h(map);
        if (a <= 0 || h2 > a) {
            g.j.a.a.a.c.D("set top notification failed - period:" + a + " frequency:" + h2);
            return;
        }
        f4Var.setPriority(2);
        Bundle bundle = new Bundle();
        bundle.putLong("mipush_org_when", j2);
        bundle.putBoolean("mipush_n_top_flag", true);
        if (h2 > 0) {
            bundle.putInt("mipush_n_top_fre", h2);
        }
        bundle.putInt("mipush_n_top_prd", a);
        f4Var.d(bundle);
    }

    private static boolean g(Map<String, String> map) {
        String str = map.get("notification_top_repeat");
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        boolean parseBoolean = Boolean.parseBoolean(str);
        g.j.a.a.a.c.B("top notification' repeat is " + parseBoolean);
        return parseBoolean;
    }

    private static int h(Map<String, String> map) {
        return Math.max(0, s9.a(map.get("notification_top_frequency"), 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String i(int i2, String str) {
        return "n_top_update_" + i2 + CartConstant.KEY_YB_INFO_LINK + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(19)
    public static void k(Context context, String str, int i2, String str2, Notification notification) {
        y e2;
        Notification b;
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || Build.VERSION.SDK_INT < 26 || (b = b(notification, i2, str2, (e2 = y.e(context, str)))) == null) {
            return;
        }
        boolean z = notification != null;
        if (b.getGroupAlertBehavior() != 1) {
            com.xiaomi.push.k0.j(b, "mGroupAlertBehavior", 1);
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = b.extras.getLong("mipush_org_when", 0L);
        int i3 = b.extras.getInt("mipush_n_top_fre", 0);
        int i4 = b.extras.getInt("mipush_n_top_prd", 0);
        if (i4 <= 0 || i4 < i3) {
            return;
        }
        long j3 = (i4 * 1000) + j2;
        int min = (j2 >= currentTimeMillis || currentTimeMillis >= j3) ? 0 : i3 > 0 ? (int) Math.min((j3 - currentTimeMillis) / 1000, i3) : i4;
        if (!z) {
            if (min > 0) {
                b.when = currentTimeMillis;
                g.j.a.a.a.c.o("update top notification: " + str2);
                e2.n(i2, b);
            } else {
                Notification.Builder recoverBuilder = Notification.Builder.recoverBuilder(context, b);
                recoverBuilder.setPriority(0);
                recoverBuilder.setWhen(currentTimeMillis);
                Bundle extras = recoverBuilder.getExtras();
                if (extras != null) {
                    extras.remove("mipush_n_top_flag");
                    extras.remove("mipush_org_when");
                    extras.remove("mipush_n_top_fre");
                    extras.remove("mipush_n_top_prd");
                    recoverBuilder.setExtras(extras);
                }
                g.j.a.a.a.c.o("update top notification to common: " + str2);
                e2.n(i2, recoverBuilder.build());
            }
        }
        if (min > 0) {
            g.j.a.a.a.c.o("schedule top notification next update delay: " + min);
            com.xiaomi.push.i.b(context).m(i(i2, str2));
            com.xiaomi.push.i.b(context).n(c(context, str, i2, str2, null), min);
        }
    }
}
