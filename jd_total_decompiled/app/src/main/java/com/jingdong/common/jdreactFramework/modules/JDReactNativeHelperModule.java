package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeHelperListener;

/* loaded from: classes5.dex */
public class JDReactNativeHelperModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactNativeHelperModule";
    private NativeHelperListener mNativeHelperListener;

    public JDReactNativeHelperModule(ReactApplicationContext reactApplicationContext, NativeHelperListener nativeHelperListener) {
        super(reactApplicationContext);
        this.mNativeHelperListener = nativeHelperListener;
    }

    @ReactMethod
    public void addScheduleToCalendar(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.addScheduleToCalendar(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void addScheduleToCalendar2(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.addScheduleToCalendar2(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void callPhone(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.callPhone(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void closePage(Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.closePage(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getAdvertParams(Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.getAdvertParams(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getClientVersion(Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.getClientVersion(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getContactByCondition(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.getContactByCondition(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getContactName(String str, Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.getContactName(str, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getContactsdata(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.getContactsdata(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getCurrentModuleVersion(String str, Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.getCurrentModuleVersion(str, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getDeviceUUID(Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.getDeviceUUID(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void getOSVersion(Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.getOSVersion(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void gpsSettings(Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.gpsSettings(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void isGpsOpen(Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.isGpsOpen(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void md5Encode(String str, Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.md5Encode(str, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void pickContact(String str, Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.pickContact(str, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void pickContact2(Callback callback, Callback callback2) {
        NativeHelperListener nativeHelperListener = this.mNativeHelperListener;
        if (nativeHelperListener != null) {
            nativeHelperListener.pickContact2(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}
