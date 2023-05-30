package com.jd.lib.cashier.sdk.d.g.h.b;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.pay.qqpay.IQQPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.qqpay.QQPayApiKey;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.d.g.b.b;

/* loaded from: classes14.dex */
public class a extends com.jd.lib.cashier.sdk.d.g.g.a<com.jd.lib.cashier.sdk.d.g.h.c.a> implements Object {
    @Override // com.jd.lib.cashier.sdk.d.g.g.a
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void d(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.h.c.a aVar) {
        try {
            r.a("QQWalletPayApi", aVar);
            IQQPay qQPay = DependInitializer.getQQPay();
            if (qQPay != null && aVar != null) {
                Bundle bundle = new Bundle();
                bundle.putString(QQPayApiKey.SIG, aVar.b);
                bundle.putString(QQPayApiKey.SIG_TYPE, aVar.f3295g);
                bundle.putString(QQPayApiKey.BARGAINORID, aVar.f3298j);
                bundle.putString(QQPayApiKey.SERIAL_NUMBER, aVar.d);
                bundle.putString(QQPayApiKey.TOKEN_ID, aVar.f3293e);
                bundle.putString(QQPayApiKey.PUB_ACC, aVar.f3296h);
                bundle.putString(QQPayApiKey.PUB_ACC_HINT, aVar.f3299k);
                bundle.putString(QQPayApiKey.NONCE, aVar.f3297i);
                bundle.putLong(QQPayApiKey.TIME_STAMP, aVar.f3292c);
                if (qQPay.checkParams(bundle)) {
                    qQPay.doPay(bundle);
                } else {
                    b.a().f(fragmentActivity);
                    com.jd.lib.cashier.sdk.d.h.a.a("qqPayParamBadCaseFunction", "qqPayParamException", "QQWalletPayApi.executePay()", aVar.toString());
                }
            }
        } catch (Exception e2) {
            r.d("QQWalletPayApi", e2.getMessage());
            com.jd.lib.cashier.sdk.d.h.a.a("qqPayParamBadCaseFunction", "qqPayTriggerException", "QQWalletPayApi.executePay()", e2.getMessage());
        }
    }
}
