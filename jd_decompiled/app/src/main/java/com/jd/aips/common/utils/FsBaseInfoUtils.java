package com.jd.aips.common.utils;

import android.content.Context;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes12.dex */
public class FsBaseInfoUtils {
    public static int getAndroidSDKVersion() {
        return BaseInfo.getAndroidSDKVersion();
    }

    public static String getAndroidVersion() {
        return BaseInfo.getAndroidVersion();
    }

    public static String getBrand() {
        return BaseInfo.getDeviceBrand();
    }

    public static float getDensity(Context context) {
        return BaseInfo.getDensity();
    }

    public static String getDeviceName() {
        return BaseInfo.getDeviceName();
    }

    public static String getManufacture() {
        return BaseInfo.getDeviceManufacture();
    }

    public static String getModel() {
        return BaseInfo.getDeviceModel();
    }

    public static String getNetworkType() {
        return BaseInfo.getNetworkType();
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

    public static String[] getSupportedABIs() {
        return BaseInfo.getDeviceSuppportedABIs();
    }

    public static boolean isAgreedPrivacy() {
        return BaseInfo.isAgreedPrivacy();
    }
}
