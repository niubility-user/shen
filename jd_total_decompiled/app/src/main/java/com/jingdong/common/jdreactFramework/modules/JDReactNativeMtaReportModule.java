package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeMtaReportListener;

/* loaded from: classes5.dex */
public class JDReactNativeMtaReportModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactNativeMtaReportModule";
    private NativeMtaReportListener mNativeMtaReportListener;

    public JDReactNativeMtaReportModule(ReactApplicationContext reactApplicationContext, NativeMtaReportListener nativeMtaReportListener) {
        super(reactApplicationContext);
        this.mNativeMtaReportListener = nativeMtaReportListener;
    }

    @ReactMethod
    void getJDV(Callback callback, Callback callback2) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.getJDV(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    void removeUserProperty(ReadableArray readableArray) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.removeUserProperty(readableArray.toArrayList());
        }
    }

    @ReactMethod
    public void savePageInfoWithSKU(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.savePageInfoWithSKU(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void sendClickData(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendClickData(readableMap.toHashMap());
        }
    }

    @ReactMethod
    void sendClickDataExtend(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendClickDataExtend(readableMap.toHashMap());
        }
    }

    @ReactMethod
    void sendClickDataWithJsonParam(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendClickDataWithJsonParam(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void sendCommonData(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendCommonData(readableMap.toHashMap());
        }
    }

    @ReactMethod
    void sendCommonDataExtend(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendCommonDataExtend(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void sendCommonDataWithExt(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendCommonDataWithExt(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void sendExposureData(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendExposureData(readableMap.toHashMap());
        }
    }

    @ReactMethod
    void sendExposureDataWithJsonParam(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendExposureDataWithJsonParam(readableMap.toHashMap());
        }
    }

    @ReactMethod
    void sendExposureExtend(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendExposureExtend(readableMap.toHashMap());
        }
    }

    @ReactMethod
    void sendOrderDataWithExt(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendOrderDataWithExt(readableMap.toHashMap());
        }
    }

    @ReactMethod
    void sendOrderExtend(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendOrderExtend(readableMap.toHashMap());
        }
    }

    @ReactMethod
    void sendPVExtend(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendPVExtend(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void sendPvData(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendPvData(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void sendVirtualOrderData(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.sendVirtualOrderData(readableMap.toHashMap());
        }
    }

    @ReactMethod
    void setUserProperty(ReadableMap readableMap) {
        NativeMtaReportListener nativeMtaReportListener = this.mNativeMtaReportListener;
        if (nativeMtaReportListener != null) {
            nativeMtaReportListener.setUserProperty(readableMap.toHashMap());
        }
    }
}
