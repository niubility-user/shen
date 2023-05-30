package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.jingdong.common.jdreactFramework.listener.NativeCustomKeyboardModuleListener;

/* loaded from: classes5.dex */
public class JDReactNativeCustomKeyboardModule extends ReactContextBaseJavaModule {
    private NativeCustomKeyboardModuleListener mNativeCustomKeyboardModuleListener;

    public JDReactNativeCustomKeyboardModule(ReactApplicationContext reactApplicationContext, NativeCustomKeyboardModuleListener nativeCustomKeyboardModuleListener) {
        super(reactApplicationContext);
        this.mNativeCustomKeyboardModuleListener = nativeCustomKeyboardModuleListener;
    }

    @ReactMethod
    public void bindKeyBoard(int i2, String str) {
        NativeCustomKeyboardModuleListener nativeCustomKeyboardModuleListener = this.mNativeCustomKeyboardModuleListener;
        if (nativeCustomKeyboardModuleListener != null) {
            nativeCustomKeyboardModuleListener.bindKeyBoard(getReactApplicationContext(), i2, str, getCurrentActivity());
        }
    }

    @ReactMethod
    public void fromBackGroundToForeGround(int i2) {
        NativeCustomKeyboardModuleListener nativeCustomKeyboardModuleListener = this.mNativeCustomKeyboardModuleListener;
        if (nativeCustomKeyboardModuleListener != null) {
            nativeCustomKeyboardModuleListener.fromBackGroundToForeGround(getReactApplicationContext(), i2, getCurrentActivity());
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeCustomKeyboardModule";
    }

    @ReactMethod
    public void showSystemKeyboard() {
        NativeCustomKeyboardModuleListener nativeCustomKeyboardModuleListener = this.mNativeCustomKeyboardModuleListener;
        if (nativeCustomKeyboardModuleListener != null) {
            nativeCustomKeyboardModuleListener.showSystemKeyboard(getReactApplicationContext());
        }
    }

    @ReactMethod
    public void switchSystemKeyboard(int i2) {
        NativeCustomKeyboardModuleListener nativeCustomKeyboardModuleListener = this.mNativeCustomKeyboardModuleListener;
        if (nativeCustomKeyboardModuleListener != null) {
            nativeCustomKeyboardModuleListener.switchSystemKeyboard(getReactApplicationContext(), i2);
        }
    }

    @ReactMethod
    public void unBindKeyBoard(int i2) {
        NativeCustomKeyboardModuleListener nativeCustomKeyboardModuleListener = this.mNativeCustomKeyboardModuleListener;
        if (nativeCustomKeyboardModuleListener != null) {
            nativeCustomKeyboardModuleListener.unBindKeyBoard(getReactApplicationContext(), i2, getCurrentActivity());
        }
    }
}
