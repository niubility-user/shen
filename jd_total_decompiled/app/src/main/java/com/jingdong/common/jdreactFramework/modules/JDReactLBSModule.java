package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativeLBSListener;

/* loaded from: classes5.dex */
public class JDReactLBSModule extends ReactContextBaseJavaModule {
    private NativeLBSListener mLBSListener;

    public JDReactLBSModule(ReactApplicationContext reactApplicationContext, NativeLBSListener nativeLBSListener) {
        super(reactApplicationContext);
        this.mLBSListener = nativeLBSListener;
    }

    @ReactMethod
    public void getAddress(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeLBSListener nativeLBSListener = this.mLBSListener;
        if (nativeLBSListener != null) {
            nativeLBSListener.getAddress(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getAddressID(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeLBSListener nativeLBSListener = this.mLBSListener;
        if (nativeLBSListener != null) {
            nativeLBSListener.getAddressID(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getAddressList(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeLBSListener nativeLBSListener = this.mLBSListener;
        if (nativeLBSListener != null) {
            nativeLBSListener.getAddressList(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getLastLocation(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeLBSListener nativeLBSListener = this.mLBSListener;
        if (nativeLBSListener != null) {
            nativeLBSListener.getLastLocation(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void getLatLng(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeLBSListener nativeLBSListener = this.mLBSListener;
        if (nativeLBSListener != null) {
            nativeLBSListener.getLatLng(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactLBSModule";
    }

    @ReactMethod
    public void hasLocationPermissionWithScene(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeLBSListener nativeLBSListener = this.mLBSListener;
        if (nativeLBSListener != null) {
            nativeLBSListener.hasLocationPermissionWithScene(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void hasLocationPermissionWithSceneV2(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeLBSListener nativeLBSListener = this.mLBSListener;
        if (nativeLBSListener != null) {
            nativeLBSListener.hasLocationPermissionWithSceneV2(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void manualRequestLocationPermissionWithScene(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeLBSListener nativeLBSListener = this.mLBSListener;
        if (nativeLBSListener != null) {
            nativeLBSListener.manualRequestLocationPermissionWithScene(getCurrentActivity(), readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void requestLocationPermissionWithScene(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeLBSListener nativeLBSListener = this.mLBSListener;
        if (nativeLBSListener != null) {
            nativeLBSListener.requestLocationPermissionWithScene(getCurrentActivity(), readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}
