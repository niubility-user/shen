package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.jdmiaosha.preload.DoubleTabDataPreloadUtil;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkWebContainerHelper {
    public static final String HOST_WEB_CONTAINER_ACTIVITY = "webcontaineractivity";
    public static final String HOST_WEB_CONTAINER_ACTIVITY_OPT = "doubletabcontainerActivity";
    private static final String SWITCH_OPEN = "1";
    private static final String TAG = "DeepLinkWebContainer";

    private static boolean isOptSwitchOpen() {
        return JDMobileConfig.getInstance().getConfig("JDMiaoSha", "jdzctabopt", "switchType").equals("1");
    }

    private static boolean isTabLinkSwitchOpen() {
        return JDMobileConfig.getInstance().getConfig("JDMiaoSha", "jdzctabopt", "tabLinkSwitch").equals("1");
    }

    public static void startActivity(Context context, Bundle bundle, int i2) {
        DoubleTabDataPreloadUtil.logD("open web container start -----------");
        if (isOptSwitchOpen()) {
            DoubleTabDataPreloadUtil.logD("open new web container");
            DoubleTabDataPreloadUtil.handlePreload(context, bundle, isTabLinkSwitchOpen());
            startActivity(context, bundle, i2, HOST_WEB_CONTAINER_ACTIVITY_OPT);
            DoubleTabDataPreloadUtil.logD("open new web container end ----------");
            return;
        }
        DoubleTabDataPreloadUtil.logD("open old web container");
        startActivity(context, bundle, i2, HOST_WEB_CONTAINER_ACTIVITY);
        DoubleTabDataPreloadUtil.logD("open old web container end ----------");
    }

    private static void startActivity(Context context, Bundle bundle, int i2, String str) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(str).toString(), bundle, i2);
    }
}
