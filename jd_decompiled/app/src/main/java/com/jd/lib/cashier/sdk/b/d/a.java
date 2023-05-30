package com.jd.lib.cashier.sdk.b.d;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.common.bean.GraduallyPayPopup;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.ExitRetainOptionEntity;
import com.jd.lib.cashier.sdk.core.model.PopBusinessMap;
import com.jd.lib.cashier.sdk.core.model.PrismResult;
import com.jd.lib.cashier.sdk.core.ui.widget.AbsCountdownView;
import com.jd.lib.cashier.sdk.core.ui.widget.MSCountDownView;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class a {

    /* renamed from: com.jd.lib.cashier.sdk.b.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    class ViewOnClickListenerC0095a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f2807g;

        ViewOnClickListenerC0095a(Dialog dialog) {
            this.f2807g = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2807g.isShowing()) {
                this.f2807g.dismiss();
            }
        }
    }

    /* loaded from: classes14.dex */
    class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f2808g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f2809h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.d.g.g.f f2810i;

        b(Dialog dialog, FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.g.f fVar) {
            this.f2808g = dialog;
            this.f2809h = fragmentActivity;
            this.f2810i = fVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2808g.isShowing()) {
                this.f2808g.dismiss();
            }
            com.jd.lib.cashier.sdk.d.g.g.e.c().b(this.f2809h, this.f2810i);
        }
    }

    /* loaded from: classes14.dex */
    class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f2811g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.b.d.b.a f2812h;

        c(Dialog dialog, com.jd.lib.cashier.sdk.b.d.b.a aVar) {
            this.f2811g = dialog;
            this.f2812h = aVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2811g.isShowing()) {
                this.f2811g.dismiss();
            }
            com.jd.lib.cashier.sdk.b.d.b.a aVar = this.f2812h;
            if (aVar != null) {
                aVar.a(null);
            }
        }
    }

    /* loaded from: classes14.dex */
    class d implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f2813g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.b.d.b.a f2814h;

        d(Dialog dialog, com.jd.lib.cashier.sdk.b.d.b.a aVar) {
            this.f2813g = dialog;
            this.f2814h = aVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2813g.isShowing()) {
                this.f2813g.dismiss();
            }
            com.jd.lib.cashier.sdk.b.d.b.a aVar = this.f2814h;
            if (aVar != null) {
                aVar.b(null);
            }
        }
    }

    /* loaded from: classes14.dex */
    class e implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f2815g;

        e(Dialog dialog) {
            this.f2815g = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2815g.isShowing()) {
                this.f2815g.dismiss();
            }
        }
    }

    /* loaded from: classes14.dex */
    class f implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f2816g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.b.d.b.a f2817h;

        f(Dialog dialog, com.jd.lib.cashier.sdk.b.d.b.a aVar) {
            this.f2816g = dialog;
            this.f2817h = aVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2816g.isShowing()) {
                this.f2816g.dismiss();
            }
            com.jd.lib.cashier.sdk.b.d.b.a aVar = this.f2817h;
            if (aVar != null) {
                aVar.b(null);
            }
        }
    }

    /* loaded from: classes14.dex */
    class g implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f2818g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Context f2819h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f2820i;

        /* renamed from: com.jd.lib.cashier.sdk.b.d.a$g$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        class C0096a implements p.a {
            C0096a() {
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public void onRefresh() {
                com.jd.lib.cashier.sdk.b.i.d.a(g.this.f2819h);
            }
        }

        g(Dialog dialog, Context context, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f2818g = dialog;
            this.f2819h = context;
            this.f2820i = cashierCommonPopConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f2818g.dismiss();
            Context context = this.f2819h;
            CashierCommonPopConfig cashierCommonPopConfig = this.f2820i;
            com.jd.lib.cashier.sdk.core.utils.p.b(context, cashierCommonPopConfig.cancelBtnUrl, cashierCommonPopConfig.cancelOpType, new C0096a());
        }
    }

    /* loaded from: classes14.dex */
    class h implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f2821g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Context f2822h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f2823i;

        /* renamed from: com.jd.lib.cashier.sdk.b.d.a$h$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        class C0097a implements p.a {
            C0097a() {
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public void onRefresh() {
                com.jd.lib.cashier.sdk.b.i.d.a(h.this.f2822h);
            }
        }

        h(Dialog dialog, Context context, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f2821g = dialog;
            this.f2822h = context;
            this.f2823i = cashierCommonPopConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f2821g.dismiss();
            Context context = this.f2822h;
            CashierCommonPopConfig cashierCommonPopConfig = this.f2823i;
            com.jd.lib.cashier.sdk.core.utils.p.b(context, cashierCommonPopConfig.confirmBtnUrl, cashierCommonPopConfig.confirmOpType, new C0097a());
            com.jd.lib.cashier.sdk.h.e.a.d().t(this.f2822h, this.f2823i.businessMap);
        }
    }

    /* loaded from: classes14.dex */
    class i implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f2824g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f2825h;

        /* renamed from: com.jd.lib.cashier.sdk.b.d.a$i$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        class ViewOnClickListenerC0098a implements View.OnClickListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ Dialog f2826g;

            ViewOnClickListenerC0098a(i iVar, Dialog dialog) {
                this.f2826g = dialog;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.f2826g.cancel();
            }
        }

        i(FragmentActivity fragmentActivity, String str) {
            this.f2824g = fragmentActivity;
            this.f2825h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentActivity fragmentActivity = this.f2824g;
            Dialog c2 = com.jd.lib.cashier.sdk.core.utils.j.c(fragmentActivity, this.f2825h, fragmentActivity.getString(R.string.lib_cashier_sdk_pay_common_dialog_btn_ok_txt));
            if (c2 != null) {
                com.jd.lib.cashier.sdk.core.utils.j.l(c2, new ViewOnClickListenerC0098a(this, c2));
                c2.show();
            }
        }
    }

    /* loaded from: classes14.dex */
    class j implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f2827g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f2828h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ String f2829i;

        /* renamed from: com.jd.lib.cashier.sdk.b.d.a$j$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        class ViewOnClickListenerC0099a implements View.OnClickListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ Dialog f2830g;

            ViewOnClickListenerC0099a(j jVar, Dialog dialog) {
                this.f2830g = dialog;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.f2830g.cancel();
            }
        }

        j(FragmentActivity fragmentActivity, String str, String str2) {
            this.f2827g = fragmentActivity;
            this.f2828h = str;
            this.f2829i = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Dialog c2 = com.jd.lib.cashier.sdk.core.utils.j.c(this.f2827g, this.f2828h, this.f2829i);
            if (c2 != null) {
                com.jd.lib.cashier.sdk.core.utils.j.l(c2, new ViewOnClickListenerC0099a(this, c2));
                c2.show();
            }
        }
    }

    /* loaded from: classes14.dex */
    class k implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f2831g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.b.d.b.a f2832h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ GraduallyPayPopup f2833i;

        k(Dialog dialog, com.jd.lib.cashier.sdk.b.d.b.a aVar, GraduallyPayPopup graduallyPayPopup) {
            this.f2831g = dialog;
            this.f2832h = aVar;
            this.f2833i = graduallyPayPopup;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2831g.isShowing()) {
                this.f2831g.dismiss();
            }
            com.jd.lib.cashier.sdk.b.d.b.a aVar = this.f2832h;
            if (aVar != null) {
                aVar.a(this.f2833i.viewOrderUrl);
            }
        }
    }

    /* loaded from: classes14.dex */
    static /* synthetic */ class l {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[com.jd.lib.cashier.sdk.d.g.g.f.values().length];
            a = iArr;
            try {
                iArr[com.jd.lib.cashier.sdk.d.g.g.f.QQWALLET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[com.jd.lib.cashier.sdk.d.g.g.f.WEIXIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[com.jd.lib.cashier.sdk.d.g.g.f.UNIONPAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[com.jd.lib.cashier.sdk.d.g.g.f.JDPAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[com.jd.lib.cashier.sdk.d.g.g.f.OCTOPUS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[com.jd.lib.cashier.sdk.d.g.g.f.CYBERMONEY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[com.jd.lib.cashier.sdk.d.g.g.f.MEDICALPAY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes14.dex */
    class m implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f2834g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.b.d.b.a f2835h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ GraduallyPayPopup f2836i;

        m(Dialog dialog, com.jd.lib.cashier.sdk.b.d.b.a aVar, GraduallyPayPopup graduallyPayPopup) {
            this.f2834g = dialog;
            this.f2835h = aVar;
            this.f2836i = graduallyPayPopup;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2834g.isShowing()) {
                this.f2834g.dismiss();
            }
            com.jd.lib.cashier.sdk.b.d.b.a aVar = this.f2835h;
            if (aVar != null) {
                aVar.b(this.f2836i.continueToPay);
            }
        }
    }

    /* loaded from: classes14.dex */
    class n implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f2837g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.b.d.b.c f2838h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f2839i;

        n(Dialog dialog, com.jd.lib.cashier.sdk.b.d.b.c cVar, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f2837g = dialog;
            this.f2838h = cVar;
            this.f2839i = cashierCommonPopConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2837g.isShowing()) {
                this.f2837g.dismiss();
            }
            com.jd.lib.cashier.sdk.b.d.b.c cVar = this.f2838h;
            if (cVar != null) {
                CashierCommonPopConfig cashierCommonPopConfig = this.f2839i;
                cVar.b(cashierCommonPopConfig.cancelOpType, cashierCommonPopConfig.cancelBtnUrl);
            }
        }
    }

    /* loaded from: classes14.dex */
    class o implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f2840g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.b.d.b.c f2841h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f2842i;

        o(Dialog dialog, com.jd.lib.cashier.sdk.b.d.b.c cVar, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f2840g = dialog;
            this.f2841h = cVar;
            this.f2842i = cashierCommonPopConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f2840g.isShowing()) {
                this.f2840g.dismiss();
            }
            com.jd.lib.cashier.sdk.b.d.b.c cVar = this.f2841h;
            if (cVar != null) {
                CashierCommonPopConfig cashierCommonPopConfig = this.f2842i;
                cVar.a(cashierCommonPopConfig.confirmOpType, cashierCommonPopConfig.confirmBtnUrl);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class p implements com.jd.lib.cashier.sdk.pay.dialog.k.e.b {
        final /* synthetic */ CashierPayActivity a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f2843c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f2844e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f2845f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f2846g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f2847h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ String f2848i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ String f2849j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ String f2850k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ String f2851l;

        p(CashierPayActivity cashierPayActivity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
            this.a = cashierPayActivity;
            this.b = str;
            this.f2843c = str2;
            this.d = str3;
            this.f2844e = str4;
            this.f2845f = str5;
            this.f2846g = str6;
            this.f2847h = str7;
            this.f2848i = str8;
            this.f2849j = str9;
            this.f2850k = str10;
            this.f2851l = str11;
        }

        @Override // com.jd.lib.cashier.sdk.pay.dialog.k.e.b
        public void a(String str) {
            com.jd.lib.cashier.sdk.h.e.a.d().i(this.a, this.b, this.f2843c, this.d, this.f2844e, this.f2845f, this.f2846g, this.f2847h, this.f2848i);
        }

        @Override // com.jd.lib.cashier.sdk.pay.dialog.k.e.b
        public void b(String str, ExitRetainOptionEntity exitRetainOptionEntity) {
            String str2 = exitRetainOptionEntity != null ? exitRetainOptionEntity.btnEventParam : "";
            com.jd.lib.cashier.sdk.core.utils.r.b("CashierDialogFactory", "retainReason = " + str2);
            com.jd.lib.cashier.sdk.h.e.a.d().k(this.a, this.b, this.f2843c, str2, this.f2849j, this.f2850k, this.f2844e, this.d, this.f2845f, this.f2846g, this.f2847h, this.f2851l, this.f2848i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class q implements AbsCountdownView.a {
        final /* synthetic */ Dialog a;

        q(Dialog dialog) {
            this.a = dialog;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountdownView.a
        public void onFinish() {
            Dialog dialog = this.a;
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class r implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MSCountDownView f2852g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Dialog f2853h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierPayActivity f2854i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ String f2855j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ String f2856k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ String f2857l;

        /* renamed from: m  reason: collision with root package name */
        final /* synthetic */ String f2858m;

        /* renamed from: n  reason: collision with root package name */
        final /* synthetic */ String f2859n;
        final /* synthetic */ String o;
        final /* synthetic */ String p;
        final /* synthetic */ String q;

        r(MSCountDownView mSCountDownView, Dialog dialog, CashierPayActivity cashierPayActivity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
            this.f2852g = mSCountDownView;
            this.f2853h = dialog;
            this.f2854i = cashierPayActivity;
            this.f2855j = str;
            this.f2856k = str2;
            this.f2857l = str3;
            this.f2858m = str4;
            this.f2859n = str5;
            this.o = str6;
            this.p = str7;
            this.q = str8;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MSCountDownView mSCountDownView = this.f2852g;
            if (mSCountDownView != null) {
                mSCountDownView.p();
            }
            Dialog dialog = this.f2853h;
            if (dialog != null) {
                dialog.dismiss();
            }
            com.jd.lib.cashier.sdk.h.e.a.d().i(this.f2854i, this.f2855j, this.f2856k, this.f2857l, this.f2858m, this.f2859n, this.o, this.p, this.q);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class s implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MSCountDownView f2860g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Dialog f2861h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CashierPayActivity f2862i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ String f2863j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ String f2864k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ String f2865l;

        /* renamed from: m  reason: collision with root package name */
        final /* synthetic */ String f2866m;

        /* renamed from: n  reason: collision with root package name */
        final /* synthetic */ String f2867n;
        final /* synthetic */ String o;
        final /* synthetic */ String p;
        final /* synthetic */ String q;
        final /* synthetic */ String r;
        final /* synthetic */ String s;
        final /* synthetic */ String t;
        final /* synthetic */ CashierCommonPopConfig u;

        /* renamed from: com.jd.lib.cashier.sdk.b.d.a$s$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        class C0100a implements p.a {
            C0100a() {
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public void onRefresh() {
                com.jd.lib.cashier.sdk.b.i.d.a(s.this.f2862i);
            }
        }

        s(MSCountDownView mSCountDownView, Dialog dialog, CashierPayActivity cashierPayActivity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f2860g = mSCountDownView;
            this.f2861h = dialog;
            this.f2862i = cashierPayActivity;
            this.f2863j = str;
            this.f2864k = str2;
            this.f2865l = str3;
            this.f2866m = str4;
            this.f2867n = str5;
            this.o = str6;
            this.p = str7;
            this.q = str8;
            this.r = str9;
            this.s = str10;
            this.t = str11;
            this.u = cashierCommonPopConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MSCountDownView mSCountDownView = this.f2860g;
            if (mSCountDownView != null) {
                mSCountDownView.p();
            }
            Dialog dialog = this.f2861h;
            if (dialog != null) {
                dialog.dismiss();
            }
            com.jd.lib.cashier.sdk.h.e.a.d().k(this.f2862i, this.f2863j, this.f2864k, "", this.f2865l, this.f2866m, this.f2867n, this.o, this.p, this.q, this.r, this.s, this.t);
            if (!TextUtils.isEmpty(this.u.confirmOpType)) {
                CashierPayActivity cashierPayActivity = this.f2862i;
                CashierCommonPopConfig cashierCommonPopConfig = this.u;
                com.jd.lib.cashier.sdk.core.utils.p.b(cashierPayActivity, cashierCommonPopConfig.confirmBtnUrl, cashierCommonPopConfig.confirmOpType, new C0100a());
                return;
            }
            if (TextUtils.equals(((CashierPayViewModel) ViewModelProviders.of(this.f2862i).get(CashierPayViewModel.class)).b().D, "1")) {
                com.jd.lib.cashier.sdk.core.utils.p.m(this.f2862i);
            }
            this.f2862i.finish();
        }
    }

    /* loaded from: classes14.dex */
    class t implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f2868g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f2869h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.b.d.b.b f2870i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f2871j;

        /* renamed from: com.jd.lib.cashier.sdk.b.d.a$t$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        class C0101a implements p.a {
            C0101a() {
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public void onRefresh() {
                com.jd.lib.cashier.sdk.b.i.d.a(t.this.f2871j);
            }
        }

        t(Dialog dialog, CashierCommonPopConfig cashierCommonPopConfig, com.jd.lib.cashier.sdk.b.d.b.b bVar, FragmentActivity fragmentActivity) {
            this.f2868g = dialog;
            this.f2869h = cashierCommonPopConfig;
            this.f2870i = bVar;
            this.f2871j = fragmentActivity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Dialog dialog = this.f2868g;
            if (dialog != null) {
                dialog.dismiss();
            }
            if (TextUtils.isEmpty(this.f2869h.confirmOpType)) {
                com.jd.lib.cashier.sdk.b.d.b.b bVar = this.f2870i;
                if (bVar != null) {
                    bVar.a(this.f2869h.confirmBtnUrl);
                    return;
                }
                return;
            }
            FragmentActivity fragmentActivity = this.f2871j;
            CashierCommonPopConfig cashierCommonPopConfig = this.f2869h;
            com.jd.lib.cashier.sdk.core.utils.p.b(fragmentActivity, cashierCommonPopConfig.confirmBtnUrl, cashierCommonPopConfig.confirmOpType, new C0101a());
        }
    }

    public static void a(Context context, com.jd.lib.cashier.sdk.b.d.b.a aVar) {
        Dialog e2 = com.jd.lib.cashier.sdk.core.utils.j.e(context, context.getString(R.string.lib_cashier_sdk_octopus_pay_other_message), context.getString(R.string.lib_cashier_sdk_octopus_pay_other_left_btn_text), context.getString(R.string.lib_cashier_sdk_octopus_pay_other_right_btn_text));
        if (e2 != null) {
            com.jd.lib.cashier.sdk.core.utils.j.k(e2, new c(e2, aVar));
            com.jd.lib.cashier.sdk.core.utils.j.l(e2, new d(e2, aVar));
            e2.show();
        }
    }

    public static void b(FragmentActivity fragmentActivity, CashierCommonPopConfig cashierCommonPopConfig, com.jd.lib.cashier.sdk.b.d.b.b bVar) {
        if (!g0.a(fragmentActivity) || cashierCommonPopConfig == null) {
            return;
        }
        Dialog f2 = com.jd.lib.cashier.sdk.core.utils.j.f(fragmentActivity, !TextUtils.isEmpty(cashierCommonPopConfig.title) ? cashierCommonPopConfig.title : fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_countdown_dialog_title), !TextUtils.isEmpty(cashierCommonPopConfig.message) ? cashierCommonPopConfig.message : fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_countdown_dialog_message), !TextUtils.isEmpty(cashierCommonPopConfig.confirmBtn) ? cashierCommonPopConfig.confirmBtn : fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_countdown_dialog_btn_text));
        if (f2 != null) {
            f2.setCancelable(false);
            f2.setCanceledOnTouchOutside(false);
            com.jd.lib.cashier.sdk.core.utils.j.k(f2, new t(f2, cashierCommonPopConfig, bVar, fragmentActivity));
            f2.show();
        }
    }

    public static void c(Context context, CashierCommonPopConfig cashierCommonPopConfig) {
        Dialog h2;
        if (cashierCommonPopConfig == null || context == null) {
            return;
        }
        if ((TextUtils.isEmpty(cashierCommonPopConfig.cancelBtn) && TextUtils.isEmpty(cashierCommonPopConfig.confirmBtn)) || (h2 = com.jd.lib.cashier.sdk.core.utils.j.h(context, cashierCommonPopConfig.title, cashierCommonPopConfig.message, null, cashierCommonPopConfig.cancelBtn, cashierCommonPopConfig.confirmBtn)) == null) {
            return;
        }
        h2.setCancelable(cashierCommonPopConfig.cancelable);
        com.jd.lib.cashier.sdk.core.utils.j.k(h2, new g(h2, context, cashierCommonPopConfig));
        com.jd.lib.cashier.sdk.core.utils.j.l(h2, new h(h2, context, cashierCommonPopConfig));
        if (context == null || h2.isShowing()) {
            return;
        }
        h2.show();
    }

    public static void d(CashierPayActivity cashierPayActivity, CashierCommonPopConfig cashierCommonPopConfig, String str, String str2, String str3) {
        PrismResult prismResult;
        if (!g0.a(cashierPayActivity) || cashierCommonPopConfig == null) {
            return;
        }
        PopBusinessMap popBusinessMap = cashierCommonPopConfig.businessMap;
        String str4 = (popBusinessMap == null || (prismResult = popBusinessMap.abTest) == null || TextUtils.isEmpty(prismResult.touchstone_expids)) ? "" : cashierCommonPopConfig.businessMap.abTest.touchstone_expids;
        String str5 = cashierCommonPopConfig.highLightString;
        PopBusinessMap popBusinessMap2 = cashierCommonPopConfig.businessMap;
        String str6 = popBusinessMap2 != null ? popBusinessMap2.leaveto : "";
        String str7 = popBusinessMap2 != null ? popBusinessMap2.popType : "";
        String str8 = popBusinessMap2 != null ? popBusinessMap2.touchstoneExpIds : "";
        String str9 = popBusinessMap2 != null ? popBusinessMap2.recomInfo : "";
        String str10 = popBusinessMap2 != null ? popBusinessMap2.newUserGeneralized : "";
        String str11 = ((CashierPayViewModel) ViewModelProviders.of(cashierPayActivity).get(CashierPayViewModel.class)).b().f3511e;
        String str12 = str7;
        com.jd.lib.cashier.sdk.pay.dialog.k.b.a(cashierPayActivity, cashierCommonPopConfig, new p(cashierPayActivity, str11, str, str12, str5, str8, str9, str10, str4, str2, str3, str6));
        com.jd.lib.cashier.sdk.h.e.a.d().U(cashierPayActivity, str11, str5, str12, str8, str9, str10, str4);
    }

    public static void e(FragmentActivity fragmentActivity, GraduallyPayPopup graduallyPayPopup, com.jd.lib.cashier.sdk.b.d.b.a aVar) {
        Dialog g2;
        if (!g0.a(fragmentActivity) || graduallyPayPopup == null) {
            return;
        }
        String str = graduallyPayPopup.paySuccess;
        String str2 = graduallyPayPopup.payRemainTitle + graduallyPayPopup.payRemain;
        String str3 = graduallyPayPopup.viewOrder;
        String str4 = graduallyPayPopup.continueToPay;
        if ((TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) || (g2 = com.jd.lib.cashier.sdk.core.utils.j.g(fragmentActivity, str, str2, str3, str4)) == null) {
            return;
        }
        com.jd.lib.cashier.sdk.core.utils.j.k(g2, new k(g2, aVar, graduallyPayPopup));
        com.jd.lib.cashier.sdk.core.utils.j.l(g2, new m(g2, aVar, graduallyPayPopup));
        g2.show();
    }

    public static void f(FragmentActivity fragmentActivity, String str) {
        if (fragmentActivity == null || TextUtils.isEmpty(str)) {
            return;
        }
        fragmentActivity.runOnUiThread(new i(fragmentActivity, str));
    }

    public static void g(FragmentActivity fragmentActivity, String str, String str2) {
        if (fragmentActivity == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = fragmentActivity.getString(R.string.lib_cashier_sdk_pay_common_dialog_btn_ok_txt);
        }
        fragmentActivity.runOnUiThread(new j(fragmentActivity, str, str2));
    }

    public static void h(Context context, com.jd.lib.cashier.sdk.b.d.b.a aVar) {
        Dialog g2 = com.jd.lib.cashier.sdk.core.utils.j.g(context, context.getResources().getString(R.string.lib_cashier_sdk_octopus_pay_exchange_title), context.getResources().getString(R.string.lib_cashier_sdk_please_retry), context.getResources().getString(R.string.lib_cashier_sdk_cancel), context.getResources().getString(R.string.lib_cashier_sdk_retry));
        if (g2 != null) {
            com.jd.lib.cashier.sdk.core.utils.j.k(g2, new e(g2));
            com.jd.lib.cashier.sdk.core.utils.j.l(g2, new f(g2, aVar));
            g2.show();
        }
    }

    public static void i(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.g.f fVar) {
        String string = fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_pay_failure);
        switch (l.a[fVar.ordinal()]) {
            case 1:
                string = fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_pay_qq_failure);
                break;
            case 2:
                string = fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_pay_wx_failure);
                break;
            case 3:
                string = fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_pay_union_failure);
                break;
            case 4:
                string = fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_pay_jd_failure);
                break;
            case 5:
                string = fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_pay_octopus_failure);
                break;
            case 6:
                string = fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_pay_cyber_failure);
                break;
            case 7:
                string = fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_pay_medical_failure);
                break;
        }
        Dialog e2 = com.jd.lib.cashier.sdk.core.utils.j.e(fragmentActivity, string, fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_cancel), fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_retry));
        if (e2 != null) {
            com.jd.lib.cashier.sdk.core.utils.j.k(e2, new ViewOnClickListenerC0095a(e2));
            com.jd.lib.cashier.sdk.core.utils.j.l(e2, new b(e2, fragmentActivity, fVar));
            e2.show();
        }
    }

    public static void j(FragmentActivity fragmentActivity, @Nullable CashierCommonPopConfig cashierCommonPopConfig) {
        if (!g0.a(fragmentActivity) || cashierCommonPopConfig == null) {
            return;
        }
        new com.jd.lib.cashier.sdk.pay.dialog.h(fragmentActivity).c(cashierCommonPopConfig);
    }

    public static void k(FragmentActivity fragmentActivity, CashierCommonPopConfig cashierCommonPopConfig, com.jd.lib.cashier.sdk.b.d.b.c cVar) {
        Dialog g2;
        if (!g0.a(fragmentActivity) || cashierCommonPopConfig == null || TextUtils.isEmpty(cashierCommonPopConfig.cancelBtn) || TextUtils.isEmpty(cashierCommonPopConfig.confirmBtn) || (g2 = com.jd.lib.cashier.sdk.core.utils.j.g(fragmentActivity, cashierCommonPopConfig.title, cashierCommonPopConfig.message, cashierCommonPopConfig.cancelBtn, cashierCommonPopConfig.confirmBtn)) == null) {
            return;
        }
        com.jd.lib.cashier.sdk.core.utils.j.k(g2, new n(g2, cVar, cashierCommonPopConfig));
        com.jd.lib.cashier.sdk.core.utils.j.l(g2, new o(g2, cVar, cashierCommonPopConfig));
        g2.show();
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0217  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void l(com.jd.lib.cashier.sdk.pay.view.CashierPayActivity r30, com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig r31, java.lang.String r32, java.lang.String r33, java.lang.String r34) {
        /*
            Method dump skipped, instructions count: 563
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.b.d.a.l(com.jd.lib.cashier.sdk.pay.view.CashierPayActivity, com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig, java.lang.String, java.lang.String, java.lang.String):void");
    }
}
