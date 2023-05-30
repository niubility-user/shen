package com.jd.lib.cashier.sdk.creditpay.dialog;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.PlanRowEntity;
import com.jd.lib.cashier.sdk.core.model.PopBusinessMap;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.creditpay.aac.viewmodel.CashierCreditPayViewModel;
import com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity;
import com.jd.lib.cashier.sdk.pay.adapter.PlanPaymentRateAdapter;
import java.util.List;

/* loaded from: classes14.dex */
public class a {

    /* renamed from: com.jd.lib.cashier.sdk.creditpay.dialog.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    class ViewOnClickListenerC0106a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3140g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayActivity f3141h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f3142i;

        /* renamed from: com.jd.lib.cashier.sdk.creditpay.dialog.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        class C0107a implements p.a {
            C0107a() {
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public void onRefresh() {
                ((CashierCreditPayViewModel) ViewModelProviders.of(ViewOnClickListenerC0106a.this.f3141h).get(CashierCreditPayViewModel.class)).j(ViewOnClickListenerC0106a.this.f3141h);
            }
        }

        ViewOnClickListenerC0106a(Dialog dialog, CashierCreditPayActivity cashierCreditPayActivity, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f3140g = dialog;
            this.f3141h = cashierCreditPayActivity;
            this.f3142i = cashierCommonPopConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3140g.dismiss();
            CashierCreditPayActivity cashierCreditPayActivity = this.f3141h;
            CashierCommonPopConfig cashierCommonPopConfig = this.f3142i;
            p.b(cashierCreditPayActivity, cashierCommonPopConfig.confirmBtnUrl, cashierCommonPopConfig.confirmOpType, new C0107a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3143g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayViewModel f3144h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayActivity f3145i;

        b(Dialog dialog, CashierCreditPayViewModel cashierCreditPayViewModel, CashierCreditPayActivity cashierCreditPayActivity) {
            this.f3143g = dialog;
            this.f3144h = cashierCreditPayViewModel;
            this.f3145i = cashierCreditPayActivity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3143g.dismiss();
            if (!TextUtils.isEmpty(this.f3144h.b().q)) {
                this.f3144h.j(this.f3145i);
            }
            com.jd.lib.cashier.sdk.e.c.a.j(this.f3145i, this.f3144h.b().d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3146g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayViewModel f3147h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayActivity f3148i;

        c(Dialog dialog, CashierCreditPayViewModel cashierCreditPayViewModel, CashierCreditPayActivity cashierCreditPayActivity) {
            this.f3146g = dialog;
            this.f3147h = cashierCreditPayViewModel;
            this.f3148i = cashierCreditPayActivity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3146g.dismiss();
            if (TextUtils.equals(this.f3147h.b().p, "1")) {
                p.m(this.f3148i);
            }
            com.jd.lib.cashier.sdk.e.c.a.k(this.f3148i, this.f3147h.b().d);
            this.f3148i.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class d implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3149g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayActivity f3150h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f3151i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayViewModel f3152j;

        /* renamed from: com.jd.lib.cashier.sdk.creditpay.dialog.a$d$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        class C0108a implements p.a {
            C0108a() {
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public void onRefresh() {
                d dVar = d.this;
                dVar.f3152j.j(dVar.f3150h);
            }
        }

        d(Dialog dialog, CashierCreditPayActivity cashierCreditPayActivity, CashierCommonPopConfig cashierCommonPopConfig, CashierCreditPayViewModel cashierCreditPayViewModel) {
            this.f3149g = dialog;
            this.f3150h = cashierCreditPayActivity;
            this.f3151i = cashierCommonPopConfig;
            this.f3152j = cashierCreditPayViewModel;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3149g.dismiss();
            CashierCreditPayActivity cashierCreditPayActivity = this.f3150h;
            CashierCommonPopConfig cashierCommonPopConfig = this.f3151i;
            p.b(cashierCreditPayActivity, cashierCommonPopConfig.cancelBtnUrl, cashierCommonPopConfig.cancelOpType, new C0108a());
            com.jd.lib.cashier.sdk.e.c.a.j(this.f3150h, this.f3152j.b().d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class e implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3153g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f3154h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayActivity f3155i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayViewModel f3156j;

        /* renamed from: com.jd.lib.cashier.sdk.creditpay.dialog.a$e$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        class C0109a implements p.a {
            C0109a() {
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public void onRefresh() {
                e eVar = e.this;
                eVar.f3156j.j(eVar.f3155i);
            }
        }

        e(Dialog dialog, CashierCommonPopConfig cashierCommonPopConfig, CashierCreditPayActivity cashierCreditPayActivity, CashierCreditPayViewModel cashierCreditPayViewModel) {
            this.f3153g = dialog;
            this.f3154h = cashierCommonPopConfig;
            this.f3155i = cashierCreditPayActivity;
            this.f3156j = cashierCreditPayViewModel;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3153g.dismiss();
            if (!TextUtils.isEmpty(this.f3154h.confirmOpType)) {
                CashierCreditPayActivity cashierCreditPayActivity = this.f3155i;
                CashierCommonPopConfig cashierCommonPopConfig = this.f3154h;
                p.b(cashierCreditPayActivity, cashierCommonPopConfig.confirmBtnUrl, cashierCommonPopConfig.confirmOpType, new C0109a());
            } else if (TextUtils.equals(this.f3156j.b().p, "1")) {
                p.m(this.f3155i);
            }
            com.jd.lib.cashier.sdk.e.c.a.k(this.f3155i, this.f3156j.b().d);
            this.f3155i.finish();
        }
    }

    /* loaded from: classes14.dex */
    class f implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3157g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayActivity f3158h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayViewModel f3159i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f3160j;

