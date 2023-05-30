package com.facebook.react.views.view;

import androidx.core.view.ViewCompat;

/* loaded from: classes12.dex */
public class ColorUtil {
    public static int getOpacityFromColor(int i2) {
        int i3 = i2 >>> 24;
        if (i3 == 255) {
            return -1;
        }
        return i3 == 0 ? -2 : -3;
    }

    public static int multiplyColorAlpha(int i2, int i3) {
        if (i3 == 255) {
            return i2;
        }
        if (i3 == 0) {
            return i2 & ViewCompat.MEASURED_SIZE_MASK;
        }
        int i4 = i3 + (i3 >> 7);
        return (i2 & ViewCompat.MEASURED_SIZE_MASK) | ((((i2 >>> 24) * i4) >> 8) << 24);
    }
}
