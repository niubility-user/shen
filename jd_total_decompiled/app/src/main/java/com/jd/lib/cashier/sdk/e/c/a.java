package com.jd.lib.cashier.sdk.e.c;

import android.content.Context;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity;
import com.jd.lib.cashier.sdk.d.e.b;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class a {
    private static final Map<String, String> a = new HashMap(5);
    private static final Map<String, String> b = new HashMap();

    private static String a(Context context) {
        if (context instanceof CashierCreditPayActivity) {
            CashierCreditPayActivity cashierCreditPayActivity = (CashierCreditPayActivity) context;
            return g0.a(cashierCreditPayActivity) ? cashierCreditPayActivity.x().b().b : "";
        }
        return "";
    }

    public static void b(Context context) {
        Map<String, String> map = a;
        map.clear();
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.g(context, 4000, "JDCashierNew_TryHome_AgreementEnter", o.b(map));
    }

    public static void c(Context context) {
        Map<String, String> map = a;
        map.clear();
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.a(context, 4000, "JDCashierNew_TryHome_ComfirmExpo", o.b(map));
    }

    public static void d(Context context) {
        Map<String, String> map = a;
        map.clear();
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.g(context, 4000, "JDCashierNew_TryHome_Comfirm", o.b(map));
    }

    public static void e(Context context, String str, String str2, String str3) {
        Map<String, String> map = a;
        map.clear();
        map.put("code", str);
        map.put("orderid", str3);
        map.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.a(context, 4000, "JDCashierNew_TryHome_SecToastPaymentExpo", o.b(map));
    }

    public static void f(Context context, String str, String str2) {
        Map<String, String> map = a;
        map.clear();
        map.put("code", str);
        map.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.a(context, 4000, "JDCashierNew_TryHome_PaymentExpo", o.b(map));
    }

    public static void g(Context context, String str) {
        Map<String, String> map = a;
        map.clear();
        map.put("abnormalorderno", str);
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.a(context, 4000, "JDCashierNew_Home_AbnormalOrderToastExpo", o.b(map));
    }

    public static void h(Context context, String str, String str2) {
        Map<String, String> map = a;
        map.clear();
        map.put("orderid", str);
        map.put("payprice", str2);
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.a(context, 4000, "JDCashierNew_TryHome_ParamExpo", o.b(map));
    }

    public static void i(Context context) {
        Map<String, String> map = a;
        map.clear();
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.a(context, 4000, "JDCashierNew_TryHome_AgreementEnterExpo", o.b(map));
    }

    public static void j(Context context, String str) {
        Map<String, String> map = a;
        map.clear();
        map.put("orderid", str);
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.g(context, 4000, "JDCashierNew_TryHome_BackPopContinue", o.b(map));
    }

    public static void k(Context context, String str) {
        Map<String, String> map = a;
        map.clear();
        map.put("orderid", str);
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.g(context, 4000, "JDCashierNew_TryHome_BackPopBack", o.b(map));
    }

    public static void l(Context context, String str) {
        Map<String, String> map = a;
        map.clear();
        map.put("orderid", str);
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.a(context, 4000, "JDCashierNew_TryHome_BackPopExpo", o.b(map));
    }

    public static void m(Context context, String str) {
        Map<String, String> map = a;
        map.clear();
        map.put("abnormalorderno", str);
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.g(context, 4000, "JDCashierNew_Home_AbnormalOrderToastClose", o.b(map));
    }

    public static void n(Context context, String str) {
        Map<String, String> map = a;
        map.clear();
        map.put("abnormalorderno", str);
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.g(context, 4000, "JDCashierNew_Home_AbnormalOrderToastCheck", o.b(map));
    }

    public static void o(Context context, String str, String str2) {
        Map<String, String> map = a;
        map.clear();
        map.put("code", str);
        map.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.g(context, 4000, "JDCashierNew_TryHome_Payment", o.b(map));
    }

    public static void p(Context context, String str, String str2, String str3) {
        Map<String, String> map = a;
        map.clear();
        map.put("code", str);
        map.put("orderid", str3);
        map.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.g(context, 4000, "JDCashierNew_TryHome_SecToastPayment", o.b(map));
    }

    public static void q(Context context) {
        Map<String, String> map = b;
        map.clear();
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.j(context, 4000, o.b(map));
    }
}
