package com.jd.cashier.app.jdlibcutter.protocol.pay.qqpay;

import android.os.Bundle;

/* loaded from: classes13.dex */
public interface IQQPay {
    boolean checkParams(Bundle bundle);

    void doPay(Bundle bundle);
}
