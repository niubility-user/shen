package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.FireEyeRearLinkUtils;

@Des(des = JumpUtil.VALUE_DES_REAR_LINK_LOGIN)
/* loaded from: classes19.dex */
public class JumpToRearLinkLogin extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        FireEyeRearLinkUtils.requestBenefit();
        c(context);
    }
}
