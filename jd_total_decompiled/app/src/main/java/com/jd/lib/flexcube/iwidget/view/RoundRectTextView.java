package com.jd.lib.flexcube.iwidget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* loaded from: classes14.dex */
public class RoundRectTextView extends AppCompatTextView {

    /* renamed from: g  reason: collision with root package name */
    private RectF f4226g;

    /* renamed from: h  reason: collision with root package name */
    private float f4227h;

    /* renamed from: i  reason: collision with root package name */
    private float f4228i;

    /* renamed from: j  reason: collision with root package name */
    private RectF f4229j;

    /* renamed from: k  reason: collision with root package name */
    private Paint f4230k;

    /* renamed from: l  reason: collision with root package name */
    private int f4231l;

    /* renamed from: m  reason: collision with root package name */
    private float f4232m;

    /* renamed from: n  reason: collision with root package name */
    private int f4233n;

    public RoundRectTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4226g = new RectF();
        this.f4227h = 0.0f;
        this.f4228i = 0.0f;
        this.f4231l = 0;
        this.f4232m = 0.0f;
        this.f4233n = -1;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        RectF rectF;
        RectF rectF2 = this.f4226g;
        rectF2.left = 0.0f;
        rectF2.top = 0.0f;
        rectF2.right = getWidth();
        this.f4226g.bottom = getHeight();
        getPaint().setColor(this.f4231l);
        RectF rectF3 = this.f4226g;
        float f2 = this.f4227h;
        canvas.drawRoundRect(rectF3, f2, f2, getPaint());
        float f3 = this.f4228i;
        if (f3 > 0.0f && this.f4230k != null && (rectF = this.f4229j) != null) {
            rectF.left = f3;
            rectF.top = f3;
            rectF.right = getWidth() - this.f4228i;
            this.f4229j.bottom = getHeight() - this.f4228i;
            RectF rectF4 = this.f4229j;
            float f4 = this.f4227h;
            canvas.drawRoundRect(rectF4, f4, f4, this.f4230k);
        }
        if (this.f4232m > 0.0f) {
            getPaint().setColor(this.f4233n);
            float f5 = this.f4232m;
            canvas.drawRect(f5, f5, getWidth() - this.f4232m, getHeight() - this.f4232m, getPaint());
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        this.f4231l = i2;
        this.f4232m = 0.0f;
    }

    public RoundRectTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f4226g = new RectF();
        this.f4227h = 0.0f;
        this.f4228i = 0.0f;
        this.f4231l = 0;
        this.f4232m = 0.0f;
        this.f4233n = -1;
    }
}
