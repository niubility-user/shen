package com.jingdong.common.jdreactFramework.utils;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.track.TrackUtil;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class ReactModuleAvailabilityUtils {
    private static final String TAG = "ReactModuleAvailabilityUtils";

    public static String appendParamsToUrl(String str, String str2) {
        Uri.Builder buildUpon;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (!TextUtils.isEmpty(str2)) {
            try {
                JDJSONObject parseObject = JDJSON.parseObject(str2);
                Set<Map.Entry<String, Object>> entrySet = parseObject.entrySet();
                if (entrySet.isEmpty()) {
                    return str;
                }
                buildUpon = Uri.parse(str).buildUpon();
                String optString = parseObject.optString("jdreactAppendPath");
                if (!TextUtils.isEmpty(optString)) {
                    buildUpon.appendEncodedPath(optString);
                }
                for (Map.Entry<String, Object> entry : entrySet) {
                    if (entry != null) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (!TextUtils.isEmpty(key) && value != null && !"jdreactAppendPath".equals(key)) {
                            buildUpon.appendQueryParameter(key, "" + value);
                        }
                    }
                }
            } catch (Exception unused) {
                return str;
            }
        }
        return buildUpon.toString();
    }

    public static void clearSharedData() {
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
        edit.clear();
        edit.apply();
    }

    public static boolean getLastAvailabilityResult() {
        return getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).getBoolean(JDReactConstant.LAST_MODULE_AVAILABILITY_RESULT, false);
    }

    public static long getLastCheckAvailabilityTime() {
        return getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).getLong(JDReactConstant.LAST_MODULE_AVAILABILITY_TIME, 0L);
    }

    public static boolean getModuleAvail(String str, boolean z) {
        if (ReactSharedPreferenceUtils.getCheckComplete()) {
            return getModuleAvailability(str, z);
        }
        return false;
    }

    public static boolean getModuleAvailability(String str) {
        return getModuleAvailability(str, true);
    }

    public static String getModuleBackupUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).getString(str + "_backupUrl", "");
    }

    public static String getModuleBackupZip(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        return getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).getString(str + "_backupZip", str2);
    }

    public static String getSharedData(String str) {
        return !TextUtils.isEmpty(str) ? getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).getString(str, "") : "";
    }

    public static SharedPreferences getSharedPreferences(String str) {
        return JDReactHelper.newInstance().getApplicationContext().getSharedPreferences(URLEncoder.encode(str), 0);
    }

    public static boolean isShowDegradeFirst(String str) {
        return getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).getBoolean(str + "_show_degrade_first", false);
    }

    public static void removeSharedData(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
        edit.remove(str);
        edit.apply();
    }

    public static void saveLastAvailabilityResult(boolean z) {
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
        edit.putBoolean(JDReactConstant.LAST_MODULE_AVAILABILITY_RESULT, z);
        edit.apply();
    }

    public static void saveLastCheckAvailabilityTime() {
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
        edit.putLong(JDReactConstant.LAST_MODULE_AVAILABILITY_TIME, new Date().getTime());
        edit.apply();
    }

    public static void saveModuleAvailability(String str, boolean z) {
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
        edit.putBoolean(str + "_unique_flag", z);
        edit.apply();
    }

    public static void saveModuleBackupUrl(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
            edit.putString(str + "_backupUrl", str2);
            edit.apply();
        } else if (TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
        } else {
            SharedPreferences.Editor edit2 = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
            edit2.remove(str + "_backupUrl");
            edit2.apply();
        }
    }

    public static void saveModuleBackupZip(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
            edit.putString(str + "_backupZip", str2);
            edit.apply();
        } else if (TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
        } else {
            SharedPreferences.Editor edit2 = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
            edit2.remove(str + "_backupZip");
            edit2.apply();
        }
    }

    public static void saveSharedData(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static void saveShowDegradeFirst(String str, boolean z) {
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
        edit.putBoolean(str + "_show_degrade_first", z);
        edit.apply();
    }

    public static boolean getModuleAvailability(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!CpuUtils.checkIfSupportReactNativeLib()) {
            TrackUtil.trackLoadFail(13, str);
            return false;
        } else if (Build.VERSION.SDK_INT < 16) {
            TrackUtil.trackLoadFail(15, str);
            return false;
        } else if (CpuUtils.isX86()) {
            TrackUtil.trackLoadFail(14, str);
            return false;
        } else {
            return getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).getBoolean(str + "_unique_flag", z);
        }
    }

    public static boolean getModuleAvail(String str) {
        if (ReactSharedPreferenceUtils.getCheckComplete()) {
            return getModuleAvailability(str, true);
        }
        return false;
    }

    public static String getModuleBackupUrl(String str, String str2) {
        String moduleBackupUrl = getModuleBackupUrl(str);
        return TextUtils.isEmpty(moduleBackupUrl) ? str2 : moduleBackupUrl;
    }

    public static Map<String, ?> getSharedData() {
        return getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).getAll();
    }
}
