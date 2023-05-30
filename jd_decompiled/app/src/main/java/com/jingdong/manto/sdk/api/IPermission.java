package com.jingdong.manto.sdk.api;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.jingdong.manto.sdk.IMantoSdkBase;
import java.util.List;

/* loaded from: classes16.dex */
public interface IPermission extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public interface PermissionCallBack {
        void onDenied();

        void onGranted();
    }

    String getAppNameAsPrefix();

    boolean hasLocationPermissionWithScene(String str, String str2);

    boolean hasPermission(String str);

    boolean hasPermissions(String[] strArr);

    void onRequestPermissionsResult(Activity activity, int i2, @NonNull String[] strArr, @NonNull int[] iArr);

    void requestLocationPermissionWithScene(Activity activity, PermissionCallBack permissionCallBack, String str, String str2, String str3);

    void requestPermission(Activity activity, String str, String str2, String str3, PermissionCallBack permissionCallBack);

    void requestPermissions(Activity activity, List<String> list, List<String> list2, List<String> list3, PermissionCallBack permissionCallBack);
}
