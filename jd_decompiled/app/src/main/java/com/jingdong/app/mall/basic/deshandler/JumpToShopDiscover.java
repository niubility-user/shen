package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkShopDiscoverHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_FINDATTENTION_SHOPDISC)
/* loaded from: classes19.dex */
public class JumpToShopDiscover extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkShopDiscoverHelper.startShopDiscoverActivity(context, bundle);
        c(context);
    }
}
