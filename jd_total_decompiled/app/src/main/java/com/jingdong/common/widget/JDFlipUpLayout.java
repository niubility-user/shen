package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Scroller;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes12.dex */
public class JDFlipUpLayout extends ViewGroup {
    private static final String TAG = "JDFlipUpLayout";
    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_SCROLLING = 1;
    public static int mDefaultSlop;
    public static int mTouchSlop;
    private boolean canPullUp;
    private boolean isAddFinish;
    private boolean isNeedBack;
    private IFlipListener mFlipListener;
    private float mLastMotionX;
    private float mLastMotionY;
    private ScrollView mScrollView;
    private Scroller mScroller;
    private int mTouchState;

    /* loaded from: classes12.dex */
    public interface IFlipListener {
        void onFlipCompleted();
    }

    public JDFlipUpLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void initView() {
        this.mScroller = new Scroller(getContext());
        int scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mDefaultSlop = scaledTouchSlop;
        mTouchSlop = scaledTouchSlop;
    }

    private boolean isFlipToBottom() {
        if (this.canPullUp) {
            return this.mScrollView.getScrollY() + this.mScrollView.getHeight() >= this.mScrollView.getChildAt(0).getMeasuredHeight();
        }
        return false;
    }

    private void snapBack() {
        IFlipListener iFlipListener;
        if (this.mScroller.isFinished()) {
            this.isAddFinish = true;
            this.isNeedBack = false;
            int top = this.mScrollView.getTop();
            int scrollY = getScrollY();
            int i2 = top - scrollY;
            this.mScroller.startScroll(0, scrollY, 0, i2, Math.abs(i2));
            if (Math.abs(i2) > 100 && (iFlipListener = this.mFlipListener) != null) {
                iFlipListener.onFlipCompleted();
            }
            invalidate();
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            if (this.isAddFinish && this.mScroller.getCurrY() == this.mScroller.getFinalY()) {
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

    @Override // android.view.View
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            this.mScrollView = (ScrollView) getChildAt(0);
        } else {
            super.onFinishInflate();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0058, code lost:
        if (r0 != 3) goto L37;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.canPullUp) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action != 2 || this.mTouchState == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (OKLog.D) {
                OKLog.d(TAG, "onInterceptTouchEvent x = " + x);
                OKLog.d(TAG, "onInterceptTouchEvent isFlipToBottom() = " + isFlipToBottom());
            }
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        int i2 = (int) (y - this.mLastMotionY);
                        int abs = Math.abs((int) (x - this.mLastMotionX));
                        boolean z = Math.abs(i2) > mTouchSlop;
                        if (OKLog.D) {
                            OKLog.d(TAG, "onInterceptTouchEvent yDiff = " + Math.abs(i2));
                            OKLog.d(TAG, "onInterceptTouchEvent xDiff = " + abs);
                            OKLog.d(TAG, "onInterceptTouchEvent isFlipToBottom() = " + isFlipToBottom());
                        }
                        if (z && Math.abs(i2) > abs * 5 && i2 < 0 && isFlipToBottom()) {
                            this.mTouchState = 1;
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
                childAt.layout(0, i7, measuredWidth, measuredHeight + i7);
            }
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

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001b, code lost:
        if (r0 != 3) goto L29;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.canPullUp) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    if (this.mTouchState == 1 && !this.isAddFinish) {
                        int i2 = (int) (this.mLastMotionY - y);
                        this.mLastMotionY = y;
                        int scrollY = getScrollY();
                        if (isFlipToBottom()) {
                            scrollBy(0, Math.max(scrollY * (-1), i2));
                        }
                    }
                }
            }
            if (this.mTouchState == 1) {
                snapBack();
            }
            this.mTouchState = 0;
        } else if (!this.mScroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        return true;
    }

    public void setFlag(boolean z) {
        this.canPullUp = z;
    }

    public void setFlipListener(IFlipListener iFlipListener) {
        this.mFlipListener = iFlipListener;
    }

    public JDFlipUpLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTouchState = 0;
        this.isAddFinish = false;
        this.canPullUp = false;
        this.isNeedBack = false;
        initView();
    }
}
