package com.jd.lib.babel.servicekit.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import com.jd.lib.babel.servicekit.BabelServer;
import com.jd.lib.babel.servicekit.iservice.ILanguage;
import java.util.Locale;

/* loaded from: classes13.dex */
public class LocalManageUtil {
    public static Locale getLocale() {
        Locale locale = Locale.ENGLISH;
        ILanguage iLanguage = (ILanguage) BabelServer.get().getService(ILanguage.class);
        if (iLanguage != null) {
            String language = iLanguage.getLanguage();
            if ("zh".equals(language)) {
                return Locale.CHINA;
            }
            if ("en".equals(language)) {
                return locale;
            }
            if ("id".equals(language)) {
                return new Locale("id");
            }
            return "th".equals(language) ? new Locale("th") : locale;
        }
        return locale;
    }

    public static Context updateResources(Context context) {
        Locale locale = getLocale();
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = new Configuration(resources.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            configuration.setLocale(locale);
            return context.createConfigurationContext(configuration);
        }
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }
}
