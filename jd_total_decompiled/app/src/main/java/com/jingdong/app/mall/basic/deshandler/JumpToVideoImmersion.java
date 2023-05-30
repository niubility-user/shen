package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLiveHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_VIDEO_IMMERSION)
/* loaded from: classes19.dex */
public class JumpToVideoImmersion extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Bundle bundle2;
        if (bundle != null && (bundle2 = bundle.getBundle("bundleOptions")) != null) {
            DeepLinkLiveHelper.startVideoImmersionActivity(context, bundle, bundle2);
            c(context);
            return;
        }
        DeepLinkLiveHelper.startVideoImmersionActivity(context, bundle);
        c(context);
    }
}
