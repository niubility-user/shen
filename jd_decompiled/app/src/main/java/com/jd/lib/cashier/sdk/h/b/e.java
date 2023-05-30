package com.jd.lib.cashier.sdk.h.b;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.d.b.a;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class e extends a {
    private static volatile e a;

    private e() {
    }

    private synchronized void b(List<com.jd.lib.cashier.sdk.d.a.e.a> list, Payment payment, CashierPayEntity cashierPayEntity) {
        if (list != null && payment != null && cashierPayEntity != null) {
            a(payment);
            list.add(new com.jd.lib.cashier.sdk.h.g.c(payment));
        }
    }

    private synchronized void d(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierPayEntity cashierPayEntity) {
        List<Payment> list2 = cashierPayEntity.payChannelList;
        for (int i2 = 0; i2 < list2.size(); i2++) {
            Payment payment = list2.get(i2);
            h(i2, list2.size(), payment);
            b(list, payment, cashierPayEntity);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
        r7.add(new com.jd.lib.cashier.sdk.h.g.e(false, r8.thirdPayExpandTip, r8.thirdPayExpandUIFlag));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void e(int r6, java.util.List<com.jd.lib.cashier.sdk.d.a.e.a> r7, com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.List<com.jd.lib.cashier.sdk.pay.bean.Payment> r0 = r8.payChannelList     // Catch: java.lang.Throwable -> L37
            r1 = 0
            r2 = 0
        L5:
            int r3 = r0.size()     // Catch: java.lang.Throwable -> L37
            if (r2 >= r3) goto L35
            java.lang.Object r3 = r0.get(r2)     // Catch: java.lang.Throwable -> L37
            com.jd.lib.cashier.sdk.pay.bean.Payment r3 = (com.jd.lib.cashier.sdk.pay.bean.Payment) r3     // Catch: java.lang.Throwable -> L37
            if (r2 >= r6) goto L23
            int r4 = r0.size()     // Catch: java.lang.Throwable -> L37
            r5.h(r2, r4, r3)     // Catch: java.lang.Throwable -> L37
            java.lang.Object r3 = r0.get(r2)     // Catch: java.lang.Throwable -> L37
            com.jd.lib.cashier.sdk.pay.bean.Payment r3 = (com.jd.lib.cashier.sdk.pay.bean.Payment) r3     // Catch: java.lang.Throwable -> L37
            r5.b(r7, r3, r8)     // Catch: java.lang.Throwable -> L37
        L23:
            if (r2 != r6) goto L32
            com.jd.lib.cashier.sdk.h.g.e r6 = new com.jd.lib.cashier.sdk.h.g.e     // Catch: java.lang.Throwable -> L37
            java.lang.String r0 = r8.thirdPayExpandTip     // Catch: java.lang.Throwable -> L37
            java.lang.String r8 = r8.thirdPayExpandUIFlag     // Catch: java.lang.Throwable -> L37
            r6.<init>(r1, r0, r8)     // Catch: java.lang.Throwable -> L37
            r7.add(r6)     // Catch: java.lang.Throwable -> L37
            goto L35
        L32:
            int r2 = r2 + 1
            goto L5
        L35:
            monitor-exit(r5)
            return
        L37:
            r6 = move-exception
            monitor-exit(r5)
            goto L3b
        L3a:
            throw r6
        L3b:
            goto L3a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.h.b.e.e(int, java.util.List, com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity):void");
    }

    public static synchronized e f() {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                synchronized (e.class) {
                    if (a == null) {
                        a = new e();
                    }
                }
            }
            eVar = a;
        }
        return eVar;
    }

    private synchronized void h(int i2, int i3, Payment payment) {
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

    public synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> c(CashierPayEntity cashierPayEntity) {
        List<Payment> list;
        ArrayList arrayList = new ArrayList();
        if (cashierPayEntity != null && (list = cashierPayEntity.payChannelList) != null && !list.isEmpty()) {
            String str = TextUtils.isEmpty(cashierPayEntity.thirdPaychannelFoldTag) ? "1" : cashierPayEntity.thirdPaychannelFoldTag;
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case 48:
                    if (str.equals("0")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 49:
                    if (str.equals("1")) {
                        c2 = '\t';
                        break;
                    }
                    break;
                case 50:
                    if (str.equals("2")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 51:
                    if (str.equals("3")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 52:
                    if (str.equals("4")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 53:
                    if (str.equals("5")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 54:
                    if (str.equals("6")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 55:
                    if (str.equals("7")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 56:
                    if (str.equals("8")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 57:
                    if (str.equals("9")) {
                        c2 = '\b';
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    arrayList.add(new com.jd.lib.cashier.sdk.h.g.e(true, cashierPayEntity.thirdPayExpandTip, cashierPayEntity.thirdPayExpandUIFlag));
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case '\b':
                    int parseInt = Integer.parseInt(str) - 1;
                    if (parseInt >= cashierPayEntity.payChannelList.size()) {
                        d(arrayList, cashierPayEntity);
                        break;
                    } else {
                        e(parseInt, arrayList, cashierPayEntity);
                        break;
                    }
                default:
                    d(arrayList, cashierPayEntity);
                    break;
            }
            return arrayList;
        }
        return arrayList;
    }

    public synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> g(CashierPayEntity cashierPayEntity) {
        List<Payment> list;
        ArrayList arrayList = new ArrayList();
        if (cashierPayEntity != null && (list = cashierPayEntity.payChannelList) != null && !list.isEmpty()) {
            String str = TextUtils.isEmpty(cashierPayEntity.thirdPaychannelFoldTag) ? "1" : cashierPayEntity.thirdPaychannelFoldTag;
            List<Payment> list2 = cashierPayEntity.payChannelList;
            int size = list2.size();
            int size2 = list2.size();
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case 48:
                    if (str.equals("0")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 49:
                    if (str.equals("1")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 50:
                    if (str.equals("2")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 51:
                    if (str.equals("3")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 52:
                    if (str.equals("4")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 53:
                    if (str.equals("5")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 54:
                    if (str.equals("6")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 55:
                    if (str.equals("7")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 56:
                    if (str.equals("8")) {
                        c2 = '\b';
                        break;
                    }
                    break;
                case 57:
                    if (str.equals("9")) {
                        c2 = '\t';
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    size = 0;
                    break;
                case 1:
                    size = list2.size();
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case '\b':
                case '\t':
                    size = Integer.parseInt(str) - 1;
                    break;
            }
            if (size2 > size) {
                while (size < size2) {
                    Payment payment = list2.get(size);
                    h(size, size2, payment);
                    b(arrayList, payment, cashierPayEntity);
                    size++;
                }
            }
            return arrayList;
        }
        return arrayList;
    }
}
