package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkShanGouHelper {
    public static final String HOST_RANK_ACTIVITY = "flashranklist";
    public static final String HOST_SHANGOU_ACTIVITY = "shangouactivity";

    public static void startRankActivity(Context context, Bundle bundle, int i2) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_RANK_ACTIVITY).toString(), bundle, i2);
    }

    public static void startShanGouActivity(Context context, Bundle bundle, int i2) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_SHANGOU_ACTIVITY).toString(), bundle, i2);
    }
}
