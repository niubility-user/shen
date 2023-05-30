package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.imhelper.DDParameterBuilder;
import com.jingdong.common.deeplinkhelper.imhelper.DeeplinkDongDongHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;

@Des(des = JumpUtil.VALUE_DES_IM)
/* loaded from: classes19.dex */
public class JumpToIm extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ILogin {
        final /* synthetic */ Bundle a;
        final /* synthetic */ Context b;

        a(JumpToIm jumpToIm, Bundle bundle, Context context) {
            this.a = bundle;
            this.b = context;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("forwardIM".equals(str)) {
                String str2 = null;
                try {
                    Bundle bundle = this.a;
                    if (bundle != null && bundle.containsKey("orgId")) {
                        str2 = this.a.getInt("orgId") + "";
                    }
                } catch (Exception unused) {
                }
                if (!TextUtils.isEmpty(str2)) {
                    DDParameterBuilder generateWithPin = DDParameterBuilder.generateWithPin();
                    if (generateWithPin != null) {
                        generateWithPin.addFrom(DDParameterBuilder.ACTION_BROADCAST_START_IM_PRIVATEORDERASK).addSkillID(String.valueOf(str2));
                    }
                    this.a.putAll(generateWithPin.getBundle());
                    DeeplinkDongDongHelper.getInstance().startDongDong(this.b, this.a);
                    return;
                }
                DDParameterBuilder generateWithPin2 = DDParameterBuilder.generateWithPin();
                if (generateWithPin2 != null) {
                    generateWithPin2.addFrom(DDParameterBuilder.ACTION_BROADCAST_START_IM_CUSTOMER_SERVICE_MANAGER);
                    this.a.putAll(generateWithPin2.getBundle());
                    DeeplinkDongDongHelper.getInstance().startDongDong(this.b, this.a);
                }
            }
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkLoginHelper.startLoginActivity(context, null, new a(this, bundle, context), "forwardIM");
        c(context);
    }
}
