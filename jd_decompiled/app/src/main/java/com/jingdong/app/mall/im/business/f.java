package com.jingdong.app.mall.im.business;

import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.impl.IMBaseInfo;
import java.lang.reflect.Array;

/* loaded from: classes4.dex */
public class f extends IMBaseInfo {
    private static final String a = "f";

    @Override // com.jingdong.service.impl.IMBaseInfo, com.jingdong.service.service.BaseInfoService
    public String getAndroidVersion() {
        OKLog.d("bundleicssdkservice", a + "---getAndroidVersion");
        try {
            return BaseInfo.getAndroidVersion();
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // com.jingdong.service.impl.IMBaseInfo, com.jingdong.service.service.BaseInfoService
    public float getDensity() {
        OKLog.d("bundleicssdkservice", a + "---getDensity");
        try {
            float density = BaseInfo.getDensity();
            if (density <= 0.0f) {
                return 1.0f;
            }
            return density;
        } catch (Exception unused) {
            return 1.0f;
        }
    }

    @Override // com.jingdong.service.impl.IMBaseInfo, com.jingdong.service.service.BaseInfoService
    public int getDensityDpi() {
        OKLog.d("bundleicssdkservice", a + "---getDensityDpi");
        try {
            return BaseInfo.getDensityDpiInt();
        } catch (Exception unused) {
            return 0;
        }
    }

    @Override // com.jingdong.service.impl.IMBaseInfo, com.jingdong.service.service.BaseInfoService
    public String getDeviceBrand() {
        OKLog.d("bundleicssdkservice", a + "---getDeviceBrand");
        try {
            return BaseInfo.getDeviceBrand();
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // com.jingdong.service.impl.IMBaseInfo, com.jingdong.service.service.BaseInfoService
    public String getDeviceModel() {
        OKLog.d("bundleicssdkservice", a + "---getDeviceModel");
        try {
            return BaseInfo.getDeviceModel();
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // com.jingdong.service.impl.IMBaseInfo, com.jingdong.service.service.BaseInfoService
    public String[][] getNetAddresses() {
        OKLog.d("bundleicssdkservice", a + "---getNetAddresses");
        try {
            return BaseInfo.getNetAddresses();
        } catch (Exception unused) {
            return (String[][]) Array.newInstance(String.class, 0, 0);
        }
    }

    @Override // com.jingdong.service.impl.IMBaseInfo, com.jingdong.service.service.BaseInfoService
    public float getScaledDensity() {
        OKLog.d("bundleicssdkservice", a + "---getScaledDensity");
        try {
            float scaledDensity = BaseInfo.getScaledDensity();
            if (scaledDensity <= 0.0f) {
                return 1.0f;
            }
            return scaledDensity;
        } catch (Exception unused) {
            return 1.0f;
        }
    }

    @Override // com.jingdong.service.impl.IMBaseInfo, com.jingdong.service.service.BaseInfoService
    public int getScreenHeight() {
        OKLog.d("bundleicssdkservice", a + "---getScreenHeight");
        try {
            return BaseInfo.getScreenHeight();
        } catch (Exception unused) {
            return 0;
        }
    }

    @Override // com.jingdong.service.impl.IMBaseInfo, com.jingdong.service.service.BaseInfoService
    public int getScreenWidth() {
        OKLog.d("bundleicssdkservice", a + "---getScreenWidth");
        try {
            return BaseInfo.getScreenWidth();
        } catch (Exception unused) {
            return 0;
        }
    }

    @Override // com.jingdong.service.impl.IMBaseInfo, com.jingdong.service.service.BaseInfoService
    public boolean isNetworkAvailable() {
        OKLog.d("bundleicssdkservice", a + "---isNetworkAvailable");
        try {
            return NetUtils.isNetworkAvailable();
        } catch (Exception unused) {
            return super.isNetworkAvailable();
        }
    }

    @Override // com.jingdong.service.impl.IMBaseInfo, com.jingdong.service.service.BaseInfoService
    public boolean isWifi() {
        OKLog.d("bundleicssdkservice", a + "---isWifi");
        try {
            return NetUtils.isWifi();
        } catch (Exception unused) {
            return super.isWifi();
        }
    }
}
