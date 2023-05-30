package com.jd.android.sdk.coreinfo.util;

import com.jingdong.manto.sdk.api.IMantoServerRequester;

/* loaded from: classes.dex */
public final class d {
    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod(IMantoServerRequester.GET, String.class, String.class).invoke(null, str, str2);
        } catch (Throwable th) {
            Logger.w("CoreInfo.SystemPropertyUtil", "An exception happends when call get(), key='" + str + "':\n" + th.toString());
            return str2;
        }
    }
}
