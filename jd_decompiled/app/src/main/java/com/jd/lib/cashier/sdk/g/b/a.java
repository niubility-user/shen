package com.jd.lib.cashier.sdk.g.b;

import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.freindpaydialog.bean.CashierFriendPayDialogEntity;
import com.jd.lib.cashier.sdk.freindpaydialog.bean.WareInfo;
import com.jd.lib.cashier.sdk.g.g.b;
import com.jd.lib.cashier.sdk.g.g.c;
import com.jd.lib.cashier.sdk.g.g.d;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class a {
    private static synchronized void a(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierFriendPayDialogEntity cashierFriendPayDialogEntity) {
        synchronized (a.class) {
            if (list != null && cashierFriendPayDialogEntity != null) {
                List<WareInfo> list2 = cashierFriendPayDialogEntity.itemList;
                if (list2 != null && !list2.isEmpty()) {
                    int size = cashierFriendPayDialogEntity.itemList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        b bVar = new b();
                        WareInfo wareInfo = cashierFriendPayDialogEntity.itemList.get(i2);
                        if (i2 == 0) {
                            bVar.f3476f = true;
                            bVar.f3477g = true;
                            bVar.f3478h = false;
                        } else if (i2 == size - 1) {
                            bVar.f3476f = false;
                            bVar.f3477g = false;
                            bVar.f3478h = true;
                        } else {
                            bVar.f3476f = false;
                            bVar.f3477g = false;
                            bVar.f3478h = false;
                        }
                        if (wareInfo != null) {
                            bVar.d = wareInfo.imageUrl;
                            bVar.a = wareInfo.wareName;
                            bVar.f3474c = wareInfo.count;
                            String str = wareInfo.skuId;
                            bVar.b = wareInfo.price;
                        }
                        bVar.f3475e = cashierFriendPayDialogEntity.itemList.size();
                        list.add(bVar);
                    }
                }
            }
        }
    }

    private static synchronized void b(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierFriendPayDialogEntity cashierFriendPayDialogEntity) {
        synchronized (a.class) {
            if (list != null && cashierFriendPayDialogEntity != null) {
                com.jd.lib.cashier.sdk.g.g.a aVar = new com.jd.lib.cashier.sdk.g.g.a();
                aVar.b = cashierFriendPayDialogEntity.payprice;
                aVar.f3473c = cashierFriendPayDialogEntity.moneyFlag;
                aVar.a = cashierFriendPayDialogEntity.description;
                String str = cashierFriendPayDialogEntity.countDownTime;
                CashierCommonPopConfig cashierCommonPopConfig = cashierFriendPayDialogEntity.countdownPopInfo;
                list.add(aVar);
            }
        }
    }

    private static synchronized void c(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierFriendPayDialogEntity cashierFriendPayDialogEntity) {
        synchronized (a.class) {
            if (list != null && cashierFriendPayDialogEntity != null) {
                c cVar = new c();
                cVar.a = cashierFriendPayDialogEntity.shareInfo;
                list.add(cVar);
            }
        }
    }

    private static synchronized void d(List<com.jd.lib.cashier.sdk.d.a.e.a> list, CashierFriendPayDialogEntity cashierFriendPayDialogEntity) {
        synchronized (a.class) {
            if (list != null && cashierFriendPayDialogEntity != null) {
                d dVar = new d();
                dVar.a = cashierFriendPayDialogEntity.friendPayTip;
                list.add(dVar);
            }
        }
    }

    public static synchronized List<com.jd.lib.cashier.sdk.d.a.e.a> e(CashierFriendPayDialogEntity cashierFriendPayDialogEntity) {
        ArrayList arrayList;
        synchronized (a.class) {
            arrayList = new ArrayList();
            b(arrayList, cashierFriendPayDialogEntity);
            d(arrayList, cashierFriendPayDialogEntity);
            a(arrayList, cashierFriendPayDialogEntity);
            c(arrayList, cashierFriendPayDialogEntity);
        }
        return arrayList;
    }
}
