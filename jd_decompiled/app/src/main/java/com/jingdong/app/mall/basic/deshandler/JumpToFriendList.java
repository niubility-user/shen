package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkFriendHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginUserHelper;

@Des(des = JumpUtil.VALUE_DES_FRIEND_LIST)
/* loaded from: classes19.dex */
public class JumpToFriendList extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f8035g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f8036h;

        a(Context context, Bundle bundle) {
            this.f8035g = context;
            this.f8036h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkFriendHelper.startFriendListActivity(this.f8035g, this.f8036h);
            JumpToFriendList.this.c(this.f8035g);
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context instanceof IMyActivity) {
            LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new a(context, bundle));
        }
    }
}
