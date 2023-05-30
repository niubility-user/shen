package com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay;

import android.app.Activity;
import android.os.Bundle;

/* loaded from: classes13.dex */
public interface IJDPay {
    void doDcepPay(Activity activity, Bundle bundle);

    void doPay(Activity activity, Bundle bundle);

    void doPay(Activity activity, Bundle bundle, int i2);
}
