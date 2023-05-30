package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;

/* loaded from: classes5.dex */
public class DeepLinkShareOrderHelper {
    public static void startCommentPhotoActivity(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_SHARE_ORDER_COMMENT_PHOTO, bundle);
    }

    public static void startCommentSelectionActivity(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_SHARE_ORDER_COMMENT_SELECTION, bundle);
    }
}
