package com.jd.lib.cashier.sdk.creditpay.dialog;

import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.c0;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jd.lib.cashier.sdk.core.utils.n;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.creditpay.aac.viewmodel.CashierCreditPayViewModel;
import com.jd.lib.cashier.sdk.creditpay.bean.CreditAuthInfo;
import com.jd.lib.cashier.sdk.creditpay.bean.CreditPayEntity;
import com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity;
import com.jd.lib.cashier.sdk.pay.bean.AllCoupons;
import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoPayPlanResponse;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponAndCutOffs;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierCreditPayDialogProxy implements Observer<com.jd.lib.cashier.sdk.e.a.b.a>, com.jd.lib.cashier.sdk.core.aac.e, com.jd.lib.cashier.sdk.d.d.a {
    private TextView A;
    private TextView B;
    private TextView C;
    private TextView D;
    private TextView E;
    private TextView F;
    private com.jd.lib.cashier.sdk.creditpay.dialog.c G;
    private com.jd.lib.cashier.sdk.creditpay.dialog.b H;
    private com.jd.lib.cashier.sdk.creditpay.dialog.d I;

    /* renamed from: g  reason: collision with root package name */
    private Handler f3123g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f3124h;

    /* renamed from: i  reason: collision with root package name */
    private Payment f3125i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f3126j;

    /* renamed from: k  reason: collision with root package name */
    private CreditPayEntity f3127k;

    /* renamed from: l  reason: collision with root package name */
    private CashierCreditPayActivity f3128l;

    /* renamed from: m  reason: collision with root package name */
    private CheckBox f3129m;

    /* renamed from: n  reason: collision with root package name */
    private ImageView f3130n;
    private View o;
    private View p;
    private View q;
    private View r;
    private View s;
    private View t;
    private View u;
    private TextView v;
    private TextView w;
    private TextView x;
    private TextView y;
    private TextView z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements com.jd.lib.cashier.sdk.core.utils.f<Payment> {
        a() {
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(Payment payment) {
            CashierCreditPayDialogProxy.this.y(payment);
            CashierCreditPayDialogProxy.this.V();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CashierCreditPayDialogProxy.this.f3127k == null || CashierCreditPayDialogProxy.this.f3127k.indexPopupConfig == null || !CashierCreditPayDialogProxy.this.f3127k.indexPopupConfig.canDialogShow()) {
                com.jd.lib.cashier.sdk.creditpay.dialog.a.b(CashierCreditPayDialogProxy.this.f3128l);
            } else {
                com.jd.lib.cashier.sdk.creditpay.dialog.a.c(CashierCreditPayDialogProxy.this.f3128l, CashierCreditPayDialogProxy.this.f3127k.indexPopupConfig);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CashierCreditPayDialogProxy.this.f3129m.isChecked()) {
                CashierCreditPayDialogProxy.this.f3126j = false;
                CashierCreditPayDialogProxy.this.f3129m.setChecked(false);
                return;
            }
            CashierCreditPayDialogProxy.this.f3126j = true;
            CashierCreditPayDialogProxy.this.f3129m.setChecked(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CashierCreditPayDialogProxy.this.H != null && CashierCreditPayDialogProxy.this.G != null && !CashierCreditPayDialogProxy.this.G.o() && CashierCreditPayDialogProxy.this.I != null && !CashierCreditPayDialogProxy.this.I.p()) {
                CashierCreditPayDialogProxy.this.H.p();
                CashierCreditPayDialogProxy.this.H.r();
            }
            com.jd.lib.cashier.sdk.e.c.a.b(CashierCreditPayDialogProxy.this.f3128l);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class e extends com.jd.lib.cashier.sdk.core.utils.b {
        e(long j2) {
            super(j2);
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            if (CashierCreditPayDialogProxy.this.r.getVisibility() == 0) {
                if (CashierCreditPayDialogProxy.this.f3129m.isChecked()) {
                    com.jd.lib.cashier.sdk.e.e.c.a.b(CashierCreditPayDialogProxy.this.f3128l, CashierCreditPayDialogProxy.this.f3125i);
                } else {
                    f0.c(CashierCreditPayDialogProxy.this.f3128l.getString(R.string.lib_cashier_sdk_credit_pay_authorize_agreement_toast));
                }
            } else {
                com.jd.lib.cashier.sdk.e.e.c.a.b(CashierCreditPayDialogProxy.this.f3128l, CashierCreditPayDialogProxy.this.f3125i);
            }
            com.jd.lib.cashier.sdk.e.c.a.d(CashierCreditPayDialogProxy.this.f3128l);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CashierCreditPayDialogProxy.this.f3125i != null) {
                if (!com.jd.lib.cashier.sdk.h.h.g.a(CashierCreditPayDialogProxy.this.f3125i.code) || CashierCreditPayDialogProxy.this.G == null || CashierCreditPayDialogProxy.this.I == null || CashierCreditPayDialogProxy.this.I.p() || CashierCreditPayDialogProxy.this.H == null || CashierCreditPayDialogProxy.this.H.o()) {
                    if (CashierCreditPayDialogProxy.this.I != null && CashierCreditPayDialogProxy.this.f3127k.jdPayChannelList != null && CashierCreditPayDialogProxy.this.G != null && !CashierCreditPayDialogProxy.this.G.o() && CashierCreditPayDialogProxy.this.H != null && !CashierCreditPayDialogProxy.this.H.o()) {
                        CashierCreditPayDialogProxy.this.I.r(CashierCreditPayDialogProxy.this.f3127k.orderId, CashierCreditPayDialogProxy.this.f3125i, CashierCreditPayDialogProxy.this.f3127k.jdPayChannelList);
                        CashierCreditPayDialogProxy.this.I.t();
                    }
                } else {
                    CashierCreditPayDialogProxy.this.f3124h = false;
                    CashierCreditPayDialogProxy.this.G.q(CashierCreditPayDialogProxy.this.f3125i);
                    CashierCreditPayDialogProxy.this.G.y();
                    CashierCreditPayDialogProxy.this.B();
                }
                CashierCreditPayDialogProxy.this.z();
            } else {
                CashierCreditPayDialogProxy.this.Y();
            }
            if (CashierCreditPayDialogProxy.this.f3125i != null) {
                com.jd.lib.cashier.sdk.e.c.a.o(CashierCreditPayDialogProxy.this.f3128l, CashierCreditPayDialogProxy.this.f3125i.code, CashierCreditPayDialogProxy.this.f3125i.uniqueChannelId);
            } else {
                com.jd.lib.cashier.sdk.e.c.a.o(CashierCreditPayDialogProxy.this.f3128l, "", "");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CashierCreditPayDialogProxy.this.s == null || CashierCreditPayDialogProxy.this.s.getVisibility() != 0) {
                return;
            }
            CashierCreditPayDialogProxy.this.s.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class h implements Runnable {
        h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c0.e(CashierCreditPayDialogProxy.this.f3128l, "CHECK_BOX_STATUS_KEY", CashierCreditPayDialogProxy.this.f3126j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class i implements Runnable {
        i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CashierCreditPayDialogProxy cashierCreditPayDialogProxy = CashierCreditPayDialogProxy.this;
            cashierCreditPayDialogProxy.f3126j = c0.b(cashierCreditPayDialogProxy.f3128l, "CHECK_BOX_STATUS_KEY", false);
        }
    }

    public CashierCreditPayDialogProxy(CashierCreditPayActivity cashierCreditPayActivity, View view) {
        this.f3128l = cashierCreditPayActivity;
        M();
        G();
        K(view);
    }

    private void A() {
        com.jd.lib.cashier.sdk.creditpay.dialog.c cVar = this.G;
        if (cVar == null || !this.f3124h) {
            return;
        }
        cVar.l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        Payment payment;
        if (this.G == null || (payment = this.f3125i) == null || !payment.canUse() || !com.jd.lib.cashier.sdk.h.h.g.a(this.f3125i.code)) {
            return;
        }
        Payment payment2 = this.f3125i;
        PlanFeeEntity planFeeEntity = payment2.selectedPlanFee;
        payment2.currentPlanFee = planFeeEntity;
        payment2.currentCoupon = payment2.selectedCoupon;
        payment2.targetPlanFee = planFeeEntity;
        payment2.targetCoupon = null;
        this.G.m(payment2, "0");
    }

    private String D(String str) {
        CouponAndCutOffs couponAndCutOffs;
        Payment payment = this.f3125i;
        if (payment != null) {
            PlanFeeEntity planFeeEntity = null;
            if (com.jd.lib.cashier.sdk.h.h.g.a(payment.code)) {
                planFeeEntity = this.f3125i.selectedPlanFee;
                couponAndCutOffs = null;
            } else {
                couponAndCutOffs = this.f3125i.selectedCommonCoupon;
            }
            if (planFeeEntity == null || TextUtils.isEmpty(planFeeEntity.getRealPayAmount())) {
                return (couponAndCutOffs == null || TextUtils.equals(couponAndCutOffs.getPayMarketingUUID(), "doNotUse") || TextUtils.equals(couponAndCutOffs.getPayMarketingUUID(), "empty") || TextUtils.isEmpty(couponAndCutOffs.getCouponAmount())) ? str : couponAndCutOffs.getCouponAmount();
            }
            return planFeeEntity.getRealPayAmount();
        }
        return str;
    }

    private void E() {
        Payment a2;
        this.f3125i = null;
        CreditPayEntity creditPayEntity = this.f3127k;
        if (creditPayEntity == null || (a2 = com.jd.lib.cashier.sdk.b.g.a.a(creditPayEntity.jdPayChannelList)) == null) {
            return;
        }
        this.f3125i = a2;
        if (com.jd.lib.cashier.sdk.h.h.g.a(a2.code)) {
            Payment payment = this.f3125i;
            payment.selectedCoupon = payment.defaultCoupon;
            payment.selectedPlanFee = payment.defaultPlanFee;
            return;
        }
        Payment payment2 = this.f3125i;
        AllCoupons allCoupons = payment2.allCoupons;
        if (allCoupons != null) {
            payment2.selectedCommonCoupon = com.jd.lib.cashier.sdk.b.c.b.a(payment2.defaultCouponId, allCoupons.couponAndCutOffs);
        }
    }

    private void F(View view) {
        View findViewById = view.findViewById(R.id.lib_cashier_credit_pay_authorize_dialog_close);
        this.r = view.findViewById(R.id.lib_cashier_credit_pay_authorize_layout);
        this.o = view.findViewById(R.id.lib_cashier_credit_pay_authorize_auth_info_container);
        this.t = view.findViewById(R.id.lib_cashier_credit_pay_authorize_auth_info_split_line);
        this.u = view.findViewById(R.id.lib_cashier_credit_pay_authorize_pay_money_split_line);
        this.q = view.findViewById(R.id.lib_cashier_credit_pay_authorize_pay_way_container);
        this.p = view.findViewById(R.id.lib_cashier_credit_pay_authorize_pay_money_container);
        this.v = (TextView) view.findViewById(R.id.lib_cashier_credit_pay_authorize_dialog_title);
        this.w = (TextView) view.findViewById(R.id.lib_cashier_credit_pay_authorize_auth_info_content);
        this.x = (TextView) view.findViewById(R.id.lib_cashier_credit_pay_authorize_pay_money_title);
        this.y = (TextView) view.findViewById(R.id.lib_cashier_credit_pay_authorize_pay_money_content);
        this.f3129m = (CheckBox) view.findViewById(R.id.lib_cashier_credit_pay_authorize_checkbox);
        this.z = (TextView) view.findViewById(R.id.lib_cashier_credit_pay_authorize_confirm);
        this.A = (TextView) view.findViewById(R.id.lib_cashier_credit_pay_authorize_agreement);
        this.B = (TextView) view.findViewById(R.id.lib_cashier_credit_pay_authorize_agreement_linker);
        this.C = (TextView) view.findViewById(R.id.lib_cashier_credit_pay_authorize_pay_way_title);
        this.D = (TextView) view.findViewById(R.id.lib_cashier_credit_pay_authorize_pay_way);
        this.f3130n = (ImageView) view.findViewById(R.id.lib_cashier_credit_pay_authorize_pay_way_arrow);
        this.E = (TextView) view.findViewById(R.id.lib_cashier_credit_pay_authorize_pay_way_coupon);
        this.F = (TextView) view.findViewById(R.id.lib_cashier_credit_pay_bt_hot_business_bubble_tv);
        this.s = view.findViewById(R.id.lib_cashier_credit_pay_bt_hot_business_bubble);
        this.f3129m.setBackgroundResource(R.drawable.lib_cashier_sdk_button_i_new);
        this.f3129m.setChecked(this.f3126j);
        m0.a(this.w, (byte) 3);
        m0.a(this.y, (byte) 3);
        m0.a(this.D, (byte) 3);
        m0.a(this.F, (byte) 3);
        this.v.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        findViewById.setOnClickListener(new b());
        this.r.setOnClickListener(new c());
        this.B.setOnClickListener(new d());
        this.z.setOnClickListener(new e(1200L));
        this.q.setOnClickListener(new f());
    }

    private void G() {
        this.f3123g = new n();
        this.G = new com.jd.lib.cashier.sdk.creditpay.dialog.c(this.f3128l);
        this.H = new com.jd.lib.cashier.sdk.creditpay.dialog.b(this.f3128l);
        this.I = new com.jd.lib.cashier.sdk.creditpay.dialog.d(this.f3128l, new a());
        ((CashierCreditPayViewModel) ViewModelProviders.of(this.f3128l).get(CashierCreditPayViewModel.class)).g().observe(this.f3128l, this);
    }

    private void I(View view, View view2) {
        View findViewById = view2.findViewById(R.id.lib_cashier_credit_pay_agreement_page);
        View findViewById2 = view2.findViewById(R.id.lib_cashier_credit_pay_channel_pay_bt_page);
        View findViewById3 = view2.findViewById(R.id.lib_cashier_credit_pay_credit_card_page);
        com.jd.lib.cashier.sdk.creditpay.dialog.c cVar = this.G;
        if (cVar != null) {
            cVar.n(view, findViewById2);
        }
        com.jd.lib.cashier.sdk.creditpay.dialog.b bVar = this.H;
        if (bVar != null) {
            bVar.n(view, findViewById);
        }
        com.jd.lib.cashier.sdk.creditpay.dialog.d dVar = this.I;
        if (dVar != null) {
            dVar.o(view, findViewById3);
        }
    }

    private void J() {
        com.jd.lib.cashier.sdk.creditpay.dialog.c cVar = this.G;
        if (cVar != null) {
            cVar.x(this.f3127k.indexPopupConfig);
        }
        com.jd.lib.cashier.sdk.creditpay.dialog.d dVar = this.I;
        if (dVar != null) {
            dVar.s(this.f3127k.indexPopupConfig);
        }
        com.jd.lib.cashier.sdk.creditpay.dialog.b bVar = this.H;
        if (bVar != null) {
            CreditPayEntity creditPayEntity = this.f3127k;
            bVar.q(creditPayEntity.indexPopupConfig, creditPayEntity.creditAuthInfo);
        }
    }

    private void K(View view) {
        View findViewById = view.findViewById(R.id.lib_cashier_credit_pay_authorize_page);
        F(findViewById);
        I(findViewById, view);
    }

    private void M() {
        try {
            y.e(new i());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void N() {
        CreditAuthInfo creditAuthInfo;
        CreditPayEntity creditPayEntity = this.f3127k;
        if (creditPayEntity == null || (creditAuthInfo = creditPayEntity.creditAuthInfo) == null) {
            return;
        }
        if (TextUtils.isEmpty(creditAuthInfo.agreementTip) && TextUtils.isEmpty(creditAuthInfo.agreementName)) {
            this.r.setVisibility(8);
        } else {
            this.r.setVisibility(0);
            this.A.setText(creditAuthInfo.agreementTip);
            this.B.setText(creditAuthInfo.agreementName);
            com.jd.lib.cashier.sdk.e.c.a.i(this.f3128l);
        }
        if (!TextUtils.isEmpty(creditAuthInfo.btnText)) {
            this.z.setText(creditAuthInfo.btnText);
        }
        com.jd.lib.cashier.sdk.e.c.a.c(this.f3128l);
    }

    private void O() {
        Payment payment = this.f3125i;
        if (payment != null && !TextUtils.isEmpty(payment.saleStr)) {
            this.s.setVisibility(0);
            this.F.setText(this.f3125i.saleStr);
            return;
        }
        this.s.setVisibility(8);
    }

    private void P() {
        com.jd.lib.cashier.sdk.creditpay.dialog.c cVar = this.G;
        if (cVar == null || !cVar.o()) {
            return;
        }
        this.G.q(this.f3125i);
    }

    private void Q(BaiTiaoPayPlanResponse baiTiaoPayPlanResponse) {
        List<PlanFeeEntity> list;
        com.jd.lib.cashier.sdk.creditpay.dialog.c cVar = this.G;
        if (cVar == null || !cVar.o() || baiTiaoPayPlanResponse == null || (list = baiTiaoPayPlanResponse.planFeeList) == null) {
            return;
        }
        this.G.r(this.f3125i, baiTiaoPayPlanResponse.serviceMap, list, baiTiaoPayPlanResponse.mianxiHighlight);
    }

    private void S(boolean z) {
        String str;
        CouponAndCutOffs couponAndCutOffs;
        CreditAuthInfo creditAuthInfo;
        CreditPayEntity creditPayEntity = this.f3127k;
        boolean z2 = (creditPayEntity == null || z || !creditPayEntity.hiddenFrozenPriceFlag) ? false : true;
        String str2 = "";
        if (creditPayEntity == null || (creditAuthInfo = creditPayEntity.creditAuthInfo) == null) {
            str = "";
        } else {
            if (!TextUtils.isEmpty(creditAuthInfo.frozenAmountTitle)) {
                this.x.setText(this.f3127k.creditAuthInfo.frozenAmountTitle);
            }
            CreditPayEntity creditPayEntity2 = this.f3127k;
            str2 = creditPayEntity2.moneyFlag;
            str = creditPayEntity2.creditAuthInfo.frozenAmount;
        }
        Payment payment = this.f3125i;
        if (payment != null) {
            PlanFeeEntity planFeeEntity = null;
            if (com.jd.lib.cashier.sdk.h.h.g.a(payment.code)) {
                planFeeEntity = this.f3125i.selectedPlanFee;
                couponAndCutOffs = null;
            } else {
                couponAndCutOffs = this.f3125i.selectedCommonCoupon;
            }
            if (planFeeEntity != null && !TextUtils.isEmpty(planFeeEntity.getRealPayAmount())) {
                str = planFeeEntity.getRealPayAmount();
            } else if (couponAndCutOffs != null && !TextUtils.equals(couponAndCutOffs.getPayMarketingUUID(), "doNotUse") && !TextUtils.equals(couponAndCutOffs.getPayMarketingUUID(), "empty") && !TextUtils.isEmpty(couponAndCutOffs.getCouponAmount())) {
                str = couponAndCutOffs.getCouponAmount();
            }
            if (z && planFeeEntity != null) {
                z2 = planFeeEntity.getHiddenFrozenPriceFlag();
            }
        }
        if (!TextUtils.isEmpty(str) && !z2) {
            this.y.setText(str2 + str);
            this.u.setVisibility(0);
            this.p.setVisibility(0);
            return;
        }
        this.p.setVisibility(8);
        this.u.setVisibility(8);
    }

    private void T() {
        S(true);
    }

    private void U() {
        S(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V() {
        CreditAuthInfo creditAuthInfo;
        CreditPayEntity creditPayEntity = this.f3127k;
        if (creditPayEntity != null && (creditAuthInfo = creditPayEntity.creditAuthInfo) != null && !TextUtils.isEmpty(creditAuthInfo.paymentTitle)) {
            this.C.setText(creditAuthInfo.paymentTitle);
        }
        Payment payment = this.f3125i;
        if (payment != null && payment.canUse()) {
            if (this.D != null) {
                Payment payment2 = this.f3125i;
                String str = payment2.channelName;
                PlanFeeEntity planFeeEntity = payment2.selectedPlanFee;
                if (planFeeEntity != null && !TextUtils.isEmpty(planFeeEntity.getPlanNum())) {
                    if (TextUtils.equals(planFeeEntity.getPlanNum(), "1")) {
                        str = str + "(" + this.f3128l.getString(R.string.lib_cashier_sdk_credit_pay_plan_num_one) + ")";
                    } else {
                        str = str + "(" + planFeeEntity.getPlanNum() + this.f3128l.getString(R.string.lib_cashier_sdk_credit_pay_plan_num_unit) + ")";
                    }
                }
                if (!TextUtils.isEmpty(str)) {
                    this.D.setVisibility(0);
                    this.D.setText(str);
                    CashierCreditPayActivity cashierCreditPayActivity = this.f3128l;
                    Payment payment3 = this.f3125i;
                    com.jd.lib.cashier.sdk.e.c.a.f(cashierCreditPayActivity, payment3.code, payment3.uniqueChannelId);
                } else {
                    this.D.setVisibility(8);
                }
            }
            if (this.E != null) {
                com.jd.lib.cashier.sdk.pay.dialog.e c2 = com.jd.lib.cashier.sdk.b.c.c.c(this.f3125i);
                if (c2 != null && !TextUtils.isEmpty(c2.getTitleName())) {
                    this.E.setVisibility(0);
                    this.E.setText(c2.getTitleName());
                    return;
                }
                this.E.setVisibility(8);
                return;
            }
            return;
        }
        CreditPayEntity creditPayEntity2 = this.f3127k;
        if (creditPayEntity2 != null && !TextUtils.isEmpty(creditPayEntity2.noCanUseChannelTip)) {
            this.D.setVisibility(0);
            this.D.setText(this.f3127k.noCanUseChannelTip);
        } else {
            this.D.setVisibility(8);
        }
        this.E.setVisibility(8);
    }

    private void W() {
        CreditAuthInfo creditAuthInfo;
        CreditPayEntity creditPayEntity = this.f3127k;
        if (creditPayEntity != null && (creditAuthInfo = creditPayEntity.creditAuthInfo) != null && !TextUtils.isEmpty(creditAuthInfo.subTitle)) {
            CreditPayEntity creditPayEntity2 = this.f3127k;
            CreditAuthInfo creditAuthInfo2 = creditPayEntity2.creditAuthInfo;
            String str = this.f3127k.moneyFlag + D(creditPayEntity2.payprice);
            String str2 = creditAuthInfo2.subTitle + "\t\t" + str;
            SpannableString spannableString = new SpannableString(str2);
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(creditAuthInfo2.dayStr) && str2.contains(creditAuthInfo2.dayStr)) {
                int indexOf = str2.indexOf(creditAuthInfo2.dayStr);
                int length = creditAuthInfo2.dayStr.length() + indexOf;
                if (indexOf >= 0 && length >= 0 && length >= indexOf) {
                    spannableString.setSpan(new ForegroundColorSpan(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FA2C19)), indexOf, length, 33);
                }
            }
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str) && str2.contains(str)) {
                int indexOf2 = str2.indexOf(str);
                int length2 = str.length() + indexOf2;
                if (indexOf2 >= 0 && length2 >= 0 && length2 >= indexOf2) {
                    spannableString.setSpan(new ForegroundColorSpan(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FA2C19)), indexOf2, length2, 33);
                }
            }
            this.w.setText(spannableString);
            this.t.setVisibility(0);
            this.o.setVisibility(0);
            return;
        }
        this.t.setVisibility(8);
        this.o.setVisibility(8);
    }

    private void X() {
        CreditAuthInfo creditAuthInfo;
        CreditPayEntity creditPayEntity = this.f3127k;
        if (creditPayEntity == null || (creditAuthInfo = creditPayEntity.creditAuthInfo) == null || TextUtils.isEmpty(creditAuthInfo.title)) {
            return;
        }
        this.v.setText(creditAuthInfo.title);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y() {
        CreditPayEntity creditPayEntity = this.f3127k;
        String str = creditPayEntity != null ? creditPayEntity.noCanUseChannelTip : "";
        if (TextUtils.isEmpty(str)) {
            str = this.f3128l.getString(R.string.lib_cashier_sdk_credit_pay_no_way_pay_toast);
        }
        f0.c(str);
    }

    private void Z() {
        this.f3129m.setEnabled(false);
        this.f3129m.setClickable(false);
        this.z.setClickable(false);
        this.f3130n.setImageResource(R.drawable.lib_cashier_sdk_pay_channel_right_arrow);
        this.v.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        this.w.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        this.t.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F5F5F5));
        this.x.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
        this.y.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        this.u.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F5F5F5));
        this.C.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
        this.D.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
        this.A.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        this.B.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FA2C19));
        this.A.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_C2C2C2, JDDarkUtil.COLOR_555353));
        this.z.setBackgroundResource(R.drawable.lib_cashier_sdk_bg_large_payment_confirm_selector_disable);
    }

    private void a0() {
        this.f3129m.setEnabled(true);
        this.f3129m.setClickable(true);
        this.z.setClickable(true);
        this.f3130n.setImageResource(R.drawable.lib_cashier_sdk_pay_channel_right_arrow);
        this.v.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        this.w.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        this.t.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F5F5F5));
        this.x.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
        this.y.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        this.u.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F5F5F5));
        this.C.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
        this.D.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
        this.A.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        this.B.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FA2C19));
        this.z.setBackgroundResource(R.drawable.lib_cashier_sdk_pay_do_pay_btn_bg);
    }

    private void b0(BaiTiaoPayPlanResponse baiTiaoPayPlanResponse) {
        Payment payment = this.f3125i;
        if (payment == null || baiTiaoPayPlanResponse == null) {
            return;
        }
        payment.selectedCoupon = baiTiaoPayPlanResponse.selectedCoupon;
        payment.selectedPlanFee = com.jd.lib.cashier.sdk.b.c.a.a(baiTiaoPayPlanResponse);
    }

    private void c0() {
        try {
            y.e(new h());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(Payment payment) {
        if (payment != null) {
            this.f3125i = payment;
            AllCoupons allCoupons = payment.allCoupons;
            if (allCoupons != null) {
                payment.selectedCommonCoupon = com.jd.lib.cashier.sdk.b.c.b.a(payment.defaultCouponId, allCoupons.couponAndCutOffs);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        Handler handler;
        if (!g0.a(this.f3128l) || (handler = this.f3123g) == null) {
            return;
        }
        handler.postDelayed(new g(), 200L);
    }

    public void C() {
        Payment payment;
        if (this.G == null || (payment = this.f3125i) == null || !payment.canUse() || !com.jd.lib.cashier.sdk.h.h.g.a(this.f3125i.code)) {
            return;
        }
        Payment payment2 = this.f3125i;
        payment2.currentCoupon = payment2.defaultCoupon;
        PlanFeeEntity planFeeEntity = payment2.defaultPlanFee;
        payment2.currentPlanFee = planFeeEntity;
        payment2.targetPlanFee = planFeeEntity;
        payment2.targetCoupon = null;
        this.G.m(payment2, "0");
    }

    public void H(CreditPayEntity creditPayEntity) {
        r.a("CashierCreditPayDialogProxy", this.f3127k);
        this.f3124h = false;
        this.f3127k = creditPayEntity;
        E();
        J();
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: L  reason: merged with bridge method [inline-methods] */
    public void onChanged(com.jd.lib.cashier.sdk.e.a.b.a aVar) {
        Payment payment;
        if (aVar != null) {
            if (aVar.a != 2048) {
                if (TextUtils.equals(aVar.b, "1312") && (payment = this.f3125i) != null) {
                    payment.selectedCoupon = com.jd.lib.cashier.sdk.b.c.c.b();
                    P();
                    T();
                    V();
                }
            } else {
                b0(aVar.f3313c);
                Q(aVar.f3313c);
                W();
                T();
                V();
                A();
            }
            this.f3124h = true;
        }
    }

    public void R() {
        X();
        W();
        U();
        V();
        O();
        N();
        Payment payment = this.f3125i;
        if (payment != null && payment.canUse()) {
            a0();
        } else {
            Z();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        com.jd.lib.cashier.sdk.creditpay.dialog.c cVar = this.G;
        if (cVar != null && cVar.o()) {
            this.G.onChangeSkin();
        }
        com.jd.lib.cashier.sdk.creditpay.dialog.d dVar = this.I;
        if (dVar != null && dVar.p()) {
            this.I.onChangeSkin();
        }
        com.jd.lib.cashier.sdk.creditpay.dialog.b bVar = this.H;
        if (bVar != null && bVar.o()) {
            this.H.onChangeSkin();
        }
        Payment payment = this.f3125i;
        if (payment != null && payment.canUse()) {
            a0();
        } else {
            Z();
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        c0();
        com.jd.lib.cashier.sdk.creditpay.dialog.d dVar = this.I;
        if (dVar != null) {
            dVar.onDestroy();
            this.I = null;
        }
        com.jd.lib.cashier.sdk.creditpay.dialog.b bVar = this.H;
        if (bVar != null) {
            bVar.onDestroy();
            this.H = null;
        }
        com.jd.lib.cashier.sdk.creditpay.dialog.c cVar = this.G;
        if (cVar != null) {
            cVar.onDestroy();
            this.G = null;
        }
        if (this.f3128l != null) {
            this.f3128l = null;
        }
    }
}
