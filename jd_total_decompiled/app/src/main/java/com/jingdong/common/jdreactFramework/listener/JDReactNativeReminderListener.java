package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.text.TextUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.jingdong.common.entity.JDReminderNewEntity;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.utils.JDReminderNewUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class JDReactNativeReminderListener implements NativeReminderListener, JDFlutterCall {
    public static final String REMINDERCHANNEL = "com.jd.jdflutter/reminder";
    private static final String TAG = "JDReactNativeReminderListener";

    private long getLongParamSafe(HashMap hashMap, String str, long j2) {
        if (hashMap == null || TextUtils.isEmpty(str)) {
            return j2;
        }
        try {
            return Long.parseLong((String) hashMap.get(str));
        } catch (Exception unused) {
            return j2;
        }
    }

    private WritableArray getformatReminders(ArrayList<JDReminderNewEntity> arrayList) {
        WritableArray createArray = Arguments.createArray();
        if (arrayList != null) {
            Iterator<JDReminderNewEntity> it = arrayList.iterator();
            while (it.hasNext()) {
                JDReminderNewEntity next = it.next();
                WritableMap createMap = Arguments.createMap();
                if (next != null && next.getBusinessType() != null) {
                    createMap.putString("businessType", next.getBusinessType());
                } else {
                    createMap.putString("businessType", "");
                }
                if (next != null && next.getReminderShowTag() != null) {
                    createMap.putString("reminderShowTag", next.getReminderShowTag());
                } else {
                    createMap.putString("reminderShowTag", "");
                }
                if (next != null && next.getIdentificationId() != null) {
                    createMap.putString("identificationId", next.getIdentificationId());
                } else {
                    createMap.putString("identificationId", "");
                }
                if (next != null && next.getReminderTitle() != null) {
                    createMap.putString("reminderTitle", next.getReminderTitle());
                } else {
                    createMap.putString("reminderTitle", "");
                }
                if (next != null && next.getReminderImgUrl() != null) {
                    createMap.putString("reminderImgUrl", next.getReminderImgUrl());
                } else {
                    createMap.putString("reminderImgUrl", "");
                }
                if (next != null && next.getStartTimeMillis() != 0) {
                    createMap.putString("startTimeMillis", String.valueOf(next.getStartTimeMillis()));
                } else {
                    createMap.putString("startTimeMillis", "");
                }
                if (next != null && next.getNotificationTimeMillis() != 0) {
                    createMap.putString("notificationTimeMillis", String.valueOf(next.getNotificationTimeMillis()));
                } else {
                    createMap.putString("notificationTimeMillis", "");
                }
                if (next != null && next.getInsertTime() != 0) {
                    createMap.putString("insertTime", String.valueOf(next.getInsertTime()));
                } else {
                    createMap.putString("insertTime", "");
                }
                if (next != null && next.getExtra() != null) {
                    createMap.putString("extra", String.valueOf(next.getExtra()));
                } else {
                    createMap.putString("extra", "");
                }
                if (next != null && next.getJump() != null) {
                    createMap.putString("jump", String.valueOf(next.getJump()));
                } else {
                    createMap.putString("jump", "");
                }
                if (next != null && next.getMore() != null) {
                    createMap.putString("more", String.valueOf(next.getMore()));
                } else {
                    createMap.putString("more", "");
                }
                createArray.pushMap(createMap);
            }
        }
        return createArray;
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeReminderListener
    public void cancelReminder(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        String str = null;
        String str2 = (hashMap == null || !hashMap.containsKey("businessType")) ? null : (String) hashMap.get("businessType");
        if (hashMap != null && hashMap.containsKey("identificationId")) {
            str = (String) hashMap.get("identificationId");
        }
        long j2 = 0;
        if (hashMap != null && hashMap.containsKey("startTimeMillis")) {
            j2 = getLongParamSafe(hashMap, "startTimeMillis", 0L);
        }
        boolean cancelReminder = JDReminderNewUtils.cancelReminder(str2, str, j2);
        try {
            if (jDCallback != null && cancelReminder) {
                jDCallback.invoke(new Object[0]);
            } else if (jDCallback2 == null) {
            } else {
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeReminderListener
    public void checkReminder(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        String str = null;
        String str2 = (hashMap == null || !hashMap.containsKey("businessType")) ? null : (String) hashMap.get("businessType");
        if (hashMap != null && hashMap.containsKey("identificationId")) {
            str = (String) hashMap.get("identificationId");
        }
        long j2 = 0;
        if (hashMap != null && hashMap.containsKey("startTimeMillis")) {
            j2 = getLongParamSafe(hashMap, "startTimeMillis", 0L);
        }
        boolean checkReminder = JDReminderNewUtils.checkReminder(str2, str, j2);
        try {
            if (jDCallback != null && checkReminder) {
                jDCallback.invoke(new Object[0]);
            } else if (jDCallback2 == null) {
            } else {
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeReminderListener
    public void getAllRemindersByBusinessType(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        String str;
        if (hashMap != null && hashMap.containsKey("businessType")) {
            str = (String) hashMap.get("businessType");
        } else {
            jDCallback2.invoke(new Object[0]);
            str = null;
        }
        WritableArray writableArray = getformatReminders(JDReminderNewUtils.getAllRemindersByBusinessType(str));
        try {
            if (jDCallback != null) {
                jDCallback.invoke(writableArray);
            } else if (jDCallback2 == null) {
            } else {
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeReminderListener
    public void getAllRemindersByBusinessTypeAndTime(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        String str;
        if (hashMap != null && hashMap.containsKey("businessType")) {
            str = (String) hashMap.get("businessType");
        } else {
            jDCallback2.invoke(new Object[0]);
            str = null;
        }
        long j2 = 0;
        if (hashMap != null && hashMap.containsKey("fromTime")) {
            j2 = getLongParamSafe(hashMap, "fromTime", 0L);
        } else {
            jDCallback2.invoke(new Object[0]);
        }
        WritableArray writableArray = getformatReminders(JDReminderNewUtils.getAllRemindersByBusinessTypeAndTime(str, j2));
        try {
            if (jDCallback != null) {
                jDCallback.invoke(writableArray);
            } else if (jDCallback2 == null) {
            } else {
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeReminderListener
    public void getAllRemindersByBusinessTypeDuringTimePeriod(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        String str;
        long j2;
        if (hashMap != null && hashMap.containsKey("businessType")) {
            str = (String) hashMap.get("businessType");
        } else {
            jDCallback2.invoke(new Object[0]);
            str = null;
        }
        long j3 = 0;
        if (hashMap != null && hashMap.containsKey("fromTime")) {
            j2 = getLongParamSafe(hashMap, "fromTime", 0L);
        } else {
            jDCallback2.invoke(new Object[0]);
            j2 = 0;
        }
        if (hashMap != null && hashMap.containsKey("toTime")) {
            j3 = getLongParamSafe(hashMap, "toTime", 0L);
        } else {
            jDCallback2.invoke(new Object[0]);
        }
        WritableArray writableArray = getformatReminders(JDReminderNewUtils.getAllRemindersByBusinessTypeDuringTimePeriod(str, j2, j3));
        try {
            if (jDCallback != null) {
                jDCallback.invoke(writableArray);
            } else if (jDCallback2 == null) {
            } else {
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("setReminder")) {
            setReminder(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeReminderListener.1
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeReminderListener.2
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("cancelReminder")) {
            cancelReminder(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeReminderListener.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeReminderListener.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("checkReminder")) {
            checkReminder(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeReminderListener.5
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeReminderListener.6
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getAllRemindersByBusinessType")) {
            getAllRemindersByBusinessType(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeReminderListener.7
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeReminderListener.8
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getAllRemindersByBusinessTypeAndTime")) {
            getAllRemindersByBusinessTypeAndTime(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeReminderListener.9
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeReminderListener.10
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("getAllRemindersByBusinessTypeDuringTimePeriod")) {
            getAllRemindersByBusinessTypeDuringTimePeriod(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeReminderListener.11
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeReminderListener.12
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeReminderListener
    public void setReminder(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        String str = null;
        String str2 = (hashMap == null || !hashMap.containsKey("businessType")) ? null : (String) hashMap.get("businessType");
        String str3 = (hashMap == null || !hashMap.containsKey("showTag")) ? null : (String) hashMap.get("showTag");
        String str4 = (hashMap == null || !hashMap.containsKey("identificationId")) ? null : (String) hashMap.get("identificationId");
        String str5 = (hashMap == null || !hashMap.containsKey("reminderTitle")) ? null : (String) hashMap.get("reminderTitle");
        long longParamSafe = (hashMap == null || !hashMap.containsKey("startTimeMillis")) ? 0L : getLongParamSafe(hashMap, "startTimeMillis", 0L);
        if (hashMap != null && hashMap.containsKey("jump")) {
            str = (String) hashMap.get("jump");
        }
        JDReminderNewEntity.ReminderBuilder reminderBuilder = new JDReminderNewEntity.ReminderBuilder(str2, str3, str4, str5, longParamSafe, str);
        if (hashMap != null && hashMap.containsKey("reminderImgUrl")) {
            reminderBuilder.reminderImgUrl((String) hashMap.get("reminderImgUrl"));
        }
        if (hashMap != null && hashMap.containsKey("notificationTimeMillis")) {
            reminderBuilder.notificationTimeMillis(getLongParamSafe(hashMap, "notificationTimeMillis", 0L));
        }
        if (hashMap != null && hashMap.containsKey("insertTime")) {
            reminderBuilder.insertTime(getLongParamSafe(hashMap, "insertTime", 0L));
        }
        if (hashMap != null && hashMap.containsKey("extra")) {
            reminderBuilder.extra((String) hashMap.get("extra"));
        }
        if (hashMap != null && hashMap.containsKey("more")) {
            reminderBuilder.more((String) hashMap.get("more"));
        }
        boolean reminder = JDReminderNewUtils.setReminder(reminderBuilder.build());
        try {
            if (jDCallback != null && reminder) {
                jDCallback.invoke(new Object[0]);
            } else if (jDCallback2 == null) {
            } else {
                jDCallback2.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            if (jDCallback2 != null) {
                jDCallback2.invoke(new Object[0]);
            }
        }
    }
}
