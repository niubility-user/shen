package com.jd.lib.flexcube.iwidget.b;

import android.content.Context;
import com.jd.lib.flexcube.iwidget.R;

/* loaded from: classes14.dex */
public class c {
    public static String a(Context context, String str) {
        return context.getResources().getString(R.string.iwidget_yangjiao) + str;
    }

    public static boolean b(String str, String str2) {
        return d(str) && d(str2) && str.length() >= str2.length() && str.substring(0, str2.length()).equals(str2);
    }

    public static boolean c(String str) {
        return str == null || !str.getClass().getName().equals("java.lang.String") || str.isEmpty();
    }

    public static boolean d(String str) {
        return !c(str);
    }
}
