package com.jingdong.common.jdreactFramework.modules;

import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeCommonPayListener;
import com.jingdong.common.jdreactFramework.utils.JLog;

/* loaded from: classes5.dex */
public class JDReactNativeCommonPayModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final String TAG = "JDReactNativeCommonPayM";
    private NativeCommonPayListener mNativeCommonPayListener;
    private ReactApplicationContext mReactApplicationContext;

    public JDReactNativeCommonPayModule(ReactApplicationContext reactApplicationContext, NativeCommonPayListener nativeCommonPayListener) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
        this.mReactApplicationContext = reactApplicationContext;
        this.mNativeCommonPayListener = nativeCommonPayListener;
    }

    @ReactMethod
    public void doJDPay(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            NativeCommonPayListener nativeCommonPayListener = this.mNativeCommonPayListener;
            if (nativeCommonPayListener != null && readableMap != null && callback != null) {
                nativeCommonPayListener.doJDPay(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
            } else {
                callback2.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            JLog.e(TAG, "doJDPay\uff1a" + e2.getMessage());
        }
    }

    @ReactMethod
    public void doJDPayApp(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            NativeCommonPayListener nativeCommonPayListener = this.mNativeCommonPayListener;
            if (nativeCommonPayListener != null && readableMap != null && callback != null) {
                nativeCommonPayListener.doJDPayApp(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
            } else {
                callback2.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            JLog.e(TAG, "doJDPayApp\uff1a" + e2.getMessage());
        }
    }

    @ReactMethod
    public void doUnionPay(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            NativeCommonPayListener nativeCommonPayListener = this.mNativeCommonPayListener;
            if (nativeCommonPayListener != null && readableMap != null && callback != null) {
                nativeCommonPayListener.doUnionPay(this.mReactApplicationContext.getCurrentActivity(), readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
            } else {
                callback2.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            JLog.e(TAG, "doUnionPay\uff1a" + e2.getMessage());
        }
    }

    @ReactMethod
    public void doWeiXinPay(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            NativeCommonPayListener nativeCommonPayListener = this.mNativeCommonPayListener;
            if (nativeCommonPayListener != null) {
                if (!nativeCommonPayListener.doWeiXinPay(readableMap.toHashMap(), this.mReactApplicationContext) && callback2 != null) {
                    callback2.invoke(new Object[0]);
                }
            } else {
                callback2.invoke(new Object[0]);
            }
        } catch (Exception e2) {
            JLog.e(TAG, "doWeiXinPay\uff1a" + e2.getMessage());
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeCommonPayModule";
    }

    @ReactMethod
    public void isWeiXinInstalled(Callback callback, Callback callback2) {
        NativeCommonPayListener nativeCommonPayListener = this.mNativeCommonPayListener;
        if (nativeCommonPayListener != null) {
            if (nativeCommonPayListener.isWeiXinInstalled() && callback != null) {
                callback.invoke(new Object[0]);
                return;
            } else if (callback2 != null) {
                callback2.invoke(new Object[0]);
                return;
            } else {
                return;
            }
        }
        callback2.invoke(new Object[0]);
    }

    @ReactMethod
    public void isWeiXinSupport(Callback callback, Callback callback2) {
        NativeCommonPayListener nativeCommonPayListener = this.mNativeCommonPayListener;
        if (nativeCommonPayListener != null) {
            if (!nativeCommonPayListener.isWeiXinInstalled()) {
                if (this.mNativeCommonPayListener.isWeiXinSupport() && callback != null) {
                    callback.invoke(new Object[0]);
                    return;
                } else if (callback2 != null) {
                    callback2.invoke(new Object[0]);
                    return;
                } else {
                    return;
                }
            }
            callback2.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void isWeixinPaySupported(Callback callback, Callback callback2) {
        NativeCommonPayListener nativeCommonPayListener = this.mNativeCommonPayListener;
        if (nativeCommonPayListener != null) {
            if (nativeCommonPayListener.isWeixinPaySupported() && callback != null) {
                callback.invoke(new Object[0]);
                return;
            } else if (callback2 != null) {
                callback2.invoke(new Object[0]);
                return;
            } else {
                return;
            }
        }
        callback2.invoke(new Object[0]);
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        NativeCommonPayListener nativeCommonPayListener = this.mNativeCommonPayListener;
        if (nativeCommonPayListener != null) {
            nativeCommonPayListener.onActivityResult(activity, i2, i3, intent);
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    void openWeixinNoPwdPay(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            NativeCommonPayListener nativeCommonPayListener = this.mNativeCommonPayListener;
            if (nativeCommonPayListener == null || readableMap == null || callback == null) {
                return;
            }
            nativeCommonPayListener.openWeixinNoPwdPay(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        } catch (Exception e2) {
            JLog.e(TAG, "openWeixinNoPwdPay\uff1a" + e2.getMessage());
        }
    }
}
