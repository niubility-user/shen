package com.jingdong.app.mall.home.floor.view.b.f;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class e {

    /* renamed from: f  reason: collision with root package name */
    private static final Handler f9757f = new Handler(Looper.getMainLooper());
    private final com.jingdong.app.mall.home.floor.view.b.f.b a;
    private final com.jingdong.app.mall.home.floor.view.b.f.a b;

    /* renamed from: c  reason: collision with root package name */
    private final AtomicBoolean f9758c;
    private final AtomicInteger d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f9759e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            e.this.f9758c.set(true);
            e.this.a.o();
            e.this.b.o();
        }
    }

    /* loaded from: classes4.dex */
    public static class b {
        static e a = new e(null);
    }

    /* synthetic */ e(a aVar) {
        this();
    }

    public static e j() {
        return b.a;
    }

    public void d() {
        this.d.getAndIncrement();
    }

    public void e(g gVar) {
        this.b.f(gVar);
    }

    public void f(g gVar) {
        this.a.f(gVar);
    }

    public void g() {
        this.f9759e = false;
        this.f9758c.set(false);
        h();
    }

    public void h() {
        this.a.g();
        this.b.g();
    }

    public void i() {
        if ((this.d.get() > 0 ? this.d.decrementAndGet() : 0) <= 0) {
            o();
        }
    }

    public void k() {
        n();
    }

    public void l() {
        o();
    }

    public void m(int i2) {
        if (i2 == 0) {
            o();
        } else {
            n();
        }
    }

    public void n() {
        this.f9758c.set(false);
        this.a.l();
        this.b.l();
    }

    public void o() {
        if (!this.f9759e || this.f9758c.get() || this.d.get() > 0) {
            return;
        }
        Handler handler = f9757f;
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new a(), 800L);
    }

    public void p(boolean z) {
        if (z) {
            d();
        } else {
            i();
        }
    }

    public void q(boolean z) {
        if (z) {
            return;
        }
        this.f9759e = true;
        o();
    }

    private e() {
        this.a = new com.jingdong.app.mall.home.floor.view.b.f.b();
        this.b = new com.jingdong.app.mall.home.floor.view.b.f.a();
        this.f9758c = new AtomicBoolean(false);
        this.d = new AtomicInteger();
    }
}
