package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkEvaluateCenterHelper;

@Des(des = "shareOrderUgcContent")
/* loaded from: classes19.dex */
public class JumpToEvaluateUgcContent extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkEvaluateCenterHelper.startUgcContentActivity(context, bundle);
        c(context);
    }
}
