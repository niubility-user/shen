package com.vivo.push;

import android.content.Context;

/* loaded from: classes11.dex */
public abstract class l implements Runnable {
    protected Context a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private o f18284c;

    public l(o oVar) {
        this.b = -1;
        this.f18284c = oVar;
        int b = oVar.b();
        this.b = b;
        if (b >= 0) {
            this.a = e.a().h();
            return;
        }
        throw new IllegalArgumentException("PushTask need a > 0 task id.");
    }

    public final int a() {
        return this.b;
    }

    protected abstract void a(o oVar);

    @Override // java.lang.Runnable
    public final void run() {
        Context context = this.a;
        if (context != null && !(this.f18284c instanceof com.vivo.push.b.n)) {
            com.vivo.push.util.p.a(context, "[\u6267\u884c\u6307\u4ee4]" + this.f18284c);
        }
        a(this.f18284c);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{");
        o oVar = this.f18284c;
        sb.append(oVar == null ? "[null]" : oVar.toString());
        sb.append("}");
        return sb.toString();
    }
}
