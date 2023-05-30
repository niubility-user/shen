package com.jingdong.app.mall.basic.deshandler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VAULE_DES_JSHOP)
/* loaded from: classes19.dex */
public class JumpToJshop extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle.containsKey("fromManto") && bundle.getBoolean("fromManto", false) && (context instanceof Activity)) {
            DeepLinkJShopHomeHelper.gotoJShopHomeForResult((Activity) context, bundle, 12);
        } else {
            DeepLinkJShopHomeHelper.gotoJShopHome(context, bundle);
        }
        c(context);
    }
}
