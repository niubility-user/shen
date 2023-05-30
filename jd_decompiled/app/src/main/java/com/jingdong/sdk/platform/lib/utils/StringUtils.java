package com.jingdong.sdk.platform.lib.utils;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.ProxyConfig;
import java.util.regex.Pattern;

/* loaded from: classes10.dex */
public class StringUtils {
    private static final String TAG = "JdStringUtils";

    public static boolean checkAddrWithSpace(String str) {
        return startCheck("[\\w\u4e00-\u9fa5\\-\\x20]+", str);
    }

    public static boolean checkEmailWithSuffix(String str) {
        return startCheck("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", str);
    }

    public static boolean checkIllegalChars(String str) {
        return startCheck("[*/+'\"\\$^.;<>={}@%~&]+", str);
    }

    public static boolean checkPassword(String str, int i2, int i3) {
        return startCheck("[a-zA-Z_0-9\\-]{" + i2 + DYConstants.DY_REGEX_COMMA + i3 + "}", str);
    }

    public static boolean checkPhone(String str) {
        return startCheck("[()\\-0-9]+", str);
    }

    public static boolean checkPhoneNumber(String str) {
        return startCheck("[*()\\-0-9]+", str);
    }

    public static boolean checkReceiveName(String str) {
        return startCheck("^([a-zA-Z\\u4E00-\\u9FA5]+(\u00b7?[a-zA-Z\\u4E00-\\u9FA5]+)+)$", str);
    }

    public static boolean checkUsername(String str, int i2, int i3) {
        return startCheck("[\\w\u4e00-\u9fa5\\-a-zA-Z0-9_]{" + i2 + DYConstants.DY_REGEX_COMMA + i3 + "}", str);
    }

    public static String getPhoneNumber(String str) {
        if (str != null) {
            try {
                if (str.trim().length() == 0) {
                    return "";
                }
                String replace = str.replace(LangUtils.SINGLE_SPACE, "").replace("-", "").replace("\u2014", "").replace("+86", "");
                StringBuffer stringBuffer = new StringBuffer();
                for (int i2 = 0; i2 < replace.length(); i2++) {
                    char charAt = replace.charAt(i2);
                    if ((charAt >= '0' && charAt <= '9') || charAt == '*') {
                        stringBuffer.append(charAt);
                    }
                }
                return stringBuffer.toString();
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
                return "";
            }
        }
        return "";
    }

    public static boolean isEncrypt(String str) {
        return (TextUtils.isEmpty(str) || str.contains(ProxyConfig.MATCH_ALL_SCHEMES)) ? false : true;
    }

    static boolean startCheck(String str, String str2) {
        return Pattern.compile(str).matcher(str2).matches();
    }

    public static double string2Double(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception unused) {
            return 0.0d;
        }
    }

    public static long string2Long(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static boolean checkUsername(String str, int i2) {
        return startCheck("[\\w\u4e00-\u9fa5\\-a-zA-Z0-9_]{" + i2 + ",}", str);
    }

    public static boolean checkUsername(String str) {
        return startCheck("[\\w\u4e00-\u9fa5\\-a-zA-Z0-9_]+", str);
    }
}
