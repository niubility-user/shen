package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_MY_DNA)
/* loaded from: classes19.dex */
public class JumpToMy_dna extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context instanceof BaseActivity) {
            com.jingdong.app.mall.utils.ui.a.a((BaseActivity) context, "JDDna");
        }
        c(context);
    }
}
