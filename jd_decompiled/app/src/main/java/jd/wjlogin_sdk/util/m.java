package jd.wjlogin_sdk.util;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.Locale;

/* loaded from: classes.dex */
public class m {
    private static Locale a(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 24) {
            return configuration.getLocales().get(0);
        }
        return configuration.locale;
    }

    public static String b(Context context) {
        Locale a = a(context);
        if (a != null) {
            return a.getLanguage() + CartConstant.KEY_YB_INFO_LINK + a.getCountry();
        }
        return "";
    }

    public static String c(Context context) {
        Locale a = a(context);
        return a != null ? a.getLanguage() : "";
    }
}
