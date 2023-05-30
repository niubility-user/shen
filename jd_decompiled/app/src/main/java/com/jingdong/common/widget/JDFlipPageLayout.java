package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes12.dex */
public class JDFlipPageLayout extends ViewGroup {
    public static final int FLIP_DIRECTION_CUR = 0;
    public static final int FLIP_DIRECTION_DOWN = 1;
    public static final int FLIP_DIRECTION_UP = -1;
    private static final int SNAP_VELOCITY = 1000;
    private static final String TAG = "FlipPageLayout";
    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_SCROLLING = 1;
    public static int mDefaultSlop;
    public static int mTouchSlop;
    private ViewGroup fatherView;
    private boolean isAddFinish;
    private int mCurrentScreen;
    private int mDataIndex;
    private int mFlipDrection;
    private IFlipListener mFlipListener;
    private float mLastMotionX;
    private float mLastMotionY;
    private int mMaximumVelocity;
    private int mNextDataIndex;
    private IFlipPage mPageBottom;
    private IFlipPage mPageTop;
    private Scroller mScroller;
    private int mTouchState;
    private VelocityTracker mVelocityTracker;

    /* loaded from: classes12.dex */
    public interface IFlipListener {
        void onFlipCompleted(int i2);
    }

    /* loaded from: classes12.dex */
    public interface IFlipPage {
        View getRootView();

        boolean isFlipToBottom();

        boolean isFlipToTop();
    }

