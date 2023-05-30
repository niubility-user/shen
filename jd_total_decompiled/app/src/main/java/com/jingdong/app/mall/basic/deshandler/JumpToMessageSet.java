package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.utils.JumpMessageActivityUtil;

@Des(des = JumpUtil.VALUE_DES_MESSAGE_SET)
/* loaded from: classes19.dex */
public class JumpToMessageSet extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ILogin {
        final /* synthetic */ Context a;

        a(JumpToMessageSet jumpToMessageSet, Context context) {
            this.a = context;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("messageSet".equals(str)) {
                JumpMessageActivityUtil.jumpToSetting(this.a);
            }
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkLoginHelper.startLoginActivity(context, null, new a(this, context), "messageSet");
        c(context);
    }
}
