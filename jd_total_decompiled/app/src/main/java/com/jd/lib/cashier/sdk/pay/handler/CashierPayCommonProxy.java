package com.jd.lib.cashier.sdk.pay.handler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.MonitorClickBaiTiaoNoPlanEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.utils.pay.CashierDeskConstantBean;

/* loaded from: classes14.dex */
public class CashierPayCommonProxy extends BroadcastReceiver implements d {

    /* renamed from: g  reason: collision with root package name */
    private boolean f4153g;

    /* renamed from: h  reason: collision with root package name */
    private CashierPayActivity f4154h;

    public CashierPayCommonProxy(CashierPayActivity cashierPayActivity) {
        this.f4154h = cashierPayActivity;
        a();
    }

    public void a() {
        if (this.f4153g) {
            return;
        }
        this.f4153g = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("cashier_bt_combination_pay_result_action");
        intentFilter.addAction("cashier_bt_combination_pay_fee_action");
        LocalBroadcastManager.getInstance(this.f4154h.getApplicationContext()).registerReceiver(this, intentFilter);
    }

    public void c() {
        this.f4153g = false;
        LocalBroadcastManager.getInstance(this.f4154h.getApplicationContext()).unregisterReceiver(this);
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        Bundle extras = intent.getExtras();
        if (TextUtils.equals(intent.getAction(), "cashier_bt_combination_pay_result_action") && extras != null) {
            int i4 = extras.getInt("requestCode", -1);
            int i5 = extras.getInt("resultCode", -1);
            String string = extras.getString(CashierDeskConstantBean.JDCASHIER_BROADCAST_FOR_SETTLEMENT, "");
            Intent intent2 = new Intent();
            intent2.putExtra("jdpay_Result", string);
            this.f4154h.E(i4, i5, intent2);
        } else if (!TextUtils.equals(intent.getAction(), "cashier_bt_combination_pay_fee_action") || extras == null) {
        } else {
            com.jd.lib.cashier.sdk.b.i.e.f("MONITOR_CLICK_BAITIAO_CHANNEL_NO_PLAN_EVENT", new MonitorClickBaiTiaoNoPlanEvent(extras.getString(PairKey.CHANNEL_TYPE), extras.getString("channelId")));
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        c();
        if (this.f4154h != null) {
            this.f4154h = null;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        j(-1, -1, intent);
    }
}
