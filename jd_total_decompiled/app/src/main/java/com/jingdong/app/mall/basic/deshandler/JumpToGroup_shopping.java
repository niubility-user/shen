package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.CommonBridge;

@Des(des = JumpUtil.VALUE_DES_GROUP_SHOPPING)
/* loaded from: classes19.dex */
public class JumpToGroup_shopping extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context instanceof BaseActivity) {
            CommonBridge.forwardWebActivity((BaseActivity) context, "tuan");
        }
        c(context);
    }
}
