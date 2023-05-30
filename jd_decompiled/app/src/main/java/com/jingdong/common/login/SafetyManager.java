package com.jingdong.common.login;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.stat.security.jma.JMA;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleManager;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.utils.JDSharedPreferences;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.net.URLEncoder;
import jd.wjlogin_sdk.common.WJLoginHelper;

/* loaded from: classes.dex */
public class SafetyManager {
    private static final String TAG = "SafetyManager";
    private static String fileName;
    private static SharedPreferences mSharedPreferences;

    private static String getAndroidid() {
        try {
            return BaseInfo.getAndroidId();
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean getBoolean(String str, boolean z) {
        return getSharedPreferences().getBoolean(str, z);
    }

    public static String getCookies() {
        String str;
        String str2;
        String str3 = "";
        try {
            WJLoginHelper wJLoginHelper = UserUtil.getWJLoginHelper();
            if (wJLoginHelper != null) {
                str2 = wJLoginHelper.getPin();
                str = wJLoginHelper.getA2();
            } else {
                str = "";
                str2 = str;
            }
            String unionFingerPrint = DeviceFingerUtil.getUnionFingerPrint();
            String softFingerprint = JMA.getSoftFingerprint(JdSdk.getInstance().getApplication());
            String riskHandleToken = JDRiskHandleManager.getInstance().getRiskHandleToken();
            if (!TextUtils.isEmpty(str2)) {
                str3 = "pin=" + URLEncoder.encode(str2, "UTF-8") + ";";
            }
            if (!TextUtils.isEmpty(str)) {
                str3 = str3 + "wskey=" + str + ";";
            }
            if (!TextUtils.isEmpty(softFingerprint)) {
                str3 = str3 + "whwswswws=" + softFingerprint + ";";
            }
            if (!TextUtils.isEmpty(unionFingerPrint)) {
                str3 = str3 + "unionwsws=" + unionFingerPrint + ";";
            }
            if (!TextUtils.isEmpty(riskHandleToken)) {
                str3 = str3 + "x-rp-evtoken=" + riskHandleToken + ";";
            }
            if (OKLog.I) {
                OKLog.i(TAG, "getCookiesNew==== " + str3);
            }
        } catch (Throwable unused) {
        }
        return str3;
    }

    private static String getShName() {
        if (TextUtils.isEmpty(fileName)) {
            fileName = Md5Encrypt.md5(getAndroidid() + "_applogin_encrypt");
        }
        return fileName;
    }

    private static SharedPreferences getSharedPreferences() {
        if (mSharedPreferences == null) {
            mSharedPreferences = new JDSharedPreferences(JdSdk.getInstance().getApplicationContext(), getShName(), 0);
        }
        return mSharedPreferences;
    }

    public static String getString(String str, String str2) {
        return getSharedPreferences().getString(str, str2);
    }

    public static void initEncryptKey() {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        String string = jdSharedPreferences.getString(Constants.ENCRYPT_KEY, null);
        if (TextUtils.isEmpty(string)) {
            string = StatisticsReportUtil.genarateDeviceUUID(JdSdk.getInstance().getApplicationContext());
            jdSharedPreferences.edit().putString(Constants.ENCRYPT_KEY, string).commit();
        }
        CommonBase.miaoShaKey = string;
    }

    public static void putBoolean(String str, boolean z) {
        getSharedPreferences().edit().putBoolean(str, z).commit();
    }

    public static void putString(String str, String str2) {
        getSharedPreferences().edit().putString(str, str2).commit();
    }

    public static void saveCookies(String str) {
    }
}
