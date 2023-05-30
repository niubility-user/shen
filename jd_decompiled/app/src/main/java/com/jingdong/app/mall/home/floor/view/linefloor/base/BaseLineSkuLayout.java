package com.jingdong.app.mall.home.floor.view.linefloor.base;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.linefloor.base.b;
import com.jingdong.app.mall.home.floor.view.linefloor.widget.LineTitleLayout;
import com.jingdong.app.mall.home.floor.view.linefloor.widget.LiveLottieView;
import com.jingdong.app.mall.home.floor.view.linefloor.widget.VideoSkuLayout;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;

/* loaded from: classes4.dex */
public class BaseLineSkuLayout<S extends com.jingdong.app.mall.home.floor.view.linefloor.base.b> extends BaseLineLayout<S> implements com.jingdong.app.mall.home.l.a {
    private VideoSkuLayout A;
    private LiveLottieView B;
    private View C;
    private float D;
    protected S s;
    private LineTitleLayout t;
    private GradientTextView u;
    private VideoSkuLayout v;
    private LiveLottieView w;
    private View x;
    private LineTitleLayout y;
    private GradientTextView z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.view.linefloor.base.b f9836g;

        a(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar) {
            this.f9836g = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (BaseLineSkuLayout.this.y != null && BaseLineSkuLayout.this.D > BaseLineSkuLayout.this.y.getLeft()) {
                this.f9836g.C0(BaseLineSkuLayout.this.o, 1, 1);
            } else {
                this.f9836g.C0(BaseLineSkuLayout.this.o, 0, 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.view.linefloor.base.b f9838g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f9839h;

        b(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
            this.f9838g = bVar;
            this.f9839h = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f9838g.C0(BaseLineSkuLayout.this.o, this.f9839h, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.view.linefloor.base.b f9841g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f9842h;

        c(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
            this.f9841g = bVar;
            this.f9842h = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f9841g.C0(BaseLineSkuLayout.this.o, this.f9842h, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.view.linefloor.base.b f9844g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f9845h;

        d(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
            this.f9844g = bVar;
            this.f9845h = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f9844g.C0(BaseLineSkuLayout.this.o, this.f9845h, 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.view.linefloor.base.b f9847g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f9848h;

        e(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
            this.f9847g = bVar;
            this.f9848h = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f9847g.C0(BaseLineSkuLayout.this.o, this.f9848h, 2);
        }
    }

    public BaseLineSkuLayout(Context context, com.jingdong.app.mall.home.floor.view.b.a aVar) {
        super(context, aVar);
    }

    private void C(S s, int i2) {
        f fVar = s.x.a;
        String W = s.W(i2);
        if (!s.x0() || TextUtils.isEmpty(W)) {
            r(true, this.v, this.x);
            return;
        }
        boolean showSkuLabel = s.a.showSkuLabel(s, i2);
        s.a.changeSkuLayoutSize(fVar, showSkuLabel, s);
        VideoSkuLayout videoSkuLayout = this.v;
        if (videoSkuLayout == null) {
            VideoSkuLayout videoSkuLayout2 = new VideoSkuLayout(this.o, this);
            this.v = videoSkuLayout2;
            videoSkuLayout2.setId(R.id.mallfloor_item1);
            RelativeLayout.LayoutParams u = fVar.u(this.v);
            u.addRule(9);
            addView(this.v, u);
        } else {
            f.d(videoSkuLayout, fVar, true);
        }
        r(false, this.v);
        this.v.setOnClickListener(new b(s, i2));
        this.v.s(s, fVar, W, showSkuLabel, i2);
        z(s, i2, this.v, fVar);
        boolean z = s.q() < 2;
        if (z && this.x == null) {
            this.x = new View(this.o);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
            layoutParams.addRule(9);
            layoutParams.addRule(7, this.v.getId());
            addView(this.x, 0, layoutParams);
        }
        View view = this.x;
        if (view != null) {
            view.setContentDescription(s.n0(i2) + s.c0(i2));
            this.x.setOnClickListener(new c(s, i2));
            r(z ^ true, this.x);
        }
    }

    private void E(S s) {
        f fVar = s.x.f9743e;
        if (fVar == null || this.t == null || !s.a.useSubTitle(s)) {
            r(true, this.u);
            return;
        }
        GradientTextView gradientTextView = this.u;
        if (gradientTextView == null) {
            GradientTextView gradientTextView2 = new GradientTextView(this.o);
            this.u = gradientTextView2;
            gradientTextView2.setGravity(16);
            RelativeLayout.LayoutParams u = fVar.u(this.u);
            u.addRule(6, this.t.getId());
            addView(this.u, u);
        } else {
            f.d(gradientTextView, fVar, true);
        }
        r(false, this.u);
        String c0 = s.c0(0);
        int d2 = com.jingdong.app.mall.home.floor.common.d.d(s.q() < 2 ? 150 : 320);
        g.o(this.u, s.e0(0));
        this.u.setText(com.jingdong.app.mall.home.o.a.f.l(this.u, d2, c0));
        this.u.setTextGradient(GradientTextView.GradientType.LeftToRight, s.d0(0));
        if (Build.VERSION.SDK_INT >= 16) {
            this.u.setImportantForAccessibility(2);
        }
    }

    private void F(S s) {
        f fVar = s.x.f9744f;
        if (fVar == null || this.y == null || this.A == null || !s.a.useSubTitle(s) || !s.z0()) {
            r(true, this.z);
            return;
        }
        GradientTextView gradientTextView = this.z;
        if (gradientTextView == null) {
            GradientTextView gradientTextView2 = new GradientTextView(this.o);
            this.z = gradientTextView2;
            gradientTextView2.setGravity(16);
            RelativeLayout.LayoutParams u = fVar.u(this.z);
            u.addRule(5, this.A.getId());
            u.addRule(6, this.y.getId());
            addView(this.z, u);
        } else {
            f.d(gradientTextView, fVar, true);
        }
        r(false, this.z);
        String c0 = s.c0(1);
        g.o(this.z, s.e0(1));
        this.z.setText(com.jingdong.app.mall.home.o.a.f.l(this.z, com.jingdong.app.mall.home.floor.common.d.d(150), c0));
        this.z.setTextGradient(GradientTextView.GradientType.LeftToRight, s.d0(1));
    }

    private void G(S s) {
        H(s);
        E(s);
        I(s);
        F(s);
    }

    private void H(S s) {
        f fVar = s.x.f9742c;
        LineTitleLayout lineTitleLayout = this.t;
        if (lineTitleLayout == null) {
            LineTitleLayout lineTitleLayout2 = new LineTitleLayout(this.o);
            this.t = lineTitleLayout2;
            lineTitleLayout2.setId(R.id.mallfloor_item3);
            LineTitleLayout lineTitleLayout3 = this.t;
            addView(lineTitleLayout3, fVar.u(lineTitleLayout3));
        } else {
            f.d(lineTitleLayout, fVar, true);
        }
        this.t.d(s, this, 0);
    }

    private void I(S s) {
        VideoSkuLayout videoSkuLayout;
        if (this.p.getSubWeight() >= 2 || (videoSkuLayout = this.A) == null || videoSkuLayout.getVisibility() == 8 || !s.z0()) {
            r(true, this.y);
            return;
        }
        f fVar = s.x.d;
        LineTitleLayout lineTitleLayout = this.y;
        if (lineTitleLayout == null) {
            LineTitleLayout lineTitleLayout2 = new LineTitleLayout(this.o);
            this.y = lineTitleLayout2;
            lineTitleLayout2.setId(R.id.mallfloor_item4);
            RelativeLayout.LayoutParams u = fVar.u(this.y);
            u.addRule(5, this.A.getId());
            addView(this.y, u);
        } else {
            f.d(lineTitleLayout, fVar, true);
        }
        r(false, this.y);
        this.y.d(s, this, 1);
        if (Build.VERSION.SDK_INT >= 16) {
            this.y.setImportantForAccessibility(2);
        }
    }

    private void z(S s, int i2, @NonNull VideoSkuLayout videoSkuLayout, @NonNull f fVar) {
        videoSkuLayout.n();
        if (i2 == 1 && s.q() == 2) {
            return;
        }
        com.jingdong.app.mall.home.l.b.b().a(s.g(i2).s(), this);
    }

    protected void A(S s, int i2) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        f fVar = s.x.a;
        if (s.w0(i2) && s.t0(i2) && fVar != null && s.a.showLiveLottie(i2, fVar.v(), fVar.h())) {
            LiveLottieView liveLottieView = this.w;
            if (liveLottieView == null) {
                LiveLottieView liveLottieView2 = new LiveLottieView(getContext());
                this.w = liveLottieView2;
                RelativeLayout.LayoutParams u = fVar.u(liveLottieView2);
                u.addRule(9);
                addView(this.w, u);
            } else {
                f.d(liveLottieView, fVar, true);
            }
            this.w.f(s, i2);
            return;
        }
        LiveLottieView liveLottieView3 = this.w;
        if (liveLottieView3 != null) {
            liveLottieView3.i();
        }
        r(true, this.w);
    }

    protected void B(S s, int i2) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        f fVar = s.x.b;
        if (s.w0(i2) && s.t0(i2) && fVar != null && s.a.showLiveLottie(i2, fVar.v(), fVar.h())) {
            LiveLottieView liveLottieView = this.B;
            if (liveLottieView == null) {
                LiveLottieView liveLottieView2 = new LiveLottieView(getContext());
                this.B = liveLottieView2;
                RelativeLayout.LayoutParams u = fVar.u(liveLottieView2);
                u.addRule(11);
                addView(this.B, u);
            } else {
                f.d(liveLottieView, fVar, true);
            }
            this.B.f(s, i2);
            r(false, this.B);
            return;
        }
        LiveLottieView liveLottieView3 = this.B;
        if (liveLottieView3 != null) {
            liveLottieView3.i();
        }
        r(true, this.B);
    }

    protected void D(S s, int i2) {
        f fVar = s.x.b;
        String W = s.W(i2);
        boolean showSkuLabel = s.a.showSkuLabel(s, i2);
        if (!s.y0() || TextUtils.isEmpty(W)) {
            r(true, this.A, this.C);
            return;
        }
        s.a.changeSkuLayoutSize(fVar, showSkuLabel, s);
        VideoSkuLayout videoSkuLayout = this.A;
        if (videoSkuLayout == null) {
            VideoSkuLayout videoSkuLayout2 = new VideoSkuLayout(this.o, this);
            this.A = videoSkuLayout2;
            videoSkuLayout2.setId(R.id.mallfloor_item2);
            RelativeLayout.LayoutParams u = fVar.u(this.A);
            u.addRule(11);
            addView(this.A, u);
        } else {
            f.d(videoSkuLayout, fVar, true);
        }
        r(false, this.A);
        this.A.setOnClickListener(new d(s, i2));
        this.A.setVisibility(0);
        this.A.s(s, fVar, W, showSkuLabel, i2);
        z(s, i2, this.A, fVar);
        boolean z = s.q() < 2;
        if (z && this.C == null) {
            this.C = new View(this.o);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
            VideoSkuLayout videoSkuLayout3 = this.v;
            if (videoSkuLayout3 != null) {
                layoutParams.addRule(1, videoSkuLayout3.getId());
            } else {
                layoutParams.addRule(5, this.A.getId());
            }
            layoutParams.addRule(11);
            addView(this.C, 0, layoutParams);
        }
        View view = this.C;
        if (view != null) {
            view.setOnClickListener(new e(s, i2));
            this.C.setContentDescription(s.n0(i2) + s.c0(i2));
            r(z ^ true, this.C);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout
    /* renamed from: J  reason: merged with bridge method [inline-methods] */
    public void v(@NonNull S s, int i2) {
        setOnClickListener(new a(s));
        this.s = s;
        C(s, 0);
        D(s, 1);
        A(s, 0);
        B(s, 1);
        G(s);
    }

    @Override // com.jingdong.app.mall.home.l.a
    public void c(int i2, String str, long j2) {
        String str2;
        boolean z;
        boolean z2 = i2 == 101;
        com.jingdong.app.mall.home.r.e.f g2 = this.s.g(0);
        String d2 = this.s.d(g2, z2);
        boolean L = this.s.L(d2);
        if (this.s.q() != 2) {
            String s = g2.s();
            com.jingdong.app.mall.home.r.e.f g3 = this.s.g(1);
            String s2 = g3.s();
            boolean equals = TextUtils.equals(s, str);
            str2 = this.s.d(g3, z2);
            z = TextUtils.equals(s2, str) && this.s.L(str2);
            L = equals;
        } else {
            str2 = d2;
            z = L;
        }
        if (L) {
            VideoSkuLayout videoSkuLayout = this.v;
            if (videoSkuLayout != null) {
                videoSkuLayout.o(d2, 0);
            }
            p(d2);
        }
        if (z) {
            VideoSkuLayout videoSkuLayout2 = this.A;
            if (videoSkuLayout2 != null) {
                videoSkuLayout2.o(str2, 1);
            }
            p(str2);
        }
    }

    @Override // com.jingdong.app.mall.home.l.a
    public boolean d(String str) {
        S s = this.s;
        if (s == null || this.v == null || this.A == null) {
            return false;
        }
        String s2 = s.g(0).s();
        String s3 = this.s.g(1).s();
        if (TextUtils.equals(s2, str) || TextUtils.equals(s3, str)) {
            return m.H(this, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, 100, true);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.D = motionEvent.getX();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout
    public void w() {
        super.w();
        VideoSkuLayout videoSkuLayout = this.v;
        if (videoSkuLayout != null) {
            videoSkuLayout.z();
        }
        VideoSkuLayout videoSkuLayout2 = this.A;
        if (videoSkuLayout2 != null) {
            videoSkuLayout2.z();
        }
    }
}
