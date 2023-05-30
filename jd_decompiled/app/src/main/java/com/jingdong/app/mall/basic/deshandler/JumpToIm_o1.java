package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;

@Des(des = JumpUtil.VALUE_DES_IM_O1)
/* loaded from: classes19.dex */
public class JumpToIm_o1 extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        a(JumpToIm_o1 jumpToIm_o1, Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("forwardIMO1".equals(str)) {
                com.jingdong.app.mall.j.a.e(this.a, this.b);
            }
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkLoginHelper.startLoginActivity(context, null, new a(this, context, bundle), "forwardIMO1");
        c(context);
    }
}
