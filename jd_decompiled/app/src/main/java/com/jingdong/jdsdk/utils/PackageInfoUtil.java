package com.jingdong.jdsdk.utils;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes.dex */
public class PackageInfoUtil {
    private static String harmonyVersionName;
    private static int versionCode;
    private static String versionName;

    public static String getHarmonyVersionName() {
        JdSdk.getInstance();
        if (!JdSdk.isHarmonyOS()) {
            harmonyVersionName = "";
            return "";
        }
        if (TextUtils.isEmpty(harmonyVersionName)) {
            try {
                String oSName = BaseInfo.getOSName();
                harmonyVersionName = oSName;
                if (TextUtils.isEmpty(oSName)) {
                    harmonyVersionName = "";
                }
                int indexOf = harmonyVersionName.indexOf(LangUtils.SINGLE_SPACE);
                if (indexOf != -1) {
                    harmonyVersionName = harmonyVersionName.substring(indexOf + 1);
                }
            } catch (Exception unused) {
                harmonyVersionName = "";
            }
        }
        return harmonyVersionName;
    }

    private static PackageInfo getPackageInfo() {
        try {
            Application application = JdSdk.getInstance().getApplication();
            return application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public static int getVersionCode() {
        if (versionCode == 0) {
            PackageInfo packageInfo = getPackageInfo();
            versionCode = packageInfo == null ? 0 : packageInfo.versionCode;
        }
        return versionCode;
    }

    public static String getVersionName() {
        if (TextUtils.isEmpty(versionName)) {
            PackageInfo packageInfo = getPackageInfo();
            versionName = packageInfo == null ? "" : packageInfo.versionName;
        }
        return versionName;
    }
}
