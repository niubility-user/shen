package com.jd.libs.hybrid.base.util;

import android.text.TextUtils;
import androidx.annotation.Keep;

@Keep
/* loaded from: classes16.dex */
public class CommonUtils {
    public static String base10StrToBase2Str(String str) {
        try {
            String binaryString = Integer.toBinaryString(Integer.parseInt(str));
            return (binaryString == null || binaryString.length() <= 10) ? binaryString : binaryString.substring(binaryString.length() - 10);
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean getBinarySwitch(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return (Integer.parseInt(str) & Double.valueOf(Math.pow(2.0d, (double) (i2 - 1))).intValue()) != 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getCleanUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf("?");
        int lastIndexOf2 = str.lastIndexOf("#");
        int max = (lastIndexOf == -1 || lastIndexOf2 == -1) ? Math.max(lastIndexOf, lastIndexOf2) : Math.min(lastIndexOf, lastIndexOf2);
        if (max != -1) {
            str = str.substring(0, max);
        }
        return str.endsWith("/") ? str.substring(0, str.length() - 1) : str;
    }

    public static String revertBinarySwitch(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return String.valueOf((((int) Math.pow(2.0d, (double) (i2 - 1))) ^ (-1)) & Integer.parseInt(str));
        } catch (Exception unused) {
            return str;
        }
    }

    public static String setBinarySwitch(String str, int i2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            int parseInt = Integer.parseInt(str);
            int pow = (int) Math.pow(2.0d, (double) (i2 - 1));
            if (z) {
                return String.valueOf(pow | parseInt);
            }
            return String.valueOf((pow ^ (-1)) & parseInt);
        } catch (Exception unused) {
            return str;
        }
    }
}
