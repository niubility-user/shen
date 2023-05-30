package com.jingdong.app.mall.home.category.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.Pair;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.FloatRange;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.ctrl.f;

/* loaded from: classes4.dex */
public class CaProgress extends View {

    /* renamed from: g  reason: collision with root package name */
    private Paint f8830g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f8831h;

    /* renamed from: i  reason: collision with root package name */
    private Pair<Integer, Bitmap> f8832i;

    /* renamed from: j  reason: collision with root package name */
    private ValueAnimator f8833j;

    /* renamed from: k  reason: collision with root package name */
    private int f8834k;

    /* renamed from: l  reason: collision with root package name */
    private int f8835l;

    /* renamed from: m  reason: collision with root package name */
    private float f8836m;

    /* renamed from: n  reason: collision with root package name */
    int f8837n;
    int o;

    /* loaded from: classes4.dex */
    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CaProgress.this.e(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public CaProgress(Context context) {
        super(context);
        this.f8830g = new Paint(1);
        this.f8831h = new Paint(1);
    }

    private Bitmap b(Bitmap bitmap) {
        Bitmap bitmap2 = null;
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (height > 0 && width > 0) {
            Matrix matrix = new Matrix();
            matrix.postScale(d.d(this.f8834k) / width, d.d(this.f8835l) / height);
            bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
        return bitmap2;
    }

    public void a(int i2, int i3) {
        this.f8834k = i2;
        this.f8835l = i3;
    }

    public void c(boolean z, int... iArr) {
        this.f8831h.setStrokeCap(z ? Paint.Cap.ROUND : Paint.Cap.SQUARE);
        int[] iArr2 = iArr.length == 1 ? new int[]{iArr[0], iArr[0]} : iArr;
        if (iArr2.length > 1) {
            this.f8831h.setShader(new LinearGradient(0.0f, 0.0f, d.d(this.f8834k), 0.0f, iArr2, (float[]) null, Shader.TileMode.CLAMP));
        }
    }

    public void d(int... iArr) {
        c(true, iArr);
    }

    public void e(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.f8836m = f2;
        postInvalidate();
    }

    public void f(int... iArr) {
        g(-1, iArr, true);
    }

    public void g(int i2, int[] iArr, boolean z) {
        this.f8830g.setStrokeCap(z ? Paint.Cap.ROUND : Paint.Cap.SQUARE);
        int[] iArr2 = iArr.length == 1 ? new int[]{iArr[0], iArr[0]} : iArr;
        if (this.f8834k <= 0 || this.f8835l <= 0) {
            return;
        }
        Pair<Integer, Bitmap> pair = this.f8832i;
        Bitmap bitmap = null;
        if (pair != null && i2 != -1) {
            if (d.f9279g + i2 == ((Integer) pair.first).intValue()) {
                bitmap = (Bitmap) this.f8832i.second;
            } else {
                ((Bitmap) this.f8832i.second).recycle();
                this.f8832i = null;
            }
        }
        if (bitmap == null && i2 != -1) {
            bitmap = b(f.c(getResources(), i2));
        }
        if (bitmap != null) {
            this.f8832i = new Pair<>(Integer.valueOf(i2 + d.f9279g), bitmap);
            Paint paint = this.f8830g;
            Shader.TileMode tileMode = Shader.TileMode.REPEAT;
            paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
        } else if (iArr2.length > 1) {
            this.f8830g.setShader(new LinearGradient(0.0f, 0.0f, d.d(this.f8834k), 0.0f, iArr2, (float[]) null, Shader.TileMode.CLAMP));
        }
        if (this.f8836m > 0.0f) {
            postInvalidate();
        }
    }

    public void h(@FloatRange(from = 0.0d, to = 1.0d) float f2, long j2) {
        ValueAnimator valueAnimator = this.f8833j;
        if (valueAnimator == null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[0]);
            this.f8833j = ofFloat;
            ofFloat.addUpdateListener(new a());
        } else {
            valueAnimator.cancel();
        }
        e(0.0f);
        this.f8833j.setFloatValues(0.0f, f2);
        this.f8833j.setInterpolator(new DecelerateInterpolator());
        this.f8833j.setDuration(j2);
        this.f8833j.setStartDelay(200L);
        this.f8833j.start();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i2 = this.o;
        canvas.drawLine(i2, i2, this.f8837n + i2, i2, this.f8831h);
        float f2 = this.f8836m;
        if (f2 > 0.0f) {
            int i3 = this.o;
            canvas.drawLine(i3, i3, (this.f8837n * f2) + i3, i3, this.f8830g);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int d = d.d(this.f8835l);
        this.f8837n = (d.d(this.f8834k) - d) - 1;
        this.o = d >> 1;
        float f2 = d;
        this.f8831h.setStrokeWidth(f2);
        this.f8830g.setStrokeWidth(f2);
    }
}
