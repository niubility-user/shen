package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;

/* loaded from: classes5.dex */
public class DeepLinkWidgetHelper {
    private static final String HOST_WIDGET_EDIT_SETTING = "WidgetEditSettingActivity";

    public static void WidgetEditSettingActivity(Context context, Bundle bundle) {
        if (context != null) {
            DeepLinkCommonHelper.startActivityDirect(context, HOST_WIDGET_EDIT_SETTING, checkBundle(bundle));
        }
    }

    private static Bundle checkBundle(Bundle bundle) {
        return bundle == null ? new Bundle() : bundle;
    }
}
