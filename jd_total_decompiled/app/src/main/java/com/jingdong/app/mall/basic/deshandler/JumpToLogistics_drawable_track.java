package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_ORDER_DRAWABLE_TRACK)
/* loaded from: classes19.dex */
public class JumpToLogistics_drawable_track extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle != null) {
            DeepLinkOrderCenterHelper.startOrderLogisticsDrawableTrack(context, bundle);
        }
        c(context);
    }
}
