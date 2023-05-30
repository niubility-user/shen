package com.jingdong.common.utils;

import android.os.Build;

/* loaded from: classes6.dex */
public class UnLottieUtils {
    public static boolean enableLottie() {
        return Build.VERSION.SDK_INT >= 16;
    }
}
