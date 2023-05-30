package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;

@Des(des = JumpUtil.VAULE_DES_QQANDGAME_ORDERDETAIL)
/* loaded from: classes19.dex */
public class JumpToQqAndGameOrderDetail extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f8060g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f8061h;

        a(Context context, Bundle bundle) {
            this.f8060g = context;
            this.f8061h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkCommonHelper.startActivityDirect(this.f8060g, DeepLinkCommonHelper.HOST_GAMECHARGEDETAIL_ACTIVITY, this.f8061h);
            JumpToQqAndGameOrderDetail.this.c(this.f8060g);
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (LoginUserBase.hasLogin()) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_GAMECHARGEDETAIL_ACTIVITY, bundle);
            c(context);
            return;
        }
        LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new a(context, bundle));
    }
}
