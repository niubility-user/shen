package com.jd.lib.cashier.sdk.a.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.btcombinationpay.aac.viewmodel.BtCombinationPayViewModel;
import com.jd.lib.cashier.sdk.btcombinationpay.view.BtCombinationPayActivity;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.paychannel.jdpay.entity.JDPayPaymentACCEntity;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.d.g.g.d;
import com.jd.lib.cashier.sdk.d.g.g.e;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.d.g.c.a.a {

    /* loaded from: classes14.dex */
    public class a implements f<JDPayPaymentACCEntity> {

        /* renamed from: g */
        final /* synthetic */ com.jd.lib.cashier.sdk.d.g.c.c.b f2784g;

        a(com.jd.lib.cashier.sdk.d.g.c.c.b bVar) {
            b.this = r1;
            this.f2784g = bVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a */
        public void callBack(JDPayPaymentACCEntity jDPayPaymentACCEntity) {
            b.this.t(this.f2784g.getActivity());
            if (jDPayPaymentACCEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.q(this.f2784g.getActivity(), jDPayPaymentACCEntity, this.f2784g.appId);
            } else if (!TextUtils.isEmpty(jDPayPaymentACCEntity.errorCode)) {
                b.this.q(this.f2784g.getActivity(), jDPayPaymentACCEntity, this.f2784g.appId);
            } else if (jDPayPaymentACCEntity.checkParamIsValid()) {
                b.this.s(this.f2784g.getActivity(), jDPayPaymentACCEntity, this.f2784g);
            } else {
                b.this.q(this.f2784g.getActivity(), jDPayPaymentACCEntity, this.f2784g.appId);
            }
        }
    }

    /* renamed from: com.jd.lib.cashier.sdk.a.a.a.b$b */
    /* loaded from: classes14.dex */
    public class RunnableC0093b implements Runnable {

        /* renamed from: g */
        final /* synthetic */ JDPayPaymentACCEntity f2786g;

        /* renamed from: h */
        final /* synthetic */ FragmentActivity f2787h;

        RunnableC0093b(b bVar, JDPayPaymentACCEntity jDPayPaymentACCEntity, FragmentActivity fragmentActivity) {
            this.f2786g = jDPayPaymentACCEntity;
            this.f2787h = fragmentActivity;
        }

        @Override // java.lang.Runnable
        public void run() {
            CashierCommonPopConfig cashierCommonPopConfig;
            String string;
            JDPayPaymentACCEntity jDPayPaymentACCEntity = this.f2786g;
            CashierCommonPopConfig cashierCommonPopConfig2 = null;
            if (jDPayPaymentACCEntity != null) {
                cashierCommonPopConfig2 = jDPayPaymentACCEntity.commonPopupInfo;
                cashierCommonPopConfig = jDPayPaymentACCEntity.orderExceptionInfo;
            } else {
                cashierCommonPopConfig = null;
            }
            if (jDPayPaymentACCEntity != null && !TextUtils.isEmpty(jDPayPaymentACCEntity.errorMsg)) {
                string = this.f2786g.errorMsg;
            } else {
                string = g0.a(this.f2787h) ? this.f2787h.getString(R.string.lib_cashier_sdk_pay_jd_failure) : "";
            }
            ((BtCombinationPayActivity) this.f2787h).D(string, cashierCommonPopConfig, cashierCommonPopConfig2);
        }
    }

    public void q(FragmentActivity fragmentActivity, JDPayPaymentACCEntity jDPayPaymentACCEntity, String str) {
        if (g0.a(fragmentActivity)) {
            com.jd.lib.cashier.sdk.d.g.e.a.a(fragmentActivity, str, m.f().i(), jDPayPaymentACCEntity != null ? jDPayPaymentACCEntity.errorCode : DYConstants.DY_NULL_STR);
            if (fragmentActivity instanceof BtCombinationPayActivity) {
                fragmentActivity.runOnUiThread(new RunnableC0093b(this, jDPayPaymentACCEntity, fragmentActivity));
            }
        }
    }

    public void s(FragmentActivity fragmentActivity, JDPayPaymentACCEntity jDPayPaymentACCEntity, com.jd.lib.cashier.sdk.d.g.c.c.b bVar) {
        if (jDPayPaymentACCEntity == null || bVar == null) {
            return;
        }
        com.jd.lib.cashier.sdk.d.g.c.c.a aVar = new com.jd.lib.cashier.sdk.d.g.c.c.a();
        aVar.b = jDPayPaymentACCEntity.appId;
        aVar.f3265c = jDPayPaymentACCEntity.sdkParam;
        aVar.d = jDPayPaymentACCEntity.cashierUrl;
        String str = bVar.a;
        String str2 = bVar.x;
        String str3 = bVar.f3269f;
        aVar.f3267f = bVar.w;
        aVar.a = bVar.f3287c;
        if (!TextUtils.isEmpty(jDPayPaymentACCEntity.controllActionParam)) {
            aVar.f3266e = jDPayPaymentACCEntity.controllActionParam;
        }
        d d = e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.JDPAY);
        if (!g0.a(fragmentActivity) || d == null) {
            return;
        }
        d.b(fragmentActivity, aVar);
    }

    public void t(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((BtCombinationPayViewModel) ViewModelProviders.of(fragmentActivity).get(BtCombinationPayViewModel.class)).f().a();
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: r */
    public void e(com.jd.lib.cashier.sdk.d.g.c.c.b bVar) {
        if (bVar != null) {
            j(new a(bVar));
            h(bVar);
        }
    }
}
