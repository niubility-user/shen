package com.jd.lib.cashier.sdk.creditpay.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.creditpay.aac.impl.CreditPayForwardImpl;
import com.jd.lib.cashier.sdk.creditpay.aac.impl.CreditPayIndexImpl;
import com.jd.lib.cashier.sdk.creditpay.aac.impl.CreditPayShowDialogImpl;
import com.jd.lib.cashier.sdk.creditpay.aac.impl.CreditPayStateImpl;
import com.jd.lib.cashier.sdk.creditpay.aac.impl.b;
import com.jd.lib.cashier.sdk.creditpay.aac.impl.c;
import com.jd.lib.cashier.sdk.creditpay.aac.impl.d;
import com.jd.lib.cashier.sdk.creditpay.aac.viewmodel.CashierCreditPayViewModel;
import com.jd.lib.cashier.sdk.creditpay.bean.CreditPayEntity;
import com.jd.lib.cashier.sdk.creditpay.pay.result.CreditPayResultCommonProxy;
import com.jd.lib.cashier.sdk.e.a.c.a;

/* loaded from: classes14.dex */
public class CashierCreditPayActivity extends AbsCashierActivity<CashierCreditPayViewModel, a> implements Observer<Integer> {

    /* renamed from: i */
    private boolean f3219i;

    /* renamed from: j */
    private b f3220j;

    /* renamed from: k */
    private d f3221k;

    /* renamed from: l */
    private com.jd.lib.cashier.sdk.creditpay.aac.impl.a f3222l;

    /* renamed from: m */
    private c f3223m;

    /* renamed from: n */
    private com.jd.lib.cashier.sdk.pay.handler.d f3224n;
    private com.jd.lib.cashier.sdk.pay.handler.d o;
    private boolean p = true;

    private void A() {
        View findViewById = findViewById(R.id.lib_cashier_credit_pay_dialog_root);
        CreditPayStateImpl creditPayStateImpl = new CreditPayStateImpl(this);
        this.f3221k = creditPayStateImpl;
        creditPayStateImpl.f(this);
        CreditPayShowDialogImpl creditPayShowDialogImpl = new CreditPayShowDialogImpl(this);
        this.f3223m = creditPayShowDialogImpl;
        creditPayShowDialogImpl.f(this);
        CreditPayIndexImpl creditPayIndexImpl = new CreditPayIndexImpl(this, w(), findViewById);
        this.f3220j = creditPayIndexImpl;
        creditPayIndexImpl.f(this);
        CreditPayForwardImpl creditPayForwardImpl = new CreditPayForwardImpl(this, w());
        this.f3222l = creditPayForwardImpl;
        creditPayForwardImpl.f(this);
        this.f3224n = new com.jd.lib.cashier.sdk.creditpay.pay.result.a(this);
        this.o = new CreditPayResultCommonProxy(this);
    }

    private void C() {
        CashierCommonPopConfig cashierCommonPopConfig;
        CreditPayEntity creditPayEntity = x().b().r;
        if (creditPayEntity != null && (cashierCommonPopConfig = creditPayEntity.indexPopupConfig) != null && cashierCommonPopConfig.canDialogShow()) {
            com.jd.lib.cashier.sdk.creditpay.dialog.a.c(this, creditPayEntity.indexPopupConfig);
        } else {
            com.jd.lib.cashier.sdk.creditpay.dialog.a.b(this);
        }
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: B */
    public void onChanged(Integer num) {
        if (num != null) {
            d dVar = this.f3221k;
            if (dVar != null) {
                dVar.onChangeSkin();
            }
            b bVar = this.f3220j;
            if (bVar != null) {
                bVar.onChangeSkin();
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    public int createLayout() {
        return R.layout.lib_cashier_sdk_credit_pay_layout;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, android.app.Activity
    public void finish() {
        com.jd.lib.cashier.sdk.d.g.b.a.a().i(this);
        d dVar = this.f3221k;
        if (dVar != null && this.f3219i) {
            dVar.i();
            this.f3219i = false;
        }
        super.finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        com.jd.lib.cashier.sdk.pay.handler.d dVar = this.f3224n;
        if (dVar != null) {
            dVar.j(i2, i3, intent);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        C();
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        this.statusBarTransparentEnable = true;
        super.onCreate(bundle);
        if (!x().m(getIntent())) {
            f0.a(this, R.string.lib_cashier_sdk_pay_init_exception);
            w().d(this, x().b().p, x().b().d);
            finish();
        } else if (!x().c()) {
            f0.a(this, R.string.lib_cashier_sdk_pay_invalid_data);
            w().d(this, x().b().p, x().b().d);
            finish();
        } else {
            A();
            x().n();
            x().j(this);
            y.c(this, this, false);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.jd.lib.cashier.sdk.pay.handler.d dVar = this.f3224n;
        if (dVar != null) {
            dVar.onDestroy();
            this.f3224n = null;
        }
        b bVar = this.f3220j;
        if (bVar != null) {
            bVar.onDestroy();
            this.f3220j = null;
        }
        d dVar2 = this.f3221k;
        if (dVar2 != null) {
            dVar2.onDestroy();
            this.f3221k = null;
        }
        com.jd.lib.cashier.sdk.creditpay.aac.impl.a aVar = this.f3222l;
        if (aVar != null) {
            aVar.onDestroy();
            this.f3222l = null;
        }
        c cVar = this.f3223m;
        if (cVar != null) {
            cVar.onDestroy();
            this.f3223m = null;
        }
        com.jd.lib.cashier.sdk.pay.handler.d dVar3 = this.o;
        if (dVar3 != null) {
            dVar3.onDestroy();
            this.o = null;
        }
        com.jd.lib.cashier.sdk.e.e.b.a.a().d();
        y.v(this);
    }

    @Override // android.app.Activity
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        d dVar = this.f3221k;
        if (dVar == null || this.f3219i) {
            return;
        }
        dVar.g();
        this.f3219i = true;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.p) {
            com.jd.lib.cashier.sdk.e.c.a.q(this);
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
        super.overridePendingTransition(R.anim.lib_cashier_sdk_dialog_bottom_enter, R.anim.lib_cashier_sdk_dialog_bottom_exit);
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: y */
    public a u() {
        return new a();
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: z */
    public CashierCreditPayViewModel v() {
        return (CashierCreditPayViewModel) ViewModelProviders.of(this).get(CashierCreditPayViewModel.class);
    }
}
