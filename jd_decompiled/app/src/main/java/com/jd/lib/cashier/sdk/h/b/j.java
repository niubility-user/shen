package com.jd.lib.cashier.sdk.h.b;

import com.jd.lib.cashier.sdk.h.g.w;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlatPayFlagTag;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class j extends a {
    private static volatile j a;

    private j() {
    }

    private synchronized void c(List<com.jd.lib.cashier.sdk.d.a.e.a> list, Payment payment) {
        if (list != null && payment != null) {
            a(payment);
            list.add(new com.jd.lib.cashier.sdk.h.g.p(payment));
        }
    }

    private synchronized void d(List<com.jd.lib.cashier.sdk.d.a.e.a> list, List<Payment> list2) {
        for (int i2 = 0; i2 < list2.size(); i2++) {
            c(list, list2.get(i2));
        }
    }

    private synchronized void e(int i2, List<com.jd.lib.cashier.sdk.d.a.e.a> list, List<Payment> list2) {
        for (int i3 = 0; i3 < list2.size(); i3++) {
            if (i3 < i2) {
                c(list, list2.get(i3));
            }
        }
    }

    public static synchronized j f() {
        j jVar;
        synchronized (j.class) {
            if (a == null) {
                synchronized (j.class) {
                    if (a == null) {
                        a = new j();
                    }
                }
            }
            jVar = a;
        }
        return jVar;
    }

    public synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> b(CashierPayEntity cashierPayEntity) {
        List<Payment> list;
        String str;
        ArrayList arrayList = new ArrayList();
        if (cashierPayEntity != null && (list = cashierPayEntity.otherPayChannelList) != null && !list.isEmpty()) {
            String str2 = "1";
            PlatPayFlagTag platPayFlagTag = cashierPayEntity.platPayFoldTag;
            if (platPayFlagTag != null && (str = platPayFlagTag.financialChannelExpandRow) != null) {
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
                    if (parseInt >= cashierPayEntity.otherPayChannelList.size()) {
                        d(arrayList, cashierPayEntity.otherPayChannelList);
                        break;
                    } else {
                        e(parseInt, arrayList, cashierPayEntity.otherPayChannelList);
                        break;
                    }
                default:
                    d(arrayList, cashierPayEntity.otherPayChannelList);
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
        if (cashierPayEntity != null && (list = cashierPayEntity.otherPayChannelList) != null && !list.isEmpty()) {
            String str2 = "1";
            PlatPayFlagTag platPayFlagTag = cashierPayEntity.platPayFoldTag;
            if (platPayFlagTag != null && (str = platPayFlagTag.financialChannelExpandRow) != null) {
                str2 = str;
            }
            List<Payment> list2 = cashierPayEntity.otherPayChannelList;
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
                    arrayList.add(new w(10));
                    arrayList.add(new com.jd.lib.cashier.sdk.h.g.l());
                }
                for (int i2 = size; i2 < list2.size(); i2++) {
                    c(arrayList, list2.get(i2));
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
