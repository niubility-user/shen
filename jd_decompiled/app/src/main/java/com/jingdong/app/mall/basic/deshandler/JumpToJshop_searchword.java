package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_JSHOP_GUESS_WORD)
/* loaded from: classes19.dex */
public class JumpToJshop_searchword extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkJShopHomeHelper.gotoJshopSearchWordActivity(context, bundle);
        c(context);
    }
}
