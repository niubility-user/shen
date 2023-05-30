package com.jingdong.common.jdreactFramework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdreactFramework.listener.NativeUploadExceptionListener;

/* loaded from: classes5.dex */
public class JDReactNativeUploadExceptionModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactNativeUploadExceptionModule";
    private NativeUploadExceptionListener mJDReactNativeUploadExceptionListener;

    public JDReactNativeUploadExceptionModule(ReactApplicationContext reactApplicationContext, NativeUploadExceptionListener nativeUploadExceptionListener) {
        super(reactApplicationContext);
        this.mJDReactNativeUploadExceptionListener = nativeUploadExceptionListener;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void uploadException(String str, Callback callback, Callback callback2) {
        NativeUploadExceptionListener nativeUploadExceptionListener = this.mJDReactNativeUploadExceptionListener;
        if (nativeUploadExceptionListener == null || !nativeUploadExceptionListener.uploadException(str) || callback == null) {
            return;
        }
        callback.invoke(DYConstants.DY_TRUE);
    }

    @ReactMethod
    public void uploadExceptionNew(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeUploadExceptionListener nativeUploadExceptionListener = this.mJDReactNativeUploadExceptionListener;
        if (nativeUploadExceptionListener == null || !nativeUploadExceptionListener.uploadExceptionNew(readableMap.toHashMap()) || callback == null) {
            return;
        }
        callback.invoke(DYConstants.DY_TRUE);
    }
}
