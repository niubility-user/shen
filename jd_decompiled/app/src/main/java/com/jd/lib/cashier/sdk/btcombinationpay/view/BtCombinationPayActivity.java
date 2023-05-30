package com.jd.lib.cashier.sdk.btcombinationpay.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.btcombinationpay.aac.viewmodel.BtCombinationPayViewModel;
import com.jd.lib.cashier.sdk.btcombinationpay.impl.CashierBtCombinationPayImpl;
import com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.y;

/* loaded from: classes14.dex */
public class BtCombinationPayActivity extends AbsCashierActivity<BtCombinationPayViewModel, com.jd.lib.cashier.sdk.a.a.c.a> implements Observer<Integer> {
    private static final String o = BtCombinationPayActivity.class.getSimpleName();

    /* renamed from: i */
    private boolean f2901i;

    /* renamed from: j */
    private ViewGroup f2902j;

    /* renamed from: k */
    private View f2903k;

    /* renamed from: l */
    private CashierBtCombinationPayImpl f2904l;

    /* renamed from: m */
    private com.jd.lib.cashier.sdk.a.c.b f2905m;

    /* renamed from: n */
    private final AlphaAnimation f2906n = new AlphaAnimation(0.0f, 1.0f);

    /* loaded from: classes14.dex */
    public class a implements View.OnClickListener {
        a() {
            BtCombinationPayActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BtCombinationPayActivity.this.onBackPressed();
        }
    }

    /* loaded from: classes14.dex */
    public class b implements Animation.AnimationListener {
        b() {
            BtCombinationPayActivity.this = r1;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            if (BtCombinationPayActivity.this.f2903k == null || BtCombinationPayActivity.this.f2902j == null) {
                return;
            }
            BtCombinationPayActivity.this.f2902j.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_7F000000));
            BtCombinationPayActivity.this.f2903k.setBackgroundColor(0);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            if (BtCombinationPayActivity.this.f2903k != null) {
                BtCombinationPayActivity.this.f2903k.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_7F000000));
            }
        }
    }

    private void A() {
        AlphaAnimation alphaAnimation;
        if (this.f2903k == null || (alphaAnimation = this.f2906n) == null) {
            return;
        }
        alphaAnimation.setDuration(200L);
        this.f2906n.setAnimationListener(new b());
        this.f2903k.startAnimation(this.f2906n);
    }

    private void E() {
        AlphaAnimation alphaAnimation;
        if (this.f2903k == null || this.f2902j == null || (alphaAnimation = this.f2906n) == null) {
            return;
        }
        alphaAnimation.cancel();
        this.f2906n.setAnimationListener(null);
        this.f2903k.setBackgroundColor(0);
        this.f2902j.setBackgroundColor(0);
    }

    private void F() {
        CashierBtCombinationPayImpl cashierBtCombinationPayImpl = new CashierBtCombinationPayImpl(this);
        this.f2904l = cashierBtCombinationPayImpl;
        cashierBtCombinationPayImpl.f(this);
        this.f2904l.h(getWindow());
        this.f2905m = new com.jd.lib.cashier.sdk.a.c.b(this, x().e(), x().d());
    }

    private void G() {
        com.jd.lib.cashier.sdk.d.g.b.a.a().d(this, x().e(), x().d());
        finish();
    }

    private void I() {
        initView();
        F();
        x().g().b(this);
        y.c(this, this, false);
    }

    private void initView() {
        this.f2902j = (ViewGroup) findViewById(R.id.lib_cashier_bt_combination_pay_root);
        View findViewById = findViewById(R.id.lib_cashier_bt_combination_pay_blank);
        this.f2903k = findViewById;
        findViewById.setOnClickListener(new a());
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: B */
    public com.jd.lib.cashier.sdk.a.a.c.a u() {
        return new com.jd.lib.cashier.sdk.a.a.c.a();
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: C */
    public BtCombinationPayViewModel v() {
        return (BtCombinationPayViewModel) ViewModelProviders.of(this).get(BtCombinationPayViewModel.class);
    }

    public void D(String str, CashierCommonPopConfig cashierCommonPopConfig, CashierCommonPopConfig cashierCommonPopConfig2) {
        Intent intent = new Intent();
        intent.setAction("com.jd.query.pay.api.fail.action");
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("query_pay_api_fail_msg_key", str);
        }
        if (cashierCommonPopConfig2 != null && cashierCommonPopConfig2.canDialogShow()) {
            bundle.putParcelable("query_pay_api_fail_common_pop_key", cashierCommonPopConfig2);
        }
        if (cashierCommonPopConfig != null && cashierCommonPopConfig.canDialogShow()) {
            bundle.putParcelable("query_pay_api_fail_pop_order_exception_key", cashierCommonPopConfig);
        }
        intent.putExtras(bundle);
        com.jd.lib.cashier.sdk.a.c.b bVar = this.f2905m;
        if (bVar != null) {
            bVar.a(bundle);
        }
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: H */
    public void onChanged(Integer num) {
        CashierBtCombinationPayImpl cashierBtCombinationPayImpl;
        if (num == null || (cashierBtCombinationPayImpl = this.f2904l) == null) {
            return;
        }
        cashierBtCombinationPayImpl.onChangeSkin();
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    public int createLayout() {
        return R.layout.lib_cashier_sdk_bt_combination_pay_layout;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, android.app.Activity
    public void finish() {
        if (this.f2901i) {
            E();
            this.f2901i = false;
        }
        super.finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        r.b(o, "onActivityResult() requestCode = " + i2 + " , resultCode = " + i3);
        com.jd.lib.cashier.sdk.a.c.b bVar = this.f2905m;
        if (bVar != null) {
            bVar.j(i2, i3, intent);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        G();
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        this.statusBarTransparentEnable = true;
        super.onCreate(bundle);
        if (!x().i(getIntent())) {
            finish();
        }
        PayTaskStackManager.addCashierBtCombination(this);
        I();
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CashierBtCombinationPayImpl cashierBtCombinationPayImpl = this.f2904l;
        if (cashierBtCombinationPayImpl != null) {
            cashierBtCombinationPayImpl.onDestroy();
        }
        com.jd.lib.cashier.sdk.a.c.b bVar = this.f2905m;
        if (bVar != null) {
            bVar.onDestroy();
            this.f2905m = null;
        }
        y.v(this);
    }

    @Override // android.app.Activity
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        if (this.f2901i) {
            return;
        }
        A();
        this.f2901i = true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Activity
    public void overridePendingTransition(int i2, int i3) {
        super.overridePendingTransition(R.anim.lib_cashier_sdk_dialog_bottom_enter, R.anim.lib_cashier_sdk_dialog_bottom_exit);
    }
}
