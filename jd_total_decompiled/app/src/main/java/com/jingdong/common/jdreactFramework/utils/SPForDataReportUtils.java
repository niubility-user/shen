package com.jingdong.common.jdreactFramework.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import java.net.URLEncoder;
import java.util.UUID;

/* loaded from: classes5.dex */
public class SPForDataReportUtils {
    public static boolean clearValueForSession(String str) {
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SP_REPORT_NAME).edit();
        edit.putString(str, "");
        return edit.commit();
    }

    public static String generateSessionId() {
        return UUID.randomUUID().toString();
    }

    private static SharedPreferences getSharedPreferences(String str) {
        return JDReactHelper.newInstance().getApplicationContext().getSharedPreferences(URLEncoder.encode(str), 0);
    }

    public static String getValueForSession(String str) {
        return getSharedPreferences(JDReactConstant.SP_REPORT_NAME).getString(str, "");
    }

    public static String saveSession(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SharedPreferences.Editor edit = getSharedPreferences(JDReactConstant.SP_REPORT_NAME).edit();
        edit.putString(str, str2);
        edit.commit();
        return str2;
    }
}
