package com.jd.manto.d;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.jd.manto.pay.MantoPayProxyActivity;
import com.jingdong.manto.sdk.api.IRequestPayment;

/* loaded from: classes17.dex */
public class o implements IRequestPayment {
    @Override // com.jingdong.manto.sdk.api.IRequestPayment
    public Bundle onHandleResult(int i2, int i3, Intent intent) {
        if (i3 == i2 && i3 == 1024 && i3 == 1025 && intent != null && intent.hasExtra("jdpay_Result")) {
            Bundle bundle = new Bundle();
            bundle.putString("result", String.valueOf(intent.getStringExtra("jdpay_Result")));
            return bundle;
        }
        return null;
    }

    @Override // com.jingdong.manto.sdk.api.IRequestPayment
    public void requestPaymentIn(Activity activity, String str, Bundle bundle, int i2) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        MantoPayProxyActivity.u(activity, bundle, i2);
    }

    @Override // com.jingdong.manto.sdk.api.IRequestPayment
    public void requestPaymentOut(Activity activity, String str, Bundle bundle, int i2) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        MantoPayProxyActivity.u(activity, bundle, i2);
    }

    @Override // com.jingdong.manto.sdk.api.IRequestPayment
    public void requestVerify(Activity activity, String str, Bundle bundle, int i2) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        MantoPayProxyActivity.v(activity, bundle, 3, i2);
    }
}
