package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

@Des(des = JumpUtil.VALUE_DES_MINI_PRODUCTDETAIL_DYN)
/* loaded from: classes19.dex */
public class JumpToMiniProductDetailDyn extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(JumpUtil.VALUE_DES_MINI_PRODUCTDETAIL_DYN);
        DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle);
        if (Log.D) {
            Log.d(this.a, "forward JumpToMiniProductdetail" + host.toString());
        }
        c(context);
    }
}
