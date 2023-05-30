package com.jd.lib.cashier.sdk.j.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.risk.aac.viewmodel.CashierRiskViewModel;
import com.jd.lib.cashier.sdk.risk.bean.CashierRiskCheckEntity;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.j.a.a.a {

    /* loaded from: classes14.dex */
    public class a implements f<CashierRiskCheckEntity> {

        /* renamed from: g */
        final /* synthetic */ com.jd.lib.cashier.sdk.j.b.a f3631g;

        a(com.jd.lib.cashier.sdk.j.b.a aVar) {
            b.this = r1;
            this.f3631g = aVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a */
        public void callBack(CashierRiskCheckEntity cashierRiskCheckEntity) {
            if (cashierRiskCheckEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.p(this.f3631g.getActivity());
            } else if (!TextUtils.isEmpty(cashierRiskCheckEntity.errorCode)) {
                b.this.p(this.f3631g.getActivity());
            } else {
                b.this.r(this.f3631g.getActivity(), cashierRiskCheckEntity);
            }
        }
    }

    public void p(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((CashierRiskViewModel) ViewModelProviders.of(fragmentActivity).get(CashierRiskViewModel.class)).e().b();
        }
    }

    public void r(FragmentActivity fragmentActivity, CashierRiskCheckEntity cashierRiskCheckEntity) {
        if (!g0.a(fragmentActivity) || cashierRiskCheckEntity == null) {
            return;
        }
        CashierRiskViewModel cashierRiskViewModel = (CashierRiskViewModel) ViewModelProviders.of(fragmentActivity).get(CashierRiskViewModel.class);
        if (cashierRiskCheckEntity.maliceFlag) {
            cashierRiskViewModel.e().c();
        } else {
            cashierRiskViewModel.e().a();
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q */
    public void e(com.jd.lib.cashier.sdk.j.b.a aVar) {
        if (aVar != null) {
            j(new a(aVar));
            h(aVar);
        }
    }
}
