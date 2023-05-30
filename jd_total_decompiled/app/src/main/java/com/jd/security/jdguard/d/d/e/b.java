package com.jd.security.jdguard.d.d.e;

import android.content.Context;
import android.content.res.Configuration;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes17.dex */
public class b {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static float a(Context context) {
        return context.getResources().getConfiguration().fontScale;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b() {
        try {
            Object invoke = Class.forName("com.jingdong.common.utils.UserUtil").getMethod("getWJLoginHelper", null).invoke(null, null);
            return (String) invoke.getClass().getMethod("getPin", null).invoke(invoke, null);
        } catch (ClassNotFoundException e2) {
            com.jd.security.jdguard.f.d.g(e2.getMessage());
            return DYConstants.DY_NULL_STR;
        } catch (IllegalAccessException e3) {
            com.jd.security.jdguard.f.d.g(e3.getMessage());
            return DYConstants.DY_NULL_STR;
        } catch (NoSuchMethodException e4) {
            com.jd.security.jdguard.f.d.g(e4.getMessage());
            return DYConstants.DY_NULL_STR;
        } catch (InvocationTargetException e5) {
            com.jd.security.jdguard.f.d.g(e5.getMessage());
            return DYConstants.DY_NULL_STR;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c(Context context) {
        Locale locale;
        Configuration configuration = context.getResources().getConfiguration();
        if (BaseInfo.getAndroidSDKVersion() >= 24) {
            locale = configuration.getLocales().get(0);
        } else {
            locale = configuration.locale;
        }
        return locale.getLanguage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String d() {
        return TimeZone.getDefault().getID();
    }
}
