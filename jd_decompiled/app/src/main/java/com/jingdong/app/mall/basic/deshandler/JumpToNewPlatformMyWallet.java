package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkMyWalletHelper;

@Des(des = "platformwalletnew")
/* loaded from: classes19.dex */
public class JumpToNewPlatformMyWallet extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkMyWalletHelper.startPlatformWalletNewActivity(context, bundle);
        c(context);
    }
}
