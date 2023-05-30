package com.jd.fireeye.a.d;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.jd.android.sdk.coreinfo.CoreInfo;
import java.util.List;

/* loaded from: classes13.dex */
public class a {
    private static long a;

    public static String a(Context context) {
        return a(context, h.b(context).longValue(), com.jd.fireeye.a.c.e.c(context), com.jd.fireeye.a.c.d.a(), com.jd.fireeye.a.c.b.a(context));
    }

    public static boolean b(Context context) {
        try {
            boolean z = (context.getResources().getConfiguration().screenLayout & 15) >= 3;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            return z || Math.sqrt(Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d) + Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d)) >= 7.0d;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String a(Context context, long j2, String str, String str2, List<String> list) {
        try {
            if (Build.BOARD == "rk30sdk") {
                a |= 1;
            }
            if (com.jd.fireeye.a.c.a.a(context) == "cn.com.longene.www.myapplication") {
                a |= 2;
            }
            if (CoreInfo.Device.isRoot() && j2 > 0 && !b(context)) {
                a |= 4;
            }
            if (Long.valueOf(str).longValue() < 26843545600L && list.contains("com.android.deskclock")) {
                a |= 8;
            }
            if (str2.contains("libTTArtArm.so") && Build.BOOTLOADER == "cph") {
                a |= 16;
            }
        } catch (Exception unused) {
        }
        return String.valueOf(a);
    }
}
