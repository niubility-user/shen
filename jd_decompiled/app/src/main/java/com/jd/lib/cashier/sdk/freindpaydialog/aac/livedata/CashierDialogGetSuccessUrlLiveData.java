package com.jd.lib.cashier.sdk.freindpaydialog.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.common.bean.CashierGetSuccessUrlEntity;
import com.jd.lib.cashier.sdk.g.a.b.c;

/* loaded from: classes14.dex */
public class CashierDialogGetSuccessUrlLiveData extends LiveData<c> {
    public void a() {
        c cVar = new c();
        cVar.a = "-1";
        postValue(cVar);
    }

    public void b(String str, CashierGetSuccessUrlEntity cashierGetSuccessUrlEntity) {
        if (cashierGetSuccessUrlEntity != null) {
            c cVar = new c();
            cVar.a = cashierGetSuccessUrlEntity.code;
            cVar.f3458f = str;
            cVar.b = cashierGetSuccessUrlEntity.payStatus;
            cVar.f3456c = cashierGetSuccessUrlEntity.successUrl;
            cVar.f3457e = cashierGetSuccessUrlEntity.successUrlType;
            cVar.d = cashierGetSuccessUrlEntity.successToastText;
            postValue(cVar);
        }
    }
}
