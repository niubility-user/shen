package com.jd.cashier.app.jdlibcutter.protocol.pay.unionpay;

import android.content.Context;
import android.os.Bundle;

/* loaded from: classes13.dex */
public interface IUnionPay {
    void doAndroidPay(Context context, Bundle bundle);

    void doUnionPay(Context context, Bundle bundle);

    boolean isUnionWalletInstalled(Context context);

    void releaseMemory();
}
