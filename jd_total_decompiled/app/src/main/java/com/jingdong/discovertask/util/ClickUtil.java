package com.jingdong.discovertask.util;

import android.view.View;

/* loaded from: classes12.dex */
public class ClickUtil {
    private static long mLastClickTime;
    private static int mLastClickViewId;

    public static boolean isFastDoubleClick(View view, long j2) {
        int id = view.getId();
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - mLastClickTime) >= j2 || id != mLastClickViewId) {
            mLastClickTime = currentTimeMillis;
            mLastClickViewId = id;
            return false;
        }
        return true;
    }
}
