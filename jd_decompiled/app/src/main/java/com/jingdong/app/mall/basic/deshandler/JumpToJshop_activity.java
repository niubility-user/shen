package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;

@Des(des = "jshopActivity")
/* loaded from: classes19.dex */
public class JumpToJshop_activity extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkJShopHomeHelper.gotoJShopActivity(context, bundle);
        c(context);
    }
}
