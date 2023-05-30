package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkSettingHelper;

@Des(des = "feedback")
/* loaded from: classes19.dex */
public class JumpToFeedback extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkSettingHelper.startFeedbackActivity(context, bundle);
        c(context);
    }
}
