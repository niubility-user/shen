package com.jingdong.sdk.language;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Locale;

/* loaded from: classes.dex */
public class LanguageUtil {
    private static Locale a(Context context, Locale locale) {
        if (locale != null) {
            Resources resources = context.getResources();
            Configuration a = a(resources.getConfiguration(), locale);
            DisplayMetrics displayMetricsObject = BaseInfo.getDisplayMetricsObject();
            if (displayMetricsObject != null) {
                resources.updateConfiguration(a, displayMetricsObject);
            }
        }
        return a(context);
    }

    public static String getAppLanguageCode(Context context) {
        return a(a(context));
    }

    public static String getCountryFromLanguageCode(String str) {
        int indexOf;
        if (!TextUtils.isEmpty(str) && (indexOf = str.indexOf(CartConstant.KEY_YB_INFO_LINK)) >= 0) {
            return str.substring(indexOf + 1, str.length()).toUpperCase();
        }
        return null;
    }

    public static String getLanguageFromLanguageCode(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf(CartConstant.KEY_YB_INFO_LINK);
        return indexOf < 0 ? str : str.substring(0, indexOf);
    }

    public static String getSystemLanguageCode() {
        return a(a());
    }

    public static String setAppLanguageCode(Context context, String str) {
        Locale a = a(str);
        if (a != null) {
            a(context, a);
        }
        return getAppLanguageCode(context);
    }

    private static Locale a(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 24) {
            return configuration.getLocales().get(0);
        }
        return configuration.locale;
    }

    private static Locale a() {
        if (Build.VERSION.SDK_INT >= 24) {
            return Resources.getSystem().getConfiguration().getLocales().get(0);
        }
        return Resources.getSystem().getConfiguration().locale;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Context a(Context context, String str) {
        Resources resources = context.getResources();
        Configuration a = a(resources.getConfiguration(), a(str));
        if (Build.VERSION.SDK_INT >= 17) {
            return context.createConfigurationContext(a);
        }
        DisplayMetrics displayMetricsObject = BaseInfo.getDisplayMetricsObject();
        if (displayMetricsObject != null) {
            resources.updateConfiguration(a, displayMetricsObject);
        }
        return context;
    }

    private static Configuration a(Configuration configuration, Locale locale) {
        Locale.setDefault(locale);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 24) {
            configuration.setLocales(new LocaleList(locale));
        } else if (i2 >= 17) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        return configuration;
    }

    private static Locale a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String languageFromLanguageCode = getLanguageFromLanguageCode(str);
        String countryFromLanguageCode = getCountryFromLanguageCode(str);
        if (TextUtils.isEmpty(languageFromLanguageCode)) {
            return null;
        }
        if (TextUtils.isEmpty(countryFromLanguageCode)) {
            return new Locale(languageFromLanguageCode);
        }
        return new Locale(languageFromLanguageCode, countryFromLanguageCode);
    }

    private static String a(Locale locale) {
        if (TextUtils.isEmpty(locale.getCountry())) {
            return locale.getLanguage();
        }
        return locale.getLanguage() + CartConstant.KEY_YB_INFO_LINK + locale.getCountry().toUpperCase();
    }
}
