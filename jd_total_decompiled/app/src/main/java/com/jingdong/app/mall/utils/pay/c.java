package com.jingdong.app.mall.utils.pay;

import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class c {
    public static void a(String str, String str2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", "CashierRouterException");
            hashMap.put("errCode", "933");
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put("url", str);
            hashMap.put("errMsg", str2);
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplicationContext(), hashMap);
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }
}
