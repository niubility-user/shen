package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkWorthbuyHelper;

@Des(des = "worthbuyWishList")
/* loaded from: classes19.dex */
public class JumpToWorthBuyWishList extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkWorthbuyHelper.startWorthbuyWishListActivity(context, bundle);
        c(context);
    }
}
