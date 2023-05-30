package com.jingdong.app.mall.home.floor.view.b.f;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.SparseArray;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public abstract class c {
    ValueAnimator b;

    /* renamed from: c  reason: collision with root package name */
    ValueAnimator.AnimatorUpdateListener f9747c;
    int d;

    /* renamed from: e  reason: collision with root package name */
    private g f9748e;
    SparseArray<g> a = new SparseArray<>();

    /* renamed from: f  reason: collision with root package name */
    private AtomicBoolean f9749f = new AtomicBoolean(false);

    /* renamed from: g  reason: collision with root package name */
    private AtomicBoolean f9750g = new AtomicBoolean(false);

    /* renamed from: h  reason: collision with root package name */
    AtomicInteger f9751h = new AtomicInteger();

    /* loaded from: classes4.dex */
    class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            c.this.f9749f.set(false);
            int size = c.this.a.size();
            if (size <= 0 || c.this.b.isRunning()) {
                return;
            }
            if (c.this.f9751h.get() == size) {
                c.this.i();
                return;
            }
            c cVar = c.this;
            cVar.d = 0;
            cVar.f9751h.set(0);
            if (c.this.f9748e != null) {
                c.this.j();
                c.this.f9748e = null;
            }
            c cVar2 = c.this;
            cVar2.n(cVar2.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends f {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f9753g;

        b(int i2) {
            this.f9753g = i2;
        }

        @Override // com.jingdong.app.mall.home.floor.view.b.f.f, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            super.onAnimationCancel(animator);
            c.this.j();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            c.this.j();
            if (c.this.f9749f.get()) {
                return;
            }
            c.this.n(this.f9753g + 1);
        }
    }

    /* renamed from: com.jingdong.app.mall.home.floor.view.b.f.c$c  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class C0299c extends com.jingdong.app.mall.home.o.a.b {
        C0299c() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            c.this.f9751h.set(0);
            c cVar = c.this;
            cVar.d = 0;
            cVar.b.cancel();
            c.this.a.clear();
            c.this.f9750g.set(false);
        }
    }

    public c() {
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(int i2) {
        int size = this.a.size();
        if (this.f9751h.get() == size) {
            i();
            return;
        }
        this.d = i2;
        if (i2 >= size) {
            return;
        }
        if (this.f9748e != null) {
            j();
        }
        g valueAt = this.a.valueAt(this.d);
        this.f9748e = valueAt;
        if (valueAt == null) {
            n(i2 + 1);
        } else if (!valueAt.g() && this.f9748e.f()) {
            if (!this.f9748e.isVisible()) {
                j();
                m();
                n(i2 + 1);
                return;
            }
            h();
            this.f9751h.getAndIncrement();
            this.f9748e.e(i2);
            this.b.addUpdateListener(this.f9747c);
            this.b.addListener(new b(i2));
            this.b.start();
        } else {
            j();
            this.f9751h.getAndIncrement();
            n(i2 + 1);
        }
    }

    public void f(g gVar) {
        this.a.put(gVar.a(), gVar);
    }

    public void g() {
        com.jingdong.app.mall.home.o.a.f.E0(new C0299c());
    }

    protected abstract void h();

    public void i() {
        this.f9750g.set(true);
        k(true);
    }

    protected void j() {
        k(false);
    }

    protected void k(boolean z) {
        if (this.f9748e == null && this.a.size() > 0) {
            this.f9748e = this.a.get(0);
        }
        g gVar = this.f9748e;
        if (gVar != null) {
            gVar.onEnd(z);
        }
    }

    public void l() {
        this.f9749f.set(true);
    }

    protected void m() {
    }

    public void o() {
        com.jingdong.app.mall.home.o.a.f.E0(new a());
    }
}
