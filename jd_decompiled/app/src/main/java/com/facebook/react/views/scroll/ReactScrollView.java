package com.facebook.react.views.scroll;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.core.view.ViewCompat;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import com.facebook.react.views.view.ReactViewBackgroundManager;
import java.lang.reflect.Field;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ReactScrollView extends ScrollView implements ReactClippingViewGroup, ViewGroup.OnHierarchyChangeListener, View.OnLayoutChangeListener {
    @Nullable
    private static Field sScrollerField;
    private static boolean sTriedToGetScrollerField;
    int currentX;
    int currentY;
    int currentY_position;
    private int delta;
    private boolean isScrolling;
    private boolean mActivelyScrolling;
    private boolean mBlcok;
    private ReactScrollView mChildReactScrollView;
    @Nullable
    private Rect mClippingRect;
    private View mContentView;
    private float mDecelerationRate;
    private boolean mDoneFlinging;
    private boolean mDragging;
    @Nullable
    private Drawable mEndBackground;
    private int mEndFillColor;
    private boolean mFlinging;
    @Nullable
    private FpsListener mFpsListener;
    boolean mInterceptScrollEnabled;
    boolean mOnInterceptTouchEvent;
    private final OnScrollDispatchHelper mOnScrollDispatchHelper;
    @Nullable
    private String mOverflow;
    private boolean mPagingEnabled;
    boolean mPassToChildren;
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
    boolean mTouchEventEnable;
    private final VelocityHelper mVelocityHelper;
    private ReactScrollListener scrollListener;
    int scrollY_P;
    int scroll_postion;
    ViewGroup v;
    private ViewGroup viewGroupH;

    /* loaded from: classes12.dex */
    public interface ReactScrollListener {
        void onScroll(ReactScrollView reactScrollView, int i2, int i3, int i4, int i5, OverScroller overScroller, float f2, float f3);
    }

    public ReactScrollView(ReactContext reactContext) {
        this(reactContext, null);
    }

    private boolean bolckevent(MotionEvent motionEvent) {
        ReactScrollView reactScrollView;
        ViewGroup viewGroup;
        if (getChildCount() > 0 && ((reactScrollView = this.mChildReactScrollView) == null || reactScrollView.getChildCount() > 0)) {
            if (this.mChildReactScrollView != null && (motionEvent.getAction() == 2 || motionEvent.getAction() == 1)) {
                int measuredHeight = getChildAt(0).getMeasuredHeight() - getMeasuredHeight();
                int scrollY = getScrollY();
                int y = (int) motionEvent.getY();
                if (this.scrollY_P > y && (motionEvent.getAction() != 1 || this.delta >= 0)) {
                    if ((this.scrollY_P > y || (motionEvent.getAction() == 1 && this.delta > 0)) && scrollY >= measuredHeight) {
                        if (motionEvent.getAction() != 1) {
                            requestDisallowInterceptTouchEvent(true);
                            this.mChildReactScrollView.smoothScrollBy(0, this.scrollY_P - y);
                        } else {
                            this.mChildReactScrollView.fling((int) (this.delta / 0.02f));
                            this.delta = 0;
                        }
                        this.scrollY_P = y;
                        return false;
                    }
                } else {
                    View childAt = this.mChildReactScrollView.getChildAt(0);
                    if (childAt != null) {
                        childAt.getMeasuredHeight();
                        getMeasuredHeight();
                        if (this.mChildReactScrollView.getScrollY() > 0) {
                            if (motionEvent.getAction() != 1) {
                                this.mChildReactScrollView.smoothScrollBy(0, this.scrollY_P - y);
                                requestDisallowInterceptTouchEvent(true);
                            } else {
                                this.mChildReactScrollView.fling((int) (this.delta / 0.02f));
                                this.delta = 0;
                            }
                            this.delta = this.scrollY_P - y;
                            this.scrollY_P = y;
                            return false;
                        }
                    }
                }
                this.scrollY_P = y;
            }
            if (this.mPassToChildren && (motionEvent.getAction() == 2 || motionEvent.getAction() == 1)) {
                if (!this.mDragging) {
                    this.mScroller.getStartX();
                    this.mScroller.getStartY();
                    this.mScroller.getCurrX();
                    this.mScroller.getCurrY();
                    if (this.scroll_postion != getScrollY()) {
                        NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
                        ReactScrollViewHelper.emitScrollBeginDragEvent(this);
                        this.mDragging = true;
                    }
                    if (Math.abs(motionEvent.getY() - this.currentY_position) >= 20.0f) {
                        NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
                        ReactScrollViewHelper.emitScrollBeginDragEvent(this);
                        this.mDragging = true;
                    }
                }
                getChildAt(0).getMeasuredHeight();
                getMeasuredHeight();
                int scrollY2 = getScrollY();
                int y2 = (int) motionEvent.getY();
                if (this.viewGroupH == null) {
                    this.viewGroupH = getScrollViewH();
                }
                int i2 = this.scrollY_P;
                if (i2 < y2) {
                    if (scrollY2 <= 0) {
                        ViewGroup viewGroup2 = this.viewGroupH;
                        if (viewGroup2 != null) {
                            viewGroup2.requestDisallowInterceptTouchEvent(false);
                        }
                        this.scrollY_P = y2;
                        return false;
                    }
                    ViewGroup viewGroup3 = this.viewGroupH;
                    if (viewGroup3 != null) {
                        viewGroup3.requestDisallowInterceptTouchEvent(true);
                    }
                } else if (i2 > y2 && (viewGroup = this.viewGroupH) != null && viewGroup.getChildCount() > 0) {
                    if (this.viewGroupH.getScrollY() < this.viewGroupH.getChildAt(0).getMeasuredHeight() - this.viewGroupH.getMeasuredHeight()) {
                        this.viewGroupH.requestDisallowInterceptTouchEvent(false);
                        return false;
                    }
                    this.viewGroupH.requestDisallowInterceptTouchEvent(true);
                }
                this.scrollY_P = y2;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c4, code lost:
        if (getScrollY() <= r4) goto L43;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0122  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void flingAndSnap(int r19) {
        /*
            Method dump skipped, instructions count: 298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactScrollView.flingAndSnap(int):void");
    }

    private int getMaxScrollY() {
        return Math.max(0, this.mContentView.getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
    }

    @Nullable
    private OverScroller getOverScrollerFromParent() {
        if (!sTriedToGetScrollerField) {
            sTriedToGetScrollerField = true;
            try {
                Field declaredField = ScrollView.class.getDeclaredField("mScroller");
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
                throw new RuntimeException("Failed to get mScroller from ScrollView!", e2);
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002a, code lost:
        if ((r0.getParent() instanceof com.facebook.react.views.scroll.ReactHorizontalScrollView) == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0032, code lost:
        return (android.view.ViewGroup) r0.getParent();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.ViewGroup getScrollView() {
        /*
            r3 = this;
            r0 = r3
        L1:
            r1 = 0
            if (r0 == 0) goto L22
            android.view.ViewParent r2 = r0.getParent()
            if (r2 == 0) goto L22
            android.view.ViewParent r2 = r0.getParent()
            boolean r2 = r2 instanceof com.facebook.react.views.scroll.ReactHorizontalScrollView
            if (r2 != 0) goto L22
            android.view.ViewParent r2 = r0.getParent()
            boolean r2 = r2 instanceof android.view.View
            if (r2 != 0) goto L1b
            return r1
        L1b:
            android.view.ViewParent r0 = r0.getParent()
            android.view.View r0 = (android.view.View) r0
            goto L1
        L22:
            if (r0 == 0) goto L33
            android.view.ViewParent r2 = r0.getParent()
            boolean r2 = r2 instanceof com.facebook.react.views.scroll.ReactHorizontalScrollView
            if (r2 == 0) goto L33
            android.view.ViewParent r0 = r0.getParent()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            return r0
        L33:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactScrollView.getScrollView():android.view.ViewGroup");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0020, code lost:
        if ((r0.getParent() instanceof com.facebook.react.views.scroll.ReactScrollView) == false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0028, code lost:
        return (android.view.ViewGroup) r0.getParent();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:?, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.ViewGroup getScrollViewH() {
        /*
            r2 = this;
            r0 = r2
        L1:
            if (r0 == 0) goto L18
            android.view.ViewParent r1 = r0.getParent()
            if (r1 == 0) goto L18
            android.view.ViewParent r1 = r0.getParent()
            boolean r1 = r1 instanceof com.facebook.react.views.scroll.ReactScrollView
            if (r1 != 0) goto L18
            android.view.ViewParent r0 = r0.getParent()
            android.view.View r0 = (android.view.View) r0
            goto L1
        L18:
            if (r0 == 0) goto L29
            android.view.ViewParent r1 = r0.getParent()
            boolean r1 = r1 instanceof com.facebook.react.views.scroll.ReactScrollView
            if (r1 == 0) goto L29
            android.view.ViewParent r0 = r0.getParent()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            return r0
        L29:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactScrollView.getScrollViewH():android.view.ViewGroup");
    }

    private int getSnapInterval() {
        int i2 = this.mSnapInterval;
        return i2 != 0 ? i2 : getHeight();
    }

    private void handlePostTouchScrolling(int i2, int i3) {
        if ((this.mSendMomentumEvents || this.mPagingEnabled || isScrollPerfLoggingEnabled()) && this.mPostTouchRunnable == null) {
            if (this.mSendMomentumEvents) {
                enableFpsListener();
                ReactScrollViewHelper.emitScrollMomentumBeginEvent(this, i2, i3);
            }
            this.mActivelyScrolling = false;
            Runnable runnable = new Runnable() { // from class: com.facebook.react.views.scroll.ReactScrollView.1
                private boolean mSnappingToPage = false;

                @Override // java.lang.Runnable
                public void run() {
                    if (ReactScrollView.this.mActivelyScrolling) {
                        ReactScrollView.this.mActivelyScrolling = false;
                        ViewCompat.postOnAnimationDelayed(ReactScrollView.this, this, 20L);
                    } else if (!ReactScrollView.this.mPagingEnabled || this.mSnappingToPage) {
                        if (ReactScrollView.this.mSendMomentumEvents) {
                            ReactScrollViewHelper.emitScrollMomentumEndEvent(ReactScrollView.this);
                        }
                        ReactScrollView.this.mPostTouchRunnable = null;
                        ReactScrollView.this.disableFpsListener();
                    } else {
                        this.mSnappingToPage = true;
                        ReactScrollView.this.flingAndSnap(0);
                        ViewCompat.postOnAnimationDelayed(ReactScrollView.this, this, 20L);
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
        overScroller.fling(getScrollX(), getScrollY(), 0, i2, 0, 0, 0, getMaxScrollY(), 0, ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
        return overScroller.getFinalY();
    }

    private void smoothScrollAndSnap(int i2) {
        double snapInterval = getSnapInterval();
        double scrollY = getScrollY();
        double predictFinalScrollPosition = predictFinalScrollPosition(i2);
        Double.isNaN(scrollY);
        Double.isNaN(snapInterval);
        double d = scrollY / snapInterval;
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
        if (d3 != scrollY) {
            this.mActivelyScrolling = true;
            smoothScrollTo(getScrollX(), (int) d3);
        }
    }

    public void addScrollListener(ReactScrollListener reactScrollListener) {
        this.scrollListener = reactScrollListener;
    }

    @Override // android.widget.ScrollView, android.view.View
    public void computeScroll() {
        super.computeScroll();
        if (this.mChildReactScrollView != null || this.mPassToChildren) {
            if (this.mScroller.computeScrollOffset()) {
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (scrollX == currX && scrollY == currY) {
                    this.mBlcok = false;
                    this.isScrolling = false;
                    return;
                }
                return;
            }
            this.mBlcok = false;
            this.isScrolling = false;
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    public void draw(Canvas canvas) {
        if (this.mEndFillColor != 0) {
            View childAt = getChildAt(0);
            if (this.mEndBackground != null && childAt != null && childAt.getBottom() < getHeight()) {
                this.mEndBackground.setBounds(0, childAt.getBottom(), getWidth(), getHeight());
                this.mEndBackground.draw(canvas);
            }
        }
        getDrawingRect(this.mRect);
        String str = this.mOverflow;
        str.hashCode();
        if (!str.equals(ViewProps.VISIBLE)) {
            canvas.clipRect(this.mRect);
        }
        super.draw(canvas);
    }

    public void flashScrollIndicators() {
        awakenScrollBars();
    }

    @Override // android.widget.ScrollView
    public void fling(int i2) {
        float signum = Math.signum(this.mOnScrollDispatchHelper.getYFlingVelocity());
        if (signum == 0.0f) {
            signum = Math.signum(i2);
        }
        int abs = (int) (Math.abs(i2) * signum);
        if (this.mPagingEnabled) {
            abs = (int) (Math.abs(i2) * Math.signum(this.mOnScrollDispatchHelper.getYFlingVelocity()));
            flingAndSnap(abs);
        } else if (this.mScroller != null) {
            this.mScroller.fling(getScrollX(), getScrollY(), 0, abs, 0, 0, 0, Integer.MAX_VALUE, 0, ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            super.fling(abs);
        }
        handlePostTouchScrolling(0, abs);
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

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public void onChildViewAdded(View view, View view2) {
        this.mContentView = view2;
        view2.addOnLayoutChangeListener(this);
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public void onChildViewRemoved(View view, View view2) {
        this.mContentView.removeOnLayoutChangeListener(this);
        this.mContentView = null;
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mScrollEnabled && !this.mInterceptScrollEnabled) {
            this.mBlcok = false;
            boolean z = this.mPassToChildren;
            if ((z || this.mOnInterceptTouchEvent) && z) {
                this.viewGroupH = getScrollViewH();
                if (motionEvent.getAction() == 1) {
                    this.viewGroupH.requestDisallowInterceptTouchEvent(false);
                } else {
                    ((ReactScrollView) this.viewGroupH).setScollView(this);
                }
            }
            if (this.mChildReactScrollView != null && (motionEvent.getAction() == 1 || motionEvent.getAction() == 0)) {
                this.scrollY_P = (int) motionEvent.getY();
                requestDisallowInterceptTouchEvent(false);
            }
            if (this.mOnInterceptTouchEvent || this.mPassToChildren) {
                if (motionEvent.getAction() == 0) {
                    this.scrollY_P = (int) motionEvent.getY();
                    ViewGroup scrollViewH = getScrollViewH();
                    this.viewGroupH = scrollViewH;
                    int scrollY = scrollViewH.getScrollY();
                    this.scroll_postion = getScrollY();
                    this.currentY_position = (int) motionEvent.getY();
                    if (getChildCount() > 0 && this.viewGroupH.getChildCount() > 0) {
                        int measuredHeight = this.viewGroupH.getChildAt(0).getMeasuredHeight() - this.viewGroupH.getMeasuredHeight();
                        int measuredHeight2 = getChildAt(0).getMeasuredHeight() - getMeasuredHeight();
                        if (scrollY >= measuredHeight && measuredHeight2 > getHeight()) {
                            if (this.mFlinging) {
                                NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
                                ReactScrollViewHelper.emitScrollBeginDragEvent(this);
                                this.mDragging = true;
                            }
                            ViewGroup viewGroup = this.viewGroupH;
                            if (viewGroup != null) {
                                viewGroup.requestDisallowInterceptTouchEvent(true);
                            }
                        }
                    }
                } else if (motionEvent.getAction() == 1) {
                    this.scroll_postion = 0;
                    ViewGroup scrollViewH2 = getScrollViewH();
                    this.viewGroupH = scrollViewH2;
                    if (scrollViewH2 != null) {
                        ((ReactScrollView) scrollViewH2).setScollView(null);
                        this.viewGroupH.requestDisallowInterceptTouchEvent(false);
                    }
                }
            }
            if (this.mTouchEventEnable) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == 0) {
                    this.currentX = (int) motionEvent.getX();
                    this.currentY = (int) motionEvent.getY();
                    if (this.v == null) {
                        this.v = getScrollView();
                    }
                    ViewGroup viewGroup2 = this.v;
                    if (viewGroup2 != null) {
                        viewGroup2.requestDisallowInterceptTouchEvent(true);
                    }
                } else if (motionEvent.getAction() == 2) {
                    if (Math.abs(this.currentX - x) > Math.abs(this.currentY - y) * 2) {
                        if (this.v == null) {
                            this.v = getScrollView();
                        }
                        ViewGroup viewGroup3 = this.v;
                        if (viewGroup3 != null) {
                            viewGroup3.requestDisallowInterceptTouchEvent(false);
                        }
                        return super.onInterceptTouchEvent(motionEvent);
                    }
                } else if (motionEvent.getAction() == 1) {
                    if (this.v == null) {
                        this.v = getScrollView();
                    }
                    ViewGroup viewGroup4 = this.v;
                    if (viewGroup4 != null) {
                        viewGroup4.requestDisallowInterceptTouchEvent(false);
                    }
                    return false;
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

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        scrollTo(getScrollX(), getScrollY());
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (this.mContentView == null) {
            return;
        }
        int scrollY = getScrollY();
        int maxScrollY = getMaxScrollY();
        if (scrollY > maxScrollY) {
            scrollTo(getScrollX(), maxScrollY);
        }
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(i2, i3);
        setMeasuredDimension(View.MeasureSpec.getSize(i2), View.MeasureSpec.getSize(i3));
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onOverScrolled(int i2, int i3, boolean z, boolean z2) {
        int maxScrollY;
        OverScroller overScroller = this.mScroller;
        if (overScroller != null && !overScroller.isFinished() && this.mScroller.getCurrY() != this.mScroller.getFinalY() && i3 >= (maxScrollY = getMaxScrollY())) {
            this.mScroller.abortAnimation();
            i3 = maxScrollY;
        }
        super.onOverScrolled(i2, i3, z, z2);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        View childAt;
        super.onScrollChanged(i2, i3, i4, i5);
        if (!this.mBlcok) {
            if (this.mChildReactScrollView != null) {
                if (getChildCount() >= 1 && (childAt = getChildAt(0)) != null) {
                    if (getScrollY() >= childAt.getMeasuredHeight() - getMeasuredHeight()) {
                        this.mChildReactScrollView.fling(((int) this.mScroller.getCurrVelocity()) / 5);
                        this.mDoneFlinging = true;
                    }
                }
            } else if (this.mPassToChildren) {
                this.isScrolling = true;
                if (getScrollY() <= 0 && i3 != i5) {
                    scrollTo(0, 0);
                    ((ReactScrollView) getScrollViewH()).fling((-((int) this.mScroller.getCurrVelocity())) / 5);
                    this.mDoneFlinging = true;
                }
            }
        }
        this.mActivelyScrolling = true;
        ReactScrollListener reactScrollListener = this.scrollListener;
        if (reactScrollListener != null) {
            reactScrollListener.onScroll(this, i2, i3, i4, i5, this.mScroller, this.mOnScrollDispatchHelper.getXFlingVelocity(), this.mOnScrollDispatchHelper.getYFlingVelocity());
        }
        if (this.mOnScrollDispatchHelper.onScrollChanged(i2, i3)) {
            if (this.mRemoveClippedSubviews) {
                updateClippingRect();
            }
            ReactScrollViewHelper.emitScrollEvent(this, this.mOnScrollDispatchHelper.getXFlingVelocity(), this.mOnScrollDispatchHelper.getYFlingVelocity());
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mScrollEnabled) {
            this.mVelocityHelper.calculateVelocity(motionEvent);
            if (this.mOnInterceptTouchEvent && motionEvent.getAction() == 2) {
                View childAt = getChildAt(0);
                int measuredHeight = (childAt != null ? childAt.getMeasuredHeight() : 0) - getMeasuredHeight();
                int scrollY = getScrollY();
                int y = (int) motionEvent.getY();
                if (this.viewGroupH == null) {
                    this.viewGroupH = getScrollViewH();
                }
                int i2 = this.scrollY_P;
                if (i2 < y) {
                    if (scrollY <= 0) {
                        if (this.viewGroupH == null) {
                            this.viewGroupH = getScrollViewH();
                        }
                        ViewGroup viewGroup = this.viewGroupH;
                        if (viewGroup != null) {
                            viewGroup.requestDisallowInterceptTouchEvent(false);
                        }
                        this.scrollY_P = y;
                        return false;
                    }
                    if (this.viewGroupH == null) {
                        this.viewGroupH = getScrollView();
                    }
                    ViewGroup viewGroup2 = this.viewGroupH;
                    if (viewGroup2 != null) {
                        viewGroup2.requestDisallowInterceptTouchEvent(true);
                    }
                } else if (i2 > y) {
                    if (this.viewGroupH == null) {
                        this.viewGroupH = getScrollViewH();
                    }
                    if (scrollY >= measuredHeight) {
                        ViewGroup viewGroup3 = this.viewGroupH;
                        if (viewGroup3 != null) {
                            viewGroup3.requestDisallowInterceptTouchEvent(false);
                        }
                        this.scrollY_P = y;
                        return false;
                    }
                    ViewGroup viewGroup4 = this.viewGroupH;
                    if (viewGroup4 != null) {
                        viewGroup4.requestDisallowInterceptTouchEvent(true);
                    }
                }
                this.scrollY_P = y;
            }
            if (bolckevent(motionEvent)) {
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
        return false;
    }

    public void removeScrollListener() {
        this.scrollListener = null;
    }

    public void scrollToPostion(int i2, int i3, boolean z) {
        this.mBlcok = z;
        scrollTo(i2, i3);
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

    public void setOnInterceptEvent(boolean z) {
        this.mOnInterceptTouchEvent = z;
    }

    public void setOnTouchEventEnable(boolean z) {
        this.mTouchEventEnable = z;
        if (this.v == null) {
            this.v = getScrollView();
        }
        ViewGroup viewGroup = this.v;
        if (viewGroup != null) {
            ((ReactHorizontalScrollView) viewGroup).setOnTouchEvent(z);
        }
    }

    public void setOverflow(String str) {
        this.mOverflow = str;
        invalidate();
    }

    public void setPagingEnabled(boolean z) {
        this.mPagingEnabled = z;
    }

    public void setPassToChildren(boolean z) {
        this.mPassToChildren = z;
        if (z) {
            ViewGroup scrollViewH = getScrollViewH();
            this.viewGroupH = scrollViewH;
            if (scrollViewH != null) {
                ((ReactScrollView) scrollViewH).setScollView(this);
            }
        }
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void setRemoveClippedSubviews(boolean z) {
        if (z && this.mClippingRect == null) {
            this.mClippingRect = new Rect();
        }
        this.mRemoveClippedSubviews = z;
        updateClippingRect();
    }

    public void setScollView(ReactScrollView reactScrollView) {
        this.mChildReactScrollView = reactScrollView;
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

    public void smoothScrollPostion(int i2, int i3, boolean z) {
        this.mBlcok = z;
        smoothScrollTo(i2, i3);
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

    public ReactScrollView(ReactContext reactContext, @Nullable FpsListener fpsListener) {
        super(reactContext);
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
        this.mBlcok = false;
        this.mOnInterceptTouchEvent = false;
        this.mPassToChildren = false;
        this.mTouchEventEnable = false;
        this.mInterceptScrollEnabled = false;
        this.scroll_postion = 0;
        this.currentY_position = 0;
        this.isScrolling = false;
        this.delta = 0;
        this.mFpsListener = fpsListener;
        this.mReactBackgroundManager = new ReactViewBackgroundManager(this);
        this.mScroller = getOverScrollerFromParent();
        setOnHierarchyChangeListener(this);
        setScrollBarStyle(33554432);
    }

    public void setBorderRadius(float f2, int i2) {
        this.mReactBackgroundManager.setBorderRadius(f2, i2);
    }
}
