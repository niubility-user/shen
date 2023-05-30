package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.jingdong.common.navutils.NavCenter;
import com.jingdong.common.navutils.NavUri;

/* loaded from: classes5.dex */
public class DeepLinkMyCouponHelper {
    public static void startFetchListActivity(Context context) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_MY_COUPON_FETCH_LIST_ACTIVITY, null);
    }

    public static void startMyCouponActivity(Context context, Bundle bundle) {
        NavCenter.from(context).putBundle(bundle).navTo(new NavUri().scheme("https").host("home.m.jd.com").path("/wallet/coupons.action").build());
    }

    public static void startMyCouponActivityForResult(Context context, Bundle bundle, int i2) {
        Uri build = new NavUri().scheme("https").host("home.m.jd.com").path("/wallet/coupons.action").build();
        NavCenter from = NavCenter.from(context);
        from.setRequestCode(i2);
        from.putBundle(bundle).navTo(build);
    }
}
