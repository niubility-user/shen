package com.jingdong.common.cart;

import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class CartWebViewUtil {
    public static void rechooseNumberSuccess(IRouterParams iRouterParams) {
        try {
            JdSdk.getInstance().getApplication().getClassLoader().loadClass("com.jd.lib.cart.utils.CartWebViewUtil").getMethod("rechooseNumberSuccess", IRouterParams.class).invoke(null, iRouterParams);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }
}
