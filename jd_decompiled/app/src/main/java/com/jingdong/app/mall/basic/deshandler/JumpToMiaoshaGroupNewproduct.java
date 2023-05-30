package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkMiaoShaHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_GROUP_MIAOSHA_NEWPRODUCT)
/* loaded from: classes19.dex */
public class JumpToMiaoshaGroupNewproduct extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkMiaoShaHelper.startMiaoShaGroupNewProduct(context, bundle);
        c(context);
    }
}
