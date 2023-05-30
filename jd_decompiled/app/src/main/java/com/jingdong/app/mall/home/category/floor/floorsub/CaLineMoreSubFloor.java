package com.jingdong.app.mall.home.category.floor.floorsub;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.n.g.x.i;
import com.jingdong.sdk.oklog.OKLog;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class CaLineMoreSubFloor extends BaseCaRecycleItem<i> {
    private f A;
    private SimpleDraweeView B;
    private View C;
    private GradientTextView D;
    private com.jingdong.app.mall.home.n.g.w.c E;
    private f F;
    private GradientTextView G;
    private com.jingdong.app.mall.home.n.g.w.c H;
    private f I;
    private int q;
    private Drawable r;
    private GradientDrawable s;
    private com.jingdong.app.mall.home.n.g.w.a t;
    private f u;
    private View v;
    private f w;
    private SimpleDraweeView x;
    private f y;
    private SimpleDraweeView z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CaLineMoreSubFloor.this.u(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CaLineMoreSubFloor.this.u(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements e.b {
        c() {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            CaLineMoreSubFloor caLineMoreSubFloor = CaLineMoreSubFloor.this;
            caLineMoreSubFloor.w(caLineMoreSubFloor.s);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            CaLineMoreSubFloor.this.w(null);
        }
    }

    public CaLineMoreSubFloor(Context context, com.jingdong.app.mall.home.n.g.w.a aVar) {
        super(context);
        this.s = new GradientDrawable();
        this.t = aVar;
        f b2 = aVar.b();
        this.A = b2;
        if (b2 != null) {
            View view = new View(context);
            this.C = view;
            RelativeLayout.LayoutParams u = this.A.u(view);
            u.addRule(14);
            addView(this.C, u);
        }
        f h2 = this.t.h();
        this.u = h2;
        if (h2 != null) {
            View view2 = new View(context);
            this.v = view2;
            view2.setBackgroundColor(-1);
            RelativeLayout.LayoutParams u2 = this.u.u(this.v);
            if (this.u.l() == 0 && this.u.m() == 0) {
                u2.addRule(14);
            }
            addView(this.v, u2);
        }
        f c2 = this.t.c();
        this.w = c2;
        if (c2 != null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(context);
            this.x = homeDraweeView;
            q(this.x, this.w.u(homeDraweeView));
        }
        f d = this.t.d();
        this.y = d;
        if (d != null) {
            HomeDraweeView homeDraweeView2 = new HomeDraweeView(context);
            this.z = homeDraweeView2;
            q(this.z, this.y.u(homeDraweeView2));
        }
        if (this.A != null) {
            HomeDraweeView homeDraweeView3 = new HomeDraweeView(context);
            this.B = homeDraweeView3;
            homeDraweeView3.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u3 = this.A.u(this.B);
            u3.addRule(14);
            addView(this.B, u3);
        }
        com.jingdong.app.mall.home.n.g.w.c g2 = this.t.g();
        this.E = g2;
        if (g2 != null && !g2.f()) {
            this.F = this.E.e();
            GradientTextView v = v(context, this.E);
            this.D = v;
            r(v, this.F);
        }
        com.jingdong.app.mall.home.n.g.w.c f2 = this.t.f();
        this.H = f2;
        if (f2 == null || f2.f()) {
            return;
        }
        this.I = this.H.e();
        GradientTextView v2 = v(context, this.H);
        this.G = v2;
        r(v2, this.I);
    }

    private void q(SimpleDraweeView simpleDraweeView, RelativeLayout.LayoutParams layoutParams) {
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        if (layoutParams.leftMargin == 0 && layoutParams.rightMargin == 0) {
            layoutParams.addRule(14);
        }
        addView(simpleDraweeView, layoutParams);
    }

    private void r(View view, f fVar) {
        if (view == null || fVar == null) {
            return;
        }
        RelativeLayout.LayoutParams u = fVar.u(view);
        if (u.width == -2) {
            u.addRule(14);
        }
        addView(view, u);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(int i2) {
        OKLog.d("clickEvent", Integer.valueOf(i2));
        com.jingdong.app.mall.home.n.h.b.e(getContext(), ((i) this.f8679m).d(), ((i) this.f8679m).k(), i2);
    }

    private GradientTextView v(Context context, com.jingdong.app.mall.home.n.g.w.c cVar) {
        g gVar = new g(context, true);
        gVar.f(1);
        gVar.c(cVar.a());
        gVar.l(cVar.c());
        gVar.d(16);
        gVar.m(cVar.d());
        gVar.j(cVar.g());
        return gVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(Drawable drawable) {
        View view = this.C;
        if (view != null) {
            view.setBackgroundDrawable(drawable);
        }
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    protected boolean i() {
        u(0);
        return true;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: s  reason: merged with bridge method [inline-methods] */
    public void e(@NotNull i iVar) {
        int x = iVar.x();
        if (2 == x) {
            GradientTextView gradientTextView = this.D;
            if (gradientTextView != null) {
                gradientTextView.setTextGradient(GradientTextView.GradientType.LeftToRight, iVar.B());
                this.D.setText(iVar.A());
            }
            GradientTextView gradientTextView2 = this.G;
            if (gradientTextView2 != null) {
                gradientTextView2.setTextGradient(GradientTextView.GradientType.LeftToRight, iVar.z());
                this.G.setText(iVar.y());
            }
            SimpleDraweeView simpleDraweeView = this.x;
            if (simpleDraweeView != null) {
                simpleDraweeView.setOnClickListener(new a());
                e.m(this.x, iVar.v(), this.r);
            }
            SimpleDraweeView simpleDraweeView2 = this.z;
            if (simpleDraweeView2 != null) {
                simpleDraweeView2.setOnClickListener(new b());
                e.m(this.z, iVar.w(), this.r);
            }
        }
        com.jingdong.app.mall.home.n.h.c.l(2 != x, this.D, this.G, this.x, this.z, this.v);
        if (this.B == null) {
            return;
        }
        if (com.jingdong.app.mall.home.n.b.S_LINE2 == iVar.l()) {
            w(null);
            if (x == 2) {
                e.m(this.B, iVar.u(), e.b);
                return;
            } else {
                e.f(iVar.u(), this.B, com.jingdong.app.mall.home.floor.ctrl.f.a().setPlaceholder(17));
                return;
            }
        }
        w(this.s);
        e.p(this.B, iVar.u(), e.b, new c());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: t  reason: merged with bridge method [inline-methods] */
    public void f(i iVar) {
        GradientTextView gradientTextView;
        GradientTextView gradientTextView2;
        super.f(iVar);
        if (this.t == null) {
            return;
        }
        if (this.q != d.f9279g) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            this.s = gradientDrawable;
            gradientDrawable.setCornerRadius(d.d(this.t.a()) + 1);
            this.s.setColor(-11048705);
            this.r = e.w();
            this.q = d.f9279g;
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setCornerRadius(d.d(this.t.i()));
        gradientDrawable2.setColor(-1);
        View view = this.v;
        if (view != null) {
            view.setBackgroundDrawable(gradientDrawable2);
        }
        com.jingdong.app.mall.home.n.h.e.d(this.x, d.d(this.t.e()));
        com.jingdong.app.mall.home.n.h.e.d(this.z, d.d(this.t.e()));
        com.jingdong.app.mall.home.n.h.e.d(this.B, d.d(this.t.a()));
        f.c(this.v, this.u);
        f.c(this.x, this.w);
        f.c(this.z, this.y);
        f.c(this.B, this.A);
        f.c(this.D, this.F);
        f.c(this.G, this.I);
        com.jingdong.app.mall.home.n.g.w.c cVar = this.E;
        if (cVar != null && (gradientTextView2 = this.D) != null) {
            gradientTextView2.setMaxWidth(d.d(cVar.b()));
            g.o(this.D, this.E.d());
        }
        com.jingdong.app.mall.home.n.g.w.c cVar2 = this.H;
        if (cVar2 == null || (gradientTextView = this.G) == null) {
            return;
        }
        gradientTextView.setMaxWidth(d.d(cVar2.b()));
        g.o(this.G, this.H.d());
    }
}
