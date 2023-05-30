package com.jd.cashier.app.jdlibcutter.impl.pay;

import android.app.Activity;
import android.os.Bundle;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.IJDPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jingdong.common.deeplinkhelper.DeepLinkDcepSdkHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkJDpaySdkHelper;
import com.jingdong.common.utils.UserUtil;

/* loaded from: classes13.dex */
public class JDPay implements IJDPay, JDPayApiKey {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.IJDPay
    public void doDcepPay(Activity activity, Bundle bundle) {
        if (activity == null || activity.isFinishing() || bundle == null) {
            return;
        }
        bundle.putString(JDPayApiKey.JDPAY_SESSION_KEY, UserUtil.getWJLoginHelper().getA2());
        DeepLinkDcepSdkHelper.startJDPayDcepActivityForResult(activity, bundle, 10);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.IJDPay
    public void doPay(Activity activity, Bundle bundle) {
        if (activity == null || activity.isFinishing() || bundle == null) {
            return;
        }
        bundle.putString(JDPayApiKey.JDPAY_SESSION_KEY, UserUtil.getWJLoginHelper().getA2());
        DeeplinkJDpaySdkHelper.startJDPayActivityForResult(activity, bundle, 10);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.IJDPay
    public void doPay(Activity activity, Bundle bundle, int i2) {
        if (activity == null || activity.isFinishing() || bundle == null) {
            return;
        }
        bundle.putString(JDPayApiKey.JDPAY_SESSION_KEY, UserUtil.getWJLoginHelper().getA2());
        DeeplinkJDpaySdkHelper.startJDPayActivityForResult(activity, bundle, i2);
    }
}
