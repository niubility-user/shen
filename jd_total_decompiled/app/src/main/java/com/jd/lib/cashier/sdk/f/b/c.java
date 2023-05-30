package com.jd.lib.cashier.sdk.f.b;

import com.jd.lib.cashier.sdk.f.f.e;
import com.jd.lib.cashier.sdk.f.f.f;
import com.jd.lib.cashier.sdk.f.f.g;
import com.jd.lib.cashier.sdk.f.f.h;
import com.jd.lib.cashier.sdk.f.f.i;
import com.jd.lib.cashier.sdk.f.f.j;
import com.jd.lib.cashier.sdk.f.f.k;
import com.jd.lib.cashier.sdk.f.f.l;
import com.jd.lib.cashier.sdk.freindpay.bean.CashierFriendPayEntity;
import com.jd.lib.cashier.sdk.freindpay.bean.WareInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class c {
    private static synchronized void a(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierFriendPayEntity cashierFriendPayEntity) {
        synchronized (c.class) {
            if (list != null && cashierFriendPayEntity != null) {
                List<WareInfo> list2 = cashierFriendPayEntity.itemList;
                if (list2 != null && !list2.isEmpty()) {
                    list.add(new j());
                    list.add(new f());
                    int size = cashierFriendPayEntity.itemList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        WareInfo wareInfo = cashierFriendPayEntity.itemList.get(i2);
                        if (wareInfo != null) {
                            list.add(new e(wareInfo));
                            if (i2 != size - 1) {
                                list.add(new k());
                            }
                        }
                    }
                }
            }
        }
    }

    private static synchronized void b(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierFriendPayEntity cashierFriendPayEntity) {
        synchronized (c.class) {
            if (list != null && cashierFriendPayEntity != null) {
                com.jd.lib.cashier.sdk.f.f.a aVar = new com.jd.lib.cashier.sdk.f.f.a();
                aVar.a = cashierFriendPayEntity.explainTitle;
                aVar.b = cashierFriendPayEntity.explain;
                list.add(aVar);
            }
        }
    }

    private static synchronized void c(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierFriendPayEntity cashierFriendPayEntity) {
        synchronized (c.class) {
            if (list != null && cashierFriendPayEntity != null) {
                g gVar = new g();
                gVar.b = cashierFriendPayEntity.payprice;
                gVar.d = cashierFriendPayEntity.moneyFlag;
                gVar.a = cashierFriendPayEntity.description;
                gVar.f3357c = cashierFriendPayEntity.countDownTime;
                gVar.f3358e = cashierFriendPayEntity.countdownPopInfo;
                list.add(gVar);
            }
        }
    }

    private static synchronized void d(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        synchronized (c.class) {
            if (list != null) {
                list.add(new h());
            }
        }
    }

    private static synchronized void e(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierFriendPayEntity cashierFriendPayEntity) {
        synchronized (c.class) {
            if (list != null && cashierFriendPayEntity != null) {
                i iVar = new i();
                iVar.a = cashierFriendPayEntity.shareInfo;
                iVar.b = cashierFriendPayEntity.familyOuterInfo;
                list.add(iVar);
            }
        }
    }

    private static synchronized void f(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierFriendPayEntity cashierFriendPayEntity) {
        synchronized (c.class) {
            if (list != null && cashierFriendPayEntity != null) {
                l lVar = new l();
                lVar.a = cashierFriendPayEntity.topImageUrl;
                list.add(lVar);
            }
        }
    }

    public static synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> g(CashierFriendPayEntity cashierFriendPayEntity) {
        ArrayList arrayList;
        synchronized (c.class) {
            arrayList = new ArrayList();
            f(arrayList, cashierFriendPayEntity);
            c(arrayList, cashierFriendPayEntity);
            d(arrayList);
            e(arrayList, cashierFriendPayEntity);
            b(arrayList, cashierFriendPayEntity);
            a(arrayList, cashierFriendPayEntity);
        }
        return arrayList;
    }
}
