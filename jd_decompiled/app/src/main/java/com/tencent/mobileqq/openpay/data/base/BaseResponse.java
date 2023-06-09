package com.tencent.mobileqq.openpay.data.base;

import android.os.Bundle;

/* loaded from: classes9.dex */
public abstract class BaseResponse {
    public int apiMark;
    public String apiName;
    public String retMsg;
    protected final int RET_CODE_UNKNOW = -9999999;
    protected final int RET_CODE_SUCESS = 0;
    public int retCode = -9999999;

    public abstract boolean checkParams();

    public void fromBundle(Bundle bundle) {
        this.retCode = bundle.getInt("_mqqpay_baseresp_retcode");
        this.retMsg = bundle.getString("_mqqpay_baseresp_retmsg");
        this.apiName = bundle.getString("_mqqpay_baseapi_apiname");
        this.apiMark = bundle.getInt("_mqqpay_baseapi_apimark");
    }

    public boolean isSuccess() {
        return this.retCode == 0;
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt("_mqqpay_baseresp_retcode", this.retCode);
        bundle.putString("_mqqpay_baseresp_retmsg", this.retMsg);
        bundle.putString("_mqqpay_baseapi_apiname", this.apiName);
        bundle.putInt("_mqqpay_baseapi_apimark", this.apiMark);
    }
}
