package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeDatePickerListener;

/* loaded from: classes5.dex */
public class JDReactNativeDatePickerModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactNativeDatePickerModule";
    private NativeDatePickerListener mNativeDatePickerListener;

    public JDReactNativeDatePickerModule(ReactApplicationContext reactApplicationContext, NativeDatePickerListener nativeDatePickerListener) {
        super(reactApplicationContext);
        this.mNativeDatePickerListener = nativeDatePickerListener;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void showDatePicker(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeDatePickerListener nativeDatePickerListener = this.mNativeDatePickerListener;
        if (nativeDatePickerListener != null) {
            nativeDatePickerListener.showDatePicker(getCurrentActivity(), readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void showDatePicker2(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeDatePickerListener nativeDatePickerListener = this.mNativeDatePickerListener;
        if (nativeDatePickerListener != null) {
            nativeDatePickerListener.showDatePicker2(getCurrentActivity(), readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void showTimeWheelPicker(String str, String str2, Callback callback, Callback callback2) {
        NativeDatePickerListener nativeDatePickerListener = this.mNativeDatePickerListener;
        if (nativeDatePickerListener != null) {
            nativeDatePickerListener.showTimeWheelPicker(getCurrentActivity(), str, str2, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void showTimeWheelPicker2(String str, String str2, ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeDatePickerListener nativeDatePickerListener = this.mNativeDatePickerListener;
        if (nativeDatePickerListener != null) {
            nativeDatePickerListener.showTimeWheelPicker2(getCurrentActivity(), str, str2, readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void showWheelPicker(ReadableArray readableArray, String str, Callback callback, Callback callback2) {
        NativeDatePickerListener nativeDatePickerListener = this.mNativeDatePickerListener;
        if (nativeDatePickerListener != null) {
            nativeDatePickerListener.showWheelPicker(getCurrentActivity(), readableArray.toArrayList(), str, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void showWheelPicker2(ReadableArray readableArray, String str, ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeDatePickerListener nativeDatePickerListener = this.mNativeDatePickerListener;
        if (nativeDatePickerListener != null) {
            nativeDatePickerListener.showWheelPicker2(getCurrentActivity(), readableArray.toArrayList(), str, readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}
