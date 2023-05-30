package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.ui.CashierSupervisionItemView;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.d.b.a;
import com.jd.lib.cashier.sdk.h.g.t;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinModel;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinWalletInfo;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceMtaObject;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBankCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickGWJIconItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickOtherJDPayItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickPayChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.UpdateHeaderFloorInfo;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierPayChannelGridFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, t> {

    /* renamed from: i  reason: collision with root package name */
    private CashierSupervisionItemView f4097i;

    /* renamed from: j  reason: collision with root package name */
    private t f4098j;

    /* renamed from: k  reason: collision with root package name */
    private Payment f4099k;

    /* loaded from: classes14.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Payment a = CashierPayChannelGridFloor.this.f4098j.a();
            Context context = CashierPayChannelGridFloor.this.getConvertView().getContext();
            if ((context instanceof CashierPayActivity ? ((CashierPayViewModel) ViewModelProviders.of((CashierPayActivity) context).get(CashierPayViewModel.class)).b().O : null) == a) {
                return;
            }
            if ("moreInfo".equals(a.code)) {
                com.jd.lib.cashier.sdk.b.i.e.f("CLICK_OTHER_JD_PAYMENT_CHANNEL_ITEM", new ClickOtherJDPayItemEvent());
            } else if (com.jd.lib.cashier.sdk.h.h.g.g(a.status)) {
                com.jd.lib.cashier.sdk.b.i.e.f("cashier_item_click", new ClickPayChannelItemEvent(a));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Context f4101j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ Payment f4102k;

        b(CashierPayChannelGridFloor cashierPayChannelGridFloor, Context context, Payment payment) {
            this.f4101j = context;
            this.f4102k = payment;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            if (this.f4101j instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().R((CashierPayActivity) this.f4101j);
            }
            if (o0.a("gwjCashierPaySquareChannelFloor")) {
                return;
            }
            com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_GWJ_ICON", new ClickGWJIconItemEvent(this.f4102k.gouWuJinModel));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Context f4103j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ Payment f4104k;

        c(CashierPayChannelGridFloor cashierPayChannelGridFloor, Context context, Payment payment) {
            this.f4103j = context;
            this.f4104k = payment;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            Context context = this.f4103j;
            if (context instanceof FragmentActivity) {
                CashierCommonPopConfig cashierCommonPopConfig = this.f4104k.toastModel;
                com.jd.lib.cashier.sdk.b.d.a.g((FragmentActivity) context, cashierCommonPopConfig.message, cashierCommonPopConfig.confirmBtn);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class d implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Payment f4105g;

        d(Payment payment) {
            this.f4105g = payment;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Payment payment = this.f4105g;
            if (payment != null && payment.equals(CashierPayChannelGridFloor.this.f4099k) && o0.a("CashierPaySquareChannelFloorcoupon")) {
                return;
            }
            if (com.jd.lib.cashier.sdk.h.h.g.c(this.f4105g.code)) {
                com.jd.lib.cashier.sdk.b.i.e.f("CLICK_BANK_COUPON_ON_PAYMENT_ITEM", new ClickBankCouponItemEvent(this.f4105g));
            }
            if (com.jd.lib.cashier.sdk.h.h.g.a(this.f4105g.code)) {
                r.b("CashierPaySquareChannelFloor", "EventBusManager sendEvent CLICK_COUPON_ON_PAYMENT_CHANNEL");
                com.jd.lib.cashier.sdk.b.i.e.f("click_baitiao_COUPON_on_item_key", new ClickBaiTiaoCouponItemEvent(this.f4105g));
            }
            CashierPayChannelGridFloor.this.f4099k = this.f4105g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class e implements ViewTreeObserver.OnGlobalLayoutListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ x f4107g;

        e(x xVar) {
            this.f4107g = xVar;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            x xVar;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 16) {
                CashierPayChannelGridFloor.this.f4097i.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else if (i2 >= 14) {
                CashierPayChannelGridFloor.this.f4097i.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
            if (CashierPayChannelGridFloor.this.f4097i == null || (xVar = this.f4107g) == null || !xVar.a().selected || !this.f4107g.a().clickEvent) {
                return;
            }
            this.f4107g.a().clickEvent = false;
            CashierPayChannelGridFloor.this.f4097i.clearFocus();
            CashierPayChannelGridFloor.this.f4097i.sendAccessibilityEvent(65536);
            CashierPayChannelGridFloor.this.f4097i.requestFocus();
            CashierPayChannelGridFloor.this.f4097i.sendAccessibilityEvent(4);
            CashierPayChannelGridFloor.this.f4097i.sendAccessibilityEvent(8);
            CashierPayChannelGridFloor.this.f4097i.sendAccessibilityEvent(32768);
        }
    }

    public CashierPayChannelGridFloor(View view) {
        super(view);
    }

    private void g(Payment payment) {
        int i2;
        int dip2px;
        try {
            DisplayMetrics i3 = y.i();
            if (i3 != null) {
                int dip2px2 = ((((i3.widthPixels - DpiUtil.dip2px(getConvertView().getContext(), 20.0f)) / 2) - DpiUtil.dip2px(getConvertView().getContext(), 4.0f)) - DpiUtil.dip2px(getConvertView().getContext(), 10.0f)) - DpiUtil.dip2px(getConvertView().getContext(), 10.0f);
                int dip2px3 = DpiUtil.dip2px(getConvertView().getContext(), 24.0f);
                int dip2px4 = DpiUtil.dip2px(getConvertView().getContext(), 12.0f);
                int dip2px5 = DpiUtil.dip2px(getConvertView().getContext(), 16.0f);
                String str = TextUtils.isEmpty(payment.channelDesc) ? payment.channelName : payment.channelDesc;
                Paint paint = new Paint();
                paint.setTextSize(i3.density * 14.0f);
                int measureText = (int) paint.measureText(str);
                int dip2px6 = DpiUtil.dip2px(getConvertView().getContext(), 24.0f);
                int dip2px7 = DpiUtil.dip2px(getConvertView().getContext(), 24.0f);
                boolean z = true;
                boolean z2 = this.f4097i.b() != null && this.f4097i.b().getVisibility() == 0 && this.f4097i.d() != null && this.f4097i.d().getVisibility() == 0;
                boolean z3 = this.f4097i.b() != null && this.f4097i.b().getVisibility() == 0;
                if (this.f4097i.d() == null || this.f4097i.d().getVisibility() != 0) {
                    z = false;
                }
                if (z2) {
                    i2 = (((dip2px2 - dip2px3) - dip2px5) - dip2px4) - DpiUtil.dip2px(getConvertView().getContext(), 6.0f);
                    dip2px = DpiUtil.dip2px(getConvertView().getContext(), 2.0f);
                } else if (z3) {
                    i2 = (dip2px2 - dip2px3) - dip2px5;
                    dip2px = DpiUtil.dip2px(getConvertView().getContext(), 6.0f);
                } else if (z) {
                    i2 = ((dip2px2 - dip2px3) - dip2px4) - DpiUtil.dip2px(getConvertView().getContext(), 6.0f);
                    dip2px = DpiUtil.dip2px(getConvertView().getContext(), 2.0f);
                } else {
                    i2 = dip2px2 - dip2px3;
                    dip2px = DpiUtil.dip2px(getConvertView().getContext(), 6.0f);
                }
                int i4 = i2 - dip2px;
                if (this.f4097i.f() != null) {
                    this.f4097i.f().setMaxWidth(i4 - DpiUtil.dip2px(getConvertView().getContext(), 6.0f));
                }
                int i5 = measureText + dip2px6;
                u(payment, DpiUtil.dip2px(getConvertView().getContext(), 2.0f) + i5 + dip2px7 + DpiUtil.dip2px(getConvertView().getContext(), 2.0f), i5 + DpiUtil.dip2px(getConvertView().getContext(), 2.0f), i4 - DpiUtil.dip2px(getConvertView().getContext(), 12.0f));
            }
        } catch (Exception e2) {
            this.f4097i.D();
            e2.printStackTrace();
        }
    }

    private void h(t tVar) {
        if (tVar != null) {
            Payment a2 = tVar.a();
            Context context = getConvertView().getContext();
            if (a2 == null || a2.hasPaymentExpo || a2.isSourceFromDialogSelected) {
                return;
            }
            a2.hasPaymentExpo = true;
            if (TextUtils.equals(a2.code, "moreInfo")) {
                com.jd.lib.cashier.sdk.h.e.a.d().N(context);
            } else if (context instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().V((FragmentActivity) context, a2.code, com.jd.lib.cashier.sdk.h.h.e.b(a2), a2.canUse(), a2.isCombine(), a2.defaultSelected, a2.openXjkLargePayFlag, a2.changetag, a2.hasCouponExpo ? "1" : "0");
            }
        }
    }

    private void i(t tVar) {
        Payment a2 = tVar.a();
        if (a2 == null) {
            return;
        }
        if (CashierPayChannelCode.JD_PAY_CREDIT.equals(a2.code)) {
            this.f4097i.x();
        } else if ("5".equals(a2.status) && com.jd.lib.cashier.sdk.h.h.g.a(a2.code)) {
            this.f4097i.x();
        } else {
            Context context = getConvertView().getContext();
            if ((com.jd.lib.cashier.sdk.h.h.g.a(a2.code) || com.jd.lib.cashier.sdk.h.h.g.c(a2.code) || com.jd.lib.cashier.sdk.h.h.g.f(a2.code)) ? false : true) {
                String str = a2.preferentiaNum;
                this.f4097i.w(str, false, null);
                if (TextUtils.isEmpty(str) || a2.hasCouponExpo || a2.isSourceFromDialogSelected) {
                    return;
                }
                a2.hasCouponExpo = true;
                com.jd.lib.cashier.sdk.h.e.a.d().M(context, a2.code, com.jd.lib.cashier.sdk.h.h.e.b(a2));
                return;
            }
            String str2 = a2.preferentiaNum;
            com.jd.lib.cashier.sdk.pay.dialog.e eVar = a2.selectedCouponEntity;
            if (eVar != null) {
                str2 = eVar.getTitleName();
            }
            if (!TextUtils.isEmpty(str2) && !a2.hasCouponExpo && !a2.isSourceFromDialogSelected) {
                a2.hasCouponExpo = true;
                com.jd.lib.cashier.sdk.h.e.a.d().M(context, a2.code, com.jd.lib.cashier.sdk.h.h.e.b(a2));
            }
            if ("1".equals(a2.canSelectCoupon)) {
                this.f4097i.w(str2, true, new d(a2));
            } else {
                this.f4097i.w(str2, false, null);
            }
        }
    }

    private void j(t tVar) {
        Payment a2 = tVar.a();
        if ("5".equals(a2.status) || "1".equals(a2.status)) {
            this.f4097i.J("");
            i(tVar);
            n(a2);
            if (!this.f4097i.g()) {
                this.f4097i.x();
                this.f4097i.H();
                this.f4097i.I(a2.tip);
            } else {
                this.f4097i.I("");
            }
        } else {
            o(a2);
        }
        if (!this.f4097i.c()) {
            this.f4097i.K(false);
        } else {
            this.f4097i.K(true);
        }
    }

    private void m(Payment payment) {
        List<GouWuJinWalletInfo> list;
        if (payment == null) {
            return;
        }
        this.f4097i.Q(false, "", null);
        Context context = getConvertView().getContext();
        if (TextUtils.equals("GOUWUJIN", payment.code)) {
            GouWuJinModel gouWuJinModel = payment.gouWuJinModel;
            if (gouWuJinModel == null || (list = gouWuJinModel.walletInfos) == null || list.isEmpty()) {
                return;
            }
            if (context instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().S((CashierPayActivity) context);
            }
            this.f4097i.Q(true, "\u8d2d\u7269\u91d1\u8bf4\u660e", new b(this, context, payment));
        } else if (payment.toastModel == null) {
        } else {
            this.f4097i.Q(true, "", new c(this, context, payment));
        }
    }

    private void n(Payment payment) {
        this.f4097i.N();
        this.f4097i.r(payment.moreInfoTip);
    }

    private void o(Payment payment) {
        this.f4097i.x();
        this.f4097i.H();
        if (!com.jd.lib.cashier.sdk.h.h.g.n(payment.code)) {
            this.f4097i.I(payment.tip);
            return;
        }
        this.f4097i.I("");
        this.f4097i.u();
        this.f4097i.z();
        this.f4097i.J(payment.regulatorCantUseDesc);
    }

    private void p(a.b bVar) {
        this.f4097i.L(bVar);
    }

    private void q(Payment payment) {
        p(payment.splitLineType);
    }

    private void r(Payment payment) {
        g(payment);
    }

    private void s(Payment payment, boolean z) {
        if ("moreInfo".equals(payment.code)) {
            this.f4097i.j();
            this.f4097i.O();
        } else if ("9".equals(payment.status)) {
            this.f4097i.j();
            this.f4097i.i();
        } else {
            this.f4097i.P();
            this.f4097i.i();
            this.f4097i.m(z);
        }
    }

    private void t(t tVar) {
        UpdateHeaderFloorInfo updateHeaderFloorInfo;
        if (tVar.a().selected) {
            TopPriceMtaObject topPriceMtaObject = new TopPriceMtaObject();
            com.jd.lib.cashier.sdk.pay.dialog.e eVar = tVar.a().selectedCouponEntity;
            topPriceMtaObject.code = tVar.a().code;
            topPriceMtaObject.uniqueChannelId = com.jd.lib.cashier.sdk.h.h.e.b(tVar.a());
            if (eVar == null) {
                topPriceMtaObject.couponType = "0";
                updateHeaderFloorInfo = new UpdateHeaderFloorInfo(null, topPriceMtaObject);
            } else {
                topPriceMtaObject.couponType = eVar.getCutOffType();
                updateHeaderFloorInfo = new UpdateHeaderFloorInfo(eVar.getTopPriceAnimationInfo(), topPriceMtaObject);
            }
            com.jd.lib.cashier.sdk.b.i.e.f("update_top_floor_price_info", updateHeaderFloorInfo);
        }
    }

    private void u(Payment payment, int i2, int i3, int i4) {
        List<String> list = payment.iconList;
        if (list != null && !list.isEmpty()) {
            if (payment.iconList.size() >= 2) {
                x(payment, i2, i3, i4);
                return;
            } else {
                v(payment, i3, i4);
                return;
            }
        }
        this.f4097i.D();
    }

    private void v(Payment payment, int i2, int i3) {
        this.f4097i.F();
        if (i2 > i3) {
            this.f4097i.C();
        } else {
            this.f4097i.A(payment.iconList.get(0));
        }
    }

    private void w(t tVar) {
        Payment a2 = tVar.a();
        CashierSupervisionItemView cashierSupervisionItemView = this.f4097i;
        if (cashierSupervisionItemView != null) {
            cashierSupervisionItemView.G(a2.logo);
            this.f4097i.v(TextUtils.isEmpty(a2.channelDesc) ? a2.channelName : a2.channelDesc);
            m(a2);
            this.f4097i.n(com.jd.lib.cashier.sdk.h.h.g.n(a2.code));
            this.f4097i.t(a2.availableBalance);
            this.f4097i.y(a2.investorDoc);
            j(tVar);
            if (TextUtils.equals("3", a2.status)) {
                this.f4097i.h();
            }
            s(a2, a2.selected);
            r(a2);
            CashierSupervisionItemView cashierSupervisionItemView2 = this.f4097i;
            cashierSupervisionItemView2.M(cashierSupervisionItemView2.e(a2.status, com.jd.lib.cashier.sdk.h.h.g.b(a2.code)));
            this.f4097i.a("3".equals(a2.status));
            this.f4097i.o(a2.rootViewHeight);
            q(a2);
        }
    }

    private void x(Payment payment, int i2, int i3, int i4) {
        if (i2 <= i4) {
            this.f4097i.A(payment.iconList.get(0));
            this.f4097i.E(payment.iconList.get(1));
        } else if (i3 > i4) {
            this.f4097i.C();
        } else {
            this.f4097i.A(payment.iconList.get(0));
            this.f4097i.F();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4097i == null) {
            CashierSupervisionItemView cashierSupervisionItemView = (CashierSupervisionItemView) view.findViewById(R.id.lib_cashier_pay_channel_floor_item);
            this.f4097i = cashierSupervisionItemView;
            cashierSupervisionItemView.s(new a());
        }
    }

    protected void k(x xVar) {
        try {
            AccessibilityManager accessibilityManager = (AccessibilityManager) getConvertView().getContext().getSystemService("accessibility");
            if (this.f4097i == null || accessibilityManager == null || !accessibilityManager.isEnabled()) {
                return;
            }
            this.f4097i.getViewTreeObserver().addOnGlobalLayoutListener(new e(xVar));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, t tVar) {
        this.f4098j = tVar;
        if (tVar != null) {
            w(tVar);
            t(tVar);
            h(tVar);
            k(tVar);
        }
    }
}
