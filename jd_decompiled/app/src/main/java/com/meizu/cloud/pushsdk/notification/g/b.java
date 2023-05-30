package com.meizu.cloud.pushsdk.notification.g;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.meizu.cloud.pushinternal.DebugLogger;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes14.dex */
public class b {
    private static Field a;
    private static Field b;

    /* renamed from: c  reason: collision with root package name */
    private static Field f15994c;
    private static final Object d = new Object();

    /* renamed from: e  reason: collision with root package name */
    private static final Map<String, Set<String>> f15995e = new ConcurrentHashMap();

    /* renamed from: f  reason: collision with root package name */
    private static Map<String, Uri> f15996f;

    static {
        try {
            a = Notification.class.getDeclaredField("mFlymeNotification");
            Field declaredField = Class.forName("android.app.NotificationExt").getDeclaredField("internalApp");
            b = declaredField;
            declaredField.setAccessible(true);
        } catch (Exception e2) {
            DebugLogger.e("NotificationUtils", "init NotificationUtils error " + e2.getMessage());
        }
        try {
            Field declaredField2 = Notification.class.getDeclaredField("replyIntent");
            f15994c = declaredField2;
            declaredField2.setAccessible(true);
        } catch (Exception e3) {
            DebugLogger.e("NotificationUtils", "init NotificationUtils error " + e3.getMessage());
        }
    }

    public static void a(Notification notification, PendingIntent pendingIntent) {
        Field field = f15994c;
        if (field != null) {
            try {
                field.set(notification, pendingIntent);
            } catch (IllegalAccessException e2) {
                DebugLogger.e("NotificationUtils", "setReplyIntent error " + e2.getMessage());
            }
        }
    }

    public static void b(Notification notification, boolean z) {
        Field field = a;
        if (field == null || b == null) {
            return;
        }
        try {
            b.set(field.get(notification), Integer.valueOf(z ? 1 : 0));
        } catch (IllegalAccessException e2) {
            DebugLogger.e("NotificationUtils", "setInternalApp error " + e2.getMessage());
        }
    }

    public static void c(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
    }

    public static void d(Context context, String str) {
        Set<String> set;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
        if (notificationManager == null || TextUtils.isEmpty(str) || (set = f15995e.get(str)) == null) {
            return;
        }
        for (String str2 : set) {
            DebugLogger.i("NotificationUtils", "clear notifyId " + str2 + " notification");
            notificationManager.cancel(Integer.parseInt(str2));
        }
        set.clear();
    }

    public static void e(Context context, String str, int i2) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
        if (notificationManager != null) {
            DebugLogger.i("NotificationUtils", "clear clearNotification notifyId " + i2);
            notificationManager.cancel(i2);
            Set<String> set = f15995e.get(str);
            if (set != null) {
                set.remove(String.valueOf(i2));
            }
        }
    }

    public static boolean f(Context context, String str, String str2) {
        synchronized (d) {
            if (TextUtils.isEmpty(str2)) {
                return false;
            }
            int q = com.meizu.cloud.pushsdk.util.b.q(context, str, str2);
            DebugLogger.e("NotificationUtils", "removeNotifyKey " + str2 + " notifyId " + q);
            i(context, str, q);
            return com.meizu.cloud.pushsdk.util.b.E(context, str, str2);
        }
    }

    public static Uri g(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str) || RingtoneManager.getActualDefaultRingtoneUri(context, 2) == null) {
            return null;
        }
        if (f15996f == null) {
            h(context);
        }
        Map<String, Uri> map = f15996f;
        if (map != null && map.size() != 0) {
            return f15996f.get(str.toLowerCase());
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x007a, code lost:
        if (0 == 0) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static synchronized void h(android.content.Context r6) {
        /*
            java.lang.Class<com.meizu.cloud.pushsdk.notification.g.b> r0 = com.meizu.cloud.pushsdk.notification.g.b.class
            monitor-enter(r0)
            java.util.Map<java.lang.String, android.net.Uri> r1 = com.meizu.cloud.pushsdk.notification.g.b.f15996f     // Catch: java.lang.Throwable -> L87
            if (r1 == 0) goto L9
            monitor-exit(r0)
            return
        L9:
            r1 = 0
            android.media.RingtoneManager r2 = new android.media.RingtoneManager     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            r6 = 2
            r2.setType(r6)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            android.database.Cursor r1 = r2.getCursor()     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            if (r1 != 0) goto L20
            if (r1 == 0) goto L1e
            r1.close()     // Catch: java.lang.Throwable -> L87
        L1e:
            monitor-exit(r0)
            return
        L20:
            java.util.HashMap r2 = new java.util.HashMap     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            int r3 = r1.getCount()     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            com.meizu.cloud.pushsdk.notification.g.b.f15996f = r2     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            boolean r2 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
        L2f:
            if (r2 == 0) goto L7c
            r2 = 1
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            java.lang.String r3 = r1.getString(r6)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            r4 = 0
            long r4 = r1.getLong(r4)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            android.net.Uri r3 = android.content.ContentUris.withAppendedId(r3, r4)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            if (r4 != 0) goto L58
            if (r3 == 0) goto L58
            java.util.Map<java.lang.String, android.net.Uri> r4 = com.meizu.cloud.pushsdk.notification.g.b.f15996f     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            java.lang.String r2 = r2.toLowerCase()     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            r4.put(r2, r3)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
        L58:
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            goto L2f
        L5d:
            r6 = move-exception
            goto L81
        L5f:
            r6 = move-exception
            java.lang.String r2 = "NotificationUtils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5d
            r3.<init>()     // Catch: java.lang.Throwable -> L5d
            java.lang.String r4 = "get ringtone info error, "
            r3.append(r4)     // Catch: java.lang.Throwable -> L5d
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> L5d
            r3.append(r6)     // Catch: java.lang.Throwable -> L5d
            java.lang.String r6 = r3.toString()     // Catch: java.lang.Throwable -> L5d
            com.meizu.cloud.pushinternal.DebugLogger.e(r2, r6)     // Catch: java.lang.Throwable -> L5d
            if (r1 == 0) goto L7f
        L7c:
            r1.close()     // Catch: java.lang.Throwable -> L87
        L7f:
            monitor-exit(r0)
            return
        L81:
            if (r1 == 0) goto L86
            r1.close()     // Catch: java.lang.Throwable -> L87
        L86:
            throw r6     // Catch: java.lang.Throwable -> L87
        L87:
            r6 = move-exception
            monitor-exit(r0)
            goto L8b
        L8a:
            throw r6
        L8b:
            goto L8a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.notification.g.b.h(android.content.Context):void");
    }

    public static void i(Context context, String str, int i2) {
        Set<String> set = f15995e.get(str);
        if (set != null) {
            set.remove(String.valueOf(i2));
            DebugLogger.i("NotificationUtils", "remove notifyId " + i2);
        }
    }

    public static void j(Context context, String str, int i2) {
        Map<String, Set<String>> map = f15995e;
        Set<String> set = map.get(str);
        DebugLogger.i("NotificationUtils", "store notifyId " + i2);
        if (set != null) {
            set.add(String.valueOf(i2));
            return;
        }
        HashSet hashSet = new HashSet();
        hashSet.add(String.valueOf(i2));
        map.put(str, hashSet);
    }
}
