package com.jingdong.app.mall.basic.deshandler;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginUserHelper;

@Des(des = JumpUtil.VALUE_DES_FAMILY_HELPER)
/* loaded from: classes19.dex */
public class JumpToJDFamilyHelper extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Bundle f8041g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Context f8042h;

        a(Bundle bundle, Context context) {
            this.f8041g = bundle;
            this.f8042h = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Intent intent = new Intent();
                intent.putExtras(this.f8041g);
                intent.setComponent(new ComponentName(this.f8042h, "com.jingdong.app.mall.bundle.familyhelper.FamilyHelperActivity"));
                this.f8042h.startActivity(intent);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            JumpToJDFamilyHelper.this.c(this.f8042h);
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new a(bundle, context));
    }
}
