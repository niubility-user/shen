package com.jd.lib.cashier.sdk.h.b;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.h.g.z;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class s extends a {
    private static volatile s a;

    private s() {
    }

    private synchronized void c(List<com.jd.lib.cashier.sdk.d.a.e.a> list, List<Payment> list2) {
        if (list != null && list2 != null) {
            if (!list2.isEmpty()) {
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    Payment payment = list2.get(i2);
                    a(payment);
                    list.add(new com.jd.lib.cashier.sdk.h.g.t(payment));
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001d, code lost:
        r5.add(new com.jd.lib.cashier.sdk.h.g.z(r7, r8));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized void d(int i2, List<com.jd.lib.cashier.sdk.d.a.e.a> list, List<Payment> list2, String str, String str2) {
        int i3 = 0;
        while (true) {
            if (i3 >= list2.size()) {
                break;
            }
            if (i3 < i2) {
                Payment payment = list2.get(i3);
                a(payment);
                list.add(new com.jd.lib.cashier.sdk.h.g.t(payment));
            }
            if (i3 == i2) {
                break;
            }
            i3++;
        }
    }

    public static synchronized s e() {
        s sVar;
        synchronized (s.class) {
            if (a == null) {
                synchronized (s.class) {
                    if (a == null) {
                        a = new s();
                    }
                }
            }
            sVar = a;
        }
        return sVar;
    }

    public synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> b(CashierPayEntity cashierPayEntity) {
        List<Payment> list;
        ArrayList arrayList = new ArrayList();
        if (cashierPayEntity != null && (list = cashierPayEntity.payChannelList) != null && !list.isEmpty()) {
            List<Payment> list2 = cashierPayEntity.payChannelList;
            String str = TextUtils.isEmpty(cashierPayEntity.thirdPaychannelFoldTag) ? "" : cashierPayEntity.thirdPaychannelFoldTag;
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
                        c2 = 5;
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
            }
            if (c2 == 0) {
                arrayList.add(new z(cashierPayEntity.thirdPayExpandTip, cashierPayEntity.thirdPayExpandUIFlag));
            } else if (c2 == 1 || c2 == 2 || c2 == 3 || c2 == 4) {
                int parseInt = (Integer.parseInt(str) - 1) * 2;
                if (parseInt >= list2.size()) {
                    c(arrayList, list2);
                } else {
                    d(parseInt, arrayList, list2, cashierPayEntity.thirdPayExpandTip, cashierPayEntity.thirdPayExpandUIFlag);
                }
            } else if (c2 != 5) {
                if (2 >= list2.size()) {
                    c(arrayList, list2);
                } else {
                    d(2, arrayList, list2, cashierPayEntity.thirdPayExpandTip, cashierPayEntity.thirdPayExpandUIFlag);
                }
            } else {
                c(arrayList, list2);
            }
            return arrayList;
        }
        return arrayList;
    }

    public synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> f(CashierPayEntity cashierPayEntity) {
        List<Payment> list;
        ArrayList arrayList = new ArrayList();
        if (cashierPayEntity != null && (list = cashierPayEntity.payChannelList) != null && !list.isEmpty()) {
            List<Payment> list2 = cashierPayEntity.payChannelList;
            String str = TextUtils.isEmpty(cashierPayEntity.thirdPaychannelFoldTag) ? "" : cashierPayEntity.thirdPaychannelFoldTag;
            char c2 = '\uffff';
            int i2 = 0;
            switch (str.hashCode()) {
                case 48:
                    if (str.equals("0")) {
                        c2 = 0;
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
            }
            if (c2 != 0) {
                i2 = (c2 == 1 || c2 == 2 || c2 == 3 || c2 == 4) ? (Integer.parseInt(str) - 1) * 2 : 2;
            }
            if (list2.size() > i2) {
                while (i2 < list2.size()) {
                    Payment payment = list2.get(i2);
                    a(payment);
                    arrayList.add(new com.jd.lib.cashier.sdk.h.g.t(payment));
                    i2++;
                }
            }
            return arrayList;
        }
        return arrayList;
    }
}
