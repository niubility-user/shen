package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.CommonBridge;

@Des(des = "lottery,caipiao")
/* loaded from: classes19.dex */
public class JumpToLottery extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context instanceof BaseActivity) {
            CommonBridge.forwardWebActivity((BaseActivity) context, JumpUtil.VALUE_DES_LOTTERY);
        }
        c(context);
    }
}
