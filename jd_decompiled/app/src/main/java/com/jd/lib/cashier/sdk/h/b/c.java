package com.jd.lib.cashier.sdk.h.b;

import com.jd.lib.cashier.sdk.d.b.a;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class c extends a {
    private static volatile c a;

    private c() {
    }

    private synchronized void c(List<com.jd.lib.cashier.sdk.d.a.e.a> list, Payment payment) {
        if (list != null && payment != null) {
            a(payment);
            list.add(new com.jd.lib.cashier.sdk.h.g.c(payment));
        }
    }

    public static synchronized c d() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                synchronized (c.class) {
                    if (a == null) {
                        a = new c();
                    }
                }
            }
            cVar = a;
        }
        return cVar;
    }

    private synchronized void e(int i2, int i3, Payment payment) {
        int i4;
        if (payment != null && i2 <= i3 - 1) {
            if (i2 == 0) {
                if (i2 == i4) {
                    payment.splitLineType = a.b.FLOOR_TOP_AND_BOTTOM;
                } else {
                    payment.splitLineType = a.b.FLOOR_TOP_AND_NORMAL;
                }
            } else if (i2 == i4) {
                payment.splitLineType = a.b.FLOOR_BOTTOM;
            } else {
                payment.splitLineType = a.b.NORMAL;
            }
        }
    }

    public synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> b(CashierPayEntity cashierPayEntity) {
        List<Payment> list;
        ArrayList arrayList = new ArrayList();
        if (cashierPayEntity != null && (list = cashierPayEntity.otherPayChannelList) != null && !list.isEmpty()) {
            List<Payment> list2 = cashierPayEntity.otherPayChannelList;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                Payment payment = list2.get(i2);
                e(i2, list2.size(), payment);
                c(arrayList, payment);
            }
            return arrayList;
        }
        return arrayList;
    }
}
