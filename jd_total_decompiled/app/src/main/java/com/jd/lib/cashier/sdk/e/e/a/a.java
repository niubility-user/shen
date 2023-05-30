package com.jd.lib.cashier.sdk.e.e.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.paychannel.jdpay.entity.JDPayPaymentACCEntity;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.g.g.d;

/* loaded from: classes14.dex */
public class a extends com.jd.lib.cashier.sdk.d.g.c.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.lib.cashier.sdk.e.e.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public class C0119a implements f<JDPayPaymentACCEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.d.g.c.c.b f3334g;

        C0119a(com.jd.lib.cashier.sdk.d.g.c.c.b bVar) {
            this.f3334g = bVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(JDPayPaymentACCEntity jDPayPaymentACCEntity) {
            if (jDPayPaymentACCEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                a.this.p(this.f3334g.getActivity(), jDPayPaymentACCEntity);
            } else if (!TextUtils.isEmpty(jDPayPaymentACCEntity.errorCode)) {
                a.this.p(this.f3334g.getActivity(), jDPayPaymentACCEntity);
            } else if (!jDPayPaymentACCEntity.checkParamIsValid()) {
                a.this.p(this.f3334g.getActivity(), jDPayPaymentACCEntity);
            } else {
                a aVar = a.this;
                FragmentActivity activity = this.f3334g.getActivity();
                com.jd.lib.cashier.sdk.d.g.c.c.b bVar = this.f3334g;
                aVar.r(activity, jDPayPaymentACCEntity, bVar.a, bVar.f3287c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, JDPayPaymentACCEntity jDPayPaymentACCEntity) {
        CashierCommonPopConfig cashierCommonPopConfig;
        String string;
        CashierCommonPopConfig cashierCommonPopConfig2 = null;
        if (jDPayPaymentACCEntity != null) {
            cashierCommonPopConfig = jDPayPaymentACCEntity.commonPopupInfo;
            cashierCommonPopConfig2 = jDPayPaymentACCEntity.orderExceptionInfo;
        } else {
            cashierCommonPopConfig = null;
        }
        if (jDPayPaymentACCEntity != null && !TextUtils.isEmpty(jDPayPaymentACCEntity.errorMsg)) {
            string = jDPayPaymentACCEntity.errorMsg;
        } else {
            string = g0.a(fragmentActivity) ? fragmentActivity.getString(R.string.lib_cashier_sdk_credit_pay_failure) : "";
        }
        if (g0.a(fragmentActivity)) {
            com.jd.lib.cashier.sdk.d.g.b.b.a().b(fragmentActivity, string, cashierCommonPopConfig2, cashierCommonPopConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, JDPayPaymentACCEntity jDPayPaymentACCEntity, String str, String str2) {
        if (jDPayPaymentACCEntity != null) {
            com.jd.lib.cashier.sdk.d.g.c.c.a aVar = new com.jd.lib.cashier.sdk.d.g.c.c.a();
            aVar.b = jDPayPaymentACCEntity.appId;
            aVar.a = str2;
            aVar.f3265c = jDPayPaymentACCEntity.sdkParam;
            aVar.d = jDPayPaymentACCEntity.cashierUrl;
            if (!TextUtils.isEmpty(jDPayPaymentACCEntity.controllActionParam)) {
                aVar.f3266e = jDPayPaymentACCEntity.controllActionParam;
            }
            d b = com.jd.lib.cashier.sdk.e.e.b.a.a().b(com.jd.lib.cashier.sdk.d.g.g.f.JDPAY);
            if (b == null || !g0.a(fragmentActivity)) {
                return;
            }
            b.b(fragmentActivity, aVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.d.g.c.c.b bVar) {
        if (bVar != null) {
            j(new C0119a(bVar));
            h(bVar);
        }
    }
}
