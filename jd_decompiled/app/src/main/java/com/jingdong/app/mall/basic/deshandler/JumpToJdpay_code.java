package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.nfc.d;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkPayCodeHelper;
import com.jingdong.common.entity.JDPayCodeParam;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.corelib.utils.Log;

@Des(des = JumpUtil.VALUE_DES_JDPAY_CODE)
/* loaded from: classes19.dex */
public class JumpToJdpay_code extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ILogin {
        final /* synthetic */ Bundle a;
        final /* synthetic */ Context b;

        a(Bundle bundle, Context context) {
            this.a = bundle;
            this.b = context;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            String userPin = LoginUserBase.getUserPin();
            if (TextUtils.isEmpty(userPin)) {
                return;
            }
            try {
                JDPayCodeParam jDPayCodeParam = new JDPayCodeParam();
                jDPayCodeParam.jdId = userPin;
                jDPayCodeParam.mode = "Native";
                jDPayCodeParam.token = UserUtil.getWJLoginHelper().getA2();
                jDPayCodeParam.source = "jdmall";
                jDPayCodeParam.extraInfo = this.a.getString("extraInfo");
                this.a.putString("businessType", "JDPayPaymentCode");
                this.a.putString("param", JDJSON.toJSONString(jDPayCodeParam));
                if (Log.D) {
                    Log.d(JumpToJdpay_code.this.a, "forwardJdPayCode() -->>param: " + this.a.getString("param"));
                }
                DeepLinkPayCodeHelper.startPayCodeActivity(this.b, this.a);
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(JumpToJdpay_code.this.a, " jumpPaymentCode.Exception-->>" + e2.getMessage());
                }
            }
        }
    }

    private void s(Context context) {
        d.e(context);
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkLoginHelper.startLoginActivity(context, bundle, new a(bundle, context), "forwardJdPayCode");
        c(context);
        s(context);
    }
}
