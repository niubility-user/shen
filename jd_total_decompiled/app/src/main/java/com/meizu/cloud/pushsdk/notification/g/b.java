package com.meizu.cloud.pushsdk.notification.g;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.meizu.cloud.pushinternal.DebugLogger;
import java.lang.reflect.Field;
import java.util.HashMap;
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
    */
    private static synchronized void h(Context context) {
        synchronized (b.class) {
            if (f15996f != null) {
                return;
            }
            Cursor cursor = null;
            try {
                RingtoneManager ringtoneManager = new RingtoneManager(context);
                ringtoneManager.setType(2);
                cursor = ringtoneManager.getCursor();
            } catch (Exception e2) {
                DebugLogger.e("NotificationUtils", "get ringtone info error, " + e2.getMessage());
            }
            if (cursor == null) {
                if (cursor != null) {
                    cursor.close();
                }
                return;
            }
            f15996f = new HashMap(cursor.getCount());
            for (boolean moveToFirst = cursor.moveToFirst(); moveToFirst; moveToFirst = cursor.moveToNext()) {
                String string = cursor.getString(1);
                Uri withAppendedId = ContentUris.withAppendedId(Uri.parse(cursor.getString(2)), cursor.getLong(0));
                if (!TextUtils.isEmpty(string) && withAppendedId != null) {
                    f15996f.put(string.toLowerCase(), withAppendedId);
                }
            }
            cursor.close();
        }
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
