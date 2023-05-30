package com.jd.lib.cashier.sdk.risk.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleChangeEventCreator;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeEvent;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeListener;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity;
import com.jd.lib.cashier.sdk.core.utils.e0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.core.utils.n;
import com.jd.lib.cashier.sdk.risk.aac.viewmodel.CashierRiskViewModel;

/* loaded from: classes14.dex */
public class CashierRiskActivity extends AbsCashierActivity<CashierRiskViewModel, com.jd.lib.cashier.sdk.j.a.c.a> implements ITitleThemeChangeListener {

    /* renamed from: i  reason: collision with root package name */
    private int f4200i = 5;

    /* renamed from: j  reason: collision with root package name */
    private n f4201j = new n();

    /* renamed from: k  reason: collision with root package name */
    private final Runnable f4202k = new a();

    /* renamed from: l  reason: collision with root package name */
    private View f4203l;

    /* renamed from: m  reason: collision with root package name */
    private TextView f4204m;

    /* renamed from: n  reason: collision with root package name */
    private TextView f4205n;
    private View o;
    private View p;
    private ITitleThemeChangeEvent q;

    /* loaded from: classes14.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CashierRiskActivity.z(CashierRiskActivity.this);
            if (CashierRiskActivity.this.f4200i > 0 || CashierRiskActivity.this.f4201j == null) {
                if (CashierRiskActivity.this.f4201j != null) {
                    CashierRiskActivity.this.f4201j.postDelayed(CashierRiskActivity.this.f4202k, 1000L);
                }
            } else {
                CashierRiskActivity.this.f4201j.removeCallbacks(CashierRiskActivity.this.f4202k);
                CashierRiskActivity.this.x().d(CashierRiskActivity.this);
            }
            CashierRiskActivity.this.L();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b extends com.jd.lib.cashier.sdk.core.utils.b {
        b() {
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            CashierRiskActivity.this.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c extends com.jd.lib.cashier.sdk.core.utils.b {
        c() {
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            CashierRiskActivity.this.x().d(CashierRiskActivity.this);
            if (CashierRiskActivity.this.f4205n != null) {
                CashierRiskActivity.this.f4205n.setText(CashierRiskActivity.this.getText(R.string.lib_cashier_sdk_risk_submit_order_checking_text));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class d implements Observer<com.jd.lib.cashier.sdk.j.a.b.a> {
        d() {
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onChanged(com.jd.lib.cashier.sdk.j.a.b.a aVar) {
            if (aVar != null) {
                int i2 = aVar.a;
                if (i2 == 1000) {
                    CashierRiskActivity.this.N();
                } else if (i2 != 2000) {
                    if (i2 != 3000) {
                        return;
                    }
                    CashierRiskActivity.this.M();
                } else {
                    CashierRiskActivity cashierRiskActivity = CashierRiskActivity.this;
                    CashierRiskActivity.this.w().c(cashierRiskActivity, cashierRiskActivity.x().b().b);
                    CashierRiskActivity.this.finish();
                }
            }
        }
    }

    private void I() {
        L();
        n nVar = this.f4201j;
        if (nVar != null) {
            nVar.postDelayed(this.f4202k, 1000L);
        }
    }

    private void J() {
        x().e().observe(this, new d());
    }

    private void K() {
        if (this.p == null) {
            View inflate = ((ViewStub) findViewById(R.id.lib_cashier_sdk_risk_error_root)).inflate();
            this.p = inflate;
            this.f4205n = (TextView) inflate.findViewById(R.id.lib_cashier_sdk_risk_error_msg);
            this.p.findViewById(R.id.lib_cashier_sdk_risk_try_again_btn).setOnClickListener(new c());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L() {
        TextView textView = this.f4204m;
        if (textView != null) {
            textView.setText(String.valueOf(this.f4200i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M() {
        j0.b(this.o);
        K();
        j0.d(this.p);
        this.f4205n.setText(getText(R.string.lib_cashier_sdk_credit_pay_service_exception));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        j0.b(this.o);
        K();
        j0.d(this.p);
        this.f4205n.setText(getText(R.string.lib_cashier_sdk_risk_submit_order_failed_text));
    }

    private void initView() {
        View findViewById = findViewById(R.id.lib_cashier_sdk_risk_loading_root);
        this.o = findViewById;
        this.f4204m = (TextView) findViewById.findViewById(R.id.lib_cashier_sdk_risk_count_down);
        this.f4203l = e0.c(this);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.lib_cashier_risk_title_root);
        ITitleChangeEventCreator titleChangeEventCreator = DependInitializer.getTitleChangeEventCreator();
        if (titleChangeEventCreator != null) {
            this.q = titleChangeEventCreator.instanceTitleThemeChangeEvent();
        }
        View view = this.f4203l;
        if (view != null) {
            view.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            e0.i(this.f4203l);
            e0.p(this.f4203l, true);
            e0.r(this.f4203l, getString(R.string.lib_cashier_sdk_risk_title_text));
            e0.n(this.f4203l, 8);
            e0.k(this.f4203l, new b());
            e0.e(this.f4203l);
        }
        if (viewGroup != null && this.f4203l != null) {
            viewGroup.removeAllViews();
            viewGroup.addView(this.f4203l);
        }
        ITitleThemeChangeEvent iTitleThemeChangeEvent = this.q;
        if (iTitleThemeChangeEvent != null) {
            iTitleThemeChangeEvent.registerTitleThemeChangeEvent(this);
        }
    }

    static /* synthetic */ int z(CashierRiskActivity cashierRiskActivity) {
        int i2 = cashierRiskActivity.f4200i;
        cashierRiskActivity.f4200i = i2 - 1;
        return i2;
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: G  reason: merged with bridge method [inline-methods] */
    public com.jd.lib.cashier.sdk.j.a.c.a u() {
        return new com.jd.lib.cashier.sdk.j.a.c.a();
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: H  reason: merged with bridge method [inline-methods] */
    public CashierRiskViewModel v() {
        return (CashierRiskViewModel) ViewModelProviders.of(this).get(CashierRiskViewModel.class);
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    public int createLayout() {
        return R.layout.lib_cashier_sdk_risk_layout;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        this.statusBarTransparentEnable = true;
        super.onCreate(bundle);
        x().f(getIntent());
        J();
        initView();
        I();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        n nVar = this.f4201j;
        if (nVar != null) {
            nVar.removeCallbacks(this.f4202k);
            this.f4201j = null;
        }
        ITitleThemeChangeEvent iTitleThemeChangeEvent = this.q;
        if (iTitleThemeChangeEvent != null) {
            iTitleThemeChangeEvent.unRegisterTitleThemeChangeEvent(this);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeListener
    public void onThemeChange(boolean z, String str) {
        View view = this.f4203l;
        if (view != null) {
            e0.f(view);
        }
    }
}
