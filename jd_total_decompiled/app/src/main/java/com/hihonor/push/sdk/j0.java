package com.hihonor.push.sdk;

/* loaded from: classes12.dex */
public class j0<TResult> {
    public final e<TResult> a = new e<>();

    public void a(Exception exc) {
        e<TResult> eVar = this.a;
        synchronized (eVar.a) {
            if (!eVar.b) {
                eVar.b = true;
                eVar.d = exc;
                eVar.a.notifyAll();
                eVar.b();
            }
        }
    }

    public void b(TResult tresult) {
        e<TResult> eVar = this.a;
        synchronized (eVar.a) {
            if (!eVar.b) {
                eVar.b = true;
                eVar.f1089c = tresult;
                eVar.a.notifyAll();
                eVar.b();
            }
        }
    }
}
