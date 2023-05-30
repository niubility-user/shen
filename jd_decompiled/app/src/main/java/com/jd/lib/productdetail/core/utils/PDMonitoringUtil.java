package com.jd.lib.productdetail.core.utils;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;

/* loaded from: classes15.dex */
public class PDMonitoringUtil {
    public static void report(String str, String str2, String str3) {
        if (TextUtils.equals(DYConstants.DY_TRUE, JDMobileConfig.getInstance().getConfig("JDProductdetail", "isTechnicalMonitoring", "enable", DYConstants.DY_TRUE))) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("type", (Object) str);
            jDJSONObject.put("params", (Object) str2);
            jDJSONObject.put("tag", (Object) str3);
            PDUtils.exposureJsonParam("Productdetail_TechnicalMonitoring", jDJSONObject.toJSONString(), "test", "test", "test", "test");
        }
    }

    public static void report(String str, String str2) {
        report(str, str2, "-100");
    }

    public static void report(String str) {
        report(str, "-100", "-100");
    }
}
