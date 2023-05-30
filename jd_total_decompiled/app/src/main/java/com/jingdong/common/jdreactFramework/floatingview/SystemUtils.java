package com.jingdong.common.jdreactFramework.floatingview;

import android.content.Context;
import com.jingdong.common.jdreactFramework.JDReactHelper;

/* loaded from: classes5.dex */
public class SystemUtils {
    public static int getScreenHeight(Context context) {
        try {
            return JDReactHelper.newInstance().getScreenHeight();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static int getScreenWidth(Context context) {
        try {
            return JDReactHelper.newInstance().getScreenWidth();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
