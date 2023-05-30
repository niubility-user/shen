package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLink3DProductHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;

@Des(des = JumpUtil.VALUE_DES_PRODUCT_THREED)
/* loaded from: classes19.dex */
public class JumpToProduct3d extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (Log.D) {
            Log.d(this.a, "forwardProduct3d");
        }
        DeepLink3DProductHelper.start3DProductDetail(context, bundle);
        c(context);
    }
}
