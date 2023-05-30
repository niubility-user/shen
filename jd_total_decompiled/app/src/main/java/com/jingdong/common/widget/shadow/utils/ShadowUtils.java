package com.jingdong.common.widget.shadow.utils;

import android.content.Context;
import android.graphics.Color;
import com.jd.lib.un.utils.config.UnDeviceInfo;

/* loaded from: classes12.dex */
public class ShadowUtils {
    public static int adjustColor(float f2, int i2) {
        return Color.argb(((int) f2) * 255, Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    public static int dpToPx(Context context, int i2) {
        return (int) ((i2 * UnDeviceInfo.getDensity()) + 0.5f);
    }
}
