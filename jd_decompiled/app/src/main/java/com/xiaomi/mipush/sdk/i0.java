package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class i0 extends ContentObserver {
    final /* synthetic */ f0 a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i0(f0 f0Var, Handler handler) {
        super(handler);
        this.a = f0Var;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        Context context;
        Integer num;
        Context context2;
        Context context3;
        f0 f0Var = this.a;
        context = f0Var.b;
        f0Var.f18382k = Integer.valueOf(com.xiaomi.push.service.p0.c(context).a());
        num = this.a.f18382k;
        if (num.intValue() != 0) {
            context2 = this.a.b;
            context2.getContentResolver().unregisterContentObserver(this);
            context3 = this.a.b;
            if (com.xiaomi.push.j0.p(context3)) {
                this.a.R();
            }
        }
    }
}
