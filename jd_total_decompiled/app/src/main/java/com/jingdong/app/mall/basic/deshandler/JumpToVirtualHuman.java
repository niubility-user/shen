package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

@Des(des = "virtualhuman")
/* loaded from: classes19.dex */
public class JumpToVirtualHuman extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(4 == bundle.getInt("channel", 1) ? "virtualhumantransparentactivity" : "virtualhumanactivity");
        DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle);
        if (Log.D) {
            Log.d(this.a, "forward virtualhuman:" + host.toString());
        }
        c(context);
    }
}
