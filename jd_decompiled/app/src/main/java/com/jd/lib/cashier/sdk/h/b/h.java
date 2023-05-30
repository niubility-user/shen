package com.jd.lib.cashier.sdk.h.b;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class h extends a {
    private static volatile h a;

    private h() {
    }

    private synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> b(CashierPayEntity cashierPayEntity) {
        ArrayList arrayList;
        Payment e2;
        arrayList = new ArrayList();
        List<Payment> list = cashierPayEntity.jdPayChannelList;
        List<Payment> list2 = cashierPayEntity.jdOtherPayChannelList;
        if (list != null && (e2 = com.jd.lib.cashier.sdk.h.h.e.e(list, list2)) != null) {
            a(e2);
            arrayList.add(new com.jd.lib.cashier.sdk.h.g.n(e2));
        }
        return arrayList;
    }

    private synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> c(CashierPayEntity cashierPayEntity) {
        ArrayList arrayList;
        List<Payment> list;
        ArrayList arrayList2 = new ArrayList(2);
        arrayList = new ArrayList();
        if (cashierPayEntity != null && (list = cashierPayEntity.jdPayChannelList) != null && !list.isEmpty()) {
            List<Payment> list2 = cashierPayEntity.jdPayChannelList;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                Payment payment = list2.get(i2);
                if (arrayList2.size() <= 1 && !TextUtils.equals(payment.code, "moreInfo")) {
                    a(payment);
                    arrayList2.add(payment);
                }
                if (arrayList2.size() == 2) {
                    break;
                }
            }
        }
        if (!arrayList2.isEmpty()) {
            arrayList.add(new com.jd.lib.cashier.sdk.h.g.o(arrayList2));
        }
        return arrayList;
    }

    public static synchronized h e() {
        h hVar;
        synchronized (h.class) {
            if (a == null) {
                synchronized (h.class) {
                    if (a == null) {
                        a = new h();
                    }
                }
            }
            hVar = a;
        }
        return hVar;
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
