package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout;
import com.jingdong.app.mall.home.floor.view.linefloor.base.b;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.n.h.g;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public class LabelLayout extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private Paint f9874g;

    /* renamed from: h  reason: collision with root package name */
    private Path f9875h;

    /* renamed from: i  reason: collision with root package name */
    private NinePatch f9876i;

    /* renamed from: j  reason: collision with root package name */
    private int f9877j;

    /* renamed from: k  reason: collision with root package name */
    private int f9878k;

    /* renamed from: l  reason: collision with root package name */
    protected SimpleDraweeView f9879l;

    /* renamed from: m  reason: collision with root package name */
    protected f f9880m;

    /* renamed from: n  reason: collision with root package name */
    protected GradientTextView f9881n;
    protected f o;
    protected b p;
    private boolean q;
    protected boolean r;
    protected boolean s;
    protected boolean t;
    private int u;
    private int[] v;
    private String w;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.t.a {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // com.jingdong.app.mall.home.t.a
        public void onBitmapWithUiNull(Bitmap bitmap) {
            if (bitmap == null) {
                LabelLayout.this.s();
            } else {
                LabelLayout.this.r(bitmap, this.a);
            }
        }
    }

    public LabelLayout(Context context, boolean z, boolean z2) {
        super(context);
        this.f9874g = new Paint(1);
        this.f9875h = new Path();
        this.q = z;
        this.t = z2;
        if (!z) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(context);
            this.f9879l = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            f fVar = new f(32, -1);
            this.f9880m = fVar;
            RelativeLayout.LayoutParams u = fVar.u(this.f9879l);
            u.addRule(15);
            addView(this.f9879l, u);
        }
        GradientTextView gradientTextView = new GradientTextView(context);
        this.f9881n = gradientTextView;
        gradientTextView.setTextColor(-1);
        this.f9881n.setGravity(16);
        this.f9881n.setMaxLines(1);
        setGravity(17);
        f fVar2 = new f(-2, -2);
        this.o = fVar2;
        if (this.t) {
            fVar2.J(12, 0, 12, 0);
        }
        View view = this.f9881n;
        addView(view, this.o.u(view));
    }

    private void j(Canvas canvas) {
        int width = getWidth();
        if (this.f9875h.isEmpty() || this.u != width) {
            m();
            n(width);
            this.u = width;
            int height = getHeight();
            this.f9875h.reset();
            g.g(0.0f, 0.0f, width, height, height >> 1, this.f9875h);
        }
        this.f9874g.setAlpha(this.f9878k);
        canvas.drawPath(this.f9875h, this.f9874g);
    }

    private void k(Canvas canvas) {
        int width = getWidth();
        if (this.f9875h.isEmpty() || this.u != width) {
            m();
            n(width);
            this.u = width;
            int height = getHeight();
            int d = d.d(8);
            int d2 = d.d(10);
            int d3 = d.d(16);
            this.f9875h.reset();
            float f2 = height;
            this.f9875h.moveTo(0.0f, f2);
            float f3 = d2;
            this.f9875h.quadTo(f3, f2, f3, height - d3);
            float f4 = d;
            this.f9875h.lineTo(f3, f4);
            this.f9875h.quadTo(f3, 0.0f, d2 + d, 0.0f);
            float f5 = width - d;
            this.f9875h.lineTo(f5, 0.0f);
            float f6 = width;
            this.f9875h.quadTo(f6, 0.0f, f6, f4);
            this.f9875h.lineTo(f6, height - d);
            this.f9875h.quadTo(f6, f2, f5, f2);
            this.f9875h.close();
        }
        this.f9874g.setAlpha(this.f9878k);
        canvas.drawPath(this.f9875h, this.f9874g);
    }

    private void l(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (this.f9875h.isEmpty() || this.u != width) {
            m();
            o(width, new float[]{0.0f, 0.4f, 1.0f});
            this.u = width;
            float f2 = height >> 1;
            float f3 = f2 * 0.5522848f;
            float f4 = ((height * 24) / 33.0f) / 2.0f;
            float f5 = (height * 7) / 33.0f;
            float f6 = f5 / 2.0f;
            float f7 = 0.5522848f * f6;
            this.f9875h.reset();
            this.f9875h.moveTo(0.0f, f2);
            g.c(0.0f, 0.0f, f2, f3, this.f9875h);
            float f8 = width;
            float f9 = f8 - ((height * 9) / 33.0f);
            this.f9875h.lineTo(f9 - f6, 0.0f);
            g.f(0.0f, f9, f6, f7, this.f9875h);
            g.d(f9, f5, f6, f7, this.f9875h);
            float f10 = f9 - f5;
            g.e(f5, f10, f6, f7, this.f9875h);
            float f11 = height;
            this.f9875h.lineTo(f10, f11 - f4);
            g.d(f10, f11, f4, f4 * 0.5522848f, this.f9875h);
            this.f9875h.lineTo(f2, f11);
            g.a(f11, 0.0f, f2, f3, this.f9875h);
            g.g(f8 - f5, 0.0f, f8, f5, f6, this.f9875h);
        }
        if (this.f9876i != null) {
            int saveLayer = canvas.saveLayer(new RectF(0.0f, 0.0f, width, height), this.f9874g, 31);
            canvas.drawPath(this.f9875h, this.f9874g);
            this.f9874g.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            this.f9876i.draw(canvas, new Rect(0, 0, width, height), this.f9874g);
            this.f9874g.setXfermode(null);
            canvas.restoreToCount(saveLayer);
            return;
        }
        canvas.drawPath(this.f9875h, this.f9874g);
    }

    private void n(int i2) {
        o(i2, null);
    }

    private void o(int i2, @Nullable float[] fArr) {
        this.f9874g.setStyle(Paint.Style.FILL);
        int[] iArr = this.v;
        if (iArr.length == 1) {
            this.f9874g.setColor(iArr[0]);
            this.f9874g.setShader(null);
        } else if (iArr.length > 1) {
            this.f9874g.setShader(new LinearGradient(0.0f, 0.0f, i2, 0.0f, this.v, (fArr == null || iArr.length == fArr.length) ? fArr : null, Shader.TileMode.CLAMP));
        }
    }

    private boolean p(String str) {
        return TextUtils.equals(str, this.w) && this.f9877j == d.d(32);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(Bitmap bitmap, String str) {
        int d = d.d(32);
        Bitmap x = e.x(bitmap, d);
        byte[] y = e.y(x, 0.4f);
        if (y != null) {
            this.f9876i = new NinePatch(x, y, null);
            this.f9877j = d;
            this.w = str;
        } else {
            this.f9877j = 0;
            this.f9876i = null;
            this.w = null;
        }
        postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        this.f9876i = null;
        this.w = null;
        this.f9877j = 0;
        postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(int i2, b bVar, BaseLineLayout baseLineLayout) {
        this.p = bVar;
        this.r = !TextUtils.isEmpty(bVar.m0());
        boolean E = bVar.E();
        this.s = E;
        f fVar = this.f9880m;
        if (fVar != null) {
            if (E) {
                fVar.R(20, 20);
                this.f9880m.F(new Rect(8, 0, 0, 0));
            } else {
                fVar.R(32, -1);
                this.f9880m.F(new Rect(0, 0, 0, 0));
            }
        }
        q(this.p.j0());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        try {
            if (this.s) {
                l(canvas);
            } else if (this.q) {
                k(canvas);
            } else {
                j(canvas);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        super.dispatchDraw(canvas);
        try {
            i(this.f9875h, canvas);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    protected void i(Path path, Canvas canvas) {
    }

    protected void m() {
    }

    public void q(String str) {
        if (this.s && !p(str)) {
            String b = com.jingdong.app.mall.home.m.a.b(str);
            if (!TextUtils.isEmpty(b) && !com.jingdong.app.mall.home.o.a.f.m0(b)) {
                com.jingdong.app.mall.home.floor.ctrl.f.i(b, new a(b));
            } else {
                s();
            }
        }
    }

    public void t(int[] iArr) {
        u(iArr, 255);
    }

    public void u(int[] iArr, int i2) {
        this.v = iArr;
        this.u = 0;
        this.f9878k = i2;
    }

    public void v(String str) {
        this.f9881n.setText(str);
    }

    public void w(String str, int i2) {
        v(str);
        com.jingdong.app.mall.home.floor.common.g.o(this.f9881n, i2);
    }

    public void x(GradientTextView.GradientType gradientType, int[] iArr) {
        this.f9881n.setTextGradient(gradientType, iArr);
    }
}
