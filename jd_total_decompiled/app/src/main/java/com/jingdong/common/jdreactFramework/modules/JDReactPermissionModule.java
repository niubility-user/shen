package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.JDCallBackHelper;
import com.jingdong.common.jdreactFramework.listener.NativePermissionListener;

/* loaded from: classes5.dex */
public class JDReactPermissionModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactPermissionModule";
    private NativePermissionListener mNativePermissionListener;

    public JDReactPermissionModule(ReactApplicationContext reactApplicationContext, NativePermissionListener nativePermissionListener) {
        super(reactApplicationContext);
        this.mNativePermissionListener = nativePermissionListener;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void getPermissionStatus(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativePermissionListener nativePermissionListener = this.mNativePermissionListener;
        if (nativePermissionListener != null) {
            nativePermissionListener.getPermissionStatus(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void newRequestPermission(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativePermissionListener nativePermissionListener = this.mNativePermissionListener;
        if (nativePermissionListener != null) {
            nativePermissionListener.newRequestPermission(getCurrentActivity(), readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }

    @ReactMethod
    public void requestPermission(ReadableArray readableArray, Callback callback, Callback callback2) {
        NativePermissionListener nativePermissionListener = this.mNativePermissionListener;
        if (nativePermissionListener != null) {
            nativePermissionListener.requestPermission(getCurrentActivity(), readableArray.toArrayList(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}
