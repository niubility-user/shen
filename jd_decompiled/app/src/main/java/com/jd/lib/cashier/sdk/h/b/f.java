package com.jd.lib.cashier.sdk.h.b;

import androidx.annotation.NonNull;
import com.jd.lib.cashier.sdk.h.g.u;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class f extends a {
    private static volatile f a;

    private f() {
    }

    public static synchronized f c() {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                synchronized (f.class) {
                    if (a == null) {
                        a = new f();
                    }
                }
            }
            fVar = a;
        }
        return fVar;
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