        f(Dialog dialog, CashierCreditPayActivity cashierCreditPayActivity, CashierCreditPayViewModel cashierCreditPayViewModel, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f3157g = dialog;
            this.f3158h = cashierCreditPayActivity;
            this.f3159i = cashierCreditPayViewModel;
            this.f3160j = cashierCommonPopConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3157g.dismiss();
            com.jd.lib.cashier.sdk.e.c.a.m(this.f3158h, this.f3159i.b().d);
            p.a(this.f3158h, this.f3160j.cancelBtnUrl);
            this.f3158h.finish();
        }
    }

    /* loaded from: classes14.dex */
    class g implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3161g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayActivity f3162h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayViewModel f3163i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f3164j;

        g(Dialog dialog, CashierCreditPayActivity cashierCreditPayActivity, CashierCreditPayViewModel cashierCreditPayViewModel, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f3161g = dialog;
            this.f3162h = cashierCreditPayActivity;
            this.f3163i = cashierCreditPayViewModel;
            this.f3164j = cashierCommonPopConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3161g.dismiss();
            com.jd.lib.cashier.sdk.e.c.a.n(this.f3162h, this.f3163i.b().d);
            if (!TextUtils.isEmpty(this.f3164j.confirmBtnUrl)) {
                p.a(this.f3162h, this.f3164j.confirmBtnUrl);
            } else if (TextUtils.equals(this.f3163i.b().p, "1")) {
                p.m(this.f3162h);
            }
            this.f3162h.finish();
        }
    }

    /* loaded from: classes14.dex */
    class h implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3165g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayActivity f3166h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f3167i;

        /* renamed from: com.jd.lib.cashier.sdk.creditpay.dialog.a$h$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        class C0110a implements p.a {
            C0110a() {
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public void onRefresh() {
                ((CashierCreditPayViewModel) ViewModelProviders.of(h.this.f3166h).get(CashierCreditPayViewModel.class)).j(h.this.f3166h);
            }
        }

        h(Dialog dialog, CashierCreditPayActivity cashierCreditPayActivity, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f3165g = dialog;
            this.f3166h = cashierCreditPayActivity;
            this.f3167i = cashierCommonPopConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3165g.dismiss();
            CashierCreditPayActivity cashierCreditPayActivity = this.f3166h;
            CashierCommonPopConfig cashierCommonPopConfig = this.f3167i;
            p.b(cashierCreditPayActivity, cashierCommonPopConfig.cancelBtnUrl, cashierCommonPopConfig.cancelOpType, new C0110a());
        }
    }

    /* loaded from: classes14.dex */
    class i implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3168g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayActivity f3169h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f3170i;

        /* renamed from: com.jd.lib.cashier.sdk.creditpay.dialog.a$i$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        class C0111a implements p.a {
            C0111a() {
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public void onRefresh() {
                ((CashierCreditPayViewModel) ViewModelProviders.of(i.this.f3169h).get(CashierCreditPayViewModel.class)).j(i.this.f3169h);
            }
        }

        i(Dialog dialog, CashierCreditPayActivity cashierCreditPayActivity, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f3168g = dialog;
            this.f3169h = cashierCreditPayActivity;
            this.f3170i = cashierCommonPopConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3168g.dismiss();
            CashierCreditPayActivity cashierCreditPayActivity = this.f3169h;
            CashierCommonPopConfig cashierCommonPopConfig = this.f3170i;
            p.b(cashierCreditPayActivity, cashierCommonPopConfig.confirmBtnUrl, cashierCommonPopConfig.confirmOpType, new C0111a());
        }
    }

    /* loaded from: classes14.dex */
    class j implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3171g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierCreditPayActivity f3172h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f3173i;

        /* renamed from: com.jd.lib.cashier.sdk.creditpay.dialog.a$j$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        class C0112a implements p.a {
            C0112a() {
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public void onRefresh() {
                ((CashierCreditPayViewModel) ViewModelProviders.of(j.this.f3172h).get(CashierCreditPayViewModel.class)).j(j.this.f3172h);
            }
        }

        j(Dialog dialog, CashierCreditPayActivity cashierCreditPayActivity, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f3171g = dialog;
            this.f3172h = cashierCreditPayActivity;
            this.f3173i = cashierCommonPopConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3171g.dismiss();
            CashierCreditPayActivity cashierCreditPayActivity = this.f3172h;
            CashierCommonPopConfig cashierCommonPopConfig = this.f3173i;
            p.b(cashierCreditPayActivity, cashierCommonPopConfig.cancelBtnUrl, cashierCommonPopConfig.cancelOpType, new C0112a());
        }
    }

    public static void a(CashierCreditPayActivity cashierCreditPayActivity, CashierCommonPopConfig cashierCommonPopConfig) {
        Dialog h2;
        if (!g0.a(cashierCreditPayActivity) || cashierCommonPopConfig == null) {
            return;
        }
        if ((TextUtils.isEmpty(cashierCommonPopConfig.cancelBtn) && TextUtils.isEmpty(cashierCommonPopConfig.confirmBtn)) || (h2 = com.jd.lib.cashier.sdk.core.utils.j.h(cashierCreditPayActivity, cashierCommonPopConfig.title, cashierCommonPopConfig.message, null, cashierCommonPopConfig.cancelBtn, cashierCommonPopConfig.confirmBtn)) == null) {
            return;
        }
        h2.setCancelable(cashierCommonPopConfig.cancelable);
        com.jd.lib.cashier.sdk.core.utils.j.k(h2, new h(h2, cashierCreditPayActivity, cashierCommonPopConfig));
        com.jd.lib.cashier.sdk.core.utils.j.l(h2, new i(h2, cashierCreditPayActivity, cashierCommonPopConfig));
        h2.show();
    }

    public static void b(CashierCreditPayActivity cashierCreditPayActivity) {
        if (g0.a(cashierCreditPayActivity)) {
            CashierCommonPopConfig cashierCommonPopConfig = new CashierCommonPopConfig();
            cashierCommonPopConfig.title = cashierCreditPayActivity.getString(R.string.lib_cashier_sdk_credit_pay_default_dialog_title);
            cashierCommonPopConfig.cancelBtn = cashierCreditPayActivity.getString(R.string.lib_cashier_sdk_credit_pay_default_dialog_cancel);
            String string = cashierCreditPayActivity.getString(R.string.lib_cashier_sdk_credit_pay_default_dialog_confirm);
            cashierCommonPopConfig.confirmBtn = string;
            Dialog g2 = com.jd.lib.cashier.sdk.core.utils.j.g(cashierCreditPayActivity, cashierCommonPopConfig.title, cashierCommonPopConfig.message, cashierCommonPopConfig.cancelBtn, string);
            if (g2 != null) {
                g2.setCancelable(cashierCommonPopConfig.cancelable);
                CashierCreditPayViewModel cashierCreditPayViewModel = (CashierCreditPayViewModel) ViewModelProviders.of(cashierCreditPayActivity).get(CashierCreditPayViewModel.class);
                com.jd.lib.cashier.sdk.core.utils.j.k(g2, new b(g2, cashierCreditPayViewModel, cashierCreditPayActivity));
                com.jd.lib.cashier.sdk.core.utils.j.l(g2, new c(g2, cashierCreditPayViewModel, cashierCreditPayActivity));
                g2.show();
                com.jd.lib.cashier.sdk.e.c.a.l(cashierCreditPayActivity, cashierCreditPayViewModel.b().d);
            }
        }
    }

    public static void c(CashierCreditPayActivity cashierCreditPayActivity, CashierCommonPopConfig cashierCommonPopConfig) {
        Dialog g2;
        if (!g0.a(cashierCreditPayActivity) || cashierCommonPopConfig == null) {
            return;
        }
        if ((TextUtils.isEmpty(cashierCommonPopConfig.cancelBtn) && TextUtils.isEmpty(cashierCommonPopConfig.confirmBtn)) || (g2 = com.jd.lib.cashier.sdk.core.utils.j.g(cashierCreditPayActivity, cashierCommonPopConfig.title, cashierCommonPopConfig.message, cashierCommonPopConfig.cancelBtn, cashierCommonPopConfig.confirmBtn)) == null) {
            return;
        }
        g2.setCancelable(cashierCommonPopConfig.cancelable);
        CashierCreditPayViewModel cashierCreditPayViewModel = (CashierCreditPayViewModel) ViewModelProviders.of(cashierCreditPayActivity).get(CashierCreditPayViewModel.class);
        com.jd.lib.cashier.sdk.core.utils.j.k(g2, new d(g2, cashierCreditPayActivity, cashierCommonPopConfig, cashierCreditPayViewModel));
        com.jd.lib.cashier.sdk.core.utils.j.l(g2, new e(g2, cashierCommonPopConfig, cashierCreditPayActivity, cashierCreditPayViewModel));
        g2.show();
        com.jd.lib.cashier.sdk.e.c.a.l(cashierCreditPayActivity, cashierCreditPayViewModel.b().d);
    }

    public static void d(CashierCreditPayActivity cashierCreditPayActivity, CashierCommonPopConfig cashierCommonPopConfig) {
        if (!g0.a(cashierCreditPayActivity) || cashierCommonPopConfig == null || TextUtils.isEmpty(cashierCommonPopConfig.cancelBtn) || TextUtils.isEmpty(cashierCommonPopConfig.confirmBtn)) {
            return;
        }
        Dialog g2 = com.jd.lib.cashier.sdk.core.utils.j.g(cashierCreditPayActivity, cashierCommonPopConfig.title, cashierCommonPopConfig.message, cashierCommonPopConfig.cancelBtn, cashierCommonPopConfig.confirmBtn);
        CashierCreditPayViewModel cashierCreditPayViewModel = (CashierCreditPayViewModel) ViewModelProviders.of(cashierCreditPayActivity).get(CashierCreditPayViewModel.class);
        if (g2 != null) {
            com.jd.lib.cashier.sdk.core.utils.j.k(g2, new f(g2, cashierCreditPayActivity, cashierCreditPayViewModel, cashierCommonPopConfig));
            com.jd.lib.cashier.sdk.core.utils.j.l(g2, new g(g2, cashierCreditPayActivity, cashierCreditPayViewModel, cashierCommonPopConfig));
            g2.show();
            com.jd.lib.cashier.sdk.e.c.a.g(cashierCreditPayActivity, cashierCreditPayViewModel.b().d);
        }
    }

    public static void e(CashierCreditPayActivity cashierCreditPayActivity, CashierCommonPopConfig cashierCommonPopConfig) {
        List<PlanRowEntity> list;
        if (!g0.a(cashierCreditPayActivity) || cashierCommonPopConfig == null) {
            return;
        }
        String str = cashierCommonPopConfig.title;
        String str2 = cashierCommonPopConfig.cancelBtn;
        String str3 = cashierCommonPopConfig.confirmBtn;
        PopBusinessMap popBusinessMap = cashierCommonPopConfig.businessMap;
        if (TextUtils.isEmpty(str) || popBusinessMap == null || (list = popBusinessMap.table) == null || list.isEmpty()) {
            return;
        }
        String str4 = popBusinessMap.tip;
        View inflate = LayoutInflater.from(cashierCreditPayActivity).inflate(R.layout.lib_cashier_sdk_dialog_plan_rate_custom_layout, (ViewGroup) null, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.lib_cashier_dialog_plan_rate_recycler);
        PlanPaymentRateAdapter planPaymentRateAdapter = new PlanPaymentRateAdapter(popBusinessMap.table);
        recyclerView.setLayoutManager(new LinearLayoutManager(cashierCreditPayActivity, 1, false));
        recyclerView.setAdapter(planPaymentRateAdapter);
        Dialog d2 = com.jd.lib.cashier.sdk.core.utils.j.d(cashierCreditPayActivity, str, str4, inflate, str2, str3);
        if (d2 != null) {
            d2.setCancelable(cashierCommonPopConfig.cancelable);
            com.jd.lib.cashier.sdk.core.utils.j.k(d2, new j(d2, cashierCreditPayActivity, cashierCommonPopConfig));
            com.jd.lib.cashier.sdk.core.utils.j.l(d2, new ViewOnClickListenerC0106a(d2, cashierCreditPayActivity, cashierCommonPopConfig));
            d2.show();
        }
    }
}
