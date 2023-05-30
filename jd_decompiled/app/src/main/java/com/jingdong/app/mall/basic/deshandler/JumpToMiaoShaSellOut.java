package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkMiaoShaHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_MIAOSHA_SELLOUT_ACTIVITY)
/* loaded from: classes19.dex */
public class JumpToMiaoShaSellOut extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkMiaoShaHelper.startMiaoShaSellOutActivity(context, bundle);
        c(context);
    }
}
