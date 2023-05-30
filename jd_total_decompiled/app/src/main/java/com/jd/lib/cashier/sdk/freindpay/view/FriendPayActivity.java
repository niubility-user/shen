package com.jd.lib.cashier.sdk.freindpay.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.aura.IAura;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleChangeEventCreator;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeEvent;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeListener;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity;
import com.jd.lib.cashier.sdk.core.utils.e0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.freindpay.aac.impl.CashierFriendPayFailImpl;
import com.jd.lib.cashier.sdk.freindpay.aac.impl.CashierFriendPaySucImpl;
import com.jd.lib.cashier.sdk.freindpay.aac.impl.CashierGetSuccessUrlImpl;
import com.jd.lib.cashier.sdk.freindpay.aac.impl.c;
import com.jd.lib.cashier.sdk.freindpay.aac.viewmodel.FriendPayViewModel;

/* loaded from: classes14.dex */
public class FriendPayActivity extends AbsCashierActivity<FriendPayViewModel, com.jd.lib.cashier.sdk.f.a.c.a> implements ITitleThemeChangeListener, Observer<Integer> {

    /* renamed from: i  reason: collision with root package name */
    private View f3405i;

    /* renamed from: j  reason: collision with root package name */
    private ViewGroup f3406j;

    /* renamed from: k  reason: collision with root package name */
    private ITitleThemeChangeEvent f3407k;

    /* renamed from: l  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.freindpay.aac.impl.b f3408l;

    /* renamed from: m  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.freindpay.aac.impl.a f3409m;

    /* renamed from: n  reason: collision with root package name */
    private c f3410n;
    private boolean o = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ boolean f3411j;

