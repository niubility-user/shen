package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtils;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.web.managers.WebPerfManager;

@Des(des = JumpUtil.VALUE_DES_JSHOP_SIGN_RANK)
/* loaded from: classes19.dex */
public class JumpToJshop_sign_rank extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        a(JumpToJshop_sign_rank jumpToJshop_sign_rank, Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("forwardSignRank".equals(str)) {
                ReactActivityUtils.startJDReactRankListActivity(this.a, this.b);
            }
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if ("mysign".equals(bundle.getString(WebPerfManager.PAGE_TYPE))) {
            DeepLinkLoginHelper.startLoginActivity(context, null, new a(this, context, bundle), "forwardSignRank");
            c(context);
            return;
        }
        ReactActivityUtils.startJDReactRankListActivity(context, bundle);
        c(context);
    }
}
