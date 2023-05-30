package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.CashierPayAdapter;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class l implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a {
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private CashierPayAdapter f3710g;

    /* loaded from: classes14.dex */
    static final class a<T> implements Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.q> {
        a() {
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void onChanged(com.jd.lib.cashier.sdk.pay.aac.livedata.a.q qVar) {
            l.this.c(qVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void c(com.jd.lib.cashier.sdk.pay.aac.livedata.a.q qVar) {
        List arrayList;
        if (qVar != null) {
            ArrayList arrayList2 = new ArrayList();
            CashierPayAdapter cashierPayAdapter = this.f3710g;
            if (cashierPayAdapter == null || (arrayList = cashierPayAdapter.getData()) == null) {
                arrayList = new ArrayList();
            }
            arrayList2.addAll(arrayList);
            if (arrayList2.contains(qVar.b)) {
                arrayList2.addAll(arrayList2.indexOf(qVar.b), qVar.a);
                arrayList2.remove(arrayList2.indexOf(qVar.b));
                CashierPayAdapter cashierPayAdapter2 = this.f3710g;
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
        ((CashierPayViewModel) viewModel).C().observe(fragmentActivity, new a());
    }

    public final void h(@Nullable CashierPayAdapter cashierPayAdapter) {
        this.f3710g = cashierPayAdapter;
    }
}
