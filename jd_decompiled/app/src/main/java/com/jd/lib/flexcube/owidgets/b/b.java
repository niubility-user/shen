package com.jd.lib.flexcube.owidgets.b;

import android.text.TextUtils;
import com.jd.lib.flexcube.iwidget.b.c;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes15.dex */
public class b {
    private static Pattern a = Pattern.compile("\\S+\\[[0-9]+\\]");
    private static Pattern b = Pattern.compile("(.*)\\[(.*)\\]");

    /* renamed from: c  reason: collision with root package name */
    private static Pattern f4427c = Pattern.compile("^[\\d]*$");
    private static Pattern d = Pattern.compile("^[-\\+]?[.\\d]*$");

    public static Object a(String str, Map<String, Object> map) {
        if (TextUtils.isEmpty(str) || map == null) {
            return null;
        }
        return str.startsWith("$") ? b(str.substring(1), map) : str;
    }

    public static Object b(String str, Map<String, Object> map) {
        Object obj;
        Object obj2;
        int g2;
        if (TextUtils.isEmpty(str) || map == null) {
            return null;
        }
        String[] split = str.split("\\.");
        int length = split.length;
        int i2 = 0;
        Map<String, Object> map2 = map;
        while (i2 < length) {
            String str2 = split[i2];
            if (!TextUtils.isEmpty(str2) && a.matcher(str2).matches()) {
                Matcher matcher = b.matcher(str2);
                if (matcher.find() && matcher.groupCount() == 2) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    if (map2 != null && (map2 instanceof Map) && (obj2 = map2.get(group)) != null && (obj2 instanceof List)) {
                        List list = (List) obj2;
                        if (f(group2) && list.size() > (g2 = g(group2))) {
                            obj = list.get(g2);
                        }
                    }
                }
                return null;
            } else if (map2 == null || !(map2 instanceof Map)) {
                return null;
            } else {
                obj = map2.get(str2);
            }
            i2++;
            map2 = obj;
        }
        return map2;
    }

    public static String c(Map<String, String> map, String str) {
        if (c.c(str)) {
            return null;
        }
        if (str.substring(0, 1).equals("$")) {
            if (map == null) {
                return null;
            }
            return map.get(str.substring(1));
        }
        return str;
    }

    public static boolean d(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return a.matcher(str).matches();
    }

    public static boolean e(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return d.matcher(str).matches();
    }

    public static boolean f(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return f4427c.matcher(str).matches();
    }

    public static int g(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            String trim = str.trim();
            return e(trim) ? Double.valueOf(trim).intValue() : f(trim) ? Integer.valueOf(trim).intValue() : 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}
