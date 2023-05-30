package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;

@Des(des = "oftenBuy")
/* loaded from: classes19.dex */
public class JumpToOften_buy extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkOrderCenterHelper.startOftenBuyList(context, bundle);
        c(context);
    }
}
