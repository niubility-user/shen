package com.jd.lib.cashier.sdk.g.e;

import android.content.Context;
import android.text.TextUtils;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.d.e.b;
import com.jd.lib.cashier.sdk.freindpaydialog.view.FriendPayDialogActivity;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class a {
    private static String a(Context context) {
        if (context instanceof FriendPayDialogActivity) {
            FriendPayDialogActivity friendPayDialogActivity = (FriendPayDialogActivity) context;
            return g0.a(friendPayDialogActivity) ? friendPayDialogActivity.x().b().f3463g : "";
        }
        return "";
    }

    public static void b(Context context) {
        HashMap hashMap = new HashMap();
        String a = a(context);
        if (!TextUtils.isEmpty(a)) {
            hashMap.put("appid", a);
        }
        b.g(context, 1000, "JDCashierNew_PayBehalfRequest_FriendPopClose", o.b(hashMap));
    }

    public static void c(Context context, String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("code", str);
        hashMap.put("payid", str4);
        hashMap.put("orderid", str3);
        hashMap.put("resultstatus", str2);
        String a = a(context);
        if (!TextUtils.isEmpty(a)) {
            hashMap.put("appid", a);
        }
        b.g(context, 1000, "JDCashierNew_Home_Payresult", o.b(hashMap));
    }

    public static void d(Context context) {
        HashMap hashMap = new HashMap();
        String a = a(context);
        if (!TextUtils.isEmpty(a)) {
            hashMap.put("appid", a);
        }
        b.g(context, 1000, "JDCashierNew_PayBehalfRequest_SendRequest", o.b(hashMap));
    }

    public static void e(Context context) {
        HashMap hashMap = new HashMap();
        String a = a(context);
        if (!TextUtils.isEmpty(a)) {
            hashMap.put("appid", a);
        }
        b.g(context, 1000, "JDCashierNew_PayBehalfRequest_FriendPopExpo", o.b(hashMap));
    }
}
