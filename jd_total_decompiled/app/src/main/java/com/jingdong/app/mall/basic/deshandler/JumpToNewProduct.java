package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkNewProductHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_NEWPRODUCT)
/* loaded from: classes19.dex */
public class JumpToNewProduct extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkNewProductHelper.startNewProductActivity(context, bundle);
        c(context);
    }
}
