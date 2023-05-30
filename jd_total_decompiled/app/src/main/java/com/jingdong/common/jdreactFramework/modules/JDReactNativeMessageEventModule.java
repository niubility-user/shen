package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeMesssageEventListener;

/* loaded from: classes5.dex */
public class JDReactNativeMessageEventModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactNativeMessageEventModule";
    private NativeMesssageEventListener mNativeMesssageEventListener;

    public JDReactNativeMessageEventModule(ReactApplicationContext reactApplicationContext, NativeMesssageEventListener nativeMesssageEventListener) {
        super(reactApplicationContext);
        this.mNativeMesssageEventListener = nativeMesssageEventListener;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void sendEventToJDMinProgram(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeMesssageEventListener nativeMesssageEventListener = this.mNativeMesssageEventListener;
        if (nativeMesssageEventListener != null) {
            nativeMesssageEventListener.sendEventToJDMinProgram(readableMap.toHashMap(), getCurrentActivity(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void sendEventToNative(String str, ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeMesssageEventListener nativeMesssageEventListener = this.mNativeMesssageEventListener;
        if (nativeMesssageEventListener != null) {
            nativeMesssageEventListener.sendEventToNative(str, readableMap, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}
