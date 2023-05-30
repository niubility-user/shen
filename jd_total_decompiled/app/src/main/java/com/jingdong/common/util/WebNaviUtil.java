package com.jingdong.common.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import androidx.annotation.StringRes;
import com.jingdong.wireless.iconfont.IconDrawable;
import com.jingdong.wireless.iconfont.widget.IconImpl;

/* loaded from: classes6.dex */
public class WebNaviUtil {
    public static IconDrawable createIconDrawable(Context context, @StringRes int i2) {
        return new IconDrawable(context, new IconImpl("", context.getString(i2)), WebNaviSettings.getIconfontCommonTypeface());
    }

    public static int getStatusBarHeight(Activity activity) {
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return activity.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static boolean greaterOrEqualKitkat() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public static boolean greaterOrEqualN() {
        return Build.VERSION.SDK_INT >= 24;
    }
}
