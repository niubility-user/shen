package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkNewProductHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_NEW_PRODUCT_PAGE)
/* loaded from: classes19.dex */
public class JumpToNewProductPage extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkNewProductHelper.startNewProductPageActivity(context, bundle);
        c(context);
    }
}
