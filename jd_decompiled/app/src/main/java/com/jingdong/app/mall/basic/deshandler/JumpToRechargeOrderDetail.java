package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;

@Des(des = JumpUtil.VAULE_DES_RECHARGE_ORDERDETAIL)
/* loaded from: classes19.dex */
public class JumpToRechargeOrderDetail extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f8064g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f8065h;

        a(Context context, Bundle bundle) {
            this.f8064g = context;
            this.f8065h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkCommonHelper.startActivityDirect(this.f8064g, DeepLinkCommonHelper.HOST_PHONECHARGEORDERDETAIL_ACTIVITY, this.f8065h);
            JumpToRechargeOrderDetail.this.c(this.f8064g);
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (LoginUserBase.hasLogin()) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_PHONECHARGEORDERDETAIL_ACTIVITY, bundle);
            c(context);
            return;
        }
        LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new a(context, bundle));
    }
}
