package com.jd.lib.cashier.sdk.pay.aac.livedata.a;

import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.List;

/* loaded from: classes14.dex */
public class g {
    public List<Payment> a;
    public List<com.jd.lib.cashier.sdk.d.a.e.a> b;

    /* renamed from: c  reason: collision with root package name */
    public List<com.jd.lib.cashier.sdk.d.a.e.a> f3780c;
    public com.jd.lib.cashier.sdk.h.g.s d;

    public boolean a() {
        List<com.jd.lib.cashier.sdk.d.a.e.a> list = this.f3780c;
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < this.f3780c.size(); i2++) {
                if (this.f3780c.get(i2).getItemType() == 400009) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean b() {
        List<com.jd.lib.cashier.sdk.d.a.e.a> list = this.b;
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                if (this.b.get(i2).getItemType() == 400009) {
                    return true;
                }
            }
        }
        return false;
    }
}
