package com.jd.aips.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import com.jd.aips.common.bean.DeviceInfo;
import com.tencent.smtt.sdk.ProxyConfig;
import jd.wjlogin_sdk.util.f;

/* loaded from: classes12.dex */
public class DeviceInfoUtil {
    @SuppressLint({"MissingPermission"})
    public static DeviceInfo buildDeviceInfo(@NonNull Context context) {
        DeviceInfo deviceInfo = new DeviceInfo();
        String model = FsBaseInfoUtils.getModel();
        String brand = FsBaseInfoUtils.getBrand();
        deviceInfo.phoneModel = model;
        deviceInfo.deviceType = FsBaseInfoUtils.getDeviceName();
        deviceInfo.brandModel = String.format("%s_%s", brand, model);
        String packageName = context.getApplicationContext().getPackageName();
        deviceInfo.appPackageName = packageName;
        deviceInfo.packageName = packageName;
        if (packageName.equals("com.jd.jrapp")) {
            deviceInfo.channelInfo = "JDJR";
        } else if (packageName.equals(f.f19954c)) {
            deviceInfo.channelInfo = "JDMall";
        }
        try {
            deviceInfo.versionName = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (Exception unused) {
        }
        deviceInfo.osVersion = FsBaseInfoUtils.getAndroidVersion();
        deviceInfo.resolution = FsBaseInfoUtils.getScreenWidth(context) + ProxyConfig.MATCH_ALL_SCHEMES + FsBaseInfoUtils.getScreenHeight(context);
        deviceInfo.networkType = FsBaseInfoUtils.getNetworkType();
        deviceInfo.androidSdkVersion = FsBaseInfoUtils.getAndroidSDKVersion();
        deviceInfo.androidManufacturer = FsBaseInfoUtils.getManufacture();
        return deviceInfo;
    }
}
