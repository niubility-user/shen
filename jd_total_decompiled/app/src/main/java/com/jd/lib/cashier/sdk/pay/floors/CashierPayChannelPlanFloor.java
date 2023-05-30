package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.model.BaiTiaoExtraTip;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.PlanRowEntity;
import com.jd.lib.cashier.sdk.core.model.PopBusinessMap;
import com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener;
import com.jd.lib.cashier.sdk.core.ui.widget.PayPlanView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.h.g.v;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickBaiTiaoPayPlanItemEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierPayChannelPlanFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, v> {

    /* renamed from: i  reason: collision with root package name */
    private ViewStub f4114i;

    /* renamed from: j  reason: collision with root package name */
    private LinearLayout f4115j;

    /* renamed from: k  reason: collision with root package name */
    private RelativeLayout f4116k;

    /* renamed from: l  reason: collision with root package name */
    private RelativeLayout f4117l;

    /* renamed from: m  reason: collision with root package name */
    private LinearLayout f4118m;

    /* renamed from: n  reason: collision with root package name */
    private TextView f4119n;
    private TextView o;
    private View p;
    private View q;
    private PayPlanView r;
    private ImageView s;
    private ImageView t;
    private x u;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ BaiTiaoExtraTip f4120g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Context f4121h;

        a(BaiTiaoExtraTip baiTiaoExtraTip, Context context) {
            this.f4120g = baiTiaoExtraTip;
            this.f4121h = context;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CashierCommonPopConfig cashierCommonPopConfig;
            BaiTiaoExtraTip baiTiaoExtraTip = this.f4120g;
            if (baiTiaoExtraTip == null || (cashierCommonPopConfig = baiTiaoExtraTip.extraTipToast) == null) {
                return;
            }
            com.jd.lib.cashier.sdk.b.d.a.c(this.f4121h, cashierCommonPopConfig);
            Payment a = CashierPayChannelPlanFloor.this.u.a();
            if (this.f4121h instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().l((FragmentActivity) this.f4121h, a.code);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f4123g;

        b(CashierPayChannelPlanFloor cashierPayChannelPlanFloor, Context context) {
            this.f4123g = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f4123g instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().m((FragmentActivity) this.f4123g);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f4124g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ x f4125h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ PlanServiceMap f4126i;

        c(CashierPayChannelPlanFloor cashierPayChannelPlanFloor, Context context, x xVar, PlanServiceMap planServiceMap) {
            this.f4124g = context;
            this.f4125h = xVar;
            this.f4126i = planServiceMap;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Context context = this.f4124g;
            if (context instanceof CashierPayActivity) {
                CashierPayActivity cashierPayActivity = (CashierPayActivity) context;
                Payment a = this.f4125h.a();
                com.jd.lib.cashier.sdk.h.e.a.d().O(cashierPayActivity, a.code, com.jd.lib.cashier.sdk.h.h.e.b(a));
                com.jd.lib.cashier.sdk.b.d.a.j(cashierPayActivity, this.f4126i.planServiceFeeToast);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class d implements OnPlanViewClickListener {
        final /* synthetic */ Context a;

        d(Context context) {
            this.a = context;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener
        public void onClick(@NonNull IPlanItemViewEntity iPlanItemViewEntity, @Nullable IPlanItemViewEntity iPlanItemViewEntity2) {
            if ((iPlanItemViewEntity instanceof PlanFeeEntity) && (iPlanItemViewEntity2 instanceof PlanFeeEntity)) {
                PlanFeeEntity planFeeEntity = (PlanFeeEntity) iPlanItemViewEntity;
                PlanFeeEntity planFeeEntity2 = (PlanFeeEntity) iPlanItemViewEntity2;
                r.b("CashierPayChannelPlanFloor", "payPlanItemView initializePayPlanView payPlanView.setOnItemClickListener");
                Payment a = CashierPayChannelPlanFloor.this.u.a();
                com.jd.lib.cashier.sdk.h.e.a.d().F(this.a, a.code, planFeeEntity.getPlan(), planFeeEntity.getSkuSplitFlag() ? "1" : "0");
                com.jd.lib.cashier.sdk.b.i.e.g("CLICK_PAYMENT_CHANNEL_PAY_PLAN_ITEM", "baitiao_plan", new ClickBaiTiaoPayPlanItemEvent(planFeeEntity, planFeeEntity2, a));
            }
        }
    }

    public CashierPayChannelPlanFloor(View view) {
        super(view);
    }

    private void d(List<IPlanItemViewEntity> list, String str) {
        Context context = getConvertView().getContext();
        PayPlanView payPlanView = (PayPlanView) getView(R.id.stub_pay_plan_view);
        this.r = payPlanView;
        payPlanView.H(AbsPayPlanView.b.PLAN_BAITIAO);
        this.r.x(list, str);
        this.r.G(new d(context));
        PayPlanView payPlanView2 = this.r;
        if (payPlanView2 != null) {
            payPlanView2.b();
        }
    }

    private boolean e(@NonNull x xVar) {
        Payment a2 = xVar.a();
        if (a2 == null) {
            return false;
        }
        boolean z = a2.selected;
        boolean a3 = com.jd.lib.cashier.sdk.h.h.g.a(a2.code);
        boolean equals = "5".equals(a2.status);
        boolean equals2 = CashierPayChannelCode.JD_PAY_CREDIT.equals(a2.code);
        boolean equals3 = TextUtils.equals(a2.status, "3");
        boolean z2 = a2.baitiaoPlanInfo != null && a3;
        ViewStub viewStub = this.f4114i;
        if (viewStub == null) {
            return false;
        }
        if (equals3) {
            viewStub.setVisibility(8);
            return false;
        } else if (equals || !(z || z2)) {
            viewStub.setVisibility(8);
            return false;
        } else if (equals2) {
            viewStub.setVisibility(0);
            return true;
        } else if (a3) {
            List<IPlanItemViewEntity> list = a2.planFeeEntityList;
            if (list != null && list.size() > 0) {
                this.f4114i.setVisibility(0);
                return true;
            }
            this.f4114i.setVisibility(8);
            return false;
        } else {
            viewStub.setVisibility(8);
            return false;
        }
    }

    private void f(@NonNull x xVar) {
        String str;
        boolean z;
        try {
            Context context = getConvertView().getContext();
            for (IPlanItemViewEntity iPlanItemViewEntity : xVar.a().planFeeEntityList) {
                if (iPlanItemViewEntity instanceof PlanFeeEntity) {
                    PlanFeeEntity planFeeEntity = (PlanFeeEntity) iPlanItemViewEntity;
                    str = planFeeEntity.getSkuSplitFlag() ? "1" : "0";
                    z = planFeeEntity.isChecked();
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

    private void g(@NonNull x xVar) {
        f(xVar);
        Payment a2 = xVar.a();
        List<IPlanItemViewEntity> list = a2.planFeeEntityList;
        if (com.jd.lib.cashier.sdk.h.h.g.a(a2.code)) {
            d(list, a2.mianxiHighlight);
        }
    }

    private void i() {
        LinearLayout linearLayout = (LinearLayout) getView(R.id.lib_cashier_pay_channel_floor_plan_root);
        PayPlanView payPlanView = this.r;
        if (payPlanView != null && payPlanView.getVisibility() == 0) {
            linearLayout.setVisibility(0);
            linearLayout.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_top_bottom_corner_plan_dark_bg : R.drawable.lib_cashier_sdk_top_bottom_corner_plan_bg);
            return;
        }
        linearLayout.setVisibility(8);
    }

    private void j(x xVar) {
        this.f4115j.setVisibility(8);
        Payment a2 = xVar.a();
        PayPlanView payPlanView = this.r;
        if (payPlanView == null || payPlanView.getVisibility() != 0) {
            return;
        }
        if ("1".equals(a2.arrowPosition)) {
            this.f4115j.setVisibility(0);
            this.f4116k.setVisibility(0);
            this.s.setImageResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_bt_plan_dark_arrow : R.drawable.lib_cashier_sdk_bt_plan_arrow);
            this.f4117l.setVisibility(4);
        } else if ("2".equals(a2.arrowPosition)) {
            this.f4115j.setVisibility(0);
            this.f4116k.setVisibility(4);
            this.f4117l.setVisibility(0);
            this.t.setImageResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_bt_plan_dark_arrow : R.drawable.lib_cashier_sdk_bt_plan_arrow);
        }
    }

    private void k(x xVar) {
        this.f4118m.setVisibility(8);
        this.f4119n.setVisibility(8);
        this.o.setVisibility(8);
        Payment a2 = xVar.a();
        PayPlanView payPlanView = this.r;
        if (payPlanView == null || payPlanView.getVisibility() != 0) {
            return;
        }
        String str = a2.choosePlanTip;
        String str2 = a2.baiTiaoTip;
        String str3 = a2.baiTiaoHighlightTip;
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        this.f4118m.setVisibility(0);
        if (!TextUtils.isEmpty(str)) {
            this.f4119n.setText(str);
            this.f4119n.setVisibility(0);
        } else {
            this.f4119n.setVisibility(8);
        }
        this.o.setVisibility(0);
        if (!TextUtils.isEmpty(str2)) {
            this.o.setText(str2);
            this.o.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
        } else if (!TextUtils.isEmpty(str3)) {
            this.o.setText(str3);
            this.o.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FA2C19));
        } else {
            this.o.setVisibility(8);
        }
        this.f4119n.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
    }

    private void l(x xVar) {
        PopBusinessMap popBusinessMap;
        List<PlanRowEntity> list;
        Context context = getConvertView().getContext();
        ViewGroup viewGroup = (ViewGroup) getView(R.id.lib_cashier_pay_channel_floor_bottom_root);
        ViewGroup viewGroup2 = (ViewGroup) getView(R.id.lib_cashier_pay_channel_floor_tip_container);
        TextView textView = (TextView) getView(R.id.lib_cashier_pay_channel_floor_tip);
        if (viewGroup == null || viewGroup2 == null || textView == null || xVar == null) {
            return;
        }
        PlanServiceMap planServiceMap = xVar.a().serviceMap;
        PayPlanView payPlanView = this.r;
        if (payPlanView != null && planServiceMap != null && payPlanView.getVisibility() == 0) {
            if (TextUtils.isEmpty(planServiceMap.planServiceFeeStr)) {
                viewGroup.setVisibility(8);
                return;
            }
            boolean z = false;
            viewGroup.setVisibility(0);
            textView.setText(planServiceMap.planServiceFeeStr);
            CashierCommonPopConfig cashierCommonPopConfig = planServiceMap.planServiceFeeToast;
            if (cashierCommonPopConfig != null && (popBusinessMap = cashierCommonPopConfig.businessMap) != null && (list = popBusinessMap.table) != null && !list.isEmpty()) {
                z = true;
            }
            if (z) {
                textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, context.getResources().getDrawable(R.drawable.lib_cashier_sdk_icon_style_light), (Drawable) null);
                textView.setCompoundDrawablePadding(DpiUtil.dip2px(context, 4.0f));
                viewGroup2.setOnClickListener(new c(this, context, xVar, planServiceMap));
            } else {
                textView.setText(planServiceMap.planServiceFeeStr);
                textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
                viewGroup2.setOnClickListener(null);
            }
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
            com.jd.lib.cashier.sdk.h.e.a.d().n(context, planServiceMap.planServiceFeeStr);
            return;
        }
        viewGroup.setVisibility(8);
    }

    private void m() {
        if (this.u == null || this.r == null) {
            return;
        }
        Context context = getConvertView().getContext();
        if (this.r.getVisibility() == 0) {
            BaiTiaoExtraTip baiTiaoExtraTip = this.u.a().baiTiaoExtraTip;
            this.r.D(new a(baiTiaoExtraTip, context));
            this.r.K(baiTiaoExtraTip, new b(this, context));
        }
    }

    private void n(x xVar) {
        if (xVar != null) {
            if (e(xVar)) {
                g(xVar);
            }
            k(xVar);
            l(xVar);
            m();
            i();
            p();
            o();
            j(xVar);
        }
    }

    private void o() {
        PayPlanView payPlanView = this.r;
        if (payPlanView == null || payPlanView.getVisibility() != 0 || this.q == null) {
            return;
        }
        if (this.r.f() && this.f4118m.getVisibility() == 0) {
            this.q.setVisibility(0);
        } else {
            this.q.setVisibility(8);
        }
    }

    private void p() {
        PayPlanView payPlanView = this.r;
        if (payPlanView == null || payPlanView.getVisibility() != 0 || this.p == null) {
            return;
        }
        if (!this.r.f() && this.f4118m.getVisibility() != 0) {
            this.p.setVisibility(8);
        } else {
            this.p.setVisibility(0);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, v vVar) {
        if (aVar != null) {
            this.u = aVar.a;
        }
        n(this.u);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        this.f4114i = (ViewStub) view.findViewById(R.id.lib_cashier_pay_channel_floor_item_stub);
        this.p = view.findViewById(R.id.view_line);
        this.q = view.findViewById(R.id.view_line1);
        this.f4115j = (LinearLayout) view.findViewById(R.id.lib_cashier_pay_channel_ll_arrow_root);
        this.f4116k = (RelativeLayout) view.findViewById(R.id.lib_cashier_pay_channel_left_arrow_root);
        this.f4117l = (RelativeLayout) view.findViewById(R.id.lib_cashier_pay_channel_right_arrow_root);
        this.f4118m = (LinearLayout) view.findViewById(R.id.lib_cashier_bt_grow_up_tip_container);
        this.f4119n = (TextView) view.findViewById(R.id.lib_cashier_bt_grow_up_choose_plan_text);
        this.o = (TextView) view.findViewById(R.id.lib_cashier_bt_grow_up_tip_text);
        this.s = (ImageView) view.findViewById(R.id.lib_cashier_pay_channel_left_arrow);
        this.t = (ImageView) view.findViewById(R.id.lib_cashier_pay_channel_right_arrow);
    }
}
