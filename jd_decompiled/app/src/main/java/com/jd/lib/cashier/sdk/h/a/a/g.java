package com.jd.lib.cashier.sdk.h.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.PlatPayFlagTag;
import com.jd.lib.cashier.sdk.pay.bean.SubmitOrderExtFlagMap;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class g extends com.jd.lib.cashier.sdk.h.a.a.l.d {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements com.jd.lib.cashier.sdk.core.utils.f<CashierPayEntity> {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.h.f.d f3495g;

        a(com.jd.lib.cashier.sdk.h.f.d dVar) {
            this.f3495g = dVar;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.f
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callBack(CashierPayEntity cashierPayEntity) {
            try {
                cashierPayEntity.checkNullObjAndInit();
                if (cashierPayEntity.getResultCode() != com.jd.lib.cashier.sdk.d.b.b.SUC) {
                    g.this.r(this.f3495g.getActivity(), cashierPayEntity);
                } else {
                    if (!TextUtils.isEmpty(cashierPayEntity.errorCode)) {
                        g.this.r(this.f3495g.getActivity(), cashierPayEntity);
                    } else {
                        g.this.t(this.f3495g.getActivity(), this.f3495g, cashierPayEntity);
                    }
                    g.this.u(this.f3495g.getActivity(), cashierPayEntity, this.f3495g);
                }
            } finally {
                g.this.v(this.f3495g.getActivity());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f3497g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CashierPayEntity f3498h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ boolean f3499i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ com.jd.lib.cashier.sdk.h.f.d f3500j;

        b(g gVar, FragmentActivity fragmentActivity, CashierPayEntity cashierPayEntity, boolean z, com.jd.lib.cashier.sdk.h.f.d dVar) {
            this.f3497g = fragmentActivity;
            this.f3498h = cashierPayEntity;
            this.f3499i = z;
            this.f3500j = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(this.f3497g).get(CashierPayViewModel.class);
            cashierPayViewModel.b().g();
            if (TextUtils.isEmpty(this.f3498h.thirdPayExpandTip)) {
                this.f3498h.thirdPayExpandTip = this.f3497g.getString(R.string.lib_cashier_sdk_third_pay_expand_tip);
            }
            PlatPayFlagTag platPayFlagTag = this.f3498h.platPayFoldTag;
            if (platPayFlagTag != null && TextUtils.isEmpty(platPayFlagTag.platPayExpandTip)) {
                this.f3498h.platPayFoldTag.platPayExpandTip = this.f3497g.getString(R.string.lib_cashier_b_sdk_third_pay_expand_tip);
            }
            if (!TextUtils.isEmpty(this.f3498h.orderId)) {
                cashierPayViewModel.b().f3511e = this.f3498h.orderId;
            }
            cashierPayViewModel.b().K = this.f3498h;
            cashierPayViewModel.b().u = this.f3498h.defaultStrategy;
            cashierPayViewModel.b().R = this.f3498h.defaultSelectTag;
            cashierPayViewModel.b().S = this.f3498h.defaultSelectToast;
            cashierPayViewModel.b().U = this.f3498h.isActionSheet;
            cashierPayViewModel.b().f3513g = this.f3498h.combinedOrderId;
            cashierPayViewModel.b().I = this.f3499i;
            cashierPayViewModel.b().E = TextUtils.equals(this.f3498h.platPayCashierType, "2");
            cashierPayViewModel.b().F = TextUtils.equals(this.f3498h.platPayCashierType, "3");
            m.f().o(this.f3498h.platPayCashierType);
            m.f().b();
            if (cashierPayViewModel.b().I) {
                m.f().m("1");
            } else if (TextUtils.equals(this.f3498h.dynamicFlag, "1")) {
                y.u();
                m.f().m("2");
            }
            cashierPayViewModel.D().a(8);
            cashierPayViewModel.I().a(this.f3498h, this.f3500j, cashierPayViewModel.b().F);
            cashierPayViewModel.H().b(this.f3498h, this.f3497g);
            cashierPayViewModel.E().a(this.f3498h);
            cashierPayViewModel.G().b(this.f3498h);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(FragmentActivity fragmentActivity, CashierPayEntity cashierPayEntity) {
        if (!g0.a(fragmentActivity) || cashierPayEntity == null) {
            return;
        }
        CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
        cashierPayViewModel.D().a(0);
        if (TextUtils.equals(cashierPayEntity.errorCode, "-6")) {
            cashierPayViewModel.b().Y = "1";
            cashierPayViewModel.G().e(cashierPayEntity);
        } else if (TextUtils.equals(cashierPayEntity.errorCode, "-5")) {
            cashierPayViewModel.G().d(fragmentActivity, cashierPayEntity);
        } else {
            CashierCommonPopConfig cashierCommonPopConfig = cashierPayEntity.orderExceptionInfo;
            if (cashierCommonPopConfig != null && cashierCommonPopConfig.canDialogShow()) {
                cashierPayViewModel.G().c(cashierPayEntity);
            } else if (TextUtils.isEmpty(cashierPayEntity.errorMsg)) {
            } else {
                f0.c(cashierPayEntity.errorMsg);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.h.f.d dVar, CashierPayEntity cashierPayEntity) {
        if (!g0.a(fragmentActivity) || cashierPayEntity == null) {
            return;
        }
        if ((TextUtils.equals(cashierPayEntity.useMCashier, "2") || TextUtils.equals(cashierPayEntity.useMCashier, "1")) && !TextUtils.isEmpty(cashierPayEntity.mCashierUrl)) {
            ((CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)).F().a(cashierPayEntity.useMCashier, cashierPayEntity.mCashierUrl);
        } else {
            w(fragmentActivity, dVar, cashierPayEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(FragmentActivity fragmentActivity, CashierPayEntity cashierPayEntity, com.jd.lib.cashier.sdk.h.f.d dVar) {
        SubmitOrderExtFlagMap submitOrderExtFlagMap;
        if (!g0.a(fragmentActivity) || cashierPayEntity == null || TextUtils.isEmpty(cashierPayEntity.orderId)) {
            return;
        }
        String str = null;
        if (dVar != null && !TextUtils.isEmpty(dVar.v) && (submitOrderExtFlagMap = (SubmitOrderExtFlagMap) o.a(dVar.v, SubmitOrderExtFlagMap.class)) != null && TextUtils.equals(submitOrderExtFlagMap.isGoodsDetailJinCaiFlag, "1")) {
            str = "jincai";
        }
        com.jd.lib.cashier.sdk.h.e.a.d().W(fragmentActivity, cashierPayEntity.orderId, cashierPayEntity.getSkuId(), cashierPayEntity.paySource, str, cashierPayEntity.orderPrice, cashierPayEntity.thirdPaychannelFoldStrategyId, cashierPayEntity.reskinStrategyId, cashierPayEntity.metricCommonParams, cashierPayEntity.foldExpoMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity) && (fragmentActivity instanceof CashierPayActivity)) {
            ((CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)).w().a();
        }
    }

    private void w(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.h.f.d dVar, CashierPayEntity cashierPayEntity) {
        if (!g0.a(fragmentActivity) || cashierPayEntity == null) {
            return;
        }
        fragmentActivity.runOnUiThread(new b(this, fragmentActivity, cashierPayEntity, TextUtils.equals(cashierPayEntity.dynamicFlag, "1") && com.jd.lib.cashier.sdk.h.h.b.a(), dVar));
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: s  reason: merged with bridge method [inline-methods] */
    public void e(com.jd.lib.cashier.sdk.h.f.d dVar) {
        if (dVar != null) {
            j(new a(dVar));
            h(dVar);
        }
    }
}
