package com.jingdong.aura.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class k {
    private static Map<String, String> a;
    private static String b = System.getProperty("java.vm.version");

    /* renamed from: c  reason: collision with root package name */
    private static String f12171c;

    static {
        a = new HashMap();
        a.put("armeabi", "arm");
        a.put("armeabi-v7a", "arm");
        a.put("mips", "mips");
        a.put("mips64", "mips64");
        a.put("x86", "x86");
        a.put("x86_64", "x86_64");
        a.put("arm64-v8a", "arm64");
        String osBuild_CPU_ABI = com.jingdong.aura.a.b.c.F().getOsBuild_CPU_ABI();
        if (osBuild_CPU_ABI != null) {
            f12171c = a.get(osBuild_CPU_ABI);
        }
        a.clear();
        a = null;
        com.jingdong.aura.core.util.l.c.a("VmUtils");
    }

    public static String a() {
        return f12171c;
    }

    public static Boolean b() {
        boolean z = false;
        if (b != null) {
            Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(b);
            if (matcher.matches()) {
                try {
                    int parseInt = Integer.parseInt(matcher.group(1));
                    int parseInt2 = Integer.parseInt(matcher.group(2));
                    if (parseInt > 2 || (parseInt == 2 && parseInt2 >= 1)) {
                        z = true;
                    }
                } catch (NumberFormatException unused) {
                }
            }
        }
        return Boolean.valueOf(z);
    }
}
