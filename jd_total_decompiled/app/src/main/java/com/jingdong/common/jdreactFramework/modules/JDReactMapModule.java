package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeMapListener;

/* loaded from: classes5.dex */
public class JDReactMapModule extends ReactContextBaseJavaModule {
    private NativeMapListener mNativeMapListener;

    public JDReactMapModule(ReactApplicationContext reactApplicationContext, NativeMapListener nativeMapListener) {
        super(reactApplicationContext);
        this.mNativeMapListener = nativeMapListener;
    }

    @ReactMethod
    public void calculateDistance(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeMapListener nativeMapListener = this.mNativeMapListener;
        if (nativeMapListener != null) {
            nativeMapListener.calculateDistance(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactMapModule";
    }

    @ReactMethod
    public void takeSnapshot(int i2, ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeMapListener nativeMapListener = this.mNativeMapListener;
        if (nativeMapListener != null) {
            nativeMapListener.takeSnapshot(i2, readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2), getReactApplicationContext());
        }
    }
}
