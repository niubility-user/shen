package com.jingdong.common.baseRecycleAdapter.util;

/* loaded from: classes5.dex */
public class TouchEventUtil {
    public static String getTouchAction(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "Unknow:id=" + i2 : "ACTION_OUTSIDE" : "ACTION_CANCEL" : "ACTION_MOVE" : "ACTION_UP" : "ACTION_DOWN";
    }
}
