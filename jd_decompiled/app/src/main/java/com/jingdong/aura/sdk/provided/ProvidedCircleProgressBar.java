package com.jingdong.aura.sdk.provided;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.jingdong.aura.sdk.update.R;
import com.jingdong.aura.sdk.update.b.f;

/* loaded from: classes4.dex */
public class ProvidedCircleProgressBar extends View {
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private float f12227c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f12228e;

    /* renamed from: f  reason: collision with root package name */
    private int f12229f;

    /* renamed from: g  reason: collision with root package name */
    private Paint f12230g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f12231h;

    /* renamed from: i  reason: collision with root package name */
    private int[] f12232i;

    public ProvidedCircleProgressBar(Context context) {
        this(context, null);
    }

    public ProvidedCircleProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProvidedCircleProgressBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = 100;
        this.b = 0;
        this.f12232i = new int[]{Color.parseColor("#27B197"), Color.parseColor("#00A6D5")};
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ProvidedCircleProgressBar, i2, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = obtainStyledAttributes.getIndex(i3);
            if (index == R.styleable.ProvidedCircleProgressBar_firstColor) {
                this.d = obtainStyledAttributes.getColor(index, -3355444);
            } else if (index == R.styleable.ProvidedCircleProgressBar_secondColor) {
                this.f12228e = obtainStyledAttributes.getColor(index, -16776961);
            } else if (index == R.styleable.ProvidedCircleProgressBar_circleWidth) {
                this.f12229f = obtainStyledAttributes.getDimensionPixelSize(index, (int) TypedValue.applyDimension(1, 6.0f, f.a(context)));
            }
        }
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.f12230g = paint;
        paint.setAntiAlias(true);
        this.f12230g.setDither(true);
        this.f12230g.setStrokeWidth(this.f12229f);
        Paint paint2 = new Paint();
        this.f12231h = paint2;
        paint2.setAntiAlias(true);
        this.f12231h.setDither(true);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int width = getWidth() / 2;
        int i2 = width - (this.f12229f / 2);
        this.f12230g.setShader(null);
        this.f12230g.setColor(this.d);
        this.f12230g.setStyle(Paint.Style.STROKE);
        float f2 = width;
        canvas.drawCircle(f2, f2, i2, this.f12230g);
        float f3 = width - i2;
        float f4 = width + i2;
        RectF rectF = new RectF(f3, f3, f4, f4);
        int i3 = this.f12229f;
        this.f12230g.setShader(new LinearGradient(i3, i3, getMeasuredWidth() - this.f12229f, getMeasuredHeight() - this.f12229f, this.f12232i, (float[]) null, Shader.TileMode.MIRROR));
        this.f12230g.setColor(this.f12228e);
        this.f12230g.setStrokeCap(Paint.Cap.ROUND);
        float f5 = ((this.b * 360.0f) / this.a) * 1.0f;
        this.f12227c = f5;
        canvas.drawArc(rectF, 0.0f, f5, false, this.f12230g);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        setMeasuredDimension(Math.min(size, size2), Math.min(size, size2));
    }

    public void setCircleWidth(int i2) {
        int applyDimension = (int) TypedValue.applyDimension(1, i2, f.a(getContext()));
        this.f12229f = applyDimension;
        this.f12230g.setStrokeWidth(applyDimension);
        invalidate();
    }

    public void setColorArray(int[] iArr) {
        this.f12232i = iArr;
        invalidate();
    }

    public void setFirstColor(int i2) {
        this.d = i2;
        this.f12230g.setColor(i2);
        invalidate();
    }

    public void setProgress(int i2) {
        int i3 = (i2 * this.a) / 100;
        if (i3 < 0) {
            i3 = 0;
        }
        this.b = i3 <= 100 ? i3 : 100;
        invalidate();
    }

    public void setSecondColor(int i2) {
        this.f12228e = i2;
        this.f12230g.setColor(i2);
        invalidate();
    }
}
