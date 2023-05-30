package com.tencent.smtt.utils;

import android.text.TextUtils;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.lang.reflect.Method;

/* loaded from: classes9.dex */
public class PropertyUtils {
    private static Class a;
    private static Method b;

    static {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            a = cls;
            b = cls.getDeclaredMethod(IMantoServerRequester.GET, String.class, String.class);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static String a(String str, String str2) {
        Method method;
        Class cls = a;
        if (cls == null || (method = b) == null) {
            return str2;
        }
        try {
            return (String) method.invoke(cls, str, str2);
        } catch (Throwable th) {
            th.printStackTrace();
            return str2;
        }
    }

    public static String getQuickly(String str, String str2) {
        return TextUtils.isEmpty(str) ? str2 : a(str, str2);
    }
}
