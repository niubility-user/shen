package com.jingdong.app.mall.bundle.smile.utils;

import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes3.dex */
public class DisplayUtils {
    private static final String TAG = "DisplayUtils";

    public static int dip2px(float f2) {
        return (int) ((f2 * getDensity()) + 0.5f);
    }

    public static float getDensity() {
        return BaseInfo.getDensity();
    }
}
