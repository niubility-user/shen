package com.facebook.react.views.scroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.OverScroller;
import androidx.core.text.TextUtilsCompat;
import androidx.core.view.ViewCompat;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import com.facebook.react.views.view.ReactViewBackgroundManager;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ReactHorizontalScrollView extends HorizontalScrollView implements ReactClippingViewGroup {
    @Nullable
    private static Field sScrollerField;
    private static boolean sTriedToGetScrollerField;
    int currentX;
    int currentY;
    private boolean mActivelyScrolling;
    @Nullable
    private Rect mClippingRect;
    private float mDecelerationRate;
    private boolean mDragging;
    @Nullable
    private Drawable mEndBackground;
    private int mEndFillColor;
    @Nullable
    private FpsListener mFpsListener;
    boolean mInterceptScrollEnabled;
    boolean mOnInterceptTouchEvent;
    private final OnScrollDispatchHelper mOnScrollDispatchHelper;
    boolean mOnTouchEvent;
    @Nullable
    private String mOverflow;
    private boolean mPagingEnabled;
    @Nullable
    private Runnable mPostTouchRunnable;
    private ReactViewBackgroundManager mReactBackgroundManager;
    private final Rect mRect;
    private boolean mRemoveClippedSubviews;
    private boolean mScrollEnabled;
    @Nullable
    private String mScrollPerfTag;
    @Nullable
    private final OverScroller mScroller;
    private boolean mSendMomentumEvents;
    private int mSnapInterval;
    @Nullable
    private List<Integer> mSnapOffsets;
    private boolean mSnapToEnd;
    private boolean mSnapToStart;
    private final VelocityHelper mVelocityHelper;
    ViewGroup v;

    public ReactHorizontalScrollView(Context context) {
        this(context, null);
    }

    public void disableFpsListener() {
        if (isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.disable(this.mScrollPerfTag);
        }
    }

    private void enableFpsListener() {
        if (isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.enable(this.mScrollPerfTag);
        }
    }

    public void flingAndSnap(int i2) {
        int i3;
        int i4;
        int i5;
        int min;
        int i6;
        if (getChildCount() <= 0) {
            return;
        }
        if (this.mSnapInterval == 0 && this.mSnapOffsets == null) {
            smoothScrollAndSnap(i2);
            return;
        }
        int max = Math.max(0, computeHorizontalScrollRange() - getWidth());
        int predictFinalScrollPosition = predictFinalScrollPosition(i2);
        int width = (getWidth() - ViewCompat.getPaddingStart(this)) - ViewCompat.getPaddingEnd(this);
        boolean z = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        if (z) {
            predictFinalScrollPosition = max - predictFinalScrollPosition;
            i3 = -i2;
        } else {
            i3 = i2;
        }
        List<Integer> list = this.mSnapOffsets;
        if (list != null) {
            i6 = list.get(0).intValue();
            List<Integer> list2 = this.mSnapOffsets;
            i4 = list2.get(list2.size() - 1).intValue();
            min = max;
            i5 = 0;
            for (int i7 = 0; i7 < this.mSnapOffsets.size(); i7++) {
                int intValue = this.mSnapOffsets.get(i7).intValue();
                if (intValue <= predictFinalScrollPosition && predictFinalScrollPosition - intValue < predictFinalScrollPosition - i5) {
                    i5 = intValue;
                }
                if (intValue >= predictFinalScrollPosition && intValue - predictFinalScrollPosition < min - predictFinalScrollPosition) {
                    min = intValue;
                }
            }
        } else {
            double snapInterval = getSnapInterval();
            double d = predictFinalScrollPosition;
            Double.isNaN(d);
            Double.isNaN(snapInterval);
            double d2 = d / snapInterval;
            double floor = Math.floor(d2);
            Double.isNaN(snapInterval);
            double ceil = Math.ceil(d2);
            Double.isNaN(snapInterval);
            i4 = max;
            i5 = (int) (floor * snapInterval);
            min = Math.min((int) (ceil * snapInterval), max);
            i6 = 0;
        }
        int i8 = predictFinalScrollPosition - i5;
        int i9 = min - predictFinalScrollPosition;
        int i10 = i8 < i9 ? i5 : min;
        int scrollX = getScrollX();
        if (z) {
            scrollX = max - scrollX;
        }
        if (this.mSnapToEnd || predictFinalScrollPosition < i4) {
            if (this.mSnapToStart || predictFinalScrollPosition > i6) {
                if (i3 > 0) {
                    double d3 = i9;
                    Double.isNaN(d3);
                    i3 += (int) (d3 * 10.0d);
                    predictFinalScrollPosition = min;
                } else if (i3 < 0) {
                    double d4 = i8;
                    Double.isNaN(d4);
                    i3 -= (int) (d4 * 10.0d);
                    predictFinalScrollPosition = i5;
                } else {
                    predictFinalScrollPosition = i10;
                }
            } else if (scrollX > i6) {
                predictFinalScrollPosition = i6;
            }
        } else if (scrollX < i4) {
            predictFinalScrollPosition = i4;
        }
        int min2 = Math.min(Math.max(0, predictFinalScrollPosition), max);
        if (z) {
            min2 = max - min2;
            i3 = -i3;
        }
        OverScroller overScroller = this.mScroller;
        if (overScroller != null) {
            this.mActivelyScrolling = true;
            overScroller.fling(getScrollX(), getScrollY(), i3 != 0 ? i3 : min2 - getScrollX(), 0, min2, min2, 0, 0, (min2 == 0 || min2 == max) ? width / 2 : 0, 0);
            postInvalidateOnAnimation();
            return;
        }
        smoothScrollTo(min2, getScrollY());
    }

    @Nullable
    private OverScroller getOverScrollerFromParent() {
        if (!sTriedToGetScrollerField) {
            sTriedToGetScrollerField = true;
            try {
                Field declaredField = HorizontalScrollView.class.getDeclaredField("mScroller");
                sScrollerField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
        }
        Field field = sScrollerField;
        if (field != null) {
            try {
                Object obj = field.get(this);
                if (obj instanceof OverScroller) {
                    return (OverScroller) obj;
                }
                return null;
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Failed to get mScroller from HorizontalScrollView!", e2);
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0028, code lost:
        if ((r0.getParent() instanceof com.facebook.react.views.scroll.ReactHorizontalScrollView) != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0030, code lost:
        if ((r0.getParent() instanceof androidx.viewpager.widget.ViewPager) == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0038, code lost:
        return (android.view.ViewGroup) r0.getParent();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:?, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.ViewGroup getScrollView() {
        /*
            r2 = this;
            r0 = r2
        L1:
            if (r0 == 0) goto L20
            android.view.ViewParent r1 = r0.getParent()
            if (r1 == 0) goto L20
            android.view.ViewParent r1 = r0.getParent()
            boolean r1 = r1 instanceof com.facebook.react.views.scroll.ReactHorizontalScrollView
            if (r1 != 0) goto L20
            android.view.ViewParent r1 = r0.getParent()
            boolean r1 = r1 instanceof androidx.viewpager.widget.ViewPager
            if (r1 != 0) goto L20
            android.view.ViewParent r0 = r0.getParent()
            android.view.View r0 = (android.view.View) r0
            goto L1
        L20:
            if (r0 == 0) goto L39
            android.view.ViewParent r1 = r0.getParent()
            boolean r1 = r1 instanceof com.facebook.react.views.scroll.ReactHorizontalScrollView
            if (r1 != 0) goto L32
            android.view.ViewParent r1 = r0.getParent()
            boolean r1 = r1 instanceof androidx.viewpager.widget.ViewPager
            if (r1 == 0) goto L39
        L32:
            android.view.ViewParent r0 = r0.getParent()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            return r0
        L39:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactHorizontalScrollView.getScrollView():android.view.ViewGroup");
    }

    private int getSnapInterval() {
        int i2 = this.mSnapInterval;
        return i2 != 0 ? i2 : getWidth();
    }

    private void handlePostTouchScrolling(int i2, int i3) {
        if ((this.mSendMomentumEvents || this.mPagingEnabled || isScrollPerfLoggingEnabled()) && this.mPostTouchRunnable == null) {
            if (this.mSendMomentumEvents) {
                ReactScrollViewHelper.emitScrollMomentumBeginEvent(this, i2, i3);
            }
            this.mActivelyScrolling = false;
            Runnable runnable = new Runnable() { // from class: com.facebook.react.views.scroll.ReactHorizontalScrollView.1
                private boolean mSnappingToPage = false;

                {
                    ReactHorizontalScrollView.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (ReactHorizontalScrollView.this.mActivelyScrolling) {
                        ReactHorizontalScrollView.this.mActivelyScrolling = false;
                        ViewCompat.postOnAnimationDelayed(ReactHorizontalScrollView.this, this, 20L);
                    } else if (!ReactHorizontalScrollView.this.mPagingEnabled || this.mSnappingToPage) {
                        if (ReactHorizontalScrollView.this.mSendMomentumEvents) {
                            ReactScrollViewHelper.emitScrollMomentumEndEvent(ReactHorizontalScrollView.this);
                        }
                        ReactHorizontalScrollView.this.mPostTouchRunnable = null;
                        ReactHorizontalScrollView.this.disableFpsListener();
                    } else {
                        this.mSnappingToPage = true;
                        ReactHorizontalScrollView.this.flingAndSnap(0);
                        ViewCompat.postOnAnimationDelayed(ReactHorizontalScrollView.this, this, 20L);
                    }
                }
            };
            this.mPostTouchRunnable = runnable;
            ViewCompat.postOnAnimationDelayed(this, runnable, 20L);
        }
    }

    private boolean isScrollPerfLoggingEnabled() {
        String str;
        return (this.mFpsListener == null || (str = this.mScrollPerfTag) == null || str.isEmpty()) ? false : true;
    }

    private int predictFinalScrollPosition(int i2) {
        OverScroller overScroller = new OverScroller(getContext());
        overScroller.setFriction(1.0f - this.mDecelerationRate);
        overScroller.fling(getScrollX(), getScrollY(), i2, 0, 0, Math.max(0, computeHorizontalScrollRange() - getWidth()), 0, 0, ((getWidth() - ViewCompat.getPaddingStart(this)) - ViewCompat.getPaddingEnd(this)) / 2, 0);
        return overScroller.getFinalX();
    }

    private void smoothScrollAndSnap(int i2) {
        double snapInterval = getSnapInterval();
        double scrollX = getScrollX();
        double predictFinalScrollPosition = predictFinalScrollPosition(i2);
        Double.isNaN(scrollX);
        Double.isNaN(snapInterval);
        double d = scrollX / snapInterval;
        int floor = (int) Math.floor(d);
        int ceil = (int) Math.ceil(d);
        int round = (int) Math.round(d);
        Double.isNaN(predictFinalScrollPosition);
        Double.isNaN(snapInterval);
        int round2 = (int) Math.round(predictFinalScrollPosition / snapInterval);
        if (i2 > 0 && ceil == floor) {
            ceil++;
        } else if (i2 < 0 && floor == ceil) {
            floor--;
        }
        if (i2 > 0 && round < ceil && round2 > floor) {
            round = ceil;
        } else if (i2 < 0 && round > floor && round2 < ceil) {
            round = floor;
        }
        double d2 = round;
        Double.isNaN(d2);
        Double.isNaN(snapInterval);
        double d3 = d2 * snapInterval;
        if (d3 != scrollX) {
            this.mActivelyScrolling = true;
            smoothScrollTo((int) d3, getScrollY());
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void draw(Canvas canvas) {
        if (this.mEndFillColor != 0) {
            View childAt = getChildAt(0);
            if (this.mEndBackground != null && childAt != null && childAt.getRight() < getWidth()) {
                this.mEndBackground.setBounds(childAt.getRight(), 0, getWidth(), getHeight());
                this.mEndBackground.draw(canvas);
            }
        }
        super.draw(canvas);
    }

    public void flashScrollIndicators() {
        awakenScrollBars();
    }

    @Override // android.widget.HorizontalScrollView
    public void fling(int i2) {
        int abs = (int) (Math.abs(i2) * Math.signum(this.mOnScrollDispatchHelper.getXFlingVelocity()));
        if (this.mPagingEnabled) {
            flingAndSnap(abs);
        } else if (this.mScroller != null) {
            this.mScroller.fling(getScrollX(), getScrollY(), abs, 0, 0, Integer.MAX_VALUE, 0, 0, ((getWidth() - ViewCompat.getPaddingStart(this)) - ViewCompat.getPaddingEnd(this)) / 2, 0);
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            super.fling(abs);
        }
        handlePostTouchScrolling(abs, 0);
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void getClippingRect(Rect rect) {
        rect.set((Rect) Assertions.assertNotNull(this.mClippingRect));
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        getDrawingRect(this.mRect);
        String str = this.mOverflow;
        str.hashCode();
        if (!str.equals(ViewProps.VISIBLE)) {
            canvas.clipRect(this.mRect);
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mScrollEnabled) {
            if (motionEvent.getAction() == 0) {
                this.mInterceptScrollEnabled = false;
            }
            if (this.mInterceptScrollEnabled) {
                return false;
            }
            if (this.mOnInterceptTouchEvent) {
                if (motionEvent.getAction() == 0) {
                    this.currentX = (int) motionEvent.getX();
                    this.currentY = (int) motionEvent.getY();
                    if (this.v == null) {
                        this.v = getScrollView();
                    }
                    ViewGroup viewGroup = this.v;
                    if (viewGroup != null) {
                        viewGroup.requestDisallowInterceptTouchEvent(true);
                        return super.onInterceptTouchEvent(motionEvent);
                    }
                } else if (motionEvent.getAction() == 1) {
                    if (this.v == null) {
                        this.v = getScrollView();
                    }
                    ViewGroup viewGroup2 = this.v;
                    if (viewGroup2 != null) {
                        viewGroup2.requestDisallowInterceptTouchEvent(false);
                        return super.onInterceptTouchEvent(motionEvent);
                    }
                }
            }
            try {
                if (super.onInterceptTouchEvent(motionEvent)) {
                    NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
                    ReactScrollViewHelper.emitScrollBeginDragEvent(this);
                    this.mDragging = true;
                    enableFpsListener();
                    return true;
                }
            } catch (IllegalArgumentException unused) {
            }
            return false;
        }
        return false;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        scrollTo(getScrollX(), getScrollY());
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(i2, i3);
        setMeasuredDimension(View.MeasureSpec.getSize(i2), View.MeasureSpec.getSize(i3));
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    protected void onOverScrolled(int i2, int i3, boolean z, boolean z2) {
        int computeHorizontalScrollRange;
        OverScroller overScroller = this.mScroller;
        if (overScroller != null && !overScroller.isFinished() && this.mScroller.getCurrX() != this.mScroller.getFinalX() && i2 >= (computeHorizontalScrollRange = computeHorizontalScrollRange() - getWidth())) {
            this.mScroller.abortAnimation();
            i2 = computeHorizontalScrollRange;
        }
        super.onOverScrolled(i2, i3, z, z2);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        this.mActivelyScrolling = true;
        if (this.mOnScrollDispatchHelper.onScrollChanged(i2, i3)) {
            if (this.mRemoveClippedSubviews) {
                updateClippingRect();
            }
            ReactScrollViewHelper.emitScrollEvent(this, this.mOnScrollDispatchHelper.getXFlingVelocity(), this.mOnScrollDispatchHelper.getYFlingVelocity());
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mScrollEnabled) {
            this.mVelocityHelper.calculateVelocity(motionEvent);
            if (this.mOnInterceptTouchEvent && motionEvent.getAction() == 2) {
                int measuredWidth = getChildAt(0).getMeasuredWidth() - getMeasuredWidth();
                int scrollX = getScrollX();
                int x = (int) motionEvent.getX();
                int i2 = this.currentX;
                if (i2 < x) {
                    if (scrollX <= 0) {
                        if (this.v == null) {
                            this.v = getScrollView();
                        }
                        ViewGroup viewGroup = this.v;
                        if (viewGroup != null) {
                            viewGroup.requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                    if (this.v == null) {
                        this.v = getScrollView();
                    }
                    ViewGroup viewGroup2 = this.v;
                    if (viewGroup2 != null) {
                        viewGroup2.requestDisallowInterceptTouchEvent(true);
                    }
                } else if (i2 > x) {
                    if (scrollX >= measuredWidth) {
                        ViewGroup viewGroup3 = this.v;
                        if (viewGroup3 != null) {
                            viewGroup3.requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                    ViewGroup viewGroup4 = this.v;
                    if (viewGroup4 != null) {
                        viewGroup4.requestDisallowInterceptTouchEvent(true);
                    }
                }
                this.currentX = x;
            }
            if ((motionEvent.getAction() & 255) == 1 && this.mDragging) {
                float xVelocity = this.mVelocityHelper.getXVelocity();
                float yVelocity = this.mVelocityHelper.getYVelocity();
                ReactScrollViewHelper.emitScrollEndDragEvent(this, xVelocity, yVelocity);
                this.mDragging = false;
                handlePostTouchScrolling(Math.round(xVelocity), Math.round(yVelocity));
            }
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        this.mReactBackgroundManager.setBackgroundColor(i2);
    }

    public void setBorderColor(int i2, float f2, float f3) {
        this.mReactBackgroundManager.setBorderColor(i2, f2, f3);
    }

    public void setBorderRadius(float f2) {
        this.mReactBackgroundManager.setBorderRadius(f2);
    }

    public void setBorderStyle(@Nullable String str) {
        this.mReactBackgroundManager.setBorderStyle(str);
    }

    public void setBorderWidth(int i2, float f2) {
        this.mReactBackgroundManager.setBorderWidth(i2, f2);
    }

    public void setDecelerationRate(float f2) {
        this.mDecelerationRate = f2;
        OverScroller overScroller = this.mScroller;
        if (overScroller != null) {
            overScroller.setFriction(1.0f - f2);
        }
    }

    public void setEndFillColor(int i2) {
        if (i2 != this.mEndFillColor) {
            this.mEndFillColor = i2;
            this.mEndBackground = new ColorDrawable(this.mEndFillColor);
        }
    }

    public void setInterceptScrollEnabled(boolean z) {
        this.mInterceptScrollEnabled = z;
    }

    public void setOnInterceptTouchEvent(boolean z) {
        this.mOnInterceptTouchEvent = z;
    }

    public void setOnTouchEvent(boolean z) {
        this.mOnTouchEvent = z;
    }

    public void setOverflow(String str) {
        this.mOverflow = str;
        invalidate();
    }

    public void setPagingEnabled(boolean z) {
        this.mPagingEnabled = z;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void setRemoveClippedSubviews(boolean z) {
        if (z && this.mClippingRect == null) {
            this.mClippingRect = new Rect();
        }
        this.mRemoveClippedSubviews = z;
        updateClippingRect();
    }

    public void setScrollEnabled(boolean z) {
        this.mScrollEnabled = z;
    }

    public void setScrollPerfTag(@Nullable String str) {
        this.mScrollPerfTag = str;
    }

    public void setSendMomentumEvents(boolean z) {
        this.mSendMomentumEvents = z;
    }

    public void setSnapInterval(int i2) {
        this.mSnapInterval = i2;
    }

    public void setSnapOffsets(List<Integer> list) {
        this.mSnapOffsets = list;
    }

    public void setSnapToEnd(boolean z) {
        this.mSnapToEnd = z;
    }

    public void setSnapToStart(boolean z) {
        this.mSnapToStart = z;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            Assertions.assertNotNull(this.mClippingRect);
            ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
            View childAt = getChildAt(0);
            if (childAt instanceof ReactClippingViewGroup) {
                ((ReactClippingViewGroup) childAt).updateClippingRect();
            }
        }
    }

    public ReactHorizontalScrollView(Context context, @Nullable FpsListener fpsListener) {
        super(context);
        this.mOnScrollDispatchHelper = new OnScrollDispatchHelper();
        this.mVelocityHelper = new VelocityHelper();
        this.mRect = new Rect();
        this.mOverflow = ViewProps.HIDDEN;
        this.mPagingEnabled = false;
        this.mScrollEnabled = true;
        this.mFpsListener = null;
        this.mEndFillColor = 0;
        this.mSnapInterval = 0;
        this.mDecelerationRate = 0.985f;
        this.mSnapToStart = true;
        this.mSnapToEnd = true;
        this.mOnInterceptTouchEvent = false;
        this.mOnTouchEvent = false;
        this.mInterceptScrollEnabled = false;
        this.mReactBackgroundManager = new ReactViewBackgroundManager(this);
        this.mFpsListener = fpsListener;
        this.mScroller = getOverScrollerFromParent();
    }

    public void setBorderRadius(float f2, int i2) {
        this.mReactBackgroundManager.setBorderRadius(f2, i2);
    }
}
