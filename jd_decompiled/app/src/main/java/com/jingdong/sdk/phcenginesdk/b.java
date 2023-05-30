package com.jingdong.sdk.phcenginesdk;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public class b {
    private static a a = new c();

    public static Map<String, String> a(Context context, String str) throws IllegalArgumentException {
        return str.length() == 0 ? new HashMap() : a.a(context, str);
    }

    public static String b(Context context, Map<String, String> map) throws IllegalArgumentException {
        return map.size() == 0 ? "" : a.b(context, map);
    }

    public static void c(Context context, d dVar) {
        a.c(context, dVar);
    }
}
