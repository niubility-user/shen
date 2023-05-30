package com.jd.lib.cashier.sdk.h.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoCouponResponse;

/* loaded from: classes14.dex */
public class a extends com.jd.lib.cashier.sdk.h.a.a.l.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.lib.cashier.sdk.h.a.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public class C0122a implements com.jd.lib.cashier.sdk.core.utils.f<BaiTiaoCouponResponse> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.h.f.a f3480g;

        C0122a(com.jd.lib.cashier.sdk.h.f.a aVar) {
            this.f3480g = aVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(BaiTiaoCouponResponse baiTiaoCouponResponse) {
            if (baiTiaoCouponResponse.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                a.this.p(this.f3480g.getActivity(), baiTiaoCouponResponse);
            } else if (!TextUtils.isEmpty(baiTiaoCouponResponse.errorCode)) {
                a.this.p(this.f3480g.getActivity(), baiTiaoCouponResponse);
            } else {
                a.this.r(this.f3480g.getActivity(), baiTiaoCouponResponse);
            }
            baiTiaoCouponResponse.channelType = this.f3480g.b();
            baiTiaoCouponResponse.channelId = this.f3480g.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, BaiTiaoCouponResponse baiTiaoCouponResponse) {
        if (fragmentActivity != null && g0.a(fragmentActivity)) {
            if (TextUtils.equals("1312", baiTiaoCouponResponse.errorCode)) {
                String str = baiTiaoCouponResponse.errorMsg;
                if (TextUtils.isEmpty(str)) {
                    str = fragmentActivity.getString(R.string.lib_cashier_sdk_baitiao_plan_error_message);
                }
                f0.c(str);
            } else {
                String str2 = baiTiaoCouponResponse.errorMsg;
                if (TextUtils.isEmpty(str2)) {
                    str2 = fragmentActivity.getString(R.string.lib_cashier_sdk_multi_coupon_get_network_exception);
                }
                f0.c(str2);
            }
            ((CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)).p().a(baiTiaoCouponResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, BaiTiaoCouponResponse baiTiaoCouponResponse) {
        if (baiTiaoCouponResponse == null || fragmentActivity == null || !g0.a(fragmentActivity)) {
            return;
        }
        CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
        if (baiTiaoCouponResponse.canUseCouponList.isEmpty() && baiTiaoCouponResponse.cantUseCouponList.isEmpty()) {
            f0.c(fragmentActivity.getString(R.string.lib_cashier_sdk_multi_coupon_get_failed_message));
            cashierPayViewModel.p().a(baiTiaoCouponResponse);
            return;
        }
        cashierPayViewModel.p().b(baiTiaoCouponResponse);
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.h.f.a aVar) {
        if (aVar != null) {
            j(new C0122a(aVar));
            h(aVar);
        }
    }
}
