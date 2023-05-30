package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkPersonalHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginUserHelper;

@Des(des = JumpUtil.VALUE_DES_PERSONAL_MORE_CHANNEL)
/* loaded from: classes19.dex */
public class JumpToPersonalMoreChannel extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f8054g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f8055h;

        a(Context context, Bundle bundle) {
            this.f8054g = context;
            this.f8055h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkPersonalHelper.startPersonalMoreChannelActivity(this.f8054g, this.f8055h);
            JumpToPersonalMoreChannel.this.c(this.f8054g);
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context instanceof IMyActivity) {
            LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new a(context, bundle));
        }
    }
}
