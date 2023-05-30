package com.jingdong.jdsdk.d.c.a;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.login.ILogin;
import com.jingdong.sdk.platform.lib.openapi.login.ILoginApi;
import com.jingdong.sdk.platform.lib.openapi.login.ILoginCallBack;

/* loaded from: classes14.dex */
public class q implements ILoginApi {
    private static q a;

    /* loaded from: classes14.dex */
    class a implements ILogin {
        final /* synthetic */ ILoginCallBack a;

        a(q qVar, ILoginCallBack iLoginCallBack) {
            this.a = iLoginCallBack;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            ILoginCallBack iLoginCallBack = this.a;
            if (iLoginCallBack != null) {
                iLoginCallBack.onSuccess(str);
            }
        }
    }

    private q() {
    }

    public static synchronized q a() {
        q qVar;
        synchronized (q.class) {
            if (a == null) {
                a = new q();
            }
            qVar = a;
        }
        return qVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.login.ILoginApi
    public void startLoginActivity(Context context, Bundle bundle, ILoginCallBack iLoginCallBack, String str) {
        DeepLinkLoginHelper.startLoginActivity(context, bundle, new a(this, iLoginCallBack), str);
    }
}
