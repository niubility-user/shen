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
public class CaSmileLineB extends View {

    /* renamed from: g  reason: collision with root package name */
    private Paint f8861g;

    /* renamed from: h  reason: collision with root package name */
    private RectF f8862h;

    /* renamed from: i  reason: collision with root package name */
    private ValueAnimator f8863i;

    /* renamed from: j  reason: collision with root package name */
    private int f8864j;

    /* renamed from: k  reason: collision with root package name */
    private int f8865k;

    /* renamed from: l  reason: collision with root package name */
    private int f8866l;

    /* renamed from: m  reason: collision with root package name */
    private float f8867m;

    /* loaded from: classes4.dex */
    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CaSmileLineB.this.c(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public CaSmileLineB(Context context) {
        super(context);
        this.f8861g = new Paint(1);
        this.f8862h = new RectF();
        this.f8861g.setAntiAlias(true);
        this.f8861g.setStrokeCap(Paint.Cap.ROUND);
        this.f8861g.setStyle(Paint.Style.STROKE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(float f2) {
        this.f8867m = f2;
        postInvalidate();
    }

    public void b(int i2, int i3, int i4, int i5) {
        this.f8861g.setShader(new LinearGradient(0.0f, 0.0f, i3, 0.0f, i2, i2, Shader.TileMode.CLAMP));
        this.f8864j = i3;
        this.f8865k = i4;
        this.f8866l = i5;
        this.f8861g.setStrokeWidth(i5);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() >> 1;
        this.f8862h.set(width - (this.f8864j >> 1), getHeight() - this.f8865k, width + (this.f8864j >> 1), (getHeight() - (this.f8866l >> 1)) - 0.5f);
        canvas.drawArc(this.f8862h, 135.0f, this.f8867m, false, this.f8861g);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        ValueAnimator valueAnimator = this.f8863i;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.f8867m, i2 == 0 ? -90.0f : 0.0f);
        this.f8863i = ofFloat;
        ofFloat.addUpdateListener(new a());
        this.f8863i.setInterpolator(new AccelerateInterpolator());
        this.f8863i.setDuration(((i2 == 0 ? this.f8867m + 90.0f : -this.f8867m) * 200.0f) / 90.0f);
        this.f8863i.start();
    }
}
