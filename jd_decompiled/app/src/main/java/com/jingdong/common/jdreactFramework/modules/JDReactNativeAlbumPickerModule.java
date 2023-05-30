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
import com.jingdong.common.jdreactFramework.listener.NativeAlbumPickListener;

/* loaded from: classes5.dex */
public class JDReactNativeAlbumPickerModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private NativeAlbumPickListener nativeImagePickListener;

    public JDReactNativeAlbumPickerModule(ReactApplicationContext reactApplicationContext, NativeAlbumPickListener nativeAlbumPickListener) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
        this.nativeImagePickListener = nativeAlbumPickListener;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeAlbumPickerModule";
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        NativeAlbumPickListener nativeAlbumPickListener = this.nativeImagePickListener;
        if (nativeAlbumPickListener != null) {
            nativeAlbumPickListener.onActivityResult(activity, i2, i3, intent);
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    public void pickImage(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeAlbumPickListener nativeAlbumPickListener = this.nativeImagePickListener;
        if (nativeAlbumPickListener != null) {
            nativeAlbumPickListener.onImagePicked(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2), getCurrentActivity());
        }
    }

    @ReactMethod
    public void previewImage(ReadableMap readableMap, Callback callback, Callback callback2) {
        NativeAlbumPickListener nativeAlbumPickListener = this.nativeImagePickListener;
        if (nativeAlbumPickListener != null) {
            nativeAlbumPickListener.previewImage(readableMap.toHashMap(), new JDCallBackHelper(callback), new JDCallBackHelper(callback2), getCurrentActivity());
        }
    }
}
