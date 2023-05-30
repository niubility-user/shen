package com.jd.lib.cashier.sdk.c.e;

import android.content.Context;
import android.text.TextUtils;
import com.jd.lib.cashier.sdk.complete.view.CashierCompleteActivity;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.d.e.b;
import com.jingdong.common.utils.pay.CashierDeskMtaIDs;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class a {
    private static volatile a b;
    private Map<String, String> a = new HashMap();

    private String b(Context context) {
        if (context instanceof CashierCompleteActivity) {
            CashierCompleteActivity cashierCompleteActivity = (CashierCompleteActivity) context;
            return g0.a(cashierCompleteActivity) ? cashierCompleteActivity.x().b().b : "";
        }
        return "";
    }

    public static a c() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public void a(Context context, String str, String str2, String str3, String str4, String str5, Map<String, Object> map) {
        com.jd.lib.cashier.sdk.b.b.a aVar = new com.jd.lib.cashier.sdk.b.b.a();
        aVar.put((com.jd.lib.cashier.sdk.b.b.a) "appid", str);
        aVar.put((com.jd.lib.cashier.sdk.b.b.a) "source", str2);
        aVar.put((com.jd.lib.cashier.sdk.b.b.a) "leaveto", str3);
        aVar.put((com.jd.lib.cashier.sdk.b.b.a) "orderid", str5);
        aVar.put((com.jd.lib.cashier.sdk.b.b.a) "sourceType", str4);
        if (map != null && !map.isEmpty()) {
            for (String str6 : map.keySet()) {
                aVar.put((com.jd.lib.cashier.sdk.b.b.a) str6, (String) map.get(str6));
            }
        }
        b.g(context, 2000, CashierDeskMtaIDs.JDCHECKOUT_PAYMENT_SUCCESSFINISH, o.b(aVar));
    }

    public void d(Context context) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.clear();
        String b2 = b(context);
        if (!TextUtils.isEmpty(b2)) {
            this.a.put("appid", b2);
        }
        b.j(context, 2000, o.b(this.a));
    }

    public void e(Context context, String str, String str2) {
        try {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str2);
            jSONObject.put("appid", b(context));
            b.g(context, 2000, str, jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void f(Context context, String str, String str2) {
        try {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str2);
            jSONObject.put("appid", b(context));
            b.a(context, 2000, str, jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
