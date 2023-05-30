package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeVoiceRecognizeListener;

/* loaded from: classes5.dex */
public class JDReactNativeVoiceRecognizeModule extends ReactContextBaseJavaModule {
    private NativeVoiceRecognizeListener mNativeVoiceRecognizeListener;

    public JDReactNativeVoiceRecognizeModule(ReactApplicationContext reactApplicationContext, NativeVoiceRecognizeListener nativeVoiceRecognizeListener) {
        super(reactApplicationContext);
        this.mNativeVoiceRecognizeListener = nativeVoiceRecognizeListener;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeVoiceRecognizeModule";
    }

    @ReactMethod
    public void startVoiceRecognize(Callback callback, Callback callback2) {
        NativeVoiceRecognizeListener nativeVoiceRecognizeListener = this.mNativeVoiceRecognizeListener;
        if (nativeVoiceRecognizeListener != null) {
            nativeVoiceRecognizeListener.startVoiceRecognize(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void stopVoiceRecognize(Callback callback, Callback callback2) {
        NativeVoiceRecognizeListener nativeVoiceRecognizeListener = this.mNativeVoiceRecognizeListener;
        if (nativeVoiceRecognizeListener != null) {
            nativeVoiceRecognizeListener.stopVoiceRecognize(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}
