package com.jd.lib.cashier.sdk.e.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.creditpay.aac.viewmodel.CashierCreditPayViewModel;
import com.jd.lib.cashier.sdk.creditpay.bean.CreditPayEntity;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.e.a.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements f<CreditPayEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.e.d.a f3309g;

        a(com.jd.lib.cashier.sdk.e.d.a aVar) {
            this.f3309g = aVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(CreditPayEntity creditPayEntity) {
            if (creditPayEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.p(this.f3309g.getActivity(), creditPayEntity);
            } else if (!TextUtils.isEmpty(creditPayEntity.errorCode)) {
                b.this.p(this.f3309g.getActivity(), creditPayEntity);
            } else if (creditPayEntity.creditAuthInfo != null) {
                b.this.r(this.f3309g.getActivity(), creditPayEntity);
            } else {
                b.this.p(this.f3309g.getActivity(), creditPayEntity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, CreditPayEntity creditPayEntity) {
        if (!g0.a(fragmentActivity) || creditPayEntity == null) {
            return;
        }
        CashierCreditPayViewModel cashierCreditPayViewModel = (CashierCreditPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierCreditPayViewModel.class);
        cashierCreditPayViewModel.b().q = "-100";
        CashierCommonPopConfig cashierCommonPopConfig = creditPayEntity.orderExceptionInfo;
        if (cashierCommonPopConfig != null && cashierCommonPopConfig.canDialogShow()) {
            cashierCreditPayViewModel.k().a(creditPayEntity);
        }
        String str = creditPayEntity.errorMsg;
        if (TextUtils.isEmpty(str)) {
            str = fragmentActivity.getString(R.string.lib_cashier_sdk_credit_pay_service_exception);
        }
        cashierCreditPayViewModel.l().a(str);
        if (TextUtils.isEmpty(creditPayEntity.orderId)) {
            return;
        }
        com.jd.lib.cashier.sdk.e.c.a.h(fragmentActivity, creditPayEntity.orderId, creditPayEntity.payprice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, CreditPayEntity creditPayEntity) {
        if (!g0.a(fragmentActivity) || creditPayEntity == null) {
            return;
        }
        CashierCreditPayViewModel cashierCreditPayViewModel = (CashierCreditPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierCreditPayViewModel.class);
        cashierCreditPayViewModel.b().r = creditPayEntity;
        cashierCreditPayViewModel.l().c();
        cashierCreditPayViewModel.i().a(creditPayEntity);
        if (TextUtils.isEmpty(creditPayEntity.orderId)) {
            return;
        }
        com.jd.lib.cashier.sdk.e.c.a.h(fragmentActivity, creditPayEntity.orderId, creditPayEntity.payprice);
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.e.d.a aVar) {
        if (aVar != null) {
            j(new a(aVar));
            h(aVar);
        }
    }
}
