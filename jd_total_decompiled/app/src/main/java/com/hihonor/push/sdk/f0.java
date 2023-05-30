package com.hihonor.push.sdk;

import android.content.Context;
import java.lang.ref.WeakReference;

/* loaded from: classes12.dex */
public class f0 {

    /* renamed from: e */
    public static final f0 f1091e = new f0();
    public WeakReference<Context> a;
    public volatile boolean b = false;

    /* renamed from: c */
    public volatile boolean f1092c = false;
    public o0 d;

    /* loaded from: classes12.dex */
    public class a implements Runnable {

        /* renamed from: g */
        public final /* synthetic */ Runnable f1093g;

        /* renamed from: h */
        public final /* synthetic */ com.hihonor.push.sdk.a f1094h;

        public a(Runnable runnable, com.hihonor.push.sdk.a aVar) {
            f0.this = r1;
            this.f1093g = runnable;
            this.f1094h = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (f0.this.b) {
                this.f1093g.run();
                return;
            }
            com.hihonor.push.sdk.a aVar = this.f1094h;
            if (aVar != null) {
                com.hihonor.push.sdk.b0.a aVar2 = com.hihonor.push.sdk.b0.a.ERROR_NOT_INITIALIZED;
                aVar.onFailure(aVar2.getErrorCode(), aVar2.getMessage());
            }
        }
    }

    public Context a() {
        return this.a.get();
    }

    public final void b(Runnable runnable, com.hihonor.push.sdk.a<?> aVar) {
        h.b(new a(runnable, aVar));
    }

    public boolean c(Context context) {
        return com.hihonor.push.sdk.b0.a.SUCCESS.statusCode == i.m(context);
    }
}
