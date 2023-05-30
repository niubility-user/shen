package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkMyStreetHelper;

@Des(des = "lookSimilar,recommend_Similar")
/* loaded from: classes19.dex */
public class JumpToLook_similar extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context instanceof BaseActivity) {
            DeepLinkMyStreetHelper.startSimilarRecom(context, bundle);
        }
        c(context);
    }
}
