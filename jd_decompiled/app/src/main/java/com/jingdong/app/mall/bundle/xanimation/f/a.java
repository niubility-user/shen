package com.jingdong.app.mall.bundle.xanimation.f;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

/* loaded from: classes3.dex */
public class a {
    private static boolean a(Context context) {
        Activity activity = (Activity) context;
        if (activity.isFinishing()) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return activity.isDestroyed();
        }
        return false;
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        return ((context instanceof Activity) && a(context)) ? false : true;
    }
}
