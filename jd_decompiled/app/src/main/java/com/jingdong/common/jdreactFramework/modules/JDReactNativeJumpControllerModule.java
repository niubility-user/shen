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
import com.jingdong.common.jdreactFramework.listener.NativeJumpControllerListener;
import com.jingdong.common.jdreactFramework.utils.CommonUtil;

/* loaded from: classes5.dex */
public class JDReactNativeJumpControllerModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final String TAG = "JDReactNativeJumpControllerModule";
    private NativeJumpControllerListener mNativeJumpControllerListener;

    public JDReactNativeJumpControllerModule(ReactApplicationContext reactApplicationContext, NativeJumpControllerListener nativeJumpControllerListener) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
        this.mNativeJumpControllerListener = nativeJumpControllerListener;
    }

    private void jumpToWebPage(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.toHomePage();
        }
    }

    private void jumptoHomePage() {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.toHomePage();
        }
    }

    @ReactMethod
    public void back(ReadableMap readableMap, Callback callback, Callback callback2) {
        boolean z;
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            z = nativeJumpControllerListener.back(getCurrentActivity(), readableMap == null ? null : readableMap.toHashMap());
        } else {
            z = false;
        }
        if (z) {
            CommonUtil.invokeCallback(callback, new Object[0]);
        } else {
            CommonUtil.invokeCallback(callback2, new Object[0]);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void jumoToFavouritesActivity(ReadableMap readableMap) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jumoToFavouritesActivity(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void jump(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jump(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void jumpJDRouter(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            if (nativeJumpControllerListener.jumpJDRouter(readableMap.toHashMap()) && callback != null) {
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
    public void jumpJDRouterWithCallback(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jumpJDRouterWithCallback(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        } else {
            callback2.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void jumpParamJson(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jumpParamJson(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void jumpPayVC(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            if (nativeJumpControllerListener.jumpPayVC(readableMap.toHashMap()) && callback != null) {
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
    public void jumpRoute(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            if (nativeJumpControllerListener.jumpRoute(readableMap.toHashMap()) && callback != null) {
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
    public void jumpToDeeplink(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jumpToDeeplink(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void jumpToGameChargeActivity(ReadableMap readableMap) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jumpToGameChargeActivity(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void jumpToHomepage(ReadableMap readableMap) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.toHomePage();
        }
    }

    @ReactMethod
    public void jumpToJShopHome(ReadableMap readableMap) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jumpToJShopHome(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void jumpToJShopSignUp(ReadableMap readableMap) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jumpToJShopSignUp(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void jumpToJump(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            if (nativeJumpControllerListener.jumpToJump(readableMap.toHashMap()) && callback != null) {
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
    public void jumpToMiniProgram(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jumpToMiniProgram(getCurrentActivity(), readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void jumpToOpenapp(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jumpToOpenapp(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void jumpToOpenappClearStackAndroid(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jumpToOpenappClearStackAndroid(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void jumpToProductDetail(ReadableMap readableMap) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jumpToProductDetail(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void jumpToSelectAddress(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jumpToSelectAddress(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void jumpToVirtualOrderDetail(ReadableMap readableMap) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.jumpToVirtualOrderDetail(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void jumpToWeb(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener == null || !nativeJumpControllerListener.jumpToWebPage(readableMap.toHashMap(), getCurrentActivity())) {
            if (callback2 != null) {
                callback2.invoke(new Object[0]);
            }
        } else if (callback != null) {
            callback.invoke(new Object[0]);
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.onActivityResult(activity, i2, i3, intent);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.onCatalystInstanceDestroy();
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    public void selectChargeCardCoupon(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.selectChargeCardCoupon(readableMap.toHashMap());
        }
    }

    @ReactMethod
    public void selectChargeCity(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeJumpControllerListener nativeJumpControllerListener = this.mNativeJumpControllerListener;
        if (nativeJumpControllerListener != null) {
            nativeJumpControllerListener.selectChargeCity(readableMap.toHashMap());
        }
    }
}
