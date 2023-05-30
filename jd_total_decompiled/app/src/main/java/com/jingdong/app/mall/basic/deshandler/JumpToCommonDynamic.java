package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.lib.babel.task.viewkit.VKEventUtil;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkJDDynamicHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;

@Des(des = JumpUtil.VALUE_DES_JD_DYNAMIC)
/* loaded from: classes19.dex */
public class JumpToCommonDynamic extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        a(JumpToCommonDynamic jumpToCommonDynamic, Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if (JumpUtil.VALUE_DES_JD_DYNAMIC.equals(str)) {
                DeepLinkJDDynamicHelper.startDynamicActivity(this.a, this.b);
            }
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (TextUtils.equals(bundle.getString(VKEventUtil.JUMP_NEEDLOGIN, "0"), "1")) {
            DeepLinkLoginHelper.startLoginActivity(context, null, new a(this, context, bundle), JumpUtil.VALUE_DES_JD_DYNAMIC);
        } else {
            DeepLinkJDDynamicHelper.startDynamicActivity(context, bundle);
        }
        c(context);
    }
}
