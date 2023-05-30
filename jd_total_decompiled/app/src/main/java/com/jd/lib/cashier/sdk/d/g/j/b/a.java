package com.jd.lib.cashier.sdk.d.g.j.b;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.pay.wxpay.IWXPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.wxpay.WXPayApiKey;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;

/* loaded from: classes14.dex */
public class a extends com.jd.lib.cashier.sdk.d.g.g.a<com.jd.lib.cashier.sdk.d.g.j.c.a> implements WXPayApiKey {
    @Override // com.jd.lib.cashier.sdk.d.g.g.a
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void d(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.j.c.a aVar) {
        try {
            r.a("WXPay", aVar);
            IWXPay wXPay = DependInitializer.getWXPay();
            if (!g0.a(fragmentActivity) || wXPay == null || aVar == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(WXPayApiKey.WX_PAY_SIGN, aVar.d);
            bundle.putString(WXPayApiKey.WX_PAY_NONCE, aVar.f3308g);
            bundle.putString(WXPayApiKey.WX_PAY_PREPAY_ID, aVar.f3306e);
            bundle.putString(WXPayApiKey.WX_PAY_TIMESTAMP, aVar.b);
            bundle.putString(WXPayApiKey.WX_PAY_PARTNER_ID, aVar.f3307f);
            bundle.putString(WXPayApiKey.WX_PAY_PACKAGE_VALUE, aVar.f3305c);
            wXPay.doWXPay(bundle);
        } catch (Exception e2) {
            r.c("WXPay", e2);
        }
    }
}
