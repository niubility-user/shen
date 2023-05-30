package com.jingdong.app.mall.home.floor.view.linefloor.base;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.text.TextUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.n.h.f;

/* loaded from: classes4.dex */
public class BaseAnimateLayout extends RelativeLayout {

    /* renamed from: m  reason: collision with root package name */
    public static String f9826m = "1";

    /* renamed from: n  reason: collision with root package name */
    public static String f9827n = "2";

    /* renamed from: g  reason: collision with root package name */
    private ValueAnimator f9828g;

    /* renamed from: h  reason: collision with root package name */
    private ValueAnimator f9829h;

    /* renamed from: i  reason: collision with root package name */
    private LinearGradient f9830i;

    /* renamed from: j  reason: collision with root package name */
    private final Paint f9831j;

    /* renamed from: k  reason: collision with root package name */
    private final Path f9832k;

    /* renamed from: l  reason: collision with root package name */
    private float f9833l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            BaseAnimateLayout.this.m(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            BaseAnimateLayout.this.setScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public BaseAnimateLayout(Context context) {
        super(context);
        this.f9831j = new Paint(1);
        this.f9832k = new Path();
    }

    private void j(int i2) {
        if (this.f9828g == null) {
            f fVar = new f();
            fVar.b(1.0f, 0.9f, 1.0f, 0.95f, 1.0f);
            fVar.d(800L);
            fVar.f(i2 == 0 ? 0L : 100L);
            fVar.e(new LinearInterpolator());
            fVar.g(new b());
            this.f9828g = fVar.a();
        }
    }

    private void k() {
        if (this.f9829h == null) {
            f fVar = new f();
            fVar.b(0.0f, 1.0f);
            fVar.d(1100L);
            fVar.e(new AccelerateDecelerateInterpolator());
            fVar.g(new a());
            this.f9829h = fVar.a();
        }
    }

    private void l() {
        int height = getHeight();
        if (height <= 0) {
            return;
        }
        int d = d.d(78);
        double d2 = height;
        double tan = Math.tan(0.6108652381980153d);
        Double.isNaN(d2);
        float f2 = (float) (d2 * tan);
        this.f9833l = 0.0f;
        this.f9832k.reset();
        float f3 = -d;
        this.f9832k.moveTo(f3, 0.0f);
        this.f9832k.lineTo(0.0f, 0.0f);
        float f4 = height;
        this.f9832k.lineTo(d - f2, f4);
        this.f9832k.lineTo(-f2, f4);
        this.f9832k.lineTo(f3, 0.0f);
        this.f9832k.close();
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, f4, new int[]{-1275068417, ViewCompat.MEASURED_SIZE_MASK}, (float[]) null, Shader.TileMode.CLAMP);
        this.f9830i = linearGradient;
        this.f9831j.setShader(linearGradient);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(float f2) {
        if (this.f9830i != null) {
            float width = f2 * (getWidth() + getHeight());
            Path path = this.f9832k;
            float f3 = this.f9833l;
            path.offset(f3 > 0.0f ? width - f3 : 0.0f, 0.0f);
            this.f9833l = width;
            postInvalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f9831j.getShader() != null) {
            canvas.drawPath(this.f9832k, this.f9831j);
        }
    }

    public void n() {
        ValueAnimator valueAnimator = this.f9828g;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.f9829h;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        this.f9831j.setShader(null);
        setScaleX(1.0f);
        setScaleY(1.0f);
    }

    public void o(String str, int i2) {
        n();
        if (TextUtils.equals(str, f9826m)) {
            setPivotX(getWidth() >> 1);
            setPivotY(getHeight());
            j(i2);
            this.f9828g.start();
        }
    }

    public void p(String str) {
        n();
        if (TextUtils.equals(str, f9827n)) {
            l();
            k();
            this.f9829h.start();
        }
    }
}
