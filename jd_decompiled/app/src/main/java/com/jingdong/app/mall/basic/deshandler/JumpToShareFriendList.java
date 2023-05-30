package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkFriendHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserHelper;

@Des(des = "showsharefriendlist")
/* loaded from: classes19.dex */
public class JumpToShareFriendList extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f8067g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f8068h;

        a(Context context, Bundle bundle) {
            this.f8067g = context;
            this.f8068h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkFriendHelper.showShareFriendList(this.f8067g, this.f8068h);
            JumpToShareFriendList.this.c(this.f8067g);
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context instanceof IMyActivity) {
            LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new a(context, bundle));
        }
    }
}
