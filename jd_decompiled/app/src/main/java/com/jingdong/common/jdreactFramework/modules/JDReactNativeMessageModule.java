package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeMessageListener;

/* loaded from: classes5.dex */
public class JDReactNativeMessageModule extends ReactContextBaseJavaModule {
    private NativeMessageListener mNativeMessageListener;

    public JDReactNativeMessageModule(ReactApplicationContext reactApplicationContext, NativeMessageListener nativeMessageListener) {
        super(reactApplicationContext);
        this.mNativeMessageListener = nativeMessageListener;
    }

    @ReactMethod
    private void stopMsgObserving() {
        NativeMessageListener nativeMessageListener = this.mNativeMessageListener;
        if (nativeMessageListener != null) {
            nativeMessageListener.stopMsgObserving();
        }
    }

    @ReactMethod
    public void getJDMessageRedDot(Callback callback, Callback callback2) {
        NativeMessageListener nativeMessageListener = this.mNativeMessageListener;
        if (nativeMessageListener != null) {
            nativeMessageListener.getJDMessageRedDot(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeMessageModule";
    }

    @ReactMethod
    public void startMsgObserving() {
        NativeMessageListener nativeMessageListener = this.mNativeMessageListener;
        if (nativeMessageListener != null) {
            nativeMessageListener.startMsgObserving();
        }
    }
}
