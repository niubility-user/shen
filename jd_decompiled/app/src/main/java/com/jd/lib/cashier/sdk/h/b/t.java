package com.jd.lib.cashier.sdk.h.b;

import androidx.annotation.NonNull;
import com.jd.lib.cashier.sdk.h.g.u;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class t extends a {
    private static volatile t a;

    private t() {
    }

    public static synchronized t c() {
        t tVar;
        synchronized (t.class) {
            if (a == null) {
                synchronized (t.class) {
                    if (a == null) {
                        a = new t();
                    }
                }
            }
            tVar = a;
        }
        return tVar;
    }

    public synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> b(@NonNull CashierPayEntity cashierPayEntity) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        List<Payment> list = cashierPayEntity.jdOtherPayChannelList;
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                Payment payment = list.get(i2);
                a(payment);
                arrayList.add(new u(payment));
            }
        }
        return arrayList;
    }
}
