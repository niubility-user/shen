package com.jingdong.app.mall.g;

import android.os.Build;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes19.dex */
class b {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a() {
        if (Build.VERSION.SDK_INT != 28) {
            OKLog.d("JDAMSFixUtil_XPJJJ", "hook return, not match version.");
            return false;
        } else if (b()) {
            return c();
        } else {
            return false;
        }
    }

    private static boolean b() {
        boolean equals = "1".equals(JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "fixAndroid9AMSCrash", "hook_enable", "0"));
        OKLog.d("JDAMSFixUtil_XPJJJ", "is enable : " + equals);
        return equals;
    }

    private static boolean c() {
        String[] d = d();
        OKLog.d("JDAMSFixUtil_XPJJJ", " brand is :" + BaseInfo.getDeviceBrand());
        if (d != null && d.length > 0) {
            String deviceBrand = BaseInfo.getDeviceBrand();
            for (String str : d) {
                if (deviceBrand.equalsIgnoreCase(str.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String[] d() {
        String config = JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "fixAndroid9AMSCrash", "hook_devices", "");
        OKLog.d("JDAMSFixUtil_XPJJJ", " hook ams devices is : " + config);
        if (TextUtils.isEmpty(config)) {
            return null;
        }
        return config.split(DYConstants.DY_REGEX_COMMA);
    }
}
