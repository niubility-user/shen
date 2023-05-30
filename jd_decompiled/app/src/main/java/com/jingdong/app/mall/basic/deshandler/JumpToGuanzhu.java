package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_GUANZHU)
/* loaded from: classes19.dex */
public class JumpToGuanzhu extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        bundle.putString("des", JumpUtil.VALUE_DES_GUANZHU);
        f(context, bundle);
    }
}
