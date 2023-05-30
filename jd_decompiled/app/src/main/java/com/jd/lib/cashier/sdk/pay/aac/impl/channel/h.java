package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class h extends a implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a {
    public h(@NotNull Function2<? super List<? extends com.jd.lib.cashier.sdk.d.a.e.a>, ? super Payment, Unit> function2) {
        super(function2);
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.channel.a
    @NotNull
    public ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> h(@NotNull Payment payment) {
        Iterable arrayList;
        com.jd.lib.cashier.sdk.d.a.e.a aVar;
        boolean z;
        com.jd.lib.cashier.sdk.d.a.e.a nVar;
        List<Payment> list;
        List<Payment> list2;
        String str;
        Payment a;
        ArrayList<com.jd.lib.cashier.sdk.d.a.e.a> arrayList2 = new ArrayList<>();
        CashierPayAdapter l2 = l();
        if (l2 == null || (arrayList = l2.getData()) == null) {
            arrayList = new ArrayList();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add((com.jd.lib.cashier.sdk.d.a.e.a) it.next());
        }
        int size = arrayList2.size();
        int i2 = 0;
        while (true) {
            aVar = null;
            if (i2 >= size) {
                z = false;
                break;
            } else if (arrayList2.get(i2) instanceof com.jd.lib.cashier.sdk.h.g.r) {
                com.jd.lib.cashier.sdk.d.a.e.a aVar2 = arrayList2.get(i2);
                if (!(aVar2 instanceof com.jd.lib.cashier.sdk.h.g.r)) {
                    aVar2 = null;
                }
                com.jd.lib.cashier.sdk.h.g.r rVar = (com.jd.lib.cashier.sdk.h.g.r) aVar2;
                if (rVar == null || (a = rVar.a()) == null || (str = a.addNewCardScene) == null) {
                    str = "";
                }
                z = TextUtils.equals(str, "1");
            } else {
                i2++;
            }
        }
        int size2 = arrayList2.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size2) {
                i3 = -1;
                break;
            }
            com.jd.lib.cashier.sdk.d.a.e.a aVar3 = arrayList2.get(i3);
            Intrinsics.checkExpressionValueIsNotNull(aVar3, "templateEntityNewSourceList[i]");
            com.jd.lib.cashier.sdk.d.a.e.a aVar4 = aVar3;
            if (aVar4.getItemType() == 500001 || aVar4.getItemType() == 500002) {
                break;
            }
            i3++;
        }
        for (com.jd.lib.cashier.sdk.d.a.e.a aVar5 : arrayList2) {
            if (aVar5 instanceof x) {
                ((x) aVar5).a().selected = false;
            } else if ((aVar5 instanceof com.jd.lib.cashier.sdk.h.g.o) && (list2 = ((com.jd.lib.cashier.sdk.h.g.o) aVar5).a) != null) {
                int size3 = list2.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    list2.get(i4).selected = false;
                }
            }
        }
        payment.selected = true;
        payment.isSourceFromDialogSelected = true;
        payment.rightIconType = "1";
        if (i3 >= 0 && i3 < arrayList2.size()) {
            aVar = arrayList2.remove(i3);
        }
        if (i3 >= 0) {
            if (z) {
                ArrayList arrayList3 = new ArrayList();
                if ((aVar instanceof com.jd.lib.cashier.sdk.h.g.o) && (list = ((com.jd.lib.cashier.sdk.h.g.o) aVar).a) != null) {
                    arrayList3.addAll(list);
                }
                if (arrayList3.size() == 2) {
                    if (arrayList3.contains(payment)) {
                        if (Intrinsics.areEqual((Payment) arrayList3.get(0), payment)) {
                            arrayList3.remove(0);
                        } else {
                            arrayList3.remove(arrayList3.size() - 1);
                        }
                        arrayList3.add(0, payment);
                    } else {
                        arrayList3.remove(arrayList3.size() - 1);
                        arrayList3.add(0, payment);
                    }
                } else if (arrayList3.size() == 1) {
                    if (Intrinsics.areEqual((Payment) arrayList3.get(0), payment)) {
                        arrayList3.remove(0);
                    }
                    arrayList3.add(0, payment);
                }
                nVar = new com.jd.lib.cashier.sdk.h.g.o(arrayList3);
            } else {
                nVar = new com.jd.lib.cashier.sdk.h.g.n(payment);
            }
            arrayList2.add(i3, nVar);
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
