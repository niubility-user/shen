package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.listener.NativeSystemListener;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeSystemModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactNativeSystemModule";
    private NativeSystemListener mNativeSystemListener;

    public JDReactNativeSystemModule(ReactApplicationContext reactApplicationContext, NativeSystemListener nativeSystemListener) {
        super(reactApplicationContext);
        this.mNativeSystemListener = nativeSystemListener;
    }

    @ReactMethod
    public void changeStausBarColor(String str, Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.changeStausBarColor(str, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void desDecode(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            String desDecode = nativeSystemListener.desDecode(readableMap.toHashMap());
            if (desDecode != null && callback != null) {
                callback.invoke(desDecode);
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
    public void desEncode(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            String desEncode = nativeSystemListener.desEncode(readableMap.toHashMap());
            if (desEncode != null && callback != null) {
                callback.invoke(desEncode);
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
    public void doPay(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            if (nativeSystemListener.doPay(readableMap.toHashMap())) {
                return;
            }
            callback2.invoke(new Object[0]);
            return;
        }
        callback2.invoke(new Object[0]);
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean doWeiXinLogin(ReadableMap readableMap) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            if (readableMap != null) {
                return nativeSystemListener.doWeiXinLogin(readableMap.toHashMap());
            }
            return nativeSystemListener.doWeiXinLogin(new HashMap());
        }
        return false;
    }

    @ReactMethod
    public void getAndroidID(Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.getAndroidID(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getCacheAddress(Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.getCacheAddress(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getCacheAddressScene(String str, Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.getCacheAddressScene(str, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getCartUUID(Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.getCartUUID(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getCurrentAddress(Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.getCurrentAddress(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getCurrentAddressScene(String str, Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.getCurrentAddressScene(str, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getDeviceID(Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.getDeviceID(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getDeviceInfo(Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.getDeviceInfo(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getDimensions() {
        if (getCurrentActivity() != null) {
            DisplayMetricsHolder.initDisplayMetricsIfNotInitializedForActivity(getCurrentActivity());
        } else if (JDReactHelper.newInstance().getCurrentMyActivity() != null) {
            DisplayMetricsHolder.initDisplayMetricsIfNotInitializedForActivity(JDReactHelper.newInstance().getCurrentMyActivity());
        } else {
            DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(getReactApplicationContext());
        }
        return DisplayMetricsHolder.getDisplayMetricsNativeMap(1.0d);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void isAppDebug(Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.isAppDebug(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void isDebugMode(Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.isDebugMode(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void isWifiVideoAutoPlay(Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.isWifiVideoAutoPlay(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void jumpPay(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            if (nativeSystemListener.jumpPay(readableMap.toHashMap())) {
                return;
            }
            callback2.invoke(new Object[0]);
            return;
        }
        callback2.invoke(new Object[0]);
    }

    @ReactMethod
    public void payOutOrder(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.payOutOrder(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void requestPermission(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.requestPermission(getCurrentActivity(), readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void setBarMode(boolean z, Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.setBarMode(getCurrentActivity(), z, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void setCacheAddress(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.setCacheAddress(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void setInputMode(String str, Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.setInputMode(getCurrentActivity(), str, new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void testLog(String str) {
        String str2 = "log:" + str;
    }

    @ReactMethod
    public void xSwitch(Callback callback, Callback callback2) {
        NativeSystemListener nativeSystemListener = this.mNativeSystemListener;
        if (nativeSystemListener != null) {
            nativeSystemListener.xSwitch(new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        } else if (callback2 != null) {
            callback2.invoke(new Object[0]);
        } else if (callback != null) {
            callback.invoke(Boolean.FALSE);
        }
    }
}
