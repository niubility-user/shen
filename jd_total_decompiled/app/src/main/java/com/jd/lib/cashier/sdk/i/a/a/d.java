package com.jd.lib.cashier.sdk.i.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.quickpay.aac.viewmodel.CashierQuickPayViewModel;
import com.jd.lib.cashier.sdk.quickpay.bean.JDPayServiceEntity;

/* loaded from: classes14.dex */
public class d extends com.jd.lib.cashier.sdk.i.a.a.a {

    /* loaded from: classes14.dex */
    public class a implements f<JDPayServiceEntity> {

        /* renamed from: g */
        final /* synthetic */ com.jd.lib.cashier.sdk.i.b.a f3579g;

        a(com.jd.lib.cashier.sdk.i.b.a aVar) {
            d.this = r1;
            this.f3579g = aVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a */
        public void callBack(JDPayServiceEntity jDPayServiceEntity) {
            if (jDPayServiceEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                d.this.t(jDPayServiceEntity);
                d.this.q(this.f3579g.getActivity());
            } else if (!TextUtils.isEmpty(jDPayServiceEntity.errorCode)) {
                d.this.t(jDPayServiceEntity);
                d.this.q(this.f3579g.getActivity());
            } else {
                d.this.s(this.f3579g.getActivity(), jDPayServiceEntity);
            }
        }
    }

    public void q(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((CashierQuickPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierQuickPayViewModel.class)).e().a();
        }
    }

    public void s(FragmentActivity fragmentActivity, JDPayServiceEntity jDPayServiceEntity) {
        if (g0.a(fragmentActivity)) {
            CashierQuickPayViewModel cashierQuickPayViewModel = (CashierQuickPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierQuickPayViewModel.class);
            if (jDPayServiceEntity != null) {
                cashierQuickPayViewModel.f().a(cashierQuickPayViewModel.b(), jDPayServiceEntity);
            } else {
                cashierQuickPayViewModel.e().a();
            }
        }
    }

    public void t(JDPayServiceEntity jDPayServiceEntity) {
        if (jDPayServiceEntity == null || TextUtils.isEmpty(jDPayServiceEntity.errorMsg)) {
            return;
        }
        f0.c(jDPayServiceEntity.errorMsg);
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: r */
    public void e(com.jd.lib.cashier.sdk.i.b.a aVar) {
        if (aVar != null) {
            j(new a(aVar));
            h(aVar);
        }
    }
}
