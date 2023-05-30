package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkMyWalletHelper;

@Des(des = "myPlatformWallet")
/* loaded from: classes19.dex */
public class JumpToPlatformMyWallet extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkMyWalletHelper.startPlatformWalletActivity(context, bundle);
        c(context);
    }
}
