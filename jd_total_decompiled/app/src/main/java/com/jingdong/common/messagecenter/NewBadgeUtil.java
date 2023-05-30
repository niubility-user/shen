package com.jingdong.common.messagecenter;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.R;
import com.jingdong.common.permission.rom.RomUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes.dex */
public class NewBadgeUtil {
    public static final int DEVICE_EMUI = 2;
    public static final int DEVICE_FLYME = 4;
    public static final int DEVICE_HONOR = 3;
    public static final int DEVICE_MIUI = 1;
    public static final int DEVICE_OPPO = 5;
    public static final int DEVICE_ORTHER = 0;
    public static final int DEVICE_SAMSUNG = 7;
    public static final int DEVICE_VIVO = 6;
    public static final int FLAG_RECEIVER_INCLUDE_BACKGROUND = 16777216;
    private static final String TAG = "NewBadgeUtil";
    public static String className = "com.jingdong.app.mall.main.MainActivity";
    public static int device = -1;
    public static String packageName = "com.jingdong.app.mall";

    public static void clearBadge() {
        try {
            int deviceBrand = getDeviceBrand();
            if (deviceBrand == 2) {
                setHuaWeiBadge(JdSdk.getInstance().getApplication(), 0);
            } else if (deviceBrand == 3) {
                MessageCommonUtils.clearBadge(JdSdk.getInstance().getApplication());
            } else if (deviceBrand == 6) {
                setViVoBadge(JdSdk.getInstance().getApplication(), 0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void clearPushBadge() {
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "clearPushBadge");
            }
            if (!BadgeCacheDataUtils.getBadgeABSwitch()) {
                MessageCommonUtils.clearBadge(JdSdk.getInstance().getApplication());
            } else if (!BadgeCacheDataUtils.getHuaWeiBadgeABSwitch() && 2 == getDeviceBrand()) {
                MessageCommonUtils.clearBadge(JdSdk.getInstance().getApplication());
            } else if (!BadgeCacheDataUtils.getHonorBadgeABSwitch() && 3 == getDeviceBrand()) {
                MessageCommonUtils.clearBadge(JdSdk.getInstance().getApplication());
            } else if (OKLog.D) {
                OKLog.d(TAG, "No clearPushBadge");
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    private static int getDeviceBrand() {
        if (device == -1) {
            if (isHonor()) {
                device = 3;
            } else if (isHuawei()) {
                device = 2;
            } else if (RomUtils.checkIsMiuiRom()) {
                device = 1;
            } else if (isSamsung()) {
                device = 7;
            } else if (RomUtil.isVIVO()) {
                device = 6;
            } else if (isGetPushDeviceFail()) {
                device = -1;
            } else {
                device = 0;
            }
            if (OKLog.D) {
                OKLog.d(TAG, String.format("getDeviceBrand:%s", Integer.valueOf(device)));
            }
        }
        return device;
    }

    private static String getLauncherClassName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setPackage(context.getPackageName());
        intent.addCategory("android.intent.category.LAUNCHER");
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
        if (resolveActivity == null) {
            resolveActivity = packageManager.resolveActivity(intent, 0);
        }
        return resolveActivity.activityInfo.name;
    }

    private static boolean isGetPushDeviceFail() {
        return MessageCommonUtils.getDeviceChannel() == -1;
    }

    private static boolean isHonor() {
        return MessageCommonUtils.getDeviceChannel() == 12;
    }

    private static boolean isHuawei() {
        return MessageCommonUtils.getDeviceChannel() == 2;
    }

    private static boolean isSamsung() {
        return BaseInfo.getDeviceManufacture().toLowerCase().contains("samsung");
    }

    public static void setBadge(int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, String.format("setBadge:num=%s,abSwitch=%s,brand=%s", Integer.valueOf(i2), Boolean.valueOf(BadgeCacheDataUtils.getBadgeABSwitch()), Integer.valueOf(getDeviceBrand())));
        }
        if (BadgeCacheDataUtils.getBadgeABSwitch()) {
            if (i2 < 0) {
                i2 = 0;
            }
            int deviceBrand = getDeviceBrand();
            if (deviceBrand == 2) {
                if (BadgeCacheDataUtils.getHuaWeiBadgeABSwitch()) {
                    setHuaWeiBadge(JdSdk.getInstance().getApplication(), i2);
                    return;
                } else {
                    setHuaWeiBadge(JdSdk.getInstance().getApplication(), 0);
                    return;
                }
            } else if (deviceBrand != 3) {
                if (deviceBrand != 6) {
                    return;
                }
                if (BadgeCacheDataUtils.getVIVOBadgeABSwitch()) {
                    setViVoBadge(JdSdk.getInstance().getApplication(), i2);
                    return;
                } else {
                    setViVoBadge(JdSdk.getInstance().getApplication(), 0);
                    return;
                }
            } else {
                try {
                    if (BadgeCacheDataUtils.getHonorBadgeABSwitch()) {
                        MessageCommonUtils.setBadgeNum(JdSdk.getInstance().getApplication(), i2);
                    } else {
                        MessageCommonUtils.clearBadge(JdSdk.getInstance().getApplication());
                    }
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
        }
        clearBadge();
    }

    private static void setHuaWeiBadge(Context context, int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, String.format("setHuaWeiBadge:num=%s", Integer.valueOf(i2)));
        }
        if (context == null) {
            return;
        }
        try {
            Log.e(TAG, "\u8bbe\u7f6e\u534e\u4e3a\u89d2\u6807\u6570:" + i2);
            Bundle bundle = new Bundle();
            bundle.putString("package", packageName);
            bundle.putString("class", className);
            bundle.putInt("badgenumber", i2);
            context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", (String) null, bundle);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, "setHuaWeiBadge error:", e2);
            }
        }
    }

