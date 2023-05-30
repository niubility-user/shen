package com.jd.lib.cashier.sdk.core.paychannel.jdpay.entity;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.d.f.b;

/* loaded from: classes14.dex */
public class JDPayPaymentACCEntity extends b {
    public String appId;
    public String cashierUrl;
    public CashierCommonPopConfig commonPopupInfo;
    public String controllActionParam;
    public CashierCommonPopConfig orderExceptionInfo;
    public String sdkParam;

    public boolean checkParamIsValid() {
        return (TextUtils.isEmpty(this.sdkParam) || TextUtils.isEmpty(this.appId)) ? false : true;
    }
}
