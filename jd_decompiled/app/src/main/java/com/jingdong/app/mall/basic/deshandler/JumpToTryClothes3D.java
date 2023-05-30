package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.deeplinkhelper.DeepLinkTryClothes3DHelper;

/* loaded from: classes19.dex */
public class JumpToTryClothes3D extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkTryClothes3DHelper.startTryClothes3D(context, bundle);
        c(context);
    }
}
