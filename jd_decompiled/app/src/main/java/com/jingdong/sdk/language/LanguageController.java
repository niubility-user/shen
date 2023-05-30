package com.jingdong.sdk.language;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class LanguageController {
    public static final String LANGUAGE_CODE_EN_US = "en_US";
    public static final String LANGUAGE_CODE_TH_TH = "th_TH";
    public static final String LANGUAGE_CODE_ZH_CN = "zh_CN";
    private static String a = "languageSetting_languageCode";
    @Deprecated
    private static List<String> b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    private static Context f15170c = null;
    public static final String LANGUAGE_CODE_FOLLOW_SYSTEM = "follow_system";
    private static String d = LANGUAGE_CODE_FOLLOW_SYSTEM;

    private static void a(Context context, String str) {
        if (context == null || context.getApplicationContext() == null) {
            return;
        }
        if (f15170c == null) {
            f15170c = context.getApplicationContext();
        }
        LanguageUtil.setAppLanguageCode(f15170c, str);
    }

    public static Context createLanguageContext(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            d = str;
        }
        String languageCodeSetting = getLanguageCodeSetting(context);
        String str2 = "language setting: " + languageCodeSetting;
        String a2 = a(languageCodeSetting);
        a(context, a2);
        return LanguageUtil.a(context, a2);
    }

    @Deprecated
    public static String getAppLanguageCode() {
        Context context = f15170c;
        return context == null ? "" : LanguageUtil.getAppLanguageCode(context);
    }

    public static String getDefaultLanguageSetting() {
        return d;
    }

    public static String getLanguageCodeSetting(Context context) {
        return context.getSharedPreferences("jd_global_sp", 0).getString(a, getDefaultLanguageSetting());
    }

    public static String getSystemLanguageCode() {
        return LanguageUtil.getSystemLanguageCode();
    }

    @Deprecated
    public static boolean isAppCurrentLanguage(String str) {
        Context context = f15170c;
        if (context == null) {
            return false;
        }
        return isAppCurrentLanguage(context, str);
    }

    public static boolean isSystemCurrentLanguage(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.equals(getSystemLanguageCode());
    }

    public static void setAppLanguageCode(Context context, String str, String str2) {
        try {
            setAppLanguageCode(context, str, Class.forName(str2));
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    @Deprecated
    public static void setDefaultWhenUnsupportSystem(String str) {
    }

    @Deprecated
    public static void setSupportedLanguages(String... strArr) {
        if (strArr != null) {
            b.clear();
            b.addAll(Arrays.asList(strArr));
        }
    }

    public static String getAppLanguageCode(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            return null;
        }
        return LanguageUtil.getAppLanguageCode(context.getApplicationContext());
    }

    public static boolean isAppCurrentLanguage(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null || context.getApplicationContext() == null) {
            return false;
        }
        return getAppLanguageCode(context.getApplicationContext()).equals(str);
    }

    public static void setAppLanguageCode(Context context, String str, Class cls) {
        if (context == null || context.getApplicationContext() == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (TextUtils.isEmpty(str) || getLanguageCodeSetting(applicationContext).equals(str)) {
            return;
        }
        applicationContext.getSharedPreferences("jd_global_sp", 0).edit().putString(a, str).commit();
        a(applicationContext, a(str));
        a(applicationContext, cls);
    }

    private static String a(String str) {
        return LANGUAGE_CODE_FOLLOW_SYSTEM.equals(str) ? getSystemLanguageCode() : str;
    }

    private static void a(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(268468224);
        context.startActivity(intent);
    }

    public static Context createLanguageContext(Context context) {
        return createLanguageContext(context, (String) null);
    }

    @Deprecated
    public static Context createLanguageContext(Context context, int i2) {
        return createLanguageContext(context, (String) null);
    }

    @Deprecated
    public static Context createLanguageContext(Context context, List<String> list, String str) {
        return createLanguageContext(context, str);
    }
}
