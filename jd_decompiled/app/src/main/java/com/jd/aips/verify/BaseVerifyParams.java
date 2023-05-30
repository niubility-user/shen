package com.jd.aips.verify;

import android.os.Bundle;
import android.text.TextUtils;
import com.jd.aips.common.bean.DeviceInfo;
import java.io.Serializable;
import java.security.InvalidParameterException;

/* loaded from: classes12.dex */
public class BaseVerifyParams<C> implements Serializable {
    public static final String VERIFY_BUSINESS_TYPE_MESHED_PHOTO = "MESHED_PHOTO_VERIFY";
    public static final String VERIFY_BUSINESS_TYPE_SIMPLE = "SIMPLE_VERIFY";
    static final long serialVersionUID = 6526769017002777673L;
    public String appAuthorityKey;
    public String appName;
    public String businessId;
    protected DeviceInfo deviceInfo;
    public String sdkToken;
    public String userId;
    public String verifyBusinessType;
    public C verifyConfig;
    public String verifySdkName;
    public String verifySdkVersion;
    public String verifyToken;

    public BaseVerifyParams() {
        this.businessId = "";
        this.appName = "";
        this.appAuthorityKey = "";
        this.verifyToken = "";
        this.userId = "";
        this.verifySdkName = "";
        this.verifySdkVersion = "";
        this.sdkToken = "";
        this.verifyBusinessType = VERIFY_BUSINESS_TYPE_SIMPLE;
    }

    public String getAppAuthorityKey() {
        return this.appAuthorityKey;
    }

    public String getAppName() {
        return this.appName;
    }

    public String getBusinessId() {
        return this.businessId;
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getVerifyBusinessType() {
        return this.verifyBusinessType;
    }

    public C getVerifyConfig() {
        return this.verifyConfig;
    }

    public String getVerifySdkName() {
        return this.verifySdkName;
    }

    public String getVerifySdkVersion() {
        return this.verifySdkVersion;
    }

    public String getVerifyToken() {
        return this.verifyToken;
    }

    public void setAppAuthorityKey(String str) {
        this.appAuthorityKey = str;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public void setBusinessId(String str) {
        this.businessId = str;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
        if (deviceInfo != null) {
            deviceInfo.sdkName = this.verifySdkName;
            deviceInfo.sdkVersion = this.verifySdkVersion;
        }
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setVerifyConfig(C c2) {
        this.verifyConfig = c2;
    }

    public void setVerifySdkName(String str) {
        this.verifySdkName = str;
    }

    public void setVerifySdkVersion(String str) {
        this.verifySdkVersion = str;
    }

    public void setVerifyToken(String str) {
        this.verifyToken = str;
    }

    public boolean validateParams() {
        return (TextUtils.isEmpty(this.businessId) || TextUtils.isEmpty(this.appName) || TextUtils.isEmpty(this.appAuthorityKey) || TextUtils.isEmpty(this.verifyToken)) ? false : true;
    }

    public BaseVerifyParams(Bundle bundle) throws InvalidParameterException {
        this.businessId = "";
        this.appName = "";
        this.appAuthorityKey = "";
        this.verifyToken = "";
        this.userId = "";
        this.verifySdkName = "";
        this.verifySdkVersion = "";
        this.sdkToken = "";
        this.verifyBusinessType = VERIFY_BUSINESS_TYPE_SIMPLE;
        String string = bundle.getString("businessId");
        this.businessId = string;
        if (!TextUtils.isEmpty(string)) {
            String string2 = bundle.getString("appName");
            this.appName = string2;
            if (!TextUtils.isEmpty(string2)) {
                String string3 = bundle.getString("appAuthorityKey");
                this.appAuthorityKey = string3;
                if (!TextUtils.isEmpty(string3)) {
                    String string4 = bundle.getString("verifyToken");
                    this.verifyToken = string4;
                    if (!TextUtils.isEmpty(string4)) {
                        this.sdkToken = bundle.getString("sdkToken", "");
                        this.userId = bundle.getString("userId", "");
                        return;
                    }
                    throw new InvalidParameterException(String.format("%s is empty!", "verifyToken"));
                }
                throw new InvalidParameterException(String.format("%s is empty!", "appAuthorityKey"));
            }
            throw new InvalidParameterException(String.format("%s is empty!", "appName"));
        }
        throw new InvalidParameterException(String.format("%s is empty!", "businessId"));
    }
}
