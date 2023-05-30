package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_MIAOSHA_MYCONCERN)
/* loaded from: classes19.dex */
public class JumpToMiaosha_myconcern extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        bundle.putString("url", "https://my-seckill-attention-pro.pf.jd.com");
        bundle.putString("des", "m");
        f(context, bundle);
    }
}
