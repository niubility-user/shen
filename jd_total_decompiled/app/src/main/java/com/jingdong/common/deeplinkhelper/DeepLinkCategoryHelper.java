package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkCategoryHelper {
    private static final String TAG = "DeepLinkCategoryHelper";

    public static void homeToCategoryActivity(Context context, Bundle bundle) {
        startCategoryActivity(context, bundle);
    }

    private static boolean isCategorySwitchOpen() {
        return DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(26));
    }

    private static void startCategoryActivity(Context context) {
        startCategoryActivity(context, null);
    }

    private static void startCategoryActivity(Context context, Bundle bundle) {
        if (context == null) {
            return;
        }
        if (!isCategorySwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-category switch is close ");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "bundle-category switch is open ");
        }
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CATEGORY_ACTIVITY, bundle);
    }
}
