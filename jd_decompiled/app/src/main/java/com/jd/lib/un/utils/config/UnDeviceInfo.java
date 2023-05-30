package com.jd.lib.un.utils.config;

import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import com.jd.lib.un.utils.UnStringUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes16.dex */
public class UnDeviceInfo {
    private static final int D_MODEL_SPLIT_LEN = 25;
    private static String deviceModel;
    private static String deviceProduct;
    private static String manufacturer;
    private static int sdkVersion;

    public static float getDensity() {
        if (UnUtilsConfig.getInstance().getOnDeviceInfo() != null && UnUtilsConfig.getInstance().getOnDeviceInfo().getDensity() > 0.0f) {
            return UnUtilsConfig.getInstance().getOnDeviceInfo().getDensity();
        }
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static int getDensityDpiInt() {
        if (UnUtilsConfig.getInstance().getOnDeviceInfo() != null && UnUtilsConfig.getInstance().getOnDeviceInfo().getDensityDpiInt() > 0) {
            return UnUtilsConfig.getInstance().getOnDeviceInfo().getDensityDpiInt();
        }
        return Resources.getSystem().getDisplayMetrics().densityDpi;
    }

    public static String getDeviceModel() {
        if (!TextUtils.isEmpty(deviceModel)) {
            return deviceModel;
        }
        if (UnUtilsConfig.getInstance().getOnDeviceInfo() != null) {
            deviceModel = UnUtilsConfig.getInstance().getOnDeviceInfo().getDeviceModel();
        } else if (TextUtils.isEmpty(deviceModel)) {
            deviceModel = UnStringUtils.spilitSubString(BaseInfo.getDeviceModel(), 25).replaceAll(LangUtils.SINGLE_SPACE, "");
        }
        String str = deviceModel;
        return str == null ? "" : str;
    }

    public static String getDeviceProduct() {
        if (!TextUtils.isEmpty(deviceProduct)) {
            return deviceProduct;
        }
        if (UnUtilsConfig.getInstance().getOnDeviceInfo() != null) {
            deviceProduct = UnUtilsConfig.getInstance().getOnDeviceInfo().getDeviceProductName();
        } else if (TextUtils.isEmpty(deviceProduct)) {
            deviceProduct = BaseInfo.getDeviceProductName();
        }
        String str = deviceProduct;
        return str == null ? "" : str;
    }

    public static String getManufacturer() {
        if (!TextUtils.isEmpty(manufacturer)) {
            return manufacturer;
        }
        if (UnUtilsConfig.getInstance().getOnDeviceInfo() != null) {
            manufacturer = UnUtilsConfig.getInstance().getOnDeviceInfo().getDeviceManufacture();
        } else if (TextUtils.isEmpty(manufacturer)) {
            manufacturer = BaseInfo.getDeviceManufacture();
        }
        String str = manufacturer;
        return str == null ? "" : str;
    }

    public static float getScaledDensity() {
        if (UnUtilsConfig.getInstance().getOnDeviceInfo() != null && UnUtilsConfig.getInstance().getOnDeviceInfo().getScaledDensity() > 0.0f) {
            return UnUtilsConfig.getInstance().getOnDeviceInfo().getScaledDensity();
        }
        return Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    public static int getScreenHeight() {
        if (UnUtilsConfig.getInstance().getOnDeviceInfo() != null && UnUtilsConfig.getInstance().getOnDeviceInfo().getScreenHeight() > 320) {
            return UnUtilsConfig.getInstance().getOnDeviceInfo().getScreenHeight();
        }
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        if (UnUtilsConfig.getInstance().getOnDeviceInfo() != null && UnUtilsConfig.getInstance().getOnDeviceInfo().getScreenWidth() > 240) {
            return UnUtilsConfig.getInstance().getOnDeviceInfo().getScreenWidth();
        }
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getSdkVersion() {
        int i2 = sdkVersion;
        if (i2 > 0) {
            return i2;
        }
        int i3 = Build.VERSION.SDK_INT;
        sdkVersion = i3;
        return i3;
    }
}
