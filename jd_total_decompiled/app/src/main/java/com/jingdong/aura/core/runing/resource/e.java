package com.jingdong.aura.core.runing.resource;

/* loaded from: classes4.dex */
public class e {
    private static final String[] a = {"Sony", "SEMC"};

    public static boolean a() {
        boolean z;
        String osBuild_BRAND = com.jingdong.aura.a.b.c.F().getOsBuild_BRAND();
        if (osBuild_BRAND != null) {
            for (String str : a) {
                if (osBuild_BRAND.equalsIgnoreCase(str)) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        String osBuild_MANUFACTURER = com.jingdong.aura.a.b.c.F().getOsBuild_MANUFACTURER();
        if (!z && osBuild_MANUFACTURER != null) {
            for (String str2 : a) {
                if (osBuild_MANUFACTURER.toLowerCase().indexOf(str2.toLowerCase()) != -1) {
                    return true;
                }
            }
        }
        return z;
    }
}
