package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.xiaomi.push.a8;
import com.xiaomi.push.h7;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(24)
/* loaded from: classes11.dex */
public class v {
    private static v a = new v();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class a {
        List<b> a;
        List<b> b;

        private a(v vVar) {
            this.a = new ArrayList();
            this.b = new ArrayList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class b {
        int a;
        Notification b;

        public b(v vVar, int i2, Notification notification) {
            this.a = i2;
            this.b = notification;
        }

        public String toString() {
            return "id:" + this.a;
        }
    }

    private v() {
    }

    private int a(String str, String str2) {
        return ("GroupSummary" + str + str2).hashCode();
    }

    public static v b() {
        return a;
    }

    private String c(Notification notification) {
        Bundle bundle;
        if (notification == null || (bundle = notification.extras) == null) {
            return null;
        }
        return bundle.getString("push_src_group_name");
    }

    private List<StatusBarNotification> e(y yVar) {
        List<StatusBarNotification> z = yVar != null ? yVar.z() : null;
        if (z == null || z.size() == 0) {
            return null;
        }
        return z;
    }

    private void g(Context context, int i2, Notification notification, boolean z) {
        Notification notification2;
        String str;
        String v = z.v(notification);
        if (TextUtils.isEmpty(v)) {
            str = "group auto not extract pkg from notification:" + i2;
        } else {
            List<StatusBarNotification> e2 = e(y.e(context, v));
            if (e2 != null) {
                String n2 = n(notification);
                HashMap hashMap = new HashMap();
                for (StatusBarNotification statusBarNotification : e2) {
                    if (statusBarNotification.getNotification() != null && statusBarNotification.getId() != i2) {
                        j(hashMap, statusBarNotification);
                    }
                }
                for (Map.Entry<String, a> entry : hashMap.entrySet()) {
                    String key = entry.getKey();
                    if (!TextUtils.isEmpty(key)) {
                        a value = entry.getValue();
                        if (z && key.equals(n2) && !p(notification)) {
                            (l(notification) ? value.b : value.a).add(new b(this, i2, notification));
                        }
                        int size = value.a.size();
                        if (value.b.size() <= 0) {
                            if (z && size >= 2) {
                                notification2 = value.a.get(0).b;
                                i(context, v, key, notification2);
                            }
                        } else if (size <= 0) {
                            h(context, v, key);
                        } else if (b0.d(context).m(h7.NotificationGroupUpdateTimeSwitch.a(), false) && (notification2 = value.b.get(0).b) != null) {
                            notification2.when = System.currentTimeMillis();
                            i(context, v, key, notification2);
                        }
                    }
                }
                return;
            }
            str = "group auto not get notifications";
        }
        g.j.a.a.a.c.o(str);
    }

    private void h(Context context, String str, String str2) {
        g.j.a.a.a.c.y("group cancel summary:" + str2);
        y.e(context, str).m(a(str, str2));
    }

    private void i(Context context, String str, String str2, Notification notification) {
        Notification.Builder defaults;
        try {
            if (TextUtils.isEmpty(str2)) {
                g.j.a.a.a.c.o("group show summary group is null");
                return;
            }
            int b2 = z.b(context, str);
            if (b2 == 0) {
                g.j.a.a.a.c.o("group show summary not get icon from " + str);
                return;
            }
            y e2 = y.e(context, str);
            if (Build.VERSION.SDK_INT >= 26) {
                String y = e2.y(notification.getChannelId(), "groupSummary");
                NotificationChannel b3 = e2.b(y);
                if ("groupSummary".equals(y) && b3 == null) {
                    e2.o(new NotificationChannel(y, "group_summary", 3));
                }
                defaults = new Notification.Builder(context, y);
            } else {
                defaults = new Notification.Builder(context).setPriority(0).setDefaults(-1);
            }
            z.o(defaults, true);
            Notification build = defaults.setContentTitle("GroupSummary").setContentText("GroupSummary").setSmallIcon(Icon.createWithResource(str, b2)).setAutoCancel(true).setGroup(str2).setGroupSummary(true).build();
            if (!a8.r() && "com.xiaomi.xmsf".equals(context.getPackageName())) {
                z.k(build, str);
            }
            int a2 = a(str, str2);
            e2.n(a2, build);
            g.j.a.a.a.c.y("group show summary notify:" + a2);
        } catch (Exception e3) {
            g.j.a.a.a.c.o("group show summary error " + e3);
        }
    }

    private void j(Map<String, a> map, StatusBarNotification statusBarNotification) {
        String n2 = n(statusBarNotification.getNotification());
        a aVar = map.get(n2);
        if (aVar == null) {
            aVar = new a();
            map.put(n2, aVar);
        }
        (l(statusBarNotification.getNotification()) ? aVar.b : aVar.a).add(new b(this, statusBarNotification.getId(), statusBarNotification.getNotification()));
    }

    private boolean k() {
        return Build.VERSION.SDK_INT >= 24;
    }

    private boolean l(Notification notification) {
        if (notification != null) {
            Object e2 = com.xiaomi.push.k0.e(notification, "isGroupSummary", null);
            if (e2 instanceof Boolean) {
                return ((Boolean) e2).booleanValue();
            }
            return false;
        }
        return false;
    }

    private boolean m(Context context) {
        if (q(context) && y.t(context)) {
            return b0.d(context).m(h7.LatestNotificationNotIntoGroupSwitch.a(), false);
        }
        return false;
    }

    private String n(Notification notification) {
        if (notification == null) {
            return null;
        }
        return p(notification) ? c(notification) : notification.getGroup();
    }

    private void o(Context context, int i2, Notification notification) {
        String str;
        String v = z.v(notification);
        if (TextUtils.isEmpty(v)) {
            str = "group restore not extract pkg from notification:" + i2;
        } else {
            y e2 = y.e(context, v);
            List<StatusBarNotification> e3 = e(e2);
            if (e3 != null) {
                for (StatusBarNotification statusBarNotification : e3) {
                    Notification notification2 = statusBarNotification.getNotification();
                    if (notification2 != null && p(notification2) && statusBarNotification.getId() != i2) {
                        Notification.Builder recoverBuilder = Notification.Builder.recoverBuilder(context, statusBarNotification.getNotification());
                        recoverBuilder.setGroup(c(notification2));
                        z.o(recoverBuilder, l(notification2));
                        e2.n(statusBarNotification.getId(), recoverBuilder.build());
                        g.j.a.a.a.c.y("group restore notification:" + statusBarNotification.getId());
                    }
                }
                return;
            }
            str = "group restore not get notifications";
        }
        g.j.a.a.a.c.o(str);
    }

    private boolean p(Notification notification) {
        Bundle bundle;
        if (notification == null || notification.getGroup() == null || (bundle = notification.extras) == null) {
            return false;
        }
        return notification.getGroup().equals(String.format("pushmask_%s_%s", Long.valueOf(bundle.getLong("push_src_group_time")), c(notification)));
    }

    private boolean q(Context context) {
        return b0.d(context).m(h7.NotificationAutoGroupSwitch.a(), true);
    }

    public String d(Context context, Notification.Builder builder, String str) {
        if (k() && m(context)) {
            long currentTimeMillis = System.currentTimeMillis();
            Bundle extras = builder.getExtras();
            extras.putString("push_src_group_name", str);
            extras.putLong("push_src_group_time", currentTimeMillis);
            return String.format("pushmask_%s_%s", Long.valueOf(currentTimeMillis), str);
        }
        return str;
    }

    public void f(Context context, int i2, Notification notification) {
        if (k()) {
            if (m(context)) {
                try {
                    o(context, i2, notification);
                } catch (Exception e2) {
                    g.j.a.a.a.c.o("group notify handle restore error " + e2);
                }
            }
            if (q(context)) {
                try {
                    g(context, i2, notification, true);
                } catch (Exception e3) {
                    g.j.a.a.a.c.o("group notify handle auto error " + e3);
                }
            }
        }
    }
}
