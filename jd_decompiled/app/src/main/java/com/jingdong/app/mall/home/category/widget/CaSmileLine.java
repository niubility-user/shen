package com.jingdong.app.mall.home.category.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

/* loaded from: classes4.dex */
public class CaSmileLine extends View {

    /* renamed from: g  reason: collision with root package name */
    private Paint f8853g;

    /* renamed from: h  reason: collision with root package name */
    private RectF f8854h;

    /* renamed from: i  reason: collision with root package name */
    private ValueAnimator f8855i;

    /* renamed from: j  reason: collision with root package name */
    private int f8856j;

    /* renamed from: k  reason: collision with root package name */
    private int f8857k;

    /* renamed from: l  reason: collision with root package name */
    private int f8858l;

    /* renamed from: m  reason: collision with root package name */
    private float f8859m;

    /* loaded from: classes4.dex */
    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CaSmileLine.this.c(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public CaSmileLine(Context context) {
        super(context);
        this.f8853g = new Paint(1);
        this.f8854h = new RectF();
        this.f8853g.setAntiAlias(true);
        this.f8853g.setStrokeCap(Paint.Cap.ROUND);
        this.f8853g.setStyle(Paint.Style.STROKE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(float f2) {
        this.f8859m = f2;
        postInvalidate();
    }

    public void b(int i2, int i3, int i4, int i5) {
        this.f8853g.setShader(new LinearGradient(0.0f, 0.0f, i3, 0.0f, new int[]{(-1711276033) & i2, i2 & 872415231}, (float[]) null, Shader.TileMode.CLAMP));
        this.f8856j = i3;
        this.f8857k = i4;
        this.f8858l = i5;
        this.f8853g.setStrokeWidth(i5);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() >> 1;
        this.f8854h.set(width - (this.f8856j >> 1), getHeight() - this.f8857k, width + (this.f8856j >> 1), (getHeight() - (this.f8858l >> 1)) - 0.5f);
        canvas.drawArc(this.f8854h, 135.0f, this.f8859m, false, this.f8853g);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        ValueAnimator valueAnimator = this.f8855i;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.f8859m, i2 == 0 ? -90.0f : 0.0f);
        this.f8855i = ofFloat;
        ofFloat.addUpdateListener(new a());
        this.f8855i.setInterpolator(new AccelerateInterpolator());
        this.f8855i.setDuration(((i2 == 0 ? this.f8859m + 90.0f : -this.f8859m) * 200.0f) / 90.0f);
        this.f8855i.start();
    }
}
