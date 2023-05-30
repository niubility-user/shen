package com.jdpay.membercode.network;

import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.HybridSDK;
import com.jdpay.bury.SessionPack;
import com.jdpay.lib.Bean;
import com.jdpay.lib.annotation.Name;
import com.jdpay.membercode.JDPayMemberCode;
import com.jdpay.system.SystemInfo;
import com.tencent.mapsdk.internal.l4;

/* loaded from: classes18.dex */
public abstract class BaseRequestBean implements Bean {
    @Name("sessionKey")
    public String sessionKey;
    @Name("deviceInfo")
    public BaseDeviceInfoBean deviceInfo = new BaseDeviceInfoBean();
    @Name("protocolVersion")
    public String protocolVersion = "2.0.0";
    @Name(SessionPack.KEY_APP_SOURCE)
    public String appSource = "jdmall";
    @Name(HybridSDK.APP_VERSION)
    public String clientVersion = SystemInfo.getClientVersion();
    @Name(l4.f16791e)
    public String sdkVersion = "1.15.04";
    @Name("androidID")
    public String androidID = SystemInfo.getAndroidID();
    @Name("buildBrand")
    public String buildBrand = SystemInfo.getBrand();
    @Name("buildManufacturer")
    public String buildManufacturer = SystemInfo.getManufacturer();

    /* loaded from: classes18.dex */
    public class BaseDeviceInfoBean implements Bean {
        @Name("deviceToken")
        public String deviceToken;
        @Name(DYConstants.DY_IDENTIFIER)
        public String identifier = SystemInfo.getPackgeName();
        @Name("sdkToken")
        public String biometricToken = JDPayMemberCode.getBiometricToken();
        @Name("deviceType")
        public String deviceType = SystemInfo.getProductName();
        @Name("osPlatform")
        public String osPlatform = "android";
        @Name(HybridSDK.OS_VERSION)
        public String osVersion = SystemInfo.getAndroidVersion();
        @Name("buildModel")
        public String buildModel = SystemInfo.getModel();

        public BaseDeviceInfoBean() {
        }
    }
}
