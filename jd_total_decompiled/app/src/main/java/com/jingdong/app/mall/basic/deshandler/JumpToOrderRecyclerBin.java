package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_ORDER_RECYCLE)
/* loaded from: classes19.dex */
public class JumpToOrderRecyclerBin extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle != null) {
            DeepLinkOrderCenterHelper.startOrderRecycle(context, bundle);
        }
        c(context);
    }
}
