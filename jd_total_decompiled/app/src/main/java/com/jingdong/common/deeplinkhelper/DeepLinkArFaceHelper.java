package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes5.dex */
public class DeepLinkArFaceHelper {
    public static final String EXTRA_ACTIVITY_ID = "activity_id";
    public static final String EXTRA_ACTIVITY_URL = "activity_url";
    private static final String TAG = "DeepLinkArFaceHelper";

    public static void startArFace(Context context, Bundle bundle) {
        if (Log.D) {
            Log.d(TAG, "start bundle-matrixar,ArFaceActivity");
        }
        DeepLinkCommonHelper.startActivityDirect(context, "arfaceactivity", bundle);
    }
}
