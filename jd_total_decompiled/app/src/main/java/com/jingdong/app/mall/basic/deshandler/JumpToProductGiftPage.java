package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_PRODUCT_GIFT_PAGE)
/* loaded from: classes19.dex */
public class JumpToProductGiftPage extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context == null) {
            return;
        }
        DeepLinkCommonHelper.startActivityDirect(context, "giftpage", bundle);
        c(context);
    }
}
