package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopCustomHelper;

@Des(des = "storetrend,native_storetrend")
/* loaded from: classes19.dex */
public class JumpToStorerend extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkJShopCustomHelper.startJShopCustomMainPage(context, bundle);
        c(context);
    }
}
