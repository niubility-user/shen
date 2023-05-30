package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;

/* loaded from: classes5.dex */
public class DeepLinkRecoderHelper {
    private static final String HOST_UNIFIEDCONTROL_RECODER = "recoder_activity";
    public static final int UNIFIEDCONTROL_RECODER_REQUEST_CODE = 12316;

    public static void startRecoderForResult(Context context) {
        DeepLinkCommonHelper.startActivityForResult((Activity) context, "recoder_activity", null, 12316);
    }
}
