package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLiveHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.HOST_VIDEO_PRODUCT_RECOMMEND)
/* loaded from: classes19.dex */
public class JumpToVideoProductRecommend extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkLiveHelper.startVideoProductRecommendActivity(context, bundle);
        c(context);
    }
}
