package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkArFaceHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

@Des(des = JumpUtil.VALUE_DES_ARFACE)
/* loaded from: classes19.dex */
public class JumpToArFace extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if ("1".equals(s(bundle, "arface_plus"))) {
            DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host("arfaceplusactivity");
            DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle);
            if (Log.D) {
                Log.d(this.a, "openapp forward Jump to ArFacePlusActivity:" + host.toString());
            }
        } else {
            DeepLinkArFaceHelper.startArFace(context, bundle);
            if (Log.D) {
                Log.d(this.a, "openapp forward Jump to ArFaceActivity");
            }
        }
        c(context);
    }

    public String s(Bundle bundle, String str) {
        Object obj = bundle.get(str);
        return obj == null ? "" : obj.toString();
    }
}
