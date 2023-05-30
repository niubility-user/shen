package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes4.dex */
public class RoundRectTextView extends TextView {

    /* renamed from: g  reason: collision with root package name */
    private RectF f11990g;

    /* renamed from: h  reason: collision with root package name */
    private float f11991h;

    /* renamed from: i  reason: collision with root package name */
    private int f11992i;

    /* renamed from: j  reason: collision with root package name */
    private float f11993j;

    public RoundRectTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11990g = new RectF();
        this.f11991h = 0.0f;
        this.f11992i = -1;
        this.f11993j = 0.0f;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        RectF rectF = this.f11990g;
        rectF.left = 0.0f;
        rectF.top = 0.0f;
        rectF.right = getWidth();
        this.f11990g.bottom = getHeight();
        getPaint().setColor(this.f11992i);
        RectF rectF2 = this.f11990g;
        float f2 = this.f11991h;
        canvas.drawRoundRect(rectF2, f2, f2, getPaint());
        if (this.f11993j > 0.0f) {
            getPaint().setColor(-1);
            float f3 = this.f11993j;
            canvas.drawRect(f3, f3, getWidth() - this.f11993j, getHeight() - this.f11993j, getPaint());
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        this.f11992i = i2;
        this.f11993j = 0.0f;
    }

    public RoundRectTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f11990g = new RectF();
        this.f11991h = 0.0f;
        this.f11992i = -1;
        this.f11993j = 0.0f;
    }
}
