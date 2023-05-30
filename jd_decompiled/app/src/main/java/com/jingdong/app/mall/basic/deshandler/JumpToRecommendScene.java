package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_RECOMMEND_SCENE_DETAIL)
/* loaded from: classes19.dex */
public class JumpToRecommendScene extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, "recommend_scene_activity", bundle);
        c(context);
    }
}
