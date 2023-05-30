package com.jingdong.manto.ui;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import com.jingdong.manto.utils.MantoLog;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/* loaded from: classes16.dex */
public class d {
    private static Boolean a;
    private static Boolean b;

    public static int a(String str, int i2) {
        return (int) a(str, i2);
    }

    private static long a(String str, long j2) {
        if (TextUtils.isEmpty(str)) {
            return j2;
        }
        if (str.startsWith("#") && str.length() == 4) {
            StringBuilder sb = new StringBuilder(str);
            sb.insert(2, str.charAt(1));
            sb.insert(4, str.charAt(2));
            sb.insert(6, str.charAt(3));
            str = sb.toString();
        }
        try {
            return Color.parseColor(str);
        } catch (Exception unused) {
            MantoLog.e("MantoActivityUtil", String.format("Failed to parse color: %s", str));
            return j2;
        }
    }

    public static Activity a(Context context) {
        if (context != null && (context instanceof ContextWrapper) && !(context instanceof Activity)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    public static boolean a() {
        if (a == null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                try {
                    try {
                        Properties properties = new Properties();
                        properties.load(fileInputStream);
                        a = Boolean.valueOf(properties.containsKey("ro.miui.ui.version.name"));
                    } catch (Throwable unused) {
                    }
                    try {
                        fileInputStream.close();
                    } catch (Exception unused2) {
                    }
                } catch (Exception unused3) {
                    a = Boolean.FALSE;
                    try {
                        fileInputStream.close();
                    } catch (Exception unused4) {
                    }
                    return a.booleanValue();
                }
            } catch (Throwable unused5) {
                return false;
            }
        }
        return a.booleanValue();
    }

    public static boolean a(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return true;
        }
        return Build.VERSION.SDK_INT >= 17 && activity.isDestroyed();
    }

    public static boolean a(Window window, boolean z) {
        if (window == null || window.getDecorView() == null || Build.VERSION.SDK_INT < 23) {
            return false;
        }
        if (a() && b()) {
            return false;
        }
        View decorView = window.getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        decorView.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
        return true;
    }

    private static boolean b() {
        if (b == null) {
            try {
                try {
                    FileInputStream fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                    try {
                        try {
                            Properties properties = new Properties();
                            properties.load(fileInputStream);
                            b = Boolean.valueOf(properties.getProperty("ro.miui.ui.version.name", "").contains("V8"));
                            fileInputStream.close();
                        } catch (Exception unused) {
                            b = Boolean.FALSE;
                            return b.booleanValue();
                        }
                    } finally {
                        try {
                            fileInputStream.close();
                        } catch (Exception unused2) {
                        }
                    }
                } catch (Throwable unused3) {
                }
            } catch (Exception unused4) {
                b = Boolean.FALSE;
                return false;
            }
        }
        return b.booleanValue();
    }
}
