package com.jd.lib.un.basewidget.widget.multi.c;

import com.jd.lib.un.utils.config.UnDeviceInfo;

/* loaded from: classes16.dex */
public class a {
    public static int a(float f2) {
        return (int) ((f2 * UnDeviceInfo.getDensity()) + 0.5f);
    }

    public static int b(float f2) {
        return (int) ((f2 * UnDeviceInfo.getScaledDensity()) + 0.5f);
    }
}
