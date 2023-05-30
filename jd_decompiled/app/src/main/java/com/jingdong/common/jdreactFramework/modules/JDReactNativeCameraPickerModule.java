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
import com.jingdong.common.jdreactFramework.listener.NativeCameraPickListener;

/* loaded from: classes5.dex */
public class JDReactNativeCameraPickerModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private NativeCameraPickListener nativeImagePickListener;

    public JDReactNativeCameraPickerModule(ReactApplicationContext reactApplicationContext, NativeCameraPickListener nativeCameraPickListener) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
        this.nativeImagePickListener = nativeCameraPickListener;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeCameraPickerModule";
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        NativeCameraPickListener nativeCameraPickListener = this.nativeImagePickListener;
        if (nativeCameraPickListener != null) {
            nativeCameraPickListener.onActivityResult(activity, i2, i3, intent);
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    public void pickImage(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeCameraPickListener nativeCameraPickListener = this.nativeImagePickListener;
        if (nativeCameraPickListener != null) {
            nativeCameraPickListener.onImagePicked(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2));
        }
    }
}
