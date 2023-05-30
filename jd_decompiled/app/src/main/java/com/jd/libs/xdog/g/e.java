package com.jd.libs.xdog.g;

import android.content.Context;
import android.content.pm.PackageManager;
import com.jd.libs.xdog.ui.XDogWebView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class e {
    public static void a(XDogWebView xDogWebView, String str, String str2, Object obj, String str3) {
        b(xDogWebView, String.format("%s && %s('%s')", str, str, d.b(str2, obj, str3)));
    }

    public static void b(final XDogWebView xDogWebView, final String str) {
        xDogWebView.getView().post(new Runnable() { // from class: com.jd.libs.xdog.g.a
            @Override // java.lang.Runnable
            public final void run() {
                XDogWebView.this.evaluateJavascript(str, null);
            }
        });
    }

    public static String c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String d(long j2) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault()).format(new Date(j2));
    }

    public static String e(Map<String, String> map, String str, String str2, boolean z) {
        if (map.containsKey(str2) && map.containsKey(str)) {
            String str3 = map.get(str);
            String str4 = map.get(str2);
            if (str3 == null || str4 == null) {
                return "0";
            }
            long parseLong = Long.parseLong(str4) - Long.parseLong(str3);
            if (z) {
                return parseLong + "ms";
            }
            return String.valueOf(parseLong);
        }
        return "0";
    }

    public static String f(JSONObject jSONObject, String str, String str2) {
        long optLong = jSONObject.optLong(str);
        long optLong2 = jSONObject.optLong(str2);
        if (optLong == 0 || optLong2 == 0) {
            return "0ms";
        }
        return (optLong2 - optLong) + "ms";
    }
}
