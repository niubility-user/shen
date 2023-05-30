package com.jingdong.common.network;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import java.util.List;

/* loaded from: classes5.dex */
public class MobileConfigHelper {
    public static final String MC_KEY_ADVANCED_MODE_ENABLE = "cdnAdvancedMode";
    public static final String MC_KEY_CDN_DOMAIN_LIST = "cdnDomainList";
    public static final String MC_NETWORK_DOMAIN_IMG = "image";
    public static final String MC_NETWORK_KEY = "JDHttpToolKit";

    public static boolean isCDNAdvancedModeEnable() {
        return TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "image", MC_KEY_ADVANCED_MODE_ENABLE, "0"));
    }

    public static boolean isSupportCDNAdvancedMode(String str) {
        String config = JDMobileConfig.getInstance().getConfig("JDHttpToolKit", "image", MC_KEY_CDN_DOMAIN_LIST, "");
        if (TextUtils.isEmpty(config)) {
            return false;
        }
        List parseArray = JDJSON.parseArray(config, String.class);
        if (str == null || str.isEmpty()) {
            return false;
        }
        return parseArray.contains(str);
    }
}
