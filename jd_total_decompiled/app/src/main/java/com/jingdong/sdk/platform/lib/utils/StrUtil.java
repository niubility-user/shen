package com.jingdong.sdk.platform.lib.utils;

import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes.dex */
public class StrUtil {
    public static String getString(int i2) {
        if (JdSdk.getInstance().getApplication() != null) {
            JdSdk.getInstance().getApplication().getString(i2);
            return "";
        }
        return "";
    }
}
