package com.jd.lib.un.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes16.dex */
public class UnRegexUtils {
    public static List<String> getMatches(String str, CharSequence charSequence) {
        if (charSequence == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile(str).matcher(charSequence);
        while (matcher.find()) {
            arrayList.add(matcher.group());
        }
        return arrayList;
    }

    public static String getReplaceAll(String str, String str2, String str3) {
        return str == null ? "" : Pattern.compile(str2).matcher(str).replaceAll(str3);
    }

    public static String getReplaceFirst(String str, String str2, String str3) {
        return str == null ? "" : Pattern.compile(str2).matcher(str).replaceFirst(str3);
    }

    public static String[] getSplits(String str, String str2) {
        return str == null ? new String[0] : str.split(str2);
    }

    public static boolean isEmail(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_EMAIL, charSequence);
    }

    public static boolean isIDCard15(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_ID_CARD15, charSequence);
    }

    public static boolean isIDCard18(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_ID_CARD18, charSequence);
    }

    public static boolean isMatch(String str, CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0 && Pattern.matches(str, charSequence);
    }

    public static boolean isMobile(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_MOBILE_EXACT, charSequence);
    }

    public static boolean isTel(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_TEL, charSequence);
    }

    public static boolean isURL(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_URL, charSequence);
    }

    public static boolean isZh(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_ZH, charSequence);
    }
}
