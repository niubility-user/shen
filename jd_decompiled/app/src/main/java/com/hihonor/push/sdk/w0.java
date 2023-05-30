package com.hihonor.push.sdk;

/* loaded from: classes12.dex */
public class w0 implements Runnable {

    /* renamed from: g */
    public final /* synthetic */ e f1134g;

    /* renamed from: h */
    public final /* synthetic */ y0 f1135h;

    public w0(y0 y0Var, e eVar) {
        this.f1135h = y0Var;
        this.f1134g = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.f1135h.f1138c) {
            Object obj = this.f1135h.b;
            if (obj != null) {
                this.f1134g.d();
                ((a1) obj).a.countDown();
            }
        }
    }
}
