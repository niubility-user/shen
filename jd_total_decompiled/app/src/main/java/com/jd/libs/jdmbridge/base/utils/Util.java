package com.jd.libs.jdmbridge.base.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes16.dex */
public class Util {
    private static Pattern pattern = Pattern.compile("\\$\\{\\w+\\}");

    public static String concatString(Object... objArr) {
        StringBuilder sb = new StringBuilder("");
        for (Object obj : objArr) {
            if (obj != null) {
                sb.append(obj.toString());
            }
        }
        return sb.toString();
    }

    public static String processTemplate(String str, Map<String, Object> map) {
        StringBuffer stringBuffer = new StringBuffer();
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            Object obj = map.get(matcher.group().substring(2, r1.length() - 1));
            matcher.appendReplacement(stringBuffer, obj == null ? "" : obj.toString());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
