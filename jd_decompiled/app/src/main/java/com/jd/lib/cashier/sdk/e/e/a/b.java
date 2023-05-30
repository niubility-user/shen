package com.jd.lib.cashier.sdk.e.e.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.common.bean.CashierGetSuccessUrlEntity;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.creditpay.aac.viewmodel.CashierCreditPayViewModel;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.b.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements f<CashierGetSuccessUrlEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.b.f.a f3336g;

        a(com.jd.lib.cashier.sdk.b.f.a aVar) {
            this.f3336g = aVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity) {
            if (cashierGetSuccessUrlEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.p(this.f3336g.getActivity(), cashierGetSuccessUrlEntity.errorMsg);
            } else if (!TextUtils.isEmpty(cashierGetSuccessUrlEntity.errorCode) || !TextUtils.isEmpty(cashierGetSuccessUrlEntity.errorMsg)) {
                b.this.p(this.f3336g.getActivity(), cashierGetSuccessUrlEntity.errorMsg);
            } else {
                b.this.r(this.f3336g.getActivity(), cashierGetSuccessUrlEntity, this.f3336g.a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, String str) {
        if (g0.a(fragmentActivity)) {
            ((CashierCreditPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierCreditPayViewModel.class)).h().a();
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f0.c(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity, String str) {
        if (!g0.a(fragmentActivity) || cashierGetSuccessUrlEntity == null) {
            return;
        }
        CashierCreditPayViewModel cashierCreditPayViewModel = (CashierCreditPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierCreditPayViewModel.class);
        cashierCreditPayViewModel.h().b(cashierGetSuccessUrlEntity, str, cashierCreditPayViewModel.b().p);
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.b.f.a aVar) {
        if (aVar != null) {
            j(new a(aVar));
            h(aVar);
        }
    }
}
