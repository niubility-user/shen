package com.jingdong.jdpush_new.j;

import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;

/* loaded from: classes12.dex */
public class f {
    public static boolean a(Context context) {
        if (Build.VERSION.SDK_INT < 19) {
            return true;
        }
        try {
            return NotificationManagerCompat.from(context).areNotificationsEnabled();
        } catch (Error | Exception unused) {
            return true;
        }
    }

    public static String b(Context context) {
        return a(context) ? "1" : "0";
    }
}
