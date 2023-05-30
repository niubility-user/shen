package com.jd.lib.cashier.sdk.complete.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.cashier.app.jdlibcutter.protocol.ui.push.SettingPushOpenListener;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.complete.aac.CashierCompleteViewModel;
import com.jd.lib.cashier.sdk.complete.jsbridge.CashierCustomJavaScript;
import com.jd.lib.cashier.sdk.complete.jsbridge.PayFinishJavaScript;
import com.jd.lib.cashier.sdk.complete.jsbridge.PayReminderScript;
import com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity;
import com.jd.lib.cashier.sdk.core.utils.e;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.i0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.core.utils.z;

/* loaded from: classes14.dex */
public class CashierCompleteActivity extends AbsCashierActivity<CashierCompleteViewModel, com.jd.lib.cashier.sdk.complete.aac.a> implements f<com.jd.lib.cashier.sdk.c.c.a>, Observer<Integer> {
    private static final String s = CashierCompleteActivity.class.getSimpleName();

    /* renamed from: i */
    private View f2929i;

    /* renamed from: j */
    private PayReminderScript f2930j;

    /* renamed from: k */
    private PayFinishJavaScript f2931k;

    /* renamed from: l */
    private com.jd.lib.cashier.sdk.c.d.c f2932l;

    /* renamed from: m */
    private CashierCustomJavaScript f2933m;

    /* renamed from: n */
    private com.jd.lib.cashier.sdk.c.d.d f2934n;
    private d o;
    private boolean p = false;
    private boolean q = true;
    private boolean r;

    /* loaded from: classes14.dex */
    public class a implements SettingPushOpenListener {
        a() {
            CashierCompleteActivity.this = r1;
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.push.SettingPushOpenListener
        public void pushOpenClicked() {
            CashierCompleteActivity.this.p = true;
        }
    }

    /* loaded from: classes14.dex */
    public class b implements Runnable {

        /* renamed from: g */
        final /* synthetic */ com.jd.lib.cashier.sdk.c.c.a f2935g;

        b(com.jd.lib.cashier.sdk.c.c.a aVar) {
            CashierCompleteActivity.this = r1;
            this.f2935g = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = this.f2935g.a;
            if (i2 != 1000) {
                if (i2 == 2000 && CashierCompleteActivity.this.f2932l != null) {
                    CashierCompleteActivity.this.f2932l.c();
                    return;
                }
                return;
            }
            if (CashierCompleteActivity.this.f2934n != null) {
                CashierCompleteActivity.this.x().b().f2910e = false;
                CashierCompleteActivity.this.f2934n.c();
            }
            if (CashierCompleteActivity.this.f2932l != null) {
                CashierCompleteActivity.this.f2932l.a();
            }
        }
    }

    /* loaded from: classes14.dex */
    public class c implements Runnable {
        c() {
            CashierCompleteActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CashierCompleteActivity.this.f2929i != null) {
                i0.g(CashierCompleteActivity.this.f2929i, "javascript:successH5DidBecomeActive()");
            }
        }
    }

