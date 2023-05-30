package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkJDpaySdkHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.utils.pay.JumpUtils;

@Des(des = JumpUtil.VAULE_DES_JDPAY_SETTING)
/* loaded from: classes19.dex */
public class JumpToJDPaySetting extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        a(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("JumpToJDPaySetting".equals(str)) {
                JumpToJDPaySetting.this.t(this.a, this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(Context context, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_PAY_SETTING");
        bundle2.putString(JDPayApiKey.JDPAY_SESSION_KEY, UserUtil.getWJLoginHelper().getA2());
        bundle2.putString(JumpUtils.JDPAY_CODE_SOURCE, "jdmall");
        bundle2.putString(JumpUtils.ACCOUNT_MODE, "Native");
        if (bundle != null) {
            bundle2.putString("BIZ_SOURCE", bundle.getString("bizSource"));
        }
        bundle2.putBoolean("JDPAY_TOAST_PRINT", true);
        try {
            DeeplinkJDpaySdkHelper.startJDPayActivity(context, bundle2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (UserUtil.getWJLoginHelper().hasLogin()) {
            t(context, bundle);
        } else {
            DeepLinkLoginHelper.startLoginActivity(context, null, new a(context, bundle), "JumpToJDPaySetting");
        }
        c(context);
    }
}
