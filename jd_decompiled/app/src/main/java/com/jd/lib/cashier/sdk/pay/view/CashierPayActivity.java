package com.jd.lib.cashier.sdk.pay.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.l0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.h.a.a.i;
import com.jd.lib.cashier.sdk.h.a.c.a;
import com.jd.lib.cashier.sdk.pay.aac.impl.CashierPayExceptionImpl;
import com.jd.lib.cashier.sdk.pay.aac.impl.CashierPayForwardImpl;
import com.jd.lib.cashier.sdk.pay.aac.impl.channel.CashierPayShowDialogImpl;
import com.jd.lib.cashier.sdk.pay.aac.impl.channel.m;
import com.jd.lib.cashier.sdk.pay.aac.impl.e.b;
import com.jd.lib.cashier.sdk.pay.aac.impl.e.c;
import com.jd.lib.cashier.sdk.pay.aac.impl.e.d;
import com.jd.lib.cashier.sdk.pay.aac.impl.e.e;
import com.jd.lib.cashier.sdk.pay.aac.impl.loading.CashierPayPageLoadingImpl;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.handler.CashierPayCommonProxy;
import com.jd.lib.cashier.sdk.pay.handler.CashierPayResultDispatcher;

/* loaded from: classes14.dex */
public class CashierPayActivity extends AbsCashierActivity<CashierPayViewModel, a> implements Observer<Integer> {
    private static final String r = CashierPayActivity.class.getSimpleName();

    /* renamed from: i */
    private b f4179i;

    /* renamed from: j */
    private d f4180j;

    /* renamed from: k */
    private c f4181k;

    /* renamed from: l */
    private CashierPayPageLoadingImpl f4182l;

    /* renamed from: m */
    private com.jd.lib.cashier.sdk.pay.aac.impl.e.a f4183m;

    /* renamed from: n */
    private e f4184n;
    private CashierPayResultDispatcher o;
    private boolean p = true;
    private CashierPayCommonProxy q;

    private void A() {
        m mVar = new m(this, x().b().L);
        this.f4179i = mVar;
        mVar.f(this);
        this.f4179i.h(getWindow());
        CashierPayForwardImpl cashierPayForwardImpl = new CashierPayForwardImpl(this, w());
        this.f4180j = cashierPayForwardImpl;
        cashierPayForwardImpl.f(this);
        this.o = new CashierPayResultDispatcher(this);
        this.q = new CashierPayCommonProxy(this);
        CashierPayExceptionImpl cashierPayExceptionImpl = new CashierPayExceptionImpl();
        this.f4181k = cashierPayExceptionImpl;
        cashierPayExceptionImpl.f(this);
        this.f4181k.h(getWindow());
        this.f4183m = new com.jd.lib.cashier.sdk.pay.aac.impl.a();
        CashierPayShowDialogImpl cashierPayShowDialogImpl = new CashierPayShowDialogImpl(this);
        this.f4184n = cashierPayShowDialogImpl;
        cashierPayShowDialogImpl.f(this);
        CashierPayPageLoadingImpl cashierPayPageLoadingImpl = new CashierPayPageLoadingImpl();
        this.f4182l = cashierPayPageLoadingImpl;
        cashierPayPageLoadingImpl.f(this);
        this.f4182l.h(getWindow());
    }

    private void B() {
        y.d();
        PayTaskStackManager.removeAllCashierTask();
        PayTaskStackManager.addCashierPayTask(this);
        if (!x().N(getIntent())) {
            finish();
        }
        if (x().b().X) {
            setTheme(R.style.CashierPopPayDialogTheme);
        } else {
            setTheme(R.style.CashierFullPayDialogTheme);
        }
        y.u();
    }

    private void D() {
        com.jd.lib.cashier.sdk.b.i.e.b();
        com.jd.lib.cashier.sdk.b.i.e.a();
        A();
        l0.e(this);
        com.jd.lib.cashier.sdk.core.utils.d.e(this);
        x().L(this);
        i.a(this);
        x().k(this);
        y.c(this, this, false);
    }

