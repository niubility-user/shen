package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.b.c;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseAnimateLayout;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout;
import com.jingdong.app.mall.home.floor.view.widget.FitTopImage;

/* loaded from: classes4.dex */
public class VideoSkuLayout extends BaseAnimateLayout {
    private View A;
    private Paint o;
    private int p;
    private BaseLineLayout q;
    private FitTopImage r;
    private f s;
    private SimpleDraweeView t;
    private VideoSkuView u;
    private com.jingdong.app.mall.home.floor.view.linefloor.base.b v;
    private SkuLabelNormalLayout w;
    private f x;
    private SkuLabelSpecialLayout y;
    private f z;

    /* loaded from: classes4.dex */
    public class a implements e.b {
        a() {
            VideoSkuLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            VideoSkuLayout.this.o.setColor(-1);
            VideoSkuLayout.this.postInvalidate();
        }
    }

    /* loaded from: classes4.dex */
    public class b implements e.b {
        b() {
            VideoSkuLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            VideoSkuLayout.this.A.setAlpha(1.0f);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            VideoSkuLayout.this.A.setAlpha(0.0f);
        }
    }

    public VideoSkuLayout(Context context, BaseLineLayout baseLineLayout) {
        super(context);
        this.o = new Paint(1);
        this.q = baseLineLayout;
        this.r = new FitTopImage(context);
        f fVar = new f(-1, -1);
        this.s = fVar;
        RelativeLayout.LayoutParams u = fVar.u(this.r);
        u.addRule(14);
        addView(this.r, u);
        this.A = new View(context);
        f fVar2 = new f(-1, -1);
        View view = this.A;
        addView(view, fVar2.u(view));
    }

    private void u(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, boolean z, int i2) {
        c cVar = bVar.a;
        if (cVar != c.NORMAL) {
            return;
        }
        cVar.changeSkuSize(this, this.s, z, bVar);
        String U = bVar.U(i2);
        if (!z) {
            if (TextUtils.equals("2", U)) {
                SkuLabelNormalLayout.c(bVar, i2, "0");
            }
            SkuLabelNormalLayout skuLabelNormalLayout = this.w;
            if (skuLabelNormalLayout != null) {
                skuLabelNormalLayout.j();
            }
            t(true, this.w);
            return;
        }
        int i3 = 42;
        if (this.x == null) {
            this.x = new f(-1, 42);
        }
        String Y = bVar.Y(i2);
        int i4 = -8;
        if (bVar.F()) {
            if (!"5".equals(U)) {
                if (bVar.E()) {
                    i3 = 32;
                    i4 = -12;
                } else {
                    i3 = 28;
                }
            }
        } else if ("2".equals(U) && !TextUtils.isEmpty(Y)) {
            i3 = 28;
            i4 = 0;
        }
        this.x.C(i3);
        this.x.E(0, 0, 0, i4);
        SkuLabelNormalLayout skuLabelNormalLayout2 = this.w;
        if (skuLabelNormalLayout2 == null) {
            SkuLabelNormalLayout skuLabelNormalLayout3 = new SkuLabelNormalLayout(getContext());
            this.w = skuLabelNormalLayout3;
            RelativeLayout.LayoutParams u = this.x.u(skuLabelNormalLayout3);
            u.addRule(8, getId());
            u.addRule(5, getId());
            u.addRule(7, getId());
            this.q.addView(this.w, u);
        } else {
            f.c(skuLabelNormalLayout2, this.x);
        }
        t(false, this.w);
        this.w.setVisibility(0);
        this.w.d(bVar, i2, i3);
    }

    private void v(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
        String V = bVar.V(i2);
        if (!bVar.a.useSkuBg() || TextUtils.isEmpty(V)) {
            t(true, this.t);
            return;
        }
        if (this.t == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.t = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            f fVar = new f(-1, -1);
            SimpleDraweeView simpleDraweeView = this.t;
            addView(simpleDraweeView, 0, fVar.u(simpleDraweeView));
        }
        if (indexOfChild(this.t) != 0) {
            m.b(this, this.t, 0);
        }
        this.A.setAlpha(1.0f);
        t(false, this.t);
        com.jingdong.app.mall.home.n.h.e.e(this.t, d.d(12), 1.5f);
        e.p(this.t, V, e.b, new b());
    }

