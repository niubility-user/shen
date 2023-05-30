package com.jd.lib.cashier.sdk.d.g.i.b;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.pay.unionpay.IUnionPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.unionpay.UnionPayApiKey;
import com.jd.lib.cashier.sdk.core.utils.g0;

/* loaded from: classes14.dex */
public class a extends com.jd.lib.cashier.sdk.d.g.g.a<com.jd.lib.cashier.sdk.d.g.i.c.a> implements UnionPayApiKey {
    private void e(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.i.c.a aVar) {
        try {
            IUnionPay unionPay = DependInitializer.getUnionPay();
            if (unionPay == null || aVar == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(UnionPayApiKey.UN_SE_TYPE, aVar.f3302c);
            bundle.putString(UnionPayApiKey.UN_PAY_TN, aVar.b);
            bundle.putString(UnionPayApiKey.UN_PAY_MODE, aVar.d);
            unionPay.doAndroidPay(fragmentActivity, bundle);
        } catch (Exception e2) {
            com.jd.lib.cashier.sdk.d.h.a.a("LaunchUnionPaySDKFunction", "LaunchUnionPaySDKException", "UnionPayApi.executeAndroidPay()", e2.getMessage());
        }
    }

    private void g(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.i.c.a aVar) {
        try {
            IUnionPay unionPay = DependInitializer.getUnionPay();
            if (unionPay == null || aVar == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(UnionPayApiKey.UN_SE_TYPE, aVar.f3302c);
            bundle.putString(UnionPayApiKey.UN_PAY_TN, aVar.b);
            bundle.putString(UnionPayApiKey.UN_PAY_MODE, aVar.d);
            unionPay.doUnionPay(fragmentActivity, bundle);
        } catch (Exception e2) {
            com.jd.lib.cashier.sdk.d.h.a.a("LaunchUnionPaySDKFunction", "LaunchUnionPaySDKException", "UnionPayApi.executeUnionPay()", e2.getMessage());
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.g.g.a
    /* renamed from: f  reason: merged with bridge method [inline-methods] */
    public void d(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.i.c.a aVar) {
        if (aVar == null || !g0.a(fragmentActivity)) {
            return;
        }
        if (!TextUtils.isEmpty(aVar.f3302c)) {
            e(fragmentActivity, aVar);
        } else {
            g(fragmentActivity, aVar);
        }
    }
}
