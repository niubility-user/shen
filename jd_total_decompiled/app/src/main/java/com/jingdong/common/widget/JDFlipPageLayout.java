package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
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
    */
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 2 || this.mTouchState == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        int i2 = (int) (y - this.mLastMotionY);
                        int i3 = (int) (x - this.mLastMotionX);
                        if ((Math.abs(i2) > mTouchSlop) && Math.abs(i2) > Math.abs(i3) * 5 && ((i2 < 0 && this.mPageTop.isFlipToBottom() && this.mCurrentScreen == 0) || (i2 > 0 && this.mPageBottom.isFlipToTop() && this.mCurrentScreen == 1))) {
                            this.mTouchState = 1;
                            ViewGroup viewGroup = this.fatherView;
                            if (viewGroup != null) {
                                viewGroup.requestDisallowInterceptTouchEvent(true);
                            }
                        }
                    }
                }
                this.mTouchState = 0;
            } else {
                this.mLastMotionY = y;
                this.mLastMotionX = x;
                this.mTouchState = !this.mScroller.isFinished();
            }
            return this.mTouchState != 0;
        }
        return true;
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
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    if (this.mTouchState == 1 && !this.isAddFinish) {
                        ViewGroup viewGroup = this.fatherView;
                        if (viewGroup != null) {
                            viewGroup.requestDisallowInterceptTouchEvent(true);
                        }
                        int i2 = (int) (this.mLastMotionY - y);
                        this.mLastMotionY = y;
                        int scrollY = getScrollY();
                        if (this.mCurrentScreen == 0) {
                            IFlipPage iFlipPage = this.mPageTop;
                            if (iFlipPage != null && iFlipPage.isFlipToBottom()) {
                                scrollBy(0, Math.max(scrollY * (-1), i2));
                            }
                        } else {
                            IFlipPage iFlipPage2 = this.mPageBottom;
                            if (iFlipPage2 != null && iFlipPage2.isFlipToTop()) {
                                scrollBy(0, i2);
                            }
                        }
                    }
                }
            }
            if (this.mTouchState == 1) {
                VelocityTracker velocityTracker = this.mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
                int yVelocity = (int) velocityTracker.getYVelocity();
                if (Math.abs(yVelocity) > 1000) {
                    if (yVelocity > 0 && this.mCurrentScreen == 1 && this.mPageBottom.isFlipToTop()) {
                        snapToScreen(this.mDataIndex - 1);
                    } else if (yVelocity < 0 && this.mCurrentScreen == 0 && this.mPageTop.isFlipToBottom()) {
                        snapToScreen(this.mDataIndex + 1);
                    } else {
                        snapToScreen(this.mDataIndex);
                    }
                } else {
                    snapToDestination();
                }
                VelocityTracker velocityTracker2 = this.mVelocityTracker;
                if (velocityTracker2 != null) {
                    velocityTracker2.recycle();
                    this.mVelocityTracker = null;
                }
            }
            this.mTouchState = 0;
        } else if (!this.mScroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        return true;
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
