package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkChargeHelper;

@Des(des = "recharge,chongzhi")
/* loaded from: classes19.dex */
public class JumpToRecharge extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkChargeHelper.startPhoneChargeActivity(context, bundle);
        c(context);
    }
}