    private void w(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
        if (bVar.a.useSkuMask() && !bVar.F()) {
            int[] n2 = bVar.n(i2);
            if (n2.length < 1) {
                this.A.setAlpha(0.0f);
                return;
            }
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, com.jingdong.app.mall.home.floor.view.b.h.a.f(n2, 0, 25));
            this.A.setAlpha(1.0f);
            this.A.setBackgroundDrawable(gradientDrawable);
            return;
        }
        this.A.setAlpha(0.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void x(com.jingdong.app.mall.home.floor.view.linefloor.base.b r6, com.jingdong.app.mall.home.floor.common.f r7, boolean r8, int r9) {
        /*
            r5 = this;
            com.jingdong.app.mall.home.floor.view.b.c r0 = r6.a
            com.jingdong.app.mall.home.floor.view.b.c r1 = com.jingdong.app.mall.home.floor.view.b.c.SPECIAL
            if (r0 == r1) goto L7
            return
        L7:
            com.jingdong.app.mall.home.floor.common.f r1 = r5.s
            int r0 = r0.changeSkuSize(r5, r1, r8, r6)
            r5.p = r0
            r0 = 1
            r1 = 0
            if (r8 != 0) goto L1d
            android.view.View[] r6 = new android.view.View[r0]
            com.jingdong.app.mall.home.floor.view.linefloor.widget.SkuLabelSpecialLayout r7 = r5.y
            r6[r1] = r7
            r5.t(r0, r6)
            return
        L1d:
            com.jingdong.app.mall.home.floor.view.linefloor.widget.SkuLabelSpecialLayout r8 = r5.y
            r2 = 2
            if (r8 != 0) goto L54
            com.jingdong.app.mall.home.floor.view.linefloor.widget.SkuLabelSpecialLayout r8 = new com.jingdong.app.mall.home.floor.view.linefloor.widget.SkuLabelSpecialLayout
            android.content.Context r3 = r5.getContext()
            r8.<init>(r3)
            r5.y = r8
            com.jingdong.app.mall.home.floor.common.f r8 = new com.jingdong.app.mall.home.floor.common.f
            r3 = -1
            r4 = 36
            r8.<init>(r3, r4)
            r5.z = r8
            r3 = -14
            r8.E(r1, r3, r1, r1)
            com.jingdong.app.mall.home.floor.common.f r8 = r5.z
            r8.J(r1, r1, r1, r2)
            com.jingdong.app.mall.home.floor.common.f r8 = r5.z
            com.jingdong.app.mall.home.floor.view.linefloor.widget.SkuLabelSpecialLayout r3 = r5.y
            android.widget.RelativeLayout$LayoutParams r8 = r8.u(r3)
            r3 = 12
            r8.addRule(r3)
            com.jingdong.app.mall.home.floor.view.linefloor.widget.SkuLabelSpecialLayout r3 = r5.y
            r5.addView(r3, r8)
            goto L59
        L54:
            com.jingdong.app.mall.home.floor.common.f r3 = r5.z
            com.jingdong.app.mall.home.floor.common.f.c(r8, r3)
        L59:
            android.view.View[] r8 = new android.view.View[r0]
            com.jingdong.app.mall.home.floor.view.linefloor.widget.SkuLabelSpecialLayout r3 = r5.y
            r8[r1] = r3
            r5.t(r1, r8)
            com.jingdong.app.mall.home.floor.view.linefloor.widget.SkuLabelSpecialLayout r8 = r5.y
            int[] r3 = r6.X(r9)
            int r7 = r7.v()
            r8.setBgColor(r3, r7)
            int[] r7 = r6.a0(r9)
            com.jingdong.app.mall.home.floor.view.linefloor.widget.SkuLabelSpecialLayout r8 = r5.y
            com.jingdong.app.mall.home.floor.view.view.GradientTextView$GradientType r3 = com.jingdong.app.mall.home.floor.view.view.GradientTextView.GradientType.LeftToRight
            r8.setTextGradient(r3, r7)
            java.lang.String r7 = r6.b0(r9)
            java.lang.String r6 = r6.U(r9)
            java.lang.String r8 = "1"
            boolean r6 = r8.equals(r6)
            r8 = 6
            if (r6 == 0) goto L99
            r6 = 1065353216(0x3f800000, float:1.0)
            java.lang.String r9 = "."
            android.text.SpannableString r6 = com.jingdong.app.mall.home.category.floor.feedssub.a.d(r7, r9, r6)
            java.lang.String r6 = r6.toString()
        L97:
            r7 = 0
            goto La4
        L99:
            java.lang.String r6 = com.jingdong.app.mall.home.o.a.f.j(r8, r7)
            int r7 = r6.length()
            if (r7 < r8) goto L97
            r7 = 1
        La4:
            com.jingdong.app.mall.home.floor.common.f r9 = r5.z
            if (r7 == 0) goto La9
            r2 = 6
        La9:
            r9.J(r1, r1, r1, r2)
            com.jingdong.app.mall.home.floor.view.linefloor.widget.SkuLabelSpecialLayout r8 = r5.y
            if (r7 == 0) goto Lb3
            r7 = 20
            goto Lb5
        Lb3:
            r7 = 22
        Lb5:
            r8.setText(r6, r7)
            com.jingdong.app.mall.home.floor.view.linefloor.widget.SkuLabelSpecialLayout r6 = r5.y
            com.jingdong.app.mall.home.floor.common.f r7 = r5.z
            com.jingdong.app.mall.home.floor.common.f.d(r6, r7, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.floor.view.linefloor.widget.VideoSkuLayout.x(com.jingdong.app.mall.home.floor.view.linefloor.base.b, com.jingdong.app.mall.home.floor.common.f, boolean, int):void");
    }

    private void y(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2) {
        String s0 = bVar.s0(i2);
        this.r.animate().cancel();
        this.r.setAlpha(1.0f);
        if (!TextUtils.isEmpty(s0) && !bVar.v()) {
            if (!bVar.u0(i2)) {
                z();
                return;
            }
            String s = bVar.g(i2).s();
            String p0 = bVar.p0(i2);
            String q0 = bVar.q0(s, p0, s0);
            VideoSkuView n2 = VideoSkuView.n(getContext(), this.r, q0, this.q);
            if (this.u != n2) {
                z();
                this.u = n2;
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(14);
                this.u.setLayoutParams(layoutParams);
            }
            this.u.setVisibility(4);
            m.b(this, this.u, 0);
            com.jingdong.app.mall.home.n.h.e.d(this.u, d.d(12));
            bVar.b(i2, "isvideo", "1");
            this.u.s(bVar, i2, q0, p0, s0);
            return;
        }
        z();
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.BaseAnimateLayout, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar = this.v;
        if (bVar != null && bVar.a == c.SPECIAL) {
            if (this.p > 0) {
                canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight() - this.p, this.o);
            } else {
                canvas.drawColor(this.o.getColor());
            }
        }
        super.dispatchDraw(canvas);
    }

    public void s(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, f fVar, String str, boolean z, int i2) {
        this.v = bVar;
        this.o.setColor(0);
        w(bVar, i2);
        u(bVar, z, i2);
        x(bVar, fVar, z, i2);
        y(bVar, i2);
        v(bVar, i2);
        f.d(this.r, this.s, true);
        f.d(this.u, this.s, true);
        com.jingdong.app.mall.home.n.h.e.d(this.r, d.d(12));
        e.p(this.r, str, e.b, new a());
        if (this.p > 0) {
            com.jingdong.app.mall.home.n.h.e.h(this, d.d(12));
        } else {
            com.jingdong.app.mall.home.n.h.e.d(this, d.d(12));
        }
        setContentDescription(bVar.n0(i2));
        this.r.setContentDescription(bVar.n0(i2));
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        SkuLabelNormalLayout skuLabelNormalLayout = this.w;
        if (skuLabelNormalLayout == null || i2 == 0) {
            return;
        }
        skuLabelNormalLayout.setVisibility(i2);
    }

    protected void t(boolean z, View... viewArr) {
        com.jingdong.app.mall.home.n.h.c.k(z, viewArr);
    }

    public void z() {
        VideoSkuView videoSkuView = this.u;
        if (videoSkuView != null) {
            removeView(videoSkuView);
            this.u.u();
            this.u = null;
        }
    }
}
