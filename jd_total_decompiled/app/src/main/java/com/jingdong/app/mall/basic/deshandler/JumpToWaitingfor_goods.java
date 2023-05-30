package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.e;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;

@Des(des = JumpUtil.VALUE_DES_WAITING_GOODS)
/* loaded from: classes19.dex */
public class JumpToWaitingfor_goods extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        a(JumpToWaitingfor_goods jumpToWaitingfor_goods, Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("forwardWaitingForGoods".equals(str)) {
                DeepLinkOrderCenterHelper.startOrderList(this.a, this.b);
            }
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkLoginHelper.startLoginActivity(e.b().a(), null, new a(this, context, bundle), "forwardWaitingForGoods");
        c(context);
    }
}
