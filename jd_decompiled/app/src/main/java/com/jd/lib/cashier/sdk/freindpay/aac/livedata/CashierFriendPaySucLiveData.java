package com.jd.lib.cashier.sdk.freindpay.aac.livedata;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.f.a.b.b;
import com.jd.lib.cashier.sdk.f.b.a;
import com.jd.lib.cashier.sdk.f.b.c;
import com.jd.lib.cashier.sdk.freindpay.bean.CashierFriendPayEntity;

/* loaded from: classes14.dex */
public class CashierFriendPaySucLiveData extends LiveData<b> {
    public synchronized void a() {
        b bVar = new b();
        bVar.a = 8;
        postValue(bVar);
    }

    public synchronized void b(CashierFriendPayEntity cashierFriendPayEntity) {
        if (cashierFriendPayEntity != null) {
            b bVar = new b();
            if (TextUtils.equals(cashierFriendPayEntity.dynamicFlag, "1")) {
                bVar.b = a.i(cashierFriendPayEntity);
            } else {
                bVar.b = c.g(cashierFriendPayEntity);
            }
            bVar.a = 0;
            postValue(bVar);
        }
    }
}
