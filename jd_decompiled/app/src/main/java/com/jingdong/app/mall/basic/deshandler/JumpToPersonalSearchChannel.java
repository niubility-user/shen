package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkPersonalHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginUserHelper;

@Des(des = JumpUtil.VALUE_DES_PERSONAL_SEARCH_CHANNEL)
/* loaded from: classes19.dex */
public class JumpToPersonalSearchChannel extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f8057g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f8058h;

        a(Context context, Bundle bundle) {
            this.f8057g = context;
            this.f8058h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkPersonalHelper.startPersonalSearchChannelActivity(this.f8057g, this.f8058h);
            JumpToPersonalSearchChannel.this.c(this.f8057g);
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context instanceof IMyActivity) {
            LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new a(context, bundle));
        }
    }
}
