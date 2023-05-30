package com.jingdong.app.mall.home.floor.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.view.widget.LinerPagerCursor;

/* loaded from: classes4.dex */
public class AnimationLinerPagerCursor extends LinerPagerCursor {
    private int A;
    private int B;
    private int t;
    private float u;
    private Paint v;
    private Paint w;
    private float x;
    private boolean y;
    private int z;

    public AnimationLinerPagerCursor(Context context) {
        super(context);
        this.y = true;
    }

    private boolean j(int i2) {
        return this.z == this.f10116m - 1 && i2 == 0;
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.LinerPagerCursor
    public void a(Canvas canvas) {
        float f2;
        Paint paint;
        float f3;
        Paint paint2;
        this.q.top = this.w.getStrokeWidth() / 2.0f;
        RectF rectF = this.q;
        rectF.bottom = this.f10112i - rectF.top;
        rectF.left = 0.0f;
        rectF.right = 0.0f;
        int i2 = this.z + 1;
        int i3 = 0;
        while (i3 < this.f10116m) {
            RectF rectF2 = this.q;
            rectF2.left = rectF2.right + (i3 == 0 ? 0 : this.f10113j) + (this.w.getStrokeWidth() / 2.0f);
            if (this.y) {
                RectF rectF3 = this.q;
                float strokeWidth = rectF3.left - this.w.getStrokeWidth();
                if (i3 == this.z) {
                    f3 = this.f10111h - this.u;
                } else {
                    f3 = (i3 == i2 || j(i3)) ? this.f10110g + this.u : this.f10110g;
                }
                rectF3.right = strokeWidth + f3;
                RectF rectF4 = this.q;
                float strokeWidth2 = (this.f10112i - this.w.getStrokeWidth()) / 2.0f;
                float strokeWidth3 = (this.f10112i - this.w.getStrokeWidth()) / 2.0f;
                if (i3 == this.z) {
                    paint2 = this.p;
                } else {
                    paint2 = (i3 == i2 || j(i3)) ? this.v : this.f10117n;
                }
                canvas.drawRoundRect(rectF4, strokeWidth2, strokeWidth3, paint2);
            } else {
                RectF rectF5 = this.q;
                float strokeWidth4 = rectF5.left - this.w.getStrokeWidth();
                if (i3 != i2 && !j(i3)) {
                    f2 = i3 == this.z ? this.f10110g + this.u : this.f10110g;
                } else {
                    f2 = this.f10111h - this.u;
                }
                rectF5.right = strokeWidth4 + f2;
                RectF rectF6 = this.q;
                float strokeWidth5 = (this.f10112i - this.w.getStrokeWidth()) / 2.0f;
                float strokeWidth6 = (this.f10112i - this.w.getStrokeWidth()) / 2.0f;
                if (i3 != i2 && !j(i3)) {
                    paint = i3 == this.z ? this.v : this.f10117n;
                } else {
                    paint = this.p;
                }
                canvas.drawRoundRect(rectF6, strokeWidth5, strokeWidth6, paint);
            }
            canvas.drawRoundRect(this.q, (this.f10112i - this.w.getStrokeWidth()) / 2.0f, (this.f10112i - this.w.getStrokeWidth()) / 2.0f, this.w);
            i3++;
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.LinerPagerCursor
    public void c(int i2, float f2, int i3) {
        int intValue;
        int intValue2;
        this.z = i2;
        float f3 = this.x;
        if (f3 > f2) {
            this.y = false;
        } else if (f3 < f2) {
            this.y = true;
        }
        this.x = f2;
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        if (this.y) {
            this.u = this.t * f2;
            intValue = ((Integer) argbEvaluator.evaluate(f2, Integer.valueOf(this.A), Integer.valueOf(this.B))).intValue();
            intValue2 = ((Integer) argbEvaluator.evaluate(f2, Integer.valueOf(this.B), Integer.valueOf(this.A))).intValue();
        } else {
            float f4 = 1.0f - f2;
            this.u = this.t * f4;
            intValue = ((Integer) argbEvaluator.evaluate(f4, Integer.valueOf(this.A), Integer.valueOf(this.B))).intValue();
            intValue2 = ((Integer) argbEvaluator.evaluate(f4, Integer.valueOf(this.B), Integer.valueOf(this.A))).intValue();
        }
        this.v.setColor(intValue);
        this.p.setColor(intValue2);
        postInvalidate();
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.LinerPagerCursor
    public void d(int i2) {
        this.z = i2;
        this.u = 0.0f;
        super.d(i2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.LinerPagerCursor
    public void f(int i2, int i3, int i4) {
        if (this.f10117n == null) {
            Paint paint = new Paint();
            this.f10117n = paint;
            paint.setAntiAlias(true);
            Paint paint2 = new Paint();
            this.p = paint2;
            paint2.setAntiAlias(true);
            Paint paint3 = new Paint();
            this.v = paint3;
            paint3.setAntiAlias(true);
            Paint paint4 = new Paint();
            this.w = paint4;
            paint4.setAntiAlias(true);
            this.w.setStyle(Paint.Style.STROKE);
        }
        this.w.setStrokeWidth(d.d(1) + 1);
        this.A = i2;
        this.B = i4;
        this.f10117n.setColor(i2);
        this.p.setColor(this.B);
        this.v.setColor(this.A);
        this.w.setColor(i3);
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.LinerPagerCursor
    public void h(int i2, int i3, int i4, int i5) {
        this.t = i3 - i2;
        super.h(i2, i3, i4, i5);
    }

    public AnimationLinerPagerCursor(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.y = true;
    }

    public AnimationLinerPagerCursor(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.y = true;
    }
}
