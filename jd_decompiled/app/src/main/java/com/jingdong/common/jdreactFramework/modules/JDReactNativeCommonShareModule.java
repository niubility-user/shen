package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeCommonShareListener;

/* loaded from: classes5.dex */
public class JDReactNativeCommonShareModule extends ReactContextBaseJavaModule {
    private NativeCommonShareListener mNativeCommonShareListener;

    public JDReactNativeCommonShareModule(ReactApplicationContext reactApplicationContext, NativeCommonShareListener nativeCommonShareListener) {
        super(reactApplicationContext);
        this.mNativeCommonShareListener = nativeCommonShareListener;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeCommonShareModule";
    }

    @ReactMethod
    public void sendWxShare(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeCommonShareListener nativeCommonShareListener = this.mNativeCommonShareListener;
        if (nativeCommonShareListener != null) {
            nativeCommonShareListener.sendWxShare(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        } else {
            callback2.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void showCustomShareDialog(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeCommonShareListener nativeCommonShareListener = this.mNativeCommonShareListener;
        if (nativeCommonShareListener != null) {
            nativeCommonShareListener.showCustomShareDialog(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        } else {
            callback2.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void showShareDialog(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeCommonShareListener nativeCommonShareListener = this.mNativeCommonShareListener;
        if (nativeCommonShareListener != null) {
            nativeCommonShareListener.showShareDialog(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        } else {
            callback2.invoke(new Object[0]);
        }
    }
}
