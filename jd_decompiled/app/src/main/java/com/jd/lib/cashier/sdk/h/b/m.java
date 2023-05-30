package com.jd.lib.cashier.sdk.h.b;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlatPayFlagTag;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class m extends a {
    private static volatile m a;

    private m() {
    }

    private synchronized void b(List<com.jd.lib.cashier.sdk.d.a.e.a> list, Payment payment, CashierPayEntity cashierPayEntity) {
        List<com.jd.lib.cashier.sdk.d.a.e.a> d;
        if (list != null && payment != null && cashierPayEntity != null) {
            if (TextUtils.equals(payment.code, "newJdpay")) {
                a(payment);
                list.add(new com.jd.lib.cashier.sdk.h.g.m(payment));
                List<Payment> list2 = cashierPayEntity.jdPayChannelList;
                if (list2 != null && !list2.isEmpty() && (d = g.e().d(cashierPayEntity, TextUtils.equals(payment.addNewCardScene, "1"))) != null && !d.isEmpty()) {
                    list.addAll(d);
                }
            } else {
                a(payment);
                list.add(new com.jd.lib.cashier.sdk.h.g.k(payment));
            }
        }
    }

    private synchronized void d(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierPayEntity cashierPayEntity) {
        List<Payment> list2 = cashierPayEntity.payChannelList;
        for (int i2 = 0; i2 < list2.size(); i2++) {
            b(list, list2.get(i2), cashierPayEntity);
        }
    }

    private synchronized void e(int i2, List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierPayEntity cashierPayEntity) {
        List<Payment> list2 = cashierPayEntity.payChannelList;
        for (int i3 = 0; i3 < list2.size(); i3++) {
            if (i3 < i2) {
                b(list, list2.get(i3), cashierPayEntity);
            }
        }
    }

    public static synchronized m f() {
        m mVar;
        synchronized (m.class) {
            if (a == null) {
                synchronized (m.class) {
                    if (a == null) {
                        a = new m();
                    }
                }
            }
            mVar = a;
        }
        return mVar;
    }

    public synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> c(CashierPayEntity cashierPayEntity) {
        List<Payment> list;
        String str;
        ArrayList arrayList = new ArrayList();
        if (cashierPayEntity != null && (list = cashierPayEntity.payChannelList) != null && !list.isEmpty()) {
            String str2 = "1";
            PlatPayFlagTag platPayFlagTag = cashierPayEntity.platPayFoldTag;
            if (platPayFlagTag != null && (str = platPayFlagTag.payChannelExpandRow) != null) {
                str2 = str;
            }
            char c2 = '\uffff';
            switch (str2.hashCode()) {
                case 48:
                    if (str2.equals("0")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 49:
                    if (str2.equals("1")) {
                        c2 = '\t';
                        break;
                    }
                    break;
                case 50:
                    if (str2.equals("2")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 51:
                    if (str2.equals("3")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 52:
                    if (str2.equals("4")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 53:
                    if (str2.equals("5")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 54:
                    if (str2.equals("6")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 55:
                    if (str2.equals("7")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 56:
                    if (str2.equals("8")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 57:
                    if (str2.equals("9")) {
                        c2 = '\b';
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case '\b':
                    int parseInt = Integer.parseInt(str2) - 1;
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
        String str;
        ArrayList arrayList = new ArrayList();
        if (cashierPayEntity != null && (list = cashierPayEntity.payChannelList) != null && !list.isEmpty()) {
            String str2 = "1";
            PlatPayFlagTag platPayFlagTag = cashierPayEntity.platPayFoldTag;
            if (platPayFlagTag != null && (str = platPayFlagTag.payChannelExpandRow) != null) {
                str2 = str;
            }
            List<Payment> list2 = cashierPayEntity.payChannelList;
            int size = list2.size();
            char c2 = '\uffff';
            switch (str2.hashCode()) {
                case 48:
                    if (str2.equals("0")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 49:
                    if (str2.equals("1")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 50:
                    if (str2.equals("2")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 51:
                    if (str2.equals("3")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 52:
                    if (str2.equals("4")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 53:
                    if (str2.equals("5")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 54:
                    if (str2.equals("6")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 55:
                    if (str2.equals("7")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 56:
                    if (str2.equals("8")) {
                        c2 = '\b';
                        break;
                    }
                    break;
                case 57:
                    if (str2.equals("9")) {
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
                    size = Integer.parseInt(str2) - 1;
                    break;
            }
            if (list2.size() > size) {
                if (size == 0) {
                    arrayList.add(new com.jd.lib.cashier.sdk.h.g.l());
                }
                for (int i2 = size; i2 < list2.size(); i2++) {
                    b(arrayList, list2.get(i2), cashierPayEntity);
                }
                if (size == 0) {
                    arrayList.add(new com.jd.lib.cashier.sdk.h.g.j());
                }
            }
            return arrayList;
        }
        return arrayList;
    }
}
