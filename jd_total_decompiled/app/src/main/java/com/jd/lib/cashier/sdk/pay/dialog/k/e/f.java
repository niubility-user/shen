package com.jd.lib.cashier.sdk.pay.dialog.k.e;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.GuideInfo;
import com.jd.lib.cashier.sdk.core.model.PopBusinessMap;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class f extends com.jd.lib.cashier.sdk.pay.dialog.k.e.a {

    /* loaded from: classes14.dex */
    static final class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ PopBusinessMap f3949g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ f f3950h;

        /* renamed from: com.jd.lib.cashier.sdk.pay.dialog.k.e.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        static final class C0143a implements p.a {
            C0143a() {
            }

            @Override // com.jd.lib.cashier.sdk.core.utils.p.a
            public final void onRefresh() {
                com.jd.lib.cashier.sdk.b.i.d.a(a.this.f3950h.c());
            }
        }

        a(PopBusinessMap popBusinessMap, f fVar, CashierCommonPopConfig cashierCommonPopConfig) {
            this.f3949g = popBusinessMap;
            this.f3950h = fVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            com.jd.lib.cashier.sdk.h.e.a.d().o(this.f3950h.c());
            Dialog d = this.f3950h.d();
            if (d != null) {
                d.dismiss();
            }
            FragmentActivity c2 = this.f3950h.c();
            GuideInfo guideInfo = this.f3949g.guideInfo;
            p.b(c2, guideInfo != null ? guideInfo.guideUrl : null, guideInfo != null ? guideInfo.guideOpType : null, new C0143a());
        }
    }

    public f(@NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.k.e.a
    @Nullable
    public View b(@NotNull CashierCommonPopConfig cashierCommonPopConfig) {
        String str;
        try {
            View inflate = LayoutInflater.from(c()).inflate(R.layout.lib_cashier_sdk_dialog_exit_xjk, (ViewGroup) null, false);
            TextView textView = (TextView) inflate.findViewById(R.id.lib_cashier_exit_xjk_dialog_subtitle);
            if (textView != null) {
                textView.setText(cashierCommonPopConfig.replacedMessage);
            }
            TextView textView2 = (TextView) inflate.findViewById(R.id.lib_cashier_exit_xjk_dialog_message);
            if (textView2 != null) {
                textView2.setText(cashierCommonPopConfig.message);
            }
            PopBusinessMap popBusinessMap = cashierCommonPopConfig.businessMap;
            TextView textView3 = (TextView) inflate.findViewById(R.id.lib_cashier_exit_xjk_dialog_action);
            if (textView3 != null) {
                GuideInfo guideInfo = popBusinessMap.guideInfo;
                if (guideInfo == null || (str = guideInfo.guideTitle) == null) {
                    str = "";
                }
                textView3.setText(str);
                if (textView3 != null) {
                    textView3.setOnClickListener(new a(popBusinessMap, this, cashierCommonPopConfig));
                }
            }
            return inflate;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.pay.dialog.k.e.a
    public void f() {
        super.f();
        FragmentActivity c2 = c();
        if (!(c2 instanceof CashierPayActivity)) {
            c2 = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) c2;
        if (cashierPayActivity != null) {
            com.jd.lib.cashier.sdk.h.e.a.d().l0(cashierPayActivity);
        }
    }
}
