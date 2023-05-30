package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;

@Des(des = JumpUtil.VAULE_DES_ORDER_LIST)
/* loaded from: classes19.dex */
public class JumpToOrder_list extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        a(JumpToOrder_list jumpToOrder_list, Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("forwardOrderList".equals(str)) {
                DeepLinkOrderCenterHelper.startOrderList(this.a, this.b);
            }
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkLoginHelper.startLoginActivity(context, null, new a(this, context, bundle), "forwardOrderList");
        c(context);
    }
}
