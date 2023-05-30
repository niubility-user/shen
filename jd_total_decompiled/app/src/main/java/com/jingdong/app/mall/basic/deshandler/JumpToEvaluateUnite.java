package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkEvaluateCenterHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_EVALUATE_CENTER_COMMENT_AND_SHARE)
/* loaded from: classes19.dex */
public class JumpToEvaluateUnite extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkEvaluateCenterHelper.startEvaluateUnite(context, bundle);
        c(context);
    }
}
