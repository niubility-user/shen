package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkDiscoveryHelper;

@Des(des = "faxian")
/* loaded from: classes19.dex */
public class JumpToFaxian4event extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkDiscoveryHelper.startDiscovery4EventActivity(context, bundle);
        c(context);
    }
}
