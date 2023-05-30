package com.jingdong.app.mall.localreminder;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import com.jingdong.app.mall.utils.q;
import com.jingdong.common.utils.JDReminderNewUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.Constants;
import java.util.Map;

/* loaded from: classes4.dex */
public class ReminderManager {
    private static final String TAG = "HHH_ReminderManager";
    private boolean isInitial;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Runnable {
        a(ReminderManager reminderManager) {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (Log.D) {
                    Log.d(ReminderManager.TAG, " -->> start new thread for ReminderManager.");
                }
                JDReminderNewUtils.compatibleWithOldData();
                Application application = JdSdk.getInstance().getApplication();
                ArrayMap<Long, Integer> remindersNotificationTimeAndRequestCodeAfterTime = JDReminderNewUtils.getRemindersNotificationTimeAndRequestCodeAfterTime(System.currentTimeMillis());
                if (remindersNotificationTimeAndRequestCodeAfterTime != null && remindersNotificationTimeAndRequestCodeAfterTime.size() > 0) {
                    if (Log.D) {
                        Log.d(ReminderManager.TAG, "allRemainReminders size: " + remindersNotificationTimeAndRequestCodeAfterTime.size());
                    }
                    for (Map.Entry<Long, Integer> entry : remindersNotificationTimeAndRequestCodeAfterTime.entrySet()) {
                        long longValue = entry.getKey().longValue();
                        int intValue = entry.getValue().intValue();
                        Intent intent = new Intent();
                        intent.setAction(Constants.JDREMINDER_RECEIVER_ACTION_NAME);
                        intent.setFlags(32);
                        intent.putExtra("notificationTime", longValue);
                        intent.putExtra("requestCode", intValue);
                        int i2 = Build.VERSION.SDK_INT;
                        PendingIntent broadcast = PendingIntent.getBroadcast(application, intValue, intent, i2 >= 31 ? 67108864 : 0);
                        AlarmManager alarmManager = (AlarmManager) application.getSystemService(NotificationCompat.CATEGORY_ALARM);
                        if (i2 >= 19) {
                            alarmManager.setExact(0, longValue, broadcast);
                        } else {
                            alarmManager.set(0, longValue, broadcast);
                        }
                        if (Log.D) {
                            Log.d(ReminderManager.TAG, " -->> \u5df2\u5f00\u542f\u65b0\u7684\u8be5\u65f6\u95f4\u7684\u8ba1\u65f6\u5668, notificationTime: " + longValue);
                        }
                    }
                } else if (Log.D) {
                    Log.d(ReminderManager.TAG, "\u6ca1\u6709\u672a\u8fc7\u671f\u7684\u6d3b\u52a8\uff0c\u4e0d\u7528\u91cd\u65b0\u8bbe\u7f6e\u8ba1\u65f6\u5668");
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class b {
        private static final ReminderManager a = new ReminderManager(null);
    }

    /* synthetic */ ReminderManager(a aVar) {
        this();
    }

    public static final ReminderManager getInstance() {
        return b.a;
    }

    public synchronized void startAlarmReminder() {
        if (this.isInitial) {
            return;
        }
        this.isInitial = true;
        q.c().d(new a(this));
    }

    private ReminderManager() {
        this.isInitial = false;
    }
}
