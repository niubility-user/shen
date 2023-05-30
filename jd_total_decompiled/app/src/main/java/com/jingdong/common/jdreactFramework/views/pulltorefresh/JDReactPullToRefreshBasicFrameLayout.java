package com.jingdong.common.jdreactFramework.views.pulltorefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TextView;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class JDReactPullToRefreshBasicFrameLayout extends ViewGroup {
    private static final boolean DEBUG_LAYOUT = true;
    private static final byte FLAG_AUTO_REFRESH_AT_ONCE = 1;
    private static final byte FLAG_AUTO_REFRESH_BUT_LATER = 2;
    private static final byte FLAG_ENABLE_NEXT_PTR_AT_ONCE = 4;
    private static final byte FLAG_PIN_CONTENT = 8;
    private static int ID = 1;
    private static final byte MASK_AUTO_REFRESH = 3;
    public static final byte PTR_STATUS_COMPLETE = 4;
    public static final byte PTR_STATUS_INIT = 1;
    public static final byte PTR_STATUS_LOADING = 3;
    public static final byte PTR_STATUS_PREPARE = 2;
    protected final String LOG_TAG;
    private int mContainerId;
    protected View mContent;
    private boolean mDisableWhenHorizontalMove;
    private int mDurationToClose;
    private int mDurationToCloseHeader;
    private int mFlag;
    private boolean mHasSendCancelEvent;
    private int mHeaderHeight;
    private int mHeaderId;
    private View mHeaderView;
    private JDReactPullToRefreshIndicator mJDReactPullToRefreshIndicator;
    private boolean mKeepHeaderWhenRefresh;
    private MotionEvent mLastMoveEvent;
    private int mLoadingMinTime;
    private long mLoadingStartTime;
    private int mPagingTouchSlop;
    private Runnable mPerformRefreshCompleteDelay;
    private boolean mPreventForHorizontal;
    private JDReactPullToRefreshHandler mPtrHandler;
    private JDReactPullToRefreshUIHandlerHolder mPtrUIHandlerHolder;
    private boolean mPullToRefresh;
    private JDReactPullToRefreshUIHandlerHook mRefreshCompleteHook;
    private ScrollChecker mScrollChecker;
    private byte mStatus;

    /* loaded from: classes5.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public class ScrollChecker implements Runnable {
        private boolean mIsRunning = false;
        private int mLastFlingY;
        private Scroller mScroller;
        private int mStart;
        private int mTo;

        public ScrollChecker() {
            this.mScroller = new Scroller(JDReactPullToRefreshBasicFrameLayout.this.getContext());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void destroy() {
            reset();
            if (this.mScroller.isFinished()) {
                return;
            }
            this.mScroller.forceFinished(true);
        }

        private void finish() {
            JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout = JDReactPullToRefreshBasicFrameLayout.this;
            OKLog.v(jDReactPullToRefreshBasicFrameLayout.LOG_TAG, String.format("finish, currentPos:%s", Integer.valueOf(jDReactPullToRefreshBasicFrameLayout.mJDReactPullToRefreshIndicator.getCurrentPosY())));
            reset();
            JDReactPullToRefreshBasicFrameLayout.this.onPtrScrollFinish();
        }

        private void reset() {
            this.mIsRunning = false;
            this.mLastFlingY = 0;
            JDReactPullToRefreshBasicFrameLayout.this.removeCallbacks(this);
        }

        public void abortIfWorking() {
            if (this.mIsRunning) {
                if (!this.mScroller.isFinished()) {
                    this.mScroller.forceFinished(true);
                }
                JDReactPullToRefreshBasicFrameLayout.this.onPtrScrollAbort();
                reset();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z = !this.mScroller.computeScrollOffset() || this.mScroller.isFinished();
            int currY = this.mScroller.getCurrY();
            int i2 = currY - this.mLastFlingY;
            if (i2 != 0) {
                OKLog.v(JDReactPullToRefreshBasicFrameLayout.this.LOG_TAG, String.format("scroll: %s, start: %s, to: %s, currentPos: %s, current :%s, last: %s, delta: %s", Boolean.valueOf(z), Integer.valueOf(this.mStart), Integer.valueOf(this.mTo), Integer.valueOf(JDReactPullToRefreshBasicFrameLayout.this.mJDReactPullToRefreshIndicator.getCurrentPosY()), Integer.valueOf(currY), Integer.valueOf(this.mLastFlingY), Integer.valueOf(i2)));
            }
            if (!z) {
                this.mLastFlingY = currY;
                JDReactPullToRefreshBasicFrameLayout.this.movePos(i2);
                JDReactPullToRefreshBasicFrameLayout.this.post(this);
                return;
            }
            finish();
        }

        public void tryToScrollTo(int i2, int i3) {
            if (JDReactPullToRefreshBasicFrameLayout.this.mJDReactPullToRefreshIndicator.isAlreadyHere(i2)) {
                return;
            }
            int currentPosY = JDReactPullToRefreshBasicFrameLayout.this.mJDReactPullToRefreshIndicator.getCurrentPosY();
            this.mStart = currentPosY;
            this.mTo = i2;
            int i4 = i2 - currentPosY;
            OKLog.d(JDReactPullToRefreshBasicFrameLayout.this.LOG_TAG, String.format("tryToScrollTo: start: %s, distance:%s, to:%s", Integer.valueOf(currentPosY), Integer.valueOf(i4), Integer.valueOf(i2)));
            JDReactPullToRefreshBasicFrameLayout.this.removeCallbacks(this);
            this.mLastFlingY = 0;
            if (!this.mScroller.isFinished()) {
                this.mScroller.forceFinished(true);
            }
            this.mScroller.startScroll(0, 0, 0, i4, i3);
            JDReactPullToRefreshBasicFrameLayout.this.post(this);
            this.mIsRunning = true;
        }
    }

    public JDReactPullToRefreshBasicFrameLayout(Context context) {
        this(context, null);
    }

    private void clearFlag() {
        this.mFlag &= -4;
    }

    private void layoutChildren() {
        int currentPosY = this.mJDReactPullToRefreshIndicator.getCurrentPosY();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        View view = this.mHeaderView;
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            int i2 = marginLayoutParams.leftMargin + paddingLeft;
            int i3 = -(((this.mHeaderHeight - paddingTop) - marginLayoutParams.topMargin) - currentPosY);
            int measuredWidth = this.mHeaderView.getMeasuredWidth() + i2;
            int measuredHeight = this.mHeaderView.getMeasuredHeight() + i3;
            this.mHeaderView.layout(i2, i3, measuredWidth, measuredHeight);
            OKLog.d(this.LOG_TAG, String.format("onLayout header: %s %s %s %s", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(measuredWidth), Integer.valueOf(measuredHeight)));
        }
        if (this.mContent != null) {
            if (isPinContent()) {
                currentPosY = 0;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mContent.getLayoutParams();
            int i4 = paddingLeft + marginLayoutParams2.leftMargin;
            int i5 = paddingTop + marginLayoutParams2.topMargin + currentPosY;
            int measuredWidth2 = this.mContent.getMeasuredWidth() + i4;
            int measuredHeight2 = this.mContent.getMeasuredHeight() + i5;
            OKLog.d(this.LOG_TAG, String.format("onLayout content: %s %s %s %s", Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(measuredWidth2), Integer.valueOf(measuredHeight2)));
            this.mContent.layout(i4, i5, measuredWidth2, measuredHeight2);
        }
    }

    private void measureContentView(View view, int i2, int i3) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin, marginLayoutParams.height));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void movePos(float f2) {
        int i2 = 0;
        if (f2 < 0.0f && this.mJDReactPullToRefreshIndicator.isInStartPosition()) {
            OKLog.e(this.LOG_TAG, String.format("has reached the top", new Object[0]));
            return;
        }
        int currentPosY = this.mJDReactPullToRefreshIndicator.getCurrentPosY() + ((int) f2);
        if (this.mJDReactPullToRefreshIndicator.willOverTop(currentPosY)) {
            OKLog.e(this.LOG_TAG, String.format("over top", new Object[0]));
        } else {
            i2 = currentPosY;
        }
        this.mJDReactPullToRefreshIndicator.setCurrentPos(i2);
        updatePos(i2 - this.mJDReactPullToRefreshIndicator.getLastPosY());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUIRefreshComplete(boolean z) {
        if (this.mJDReactPullToRefreshIndicator.hasLeftStartPosition() && !z && this.mRefreshCompleteHook != null) {
            OKLog.d(this.LOG_TAG, "notifyUIRefreshComplete mRefreshCompleteHook run.");
            this.mRefreshCompleteHook.takeOver();
            return;
        }
        if (this.mPtrUIHandlerHolder.hasHandler()) {
            OKLog.i(this.LOG_TAG, "JDReactPullToRefreshUIHandler: onUIRefreshComplete");
            this.mPtrUIHandlerHolder.onUIRefreshComplete(this);
        }
        this.mJDReactPullToRefreshIndicator.onUIRefreshComplete();
        tryScrollBackToTopAfterComplete();
        tryToNotifyReset();
    }

    private void onRelease(boolean z) {
        tryToPerformRefresh();
        byte b = this.mStatus;
        if (b != 3) {
            if (b == 4) {
                notifyUIRefreshComplete(false);
            } else {
                tryScrollBackToTopAbortRefresh();
            }
        } else if (this.mKeepHeaderWhenRefresh) {
            if (!this.mJDReactPullToRefreshIndicator.isOverOffsetToKeepHeaderWhileLoading() || z) {
                return;
            }
            this.mScrollChecker.tryToScrollTo(this.mJDReactPullToRefreshIndicator.getOffsetToKeepHeaderWhileLoading(), this.mDurationToClose);
        } else {
            tryScrollBackToTopWhileLoading();
        }
    }

    private boolean performAutoRefreshButLater() {
        return (this.mFlag & 3) == 2;
    }

    private void performRefresh() {
        this.mLoadingStartTime = System.currentTimeMillis();
        if (this.mPtrUIHandlerHolder.hasHandler()) {
            this.mPtrUIHandlerHolder.onUIRefreshBegin(this);
            OKLog.i(this.LOG_TAG, "JDReactPullToRefreshUIHandler: onUIRefreshBegin");
        }
        JDReactPullToRefreshHandler jDReactPullToRefreshHandler = this.mPtrHandler;
        if (jDReactPullToRefreshHandler != null) {
            jDReactPullToRefreshHandler.onRefreshBegin(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performRefreshComplete() {
        this.mStatus = (byte) 4;
        if (this.mScrollChecker.mIsRunning && isAutoRefresh()) {
            OKLog.d(this.LOG_TAG, String.format("performRefreshComplete do nothing, scrolling: %s, auto refresh: %s", Boolean.valueOf(this.mScrollChecker.mIsRunning), Integer.valueOf(this.mFlag)));
        } else {
            notifyUIRefreshComplete(false);
        }
    }

    private void sendCancelEvent() {
        OKLog.d(this.LOG_TAG, "send cancel event");
        MotionEvent motionEvent = this.mLastMoveEvent;
        if (motionEvent == null) {
            return;
        }
        dispatchTouchEventSupper(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime() + ViewConfiguration.getLongPressTimeout(), 3, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState()));
    }

    private void sendDownEvent() {
        OKLog.d(this.LOG_TAG, "send down event");
        MotionEvent motionEvent = this.mLastMoveEvent;
        dispatchTouchEventSupper(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 0, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState()));
    }

    private void tryScrollBackToTop() {
        if (this.mJDReactPullToRefreshIndicator.isUnderTouch()) {
            return;
        }
        this.mScrollChecker.tryToScrollTo(0, this.mDurationToCloseHeader);
    }

    private void tryScrollBackToTopAbortRefresh() {
        tryScrollBackToTop();
    }

    private void tryScrollBackToTopAfterComplete() {
        tryScrollBackToTop();
    }

    private void tryScrollBackToTopWhileLoading() {
        tryScrollBackToTop();
    }

    private boolean tryToNotifyReset() {
        byte b = this.mStatus;
        if ((b == 4 || b == 2) && this.mJDReactPullToRefreshIndicator.isInStartPosition()) {
            if (this.mPtrUIHandlerHolder.hasHandler()) {
                this.mPtrUIHandlerHolder.onUIReset(this);
                OKLog.i(this.LOG_TAG, "JDReactPullToRefreshUIHandler: onUIReset");
            }
            this.mStatus = (byte) 1;
            clearFlag();
            return true;
        }
        return false;
    }

    private boolean tryToPerformRefresh() {
        if (this.mStatus != 2) {
            return false;
        }
        if ((this.mJDReactPullToRefreshIndicator.isOverOffsetToKeepHeaderWhileLoading() && isAutoRefresh()) || this.mJDReactPullToRefreshIndicator.isOverOffsetToRefresh()) {
            this.mStatus = (byte) 3;
            performRefresh();
        }
        return false;
    }

    private void updatePos(int i2) {
        if (i2 == 0) {
            return;
        }
        boolean isUnderTouch = this.mJDReactPullToRefreshIndicator.isUnderTouch();
        if (isUnderTouch && !this.mHasSendCancelEvent && this.mJDReactPullToRefreshIndicator.hasMovedAfterPressedDown()) {
            this.mHasSendCancelEvent = true;
            sendCancelEvent();
        }
        if ((this.mJDReactPullToRefreshIndicator.hasJustLeftStartPosition() && this.mStatus == 1) || (this.mJDReactPullToRefreshIndicator.goDownCrossFinishPosition() && this.mStatus == 4 && isEnabledNextPtrAtOnce())) {
            this.mStatus = (byte) 2;
            this.mPtrUIHandlerHolder.onUIRefreshPrepare(this);
            OKLog.i(this.LOG_TAG, String.format("JDReactPullToRefreshUIHandler: onUIRefreshPrepare, mFlag %s", Integer.valueOf(this.mFlag)));
        }
        if (this.mJDReactPullToRefreshIndicator.hasJustBackToStartPosition()) {
            tryToNotifyReset();
            if (isUnderTouch) {
                sendDownEvent();
            }
        }
        if (this.mStatus == 2) {
            if (isUnderTouch && !isAutoRefresh() && this.mPullToRefresh && this.mJDReactPullToRefreshIndicator.crossRefreshLineFromTopToBottom()) {
                tryToPerformRefresh();
            }
            if (performAutoRefreshButLater() && this.mJDReactPullToRefreshIndicator.hasJustReachedHeaderHeightFromTopToBottom()) {
                tryToPerformRefresh();
            }
        }
        OKLog.v(this.LOG_TAG, String.format("updatePos: change: %s, current: %s last: %s, top: %s, headerHeight: %s", Integer.valueOf(i2), Integer.valueOf(this.mJDReactPullToRefreshIndicator.getCurrentPosY()), Integer.valueOf(this.mJDReactPullToRefreshIndicator.getLastPosY()), Integer.valueOf(this.mContent.getTop()), Integer.valueOf(this.mHeaderHeight)));
        this.mHeaderView.offsetTopAndBottom(i2);
        if (!isPinContent()) {
            this.mContent.offsetTopAndBottom(i2);
        }
        invalidate();
        if (this.mPtrUIHandlerHolder.hasHandler()) {
            this.mPtrUIHandlerHolder.onUIPositionChange(this, isUnderTouch, this.mStatus, this.mJDReactPullToRefreshIndicator);
        }
        onPositionChange(isUnderTouch, this.mStatus, this.mJDReactPullToRefreshIndicator);
    }

    public void addPtrUIHandler(JDReactPullToRefreshUIHandler jDReactPullToRefreshUIHandler) {
        JDReactPullToRefreshUIHandlerHolder.addHandler(this.mPtrUIHandlerHolder, jDReactPullToRefreshUIHandler);
    }

    public void autoRefresh() {
        autoRefresh(true, this.mDurationToCloseHeader);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof LayoutParams);
    }

    public void disableWhenHorizontalMove(boolean z) {
        this.mDisableWhenHorizontalMove = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001e, code lost:
        if (r0 != 3) goto L54;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        JDReactPullToRefreshHandler jDReactPullToRefreshHandler;
        if (isEnabled() && this.mContent != null && this.mHeaderView != null) {
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        this.mLastMoveEvent = motionEvent;
                        this.mJDReactPullToRefreshIndicator.onMove(motionEvent.getX(), motionEvent.getY());
                        float offsetX = this.mJDReactPullToRefreshIndicator.getOffsetX();
                        float offsetY = this.mJDReactPullToRefreshIndicator.getOffsetY();
                        if (this.mDisableWhenHorizontalMove && !this.mPreventForHorizontal && Math.abs(offsetX) > this.mPagingTouchSlop && Math.abs(offsetX) > Math.abs(offsetY) && this.mJDReactPullToRefreshIndicator.isInStartPosition()) {
                            this.mPreventForHorizontal = true;
                        }
                        if (this.mPreventForHorizontal) {
                            return dispatchTouchEventSupper(motionEvent);
                        }
                        boolean z = offsetY > 0.0f;
                        boolean z2 = !z;
                        boolean hasLeftStartPosition = this.mJDReactPullToRefreshIndicator.hasLeftStartPosition();
                        JDReactPullToRefreshHandler jDReactPullToRefreshHandler2 = this.mPtrHandler;
                        OKLog.v(this.LOG_TAG, String.format("ACTION_MOVE: offsetY:%s, currentPos: %s, moveUp: %s, canMoveUp: %s, moveDown: %s: canMoveDown: %s", Float.valueOf(offsetY), Integer.valueOf(this.mJDReactPullToRefreshIndicator.getCurrentPosY()), Boolean.valueOf(z2), Boolean.valueOf(hasLeftStartPosition), Boolean.valueOf(z), Boolean.valueOf(jDReactPullToRefreshHandler2 != null && jDReactPullToRefreshHandler2.checkCanDoRefresh(this, this.mContent, this.mHeaderView))));
                        if (z && (jDReactPullToRefreshHandler = this.mPtrHandler) != null && !jDReactPullToRefreshHandler.checkCanDoRefresh(this, this.mContent, this.mHeaderView)) {
                            return dispatchTouchEventSupper(motionEvent);
                        }
                        if ((z2 && hasLeftStartPosition) || z) {
                            movePos(offsetY);
                            return true;
                        }
                    }
                    return dispatchTouchEventSupper(motionEvent);
                }
                this.mJDReactPullToRefreshIndicator.onRelease();
                if (this.mJDReactPullToRefreshIndicator.hasLeftStartPosition()) {
                    OKLog.d(this.LOG_TAG, "call onRelease when user release");
                    onRelease(false);
                    if (this.mJDReactPullToRefreshIndicator.hasMovedAfterPressedDown()) {
                        sendCancelEvent();
                        return true;
                    }
                    return dispatchTouchEventSupper(motionEvent);
                }
                return dispatchTouchEventSupper(motionEvent);
            }
            this.mHasSendCancelEvent = false;
            this.mJDReactPullToRefreshIndicator.onPressDown(motionEvent.getX(), motionEvent.getY());
            this.mScrollChecker.abortIfWorking();
            this.mPreventForHorizontal = false;
            dispatchTouchEventSupper(motionEvent);
            return true;
        }
        return dispatchTouchEventSupper(motionEvent);
    }

    public boolean dispatchTouchEventSupper(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public View getContentView() {
        return this.mContent;
    }

    public float getDurationToClose() {
        return this.mDurationToClose;
    }

    public long getDurationToCloseHeader() {
        return this.mDurationToCloseHeader;
    }

    public int getHeaderHeight() {
        return this.mHeaderHeight;
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    public int getOffsetToKeepHeaderWhileLoading() {
        return this.mJDReactPullToRefreshIndicator.getOffsetToKeepHeaderWhileLoading();
    }

    public int getOffsetToRefresh() {
        return this.mJDReactPullToRefreshIndicator.getOffsetToRefresh();
    }

    public float getRatioOfHeaderToHeightRefresh() {
        return this.mJDReactPullToRefreshIndicator.getRatioOfHeaderToHeightRefresh();
    }

    public float getResistance() {
        return this.mJDReactPullToRefreshIndicator.getResistance();
    }

    public boolean isAutoRefresh() {
        return (this.mFlag & 3) > 0;
    }

    public boolean isEnabledNextPtrAtOnce() {
        return (this.mFlag & 4) > 0;
    }

    public boolean isKeepHeaderWhenRefresh() {
        return this.mKeepHeaderWhenRefresh;
    }

    public boolean isPinContent() {
        return (this.mFlag & 8) > 0;
    }

    public boolean isPullToRefresh() {
        return this.mPullToRefresh;
    }

    public boolean isRefreshing() {
        return this.mStatus == 3;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ScrollChecker scrollChecker = this.mScrollChecker;
        if (scrollChecker != null) {
            scrollChecker.destroy();
        }
        Runnable runnable = this.mPerformRefreshCompleteDelay;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onFinishInflate() {
        int childCount = getChildCount();
        if (childCount <= 2) {
            if (childCount == 2) {
                int i2 = this.mHeaderId;
                if (i2 != 0 && this.mHeaderView == null) {
                    this.mHeaderView = findViewById(i2);
                }
                int i3 = this.mContainerId;
                if (i3 != 0 && this.mContent == null) {
                    this.mContent = findViewById(i3);
                }
                if (this.mContent == null || this.mHeaderView == null) {
                    View childAt = getChildAt(0);
                    View childAt2 = getChildAt(1);
                    if (childAt instanceof JDReactPullToRefreshUIHandler) {
                        this.mHeaderView = childAt;
                        this.mContent = childAt2;
                    } else if (childAt2 instanceof JDReactPullToRefreshUIHandler) {
                        this.mHeaderView = childAt2;
                        this.mContent = childAt;
                    } else {
                        View view = this.mContent;
                        if (view == null && this.mHeaderView == null) {
                            this.mHeaderView = childAt;
                            this.mContent = childAt2;
                        } else {
                            View view2 = this.mHeaderView;
                            if (view2 == null) {
                                if (view == childAt) {
                                    childAt = childAt2;
                                }
                                this.mHeaderView = childAt;
                            } else {
                                if (view2 == childAt) {
                                    childAt = childAt2;
                                }
                                this.mContent = childAt;
                            }
                        }
                    }
                }
            } else if (childCount == 1) {
                this.mContent = getChildAt(0);
            } else {
                TextView textView = new TextView(getContext());
                textView.setClickable(true);
                textView.setTextColor(-39424);
                textView.setGravity(17);
                textView.setTextSize(20.0f);
                textView.setText("The content view in PtrFrameLayout is empty. Do you forget to specify its id in xml layout file?");
                this.mContent = textView;
                addView(textView);
            }
            View view3 = this.mHeaderView;
            if (view3 != null) {
                view3.bringToFront();
            }
            super.onFinishInflate();
            return;
        }
        throw new IllegalStateException("PtrFrameLayout can only contains 2 children");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        layoutChildren();
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        OKLog.d(this.LOG_TAG, String.format("onMeasure frame: width: %s, height: %s, padding: %s %s %s %s", Integer.valueOf(getMeasuredHeight()), Integer.valueOf(getMeasuredWidth()), Integer.valueOf(getPaddingLeft()), Integer.valueOf(getPaddingRight()), Integer.valueOf(getPaddingTop()), Integer.valueOf(getPaddingBottom())));
        View view = this.mHeaderView;
        if (view != null) {
            measureChildWithMargins(view, i2, 0, i3, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mHeaderView.getLayoutParams();
            int measuredHeight = this.mHeaderView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            this.mHeaderHeight = measuredHeight;
            this.mJDReactPullToRefreshIndicator.setHeaderHeight(measuredHeight);
        }
        View view2 = this.mContent;
        if (view2 != null) {
            measureContentView(view2, i2, i3);
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mContent.getLayoutParams();
            OKLog.d(this.LOG_TAG, String.format("onMeasure content, width: %s, height: %s, margin: %s %s %s %s", Integer.valueOf(getMeasuredWidth()), Integer.valueOf(getMeasuredHeight()), Integer.valueOf(marginLayoutParams2.leftMargin), Integer.valueOf(marginLayoutParams2.topMargin), Integer.valueOf(marginLayoutParams2.rightMargin), Integer.valueOf(marginLayoutParams2.bottomMargin)));
            OKLog.d(this.LOG_TAG, String.format("onMeasure, currentPos: %s, lastPos: %s, top: %s", Integer.valueOf(this.mJDReactPullToRefreshIndicator.getCurrentPosY()), Integer.valueOf(this.mJDReactPullToRefreshIndicator.getLastPosY()), Integer.valueOf(this.mContent.getTop())));
        }
    }

    protected void onPositionChange(boolean z, byte b, JDReactPullToRefreshIndicator jDReactPullToRefreshIndicator) {
    }

    protected void onPtrScrollAbort() {
        if (this.mJDReactPullToRefreshIndicator.hasLeftStartPosition() && isAutoRefresh()) {
            OKLog.d(this.LOG_TAG, "call onRelease after scroll abort");
            onRelease(true);
        }
    }

    protected void onPtrScrollFinish() {
        if (this.mJDReactPullToRefreshIndicator.hasLeftStartPosition() && isAutoRefresh()) {
            OKLog.d(this.LOG_TAG, "call onRelease after scroll finish");
            onRelease(true);
        }
    }

    public final void refreshComplete() {
        OKLog.i(this.LOG_TAG, "refreshComplete");
        JDReactPullToRefreshUIHandlerHook jDReactPullToRefreshUIHandlerHook = this.mRefreshCompleteHook;
        if (jDReactPullToRefreshUIHandlerHook != null) {
            jDReactPullToRefreshUIHandlerHook.reset();
        }
        int currentTimeMillis = (int) (this.mLoadingMinTime - (System.currentTimeMillis() - this.mLoadingStartTime));
        if (currentTimeMillis <= 0) {
            OKLog.d(this.LOG_TAG, "performRefreshComplete at once");
            performRefreshComplete();
            return;
        }
        postDelayed(this.mPerformRefreshCompleteDelay, currentTimeMillis);
        OKLog.d(this.LOG_TAG, String.format("performRefreshComplete after delay: %s", Integer.valueOf(currentTimeMillis)));
    }

    public void removePtrUIHandler(JDReactPullToRefreshUIHandler jDReactPullToRefreshUIHandler) {
        this.mPtrUIHandlerHolder = JDReactPullToRefreshUIHandlerHolder.removeHandler(this.mPtrUIHandlerHolder, jDReactPullToRefreshUIHandler);
    }

    public void setDurationToClose(int i2) {
        this.mDurationToClose = i2;
    }

    public void setDurationToCloseHeader(int i2) {
        this.mDurationToCloseHeader = i2;
    }

    public void setEnabledNextPtrAtOnce(boolean z) {
        if (z) {
            this.mFlag |= 4;
        } else {
            this.mFlag &= -5;
        }
    }

    public void setHeaderView(View view) {
        View view2 = this.mHeaderView;
        if (view2 != null && view != null && view2 != view) {
            removeView(view2);
        }
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new LayoutParams(-1, -2));
        }
        this.mHeaderView = view;
        addView(view);
    }

    @Deprecated
    public void setInterceptEventWhileWorking(boolean z) {
    }

    public void setJDReactPullToRefreshIndicator(JDReactPullToRefreshIndicator jDReactPullToRefreshIndicator) {
        JDReactPullToRefreshIndicator jDReactPullToRefreshIndicator2 = this.mJDReactPullToRefreshIndicator;
        if (jDReactPullToRefreshIndicator2 != null && jDReactPullToRefreshIndicator2 != jDReactPullToRefreshIndicator) {
            jDReactPullToRefreshIndicator.convertFrom(jDReactPullToRefreshIndicator2);
        }
        this.mJDReactPullToRefreshIndicator = jDReactPullToRefreshIndicator;
    }

    public void setKeepHeaderWhenRefresh(boolean z) {
        this.mKeepHeaderWhenRefresh = z;
    }

    public void setLoadingMinTime(int i2) {
        this.mLoadingMinTime = i2;
    }

    public void setOffsetToKeepHeaderWhileLoading(int i2) {
        this.mJDReactPullToRefreshIndicator.setOffsetToKeepHeaderWhileLoading(i2);
    }

    public void setOffsetToRefresh(int i2) {
        this.mJDReactPullToRefreshIndicator.setOffsetToRefresh(i2);
    }

    public void setPinContent(boolean z) {
        if (z) {
            this.mFlag |= 8;
        } else {
            this.mFlag &= -9;
        }
    }

    public void setPtrHandler(JDReactPullToRefreshHandler jDReactPullToRefreshHandler) {
        this.mPtrHandler = jDReactPullToRefreshHandler;
    }

    public void setPullToRefresh(boolean z) {
        this.mPullToRefresh = z;
    }

    public void setRatioOfHeaderHeightToRefresh(float f2) {
        this.mJDReactPullToRefreshIndicator.setRatioOfHeaderHeightToRefresh(f2);
    }

    public void setRefreshCompleteHook(JDReactPullToRefreshUIHandlerHook jDReactPullToRefreshUIHandlerHook) {
        this.mRefreshCompleteHook = jDReactPullToRefreshUIHandlerHook;
        jDReactPullToRefreshUIHandlerHook.setResumeAction(new Runnable() { // from class: com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshBasicFrameLayout.2
            @Override // java.lang.Runnable
            public void run() {
                OKLog.d(JDReactPullToRefreshBasicFrameLayout.this.LOG_TAG, "mRefreshCompleteHook resume.");
                JDReactPullToRefreshBasicFrameLayout.this.notifyUIRefreshComplete(true);
            }
        });
    }

    public void setResistance(float f2) {
        this.mJDReactPullToRefreshIndicator.setResistance(f2);
    }

    public JDReactPullToRefreshBasicFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void autoRefresh(boolean z) {
        autoRefresh(z, this.mDurationToCloseHeader);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public JDReactPullToRefreshBasicFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mStatus = (byte) 1;
        StringBuilder sb = new StringBuilder();
        sb.append("ptr-frame-");
        int i3 = ID + 1;
        ID = i3;
        sb.append(i3);
        this.LOG_TAG = sb.toString();
        this.mHeaderId = 0;
        this.mContainerId = 0;
        this.mDurationToClose = 200;
        this.mDurationToCloseHeader = 1000;
        this.mKeepHeaderWhenRefresh = true;
        this.mPullToRefresh = false;
        this.mPtrUIHandlerHolder = JDReactPullToRefreshUIHandlerHolder.create();
        this.mDisableWhenHorizontalMove = false;
        this.mFlag = 0;
        this.mPreventForHorizontal = false;
        this.mLoadingMinTime = 500;
        this.mLoadingStartTime = 0L;
        this.mHasSendCancelEvent = false;
        this.mPerformRefreshCompleteDelay = new Runnable() { // from class: com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshBasicFrameLayout.1
            @Override // java.lang.Runnable
            public void run() {
                JDReactPullToRefreshBasicFrameLayout.this.performRefreshComplete();
            }
        };
        this.mJDReactPullToRefreshIndicator = new JDReactPullToRefreshIndicator();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.JDReactPullToRefreshFrameLayout, 0, 0);
        if (obtainStyledAttributes != null) {
            this.mHeaderId = obtainStyledAttributes.getResourceId(0, this.mHeaderId);
            this.mContainerId = obtainStyledAttributes.getResourceId(1, this.mContainerId);
            JDReactPullToRefreshIndicator jDReactPullToRefreshIndicator = this.mJDReactPullToRefreshIndicator;
            jDReactPullToRefreshIndicator.setResistance(obtainStyledAttributes.getFloat(2, jDReactPullToRefreshIndicator.getResistance()));
            this.mDurationToClose = obtainStyledAttributes.getInt(4, this.mDurationToClose);
            this.mDurationToCloseHeader = obtainStyledAttributes.getInt(5, this.mDurationToCloseHeader);
            this.mJDReactPullToRefreshIndicator.setRatioOfHeaderHeightToRefresh(obtainStyledAttributes.getFloat(3, this.mJDReactPullToRefreshIndicator.getRatioOfHeaderToHeightRefresh()));
            this.mKeepHeaderWhenRefresh = obtainStyledAttributes.getBoolean(7, this.mKeepHeaderWhenRefresh);
            this.mPullToRefresh = obtainStyledAttributes.getBoolean(6, this.mPullToRefresh);
            obtainStyledAttributes.recycle();
        }
        this.mScrollChecker = new ScrollChecker();
        this.mPagingTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop() * 2;
    }

    public void autoRefresh(boolean z, int i2) {
        if (this.mStatus != 1) {
            return;
        }
        this.mFlag |= z ? 1 : 2;
        this.mStatus = (byte) 2;
        if (this.mPtrUIHandlerHolder.hasHandler()) {
            this.mPtrUIHandlerHolder.onUIRefreshPrepare(this);
            OKLog.i(this.LOG_TAG, String.format("JDReactPullToRefreshUIHandler: onUIRefreshPrepare, mFlag %s", Integer.valueOf(this.mFlag)));
        }
        this.mScrollChecker.tryToScrollTo(this.mJDReactPullToRefreshIndicator.getOffsetToRefresh(), i2);
        if (z) {
            this.mStatus = (byte) 3;
            performRefresh();
        }
    }
}
