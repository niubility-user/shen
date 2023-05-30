package com.jd.lib.cashier.sdk.creditpay.aac.impl;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.q;
import com.jd.lib.cashier.sdk.creditpay.aac.viewmodel.CashierCreditPayViewModel;
import com.jd.lib.cashier.sdk.creditpay.ui.CashierCreditPayErrorView;
import com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity;

/* loaded from: classes14.dex */
public class CreditPayStateImpl implements d, Observer<com.jd.lib.cashier.sdk.e.a.b.d> {

    /* renamed from: g  reason: collision with root package name */
    private ViewStub f3107g;

    /* renamed from: h  reason: collision with root package name */
    private CashierCreditPayActivity f3108h;

    /* renamed from: i  reason: collision with root package name */
    private ViewGroup f3109i;

    /* renamed from: j  reason: collision with root package name */
    private ViewGroup f3110j;

    /* renamed from: k  reason: collision with root package name */
    private CashierCreditPayErrorView f3111k;

    /* renamed from: l  reason: collision with root package name */
    private View f3112l;

    /* renamed from: m  reason: collision with root package name */
    private View f3113m;

    /* renamed from: n  reason: collision with root package name */
    private View f3114n;
    private final AlphaAnimation o = new AlphaAnimation(0.0f, 1.0f);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements Animation.AnimationListener {
        a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            if (CreditPayStateImpl.this.f3114n == null || CreditPayStateImpl.this.f3112l == null) {
                return;
            }
            CreditPayStateImpl.this.f3114n.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_7F000000));
            CreditPayStateImpl.this.f3112l.setBackgroundColor(0);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            if (CreditPayStateImpl.this.f3112l != null) {
                CreditPayStateImpl.this.f3112l.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_7F000000));
            }
        }
    }

    public CreditPayStateImpl(CashierCreditPayActivity cashierCreditPayActivity) {
        this.f3108h = cashierCreditPayActivity;
        n();
        onChangeSkin();
    }

    private void h() {
        ViewGroup viewGroup = this.f3110j;
        if (viewGroup != null) {
            viewGroup.setVisibility(8);
        }
    }

    private void l() {
        CashierCreditPayErrorView cashierCreditPayErrorView = this.f3111k;
        if (cashierCreditPayErrorView != null) {
            cashierCreditPayErrorView.setVisibility(8);
        }
    }

    private void m() {
        ViewGroup viewGroup = this.f3109i;
        if (viewGroup != null) {
            q.b(viewGroup);
        }
    }

    private void n() {
        CashierCreditPayActivity cashierCreditPayActivity = this.f3108h;
        if (cashierCreditPayActivity != null) {
            this.f3112l = cashierCreditPayActivity.findViewById(R.id.lib_cashier_credit_pay_blank_root);
            this.f3114n = this.f3108h.findViewById(R.id.lib_cashier_credit_pay_dialog_root);
            View findViewById = this.f3108h.findViewById(R.id.lib_cashier_credit_pay_authorize_page);
            CashierCreditPayActivity cashierCreditPayActivity2 = this.f3108h;
            int i2 = R.id.lib_cashier_credit_pay_authorize_root;
            this.f3113m = cashierCreditPayActivity2.findViewById(i2);
            this.f3107g = (ViewStub) findViewById.findViewById(R.id.lib_cashier_credit_pay_state_error_view_stub);
            this.f3109i = (ViewGroup) findViewById.findViewById(R.id.lib_cashier_credit_pay_state_loading);
            this.f3110j = (ViewGroup) findViewById.findViewById(i2);
        }
    }

    private void p() {
        ViewGroup viewGroup = this.f3110j;
        if (viewGroup != null) {
            viewGroup.setVisibility(0);
        }
    }

    private void q(String str) {
        if (this.f3111k == null) {
            this.f3111k = (CashierCreditPayErrorView) this.f3107g.inflate();
        }
        CashierCreditPayErrorView cashierCreditPayErrorView = this.f3111k;
        if (cashierCreditPayErrorView != null) {
            cashierCreditPayErrorView.setVisibility(0);
            this.f3111k.onShowErrorMsg(str);
        }
    }

    private void r() {
        ViewGroup viewGroup = this.f3109i;
        if (viewGroup == null || viewGroup.getVisibility() != 8) {
            return;
        }
        q.c(this.f3109i);
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            ((CashierCreditPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierCreditPayViewModel.class)).l().observe(fragmentActivity, this);
        }
    }

    @Override // com.jd.lib.cashier.sdk.creditpay.aac.impl.d
    public void g() {
        if (this.f3112l != null) {
            this.o.setDuration(200L);
            this.o.setAnimationListener(new a());
            this.f3112l.startAnimation(this.o);
        }
    }

    @Override // com.jd.lib.cashier.sdk.creditpay.aac.impl.d
    public void i() {
        if (this.f3112l == null || this.f3114n == null) {
            return;
        }
        this.o.cancel();
        this.o.setAnimationListener(null);
        this.f3114n.setBackgroundColor(0);
        this.f3112l.setBackgroundColor(0);
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: o  reason: merged with bridge method [inline-methods] */
    public void onChanged(com.jd.lib.cashier.sdk.e.a.b.d dVar) {
        if (dVar != null) {
            int i2 = dVar.a;
            if (i2 == 1024) {
                r();
                l();
                h();
                return;
            } else if (i2 == 2048) {
                m();
                h();
                q(dVar.b);
                return;
            } else if (i2 == 4096) {
                m();
                l();
                p();
                return;
            } else if (i2 == 6144) {
                g();
                return;
            } else if (i2 != 8192) {
                return;
            } else {
                i();
                return;
            }
        }
        m();
        h();
        q("");
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        if (this.f3113m != null) {
            if (JDDarkUtil.isDarkMode()) {
                this.f3113m.setBackgroundResource(R.drawable.lib_cashier_sdk_top_corner_dark_bg);
            } else {
                this.f3113m.setBackgroundResource(R.drawable.lib_cashier_sdk_top_corner_bg);
            }
        }
        CashierCreditPayErrorView cashierCreditPayErrorView = this.f3111k;
        if (cashierCreditPayErrorView != null) {
            cashierCreditPayErrorView.onHandSkinMode();
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        q.a(this.f3109i);
    }
}
