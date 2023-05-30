package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.b.f.g;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout;
import com.jingdong.app.mall.home.floor.view.linefloor.base.b;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class LabelTitleLayout extends LabelLayout implements g {
    private LinearGradient x;
    private final Paint y;
    private final Matrix z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements e.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            LabelTitleLayout labelTitleLayout = LabelTitleLayout.this;
            labelTitleLayout.y(labelTitleLayout.f9879l, false);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            LabelTitleLayout labelTitleLayout = LabelTitleLayout.this;
            labelTitleLayout.y(labelTitleLayout.f9879l, true);
        }
    }

    public LabelTitleLayout(Context context, boolean z) {
        super(context, z, true);
        this.y = new Paint(1);
        this.z = new Matrix();
    }

    private void A(boolean z) {
        if (this.x != null) {
            this.z.setTranslate(-d.f9279g, -d.f9279g);
            this.x.setLocalMatrix(this.z);
            if (z) {
                postInvalidate();
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public int a() {
        return (this.p.h().t * 10) + this.p.m();
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.widget.LabelLayout
    public void d(int i2, b bVar, BaseLineLayout baseLineLayout) {
        super.d(i2, bVar, baseLineLayout);
        setVisibility(0);
        com.jingdong.app.mall.home.floor.common.g.o(this.f9881n, 22);
        z(bVar.m0());
        int[] l0 = bVar.l0();
        u(bVar.k0(), bVar.k() == com.jingdong.app.mall.home.floor.view.b.a.LINE_VIDEO ? 120 : 255);
        x(GradientTextView.GradientType.LeftToRight, l0);
        v(f.l(this.f9881n, d.d(this.r ? R2.attr.OverlayInnerPaddingTop : 290) - i2, bVar.i0()));
        if (!bVar.v() && bVar.M() && com.jingdong.app.mall.home.floor.view.b.f.d.f(this.p.p(), this.p.o())) {
            this.p.b(0, "islabelfrash", "1");
            com.jingdong.app.mall.home.floor.view.b.f.e.j().f(this);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public void e(int i2) {
        com.jingdong.app.mall.home.floor.view.b.f.d.a(this.p.p(), this.p.o());
        this.p.D0(true);
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public boolean f() {
        return com.jingdong.app.mall.home.floor.view.b.f.d.f(this.p.p(), this.p.o());
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public boolean g() {
        return this.p.v0();
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public void h(float f2) {
        if (this.x != null) {
            this.z.setTranslate(f2 * (getWidth() + getHeight()), 0.0f);
            this.x.setLocalMatrix(this.z);
            postInvalidate();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.widget.LabelLayout
    protected void i(Path path, Canvas canvas) {
        if (this.y.getShader() != null) {
            canvas.drawPath(path, this.y);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public boolean isVisible() {
        return m.H(this, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, 50, true);
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.widget.LabelLayout
    protected void m() {
        float height = getHeight();
        this.x = new LinearGradient(0.0f, 0.0f, (8.0f * height) / 3.0f, height, new int[]{0, -1862270977, -1862270977, 0}, new float[]{0.139f, 0.14f, 0.28f, 0.281f}, Shader.TileMode.CLAMP);
        A(false);
        this.y.setShader(this.x);
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public void onEnd(boolean z) {
        A(true);
    }

    protected void y(View view, boolean z) {
        if (view != null && view.getVisibility() == 0 && z) {
            this.o.E(this.s ? 20 : 28, 0, 0, 0);
        } else {
            this.o.E(0, 0, 0, 0);
        }
        if (this.t) {
            this.o.J(12, -d.d(1), this.s ? 24 : 12, 0);
        }
        com.jingdong.app.mall.home.floor.common.f.d(this.f9881n, this.o, true);
    }

    public boolean z(String str) {
        y(this.f9879l, false);
        if (this.f9879l == null) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            this.f9879l.setVisibility(8);
            return false;
        }
        com.jingdong.app.mall.home.n.h.e.d(this.f9879l, this.f9880m.h() >> 1);
        this.f9879l.setVisibility(0);
        e.p(this.f9879l, str, e.b, new a());
        com.jingdong.app.mall.home.floor.common.f.c(this.f9879l, this.f9880m);
        return true;
    }
}
