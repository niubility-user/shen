package com.jingdong.common.XView2.business;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.common.permission.PermissionHelper;

/* loaded from: classes5.dex */
public class PermissionBridge {
    public static final String HOME_COMMON_LBS_ID = "73c534b018663b2994a61d87c16ff079";
    private static final Bundle sHomeLbsBundle = PermissionHelper.generateBundle("home", PermissionBridge.class.getSimpleName(), "getAddress", true);

    public static boolean hasGrantedLocation() {
        return PermissionHelper.hasGrantedLocation(sHomeLbsBundle);
    }

    public static void requestLocationWithScene(Activity activity) {
        PermissionHelper.manualRequestLocationPermissionWithScene(activity, null, "basicShoppingProcess", HOME_COMMON_LBS_ID, "");
    }

    public static boolean hasGrantedLocation(Activity activity, PermissionHelper.PermissionResultCallBack permissionResultCallBack) {
        return PermissionHelper.hasGrantedLocation(activity, sHomeLbsBundle, permissionResultCallBack);
    }
}
