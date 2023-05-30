package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkSettingHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_PLATFORM_FEEDBACK)
/* loaded from: classes19.dex */
public class JumpToPlatformFeedback extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkSettingHelper.startPlatformFeedBackActivity(context, bundle);
        c(context);
    }
}
