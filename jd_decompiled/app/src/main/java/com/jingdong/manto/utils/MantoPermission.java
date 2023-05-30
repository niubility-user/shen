package com.jingdong.manto.utils;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.R;
import com.jingdong.manto.sdk.api.IPermission;
import java.util.ArrayList;

/* loaded from: classes16.dex */
public class MantoPermission {
    public static Bundle getPermissionInfo(String str, String str2) {
        String format;
        Bundle bundle = new Bundle();
        str2.hashCode();
        str2.hashCode();
        char c2 = '\uffff';
        switch (str2.hashCode()) {
            case -1888586689:
                if (str2.equals("android.permission.ACCESS_FINE_LOCATION")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1561629405:
                if (str2.equals("android.permission.SYSTEM_ALERT_WINDOW")) {
                    c2 = 1;
                    break;
                }
                break;
            case -798669607:
                if (str2.equals("android.permission.BLUETOOTH_CONNECT")) {
                    c2 = 2;
                    break;
                }
                break;
            case -63024214:
                if (str2.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                    c2 = 3;
                    break;
                }
                break;
            case 463403621:
                if (str2.equals("android.permission.CAMERA")) {
                    c2 = 4;
                    break;
                }
                break;
            case 1166454870:
                if (str2.equals("android.permission.BLUETOOTH_ADVERTISE")) {
                    c2 = 5;
                    break;
                }
                break;
            case 1831139720:
                if (str2.equals("android.permission.RECORD_AUDIO")) {
                    c2 = 6;
                    break;
                }
                break;
            case 2062356686:
                if (str2.equals("android.permission.BLUETOOTH_SCAN")) {
                    c2 = 7;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 3:
                bundle.putString("title", com.jingdong.a.g().getResources().getString(R.string.manto_permission_location_title));
                format = String.format(com.jingdong.a.g().getResources().getString(R.string.manto_permission_location_msg), str, str);
                break;
            case 1:
                bundle.putString("title", com.jingdong.a.g().getResources().getString(R.string.manto_permission_alert_window_title));
                format = String.format(com.jingdong.a.g().getResources().getString(R.string.manto_permission_alert_window_msg), str, str);
                break;
            case 2:
                bundle.putString("title", com.jingdong.a.g().getResources().getString(R.string.manto_permission_ble_connect_title));
                format = String.format(com.jingdong.a.g().getResources().getString(R.string.manto_permission_ble_connect_msg), str, str);
                break;
            case 4:
                bundle.putString("title", com.jingdong.a.g().getResources().getString(R.string.manto_permission_camera_title));
                format = String.format(com.jingdong.a.g().getResources().getString(R.string.manto_permission_camera_msg), str, str);
                break;
            case 5:
                bundle.putString("title", com.jingdong.a.g().getResources().getString(R.string.manto_permission_ble_advertise_title));
                format = String.format(com.jingdong.a.g().getResources().getString(R.string.manto_permission_ble_advertise_msg), str, str);
                break;
            case 6:
                bundle.putString("title", com.jingdong.a.g().getResources().getString(R.string.manto_permission_record_title));
                format = String.format(com.jingdong.a.g().getResources().getString(R.string.manto_permission_record_msg), str, str);
                break;
            case 7:
                bundle.putString("title", com.jingdong.a.g().getResources().getString(R.string.manto_permission_ble_scan_title));
                format = String.format(com.jingdong.a.g().getResources().getString(R.string.manto_permission_ble_scan_msg), str, str);
                break;
            default:
                return bundle;
        }
        bundle.putString("msg", format);
        return bundle;
    }

    public static boolean hasLocationPermissionWithScene(String str, String str2) {
        IPermission iPermission = (IPermission) com.jingdong.a.n(IPermission.class);
        if (iPermission != null) {
            return iPermission.hasLocationPermissionWithScene(str, str2);
        }
        return false;
    }

    public static boolean hasPermission(String str) {
        IPermission iPermission = (IPermission) com.jingdong.a.n(IPermission.class);
        if (iPermission != null) {
            return iPermission.hasPermission(str);
        }
        return false;
    }

    public static boolean hasPermissions(String[] strArr) {
        IPermission iPermission = (IPermission) com.jingdong.a.n(IPermission.class);
        if (iPermission != null) {
            return iPermission.hasPermissions(strArr);
        }
        return false;
    }

    public static void requestLocationPermissionWithScene(Activity activity, IPermission.PermissionCallBack permissionCallBack, String str, String str2, String str3) {
        IPermission iPermission = (IPermission) com.jingdong.a.n(IPermission.class);
        if (iPermission == null) {
            return;
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = String.format(com.jingdong.a.g().getResources().getString(R.string.manto_permission_location_msg), iPermission.getAppNameAsPrefix(), iPermission.getAppNameAsPrefix());
        }
        iPermission.requestLocationPermissionWithScene(activity, permissionCallBack, str, str2, str3);
    }

    public static void requestPermission(Activity activity, String str, IPermission.PermissionCallBack permissionCallBack) {
        IPermission iPermission = (IPermission) com.jingdong.a.n(IPermission.class);
        if (iPermission == null) {
            return;
        }
        Bundle permissionInfo = getPermissionInfo(iPermission.getAppNameAsPrefix(), str);
        iPermission.requestPermission(activity, str, permissionInfo.getString("title", ""), permissionInfo.getString("msg", ""), permissionCallBack);
    }

    public static void requestPermissions(Activity activity, String[] strArr, IPermission.PermissionCallBack permissionCallBack) {
        IPermission iPermission = (IPermission) com.jingdong.a.n(IPermission.class);
        if (iPermission == null) {
            return;
        }
        String appNameAsPrefix = iPermission.getAppNameAsPrefix();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (String str : strArr) {
            Bundle permissionInfo = getPermissionInfo(appNameAsPrefix, str);
            if (permissionInfo != null) {
                arrayList.add(permissionInfo.getString("title", ""));
                arrayList2.add(permissionInfo.getString("msg", ""));
            }
            arrayList3.add(str);
        }
        iPermission.requestPermissions(activity, arrayList3, arrayList, arrayList2, permissionCallBack);
    }
}
