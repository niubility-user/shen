package com.jingdong.common;

import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes5.dex */
public class PdColorSizeUtil {
    private static float mDensity;

    public static int dip2px(int i2) {
        return DPIUtil.dip2px(i2);
    }

    public static void setmDensity(float f2) {
        mDensity = f2;
    }
}
