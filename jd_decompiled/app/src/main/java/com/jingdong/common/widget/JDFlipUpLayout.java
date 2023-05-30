package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            boolean r0 = r8.canPullUp
            if (r0 != 0) goto L9
            boolean r9 = super.onInterceptTouchEvent(r9)
            return r9
        L9:
            int r0 = r9.getAction()
            r1 = 2
            r2 = 1
            if (r0 != r1) goto L16
            int r3 = r8.mTouchState
            if (r3 == 0) goto L16
            return r2
        L16:
            float r3 = r9.getX()
            float r9 = r9.getY()
            boolean r4 = com.jingdong.sdk.oklog.OKLog.D
            java.lang.String r5 = "onInterceptTouchEvent isFlipToBottom() = "
            java.lang.String r6 = "JDFlipUpLayout"
            if (r4 == 0) goto L50
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = "onInterceptTouchEvent x = "
            r4.append(r7)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            com.jingdong.sdk.oklog.OKLog.d(r6, r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r5)
            boolean r7 = r8.isFlipToBottom()
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            com.jingdong.sdk.oklog.OKLog.d(r6, r4)
        L50:
            r4 = 0
            if (r0 == 0) goto Ld1
            if (r0 == r2) goto Lce
            if (r0 == r1) goto L5c
            r9 = 3
            if (r0 == r9) goto Lce
            goto Lde
        L5c:
            float r0 = r8.mLastMotionY
            float r9 = r9 - r0
            int r9 = (int) r9
            float r0 = r8.mLastMotionX
            float r3 = r3 - r0
            int r0 = (int) r3
            int r0 = java.lang.Math.abs(r0)
            int r1 = com.jingdong.common.widget.JDFlipUpLayout.mTouchSlop
            int r3 = java.lang.Math.abs(r9)
            if (r3 <= r1) goto L72
            r1 = 1
            goto L73
        L72:
            r1 = 0
        L73:
            boolean r3 = com.jingdong.sdk.oklog.OKLog.D
            if (r3 == 0) goto Lb9
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r7 = "onInterceptTouchEvent yDiff = "
            r3.append(r7)
            int r7 = java.lang.Math.abs(r9)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            com.jingdong.sdk.oklog.OKLog.d(r6, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r7 = "onInterceptTouchEvent xDiff = "
            r3.append(r7)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            com.jingdong.sdk.oklog.OKLog.d(r6, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r5)
            boolean r5 = r8.isFlipToBottom()
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            com.jingdong.sdk.oklog.OKLog.d(r6, r3)
        Lb9:
            if (r1 == 0) goto Lde
            int r1 = java.lang.Math.abs(r9)
            int r0 = r0 * 5
            if (r1 <= r0) goto Lde
            if (r9 >= 0) goto Lde
            boolean r9 = r8.isFlipToBottom()
            if (r9 == 0) goto Lde
            r8.mTouchState = r2
            goto Lde
        Lce:
            r8.mTouchState = r4
            goto Lde
        Ld1:
            r8.mLastMotionY = r9
            r8.mLastMotionX = r3
            android.widget.Scroller r9 = r8.mScroller
            boolean r9 = r9.isFinished()
            r9 = r9 ^ r2
            r8.mTouchState = r9
        Lde:
            int r9 = r8.mTouchState
            if (r9 == 0) goto Le3
            goto Le4
        Le3:
            r2 = 0
        Le4:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.widget.JDFlipUpLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.canPullUp
            if (r0 != 0) goto L9
            boolean r5 = super.onTouchEvent(r5)
            return r5
        L9:
            int r0 = r5.getAction()
            float r5 = r5.getY()
            r1 = 1
            if (r0 == 0) goto L4a
            r2 = 0
            if (r0 == r1) goto L40
            r3 = 2
            if (r0 == r3) goto L1e
            r5 = 3
            if (r0 == r5) goto L40
            goto L57
        L1e:
            int r0 = r4.mTouchState
            if (r0 != r1) goto L57
            boolean r0 = r4.isAddFinish
            if (r0 != 0) goto L57
            float r0 = r4.mLastMotionY
            float r0 = r0 - r5
            int r0 = (int) r0
            r4.mLastMotionY = r5
            int r5 = r4.getScrollY()
            boolean r3 = r4.isFlipToBottom()
            if (r3 == 0) goto L57
            int r5 = r5 * (-1)
            int r5 = java.lang.Math.max(r5, r0)
            r4.scrollBy(r2, r5)
            goto L57
        L40:
            int r5 = r4.mTouchState
            if (r5 != r1) goto L47
            r4.snapBack()
        L47:
            r4.mTouchState = r2
            goto L57
        L4a:
            android.widget.Scroller r5 = r4.mScroller
            boolean r5 = r5.isFinished()
            if (r5 != 0) goto L57
            android.widget.Scroller r5 = r4.mScroller
            r5.abortAnimation()
        L57:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.widget.JDFlipUpLayout.onTouchEvent(android.view.MotionEvent):boolean");
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
