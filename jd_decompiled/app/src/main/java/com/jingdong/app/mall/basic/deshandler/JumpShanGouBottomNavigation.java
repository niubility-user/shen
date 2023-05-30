package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkBottomNavigationHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_SSS_TAB_CONTAINER)
/* loaded from: classes19.dex */
public class JumpShanGouBottomNavigation extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkBottomNavigationHelper.startActivity(context, bundle, 0);
        c(context);
    }
}
