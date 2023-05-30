package com.jd.lib.un.utils;

/* loaded from: classes16.dex */
public class UnStringUtils {
    public static boolean equalsIgnoreCase(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equalsIgnoreCase(str2);
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (!Character.isWhitespace(str.charAt(i2))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTrimEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String lowerFirstLetter(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if (Character.isUpperCase(str.charAt(0))) {
            return String.valueOf((char) (str.charAt(0) + ' ')) + str.substring(1);
        }
        return str;
    }

    public static String null2Length0(String str) {
        return str == null ? "" : str;
    }

    public static String reverse(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        if (length <= 1) {
            return str;
        }
        int i2 = length >> 1;
        char[] charArray = str.toCharArray();
        for (int i3 = 0; i3 < i2; i3++) {
            char c2 = charArray[i3];
            int i4 = (length - i3) - 1;
            charArray[i3] = charArray[i4];
            charArray[i4] = c2;
        }
        return new String(charArray);
    }

    public static String spilitSubString(String str, int i2) {
        if (str != null) {
            try {
                if (str.length() > i2) {
                    str = str.substring(0, i2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return str == null ? "" : str;
    }

    public static String upperFirstLetter(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if (Character.isLowerCase(str.charAt(0))) {
            return String.valueOf((char) (str.charAt(0) - ' ')) + str.substring(1);
        }
        return str;
    }
}
