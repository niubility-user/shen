package com.jd.lib.cashier.sdk.creditpay.dialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.aac.e;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.i0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.creditpay.bean.CreditAuthInfo;
import com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity;

/* loaded from: classes14.dex */
public class b implements e, com.jd.lib.cashier.sdk.d.d.a {

    /* renamed from: g  reason: collision with root package name */
    private float f3174g;

    /* renamed from: h  reason: collision with root package name */
    private View f3175h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f3176i;

    /* renamed from: j  reason: collision with root package name */
    private ImageView f3177j;

    /* renamed from: k  reason: collision with root package name */
    private ViewGroup f3178k;

    /* renamed from: l  reason: collision with root package name */
    private CashierCreditPayActivity f3179l;

    /* renamed from: m  reason: collision with root package name */
    private View f3180m;

    /* renamed from: n  reason: collision with root package name */
    private View f3181n;
    private String o;
    private String p;
    private CashierCommonPopConfig q;

    /* loaded from: classes14.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b.this.m();
        }
    }

    /* renamed from: com.jd.lib.cashier.sdk.creditpay.dialog.b$b  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    class ViewOnClickListenerC0113b implements View.OnClickListener {
        ViewOnClickListenerC0113b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.this.q == null || !b.this.q.canDialogShow()) {
                com.jd.lib.cashier.sdk.creditpay.dialog.a.b(b.this.f3179l);
            } else {
                com.jd.lib.cashier.sdk.creditpay.dialog.a.c(b.this.f3179l, b.this.q);
            }
        }
    }

    /* loaded from: classes14.dex */
    class c implements Animator.AnimatorListener {
        c() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            j0.b(b.this.f3180m);
            if (b.this.f3181n != null) {
                b.this.f3181n.bringToFront();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            j0.d(b.this.f3181n);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class d implements Animator.AnimatorListener {
        d() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            j0.b(b.this.f3181n);
            if (b.this.f3180m != null) {
                b.this.f3180m.bringToFront();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            j0.d(b.this.f3180m);
        }
    }

    public b(CashierCreditPayActivity cashierCreditPayActivity) {
        this.f3179l = cashierCreditPayActivity;
        this.f3174g = DpiUtil.getAppWidth(cashierCreditPayActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        View view = this.f3180m;
        if (view == null || view.getVisibility() != 8) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this.f3181n, "TranslationX", 0.0f, this.f3174g), ObjectAnimator.ofFloat(this.f3180m, "TranslationX", -this.f3174g, 0.0f));
        animatorSet.setDuration(200L);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.addListener(new d());
        animatorSet.start();
    }

    public void n(View view, View view2) {
        this.f3180m = view;
        this.f3181n = view2;
        ViewGroup viewGroup = (ViewGroup) view2.findViewById(R.id.lib_cashier_credit_pay_protocol_agreement_content);
        this.f3178k = viewGroup;
        try {
            viewGroup.removeAllViews();
            View e2 = i0.e(this.f3179l, new LinearLayout.LayoutParams(-1, -1));
            this.f3175h = e2;
            this.f3178k.addView(e2);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        this.f3176i = (TextView) this.f3181n.findViewById(R.id.lib_cashier_credit_pay_protocol_agreement_title);
        this.f3177j = (ImageView) this.f3181n.findViewById(R.id.lib_cashier_credit_pay_protocol_agreement_back);
        View findViewById = this.f3181n.findViewById(R.id.lib_cashier_credit_pay_protocol_agreement_close);
        this.f3177j.setOnClickListener(new a());
        findViewById.setOnClickListener(new ViewOnClickListenerC0113b());
        onChangeSkin();
    }

    public boolean o() {
        View view = this.f3181n;
        return view != null && view.getVisibility() == 0;
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        TextView textView = this.f3176i;
        if (textView != null) {
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        }
        if (this.f3177j != null) {
            if (JDDarkUtil.isDarkMode()) {
                this.f3177j.setImageResource(R.drawable.lib_cashier_sdk_bg_back_selector_dark);
            } else {
                this.f3177j.setImageResource(R.drawable.lib_cashier_sdk_bg_back_selector);
            }
        }
        if (this.f3175h != null) {
            if (JDDarkUtil.isDarkMode()) {
                if (TextUtils.isEmpty(this.p)) {
                    return;
                }
                i0.h(this.f3175h, this.p);
            } else if (TextUtils.isEmpty(this.o)) {
            } else {
                i0.h(this.f3175h, this.o);
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        this.f3179l = null;
        this.q = null;
        View view = this.f3175h;
        if (view != null) {
            i0.i(view);
            this.f3175h = null;
        }
    }

    public void p() {
        onChangeSkin();
    }

    public void q(CashierCommonPopConfig cashierCommonPopConfig, CreditAuthInfo creditAuthInfo) {
        this.q = cashierCommonPopConfig;
        if (creditAuthInfo != null) {
            this.o = creditAuthInfo.agreementUrl;
            this.p = creditAuthInfo.agreementUrlDark;
        }
    }

    public void r() {
        View view = this.f3181n;
        if (view == null || view.getVisibility() != 8) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this.f3181n, "TranslationX", this.f3174g, 0.0f), ObjectAnimator.ofFloat(this.f3180m, "TranslationX", 0.0f, -this.f3174g));
        animatorSet.setDuration(200L);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.addListener(new c());
        animatorSet.start();
    }
}
