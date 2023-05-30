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
public final class c implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a {
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private CashierPayAdapter f3699g;

    /* loaded from: classes14.dex */
    static final class a<T> implements Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.e> {
        a() {
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final void onChanged(com.jd.lib.cashier.sdk.pay.aac.livedata.a.e eVar) {
            c.this.c(eVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void c(com.jd.lib.cashier.sdk.pay.aac.livedata.a.e eVar) {
        List arrayList;
        if (eVar != null) {
            ArrayList arrayList2 = new ArrayList();
            CashierPayAdapter cashierPayAdapter = this.f3699g;
            if (cashierPayAdapter == null || (arrayList = cashierPayAdapter.getData()) == null) {
                arrayList = new ArrayList();
            }
            arrayList2.addAll(arrayList);
            if (arrayList2.contains(eVar.b)) {
                arrayList2.addAll(arrayList2.indexOf(eVar.b), eVar.a);
                arrayList2.remove(arrayList2.indexOf(eVar.b));
                CashierPayAdapter cashierPayAdapter2 = this.f3699g;
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
        ((CashierPayViewModel) viewModel).t().observe(fragmentActivity, new a());
    }

    public final void h(@Nullable CashierPayAdapter cashierPayAdapter) {
        this.f3699g = cashierPayAdapter;
    }
}
