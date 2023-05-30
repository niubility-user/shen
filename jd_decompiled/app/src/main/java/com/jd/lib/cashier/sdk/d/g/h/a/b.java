package com.jd.lib.cashier.sdk.d.g.h.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.paychannel.qqwallet.entity.QQWalletPayEntity;
import com.jd.lib.cashier.sdk.core.paychannel.qqwallet.entity.QQWalletPayInfoEntity;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.g.g.d;
import com.jd.lib.cashier.sdk.d.g.g.e;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.d.g.h.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements f<QQWalletPayEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.d.g.h.c.b f3290g;

        a(com.jd.lib.cashier.sdk.d.g.h.c.b bVar) {
            this.f3290g = bVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(QQWalletPayEntity qQWalletPayEntity) {
            if (qQWalletPayEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.p(this.f3290g.getActivity(), qQWalletPayEntity);
            } else if (!TextUtils.isEmpty(qQWalletPayEntity.errorCode) || qQWalletPayEntity.commonPopupInfo != null) {
                b.this.p(this.f3290g.getActivity(), qQWalletPayEntity);
            } else if (qQWalletPayEntity.payInfo != null) {
                b.this.r(this.f3290g.getActivity(), qQWalletPayEntity.payInfo, this.f3290g.f3287c);
            } else {
                b.this.p(this.f3290g.getActivity(), qQWalletPayEntity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, QQWalletPayEntity qQWalletPayEntity) {
        CashierCommonPopConfig cashierCommonPopConfig;
        String string;
        CashierCommonPopConfig cashierCommonPopConfig2 = null;
        if (qQWalletPayEntity != null) {
            cashierCommonPopConfig = qQWalletPayEntity.commonPopupInfo;
            cashierCommonPopConfig2 = qQWalletPayEntity.orderExceptionInfo;
        } else {
            cashierCommonPopConfig = null;
        }
        if (qQWalletPayEntity != null && !TextUtils.isEmpty(qQWalletPayEntity.errorMsg)) {
            string = qQWalletPayEntity.errorMsg;
        } else {
            string = g0.a(fragmentActivity) ? fragmentActivity.getString(R.string.lib_cashier_sdk_pay_qq_failure) : "";
        }
        if (g0.a(fragmentActivity)) {
            com.jd.lib.cashier.sdk.d.g.b.b.a().b(fragmentActivity, string, cashierCommonPopConfig2, cashierCommonPopConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, QQWalletPayInfoEntity qQWalletPayInfoEntity, String str) {
        if (qQWalletPayInfoEntity != null) {
            com.jd.lib.cashier.sdk.d.g.h.c.a aVar = new com.jd.lib.cashier.sdk.d.g.h.c.a();
            aVar.a = str;
            aVar.b = qQWalletPayInfoEntity.sig;
            aVar.f3294f = qQWalletPayInfoEntity.appId;
            aVar.f3297i = qQWalletPayInfoEntity.nonce;
            aVar.f3296h = qQWalletPayInfoEntity.pubAcc;
            aVar.f3293e = qQWalletPayInfoEntity.tokenId;
            aVar.f3295g = qQWalletPayInfoEntity.sigType;
            aVar.f3292c = qQWalletPayInfoEntity.timeStamp;
            aVar.f3299k = qQWalletPayInfoEntity.pubAccHint;
            aVar.f3298j = qQWalletPayInfoEntity.bargainorId;
            aVar.d = qQWalletPayInfoEntity.serialNumber;
            d d = e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.QQWALLET);
            if (!g0.a(fragmentActivity) || d == null) {
                return;
            }
            d.b(fragmentActivity, aVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.d.g.h.c.b bVar) {
        if (bVar != null) {
            j(new a(bVar));
            h(bVar);
        }
    }
}
