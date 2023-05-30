package com.jd.dynamic.lib.views.skeleton.shimmer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.dynamic.R;
import com.jd.dynamic.lib.views.skeleton.shimmer.Shimmer;

/* loaded from: classes13.dex */
public class ShimmerFrameLayout extends FrameLayout {

    /* renamed from: g  reason: collision with root package name */
    private final Paint f2597g;

    /* renamed from: h  reason: collision with root package name */
    private final ShimmerDrawable f2598h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f2599i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f2600j;

    public ShimmerFrameLayout(Context context) {
        super(context);
        this.f2597g = new Paint();
        this.f2598h = new ShimmerDrawable();
        this.f2599i = true;
        this.f2600j = false;
        a(context, null);
    }

    public ShimmerFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2597g = new Paint();
        this.f2598h = new ShimmerDrawable();
        this.f2599i = true;
        this.f2600j = false;
        a(context, attributeSet);
    }

    public ShimmerFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2597g = new Paint();
        this.f2598h = new ShimmerDrawable();
        this.f2599i = true;
        this.f2600j = false;
        a(context, attributeSet);
    }

    @TargetApi(21)
    public ShimmerFrameLayout(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f2597g = new Paint();
        this.f2598h = new ShimmerDrawable();
        this.f2599i = true;
        this.f2600j = false;
        a(context, attributeSet);
    }

    private void a(Context context, @Nullable AttributeSet attributeSet) {
        setWillNotDraw(false);
        this.f2598h.setCallback(this);
        if (attributeSet == null) {
            setShimmer(new Shimmer.AlphaHighlightBuilder().build());
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShimmerFrameLayout, 0, 0);
        try {
            int i2 = R.styleable.ShimmerFrameLayout_shimmer_colored;
            setShimmer(((obtainStyledAttributes.hasValue(i2) && obtainStyledAttributes.getBoolean(i2, false)) ? new Shimmer.ColorHighlightBuilder() : new Shimmer.AlphaHighlightBuilder()).b(obtainStyledAttributes).build());
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void clearStaticAnimationProgress() {
        this.f2598h.clearStaticAnimationProgress();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f2599i) {
            this.f2598h.draw(canvas);
        }
    }

    @Nullable
    public Shimmer getShimmer() {
        return this.f2598h.getShimmer();
    }

    public void hideShimmer() {
        stopShimmer();
        this.f2599i = false;
        invalidate();
    }

    public boolean isShimmerRunning() {
        return this.f2598h.isShimmerRunning();
    }

    public boolean isShimmerStarted() {
        return this.f2598h.isShimmerStarted();
    }

    public boolean isShimmerVisible() {
        return this.f2599i;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f2598h.b();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopShimmer();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.f2598h.setBounds(0, 0, getWidth(), getHeight());
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i2) {
        boolean z;
        super.onVisibilityChanged(view, i2);
        ShimmerDrawable shimmerDrawable = this.f2598h;
        if (shimmerDrawable == null) {
            return;
        }
        if (i2 != 0) {
            if (!isShimmerStarted()) {
                return;
            }
            stopShimmer();
            z = true;
        } else if (!this.f2600j) {
            return;
        } else {
            shimmerDrawable.b();
            z = false;
        }
        this.f2600j = z;
    }

    public ShimmerFrameLayout setShimmer(@Nullable Shimmer shimmer) {
        int i2;
        Paint paint;
        this.f2598h.setShimmer(shimmer);
        if (shimmer == null || !shimmer.f2591n) {
            i2 = 0;
            paint = null;
        } else {
            i2 = 2;
            paint = this.f2597g;
        }
        setLayerType(i2, paint);
        return this;
    }

    public void setStaticAnimationProgress(float f2) {
        this.f2598h.setStaticAnimationProgress(f2);
    }

    public void showShimmer(boolean z) {
        this.f2599i = true;
        if (z) {
            startShimmer();
        }
        invalidate();
    }

    public void startShimmer() {
        if (getWindowToken() != null) {
            this.f2598h.startShimmer();
        }
    }

    public void stopShimmer() {
        this.f2600j = false;
        this.f2598h.stopShimmer();
    }

    @Override // android.view.View
    protected boolean verifyDrawable(@NonNull Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f2598h;
    }
}
