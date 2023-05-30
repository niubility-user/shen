package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class g0 extends Handler {
    final /* synthetic */ f0 a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g0(f0 f0Var, Looper looper) {
        super(looper);
        this.a = f0Var;
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        Context context;
        Context context2;
        Context context3;
        Context context4;
        Context context5;
        HashMap<String, String> e2;
        f0 f0Var;
        Context context6;
        Context context7;
        Context context8;
        Context context9;
        Context context10;
        Context context11;
        Context context12;
        f0 f0Var2;
        Context context13;
        Context context14;
        if (message.what != 19) {
            return;
        }
        String str = (String) message.obj;
        int i2 = message.arg1;
        synchronized (x.class) {
            context = this.a.b;
            if (x.b(context).f(str)) {
                context2 = this.a.b;
                if (x.b(context2).a(str) < 10) {
                    String string = message.getData() != null ? message.getData().getString("third_sync_reason") : "";
                    l0 l0Var = l0.DISABLE_PUSH;
                    if (l0Var.ordinal() == i2) {
                        context14 = this.a.b;
                        if ("syncing".equals(x.b(context14).c(l0Var))) {
                            f0Var2 = this.a;
                            f0Var2.F(str, l0Var, true, null);
                            context13 = this.a.b;
                            x.b(context13).g(str);
                        }
                    }
                    l0Var = l0.ENABLE_PUSH;
                    if (l0Var.ordinal() == i2) {
                        context12 = this.a.b;
                        if ("syncing".equals(x.b(context12).c(l0Var))) {
                            f0Var2 = this.a;
                            f0Var2.F(str, l0Var, true, null);
                            context13 = this.a.b;
                            x.b(context13).g(str);
                        }
                    }
                    l0 l0Var2 = l0.UPLOAD_HUAWEI_TOKEN;
                    if (l0Var2.ordinal() == i2) {
                        context10 = this.a.b;
                        if ("syncing".equals(x.b(context10).c(l0Var2))) {
                            context11 = this.a.b;
                            e2 = v0.e(context11, r0.ASSEMBLE_PUSH_HUAWEI);
                            e2.put("third_sync_reason", string);
                            f0Var = this.a;
                            f0Var.F(str, l0Var2, false, e2);
                            context13 = this.a.b;
                            x.b(context13).g(str);
                        }
                    }
                    l0 l0Var3 = l0.UPLOAD_FCM_TOKEN;
                    if (l0Var3.ordinal() == i2) {
                        context8 = this.a.b;
                        if ("syncing".equals(x.b(context8).c(l0Var3))) {
                            f0 f0Var3 = this.a;
                            context9 = f0Var3.b;
                            f0Var3.F(str, l0Var3, false, v0.e(context9, r0.ASSEMBLE_PUSH_FCM));
                            context13 = this.a.b;
                            x.b(context13).g(str);
                        }
                    }
                    l0Var2 = l0.UPLOAD_COS_TOKEN;
                    if (l0Var2.ordinal() == i2) {
                        context6 = this.a.b;
                        if ("syncing".equals(x.b(context6).c(l0Var2))) {
                            context7 = this.a.b;
                            e2 = v0.e(context7, r0.ASSEMBLE_PUSH_COS);
                            e2.put("third_sync_reason", string);
                            f0Var = this.a;
                            f0Var.F(str, l0Var2, false, e2);
                            context13 = this.a.b;
                            x.b(context13).g(str);
                        }
                    }
                    l0Var2 = l0.UPLOAD_FTOS_TOKEN;
                    if (l0Var2.ordinal() == i2) {
                        context4 = this.a.b;
                        if ("syncing".equals(x.b(context4).c(l0Var2))) {
                            context5 = this.a.b;
                            e2 = v0.e(context5, r0.ASSEMBLE_PUSH_FTOS);
                            e2.put("third_sync_reason", string);
                            f0Var = this.a;
                            f0Var.F(str, l0Var2, false, e2);
                        }
                    }
                    context13 = this.a.b;
                    x.b(context13).g(str);
                } else {
                    context3 = this.a.b;
                    x.b(context3).h(str);
                }
            }
        }
    }
}
