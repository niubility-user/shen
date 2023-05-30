package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class g implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a {
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private CashierPayAdapter f3703g;

    /* loaded from: classes14.dex */
    static final class a<T> implements Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.f> {
        a() {
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void onChanged(com.jd.lib.cashier.sdk.pay.aac.livedata.a.f fVar) {
            g.this.c(fVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void c(com.jd.lib.cashier.sdk.pay.aac.livedata.a.f fVar) {
        List arrayList;
        if (fVar != null) {
            ArrayList arrayList2 = new ArrayList();
            CashierPayAdapter cashierPayAdapter = this.f3703g;
            if (cashierPayAdapter == null || (arrayList = cashierPayAdapter.getData()) == null) {
                arrayList = new ArrayList();
            }
            arrayList2.addAll(arrayList);
            if (arrayList2.contains(fVar.d)) {
                int i2 = 0;
                if (fVar.b()) {
                    arrayList2.addAll(0, fVar.b);
                } else {
                    Iterator it = arrayList2.iterator();
                    int i3 = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        com.jd.lib.cashier.sdk.d.a.e.a template = (com.jd.lib.cashier.sdk.d.a.e.a) it.next();
                        Intrinsics.checkExpressionValueIsNotNull(template, "template");
                        if (template.getItemType() == 400010) {
                            Object obj = arrayList2.get(i3 - 1);
                            Intrinsics.checkExpressionValueIsNotNull(obj, "newDataSource[insertThirdPayIndex - 1]");
                            List<Payment> list = fVar.a;
                            Intrinsics.checkExpressionValueIsNotNull(list, "expandEvent.otherPaymentList");
                            if (!h((com.jd.lib.cashier.sdk.d.a.e.a) obj, list)) {
                                arrayList2.addAll(i3, fVar.b);
                                break;
                            }
                        }
                        i3++;
                    }
                }
                if (fVar.a()) {
                    arrayList2.addAll(arrayList2.indexOf(fVar.d), fVar.f3779c);
                } else {
                    Iterator it2 = arrayList2.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        com.jd.lib.cashier.sdk.d.a.e.a template2 = (com.jd.lib.cashier.sdk.d.a.e.a) it2.next();
                        Intrinsics.checkExpressionValueIsNotNull(template2, "template");
                        if (template2.getItemType() == 400010) {
                            Object obj2 = arrayList2.get(i2 - 1);
                            Intrinsics.checkExpressionValueIsNotNull(obj2, "newDataSource[insertOtherPayIndex - 1]");
                            List<Payment> list2 = fVar.a;
                            Intrinsics.checkExpressionValueIsNotNull(list2, "expandEvent.otherPaymentList");
                            if (h((com.jd.lib.cashier.sdk.d.a.e.a) obj2, list2)) {
                                arrayList2.addAll(i2, fVar.f3779c);
                                break;
                            }
                        }
                        i2++;
                    }
                }
                arrayList2.remove(arrayList2.indexOf(fVar.d));
                CashierPayAdapter cashierPayAdapter2 = this.f3703g;
                if (cashierPayAdapter2 != null) {
                    cashierPayAdapter2.D(arrayList2);
                }
            }
        }
    }

    private final boolean h(com.jd.lib.cashier.sdk.d.a.e.a aVar, List<? extends Payment> list) {
        if (aVar instanceof x) {
            return com.jd.lib.cashier.sdk.h.h.g.o(((x) aVar).a(), list);
        }
        if (aVar instanceof com.jd.lib.cashier.sdk.h.g.o) {
        }
        return false;
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(@NotNull FragmentActivity fragmentActivity) {
        ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
        ((CashierPayViewModel) viewModel).u().observe(fragmentActivity, new a());
    }

    public final void l(@Nullable CashierPayAdapter cashierPayAdapter) {
        this.f3703g = cashierPayAdapter;
    }
}
