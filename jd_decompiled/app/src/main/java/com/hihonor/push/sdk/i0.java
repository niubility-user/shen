package com.hihonor.push.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.hihonor.push.framework.aidl.entity.PushTokenResult;
import com.hihonor.push.sdk.common.data.DownMsgType;
import com.hihonor.push.sdk.common.data.UpMsgType;
import java.util.concurrent.Callable;

/* loaded from: classes12.dex */
public class i0 implements Callable<String> {

    /* renamed from: g */
    public final /* synthetic */ boolean f1097g;

    /* renamed from: h */
    public final /* synthetic */ o0 f1098h;

    public i0(o0 o0Var, boolean z) {
        this.f1098h = o0Var;
        this.f1097g = z;
    }

    @Override // java.util.concurrent.Callable
    public String call() throws Exception {
        this.f1098h.b.getClass();
        try {
            q qVar = new q(UpMsgType.REQUEST_PUSH_TOKEN, null);
            qVar.f1126e = i.a();
            String pushToken = ((PushTokenResult) i.e(c1.f1081i.a(qVar))).getPushToken();
            if (this.f1097g) {
                o0 o0Var = this.f1098h;
                o0Var.getClass();
                if (!TextUtils.isEmpty(pushToken)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("event_type", DownMsgType.RECEIVE_TOKEN);
                    bundle.putString("push_token", pushToken);
                    w wVar = new w();
                    Context context = o0Var.a;
                    try {
                        Intent intent = new Intent();
                        intent.setPackage(context.getPackageName());
                        intent.setAction("com.hihonor.push.action.MESSAGING_EVENT");
                        Context applicationContext = context.getApplicationContext();
                        wVar.f1133i = applicationContext;
                        wVar.f1132h = bundle;
                        applicationContext.bindService(intent, wVar, 1);
                    } catch (Exception e2) {
                        String str = "bind service failed." + e2.getMessage();
                    }
                }
            }
            return pushToken;
        } catch (Exception e3) {
            throw i.d(e3);
        }
    }
}
