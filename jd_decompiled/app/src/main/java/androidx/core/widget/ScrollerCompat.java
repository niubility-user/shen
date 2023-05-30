package androidx.core.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

@Deprecated
/* loaded from: classes.dex */
public final class ScrollerCompat {
    OverScroller mScroller;

    ScrollerCompat(Context context, Interpolator interpolator) {
        this.mScroller = interpolator != null ? new OverScroller(context, interpolator) : new OverScroller(context);
    }

    @Deprecated
    public static ScrollerCompat create(Context context) {
        return create(context, null);
    }

    @Deprecated
    public void abortAnimation() {
        this.mScroller.abortAnimation();
    }

    @Deprecated
    public boolean computeScrollOffset() {
        return this.mScroller.computeScrollOffset();
    }

    @Deprecated
    public void fling(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.mScroller.fling(i2, i3, i4, i5, i6, i7, i8, i9);
    }

    @Deprecated
    public float getCurrVelocity() {
        return this.mScroller.getCurrVelocity();
    }

    @Deprecated
    public int getCurrX() {
        return this.mScroller.getCurrX();
    }

    @Deprecated
    public int getCurrY() {
        return this.mScroller.getCurrY();
    }

    @Deprecated
    public int getFinalX() {
        return this.mScroller.getFinalX();
    }

    @Deprecated
    public int getFinalY() {
        return this.mScroller.getFinalY();
    }

    @Deprecated
    public boolean isFinished() {
        return this.mScroller.isFinished();
    }

    @Deprecated
    public boolean isOverScrolled() {
        return this.mScroller.isOverScrolled();
    }

    @Deprecated
    public void notifyHorizontalEdgeReached(int i2, int i3, int i4) {
        this.mScroller.notifyHorizontalEdgeReached(i2, i3, i4);
    }

    @Deprecated
    public void notifyVerticalEdgeReached(int i2, int i3, int i4) {
        this.mScroller.notifyVerticalEdgeReached(i2, i3, i4);
    }

    @Deprecated
    public boolean springBack(int i2, int i3, int i4, int i5, int i6, int i7) {
        return this.mScroller.springBack(i2, i3, i4, i5, i6, i7);
    }

    @Deprecated
    public void startScroll(int i2, int i3, int i4, int i5) {
        this.mScroller.startScroll(i2, i3, i4, i5);
    }

    @Deprecated
    public static ScrollerCompat create(Context context, Interpolator interpolator) {
        return new ScrollerCompat(context, interpolator);
    }

    @Deprecated
    public void fling(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        this.mScroller.fling(i2, i3, i4, i5, i6, i7, i8, i9, i10, i11);
    }

    @Deprecated
    public void startScroll(int i2, int i3, int i4, int i5, int i6) {
        this.mScroller.startScroll(i2, i3, i4, i5, i6);
    }
}
