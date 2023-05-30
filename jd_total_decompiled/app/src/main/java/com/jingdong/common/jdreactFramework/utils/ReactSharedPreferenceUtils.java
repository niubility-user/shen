package com.jingdong.common.jdreactFramework.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginDownloadInfo;
import com.jingdong.common.jdreactFramework.download.PluginLocalDownloadInfo;
import com.jingdong.common.jdreactFramework.download.ReactUpdateModelHelper;
import java.io.File;
import java.net.URLEncoder;
import java.util.Date;

/* loaded from: classes5.dex */
public class ReactSharedPreferenceUtils {
    private static long LAST_CHEECK_TIME = 0;
    private static final String TAG = "ReactSharedPreferenceUtils";

    public static String getAppVersion(String str) {
        return getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).getString(str, "");
    }

    public static String getBakPath(boolean z, String str) {
        String string = getSharedPreferences(str).getString(z ? JDReactConstant.BAK_FULL_PATH_KEY : JDReactConstant.BAK_SPLIT_PATH_KEY, "");
        if (TextUtils.isEmpty(string)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str2 = File.separator;
            sb.append(str2);
            sb.append(z ? JDReactConstant.BUFF_DIR_FULL : JDReactConstant.BUFF_DIR_SPLIT);
            sb.append(str2);
            sb.append(JDReactConstant.BUFF_DIR_ONE);
            return sb.toString();
        }
        return string;
    }

    public static String getBlockBundleName(String str) {
        return getSharedPreferences(str).getString("bundle_name", "");
    }

    public static String getBlockPath(String str) {
        return getSharedPreferences(str).getString("path", "");
    }

    public static String getBlockVersion(String str) {
        return getSharedPreferences(str).getString("version", "");
    }

    public static boolean getCheckComplete() {
        return getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).getBoolean(JDReactConstant.CHECK_COMPLETE_KEY, false);
    }

    public static String getCurPath(boolean z, String str) {
        String string = getSharedPreferences(str).getString(z ? JDReactConstant.CUR_FULL_PATH_KEY : JDReactConstant.CUR_SPLIT_PATH_KEY, "");
        if (TextUtils.isEmpty(string)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str2 = File.separator;
            sb.append(str2);
            sb.append(z ? JDReactConstant.BUFF_DIR_FULL : JDReactConstant.BUFF_DIR_SPLIT);
            sb.append(str2);
            sb.append(JDReactConstant.BUFF_DIR_TWO);
            return sb.toString();
        }
        return string;
    }

    public static String getDebugInfo(String str) {
        return getSharedPreferences(JDReactConstant.SHARE_DEBUG_PREFRENCE_NAME).getString(str, "");
    }

    public static PluginLocalDownloadInfo getDownLoadingStatus(String str) {
        if (TextUtils.isEmpty(str)) {
            return new PluginLocalDownloadInfo("none");
        }
        SharedPreferences sharedPreferences = getSharedPreferences(str);
        return new PluginLocalDownloadInfo(sharedPreferences.getString(JDReactConstant.D_FLAG, "none"), sharedPreferences.getBoolean(JDReactConstant.TPYE_FLAG, false), sharedPreferences.getString("commonVersion", ""));
    }

    public static String getJDFlutterStatus(String str) {
        return getSharedPreferences(str).getString("exchange_staus", "none");
    }

    public static boolean getLastCheckCompleteFlag() {
        return getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).getBoolean(JDReactConstant.LAST_CHECK_COMPLETE_KEY, false);
    }

    public static long getLastCheckTime() {
        if (LAST_CHEECK_TIME == 0) {
            LAST_CHEECK_TIME = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).getLong(JDReactConstant.LAST_CHECK_TIME_KEY, 0L);
        }
        return LAST_CHEECK_TIME;
    }

    public static String getPreloadPackage(String str) {
        return getSharedPreferences(str).getString(JDReactConstant.PRELOAD_PACKAGE, "");
    }

    private static SharedPreferences getSharedPreferences(String str) {
        return JDReactHelper.newInstance().getApplicationContext().getSharedPreferences(URLEncoder.encode(str), 0);
    }

    public static String getUnzipStatus(String str) {
        return getSharedPreferences(str).getString(JDReactConstant.U_FLAG, "none");
    }

    public static String getUpdateInfo() {
        return getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).getString(JDReactConstant.LAST_CHECK_RESPONSE_STRING, "");
    }

    public static int getUpdateLevel(String str) {
        try {
            for (PluginDownloadInfo pluginDownloadInfo : ReactUpdateModelHelper.setPluginDownloadModel(getUpdateInfo())) {
                if (pluginDownloadInfo.getPluginResult().pluginUpdateName.equals(str)) {
                    return pluginDownloadInfo.getPluginResult().upgradeLevel;
                }
            }
            return -1;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static void putCheckComplete(boolean z) {
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
        edit.putBoolean(JDReactConstant.CHECK_COMPLETE_KEY, z);
        edit.commit();
    }

    public static void putDownLoadingStatus(String str, boolean z, String str2, String str3) {
        SharedPreferences.Editor edit = getSharedPreferences(str).edit();
        edit.putString("commonVersion", str2);
        edit.putString(JDReactConstant.D_FLAG, str3);
        edit.putBoolean(JDReactConstant.TPYE_FLAG, z);
        edit.commit();
    }

    public static void putJDFlutterStatus(String str, String str2) {
        SharedPreferences.Editor edit = getSharedPreferences(str).edit();
        edit.putString("exchange_staus", str2);
        edit.commit();
    }

    public static void putLastCheckCompleteFlag(boolean z) {
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
        edit.putBoolean(JDReactConstant.LAST_CHECK_COMPLETE_KEY, z);
        edit.commit();
    }

    public static void putLastCheckTime() {
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
        long time = new Date().getTime();
        LAST_CHEECK_TIME = time;
        edit.putLong(JDReactConstant.LAST_CHECK_TIME_KEY, time);
        edit.commit();
    }

    public static void putUnzipStatus(String str, String str2) {
        SharedPreferences.Editor edit = getSharedPreferences(str).edit();
        edit.putString(JDReactConstant.U_FLAG, str2);
        edit.commit();
    }

    public static void reverseCurBakSP(boolean z, String str) {
        SharedPreferences.Editor edit = getSharedPreferences(str).edit();
        String curPath = getCurPath(z, str);
        edit.putString(z ? JDReactConstant.CUR_FULL_PATH_KEY : JDReactConstant.CUR_SPLIT_PATH_KEY, getBakPath(z, str));
        edit.putString(z ? JDReactConstant.BAK_FULL_PATH_KEY : JDReactConstant.BAK_SPLIT_PATH_KEY, curPath);
        edit.commit();
    }

    public static void saveAppVersion(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static void savePreloadPackage(String str, String str2) {
        SharedPreferences.Editor edit = getSharedPreferences(str).edit();
        edit.putString(JDReactConstant.PRELOAD_PACKAGE, str2);
        edit.commit();
    }

    public static void saveUpdateInfo(String str) {
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_PREFRENCE_NAME).edit();
        edit.putString(JDReactConstant.LAST_CHECK_RESPONSE_STRING, str);
        edit.commit();
    }

    public static void setBlockInfo(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor edit = getSharedPreferences(str).edit();
        edit.putString("path", str2);
        edit.putString("version", str3);
        edit.putString("bundle_name", str4);
        edit.commit();
    }

    public static void setDebugInfo(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SHARE_DEBUG_PREFRENCE_NAME).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static void setDefaultDownLoadingStatus(String str, boolean z, String str2) {
        putDownLoadingStatus(str, z, str2, JDReactConstant.PREPARE);
    }

    public static void setUpdateLevel(String str, int i2) {
    }

    public static void putDownLoadingStatus(String str, String str2) {
        putDownLoadingStatus(str, true, "1.0", str2);
    }
}
