package com.jingdong.app.mall.home.deploy.view.layout.widget;

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
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.deploy.view.layout.core2x2.LiveIconLottie;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.t.a;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public class IconLabel extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private Context f9031g;

    /* renamed from: h  reason: collision with root package name */
    private Info f9032h;

    /* renamed from: i  reason: collision with root package name */
    private Paint f9033i;

    /* renamed from: j  reason: collision with root package name */
    private Path f9034j;

    /* renamed from: k  reason: collision with root package name */
    private NinePatch f9035k;

    /* renamed from: l  reason: collision with root package name */
    private int f9036l;

    /* renamed from: m  reason: collision with root package name */
    private IconView f9037m;

    /* renamed from: n  reason: collision with root package name */
    private GradientTextView f9038n;
    private LiveIconLottie o;
    private int p;
    private String q;

    /* loaded from: classes4.dex */
    public static class Info {
        boolean a;
        f b;

        /* renamed from: c  reason: collision with root package name */
        String f9039c;
        boolean d;

        /* renamed from: e  reason: collision with root package name */
        int[] f9040e;

        /* renamed from: f  reason: collision with root package name */
        Rect f9041f;

        /* renamed from: g  reason: collision with root package name */
        boolean f9042g;

        /* renamed from: h  reason: collision with root package name */
        String f9043h;

        /* renamed from: i  reason: collision with root package name */
        int[] f9044i;

        /* renamed from: j  reason: collision with root package name */
        boolean f9045j;

        /* renamed from: k  reason: collision with root package name */
        f f9046k;

        /* renamed from: l  reason: collision with root package name */
        String f9047l;

        /* renamed from: m  reason: collision with root package name */
        int[] f9048m;

        /* renamed from: n  reason: collision with root package name */
        boolean f9049n;
        int o;
        f p = new f(-2, -1);
        ShapeEnum q = ShapeEnum.STYLE_RADIUS;
        int r;
        boolean s;

        private Info() {
        }

        public static Info a() {
            return new Info();
        }

        public Info b(int[] iArr, String str) {
            this.f9044i = iArr;
            this.f9043h = str;
            this.r = d.d(8);
            this.f9042g = true;
            return this;
        }

        public Info c(boolean z) {
            this.f9049n = z;
            return this;
        }

        public Info d(int i2, int i3, String str) {
            if (i2 > 0 && i3 > 0 && !TextUtils.isEmpty(str)) {
                f fVar = new f(i2, i3);
                this.b = fVar;
                fVar.F(new Rect(0, 0, 6, 0));
                this.f9039c = str;
                this.a = true;
            }
            return this;
        }

        public Info e(boolean z, int i2) {
            if (z) {
                this.f9046k = new f(i2, i2);
                this.f9042g = false;
            }
            this.f9045j = z;
            return this;
        }

        public Info f(String str) {
            this.f9047l = str;
            return this;
        }

        public Info g(int[] iArr, int i2) {
            this.f9048m = iArr;
            this.o = i2;
            return this;
        }
    }

    public IconLabel(Context context) {
        super(context);
        this.f9033i = new Paint(1);
        this.f9034j = new Path();
        this.f9031g = context;
    }

    private void e(View view, View view2, int i2) {
        if (view == null || view2 == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            f((RelativeLayout.LayoutParams) layoutParams, view2, i2);
        }
    }

    private void f(RelativeLayout.LayoutParams layoutParams, View view, int i2) {
        if (layoutParams == null || view == null) {
            return;
        }
        layoutParams.addRule(i2, view.getId());
    }

    private void g() {
        f fVar = this.f9032h.b;
        if (fVar == null) {
            c.k(true, this.f9037m);
            this.f9032h.a = false;
            return;
        }
        IconView iconView = this.f9037m;
        if (iconView == null) {
            IconView iconView2 = new IconView(this.f9031g);
            this.f9037m = iconView2;
            iconView2.setId(R.id.home_deploy_label_icon);
            this.f9037m.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u = fVar.u(this.f9037m);
            u.addRule(15);
            addView(this.f9037m, u);
        } else {
            f.c(iconView, fVar);
        }
        c.k(false, this.f9037m);
    }

    private void j(Info info) {
        if (info == null) {
            return;
        }
        f fVar = info.f9046k;
        if (fVar == null) {
            c.k(true, this.o);
            return;
        }
        setPadding(0, 0, 0, 0);
        setVisibility(0);
        LiveIconLottie liveIconLottie = this.o;
        if (liveIconLottie == null) {
            LiveIconLottie liveIconLottie2 = new LiveIconLottie(this.f9031g);
            this.o = liveIconLottie2;
            RelativeLayout.LayoutParams u = info.f9046k.u(liveIconLottie2);
            u.addRule(15);
            addView(this.o, u);
        } else {
            f.c(liveIconLottie, fVar);
        }
        c.k(false, this.o);
        c.k(true, this.f9037m);
        c.k(true, this.f9038n);
        this.o.k();
    }

    private void k(int i2, int i3) {
        f fVar = this.f9032h.p;
        if (fVar == null) {
            return;
        }
        View view = this.f9038n;
        if (view == null) {
            GradientTextView gradientTextView = new GradientTextView(this.f9031g);
            this.f9038n = gradientTextView;
            gradientTextView.setPadding(0, -10, 0, -10);
            this.f9038n.setMaxLines(1);
            this.f9038n.setGravity(16);
            RelativeLayout.LayoutParams u = fVar.u(this.f9038n);
            u.addRule(15);
            f(u, this.f9037m, 1);
            addView(this.f9038n, u);
        } else {
            e(view, this.f9037m, 1);
            f.c(this.f9038n, fVar);
        }
        c.k(false, this.f9038n);
        this.f9038n.setTextGradient(GradientTextView.GradientType.LeftToRight, this.f9032h.f9048m);
        g.o(this.f9038n, this.f9032h.o);
        g.k(this.f9038n, this.f9032h.f9049n);
        t(i2, i3);
    }

    private void l(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (!this.f9032h.s || this.f9034j.isEmpty() || this.p != width) {
            this.p = width;
            n(width, height, this.f9032h.q);
            this.f9032h.s = true;
        }
        if (this.f9035k != null) {
            int saveLayer = canvas.saveLayer(new RectF(0.0f, 0.0f, width, height), this.f9033i, 31);
            canvas.drawPath(this.f9034j, this.f9033i);
            this.f9033i.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            this.f9035k.draw(canvas, new Rect(0, 0, width, height), this.f9033i);
            this.f9033i.setXfermode(null);
            canvas.restoreToCount(saveLayer);
            return;
        }
        canvas.drawPath(this.f9034j, this.f9033i);
    }

    private void m(int i2, @Nullable float[] fArr) {
        int[] iArr;
        Info info = this.f9032h;
        if (info == null || (iArr = info.f9044i) == null) {
            return;
        }
        this.f9033i.setStyle(Paint.Style.FILL);
        if (iArr.length == 1) {
            this.f9033i.setColor(iArr[0]);
            this.f9033i.setShader(null);
        } else if (iArr.length > 1) {
            this.f9033i.setShader(new LinearGradient(0.0f, 0.0f, i2, 0.0f, iArr, (fArr == null || iArr.length == fArr.length) ? fArr : null, Shader.TileMode.CLAMP));
        }
    }

    private void n(int i2, int i3, ShapeEnum shapeEnum) {
        m(i2, new float[]{0.0f, 0.4f, 1.0f});
        float d = d.d(4);
        float f2 = d * 0.5522848f;
        float f3 = i3;
        float f4 = f3 / 2.0f;
        float f5 = f4 * 0.5522848f;
        this.f9034j.reset();
        if (shapeEnum == ShapeEnum.STYLE_SQUARE) {
            this.f9034j.moveTo(f4, 0.0f);
            float f6 = i2;
            this.f9034j.lineTo(f6 - d, 0.0f);
            com.jingdong.app.mall.home.n.h.g.f(0.0f, f6, d, f2, this.f9034j);
            this.f9034j.lineTo(f6, f4 + 0.0f);
            com.jingdong.app.mall.home.n.h.g.d(f6, f3, f4, f5, this.f9034j);
            this.f9034j.lineTo(f4, f3);
            com.jingdong.app.mall.home.n.h.g.a(f3, 0.0f, f4, f5, this.f9034j);
            com.jingdong.app.mall.home.n.h.g.c(0.0f, 0.0f, f4, f5, this.f9034j);
        } else if (shapeEnum == ShapeEnum.STYLE_ROUND) {
            this.f9034j.moveTo(f4, 0.0f);
            float f7 = i2;
            this.f9034j.lineTo(f7 - f4, 0.0f);
            com.jingdong.app.mall.home.n.h.g.f(0.0f, f7, f4, f5, this.f9034j);
            com.jingdong.app.mall.home.n.h.g.d(f7, f3, f4, f5, this.f9034j);
            this.f9034j.lineTo(f4, f3);
            com.jingdong.app.mall.home.n.h.g.a(f3, 0.0f, f4, f5, this.f9034j);
            com.jingdong.app.mall.home.n.h.g.c(0.0f, 0.0f, f4, f5, this.f9034j);
        } else if (shapeEnum == ShapeEnum.STYLE_RADIUS) {
            float f8 = this.f9032h.r;
            float f9 = 0.5522848f * f8;
            this.f9034j.moveTo(f8, 0.0f);
            float f10 = i2;
            this.f9034j.lineTo(f10 - f8, 0.0f);
            com.jingdong.app.mall.home.n.h.g.f(0.0f, f10, f8, f9, this.f9034j);
            this.f9034j.lineTo(f10, f3 - f8);
            com.jingdong.app.mall.home.n.h.g.d(f10, f3, f8, f9, this.f9034j);
            this.f9034j.lineTo(f8, f3);
            com.jingdong.app.mall.home.n.h.g.a(f3, 0.0f, f8, f9, this.f9034j);
            this.f9034j.lineTo(0.0f, f8);
            com.jingdong.app.mall.home.n.h.g.c(0.0f, 0.0f, f8, f9, this.f9034j);
        }
        this.f9034j.close();
    }

    private boolean o(String str) {
        return TextUtils.equals(str, this.q) && this.f9036l == d.d(32);
    }

    private void p() {
        Info info;
        IconView iconView = this.f9037m;
        if (iconView == null || (info = this.f9032h) == null || !info.a) {
            return;
        }
        iconView.setVisibility(0);
        this.f9037m.a(this.f9032h);
        e.p(this.f9037m, this.f9032h.f9039c, e.b, new e.b() { // from class: com.jingdong.app.mall.home.deploy.view.layout.widget.IconLabel.1
            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onFailed(String str, View view) {
                IconLabel.this.f9037m.setVisibility(8);
                IconLabel.this.f9032h.a = false;
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onStart(String str, View view) {
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onSuccess(String str, View view) {
                IconLabel.this.f9037m.setVisibility(0);
                IconLabel.this.f9032h.a = true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        this.f9035k = null;
        this.q = null;
        this.f9036l = 0;
        postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(Bitmap bitmap, String str) {
        int d = d.d(32);
        Bitmap x = e.x(bitmap, d);
        byte[] y = e.y(x, 0.4f);
        if (y != null) {
            this.f9035k = new NinePatch(x, y, null);
            this.f9036l = d;
            this.q = str;
        } else {
            this.f9036l = 0;
            this.f9035k = null;
            this.q = null;
        }
        postInvalidate();
    }

    private void t(int i2, int i3) {
        String str = this.f9032h.f9047l;
        if (i2 != 0 && i3 != 0) {
            int paddingLeft = ((i2 - i3) - getPaddingLeft()) - getPaddingRight();
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            com.jingdong.app.mall.home.o.a.f.n(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            if (layoutParams2 != null) {
                paddingLeft = (paddingLeft - layoutParams2.leftMargin) - layoutParams2.rightMargin;
            }
            f fVar = this.f9032h.b;
            if (fVar != null) {
                paddingLeft = ((paddingLeft - fVar.v()) - fVar.l()) - fVar.m();
            }
            str = com.jingdong.app.mall.home.o.a.f.l(this.f9038n, paddingLeft, this.f9032h.f9047l);
        }
        this.f9038n.setText(com.jingdong.app.mall.home.o.a.f.j(6, str));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Info info = this.f9032h;
        if (info != null && info.f9042g) {
            l(canvas);
        }
        super.dispatchDraw(canvas);
    }

    public void h(Info info, int i2, int i3) {
        j(info);
        i(info, i2, i3, d.d(10));
    }

    public void i(Info info, int i2, int i3, int i4) {
        if (info == null || info.f9045j) {
            return;
        }
        setPadding(i4, 0, i4, 0);
        setVisibility(0);
        setAlpha(0.0f);
        this.f9032h = info;
        g();
        k(i2, i3);
        if (this.f9038n.getText().length() < 2) {
            setVisibility(8);
            return;
        }
        setAlpha(1.0f);
        p();
        q(info.f9043h);
    }

    public void q(final String str) {
        if (o(str)) {
            return;
        }
        if (!TextUtils.isEmpty(str) && !str.toLowerCase().endsWith(".gif")) {
            com.jingdong.app.mall.home.floor.ctrl.f.i(str, new a() { // from class: com.jingdong.app.mall.home.deploy.view.layout.widget.IconLabel.2
                @Override // com.jingdong.app.mall.home.t.a
                public void onBitmapWithUiNull(Bitmap bitmap) {
                    if (bitmap == null) {
                        IconLabel.this.r();
                    } else {
                        IconLabel.this.s(bitmap, str);
                    }
                }
            });
        } else {
            r();
        }
    }
}
