package com.jd.lib.cashier.sdk.h.b;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class g extends a {
    private static volatile g a;

    private g() {
    }

    private synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> b(CashierPayEntity cashierPayEntity) {
        ArrayList arrayList;
        Payment e2;
        arrayList = new ArrayList();
        List<Payment> list = cashierPayEntity.jdPayChannelList;
        List<Payment> list2 = cashierPayEntity.jdOtherPayChannelList;
        if (list != null && (e2 = com.jd.lib.cashier.sdk.h.h.e.e(list, list2)) != null) {
            a(e2);
            arrayList.add(new com.jd.lib.cashier.sdk.h.g.g(e2));
        }
        return arrayList;
    }

    private synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> c(CashierPayEntity cashierPayEntity) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        List<Payment> list = cashierPayEntity.jdPayChannelList;
        if (list != null && !list.isEmpty()) {
            List<Payment> list2 = cashierPayEntity.jdPayChannelList;
            boolean z = false;
            int i2 = 0;
            for (int i3 = 0; i3 < list2.size(); i3++) {
                Payment payment = list2.get(i3);
                if (TextUtils.equals(payment.code, "moreInfo")) {
                    arrayList.add(new com.jd.lib.cashier.sdk.h.g.h());
                    z = true;
                } else if (i2 <= 1) {
                    a(payment);
                    arrayList.add(new com.jd.lib.cashier.sdk.h.g.i(payment, false));
                    i2++;
                }
            }
            if (arrayList.size() == 1 && z) {
                ArrayList arrayList2 = new ArrayList(2);
                arrayList2.add(new com.jd.lib.cashier.sdk.h.g.i(new Payment(), true));
                arrayList2.add(new com.jd.lib.cashier.sdk.h.g.i(new Payment(), true));
                arrayList.addAll(0, arrayList2);
            } else if (arrayList.size() == 2 && z) {
                arrayList.add(1, new com.jd.lib.cashier.sdk.h.g.i(new Payment(), true));
            } else if (arrayList.size() == 1) {
                arrayList.add(new com.jd.lib.cashier.sdk.h.g.i(new Payment(), true));
            }
            if (arrayList.size() > 1 && (arrayList.get(1) instanceof com.jd.lib.cashier.sdk.h.g.i)) {
                ((com.jd.lib.cashier.sdk.h.g.i) arrayList.get(1)).f3562c = true;
            }
        }
        return arrayList;
    }

    public static synchronized g e() {
        g gVar;
        synchronized (g.class) {
            if (a == null) {
                synchronized (g.class) {
                    if (a == null) {
                        a = new g();
                    }
                }
            }
            gVar = a;
        }
        return gVar;
    }

    public synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> d(CashierPayEntity cashierPayEntity, boolean z) {
        List<com.jd.lib.cashier.sdk.d.a.e.a> b;
        if (z) {
            b = c(cashierPayEntity);
        } else {
            b = b(cashierPayEntity);
        }
        return b;
    }
}
