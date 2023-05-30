package com.huawei.hms.push;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.api.push.TransActivity;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: classes12.dex */
public class l {
    private static int a;

    public static synchronized void a(Context context, m mVar) {
        int hashCode;
        int i2;
        int i3;
        int hashCode2;
        synchronized (l.class) {
            if (context != null) {
                if (!a(mVar)) {
                    HMSLog.d("PushSelfShowLog", "showNotification, the msg id = " + mVar.p());
                    if (a == 0) {
                        a = (context.getPackageName() + System.currentTimeMillis()).hashCode();
                    }
                    if (TextUtils.isEmpty(mVar.l())) {
                        String q = mVar.q();
                        if (!TextUtils.isEmpty(q)) {
                            int hashCode3 = q.hashCode();
                            mVar.a(hashCode3);
                            HMSLog.d("PushSelfShowLog", "notification msgTag = " + hashCode3);
                        }
                        if (mVar.s() != -1) {
                            hashCode = mVar.s();
                            i2 = (mVar.k() + System.currentTimeMillis()).hashCode();
                            i3 = i2 + 1;
                            hashCode2 = (mVar.s() + mVar.k() + context.getPackageName()).hashCode();
                        } else {
                            hashCode = a + 1;
                            i2 = hashCode + 1;
                            i3 = i2 + 1;
                            hashCode2 = i3 + 1;
                            a = hashCode2;
                        }
                    } else {
                        hashCode = (mVar.l() + mVar.k()).hashCode();
                        i2 = a + 1;
                        i3 = i2 + 1;
                        a = i3;
                        hashCode2 = (mVar.l() + mVar.k() + context.getPackageName()).hashCode();
                    }
                    HMSLog.d("PushSelfShowLog", "notifyId:" + hashCode + ",openNotifyId:" + i2 + ",delNotifyId:" + i3 + ",alarmNotifyId:" + hashCode2);
                    int[] iArr = new int[4];
                    iArr[0] = hashCode;
                    iArr[1] = i2;
                    iArr[2] = i3;
                    if (mVar.f() <= 0) {
                        hashCode2 = 0;
                    }
                    iArr[3] = hashCode2;
                    Notification a2 = d.f() ? a(context, mVar, iArr) : null;
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
                    if (notificationManager != null && a2 != null) {
                        if (Build.VERSION.SDK_INT >= 26) {
                            notificationManager.createNotificationChannel(new NotificationChannel("HwPushChannelID", context.getString(ResourceLoaderUtil.getStringId("hms_push_channel")), 3));
                        }
                        notificationManager.notify(hashCode, a2);
                        d(context, mVar, iArr);
                        j.a(context, mVar.p(), mVar.b(), "100");
                    }
                }
            }
        }
    }

    private static PendingIntent b(Context context, m mVar, int[] iArr) {
        return PendingIntent.getBroadcast(context, iArr[2], a(context, mVar, iArr, "2", 268435456), d.b());
    }

    private static PendingIntent c(Context context, m mVar, int[] iArr) {
        Intent a2 = a(context, mVar, iArr, "1", 268435456);
        if (a()) {
            a2.setClass(context, TransActivity.class);
            a2.setFlags(268468224);
            return PendingIntent.getActivity(context, iArr[1], a2, d.b());
        }
        return PendingIntent.getBroadcast(context, iArr[1], a2, d.b());
    }

    private static void d(Context context, m mVar, int[] iArr) {
        HMSLog.i("PushSelfShowLog", "setAutoClear time is: " + mVar.f());
        if (mVar.f() <= 0) {
            return;
        }
        a(context, a(context, mVar, iArr, "-1", 32), mVar.f(), iArr[3]);
    }

    @SuppressLint({"NewApi"})
    private static void b(Context context, Notification.Builder builder, m mVar) {
        if ("com.huawei.android.pushagent".equals(context.getPackageName())) {
            Bundle bundle = new Bundle();
            String k2 = mVar.k();
            if (TextUtils.isEmpty(k2)) {
                return;
            }
            bundle.putString("hw_origin_sender_package_name", k2);
            builder.setExtras(bundle);
        }
    }

