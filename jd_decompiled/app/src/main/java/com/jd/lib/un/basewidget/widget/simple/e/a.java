package com.jd.lib.un.basewidget.widget.simple.e;

import com.jd.lib.un.utils.config.UnDeviceInfo;

/* loaded from: classes16.dex */
public class a {
    public static final float a = UnDeviceInfo.getDensity();

    public static int a(float f2) {
        double d = f2 * a;
        Double.isNaN(d);
        return (int) (d + 0.5d);
    }

    public static int b(float f2) {
        return (int) (f2 / a);
    }
}
