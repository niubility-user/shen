package com.jingdong.app.mall.home.deploy.view.layout.core1x2lr;

import android.content.Context;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.p.b.c.a;
import com.jingdong.app.mall.home.p.b.c.b;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class Core1x2LRAnimateSku extends RelativeLayout implements b {

    /* renamed from: g  reason: collision with root package name */
    private final SkuLayout f8925g;

    /* renamed from: h  reason: collision with root package name */
    private final SkuLayout f8926h;

    /* renamed from: i  reason: collision with root package name */
    private SkuLayout f8927i;

    /* renamed from: j  reason: collision with root package name */
    private SkuLayout f8928j;

    /* renamed from: k  reason: collision with root package name */
    private DCore1x2LRModel f8929k;

    /* renamed from: l  reason: collision with root package name */
    private int f8930l;

    /* renamed from: m  reason: collision with root package name */
    private final AtomicBoolean f8931m;

    public Core1x2LRAnimateSku(Context context) {
        super(context);
        this.f8929k = null;
        this.f8931m = new AtomicBoolean(false);
        SkuLayout skuLayout = new SkuLayout(context, 10);
        this.f8926h = skuLayout;
        f fVar = new f(-1, -1);
        addView(skuLayout, fVar.u(skuLayout));
        this.f8928j = skuLayout;
        SkuLayout skuLayout2 = new SkuLayout(context, 10);
        this.f8925g = skuLayout2;
        addView(skuLayout2, fVar.u(skuLayout2));
        this.f8927i = skuLayout2;
        this.f8928j.setAlpha(0.0f);
        this.f8928j.setVisibility(8);
    }

    private void f() {
        this.f8925g.q(1.0f, 1.0f);
        this.f8926h.q(0.0f, 1.0f);
        this.f8925g.o(1.0f, 1.0f);
        this.f8925g.m(0.0f, 1.0f);
        this.f8927i = this.f8925g;
        this.f8928j = this.f8926h;
        this.f8931m.set(false);
    }

    private void g() {
        SkuLayout skuLayout = this.f8927i;
        if (skuLayout != null) {
            skuLayout.q(1.0f, 1.0f);
        }
        SkuLayout skuLayout2 = this.f8928j;
        if (skuLayout2 != null) {
            skuLayout2.q(0.0f, 0.7f);
        }
        this.f8930l = 0;
    }

    private void h() {
        SkuLayout skuLayout = this.f8927i;
        this.f8927i = this.f8928j;
        this.f8928j = skuLayout;
    }

    public void a(DCore1x2LRModel dCore1x2LRModel) {
        this.f8929k = dCore1x2LRModel;
        f();
        SkuLayout.Info D0 = this.f8929k.D0();
        this.f8925g.c(D0, this.f8929k);
        this.f8925g.n(0);
        if (D0.c() && D0.b()) {
            a.r().o(this);
        }
        SkuLayout.Info z0 = this.f8929k.z0();
        if (z0 == null) {
            this.f8926h.setVisibility(8);
            return;
        }
        this.f8926h.setVisibility(0);
        this.f8926h.b(z0);
        this.f8926h.n(1);
        a.r().p(this);
    }

    public int b() {
        SkuLayout skuLayout;
        if (this.f8929k.y0() != 1 || (skuLayout = this.f8928j) == null) {
            return 0;
        }
        if (this.f8930l < 250) {
            skuLayout = this.f8927i;
        }
        return skuLayout.j();
    }

    @Override // com.jingdong.app.mall.home.p.b.c.b
    public void c(boolean z) {
        if (z) {
            h();
            g();
            return;
        }
        this.f8925g.r();
        this.f8925g.p();
    }

    @Override // com.jingdong.app.mall.home.p.b.c.b
    public void d(boolean z) {
        if (z) {
            g();
        } else {
            this.f8925g.p();
        }
        if (this.f8931m.getAndSet(true)) {
            return;
        }
        this.f8929k.Q0(z ? 1 : 2);
    }

    @Override // com.jingdong.app.mall.home.p.b.c.b
    public void e(boolean z, int i2) {
        if (z) {
            this.f8927i.i(i2);
            this.f8928j.h(i2);
            this.f8930l = i2;
            return;
        }
        this.f8925g.g(i2);
        this.f8925g.f(i2);
    }
}
