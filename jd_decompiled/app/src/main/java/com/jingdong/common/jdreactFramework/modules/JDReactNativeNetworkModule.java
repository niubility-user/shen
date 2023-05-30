package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeNetworkListener;

/* loaded from: classes5.dex */
public class JDReactNativeNetworkModule extends ReactContextBaseJavaModule {
    private static final int NORMAL_ERROR_CODE = 0;
    private static final String TAG = "JDReactNativeNetworkModule";
    private NativeNetworkListener mNativeNetworkListener;

    public JDReactNativeNetworkModule(ReactApplicationContext reactApplicationContext, NativeNetworkListener nativeNetworkListener) {
        super(reactApplicationContext);
        this.mNativeNetworkListener = nativeNetworkListener;
    }

    @ReactMethod
    public void fetch(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeNetworkListener nativeNetworkListener = this.mNativeNetworkListener;
        if (nativeNetworkListener != null) {
            nativeNetworkListener.fetch(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void fetchWithoutCertificate(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeNetworkListener nativeNetworkListener = this.mNativeNetworkListener;
        if (nativeNetworkListener != null) {
            nativeNetworkListener.fetchWithoutCertificate(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void isBeta(Callback callback, Callback callback2) {
        NativeNetworkListener nativeNetworkListener = this.mNativeNetworkListener;
        if (nativeNetworkListener != null) {
            boolean isBeta = nativeNetworkListener.isBeta();
            if (callback != null) {
                callback.invoke(Boolean.valueOf(isBeta));
                return;
            }
        }
        if (callback2 != null) {
            callback2.invoke(0);
        }
    }

    @ReactMethod
    public void isBetaHost(Callback callback, Callback callback2) {
        NativeNetworkListener nativeNetworkListener = this.mNativeNetworkListener;
        if (nativeNetworkListener != null) {
            boolean isBetaHost = nativeNetworkListener.isBetaHost();
            if (callback != null) {
                callback.invoke(Boolean.valueOf(isBetaHost));
                return;
            }
        }
        if (callback2 != null) {
            callback2.invoke(0);
        }
    }
}
