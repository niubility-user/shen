package com.facebook.react.uimanager.common;

/* loaded from: classes12.dex */
public class ViewUtil {
    public static int getUIManagerType(int i2) {
        return i2 % 2 == 0 ? 2 : 1;
    }

    @Deprecated
    public static boolean isRootTag(int i2) {
        return i2 % 10 == 1;
    }
}
