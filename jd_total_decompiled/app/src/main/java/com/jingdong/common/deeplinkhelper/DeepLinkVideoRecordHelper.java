package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkVideoRecordHelper {
    public static void startVideoRecordActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host("VideoRecordActivity").toString(), bundle);
    }

    public static void startVideoRecordActivityForResult(Activity activity, Bundle bundle, int i2) {
        DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host("VideoRecordActivity").toString(), bundle, i2);
    }
}
