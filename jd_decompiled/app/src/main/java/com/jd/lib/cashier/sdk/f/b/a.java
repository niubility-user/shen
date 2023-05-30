package com.jd.lib.cashier.sdk.f.b;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.f.f.f;
import com.jd.lib.cashier.sdk.f.f.g;
import com.jd.lib.cashier.sdk.f.f.h;
import com.jd.lib.cashier.sdk.f.f.i;
import com.jd.lib.cashier.sdk.f.f.j;
import com.jd.lib.cashier.sdk.f.f.k;
import com.jd.lib.cashier.sdk.freindpay.bean.CashierFriendPayEntity;
import com.jd.lib.cashier.sdk.freindpay.bean.DynamicProductInfoFloorData;
import com.jd.lib.cashier.sdk.freindpay.bean.DynamicTopFloorData;
import com.jd.lib.cashier.sdk.freindpay.bean.WareInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class a {
    private static synchronized void a(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        synchronized (a.class) {
            if (list != null) {
                list.add(new k());
            }
        }
    }

    private static synchronized void b(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierFriendPayEntity cashierFriendPayEntity) {
        synchronized (a.class) {
            if (list != null && cashierFriendPayEntity != null) {
                List<WareInfo> list2 = cashierFriendPayEntity.itemList;
                if (list2 != null && !list2.isEmpty()) {
                    g(list);
                    c(list);
                    int size = cashierFriendPayEntity.itemList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        WareInfo wareInfo = cashierFriendPayEntity.itemList.get(i2);
                        if (wareInfo != null) {
                            com.jd.lib.cashier.sdk.f.f.b bVar = new com.jd.lib.cashier.sdk.f.f.b();
                            bVar.a = new DynamicProductInfoFloorData(wareInfo.imageUrl, wareInfo.price, wareInfo.wareName, wareInfo.count);
                            list.add(bVar);
                            if (i2 != size - 1) {
                                a(list);
                            }
                        }
                    }
                }
            }
        }
    }

    private static synchronized void c(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        synchronized (a.class) {
            if (list != null) {
                list.add(new f());
            }
        }
    }

    private static synchronized void d(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierFriendPayEntity cashierFriendPayEntity) {
        synchronized (a.class) {
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

    private static synchronized void e(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierFriendPayEntity cashierFriendPayEntity) {
        synchronized (a.class) {
            if (list != null && cashierFriendPayEntity != null) {
                i iVar = new i();
                iVar.a = cashierFriendPayEntity.shareInfo;
                iVar.b = cashierFriendPayEntity.familyOuterInfo;
                list.add(iVar);
                com.jd.lib.cashier.sdk.f.f.a aVar = new com.jd.lib.cashier.sdk.f.f.a();
                aVar.a = cashierFriendPayEntity.explainTitle;
                aVar.b = cashierFriendPayEntity.explain;
                list.add(aVar);
            }
        }
    }

    private static synchronized void f(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierFriendPayEntity cashierFriendPayEntity) {
        synchronized (a.class) {
            if (list != null && cashierFriendPayEntity != null) {
                if (!TextUtils.isEmpty(cashierFriendPayEntity.topImageUrl)) {
                    com.jd.lib.cashier.sdk.f.f.c cVar = new com.jd.lib.cashier.sdk.f.f.c();
                    cVar.a = new DynamicTopFloorData(cashierFriendPayEntity.topImageUrl);
                    list.add(cVar);
                }
            }
        }
    }

    private static synchronized void g(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        synchronized (a.class) {
            if (list != null) {
                list.add(new j());
            }
        }
    }

    private static synchronized void h(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        synchronized (a.class) {
            if (list != null) {
                list.add(new h());
            }
        }
    }

    public static synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> i(CashierFriendPayEntity cashierFriendPayEntity) {
        ArrayList arrayList;
        synchronized (a.class) {
            arrayList = new ArrayList();
            f(arrayList, cashierFriendPayEntity);
            d(arrayList, cashierFriendPayEntity);
            h(arrayList);
            e(arrayList, cashierFriendPayEntity);
            b(arrayList, cashierFriendPayEntity);
        }
        return arrayList;
    }
}
