package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.nfc.NfcIntentHandleActivity;
import com.jingdong.app.mall.nfc.d;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ICancelLogin;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.login.LoginUserHelper;

@Des(des = JumpUtil.VALUE_DES_WJ)
/* loaded from: classes19.dex */
public class JumpToWJ extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ICancelLogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f8070c;

        a(Context context, Bundle bundle, String str) {
            this.a = context;
            this.b = bundle;
            this.f8070c = str;
        }

        @Override // com.jingdong.common.login.ICancelLogin
        public void onCancel(String str) {
            JumpToWJ.u(this.a);
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            JumpToWJ.this.v((BaseActivity) this.a, this.b);
            d.d(this.a, this.f8070c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void u(Context context) {
        if (context == null || !(context instanceof NfcIntentHandleActivity)) {
            return;
        }
        ((NfcIntentHandleActivity) context).finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(BaseActivity baseActivity, Bundle bundle) {
        String str;
        String str2 = "";
        try {
            str = bundle.getString("skuId");
        } catch (Exception e2) {
            e = e2;
            str = "";
        }
        try {
            str2 = bundle.getString("shopId");
        } catch (Exception e3) {
            e = e3;
            if (Log.D) {
                Log.d(this.a, "forwardShoppingCart skuId : " + str);
                Log.d(this.a, "forwardShoppingCart packsId : ");
                e.printStackTrace();
            }
            d.h(baseActivity, str, str2);
        }
        d.h(baseActivity, str, str2);
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("skuId");
        String str = "JumpToWJ skuId: " + string;
        if (LoginUserHelper.getInstance().getLoginUser().hasLogin()) {
            v((BaseActivity) context, bundle);
            d.d(context, string);
            return;
        }
        DeepLinkLoginHelper.startLoginActivity(context, null, new a(context, bundle, string), this.a);
    }
}
