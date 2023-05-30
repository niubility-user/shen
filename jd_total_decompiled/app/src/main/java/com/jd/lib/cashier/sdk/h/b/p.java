package com.jd.lib.cashier.sdk.h.b;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.lib.cashier.sdk.d.b.a;
import com.jd.lib.cashier.sdk.h.g.a0;
import com.jd.lib.cashier.sdk.h.g.b0;
import com.jd.lib.cashier.sdk.h.g.c0;
import com.jd.lib.cashier.sdk.h.g.u;
import com.jd.lib.cashier.sdk.h.g.v;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class p extends a {
    private static volatile p a;

    private p() {
    }

    private void b(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierPayEntity cashierPayEntity) {
        if (list.size() > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i3 >= list.size()) {
                    i3 = -1;
                    break;
                }
                com.jd.lib.cashier.sdk.d.a.e.a aVar = list.get(i3);
                if ((aVar instanceof com.jd.lib.cashier.sdk.h.g.t) && com.jd.lib.cashier.sdk.h.h.g.a(((com.jd.lib.cashier.sdk.h.g.t) aVar).a().code)) {
                    break;
                }
                i3++;
            }
            while (true) {
                if (i2 >= cashierPayEntity.otherPayChannelList.size()) {
                    i2 = -1;
                    break;
                } else if (com.jd.lib.cashier.sdk.h.h.g.a(cashierPayEntity.otherPayChannelList.get(i2).code)) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i3 == -1 || i2 == -1) {
                return;
            }
            int i4 = i3 + 1;
            int i5 = i3 + 2;
            Payment a2 = ((com.jd.lib.cashier.sdk.h.g.t) list.get(i3)).a();
            v vVar = new v(a2);
            if ((i2 + 1) % 2 == 0) {
                a2.arrowPosition = "2";
                list.add(i4, vVar);
                return;
            }
            a2.arrowPosition = "1";
            if (i2 == cashierPayEntity.otherPayChannelList.size() - 1) {
                list.add(i4, vVar);
            } else {
                list.add(i5, vVar);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0028 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x002a A[Catch: all -> 0x0083, TRY_ENTER, TryCatch #0 {, blocks: (B:6:0x0007, B:8:0x000c, B:14:0x0018, B:16:0x001e, B:20:0x002a, B:22:0x0033, B:24:0x0043, B:28:0x005d, B:30:0x0063, B:32:0x0069, B:37:0x007b, B:33:0x006e, B:35:0x0072, B:36:0x0077, B:25:0x004c, B:27:0x0052), top: B:46:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized void c(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierPayEntity cashierPayEntity, List<com.jd.lib.cashier.sdk.d.a.e.a> list2) {
        boolean z;
        x k2;
        if (list == null || cashierPayEntity == null) {
            return;
        }
        List<Payment> list3 = cashierPayEntity.jdPayChannelList;
        if (list3 != null && !list3.isEmpty()) {
            z = false;
            if (z && cashierPayEntity.showPayLogo()) {
                list.add(new c0());
            }
            if (z) {
                List<Payment> list4 = cashierPayEntity.jdPayChannelList;
                int size = list4.size();
                for (int i2 = 0; i2 < size; i2++) {
                    Payment payment = list4.get(i2);
                    if ("moreInfo".equals(payment.code)) {
                        a(payment);
                        k2 = new u(payment);
                    } else {
                        k2 = k(list2, payment);
                        if (k2 == null) {
                            a(payment);
                            k2 = new u(payment);
                            list2.add(0, k2);
                        }
                    }
                    Payment a2 = k2.a();
                    if (i2 == 0 && !cashierPayEntity.showPayLogo()) {
                        a2.splitLineType = a.b.FLOOR_TOP_AND_NORMAL;
                    } else if (i2 < size - 1) {
                        a2.splitLineType = a.b.NORMAL;
                    } else {
                        a2.splitLineType = a.b.FLOOR_BOTTOM;
                    }
                    list.add(k2);
                }
                return;
            }
            return;
        }
        z = true;
        if (z) {
            list.add(new c0());
        }
        if (z) {
        }
    }

    private synchronized void d(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierPayEntity cashierPayEntity) {
        if (list == null || cashierPayEntity == null) {
            return;
        }
        if (!TextUtils.isEmpty(cashierPayEntity.jdPayIcon)) {
            list.add(new b0(cashierPayEntity.jdPayIcon, cashierPayEntity.titleDesc, cashierPayEntity.publicGoodPlan, cashierPayEntity.jdPayTheme, cashierPayEntity.jdPayThemeBlack, cashierPayEntity.reskinTag));
        }
    }

    private synchronized void e(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierPayEntity cashierPayEntity) {
        if (list != null && cashierPayEntity != null) {
            List<Payment> list2 = cashierPayEntity.otherPayChannelList;
            if (list2 != null && !list2.isEmpty()) {
                List<Payment> list3 = cashierPayEntity.otherPayChannelList;
                for (int i2 = 0; i2 < list3.size(); i2++) {
                    Payment payment = list3.get(i2);
                    a(payment);
                    list.add(new com.jd.lib.cashier.sdk.h.g.t(payment));
                }
                b(list, cashierPayEntity);
            }
        }
    }

    private synchronized void f(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierPayEntity cashierPayEntity) {
        if (list != null && cashierPayEntity != null) {
            List<Payment> list2 = cashierPayEntity.otherPayChannelList;
            if (list2 != null && !list2.isEmpty()) {
                if (!TextUtils.isEmpty(cashierPayEntity.otherPayChannelTip)) {
                    list.add(new a0(cashierPayEntity.otherPayChannelTip));
                }
            }
        }
    }

    private synchronized void g(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierPayEntity cashierPayEntity) {
        List<Payment> list2;
        if (list == null || cashierPayEntity == null) {
            return;
        }
        List<Payment> list3 = cashierPayEntity.jdPayChannelList;
        if ((list3 == null || list3.isEmpty()) && !cashierPayEntity.showPayLogo() && ((list2 = cashierPayEntity.payChannelList) == null || list2.isEmpty())) {
            return;
        }
        if (!TextUtils.isEmpty(cashierPayEntity.payChannelTip)) {
            list.add(new a0(cashierPayEntity.payChannelTip));
        }
    }

    private synchronized void h(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierPayEntity cashierPayEntity) {
        List<com.jd.lib.cashier.sdk.d.a.e.a> b = s.e().b(cashierPayEntity);
        if (list != null && b != null && !b.isEmpty()) {
            list.addAll(b);
        }
    }

    private synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> j(@NonNull CashierPayEntity cashierPayEntity) {
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

    @Nullable
    private synchronized x k(@Nullable List<com.jd.lib.cashier.sdk.d.a.e.a> list, @Nullable Payment payment) {
        if (list == null || payment == null) {
            return null;
        }
        for (com.jd.lib.cashier.sdk.d.a.e.a aVar : list) {
            if (aVar instanceof x) {
                x xVar = (x) aVar;
                if (xVar.a().equals(payment)) {
                    return xVar;
                }
            }
        }
        return null;
    }

    public static synchronized p l() {
        p pVar;
        synchronized (p.class) {
            if (a == null) {
                synchronized (p.class) {
                    if (a == null) {
                        a = new p();
                    }
                }
            }
            pVar = a;
        }
        return pVar;
    }

    @NonNull
    public synchronized o i(@NonNull CashierPayEntity cashierPayEntity) {
        List<com.jd.lib.cashier.sdk.d.a.e.a> j2;
        ArrayList arrayList;
        j2 = j(cashierPayEntity);
        arrayList = new ArrayList();
        g(arrayList, cashierPayEntity);
        d(arrayList, cashierPayEntity);
        c(arrayList, cashierPayEntity, j2);
        h(arrayList, cashierPayEntity);
        f(arrayList, cashierPayEntity);
        e(arrayList, cashierPayEntity);
        return new o(arrayList, j2);
    }
}
