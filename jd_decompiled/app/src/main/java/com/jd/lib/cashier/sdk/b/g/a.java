package com.jd.lib.cashier.sdk.b.g;

import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.List;

/* loaded from: classes14.dex */
public class a {
    public static synchronized Payment a(List<Payment> list) {
        synchronized (a.class) {
            if (list != null) {
                if (!list.isEmpty()) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        Payment payment = list.get(i2);
                        if (payment != null && payment.defaultSelected) {
                            return payment;
                        }
                    }
                    return list.get(0);
                }
            }
            return null;
        }
    }

    public static synchronized void b(Payment payment, List<Payment> list) {
        synchronized (a.class) {
            if (payment != null && list != null) {
                if (!list.isEmpty()) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        Payment payment2 = list.get(i2);
                        if (payment2 != null) {
                            payment2.defaultSelected = payment.equals(payment2);
                        }
                    }
                }
            }
        }
    }
}
