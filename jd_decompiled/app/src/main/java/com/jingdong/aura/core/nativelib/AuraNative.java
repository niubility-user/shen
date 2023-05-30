package com.jingdong.aura.core.nativelib;

import com.jingdong.aura.a.b.e;
import com.jingdong.aura.core.util.k;
import com.jingdong.aura.core.util.l.b;
import com.jingdong.aura.core.util.l.c;

/* loaded from: classes4.dex */
public class AuraNative {
    private static final b a = c.a("AuraNative");
    private static boolean b;

    /* renamed from: c */
    private static boolean f12148c;

    static {
        b = false;
        try {
            System.loadLibrary("aura");
            b = true;
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
        }
        try {
            if (com.jingdong.aura.a.b.c.A()) {
                System.loadLibrary("aurautils");
                f12148c = true;
            }
        } catch (Exception e3) {
            e.a("AuraNative", "aurautils loaded failed", "AuraNative", e3);
        }
    }

    public static boolean a(String str) {
        if (!f12148c) {
            a.b("mIsLoadedAuraUtils is false, loade aurautils failed! ");
            return false;
        }
        try {
        } catch (Throwable th) {
            th.printStackTrace();
            e.a("AuraNative", "vefiySignByAura failed", "AuraNative.vefiySignByAura", th);
        }
        return verifySign(str);
    }

    private static native void compile(String str, String str2, boolean z, String str3, boolean z2, int i2);

    public static native boolean verifySign(String str);

    public static boolean a(String str, String str2) {
        if (b && com.jingdong.aura.a.b.c.z()) {
            boolean x = com.jingdong.aura.a.b.c.x();
            int L = com.jingdong.aura.a.b.c.L();
            try {
                if (!k.b().booleanValue()) {
                    compile(str, str2, false, "", x, L);
                    return true;
                }
                compile(str, str2, true, k.a(), x, L);
                return true;
            } catch (Throwable th) {
                a.a("Exception while try to compile code >>>", th);
                return false;
            }
        }
        a.b("not use AuraNative to opt dex. sIsLoadedNativeLibry = " + b + ", AuraConfigInternal.getIsUseAuraDexOpt()" + com.jingdong.aura.a.b.c.z());
        return false;
    }
}
