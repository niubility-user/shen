package com.jingdong.common.login;

import android.text.TextUtils;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.jdsdk.utils.Md5Encrypt;

/* loaded from: classes5.dex */
public class LoginCrytoUtil {
    private static String getAndroidid() {
        try {
            String readAndroidId = StatisticsReportUtil.readAndroidId();
            return TextUtils.isEmpty(readAndroidId) ? "" : readAndroidId;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getSeed() {
        return getAndroidid();
    }

    public static String getShName() {
        return Md5Encrypt.md5(getAndroidid() + "_applogin_encrypt");
    }
}
