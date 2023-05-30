package com.jdjr.risk.jdcn.common.utils;

import android.content.Context;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class FsBaseInfoUtils {
    public static int getAndroidSDKVersion() {
        return BaseInfo.getAndroidSDKVersion();
    }

    public static float getDensity(Context context) {
        return BaseInfo.getDensity();
    }

    public static String getManufacture() {
        return BaseInfo.getDeviceManufacture();
    }

    public static String getModel() {
        return BaseInfo.getDeviceModel();
    }

    public static String getOSName() {
        return BaseInfo.getOSName();
    }

    public static int getScreenHeight(Context context) {
        return BaseInfo.getScreenHeight();
    }

    public static int getScreenWidth(Context context) {
        return BaseInfo.getScreenWidth();
    }
}
