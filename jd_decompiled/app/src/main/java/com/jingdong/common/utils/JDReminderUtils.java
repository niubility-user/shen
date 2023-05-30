package com.jingdong.common.utils;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.jingdong.common.R;
import com.jingdong.common.database.table.JDReminderTable;
import com.jingdong.common.entity.JDReminder;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.constant.JDReminderConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.unionpay.tsmservice.mi.data.Constant;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import jd.wjlogin_sdk.util.f;

@Deprecated
/* loaded from: classes6.dex */
public class JDReminderUtils {
    public static final long REMINDER_DURATION_TIME = 180000;
    private static final String TAG = "JDReminderUtils";
    private static final AlarmManager alarmManager;
    private static final Context context;

    /* loaded from: classes6.dex */
    public enum Type {
        MIAOSHA,
        COUPON,
        SHOP,
        SHANGOU,
        PAIMAI,
        HUODONG,
        ZHIBO,
        MIAOSHAZHIBO,
        DUOBAODAO,
        BABEL,
        TRAINTICKET,
        AIRTICKET,
        PRODUCTSUBSCRIBE,
        MIAOSHANEW;

        public static Type getType(String str) {
            str.hashCode();
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case -1942355523:
                    if (str.equals("PAIMAI")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -1876099034:
                    if (str.equals("MIAOSHAZHIBO")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -1522883605:
                    if (str.equals("SHANGOU")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -1487508256:
                    if (str.equals("DUOBAODAO")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case -959181509:
                    if (str.equals("PRODUCTSUBSCRIBE")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case -909093706:
                    if (str.equals("AIRTICKET")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 2544374:
                    if (str.equals("SHOP")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 62954442:
                    if (str.equals(JDReminderConstant.BUSINESS_TYPE_BABEL)) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 85334120:
                    if (str.equals("ZHIBO")) {
                        c2 = '\b';
                        break;
                    }
                    break;
                case 109767870:
                    if (str.equals("MIAOSHANEW")) {
                        c2 = '\t';
                        break;
                    }
                    break;
                case 281561844:
                    if (str.equals("TRAINTICKET")) {
                        c2 = '\n';
                        break;
                    }
                    break;
                case 1770699138:
                    if (str.equals("MIAOSHA")) {
                        c2 = 11;
                        break;
                    }
                    break;
                case 1984295782:
                    if (str.equals("HUODONG")) {
                        c2 = '\f';
                        break;
                    }
                    break;
                case 1993722918:
                    if (str.equals("COUPON")) {
                        c2 = '\r';
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    return PAIMAI;
                case 1:
                    return ZHIBO;
                case 2:
                    return SHANGOU;
                case 3:
                    return DUOBAODAO;
                case 4:
                    return PRODUCTSUBSCRIBE;
                case 5:
                    return AIRTICKET;
                case 6:
                    return SHOP;
                case 7:
                    return BABEL;
                case '\b':
                    return ZHIBO;
                case '\t':
                    return MIAOSHANEW;
                case '\n':
                    return TRAINTICKET;
                case 11:
                    return MIAOSHA;
                case '\f':
                    return HUODONG;
                case '\r':
                    return COUPON;
                default:
                    return null;
            }
        }
    }

    static {
        Application application = JdSdk.getInstance().getApplication();
        context = application;
        alarmManager = (AlarmManager) application.getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    private static void cancelAlarm(long j2) {
        LinkedHashMap<Integer, Integer> remindersByStartTime = JDReminderTable.getRemindersByStartTime(j2);
        if (remindersByStartTime.size() - 1 == 0) {
            alarmManager.cancel(setPendingIntent(j2, remindersByStartTime.entrySet().iterator().next().getValue().intValue()));
            if (OKLog.D) {
                OKLog.d(TAG, "\u8ba1\u65f6\u5668\u5df2\u53d6\u6d88");
            }
        } else if (OKLog.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("\u8ba1\u65f6\u5668\u8fd8\u4e0d\u80fd\u53d6\u6d88\uff0c\u4ecd\u6709\u63d0\u9192\u6570\u91cf\u4e3a\uff1a");
            sb.append(remindersByStartTime.size() - 1);
            OKLog.d(TAG, sb.toString());
        }
    }

    public static boolean cancelReminder(Type type, long j2, long j3) {
        if (type == null) {
            if (OKLog.E) {
                OKLog.e(TAG, "\u4e1a\u52a1\u7c7b\u578b\u4e3a\u7a7a");
            }
            return false;
        }
        long alignedTime = getAlignedTime(j3);
        if (JDReminderTable.checkExistByTypeAndId(type.toString(), j2, alignedTime) == -1) {
            if (OKLog.D) {
                OKLog.d(TAG, "\u6570\u636e\u5e93\u4e2d\u65e0\u8be5\u63d0\u9192\uff0c\u4e0d\u7528\u53d6\u6d88");
            }
            return true;
        }
        cancelAlarm(alignedTime);
        try {
            JDReminderTable.deleteByTypeAndId(type.toString(), j2, alignedTime);
            return true;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return false;
        }
    }

    public static boolean checkReminder(Type type, long j2, long j3) {
        if (JDReminderTable.checkExistByTypeAndId(type.toString(), j2, getAlignedTime(j3)) == -1) {
            if (OKLog.D) {
                OKLog.d(TAG, "\u8be5\u63d0\u9192\u5728\u6570\u636e\u5e93\u4e2d\u4e0d\u5b58\u5728");
                return false;
            }
            return false;
        } else if (OKLog.D) {
            OKLog.d(TAG, "\u8be5\u63d0\u9192\u5728\u6570\u636e\u5e93\u4e2d\u5df2\u5b58\u5728");
            return true;
        } else {
            return true;
        }
    }

    public static void deleteRemindersBeforeTargetTime(long j2) {
        JDReminderTable.deleteRemindersBeforeTargetTime(getOneDayTimeMills(j2));
    }

    private static String displayTime(long j2) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j2));
    }

    public static long getAlignedTime(long j2) {
        long j3 = j2 % 600000;
        return j3 < 300000 ? j2 - j3 : (j2 - j3) + 600000;
    }

    public static Map<Long, Integer> getAllRemainReminders(long j2) {
        return JDReminderTable.getAllRemainReminders(j2);
    }

    public static ArrayList<Long> getAllReminderIdBasedOnType(Type type) {
        return JDReminderTable.getAllReminderIdBasedOnTypeAndTime(type.toString(), -1L);
    }

    public static ArrayList<Long> getAllReminderIdBasedOnTypeAndTime(Type type, long j2) {
        return JDReminderTable.getAllReminderIdBasedOnTypeAndTime(type.toString(), j2);
    }

    public static ArrayList<JDReminder> getAllRemindersAfterTargetTime(long j2) {
        return JDReminderTable.getAllRemindersAfterTargetTime(j2);
    }

    private static long getOneDayTimeMills(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(new Date(j2));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static Set<Long> getReminderDaysInOneMonth(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeInMillis(j2);
        int i2 = calendar.get(1);
        int i3 = calendar.get(2);
        calendar.clear();
        calendar.set(i2, i3, 1);
        long timeInMillis = calendar.getTimeInMillis();
        calendar.clear();
        calendar.set(i2, i3 + 1, 1);
        return JDReminderTable.getReminderDaysDuringTimePeriod(timeInMillis - 300000, calendar.getTimeInMillis() - 300000);
    }

    public static ArrayList<JDReminder> getRemindersAtStartTime(long j2) {
        long alignedTime = getAlignedTime(j2);
        return JDReminderTable.getRemindersDuringTimePeriod(alignedTime - 300000, alignedTime + 300000);
    }

    public static ArrayList<JDReminder> getRemindersOfOneDay(long j2) {
        long oneDayTimeMills = getOneDayTimeMills(j2);
        return JDReminderTable.getRemindersDuringTimePeriod(oneDayTimeMills - 300000, (oneDayTimeMills + 86400000) - 300000);
    }

    private static boolean isColorOS() {
        return "OPPO".equalsIgnoreCase(BaseInfo.getDeviceManufacture());
    }

    private static boolean isFirstLaunch() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(JumpUtil.VALUE_DES_JDREMINDER, 0);
        if (Boolean.valueOf(sharedPreferences.getBoolean("FIRSTREMINDER", true)).booleanValue()) {
            sharedPreferences.edit().putBoolean("FIRSTREMINDER", false).commit();
            return true;
        }
        return false;
    }

    public static boolean isHasReminderOfOneDay(long j2) {
        ArrayList<JDReminder> remindersOfOneDay = getRemindersOfOneDay(j2);
        return remindersOfOneDay != null && remindersOfOneDay.size() > 0;
    }

    private static boolean isMiui() {
        return Constant.DEVICE_XIAOMI.equalsIgnoreCase(BaseInfo.getDeviceManufacture());
    }

    private static PendingIntent setPendingIntent(long j2, int i2) {
        Intent intent = new Intent();
        intent.setAction(Constants.JDREMINDER_RECEIVER_ACTION_NAME);
        intent.setFlags(32);
        intent.setClassName(JdSdk.getInstance().getApplication() == null ? f.f19954c : JdSdk.getInstance().getApplication().getPackageName(), "com.jingdong.app.mall.localreminder.JDReminderReceiver");
        intent.putExtra("startTime", j2);
        intent.putExtra("requestCode", i2);
        return PendingIntent.getBroadcast(context, i2, intent, Build.VERSION.SDK_INT >= 31 ? 67108864 : 0);
    }

    public static boolean setReminder(Type type, long j2, String str, long j3) {
        return setReminder(type, j2, str, j3, "");
    }

    public static boolean setReminder(Type type, String str, long j2, String str2) {
        return setReminder(type, 0L, str, j2, str2);
    }

    public static boolean setReminder(Type type, long j2, String str, long j3, String str2) {
        long j4;
        int insertOrUpdate;
        if (isColorOS() && isFirstLaunch()) {
            Context context2 = context;
            Toast.makeText(context2, context2.getResources().getString(R.string.jdreminder_oppo_info), 1).show();
        }
        if (isMiui() && isFirstLaunch()) {
            Context context3 = context;
            Toast.makeText(context3, context3.getResources().getString(R.string.jdreminder_xiaomi_info), 1).show();
        }
        if (type == null) {
            if (OKLog.E) {
                OKLog.e(TAG, "\u4e1a\u52a1\u7c7b\u578b\u4e3a\u7a7a");
            }
            Context context4 = context;
            Toast.makeText(context4, context4.getResources().getString(R.string.jdreminder_error_info), 1).show();
            return false;
        }
        String str3 = (TextUtils.isEmpty(str) || str.trim().equals("")) ? "" : str;
        if (j3 <= 0) {
            Context context5 = context;
            Toast.makeText(context5, context5.getResources().getString(R.string.jdreminder_error_info), 1).show();
            return false;
        }
        long alignedTime = getAlignedTime(j3);
        if (OKLog.D) {
            OKLog.d(TAG, "\u5bf9\u9f50\u524d\u65f6\u95f4\u4e3a: " + j3 + ",\u5373: " + displayTime(j3));
            OKLog.d(TAG, "\u5bf9\u9f50\u540e\u65f6\u95f4\u4e3a: " + alignedTime + ",\u5373: " + displayTime(alignedTime));
        }
        try {
            LinkedHashMap<Integer, Integer> remindersByStartTime = JDReminderTable.getRemindersByStartTime(alignedTime);
            if (remindersByStartTime.size() > 0) {
                insertOrUpdate = remindersByStartTime.entrySet().iterator().next().getValue().intValue();
                j4 = alignedTime;
                JDReminderTable.insertOrUpdate(type.toString(), j2, str3, j3, 0L, System.currentTimeMillis(), insertOrUpdate, str2);
                if (OKLog.D) {
                    OKLog.d(TAG, "\u5df2\u5b58\u5728\u8be5\u65f6\u95f4\u7684\u8ba1\u65f6\u5668\uff0cstartTime(aligned)\uff1a" + displayTime(j4) + ", \u65b0\u8ba1\u65f6\u5668\u4f1a\u8986\u76d6\u65e7\u8ba1\u65f6\u5668");
                }
            } else {
                j4 = alignedTime;
                insertOrUpdate = JDReminderTable.insertOrUpdate(type.toString(), j2, str3, j3, 0L, System.currentTimeMillis(), -1, str2);
                if (insertOrUpdate == -1) {
                    Context context6 = context;
                    Toast.makeText(context6, context6.getResources().getString(R.string.jdreminder_error_info), 1).show();
                    return false;
                }
                JDReminderTable.updateByIdAndRequestCode(insertOrUpdate, insertOrUpdate);
            }
            long j5 = j4;
            PendingIntent pendingIntent = setPendingIntent(j5, insertOrUpdate);
            long j6 = j5 - 180000;
            if (OKLog.D) {
                OKLog.d(TAG, "notification time\uff1a" + displayTime(j6));
            }
            if (Build.VERSION.SDK_INT >= 19) {
                alarmManager.setExact(0, j6, pendingIntent);
            } else {
                alarmManager.set(0, j6, pendingIntent);
            }
            if (OKLog.D) {
                OKLog.d(TAG, "\u5df2\u5f00\u542f\u65b0\u8ba1\u65f6\u5668\uff0cstartTime(aligned)\uff1a" + displayTime(j5));
            }
            return true;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            Context context7 = context;
            Toast.makeText(context7, context7.getResources().getString(R.string.jdreminder_error_info), 1).show();
            return false;
        }
    }

    public static boolean checkReminder(Type type, long j2, String str) {
        if (JDReminderTable.checkExistByTypeAndURL(type.toString(), getAlignedTime(j2), str) == -1) {
            if (OKLog.D) {
                OKLog.d(TAG, "\u8be5\u63d0\u9192\u5728\u6570\u636e\u5e93\u4e2d\u4e0d\u5b58\u5728");
                return false;
            }
            return false;
        } else if (OKLog.D) {
            OKLog.d(TAG, "\u8be5\u63d0\u9192\u5728\u6570\u636e\u5e93\u4e2d\u5df2\u5b58\u5728");
            return true;
        } else {
            return true;
        }
    }

    public static boolean cancelReminder(Type type, long j2, String str) {
        if (type == null) {
            if (OKLog.E) {
                OKLog.e(TAG, "\u4e1a\u52a1\u7c7b\u578b\u4e3a\u7a7a");
            }
            return false;
        }
        long alignedTime = getAlignedTime(j2);
        if (JDReminderTable.checkExistByTypeAndURL(type.toString(), alignedTime, str) == -1) {
            if (OKLog.D) {
                OKLog.d(TAG, "\u6570\u636e\u5e93\u4e2d\u65e0\u8be5\u63d0\u9192\uff0c\u4e0d\u7528\u53d6\u6d88");
            }
            return true;
        }
        cancelAlarm(alignedTime);
        try {
            JDReminderTable.deleteByTypeAndURL(type.toString(), alignedTime, str);
            return true;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return false;
        }
    }
}
