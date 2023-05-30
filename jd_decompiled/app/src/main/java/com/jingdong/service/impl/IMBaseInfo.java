package com.jingdong.service.impl;

import com.jingdong.service.BaseService;
import com.jingdong.service.service.BaseInfoService;
import java.lang.reflect.Array;

/* loaded from: classes10.dex */
public class IMBaseInfo extends BaseService implements BaseInfoService {
    public String getAndroidVersion() {
        return "";
    }

    public float getDensity() {
        return 1.0f;
    }

    public int getDensityDpi() {
        return 0;
    }

    public String getDeviceBrand() {
        return "";
    }

    public String getDeviceModel() {
        return "";
    }

    public String[][] getNetAddresses() {
        return (String[][]) Array.newInstance(String.class, 0, 0);
    }

    public float getScaledDensity() {
        return 1.0f;
    }

    public int getScreenHeight() {
        return 0;
    }

    public int getScreenWidth() {
        return 0;
    }

    public boolean isNetworkAvailable() {
        return false;
    }

    public boolean isWifi() {
        return false;
    }
}
