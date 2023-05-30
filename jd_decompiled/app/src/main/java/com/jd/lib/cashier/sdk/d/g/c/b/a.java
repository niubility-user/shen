package com.jd.lib.cashier.sdk.d.g.c.b;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.IJDPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.core.utils.r;

/* loaded from: classes14.dex */
public class a extends com.jd.lib.cashier.sdk.d.g.g.a<com.jd.lib.cashier.sdk.d.g.c.c.a> implements JDPayApiKey {
    private void e(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.c.c.a aVar) {
        if (fragmentActivity == null || aVar == null) {
            return;
        }
        p.h(fragmentActivity, aVar.d);
    }

    private void f(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.c.c.a aVar) {
        if (fragmentActivity == null || aVar == null) {
            return;
        }
        IJDPay jDPay = DependInitializer.getJDPay();
        Bundle bundle = new Bundle();
        bundle.putString("PAY_PARAM", aVar.f3265c);
        bundle.putString("APP_ID", aVar.b);
        bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_COUNTER_V4");
        bundle.putString("JDPAY_CHANNEL_TYPE", aVar.f3267f);
        try {
            bundle.putBoolean("JDPAY_TOAST_PRINT", false);
            if (!TextUtils.isEmpty(aVar.f3266e)) {
                bundle.putString("JDPAY_EXTEND_INFO", aVar.f3266e);
            }
            if (jDPay != null) {
                jDPay.doPay(fragmentActivity, bundle);
            }
        } catch (Exception e2) {
            r.d("JDPayApi", "get sessionKey from jd pay in exception-->" + e2.getMessage());
            com.jd.lib.cashier.sdk.d.h.a.a("PayChannelFunction", "PayChannelException", "JDPayApi.executePay()", e2.getMessage());
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.g.g.a
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    public void d(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.c.c.a aVar) {
        if (aVar != null) {
            try {
                if (!TextUtils.isEmpty(aVar.b) && !TextUtils.isEmpty(aVar.f3265c)) {
                    f(fragmentActivity, aVar);
                } else if (!TextUtils.isEmpty(aVar.d)) {
                    e(fragmentActivity, aVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
