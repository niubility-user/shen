package com.jingdong.common.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.R;
import com.jingdong.common.database.table.JDReminderNewTable;
import com.jingdong.common.entity.JDReminder;
import com.jingdong.common.entity.JDReminderNewEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.permission.RomUtil;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.JDReminderUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.constant.JDReminderConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.unionpay.tsmservice.mi.data.Constant;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import jd.wjlogin_sdk.util.f;

/* loaded from: classes6.dex */
public class JDReminderNewUtils {
    private static final long[] DEVIATION_NOTIFICATION_TIMEMILLIS;
    public static final long REMINDER_DURATION_TIME_DEFAULT = 60000;
    public static final long REMINDER_DURATION_TIME_MAX = 7200000;
    public static final long REMINDER_DURATION_TIME_MIN = 180000;
    private static final String TAG = "HHH_JDReminderNewUtils";
    private static final AlarmManager alarmManager;
    private static final Context context;

    /* renamed from: com.jingdong.common.utils.JDReminderNewUtils$3  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$utils$JDReminderUtils$Type;

        static {
            int[] iArr = new int[JDReminderUtils.Type.values().length];
            $SwitchMap$com$jingdong$common$utils$JDReminderUtils$Type = iArr;
            try {
                iArr[JDReminderUtils.Type.MIAOSHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$utils$JDReminderUtils$Type[JDReminderUtils.Type.MIAOSHANEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$utils$JDReminderUtils$Type[JDReminderUtils.Type.ZHIBO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$common$utils$JDReminderUtils$Type[JDReminderUtils.Type.COUPON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static {
        Application application = JdSdk.getInstance().getApplication();
        context = application;
        alarmManager = (AlarmManager) application.getSystemService(NotificationCompat.CATEGORY_ALARM);
        DEVIATION_NOTIFICATION_TIMEMILLIS = new long[]{171000, 174000, 177000, 183000, 186000, 189000};
    }

    private static void cancelAlarm(long j2) {
        ArrayMap<Long, Integer> remindersKeyIdAndRequestCodeByNotificationTime = JDReminderNewTable.getRemindersKeyIdAndRequestCodeByNotificationTime(j2);
        if (remindersKeyIdAndRequestCodeByNotificationTime.size() - 1 == 0) {
            alarmManager.cancel(setPendingIntent(j2, remindersKeyIdAndRequestCodeByNotificationTime.entrySet().iterator().next().getValue().intValue()));
            if (OKLog.D) {
                OKLog.d(TAG, "\u8ba1\u65f6\u5668\u5df2\u53d6\u6d88");
            }
        } else if (OKLog.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("\u8ba1\u65f6\u5668\u8fd8\u4e0d\u80fd\u53d6\u6d88\uff0c\u4ecd\u6709\u63d0\u9192\u6570\u91cf\u4e3a\uff1a");
            sb.append(remindersKeyIdAndRequestCodeByNotificationTime.size() - 1);
            OKLog.d(TAG, sb.toString());
        }
    }

    public static boolean cancelReminder(String str, String str2, long j2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && j2 >= 0) {
            long preciseTime = getPreciseTime(j2);
            if (OKLog.D) {
                StringBuilder sb = new StringBuilder();
                sb.append("cancelReminder: ");
                sb.append(String.format("%s, %s, %s", str, str2, preciseTime + ""));
                OKLog.d(TAG, sb.toString());
            }
            if (JDReminderNewTable.checkReminder(str, str2, preciseTime) == -1) {
                if (OKLog.D) {
                    OKLog.d(TAG, "\u6570\u636e\u5e93\u4e2d\u65e0\u8be5\u63d0\u9192\uff0c\u4e0d\u7528\u53d6\u6d88");
                }
                JDMtaUtils.onClickWithPageId(context, "Notification_CancelSubscribe", JDReminderNewUtils.class.getName(), str + "_1", "MyCalendar_Main");
                return true;
            }
            long notificationTimeMillis = JDReminderNewTable.getNotificationTimeMillis(str, str2, preciseTime);
            if (notificationTimeMillis != -1) {
                cancelAlarm(notificationTimeMillis);
            }
            try {
                JDReminderNewTable.deleteReminder(str, str2, preciseTime);
                JDMtaUtils.onClickWithPageId(context, "Notification_CancelSubscribe", JDReminderNewUtils.class.getName(), str + "_1", "MyCalendar_Main");
                return true;
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                    OKLog.d(TAG, "\u5220\u9664\u6570\u636e\u5e93\u6570\u636e\u5f02\u5e38");
                }
                JDMtaUtils.onClickWithPageId(context, "Notification_CancelSubscribe", JDReminderNewUtils.class.getName(), str + "_0", "MyCalendar_Main");
                return false;
            }
        }
        if (OKLog.E) {
            OKLog.e(TAG, "cancelReminder, \u53c2\u6570\u9519\u8bef.");
        }
        JDMtaUtils.onClickWithPageId(context, "Notification_CancelSubscribe", JDReminderNewUtils.class.getName(), str + "_0", "MyCalendar_Main");
        return false;
    }

    public static boolean checkReminder(String str, String str2, long j2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && j2 >= 0) {
            long preciseTime = getPreciseTime(j2);
            if (OKLog.D) {
                StringBuilder sb = new StringBuilder();
                sb.append("checkReminder: ");
                sb.append(String.format("%s, %s, %s", str, str2, preciseTime + ""));
                OKLog.d(TAG, sb.toString());
            }
            if (JDReminderNewTable.checkReminder(str, str2, preciseTime) == -1) {
                if (OKLog.D) {
                    OKLog.d(TAG, "\u8be5\u63d0\u9192\u5728\u6570\u636e\u5e93\u4e2d\u4e0d\u5b58\u5728");
                }
                return false;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "\u8be5\u63d0\u9192\u5728\u6570\u636e\u5e93\u4e2d\u5df2\u5b58\u5728");
            }
            return true;
        }
        if (OKLog.E) {
            OKLog.e(TAG, "checkReminder, \u53c2\u6570\u9519\u8bef.");
        }
        return false;
    }

    public static void compatibleWithOldData() {
        int i2 = SharedPreferencesUtil.getInt("JDREMINDER_FIRST_COMPATIBLEWITHOLDDATA", 0);
        if (OKLog.D) {
            OKLog.d(TAG, "compatibleWithOldData firstCompatibleWithOldData: " + i2);
        }
        if (i2 != 1) {
            SharedPreferencesUtil.putInt("JDREMINDER_FIRST_COMPATIBLEWITHOLDDATA", 1);
            ArrayList<JDReminder> allRemindersAfterTargetTime = JDReminderUtils.getAllRemindersAfterTargetTime(System.currentTimeMillis() - 180000);
            if (allRemindersAfterTargetTime != null && allRemindersAfterTargetTime.size() > 0) {
                if (OKLog.D) {
                    OKLog.d(TAG, "compatibleWithOldData oldData size: " + allRemindersAfterTargetTime.size());
                }
                Iterator<JDReminder> it = allRemindersAfterTargetTime.iterator();
                while (it.hasNext()) {
                    JDReminder next = it.next();
                    if (next != null) {
                        int i3 = AnonymousClass3.$SwitchMap$com$jingdong$common$utils$JDReminderUtils$Type[next.type.ordinal()];
                        if (i3 == 1) {
                            setReminder(new JDReminderNewEntity.ReminderBuilder(JDReminderConstant.BUSINESS_TYPE_SECKILL, context.getResources().getString(R.string.reminder_business_type_seckill), next.reminderId + "", next.title, getAlignedTimeForMS(next.startTime), getSecKillJumpString(next.reminderId)).build());
                        } else if (i3 == 2) {
                            setReminder(new JDReminderNewEntity.ReminderBuilder(JDReminderConstant.BUSINESS_TYPE_SECKILLNEW, context.getResources().getString(R.string.reminder_business_type_seckill), next.reminderId + "", next.title, getAlignedTimeForMS(next.startTime), getSecKillNewJumpString(next.reminderId)).build());
                        } else if (i3 == 3) {
                            setReminder(new JDReminderNewEntity.ReminderBuilder(JDReminderConstant.BUSINESS_TYPE_JDLIVE, context.getResources().getString(R.string.reminder_business_type_jdlive), next.reminderId + "", next.title, next.startTime, getJDLiveJumpString(next.reminderId)).build());
                        } else if (i3 == 4) {
                            setReminder(new JDReminderNewEntity.ReminderBuilder("COUPON", context.getResources().getString(R.string.reminder_business_type_coupon), next.reminderId + "", next.title, next.startTime, getCouponJumpString(next.reminderId)).build());
                        }
                    }
                }
            } else if (OKLog.D) {
                OKLog.d(TAG, "compatibleWithOldData \u6ca1\u6709\u9700\u8981\u517c\u5bb9\u7684\u65e7\u6570\u636e");
            }
        }
    }

    public static void deleteRemindersBeforeTargetTime(long j2) {
        JDReminderNewTable.deleteRemindersBeforeTargetTime(getPreciseTime(j2));
    }

    private static String displayTime(long j2) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(j2));
    }

    private static long getAlignedTimeForMS(long j2) {
        long j3 = j2 % 600000;
        return j3 < 300000 ? j2 - j3 : (j2 - j3) + 600000;
    }

    public static ArrayList<JDReminderNewEntity> getAllRemindersAfterTargetTime(long j2) {
        return JDReminderNewTable.getAllRemindersAfterTargetTime(getPreciseTime(j2));
    }

    public static ArrayList<JDReminderNewEntity> getAllRemindersByBusinessType(String str) {
        return getAllRemindersByBusinessTypeAndTime(str, -1L);
    }

    public static ArrayList<JDReminderNewEntity> getAllRemindersByBusinessTypeAndTime(String str, long j2) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList<>();
        }
        return JDReminderNewTable.getAllRemindersBasedOnTypeAndTime(str, getPreciseTime(j2));
    }

    public static ArrayList<JDReminderNewEntity> getAllRemindersByBusinessTypeDuringTimePeriod(String str, long j2, long j3) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList<>();
        }
        if (j3 <= 0) {
            return JDReminderNewTable.getAllRemindersBasedOnTypeAndTime(str, getPreciseTime(j2));
        }
        return JDReminderNewTable.getAllRemindersBasedOnTypeDuringTimePeriod(str, getPreciseTime(j2), getPreciseTime(j3));
    }

    private static String getCouponJumpString(long j2) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("des", (Object) JumpUtil.VAULE_DES_COUPON_CENTER);
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        jDJSONObject2.put("categoryId", (Object) (j2 + ""));
        jDJSONObject2.put("id", (Object) (j2 + ""));
        jDJSONObject2.put(JshopConst.JSKEY_BATCH_ID, (Object) (j2 + ""));
        jDJSONObject.put("params", (Object) jDJSONObject2.toJSONString());
        return jDJSONObject.toJSONString();
    }

    private static long getDefNotificationTimeMillis(String str, String str2, long j2) {
        int abs = Math.abs((str + str2 + j2).hashCode());
        long[] jArr = DEVIATION_NOTIFICATION_TIMEMILLIS;
        return j2 - jArr[abs % jArr.length];
    }

    private static String getJDLiveJumpString(long j2) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("des", (Object) JumpUtil.VALUE_DES_FIND_LIVE_PRE);
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        jDJSONObject2.put("position", (Object) "0");
        jDJSONObject2.put("id", (Object) (j2 + ""));
        jDJSONObject.put("params", (Object) jDJSONObject2.toJSONString());
        return jDJSONObject.toJSONString();
    }

    private static long getPreciseTime(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeInMillis(j2);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static Set<Long> getReminderDaysDuringTimePeriod(long j2, long j3) {
        return JDReminderNewTable.getReminderDaysDuringTimePeriod(getPreciseTime(j2), getPreciseTime(j3));
    }

    public static ArrayList<JDReminderNewEntity> getRemindersAtNotificationTime(long j2) {
        return JDReminderNewTable.getAllRemindersAtNotificationTime(j2);
    }

    public static ArrayList<JDReminderNewEntity> getRemindersAtStartTime(long j2) {
        long preciseTime = getPreciseTime(j2);
        return JDReminderNewTable.getRemindersDuringTimePeriod(preciseTime, 1 + preciseTime);
    }

    public static ArrayList<JDReminderNewEntity> getRemindersDuringTimePeriod(long j2, long j3) {
        return JDReminderNewTable.getRemindersDuringTimePeriod(getPreciseTime(j2), getPreciseTime(j3));
    }

    public static ArrayMap<Long, Integer> getRemindersNotificationTimeAndRequestCodeAfterTime(long j2) {
        return JDReminderNewTable.getRemindersNotificationTimeAndRequestCodeAfterTime(getPreciseTime(j2) - 10000);
    }

    public static ArrayMap<Long, Integer> getRemindersStartTimeAndRequestCodeAfterTime(long j2) {
        return JDReminderNewTable.getRemindersStartTimeAndRequestCodeAfterTime(getPreciseTime(j2));
    }

    private static String getSecKillJumpString(long j2) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("des", (Object) JumpUtil.VALUE_DES_MIAOSHA_MYCONCERN);
        return jDJSONObject.toJSONString();
    }

    private static String getSecKillNewJumpString(long j2) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("des", (Object) JumpUtil.VALUE_DES_MIAOSHA_NEWPRODUCT);
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        jDJSONObject2.put(MiaoShaPublicConstants.KEY_NEWGOODS_ID, (Object) (j2 + ""));
        jDJSONObject.put("params", (Object) jDJSONObject2.toJSONString());
        return jDJSONObject.toJSONString();
    }

    private static boolean isColorOS() {
        return "OPPO".equalsIgnoreCase(BaseInfo.getDeviceManufacture());
    }

    private static boolean isFirstLaunch() {
        if (SharedPreferencesUtil.getInt("JDREMINDER_FIRST_SET", 0) != 1) {
            SharedPreferencesUtil.putInt("JDREMINDER_FIRST_SET", 1);
            return true;
        }
        return false;
    }

    private static boolean isMiui() {
        return Constant.DEVICE_XIAOMI.equalsIgnoreCase(BaseInfo.getDeviceManufacture());
    }

    private static boolean isNotificationEnable() {
        boolean areNotificationsEnabled = NotificationManagerCompat.from(JdSdk.getInstance().getApplication()).areNotificationsEnabled();
        if (!areNotificationsEnabled) {
            IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
            if (currentMyActivity instanceof Activity) {
                Context context2 = context;
                final JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2((Activity) currentMyActivity, context2.getString(R.string.jdreminder_notification_dialog_title), context2.getString(R.string.cancel), context2.getString(R.string.jdreminder_notificaiton_dialog_setting));
                createJdDialogWithStyle2.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.JDReminderNewUtils.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JDDialog.this.cancel();
                    }
                });
                createJdDialogWithStyle2.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.JDReminderNewUtils.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JDReminderNewUtils.openNotificationSettings();
                        JDDialog.this.cancel();
                    }
                });
                createJdDialogWithStyle2.show();
            } else {
                ToastUtils.shortToast(context, R.string.jdreminder_notification_dialog_title);
            }
        }
        return areNotificationsEnabled;
    }

    private static void openMiuiSetting(Activity activity) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName("com.android.settings", "com.android.settings.Settings$NotificationFilterActivity");
        intent.addFlags(32768);
        intent.addFlags(268435456);
        intent.putExtra("appName", com.jingdong.jdsdk.res.StringUtil.app_name);
        intent.putExtra("packageName", JdSdk.getInstance().getApplication().getPackageName());
        activity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void openNotificationSettings() {
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (currentMyActivity instanceof Activity) {
            try {
                if (RomUtil.isMiui() && Build.VERSION.SDK_INT >= 26) {
                    openMiuiSetting((Activity) currentMyActivity);
                } else {
                    Intent intent = new Intent();
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse("package:" + context.getPackageName()));
                    ((Activity) currentMyActivity).startActivity(intent);
                }
            } catch (Exception unused) {
                ToastUtils.shortToast(context, R.string.jdreminder_notification_toast_fail);
            }
        }
    }

    private static PendingIntent setPendingIntent(long j2, int i2) {
        Intent intent = new Intent();
        intent.setAction(Constants.JDREMINDER_RECEIVER_ACTION_NAME);
        intent.setFlags(32);
        intent.setClassName(JdSdk.getInstance().getApplication() == null ? f.f19954c : JdSdk.getInstance().getApplication().getPackageName(), "com.jingdong.app.mall.localreminder.JDReminderReceiver");
        intent.putExtra("notificationTime", j2);
        intent.putExtra("requestCode", i2);
        return PendingIntent.getBroadcast(context, i2, intent, Build.VERSION.SDK_INT >= 31 ? 67108864 : 0);
    }

    public static boolean setReminder(JDReminderNewEntity jDReminderNewEntity) {
        return jDReminderNewEntity != null && setReminder(jDReminderNewEntity.getBusinessType(), jDReminderNewEntity.getReminderShowTag(), jDReminderNewEntity.getIdentificationId(), jDReminderNewEntity.getReminderTitle(), jDReminderNewEntity.getReminderImgUrl(), jDReminderNewEntity.getStartTimeMillis(), jDReminderNewEntity.getNotificationTimeMillis(), jDReminderNewEntity.getJump(), jDReminderNewEntity.getExtra(), jDReminderNewEntity.getMore());
    }

    private static void showMiuiAndColorOSInfoToast() {
        if (isColorOS() && isFirstLaunch()) {
            Context context2 = context;
            Toast.makeText(context2, context2.getResources().getString(R.string.jdreminder_oppo_info), 1).show();
        }
        if (isMiui() && isFirstLaunch()) {
            Context context3 = context;
            Toast.makeText(context3, context3.getResources().getString(R.string.jdreminder_xiaomi_info), 1).show();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x007d, code lost:
        if (r6 > com.jingdong.common.utils.JDReminderNewUtils.REMINDER_DURATION_TIME_MAX) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x025f A[LOOP:0: B:107:0x025d->B:108:0x025f, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean setReminder(String str, String str2, String str3, String str4, String str5, long j2, long j3, String str6, String str7, String str8) {
        long preciseTime;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        boolean z;
        int lastRequestCode;
        int i2;
        if (isNotificationEnable()) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str6) && j2 >= 0) {
                String str14 = TextUtils.isEmpty(str5) ? "" : str5;
                String str15 = TextUtils.isEmpty(str8) ? "" : str8;
                long preciseTime2 = getPreciseTime(j2);
                if (j3 <= 0) {
                    preciseTime = getDefNotificationTimeMillis(str, str3, preciseTime2);
                } else if ("CALENDAR_ACTIVITY_NEW".equals(str)) {
                    preciseTime = getPreciseTime(j3);
                } else {
                    preciseTime = getPreciseTime(j3);
                    long j4 = preciseTime2 - preciseTime;
                    long j5 = j4 >= 60000 ? REMINDER_DURATION_TIME_MAX : 60000L;
                    preciseTime = preciseTime2 - j5;
                }
                long j6 = preciseTime;
                String str16 = (!TextUtils.isEmpty(str7) && ("CALENDAR_ACTIVITY_NEW".equals(str) || getPreciseTime(Long.parseLong(str7)) <= j6)) ? str7 : "";
                if (OKLog.D) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("setReminder: ");
                    sb.append(String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s", str, str2, str3, str4, str14, preciseTime2 + "", j6 + "", str6, str16, str15));
                    OKLog.d(TAG, sb.toString());
                }
                try {
                    ArrayMap<Long, Integer> remindersKeyIdAndRequestCodeByNotificationTime = JDReminderNewTable.getRemindersKeyIdAndRequestCodeByNotificationTime(j6);
                    if (remindersKeyIdAndRequestCodeByNotificationTime.size() > 0) {
                        lastRequestCode = remindersKeyIdAndRequestCodeByNotificationTime.valueAt(0).intValue();
                        if (OKLog.D) {
                            OKLog.d(TAG, "old requestCode: " + lastRequestCode);
                        }
                    } else {
                        lastRequestCode = JDReminderNewTable.getLastRequestCode() + 1;
                        if (OKLog.D) {
                            OKLog.d(TAG, "new requestCode: " + lastRequestCode);
                        }
                    }
                    i2 = lastRequestCode;
                } catch (Exception e2) {
                    e = e2;
                    str9 = "Notification_AddSubscribe";
                    str10 = TAG;
                    str11 = "MyCalendar_Main";
                    str12 = "_0";
                    str13 = str;
                }
                try {
                    if (JDReminderNewTable.insertOrUpdate(str, str2, str3, str4, str14, preciseTime2, j6, System.currentTimeMillis(), str6, str16, str15, i2) == -1) {
                        try {
                            Context context2 = context;
                            String name = JDReminderNewUtils.class.getName();
                            StringBuilder sb2 = new StringBuilder();
                            str13 = str;
                            try {
                                sb2.append(str13);
                                str12 = "_0";
                                try {
                                    sb2.append(str12);
                                    str9 = "Notification_AddSubscribe";
                                    str11 = "MyCalendar_Main";
                                    try {
                                        JDMtaUtils.onClickWithPageId(context2, str9, name, sb2.toString(), str11);
                                        return false;
                                    } catch (Exception e3) {
                                        e = e3;
                                        z = false;
                                        str10 = TAG;
                                        if (OKLog.E) {
                                        }
                                        StringBuilder sb3 = new StringBuilder();
                                        while (r14 < r7) {
                                        }
                                        ExceptionReporter.reportNormalRemindErr(sb3.toString());
                                        JDMtaUtils.onClickWithPageId(context, str9, JDReminderNewUtils.class.getName(), str13 + str12, str11);
                                        return z;
                                    }
                                } catch (Exception e4) {
                                    e = e4;
                                    str9 = "Notification_AddSubscribe";
                                    str11 = "MyCalendar_Main";
                                }
                            } catch (Exception e5) {
                                e = e5;
                                str9 = "Notification_AddSubscribe";
                                str11 = "MyCalendar_Main";
                                str12 = "_0";
                                z = false;
                                str10 = TAG;
                                if (OKLog.E) {
                                }
                                StringBuilder sb32 = new StringBuilder();
                                while (r14 < r7) {
                                }
                                ExceptionReporter.reportNormalRemindErr(sb32.toString());
                                JDMtaUtils.onClickWithPageId(context, str9, JDReminderNewUtils.class.getName(), str13 + str12, str11);
                                return z;
                            }
                        } catch (Exception e6) {
                            e = e6;
                            str13 = str;
                        }
                    } else {
                        str13 = str;
                        str9 = "Notification_AddSubscribe";
                        str11 = "MyCalendar_Main";
                        str12 = "_0";
                        z = false;
                        try {
                            PendingIntent pendingIntent = setPendingIntent(j6, i2);
                            if (Build.VERSION.SDK_INT >= 19) {
                                alarmManager.setExact(0, j6, pendingIntent);
                            } else {
                                alarmManager.set(0, j6, pendingIntent);
                            }
                            if (OKLog.D) {
                                String str17 = "\u5df2\u5f00\u542f\u65b0\u8ba1\u65f6\u5668\uff0cnotificationTimeMillis\uff1a" + displayTime(j6) + ", startTime: " + displayTime(preciseTime2);
                                str10 = TAG;
                                try {
                                    OKLog.d(str10, str17);
                                } catch (Exception e7) {
                                    e = e7;
                                }
                            } else {
                                str10 = TAG;
                            }
                            JDMtaUtils.onClickWithPageId(context, str9, JDReminderNewUtils.class.getName(), str13 + "_1", str11);
                            return true;
                        } catch (Exception e8) {
                            e = e8;
                        }
                    }
                    str10 = TAG;
                } catch (Exception e9) {
                    e = e9;
                    str13 = str;
                    str9 = "Notification_AddSubscribe";
                    str10 = TAG;
                    str11 = "MyCalendar_Main";
                    str12 = "_0";
                    z = false;
                    if (OKLog.E) {
                    }
                    StringBuilder sb322 = new StringBuilder();
                    while (r14 < r7) {
                    }
                    ExceptionReporter.reportNormalRemindErr(sb322.toString());
                    JDMtaUtils.onClickWithPageId(context, str9, JDReminderNewUtils.class.getName(), str13 + str12, str11);
                    return z;
                }
                if (OKLog.E) {
                    OKLog.e(str10, e);
                    OKLog.d(str10, "\u5199\u5165\u6570\u636e\u5e93\u5f02\u5e38, e: " + e.toString());
                }
                StringBuilder sb3222 = new StringBuilder();
                for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                    sb3222.append(stackTraceElement.toString() + "\\n");
                }
                ExceptionReporter.reportNormalRemindErr(sb3222.toString());
                JDMtaUtils.onClickWithPageId(context, str9, JDReminderNewUtils.class.getName(), str13 + str12, str11);
                return z;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "setReminder, \u5fc5\u9009\u53c2\u6570\u9519\u8bef.");
            }
            JDMtaUtils.onClickWithPageId(context, "Notification_AddSubscribe", JDReminderNewUtils.class.getName(), str + "_0", "MyCalendar_Main");
            return false;
        }
        return false;
    }
}
