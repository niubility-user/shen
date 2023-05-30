package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

@Des(des = JumpUtil.VALUE_DES_AR_FLOOR)
/* loaded from: classes19.dex */
public class JumpToArFloor extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host("productarflooractivity");
        DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle);
        if (Log.D) {
            Log.d(this.a, "forward JumpToArFloor" + host.toString());
        }
        c(context);
    }
}
