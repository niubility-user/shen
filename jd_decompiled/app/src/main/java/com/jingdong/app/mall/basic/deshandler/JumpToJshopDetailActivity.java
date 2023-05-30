package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

@Des(des = JumpUtil.VALUE_DES_JSHOP_DETAIL)
/* loaded from: classes19.dex */
public class JumpToJshopDetailActivity extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_DES_JSHOP_DETAIL).toString(), bundle);
        c(context);
    }
}
