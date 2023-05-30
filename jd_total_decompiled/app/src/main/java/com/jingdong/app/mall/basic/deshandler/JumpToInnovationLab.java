package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkUserManagerHelper;

@Des(des = "innovationLab")
/* loaded from: classes19.dex */
public class JumpToInnovationLab extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkUserManagerHelper.startInnovationLabActivity(context, bundle);
        c(context);
    }
}
