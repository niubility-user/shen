package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.AresCommonUtils;
import com.jingdong.jdreact.plugin.photoPicker.ImagePicker;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeImagePickerListener implements ImagePickerListener, JDFlutterCall {
    @Override // com.jingdong.common.jdreactFramework.listener.ImagePickerListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        ImagePicker.handleActivityResult(activity, i2, i3, intent);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.ImagePickerListener
    public void onDestroy() {
        ImagePicker.release();
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.equals("pickFromCamera")) {
            pickFromCamera(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeImagePickerListener.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0]);
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeImagePickerListener.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error((objArr == null || objArr.length <= 0) ? null : String.valueOf(objArr[0]), "", "");
                }
            }, activity);
        } else if (str.equals("pickFromAlbum")) {
            pickFromAlbum(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeImagePickerListener.5
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0]);
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeImagePickerListener.6
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error((objArr == null || objArr.length <= 0) ? null : String.valueOf(objArr[0]), "", "");
                }
            }, activity);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.ImagePickerListener
    public void pickFromAlbum(HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2, Activity activity) {
        if (activity == null) {
            activity = JDReactHelper.newInstance().getCurrentMyActivity();
        }
        boolean z = false;
        if (activity == null) {
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
            return;
        }
        if (hashMap != null && hashMap.containsKey("crop") && ((Boolean) hashMap.get("crop")).booleanValue()) {
            z = true;
        }
        ImagePicker.pickFromAlbum(activity, z, new ImagePicker.PickImageCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeImagePickerListener.2
            @Override // com.jingdong.jdreact.plugin.photoPicker.ImagePicker.PickImageCallback
            public void onPickImage(boolean z2, String str) {
                if (z2) {
                    AresCommonUtils.invokeCallback(jDCallback, str);
                } else {
                    AresCommonUtils.invokeCallback(jDCallback2, str);
                }
            }
        });
    }

    @Override // com.jingdong.common.jdreactFramework.listener.ImagePickerListener
    public void pickFromCamera(HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2, Activity activity) {
        if (activity == null) {
            activity = JDReactHelper.newInstance().getCurrentMyActivity();
        }
        boolean z = false;
        if (activity == null) {
            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
            return;
        }
        if (hashMap != null && hashMap.containsKey("crop") && ((Boolean) hashMap.get("crop")).booleanValue()) {
            z = true;
        }
        ImagePicker.pickFromCamera(activity, z, new ImagePicker.PickImageCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeImagePickerListener.1
            @Override // com.jingdong.jdreact.plugin.photoPicker.ImagePicker.PickImageCallback
            public void onPickImage(boolean z2, String str) {
                if (z2) {
                    AresCommonUtils.invokeCallback(jDCallback, str);
                } else {
                    AresCommonUtils.invokeCallback(jDCallback2, str);
                }
            }
        });
    }
}
