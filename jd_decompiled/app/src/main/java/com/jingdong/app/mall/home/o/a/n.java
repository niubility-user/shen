package com.jingdong.app.mall.home.o.a;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.common.XView2.business.PermissionBridge;
import com.jingdong.common.permission.LBSSceneSwitchHelper;
import com.jingdong.common.permission.PermissionHelper;

/* loaded from: classes4.dex */
public class n {
    private static final Bundle a = PermissionHelper.generateBundle("home", n.class.getSimpleName(), "getAddress", true);

    public static boolean a() {
        return PermissionHelper.hasGrantedLocation(a);
    }

    public static boolean b() {
        return PermissionHelper.hasLocationPermissionWithScene("basicShoppingProcess", PermissionBridge.HOME_COMMON_LBS_ID);
    }

    public static boolean c() {
        return LBSSceneSwitchHelper.getLbsSceneSwitch("basicShoppingProcess");
    }

    public static boolean d() {
        return LBSSceneSwitchHelper.getLbsSceneSwitch("locService");
    }

    public static void e(Activity activity) {
        PermissionHelper.manualRequestLocationPermissionWithScene(activity, null, "basicShoppingProcess", PermissionBridge.HOME_COMMON_LBS_ID, "");
    }
}
