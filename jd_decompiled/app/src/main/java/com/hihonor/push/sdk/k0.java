package com.hihonor.push.sdk;

import android.content.Context;
import com.hihonor.push.sdk.common.data.UpMsgType;
import java.util.concurrent.Callable;

/* loaded from: classes12.dex */
public class k0 implements Callable<Void> {

    /* renamed from: g */
    public final /* synthetic */ o0 f1100g;

    public k0(o0 o0Var) {
        this.f1100g = o0Var;
    }

    @Override // java.util.concurrent.Callable
    public Void call() throws Exception {
        o0 o0Var = this.f1100g;
        d dVar = o0Var.b;
        Context context = o0Var.a;
        dVar.getClass();
        try {
            n nVar = new n(UpMsgType.UNREGISTER_PUSH_TOKEN, null);
            nVar.f1126e = i.a();
            i.e(c1.f1081i.a(nVar));
            o.b.b(context, null);
            return null;
        } catch (Exception e2) {
            throw i.d(e2);
        }
    }
}
