package com.jd.lib.cashier.sdk.quickpay.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.h.h.g;
import com.jd.lib.cashier.sdk.i.a.c.a;
import com.jd.lib.cashier.sdk.quickpay.aac.impl.CashierQuickPayForwardImpl;
import com.jd.lib.cashier.sdk.quickpay.aac.impl.CashierQuickPayPayingImpl;
import com.jd.lib.cashier.sdk.quickpay.aac.impl.b;
import com.jd.lib.cashier.sdk.quickpay.aac.viewmodel.CashierQuickPayViewModel;
import com.jd.lib.cashier.sdk.quickpay.handler.CashierQuickPayResultHandler;

/* loaded from: classes14.dex */
public class CashierQuickPayActivity extends AbsCashierActivity<CashierQuickPayViewModel, a> {

    /* renamed from: i */
    private CashierQuickPayViewModel f4193i;

    /* renamed from: j */
    private a f4194j;

    /* renamed from: k */
    private b f4195k;

    /* renamed from: l */
    private com.jd.lib.cashier.sdk.quickpay.aac.impl.a f4196l;

    /* renamed from: m */
    private CashierQuickPayResultHandler f4197m;

    /* renamed from: n */
    private com.jd.lib.cashier.sdk.quickpay.handler.a f4198n;

    private void B() {
        if (g.p(x().b().o)) {
            x().g().d(this);
        } else if (g.i(x().b().o)) {
            x().g().c(this);
        } else {
            w().b(this);
            f0.a(this, R.string.lib_cashier_sdk_pay_invalid_data);
            finish();
        }
    }

    private void C() {
        this.f4197m = new CashierQuickPayResultHandler(this);
        this.f4198n = new com.jd.lib.cashier.sdk.quickpay.handler.a(this);
        CashierQuickPayPayingImpl cashierQuickPayPayingImpl = new CashierQuickPayPayingImpl(this);
        this.f4195k = cashierQuickPayPayingImpl;
        cashierQuickPayPayingImpl.f(this);
        CashierQuickPayForwardImpl cashierQuickPayForwardImpl = new CashierQuickPayForwardImpl(this, w());
        this.f4196l = cashierQuickPayForwardImpl;
        cashierQuickPayForwardImpl.f(this);
    }

    private void y() {
        if (!x().h(getIntent())) {
            f0.a(this, R.string.lib_cashier_sdk_pay_init_exception);
            w().b(this);
            finish();
        } else if (x().c()) {
        } else {
            w().b(this);
            f0.a(this, R.string.lib_cashier_sdk_pay_invalid_data);
            finish();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: A */
    public CashierQuickPayViewModel v() {
        return (CashierQuickPayViewModel) ViewModelProviders.of(this).get(CashierQuickPayViewModel.class);
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    public int createLayout() {
        return R.layout.lib_cashier_sdk_quick_pay_layout;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, android.app.Activity
    public void finish() {
        com.jd.lib.cashier.sdk.d.g.b.a.a().i(this);
        super.finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        com.jd.lib.cashier.sdk.quickpay.handler.a aVar = this.f4198n;
        if (aVar != null) {
            aVar.j(i2, i3, intent);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (TextUtils.isEmpty(m.f().i())) {
            w().b(this);
            finish();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        PayTaskStackManager.addCashierQuickPay(this);
        y();
        C();
        B();
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.f4193i != null) {
            this.f4193i = null;
        }
        if (this.f4194j != null) {
            this.f4194j = null;
        }
        b bVar = this.f4195k;
        if (bVar != null) {
            bVar.onDestroy();
            this.f4195k = null;
        }
        com.jd.lib.cashier.sdk.quickpay.aac.impl.a aVar = this.f4196l;
        if (aVar != null) {
            aVar.onDestroy();
            this.f4196l = null;
        }
        CashierQuickPayResultHandler cashierQuickPayResultHandler = this.f4197m;
        if (cashierQuickPayResultHandler != null) {
            cashierQuickPayResultHandler.onDestroy();
            this.f4197m = null;
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (x().b().q) {
            x().g().b(this, true);
            x().b().q = false;
        }
    }

    @Override // android.app.Activity
    public void overridePendingTransition(int i2, int i3) {
        super.overridePendingTransition(0, 0);
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: z */
    public a u() {
        return new a();
    }
}
