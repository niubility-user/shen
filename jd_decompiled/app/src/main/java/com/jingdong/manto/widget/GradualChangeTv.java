package com.jingdong.manto.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* loaded from: classes16.dex */
public class GradualChangeTv extends AppCompatTextView {
    public Paint a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public int f14327c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    float f14328e;

    /* renamed from: f  reason: collision with root package name */
    float f14329f;

    /* renamed from: g  reason: collision with root package name */
    int f14330g;

    public GradualChangeTv(Context context) {
        this(context, null);
    }

    public GradualChangeTv(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GradualChangeTv(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Paint paint = new Paint();
        this.a = paint;
        this.b = "";
        this.f14327c = -7829368;
        this.d = -16777216;
        this.f14328e = 0.0f;
        this.f14329f = 54.0f;
        this.f14330g = 1;
        paint.setAntiAlias(true);
        this.a.setTextSize(this.f14329f);
        this.a.setTypeface(Typeface.create(Typeface.SANS_SERIF, 1));
    }

    private void a(Canvas canvas, int i2, int i3, float f2, float f3) {
        this.a.setColor(this.f14327c);
        canvas.save();
        canvas.drawText(this.b, i2 - (f2 / 2.0f), i3 - (f3 / 2.0f), this.a);
        canvas.restore();
    }

    private void b(Canvas canvas, int i2, int i3, float f2, float f3) {
        this.a.setColor(this.d);
        canvas.save();
        float f4 = i2;
        float f5 = f4 - (f2 / 2.0f);
        canvas.clipRect(f4 + (this.a.measureText(this.b) / 2.0f), 0.0f, (f2 * (1.0f - this.f14328e)) + f5, getHeight());
        canvas.drawText(this.b, f5, i3 - (f3 / 2.0f), this.a);
        canvas.restore();
    }

    private void c(Canvas canvas, int i2, int i3, float f2, float f3) {
        this.a.setColor(this.d);
        canvas.save();
        float f4 = i2 - (f2 / 2.0f);
        float f5 = i3 - (f3 / 2.0f);
        float f6 = (int) f4;
        canvas.clipRect(f6, 0.0f, (this.f14328e * f2) + f6, getHeight());
        this.a.setSubpixelText(true);
        if (this.a.breakText(this.b, false, f2 * this.f14328e, new float[0]) >= this.b.length()) {
            this.b.length();
        }
        canvas.drawText(this.b, f4, f5, this.a);
        canvas.restore();
    }

    public void a(int i2, int i3) {
        this.b = this.b;
        this.f14327c = i2;
        this.d = i3;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (TextUtils.isEmpty(this.b)) {
            return;
        }
        float measureText = this.a.measureText(this.b);
        float descent = this.a.descent() + this.a.ascent();
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        this.a.setTextSize(this.f14329f);
        a(canvas, width, height, measureText, descent);
        int i2 = this.f14330g;
        if (i2 == 1) {
            c(canvas, width, height, measureText, descent);
        } else if (i2 == 0) {
            b(canvas, width, height, measureText, descent);
        }
    }

    public void setGradualText(String str) {
        this.b = str;
    }

    public void setGradualTextSize(float f2) {
        this.f14329f = f2;
        this.a.setTextSize(f2);
    }

    public void setProgressAndInvalidate(float f2) {
        this.f14328e = f2;
        invalidate();
    }

    public void setType(int i2) {
        this.f14330g = i2;
    }
}
