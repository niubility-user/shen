package com.jd.aips.common.bean;

import com.huawei.hms.adapter.internal.CommonCode;
import com.jd.libs.hybrid.HybridSDK;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class DeviceInfo implements Serializable {
    static final long serialVersionUID = 5619920608959534385L;
    public int androidSdkVersion;
    public String brandModel = "";
    public String packageName = "";
    public String channelInfo = "";
    public String versionName = "";
    public String deviceType = "";
    public String networkType = "";
    public String osPlatform = "android";
    public String osVersion = "";
    public String resolution = "";
    public String startNo = "1";
    public String terminalType = "2";
    public String phoneModel = "";
    public String platform = "android";
    public String androidManufacturer = "";
    public String appPackageName = "";
    public String sdkName = "";
    public String sdkVersion = "";

    public String getBrandModel() {
        return this.brandModel;
    }

    public String getChannelInfo() {
        return this.channelInfo;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getNetworkType() {
        return this.networkType;
    }

    public String getOsPlatform() {
        return this.osPlatform;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getResolution() {
        return this.resolution;
    }

    public String getStartNo() {
        return this.startNo;
    }

    public String getTerminalType() {
        return this.terminalType;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setBrandModel(String str) {
        this.brandModel = str;
    }

    public void setChannelInfo(String str) {
        this.channelInfo = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setNetworkType(String str) {
        this.networkType = str;
    }

    public void setOsPlatform(String str) {
        this.osPlatform = str;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setResolution(String str) {
        this.resolution = str;
    }

    public void setStartNo(String str) {
        this.startNo = str;
    }

    public void setTerminalType(String str) {
        this.terminalType = str;
    }

    public void setVersionName(String str) {
        this.versionName = str;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("brandModel", this.brandModel);
            jSONObject.put("packageName", this.packageName);
            jSONObject.put("channelInfo", this.channelInfo);
            jSONObject.put("versionName", this.versionName);
            jSONObject.put("deviceType", this.deviceType);
            jSONObject.put("networkType", this.networkType);
            jSONObject.put("osPlatform", this.osPlatform);
            jSONObject.put(HybridSDK.OS_VERSION, this.osVersion);
            jSONObject.put(CommonCode.MapKey.HAS_RESOLUTION, this.resolution);
            jSONObject.put("startNo", this.startNo);
            jSONObject.put("terminalType", this.terminalType);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
