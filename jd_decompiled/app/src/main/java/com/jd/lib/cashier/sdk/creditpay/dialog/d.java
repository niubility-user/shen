package com.jd.lib.cashier.sdk.creditpay.dialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.layoutmanager.WrapContentLinearLayoutManager;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.creditpay.adapter.CreditPaymentAdapter;
import com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class d implements com.jd.lib.cashier.sdk.core.aac.e, com.jd.lib.cashier.sdk.d.d.a {

    /* renamed from: g  reason: collision with root package name */
    private TextView f3200g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f3201h;

    /* renamed from: i  reason: collision with root package name */
    private float f3202i;

    /* renamed from: j  reason: collision with root package name */
    private RecyclerView f3203j;

    /* renamed from: k  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.core.utils.f<Payment> f3204k;

    /* renamed from: l  reason: collision with root package name */
    private CashierCreditPayActivity f3205l;

    /* renamed from: m  reason: collision with root package name */
    private View f3206m;

    /* renamed from: n  reason: collision with root package name */
    private View f3207n;
    private CashierCommonPopConfig o;
    private CreditPaymentAdapter p;
    private List<Payment> q = new ArrayList();

    /* loaded from: classes14.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d.this.n();
        }
    }

    /* loaded from: classes14.dex */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (d.this.o == null || !d.this.o.canDialogShow()) {
                com.jd.lib.cashier.sdk.creditpay.dialog.a.b(d.this.f3205l);
            } else {
                com.jd.lib.cashier.sdk.creditpay.dialog.a.c(d.this.f3205l, d.this.o);
            }
        }
    }

    /* loaded from: classes14.dex */
    class c implements com.jd.lib.cashier.sdk.core.utils.f<Payment> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f3210g;

        c(String str) {
            this.f3210g = str;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(Payment payment) {
            if (payment != null) {
                com.jd.lib.cashier.sdk.e.c.a.e(d.this.f3205l, payment.code, payment.uniqueChannelId, this.f3210g);
            }
        }
    }

    /* renamed from: com.jd.lib.cashier.sdk.creditpay.dialog.d$d  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    class C0115d implements com.jd.lib.cashier.sdk.core.utils.f<Payment> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f3212g;

        C0115d(String str) {
            this.f3212g = str;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(Payment payment) {
            d.this.n();
            if (d.this.f3204k != null) {
                d.this.f3204k.callBack(payment);
            }
            if (payment != null) {
                com.jd.lib.cashier.sdk.e.c.a.p(d.this.f3205l, payment.code, payment.uniqueChannelId, this.f3212g);
            }
        }
    }

    /* loaded from: classes14.dex */
    class e implements Animator.AnimatorListener {
        e() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            j0.b(d.this.f3206m);
            if (d.this.f3207n != null) {
                d.this.f3207n.bringToFront();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            j0.d(d.this.f3207n);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class f implements Animator.AnimatorListener {
        f() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            j0.b(d.this.f3207n);
            if (d.this.f3206m != null) {
                d.this.f3206m.bringToFront();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            j0.d(d.this.f3206m);
        }
    }

    public d(CashierCreditPayActivity cashierCreditPayActivity, com.jd.lib.cashier.sdk.core.utils.f<Payment> fVar) {
        this.f3204k = fVar;
        this.f3205l = cashierCreditPayActivity;
        this.f3202i = DpiUtil.getAppWidth(cashierCreditPayActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        View view = this.f3206m;
        if (view == null || view.getVisibility() != 8) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this.f3207n, "TranslationX", 0.0f, this.f3202i), ObjectAnimator.ofFloat(this.f3206m, "TranslationX", -this.f3202i, 0.0f));
        animatorSet.setDuration(200L);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.addListener(new f());
        animatorSet.start();
    }

    private void q(boolean z) {
        TextView textView = this.f3200g;
        if (textView != null) {
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        }
        if (this.f3201h != null) {
            if (JDDarkUtil.isDarkMode()) {
                this.f3201h.setBackgroundResource(R.drawable.lib_cashier_sdk_bg_back_selector_dark);
            } else {
                this.f3201h.setBackgroundResource(R.drawable.lib_cashier_sdk_bg_back_selector);
            }
        }
        if (this.p == null || !z) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.q);
        this.q.clear();
        this.q.addAll(arrayList);
        this.p.notifyDataSetChanged();
    }

    public void o(View view, View view2) {
        this.f3206m = view;
        this.f3207n = view2;
        this.f3203j = (RecyclerView) view2.findViewById(R.id.lib_cashier_credit_pay_channel_credit_card_list);
        this.f3201h = (ImageView) view2.findViewById(R.id.lib_cashier_credit_pay_channel_credit_card_back);
        this.f3200g = (TextView) view2.findViewById(R.id.lib_cashier_credit_pay_channel_credit_card_title);
        View findViewById = view2.findViewById(R.id.lib_cashier_credit_pay_channel_credit_card_close);
        this.f3201h.setOnClickListener(new a());
        findViewById.setOnClickListener(new b());
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        q(true);
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        this.f3205l = null;
        this.q.clear();
        this.q = null;
        this.f3203j = null;
        this.f3206m = null;
        this.f3207n = null;
        this.p = null;
    }

    public boolean p() {
        View view = this.f3207n;
        return view != null && view.getVisibility() == 0;
    }

    public void r(String str, Payment payment, List<Payment> list) {
        q(false);
        com.jd.lib.cashier.sdk.b.g.a.b(payment, list);
        this.q.clear();
        this.q.addAll(list);
        CreditPaymentAdapter creditPaymentAdapter = this.p;
        if (creditPaymentAdapter == null) {
            CreditPaymentAdapter creditPaymentAdapter2 = new CreditPaymentAdapter(this.q);
            this.p = creditPaymentAdapter2;
            creditPaymentAdapter2.o(new c(str));
            this.p.n(new C0115d(str));
            WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this.f3205l);
            wrapContentLinearLayoutManager.setOrientation(1);
            this.f3203j.setLayoutManager(wrapContentLinearLayoutManager);
            this.f3203j.setAdapter(this.p);
            return;
        }
        creditPaymentAdapter.notifyDataSetChanged();
    }

    public void s(CashierCommonPopConfig cashierCommonPopConfig) {
        this.o = cashierCommonPopConfig;
    }

    public void t() {
        View view = this.f3207n;
        if (view == null || view.getVisibility() != 8) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this.f3207n, "TranslationX", this.f3202i, 0.0f), ObjectAnimator.ofFloat(this.f3206m, "TranslationX", 0.0f, -this.f3202i));
        animatorSet.setDuration(200L);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.addListener(new e());
        animatorSet.start();
    }
}
