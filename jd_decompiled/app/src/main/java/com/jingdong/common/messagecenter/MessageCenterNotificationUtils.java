package com.jingdong.common.messagecenter;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import com.google.gson.Gson;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.TypeToken;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.R;
import com.jingdong.common.jdreactFramework.SpecialMtaConstants;
import com.jingdong.common.utils.inter.JDInternationalUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jd.wjlogin_sdk.util.f;

/* loaded from: classes5.dex */
public class MessageCenterNotificationUtils {
    private static final String Channal_ID = "618";
    public static final String MESSAGE_NOTIFICATION_IDS = "message_notification_id_list";
    public static final int PUSH_MESSAGE_NOTIFY_ID = 65623;
    public static NotificationMessageSummary Summary = null;
    public static final String TAG = "MessageCenterNotificationUtils";
    private static NotificationManager mNotificationManager;

    public static void clearAllDDNotices(Context context) {
        if (RomUtil.getDevice() != 0) {
            return;
        }
        Map map = (Map) new Gson().fromJson(SharedPreferencesUtil.getString(MESSAGE_NOTIFICATION_IDS, ""), new TypeToken<Map<String, List<String>>>() { // from class: com.jingdong.common.messagecenter.MessageCenterNotificationUtils.3
        }.getType());
        if (map == null) {
            return;
        }
        Iterator it = map.entrySet().iterator();
        NotificationManager notificationManager = getNotificationManager(context);
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (((String) entry.getKey()).startsWith("d")) {
                Iterator it2 = ((List) entry.getValue()).iterator();
                while (it2.hasNext()) {
                    try {
                        notificationManager.cancel(Integer.parseInt((String) it2.next()));
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e(TAG, e2);
                        }
                    }
                }
                it.remove();
            }
        }
        SharedPreferencesUtil.putString(MESSAGE_NOTIFICATION_IDS, new Gson().toJson(map));
    }

    public static void clearAllNotices(Context context) {
        try {
            if (RomUtil.getDevice() == 1 || RomUtil.getDevice() == 6) {
                MessageCommonUtils.clearPushNotices();
            }
            getNotificationManager(context).cancelAll();
            SharedPreferencesUtil.putString(MESSAGE_NOTIFICATION_IDS, "");
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    @Deprecated
    public static void clearBadge(Context context) {
        try {
            if (RomUtil.getDevice() == 2) {
                MessageCommonUtils.clearBadge(context);
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            clearHuaWeiBadge(context);
        }
    }

    public static void clearDDNotices(Context context, String str) {
        clearNotices(context, "d" + str);
    }

    public static void clearHuaWeiBadge(Context context) {
        if (context == null) {
            return;
        }
        try {
            if (RomUtil.getDevice() == 2) {
                Bundle bundle = new Bundle();
                bundle.putString("package", f.f19954c);
                bundle.putString("class", Constants.MAINACTIVITY_FULLNAME);
                bundle.putInt("badgenumber", 0);
                context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", (String) null, bundle);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void clearNotices(Context context, String str) {
        if (RomUtil.getDevice() != 0) {
            return;
        }
        Map map = (Map) new Gson().fromJson(SharedPreferencesUtil.getString(MESSAGE_NOTIFICATION_IDS, ""), new TypeToken<Map<String, List<String>>>() { // from class: com.jingdong.common.messagecenter.MessageCenterNotificationUtils.2
        }.getType());
        if (map == null) {
            return;
        }
        if (map.containsKey(str)) {
            List list = (List) map.get(str);
            NotificationManager notificationManager = getNotificationManager(context);
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    try {
                        notificationManager.cancel(Integer.parseInt((String) it.next()));
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e(TAG, e2);
                        }
                    }
                }
            }
            map.remove(str);
        }
        SharedPreferencesUtil.putString(MESSAGE_NOTIFICATION_IDS, new Gson().toJson(map));
    }

    private static Bundle getDisallowBundle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("disallow.ticwear", true);
        return bundle;
    }

    private static NotificationManager getNotificationManager(Context context) {
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
            if (Build.VERSION.SDK_INT >= 26) {
                mNotificationManager.createNotificationChannel(new NotificationChannel(Channal_ID, "\u4eac\u4e1c\u901a\u77e5", 3));
            }
        }
        return mNotificationManager;
    }

    private static boolean isShowJDNoti() {
        boolean z;
        try {
            String deviceManufacture = BaseInfo.getDeviceManufacture();
            if (!deviceManufacture.equalsIgnoreCase("OPPO")) {
                if (!deviceManufacture.equalsIgnoreCase("VIVO")) {
                    z = false;
                    return true ^ z;
                }
            }
            z = true;
            return true ^ z;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return true;
        }
    }

    @TargetApi(16)
    public static void notTakeNoticeOfPictures(NotificationMessageSummary notificationMessageSummary, boolean z, int i2) {
        String str;
        if (JDInternationalUtil.isInInterSite()) {
            return;
        }
        Application application = JdSdk.getInstance().getApplication();
        Summary = notificationMessageSummary;
        if (notificationMessageSummary != null) {
            Bitmap bitmap = ((BitmapDrawable) application.getResources().getDrawable(R.drawable.jd_push)).getBitmap();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(application, Channal_ID);
            if (!TextUtils.isEmpty(notificationMessageSummary.watchCategoryId) && "order".equals(notificationMessageSummary.watchCategoryId)) {
                builder.setExtras(getDisallowBundle());
            }
            builder.setSmallIcon(R.drawable.jd_push_icon).setLargeIcon(bitmap).setContentTitle(notificationMessageSummary.title).setContentText(notificationMessageSummary.alert).setPriority(2).setStyle(new NotificationCompat.BigTextStyle().bigText(notificationMessageSummary.alert)).setWhen(System.currentTimeMillis());
            Notification build = builder.build();
            int i3 = build.flags | 16;
            build.flags = i3;
            build.defaults |= 4;
            build.ledARGB = -1712259328;
            build.ledOnMS = 300;
            build.ledOffMS = 2000;
            build.flags = i3 | 1;
            int ringerMode = ((AudioManager) application.getSystemService("audio")).getRingerMode();
            if (z && ringerMode != 0 && ringerMode != 1) {
                if (TextUtils.isEmpty(Summary.soundSource)) {
                    build.defaults |= 1;
                } else {
                    build.sound = Uri.parse("android.resource://" + application.getPackageName() + "/raw/" + Summary.soundSource.split("\\.")[0]);
                }
            }
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(application, "com.jingdong.app.mall.open.MessageNotificationActivity"));
            intent.putExtra("messageFlag", i2);
            intent.putExtra("summary", JDJSON.toJSONString(Summary));
            intent.addFlags(335544320);
            build.contentIntent = PendingIntent.getActivity(application, ((int) (Math.random() * 1000.0d)) + 1, intent, Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728);
            try {
                String str2 = notificationMessageSummary.accountType;
                if (str2 != null) {
                    if (str2.equals(SpecialMtaConstants.JDReact_ModuleUpgradeSuccessfully)) {
                        String str3 = notificationMessageSummary.venderId;
                        if (TextUtils.isEmpty(str3)) {
                            return;
                        }
                        str = "d" + str3;
                    } else {
                        str = notificationMessageSummary.accountType;
                    }
                    int currentTimeMillis = (int) System.currentTimeMillis();
                    saveNoticesId(str, String.valueOf(currentTimeMillis));
                    getNotificationManager(application).notify(currentTimeMillis, build);
                    return;
                }
                getNotificationManager(application).notify(PUSH_MESSAGE_NOTIFY_ID, build);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
    }

    public static void removeNotify(Context context, int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, "removeNotify type:" + i2);
        }
        try {
            getNotificationManager(context).cancel(i2);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void saveNoticesId(String str, String str2) {
        String string = SharedPreferencesUtil.getString(MESSAGE_NOTIFICATION_IDS, "");
        Map hashMap = new HashMap();
        if (TextUtils.isEmpty(string)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str2);
            hashMap.put(str, arrayList);
        } else {
            hashMap = (Map) new Gson().fromJson(string, new TypeToken<Map<String, List<String>>>() { // from class: com.jingdong.common.messagecenter.MessageCenterNotificationUtils.1
            }.getType());
            if (hashMap != null) {
                List arrayList2 = new ArrayList();
                if (hashMap.containsKey(str)) {
                    arrayList2 = (List) hashMap.get(str);
                }
                arrayList2.add(str2);
                hashMap.put(str, arrayList2);
            }
        }
        SharedPreferencesUtil.putString(MESSAGE_NOTIFICATION_IDS, new Gson().toJson(hashMap));
    }

    @TargetApi(16)
    public static void takeNoticeOfPictures(NotificationMessageSummary notificationMessageSummary, boolean z, Bitmap bitmap, int i2) {
        String str;
        if (JDInternationalUtil.isInInterSite()) {
            return;
        }
        Application application = JdSdk.getInstance().getApplication();
        Summary = notificationMessageSummary;
        if (notificationMessageSummary != null) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(application, Channal_ID);
            if (!TextUtils.isEmpty(notificationMessageSummary.watchCategoryId) && "order".equals(notificationMessageSummary.watchCategoryId)) {
                builder.setExtras(getDisallowBundle());
            }
            builder.setSmallIcon(R.drawable.jd_push_icon).setContentText(notificationMessageSummary.alert).setContentTitle(notificationMessageSummary.title).setPriority(2);
            if (useBigPictureStyle()) {
                Bitmap bitmap2 = ((BitmapDrawable) application.getResources().getDrawable(R.drawable.jd_push)).getBitmap();
                builder.setWhen(System.currentTimeMillis()).setStyle(new NotificationCompat.BigPictureStyle().setBigContentTitle(notificationMessageSummary.title).setSummaryText(notificationMessageSummary.alert).bigLargeIcon(bitmap2).bigPicture(bitmap)).setLargeIcon(bitmap2);
            } else {
                builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                Date date = new Date(System.currentTimeMillis());
                RemoteViews remoteViews = new RemoteViews(application.getPackageName(), R.layout.app_push_notification_view);
                int i3 = R.id.notification_title;
                remoteViews.setTextViewText(i3, notificationMessageSummary.title);
                int i4 = R.id.notification_content;
                remoteViews.setTextViewText(i4, notificationMessageSummary.alert);
                int i5 = R.id.notification_time;
                remoteViews.setTextViewText(i5, simpleDateFormat.format(date));
                builder.setCustomContentView(remoteViews);
                RemoteViews remoteViews2 = new RemoteViews(application.getPackageName(), R.layout.app_push_notification_bigview);
                remoteViews2.setTextViewText(i3, notificationMessageSummary.title);
                remoteViews2.setTextViewText(i4, notificationMessageSummary.alert);
                remoteViews2.setTextViewText(i5, simpleDateFormat.format(date));
                remoteViews2.setImageViewBitmap(R.id.notification_bigview, bitmap);
                builder.setCustomBigContentView(remoteViews2);
            }
            Notification build = builder.build();
            int i6 = build.flags | 16;
            build.flags = i6;
            build.defaults |= 4;
            build.ledARGB = -1712259328;
            build.ledOnMS = 300;
            build.ledOffMS = 2000;
            build.flags = i6 | 1;
            int ringerMode = ((AudioManager) application.getSystemService("audio")).getRingerMode();
            if (z && ringerMode != 0 && ringerMode != 1) {
                if (TextUtils.isEmpty(Summary.soundSource)) {
                    build.defaults |= 1;
                } else {
                    build.sound = Uri.parse("android.resource://" + application.getPackageName() + "/raw/" + Summary.soundSource.split("\\.")[0]);
                }
            }
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(application, "com.jingdong.app.mall.open.MessageNotificationActivity"));
            intent.putExtra("messageFlag", i2);
            intent.putExtra("summary", JDJSON.toJSONString(Summary));
            intent.addFlags(335544320);
            build.contentIntent = PendingIntent.getActivity(application, ((int) (Math.random() * 1000.0d)) + 1, intent, Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728);
            try {
                String str2 = notificationMessageSummary.accountType;
                if (str2 != null) {
                    if (str2.equals(SpecialMtaConstants.JDReact_ModuleUpgradeSuccessfully)) {
                        String str3 = notificationMessageSummary.venderId;
                        if (TextUtils.isEmpty(str3)) {
                            return;
                        }
                        str = "d" + str3;
                    } else {
                        str = notificationMessageSummary.accountType;
                    }
                    int currentTimeMillis = (int) System.currentTimeMillis();
                    saveNoticesId(str, String.valueOf(currentTimeMillis));
                    getNotificationManager(application).notify(currentTimeMillis, build);
                    return;
                }
                getNotificationManager(application).notify(PUSH_MESSAGE_NOTIFY_ID, build);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
    }

    @TargetApi(16)
    public static void takeNoticeOfPictures2(NotificationMessageSummary notificationMessageSummary, boolean z, Bitmap bitmap, int i2) {
        NotificationCompat.Builder builder;
        Notification build;
        String str;
        if (JDInternationalUtil.isInInterSite()) {
            return;
        }
        Application application = JdSdk.getInstance().getApplication();
        Summary = notificationMessageSummary;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        if (notificationMessageSummary != null) {
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 26) {
                builder = new NotificationCompat.Builder(application, Channal_ID);
            } else {
                builder = new NotificationCompat.Builder(application);
            }
            if (!TextUtils.isEmpty(notificationMessageSummary.watchCategoryId) && "order".equals(notificationMessageSummary.watchCategoryId)) {
                builder.setExtras(getDisallowBundle());
            }
            if (i3 >= 20) {
                build = builder.setContentText(notificationMessageSummary.alert).setContentTitle(notificationMessageSummary.title).setSmallIcon(R.drawable.jd_push_icon).setPriority(2).build();
            } else {
                build = builder.setContentText(notificationMessageSummary.alert).setContentTitle(notificationMessageSummary.title).setSmallIcon(R.drawable.jd_push).setPriority(2).build();
            }
            if (isShowJDNoti()) {
                RemoteViews remoteViews = new RemoteViews(application.getPackageName(), R.layout.app_push_notification_bigview);
                int i4 = R.id.notification_title;
                remoteViews.setTextViewText(i4, notificationMessageSummary.title);
                int i5 = R.id.notification_content;
                remoteViews.setTextViewText(i5, notificationMessageSummary.alert);
                int i6 = R.id.notification_time;
                remoteViews.setTextViewText(i6, simpleDateFormat.format(date));
                remoteViews.setImageViewBitmap(R.id.notification_bigview, bitmap);
                RemoteViews remoteViews2 = new RemoteViews(application.getPackageName(), R.layout.app_push_notification_view);
                remoteViews2.setTextViewText(i4, notificationMessageSummary.title);
                remoteViews2.setTextViewText(i5, notificationMessageSummary.alert);
                remoteViews2.setTextViewText(i6, simpleDateFormat.format(date));
                build.contentView = remoteViews2;
                build.bigContentView = remoteViews;
            }
            int i7 = build.flags | 16;
            build.flags = i7;
            build.defaults |= 4;
            build.ledARGB = -1712259328;
            build.ledOnMS = 300;
            build.ledOffMS = 2000;
            build.flags = i7 | 1;
            int ringerMode = ((AudioManager) application.getSystemService("audio")).getRingerMode();
            if (z && ringerMode != 0 && ringerMode != 1) {
                if (TextUtils.isEmpty(Summary.soundSource)) {
                    build.defaults |= 1;
                } else {
                    build.sound = Uri.parse("android.resource://" + application.getPackageName() + "/raw/" + Summary.soundSource.split("\\.")[0]);
                }
            }
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(application, "com.jingdong.app.mall.open.MessageNotificationActivity"));
            intent.putExtra("messageFlag", i2);
            intent.putExtra("summary", JDJSON.toJSONString(Summary));
            intent.addFlags(335544320);
            build.contentIntent = PendingIntent.getActivity(application, ((int) (Math.random() * 1000.0d)) + 1, intent, i3 >= 31 ? 167772160 : 134217728);
            try {
                String str2 = notificationMessageSummary.accountType;
                if (str2 != null) {
                    if (str2.equals(SpecialMtaConstants.JDReact_ModuleUpgradeSuccessfully)) {
                        String str3 = notificationMessageSummary.venderId;
                        if (TextUtils.isEmpty(str3)) {
                            return;
                        }
                        str = "d" + str3;
                    } else {
                        str = notificationMessageSummary.accountType;
                    }
                    int currentTimeMillis = (int) System.currentTimeMillis();
                    saveNoticesId(str, String.valueOf(currentTimeMillis));
                    getNotificationManager(application).notify(currentTimeMillis, build);
                    return;
                }
                getNotificationManager(application).notify(PUSH_MESSAGE_NOTIFY_ID, build);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
    }

    private static boolean useBigPictureStyle() {
        String config = JDMobileConfig.getInstance().getConfig("JDPush", "switch", "useBigPictureStyle", "1");
        if (OKLog.D) {
            OKLog.d("JDPush.notice", "useBigPictureStyle:" + config);
        }
        return TextUtils.equals(config, "1");
    }
}
