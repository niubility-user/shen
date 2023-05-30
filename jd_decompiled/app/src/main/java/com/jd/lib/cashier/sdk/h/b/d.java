package com.jd.lib.cashier.sdk.h.b;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.lib.cashier.sdk.h.g.w;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class d extends a {
    private static volatile d a;

    private synchronized void b(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierPayEntity cashierPayEntity) {
        if (list != null && cashierPayEntity != null) {
            List<Payment> list2 = cashierPayEntity.otherPayChannelList;
            if (list2 != null && !list2.isEmpty()) {
                if (!TextUtils.isEmpty(cashierPayEntity.otherPayChannelTip)) {
                    list.add(new com.jd.lib.cashier.sdk.h.g.d(cashierPayEntity.otherPayChannelTip));
                }
            }
        }
    }

    public static synchronized d d() {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                synchronized (d.class) {
                    if (a == null) {
                        a = new d();
                    }
                }
            }
            dVar = a;
        }
        return dVar;
    }

    @NonNull
    public synchronized o c(@NonNull CashierPayEntity cashierPayEntity) {
        List<com.jd.lib.cashier.sdk.d.a.e.a> arrayList;
        List<com.jd.lib.cashier.sdk.d.a.e.a> b;
        arrayList = new ArrayList<>();
        b = f.c().b(cashierPayEntity);
        List<com.jd.lib.cashier.sdk.d.a.e.a> b2 = b.c().b(cashierPayEntity);
        if (b2 != null && !b2.isEmpty()) {
            arrayList.addAll(b2);
            arrayList.add(new w(12));
        }
        List<com.jd.lib.cashier.sdk.d.a.e.a> c2 = e.f().c(cashierPayEntity);
        if (c2 != null && !c2.isEmpty()) {
            arrayList.addAll(c2);
        }
        List<com.jd.lib.cashier.sdk.d.a.e.a> b3 = c.d().b(cashierPayEntity);
        if (b3 != null && !b3.isEmpty()) {
            b(arrayList, cashierPayEntity);
            arrayList.addAll(b3);
        }
        if (!arrayList.isEmpty()) {
            arrayList.add(new w(12));
        }
        return new o(arrayList, b);
    }
}
