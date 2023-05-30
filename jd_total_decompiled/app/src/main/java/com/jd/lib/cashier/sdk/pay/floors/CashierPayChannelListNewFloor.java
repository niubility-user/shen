package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.PlanRowEntity;
import com.jd.lib.cashier.sdk.core.model.PopBusinessMap;
import com.jd.lib.cashier.sdk.core.ui.CashierRegulatorItemView;
import com.jd.lib.cashier.sdk.core.ui.widget.PayPlanView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.AgreementServiceMapMap;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierPayChannelListNewFloor extends AbstractCashierPayChannelListFloor {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f4109g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ x f4110h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ PlanServiceMap f4111i;

        a(CashierPayChannelListNewFloor cashierPayChannelListNewFloor, Context context, x xVar, PlanServiceMap planServiceMap) {
            this.f4109g = context;
            this.f4110h = xVar;
            this.f4111i = planServiceMap;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Context context = this.f4109g;
            if (context instanceof CashierPayActivity) {
                CashierPayActivity cashierPayActivity = (CashierPayActivity) context;
                Payment a = this.f4110h.a();
                com.jd.lib.cashier.sdk.h.e.a.d().O(cashierPayActivity, a.code, com.jd.lib.cashier.sdk.h.h.e.b(a));
                com.jd.lib.cashier.sdk.b.d.a.j(cashierPayActivity, this.f4111i.planServiceFeeToast);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Context f4112j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ AgreementServiceMapMap f4113k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(CashierPayChannelListNewFloor cashierPayChannelListNewFloor, long j2, Context context, AgreementServiceMapMap agreementServiceMapMap) {
            super(j2);
            this.f4112j = context;
            this.f4113k = agreementServiceMapMap;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            Context context = this.f4112j;
            if (context instanceof CashierPayActivity) {
                AgreementServiceMapMap agreementServiceMapMap = this.f4113k;
                new com.jd.lib.cashier.sdk.pay.dialog.a().e((CashierPayActivity) context, agreementServiceMapMap.agreementUrl, agreementServiceMapMap.agreementTitle);
            }
        }
    }

    public CashierPayChannelListNewFloor(View view) {
        super(view);
    }

    private void B() {
        CashierRegulatorItemView cashierRegulatorItemView = this.f3955k;
        if (cashierRegulatorItemView != null && cashierRegulatorItemView.getVisibility() == 0) {
            this.f3955k.S();
        }
    }

    private void C(String str) {
        View findViewById;
        CashierRegulatorItemView cashierRegulatorItemView = this.f3955k;
        if (cashierRegulatorItemView == null || (findViewById = cashierRegulatorItemView.findViewById(R.id.pay_item_view)) == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
        int dip2px = DpiUtil.dip2px(getConvertView().getContext(), 56.0f);
        int dip2px2 = DpiUtil.dip2px(getConvertView().getContext(), 46.5f);
        if (com.jd.lib.cashier.sdk.h.h.g.l(str)) {
            if (layoutParams.height != dip2px2) {
                layoutParams.height = dip2px2;
                findViewById.setLayoutParams(layoutParams);
            }
        } else if (layoutParams.height != dip2px) {
            layoutParams.height = dip2px;
            findViewById.setLayoutParams(layoutParams);
        }
    }

    private void D(x xVar) {
        PopBusinessMap popBusinessMap;
        List<PlanRowEntity> list;
        Context context = getConvertView().getContext();
        TextView textView = (TextView) getView(R.id.lib_cashier_pay_channel_floor_tip);
        ViewGroup viewGroup = (ViewGroup) getView(R.id.lib_cashier_pay_channel_floor_bottom_root);
        ViewGroup viewGroup2 = (ViewGroup) getView(R.id.lib_cashier_pay_channel_floor_tip_container);
        ViewGroup viewGroup3 = (ViewGroup) getView(R.id.lib_cashier_pay_channel_floor_agreement_container);
        TextView textView2 = (TextView) getView(R.id.lib_cashier_pay_channel_floor_agreement_name);
        View view = getView(R.id.lib_cashier_pay_channel_floor_agreement_view);
        if (viewGroup == null || viewGroup2 == null || textView == null || xVar == null || viewGroup3 == null || textView2 == null) {
            return;
        }
        PlanServiceMap planServiceMap = xVar.a().serviceMap;
        AgreementServiceMapMap agreementServiceMapMap = xVar.a().agreementServiceMap;
        PayPlanView payPlanView = this.f3954j;
        if (payPlanView == null || payPlanView.getVisibility() != 0) {
            viewGroup.setVisibility(8);
        } else if (planServiceMap == null && agreementServiceMapMap == null) {
            viewGroup.setVisibility(8);
        } else if (planServiceMap != null && TextUtils.isEmpty(planServiceMap.planServiceFeeStr) && agreementServiceMapMap != null && TextUtils.isEmpty(agreementServiceMapMap.agreementName)) {
            viewGroup.setVisibility(8);
        } else {
            viewGroup.setVisibility(0);
            if (planServiceMap != null && !TextUtils.isEmpty(planServiceMap.planServiceFeeStr)) {
                textView.setVisibility(0);
                textView.setText(planServiceMap.planServiceFeeStr);
                CashierCommonPopConfig cashierCommonPopConfig = planServiceMap.planServiceFeeToast;
                if ((cashierCommonPopConfig == null || (popBusinessMap = cashierCommonPopConfig.businessMap) == null || (list = popBusinessMap.table) == null || list.isEmpty()) ? false : true) {
                    textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, context.getResources().getDrawable(R.drawable.lib_cashier_sdk_icon_style_light), (Drawable) null);
                    textView.setCompoundDrawablePadding(DpiUtil.dip2px(context, 4.0f));
                    textView.setOnClickListener(new a(this, context, xVar, planServiceMap));
                } else {
                    textView.setText(planServiceMap.planServiceFeeStr);
                    textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
                    textView.setOnClickListener(null);
                }
                textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
                com.jd.lib.cashier.sdk.h.e.a.d().n(context, planServiceMap.planServiceFeeStr);
            } else {
                textView.setVisibility(8);
            }
            if (agreementServiceMapMap != null && !TextUtils.isEmpty(agreementServiceMapMap.agreementName)) {
                viewGroup3.setVisibility(0);
                textView2.setText(agreementServiceMapMap.agreementName);
                textView2.setOnClickListener(new b(this, 3000L, context, agreementServiceMapMap));
            } else {
                viewGroup3.setVisibility(8);
            }
            if (textView.getVisibility() != 0 && viewGroup3.getVisibility() != 0) {
                viewGroup.setVisibility(8);
            }
            if (viewGroup3.getVisibility() == 0 && textView.getVisibility() == 0) {
                view.setVisibility(0);
            } else {
                view.setVisibility(8);
            }
            if (textView.getVisibility() == 0 || viewGroup3.getVisibility() == 0) {
                viewGroup.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            }
            if (view.getVisibility() == 0) {
                view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_BFBFBF, JDDarkUtil.COLOR_404040));
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.pay.floors.AbstractCashierPayChannelListFloor
    public void p(x xVar) {
        if (xVar != null) {
            boolean k2 = k(xVar);
            if (k2) {
                q(xVar);
            }
            C(xVar.a().code);
            z(xVar, k2);
            D(xVar);
            B();
            A();
            t(xVar.a());
            y(xVar);
            g(xVar);
            n(xVar);
        }
    }
}
