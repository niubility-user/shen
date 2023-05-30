package com.jd.lib.cashier.sdk.pay.handler;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.cashier.app.jdlibcutter.protocol.pay.qqpay.QQPayApiKey;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class CashierPayResultDispatcher extends BroadcastReceiver implements com.jd.lib.cashier.sdk.d.d.a {

    /* renamed from: g  reason: collision with root package name */
    private Activity f4155g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f4156h;

    /* renamed from: i  reason: collision with root package name */
    private d f4157i;

    /* renamed from: j  reason: collision with root package name */
    private d f4158j;

    /* renamed from: k  reason: collision with root package name */
    private d f4159k;

    /* renamed from: l  reason: collision with root package name */
    private d f4160l;

    /* renamed from: m  reason: collision with root package name */
    private d f4161m;

    /* renamed from: n  reason: collision with root package name */
    private d f4162n;
    private d o;
    private d p;
    private d q;

    public CashierPayResultDispatcher(CashierPayActivity cashierPayActivity) {
        this.f4155g = cashierPayActivity;
        a();
        this.f4157i = new k(cashierPayActivity);
        this.f4158j = new i(cashierPayActivity);
        this.f4159k = new f(cashierPayActivity);
        this.f4160l = new j(cashierPayActivity);
        this.f4161m = new g(cashierPayActivity);
        this.f4162n = new h(cashierPayActivity);
        this.o = new b(cashierPayActivity);
        this.p = new c(cashierPayActivity);
        this.q = new e(cashierPayActivity);
    }

    public void a() {
        if (this.f4156h) {
            return;
        }
        this.f4156h = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jd.wxPayResult");
        intentFilter.addAction("com.jd.QQPayResult");
        intentFilter.addAction(QQPayApiKey.QQ_PAY_STOP_ACTION);
        intentFilter.addAction("com.jd.octopusPayFail");
        intentFilter.addAction("com.jd.cyberMoneyPayFail");
        intentFilter.addAction("com.jd.query.pay.api.fail.action");
        LocalBroadcastManager.getInstance(this.f4155g.getApplicationContext()).registerReceiver(this, intentFilter);
    }

    public void c() {
        this.f4156h = false;
        LocalBroadcastManager.getInstance(this.f4155g.getApplicationContext()).unregisterReceiver(this);
    }

    public void j(int i2, int i3, Intent intent) {
        d dVar = this.f4159k;
        if (dVar != null) {
            dVar.j(i2, i3, intent);
        }
        d dVar2 = this.f4160l;
        if (dVar2 != null) {
            dVar2.j(i2, i3, intent);
        }
        d dVar3 = this.f4161m;
        if (dVar3 != null) {
            dVar3.j(i2, i3, intent);
        }
        d dVar4 = this.o;
        if (dVar4 != null) {
            dVar4.j(i2, i3, intent);
        }
        d dVar5 = this.p;
        if (dVar5 != null) {
            dVar5.j(i2, i3, intent);
        }
        d dVar6 = this.q;
        if (dVar6 != null) {
            dVar6.j(i2, i3, intent);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        c();
        d dVar = this.f4161m;
        if (dVar != null) {
            dVar.onDestroy();
            this.f4161m = null;
        }
        d dVar2 = this.f4160l;
        if (dVar2 != null) {
            dVar2.onDestroy();
            this.f4160l = null;
        }
        d dVar3 = this.f4159k;
        if (dVar3 != null) {
            dVar3.onDestroy();
            this.f4159k = null;
        }
        d dVar4 = this.f4157i;
        if (dVar4 != null) {
            dVar4.onDestroy();
            this.f4157i = null;
        }
        d dVar5 = this.f4158j;
        if (dVar5 != null) {
            dVar5.onDestroy();
            this.f4158j = null;
        }
        d dVar6 = this.f4162n;
        if (dVar6 != null) {
            dVar6.onDestroy();
            this.f4162n = null;
        }
        if (this.f4155g != null) {
            this.f4155g = null;
        }
        d dVar7 = this.o;
        if (dVar7 != null) {
            dVar7.onDestroy();
            this.o = null;
        }
        d dVar8 = this.p;
        if (dVar8 != null) {
            dVar8.onDestroy();
            this.p = null;
        }
        d dVar9 = this.q;
        if (dVar9 != null) {
            dVar9.onDestroy();
            this.q = null;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            r.b("CashierPayResultHandler", "PayResult.getAction-->" + intent.getAction());
            d dVar = this.f4158j;
            if (dVar != null) {
                dVar.j(-1, -1, intent);
            }
            d dVar2 = this.f4157i;
            if (dVar2 != null) {
                dVar2.j(-1, -1, intent);
            }
            d dVar3 = this.f4161m;
            if (dVar3 != null) {
                dVar3.j(-1, -1, intent);
            }
            d dVar4 = this.f4162n;
            if (dVar4 != null) {
                dVar4.j(-1, -1, intent);
            }
            d dVar5 = this.o;
            if (dVar5 != null) {
                dVar5.j(-1, -1, intent);
            }
        }
    }
}
