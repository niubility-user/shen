package com.hihonor.push.sdk;

/* loaded from: classes12.dex */
public class r0 implements Runnable {

    /* renamed from: g */
    public final /* synthetic */ a f1111g;

    /* renamed from: h */
    public final /* synthetic */ Object f1112h;

    public r0(o0 o0Var, a aVar, Object obj) {
        this.f1111g = aVar;
        this.f1112h = obj;
    }

    @Override // java.lang.Runnable
    public void run() {
        a aVar = this.f1111g;
        if (aVar != null) {
            aVar.onSuccess(this.f1112h);
        }
    }
}
