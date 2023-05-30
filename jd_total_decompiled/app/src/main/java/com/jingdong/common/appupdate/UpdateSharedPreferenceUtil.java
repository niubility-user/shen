package com.jingdong.common.appupdate;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes5.dex */
public class UpdateSharedPreferenceUtil {
    public static final String SP_NAME = "app_update_sp";
    private static SharedPreferences sp;

    public static void doMtaData(String str, boolean z, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i2);
        stringBuffer.append(CartConstant.KEY_YB_INFO_LINK);
        stringBuffer.append(z);
        JDMtaUtils.onClick(JdSdk.getInstance().getApplicationContext(), str, "auto_upgrade", stringBuffer.toString());
    }

    public static boolean getBoolean(String str, boolean z) {
        if (TextUtils.equals(str, Constants.UPGRADE_WIFI_AUTO_KEY)) {
            return getBoolean(str, z, 0);
        }
        return getInstance().getBoolean(str, z);
    }

    public static synchronized SharedPreferences getInstance() {
        SharedPreferences sharedPreferences;
        synchronized (UpdateSharedPreferenceUtil.class) {
            if (sp == null) {
                sp = JdSdk.getInstance().getApplication().getSharedPreferences(SP_NAME, 0);
            }
            sharedPreferences = sp;
        }
        return sharedPreferences;
    }

    public static void putBoolean(String str, boolean z) {
        if (TextUtils.equals(str, Constants.UPGRADE_WIFI_AUTO_KEY)) {
            putBoolean(str, z, 0);
        } else {
            getInstance().edit().putBoolean(str, z).apply();
        }
    }

    public static boolean getBoolean(String str, boolean z, int i2) {
        boolean z2;
        if (CommonBase.getJdSharedPreferences().contains(str)) {
            z2 = CommonBase.getBooleanFromPreference(str, Boolean.TRUE).booleanValue();
            putBoolean(str, z2, i2);
            CommonBase.getJdSharedPreferences().edit().remove(str).apply();
        } else {
            z2 = getInstance().getBoolean(str, z);
        }
        if (TextUtils.equals(str, Constants.UPGRADE_WIFI_AUTO_KEY)) {
            doMtaData("App_AutoUpgradeSwitchStatus_get", z2, i2);
        }
        return z2;
    }

    public static void putBoolean(String str, boolean z, int i2) {
        if (TextUtils.equals(str, Constants.UPGRADE_WIFI_AUTO_KEY)) {
            doMtaData("App_AutoUpgradeSwitchStatus_save", z, i2);
        }
        if (CommonBase.getJdSharedPreferences().contains(str)) {
            CommonBase.getJdSharedPreferences().edit().remove(str).apply();
        }
        getInstance().edit().putBoolean(str, z).apply();
    }
}
