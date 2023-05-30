package com.jingdong.common.jdreactFramework.modules;

import android.text.TextUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeUserLoginListener;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeUserLoginModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactNativeUserLoginModule";
    private NativeUserLoginListener mJDReactNativeUserLoginListener;

    public JDReactNativeUserLoginModule(ReactApplicationContext reactApplicationContext, NativeUserLoginListener nativeUserLoginListener) {
        super(reactApplicationContext);
        this.mJDReactNativeUserLoginListener = nativeUserLoginListener;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void getUserInfo(Callback callback, Callback callback2) {
        NativeUserLoginListener nativeUserLoginListener = this.mJDReactNativeUserLoginListener;
        if (nativeUserLoginListener != null) {
            HashMap userInfo = nativeUserLoginListener.getUserInfo();
            if (callback != null && userInfo != null) {
                callback.invoke(Arguments.makeNativeMap(userInfo));
                return;
            } else if (userInfo == null) {
                callback2.invoke(new Object[0]);
                return;
            } else {
                return;
            }
        }
        callback2.invoke(new Object[0]);
    }

    @ReactMethod
    public void getUserPin(Callback callback, Callback callback2) {
        NativeUserLoginListener nativeUserLoginListener = this.mJDReactNativeUserLoginListener;
        if (nativeUserLoginListener != null) {
            String userPin = nativeUserLoginListener.getUserPin();
            if (callback != null && !TextUtils.isEmpty(userPin)) {
                callback.invoke("{\"pin\":" + userPin + "}");
                return;
            } else if (TextUtils.isEmpty(userPin)) {
                callback2.invoke(new Object[0]);
                return;
            } else {
                return;
            }
        }
        callback2.invoke(new Object[0]);
    }

    @ReactMethod
    public void isLogin(Callback callback, Callback callback2) {
        NativeUserLoginListener nativeUserLoginListener = this.mJDReactNativeUserLoginListener;
        if (nativeUserLoginListener != null) {
            if (nativeUserLoginListener.isLogin()) {
                callback.invoke(new Object[0]);
                return;
            } else {
                callback2.invoke(new Object[0]);
                return;
            }
        }
        callback2.invoke(new Object[0]);
    }

    @ReactMethod
    public void login(Callback callback, Callback callback2) {
        NativeUserLoginListener nativeUserLoginListener = this.mJDReactNativeUserLoginListener;
        if (nativeUserLoginListener != null) {
            nativeUserLoginListener.login(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        } else {
            callback2.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void loginWithPhoneNumber(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeUserLoginListener nativeUserLoginListener = this.mJDReactNativeUserLoginListener;
        if (nativeUserLoginListener != null) {
            nativeUserLoginListener.login(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        } else {
            callback2.invoke(new Object[0]);
        }
    }
}
