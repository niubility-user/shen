package com.jingdong.aura.a.b;

import com.jingdong.aura.wrapper.listener.AuraEventListener;

/* loaded from: classes.dex */
public class e {
    private static AuraEventListener a;

    public static void a(AuraEventListener auraEventListener) {
        a = auraEventListener;
    }

    public static void a(String str, String str2, String str3, Throwable th) {
        AuraEventListener auraEventListener = a;
        if (auraEventListener != null) {
            auraEventListener.onTrace(str, a(str), str2, str3, th);
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, Throwable th) {
        AuraEventListener auraEventListener = a;
        if (auraEventListener != null) {
            auraEventListener.onTrace(str, a(str), str2, str3, str4, str5, th);
        }
    }

    public static void a(String str, int i2, String str2, String str3, Throwable th) {
        AuraEventListener auraEventListener = a;
        if (auraEventListener != null) {
            auraEventListener.onTrace(str, i2, str2, str3, th);
        }
    }

    public static void a(String str, String str2, int i2, String str3, String str4) {
        AuraEventListener auraEventListener = a;
        if (auraEventListener != null) {
            if (str3 == null) {
                str3 = "";
            }
            auraEventListener.onTrace(str, str2, i2, str3, str4);
        }
    }

    private static int a(String str) {
        h hVar = (h) com.jingdong.aura.a.b.k.b.b(str);
        if (hVar != null) {
            return hVar.l();
        }
        return -1;
    }
}
