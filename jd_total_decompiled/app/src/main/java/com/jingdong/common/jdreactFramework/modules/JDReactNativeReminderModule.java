package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeReminderListener;

/* loaded from: classes5.dex */
public class JDReactNativeReminderModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactNativeReminderModule";
    private NativeReminderListener mNativeReminderListener;

    public JDReactNativeReminderModule(ReactApplicationContext reactApplicationContext, NativeReminderListener nativeReminderListener) {
        super(reactApplicationContext);
        this.mNativeReminderListener = nativeReminderListener;
    }

    @ReactMethod
    public void cancelReminder(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeReminderListener nativeReminderListener = this.mNativeReminderListener;
        if (nativeReminderListener != null) {
            nativeReminderListener.cancelReminder(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void checkReminder(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeReminderListener nativeReminderListener = this.mNativeReminderListener;
        if (nativeReminderListener != null) {
            nativeReminderListener.checkReminder(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getAllRemindersByBusinessType(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeReminderListener nativeReminderListener = this.mNativeReminderListener;
        if (nativeReminderListener != null) {
            nativeReminderListener.getAllRemindersByBusinessType(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getAllRemindersByBusinessTypeAndTime(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeReminderListener nativeReminderListener = this.mNativeReminderListener;
        if (nativeReminderListener != null) {
            nativeReminderListener.getAllRemindersByBusinessTypeAndTime(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getAllRemindersByBusinessTypeDuringTimePeriod(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeReminderListener nativeReminderListener = this.mNativeReminderListener;
        if (nativeReminderListener != null) {
            nativeReminderListener.getAllRemindersByBusinessTypeDuringTimePeriod(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void setReminder(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeReminderListener nativeReminderListener = this.mNativeReminderListener;
        if (nativeReminderListener != null) {
            nativeReminderListener.setReminder(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}
