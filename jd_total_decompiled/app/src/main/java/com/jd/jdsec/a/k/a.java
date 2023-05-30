package com.jd.jdsec.a.k;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.List;

/* loaded from: classes13.dex */
public class a {
    private static long a;

    public static Long a(Context context, long j2, String str, String str2, List<String> list) {
        try {
            if (Build.BOARD == "rk30sdk") {
                a |= 1;
            }
            if (com.jd.jdsec.a.b.a().equals("cn.com.longene.www.myapplication")) {
                a |= 2;
            }
            if (BaseInfo.isRoot() && j2 > 0 && !b(context)) {
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
        return Long.valueOf(a);
    }

    public static boolean b(Context context) {
        boolean z = (context.getResources().getConfiguration().screenLayout & 15) >= 3;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return z || Math.sqrt(Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d) + Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d)) >= 7.0d;
    }
}
