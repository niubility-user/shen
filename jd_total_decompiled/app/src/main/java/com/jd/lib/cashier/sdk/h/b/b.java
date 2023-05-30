package com.jd.lib.cashier.sdk.h.b;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.d.b.a;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class b extends a {
    private static volatile b a;

    private b() {
    }

    public static synchronized b c() {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                synchronized (b.class) {
                    if (a == null) {
                        a = new b();
                    }
                }
            }
            bVar = a;
        }
        return bVar;
    }

    private synchronized void d(int i2, int i3, Payment payment) {
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

    /* JADX WARN: Removed duplicated region for block: B:22:0x003f A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041 A[Catch: all -> 0x0075, TRY_ENTER, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000a, B:9:0x0010, B:10:0x001e, B:12:0x0023, B:18:0x002f, B:20:0x0035, B:24:0x0041, B:26:0x0049, B:28:0x005c, B:30:0x006d, B:29:0x0065), top: B:39:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> b(CashierPayEntity cashierPayEntity) {
        boolean z;
        com.jd.lib.cashier.sdk.d.a.a.a cVar;
        ArrayList arrayList = new ArrayList();
        if (cashierPayEntity == null) {
            return arrayList;
        }
        if (cashierPayEntity.showPayLogo()) {
            arrayList.add(new com.jd.lib.cashier.sdk.h.g.b(cashierPayEntity.jdPayIcon, cashierPayEntity.jdPayTheme, cashierPayEntity.jdPayThemeBlack));
        }
        List<Payment> list = cashierPayEntity.jdPayChannelList;
        if (list != null && !list.isEmpty()) {
            z = false;
            if (z && cashierPayEntity.showPayLogo()) {
                arrayList.add(new com.jd.lib.cashier.sdk.h.g.f());
            }
            if (z) {
                List<Payment> list2 = cashierPayEntity.jdPayChannelList;
                int size = list2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    Payment payment = list2.get(i2);
                    d(i2, size, payment);
                    if (TextUtils.equals(payment.code, "moreInfo")) {
                        a(payment);
                        cVar = new com.jd.lib.cashier.sdk.h.g.a(payment);
                    } else {
                        a(payment);
                        cVar = new com.jd.lib.cashier.sdk.h.g.c(payment);
                    }
                    arrayList.add(cVar);
                }
                return arrayList;
            }
            return arrayList;
        }
        z = true;
        if (z) {
            arrayList.add(new com.jd.lib.cashier.sdk.h.g.f());
        }
        if (z) {
        }
    }
}
