package com.jd.lib.cashier.sdk.d.g.b;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.cashier.app.jdlibcutter.protocol.pay.qqpay.QQPayApiKey;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.r;

/* loaded from: classes14.dex */
public class b implements QQPayApiKey {

    /* renamed from: h  reason: collision with root package name */
    private static volatile b f3260h = null;

    /* renamed from: i  reason: collision with root package name */
    private static final String f3261i = "b";

    /* renamed from: g  reason: collision with root package name */
    private LocalBroadcastManager f3262g;

    private b() {
    }

    public static b a() {
        if (f3260h == null) {
            synchronized (b.class) {
                if (f3260h == null) {
                    f3260h = new b();
                }
            }
        }
        return f3260h;
    }

    private synchronized void d(Context context, Intent intent) {
        if (context == null) {
            return;
        }
        if (this.f3262g == null) {
            this.f3262g = LocalBroadcastManager.getInstance(context.getApplicationContext());
        }
        this.f3262g.sendBroadcast(intent);
    }

    public synchronized void b(Context context, String str, CashierCommonPopConfig cashierCommonPopConfig, CashierCommonPopConfig cashierCommonPopConfig2) {
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
        d(context, intent);
        r.b(f3261i, "on executing sendCommonPayFailEvent");
    }

    public synchronized void c(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.jd.cyberMoneyPayFail");
        d(context, intent);
        r.b(f3261i, "on executing sendCyberMoneyPayFailEvent");
    }

    public synchronized void e(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.jd.octopusPayFail");
        d(context, intent);
        r.b(f3261i, "on executing sendOctopusPayFailEvent");
    }

    public synchronized void f(Context context) {
        Intent intent = new Intent();
        intent.setAction(QQPayApiKey.QQ_PAY_STOP_ACTION);
        intent.putExtra(QQPayApiKey.QQ_PAY_PAYING_RESULT, 1000);
        d(context, intent);
        r.b(f3261i, "on executing sendQQPayBadParamEvent");
    }
}