    /* loaded from: classes14.dex */
    public static class d extends BroadcastReceiver {
        private d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                r.b(CashierCompleteActivity.s, "event from event dispatcher -->" + intent.getAction());
                if (TextUtils.equals(intent.getAction(), "cashier_pay_finish_event")) {
                    y.w();
                }
            }
        }

        /* synthetic */ d(a aVar) {
            this();
        }
    }

    private boolean G() {
        if (-1 != x().b().d) {
            setResult(-1);
        } else {
            PayFinishJavaScript payFinishJavaScript = this.f2931k;
            if (payFinishJavaScript != null && payFinishJavaScript.getCashDeskConfig() != null) {
                e.a(this.f2931k.getCashDeskConfig());
            }
        }
        finish();
        return true;
    }

    private void H() {
        String str = x().b().f2909c;
        if (TextUtils.isEmpty(str) || this.f2929i == null || !g0.a(this)) {
            return;
        }
        i0.h(this.f2929i, str);
    }

    private void I() {
        this.statusBarTransparentEnable = true;
        if (getIntent() != null) {
            this.statusBarTransparentEnable = getIntent().getBooleanExtra("statusBarHint", true);
        }
    }

    private void J() {
        runOnUiThread(new c());
    }

    private void L() {
        this.f2929i = i0.e(this, new RelativeLayout.LayoutParams(-1, -1));
        if (this.f2931k == null) {
            PayFinishJavaScript payFinishJavaScript = new PayFinishJavaScript(this, this.f2929i, this);
            this.f2931k = payFinishJavaScript;
            i0.a(this.f2929i, payFinishJavaScript, PayFinishJavaScript.WEBJAVASCRIPT);
        }
        if (this.f2930j == null) {
            PayReminderScript payReminderScript = new PayReminderScript(this.f2929i);
            this.f2930j = payReminderScript;
            i0.a(this.f2929i, payReminderScript, PayReminderScript.JDAPPUNITE);
        }
        if (this.f2933m == null) {
            CashierCustomJavaScript cashierCustomJavaScript = new CashierCustomJavaScript(this);
            this.f2933m = cashierCustomJavaScript;
            i0.a(this.f2929i, cashierCustomJavaScript, CashierCustomJavaScript.BRIDGE_NAME);
        }
    }

    private void M() {
        if (this.f2932l == null) {
            if (x().b().f2911f) {
                this.f2932l = new com.jd.lib.cashier.sdk.c.d.a(this, x().b().b, this.f2931k, this.f2929i);
            } else {
                this.f2932l = new com.jd.lib.cashier.sdk.c.d.b(this, x().b().b, this.f2929i);
            }
            this.f2932l.onLayout();
        }
        if (this.f2934n == null) {
            this.f2934n = new com.jd.lib.cashier.sdk.c.d.d(this, this.f2929i, this.f2931k);
        }
        z.a(new a());
    }

    private void initData() {
        x().d(getIntent());
        this.o = new d(null);
    }

    private void initView() {
        L();
        M();
    }

    @Override // com.jd.lib.cashier.sdk.core.utils.f
    /* renamed from: D */
    public void callBack(com.jd.lib.cashier.sdk.c.c.a aVar) {
        if (aVar != null) {
            runOnUiThread(new b(aVar));
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: E */
    public com.jd.lib.cashier.sdk.complete.aac.a u() {
        return new com.jd.lib.cashier.sdk.complete.aac.a();
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    /* renamed from: F */
    public CashierCompleteViewModel v() {
        return (CashierCompleteViewModel) ViewModelProviders.of(this).get(CashierCompleteViewModel.class);
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: K */
    public void onChanged(Integer num) {
        com.jd.lib.cashier.sdk.c.d.c cVar = this.f2932l;
        if (cVar != null) {
            cVar.b();
        }
    }

    public void N() {
        if (this.r) {
            return;
        }
        this.r = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("cashier_pay_finish_event");
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(this.o, intentFilter);
    }

    public void O() {
        this.r = false;
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(this.o);
        this.o = null;
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity
    public int createLayout() {
        return R.layout.lib_cashier_sdk_complete_layout;
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        I();
        super.onCreate(bundle);
        PayTaskStackManager.addCashierFinishTask(this);
        initData();
        initView();
        N();
        H();
        y.c(this, this, false);
    }

    @Override // com.jd.lib.cashier.sdk.core.activity.AbsCashierActivity, com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        O();
        PayReminderScript payReminderScript = this.f2930j;
        if (payReminderScript != null) {
            payReminderScript.onDestroy();
            this.f2930j = null;
        }
        com.jd.lib.cashier.sdk.c.d.c cVar = this.f2932l;
        if (cVar != null) {
            cVar.onDestroy();
            this.f2932l = null;
        }
        com.jd.lib.cashier.sdk.c.d.d dVar = this.f2934n;
        if (dVar != null) {
            dVar.d();
            this.f2934n = null;
        }
        PayFinishJavaScript payFinishJavaScript = this.f2931k;
        if (payFinishJavaScript != null) {
            payFinishJavaScript.onDestroy();
            this.f2931k = null;
        }
        CashierCustomJavaScript cashierCustomJavaScript = this.f2933m;
        if (cashierCustomJavaScript != null) {
            cashierCustomJavaScript.onDestroy();
            this.f2933m = null;
        }
        View view = this.f2929i;
        if (view != null) {
            i0.i(view);
            this.f2929i = null;
        }
        y.b();
        y.v(this);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            if (G()) {
                return true;
            }
            return super.onKeyDown(i2, keyEvent);
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        y.b();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, android.app.Activity
    public void onRestart() {
        super.onRestart();
        if (this.p) {
            this.p = false;
            J();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.q) {
            com.jd.lib.cashier.sdk.c.e.a.c().d(this);
        }
        y.w();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        if (!g0.e()) {
            this.q = false;
        } else {
            this.q = true;
        }
        super.onStop();
    }
}
