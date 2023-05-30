package com.jingdong.jdsdk.utils;

import com.jingdong.common.bing.utils.JDBingConst;
import java.util.regex.Pattern;

/* loaded from: classes14.dex */
public class PhoneStrUtils {
    public static String getDigitStartStr(String str) {
        int length = str.length();
        int i2 = 1;
        while (true) {
            if (i2 >= length) {
                i2 = 0;
                break;
            } else if (isDigit(str.charAt(i2))) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 == 0) {
            return null;
        }
        return str.substring(i2);
    }

    public static String handlePhoneStr(String str) {
        if (str.length() <= 0) {
            return null;
        }
        if (!isDigit(str.charAt(0))) {
            str = getDigitStartStr(str);
        }
        if (str.contains(":")) {
            if (str.indexOf(":") < 8) {
                str = str.substring(str.indexOf(":") + 1);
            }
        } else if (str.contains("\uff1a") && str.indexOf("\uff1a") < 8) {
            str = str.substring(str.indexOf("\uff1a") + 1);
        }
        if (str.length() > 11) {
            if (str.substring(0, 1).equals("1")) {
                str = str.substring(0, 11);
            } else if (str.substring(0, 1).equals("0")) {
                if (str.contains("-")) {
                    if (str.indexOf("-") == 3) {
                        return str.substring(0, 12);
                    }
                    if (str.indexOf("-") != 4 || str.length() < 13) {
                        return null;
                    }
                    return str.substring(0, 13);
                }
                String substring = str.substring(0, 3);
                if (!substring.equals(JDBingConst.MOBILE_BOUND_ERROR_CODE) && !substring.equals("020") && !substring.equals("021") && !substring.equals("022") && !substring.equals("023") && !substring.equals("024") && !substring.equals("025") && !substring.equals("026") && !substring.equals("027") && !substring.equals("028") && !substring.equals("029")) {
                    str = str.substring(0, 12);
                } else {
                    str = str.substring(0, 11);
                }
            } else {
                str = str.substring(0, 8);
            }
            if (!isNumeric(str)) {
                return null;
            }
        }
        return str;
    }

    public static boolean isDigit(char c2) {
        return Pattern.compile("[0-9]").matcher(String.valueOf(c2)).matches();
    }

    public static boolean isNumeric(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }
}
