package com.jd.lib.cashier.sdk.i.a.d;

import com.jd.lib.cashier.sdk.d.f.c;
import com.jd.lib.cashier.sdk.i.a.a.d;
import com.jd.lib.cashier.sdk.i.a.a.e;
import com.jd.lib.cashier.sdk.i.b.b;
import com.jd.lib.cashier.sdk.quickpay.view.CashierQuickPayActivity;

/* loaded from: classes14.dex */
public class a {
    private final com.jd.lib.cashier.sdk.i.a.e.a a;

    public a(com.jd.lib.cashier.sdk.i.a.e.a aVar) {
        this.a = aVar;
    }

    private void a(c cVar) {
        com.jd.lib.cashier.sdk.i.a.e.a aVar;
        if (cVar == null || (aVar = this.a) == null) {
            return;
        }
        cVar.appId = aVar.b;
        cVar.paySign = aVar.f3608n;
        cVar.orderId = aVar.d;
        cVar.orderType = aVar.f3599e;
        cVar.orderPrice = aVar.f3601g;
        cVar.orderTypeCode = aVar.f3600f;
    }

    public void b(CashierQuickPayActivity cashierQuickPayActivity, boolean z) {
        try {
            com.jd.lib.cashier.sdk.i.a.e.a aVar = this.a;
            if (aVar == null || aVar.p) {
                return;
            }
            aVar.p = true;
            com.jd.lib.cashier.sdk.b.f.a aVar2 = new com.jd.lib.cashier.sdk.b.f.a();
            if (z) {
                aVar2.b = "1";
            }
            aVar2.setActivity(cashierQuickPayActivity);
            aVar2.d = 1000;
            aVar2.a = this.a.o;
            com.jd.lib.cashier.sdk.i.a.a.c cVar = new com.jd.lib.cashier.sdk.i.a.a.c();
            a(aVar2);
            cVar.e(aVar2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void c(CashierQuickPayActivity cashierQuickPayActivity) {
        if (this.a != null) {
            d dVar = new d();
            com.jd.lib.cashier.sdk.i.b.a aVar = new com.jd.lib.cashier.sdk.i.b.a();
            aVar.setActivity(cashierQuickPayActivity);
            com.jd.lib.cashier.sdk.i.a.e.a aVar2 = this.a;
            aVar.f3613h = aVar2.f3606l;
            aVar.b = aVar2.f3603i;
            aVar.f3609c = aVar2.f3604j;
            aVar.d = aVar2.f3605k;
            aVar.a = aVar2.f3602h;
            aVar.u = aVar2.o;
            aVar.f3610e = aVar2.f3607m;
            aVar.f3612g = com.jd.lib.cashier.sdk.b.e.a.a().b();
            aVar.f3611f = com.jd.lib.cashier.sdk.b.e.a.a().c();
            com.jd.lib.cashier.sdk.i.a.e.a aVar3 = this.a;
            com.jd.lib.cashier.sdk.i.a.b.a aVar4 = aVar3.s;
            if (aVar4 != null) {
                aVar.s = aVar4.f3590k;
                aVar.t = aVar4.f3591l;
                aVar.f3614i = aVar4.a;
                aVar.f3617l = aVar3.r.f3596c;
                aVar.f3615j = aVar4.b;
                aVar.f3616k = aVar4.f3583c;
                aVar.p = aVar4.f3587h;
                aVar.o = aVar4.f3586g;
                aVar.f3618m = aVar4.f3584e;
                aVar.f3619n = aVar4.f3585f;
                aVar.r = aVar4.f3589j;
                aVar.q = aVar4.f3588i;
            }
            a(aVar);
            dVar.e(aVar);
        }
    }

    public void d(CashierQuickPayActivity cashierQuickPayActivity) {
        if (this.a != null) {
            e eVar = new e();
            b bVar = new b();
            bVar.setActivity(cashierQuickPayActivity);
            com.jd.lib.cashier.sdk.i.a.e.a aVar = this.a;
            bVar.p = aVar.f3606l;
            bVar.f3624h = aVar.f3603i;
            bVar.f3625i = aVar.f3604j;
            bVar.f3628l = aVar.f3605k;
            bVar.f3622f = aVar.f3602h;
            bVar.f3629m = aVar.f3607m;
            bVar.o = com.jd.lib.cashier.sdk.b.e.a.a().b();
            bVar.f3630n = com.jd.lib.cashier.sdk.b.e.a.a().c();
            a(bVar);
            eVar.e(bVar);
        }
    }
}
