package com.jd.lib.babel.ifloor.utils;

import android.graphics.Color;
import android.text.TextUtils;

/* loaded from: classes13.dex */
public class ColorUtil {
    public static int parseColor(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        try {
            return Color.parseColor(str.trim());
        } catch (Exception e2) {
            e2.printStackTrace();
            return i2;
        }
    }
}
