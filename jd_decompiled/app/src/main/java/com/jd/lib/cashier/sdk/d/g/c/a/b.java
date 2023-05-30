package com.jd.lib.cashier.sdk.d.g.c.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.paychannel.jdpay.entity.JDPayPaymentACCEntity;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.d.g.g.d;
import com.jd.lib.cashier.sdk.d.g.g.e;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.d.g.c.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements f<JDPayPaymentACCEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.d.g.c.c.b f3263g;

        a(com.jd.lib.cashier.sdk.d.g.c.c.b bVar) {
            this.f3263g = bVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(JDPayPaymentACCEntity jDPayPaymentACCEntity) {
            if (jDPayPaymentACCEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.p(this.f3263g.getActivity(), this.f3263g.appId, jDPayPaymentACCEntity);
            } else if (!TextUtils.isEmpty(jDPayPaymentACCEntity.errorCode)) {
                b.this.p(this.f3263g.getActivity(), this.f3263g.appId, jDPayPaymentACCEntity);
            } else if (jDPayPaymentACCEntity.checkParamIsValid()) {
                b.this.r(this.f3263g.getActivity(), jDPayPaymentACCEntity, this.f3263g);
            } else {
                b.this.p(this.f3263g.getActivity(), this.f3263g.appId, jDPayPaymentACCEntity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, String str, JDPayPaymentACCEntity jDPayPaymentACCEntity) {
        CashierCommonPopConfig cashierCommonPopConfig;
        String string;
        CashierCommonPopConfig cashierCommonPopConfig2 = null;
        if (jDPayPaymentACCEntity != null) {
            cashierCommonPopConfig = jDPayPaymentACCEntity.commonPopupInfo;
            cashierCommonPopConfig2 = jDPayPaymentACCEntity.orderExceptionInfo;
        } else {
            cashierCommonPopConfig = null;
        }
        String str2 = jDPayPaymentACCEntity != null ? jDPayPaymentACCEntity.errorCode : DYConstants.DY_NULL_STR;
        if (jDPayPaymentACCEntity != null && !TextUtils.isEmpty(jDPayPaymentACCEntity.errorMsg)) {
            string = jDPayPaymentACCEntity.errorMsg;
        } else {
            string = g0.a(fragmentActivity) ? fragmentActivity.getString(R.string.lib_cashier_sdk_pay_jd_failure) : "";
        }
        if (g0.a(fragmentActivity)) {
            com.jd.lib.cashier.sdk.d.g.e.a.a(fragmentActivity, str, m.f().i(), str2);
            com.jd.lib.cashier.sdk.d.g.b.b.a().b(fragmentActivity, string, cashierCommonPopConfig2, cashierCommonPopConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, JDPayPaymentACCEntity jDPayPaymentACCEntity, com.jd.lib.cashier.sdk.d.g.c.c.b bVar) {
        if (jDPayPaymentACCEntity == null || bVar == null) {
            return;
        }
        com.jd.lib.cashier.sdk.d.g.c.c.a aVar = new com.jd.lib.cashier.sdk.d.g.c.c.a();
        aVar.b = jDPayPaymentACCEntity.appId;
        aVar.f3265c = jDPayPaymentACCEntity.sdkParam;
        aVar.d = jDPayPaymentACCEntity.cashierUrl;
        String str = bVar.a;
        String str2 = bVar.f3269f;
        aVar.a = bVar.f3287c;
        aVar.f3267f = bVar.w;
        String str3 = bVar.x;
        if (!TextUtils.isEmpty(jDPayPaymentACCEntity.controllActionParam)) {
            aVar.f3266e = jDPayPaymentACCEntity.controllActionParam;
        }
        d d = e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.JDPAY);
        if (!g0.a(fragmentActivity) || d == null) {
            return;
        }
        d.b(fragmentActivity, aVar);
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.d.g.c.c.b bVar) {
        if (bVar != null) {
            j(new a(bVar));
            h(bVar);
        }
    }
}
