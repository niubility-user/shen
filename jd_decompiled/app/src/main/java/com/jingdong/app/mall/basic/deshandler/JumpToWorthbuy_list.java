package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkWorthbuyHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_WORTHBUY_LIST)
/* loaded from: classes19.dex */
public class JumpToWorthbuy_list extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle == null) {
            c(context);
            return;
        }
        bundle.putString("channelTag", DeepLinkWorthbuyHelper.HOST_WORTHBUY_LIST);
        DeepLinkWorthbuyHelper.startWorthbuyActivity(context, bundle);
        c(context);
    }
}
