package com.jingdong.manto.sdk.api;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface IRequestPayment extends IMantoSdkBase {
    public static final String IN_appId = "appId";
    public static final String IN_payParam = "payParam";
    public static final String OUT_merchant = "merchant";
    public static final String OUT_orderId = "package";
    public static final String OUT_signData = "paySign";
    public static final String V_EXTRA = "extraInfo";
    public static final String V_MODE = "mode";
    public static final String V_PAY_TYPE = "paymentType";

    Bundle onHandleResult(int i2, int i3, Intent intent);

    void requestPaymentIn(Activity activity, String str, Bundle bundle, int i2);

    void requestPaymentOut(Activity activity, String str, Bundle bundle, int i2);

    void requestVerify(Activity activity, String str, Bundle bundle, int i2);
}
