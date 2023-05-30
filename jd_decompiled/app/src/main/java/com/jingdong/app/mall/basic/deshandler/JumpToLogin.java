package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.corelib.utils.Log;

@Des(des = JumpUtil.VALUE_DES_JDLOGIN)
/* loaded from: classes19.dex */
public class JumpToLogin extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("com.360buy:singleInstanceFlag", true);
        bundle2.putInt("com.360buy:navigationDisplayFlag", -1);
        if (bundle != null) {
            String string = bundle.getString("from");
            if (Log.D) {
                Log.d(this.a, "forwardLoginActivity from:" + string);
                Log.d(this.a, "forwardLoginActivity bundle:" + bundle);
            }
            if (!TextUtils.isEmpty(string) && "jdgame".equals(string)) {
                bundle2.putInt(LoginConstans.RESEND_FLAG, 2);
            }
            bundle2.putAll(bundle);
        }
        DeepLinkLoginHelper.startLoginActivity(context, bundle2);
        c(context);
    }
}
