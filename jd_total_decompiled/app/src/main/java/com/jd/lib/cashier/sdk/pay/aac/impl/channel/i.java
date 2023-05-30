package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class i implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a {
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private CashierPayAdapter f3705g;

    /* loaded from: classes14.dex */
    static final class a<T> implements Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.g> {
        a() {
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void onChanged(com.jd.lib.cashier.sdk.pay.aac.livedata.a.g gVar) {
            i.this.c(gVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void c(com.jd.lib.cashier.sdk.pay.aac.livedata.a.g gVar) {
        List arrayList;
        if (gVar != null) {
            ArrayList arrayList2 = new ArrayList();
            CashierPayAdapter cashierPayAdapter = this.f3705g;
            if (cashierPayAdapter == null || (arrayList = cashierPayAdapter.getData()) == null) {
                arrayList = new ArrayList();
            }
            arrayList2.addAll(arrayList);
            if (arrayList2.contains(gVar.d)) {
                int i2 = 0;
                if (gVar.b()) {
                    arrayList2.addAll(0, gVar.b);
                } else {
                    Iterator it = arrayList2.iterator();
                    int i3 = 0;
                    while (it.hasNext()) {
                        com.jd.lib.cashier.sdk.d.a.e.a template = (com.jd.lib.cashier.sdk.d.a.e.a) it.next();
                        Intrinsics.checkExpressionValueIsNotNull(template, "template");
                        if (template.getItemType() == 400010) {
                            int i4 = i3 - 1;
                            Object obj = arrayList2.get(i4);
                            Intrinsics.checkExpressionValueIsNotNull(obj, "newDataSource[insertThirdPayIndex - 1]");
                            if (((com.jd.lib.cashier.sdk.d.a.e.a) obj).getItemType() != 400006) {
                                Object obj2 = arrayList2.get(i4);
                                if (!(obj2 instanceof x)) {
                                    obj2 = null;
                                }
                                x xVar = (x) obj2;
                                if (!com.jd.lib.cashier.sdk.h.h.g.o(xVar != null ? xVar.a() : null, gVar.a)) {
                                }
                            }
                            arrayList2.addAll(i3, gVar.b);
                            break;
                        }
                        i3++;
                    }
                }
                if (gVar.a()) {
                    arrayList2.addAll(arrayList2.indexOf(gVar.d), gVar.f3780c);
                } else {
                    Iterator it2 = arrayList2.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        com.jd.lib.cashier.sdk.d.a.e.a template2 = (com.jd.lib.cashier.sdk.d.a.e.a) it2.next();
                        Intrinsics.checkExpressionValueIsNotNull(template2, "template");
                        if (template2.getItemType() == 400010) {
                            Object obj3 = arrayList2.get(i2 - 1);
                            if (!(obj3 instanceof x)) {
                                obj3 = null;
                            }
                            x xVar2 = (x) obj3;
                            if (com.jd.lib.cashier.sdk.h.h.g.o(xVar2 != null ? xVar2.a() : null, gVar.a)) {
                                arrayList2.addAll(i2, gVar.f3780c);
                                break;
                            }
                        }
                        i2++;
                    }
                }
                arrayList2.remove(arrayList2.indexOf(gVar.d));
                CashierPayAdapter cashierPayAdapter2 = this.f3705g;
                if (cashierPayAdapter2 != null) {
                    cashierPayAdapter2.D(arrayList2);
                }
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(@NotNull FragmentActivity fragmentActivity) {
        ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
        ((CashierPayViewModel) viewModel).v().observe(fragmentActivity, new a());
    }

    public final void h(@Nullable CashierPayAdapter cashierPayAdapter) {
        this.f3705g = cashierPayAdapter;
    }
}
