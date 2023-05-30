package com.jingdong.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.auth.BASE64Encoder;
import com.jingdong.sdk.oklog.OKLog;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes6.dex */
public class JDGameUtil {
    private static final String ACTION_LOGIN_CANCEL = "jd.jgame.login.CANCEL";
    private static final String ACTION_LOGIN_OUT = "jd.jgame.login.OUT";
    private static final String ACTION_LOGIN_SUCCESS = "jd.jgame.login.SUCCESS";
    private static final String ACTION_MAIN = "jd.intent.action.MAIN";
    public static final int DES_AFTER_LOGIN_CANCEL = 3;
    public static final int DES_AFTER_LOGIN_SUCCESS = 2;
    public static final int DES_MAIN = 1;
    public static final String FUNCTION_ID = "jdgame";
    private static final String JD_GAME_COMPONENT_NAME = "com.jingdong.jgame";
    private static final String KEY_CATEGORY = "jd.intent.category.LAUNCHER";
    public static final String KEY_LOGIN_COOKIE = "loginCookie";
    public static final String KEY_LOGIN_NAME = "loginName";
    private static final String TAG = "JDGameUtil";

    public static boolean checkJDGameHasExist(Context context) {
        return CommonBase.getPackageInfo(context, JD_GAME_COMPONENT_NAME) != null;
    }

    public static boolean checkJDGameHasExistAndSupportMJump(Context context) {
        PackageInfo packageInfo = CommonBase.getPackageInfo(context, JD_GAME_COMPONENT_NAME);
        return packageInfo != null && packageInfo.versionCode > 4;
    }

    public static String getDESCookie(String str) {
        try {
            return getDesEncryptValue(str);
        } catch (Throwable unused) {
            return "";
        }
    }

    private static String getDesEncryptValue(String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec("6A647E2D".getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, secretKeySpec);
            return new BASE64Encoder().encode(cipher.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return "";
        }
    }

    public static void gotoJDGameUtil(IMyActivity iMyActivity, String str, String str2, int i2) {
        gotoJDGameUtil(iMyActivity, str, str2, i2, "", 0);
    }

    public static void sendLogoutBroadCast(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(ACTION_LOGIN_OUT);
        context.sendBroadcast(intent);
    }

    public static void gotoJDGameUtil(IMyActivity iMyActivity, String str, String str2, int i2, String str3, int i3) {
        if (OKLog.D) {
            OKLog.d(TAG, "context : " + iMyActivity);
            OKLog.d(TAG, "userName : " + str);
            OKLog.d(TAG, "cookie : " + str2);
            OKLog.d(TAG, "isNeedToHome : " + i2);
        }
        if (iMyActivity == null) {
            return;
        }
        if (checkJDGameHasExist(iMyActivity.getThisActivity())) {
            Intent intent = new Intent();
            intent.setFlags(32);
            if (i2 != 1) {
                if (i2 != 2) {
                    intent.setAction(ACTION_LOGIN_CANCEL);
                } else {
                    intent.setAction(ACTION_LOGIN_SUCCESS);
                }
                Bundle bundle = new Bundle();
                bundle.putString(KEY_LOGIN_NAME, str);
                bundle.putString(KEY_LOGIN_COOKIE, getDESCookie(str2));
                intent.putExtras(bundle);
                iMyActivity.getThisActivity().sendBroadcast(intent);
                if (OKLog.D) {
                    OKLog.d(TAG, "send broadcast intent : " + intent);
                    OKLog.d(TAG, "send broadcast bundle : " + bundle);
                    return;
                }
                return;
            }
            intent.setAction(ACTION_MAIN);
            intent.setFlags(intent.getFlags() | 268435456);
            intent.putExtra(KEY_LOGIN_COOKIE, getDESCookie(str2));
            intent.putExtra(KEY_LOGIN_NAME, str);
            intent.addCategory(KEY_CATEGORY);
            intent.setPackage(JD_GAME_COMPONENT_NAME);
            iMyActivity.getThisActivity().startActivity(intent);
        } else if (TextUtils.isEmpty(str3) || i3 < 1) {
        } else {
            ApplicationUpgradeHelper.tryDownloadAndInstall(iMyActivity, str3, i3, com.jingdong.jdsdk.res.StringUtil.jd_game_app_has_no_install, "jdgame");
        }
    }
}
