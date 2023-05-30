package com.jd.lib.cashier.sdk.i.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.quickpay.aac.viewmodel.CashierQuickPayViewModel;
import com.jd.lib.cashier.sdk.quickpay.bean.WXPayServiceEntity;

/* loaded from: classes14.dex */
public class e extends b {

    /* loaded from: classes14.dex */
    public class a implements f<WXPayServiceEntity> {

        /* renamed from: g */
        final /* synthetic */ com.jd.lib.cashier.sdk.i.b.b f3581g;

        a(com.jd.lib.cashier.sdk.i.b.b bVar) {
            e.this = r1;
            this.f3581g = bVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a */
        public void callBack(WXPayServiceEntity wXPayServiceEntity) {
            if (wXPayServiceEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                e.this.t(wXPayServiceEntity);
                e.this.q(this.f3581g.getActivity());
            } else if (!TextUtils.isEmpty(wXPayServiceEntity.errorCode)) {
                e.this.t(wXPayServiceEntity);
                e.this.q(this.f3581g.getActivity());
            } else {
                e.this.s(this.f3581g.getActivity(), wXPayServiceEntity);
            }
        }
    }

    public void q(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((CashierQuickPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierQuickPayViewModel.class)).e().a();
        }
    }

    public void s(FragmentActivity fragmentActivity, WXPayServiceEntity wXPayServiceEntity) {
        if (g0.a(fragmentActivity)) {
            CashierQuickPayViewModel cashierQuickPayViewModel = (CashierQuickPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierQuickPayViewModel.class);
            if (wXPayServiceEntity != null && wXPayServiceEntity.payInfo != null) {
                cashierQuickPayViewModel.f().b(cashierQuickPayViewModel.b(), wXPayServiceEntity);
            } else {
                cashierQuickPayViewModel.e().a();
            }
        }
    }

    public void t(WXPayServiceEntity wXPayServiceEntity) {
        if (wXPayServiceEntity == null || TextUtils.isEmpty(wXPayServiceEntity.errorMsg)) {
            return;
        }
        f0.c(wXPayServiceEntity.errorMsg);
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: r */
    public void e(com.jd.lib.cashier.sdk.i.b.b bVar) {
        if (bVar != null) {
            j(new a(bVar));
            h(bVar);
        }
    }
}
