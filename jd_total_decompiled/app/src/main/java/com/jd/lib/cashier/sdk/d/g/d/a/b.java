package com.jd.lib.cashier.sdk.d.g.d.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.paychannel.medicalpay.entity.MedicalPayEntity;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.g.g.d;
import com.jd.lib.cashier.sdk.d.g.g.e;

/* loaded from: classes14.dex */
public class b extends com.jd.lib.cashier.sdk.d.g.d.a.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements f<MedicalPayEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.d.g.d.c.b f3278g;

        a(com.jd.lib.cashier.sdk.d.g.d.c.b bVar) {
            this.f3278g = bVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(MedicalPayEntity medicalPayEntity) {
            if (medicalPayEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                b.this.p(this.f3278g.getActivity(), medicalPayEntity);
            } else if (!TextUtils.isEmpty(medicalPayEntity.errorCode) || medicalPayEntity.commonPopupInfo != null) {
                b.this.p(this.f3278g.getActivity(), medicalPayEntity);
            } else if (!TextUtils.isEmpty(medicalPayEntity.actionUrl)) {
                b.this.r(this.f3278g.getActivity(), medicalPayEntity, this.f3278g.f3287c);
            } else {
                b.this.p(this.f3278g.getActivity(), medicalPayEntity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(FragmentActivity fragmentActivity, MedicalPayEntity medicalPayEntity) {
        CashierCommonPopConfig cashierCommonPopConfig;
        String string;
        CashierCommonPopConfig cashierCommonPopConfig2 = null;
        if (medicalPayEntity != null) {
            cashierCommonPopConfig = medicalPayEntity.commonPopupInfo;
            cashierCommonPopConfig2 = medicalPayEntity.orderExceptionInfo;
        } else {
            cashierCommonPopConfig = null;
        }
        if (medicalPayEntity != null && !TextUtils.isEmpty(medicalPayEntity.errorMsg)) {
            string = medicalPayEntity.errorMsg;
        } else {
            string = g0.a(fragmentActivity) ? fragmentActivity.getString(R.string.lib_cashier_sdk_pay_medical_failure) : "";
        }
        if (g0.a(fragmentActivity)) {
            com.jd.lib.cashier.sdk.d.g.b.b.a().b(fragmentActivity, string, cashierCommonPopConfig2, cashierCommonPopConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, MedicalPayEntity medicalPayEntity, String str) {
        if (!g0.a(fragmentActivity) || medicalPayEntity == null) {
            return;
        }
        com.jd.lib.cashier.sdk.d.g.d.c.a aVar = new com.jd.lib.cashier.sdk.d.g.d.c.a();
        aVar.a = str;
        aVar.b = medicalPayEntity.actionUrl;
        d d = e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.MEDICALPAY);
        if (d != null) {
            d.b(fragmentActivity, aVar);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.d.g.d.c.b bVar) {
        if (bVar != null) {
            j(new a(bVar));
            h(bVar);
        }
    }
}
