package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.xiaomi.push.a8;
import com.xiaomi.push.h7;
import com.xiaomi.push.z6;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: classes11.dex */
public class y {

    /* renamed from: c  reason: collision with root package name */
    private static Context f19202c;
    private static Object d;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f19203e;

    /* renamed from: f  reason: collision with root package name */
    private static WeakHashMap<Integer, y> f19204f = new WeakHashMap<>();
    private String a;
    private String b;

    private y(String str) {
        this.a = str;
    }

    private static int a(String str) {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                return f19202c.getPackageManager().getPackageUid(str, 0);
            } catch (Exception unused) {
                return -1;
            }
        }
        return -1;
    }

    private static NotificationManager c() {
        return (NotificationManager) f19202c.getSystemService(RemoteMessageConst.NOTIFICATION);
    }

    public static y e(Context context, String str) {
        q(context);
        int hashCode = str.hashCode();
        y yVar = f19204f.get(Integer.valueOf(hashCode));
        if (yVar == null) {
            y yVar2 = new y(str);
            f19204f.put(Integer.valueOf(hashCode), yVar2);
            return yVar2;
        }
        return yVar;
    }

    private static <T> T f(Object obj) {
        if (obj != null) {
            try {
                return (T) obj.getClass().getMethod("getList", new Class[0]).invoke(obj, new Object[0]);
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    private static Object g(List list) {
        return Class.forName("android.content.pm.ParceledListSlice").getConstructor(List.class).newInstance(list);
    }

    public static String j(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String k2 = k("mipush|%s|%s", str2, "");
        return str.startsWith(k2) ? k("mipush_%s_%s", str2, str.replace(k2, "")) : str;
    }

    private static String k(String str, String str2, String str3) {
        return TextUtils.isEmpty(str) ? "" : String.format(str, str2, str3);
    }

    private static void q(Context context) {
        if (f19202c == null) {
            f19202c = context.getApplicationContext();
            NotificationManager c2 = c();
            Boolean bool = (Boolean) com.xiaomi.push.k0.e(c2, "isSystemConditionProviderEnabled", "xmsf_fake_condition_provider_path");
            r("fwk is support.init:" + bool);
            boolean booleanValue = bool != null ? bool.booleanValue() : false;
            f19203e = booleanValue;
            if (booleanValue) {
                d = com.xiaomi.push.k0.e(c2, "getService", new Object[0]);
            }
        }
    }

    static void r(String str) {
        g.j.a.a.a.c.o("NMHelper:" + str);
    }

    private static boolean s() {
        if (a8.i() && b0.d(f19202c).m(h7.NotificationBelongToAppSwitch.a(), true)) {
            return f19203e;
        }
        return false;
    }

    public static boolean t(Context context) {
        q(context);
        return s();
    }

    private StatusBarNotification[] v() {
        if (a8.j(d())) {
            try {
                Object e2 = com.xiaomi.push.k0.e(d, "getActiveNotifications", d().getPackageName());
                if (e2 instanceof StatusBarNotification[]) {
                    return (StatusBarNotification[]) e2;
                }
                return null;
            } catch (Throwable th) {
                r("getAllNotifications error " + th);
                return null;
            }
        }
        return null;
    }

    private String x(String str) {
        return k(s() ? "mipush|%s|%s" : "mipush_%s_%s", this.a, str);
    }

    @TargetApi(26)
    public NotificationChannel b(String str) {
        NotificationChannel notificationChannel = null;
        try {
            if (s()) {
                List<NotificationChannel> l2 = l();
                if (l2 != null) {
                    for (NotificationChannel notificationChannel2 : l2) {
                        if (str.equals(notificationChannel2.getId())) {
                            notificationChannel = notificationChannel2;
                            break;
                        }
                    }
                }
            } else {
                notificationChannel = c().getNotificationChannel(str);
            }
        } catch (Exception e2) {
            r("getNotificationChannel error" + e2);
        }
        return notificationChannel;
    }

    public Context d() {
        return f19202c;
    }

    public String h() {
        return this.a;
    }

    public String i(String str) {
        return TextUtils.isEmpty(str) ? w() : a8.j(d()) ? x(str) : str;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:10:0x0036
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    @android.annotation.TargetApi(26)
    java.util.List<android.app.NotificationChannel> l() {
        /*
            r8 = this;
            java.lang.String r0 = r8.a
            r1 = 0
            boolean r2 = s()     // Catch: java.lang.Exception -> L78
            if (r2 == 0) goto L3b
            int r2 = a(r0)     // Catch: java.lang.Exception -> L78
            r3 = -1
            if (r2 == r3) goto L39
            java.lang.Object r3 = com.xiaomi.push.service.y.d     // Catch: java.lang.Exception -> L78
            java.lang.String r4 = "getNotificationChannelsForPackage"
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> L78
            r6 = 0
            r5[r6] = r0     // Catch: java.lang.Exception -> L78
            r6 = 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Exception -> L78
            r5[r6] = r2     // Catch: java.lang.Exception -> L78
            r2 = 2
            java.lang.Boolean r6 = java.lang.Boolean.FALSE     // Catch: java.lang.Exception -> L78
            r5[r2] = r6     // Catch: java.lang.Exception -> L78
            java.lang.Object r2 = com.xiaomi.push.k0.e(r3, r4, r5)     // Catch: java.lang.Exception -> L78
            java.lang.Object r2 = f(r2)     // Catch: java.lang.Exception -> L78
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.Exception -> L78
            java.lang.String r1 = "mipush|%s|%s"
            r7 = r2
            r2 = r1
            r1 = r7
            goto L45
        L36:
            r0 = move-exception
            r1 = r2
            goto L79
        L39:
            r2 = r1
            goto L45
        L3b:
            android.app.NotificationManager r2 = c()     // Catch: java.lang.Exception -> L78
            java.util.List r1 = r2.getNotificationChannels()     // Catch: java.lang.Exception -> L78
            java.lang.String r2 = "mipush_%s_%s"
        L45:
            boolean r3 = com.xiaomi.push.a8.i()     // Catch: java.lang.Exception -> L78
            if (r3 == 0) goto L8d
            if (r1 == 0) goto L8d
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: java.lang.Exception -> L78
            r3.<init>()     // Catch: java.lang.Exception -> L78
            java.lang.String r4 = ""
            java.lang.String r0 = k(r2, r0, r4)     // Catch: java.lang.Exception -> L78
            java.util.Iterator r2 = r1.iterator()     // Catch: java.lang.Exception -> L78
        L5c:
            boolean r4 = r2.hasNext()     // Catch: java.lang.Exception -> L78
            if (r4 == 0) goto L76
            java.lang.Object r4 = r2.next()     // Catch: java.lang.Exception -> L78
            android.app.NotificationChannel r4 = (android.app.NotificationChannel) r4     // Catch: java.lang.Exception -> L78
            java.lang.String r5 = r4.getId()     // Catch: java.lang.Exception -> L78
            boolean r5 = r5.startsWith(r0)     // Catch: java.lang.Exception -> L78
            if (r5 == 0) goto L5c
            r3.add(r4)     // Catch: java.lang.Exception -> L78
            goto L5c
        L76:
            r1 = r3
            goto L8d
        L78:
            r0 = move-exception
        L79:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "getNotificationChannels error "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r(r0)
        L8d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.y.l():java.util.List");
    }

    public void m(int i2) {
        String str = this.a;
        try {
            if (!s()) {
                c().cancel(i2);
                return;
            }
            int c2 = z6.c();
            String packageName = d().getPackageName();
            if (Build.VERSION.SDK_INT >= 30) {
                com.xiaomi.push.k0.n(d, "cancelNotificationWithTag", str, packageName, null, Integer.valueOf(i2), Integer.valueOf(c2));
            } else {
                com.xiaomi.push.k0.n(d, "cancelNotificationWithTag", str, null, Integer.valueOf(i2), Integer.valueOf(c2));
            }
            r("cancel succ:" + i2);
        } catch (Exception e2) {
            r("cancel error" + e2);
        }
    }

    public void n(int i2, Notification notification) {
        String str = this.a;
        NotificationManager c2 = c();
        try {
            int i3 = Build.VERSION.SDK_INT;
            if (s()) {
                if (i3 >= 19) {
                    notification.extras.putString("xmsf_target_package", str);
                }
                if (i3 >= 29) {
                    c2.notifyAsPackage(str, null, i2, notification);
                    return;
                }
            }
            c2.notify(i2, notification);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(26)
    public void o(NotificationChannel notificationChannel) {
        String str = this.a;
        try {
            if (s()) {
                int a = a(str);
                if (a != -1) {
                    com.xiaomi.push.k0.n(d, "createNotificationChannelsForPackage", str, Integer.valueOf(a), g(Arrays.asList(notificationChannel)));
                }
            } else {
                c().createNotificationChannel(notificationChannel);
            }
        } catch (Exception e2) {
            r("createNotificationChannel error" + e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(NotificationChannel notificationChannel, boolean z) {
        String str = this.a;
        try {
            if (z) {
                int a = a(str);
                if (a != -1) {
                    com.xiaomi.push.k0.n(d, "updateNotificationChannelForPackage", str, Integer.valueOf(a), notificationChannel);
                }
            } else {
                o(notificationChannel);
            }
        } catch (Exception e2) {
            r("updateNotificationChannel error " + e2);
        }
    }

    public String toString() {
        return "NotificationManagerHelper{" + this.a + "}";
    }

    public boolean u(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith(x(""));
    }

    String w() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = x("default");
        }
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String y(String str, String str2) {
        return s() ? str : str2;
    }

    public List<StatusBarNotification> z() {
        String str = this.a;
        NotificationManager c2 = c();
        ArrayList arrayList = null;
        try {
            if (s()) {
                int c3 = z6.c();
                if (c3 != -1) {
                    return (List) f(com.xiaomi.push.k0.e(d, "getAppActiveNotifications", str, Integer.valueOf(c3)));
                }
                return null;
            }
            StatusBarNotification[] activeNotifications = Build.VERSION.SDK_INT >= 23 ? c2.getActiveNotifications() : v();
            if (activeNotifications == null || activeNotifications.length <= 0) {
                return null;
            }
            ArrayList arrayList2 = new ArrayList();
            try {
                for (StatusBarNotification statusBarNotification : activeNotifications) {
                    if (str.equals(z.v(statusBarNotification.getNotification()))) {
                        arrayList2.add(statusBarNotification);
                    }
                }
                return arrayList2;
            } catch (Throwable th) {
                th = th;
                arrayList = arrayList2;
                r("getActiveNotifications error " + th);
                return arrayList;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
