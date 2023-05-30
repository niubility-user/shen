package com.jingdong.common.utils;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class StringUtil {
    public static final String EMPTY_STRING = "";
    private static final String TAG = "StringUtil";

    public static String cutAppointedLengthText(int i2, int i3) {
        return cutAppointedLengthText(i2, JdSdk.getInstance().getApplication().getApplicationContext().getString(i3));
    }

    public static String cutAppointedLengthText(int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() <= i2 || i2 <= 0) {
            return str;
        }
        return str.substring(0, i2) + "...";
    }

    public static synchronized String getString(int i2) {
        synchronized (StringUtil.class) {
            Context applicationContext = JdSdk.getInstance().getApplicationContext();
            if (applicationContext == null || applicationContext.getResources() == null) {
                return "";
            }
            return applicationContext.getString(i2);
        }
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isIp(String str) {
        String trim = str.trim();
        if (trim.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
            String[] split = trim.split("\\.");
            if (Integer.parseInt(split[0]) < 255 && Integer.parseInt(split[1]) < 255 && Integer.parseInt(split[2]) < 255 && Integer.parseInt(split[3]) < 255) {
                return true;
            }
        }
        return false;
    }

    public static String spilitSubString(String str, int i2) {
        if (str != null) {
            try {
                if (str.length() > i2) {
                    str = str.substring(0, i2);
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
        return str == null ? "" : str;
    }
}
