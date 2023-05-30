package com.hihonor.push.sdk;

import android.content.Context;
import com.hihonor.push.sdk.common.data.ApiException;
import java.util.concurrent.Callable;

/* loaded from: classes12.dex */
public class o0 {
    public final Context a;
    public d b = new d();

    /* loaded from: classes12.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ Callable f1104g;

        /* renamed from: h  reason: collision with root package name */
        public final /* synthetic */ com.hihonor.push.sdk.a f1105h;

        public a(Callable callable, com.hihonor.push.sdk.a aVar) {
            this.f1104g = callable;
            this.f1105h = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Object call = this.f1104g.call();
                o0 o0Var = o0.this;
                com.hihonor.push.sdk.a aVar = this.f1105h;
                o0Var.getClass();
                h.b(new r0(o0Var, aVar, call));
            } catch (ApiException e2) {
                o0.a(o0.this, this.f1105h, e2.getErrorCode(), e2.getMessage());
            } catch (Exception unused) {
                o0 o0Var2 = o0.this;
                com.hihonor.push.sdk.a aVar2 = this.f1105h;
                com.hihonor.push.sdk.b0.a aVar3 = com.hihonor.push.sdk.b0.a.ERROR_INTERNAL_ERROR;
                o0.a(o0Var2, aVar2, aVar3.getErrorCode(), aVar3.getMessage());
            }
        }
    }

    public o0(Context context) {
        this.a = context;
    }

    public static void a(o0 o0Var, com.hihonor.push.sdk.a aVar, int i2, String str) {
        o0Var.getClass();
        h.b(new t0(o0Var, aVar, i2, str));
    }

    public final <T> void b(Callable<T> callable, com.hihonor.push.sdk.a<T> aVar) {
        a aVar2 = new a(callable, aVar);
        h hVar = h.d;
        if (hVar.b == null) {
            synchronized (hVar.f1096c) {
                if (hVar.b == null) {
                    hVar.b = hVar.c();
                }
            }
        }
        hVar.b.execute(aVar2);
    }
}
