package com.jd.lib.cashier.sdk.e.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.creditpay.aac.viewmodel.CashierCreditPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoPayPlanResponse;

/* loaded from: classes14.dex */
public class c extends com.jd.lib.cashier.sdk.h.a.a.l.b {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements f<BaiTiaoPayPlanResponse> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.h.f.b f3311g;

        a(com.jd.lib.cashier.sdk.h.f.b bVar) {
            this.f3311g = bVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(BaiTiaoPayPlanResponse baiTiaoPayPlanResponse) {
            if (baiTiaoPayPlanResponse.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                c.this.p(this.f3311g.getActivity(), baiTiaoPayPlanResponse);
            } else if (!TextUtils.isEmpty(baiTiaoPayPlanResponse.errorCode)) {
                c.this.p(this.f3311g.getActivity(), baiTiaoPayPlanResponse);
            } else {
                c.this.r(this.f3311g.getActivity(), baiTiaoPayPlanResponse);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, BaiTiaoPayPlanResponse baiTiaoPayPlanResponse) {
        if (fragmentActivity == null || baiTiaoPayPlanResponse == null || !g0.a(fragmentActivity)) {
            return;
        }
        String str = baiTiaoPayPlanResponse.errorMsg;
        if (TextUtils.isEmpty(str)) {
            if (TextUtils.equals("1312", baiTiaoPayPlanResponse.errorCode)) {
                str = fragmentActivity.getString(R.string.lib_cashier_sdk_baitiao_plan_error_message);
            } else {
                str = fragmentActivity.getString(R.string.lib_cashier_sdk_plan_exception_message);
            }
        }
        f0.c(str);
        CashierCreditPayViewModel cashierCreditPayViewModel = (CashierCreditPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierCreditPayViewModel.class);
        if (TextUtils.equals("1312", baiTiaoPayPlanResponse.errorCode)) {
            cashierCreditPayViewModel.g().b(baiTiaoPayPlanResponse);
        } else {
            cashierCreditPayViewModel.g().a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, BaiTiaoPayPlanResponse baiTiaoPayPlanResponse) {
        if (!g0.a(fragmentActivity) || baiTiaoPayPlanResponse == null) {
            return;
        }
        ((CashierCreditPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierCreditPayViewModel.class)).g().c(baiTiaoPayPlanResponse);
        if (TextUtils.isEmpty(baiTiaoPayPlanResponse.discountConflictTip)) {
            return;
        }
        f0.c(baiTiaoPayPlanResponse.discountConflictTip);
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.h.f.b bVar) {
        if (bVar != null) {
            j(new a(bVar));
            h(bVar);
        }
    }
}
