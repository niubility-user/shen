package com.jd.lib.cashier.sdk.a.d;

import android.content.Context;
import android.text.TextUtils;
import com.jd.lib.cashier.sdk.btcombinationpay.view.BtCombinationPayActivity;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.d.e.b;
import java.util.Map;

/* loaded from: classes14.dex */
public class a {
    private static volatile a b;
    private Map<String, Object> a = new com.jd.lib.cashier.sdk.b.b.a();

    private String a(Context context) {
        if (context instanceof BtCombinationPayActivity) {
            BtCombinationPayActivity btCombinationPayActivity = (BtCombinationPayActivity) context;
            return g0.a(btCombinationPayActivity) ? btCombinationPayActivity.x().b().b : "";
        }
        return "";
    }

    public static a b() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public void c(Context context, String str) {
        this.a.clear();
        this.a.put("orderid", str);
        String a = a(context);
        if (!TextUtils.isEmpty(a)) {
            this.a.put("appid", a);
        }
        b.g(context, 1000, "JDCashierNew_Home_ComPayClick", o.b(this.a));
    }
}
