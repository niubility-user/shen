package com.jingdong.manto.utils;

import java.util.Map;

/* loaded from: classes16.dex */
public class m {
    public static Map<String, String> a;

    public static String a(String str, String str2) {
        String str3;
        Map<String, String> map = a;
        return (map == null || (str3 = map.get(str)) == null) ? str2 : str3;
    }

    public static void a(Map<String, String> map) {
        a = map;
    }
}
