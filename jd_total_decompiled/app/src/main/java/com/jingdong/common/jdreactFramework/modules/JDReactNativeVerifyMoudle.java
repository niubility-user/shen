package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.listener.NativeVerifyListener;

/* loaded from: classes5.dex */
public class JDReactNativeVerifyMoudle extends ReactContextBaseJavaModule {
    private NativeVerifyListener mNativeVerifyListener;

    /* loaded from: classes5.dex */
    public interface VerfyInterface {
        void freeVerify();

        void showVeriyDialog(JDCallback jDCallback, JDCallback jDCallback2, String str, String str2, String str3, String str4);
    }

    public JDReactNativeVerifyMoudle(ReactApplicationContext reactApplicationContext, NativeVerifyListener nativeVerifyListener) {
        super(reactApplicationContext);
        this.mNativeVerifyListener = nativeVerifyListener;
    }

    @ReactMethod
    public void free(Callback callback, Callback callback2) {
        NativeVerifyListener nativeVerifyListener = this.mNativeVerifyListener;
        if (nativeVerifyListener != null) {
            nativeVerifyListener.free(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeVerifyModule";
    }

    @ReactMethod
    public void getSeesionID(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeVerifyListener nativeVerifyListener = this.mNativeVerifyListener;
        if (nativeVerifyListener != null) {
            nativeVerifyListener.getSeesionID(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void verify(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeVerifyListener nativeVerifyListener = this.mNativeVerifyListener;
        if (nativeVerifyListener != null) {
            nativeVerifyListener.verify(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}
