package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkNewArrivalsHelper {
    public static void startNewArrivalsActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_NEW_ARRIVALS).toString(), bundle);
    }
}
