package com.jd.lib.cashier.sdk.d.g.e;

import android.content.Context;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.d.e.b;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class a {
    public static void a(Context context, String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("appid", str);
        hashMap.put("payid", str2);
        hashMap.put(IMantoBaseModule.ERROR_CODE, str3);
        b.g(context, 1000, "JDCashierNew_Home_JDPayFail", o.b(hashMap));
    }
}
