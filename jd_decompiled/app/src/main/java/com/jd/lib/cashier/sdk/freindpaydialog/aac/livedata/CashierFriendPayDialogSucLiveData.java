package com.jd.lib.cashier.sdk.freindpaydialog.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.freindpaydialog.bean.CashierFriendPayDialogEntity;
import com.jd.lib.cashier.sdk.g.a.b.b;
import com.jd.lib.cashier.sdk.g.b.a;

/* loaded from: classes14.dex */
public class CashierFriendPayDialogSucLiveData extends LiveData<b> {
    public void a() {
        b bVar = new b();
        bVar.a = 8;
        postValue(bVar);
    }

    public void b(CashierFriendPayDialogEntity cashierFriendPayDialogEntity) {
        if (cashierFriendPayDialogEntity != null) {
            b bVar = new b();
            bVar.b = a.e(cashierFriendPayDialogEntity);
            bVar.a = 0;
            postValue(bVar);
        }
    }
}
