package com.jd.lib.un.basewidget.widget.multi.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.jd.lib.un.basewidget.R;

/* loaded from: classes16.dex */
public class MultiIndicator extends View {
    private static final Interpolator t = new a();

    /* renamed from: g  reason: collision with root package name */
    private Paint f5729g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f5730h;

    /* renamed from: i  reason: collision with root package name */
    private int f5731i;

    /* renamed from: j  reason: collision with root package name */
    private int f5732j;

    /* renamed from: k  reason: collision with root package name */
    private float f5733k;

    /* renamed from: l  reason: collision with root package name */
    private int f5734l;

    /* renamed from: m  reason: collision with root package name */
    private int f5735m;

    /* renamed from: n  reason: collision with root package name */
    private RectF f5736n;
    private View o;
    private View p;
    private Scroller q;
    private float r;
    private float s;

    /* loaded from: classes16.dex */
    static class a implements Interpolator {
        a() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    public MultiIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5730h = false;
        this.f5731i = 3;
        this.f5732j = 84;
        this.f5733k = 0.7f;
        this.f5734l = 0;
        this.f5735m = -14474458;
        if (isInEditMode()) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MultiIndicator);
        this.f5732j = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MultiIndicator_indicator_item_width, this.f5732j);
        this.f5731i = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MultiIndicator_indicator_item_height, this.f5731i);
        this.f5734l = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MultiIndicator_indicator_radius, this.f5734l);
        this.f5733k = obtainStyledAttributes.getFloat(R.styleable.MultiIndicator_indicator_percent, this.f5733k);
        this.f5735m = obtainStyledAttributes.getColor(R.styleable.MultiIndicator_indicator_select_color, this.f5735m);
        obtainStyledAttributes.recycle();
        a();
    }

    private void a() {
        this.f5729g = new Paint(1);
        this.q = new Scroller(getContext(), t);
        this.f5729g.setColor(this.f5735m);
        this.f5736n = new RectF();
    }

    public void b(View view, View view2) {
        if (view2 != null) {
            if (this.f5730h || this.f5732j <= 0) {
                this.f5732j = (int) (view2.getWidth() * this.f5733k);
            }
            this.o = view;
            this.p = view2;
            if (view != null && view != view2) {
                int left = view.getLeft();
                this.q.startScroll(left, 0, view2.getLeft() - left, 0, 400);
            } else {
                this.r = view2.getLeft();
            }
            this.s = ((view2.getRight() - view2.getLeft()) - this.f5732j) / 2;
            invalidate();
        }
    }

    public void c(int i2) {
        Paint paint = this.f5729g;
        if (paint != null) {
            paint.setColor(i2);
            requestLayout();
        }
    }

    public void d(int i2) {
        this.f5731i = i2;
    }

    public void e(boolean z) {
        this.f5730h = z;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.p == null) {
            return;
        }
        float paddingLeft = getPaddingLeft() + this.r + this.s;
        float f2 = paddingLeft + this.f5732j;
        float paddingTop = getPaddingTop();
        float height = getHeight() - getPaddingBottom();
        int i2 = this.f5734l;
        if (i2 == 0) {
            canvas.drawRect(paddingLeft, paddingTop, f2, height, this.f5729g);
        } else {
            RectF rectF = this.f5736n;
            rectF.left = paddingLeft;
            rectF.top = paddingTop;
            rectF.right = f2;
            rectF.bottom = height;
            canvas.drawRoundRect(rectF, i2, i2, this.f5729g);
        }
        if (!this.q.isFinished() && this.q.computeScrollOffset()) {
            this.r = this.q.getCurrX();
            invalidate();
            return;
        }
        this.q.abortAnimation();
    }
}
