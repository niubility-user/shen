package com.meizu.cloud.pushsdk.notification;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.jdsdk.constant.CartConstant;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSettingEx;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public abstract class a implements c {
    protected final Context a;
    protected final PushNotificationBuilder b;

    /* renamed from: c */
    private final NotificationManager f15992c;

    public a(Context context, PushNotificationBuilder pushNotificationBuilder) {
        this.b = pushNotificationBuilder;
        this.a = context;
        new Handler(context.getMainLooper());
        this.f15992c = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
    }

    private Notification b(MessageV3 messageV3, PendingIntent pendingIntent, PendingIntent pendingIntent2, PendingIntent pendingIntent3) {
        Notification.Builder builder = new Notification.Builder(this.a);
        h(builder, messageV3, pendingIntent, pendingIntent2);
        g(builder, messageV3);
        o(builder, messageV3);
        q(builder, messageV3);
        t(builder, messageV3);
        Notification build = MinSdkChecker.isSupportNotificationBuild() ? builder.build() : builder.getNotification();
        p(build, messageV3);
        i(build, messageV3);
        j(build, messageV3, pendingIntent3);
        return build;
    }

    private PendingIntent c(MessageV3 messageV3, String str) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        if (MinSdkChecker.isSupportTransmitMessageValue(this.a, str)) {
            intent.putExtra(PushConstants.MZ_MESSAGE_VALUE, com.meizu.cloud.pushsdk.handler.d.b(messageV3));
        } else {
            intent.putExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE, messageV3);
        }
        intent.putExtra("method", PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_PRIVATE);
        intent.putExtra("package_name", str);
        String findReceiver = MzSystemUtils.findReceiver(this.a, PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, str);
        findReceiver.getClass();
        intent.setClassName(str, findReceiver);
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        intent.setFlags(32);
        return PendingIntent.getBroadcast(this.a, 0, intent, 1140850688);
    }

    private void f(int i2, String str, MessageV3 messageV3) {
        if (messageV3 == null || messageV3.getAdvertisementOption() == null || TextUtils.isEmpty(messageV3.getAdvertisementOption().getAdPackage())) {
            return;
        }
        DebugLogger.e("AbstractPushNotification", "save ad and recovery package, uploadDataPackageName:" + str);
        com.meizu.cloud.pushsdk.handler.f.b.a e2 = com.meizu.cloud.pushsdk.b.a(this.a).e();
        if (e2 != null) {
            int priorityValidTime = messageV3.getAdvertisementOption().getPriorityValidTime();
            e2.e(messageV3);
            e2.d(i2, b(messageV3, s(messageV3), v(messageV3), u(messageV3)), priorityValidTime);
        }
        messageV3.setUploadDataPackageName(str);
    }

    @SuppressLint({"WrongConstant"})
    private void g(Notification.Builder builder, MessageV3 messageV3) {
        boolean isSound;
        AdvanceSetting advanceSetting = messageV3.getAdvanceSetting();
        AdvanceSettingEx advanceSettingEx = messageV3.getAdvanceSettingEx();
        if (advanceSetting == null) {
            return;
        }
        if (advanceSettingEx == null || TextUtils.isEmpty(advanceSettingEx.getSoundTitle())) {
            isSound = advanceSetting.getNotifyType().isSound();
        } else {
            Uri g2 = com.meizu.cloud.pushsdk.notification.g.b.g(this.a, advanceSettingEx.getSoundTitle());
            if (g2 != null) {
                DebugLogger.e("AbstractPushNotification", "advance setting builder, sound:" + g2);
                builder.setSound(g2);
                isSound = false;
            } else {
                isSound = true;
            }
        }
        if (advanceSetting.getNotifyType() != null) {
            boolean isVibrate = advanceSetting.getNotifyType().isVibrate();
            boolean isLights = advanceSetting.getNotifyType().isLights();
            if (isVibrate || isLights || isSound) {
                int i2 = isVibrate ? 2 : 0;
                if (isLights) {
                    i2 |= 4;
                }
                if (isSound) {
                    i2 |= 1;
                }
                DebugLogger.e("AbstractPushNotification", "advance setting builder, defaults:" + i2);
                builder.setDefaults(i2);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("advance setting builder, ongoing:");
        sb.append(!advanceSetting.isClearNotification());
        DebugLogger.e("AbstractPushNotification", sb.toString());
        builder.setOngoing(!advanceSetting.isClearNotification());
        if (advanceSettingEx == null || !MinSdkChecker.isSupportNotificationBuild()) {
            return;
        }
        DebugLogger.e("AbstractPushNotification", "advance setting builder, priority:" + advanceSettingEx.getPriorityDisplay());
        builder.setPriority(advanceSettingEx.getPriorityDisplay());
    }

    private void h(Notification.Builder builder, MessageV3 messageV3, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        int statusBarIcon;
        builder.setContentTitle(messageV3.getTitle());
        builder.setContentText(messageV3.getContent());
        builder.setTicker(messageV3.getTitle());
        builder.setAutoCancel(true);
        if (MinSdkChecker.isSupportSendNotification()) {
            builder.setVisibility(1);
        }
        if (MinSdkChecker.isSupportSetDrawableSmallIcon()) {
            Icon l2 = l(messageV3.getUploadDataPackageName());
            if (l2 != null) {
                builder.setSmallIcon(l2);
                builder.setContentIntent(pendingIntent);
                builder.setDeleteIntent(pendingIntent2);
            }
            DebugLogger.e("AbstractPushNotification", "cannot get " + messageV3.getUploadDataPackageName() + " smallIcon");
        } else {
            PushNotificationBuilder pushNotificationBuilder = this.b;
            if (pushNotificationBuilder != null && pushNotificationBuilder.getStatusBarIcon() != 0) {
                statusBarIcon = this.b.getStatusBarIcon();
                builder.setSmallIcon(statusBarIcon);
                builder.setContentIntent(pendingIntent);
                builder.setDeleteIntent(pendingIntent2);
            }
        }
        statusBarIcon = com.meizu.cloud.pushsdk.notification.g.c.m(this.a);
        builder.setSmallIcon(statusBarIcon);
        builder.setContentIntent(pendingIntent);
        builder.setDeleteIntent(pendingIntent2);
    }

    @TargetApi(23)
    private Icon l(String str) {
        try {
            int identifier = this.a.getPackageManager().getResourcesForApplication(str).getIdentifier(PushConstants.MZ_PUSH_NOTIFICATION_SMALL_ICON, "drawable", str);
            if (identifier != 0) {
                DebugLogger.i("AbstractPushNotification", "get " + str + " smallIcon success resId " + identifier);
                return Icon.createWithResource(str, identifier);
            }
        } catch (Exception e2) {
            DebugLogger.e("AbstractPushNotification", "cannot load smallIcon form package " + str + " Error message " + e2.getMessage());
        }
        return null;
    }

    private String m(Context context, String str) {
        CharSequence applicationLabel;
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            if (applicationInfo != null && (applicationLabel = packageManager.getApplicationLabel(applicationInfo)) != null) {
                return (String) applicationLabel;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            DebugLogger.e("AbstractPushNotification", "can not find " + str + " application info");
        }
        return null;
    }

    private String n(MessageV3 messageV3) {
        if (messageV3 == null || messageV3.getAdvertisementOption() == null || TextUtils.isEmpty(messageV3.getAdvertisementOption().getAdPackage())) {
            return null;
        }
        String uploadDataPackageName = messageV3.getUploadDataPackageName();
        String adPackage = messageV3.getAdvertisementOption().getAdPackage();
        DebugLogger.e("AbstractPushNotification", "again show old ad and replace package, uploadDataPackageName:" + uploadDataPackageName + ", adPackageName:" + adPackage);
        com.meizu.cloud.pushsdk.handler.f.b.a e2 = com.meizu.cloud.pushsdk.b.a(this.a).e();
        if (e2 != null) {
            e2.a();
        }
        messageV3.setUploadDataPackageName(adPackage);
        return uploadDataPackageName;
    }

    @SuppressLint({"NewApi"})
    private void p(Notification notification, MessageV3 messageV3) {
        com.meizu.cloud.pushsdk.notification.g.b.b(notification, true);
        com.meizu.cloud.pushsdk.notification.g.b.a(notification, w(messageV3));
        notification.extras.putString(PushConstants.EXTRA_ORIGINAL_NOTIFICATION_PACKAGE_NAME, messageV3.getUploadDataPackageName());
        notification.extras.putString(PushConstants.EXTRA_FLYME_GREEN_NOTIFICATION_SETTING, x(messageV3));
        notification.extras.putString(PushConstants.NOTIFICATION_EXTRA_TASK_ID, messageV3.getTaskId());
        notification.extras.putString(PushConstants.NOTIFICATION_EXTRA_SEQ_ID, messageV3.getSeqId());
        notification.extras.putString(PushConstants.NOTIFICATION_EXTRA_DEVICE_ID, messageV3.getDeviceId());
        notification.extras.putString(PushConstants.NOTIFICATION_EXTRA_PUSH_TIMESTAMP, messageV3.getPushTimestamp());
        if (!TextUtils.isEmpty(this.b.getAppLabel())) {
            DebugLogger.e("AbstractPushNotification", "set app label " + this.b.getAppLabel());
            notification.extras.putString(PushConstants.EXTRA_SUBSTITUTE_APP_NAME, this.b.getAppLabel());
            return;
        }
        String m2 = m(this.a, messageV3.getUploadDataPackageName());
        DebugLogger.e("AbstractPushNotification", "current package " + messageV3.getUploadDataPackageName() + " label is " + m2);
        if (TextUtils.isEmpty(m2)) {
            return;
        }
        notification.extras.putString(PushConstants.EXTRA_SUBSTITUTE_APP_NAME, m2);
    }

    private boolean r(MessageV3 messageV3) {
        if (messageV3.getAdvertisementOption() == null || TextUtils.isEmpty(messageV3.getAdvertisementOption().getAdPackage())) {
            return messageV3.getWhiteList() && !MzSystemUtils.isExistReceiver(this.a, messageV3.getUploadDataPackageName(), PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        }
        return true;
    }

    private PendingIntent s(MessageV3 messageV3) {
        return c(messageV3, r(messageV3) ? messageV3.getPackageName() : messageV3.getUploadDataPackageName());
    }

    private void t(Notification.Builder builder, MessageV3 messageV3) {
        String str;
        String str2;
        if (MinSdkChecker.isSupportNotificationChannel()) {
            AdvanceSetting advanceSetting = messageV3.getAdvanceSetting();
            AdvanceSettingEx advanceSettingEx = messageV3.getAdvanceSettingEx();
            int priorityDisplay = advanceSettingEx != null ? advanceSettingEx.getPriorityDisplay() : 0;
            if (Build.VERSION.SDK_INT >= 29 && advanceSetting.isHeadUpNotification() && advanceSettingEx.getPriorityDisplay() < 1) {
                priorityDisplay = 1;
            }
            int i2 = 2;
            if (priorityDisplay == -2) {
                str = "mz_push_notification_channel_min";
                str2 = "MEIZUPUSHMIN";
                i2 = 1;
            } else if (priorityDisplay == -1) {
                str = "mz_push_notification_channel_low";
                str2 = "MEIZUPUSHLOW";
            } else if (priorityDisplay == 1) {
                i2 = 4;
                str = "mz_push_notification_channel_high";
                str2 = "MEIZUPUSHHIGH";
            } else if (priorityDisplay != 2) {
                i2 = 3;
                str = "mz_push_notification_channel";
                str2 = "MEIZUPUSH";
            } else {
                i2 = 5;
                str = "mz_push_notification_channel_max";
                str2 = "MEIZUPUSHMAX";
            }
            Uri g2 = advanceSettingEx.getSoundTitle() != null ? com.meizu.cloud.pushsdk.notification.g.b.g(this.a, advanceSettingEx.getSoundTitle()) : null;
            if (!advanceSetting.getNotifyType().isSound() && advanceSettingEx.getSoundTitle() == null) {
                str = str + "_mute";
                str2 = str2 + "_MUTE";
            } else if (g2 != null) {
                String str3 = str + CartConstant.KEY_YB_INFO_LINK + advanceSettingEx.getSoundTitle().toLowerCase();
                str2 = str2 + CartConstant.KEY_YB_INFO_LINK + advanceSettingEx.getSoundTitle().toUpperCase();
                str = str3;
            }
            DebugLogger.e("AbstractPushNotification", "notification channel builder, channelId: " + str + ", channelName: " + str2 + ", importance:" + i2 + ", sound: " + g2);
            NotificationChannel notificationChannel = new NotificationChannel(str, str2, i2);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(-16711936);
            notificationChannel.setShowBadge(true);
            notificationChannel.enableVibration(true);
            if (!advanceSetting.getNotifyType().isSound() && advanceSettingEx.getSoundTitle() == null) {
                notificationChannel.setSound(null, Notification.AUDIO_ATTRIBUTES_DEFAULT);
            } else if (g2 != null) {
                notificationChannel.setSound(g2, Notification.AUDIO_ATTRIBUTES_DEFAULT);
            }
            this.f15992c.createNotificationChannel(notificationChannel);
            builder.setChannelId(str);
        }
    }

    private PendingIntent u(MessageV3 messageV3) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        intent.putExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE, messageV3);
        intent.putExtra("method", PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_CLOSE);
        intent.setClassName(messageV3.getPackageName(), MzSystemUtils.findReceiver(this.a, PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, messageV3.getPackageName()));
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        return PendingIntent.getBroadcast(this.a, 0, intent, 1140850688);
    }

    private PendingIntent v(MessageV3 messageV3) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        intent.putExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE, messageV3);
        intent.putExtra("method", PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_DELETE);
        intent.setClassName(messageV3.getPackageName(), MzSystemUtils.findReceiver(this.a, PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, messageV3.getPackageName()));
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        return PendingIntent.getBroadcast(this.a, 0, intent, 1140850688);
    }

    private PendingIntent w(MessageV3 messageV3) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        intent.putExtra(PushConstants.MZ_PUSH_NOTIFICATION_STATE_MESSAGE, messageV3.getNotificationMessage());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_TASK_ID, messageV3.getTaskId());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_SEQ_ID, messageV3.getSeqId());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_DEVICE_ID, messageV3.getDeviceId());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_PUSH_TIMESTAMP, messageV3.getPushTimestamp());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_SHOW_PACKAGE_NAME, messageV3.getUploadDataPackageName());
        intent.putExtra(PushConstants.MZ_PUSH_WHITE_LIST, messageV3.getWhiteList());
        intent.putExtra(PushConstants.MZ_PUSH_DELAYED_REPORT_MILLIS, messageV3.getDelayedReportMillis());
        intent.putExtra("method", PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_STATE);
        intent.setClassName(messageV3.getPackageName(), MzSystemUtils.findReceiver(this.a, PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, messageV3.getPackageName()));
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        return PendingIntent.getBroadcast(this.a, 0, intent, 1107296256);
    }

    @Override // com.meizu.cloud.pushsdk.notification.c
    @SuppressLint({"NewApi"})
    public void a(MessageV3 messageV3) {
        String n2 = (messageV3.getAdvertisementOption() == null || TextUtils.isEmpty(messageV3.getAdvertisementOption().getAdPackage())) ? null : n(messageV3);
        Notification b = b(messageV3, s(messageV3), v(messageV3), u(messageV3));
        int abs = Math.abs((int) System.currentTimeMillis());
        com.meizu.cloud.pushsdk.notification.model.a f2 = com.meizu.cloud.pushsdk.notification.model.a.f(messageV3);
        if (f2 != null && f2.a() != 0) {
            abs = f2.a();
            DebugLogger.e("AbstractPushNotification", "server notify id " + abs);
            if (!TextUtils.isEmpty(f2.h())) {
                int q = com.meizu.cloud.pushsdk.util.b.q(this.a, messageV3.getUploadDataPackageName(), f2.h());
                DebugLogger.e("AbstractPushNotification", "notifyKey " + f2.h() + " preference notifyId is " + q);
                if (q != 0) {
                    DebugLogger.e("AbstractPushNotification", "use preference notifyId " + q + " and cancel it");
                    this.f15992c.cancel(q);
                }
                DebugLogger.e("AbstractPushNotification", "store new notifyId " + abs + " by notifyKey " + f2.h());
                com.meizu.cloud.pushsdk.util.b.n(this.a, messageV3.getUploadDataPackageName(), f2.h(), abs);
            }
        }
        DebugLogger.e("AbstractPushNotification", "current notify id " + abs);
        if (messageV3.isDiscard()) {
            if (com.meizu.cloud.pushsdk.util.b.k(this.a, messageV3.getPackageName()) == 0) {
                com.meizu.cloud.pushsdk.util.b.c(this.a, messageV3.getPackageName(), abs);
                DebugLogger.i("AbstractPushNotification", "no notification show so put notification id " + abs);
            }
            if (!TextUtils.isEmpty(messageV3.getTaskId())) {
                if (com.meizu.cloud.pushsdk.util.b.p(this.a, messageV3.getPackageName()) == 0) {
                    com.meizu.cloud.pushsdk.util.b.m(this.a, messageV3.getPackageName(), Integer.valueOf(messageV3.getTaskId()).intValue());
                } else if (Integer.valueOf(messageV3.getTaskId()).intValue() < com.meizu.cloud.pushsdk.util.b.p(this.a, messageV3.getPackageName())) {
                    DebugLogger.i("AbstractPushNotification", "current package " + messageV3.getPackageName() + " task id " + messageV3.getTaskId() + " don't show notification");
                    return;
                } else {
                    com.meizu.cloud.pushsdk.util.b.m(this.a, messageV3.getPackageName(), Integer.valueOf(messageV3.getTaskId()).intValue());
                    abs = com.meizu.cloud.pushsdk.util.b.k(this.a, messageV3.getPackageName());
                }
            }
            DebugLogger.i("AbstractPushNotification", "current package " + messageV3.getPackageName() + " notificationId=" + abs + " taskId=" + messageV3.getTaskId());
        }
        if (messageV3.getAdvertisementOption() != null && !TextUtils.isEmpty(messageV3.getAdvertisementOption().getAdPackage())) {
            f(abs, n2, messageV3);
        }
        this.f15992c.notify(abs, b);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0043 A[Catch: Exception -> 0x0048, TRY_LEAVE, TryCatch #0 {Exception -> 0x0048, blocks: (B:24:0x0000, B:26:0x000f, B:29:0x0014, B:31:0x0018, B:35:0x0043, B:33:0x003f), top: B:40:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Bitmap d(android.content.Context r7, java.lang.String r8) {
        /*
            r6 = this;
            android.content.pm.PackageManager r0 = r7.getPackageManager()     // Catch: java.lang.Exception -> L48
            android.graphics.drawable.Drawable r8 = r0.getApplicationIcon(r8)     // Catch: java.lang.Exception -> L48
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L48
            r1 = 26
            r2 = 0
            if (r0 < r1) goto L3f
            boolean r0 = r8 instanceof android.graphics.drawable.BitmapDrawable     // Catch: java.lang.Exception -> L48
            if (r0 == 0) goto L14
            goto L3f
        L14:
            boolean r0 = r8 instanceof android.graphics.drawable.AdaptiveIconDrawable     // Catch: java.lang.Exception -> L48
            if (r0 == 0) goto L3d
            int r0 = r8.getIntrinsicWidth()     // Catch: java.lang.Exception -> L48
            int r1 = r8.getIntrinsicHeight()     // Catch: java.lang.Exception -> L48
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_8888     // Catch: java.lang.Exception -> L48
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r0, r1, r3)     // Catch: java.lang.Exception -> L48
            android.graphics.Canvas r1 = new android.graphics.Canvas     // Catch: java.lang.Exception -> L48
            r1.<init>(r0)     // Catch: java.lang.Exception -> L48
            int r3 = r1.getWidth()     // Catch: java.lang.Exception -> L48
            int r4 = r1.getHeight()     // Catch: java.lang.Exception -> L48
            r5 = 0
            r8.setBounds(r5, r5, r3, r4)     // Catch: java.lang.Exception -> L48
            r8.draw(r1)     // Catch: java.lang.Exception -> L48
            r8 = r2
            r2 = r0
            goto L41
        L3d:
            r8 = r2
            goto L41
        L3f:
            android.graphics.drawable.BitmapDrawable r8 = (android.graphics.drawable.BitmapDrawable) r8     // Catch: java.lang.Exception -> L48
        L41:
            if (r2 != 0) goto L75
            android.graphics.Bitmap r2 = r8.getBitmap()     // Catch: java.lang.Exception -> L48
            goto L75
        L48:
            r8 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "get app icon error "
            r0.append(r1)
            java.lang.String r8 = r8.getMessage()
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "AbstractPushNotification"
            com.meizu.cloud.pushinternal.DebugLogger.i(r0, r8)
            android.content.pm.ApplicationInfo r8 = r7.getApplicationInfo()
            android.content.pm.PackageManager r7 = r7.getPackageManager()
            android.graphics.drawable.Drawable r7 = r8.loadIcon(r7)
            android.graphics.drawable.BitmapDrawable r7 = (android.graphics.drawable.BitmapDrawable) r7
            android.graphics.Bitmap r2 = r7.getBitmap()
        L75:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.notification.a.d(android.content.Context, java.lang.String):android.graphics.Bitmap");
    }

    public Bitmap e(String str) {
        com.meizu.cloud.pushsdk.e.b.c b = com.meizu.cloud.pushsdk.e.a.b(str).c().b();
        if (!b.f() || b.e() == null) {
            DebugLogger.i("AbstractPushNotification", "ANRequest On other Thread down load largeIcon " + str + "image fail");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ANRequest On other Thread down load largeIcon ");
        sb.append(str);
        sb.append("image ");
        sb.append(b.e() != null ? "success" : "fail");
        DebugLogger.i("AbstractPushNotification", sb.toString());
        return (Bitmap) b.e();
    }

    public void i(Notification notification, MessageV3 messageV3) {
    }

    protected void j(Notification notification, MessageV3 messageV3, PendingIntent pendingIntent) {
    }

    public boolean k() {
        return Thread.currentThread() == this.a.getMainLooper().getThread();
    }

    protected void o(Notification.Builder builder, MessageV3 messageV3) {
    }

    protected void q(Notification.Builder builder, MessageV3 messageV3) {
    }

    protected String x(MessageV3 messageV3) {
        String str;
        try {
        } catch (Exception e2) {
            DebugLogger.e("AbstractPushNotification", "parse flyme notification setting error " + e2.getMessage());
        }
        if (!TextUtils.isEmpty(messageV3.getNotificationMessage())) {
            str = new JSONObject(messageV3.getNotificationMessage()).getJSONObject("data").getJSONObject("extra").getString("fns");
            DebugLogger.i("AbstractPushNotification", "current FlymeGreen notification setting is " + str);
            return str;
        }
        str = null;
        DebugLogger.i("AbstractPushNotification", "current FlymeGreen notification setting is " + str);
        return str;
    }
}
