package com.jingdong.manto;

import android.content.Context;
import android.content.SharedPreferences;
import com.jingdong.a;

/* loaded from: classes15.dex */
public class c {
    private static a.C0232a a;

    public static Context a() {
        return a.a();
    }

    public static void a(a.C0232a c0232a) {
        a = c0232a;
    }

    public static String b() {
        Context a2 = a();
        return a2 != null ? a2.getPackageName() : "com.jingdong.manto";
    }

    public static SharedPreferences c() {
        Context a2 = a();
        if (a2 != null) {
            return a2.getSharedPreferences(d(), 0);
        }
        return null;
    }

    public static String d() {
        return b() + "_preferences";
    }
}
