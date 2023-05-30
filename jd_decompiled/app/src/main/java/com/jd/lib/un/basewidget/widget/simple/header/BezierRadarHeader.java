package com.jd.lib.un.basewidget.widget.simple.header;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.jd.lib.un.basewidget.widget.simple.abs.AbsRefreshInternal;
import com.jd.lib.un.basewidget.widget.simple.c.c;
import com.jd.lib.un.basewidget.widget.simple.c.f;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes16.dex */
public class BezierRadarHeader extends AbsRefreshInternal implements c {
    protected float A;
    protected Animator B;
    protected RectF C;

    /* renamed from: j  reason: collision with root package name */
    protected int f5789j;

    /* renamed from: k  reason: collision with root package name */
    protected int f5790k;

    /* renamed from: l  reason: collision with root package name */
    protected boolean f5791l;

    /* renamed from: m  reason: collision with root package name */
    protected boolean f5792m;

    /* renamed from: n  reason: collision with root package name */
    protected boolean f5793n;
    protected Path o;
    protected Paint p;
    protected int q;
    protected int r;
    protected int s;
    protected float t;
    protected float u;
    protected float v;
    protected float w;
    protected int x;
    protected float y;
    protected float z;

    /* loaded from: classes16.dex */
    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[com.jd.lib.un.basewidget.widget.simple.a.c.values().length];
            a = iArr;
            try {
                iArr[com.jd.lib.un.basewidget.widget.simple.a.c.None.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[com.jd.lib.un.basewidget.widget.simple.a.c.PullDownToRefresh.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes16.dex */
    protected class b implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        byte f5794g;

        b(byte b) {
            this.f5794g = b;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            byte b = this.f5794g;
            if (b == 0) {
                BezierRadarHeader.this.A = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            } else if (1 == b) {
                BezierRadarHeader bezierRadarHeader = BezierRadarHeader.this;
                if (bezierRadarHeader.f5793n) {
                    valueAnimator.cancel();
                    return;
                }
                bezierRadarHeader.r = ((Integer) valueAnimator.getAnimatedValue()).intValue() / 2;
            } else if (2 == b) {
                BezierRadarHeader.this.t = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            } else if (3 == b) {
                BezierRadarHeader.this.w = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            } else if (4 == b) {
                BezierRadarHeader.this.x = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            }
            BezierRadarHeader.this.invalidate();
        }
    }

    public BezierRadarHeader(Context context) {
        this(context, null);
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.abs.AbsRefreshInternal, com.jd.lib.un.basewidget.widget.simple.c.d
    @Deprecated
    public void c(@ColorInt int... iArr) {
        if (iArr.length > 0 && !this.f5791l) {
            w(iArr[0]);
            this.f5791l = false;
        }
        if (iArr.length <= 1 || this.f5792m) {
            return;
        }
        v(iArr[1]);
        this.f5792m = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        u(canvas, width);
        r(canvas, width, height);
        s(canvas, width, height);
        t(canvas, width, height);
        super.dispatchDraw(canvas);
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.abs.AbsRefreshInternal, com.jd.lib.un.basewidget.widget.simple.c.d
    public void e(@NonNull f fVar, int i2, int i3) {
        this.q = i2;
        this.f5793n = false;
        ValueAnimator ofInt = ValueAnimator.ofInt(0, R2.attr.additionBottom);
        ofInt.setDuration(720L);
        ofInt.setRepeatCount(-1);
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.addUpdateListener(new b((byte) 4));
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat.setInterpolator(decelerateInterpolator);
        ofFloat.addUpdateListener(new b((byte) 2));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setInterpolator(decelerateInterpolator);
        ofFloat2.addUpdateListener(new b((byte) 0));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(ofFloat, ofFloat2, ofInt);
        animatorSet.start();
        int i4 = this.r;
        ValueAnimator ofInt2 = ValueAnimator.ofInt(i4, 0, -((int) (i4 * 0.8f)), 0, -((int) (i4 * 0.4f)), 0);
        ofInt2.addUpdateListener(new b((byte) 1));
        ofInt2.setInterpolator(decelerateInterpolator);
        ofInt2.setDuration(800L);
        ofInt2.start();
        this.B = animatorSet;
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.abs.AbsRefreshInternal, com.jd.lib.un.basewidget.widget.simple.d.e
    public void m(@NonNull f fVar, @NonNull com.jd.lib.un.basewidget.widget.simple.a.c cVar, @NonNull com.jd.lib.un.basewidget.widget.simple.a.c cVar2) {
        int i2 = a.a[cVar2.ordinal()];
        if (i2 == 1 || i2 == 2) {
            this.t = 1.0f;
            this.A = 0.0f;
            this.w = 0.0f;
        }
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.abs.AbsRefreshInternal, com.jd.lib.un.basewidget.widget.simple.c.d
    public void o(boolean z, float f2, int i2, int i3, int i4) {
        if (z || this.f5793n) {
            this.f5793n = true;
            this.q = Math.min(i3, i2);
            this.r = (int) (Math.max(0, i2 - i3) * 1.9f);
            this.u = f2;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Animator animator = this.B;
        if (animator != null) {
            animator.removeAllListeners();
            this.B.end();
            this.B = null;
        }
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.abs.AbsRefreshInternal, com.jd.lib.un.basewidget.widget.simple.c.d
    public int q(@NonNull f fVar, boolean z) {
        Animator animator = this.B;
        if (animator != null) {
            animator.removeAllListeners();
            this.B.end();
            this.B = null;
        }
        int width = getWidth();
        int height = getHeight();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, (float) Math.sqrt((width * width) + (height * height)));
        ofFloat.setDuration(400L);
        ofFloat.addUpdateListener(new b((byte) 3));
        ofFloat.start();
        return 400;
    }

    protected void r(Canvas canvas, int i2, int i3) {
        if (this.t > 0.0f) {
            this.p.setColor(this.f5789j);
            float f2 = i3;
            float b2 = com.jd.lib.un.basewidget.widget.simple.e.a.b(f2);
            float f3 = i2 / 7;
            float f4 = this.u;
            float f5 = (f3 * f4) - (f4 > 1.0f ? ((f4 - 1.0f) * f3) / f4 : 0.0f);
            float f6 = 2.0f;
            float f7 = f2 - (f4 > 1.0f ? (((f4 - 1.0f) * f2) / 2.0f) / f4 : 0.0f);
            int i4 = 0;
            while (i4 < 7) {
                float f8 = (i4 + 1.0f) - 4.0f;
                Paint paint = this.p;
                double abs = this.t * (1.0f - ((Math.abs(f8) / 7.0f) * f6)) * 255.0f;
                double d = b2;
                Double.isNaN(d);
                Double.isNaN(abs);
                paint.setAlpha((int) (abs * (1.0d - (1.0d / Math.pow((d / 800.0d) + 1.0d, 15.0d)))));
                float f9 = this.v * (1.0f - (1.0f / ((b2 / 10.0f) + 1.0f)));
                canvas.drawCircle(((i2 / 2) - (f9 / 2.0f)) + (f5 * f8), f7 / 2.0f, f9, this.p);
                i4++;
                f6 = 2.0f;
            }
            this.p.setAlpha(255);
        }
    }

    protected void s(Canvas canvas, int i2, int i3) {
        if (this.B != null || isInEditMode()) {
            float f2 = this.y;
            float f3 = this.A;
            float f4 = f2 * f3;
            float f5 = this.z * f3;
            this.p.setColor(this.f5789j);
            this.p.setStyle(Paint.Style.FILL);
            float f6 = i2 / 2;
            float f7 = i3 / 2;
            canvas.drawCircle(f6, f7, f4, this.p);
            this.p.setStyle(Paint.Style.STROKE);
            float f8 = f5 + f4;
            canvas.drawCircle(f6, f7, f8, this.p);
            this.p.setColor((this.f5790k & ViewCompat.MEASURED_SIZE_MASK) | ReactBaseTextShadowNode.DEFAULT_TEXT_SHADOW_COLOR);
            this.p.setStyle(Paint.Style.FILL);
            this.C.set(f6 - f4, f7 - f4, f6 + f4, f4 + f7);
            canvas.drawArc(this.C, 270.0f, this.x, true, this.p);
            this.p.setStyle(Paint.Style.STROKE);
            this.C.set(f6 - f8, f7 - f8, f6 + f8, f7 + f8);
            canvas.drawArc(this.C, 270.0f, this.x, false, this.p);
            this.p.setStyle(Paint.Style.FILL);
        }
    }

    protected void t(Canvas canvas, int i2, int i3) {
        if (this.w > 0.0f) {
            this.p.setColor(this.f5789j);
            canvas.drawCircle(i2 / 2, i3 / 2, this.w, this.p);
        }
    }

    protected void u(Canvas canvas, int i2) {
        this.o.reset();
        this.o.lineTo(0.0f, this.q);
        Path path = this.o;
        int i3 = this.s;
        if (i3 < 0) {
            i3 = i2 / 2;
        }
        float f2 = i2;
        path.quadTo(i3, this.r + r3, f2, this.q);
        this.o.lineTo(f2, 0.0f);
        this.p.setColor(this.f5790k);
        canvas.drawPath(this.o, this.p);
    }

    public BezierRadarHeader v(@ColorInt int i2) {
        this.f5789j = i2;
        this.f5792m = true;
        return this;
    }

    public BezierRadarHeader w(@ColorInt int i2) {
        this.f5790k = i2;
        this.f5791l = true;
        return this;
    }

    public BezierRadarHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BezierRadarHeader(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.s = -1;
        this.x = 0;
        this.y = 0.0f;
        this.z = 0.0f;
        this.A = 0.0f;
        this.C = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.f5776h = com.jd.lib.un.basewidget.widget.simple.a.b.SCALE;
        this.o = new Path();
        Paint paint = new Paint();
        this.p = paint;
        paint.setAntiAlias(true);
        this.v = com.jd.lib.un.basewidget.widget.simple.e.a.a(7.0f);
        this.y = com.jd.lib.un.basewidget.widget.simple.e.a.a(20.0f);
        this.z = com.jd.lib.un.basewidget.widget.simple.e.a.a(7.0f);
        this.p.setStrokeWidth(com.jd.lib.un.basewidget.widget.simple.e.a.a(3.0f));
        setMinimumHeight(com.jd.lib.un.basewidget.widget.simple.e.a.a(100.0f));
        if (isInEditMode()) {
            this.q = 1000;
            this.A = 1.0f;
            this.x = 270;
        } else {
            this.A = 0.0f;
        }
        v(-1);
        w(-14540254);
    }
}
