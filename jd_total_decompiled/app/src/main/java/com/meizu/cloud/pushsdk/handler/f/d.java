package com.meizu.cloud.pushsdk.handler.f;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.NotificationService;
import com.meizu.cloud.pushsdk.b;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.notification.model.styleenum.BaseStyleModel;
import com.meizu.cloud.pushsdk.notification.model.styleenum.InnerStyleLayout;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class d extends a<MessageV3> {
    public d(Context context, com.meizu.cloud.pushsdk.handler.a aVar) {
        super(context, aVar);
    }

    private boolean L(Context context, MessageV3 messageV3) {
        if (PushConstants.PUSH_PACKAGE_NAME.equalsIgnoreCase(context.getPackageName())) {
            boolean e2 = com.meizu.cloud.pushsdk.handler.f.h.a.a().e(context, messageV3.getUploadDataPackageName());
            DebugLogger.i("AbstractMessageHandler", "check message effective result: " + e2);
            if (e2) {
                return true;
            }
            com.meizu.cloud.pushsdk.util.d.u(context, context.getPackageName(), messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp());
            return false;
        }
        return true;
    }

    private String P(MessageV3 messageV3) {
        String selfDefineContentString = MzPushMessage.fromMessageV3(messageV3).getSelfDefineContentString();
        if (!TextUtils.isEmpty(selfDefineContentString)) {
            try {
                return new JSONObject(selfDefineContentString).getString("package_name");
            } catch (JSONException unused) {
                DebugLogger.e("AbstractMessageHandler", "no quick json message");
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: K  reason: merged with bridge method [inline-methods] */
    public void h(MessageV3 messageV3, com.meizu.cloud.pushsdk.notification.c cVar) {
        if (cVar != null) {
            cVar.a(messageV3);
            u(messageV3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: M  reason: merged with bridge method [inline-methods] */
    public boolean l(MessageV3 messageV3, String str) {
        return k(messageV3, str) && L(t(), messageV3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: N  reason: merged with bridge method [inline-methods] */
    public void g(MessageV3 messageV3) {
        Intent intent = new Intent(t(), NotificationService.class);
        intent.setPackage(messageV3.getPackageName());
        intent.addCategory(messageV3.getPackageName());
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        intent.putExtra("command_type", "reflect_receiver");
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        intent.putExtra(PushConstants.EXTRA_APP_PUSH_BRIGHT_NOTIFICATION_MESSAGE, messageV3);
        intent.putExtra("method", PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_BRIGHT_NOTIFICATION_MESSAGE);
        com.meizu.cloud.pushsdk.handler.f.l.a h2 = b.a(t()).h();
        if (h2 != null) {
            h2.d(intent, messageV3.getUploadDataPackageName());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: O  reason: merged with bridge method [inline-methods] */
    public boolean p(MessageV3 messageV3) {
        String uriPackageName = messageV3.getUriPackageName();
        if (!TextUtils.isEmpty(uriPackageName) && !MzSystemUtils.isPackageInstalled(t(), uriPackageName)) {
            DebugLogger.i("AbstractMessageHandler", "canSendMessage isPackageInstalled false");
            return false;
        } else if (Build.VERSION.SDK_INT < 31 || messageV3.getClickType() != 3) {
            return true;
        } else {
            DebugLogger.i("AbstractMessageHandler", "canSendMessage android 12 and clickType == 3 return false");
            com.meizu.cloud.pushsdk.util.d.w(t(), messageV3.getUploadDataPackageName(), messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: Q  reason: merged with bridge method [inline-methods] */
    public void v(MessageV3 messageV3) {
        com.meizu.cloud.pushsdk.util.d.x(t(), messageV3.getUploadDataPackageName(), messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp(), messageV3.getDelayedReportMillis());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: R  reason: merged with bridge method [inline-methods] */
    public void y(MessageV3 messageV3) {
        com.meizu.cloud.pushsdk.util.d.t(t(), messageV3.getUploadDataPackageName(), messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp(), messageV3.getDelayedReportMillis());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:37:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: S  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public com.meizu.cloud.pushsdk.notification.c z(MessageV3 messageV3) {
        com.meizu.cloud.pushsdk.notification.c cVar;
        PushNotificationBuilder pushNotificationBuilder = new PushNotificationBuilder(t());
        pushNotificationBuilder.setClickPackageName(P(messageV3));
        n().c(pushNotificationBuilder);
        if (messageV3.getNotificationStyle() != null) {
            int baseStyle = messageV3.getNotificationStyle().getBaseStyle();
            if (BaseStyleModel.FLYME.getCode() == baseStyle) {
                int innerStyle = messageV3.getNotificationStyle().getInnerStyle();
                if (InnerStyleLayout.EXPANDABLE_STANDARD.getCode() == innerStyle) {
                    DebugLogger.i("AbstractMessageHandler", "show Standard Notification with Expandable disable");
                    cVar = new com.meizu.cloud.pushsdk.notification.f.c(t(), pushNotificationBuilder);
                } else if (InnerStyleLayout.EXPANDABLE_TEXT.getCode() == innerStyle) {
                    DebugLogger.i("AbstractMessageHandler", "show Standard Notification with Expandable Text");
                    cVar = new com.meizu.cloud.pushsdk.notification.f.b(t(), pushNotificationBuilder);
                } else if (InnerStyleLayout.EXPANDABLE_PIC.getCode() == innerStyle) {
                    DebugLogger.i("AbstractMessageHandler", "show Standard Notification with Expandable Picture");
                    cVar = new com.meizu.cloud.pushsdk.notification.f.a(t(), pushNotificationBuilder);
                } else if (InnerStyleLayout.EXPANDABLE_VIDEO.getCode() == innerStyle) {
                    DebugLogger.i("AbstractMessageHandler", "show Flyme Video notification");
                    cVar = new com.meizu.cloud.pushsdk.notification.e.d(t(), pushNotificationBuilder);
                }
            } else if (BaseStyleModel.PURE_PICTURE.getCode() == baseStyle) {
                cVar = new com.meizu.cloud.pushsdk.notification.b(t(), pushNotificationBuilder);
                DebugLogger.i("AbstractMessageHandler", "show Pure Picture Notification");
            } else if (BaseStyleModel.ANDROID.getCode() == baseStyle) {
                int innerStyle2 = messageV3.getNotificationStyle().getInnerStyle();
                if (InnerStyleLayout.EXPANDABLE_STANDARD.getCode() == innerStyle2) {
                    DebugLogger.i("AbstractMessageHandler", "show Android  Notification with Expandable disable");
                    cVar = new com.meizu.cloud.pushsdk.notification.e.c(t(), pushNotificationBuilder);
                } else if (InnerStyleLayout.EXPANDABLE_TEXT.getCode() == innerStyle2) {
                    DebugLogger.i("AbstractMessageHandler", "show Android  Notification with Expandable Text");
                    cVar = new com.meizu.cloud.pushsdk.notification.e.b(t(), pushNotificationBuilder);
                } else if (InnerStyleLayout.EXPANDABLE_PIC.getCode() == innerStyle2) {
                    DebugLogger.i("AbstractMessageHandler", "show Android  Notification with Expandable Picture");
                    cVar = new com.meizu.cloud.pushsdk.notification.e.a(t(), pushNotificationBuilder);
                } else if (InnerStyleLayout.EXPANDABLE_VIDEO.getCode() == innerStyle2) {
                    DebugLogger.i("AbstractMessageHandler", "show Flyme Video notification");
                    cVar = new com.meizu.cloud.pushsdk.notification.e.d(t(), pushNotificationBuilder);
                }
            }
            if (cVar != null) {
                DebugLogger.e("AbstractMessageHandler", "use standard v2 notification");
                return new com.meizu.cloud.pushsdk.notification.d(t(), pushNotificationBuilder);
            }
            return cVar;
        }
        cVar = null;
        if (cVar != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: T  reason: merged with bridge method [inline-methods] */
    public int C(MessageV3 messageV3) {
        int i2;
        Context t;
        String taskId;
        String deviceId;
        int i3;
        String str;
        if (messageV3.getBrightRemindSetting() == null || !messageV3.getBrightRemindSetting().getIsBrightRemind() || MzSystemUtils.isInteractive(t())) {
            if (messageV3.getAdvertisementOption() != null && !TextUtils.isEmpty(messageV3.getAdvertisementOption().getAdInstallPackage())) {
                com.meizu.cloud.pushsdk.util.d.g(t(), messageV3.getAdvertisementOption().getAdInstallPackage(), messageV3.getDeviceId(), messageV3.getTaskId(), messageV3.getSeqId(), messageV3.getPushTimestamp());
                return 5;
            } else if (messageV3.getTimeDisplaySetting() == null || !messageV3.getTimeDisplaySetting().isTimeDisplay()) {
                return 0;
            } else {
                if (System.currentTimeMillis() > Long.valueOf(messageV3.getTimeDisplaySetting().getEndShowTime()).longValue()) {
                    i2 = 1;
                    t = t();
                    taskId = messageV3.getTaskId();
                    deviceId = messageV3.getDeviceId();
                    i3 = 2200;
                    str = "schedule notification expire";
                } else if (System.currentTimeMillis() > Long.valueOf(messageV3.getTimeDisplaySetting().getStartShowTime()).longValue()) {
                    i2 = 2;
                    t = t();
                    taskId = messageV3.getTaskId();
                    deviceId = messageV3.getDeviceId();
                    i3 = 2201;
                    str = "schedule notification on time";
                } else {
                    i2 = 3;
                    t = t();
                    taskId = messageV3.getTaskId();
                    deviceId = messageV3.getDeviceId();
                    i3 = 2202;
                    str = "schedule notification delay";
                }
                com.meizu.cloud.pushsdk.util.d.f(t, str, i3, taskId, deviceId);
                return i2;
            }
        }
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    @SuppressLint({"MissingPermission"})
    /* renamed from: U  reason: merged with bridge method [inline-methods] */
    public void F(MessageV3 messageV3) {
        AlarmManager alarmManager = (AlarmManager) t().getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(t(), NotificationService.class);
        intent.setPackage(messageV3.getPackageName());
        intent.addCategory(messageV3.getPackageName());
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        intent.putExtra("command_type", "reflect_receiver");
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        intent.putExtra(PushConstants.EXTRA_APP_PUSH_SCHEDULE_NOTIFICATION_MESSAGE, messageV3);
        intent.putExtra("method", PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_SCHEDULE_NOTIFICATION);
        PendingIntent service = PendingIntent.getService(t(), 0, intent, MinSdkChecker.isSupportSetDrawableSmallIcon() ? 67108864 : 1140850688);
        String startShowTime = messageV3.getTimeDisplaySetting().getStartShowTime();
        String format = TextUtils.isEmpty(startShowTime) ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.valueOf(startShowTime).longValue()));
        long longValue = Long.valueOf(startShowTime).longValue() - System.currentTimeMillis();
        DebugLogger.i("AbstractMessageHandler", "after " + (longValue / 1000) + " seconds Notification AlarmManager execute At " + format);
        if (alarmManager != null && Build.VERSION.SDK_INT >= 19) {
            DebugLogger.i("AbstractMessageHandler", "setAlarmManager setExact ELAPSED_REALTIME_WAKEUP");
            alarmManager.setExact(2, SystemClock.elapsedRealtime() + longValue, service);
        } else if (alarmManager != null) {
            DebugLogger.i("AbstractMessageHandler", "setAlarmManager set ELAPSED_REALTIME_WAKEUP");
            alarmManager.set(2, SystemClock.elapsedRealtime() + longValue, service);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.meizu.cloud.pushsdk.handler.f.a
    /* renamed from: V  reason: merged with bridge method [inline-methods] */
    public MessageV3 D(Intent intent) {
        return MessageV3.parse(t().getPackageName(), E(intent), G(intent), x(intent), I(intent), H(intent), intent.getStringExtra(PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_SHOW_V3.equals(B(intent)) ? PushConstants.MZ_PUSH_PRIVATE_MESSAGE : "message"), J(intent), s(intent));
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public int a() {
        return 4;
    }

    @Override // com.meizu.cloud.pushsdk.handler.c
    public boolean b(Intent intent) {
        DebugLogger.i("AbstractMessageHandler", "start MessageV3Handler match");
        if (i(0, E(intent))) {
            if (PushConstants.MZ_PUSH_ON_MESSAGE_ACTION.equals(intent.getAction()) && PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_SHOW_V3.equals(B(intent))) {
                return true;
            }
            if (TextUtils.isEmpty(B(intent))) {
                String stringExtra = intent.getStringExtra("message");
                if (!TextUtils.isEmpty(stringExtra) && q(stringExtra)) {
                    DebugLogger.e("AbstractMessageHandler", "old cloud notification message");
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
