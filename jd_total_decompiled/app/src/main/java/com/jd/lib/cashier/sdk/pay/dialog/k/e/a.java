package com.jd.lib.cashier.sdk.pay.dialog.k.e;

import android.app.Dialog;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.ExitRetainOptionEntity;
import com.jd.lib.cashier.sdk.core.utils.j;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public abstract class a implements com.jd.lib.cashier.sdk.pay.dialog.k.c {
    @Nullable
    private Dialog a;
    @Nullable
    private ExitRetainOptionEntity b;
    @NotNull

    /* renamed from: c */
    private final FragmentActivity f3934c;

    /* renamed from: com.jd.lib.cashier.sdk.pay.dialog.k.e.a$a */
    /* loaded from: classes14.dex */
    static final class ViewOnClickListenerC0141a implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ Dialog f3935g;

        /* renamed from: h */
        final /* synthetic */ CashierCommonPopConfig f3936h;

        /* renamed from: i */
        final /* synthetic */ com.jd.lib.cashier.sdk.pay.dialog.k.e.b f3937i;

        ViewOnClickListenerC0141a(Dialog dialog, CashierCommonPopConfig cashierCommonPopConfig, a aVar, com.jd.lib.cashier.sdk.pay.dialog.k.e.b bVar, CashierCommonPopConfig cashierCommonPopConfig2) {
            this.f3935g = dialog;
            this.f3936h = cashierCommonPopConfig;
            this.f3937i = bVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (this.f3935g.isShowing()) {
                this.f3935g.dismiss();
            }
            com.jd.lib.cashier.sdk.pay.dialog.k.e.b bVar = this.f3937i;
            if (bVar != null) {
                String cancelBtnUrl = this.f3936h.cancelBtnUrl;
                Intrinsics.checkExpressionValueIsNotNull(cancelBtnUrl, "cancelBtnUrl");
                bVar.a(cancelBtnUrl);
            }
        }
    }

    /* loaded from: classes14.dex */
    public static final class b implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ Dialog f3938g;

        /* renamed from: h */
        final /* synthetic */ CashierCommonPopConfig f3939h;

        /* renamed from: i */
        final /* synthetic */ a f3940i;

        /* renamed from: j */
        final /* synthetic */ com.jd.lib.cashier.sdk.pay.dialog.k.e.b f3941j;

        /* renamed from: com.jd.lib.cashier.sdk.pay.dialog.k.e.a$b$a */
        /* loaded from: classes14.dex */
        static final class C0142a implements p.a {
            C0142a() {
                b.this = r1;
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public final void onRefresh() {
                com.jd.lib.cashier.sdk.b.i.d.a(b.this.f3940i.c());
            }
        }

        b(Dialog dialog, CashierCommonPopConfig cashierCommonPopConfig, a aVar, com.jd.lib.cashier.sdk.pay.dialog.k.e.b bVar, CashierCommonPopConfig cashierCommonPopConfig2) {
            this.f3938g = dialog;
            this.f3939h = cashierCommonPopConfig;
            this.f3940i = aVar;
            this.f3941j = bVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            if (this.f3938g.isShowing()) {
                this.f3938g.dismiss();
            }
            com.jd.lib.cashier.sdk.pay.dialog.k.e.b bVar = this.f3941j;
            if (bVar != null) {
                String confirmBtnUrl = this.f3939h.confirmBtnUrl;
                Intrinsics.checkExpressionValueIsNotNull(confirmBtnUrl, "confirmBtnUrl");
                bVar.b(confirmBtnUrl, this.f3940i.e());
            }
            if (!TextUtils.isEmpty(this.f3939h.confirmOpType)) {
                FragmentActivity c2 = this.f3940i.c();
                CashierCommonPopConfig cashierCommonPopConfig = this.f3939h;
                p.b(c2, cashierCommonPopConfig.confirmBtnUrl, cashierCommonPopConfig.confirmOpType, new C0142a());
                return;
            }
            FragmentActivity c3 = this.f3940i.c();
            String str = null;
            if (!(c3 instanceof CashierPayActivity)) {
                c3 = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) c3;
            if (cashierPayActivity != null && (x = cashierPayActivity.x()) != null && (b = x.b()) != null) {
                str = b.D;
            }
            if (TextUtils.equals(str, "1")) {
                p.m(this.f3940i.c());
            }
            this.f3940i.c().finish();
        }
    }

    public a(@NotNull FragmentActivity fragmentActivity) {
        this.f3934c = fragmentActivity;
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.k.c
    public void a(@NotNull CashierCommonPopConfig cashierCommonPopConfig, @Nullable com.jd.lib.cashier.sdk.pay.dialog.k.e.b bVar) {
        if (cashierCommonPopConfig.businessMap == null || TextUtils.isEmpty(cashierCommonPopConfig.cancelBtn) || TextUtils.isEmpty(cashierCommonPopConfig.confirmBtn)) {
            return;
        }
        Dialog h2 = j.h(this.f3934c, cashierCommonPopConfig.title, "", b(cashierCommonPopConfig), cashierCommonPopConfig.cancelBtn, cashierCommonPopConfig.confirmBtn);
        this.a = h2;
        j.k(h2, new ViewOnClickListenerC0141a(h2, cashierCommonPopConfig, this, bVar, cashierCommonPopConfig));
        j.l(h2, new b(h2, cashierCommonPopConfig, this, bVar, cashierCommonPopConfig));
        SpannableString spannableString = cashierCommonPopConfig.highLightTitle;
        if (spannableString != null) {
            j.m(h2, spannableString);
        }
        f();
        h2.show();
    }

    @Nullable
    public abstract View b(@NotNull CashierCommonPopConfig cashierCommonPopConfig);

    @NotNull
    public final FragmentActivity c() {
        return this.f3934c;
    }

    @Nullable
    public final Dialog d() {
        return this.a;
    }

    @Nullable
    protected final ExitRetainOptionEntity e() {
        return this.b;
    }

    public void f() {
    }

    public final void g(@Nullable ExitRetainOptionEntity exitRetainOptionEntity) {
        this.b = exitRetainOptionEntity;
    }
}
