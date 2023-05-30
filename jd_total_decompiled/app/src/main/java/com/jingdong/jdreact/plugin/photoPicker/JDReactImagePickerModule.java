package com.jingdong.jdreact.plugin.photoPicker;

import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jingdong.common.jdreactFramework.utils.CommonUtil;
import com.jingdong.jdreact.plugin.photoPicker.ImagePicker;

/* loaded from: classes13.dex */
public class JDReactImagePickerModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    public JDReactImagePickerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeImagePickerModule";
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        ImagePicker.handleActivityResult(activity, i2, i3, intent);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        ImagePicker.release();
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    public void pickFromAlbum(ReadableMap readableMap, final Callback callback, final Callback callback2) {
        Activity currentActivity = getCurrentActivity();
        boolean z = false;
        if (currentActivity == null) {
            CommonUtil.invokeCallback(callback2, new Object[0]);
            return;
        }
        if (readableMap != null && readableMap.hasKey("crop") && readableMap.getBoolean("crop")) {
            z = true;
        }
        ImagePicker.pickFromAlbum(currentActivity, z, new ImagePicker.PickImageCallback() { // from class: com.jingdong.jdreact.plugin.photoPicker.JDReactImagePickerModule.3
            @Override // com.jingdong.jdreact.plugin.photoPicker.ImagePicker.PickImageCallback
            public void onPickImage(boolean z2, String str) {
                if (z2) {
                    CommonUtil.invokeCallback(callback, str);
                } else {
                    CommonUtil.invokeCallback(callback2, str);
                }
            }
        }, readableMap.toHashMap());
    }

    @ReactMethod
    public void pickFromCamera(ReadableMap readableMap, final Callback callback, final Callback callback2) {
        Activity currentActivity = getCurrentActivity();
        boolean z = false;
        if (currentActivity == null) {
            CommonUtil.invokeCallback(callback2, new Object[0]);
            return;
        }
        if (readableMap != null && readableMap.hasKey("crop") && readableMap.getBoolean("crop")) {
            z = true;
        }
        ImagePicker.pickFromCamera(currentActivity, z, true, new ImagePicker.PickImageCallback() { // from class: com.jingdong.jdreact.plugin.photoPicker.JDReactImagePickerModule.1
            @Override // com.jingdong.jdreact.plugin.photoPicker.ImagePicker.PickImageCallback
            public void onPickImage(boolean z2, String str) {
                if (z2) {
                    CommonUtil.invokeCallback(callback, str);
                } else {
                    CommonUtil.invokeCallback(callback2, str);
                }
            }
        }, readableMap.toHashMap());
    }

    @ReactMethod
    public void pickFromCamera(ReadableMap readableMap, boolean z, final Callback callback, final Callback callback2) {
        Activity currentActivity = getCurrentActivity();
        boolean z2 = false;
        if (currentActivity == null) {
            CommonUtil.invokeCallback(callback2, new Object[0]);
            return;
        }
        if (readableMap != null && readableMap.hasKey("crop") && readableMap.getBoolean("crop")) {
            z2 = true;
        }
        ImagePicker.pickFromCamera(currentActivity, z2, z, new ImagePicker.PickImageCallback() { // from class: com.jingdong.jdreact.plugin.photoPicker.JDReactImagePickerModule.2
            @Override // com.jingdong.jdreact.plugin.photoPicker.ImagePicker.PickImageCallback
            public void onPickImage(boolean z3, String str) {
                if (z3) {
                    CommonUtil.invokeCallback(callback, str);
                } else {
                    CommonUtil.invokeCallback(callback2, str);
                }
            }
        }, readableMap.toHashMap());
    }
}
