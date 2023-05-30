package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class k extends a implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a {
    public k(@NotNull Function2<? super List<? extends com.jd.lib.cashier.sdk.d.a.e.a>, ? super Payment, Unit> function2) {
        super(function2);
    }

    private final void w(x xVar, List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list, int i2) {
        if (xVar instanceof com.jd.lib.cashier.sdk.h.g.i) {
            ((com.jd.lib.cashier.sdk.h.g.i) xVar).f3562c = false;
            if (list.size() < i2 || list.get(i2).getItemType() != 400004) {
                return;
            }
            com.jd.lib.cashier.sdk.d.a.e.a aVar = list.get(i2);
            if (aVar == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.jd.lib.cashier.sdk.pay.templates.CashierBPayChannelBindingCardTemplate");
            }
            ((com.jd.lib.cashier.sdk.h.g.i) aVar).f3562c = true;
        }
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.channel.a
    @NotNull
    public ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> h(@NotNull Payment payment) {
        Iterable<com.jd.lib.cashier.sdk.d.a.e.a> arrayList;
        Object obj;
        boolean z;
        x gVar;
        String str;
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
        int i3 = 0;
        while (true) {
            obj = null;
            if (i3 >= size) {
                z = false;
                break;
            } else if (arrayList2.get(i3) instanceof com.jd.lib.cashier.sdk.h.g.m) {
                com.jd.lib.cashier.sdk.d.a.e.a aVar2 = arrayList2.get(i3);
                if (!(aVar2 instanceof com.jd.lib.cashier.sdk.h.g.m)) {
                    aVar2 = null;
                }
                com.jd.lib.cashier.sdk.h.g.m mVar = (com.jd.lib.cashier.sdk.h.g.m) aVar2;
                if (mVar == null || (a = mVar.a()) == null || (str = a.addNewCardScene) == null) {
                    str = "";
                }
                z = TextUtils.equals(str, "1");
            } else {
                i3++;
            }
        }
        int size2 = arrayList2.size();
        int i4 = -1;
        int i5 = -1;
        for (int i6 = 0; i6 < size2; i6++) {
            i5++;
            if ((arrayList2.get(i6) instanceof com.jd.lib.cashier.sdk.h.g.i) || (arrayList2.get(i6) instanceof com.jd.lib.cashier.sdk.h.g.g)) {
                break;
            }
        }
        int size3 = arrayList2.size();
        for (int i7 = 0; i7 < size3; i7++) {
            com.jd.lib.cashier.sdk.d.a.e.a aVar3 = arrayList2.get(i7);
            Intrinsics.checkExpressionValueIsNotNull(aVar3, "templateEntityNewSourceList[i]");
            com.jd.lib.cashier.sdk.d.a.e.a aVar4 = aVar3;
            if (aVar4.getItemType() == 400004 || aVar4.getItemType() == 400005) {
                i4 = i7;
            }
        }
        if (arrayList2.size() > i2) {
            for (com.jd.lib.cashier.sdk.d.a.e.a aVar5 : arrayList2) {
                if (aVar5 instanceof x) {
                    ((x) aVar5).a().selected = false;
                }
            }
            payment.selected = true;
            payment.isSourceFromDialogSelected = true;
            payment.rightIconType = "1";
            com.jd.lib.cashier.sdk.core.utils.r.b(a.f3681m.a(), "jdPaymentSize = " + i2);
            Iterator it = arrayList2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                com.jd.lib.cashier.sdk.d.a.e.a aVar6 = (com.jd.lib.cashier.sdk.d.a.e.a) next;
                if (!(aVar6 instanceof x)) {
                    aVar6 = null;
                }
                x xVar = (x) aVar6;
                if (Intrinsics.areEqual(xVar != null ? xVar.a() : null, payment)) {
                    obj = next;
                    break;
                }
            }
            com.jd.lib.cashier.sdk.d.a.e.a aVar7 = (com.jd.lib.cashier.sdk.d.a.e.a) obj;
            if (aVar7 != null) {
                Intrinsics.checkExpressionValueIsNotNull(arrayList2.remove(arrayList2.indexOf(aVar7)), "templateEntityNewSourceL\u2026moveAt(needToDeleteIndex)");
            } else if (i2 >= o() && i4 >= 0) {
                arrayList2.remove(i4);
            }
            if (i5 >= 0) {
                if (z) {
                    gVar = new com.jd.lib.cashier.sdk.h.g.i(payment, false);
                } else {
                    gVar = new com.jd.lib.cashier.sdk.h.g.g(payment);
                }
                w(gVar, arrayList2, i5);
                arrayList2.add(i5, gVar);
            }
            return arrayList2;
        }
        return arrayList2;
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.channel.a
    public void p(@NotNull FragmentActivity fragmentActivity, @NotNull List<? extends com.jd.lib.cashier.sdk.d.a.e.a> list) {
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.channel.a
    public void q(@NotNull FragmentActivity fragmentActivity) {
        u(1);
    }
}
