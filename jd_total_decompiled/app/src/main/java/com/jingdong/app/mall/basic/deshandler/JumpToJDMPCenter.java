package com.jingdong.app.mall.basic.deshandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jd.manto.center.MantoNewCenterActivity;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;

@Des(des = "jdmpCenter")
/* loaded from: classes19.dex */
public class JumpToJDMPCenter extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f8044g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Intent f8045h;

        a(JumpToJDMPCenter jumpToJDMPCenter, Context context, Intent intent) {
            this.f8044g = context;
            this.f8045h = intent;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!(this.f8044g instanceof Activity)) {
                this.f8045h.addFlags(268435456);
            }
            this.f8044g.startActivity(this.f8045h);
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        a aVar = new a(this, context, new Intent(context, MantoNewCenterActivity.class));
        try {
            if (LoginUserBase.hasLogin()) {
                aVar.run();
            } else {
                LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, aVar);
            }
        } catch (Exception unused) {
        }
        c(context);
    }
}
