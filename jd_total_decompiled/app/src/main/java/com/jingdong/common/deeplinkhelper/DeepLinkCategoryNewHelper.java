package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;

/* loaded from: classes5.dex */
public class DeepLinkCategoryNewHelper {
    public static void startCategoryActivity(Context context, Bundle bundle) {
        if (context == null) {
            return;
        }
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_NEW_CATEGORY_ACTIVITY, bundle);
    }
}