    private static void d(m mVar, Notification.Builder builder) {
        String u = mVar.u();
        String j2 = mVar.j();
        if (TextUtils.isEmpty(j2)) {
            builder.setContentText(u);
            return;
        }
        builder.setContentText(j2);
        if (TextUtils.isEmpty(u)) {
            return;
        }
        builder.setContentTitle(u);
    }

    private static void c(m mVar, Notification.Builder builder) {
        builder.setTicker(mVar.x());
    }

    private static void b(m mVar, Notification.Builder builder) {
        String t = mVar.t();
        if (TextUtils.isEmpty(t)) {
            return;
        }
        builder.setSubText(t);
    }

    private static boolean a() {
        return Build.VERSION.SDK_INT >= 30;
    }

    private static Intent a(Context context, m mVar, int[] iArr, String str, int i2) {
        Intent intent = new Intent("com.huawei.intent.action.PUSH_DELAY_NOTIFY");
        intent.putExtra("selfshow_info", mVar.o()).putExtra("selfshow_token", mVar.y()).putExtra("selfshow_event_id", str).putExtra("selfshow_notify_id", iArr[0]).putExtra("selfshow_auto_clear_id", iArr[3]).setPackage(context.getPackageName()).setFlags(i2);
        return intent;
    }

    private static Notification a(Context context, m mVar, int[] iArr) {
        Notification.Builder builder = new Notification.Builder(context);
        if (h.a(mVar) == i.STYLE_BIGTEXT) {
            h.a(builder, mVar.g(), mVar);
        }
        f.a(context, builder, mVar);
        b(mVar, builder);
        d(mVar, builder);
        a(context, mVar, builder);
        a(builder);
        a(mVar, builder);
        c(mVar, builder);
        builder.setContentIntent(c(context, mVar, iArr));
        builder.setDeleteIntent(b(context, mVar, iArr));
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId("HwPushChannelID");
        }
        b(context, builder, mVar);
        a(context, builder, mVar);
        return builder.build();
    }

    @SuppressLint({"NewApi"})
    private static void a(Context context, Notification.Builder builder, m mVar) {
        if (HwBuildEx.VERSION.EMUI_SDK_INT < 11 || !d.a(context)) {
            return;
        }
        Bundle bundle = new Bundle();
        String k2 = mVar.k();
        HMSLog.i("PushSelfShowLog", "the package name of notification is:" + k2);
        if (!TextUtils.isEmpty(k2)) {
            String a2 = d.a(context, k2);
            HMSLog.i("PushSelfShowLog", "the app name is:" + a2);
            if (a2 != null) {
                bundle.putCharSequence("android.extraAppName", a2);
            }
        }
        builder.setExtras(bundle);
    }

    private static void a(Context context, Intent intent, long j2, int i2) {
        try {
            HMSLog.d("PushSelfShowLog", "enter setDelayAlarm(interval:" + j2 + "ms.");
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            if (alarmManager != null) {
                alarmManager.set(0, System.currentTimeMillis() + j2, PendingIntent.getBroadcast(context, i2, intent, d.b()));
            }
        } catch (Exception e2) {
            HMSLog.w("PushSelfShowLog", "set DelayAlarm error." + e2.toString());
        }
    }

    private static void a(Context context, m mVar, Notification.Builder builder) {
        Bitmap a2 = f.a(context, mVar);
        if (a2 != null) {
            builder.setLargeIcon(a2);
        }
    }

    private static void a(Notification.Builder builder) {
        builder.setShowWhen(true);
        builder.setWhen(System.currentTimeMillis());
    }

    private static void a(m mVar, Notification.Builder builder) {
        builder.setAutoCancel(mVar.e() == 1);
        builder.setOngoing(false);
    }

    private static boolean a(m mVar) {
        return mVar == null || (TextUtils.isEmpty(mVar.u()) && TextUtils.isEmpty(mVar.j()));
    }
}
