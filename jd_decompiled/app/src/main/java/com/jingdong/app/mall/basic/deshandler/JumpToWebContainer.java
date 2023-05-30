package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkWebContainerHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_ZC_PRESALE)
/* loaded from: classes19.dex */
public class JumpToWebContainer extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkWebContainerHelper.startActivity(context, bundle, 0);
        c(context);
    }
}
