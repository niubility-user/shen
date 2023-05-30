package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.navutils.NavCenter;
import com.jingdong.common.navutils.NavUri;

/* loaded from: classes5.dex */
public class DeepLinkEnjoyBuyHelper {
    public static void startEnjoyBuyActivity(Context context, Bundle bundle) {
        NavCenter.from(context).putBundle(bundle).navTo(new NavUri().scheme("https").host("bean.m.jd.com").build());
    }

    public static void startEnjoyBuyBrandActivity(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_ENJOYBUY_BRAND_ACTIVITY, bundle);
    }
}
