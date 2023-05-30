package com.jingdong.common.unification.router;

import android.content.Context;
import com.jingdong.common.unification.router.config.JDRouterConfig;

/* loaded from: classes6.dex */
public class JDRouterMtaUtil {
    private static final String ENTER_EVENT_ID = "JDRouter_Enter";
    private static final String ERROR_EVENT_ID = "JDRouter_Error";
    private static final String TAG = "JDRouterMtaUtil";

    public static void routerEnterMta(Context context, String str, String str2) {
        try {
            JDRouterConfig.d(TAG, "routerEnterMta:page_name:" + str + ",event_Param:" + str2);
            JDRouterConfig.mtaEvent(context, ENTER_EVENT_ID, str2, str);
        } catch (Throwable th) {
            if (JDRouterConfig.isDebug()) {
                JDRouterConfig.e(TAG, th.getMessage());
            }
        }
    }

    public static void routerErrorMta(Context context, String str, String str2) {
        try {
            JDRouterConfig.d(TAG, "routerEnterMta:page_name:" + str + ",event_Param:" + str2);
            JDRouterConfig.mtaEvent(context, ERROR_EVENT_ID, str2, str);
        } catch (Throwable th) {
            if (JDRouterConfig.isDebug()) {
                JDRouterConfig.e(TAG, th.getMessage());
            }
        }
    }
}
