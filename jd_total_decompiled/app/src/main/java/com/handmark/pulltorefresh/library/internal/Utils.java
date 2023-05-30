package com.handmark.pulltorefresh.library.internal;

import com.jingdong.common.UnLog;

/* loaded from: classes12.dex */
public class Utils {
    static final String LOG_TAG = "PullToRefresh";

    public static void warnDeprecation(String str, String str2) {
        UnLog.w(LOG_TAG, "You're using the deprecated " + str + " attr, please switch over to " + str2);
    }
}
