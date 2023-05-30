package com.meizu.cloud.pushsdk.notification.g;

import android.content.Context;
import com.jingdong.common.utils.LangUtils;
import com.meizu.cloud.pushinternal.DebugLogger;

/* loaded from: classes14.dex */
public class d {
    private static d a;

    private d(Context context) {
        c(context);
    }

    public static d b(Context context) {
        if (a == null) {
            a = new d(context);
        }
        return a;
    }

    private void c(Context context) {
        context.getAssets();
    }

    public int a(Context context, String str, String str2) {
        DebugLogger.i("ResourceReader", "Get resource type " + str2 + LangUtils.SINGLE_SPACE + str);
        return context.getResources().getIdentifier(str, str2, context.getApplicationInfo().packageName);
    }
}