    public static boolean setNotificationBadge(Context context, int i2) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
        if (notificationManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("badge", "badge", 3);
            notificationChannel.setShowBadge(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Notification build = new NotificationCompat.Builder(context, "badge").setContentTitle("\u5e94\u7528\u89d2\u6807").setContentText("\u60a8\u6709" + i2 + "\u6761\u672a\u8bfb\u6d88\u606f").setSmallIcon(R.drawable.jd_push).setAutoCancel(true).setChannelId("badge").setNumber(i2).setBadgeIconType(1).build();
        try {
            Log.e(TAG, "\u8bbe\u7f6e\u5c0f\u7c73\u89d2\u6807\u6570:" + i2);
            Object obj = build.getClass().getDeclaredField("extraNotification").get(build);
            obj.getClass().getDeclaredMethod("setMessageCount", Integer.TYPE).invoke(obj, Integer.valueOf(i2));
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(TAG, "\u8bbe\u7f6e\u5c0f\u7c73\u89d2\u6807\u5f02\u5e381");
        }
        notificationManager.notify((int) System.currentTimeMillis(), build);
        return true;
    }

    private static void setSamsungBadge(Context context, int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, String.format("setSamsungBadge:num=%s", Integer.valueOf(i2)));
        }
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        try {
            Log.e(TAG, "\u8bbe\u7f6e\u4e09\u661f\u89d2\u6807\u6570:" + i2);
            Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            intent.putExtra("badge_count", i2);
            intent.putExtra("badge_count_package_name", context.getPackageName());
            intent.putExtra("badge_count_class_name", launcherClassName);
            context.sendBroadcast(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(TAG, "\u8bbe\u7f6e\u4e09\u661f\u89d2\u6807\u6570\u5f02\u5e38");
        }
    }

    @SuppressLint({"WrongConstant"})
    private static void setViVoBadge(Context context, int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, String.format("setViVoBadge:num=%s", Integer.valueOf(i2)));
        }
        if (context == null) {
            return;
        }
        try {
            Log.e(TAG, "\u8bbe\u7f6evivo\u624b\u673a\u89d2\u6807\u6570:" + i2);
            Intent intent = new Intent();
            intent.setAction("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
            intent.putExtra("packageName", packageName);
            intent.putExtra("className", className);
            intent.putExtra("notificationNum", i2);
            if (Build.VERSION.SDK_INT >= 26) {
                intent.addFlags(16777216);
            }
            context.sendBroadcast(intent);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, "setViVoBadge error:", e2);
            }
        }
    }

    private static void setXiaomiBadge(Context context, int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, String.format("setXiaomiBadge:num=%s", Integer.valueOf(i2)));
        }
        if (context == null) {
            return;
        }
        try {
            if (RomUtils.getMiuiVersion() > 6) {
                Log.e(TAG, "6.0\u4ee5\u4e0a\u7248\u672c\u8bbe\u7f6e\u5c0f\u7c73\u89d2\u6807\u6570:" + i2);
                Notification build = new Notification.Builder(context).setContentTitle("title").setContentText("text").setSmallIcon(R.drawable.jd_push_icon).build();
                Object obj = build.getClass().getDeclaredField("extraNotification").get(build);
                obj.getClass().getDeclaredMethod("setMessageCount", Integer.TYPE).invoke(obj, Integer.valueOf(i2));
                ((NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION)).notify();
                return;
            }
            Log.e(TAG, "6.0\u4ee5\u4e0b\u7248\u672c\u8bbe\u7f6e\u5c0f\u7c73\u89d2\u6807\u6570:" + i2);
            Intent intent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
            intent.putExtra("android.intent.extra.update_application_component_name", context.getPackageName() + "/" + getLauncherClassName(context));
            intent.putExtra("android.intent.extra.update_application_message_text", String.valueOf(i2 == 0 ? "" : Integer.valueOf(i2)));
            context.sendBroadcast(intent);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, "setXiaomiBadge error:", e2);
            }
        }
    }
}
