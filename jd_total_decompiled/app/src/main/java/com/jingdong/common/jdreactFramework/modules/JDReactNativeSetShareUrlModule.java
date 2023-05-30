package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeSetShareUrlListener;

/* loaded from: classes5.dex */
public class JDReactNativeSetShareUrlModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactNativeSetShareUrlModule";
    private NativeSetShareUrlListener mNativeSetShareUrlListener;

    public JDReactNativeSetShareUrlModule(ReactApplicationContext reactApplicationContext, NativeSetShareUrlListener nativeSetShareUrlListener) {
        super(reactApplicationContext);
        this.mNativeSetShareUrlListener = nativeSetShareUrlListener;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void setUrl(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeSetShareUrlListener nativeSetShareUrlListener = this.mNativeSetShareUrlListener;
        if (nativeSetShareUrlListener != null) {
            nativeSetShareUrlListener.setUrl(getCurrentActivity(), readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}