    public JDFlipPageLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void clearOnTouchEvents() {
        this.mTouchState = 0;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void initView() {
        this.mScroller = new Scroller(getContext());
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        mDefaultSlop = scaledTouchSlop;
        mTouchSlop = scaledTouchSlop;
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    private void scrollToNext(int i2) {
        this.mDataIndex = i2;
        this.mCurrentScreen = getCurrentScreen();
    }

    private void scrollToPrev(int i2) {
        this.mDataIndex = i2;
        this.mCurrentScreen = getCurrentScreen();
    }

    private void showPage() {
        this.mPageTop.getRootView().setId(0);
        this.mPageBottom.getRootView().setId(1);
        addView(this.mPageTop.getRootView());
        addView(this.mPageBottom.getRootView());
        postInvalidate();
    }

    private void snapToDestination() {
        int i2;
        int height = getHeight() / 8;
        View currentView = getCurrentView();
        if (currentView != null) {
            int top = currentView.getTop();
            if (top < getScrollY() && getScrollY() - top >= height && this.mCurrentScreen == 0) {
                i2 = this.mDataIndex + 1;
            } else if (top > getScrollY() && top - getScrollY() >= height && this.mCurrentScreen == 1) {
                i2 = this.mDataIndex - 1;
            } else {
                i2 = this.mDataIndex;
            }
        } else {
            i2 = -1;
        }
        snapToScreen(i2);
    }

    private void snapToScreen(int i2) {
        if (this.mScroller.isFinished()) {
            this.isAddFinish = true;
            int i3 = this.mDataIndex;
            int i4 = i2 - i3;
            this.mNextDataIndex = i2;
            int i5 = 0;
            boolean z = i2 != i3;
            View focusedChild = getFocusedChild();
            if (focusedChild != null && z) {
                focusedChild.clearFocus();
            }
            View currentView = getCurrentView();
            if (currentView != null) {
                if (i4 == -1) {
                    i5 = currentView.getTop() - getHeight();
                } else if (i4 == 0) {
                    i5 = currentView.getTop();
                } else if (i4 == 1) {
                    i5 = currentView.getBottom();
                }
            }
            int scrollY = getScrollY();
            int i6 = i5 - scrollY;
            this.mScroller.startScroll(0, scrollY, 0, i6, Math.abs(i6));
            invalidate();
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            if (this.isAddFinish && this.mScroller.getCurrY() == this.mScroller.getFinalY()) {
                int i2 = this.mNextDataIndex;
                int i3 = this.mDataIndex;
                if (i2 > i3) {
                    this.mFlipDrection = 1;
                    scrollToNext(i2);
                } else if (i2 < i3) {
                    this.mFlipDrection = -1;
                    scrollToPrev(i2);
                } else {
                    this.mFlipDrection = 0;
                }
                IFlipListener iFlipListener = this.mFlipListener;
                if (iFlipListener != null) {
                    iFlipListener.onFlipCompleted(this.mFlipDrection);
                }
                this.isAddFinish = false;
            }
            scrollTo(this.mScroller.getCurrX(), this.mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        long drawingTime = getDrawingTime();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            try {
                drawChild(canvas, getChildAt(i2), drawingTime);
            } catch (Throwable th) {
                if (OKLog.E) {
                    OKLog.e(TAG, th);
                    return;
                }
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchUnhandledMove(View view, int i2) {
        if (i2 == 17) {
            if (getCurrentScreen() > 0) {
                snapToScreen(getCurrentScreen() - 1);
                return true;
            }
        } else if (i2 == 66 && getCurrentScreen() < getChildCount() - 1) {
            snapToScreen(getCurrentScreen() + 1);
            return true;
        }
        return super.dispatchUnhandledMove(view, i2);
    }

    public int getCurrentScreen() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if (getChildAt(i2).getId() == this.mDataIndex) {
                return i2;
            }
        }
        return this.mCurrentScreen;
    }

    public View getCurrentView() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if (getChildAt(i2).getId() == this.mDataIndex) {
                return getChildAt(i2);
            }
        }
        if (getChildCount() != 0) {
            return getChildAt(0);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        if (r0 != 3) goto L36;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getAction()
            r1 = 2
            r2 = 1
            if (r0 != r1) goto Ld
            int r3 = r5.mTouchState
            if (r3 == 0) goto Ld
            return r2
        Ld:
            float r3 = r6.getX()
            float r6 = r6.getY()
            r4 = 0
            if (r0 == 0) goto L6a
            if (r0 == r2) goto L67
            if (r0 == r1) goto L20
            r6 = 3
            if (r0 == r6) goto L67
            goto L77
        L20:
            float r0 = r5.mLastMotionY
            float r6 = r6 - r0
            int r6 = (int) r6
            float r0 = r5.mLastMotionX
            float r3 = r3 - r0
            int r0 = (int) r3
            int r1 = com.jingdong.common.widget.JDFlipPageLayout.mTouchSlop
            int r3 = java.lang.Math.abs(r6)
            if (r3 <= r1) goto L32
            r1 = 1
            goto L33
        L32:
            r1 = 0
        L33:
            if (r1 == 0) goto L77
            int r1 = java.lang.Math.abs(r6)
            int r0 = java.lang.Math.abs(r0)
            int r0 = r0 * 5
            if (r1 <= r0) goto L77
            if (r6 >= 0) goto L4f
            com.jingdong.common.widget.JDFlipPageLayout$IFlipPage r0 = r5.mPageTop
            boolean r0 = r0.isFlipToBottom()
            if (r0 == 0) goto L4f
            int r0 = r5.mCurrentScreen
            if (r0 == 0) goto L5d
        L4f:
            if (r6 <= 0) goto L77
            com.jingdong.common.widget.JDFlipPageLayout$IFlipPage r6 = r5.mPageBottom
            boolean r6 = r6.isFlipToTop()
            if (r6 == 0) goto L77
            int r6 = r5.mCurrentScreen
            if (r6 != r2) goto L77
        L5d:
            r5.mTouchState = r2
            android.view.ViewGroup r6 = r5.fatherView
            if (r6 == 0) goto L77
            r6.requestDisallowInterceptTouchEvent(r2)
            goto L77
        L67:
            r5.mTouchState = r4
            goto L77
        L6a:
            r5.mLastMotionY = r6
            r5.mLastMotionX = r3
            android.widget.Scroller r6 = r5.mScroller
            boolean r6 = r6.isFinished()
            r6 = r6 ^ r2
            r5.mTouchState = r6
        L77:
            int r6 = r5.mTouchState
            if (r6 == 0) goto L7c
            goto L7d
        L7c:
            r2 = 0
        L7d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.widget.JDFlipPageLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i7 = measuredHeight * i6;
                try {
                    childAt.layout(0, i7, measuredWidth, measuredHeight + i7);
                } catch (Exception e2) {
                    if (OKLog.D) {
                        OKLog.e(TAG, e2);
                    }
                }
            }
        }
        if (childCount > 0) {
            snapToScreen(this.mDataIndex);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            try {
                getChildAt(i4).measure(i2, i3);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0021, code lost:
        if (r0 != 3) goto L58;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            android.view.VelocityTracker r0 = r4.mVelocityTracker
            if (r0 != 0) goto La
            android.view.VelocityTracker r0 = android.view.VelocityTracker.obtain()
            r4.mVelocityTracker = r0
        La:
            android.view.VelocityTracker r0 = r4.mVelocityTracker
            r0.addMovement(r5)
            int r0 = r5.getAction()
            float r5 = r5.getY()
            r1 = 1
            if (r0 == 0) goto Lbe
            r2 = 0
            if (r0 == r1) goto L65
            r3 = 2
            if (r0 == r3) goto L25
            r5 = 3
            if (r0 == r5) goto L65
            goto Lcb
        L25:
            int r0 = r4.mTouchState
            if (r0 != r1) goto Lcb
            boolean r0 = r4.isAddFinish
            if (r0 != 0) goto Lcb
            android.view.ViewGroup r0 = r4.fatherView
            if (r0 == 0) goto L34
            r0.requestDisallowInterceptTouchEvent(r1)
        L34:
            float r0 = r4.mLastMotionY
            float r0 = r0 - r5
            int r0 = (int) r0
            r4.mLastMotionY = r5
            int r5 = r4.getScrollY()
            int r3 = r4.mCurrentScreen
            if (r3 != 0) goto L57
            com.jingdong.common.widget.JDFlipPageLayout$IFlipPage r3 = r4.mPageTop
            if (r3 == 0) goto Lcb
            boolean r3 = r3.isFlipToBottom()
            if (r3 == 0) goto Lcb
            int r5 = r5 * (-1)
            int r5 = java.lang.Math.max(r5, r0)
            r4.scrollBy(r2, r5)
            goto Lcb
        L57:
            com.jingdong.common.widget.JDFlipPageLayout$IFlipPage r5 = r4.mPageBottom
            if (r5 == 0) goto Lcb
            boolean r5 = r5.isFlipToTop()
            if (r5 == 0) goto Lcb
            r4.scrollBy(r2, r0)
            goto Lcb
        L65:
            int r5 = r4.mTouchState
            if (r5 != r1) goto Lbb
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            int r0 = r4.mMaximumVelocity
            float r0 = (float) r0
            r3 = 1000(0x3e8, float:1.401E-42)
            r5.computeCurrentVelocity(r3, r0)
            float r5 = r5.getYVelocity()
            int r5 = (int) r5
            int r0 = java.lang.Math.abs(r5)
            if (r0 <= r3) goto Lae
            if (r5 <= 0) goto L93
            int r0 = r4.mCurrentScreen
            if (r0 != r1) goto L93
            com.jingdong.common.widget.JDFlipPageLayout$IFlipPage r0 = r4.mPageBottom
            boolean r0 = r0.isFlipToTop()
            if (r0 == 0) goto L93
            int r5 = r4.mDataIndex
            int r5 = r5 - r1
            r4.snapToScreen(r5)
            goto Lb1
        L93:
            if (r5 >= 0) goto La8
            int r5 = r4.mCurrentScreen
            if (r5 != 0) goto La8
            com.jingdong.common.widget.JDFlipPageLayout$IFlipPage r5 = r4.mPageTop
            boolean r5 = r5.isFlipToBottom()
            if (r5 == 0) goto La8
            int r5 = r4.mDataIndex
            int r5 = r5 + r1
            r4.snapToScreen(r5)
            goto Lb1
        La8:
            int r5 = r4.mDataIndex
            r4.snapToScreen(r5)
            goto Lb1
        Lae:
            r4.snapToDestination()
        Lb1:
            android.view.VelocityTracker r5 = r4.mVelocityTracker
            if (r5 == 0) goto Lbb
            r5.recycle()
            r5 = 0
            r4.mVelocityTracker = r5
        Lbb:
            r4.mTouchState = r2
            goto Lcb
        Lbe:
            android.widget.Scroller r5 = r4.mScroller
            boolean r5 = r5.isFinished()
            if (r5 != 0) goto Lcb
            android.widget.Scroller r5 = r4.mScroller
            r5.abortAnimation()
        Lcb:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.widget.JDFlipPageLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setFatherView(ViewGroup viewGroup) {
        this.fatherView = viewGroup;
    }

    public void setFlipFragment(IFlipPage iFlipPage, IFlipPage iFlipPage2) {
        this.mPageTop = iFlipPage;
        this.mPageBottom = iFlipPage2;
        getChildAt(0).setId(0);
        getChildAt(1).setId(1);
        postInvalidate();
    }

    public void setFlipListener(IFlipListener iFlipListener) {
        this.mFlipListener = iFlipListener;
    }

    public void setFlipPage(IFlipPage iFlipPage, IFlipPage iFlipPage2) {
        this.mPageTop = iFlipPage;
        this.mPageBottom = iFlipPage2;
        showPage();
    }

    public void snapToCurrent() {
        snapToScreen(this.mCurrentScreen);
        clearOnTouchEvents();
    }

    public void snapToNext() {
        if (this.mCurrentScreen == 0) {
            snapToScreen(1);
        }
    }

    public void snapToPrev() {
        if (this.mCurrentScreen == 1) {
            snapToScreen(0);
        }
    }

    public JDFlipPageLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mFlipDrection = 0;
        this.mTouchState = 0;
        this.mDataIndex = 0;
        this.mCurrentScreen = 0;
        this.mNextDataIndex = 0;
        this.isAddFinish = false;
        initView();
    }
}
