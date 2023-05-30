package com.jd.lib.cashier.sdk.a.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.btcombinationpay.aac.viewmodel.BtCombinationPayViewModel;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.BTSkuCalculateEntity;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.RequestParamBTSkuCalculateRate;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;

/* loaded from: classes14.dex */
public class c extends com.jd.lib.cashier.sdk.a.a.a.a {

    /* loaded from: classes14.dex */
    public class a implements f<BTSkuCalculateEntity> {

        /* renamed from: g */
        final /* synthetic */ RequestParamBTSkuCalculateRate f2788g;

        a(RequestParamBTSkuCalculateRate requestParamBTSkuCalculateRate) {
            c.this = r1;
            this.f2788g = requestParamBTSkuCalculateRate;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a */
        public void callBack(BTSkuCalculateEntity bTSkuCalculateEntity) {
            if (bTSkuCalculateEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                c.this.r(this.f2788g.getActivity(), bTSkuCalculateEntity.errorCode, bTSkuCalculateEntity.errorMsg);
            } else if (!TextUtils.isEmpty(bTSkuCalculateEntity.errorCode)) {
                c.this.q(this.f2788g.getActivity(), bTSkuCalculateEntity.errorMsg, bTSkuCalculateEntity.errorCode);
            } else {
                c.this.t(this.f2788g.getActivity(), bTSkuCalculateEntity);
            }
        }
    }

    public void q(FragmentActivity fragmentActivity, String str, String str2) {
        if (g0.a(fragmentActivity)) {
            if (!TextUtils.isEmpty(str)) {
                f0.c(str);
            }
            ((BtCombinationPayViewModel) ViewModelProviders.of(fragmentActivity).get(BtCombinationPayViewModel.class)).f().b(str);
        }
    }

    public void r(FragmentActivity fragmentActivity, String str, String str2) {
        if (g0.a(fragmentActivity)) {
            if (!TextUtils.isEmpty(str2)) {
                f0.c(str2);
            }
            ((BtCombinationPayViewModel) ViewModelProviders.of(fragmentActivity).get(BtCombinationPayViewModel.class)).f().b(str2);
        }
    }

    public void t(FragmentActivity fragmentActivity, BTSkuCalculateEntity bTSkuCalculateEntity) {
        if (g0.a(fragmentActivity)) {
            BtCombinationPayViewModel btCombinationPayViewModel = (BtCombinationPayViewModel) ViewModelProviders.of(fragmentActivity).get(BtCombinationPayViewModel.class);
            if (bTSkuCalculateEntity != null) {
                btCombinationPayViewModel.b().f2797j = bTSkuCalculateEntity.selectedSplitSkuPlanInfoList;
                if (!TextUtils.isEmpty(bTSkuCalculateEntity.skuSplitPopTip)) {
                    f0.c(bTSkuCalculateEntity.skuSplitPopTip);
                }
            }
            btCombinationPayViewModel.f().d(bTSkuCalculateEntity);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: s */
    public void e(RequestParamBTSkuCalculateRate requestParamBTSkuCalculateRate) {
        if (requestParamBTSkuCalculateRate != null) {
            j(new a(requestParamBTSkuCalculateRate));
            h(requestParamBTSkuCalculateRate);
        }
    }
}
