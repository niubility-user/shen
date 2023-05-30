package com.jd.lib.cashier.sdk.pay.dialog.k.e;

import android.app.Dialog;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.j;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class c implements com.jd.lib.cashier.sdk.pay.dialog.k.c {
    private final FragmentActivity a;

    /* loaded from: classes14.dex */
    static final class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3942g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f3943h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.pay.dialog.k.e.b f3944i;

        a(Dialog dialog, CashierCommonPopConfig cashierCommonPopConfig, c cVar, com.jd.lib.cashier.sdk.pay.dialog.k.e.b bVar, CashierCommonPopConfig cashierCommonPopConfig2) {
            this.f3942g = dialog;
            this.f3943h = cashierCommonPopConfig;
            this.f3944i = bVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (this.f3942g.isShowing()) {
                this.f3942g.dismiss();
            }
            com.jd.lib.cashier.sdk.pay.dialog.k.e.b bVar = this.f3944i;
            if (bVar != null) {
                String cancelBtnUrl = this.f3943h.cancelBtnUrl;
                Intrinsics.checkExpressionValueIsNotNull(cancelBtnUrl, "cancelBtnUrl");
                bVar.a(cancelBtnUrl);
            }
        }
    }

    /* loaded from: classes14.dex */
    static final class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3945g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierCommonPopConfig f3946h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ c f3947i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.pay.dialog.k.e.b f3948j;

        /* loaded from: classes14.dex */
        static final class a implements p.a {
            a() {
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public final void onRefresh() {
                com.jd.lib.cashier.sdk.b.i.d.a(b.this.f3947i.a);
            }
        }

        b(Dialog dialog, CashierCommonPopConfig cashierCommonPopConfig, c cVar, com.jd.lib.cashier.sdk.pay.dialog.k.e.b bVar, CashierCommonPopConfig cashierCommonPopConfig2) {
            this.f3945g = dialog;
            this.f3946h = cashierCommonPopConfig;
            this.f3947i = cVar;
            this.f3948j = bVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            if (this.f3945g.isShowing()) {
                this.f3945g.dismiss();
            }
            com.jd.lib.cashier.sdk.pay.dialog.k.e.b bVar = this.f3948j;
            String str = null;
            if (bVar != null) {
                String confirmBtnUrl = this.f3946h.confirmBtnUrl;
                Intrinsics.checkExpressionValueIsNotNull(confirmBtnUrl, "confirmBtnUrl");
                bVar.b(confirmBtnUrl, null);
            }
            if (!TextUtils.isEmpty(this.f3946h.confirmOpType)) {
                FragmentActivity fragmentActivity = this.f3947i.a;
                CashierCommonPopConfig cashierCommonPopConfig = this.f3946h;
                p.b(fragmentActivity, cashierCommonPopConfig.confirmBtnUrl, cashierCommonPopConfig.confirmOpType, new a());
                return;
            }
            FragmentActivity fragmentActivity2 = this.f3947i.a;
            if (!(fragmentActivity2 instanceof CashierPayActivity)) {
                fragmentActivity2 = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity2;
            if (cashierPayActivity != null && (x = cashierPayActivity.x()) != null && (b = x.b()) != null) {
                str = b.D;
            }
            if (TextUtils.equals(str, "1")) {
                p.m(this.f3947i.a);
            }
            this.f3947i.a.finish();
        }
    }

    public c(@NotNull FragmentActivity fragmentActivity) {
        this.a = fragmentActivity;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.k.c
    public void a(@NotNull CashierCommonPopConfig cashierCommonPopConfig, @Nullable com.jd.lib.cashier.sdk.pay.dialog.k.e.b bVar) {
        if (TextUtils.isEmpty(cashierCommonPopConfig.cancelBtn) || TextUtils.isEmpty(cashierCommonPopConfig.confirmBtn)) {
            return;
        }
        Dialog g2 = j.g(this.a, cashierCommonPopConfig.title, cashierCommonPopConfig.replacedMessage, cashierCommonPopConfig.cancelBtn, cashierCommonPopConfig.confirmBtn);
        j.k(g2, new a(g2, cashierCommonPopConfig, this, bVar, cashierCommonPopConfig));
        j.l(g2, new b(g2, cashierCommonPopConfig, this, bVar, cashierCommonPopConfig));
        SpannableString spannableString = cashierCommonPopConfig.highLightTitle;
        if (spannableString != null) {
            j.m(g2, spannableString);
        }
        g2.show();
    }
}
