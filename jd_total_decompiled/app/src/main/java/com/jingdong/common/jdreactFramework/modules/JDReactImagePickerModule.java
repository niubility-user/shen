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
import com.jingdong.common.jdreactFramework.listener.ImagePickerListener;

/* loaded from: classes5.dex */
public class JDReactImagePickerModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private ImagePickerListener mListener;

    public JDReactImagePickerModule(ReactApplicationContext reactApplicationContext, ImagePickerListener imagePickerListener) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
        this.mListener = imagePickerListener;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeImagePickerModule";
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        ImagePickerListener imagePickerListener = this.mListener;
        if (imagePickerListener != null) {
            imagePickerListener.onActivityResult(activity, i2, i3, intent);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        ImagePickerListener imagePickerListener = this.mListener;
        if (imagePickerListener != null) {
            imagePickerListener.onDestroy();
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    public void pickFromAlbum(ReadableMap readableMap, Callback callback, Callback callback2) {
        ImagePickerListener imagePickerListener = this.mListener;
        if (imagePickerListener != null) {
            imagePickerListener.pickFromAlbum(readableMap == null ? null : readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2), getCurrentActivity());
        }
    }

    @ReactMethod
    public void pickFromCamera(ReadableMap readableMap, Callback callback, Callback callback2) {
        ImagePickerListener imagePickerListener = this.mListener;
        if (imagePickerListener != null) {
            imagePickerListener.pickFromCamera(readableMap == null ? null : readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2), getCurrentActivity());
        }
    }
}
