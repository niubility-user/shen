package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkDiscoveryHelper;

@Des(des = "faxian_article")
/* loaded from: classes19.dex */
public class JumpToFaxian_article extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkDiscoveryHelper.startDiscoveryArticleActivity(context, bundle);
        c(context);
    }
}
