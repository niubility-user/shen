package com.jd.lib.cashier.sdk.h.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.common.bean.CashierGetSuccessUrlEntity;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;

/* loaded from: classes14.dex */
public class h extends com.jd.lib.cashier.sdk.b.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements com.jd.lib.cashier.sdk.core.utils.f<CashierGetSuccessUrlEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.b.f.a f3501g;

        a(com.jd.lib.cashier.sdk.b.f.a aVar) {
            this.f3501g = aVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity) {
            if (cashierGetSuccessUrlEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                h.this.p(this.f3501g.getActivity(), cashierGetSuccessUrlEntity.errorMsg);
            } else if (!TextUtils.isEmpty(cashierGetSuccessUrlEntity.errorCode) || !TextUtils.isEmpty(cashierGetSuccessUrlEntity.errorMsg)) {
                h.this.p(this.f3501g.getActivity(), cashierGetSuccessUrlEntity.errorMsg);
            } else {
                h.this.r(this.f3501g.getActivity(), cashierGetSuccessUrlEntity, this.f3501g.a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, String str) {
        if (g0.a(fragmentActivity)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            cashierPayViewModel.F().b();
            cashierPayViewModel.b().G = false;
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
        CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
        if ("1".equals(cashierGetSuccessUrlEntity.graduallyPay)) {
            cashierPayViewModel.A().a(cashierGetSuccessUrlEntity);
        } else if (cashierGetSuccessUrlEntity.globalPresaleCombinedPayPopup != null) {
            cashierPayViewModel.G().a(cashierGetSuccessUrlEntity);
        } else {
            cashierPayViewModel.F().c(cashierGetSuccessUrlEntity, str, cashierPayViewModel.b().D);
        }
        cashierPayViewModel.b().G = false;
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
