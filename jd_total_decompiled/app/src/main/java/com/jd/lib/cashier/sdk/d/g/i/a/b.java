package com.jd.lib.cashier.sdk.d.g.i.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.paychannel.unionpay.entity.UnionPayEntity;
import com.jd.lib.cashier.sdk.core.paychannel.unionpay.entity.UnionPayInfoEntity;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.g.g.d;
import com.jd.lib.cashier.sdk.d.g.g.e;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.d.g.i.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements f<UnionPayEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.d.g.i.c.b f3300g;

        a(com.jd.lib.cashier.sdk.d.g.i.c.b bVar) {
            this.f3300g = bVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(UnionPayEntity unionPayEntity) {
            if (unionPayEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.p(this.f3300g.getActivity(), unionPayEntity);
            } else if (!TextUtils.isEmpty(unionPayEntity.errorCode) || unionPayEntity.commonPopupInfo != null) {
                b.this.p(this.f3300g.getActivity(), unionPayEntity);
            } else if (unionPayEntity.payInfo != null) {
                b.this.r(this.f3300g.getActivity(), unionPayEntity, this.f3300g.f3287c);
            } else {
                b.this.p(this.f3300g.getActivity(), unionPayEntity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, UnionPayEntity unionPayEntity) {
        CashierCommonPopConfig cashierCommonPopConfig;
        String string;
        CashierCommonPopConfig cashierCommonPopConfig2 = null;
        if (unionPayEntity != null) {
            cashierCommonPopConfig = unionPayEntity.commonPopupInfo;
            cashierCommonPopConfig2 = unionPayEntity.orderExceptionInfo;
        } else {
            cashierCommonPopConfig = null;
        }
        if (unionPayEntity != null && !TextUtils.isEmpty(unionPayEntity.errorMsg)) {
            string = unionPayEntity.errorMsg;
        } else {
            string = g0.a(fragmentActivity) ? fragmentActivity.getString(R.string.lib_cashier_sdk_pay_union_failure) : "";
        }
        if (g0.a(fragmentActivity)) {
            com.jd.lib.cashier.sdk.d.g.b.b.a().b(fragmentActivity, string, cashierCommonPopConfig2, cashierCommonPopConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, UnionPayEntity unionPayEntity, String str) {
        UnionPayInfoEntity unionPayInfoEntity;
        UnionPayInfoEntity unionPayInfoEntity2;
        com.jd.lib.cashier.sdk.d.g.i.c.a aVar = new com.jd.lib.cashier.sdk.d.g.i.c.a();
        if (unionPayEntity != null && (unionPayInfoEntity2 = unionPayEntity.payInfo) != null) {
            aVar.f3302c = unionPayInfoEntity2.seType;
        }
        aVar.a = str;
        if (unionPayEntity == null || (unionPayInfoEntity = unionPayEntity.payInfo) == null) {
            return;
        }
        aVar.b = unionPayInfoEntity.payMessage;
        d d = e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.UNIONPAY);
        if (!g0.a(fragmentActivity) || d == null) {
            return;
        }
        d.b(fragmentActivity, aVar);
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.d.g.i.c.b bVar) {
        if (bVar != null) {
            j(new a(bVar));
            h(bVar);
        }
    }
}
