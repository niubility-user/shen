package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeNetworkWithSignListener;

/* loaded from: classes5.dex */
public class JDReactNativeNetworkWithSignModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactNativeNetworkWithSignModule";
    private NativeNetworkWithSignListener mNativeNetworkWithSignListener;

    public JDReactNativeNetworkWithSignModule(ReactApplicationContext reactApplicationContext, NativeNetworkWithSignListener nativeNetworkWithSignListener) {
        super(reactApplicationContext);
        this.mNativeNetworkWithSignListener = nativeNetworkWithSignListener;
    }

    @ReactMethod
    public void fetch(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeNetworkWithSignListener nativeNetworkWithSignListener = this.mNativeNetworkWithSignListener;
        if (nativeNetworkWithSignListener != null) {
            nativeNetworkWithSignListener.fetch(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }
}
