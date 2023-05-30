package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkTryClothesHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;

@Des(des = JumpUtil.VALUE_DES_TRY_CLOTHES)
/* loaded from: classes19.dex */
public class JumpToTryClothes extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (Log.D) {
            Log.d(this.a, "forwardTryClothes");
        }
        DeepLinkTryClothesHelper.startTryClothes(context, bundle);
        c(context);
    }
}
