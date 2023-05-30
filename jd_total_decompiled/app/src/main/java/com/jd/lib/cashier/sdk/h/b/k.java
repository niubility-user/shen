package com.jd.lib.cashier.sdk.h.b;

import androidx.annotation.NonNull;
import com.jd.lib.cashier.sdk.h.g.w;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlatPayFlagTag;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.templatedata.CashierBPayExpandTemplateData;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class k {
    private static volatile k a;

    private k() {
    }

    private void a(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierPayEntity cashierPayEntity) {
        PlatPayFlagTag platPayFlagTag;
        if (list == null || cashierPayEntity == null || (platPayFlagTag = cashierPayEntity.platPayFoldTag) == null) {
            return;
        }
        String str = platPayFlagTag.payChannelExpandRow;
        String str2 = platPayFlagTag.financialChannelExpandRow;
        List<Payment> list2 = cashierPayEntity.payChannelList;
        List<Payment> list3 = cashierPayEntity.otherPayChannelList;
        int c2 = com.jd.lib.cashier.sdk.core.utils.s.c(str);
        int c3 = com.jd.lib.cashier.sdk.core.utils.s.c(str2);
        if ((c2 == 0 && list2 != null && !list2.isEmpty()) || (c3 == 0 && list3 != null && !list3.isEmpty())) {
            list.add(new com.jd.lib.cashier.sdk.h.g.q(new CashierBPayExpandTemplateData(cashierPayEntity.platPayFoldTag.platPayExpandTip)));
        } else if ((c2 <= 1 || list2 == null || list2.size() <= c2 - 1) && (c3 <= 1 || list3 == null || list3.size() <= c3 - 1)) {
        } else {
            list.add(new com.jd.lib.cashier.sdk.h.g.q(new CashierBPayExpandTemplateData(cashierPayEntity.platPayFoldTag.platPayExpandTip)));
        }
    }

    public static synchronized k c() {
        k kVar;
        synchronized (k.class) {
            if (a == null) {
                synchronized (k.class) {
                    if (a == null) {
                        a = new k();
                    }
                }
            }
            kVar = a;
        }
        return kVar;
    }

    @NonNull
    public synchronized o b(@NonNull CashierPayEntity cashierPayEntity) {
        List<com.jd.lib.cashier.sdk.d.a.e.a> arrayList;
        List<com.jd.lib.cashier.sdk.d.a.e.a> b;
        arrayList = new ArrayList<>();
        b = t.c().b(cashierPayEntity);
        List<com.jd.lib.cashier.sdk.d.a.e.a> c2 = n.f().c(cashierPayEntity);
        if (c2 != null && !c2.isEmpty()) {
            arrayList.add(new com.jd.lib.cashier.sdk.h.g.l());
            arrayList.addAll(c2);
            arrayList.add(new com.jd.lib.cashier.sdk.h.g.j());
        }
        List<com.jd.lib.cashier.sdk.d.a.e.a> b2 = j.f().b(cashierPayEntity);
        if (b2 != null && !b2.isEmpty()) {
            arrayList.add(new w(10));
            arrayList.add(new com.jd.lib.cashier.sdk.h.g.l());
            arrayList.addAll(b2);
            arrayList.add(new com.jd.lib.cashier.sdk.h.g.j());
        }
        a(arrayList, cashierPayEntity);
        arrayList.add(new w(46));
        return new o(arrayList, b);
    }
}
