package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.ui.CashierBItemView;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.h.g.m;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinModel;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinWalletInfo;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.ProductInfo;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBankCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickGWJIconItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickJXJIconItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickPayChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierBPayChannelVirtualJDPayFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, m> {

    /* renamed from: i  reason: collision with root package name */
    private CashierBItemView f4060i;

    /* renamed from: j  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.h.d.a f4061j;

    /* renamed from: k  reason: collision with root package name */
    private m f4062k;

    /* renamed from: l  reason: collision with root package name */
    private Payment f4063l;

    /* renamed from: m  reason: collision with root package name */
    private final Runnable f4064m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Context f4065j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ String f4066k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ String f4067l;

        a(CashierBPayChannelVirtualJDPayFloor cashierBPayChannelVirtualJDPayFloor, Context context, String str, String str2) {
            this.f4065j = context;
            this.f4066k = str;
            this.f4067l = str2;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            Context context = this.f4065j;
            if (context instanceof FragmentActivity) {
                com.jd.lib.cashier.sdk.b.d.a.g((FragmentActivity) context, this.f4066k, this.f4067l);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Payment f4068g;

        b(Payment payment) {
            this.f4068g = payment;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Payment payment = this.f4068g;
            if (payment != null && payment.equals(CashierBPayChannelVirtualJDPayFloor.this.f4063l) && o0.a("CashierBPayChannelVirtualJDPayFloorcoupon")) {
                return;
            }
            if (com.jd.lib.cashier.sdk.h.h.g.c(this.f4068g.code)) {
                com.jd.lib.cashier.sdk.b.i.e.f("CLICK_BANK_COUPON_ON_PAYMENT_ITEM", new ClickBankCouponItemEvent(this.f4068g));
            }
            if (com.jd.lib.cashier.sdk.h.h.g.a(this.f4068g.code)) {
                r.b("CashierBPayChannelVirtualJDPayFloor", "EventBusManager sendEvent CLICK_COUPON_ON_PAYMENT_CHANNEL");
                com.jd.lib.cashier.sdk.b.i.e.f("click_baitiao_COUPON_on_item_key", new ClickBaiTiaoCouponItemEvent(this.f4068g));
            }
            CashierBPayChannelVirtualJDPayFloor.this.f4063l = this.f4068g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CashierBPayChannelVirtualJDPayFloor.this.f4064m != null) {
                CashierBPayChannelVirtualJDPayFloor.this.f4064m.run();
            }
        }
    }

    /* loaded from: classes14.dex */
    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CashierBPayChannelVirtualJDPayFloor.this.g();
        }
    }

    public CashierBPayChannelVirtualJDPayFloor(View view) {
        super(view);
        this.f4064m = new d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        List<com.jd.lib.cashier.sdk.d.a.e.a> list;
        com.jd.lib.cashier.sdk.h.d.a aVar;
        Payment payment;
        int i2 = 0;
        if ((this.f4062k == null || (aVar = this.f4061j) == null || ((payment = aVar.f3523e) != null && (payment == null || com.jd.lib.cashier.sdk.h.h.g.j(payment, aVar.b)))) ? false : true) {
            com.jd.lib.cashier.sdk.h.d.a aVar2 = this.f4061j;
            Payment payment2 = aVar2.f3524f;
            if (payment2 == null && (list = aVar2.f3522c) != null && list.size() > 0) {
                while (true) {
                    if (i2 >= this.f4061j.f3522c.size()) {
                        break;
                    }
                    com.jd.lib.cashier.sdk.d.a.e.a aVar3 = this.f4061j.f3522c.get(i2);
                    if (aVar3 instanceof x) {
                        x xVar = (x) aVar3;
                        if (xVar.a().jdPay && com.jd.lib.cashier.sdk.h.h.e.g(xVar.a())) {
                            payment2 = xVar.a();
                            break;
                        }
                    }
                    i2++;
                }
            }
            if (payment2 == null) {
                Context context = getConvertView().getContext();
                if (context instanceof CashierPayActivity) {
                    f0.c(context.getString(R.string.lib_cashier_sdk_credit_pay_no_way_pay_toast));
                    return;
                }
                return;
            }
            com.jd.lib.cashier.sdk.b.i.e.f("cashier_item_click", new ClickPayChannelItemEvent(payment2));
        }
    }

    private void h(m mVar) {
        Payment a2 = mVar.a();
        Context context = getConvertView().getContext();
        if (a2 == null) {
            return;
        }
        if (CashierPayChannelCode.JD_PAY_CREDIT.equals(a2.code)) {
            this.f4060i.n();
        } else if ("5".equals(a2.status) && com.jd.lib.cashier.sdk.h.h.g.a(a2.code)) {
            this.f4060i.n();
        } else {
            if ((com.jd.lib.cashier.sdk.h.h.g.a(a2.code) || com.jd.lib.cashier.sdk.h.h.g.c(a2.code) || com.jd.lib.cashier.sdk.h.h.g.f(a2.code)) ? false : true) {
                String str = a2.preferentiaNum;
                this.f4060i.m(str, false, null);
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
                this.f4060i.m(str2, true, new b(a2));
            } else {
                this.f4060i.m(str2, false, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void i(Payment payment, View view) {
        if (o0.a("jxjCashierBPayChannelVirtualJDPayFloor")) {
            return;
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_JXJ_ICON", new ClickJXJIconItemEvent(payment));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void j(Context context, Payment payment, View view) {
        if (o0.a("gwjCashierBPayChannelVirtualJDPayFloor")) {
            return;
        }
        if (context instanceof CashierPayActivity) {
            com.jd.lib.cashier.sdk.h.e.a.d().R((CashierPayActivity) context);
        }
        com.jd.lib.cashier.sdk.b.i.e.f("CLICK_PAYMENT_ITEM_GWJ_ICON", new ClickGWJIconItemEvent(payment.gouWuJinModel));
    }

    private void l() {
        this.f4060i.k(new c());
    }

    private void m(final Payment payment) {
        List<GouWuJinWalletInfo> list;
        List<ProductInfo> list2;
        final Context context = getConvertView().getContext();
        List<String> list3 = payment.iconList;
        if (list3 != null && !list3.isEmpty()) {
            int size = payment.iconList.size();
            this.f4060i.f(24, 20);
            if (size >= 2) {
                this.f4060i.o(payment.iconList.get(0));
                this.f4060i.t(payment.iconList.get(1));
                return;
            }
            this.f4060i.u();
            if (TextUtils.equals(CashierPayChannelCode.JD_PAY_JXJ, payment.code)) {
                if (payment.showSkuList && (list2 = payment.productInfos) != null && !list2.isEmpty()) {
                    this.f4060i.g();
                    this.f4060i.p(payment.iconList.get(0), "\u81a8\u80c0\u91d1\u8bf4\u660e");
                    this.f4060i.q(new View.OnClickListener() { // from class: com.jd.lib.cashier.sdk.pay.floors.j
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            CashierBPayChannelVirtualJDPayFloor.i(Payment.this, view);
                        }
                    });
                    return;
                }
                this.f4060i.r();
                return;
            } else if (TextUtils.equals("GOUWUJIN", payment.code)) {
                GouWuJinModel gouWuJinModel = payment.gouWuJinModel;
                if (gouWuJinModel != null && (list = gouWuJinModel.walletInfos) != null && !list.isEmpty()) {
                    if (context instanceof CashierPayActivity) {
                        com.jd.lib.cashier.sdk.h.e.a.d().S((CashierPayActivity) context);
                    }
                    this.f4060i.g();
                    this.f4060i.p(payment.iconList.get(0), "\u8d2d\u7269\u91d1\u8bf4\u660e");
                    this.f4060i.q(new View.OnClickListener() { // from class: com.jd.lib.cashier.sdk.pay.floors.i
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            CashierBPayChannelVirtualJDPayFloor.j(context, payment, view);
                        }
                    });
                    return;
                }
                this.f4060i.r();
                return;
            } else {
                this.f4060i.o(payment.iconList.get(0));
                return;
            }
        }
        this.f4060i.s();
    }

    private void n(com.jd.lib.cashier.sdk.h.d.a aVar, m mVar) {
        String str;
        if (this.f4060i == null) {
            return;
        }
        Context context = getConvertView().getContext();
        Payment a2 = mVar.a();
        Payment payment = aVar.f3523e;
        if (payment == null) {
            payment = a2;
        }
        this.f4060i.e(com.jd.lib.cashier.sdk.h.h.g.j(payment, aVar.b));
        this.f4060i.A();
        this.f4060i.v(a2.logo);
        if (!TextUtils.isEmpty(a2.channelDesc)) {
            this.f4060i.l(a2.channelDesc);
        } else {
            this.f4060i.l(a2.channelName);
        }
        this.f4060i.x(a2.tip);
        String str2 = "";
        if (!TextUtils.isEmpty(a2.extraInfo)) {
            str2 = a2.extraInfo;
            str = "";
        } else {
            CashierCommonPopConfig cashierCommonPopConfig = a2.toastModel;
            if (cashierCommonPopConfig != null) {
                str2 = cashierCommonPopConfig.message;
                str = cashierCommonPopConfig.confirmBtn;
            } else {
                str = "";
            }
        }
        this.f4060i.y(str2, new a(this, context, str2, str));
        this.f4060i.w(a2.statusDesc);
        m(a2);
        h(mVar);
        this.f4060i.z();
        this.f4060i.j(a2.moreInfoTip);
        this.f4060i.a("3".equals(a2.status));
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4060i == null) {
            this.f4060i = (CashierBItemView) view.findViewById(R.id.lib_cashier_pay_channel_floor_item);
            l();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, m mVar) {
        if (mVar != null) {
            this.f4062k = mVar;
            this.f4061j = aVar;
            n(aVar, mVar);
        }
    }
}
