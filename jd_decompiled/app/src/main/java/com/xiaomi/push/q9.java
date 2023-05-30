package com.xiaomi.push;

import com.jingdong.manto.sdk.api.IMantoServerRequester;

/* loaded from: classes11.dex */
public class q9 {
    public static String a(String str, String str2) {
        try {
            return (String) r9.c(null, "android.os.SystemProperties").getMethod(IMantoServerRequester.GET, String.class, String.class).invoke(null, str, str2);
        } catch (Exception e2) {
            g.j.a.a.a.c.o("SystemProperties.get: " + e2);
            return str2;
        }
    }
}
