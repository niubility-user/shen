package com.jingdong.jdsdk.d.c.a;

import com.jingdong.common.deeplinkhelper.DeepLinkFavouritesHelper;
import com.jingdong.sdk.platform.lib.openapi.favourites.IFavouritesHelper;

/* loaded from: classes14.dex */
public class l implements IFavouritesHelper {
    private static l a;

    private l() {
    }

    public static synchronized l a() {
        l lVar;
        synchronized (l.class) {
            if (a == null) {
                a = new l();
            }
            lVar = a;
        }
        return lVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.favourites.IFavouritesHelper
    public void setCartToFavorite(boolean z) {
        DeepLinkFavouritesHelper.isCartToFavorite = z;
    }
}
