package com.jdjr.risk.a.a;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jdjr.risk.device.c.ag;
import com.jdjr.risk.device.c.b;
import com.jdjr.risk.util.a.d;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.util.UUID;

/* loaded from: classes18.dex */
public class a {
    private static String[] a = {ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID, "document", "MQ", "web"};
    private static String b = "BASEIN_DEVICE_ANDROIDID";

    /* renamed from: c  reason: collision with root package name */
    private static String f7278c = "BASEIN_DEVICE_UUID";
    private static volatile a d;

    private a() {
    }

    public static a a() {
        if (d == null) {
            synchronized (a.class) {
                if (d == null) {
                    d = new a();
                }
            }
        }
        return d;
    }

    private boolean a(Context context, String str, String str2) {
        try {
            String a2 = com.jdjr.risk.a.b.a.a(str);
            if (TextUtils.isEmpty(a2)) {
                return false;
            }
            d.a(context, str2, a2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private String b(Context context) {
        try {
            String c2 = c(context, b);
            if (TextUtils.isEmpty(c2)) {
                c2 = Build.VERSION.SDK_INT < 29 ? ag.c(context) : b.a(context);
            }
            if (TextUtils.isEmpty(c2)) {
                return c2;
            }
            a(context, c2, b);
            return c2;
        } catch (Exception unused) {
            return "";
        }
    }

    private boolean b(Context context, String str, String str2) {
        return false;
    }

    private String c(Context context) {
        try {
            String d2 = d(context, f7278c);
            if (TextUtils.isEmpty(d2)) {
                d2 = UUID.randomUUID().toString();
                if (!TextUtils.isEmpty(d2)) {
                    if (!b(context, d2, f7278c)) {
                        return "";
                    }
                }
            }
            return d2;
        } catch (Exception unused) {
            return "";
        }
    }

    private String c(Context context, String str) {
        try {
            String b2 = d.b(context, str, "");
            return !TextUtils.isEmpty(b2) ? com.jdjr.risk.a.b.a.b(b2) : "";
        } catch (Exception unused) {
            return "";
        }
    }

    private String d(Context context, String str) {
        try {
            TextUtils.isEmpty(d.b(context, str, ""));
        } catch (Exception unused) {
        }
        return "";
    }

    public String a(Context context) {
        String b2 = b(context);
        return TextUtils.isEmpty(b2) ? c(context) : b2;
    }

    public boolean a(Context context, String str) {
        return a(context, str, b);
    }

    public boolean b(Context context, String str) {
        return b(context, str, f7278c);
    }
}
