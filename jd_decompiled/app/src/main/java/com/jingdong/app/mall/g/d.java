package com.jingdong.app.mall.g;

import android.os.Build;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes19.dex */
class d {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a() {
        if (b()) {
            return c();
        }
        return false;
    }

    private static boolean b() {
        boolean equals = "1".equals(JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "fixAndroid10PMSCrash", "hook_enable", "0"));
        OKLog.d("JDPMSFixUtil", "Hook PMS is enable: " + equals);
        return equals;
    }

    private static boolean c() {
        String config = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "fixAndroid10PMSCrash", "sdkVersions", "");
        if (TextUtils.isEmpty(config)) {
            return false;
        }
        OKLog.d("JDPMSFixUtil", "Hook PMS sdk configure: " + config);
        JDJSONObject parseObject = JDJSON.parseObject(config);
        if (parseObject == null) {
            return false;
        }
        String valueOf = String.valueOf(Build.VERSION.SDK_INT);
        OKLog.d("JDPMSFixUtil", "Hook PMS sdk version: " + valueOf);
        String[] d = d(parseObject.getString(valueOf));
        if (d != null && d.length > 0) {
            String deviceBrand = BaseInfo.getDeviceBrand();
            OKLog.d("JDPMSFixUtil", "Hook PMS brand is: " + deviceBrand);
            for (String str : d) {
                if ("ALL_BRAND".equalsIgnoreCase(str) || deviceBrand.equalsIgnoreCase(str.trim())) {
                    return true;
                }
            }
            return false;
        }
        OKLog.d("JDPMSFixUtil", "Hook PMS not support SDK. ");
        return false;
    }

    private static String[] d(String str) {
        OKLog.d("JDPMSFixUtil", "Hook PMS devices is: " + str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split(";");
    }
}
