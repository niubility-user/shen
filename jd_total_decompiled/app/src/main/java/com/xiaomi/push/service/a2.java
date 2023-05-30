package com.xiaomi.push.service;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.service.notification.StatusBarNotification;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.xiaomi.push.a8;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes11.dex */
public class a2 {
    private static List<a> a = new CopyOnWriteArrayList();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class a {
        public final long a;

        a(String str, long j2, int i2, Notification.Action[] actionArr) {
            this.a = j2;
        }
    }

    private static void a() {
        for (int size = a.size() - 1; size >= 0; size--) {
            a aVar = a.get(size);
            if (SystemClock.elapsedRealtime() - aVar.a > Final.FIVE_SECOND) {
                a.remove(aVar);
            }
        }
        if (a.size() > 10) {
            a.remove(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void b(Context context, StatusBarNotification statusBarNotification, int i2) {
        if (!a8.j(context) || i2 <= 0 || statusBarNotification == null || Build.VERSION.SDK_INT < 20) {
            return;
        }
        c(new a(statusBarNotification.getKey(), SystemClock.elapsedRealtime(), i2, z.s(statusBarNotification.getNotification())));
    }

    private static void c(a aVar) {
        a.add(aVar);
        a();
    }
}
