package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VAULE_DES_ORDER_TRACE)
/* loaded from: classes19.dex */
public class JumpToOrder_trace extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("orderid");
            String string2 = bundle.getString("shareData");
            if (!TextUtils.isEmpty(string) || !TextUtils.isEmpty(string2)) {
                DeepLinkOrderCenterHelper.startLogistics(context, bundle);
            }
        }
        c(context);
    }
}
