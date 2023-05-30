package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.model.BaiTiaoExtraTip;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.ui.CashierAPayItemView;
import com.jd.lib.cashier.sdk.core.ui.widget.CashierAPayPlanView;
import com.jd.lib.cashier.sdk.core.ui.widget.CashierAPlanExpandView;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.d.b.a;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinModel;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinWalletInfo;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.lib.cashier.sdk.pay.bean.ProductInfo;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceMtaObject;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.AgreementServiceMapMap;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoPayPlanItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBankCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickGWJIconItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickJXJIconItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickPayChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.UpdateHeaderFloorInfo;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierAPayChannelListFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, com.jd.lib.cashier.sdk.h.g.c> {

    /* renamed from: i  reason: collision with root package name */
    private CashierAPayPlanView f3983i;

    /* renamed from: j  reason: collision with root package name */
    private CashierAPayItemView f3984j;

    /* renamed from: k  reason: collision with root package name */
    private CashierAPlanExpandView f3985k;

    /* renamed from: l  reason: collision with root package name */
    private View f3986l;

    /* renamed from: m  reason: collision with root package name */
    private View f3987m;

    /* renamed from: n  reason: collision with root package name */
    private x f3988n;
    private final Runnable o;
    private Payment p;

    /* loaded from: classes14.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CashierAPayChannelListFloor.this.o != null) {
                CashierAPayChannelListFloor.this.o.run();
            }
        }
    }

    /* loaded from: classes14.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CashierAPayChannelListFloor.this.f3984j != null) {
                Context context = CashierAPayChannelListFloor.this.getConvertView().getContext();
                Payment a = CashierAPayChannelListFloor.this.f3988n.a();
                if ((context instanceof CashierPayActivity ? ((CashierPayViewModel) ViewModelProviders.of((CashierPayActivity) context).get(CashierPayViewModel.class)).b().O : null) != a && com.jd.lib.cashier.sdk.h.h.g.g(a.status)) {
                    com.jd.lib.cashier.sdk.b.i.e.f("cashier_item_click", new ClickPayChannelItemEvent(a));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Context f3991j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ BaiTiaoExtraTip f3992k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(long j2, Context context, BaiTiaoExtraTip baiTiaoExtraTip) {
            super(j2);
            this.f3991j = context;
            this.f3992k = baiTiaoExtraTip;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            com.jd.lib.cashier.sdk.b.d.a.c(this.f3991j, this.f3992k.extraTipToast);
            Payment a = CashierAPayChannelListFloor.this.f3988n.a();
            if (this.f3991j instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().l((CashierPayActivity) this.f3991j, a.code);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class d extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Context f3994j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ Payment f3995k;

        d(CashierAPayChannelListFloor cashierAPayChannelListFloor, Context context, Payment payment) {
            this.f3994j = context;
            this.f3995k = payment;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            Context context = this.f3994j;
            if (context instanceof FragmentActivity) {
                CashierCommonPopConfig cashierCommonPopConfig = this.f3995k.toastModel;
                com.jd.lib.cashier.sdk.b.d.a.g((FragmentActivity) context, cashierCommonPopConfig.message, cashierCommonPopConfig.confirmBtn);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class e implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Payment f3996g;

        e(Payment payment) {
            this.f3996g = payment;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Payment payment = this.f3996g;
            if (payment != null && payment.equals(CashierAPayChannelListFloor.this.p) && o0.a("CashierAPayChannelListFloorcoupon")) {
                return;
            }
            if (com.jd.lib.cashier.sdk.h.h.g.c(this.f3996g.code)) {
                com.jd.lib.cashier.sdk.b.i.e.f("CLICK_BANK_COUPON_ON_PAYMENT_ITEM", new ClickBankCouponItemEvent(this.f3996g));
            } else if (com.jd.lib.cashier.sdk.h.h.g.a(this.f3996g.code)) {
                com.jd.lib.cashier.sdk.b.i.e.f("click_baitiao_COUPON_on_item_key", new ClickBaiTiaoCouponItemEvent(this.f3996g));
            }
            CashierAPayChannelListFloor.this.p = this.f3996g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class f implements OnPlanViewClickListener {
        final /* synthetic */ Context a;

        f(Context context) {
            this.a = context;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener
        public void onClick(@NonNull IPlanItemViewEntity iPlanItemViewEntity, @Nullable IPlanItemViewEntity iPlanItemViewEntity2) {
            if ((iPlanItemViewEntity instanceof PlanFeeEntity) && (iPlanItemViewEntity2 instanceof PlanFeeEntity)) {
                PlanFeeEntity planFeeEntity = (PlanFeeEntity) iPlanItemViewEntity;
                PlanFeeEntity planFeeEntity2 = (PlanFeeEntity) iPlanItemViewEntity2;
                r.b("CashierAPayChannelListFloor", "payPlanItemView initializePayPlanView payPlanView.setOnItemClickListener");
                Payment a = CashierAPayChannelListFloor.this.f3988n.a();
                com.jd.lib.cashier.sdk.h.e.a.d().F(this.a, a.code, planFeeEntity.getPlan(), planFeeEntity.getSkuSplitFlag() ? "1" : "0");
                com.jd.lib.cashier.sdk.b.i.e.g("CLICK_PAYMENT_CHANNEL_PAY_PLAN_ITEM", "baitiao_plan", new ClickBaiTiaoPayPlanItemEvent(planFeeEntity, planFeeEntity2, a));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class g implements ViewTreeObserver.OnGlobalLayoutListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ x f3998g;

        g(x xVar) {
            this.f3998g = xVar;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            x xVar;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 16) {
                CashierAPayChannelListFloor.this.f3984j.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else if (i2 >= 14) {
                CashierAPayChannelListFloor.this.f3984j.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
            if (CashierAPayChannelListFloor.this.f3984j == null || (xVar = this.f3998g) == null || !xVar.a().selected || !this.f3998g.a().clickEvent) {
                return;
            }
            this.f3998g.a().clickEvent = false;
            CashierAPayChannelListFloor.this.f3984j.clearFocus();
            CashierAPayChannelListFloor.this.f3984j.sendAccessibilityEvent(65536);
            CashierAPayChannelListFloor.this.f3984j.sendAccessibilityEvent(4);
            CashierAPayChannelListFloor.this.f3984j.sendAccessibilityEvent(8);
            CashierAPayChannelListFloor.this.f3984j.sendAccessibilityEvent(32768);
        }
    }

    public CashierAPayChannelListFloor(View view) {
        super(view);
        this.o = new b();
    }

    private void A(Payment payment) {
        CashierAPlanExpandView cashierAPlanExpandView = this.f3985k;
        if (cashierAPlanExpandView != null && cashierAPlanExpandView.getVisibility() == 0) {
            this.f3985k.e(getConvertView().getContext(), payment.serviceMap, payment.agreementServiceMap, payment);
        }
    }

    private void C(boolean z) {
        CashierAPayItemView cashierAPayItemView = this.f3984j;
        if (cashierAPayItemView == null) {
            return;
        }
        if (z) {
            cashierAPayItemView.S(0, DpiUtil.dip2px(getConvertView().getContext(), 16.0f), 0, DpiUtil.dip2px(getConvertView().getContext(), 12.0f));
        } else {
            cashierAPayItemView.S(0, DpiUtil.dip2px(getConvertView().getContext(), 16.0f), 0, DpiUtil.dip2px(getConvertView().getContext(), 16.0f));
        }
    }

    private void D(boolean z) {
        CashierAPlanExpandView cashierAPlanExpandView = this.f3985k;
        boolean z2 = cashierAPlanExpandView != null && cashierAPlanExpandView.getVisibility() == 0;
        CashierAPayPlanView cashierAPayPlanView = this.f3983i;
        if (cashierAPayPlanView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cashierAPayPlanView.getLayoutParams();
            if (z && z2) {
                layoutParams.setMargins(0, 0, 0, DpiUtil.dip2px(getConvertView().getContext(), 12.0f));
            } else {
                layoutParams.setMargins(0, 0, 0, DpiUtil.dip2px(getConvertView().getContext(), 16.0f));
            }
            this.f3983i.setLayoutParams(layoutParams);
        }
    }

    private void h(Payment payment) {
        View view = this.f3987m;
        if (view != null) {
            view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_0F000000));
            this.f3984j.a("3".equals(payment.status));
        }
    }

    private void j(List<IPlanItemViewEntity> list, String str) {
        Context context = getConvertView().getContext();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 < list.size() - 1) {
                IPlanItemViewEntity iPlanItemViewEntity = list.get(i2);
                if (iPlanItemViewEntity != null && !TextUtils.isEmpty(iPlanItemViewEntity.getFlagText())) {
                    z = true;
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        this.f3983i.h(list, str, z);
        this.f3983i.onChangeSkin();
        this.f3983i.j(new f(context));
    }

    private void k(x xVar) {
        Payment a2 = xVar.a();
        Context context = getConvertView().getContext();
        if (a2 == null) {
            return;
        }
        if (CashierPayChannelCode.JD_PAY_CREDIT.equals(a2.code)) {
            this.f3984j.w();
        } else if ("5".equals(a2.status) && com.jd.lib.cashier.sdk.h.h.g.a(a2.code)) {
            this.f3984j.w();
        } else {
            if ((com.jd.lib.cashier.sdk.h.h.g.a(a2.code) || com.jd.lib.cashier.sdk.h.h.g.c(a2.code) || com.jd.lib.cashier.sdk.h.h.g.f(a2.code)) ? false : true) {
                String str = a2.preferentiaNum;
                this.f3984j.v(str, false, null);
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
                this.f3984j.v(str2, true, new e(a2));
            } else {
                this.f3984j.v(str2, false, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void m(Payment payment, View view) {
        if (o0.a("jxjCashierAPayChannelListFloor")) {
            return;
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_JXJ_ICON", new ClickJXJIconItemEvent(payment));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void n(Context context, Payment payment, View view) {
        if (o0.a("gwjCashierAPayChannelListFloor")) {
            return;
        }
        if (context instanceof CashierPayActivity) {
            com.jd.lib.cashier.sdk.h.e.a.d().R((CashierPayActivity) context);
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_GWJ_ICON", new ClickGWJIconItemEvent(payment.gouWuJinModel));
    }

    private void p(@NonNull x xVar) {
        String str;
        boolean z;
        try {
            Context context = getConvertView().getContext();
            for (IPlanItemViewEntity iPlanItemViewEntity : xVar.a().planFeeEntityList) {
                if (iPlanItemViewEntity instanceof PlanFeeEntity) {
                    str = ((PlanFeeEntity) iPlanItemViewEntity).getSkuSplitFlag() ? "1" : "0";
                    z = iPlanItemViewEntity.isChecked();
                } else {
                    str = "0";
                    z = false;
                }
                com.jd.lib.cashier.sdk.h.e.a.d().T(context, iPlanItemViewEntity.getPlanNum(), z, str, xVar.a().code);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void s(a.b bVar) {
        View view = this.f3986l;
        if (view != null) {
            if (a.b.NORMAL == bVar) {
                view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            } else if (a.b.FLOOR_BOTTOM == bVar) {
                if (JDDarkUtil.isDarkMode()) {
                    this.f3986l.setBackgroundResource(R.drawable.lib_cashier_sdk_a_bottom_corner_dark_bg);
                } else {
                    this.f3986l.setBackgroundResource(R.drawable.lib_cashier_sdk_a_bottom_corner_bg);
                }
            } else if (a.b.FLOOR_TOP_AND_NORMAL == bVar) {
                if (JDDarkUtil.isDarkMode()) {
                    this.f3986l.setBackgroundResource(R.drawable.lib_cashier_sdk_a_top_corner_dark_bg);
                } else {
                    this.f3986l.setBackgroundResource(R.drawable.lib_cashier_sdk_a_top_corner_bg);
                }
            } else if (a.b.FLOOR_TOP_AND_BOTTOM == bVar) {
                if (JDDarkUtil.isDarkMode()) {
                    this.f3986l.setBackgroundResource(R.drawable.lib_cashier_sdk_a_top_bottom_corner_dark_bg);
                } else {
                    this.f3986l.setBackgroundResource(R.drawable.lib_cashier_sdk_a_top_bottom_corner_bg);
                }
            }
        }
    }

    private void t(Payment payment) {
        s(payment.splitLineType);
    }

    private void u(final Payment payment) {
        List<GouWuJinWalletInfo> list;
        List<ProductInfo> list2;
        final Context context = getConvertView().getContext();
        List<String> list3 = payment.iconList;
        if (list3 != null && !list3.isEmpty()) {
            int size = payment.iconList.size();
            this.f3984j.l(24, 20);
            if (size >= 2) {
                this.f3984j.x(payment.iconList.get(0));
                this.f3984j.C(payment.iconList.get(1));
                return;
            }
            this.f3984j.D();
            if (TextUtils.equals(CashierPayChannelCode.JD_PAY_JXJ, payment.code)) {
                if (payment.showSkuList && (list2 = payment.productInfos) != null && !list2.isEmpty()) {
                    this.f3984j.m();
                    this.f3984j.y(payment.iconList.get(0), "\u81a8\u80c0\u91d1\u8bf4\u660e");
                    this.f3984j.z(new View.OnClickListener() { // from class: com.jd.lib.cashier.sdk.pay.floors.c
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            CashierAPayChannelListFloor.m(Payment.this, view);
                        }
                    });
                    return;
                }
                this.f3984j.A();
                return;
            } else if (TextUtils.equals("GOUWUJIN", payment.code)) {
                GouWuJinModel gouWuJinModel = payment.gouWuJinModel;
                if (gouWuJinModel != null && (list = gouWuJinModel.walletInfos) != null && !list.isEmpty()) {
                    if (context instanceof CashierPayActivity) {
                        com.jd.lib.cashier.sdk.h.e.a.d().S((CashierPayActivity) context);
                    }
                    this.f3984j.m();
                    this.f3984j.y(payment.iconList.get(0), "\u8d2d\u7269\u91d1\u8bf4\u660e");
                    this.f3984j.z(new View.OnClickListener() { // from class: com.jd.lib.cashier.sdk.pay.floors.d
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            CashierAPayChannelListFloor.n(context, payment, view);
                        }
                    });
                    return;
                }
                this.f3984j.A();
                return;
            } else {
                this.f3984j.x(payment.iconList.get(0));
                return;
            }
        }
        this.f3984j.B();
    }

    private void v(Payment payment, boolean z) {
        if ("9".equals(payment.status)) {
            this.f3984j.g();
            this.f3984j.f();
            return;
        }
        this.f3984j.P();
        this.f3984j.f();
        this.f3984j.k(z);
    }

    private void w(Payment payment, boolean z) {
        View view = this.f3987m;
        if (view == null) {
            return;
        }
        a.b bVar = payment.splitLineType;
        if (bVar != a.b.NORMAL && bVar != a.b.FLOOR_TOP_AND_NORMAL) {
            view.setVisibility(8);
        } else {
            view.setVisibility(0);
        }
    }

    private void x(Payment payment, boolean z) {
        CashierAPlanExpandView cashierAPlanExpandView = this.f3985k;
        if (cashierAPlanExpandView == null) {
            return;
        }
        if (z) {
            BaiTiaoExtraTip baiTiaoExtraTip = payment.baiTiaoExtraTip;
            PlanServiceMap planServiceMap = payment.serviceMap;
            AgreementServiceMapMap agreementServiceMapMap = payment.agreementServiceMap;
            boolean z2 = (baiTiaoExtraTip == null || TextUtils.isEmpty(baiTiaoExtraTip.extraTipStr)) ? false : true;
            boolean z3 = (planServiceMap == null || TextUtils.isEmpty(planServiceMap.planServiceFeeStr)) ? false : true;
            boolean z4 = (agreementServiceMapMap == null || TextUtils.isEmpty(agreementServiceMapMap.agreementName)) ? false : true;
            if (!z2 && !z3 && !z4) {
                this.f3985k.setVisibility(8);
                return;
            } else {
                this.f3985k.setVisibility(0);
                return;
            }
        }
        cashierAPlanExpandView.setVisibility(8);
    }

    private void z(com.jd.lib.cashier.sdk.h.g.c cVar, boolean z) {
        Payment a2 = cVar.a();
        Context context = getConvertView().getContext();
        CashierAPayItemView cashierAPayItemView = this.f3984j;
        if (cashierAPayItemView != null) {
            cashierAPayItemView.E(a2.logo);
            if (CashierPayChannelCode.JD_PAY_HONEY.equals(a2.code)) {
                this.f3984j.s(a2.channelNamePre);
            } else {
                this.f3984j.s(a2.channelName);
            }
            this.f3984j.G(!TextUtils.isEmpty(a2.tip) ? a2.tip : a2.investorDoc);
            CashierCommonPopConfig cashierCommonPopConfig = a2.toastModel;
            if (cashierCommonPopConfig != null) {
                this.f3984j.H(cashierCommonPopConfig.message, new d(this, context, a2));
            } else {
                this.f3984j.H("", null);
            }
            this.f3984j.F(a2.statusDesc);
            u(a2);
            this.f3984j.u(a2.channelNameTail);
            this.f3984j.t(a2.channelNameMiddle);
            k(cVar);
            this.f3984j.K();
            this.f3984j.p(a2.moreInfoTip);
            if (TextUtils.equals("3", a2.status)) {
                this.f3984j.d();
            }
            v(a2, a2.selected);
            CashierAPayItemView cashierAPayItemView2 = this.f3984j;
            cashierAPayItemView2.J(cashierAPayItemView2.c(a2.status, com.jd.lib.cashier.sdk.h.h.g.b(a2.code)));
            w(a2, z);
            t(a2);
            this.f3984j.T(a2, getConvertView().getContext());
        }
    }

    protected void B() {
        if (this.f3988n == null || this.f3985k == null || this.f3983i == null) {
            return;
        }
        Context context = getConvertView().getContext();
        if (this.f3985k.getVisibility() == 0) {
            BaiTiaoExtraTip baiTiaoExtraTip = this.f3988n.a().baiTiaoExtraTip;
            if (baiTiaoExtraTip != null && !TextUtils.isEmpty(baiTiaoExtraTip.extraTipStr)) {
                this.f3985k.c(baiTiaoExtraTip.extraTipStr);
                CashierCommonPopConfig cashierCommonPopConfig = baiTiaoExtraTip.extraTipToast;
                if (cashierCommonPopConfig != null) {
                    if (TextUtils.isEmpty(cashierCommonPopConfig.cancelBtn) && TextUtils.isEmpty(baiTiaoExtraTip.extraTipToast.confirmBtn)) {
                        return;
                    }
                    if (context instanceof CashierPayActivity) {
                        com.jd.lib.cashier.sdk.h.e.a.d().m((CashierPayActivity) context);
                    }
                    this.f3985k.b(new c(1000L, context, baiTiaoExtraTip));
                    return;
                }
                return;
            }
            this.f3985k.c("");
        }
    }

    protected void i(x xVar) {
        if (xVar != null) {
            Payment a2 = xVar.a();
            Context context = getConvertView().getContext();
            if (a2 == null || a2.hasPaymentExpo || a2.isSourceFromDialogSelected) {
                return;
            }
            a2.hasPaymentExpo = true;
            com.jd.lib.cashier.sdk.h.e.a.d().V((CashierPayActivity) context, a2.code, com.jd.lib.cashier.sdk.h.h.e.b(a2), a2.canUse(), a2.isCombine(), a2.defaultSelected, a2.openXjkLargePayFlag, a2.changetag, a2.hasCouponExpo ? "1" : "0");
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f3984j == null) {
            CashierAPayItemView cashierAPayItemView = (CashierAPayItemView) getView(R.id.lib_cashier_a_pay_channel_floor_item);
            this.f3984j = cashierAPayItemView;
            cashierAPayItemView.r(new a());
        }
        if (this.f3985k == null) {
            this.f3985k = (CashierAPlanExpandView) getView(R.id.lib_cashier_a_pay_channel_floor_item_expand);
        }
        if (this.f3983i == null) {
            this.f3983i = (CashierAPayPlanView) getView(R.id.lib_cashier_a_pay_channel_floor_item_plan);
        }
        if (this.f3986l == null) {
            this.f3986l = getView(R.id.lib_cashier_sdk_a_pay_channel_list_floor_root);
        }
        if (this.f3987m == null) {
            this.f3987m = getView(R.id.lib_cashier_a_pay_channel_list_floor_view);
        }
    }

    protected boolean l(@NonNull x xVar) {
        Payment a2 = xVar.a();
        boolean z = a2.selected;
        boolean a3 = com.jd.lib.cashier.sdk.h.h.g.a(a2.code);
        boolean equals = "5".equals(a2.status);
        boolean equals2 = TextUtils.equals(a2.status, "3");
        boolean z2 = a2.baitiaoPlanInfo != null && a3;
        CashierAPayPlanView cashierAPayPlanView = this.f3983i;
        if (cashierAPayPlanView == null) {
            return false;
        }
        if (equals2) {
            cashierAPayPlanView.setVisibility(8);
            return false;
        } else if (equals || !(z || z2)) {
            cashierAPayPlanView.setVisibility(8);
            return false;
        } else if (a3) {
            List<IPlanItemViewEntity> list = a2.planFeeEntityList;
            if (list != null && list.size() > 0) {
                this.f3983i.setVisibility(0);
                return true;
            }
            this.f3983i.setVisibility(8);
            return false;
        } else {
            cashierAPayPlanView.setVisibility(8);
            return false;
        }
    }

    protected void o(x xVar) {
        try {
            if (this.f3984j == null || !com.jd.lib.cashier.sdk.core.utils.g.a(getConvertView().getContext())) {
                return;
            }
            this.f3984j.getViewTreeObserver().addOnGlobalLayoutListener(new g(xVar));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected void q(@NonNull x xVar) {
        p(xVar);
        j(xVar.a().planFeeEntityList, xVar.a().mianxiHighlight);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: r  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, com.jd.lib.cashier.sdk.h.g.c cVar) {
        if (cVar != null) {
            this.f3988n = cVar;
            boolean l2 = l(cVar);
            if (l2) {
                q(cVar);
            }
            z(cVar, l2);
            x(cVar.a(), l2);
            A(cVar.a());
            B();
            y(cVar);
            h(cVar.a());
            D(l2);
            C(l2);
            i(cVar);
            o(cVar);
        }
    }

    protected void y(x xVar) {
        UpdateHeaderFloorInfo updateHeaderFloorInfo;
        if (xVar.a().selected) {
            TopPriceMtaObject topPriceMtaObject = new TopPriceMtaObject();
            com.jd.lib.cashier.sdk.pay.dialog.e eVar = xVar.a().selectedCouponEntity;
            topPriceMtaObject.code = xVar.a().code;
            topPriceMtaObject.uniqueChannelId = com.jd.lib.cashier.sdk.h.h.e.b(xVar.a());
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
}
