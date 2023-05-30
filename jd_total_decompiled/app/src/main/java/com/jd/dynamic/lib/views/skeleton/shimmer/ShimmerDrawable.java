package com.jd.dynamic.lib.views.skeleton.shimmer;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes13.dex */
public final class ShimmerDrawable extends Drawable {
    private final ValueAnimator.AnimatorUpdateListener a = new ValueAnimator.AnimatorUpdateListener() { // from class: com.jd.dynamic.lib.views.skeleton.shimmer.ShimmerDrawable.1
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ShimmerDrawable.this.invalidateSelf();
        }
    };
    private final Paint b;

    /* renamed from: c  reason: collision with root package name */
    private final Rect f2592c;
    private final Matrix d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    private ValueAnimator f2593e;

    /* renamed from: f  reason: collision with root package name */
    private float f2594f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    private Shimmer f2595g;

    public ShimmerDrawable() {
        Paint paint = new Paint();
        this.b = paint;
        this.f2592c = new Rect();
        this.d = new Matrix();
        this.f2594f = -1.0f;
        paint.setAntiAlias(true);
    }

    private float a(float f2, float f3, float f4) {
        return f2 + ((f3 - f2) * f4);
    }

    private void c() {
        boolean z;
        if (this.f2595g == null) {
            return;
        }
        ValueAnimator valueAnimator = this.f2593e;
        if (valueAnimator != null) {
            z = valueAnimator.isStarted();
            this.f2593e.cancel();
            this.f2593e.removeAllUpdateListeners();
        } else {
            z = false;
        }
        Shimmer shimmer = this.f2595g;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, ((float) (shimmer.t / shimmer.s)) + 1.0f);
        this.f2593e = ofFloat;
        ofFloat.setInterpolator(new LinearInterpolator());
        this.f2593e.setRepeatMode(this.f2595g.r);
        this.f2593e.setStartDelay(this.f2595g.u);
        this.f2593e.setRepeatCount(this.f2595g.q);
        ValueAnimator valueAnimator2 = this.f2593e;
        Shimmer shimmer2 = this.f2595g;
        valueAnimator2.setDuration(shimmer2.s + shimmer2.t);
        this.f2593e.addUpdateListener(this.a);
        if (z) {
            this.f2593e.start();
        }
    }

    private void d() {
        Shimmer shimmer;
        Shader radialGradient;
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        if (width == 0 || height == 0 || (shimmer = this.f2595g) == null) {
            return;
        }
        int a = shimmer.a(width);
        int c2 = this.f2595g.c(height);
        Shimmer shimmer2 = this.f2595g;
        boolean z = true;
        if (shimmer2.f2583f != 1) {
            int i2 = shimmer2.f2581c;
            if (i2 != 1 && i2 != 3) {
                z = false;
            }
            if (z) {
                a = 0;
            }
            if (!z) {
                c2 = 0;
            }
            float f2 = c2;
            Shimmer shimmer3 = this.f2595g;
            radialGradient = new LinearGradient(0.0f, 0.0f, a, f2, shimmer3.b, shimmer3.a, Shader.TileMode.CLAMP);
        } else {
            float f3 = c2 / 2.0f;
            double max = Math.max(a, c2);
            double sqrt = Math.sqrt(2.0d);
            Double.isNaN(max);
            float f4 = (float) (max / sqrt);
            Shimmer shimmer4 = this.f2595g;
            radialGradient = new RadialGradient(a / 2.0f, f3, f4, shimmer4.b, shimmer4.a, Shader.TileMode.CLAMP);
        }
        this.b.setShader(radialGradient);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        Shimmer shimmer;
        ValueAnimator valueAnimator = this.f2593e;
        if (valueAnimator == null || valueAnimator.isStarted() || (shimmer = this.f2595g) == null || !shimmer.o || getCallback() == null) {
            return;
        }
        this.f2593e.start();
    }

    public void clearStaticAnimationProgress() {
        setStaticAnimationProgress(-1.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        float a;
        float a2;
        if (this.f2595g == null || this.b.getShader() == null) {
            return;
        }
        float tan = (float) Math.tan(Math.toRadians(this.f2595g.f2590m));
        float height = this.f2592c.height() + (this.f2592c.width() * tan);
        float width = this.f2592c.width() + (tan * this.f2592c.height());
        float f2 = this.f2594f;
        float f3 = 0.0f;
        if (f2 < 0.0f) {
            ValueAnimator valueAnimator = this.f2593e;
            f2 = valueAnimator != null ? ((Float) valueAnimator.getAnimatedValue()).floatValue() : 0.0f;
        }
        int i2 = this.f2595g.f2581c;
        if (i2 != 1) {
            if (i2 == 2) {
                a2 = a(width, -width, f2);
            } else if (i2 != 3) {
                a2 = a(-width, width, f2);
            } else {
                a = a(height, -height, f2);
            }
            f3 = a2;
            a = 0.0f;
        } else {
            a = a(-height, height, f2);
        }
        this.d.reset();
        this.d.setRotate(this.f2595g.f2590m, this.f2592c.width() / 2.0f, this.f2592c.height() / 2.0f);
        this.d.preTranslate(f3, a);
        this.b.getShader().setLocalMatrix(this.d);
        canvas.drawRect(this.f2592c, this.b);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Shimmer shimmer = this.f2595g;
        return (shimmer == null || !(shimmer.f2591n || shimmer.p)) ? -1 : -3;
    }

    @Nullable
    public Shimmer getShimmer() {
        return this.f2595g;
    }

    public boolean isShimmerRunning() {
        ValueAnimator valueAnimator = this.f2593e;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public boolean isShimmerStarted() {
        ValueAnimator valueAnimator = this.f2593e;
        return valueAnimator != null && valueAnimator.isStarted();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f2592c.set(rect);
        d();
        b();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public void setShimmer(@Nullable Shimmer shimmer) {
        this.f2595g = shimmer;
        if (shimmer != null) {
            this.b.setXfermode(new PorterDuffXfermode(this.f2595g.p ? PorterDuff.Mode.DST_IN : PorterDuff.Mode.SRC_IN));
        }
        d();
        c();
        invalidateSelf();
    }

    public void setStaticAnimationProgress(float f2) {
        if (Float.compare(f2, this.f2594f) != 0) {
            if (f2 >= 0.0f || this.f2594f >= 0.0f) {
                this.f2594f = Math.min(f2, 1.0f);
                invalidateSelf();
            }
        }
    }

    public void startShimmer() {
        if (this.f2593e == null || isShimmerStarted() || getCallback() == null) {
            return;
        }
        this.f2593e.start();
    }

    public void stopShimmer() {
        if (this.f2593e == null || !isShimmerStarted()) {
            return;
        }
        this.f2593e.cancel();
    }
}
