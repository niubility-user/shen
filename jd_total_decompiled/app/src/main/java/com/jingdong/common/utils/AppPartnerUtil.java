package com.jingdong.common.utils;

import android.text.TextUtils;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.config.Configuration;

/* loaded from: classes6.dex */
public class AppPartnerUtil {
    private static final String SP_KEY_ORIGINAL_PARTNER = "original_partner";

    public static String getOriginalPartner() {
        return CommonBase.getStringFromPreference(SP_KEY_ORIGINAL_PARTNER, "");
    }

    public static String getOriginalPartnerFromJs(IRouterParams iRouterParams) {
        return getOriginalPartner();
    }

    public static void saveOriginalPartner() {
        if (TextUtils.isEmpty(getOriginalPartner())) {
            CommonBase.putStringToPreference(SP_KEY_ORIGINAL_PARTNER, Configuration.getProperty(Configuration.PARTNER));
        }
    }
}
