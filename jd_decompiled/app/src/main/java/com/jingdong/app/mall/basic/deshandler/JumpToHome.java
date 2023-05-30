package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_APPHOME)
/* loaded from: classes19.dex */
public class JumpToHome extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        bundle.putInt(RemoteMessageConst.TO, 0);
        g(context, bundle);
    }
}
