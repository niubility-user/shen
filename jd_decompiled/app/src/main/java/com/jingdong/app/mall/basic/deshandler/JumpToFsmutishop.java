package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_FSMUTISHOP)
/* loaded from: classes19.dex */
public class JumpToFsmutishop extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkProductListHelper.startMultiSellerActivity(context, bundle);
        c(context);
    }
}
