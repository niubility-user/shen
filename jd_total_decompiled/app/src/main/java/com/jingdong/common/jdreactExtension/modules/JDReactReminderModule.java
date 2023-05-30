package com.jingdong.common.jdreactExtension.modules;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.jingdong.common.entity.JDReminderNewEntity;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class JDReactReminderModule extends ReactContextBaseJavaModule {
    NativeReminderListener mNativeReminderLister;

    /* loaded from: classes5.dex */
    public interface NativeReminderListener {
        ArrayList<JDReminderNewEntity> getAllRemindersByTime(long j2);

        void removeRemindersEarlierThanDate(long j2);
    }

    public JDReactReminderModule(ReactApplicationContext reactApplicationContext, NativeReminderListener nativeReminderListener) {
        super(reactApplicationContext);
        this.mNativeReminderLister = nativeReminderListener;
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
                    createMap.putString("showTag", next.getReminderShowTag());
                } else {
                    createMap.putString("showTag", "");
                }
                if (next != null && next.getIdentificationId() != null) {
                    createMap.putString("uniqueId", next.getIdentificationId());
                } else {
                    createMap.putString("uniqueId", "");
                }
                if (next != null && next.getReminderTitle() != null) {
                    createMap.putString("remindTitle", next.getReminderTitle());
                } else {
                    createMap.putString("remindTitle", "");
                }
                if (next != null && next.getReminderImgUrl() != null) {
                    createMap.putString("imgUrl", next.getReminderImgUrl());
                } else {
                    createMap.putString("imgUrl", "");
                }
                if (next != null && next.getStartTimeMillis() != 0) {
                    createMap.putString(JshopConst.JSKEY_COUPON_BEGIN_TIME, String.valueOf(next.getStartTimeMillis()));
                } else {
                    createMap.putString(JshopConst.JSKEY_COUPON_BEGIN_TIME, "");
                }
                if (next != null && next.getNotificationTimeMillis() != 0) {
                    createMap.putString("notifyTime", String.valueOf(next.getNotificationTimeMillis()));
                } else {
                    createMap.putString("notifyTime", "");
                }
                if (next != null && next.getInsertTime() != 0) {
                    createMap.putString("insertTime", String.valueOf(next.getInsertTime()));
                } else {
                    createMap.putString("insertTime", "");
                }
                if (next != null && next.getExtra() != null) {
                    createMap.putString("extraStr1", String.valueOf(next.getExtra()));
                } else {
                    createMap.putString("extraStr1", "");
                }
                if (next != null && next.getJump() != null) {
                    createMap.putString("jumpStr", String.valueOf(next.getJump()));
                } else {
                    createMap.putString("jumpStr", "");
                }
                if (next != null && next.getMore() != null) {
                    createMap.putString("extraStr2", String.valueOf(next.getMore()));
                } else {
                    createMap.putString("extraStr2", "");
                }
                createArray.pushMap(createMap);
            }
        }
        return createArray;
    }

    @ReactMethod
    public void getAllRemindersByTime(ReadableMap readableMap, Callback callback, Callback callback2) {
        long j2;
        if (readableMap == null || !readableMap.hasKey("fromTime")) {
            if (callback2 != null) {
                callback2.invoke(new Object[0]);
            }
            j2 = 0;
        } else {
            j2 = Long.parseLong(readableMap.getString("fromTime"));
        }
        WritableArray writableArray = getformatReminders(this.mNativeReminderLister.getAllRemindersByTime(j2));
        if (callback != null) {
            try {
                callback.invoke(writableArray);
            } catch (Exception unused) {
                if (callback2 != null) {
                    callback2.invoke(new Object[0]);
                }
            }
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactReminderModule";
    }

    @ReactMethod
    public void removeRemindersEarlierThanDate(ReadableMap readableMap) {
        if (readableMap == null || !readableMap.hasKey("fromTime")) {
            return;
        }
        this.mNativeReminderLister.removeRemindersEarlierThanDate(Long.parseLong(readableMap.getString("fromTime")));
    }
}
