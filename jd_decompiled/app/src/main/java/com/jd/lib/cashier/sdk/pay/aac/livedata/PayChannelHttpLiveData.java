package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.d.a.e.a;
import com.jd.lib.cashier.sdk.h.b.d;
import com.jd.lib.cashier.sdk.h.b.k;
import com.jd.lib.cashier.sdk.h.b.l;
import com.jd.lib.cashier.sdk.h.b.o;
import com.jd.lib.cashier.sdk.h.b.p;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.h.h.e;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.i;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.List;

/* loaded from: classes14.dex */
public class PayChannelHttpLiveData extends LiveData<i> {
    @Nullable
    private synchronized o a(boolean z, boolean z2, boolean z3, @Nullable CashierPayEntity cashierPayEntity) {
        o oVar;
        oVar = null;
        if (cashierPayEntity != null) {
            if (z2) {
                if (z3) {
                    oVar = k.c().b(cashierPayEntity);
                } else {
                    oVar = l.d().c(cashierPayEntity);
                }
            } else if (z) {
                oVar = d.d().c(cashierPayEntity);
            } else {
                oVar = p.l().i(cashierPayEntity);
            }
        }
        return oVar;
    }

    public synchronized void b(CashierPayEntity cashierPayEntity, FragmentActivity fragmentActivity) {
        Payment f2;
        if (cashierPayEntity != null) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            if (cashierPayViewModel.b().E) {
                f2 = e.a(cashierPayEntity);
            } else {
                f2 = e.f(cashierPayEntity);
            }
            cashierPayViewModel.b().O = f2;
            cashierPayViewModel.b().T = f2.code;
            r.b("PayChannelHttpLiveData", "mMtaCurrentClickPayment = " + cashierPayViewModel.b().O);
            o a = a(cashierPayViewModel.b().F, cashierPayViewModel.b().E, cashierPayViewModel.b().I, cashierPayEntity);
            if (a == null) {
                return;
            }
            List<a> a2 = a.a();
            Payment payment = null;
            for (a aVar : a2) {
                if (aVar instanceof com.jd.lib.cashier.sdk.h.g.o) {
                    List<Payment> list = ((com.jd.lib.cashier.sdk.h.g.o) aVar).a;
                    if (list != null && !list.isEmpty()) {
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            Payment payment2 = list.get(i2);
                            if (payment2 != null && payment2.equals(f2)) {
                                payment2.selected = true;
                                payment = payment2;
                            } else if (payment2 != null) {
                                payment2.selected = false;
                            }
                        }
                    }
                } else if (aVar instanceof x) {
                    x xVar = (x) aVar;
                    if (xVar.a().equals(f2)) {
                        xVar.a().selected = true;
                        payment = xVar.a();
                    } else {
                        xVar.a().selected = false;
                    }
                }
            }
            List<a> b = a.b();
            r.b("jdOtherWholeFloorList2", "jdOtherWholeFloorList");
            postValue(new i(a2, b, cashierPayEntity.jdPayChannelList, payment));
        }
    }
}
