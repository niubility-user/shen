package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtils;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_YIYUANQIANGBAO)
/* loaded from: classes19.dex */
public class JumpToYiyuanqiangbao extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        ReactActivityUtils.startJDReactOneTreasureActivity(context, new Intent());
        c(context);
    }
}
