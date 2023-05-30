package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkTryClothes3DHelper {
    private static final String TAG = "DeepLinkTryClothes3DHelper";

    public static void startTryClothes3D(Context context, Bundle bundle) {
        if (OKLog.D) {
            OKLog.d(TAG, "start bundle-threedtryclothes");
        }
        DeepLinkCommonHelper.startActivityDirect(context, "threedtryclothactivity", bundle);
    }
}
