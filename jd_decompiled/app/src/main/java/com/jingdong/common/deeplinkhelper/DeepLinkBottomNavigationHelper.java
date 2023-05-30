package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.jdmiaosha.preload.BottomNavigationDataPreloadUtil;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkBottomNavigationHelper {
    public static final String HOST_ACTIVITY = "bottomnavigationActivity";

    public static void startActivity(Context context, Bundle bundle, int i2) {
        BottomNavigationDataPreloadUtil.logD("start bottom navigation Activity start");
        BottomNavigationDataPreloadUtil.handlePreload(context, bundle);
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_ACTIVITY).toString(), bundle, i2);
        BottomNavigationDataPreloadUtil.logD("start bottom navigation Activity end");
    }
}