        a(boolean z) {
            this.f3411j = z;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            if (this.f3411j) {
                FriendPayActivity friendPayActivity = FriendPayActivity.this;
                FriendPayActivity.this.w().e(friendPayActivity, friendPayActivity.x().b().b);
                return;
            }
            FriendPayActivity.this.w().b(FriendPayActivity.this);
            FriendPayActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b extends com.jd.lib.cashier.sdk.core.utils.b {
        b() {
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            FriendPayActivity.this.onBackPressed();
        }
    }

    private void A() {
        CashierFriendPaySucImpl cashierFriendPaySucImpl = new CashierFriendPaySucImpl(this);
        this.f3408l = cashierFriendPaySucImpl;
        cashierFriendPaySucImpl.f(this);
        this.f3408l.h(getWindow());
        CashierFriendPayFailImpl cashierFriendPayFailImpl = new CashierFriendPayFailImpl(this);
        this.f3409m = cashierFriendPayFailImpl;
        cashierFriendPayFailImpl.f(this);
        this.f3409m.h(getWindow());
        CashierGetSuccessUrlImpl cashierGetSuccessUrlImpl = new CashierGetSuccessUrlImpl(this, w());
        this.f3410n = cashierGetSuccessUrlImpl;
        cashierGetSuccessUrlImpl.f(this);
        this.f3410n.h(getWindow());
    }

    private void B() {
        if (x().b().o == null) {
            if (TextUtils.equals(x().b().f3352j, "1")) {
                w().b(this);
            }
            finish();
            return;
        }
        x().e(this);
    }

    private void initView() {
        ITitleChangeEventCreator titleChangeEventCreator = DependInitializer.getTitleChangeEventCreator();
        if (titleChangeEventCreator != null) {
            this.f3407k = titleChangeEventCreator.instanceTitleThemeChangeEvent();
        }
        this.f3405i = e0.c(this);
        this.f3406j = (ViewGroup) findViewById(R.id.lib_cashier_friend_pay_title_root);
        View view = this.f3405i;
        if (view != null) {
            view.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            e0.i(this.f3405i);
            e0.p(this.f3405i, true);
            e0.r(this.f3405i, getString(R.string.lib_cashier_sdk_friend_pay_title));
            if (TextUtils.equals(x().b().f3352j, "1")) {
                IAura aura = DependInitializer.getAura();
                boolean z = (TextUtils.isEmpty(x().b().b) || aura == null || !aura.isBundlePrepared("com.jd.lib.orderinfocard")) ? false : true;
                e0.n(this.f3405i, 0);
                e0.m(this.f3405i, getString(z ? R.string.lib_cashier_sdk_pay_order_detail_txt : R.string.lib_cashier_sdk_pay_right_btn_txt));
                e0.l(this.f3405i, new a(z));
            } else {
                e0.n(this.f3405i, 8);
            }
            e0.k(this.f3405i, new b());
            e0.e(this.f3405i);
        }
        ViewGroup viewGroup = this.f3406j;
        if (viewGroup != null && this.f3405i != null) {
            viewGroup.removeAllViews();
            this.f3406j.addView(this.f3405i);
        }
        ITitleThemeChangeEvent iTitleThemeChangeEvent = this.f3407k;
        if (iTitleThemeChangeEvent != null) {
            iTitleThemeChangeEvent.registerTitleThemeChangeEvent(this);
        }
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: C  reason: merged with bridge method [inline-methods] */
    public void onChanged(Integer num) {
        if (num != null) {
            com.jd.lib.cashier.sdk.freindpay.aac.impl.a aVar = this.f3409m;
            if (aVar != null) {
                aVar.onChangeSkin();
            }
            com.jd.lib.cashier.sdk.freindpay.aac.impl.b bVar = this.f3408l;
            if (bVar != null) {
                bVar.onChangeSkin();
            }
            View view = this.f3405i;
            if (view != null) {
                e0.e(view);
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    public int createLayout() {
        return R.layout.lib_cashier_sdk_friend_pay_layout;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, android.app.Activity
    public void finish() {
        if (!TextUtils.isEmpty(x().b().f3353k)) {
            com.jd.lib.cashier.sdk.d.g.b.a.a().i(this);
        }
        super.finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        x().b().c().c(i2, i3, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        com.jd.lib.cashier.sdk.f.d.a.b(this, x().b().f3352j);
        B();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        this.statusBarTransparentEnable = true;
        super.onCreate(bundle);
        PayTaskStackManager.addCashierFriendPay(this);
        if (!x().k(getIntent())) {
            finish();
        }
        initView();
        A();
        x().d(this);
        y.c(this, this, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.f3405i != null) {
            this.f3405i = null;
        }
        com.jd.lib.cashier.sdk.freindpay.aac.impl.b bVar = this.f3408l;
        if (bVar != null) {
            bVar.onDestroy();
            this.f3408l = null;
        }
        com.jd.lib.cashier.sdk.freindpay.aac.impl.a aVar = this.f3409m;
        if (aVar != null) {
            aVar.onDestroy();
            this.f3409m = null;
        }
        c cVar = this.f3410n;
        if (cVar != null) {
            cVar.onDestroy();
            this.f3410n = null;
        }
        ViewGroup viewGroup = this.f3406j;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
            this.f3406j = null;
        }
        com.jd.lib.cashier.sdk.b.h.c.c(this);
        y.b();
        y.v(this);
        ITitleThemeChangeEvent iTitleThemeChangeEvent = this.f3407k;
        if (iTitleThemeChangeEvent != null) {
            iTitleThemeChangeEvent.unRegisterTitleThemeChangeEvent(this);
        }
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
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        y.w();
        com.jd.lib.cashier.sdk.b.h.c.b(this);
        if (this.o) {
            com.jd.lib.cashier.sdk.f.d.a.e(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        if (!g0.e()) {
            this.o = false;
        } else {
            this.o = true;
        }
        super.onStop();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeListener
    public void onThemeChange(boolean z, String str) {
        View view = this.f3405i;
        if (view != null) {
            e0.f(view);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: y  reason: merged with bridge method [inline-methods] */
    public com.jd.lib.cashier.sdk.f.a.c.a u() {
        return new com.jd.lib.cashier.sdk.f.a.c.a();
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: z  reason: merged with bridge method [inline-methods] */
    public FriendPayViewModel v() {
        return (FriendPayViewModel) ViewModelProviders.of(this).get(FriendPayViewModel.class);
    }
}
