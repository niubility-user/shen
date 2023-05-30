package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkFitShoesHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;

@Des(des = JumpUtil.VALUE_DES_FIT_SHOES)
/* loaded from: classes19.dex */
public class JumpToFitShoes extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (Log.D) {
            Log.d(this.a, "forwardFitShoes");
        }
        DeepLinkFitShoesHelper.startFitShoes(context, bundle);
        c(context);
    }
}
