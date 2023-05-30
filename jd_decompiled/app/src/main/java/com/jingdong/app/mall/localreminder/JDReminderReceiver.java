package com.jingdong.app.mall.localreminder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.R;
import com.jingdong.common.entity.JDReminderNewEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.JDReminderNewUtils;
import com.jingdong.common.utils.inter.JDInternationalUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.res.StringUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class JDReminderReceiver extends BroadcastReceiver {
    private String a(ArrayList<JDReminderNewEntity> arrayList) {
        int i2 = 0;
        if (arrayList.size() == 1 && "CALENDAR_ACTIVITY_NEW".equals(arrayList.get(0).getBusinessType()) && !TextUtils.isEmpty(arrayList.get(0).getExtra())) {
            return arrayList.get(0).getExtra();
        }
        ArrayMap arrayMap = new ArrayMap();
        Iterator<JDReminderNewEntity> it = arrayList.iterator();
        while (it.hasNext()) {
            JDReminderNewEntity next = it.next();
            if (arrayMap.containsKey(next.getReminderShowTag())) {
                arrayMap.put(next.getReminderShowTag(), Integer.valueOf(((Integer) arrayMap.get(next.getReminderShowTag())).intValue() + 1));
            } else {
                arrayMap.put(next.getReminderShowTag(), 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\u60a8\u6709");
        int size = arrayMap.entrySet().size();
        for (Map.Entry entry : arrayMap.entrySet()) {
            i2++;
            sb.append(((Integer) entry.getValue()).intValue());
            sb.append("\u4e2a");
            sb.append((String) entry.getKey());
            sb.append("\u63d0\u9192");
            if (i2 < size) {
                sb.append("\u3001");
            }
        }
        sb.append("\uff0c\u5373\u5c06\u5f00\u59cb\uff0c\u5feb\u53bb\u770b\u770b\u5427\uff01");
        return sb.toString();
    }

    private void b(Context context, int i2, long j2, String str) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactMyCalendar\",\"appname\":\"JDReactMyCalendar\",\"ishidden\":true,\"param\":{\"defaultTabIndex\":1,\"transparentenable\":true}}"));
        intent.setPackage(context.getPackageName());
        int i3 = Build.VERSION.SDK_INT;
        PendingIntent activity = PendingIntent.getActivity(context, i2, intent, i3 >= 31 ? 201326592 : 134217728);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, JumpUtil.VALUE_DES_JDREMINDER);
        builder.setContentTitle(StringUtil.app_name).setTicker(str).setContentText(str).setContentIntent(activity).setAutoCancel(true).setDefaults(-1);
        if (i3 >= 20) {
            builder.setLargeIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.android_jd_notification)).getBitmap());
            builder.setSmallIcon(R.drawable.android_jd_notification_new);
        } else {
            builder.setSmallIcon(R.drawable.android_jd_notification);
        }
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText(str);
        builder.setStyle(bigTextStyle);
        Notification build = builder.build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
        if (i3 >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(JumpUtil.VALUE_DES_JDREMINDER, "\u63d0\u9192", 4));
        }
        notificationManager.notify(i2, build);
        JDMtaUtils.onClickWithPageId(context, "NotificationMessage_PushMessageBuild", "com.jd.lib.mycalendar.view.activity.MyCalendarActivity", "MyCalendar_Main");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (Log.D) {
            Log.d("HHH_JDReminderReceiver", " -->> onReceive()");
        }
        if (intent != null && JDPrivacyHelper.isAcceptPrivacy(context)) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action) && action.equals(Constants.JDREMINDER_RECEIVER_ACTION_NAME)) {
                if (Log.D) {
                    Log.d("HHH_JDReminderReceiver", " -->> \u63a5\u6536\u5230\u63d0\u9192\u5b9a\u65f6\u5e7f\u64ad");
                }
                if (JDInternationalUtil.isInInterSite()) {
                    if (Log.D) {
                        Log.d("HHH_JDReminderReceiver", " is international version\uff0cremove msg");
                        return;
                    }
                    return;
                }
                Bundle extras = intent.getExtras();
                if (extras == null) {
                    return;
                }
                long j2 = extras.getLong("notificationTime", 0L);
                int i2 = extras.getInt("requestCode", 0);
                if (j2 <= 0) {
                    return;
                }
                Calendar calendar = Calendar.getInstance();
                calendar.clear();
                calendar.setTimeInMillis(j2);
                String str = calendar.get(11) + ":" + calendar.get(12);
                if (Log.D) {
                    Log.d("HHH_JDReminderReceiver", "\u6536\u5230\u5e7f\u64adIntent, notificationTime:" + str + ", requestCode:" + i2);
                }
                ArrayList<JDReminderNewEntity> remindersAtNotificationTime = JDReminderNewUtils.getRemindersAtNotificationTime(j2);
                if (remindersAtNotificationTime != null && remindersAtNotificationTime.size() > 0) {
                    String a = a(remindersAtNotificationTime);
                    if (Log.D) {
                        Log.d("HHH_JDReminderReceiver", "\u8be5\u5f00\u59cb\u65f6\u95f4\u63d0\u9192\u6570\u91cf\uff1a" + remindersAtNotificationTime.size());
                        Log.d("HHH_JDReminderReceiver", "\u63d0\u9192\u6587\u6848\u4e3a\uff1a" + a);
                    }
                    b(context, i2, j2, a);
                } else if (Log.D) {
                    Log.d("HHH_JDReminderReceiver", "\u8be5\u5f00\u59cb\u65f6\u95f4\u6ca1\u6709\u63d0\u9192");
                }
            } else if (TextUtils.isEmpty(action) || !action.equals("android.intent.action.BOOT_COMPLETED")) {
            } else {
                if (Log.D) {
                    Log.d("HHH_JDReminderReceiver", " -->> \u63a5\u6536\u5230\u8bbe\u5907\u91cd\u542f\u5e7f\u64ad");
                }
                try {
                    ReminderManager.getInstance().startAlarmReminder();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }
}
