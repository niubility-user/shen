package com.jd.lib.cashier.sdk.d.g.a.b;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.IJDPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.d.g.b.b;

/* loaded from: classes14.dex */
public class a extends com.jd.lib.cashier.sdk.d.g.g.a<com.jd.lib.cashier.sdk.d.g.a.c.a> implements Object {

    /* renamed from: h  reason: collision with root package name */
    private static final String f3243h = "a";

    private void e(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.a.c.a aVar) {
        if (fragmentActivity == null || aVar == null) {
            return;
        }
        p.h(fragmentActivity, aVar.b);
    }

    private void f(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.a.c.a aVar) {
        if (fragmentActivity == null || aVar == null) {
            return;
        }
        IJDPay jDPay = DependInitializer.getJDPay();
        Bundle bundle = new Bundle();
        bundle.putString("PAY_PARAM", aVar.d);
        bundle.putString("APP_ID", aVar.f3245e);
        bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_COUNTER_V4");
        bundle.putString("JDPAY_CHANNEL_TYPE", aVar.f3248h);
        try {
            bundle.putBoolean("JDPAY_TOAST_PRINT", false);
            if (!TextUtils.isEmpty(aVar.f3246f)) {
                bundle.putString("JDPAY_EXTEND_INFO", aVar.f3246f);
            }
            if (jDPay != null) {
                jDPay.doPay(fragmentActivity, bundle, 20);
            }
        } catch (Exception e2) {
            r.d(f3243h, "get sessionKey from jd pay in exception-->" + e2.getMessage());
            com.jd.lib.cashier.sdk.d.h.a.a("PayChannelFunction", "PayChannelException", "CyberMoneyPayApi.executePay()", e2.getMessage());
        }
    }

    private void g(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.a.c.a aVar) {
        if (!TextUtils.isEmpty(aVar.f3245e) && !TextUtils.isEmpty(aVar.d)) {
            f(fragmentActivity, aVar);
        } else if (TextUtils.isEmpty(aVar.b)) {
        } else {
            e(fragmentActivity, aVar);
        }
    }

    private void h(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.a.c.a aVar) {
        try {
            if (aVar.a()) {
                if (y.r(fragmentActivity, "cn.gov.pbc.dcep")) {
                    j(fragmentActivity, aVar);
                } else {
                    b.a().c(fragmentActivity);
                }
            } else {
                f0.c(fragmentActivity.getString(R.string.lib_cashier_sdk_cyber_money_pay_failure));
            }
        } catch (Exception e2) {
            r.d(f3243h, e2.getMessage());
            com.jd.lib.cashier.sdk.d.h.a.a("InItPaySdkFunction", "InItPaySdkNorException", "CyberMoneyPayApi.executePay()", e2.getMessage());
        }
    }

    private void i(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.a.c.a aVar) {
        if (fragmentActivity == null || aVar == null) {
            return;
        }
        IJDPay jDPay = DependInitializer.getJDPay();
        Bundle bundle = new Bundle();
        bundle.putString("PAY_PARAM", aVar.d);
        bundle.putString("APP_ID", aVar.f3245e);
        bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_COUNTER_V4");
        bundle.putString("JDPAY_CHANNEL_TYPE", aVar.f3248h);
        try {
            bundle.putString(JDPayApiKey.DCEP_ENTRANCE_VERIFY, "DCEP_PAY_COUNTER");
            bundle.putBoolean(JDPayApiKey.DCEP_TOAST_PRINT, false);
            if (!TextUtils.isEmpty(aVar.f3246f)) {
                bundle.putString(JDPayApiKey.DCEP_EXTRA_INFO, aVar.f3246f);
            }
            if (jDPay != null) {
                jDPay.doDcepPay(fragmentActivity, bundle);
            }
        } catch (Exception e2) {
            r.d(f3243h, e2.getMessage());
            com.jd.lib.cashier.sdk.d.h.a.a("InItPaySdkFunction", "InItPaySdkNorException", "CyberMoneyPayApi.executePay()", e2.getMessage());
        }
    }

    private void j(Activity activity, com.jd.lib.cashier.sdk.d.g.a.c.a aVar) {
        if (aVar == null || TextUtils.isEmpty(aVar.b)) {
            return;
        }
        activity.startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse(aVar.b)), 10001);
    }

    @Override // com.jd.lib.cashier.sdk.d.g.g.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void d(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.a.c.a aVar) {
        try {
            if (fragmentActivity == null || aVar == null) {
                if (fragmentActivity == null) {
                    return;
                }
                f0.c(fragmentActivity.getString(R.string.lib_cashier_sdk_cyber_money_pay_failure));
            } else if (TextUtils.equals(aVar.f3247g, "jdAppSdk")) {
                i(fragmentActivity, aVar);
            } else if (TextUtils.equals(aVar.f3247g, "ecnyApp")) {
                h(fragmentActivity, aVar);
            } else {
                g(fragmentActivity, aVar);
            }
        } catch (Exception e2) {
            r.d(f3243h, e2.getMessage());
            com.jd.lib.cashier.sdk.d.h.a.a("InItPaySdkFunction", "InItPaySdkNorException", "CyberMoneyPayApi.executePay()", e2.getMessage());
        }
    }
}
