package com.jingdong.common.utils.inter;

import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.oklog.OKLog;

@Deprecated
/* loaded from: classes6.dex */
public class JDInternationalUtil {
    private static final String TAG = JDInterConstant.TAG_INTER + JDInternationalUtil.class.getSimpleName();

    @Deprecated
    public static String getLastInterSiteUrl() {
        String string = SharedPreferencesUtil.getString(JDInterConstant.KEY_LAST_SITE_URL, "");
        if (OKLog.D) {
            OKLog.d(TAG, "getLastInterSiteUrl   " + string);
        }
        return string;
    }

    @Deprecated
    public static void gotoInterSite(BaseActivity baseActivity, String str, boolean z) {
        if (OKLog.D) {
            OKLog.d(TAG, "gotoInterSite siteUrl: " + str + " fromMain: " + z);
        }
        if (z) {
            SharedPreferencesUtil.putInt(JDInterConstant.KEY_LAST_CHOICE, 2);
        }
        SharedPreferencesUtil.putBoolean(JDInterConstant.KEY_IS_OFFSHORE_REMINDED, true);
        setLastInterSiteUrl(str);
        jumpToInterSite(baseActivity, str);
    }

    @Deprecated
    public static boolean isInInterSite() {
        boolean z = SharedPreferencesUtil.getBoolean(JDInterConstant.KEY_IS_IN_INTER_SITE, false);
        if (OKLog.D) {
            OKLog.d(TAG, "isInInterSite==" + z);
        }
        return z;
    }

    @Deprecated
    public static boolean isOffshore() {
        boolean z = SharedPreferencesUtil.getBoolean(JDInterConstant.KEY_IS_OFFSHORE, false);
        if (OKLog.D) {
            OKLog.d(TAG, "isOffshore==" + z);
        }
        return z;
    }

    private static void jumpToInterSite(BaseActivity baseActivity, String str) {
        if (OKLog.D) {
            OKLog.d(TAG, "jumpToInterSite is NOT supported anymore.");
        }
    }

    @Deprecated
    public static void setInInterSite(boolean z) {
        if (OKLog.D) {
            OKLog.d(TAG, "setInInterSite:  " + z);
        }
        SharedPreferencesUtil.putBoolean(JDInterConstant.KEY_IS_IN_INTER_SITE, z);
    }

    @Deprecated
    public static void setLastInterSiteUrl(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, "setLastSiteUrl:  " + str);
        }
        SharedPreferencesUtil.putString(JDInterConstant.KEY_LAST_SITE_URL, str);
    }
}
