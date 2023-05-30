package com.huawei.hmf.tasks.a;

import g.e.c.a.h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes12.dex */
public final class e<TResult> extends g.e.c.a.f<TResult> {
    private boolean b;

    /* renamed from: c */
    private volatile boolean f1166c;
    private TResult d;

    /* renamed from: e */
    private Exception f1167e;
    private final Object a = new Object();

    /* renamed from: f */
    private List<g.e.c.a.b<TResult>> f1168f = new ArrayList();

    private g.e.c.a.f<TResult> i(g.e.c.a.b<TResult> bVar) {
        boolean g2;
        synchronized (this.a) {
            g2 = g();
            if (!g2) {
                this.f1168f.add(bVar);
            }
        }
        if (g2) {
            bVar.onComplete(this);
        }
        return this;
    }

    private void p() {
        synchronized (this.a) {
            Iterator<g.e.c.a.b<TResult>> it = this.f1168f.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onComplete(this);
                } catch (RuntimeException e2) {
                    throw e2;
                } catch (Exception e3) {
                    throw new RuntimeException(e3);
                }
            }
            this.f1168f = null;
        }
    }

    @Override // g.e.c.a.f
    public final g.e.c.a.f<TResult> a(g.e.c.a.c<TResult> cVar) {
        m(h.b(), cVar);
        return this;
    }

    @Override // g.e.c.a.f
    public final g.e.c.a.f<TResult> b(g.e.c.a.d dVar) {
        n(h.b(), dVar);
        return this;
    }

    @Override // g.e.c.a.f
    public final g.e.c.a.f<TResult> c(g.e.c.a.e<TResult> eVar) {
        o(h.b(), eVar);
        return this;
    }

    @Override // g.e.c.a.f
    public final Exception d() {
        Exception exc;
        synchronized (this.a) {
            exc = this.f1167e;
        }
        return exc;
    }

    @Override // g.e.c.a.f
    public final TResult e() {
        TResult tresult;
        synchronized (this.a) {
            if (this.f1167e != null) {
                throw new RuntimeException(this.f1167e);
            }
            tresult = this.d;
        }
        return tresult;
    }

    @Override // g.e.c.a.f
    public final boolean f() {
        return this.f1166c;
    }

    @Override // g.e.c.a.f
    public final boolean g() {
        boolean z;
        synchronized (this.a) {
            z = this.b;
        }
        return z;
    }

    @Override // g.e.c.a.f
    public final boolean h() {
        boolean z;
        synchronized (this.a) {
            z = this.b && !f() && this.f1167e == null;
        }
        return z;
    }

    public final void j(Exception exc) {
        synchronized (this.a) {
            if (this.b) {
                return;
            }
            this.b = true;
            this.f1167e = exc;
            this.a.notifyAll();
            p();
        }
    }

    public final void k(TResult tresult) {
        synchronized (this.a) {
            if (this.b) {
                return;
            }
            this.b = true;
            this.d = tresult;
            this.a.notifyAll();
            p();
        }
    }

    public final boolean l() {
        synchronized (this.a) {
            if (this.b) {
                return false;
            }
            this.b = true;
            this.f1166c = true;
            this.a.notifyAll();
            p();
            return true;
        }
    }

    public final g.e.c.a.f<TResult> m(Executor executor, g.e.c.a.c<TResult> cVar) {
        i(new b(executor, cVar));
        return this;
    }

    public final g.e.c.a.f<TResult> n(Executor executor, g.e.c.a.d dVar) {
        i(new c(executor, dVar));
        return this;
    }

    public final g.e.c.a.f<TResult> o(Executor executor, g.e.c.a.e<TResult> eVar) {
        i(new d(executor, eVar));
        return this;
    }
}
