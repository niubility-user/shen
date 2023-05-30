package com.jingdong.common.jdpay;

import android.app.Application;
import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.JDReactConstant;

/* loaded from: classes5.dex */
public class JDPayCodeAuraHelper {
    public static void prepare(Application application, String str) {
        if (application == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Class<?> loadClass = application.getClassLoader().loadClass("com.jdpay.paymentcode.PaymentCode");
            if (loadClass != null) {
                loadClass.getMethod(JDReactConstant.PREPARE, Application.class, String.class).invoke(null, application, str);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
