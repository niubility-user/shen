package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkJDpayQuickPassSdkHelper;
import com.jingdong.common.entity.JDQuickPassParam;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ICancelLogin;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.UserUtil;

@Des(des = JumpUtil.VALUE_DES_QUICK_PASS)
/* loaded from: classes19.dex */
public class JumpToQuickpass extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ICancelLogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        a(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ICancelLogin
        public void onCancel(String str) {
            JumpToQuickpass.this.c(this.a);
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("JumpToQuickpass".equals(str)) {
                JumpToQuickpass.this.t(this.a, this.b);
            }
            JumpToQuickpass.this.c(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(Context context, Bundle bundle) {
        try {
            JDQuickPassParam jDQuickPassParam = new JDQuickPassParam();
            String string = bundle.getString("source", "");
            if (TextUtils.isEmpty(string)) {
                jDQuickPassParam.source = "jdmall_null";
            } else {
                jDQuickPassParam.source = "jdmall_" + string;
            }
            jDQuickPassParam.mode = JDQuickPassParam.JD_QUICK_PASS_MODE_NATIVE;
            jDQuickPassParam.sessionKey = UserUtil.getWJLoginHelper().getA2();
            jDQuickPassParam.pin = LoginUserBase.getUserPin();
            String jSONString = JDJSON.toJSONString(jDQuickPassParam);
            bundle.putString("JDPAY_ENTRANCE_VERIFY", JDQuickPassParam.JD_PAY_QUICK_PASS);
            bundle.putString(JDQuickPassParam.JD_QUICK_PASS_PARAM, jSONString);
            DeeplinkJDpayQuickPassSdkHelper.startJDPayActivity(context, bundle);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (LoginUserBase.hasLogin()) {
            t(context, bundle);
            c(context);
            return;
        }
        DeepLinkLoginHelper.startLoginActivity(context, null, new a(context, bundle), "JumpToQuickpass");
    }
}
