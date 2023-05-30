package com.jd.lib.cashier.sdk.i.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.common.bean.CashierGetSuccessUrlEntity;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.quickpay.aac.viewmodel.CashierQuickPayViewModel;

/* loaded from: classes14.dex */
public class c extends com.jd.lib.cashier.sdk.b.a.a {

    /* loaded from: classes14.dex */
    public class a implements f<CashierGetSuccessUrlEntity> {

        /* renamed from: g */
        final /* synthetic */ com.jd.lib.cashier.sdk.b.f.a f3577g;

        a(com.jd.lib.cashier.sdk.b.f.a aVar) {
            c.this = r1;
            this.f3577g = aVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a */
        public void callBack(CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity) {
            if (cashierGetSuccessUrlEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                c.this.p(this.f3577g.getActivity(), cashierGetSuccessUrlEntity.errorMsg);
            } else if (!TextUtils.isEmpty(cashierGetSuccessUrlEntity.errorCode)) {
                c.this.p(this.f3577g.getActivity(), cashierGetSuccessUrlEntity.errorMsg);
            } else {
                c.this.r(this.f3577g.getActivity(), cashierGetSuccessUrlEntity);
            }
        }
    }

    public void p(FragmentActivity fragmentActivity, String str) {
        if (g0.a(fragmentActivity)) {
            CashierQuickPayViewModel cashierQuickPayViewModel = (CashierQuickPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierQuickPayViewModel.class);
            cashierQuickPayViewModel.e().a();
            cashierQuickPayViewModel.b().p = false;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f0.c(str);
    }

    public void r(FragmentActivity fragmentActivity, CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity) {
        if (!g0.a(fragmentActivity) || cashierGetSuccessUrlEntity == null) {
            return;
        }
        CashierQuickPayViewModel cashierQuickPayViewModel = (CashierQuickPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierQuickPayViewModel.class);
        cashierQuickPayViewModel.e().b(cashierGetSuccessUrlEntity, cashierQuickPayViewModel.b().o);
        cashierQuickPayViewModel.b().p = false;
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q */
    public void e(com.jd.lib.cashier.sdk.b.f.a aVar) {
        if (aVar != null) {
            j(new a(aVar));
            h(aVar);
        }
    }
}
