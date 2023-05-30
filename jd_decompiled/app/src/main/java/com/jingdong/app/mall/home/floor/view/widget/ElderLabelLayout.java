package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
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
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;

/* loaded from: classes4.dex */
public class ElderLabelLayout extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private Paint f10051g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f10052h;

    /* renamed from: i  reason: collision with root package name */
    private SimpleDraweeView f10053i;

    /* renamed from: j  reason: collision with root package name */
    private GradientTextView f10054j;

    /* renamed from: k  reason: collision with root package name */
    private f f10055k;

    /* renamed from: l  reason: collision with root package name */
    private f f10056l;

    /* renamed from: m  reason: collision with root package name */
    private int[] f10057m;

    /* renamed from: n  reason: collision with root package name */
    private int[] f10058n;
    private int o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements e.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            ElderLabelLayout elderLabelLayout = ElderLabelLayout.this;
            elderLabelLayout.c(elderLabelLayout.f10053i, false);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            ElderLabelLayout elderLabelLayout = ElderLabelLayout.this;
            elderLabelLayout.c(elderLabelLayout.f10053i, true);
        }
    }

    public ElderLabelLayout(Context context) {
        super(context);
        this.f10051g = new Paint(1);
        this.f10052h = new Paint(1);
        this.o = -588469;
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.f10053i = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar = new f(45, -1);
        this.f10055k = fVar;
        View view = this.f10053i;
        addView(view, fVar.u(view));
        GradientTextView gradientTextView = new GradientTextView(context);
        this.f10054j = gradientTextView;
        gradientTextView.setTextColor(-1);
        this.f10054j.setMaxLines(1);
        f fVar2 = new f(-2, -2);
        this.f10056l = fVar2;
        fVar2.J(0, -3, 0, -3);
        RelativeLayout.LayoutParams u = this.f10056l.u(this.f10054j);
        u.addRule(15);
        addView(this.f10054j, u);
    }

    private void d(Canvas canvas) {
        int width = getWidth();
        this.f10052h.setStyle(Paint.Style.STROKE);
        float d = d.d(2);
        this.f10052h.setStrokeWidth(d);
        int[] iArr = this.f10058n;
        if (iArr.length == 1) {
            this.f10052h.setColor(iArr[0]);
            this.f10052h.setShader(null);
        } else if (iArr.length > 1) {
            this.f10052h.setShader(new LinearGradient(0.0f, 0.0f, width, 0.0f, this.f10058n, (float[]) null, Shader.TileMode.CLAMP));
        }
        this.f10051g.setStyle(Paint.Style.FILL);
        int[] iArr2 = this.f10057m;
        if (iArr2.length == 1) {
            this.f10051g.setColor(iArr2[0]);
            this.f10051g.setShader(null);
        } else if (iArr2.length > 1) {
            this.f10051g.setShader(new LinearGradient(0.0f, 0.0f, width, 0.0f, this.f10057m, (float[]) null, Shader.TileMode.CLAMP));
        }
        float height = getHeight();
        float f2 = d / 2.0f;
        float f3 = width - f2;
        float f4 = height - f2;
        float f5 = (height - d) / 2.0f;
        canvas.drawRoundRect(new RectF(f2, f2, f3, f4), f5, f5, this.f10051g);
        canvas.drawRoundRect(new RectF(f2, f2, f3, f4), f5, f5, this.f10052h);
    }

    private void e(String str) {
        if (TextUtils.isEmpty(str)) {
            SimpleDraweeView simpleDraweeView = this.f10053i;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(8);
            }
            c(this.f10053i, false);
            return;
        }
        this.f10053i.setVisibility(0);
        e.p(this.f10053i, str, e.b, new a());
        f.c(this.f10053i, this.f10055k);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(com.jingdong.app.mall.home.r.e.f fVar) {
        e(fVar.Z());
        int[] p = m.p(fVar.Y(), this.o, true);
        h(p);
        this.f10054j.setTextGradient(GradientTextView.GradientType.LeftToRight, p);
        g.o(this.f10054j, 28);
        this.f10054j.setText(com.jingdong.app.mall.home.o.a.f.j(6, fVar.W()));
        int[] p2 = m.p(fVar.X(), -1, false);
        if (p2 == null || p2.length <= 0) {
            p2 = new int[]{-1285, 16772601};
        }
        f(p2);
    }

    protected void c(View view, boolean z) {
        if (view != null && view.getVisibility() == 0 && z) {
            this.f10056l.E(53, 0, 12, 0);
        } else {
            this.f10056l.E(12, 0, 12, 0);
        }
        f.d(this.f10054j, this.f10056l, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        try {
            d(canvas);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        super.dispatchDraw(canvas);
    }

    public void f(int[] iArr) {
        this.f10057m = iArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g(int i2) {
        this.o = i2;
    }

    public void h(int[] iArr) {
        this.f10058n = iArr;
    }
}
