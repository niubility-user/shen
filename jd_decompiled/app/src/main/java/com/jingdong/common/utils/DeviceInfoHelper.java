package com.jingdong.common.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.UUID;

/* loaded from: classes.dex */
public final class DeviceInfoHelper {
    public static final String INSTALLTION_ID = "installtion_id";
    public static final String STR_UNKNOWN = "unknown";
    public static final String TAG = "DeviceInfoHelper";
    private static String sInstallationId;

    @Deprecated
    public static String genarateDeviceUUID(Context context) {
        return "";
    }

    @Deprecated
    public static String generateDeviceId(Context context) {
        return "";
    }

    public static String getAid() {
        return BaseInfo.getAndroidId();
    }

    public static String getInstallationId(Context context) {
        if (TextUtils.isEmpty(sInstallationId)) {
            String string = SharedPreferencesUtil.getString(INSTALLTION_ID, "unknown");
            if (TextUtils.equals("unknown", string)) {
                try {
                    string = UUID.randomUUID().toString().replaceAll("-", "");
                    if (!TextUtils.isEmpty(string)) {
                        SharedPreferencesUtil.putString(INSTALLTION_ID, string);
                        sInstallationId = string;
                        return string;
                    }
                } catch (Exception unused) {
                }
            }
            sInstallationId = string;
            return string;
        }
        return sInstallationId;
    }

    public static String getNetworkTypeAccordingPrivacy() {
        return !JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()) ? "UNKNOWN" : NetUtils.getNetworkType();
    }

    public static String getOAID() {
        return BaseInfo.getOAID();
    }

    public static String getOpenUDID() {
        if (Build.VERSION.SDK_INT > 28) {
            String aid = getAid();
            if (TextUtils.isEmpty(aid)) {
                aid = getOAID();
            }
            return TextUtils.isEmpty(aid) ? readDeviceUUID() : aid;
        }
        return null;
    }

    public static String getValidDeviceUUIDByInstant() {
        String string = CommonBase.getJdSharedPreferences().getString("uuid", null);
        if (isValidDeviceUUID(string)) {
            return string;
        }
        return null;
    }

    public static boolean isValidDeviceUUID(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("-");
        return (split.length <= 1 || TextUtils.isEmpty(split[0]) || TextUtils.isEmpty(split[1])) ? false : true;
    }

    public static String readDeviceUUID() {
        String validDeviceUUIDByInstant = getValidDeviceUUIDByInstant();
        if (TextUtils.isEmpty(validDeviceUUIDByInstant)) {
            String aid = getAid();
            return (TextUtils.isEmpty(aid) || aid.matches("0+")) ? StatisticsReportUtil.readInstallationId() : aid;
        }
        return validDeviceUUIDByInstant;
    }
}
