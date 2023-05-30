package com.jd.lib.cashier.sdk.d.g.b;

import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.pay.CashierDeskConstantBean;

/* loaded from: classes14.dex */
public class a {
    private static volatile a b = null;

    /* renamed from: c  reason: collision with root package name */
    private static final String f3259c = "a";
    private LocalBroadcastManager a;

    private a() {
    }

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public synchronized void b(Context context, Intent intent) {
        if (context == null) {
            return;
        }
        if (this.a == null) {
            this.a = LocalBroadcastManager.getInstance(context.getApplicationContext());
        }
        this.a.sendBroadcast(intent);
    }

    public synchronized void c(Context context) {
        Intent intent = new Intent();
        intent.setAction("cashier_pay_finish_event");
        b(context, intent);
        r.b(f3259c, "on executing sendEventBusToComplete");
    }

    public synchronized void d(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra(PairKey.CHANNEL_TYPE, str);
        intent.putExtra("channelId", str2);
        intent.setAction("cashier_bt_combination_pay_fee_action");
        b(context, intent);
        r.b(f3259c, "on executing sendEventBusToFeePlan");
    }

    public synchronized void e(Context context) {
        Intent intent = new Intent();
        intent.setAction(PairKey.CASHIER_BROADCAST_ACTION_FINISH_BTN_CLICK);
        b(context, intent);
    }

    public synchronized void f(Context context, String str, int i2, int i3, String str2, String str3) {
        Intent intent = new Intent();
        intent.setAction("cashier_bt_combination_pay_result_action");
        intent.putExtra(CashierDeskConstantBean.JDCASHIER_BROADCAST_FOR_SETTLEMENT, str);
        intent.putExtra("requestCode", i2);
        intent.putExtra("resultCode", i3);
        intent.putExtra(PairKey.CHANNEL_TYPE, str2);
        intent.putExtra("channelId", str3);
        b(context, intent);
        r.b(f3259c, "on executing sendEventBusToPayResult");
    }

    public synchronized void g(Context context) {
        Intent intent = new Intent();
        intent.setAction("pay_success");
        b(context, intent);
        r.b(f3259c, "on executing sendEventBusToPhoneCharge");
    }

    public synchronized void h(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.jingdong.cashierAction.payResult");
        intent.putExtra("productPayResult", JumpUtil.VAULE_DES_REACTNATIVE_PAYSUCCESS);
        b(context, intent);
        r.b(f3259c, "on executing sendEventBusToProductDetail");
    }

    public synchronized void i(Context context) {
        Intent intent = new Intent();
        intent.setAction(CashierDeskConstantBean.JDCASHIER_BROADCAST_FOR_SETTLEMENT);
        intent.putExtra(CashierDeskConstantBean.JDCASHIER_SETTLEMENT_OPT_TYPE, 10000);
        b(context, intent);
        r.b(f3259c, "on executing sendEventBusToSettlement");
    }

    public synchronized void j(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction(CashierDeskConstantBean.JDCASHIER_BROADCAST_FOR_SETTLEMENT);
        intent.putExtra(CashierDeskConstantBean.JDCASHIER_PLUS_INFO_FOR_SETTLEMENT, str);
        b(context, intent);
        r.b(f3259c, "on executing sendEventBusToSettlement");
    }
}
