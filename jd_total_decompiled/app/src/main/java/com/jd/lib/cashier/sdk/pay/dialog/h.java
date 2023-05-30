package com.jd.lib.cashier.sdk.pay.dialog;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.PlanRowEntity;
import com.jd.lib.cashier.sdk.core.model.PopBusinessMap;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.pay.adapter.PlanPaymentRateAdapter;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class h {
    private final FragmentActivity a;

    /* loaded from: classes14.dex */
    public static final class a implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ Dialog f3922g;

        /* renamed from: h */
        final /* synthetic */ h f3923h;

        /* renamed from: i */
        final /* synthetic */ CashierCommonPopConfig f3924i;

        /* renamed from: com.jd.lib.cashier.sdk.pay.dialog.h$a$a */
        /* loaded from: classes14.dex */
        static final class C0139a implements p.a {
            C0139a() {
                a.this = r1;
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public final void onRefresh() {
                com.jd.lib.cashier.sdk.b.i.d.a(a.this.f3923h.a);
            }
        }

        a(Dialog dialog, h hVar, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f3922g = dialog;
            this.f3923h = hVar;
            this.f3924i = cashierCommonPopConfig;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3922g.dismiss();
            FragmentActivity fragmentActivity = this.f3923h.a;
            CashierCommonPopConfig cashierCommonPopConfig = this.f3924i;
            p.b(fragmentActivity, cashierCommonPopConfig.cancelBtnUrl, cashierCommonPopConfig.cancelOpType, new C0139a());
        }
    }

    /* loaded from: classes14.dex */
    public static final class b implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ Dialog f3925g;

        /* renamed from: h */
        final /* synthetic */ h f3926h;

        /* renamed from: i */
        final /* synthetic */ CashierCommonPopConfig f3927i;

        /* loaded from: classes14.dex */
        static final class a implements p.a {
            a() {
                b.this = r1;
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public final void onRefresh() {
                com.jd.lib.cashier.sdk.b.i.d.a(b.this.f3926h.a);
            }
        }

        b(Dialog dialog, h hVar, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f3925g = dialog;
            this.f3926h = hVar;
            this.f3927i = cashierCommonPopConfig;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3925g.dismiss();
            FragmentActivity fragmentActivity = this.f3926h.a;
            CashierCommonPopConfig cashierCommonPopConfig = this.f3927i;
            p.b(fragmentActivity, cashierCommonPopConfig.confirmBtnUrl, cashierCommonPopConfig.confirmOpType, new a());
        }
    }

    public h(@NotNull FragmentActivity fragmentActivity) {
        this.a = fragmentActivity;
    }

    private final View b(PopBusinessMap popBusinessMap) {
        List arrayList;
        try {
            View inflate = LayoutInflater.from(this.a).inflate(R.layout.lib_cashier_sdk_dialog_plan_rate_custom_layout, (ViewGroup) null, false);
            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.lib_cashier_dialog_plan_rate_recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.a, 1, false));
            if (popBusinessMap == null || (arrayList = popBusinessMap.table) == null) {
                arrayList = new ArrayList();
            }
            recyclerView.setAdapter(new PlanPaymentRateAdapter(arrayList));
            return inflate;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public final void c(@NotNull CashierCommonPopConfig cashierCommonPopConfig) {
        String str = cashierCommonPopConfig.title;
        String str2 = cashierCommonPopConfig.confirmBtn;
        String str3 = cashierCommonPopConfig.cancelBtn;
        PopBusinessMap popBusinessMap = cashierCommonPopConfig.businessMap;
        List<PlanRowEntity> list = popBusinessMap != null ? popBusinessMap.table : null;
        if ((list == null || list.isEmpty()) || TextUtils.isEmpty(str)) {
            return;
        }
        Dialog d = com.jd.lib.cashier.sdk.core.utils.j.d(this.a, str, popBusinessMap.tip, b(cashierCommonPopConfig.businessMap), str3, str2);
        if (d != null) {
            d.setCancelable(false);
            d.setCanceledOnTouchOutside(false);
            com.jd.lib.cashier.sdk.core.utils.j.k(d, new a(d, this, cashierCommonPopConfig));
            com.jd.lib.cashier.sdk.core.utils.j.l(d, new b(d, this, cashierCommonPopConfig));
            if (d != null) {
                d.show();
            }
        }
    }
}
