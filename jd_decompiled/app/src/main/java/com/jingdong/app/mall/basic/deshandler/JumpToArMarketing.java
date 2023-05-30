package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

@Des(des = "armarketing")
/* loaded from: classes19.dex */
public class JumpToArMarketing extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host("armarketingactivity");
        DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle);
        if (Log.D) {
            Log.d(this.a, "forward JumpToArMarketingActivity:" + host.toString());
        }
        c(context);
    }
}
