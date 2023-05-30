package com.jd.lib.cashier.sdk.h.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.paychannel.jdpay.entity.JDPayPaymentACCEntity;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;

/* loaded from: classes14.dex */
public class k extends com.jd.lib.cashier.sdk.d.g.c.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements com.jd.lib.cashier.sdk.core.utils.f<JDPayPaymentACCEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.d.g.c.c.b f3505g;

        a(com.jd.lib.cashier.sdk.d.g.c.c.b bVar) {
            this.f3505g = bVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(JDPayPaymentACCEntity jDPayPaymentACCEntity) {
            if (jDPayPaymentACCEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                k.this.p(this.f3505g.getActivity(), jDPayPaymentACCEntity);
            } else if (!TextUtils.isEmpty(jDPayPaymentACCEntity.errorCode)) {
                k.this.p(this.f3505g.getActivity(), jDPayPaymentACCEntity);
            } else if (jDPayPaymentACCEntity.checkParamIsValid()) {
                k.this.r(this.f3505g.getActivity(), jDPayPaymentACCEntity, this.f3505g);
            } else {
                k.this.p(this.f3505g.getActivity(), jDPayPaymentACCEntity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f3507g;

        b(k kVar, FragmentActivity fragmentActivity) {
            this.f3507g = fragmentActivity;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((CashierPayViewModel) ViewModelProviders.of(this.f3507g).get(CashierPayViewModel.class)).k(this.f3507g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, JDPayPaymentACCEntity jDPayPaymentACCEntity) {
        if (g0.a(fragmentActivity)) {
            f0.c(fragmentActivity.getString(R.string.lib_cashier_sdk_pay_network_exception));
        }
        if (!g0.a(fragmentActivity) || jDPayPaymentACCEntity == null || TextUtils.equals(jDPayPaymentACCEntity.errorCode, "-3")) {
            return;
        }
        fragmentActivity.runOnUiThread(new b(this, fragmentActivity));
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
        String str2 = bVar.x;
        String str3 = bVar.f3269f;
        aVar.f3267f = bVar.w;
        aVar.a = "NEWBANKCARD";
        if (!TextUtils.isEmpty(jDPayPaymentACCEntity.controllActionParam)) {
            aVar.f3266e = jDPayPaymentACCEntity.controllActionParam;
        }
        com.jd.lib.cashier.sdk.d.g.g.d d = com.jd.lib.cashier.sdk.d.g.g.e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.JDPAY);
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
