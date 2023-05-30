package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;

@Des(des = JumpUtil.VALUE_DES_SCAN_LOGIN)
/* loaded from: classes19.dex */
public class JumpToScan_login extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ILogin {
        final /* synthetic */ Bundle a;
        final /* synthetic */ Context b;

        a(JumpToScan_login jumpToScan_login, Bundle bundle, Context context) {
            this.a = bundle;
            this.b = context;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("forwardScanCodeActivity".equals(str)) {
                Bundle bundle = this.a;
                if (bundle != null) {
                    bundle.putBoolean("fromOpenApp", true);
                }
                DeepLinkLoginHelper.startScanLoginActivity(this.b, this.a);
            }
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkLoginHelper.startLoginActivity(context, null, new a(this, bundle, context), "forwardScanCodeActivity");
        c(context);
    }
}
