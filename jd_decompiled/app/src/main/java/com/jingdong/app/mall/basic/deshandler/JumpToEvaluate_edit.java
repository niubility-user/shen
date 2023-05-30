package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkEvaluateCenterHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;

@Des(des = JumpUtil.VALUE_DES_EVALUATE_EDIT)
/* loaded from: classes19.dex */
public class JumpToEvaluate_edit extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ILogin {
        final /* synthetic */ Bundle a;
        final /* synthetic */ Context b;

        a(JumpToEvaluate_edit jumpToEvaluate_edit, Bundle bundle, Context context) {
            this.a = bundle;
            this.b = context;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            Intent intent = new Intent();
            this.a.putBoolean("fromOpenApp", true);
            intent.putExtras(this.a);
            DeepLinkEvaluateCenterHelper.startEvaluateEdit(this.b, intent.getExtras());
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkLoginHelper.startLoginActivity(context, bundle, new a(this, bundle, context), "forwardEvaluateEdit");
        c(context);
    }
}
