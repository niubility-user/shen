package com.jd.lib.cashier.sdk.freindpaydialog.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.b.h.c;
import com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.impl.CashierDialogGetSuccessUrlImpl;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.impl.CashierFriendPayDialogFailImpl;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.impl.CashierFriendPayDialogSucImpl;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.impl.CashierFriendPayShowDialogImpl;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.viewmodel.FriendPayDialogViewModel;
import com.jd.lib.cashier.sdk.pay.aac.impl.e.e;

/* loaded from: classes14.dex */
public class FriendPayDialogActivity extends AbsCashierActivity<FriendPayDialogViewModel, com.jd.lib.cashier.sdk.g.a.c.a> implements Observer<Integer> {

    /* renamed from: i  reason: collision with root package name */
    private boolean f3433i;

    /* renamed from: j  reason: collision with root package name */
    private View f3434j;

    /* renamed from: k  reason: collision with root package name */
    private View f3435k;

    /* renamed from: l  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.freindpaydialog.aac.impl.b f3436l;

    /* renamed from: m  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.freindpaydialog.aac.impl.a f3437m;

    /* renamed from: n  reason: collision with root package name */
    private CashierDialogGetSuccessUrlImpl f3438n;
    private e o;
    private final AlphaAnimation p = new AlphaAnimation(0.0f, 1.0f);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FriendPayDialogActivity.this.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements Animation.AnimationListener {
        b() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            if (FriendPayDialogActivity.this.f3434j == null || FriendPayDialogActivity.this.f3435k == null) {
                return;
            }
            FriendPayDialogActivity.this.f3435k.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_7F000000));
            FriendPayDialogActivity.this.f3434j.setBackgroundColor(0);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            if (FriendPayDialogActivity.this.f3434j != null) {
                FriendPayDialogActivity.this.f3434j.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_7F000000));
            }
        }
    }

    private void A() {
        AlphaAnimation alphaAnimation;
        if (this.f3434j == null || (alphaAnimation = this.p) == null) {
            return;
        }
        alphaAnimation.setDuration(200L);
        this.p.setAnimationListener(new b());
        this.f3434j.startAnimation(this.p);
    }

    private void D() {
        AlphaAnimation alphaAnimation;
        if (this.f3434j == null || this.f3435k == null || (alphaAnimation = this.p) == null) {
            return;
        }
        alphaAnimation.cancel();
        this.p.setAnimationListener(null);
        this.f3434j.setBackgroundColor(0);
        this.f3435k.setBackgroundColor(0);
    }

    private void E() {
        CashierFriendPayDialogSucImpl cashierFriendPayDialogSucImpl = new CashierFriendPayDialogSucImpl(this);
        this.f3436l = cashierFriendPayDialogSucImpl;
        cashierFriendPayDialogSucImpl.f(this);
        this.f3436l.h(getWindow());
        CashierFriendPayDialogFailImpl cashierFriendPayDialogFailImpl = new CashierFriendPayDialogFailImpl(this);
        this.f3437m = cashierFriendPayDialogFailImpl;
        cashierFriendPayDialogFailImpl.f(this);
        this.f3437m.h(getWindow());
        CashierDialogGetSuccessUrlImpl cashierDialogGetSuccessUrlImpl = new CashierDialogGetSuccessUrlImpl(this, w());
        this.f3438n = cashierDialogGetSuccessUrlImpl;
        cashierDialogGetSuccessUrlImpl.f(this);
        this.f3438n.h(getWindow());
        CashierFriendPayShowDialogImpl cashierFriendPayShowDialogImpl = new CashierFriendPayShowDialogImpl(this);
        this.o = cashierFriendPayShowDialogImpl;
        cashierFriendPayShowDialogImpl.f(this);
    }

    private void F() {
        if (x().b().f3469m == null) {
            if (TextUtils.equals(x().b().f3466j, "1")) {
                w().b(this);
            }
            finish();
        } else {
            x().e(this);
        }
        com.jd.lib.cashier.sdk.g.e.a.b(this);
    }

    private void initView() {
        this.f3435k = findViewById(R.id.lib_cashier_friend_pay_dialog_root);
        View findViewById = findViewById(R.id.lib_cashier_friend_pay_dialog_blank);
        this.f3434j = findViewById;
        findViewById.setOnClickListener(new a());
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: B  reason: merged with bridge method [inline-methods] */
    public com.jd.lib.cashier.sdk.g.a.c.a u() {
        return new com.jd.lib.cashier.sdk.g.a.c.a();
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: C  reason: merged with bridge method [inline-methods] */
    public FriendPayDialogViewModel v() {
        return (FriendPayDialogViewModel) ViewModelProviders.of(this).get(FriendPayDialogViewModel.class);
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: G  reason: merged with bridge method [inline-methods] */
    public void onChanged(Integer num) {
        if (num != null) {
            com.jd.lib.cashier.sdk.freindpaydialog.aac.impl.a aVar = this.f3437m;
            if (aVar != null) {
                aVar.onChangeSkin();
            }
            com.jd.lib.cashier.sdk.freindpaydialog.aac.impl.b bVar = this.f3436l;
            if (bVar != null) {
                bVar.onChangeSkin();
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    public int createLayout() {
        return R.layout.lib_cashier_sdk_friend_pay_dialog_layout;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, android.app.Activity
    public void finish() {
        if (this.f3433i) {
            D();
            this.f3433i = false;
        }
        super.finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        F();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        this.statusBarTransparentEnable = true;
        super.onCreate(bundle);
        if (!x().l(getIntent())) {
            finish();
        }
        PayTaskStackManager.addCashierFriendPayDialog(this);
        initView();
        E();
        x().d(this);
        y.c(this, this, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.jd.lib.cashier.sdk.freindpaydialog.aac.impl.b bVar = this.f3436l;
        if (bVar != null) {
            bVar.onDestroy();
            this.f3436l = null;
        }
        com.jd.lib.cashier.sdk.freindpaydialog.aac.impl.a aVar = this.f3437m;
        if (aVar != null) {
            aVar.onDestroy();
            this.f3437m = null;
        }
        CashierDialogGetSuccessUrlImpl cashierDialogGetSuccessUrlImpl = this.f3438n;
        if (cashierDialogGetSuccessUrlImpl != null) {
            cashierDialogGetSuccessUrlImpl.onDestroy();
            this.f3438n = null;
        }
        e eVar = this.o;
        if (eVar != null) {
            eVar.onDestroy();
            this.o = null;
        }
        c.c(this);
        y.v(this);
    }

    @Override // android.app.Activity
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        if (this.f3433i) {
            return;
        }
        A();
        this.f3433i = true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c.b(this);
    }

    @Override // android.app.Activity
    public void overridePendingTransition(int i2, int i3) {
        super.overridePendingTransition(R.anim.lib_cashier_sdk_dialog_bottom_enter, R.anim.lib_cashier_sdk_dialog_bottom_exit);
    }
}
