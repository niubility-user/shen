package com.tencent.smtt.utils;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class f {
    private static f b;
    private final Map<Integer, String> a;

    private f() {
        HashMap hashMap = new HashMap();
        this.a = hashMap;
        hashMap.put(325, "if NoSuchMethodError, please check the Class in base.apk which is conflict with x5.");
        hashMap.put(326, "your CPU is i686|mips|x86_64? sorry X5 is not support them.");
        hashMap.put(402, "QbSdk.forceSysWebView() has been called! Check it.");
        hashMap.put(404, "try to restart your app.");
    }

    public static f a() {
        if (b == null) {
            b = new f();
        }
        return b;
    }

    public String a(int i2) {
        if (i2 < 303 || i2 > 324) {
            try {
                return this.a.get(Integer.valueOf(i2));
            } catch (Exception unused) {
                return "Unexpected load error.";
            }
        }
        return "Core has some problem, try to reinstall the app.";
    }
}
