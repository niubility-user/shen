package com.jd.aips.verify.face.bean;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes12.dex */
public class UploadVerifyRecord implements Serializable {
    static final long serialVersionUID = 1774042712100289221L;
    public String businessId;
    public String deviceId;
    public DeviceInfo deviceInfo;
    public List<Flow> flowList;
    public String sdkToken;

    public String getBusinessId() {
        return this.businessId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public List<Flow> getFlowList() {
        return this.flowList;
    }

    public String getSdkToken() {
        return this.sdkToken;
    }

    public void setBusinessId(String str) {
        this.businessId = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public void setFlowList(List<Flow> list) {
        this.flowList = list;
    }

    public void setSdkToken(String str) {
        this.sdkToken = str;
    }
}
