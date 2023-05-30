package com.jd.lib.cashier.sdk.d.g.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.paychannel.cybermoneypay.entity.CyberMoneyPayEntity;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.g.g.d;
import com.jd.lib.cashier.sdk.d.g.g.e;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.d.g.a.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements f<CyberMoneyPayEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.d.g.a.c.b f3241g;

        a(com.jd.lib.cashier.sdk.d.g.a.c.b bVar) {
            this.f3241g = bVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(CyberMoneyPayEntity cyberMoneyPayEntity) {
            if (cyberMoneyPayEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.p(this.f3241g.getActivity(), cyberMoneyPayEntity);
            } else if (!TextUtils.isEmpty(cyberMoneyPayEntity.errorCode) || cyberMoneyPayEntity.commonPopupInfo != null) {
                b.this.p(this.f3241g.getActivity(), cyberMoneyPayEntity);
            } else {
                b.this.r(this.f3241g.getActivity(), cyberMoneyPayEntity, this.f3241g);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, CyberMoneyPayEntity cyberMoneyPayEntity) {
        CashierCommonPopConfig cashierCommonPopConfig;
        String string;
        CashierCommonPopConfig cashierCommonPopConfig2 = null;
        if (cyberMoneyPayEntity != null) {
            cashierCommonPopConfig = cyberMoneyPayEntity.commonPopupInfo;
            cashierCommonPopConfig2 = cyberMoneyPayEntity.orderExceptionInfo;
        } else {
            cashierCommonPopConfig = null;
        }
        if (cyberMoneyPayEntity != null && !TextUtils.isEmpty(cyberMoneyPayEntity.errorMsg)) {
            string = cyberMoneyPayEntity.errorMsg;
        } else {
            string = g0.a(fragmentActivity) ? fragmentActivity.getString(R.string.lib_cashier_sdk_pay_cyber_failure) : "";
        }
        if (g0.a(fragmentActivity)) {
            com.jd.lib.cashier.sdk.d.g.b.b.a().b(fragmentActivity, string, cashierCommonPopConfig2, cashierCommonPopConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, CyberMoneyPayEntity cyberMoneyPayEntity, com.jd.lib.cashier.sdk.d.g.a.c.b bVar) {
        if (cyberMoneyPayEntity != null) {
            com.jd.lib.cashier.sdk.d.g.a.c.a aVar = new com.jd.lib.cashier.sdk.d.g.a.c.a();
            aVar.f3245e = cyberMoneyPayEntity.appId;
            aVar.d = cyberMoneyPayEntity.sdkParam;
            aVar.a = bVar.f3287c;
            aVar.b = cyberMoneyPayEntity.cashierUrl;
            aVar.f3247g = bVar.f3249e;
            String str = bVar.a;
            String str2 = bVar.f3250f;
            aVar.f3248h = bVar.f3251g;
            String str3 = bVar.f3252h;
            if (!TextUtils.isEmpty(cyberMoneyPayEntity.controllActionParam)) {
                aVar.f3246f = cyberMoneyPayEntity.controllActionParam;
            }
            d d = e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.CYBERMONEY);
            if (!g0.a(fragmentActivity) || d == null) {
                return;
            }
            d.b(fragmentActivity, aVar);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.d.g.a.c.b bVar) {
        if (bVar != null) {
            j(new a(bVar));
            h(bVar);
        }
    }
}
