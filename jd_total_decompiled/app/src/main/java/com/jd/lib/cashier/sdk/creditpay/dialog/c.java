package com.jd.lib.cashier.sdk.creditpay.dialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.PlanRowEntity;
import com.jd.lib.cashier.sdk.core.model.PopBusinessMap;
import com.jd.lib.cashier.sdk.core.ui.CashierItemView;
import com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener;
import com.jd.lib.cashier.sdk.core.ui.widget.PayPlanView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.creditpay.aac.viewmodel.CashierCreditPayViewModel;
import com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public class c implements com.jd.lib.cashier.sdk.core.aac.e, com.jd.lib.cashier.sdk.d.d.a {

    /* renamed from: g */
    private TextView f3186g;

    /* renamed from: h */
    private ImageView f3187h;

    /* renamed from: i */
    private boolean f3188i;

    /* renamed from: j */
    private float f3189j;

    /* renamed from: k */
    private PayPlanView f3190k;

    /* renamed from: l */
    private CashierItemView f3191l;

    /* renamed from: m */
    private View f3192m;

    /* renamed from: n */
    private View f3193n;
    private CashierCreditPayActivity o;
    private CashierCommonPopConfig p;
    private View q;
    private TextView r;
    private View s;
    private View t;

    /* loaded from: classes14.dex */
    public class a implements View.OnClickListener {
        a() {
            c.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c.this.l();
        }
    }

    /* loaded from: classes14.dex */
    public class b implements View.OnClickListener {
        b() {
            c.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (c.this.p == null || !c.this.p.canDialogShow()) {
                com.jd.lib.cashier.sdk.creditpay.dialog.a.b(c.this.o);
            } else {
                com.jd.lib.cashier.sdk.creditpay.dialog.a.c(c.this.o, c.this.p);
            }
        }
    }

    /* renamed from: com.jd.lib.cashier.sdk.creditpay.dialog.c$c */
    /* loaded from: classes14.dex */
    public class C0114c implements OnPlanViewClickListener {
        final /* synthetic */ Payment a;

        C0114c(Payment payment) {
            c.this = r1;
            this.a = payment;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener
        public void onClick(@NotNull IPlanItemViewEntity iPlanItemViewEntity, @Nullable IPlanItemViewEntity iPlanItemViewEntity2) {
            if (g0.a(c.this.o)) {
                Payment payment = this.a;
                PlanFeeEntity planFeeEntity = (PlanFeeEntity) iPlanItemViewEntity;
                payment.targetPlanFee = planFeeEntity;
                payment.currentPlanFee = (PlanFeeEntity) iPlanItemViewEntity2;
                payment.currentCoupon = payment.selectedCoupon;
                payment.targetCoupon = planFeeEntity.getRecommendCoupon();
                c.this.m(this.a, "2");
            }
        }
    }

    /* loaded from: classes14.dex */
    public class d implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ PlanServiceMap f3196g;

        d(PlanServiceMap planServiceMap) {
            c.this = r1;
            this.f3196g = planServiceMap;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jd.lib.cashier.sdk.creditpay.dialog.a.e(c.this.o, this.f3196g.planServiceFeeToast);
        }
    }

    /* loaded from: classes14.dex */
    public class e implements Animator.AnimatorListener {
        e() {
            c.this = r1;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            j0.b(c.this.f3192m);
            if (c.this.f3193n != null) {
                c.this.f3193n.bringToFront();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            j0.d(c.this.f3193n);
        }
    }

    /* loaded from: classes14.dex */
    public class f implements Animator.AnimatorListener {
        f() {
            c.this = r1;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            j0.b(c.this.f3193n);
            if (c.this.f3192m != null) {
                c.this.f3192m.bringToFront();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            j0.d(c.this.f3192m);
        }
    }

    public c(CashierCreditPayActivity cashierCreditPayActivity) {
        this.o = cashierCreditPayActivity;
        this.f3189j = DpiUtil.getAppWidth(cashierCreditPayActivity);
    }

    private void p() {
        TextView textView = this.f3186g;
        if (textView != null) {
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        }
        if (this.f3187h != null) {
            if (JDDarkUtil.isDarkMode()) {
                this.f3187h.setImageResource(R.drawable.lib_cashier_sdk_bg_back_selector_dark);
            } else {
                this.f3187h.setImageResource(R.drawable.lib_cashier_sdk_bg_back_selector);
            }
        }
        PayPlanView payPlanView = this.f3190k;
        if (payPlanView != null && payPlanView.getVisibility() == 0) {
            this.f3190k.onChangeSkin();
            this.f3190k.v();
        }
        CashierItemView cashierItemView = this.f3191l;
        if (cashierItemView != null) {
            cashierItemView.a(!this.f3188i);
        }
        TextView textView2 = this.r;
        if (textView2 != null && textView2.getVisibility() == 0) {
            this.r.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
        }
        View view = this.s;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.s.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
    }

    private void s(Payment payment, PayPlanView payPlanView, List<PlanFeeEntity> list, String str) {
        if (payment == null || payPlanView == null || list == null) {
            return;
        }
        payPlanView.H(AbsPayPlanView.b.PLAN_BAITIAO);
        payPlanView.x(list, str);
        payPlanView.G(new C0114c(payment));
    }

    private void t(CashierItemView cashierItemView, Payment payment) {
        if (cashierItemView == null || payment == null) {
            return;
        }
        cashierItemView.E(payment.logo);
        cashierItemView.s(payment.channelName);
        cashierItemView.G(payment.tip);
        cashierItemView.F(payment.statusDesc);
        cashierItemView.u(payment.channelNameTail);
        cashierItemView.t(payment.channelNameMiddle);
        cashierItemView.P();
        cashierItemView.k(true);
        cashierItemView.f();
        cashierItemView.e();
        u(cashierItemView, payment);
        v(cashierItemView, payment);
    }

    private void u(CashierItemView cashierItemView, Payment payment) {
        if (cashierItemView == null || payment == null) {
            return;
        }
        List<String> list = payment.iconList;
        if (list != null && !list.isEmpty()) {
            int size = payment.iconList.size();
            cashierItemView.l(24, 20);
            if (size >= 2) {
                cashierItemView.x(payment.iconList.get(0));
                cashierItemView.C(payment.iconList.get(1));
                return;
            }
            cashierItemView.D();
            cashierItemView.x(payment.iconList.get(0));
            return;
        }
        cashierItemView.B();
    }

    private void v(CashierItemView cashierItemView, Payment payment) {
        if (cashierItemView == null || payment == null) {
            return;
        }
        String str = payment.preferentiaNum;
        CouponEntity couponEntity = payment.selectedCoupon;
        if (couponEntity != null) {
            str = couponEntity.getTitleName();
        }
        if (TextUtils.isEmpty(str)) {
            cashierItemView.w();
        } else {
            cashierItemView.v(str, false, null);
        }
    }

    private void w(PlanServiceMap planServiceMap) {
        PopBusinessMap popBusinessMap;
        List<PlanRowEntity> list;
        if (this.s == null || this.t == null || this.r == null) {
            return;
        }
        PayPlanView payPlanView = this.f3190k;
        if (payPlanView != null && planServiceMap != null && payPlanView.getVisibility() == 0) {
            if (TextUtils.isEmpty(planServiceMap.planServiceFeeStr)) {
                this.s.setVisibility(8);
                return;
            }
            boolean z = false;
            this.s.setVisibility(0);
            this.r.setText(planServiceMap.planServiceFeeStr);
            CashierCommonPopConfig cashierCommonPopConfig = planServiceMap.planServiceFeeToast;
            if (cashierCommonPopConfig != null && (popBusinessMap = cashierCommonPopConfig.businessMap) != null && (list = popBusinessMap.table) != null && !list.isEmpty()) {
                z = true;
            }
            if (z) {
                j0.d(this.q);
                this.t.setOnClickListener(new d(planServiceMap));
                return;
            }
            j0.b(this.q);
            this.t.setOnClickListener(null);
            return;
        }
        this.s.setVisibility(8);
    }

    public void l() {
        View view = this.f3192m;
        if (view == null || view.getVisibility() != 8) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this.f3193n, "TranslationX", 0.0f, this.f3189j), ObjectAnimator.ofFloat(this.f3192m, "TranslationX", -this.f3189j, 0.0f));
        animatorSet.setDuration(200L);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.addListener(new f());
        animatorSet.start();
    }

    public void m(Payment payment, String str) {
        if (g0.a(this.o)) {
            ((CashierCreditPayViewModel) ViewModelProviders.of(this.o).get(CashierCreditPayViewModel.class)).e(this.o, payment, str);
        }
    }

    public void n(View view, View view2) {
        this.f3193n = view2;
        this.f3192m = view;
        this.f3186g = (TextView) view2.findViewById(R.id.lib_cashier_credit_pay_channel_pay_bt_title);
        this.f3187h = (ImageView) this.f3193n.findViewById(R.id.lib_cashier_credit_pay_channel_pay_bt_back);
        this.f3191l = (CashierItemView) this.f3193n.findViewById(R.id.lib_cashier_credit_pay_channel_pay_bt_item);
        this.f3190k = (PayPlanView) this.f3193n.findViewById(R.id.lib_cashier_credit_pay_channel_pay_bt_plan_view);
        this.q = this.f3193n.findViewById(R.id.lib_cashier_credit_pay_channel_pay_bt_plan_fee_image);
        this.r = (TextView) this.f3193n.findViewById(R.id.lib_cashier_credit_pay_channel_pay_bt_plan_fee_tip);
        this.s = this.f3193n.findViewById(R.id.lib_cashier_credit_pay_channel_pay_bt_plan_fee_root);
        this.t = this.f3193n.findViewById(R.id.lib_cashier_credit_pay_channel_pay_bt_plan_fee_tip_container);
        View findViewById = this.f3193n.findViewById(R.id.lib_cashier_credit_pay_channel_pay_bt_close);
        this.f3187h.setOnClickListener(new a());
        findViewById.setOnClickListener(new b());
    }

    public boolean o() {
        View view = this.f3193n;
        return view != null && view.getVisibility() == 0;
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        p();
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        this.o = null;
        this.p = null;
    }

    public void q(Payment payment) {
        if (payment != null) {
            this.f3188i = payment.canUse();
            t(this.f3191l, payment);
        }
        onChangeSkin();
    }

    public void r(Payment payment, PlanServiceMap planServiceMap, List<PlanFeeEntity> list, String str) {
        if (payment != null) {
            this.f3188i = payment.canUse();
            t(this.f3191l, payment);
            if (list != null && !list.isEmpty()) {
                s(payment, this.f3190k, list, str);
            }
            w(planServiceMap);
        }
        onChangeSkin();
    }

    public void x(CashierCommonPopConfig cashierCommonPopConfig) {
        this.p = cashierCommonPopConfig;
    }

    public void y() {
        View view = this.f3193n;
        if (view == null || view.getVisibility() != 8) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this.f3193n, "TranslationX", this.f3189j, 0.0f), ObjectAnimator.ofFloat(this.f3192m, "TranslationX", 0.0f, -this.f3189j));
        animatorSet.setDuration(200L);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.addListener(new e());
        animatorSet.start();
    }
}
