package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeCookieListener;

/* loaded from: classes5.dex */
public class JDReactCookieModule extends ReactContextBaseJavaModule {
    private NativeCookieListener mNativeCookieListener;

    public JDReactCookieModule(ReactApplicationContext reactApplicationContext, NativeCookieListener nativeCookieListener) {
        super(reactApplicationContext);
        this.mNativeCookieListener = nativeCookieListener;
    }

    @ReactMethod
    public void clearCookie(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeCookieListener nativeCookieListener = this.mNativeCookieListener;
        if (nativeCookieListener != null) {
            nativeCookieListener.clearCookie(readableMap == null ? null : readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void clearWebkitCookie(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeCookieListener nativeCookieListener = this.mNativeCookieListener;
        if (nativeCookieListener != null) {
            nativeCookieListener.clearWebkitCookie(readableMap == null ? null : readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getCookie(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeCookieListener nativeCookieListener = this.mNativeCookieListener;
        if (nativeCookieListener != null) {
            nativeCookieListener.getCookie(readableMap == null ? null : readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactCookieModule";
    }

    @ReactMethod
    public void getUnionCookie(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeCookieListener nativeCookieListener = this.mNativeCookieListener;
        if (nativeCookieListener != null) {
            nativeCookieListener.getUnionCookie(readableMap == null ? null : readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getWebkitCookie(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeCookieListener nativeCookieListener = this.mNativeCookieListener;
        if (nativeCookieListener != null) {
            nativeCookieListener.getWebkitCookie(readableMap == null ? null : readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void setCookie(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeCookieListener nativeCookieListener = this.mNativeCookieListener;
        if (nativeCookieListener != null) {
            nativeCookieListener.setCookie(readableMap == null ? null : readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void setWebkitCookie(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeCookieListener nativeCookieListener = this.mNativeCookieListener;
        if (nativeCookieListener != null) {
            nativeCookieListener.setWebkitCookie(readableMap == null ? null : readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}
