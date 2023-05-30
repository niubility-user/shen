package com.jingdong.amon.router.a;

import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.amon.router.module.DynamicLetter;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes18.dex */
public class b {
    private static HashMap<String, DynamicLetter> a;

    public static DynamicLetter a(String str) {
        if (TextUtils.isEmpty(str) || a == null) {
            return null;
        }
        return a.get(a(Uri.parse(str)));
    }

    public static String a(Uri uri) {
        String scheme = uri.getScheme();
        String host = uri.getHost();
        String path = uri.getPath();
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(scheme)) {
            sb.append(scheme);
            sb.append("://");
        }
        if (!TextUtils.isEmpty(host)) {
            sb.append(host);
        }
        if (!TextUtils.isEmpty(path)) {
            sb.append(path);
        }
        return sb.toString();
    }

    public static void a() {
        HashMap<String, DynamicLetter> hashMap = a;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public static void a(String str, DynamicLetter dynamicLetter) {
        if (a == null) {
            a = new HashMap<>();
        }
        a.put(a(dynamicLetter.getUri()), dynamicLetter);
    }

    public static void a(String str, Class cls) {
        if (a == null) {
            a = new HashMap<>();
        }
        a(str, new DynamicLetter.Builder(str, cls).build());
    }

    public static DynamicLetter b(String str) {
        if (TextUtils.isEmpty(str) || a == null) {
            return null;
        }
        return a.remove(a(Uri.parse(str)));
    }

    public static Map<String, DynamicLetter> b() {
        return a;
    }
}
