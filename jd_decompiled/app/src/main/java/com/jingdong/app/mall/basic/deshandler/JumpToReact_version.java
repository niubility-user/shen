package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtils;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VAULE_DES_REACTNATIVE_VERSION)
/* loaded from: classes19.dex */
public class JumpToReact_version extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        ReactActivityUtils.startJDReactVersionActivity(context);
        c(context);
    }
}
