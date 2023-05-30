package com.jd.lib.cashier.sdk.h.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.CyberMoneyCouponResponse;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard;

/* loaded from: classes14.dex */
public class j extends com.jd.lib.cashier.sdk.h.a.a.l.g {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements com.jd.lib.cashier.sdk.core.utils.f<CyberMoneyCouponResponse> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.h.f.g f3503g;

        a(com.jd.lib.cashier.sdk.h.f.g gVar) {
            this.f3503g = gVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(CyberMoneyCouponResponse cyberMoneyCouponResponse) {
            cyberMoneyCouponResponse.checkNullObjAndInit();
            if (cyberMoneyCouponResponse.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                j.this.p(this.f3503g.getActivity(), cyberMoneyCouponResponse.errorMsg);
            } else if (!TextUtils.isEmpty(cyberMoneyCouponResponse.errorCode)) {
                j.this.p(this.f3503g.getActivity(), cyberMoneyCouponResponse.errorMsg);
            } else {
                j.this.r(this.f3503g.getActivity(), this.f3503g.f3557f, cyberMoneyCouponResponse);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, String str) {
        if (g0.a(fragmentActivity)) {
            if (TextUtils.isEmpty(str)) {
                str = fragmentActivity.getString(R.string.lib_cashier_sdk_dialog_digital_money_coupon_exception_message);
            }
            f0.c(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, DigitalMoneyBankCard digitalMoneyBankCard, CyberMoneyCouponResponse cyberMoneyCouponResponse) {
        if (!g0.a(fragmentActivity) || cyberMoneyCouponResponse == null) {
            return;
        }
        ((CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)).z().b(digitalMoneyBankCard, cyberMoneyCouponResponse);
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.h.f.g gVar) {
        if (gVar != null) {
            j(new a(gVar));
            h(gVar);
        }
    }
}