    private void F() {
        if (!x().N(getIntent())) {
            finish();
        }
        x().k(this);
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: C */
    public void onChanged(Integer num) {
        if (num != null) {
            b bVar = this.f4179i;
            if (bVar != null) {
                bVar.onChangeSkin();
            }
            c cVar = this.f4181k;
            if (cVar != null) {
                cVar.onChangeSkin();
            }
        }
    }

    public void E(int i2, int i3, Intent intent) {
        onActivityResult(i2, i3, intent);
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    public int createLayout() {
        return x().b().X ? R.layout.lib_cashier_sdk_b_pay_pop_layout : R.layout.lib_cashier_sdk_pay_layout;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, android.app.Activity
    public void finish() {
        if (!TextUtils.isEmpty(x().b().W)) {
            com.jd.lib.cashier.sdk.d.g.b.a.a().i(this);
        }
        b bVar = this.f4179i;
        if (bVar != null) {
            bVar.a();
        }
        super.finish();
        com.jd.lib.cashier.sdk.b.i.e.b();
        y.a();
        y.f();
        com.jd.lib.cashier.sdk.core.utils.m.f().e();
        com.jd.lib.cashier.sdk.core.utils.m.f().d();
        com.jd.lib.cashier.sdk.core.utils.m.f().b();
        com.jd.lib.cashier.sdk.core.utils.m.f().a();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        r.b(r, "onActivityResult() requestCode = " + i2 + " , resultCode = " + i3);
        CashierPayResultDispatcher cashierPayResultDispatcher = this.o;
        if (cashierPayResultDispatcher != null) {
            cashierPayResultDispatcher.j(i2, i3, intent);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        CashierCommonPopConfig cashierCommonPopConfig;
        CashierCommonPopConfig cashierCommonPopConfig2;
        com.jd.lib.cashier.sdk.h.e.a.d().j(this, x().b().D);
        CashierPayEntity cashierPayEntity = x().b().K;
        String string = getString(R.string.lib_cashier_sdk_back_pop_dialog_default_msg);
        if (cashierPayEntity != null && TextUtils.equals("1", cashierPayEntity.tenBillionSubsidy) && (cashierCommonPopConfig2 = cashierPayEntity.subsidyPopupConfig) != null && !TextUtils.isEmpty(cashierCommonPopConfig2.confirmBtn) && !TextUtils.isEmpty(cashierPayEntity.subsidyPopupConfig.cancelBtn)) {
            com.jd.lib.cashier.sdk.pay.aac.impl.e.a aVar = this.f4183m;
            if (aVar != null && aVar.e(x().b())) {
                finish();
            } else {
                com.jd.lib.cashier.sdk.b.d.a.l(this, cashierPayEntity.subsidyPopupConfig, x().b().D, cashierPayEntity.paySource, cashierPayEntity.checkIsNewUser());
            }
        } else if (cashierPayEntity != null && (cashierCommonPopConfig = cashierPayEntity.indexPopupConfig) != null && !TextUtils.isEmpty(cashierCommonPopConfig.confirmBtn) && !TextUtils.isEmpty(cashierPayEntity.indexPopupConfig.cancelBtn)) {
            com.jd.lib.cashier.sdk.pay.aac.impl.e.a aVar2 = this.f4183m;
            if (aVar2 != null && !aVar2.b(x().b())) {
                cashierPayEntity.indexPopupConfig.replacedMessage = string;
            }
            com.jd.lib.cashier.sdk.pay.aac.impl.e.a aVar3 = this.f4183m;
            if (aVar3 != null) {
                aVar3.k(cashierPayEntity.indexPopupConfig);
            }
            com.jd.lib.cashier.sdk.b.d.a.d(this, cashierPayEntity.indexPopupConfig, x().b().D, cashierPayEntity.paySource, cashierPayEntity.checkIsNewUser());
        } else {
            finish();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        this.statusBarTransparentEnable = true;
        B();
        super.onCreate(bundle);
        D();
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CashierPayCommonProxy cashierPayCommonProxy = this.q;
        if (cashierPayCommonProxy != null) {
            cashierPayCommonProxy.onDestroy();
            this.q = null;
        }
        b bVar = this.f4179i;
        if (bVar != null) {
            bVar.onDestroy();
            this.f4179i = null;
        }
        d dVar = this.f4180j;
        if (dVar != null) {
            dVar.onDestroy();
            this.f4180j = null;
        }
        CashierPayResultDispatcher cashierPayResultDispatcher = this.o;
        if (cashierPayResultDispatcher != null) {
            cashierPayResultDispatcher.onDestroy();
            this.o = null;
        }
        c cVar = this.f4181k;
        if (cVar != null) {
            cVar.onDestroy();
            this.f4181k = null;
        }
        com.jd.lib.cashier.sdk.pay.aac.impl.e.a aVar = this.f4183m;
        if (aVar != null) {
            aVar.onDestroy();
            this.f4183m = null;
        }
        e eVar = this.f4184n;
        if (eVar != null) {
            eVar.onDestroy();
            this.f4184n = null;
        }
        CashierPayPageLoadingImpl cashierPayPageLoadingImpl = this.f4182l;
        if (cashierPayPageLoadingImpl != null) {
            cashierPayPageLoadingImpl.onDestroy();
            this.f4182l = null;
        }
        com.jd.lib.cashier.sdk.b.h.c.c(this);
        com.jd.lib.cashier.sdk.d.g.g.e.c().a();
        com.jd.lib.cashier.sdk.h.e.a.d().onClearEvent();
        y.b();
        com.jd.lib.cashier.sdk.d.g.b.a.a().c(this);
        y.v(this);
        y.a();
    }

    @Override // android.app.Activity
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        b bVar = this.f4179i;
        if (bVar != null) {
            bVar.c();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        F();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        b bVar = this.f4179i;
        if (bVar != null) {
            bVar.onPause();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        d dVar = this.f4180j;
        if (dVar != null) {
            dVar.onResume();
        }
        b bVar = this.f4179i;
        if (bVar != null) {
            bVar.onResume();
        }
        y.w();
        com.jd.lib.cashier.sdk.b.h.c.b(this);
        y.x();
        if (this.p) {
            com.jd.lib.cashier.sdk.h.e.a.d().m0(this);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        if (!g0.e()) {
            this.p = false;
        } else {
            this.p = true;
        }
        super.onStop();
    }

    @Override // android.app.Activity
    public void overridePendingTransition(int i2, int i3) {
        if (x().b().X) {
            super.overridePendingTransition(R.anim.lib_cashier_sdk_dialog_bottom_enter, R.anim.lib_cashier_sdk_dialog_bottom_exit);
        } else {
            super.overridePendingTransition(i2, i3);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: y */
    public a u() {
        return new a();
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: z */
    public CashierPayViewModel v() {
        return (CashierPayViewModel) ViewModelProviders.of(this).get(CashierPayViewModel.class);
    }
}
