package g.d.a.f;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import com.heytap.msp.push.notification.PushNotification;
import com.huawei.hms.push.constant.RemoteMessageConst;

/* loaded from: classes12.dex */
public class a {
    public static Notification a(Context context, String str, PushNotification.Builder builder) {
        Notification.Builder builder2 = new Notification.Builder(context);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            builder2.setChannelId("Heytap PUSH");
        }
        if (i2 >= 20) {
            builder2.setGroup(str);
            builder2.setGroupSummary(true);
        }
        if (e(builder2, builder)) {
            return builder2.build();
        }
        return null;
    }

    public static StatusBarNotification[] b(NotificationManager notificationManager, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return notificationManager.getActiveNotifications();
        }
        return null;
    }

    public static NotificationManager c(Context context) {
        if (context != null) {
            try {
                return (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public static boolean d(NotificationManager notificationManager, String str, int i2) {
        StatusBarNotification[] b = b(notificationManager, str);
        if (b != null && b.length != 0) {
            for (StatusBarNotification statusBarNotification : b) {
                if (statusBarNotification.getId() == i2) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean e(Notification.Builder builder, PushNotification.Builder builder2) {
        int iconRes = builder2.getIconRes();
        int iconLevel = builder2.getIconLevel();
        Icon icon = builder2.getIcon();
        if (icon != null && Build.VERSION.SDK_INT >= 23) {
            builder.setSmallIcon(icon);
            return true;
        } else if (iconRes != 0 && iconLevel != 0) {
            builder.setSmallIcon(iconRes, iconLevel);
            return true;
        } else if (iconRes != 0) {
            builder.setSmallIcon(iconRes);
            return true;
        } else {
            return false;
        }
    }
}
