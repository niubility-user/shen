package com.tencent.tmsqmsp.oaid2;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes9.dex */
public class e {
    public static Context a;

    public static Context a() {
        Context context;
        synchronized (e.class) {
            if (a == null) {
                a = b();
            }
            context = a;
        }
        return context;
    }

    public static String a(Context context) {
        String packageName;
        if (context == null) {
            return "";
        }
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo != null && !TextUtils.isEmpty(applicationInfo.packageName)) {
                packageName = applicationInfo.packageName;
                return packageName;
            }
            packageName = a().getPackageName();
            return packageName;
        } catch (Exception unused) {
            return "";
        }
    }

    public static Context b() {
        try {
            return (Context) Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
