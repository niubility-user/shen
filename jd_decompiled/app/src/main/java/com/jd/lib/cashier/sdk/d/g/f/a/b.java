package com.jd.lib.cashier.sdk.d.g.f.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.paychannel.octopuspay.entity.OctopusPayEntity;
import com.jd.lib.cashier.sdk.core.paychannel.octopuspay.entity.OctopusPayInfo;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.g.g.d;
import com.jd.lib.cashier.sdk.d.g.g.e;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.d.g.f.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements f<OctopusPayEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.d.g.f.c.b f3280g;

        a(com.jd.lib.cashier.sdk.d.g.f.c.b bVar) {
            this.f3280g = bVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(OctopusPayEntity octopusPayEntity) {
            if (octopusPayEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.p(this.f3280g.getActivity(), octopusPayEntity);
            } else if (!TextUtils.isEmpty(octopusPayEntity.errorCode) || octopusPayEntity.commonPopupInfo != null) {
                b.this.p(this.f3280g.getActivity(), octopusPayEntity);
            } else {
                OctopusPayInfo octopusPayInfo = octopusPayEntity.payInfo;
                if (octopusPayInfo == null || TextUtils.isEmpty(octopusPayInfo.token)) {
                    b.this.p(this.f3280g.getActivity(), octopusPayEntity);
                } else {
                    b.this.r(this.f3280g.getActivity(), octopusPayEntity.payInfo, this.f3280g.f3287c);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, OctopusPayEntity octopusPayEntity) {
        CashierCommonPopConfig cashierCommonPopConfig;
        String string;
        CashierCommonPopConfig cashierCommonPopConfig2 = null;
        if (octopusPayEntity != null) {
            cashierCommonPopConfig = octopusPayEntity.commonPopupInfo;
            cashierCommonPopConfig2 = octopusPayEntity.orderExceptionInfo;
        } else {
            cashierCommonPopConfig = null;
        }
        if (octopusPayEntity != null && !TextUtils.isEmpty(octopusPayEntity.errorMsg)) {
            string = octopusPayEntity.errorMsg;
        } else {
            string = g0.a(fragmentActivity) ? fragmentActivity.getString(R.string.lib_cashier_sdk_pay_octopus_failure) : "";
        }
        if (g0.a(fragmentActivity)) {
            com.jd.lib.cashier.sdk.d.g.b.b.a().b(fragmentActivity, string, cashierCommonPopConfig2, cashierCommonPopConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, OctopusPayInfo octopusPayInfo, String str) {
        if (octopusPayInfo != null) {
            com.jd.lib.cashier.sdk.d.g.f.c.a aVar = new com.jd.lib.cashier.sdk.d.g.f.c.a();
            aVar.a = str;
            aVar.b = octopusPayInfo.token;
            String str2 = octopusPayInfo.nonceStr;
            aVar.f3282c = octopusPayInfo.octopusPayId;
            d d = e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.OCTOPUS);
            if (!g0.a(fragmentActivity) || d == null) {
                return;
            }
            d.b(fragmentActivity, aVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.d.g.f.c.b bVar) {
        if (bVar != null) {
            j(new a(bVar));
            h(bVar);
        }
    }
}
