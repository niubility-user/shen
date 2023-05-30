package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.b.a;
import com.jd.lib.cashier.sdk.h.g.u;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class t extends a implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a {
    public t(@NotNull Function2<? super List<? extends com.jd.lib.cashier.sdk.d.a.e.a>, ? super Payment, Unit> function2) {
        super(function2);
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.channel.a
    @NotNull
    public ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> h(@NotNull Payment payment) {
        Iterable<com.jd.lib.cashier.sdk.d.a.e.a> arrayList;
        Object obj;
        Payment a;
        ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> arrayList2 = new ArrayList<>();
        CashierPayAdapter l2 = l();
        if (l2 == null || (arrayList = l2.getData()) == null) {
            arrayList = new ArrayList();
        }
        int i2 = 0;
        for (com.jd.lib.cashier.sdk.d.a.e.a aVar : arrayList) {
            arrayList2.add(aVar);
            if ((aVar instanceof x) && com.jd.lib.cashier.sdk.h.h.g.j(((x) aVar).a(), n())) {
                i2++;
            }
        }
        int size = arrayList2.size();
        int i3 = -1;
        int i4 = -1;
        for (int i5 = 0; i5 < size; i5++) {
            i4++;
            if (arrayList2.get(i5) instanceof x) {
                break;
            }
        }
        int size2 = arrayList2.size();
        int i6 = 0;
        while (true) {
            if (i6 >= size2) {
                break;
            }
            com.jd.lib.cashier.sdk.d.a.e.a aVar2 = arrayList2.get(i6);
            Intrinsics.checkExpressionValueIsNotNull(aVar2, "templateEntityNewSourceList[i]");
            com.jd.lib.cashier.sdk.d.a.e.a aVar3 = aVar2;
            if ((aVar3 instanceof x) && com.jd.lib.cashier.sdk.h.h.g.l(((x) aVar3).a().code)) {
                i3 = i6 - 1;
                break;
            }
            i6++;
        }
        if (arrayList2.size() > i2) {
            for (com.jd.lib.cashier.sdk.d.a.e.a aVar4 : arrayList2) {
                if ((aVar4 instanceof x) && (a = ((x) aVar4).a()) != null) {
                    a.selected = false;
                }
            }
            payment.rightIconType = "1";
            payment.selected = true;
            payment.isSourceFromDialogSelected = true;
            com.jd.lib.cashier.sdk.core.utils.r.b(a.f3681m.a(), "jdPaymentSize = " + i2);
            Iterator<T> it = arrayList2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                com.jd.lib.cashier.sdk.d.a.e.a aVar5 = (com.jd.lib.cashier.sdk.d.a.e.a) next;
                if (!(aVar5 instanceof x)) {
                    aVar5 = null;
                }
                x xVar = (x) aVar5;
                if (Intrinsics.areEqual(xVar != null ? xVar.a() : null, payment)) {
                    obj = next;
                    break;
                }
            }
            com.jd.lib.cashier.sdk.d.a.e.a aVar6 = (com.jd.lib.cashier.sdk.d.a.e.a) obj;
            if (aVar6 != null) {
                Intrinsics.checkExpressionValueIsNotNull(arrayList2.remove(arrayList2.indexOf(aVar6)), "templateEntityNewSourceL\u2026moveAt(needToDeleteIndex)");
            } else if (i2 >= o() && i3 >= 0) {
                arrayList2.remove(i3);
            }
            if (i4 >= 0) {
                arrayList2.add(i4, new u(payment));
            }
            return arrayList2;
        }
        return arrayList2;
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.channel.a
    public void p(@NotNull FragmentActivity fragmentActivity, @NotNull List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list) {
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b;
        CashierPayEntity cashierPayEntity;
        Boolean bool = null;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        if (cashierPayActivity != null && (x = cashierPayActivity.x()) != null && (b = x.b()) != null && (cashierPayEntity = b.K) != null) {
            bool = Boolean.valueOf(cashierPayEntity.showPayLogo());
        }
        int i2 = 0;
        for (com.jd.lib.cashier.sdk.d.a.e.a aVar : list) {
            if (aVar instanceof x) {
                x xVar = (x) aVar;
                Payment a = xVar.a();
                if (com.jd.lib.cashier.sdk.h.h.g.j(xVar.a(), n())) {
                    if (i2 == 0 && Intrinsics.areEqual(bool, Boolean.FALSE)) {
                        a.splitLineType = a.b.FLOOR_TOP_AND_NORMAL;
                    } else {
                        a.splitLineType = a.b.NORMAL;
                    }
                    i2++;
                }
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.channel.a
    public void q(@NotNull FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) viewModel;
            List<com.jd.lib.cashier.sdk.d.a.e.a> n2 = n();
            int size = n2 != null ? n2.size() : 0;
            CashierPayEntity cashierPayEntity = cashierPayViewModel.b().K;
            int i2 = cashierPayEntity != null ? cashierPayEntity.recommendJDPayChannelSize : 0;
            if (1 <= i2 && size > i2) {
                u(i2);
            } else {
                u(1);
            }
        }
    }
}
