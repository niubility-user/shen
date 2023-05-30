package com.jd.lib.cashier.sdk.d.g.j.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.paychannel.wxpay.entity.WXPayEntity;
import com.jd.lib.cashier.sdk.core.paychannel.wxpay.entity.WXPayInfoEntity;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.g.g.d;
import com.jd.lib.cashier.sdk.d.g.g.e;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.d.g.j.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements f<WXPayEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.d.g.j.c.b f3303g;

        a(com.jd.lib.cashier.sdk.d.g.j.c.b bVar) {
            this.f3303g = bVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(WXPayEntity wXPayEntity) {
            if (wXPayEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.p(this.f3303g.getActivity(), wXPayEntity);
            } else if (!TextUtils.isEmpty(wXPayEntity.errorCode) || wXPayEntity.commonPopupInfo != null) {
                b.this.p(this.f3303g.getActivity(), wXPayEntity);
            } else if (wXPayEntity.payInfo != null) {
                b.this.r(this.f3303g.getActivity(), wXPayEntity.payInfo, this.f3303g.f3287c);
            } else {
                b.this.p(this.f3303g.getActivity(), wXPayEntity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, WXPayEntity wXPayEntity) {
        CashierCommonPopConfig cashierCommonPopConfig;
        String string;
        CashierCommonPopConfig cashierCommonPopConfig2 = null;
        if (wXPayEntity != null) {
            cashierCommonPopConfig = wXPayEntity.commonPopupInfo;
            cashierCommonPopConfig2 = wXPayEntity.orderExceptionInfo;
        } else {
            cashierCommonPopConfig = null;
        }
        if (wXPayEntity != null && !TextUtils.isEmpty(wXPayEntity.errorMsg)) {
            string = wXPayEntity.errorMsg;
        } else {
            string = g0.a(fragmentActivity) ? fragmentActivity.getString(R.string.lib_cashier_sdk_pay_wx_failure) : "";
        }
        if (g0.a(fragmentActivity)) {
            com.jd.lib.cashier.sdk.d.g.b.b.a().b(fragmentActivity, string, cashierCommonPopConfig2, cashierCommonPopConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, WXPayInfoEntity wXPayInfoEntity, String str) {
        if (wXPayInfoEntity != null) {
            com.jd.lib.cashier.sdk.d.g.j.c.a aVar = new com.jd.lib.cashier.sdk.d.g.j.c.a();
            aVar.d = wXPayInfoEntity.getSign();
            aVar.a = str;
            wXPayInfoEntity.getPayEnum();
            aVar.f3306e = wXPayInfoEntity.getPrepayId();
            aVar.f3308g = wXPayInfoEntity.getNonceStr();
            aVar.f3307f = wXPayInfoEntity.getPartnerId();
            aVar.b = wXPayInfoEntity.getTimeStamp();
            aVar.f3305c = wXPayInfoEntity.getPackage();
            d d = e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.WEIXIN);
            if (!g0.a(fragmentActivity) || d == null) {
                return;
            }
            d.b(fragmentActivity, aVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.d.g.j.c.b bVar) {
        if (bVar != null) {
            j(new a(bVar));
            h(bVar);
        }
    }
}
