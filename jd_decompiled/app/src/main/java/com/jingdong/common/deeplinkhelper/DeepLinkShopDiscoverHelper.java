package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkShopDiscoverHelper {
    private static final String HOST_SHOP_DISCOVER = "ShopDiscoverActivity";

    public static void startShopDiscoverActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_SHOP_DISCOVER).toString(), bundle);
    }

    public static void startShopDiscoverActivity(Context context, String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putString("shopId", str);
        bundle.putString("venderId", str2);
        bundle.putString("logoURI", str3);
        bundle.putString("shopName", str4);
        startShopDiscoverActivity(context, bundle);
    }
}
