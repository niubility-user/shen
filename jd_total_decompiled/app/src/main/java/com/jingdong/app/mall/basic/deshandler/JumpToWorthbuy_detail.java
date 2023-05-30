package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkWorthbuyHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_WORTHBUY_DETAIL)
/* loaded from: classes19.dex */
public class JumpToWorthbuy_detail extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkWorthbuyHelper.startWorthbuyGoodsDetailActivity(context, bundle);
        c(context);
    }
}
