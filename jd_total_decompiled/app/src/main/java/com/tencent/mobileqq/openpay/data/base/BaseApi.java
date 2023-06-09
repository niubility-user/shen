package com.tencent.mobileqq.openpay.data.base;

import android.os.Bundle;

/* loaded from: classes9.dex */
public abstract class BaseApi {
    public String appId;
    protected final String sdkVersion = "1.1.0";
    protected final String appType = "native";
    protected String appName = "native";

    public abstract boolean checkParams();

    public void fromBundle(Bundle bundle) {
        this.appId = bundle.getString("_mqqpay_baseapi_appid");
        this.appName = bundle.getString("_mqqpay_baseapi_appname");
    }

    public abstract int getApiMark();

    public abstract String getApiName();

    public void toBundle(Bundle bundle) {
        bundle.putString("_mqqpay_baseapi_appid", this.appId);
        bundle.putString("_mqqpay_baseapi_appname", this.appName);
        bundle.putString("_mqqpay_baseapi_apptype", "native");
        bundle.putString("_mqqpay_baseapi_sdkversion", "1.1.0");
        bundle.putString("_mqqpay_baseapi_apiname", getApiName());
        bundle.putInt("_mqqpay_baseapi_apimark", getApiMark());
    }
}
