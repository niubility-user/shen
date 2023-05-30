package com.jingdong.aura.core.util;

import android.text.TextUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class e {
    private static final String[] a = {"armeabi-v7a", "armeabi"};
    private static final String[] b = {"arm64-v8a"};

    /* renamed from: c  reason: collision with root package name */
    private static Map<String, String> f12169c = new HashMap();

    private static boolean a(String str, String[] strArr) {
        if (!TextUtils.isEmpty(str) && strArr != null && strArr.length > 0) {
            for (String str2 : strArr) {
                if (str.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean b() {
        return j.a();
    }

    public static String a(String str, File file) {
        String a2;
        if (str == null) {
            return "armeabi";
        }
        if (f12169c.containsKey(str)) {
            return f12169c.get(str);
        }
        if (b()) {
            a2 = a();
        } else {
            a2 = a(file);
        }
        f12169c.put(str, a2);
        return a2;
    }

    private static String a() {
        return b[0];
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00d5, code lost:
        r0 = (java.lang.String) r1.get(r3);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a(java.io.File r10) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.core.util.e.a(java.io.File):java.lang.String");
    }
}
