package com.jd.cashier.app.jdlibcutter.protocol.pay.wxpay;

import android.os.Bundle;

/* loaded from: classes13.dex */
public interface IWXPay {
    void doWXPay(Bundle bundle);

    boolean isWXInstalled();

    boolean isWXSupported();
}
