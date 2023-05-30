package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;

/* loaded from: classes5.dex */
public class DeepLinkJDDynamicHelper {
    public static void startDynamicActivity(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JD_COMMON_DYNAMIC_ACTIVITY, bundle);
    }
}
