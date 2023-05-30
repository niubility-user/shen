package com.jd.lib.cashier.sdk.f.d;

import android.content.Context;
import android.text.TextUtils;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.d.e.b;
import com.jd.lib.cashier.sdk.freindpay.view.FriendPayActivity;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class a {
    private static final Map<String, String> a = new HashMap();
    private static final Map<String, String> b = new HashMap();

    private static String a(Context context) {
        if (context instanceof FriendPayActivity) {
            FriendPayActivity friendPayActivity = (FriendPayActivity) context;
            return g0.a(friendPayActivity) ? friendPayActivity.x().b().f3349g : "";
        }
        return "";
    }

    public static void b(Context context, String str) {
        Map<String, String> map = a;
        map.clear();
        map.put("isfromsettlementpage", TextUtils.equals(str, "1") ? "1" : "0");
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.g(context, 3000, "JDCashierNew_Home_SettlementGetBack", o.b(map));
    }

    public static void c(Context context, String str, String str2, String str3, String str4) {
        Map<String, String> map = a;
        map.clear();
        map.put("code", str);
        map.put("payid", str4);
        map.put("orderid", str3);
        map.put("resultstatus", str2);
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.g(context, 3000, "JDCashierNew_Home_Payresult", o.b(map));
    }

    public static void d(Context context) {
        Map<String, String> map = a;
        map.clear();
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.g(context, 3000, "JDCashierNew_PayBehalfRequest_SendRequest", o.b(map));
    }

    public static void e(Context context) {
        Map<String, String> map = b;
        map.clear();
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2)) {
            map.put("appid", a2);
        }
        b.j(context, 3000, o.b(map));
    }
}
