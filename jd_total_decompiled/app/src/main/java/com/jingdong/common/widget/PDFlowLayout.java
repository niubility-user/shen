package com.jingdong.common.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;
import com.jingdong.common.DpiUtil;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;

/* loaded from: classes12.dex */
public class PDFlowLayout extends ViewGroup {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "PDFlowLayout";
    private Activity activity;
    private int curRowNum;
    private boolean isIntercept;
    private boolean isOverFlowRow;
    private boolean mCanOverRowSize;
    private int mFinalX;
    private int mHeight;
    private int mLastX;
    private int mLastY;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mRowMaxLength;
    private Scroller mScroller;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private int maxRowCount;
    private int maxRows;
    private onScrollChanged onScrollChanged;
    private int padH;
    private int padV;
    private int padding;
    private ArrayList<Integer> rowHeight;
    private RowNumListener rowNumListener;

    /* loaded from: classes12.dex */
    public interface RowNumListener {
        void overflow(int i2);
    }

    /* loaded from: classes12.dex */
    public interface onScrollChanged {
        void onScrollChanged(int i2);
    }

    public PDFlowLayout(Context context) {
        super(context);
        this.padding = 0;
        this.maxRows = -1;
        this.curRowNum = 0;
        this.rowHeight = new ArrayList<>();
    }

    private void stopFling() {
        Scroller scroller = this.mScroller;
        if (scroller == null || scroller.isFinished()) {
            return;
        }
        this.mScroller.abortAnimation();
    }

    @Override // android.view.View
    public void computeScroll() {
        Scroller scroller = this.mScroller;
        if (scroller != null && scroller.computeScrollOffset()) {
            scrollTo(this.mScroller.getCurrX(), this.mScroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    public int getCurRowNum() {
        return this.curRowNum;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mCanOverRowSize && this.maxRowCount != 0 && this.mRowMaxLength != 0) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.isIntercept = false;
                this.mLastX = (int) motionEvent.getX();
                this.mLastY = (int) motionEvent.getY();
            } else if (action == 2) {
                float x = this.mLastX - motionEvent.getX();
                float y = this.mLastY - motionEvent.getY();
                if (!this.isIntercept && (Math.abs(x) <= Math.abs(y) || Math.abs(x) - Math.abs(y) <= 20.0f)) {
                    this.isIntercept = false;
                    if (getParent() != null) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                } else {
                    this.isIntercept = true;
                    if (getParent() != null) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
            }
            return this.isIntercept;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        RowNumListener rowNumListener;
        int i7;
        int i8;
        int i9;
        int abs;
        this.rowHeight.clear();
        int i10 = i4 - i2;
        int paddingLeft = (i10 - getPaddingLeft()) - getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        while (true) {
            i6 = 8;
            if (i11 >= childCount) {
                break;
            }
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (i13 + measuredWidth > paddingLeft) {
                    this.rowHeight.add(Integer.valueOf(i12));
                    i12 = measuredHeight;
                    i13 = 0;
                } else {
                    i12 = Math.max(measuredHeight, i12);
                }
                i13 += measuredWidth + this.padH;
            }
            i11++;
        }
        this.rowHeight.add(Integer.valueOf(i12));
        int paddingLeft2 = getPaddingLeft();
        this.isOverFlowRow = false;
        this.mRowMaxLength = 0;
        View view = null;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (i14 < childCount) {
            View childAt2 = getChildAt(i14);
            if (childAt2.getVisibility() != i6) {
                if (childAt2.isSelected()) {
                    view = childAt2;
                }
                int measuredWidth2 = childAt2.getMeasuredWidth();
                int measuredHeight2 = childAt2.getMeasuredHeight();
                if (paddingLeft2 + measuredWidth2 > i10 - getPaddingRight()) {
                    if (this.mCanOverRowSize) {
                        int i17 = i14 + 1;
                        if (i17 - i15 > this.maxRowCount) {
                            i15 += i17;
                            try {
                                i9 = this.rowHeight.get(i16).intValue();
                            } catch (Exception unused) {
                                i9 = measuredHeight2;
                            }
                            paddingTop += i9 + this.padV;
                            i16++;
                            int i18 = this.maxRows;
                            if (i16 > i18) {
                                i16 = i18;
                            }
                            Activity activity = this.activity;
                            if (activity != null) {
                                int appWidth = (DPIUtil.getAppWidth(activity) - this.padding) - (i2 * 2);
                                if (paddingLeft2 > appWidth) {
                                    abs = Math.abs(paddingLeft2 - appWidth);
                                    this.mRowMaxLength = Math.max(abs, this.mRowMaxLength);
                                    paddingLeft2 = getPaddingLeft();
                                }
                                abs = 0;
                                this.mRowMaxLength = Math.max(abs, this.mRowMaxLength);
                                paddingLeft2 = getPaddingLeft();
                            } else {
                                Context context = getContext();
                                if (context instanceof Activity) {
                                    int appWidth2 = (DPIUtil.getAppWidth((Activity) getContext()) - this.padding) - (i2 * 2);
                                    if (paddingLeft2 > appWidth2) {
                                        abs = Math.abs(paddingLeft2 - appWidth2);
                                        this.mRowMaxLength = Math.max(abs, this.mRowMaxLength);
                                        paddingLeft2 = getPaddingLeft();
                                    }
                                    abs = 0;
                                    this.mRowMaxLength = Math.max(abs, this.mRowMaxLength);
                                    paddingLeft2 = getPaddingLeft();
                                } else {
                                    int width = (DPIUtil.getWidth(context) - this.padding) - (i2 * 2);
                                    if (paddingLeft2 > width) {
                                        abs = Math.abs(paddingLeft2 - width);
                                        this.mRowMaxLength = Math.max(abs, this.mRowMaxLength);
                                        paddingLeft2 = getPaddingLeft();
                                    }
                                    abs = 0;
                                    this.mRowMaxLength = Math.max(abs, this.mRowMaxLength);
                                    paddingLeft2 = getPaddingLeft();
                                }
                            }
                        } else {
                            this.isOverFlowRow = true;
                        }
                    } else {
                        try {
                            i8 = this.rowHeight.get(i16).intValue();
                        } catch (Exception unused2) {
                            i8 = measuredHeight2;
                        }
                        paddingTop += i8 + this.padV;
                        i16++;
                        paddingLeft2 = getPaddingLeft();
                    }
                }
                int i19 = this.maxRows;
                if (i19 != -1 && i16 >= i19) {
                    return;
                }
                try {
                    i7 = this.rowHeight.get(i16).intValue() - measuredHeight2;
                } catch (Exception unused3) {
                    i7 = 0;
                }
                childAt2.layout(paddingLeft2, paddingTop + i7, paddingLeft2 + measuredWidth2, measuredHeight2 + paddingTop + i7);
                paddingLeft2 += measuredWidth2 + this.padH;
            }
            i14++;
            i6 = 8;
        }
        if (view == null) {
            scrollTo(0, 0);
        } else {
            int appWidth3 = (DPIUtil.getAppWidth(this.activity) - this.padding) - (i2 * 2);
            int right = view.getRight();
            int i20 = this.mFinalX;
            if (i20 == 0) {
                scrollTo(right - appWidth3, 0);
            } else if (right - i20 > appWidth3) {
                scrollTo(right - appWidth3, 0);
            } else if (this.isOverFlowRow) {
                scrollTo(i20, 0);
            } else if (right < appWidth3) {
                scrollTo(0, 0);
            } else {
                scrollTo(right - appWidth3, 0);
            }
        }
        if (i16 < this.maxRows || (rowNumListener = this.rowNumListener) == null) {
            return;
        }
        rowNumListener.overflow(i16);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a6, code lost:
        if ((r4 + r13) < r1) goto L24;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onMeasure(int i2, int i3) {
        int i4;
        int size = (View.MeasureSpec.getSize(i2) - getPaddingLeft()) - getPaddingRight();
        int size2 = (View.MeasureSpec.getSize(i3) - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.mHeight = 0;
        this.curRowNum = 0;
        this.maxRowCount = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i2, i3);
                int measuredWidth = childAt.getMeasuredWidth();
                if (paddingLeft + measuredWidth > getPaddingLeft() + size) {
                    if (this.mCanOverRowSize && this.maxRowCount == 0) {
                        this.maxRowCount = (childCount % 2) + (childCount / 2);
                    }
                    int i6 = this.curRowNum + 1;
                    this.curRowNum = i6;
                    int i7 = this.maxRows;
                    if (i7 == -1 || (i7 != -1 && i6 < i7)) {
                        paddingLeft = getPaddingLeft();
                        paddingTop += this.mHeight + this.padV;
                        this.mHeight = childAt.getMeasuredHeight();
                    }
                } else {
                    this.mHeight = Math.max(this.mHeight, childAt.getMeasuredHeight());
                }
                paddingLeft += measuredWidth + this.padH;
            }
        }
        if (View.MeasureSpec.getMode(i3) == 0) {
            i4 = this.mHeight;
        } else {
            if (View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE) {
                i4 = this.mHeight;
            }
            setMeasuredDimension(View.MeasureSpec.getSize(i2), size2 + getPaddingBottom());
        }
        size2 = paddingTop + i4;
        setMeasuredDimension(View.MeasureSpec.getSize(i2), size2 + getPaddingBottom());
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        onScrollChanged onscrollchanged = this.onScrollChanged;
        if (onscrollchanged != null) {
            onscrollchanged.onScrollChanged(i2);
        }
        super.onScrollChanged(i2, i3, i4, i5);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mCanOverRowSize && this.maxRowCount != 0 && this.mRowMaxLength != 0) {
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            this.mVelocityTracker.addMovement(motionEvent);
            int action = motionEvent.getAction();
            if (action == 0) {
                this.isIntercept = false;
                stopFling();
            } else if (action == 1) {
                this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
                int xVelocity = (int) this.mVelocityTracker.getXVelocity();
                if (Math.abs(xVelocity) > this.mMinimumVelocity) {
                    this.mScroller.fling(getScrollX(), 0, -xVelocity, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
                }
                VelocityTracker velocityTracker = this.mVelocityTracker;
                if (velocityTracker != null) {
                    velocityTracker.recycle();
                    this.mVelocityTracker = null;
                }
            } else if (action == 2) {
                int i2 = this.mLastX;
                int i3 = x - i2;
                if (Math.abs(i2 - motionEvent.getX()) <= Math.abs(this.mLastY - motionEvent.getY())) {
                    return false;
                }
                scrollBy(-i3, 0);
            }
            this.mLastX = x;
            this.mLastY = y;
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        if (i2 < 0) {
            i2 = 0;
            stopFling();
        } else {
            int i4 = this.mRowMaxLength;
            if (i4 > 0 && i2 > i4) {
                stopFling();
                i2 = i4;
            }
        }
        this.mFinalX = i2;
        onScrollChanged onscrollchanged = this.onScrollChanged;
        if (onscrollchanged != null) {
            onscrollchanged.onScrollChanged(i2);
        }
        super.scrollTo(i2, i3);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setCurRowNum(int i2) {
        this.curRowNum = i2;
    }

    public void setFinalX(int i2) {
        this.mFinalX = i2;
    }

    public void setMaxRows(int i2) {
        this.maxRows = i2;
    }

    public void setPadW(int i2) {
        this.padding = i2;
    }

    public void setRowNumListener(RowNumListener rowNumListener) {
        this.rowNumListener = rowNumListener;
    }

    public void setSpace(int i2, int i3) {
        this.padH = i2;
        this.padV = i3;
    }

    public void setmCanOverRowSize(boolean z) {
        this.mCanOverRowSize = z;
        this.mScroller = new Scroller(getContext(), new LinearInterpolator());
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public void setonScrollChanged(onScrollChanged onscrollchanged) {
        this.onScrollChanged = onscrollchanged;
    }

    public void smoothScrollBy(int i2, int i3) {
        Scroller scroller = this.mScroller;
        if (scroller != null) {
            scroller.startScroll(0, 0, i2, i3, 1000);
            invalidate();
        }
    }

    public void smoothScrollTo(int i2, int i3) {
        Scroller scroller = this.mScroller;
        if (scroller != null) {
            smoothScrollBy(i2 - scroller.getFinalX(), i3 - this.mScroller.getFinalY());
        }
    }

    public PDFlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.padding = 0;
        this.maxRows = -1;
        this.curRowNum = 0;
        this.rowHeight = new ArrayList<>();
        this.padH = DpiUtil.dip2px(context, 15.0f);
        this.padV = DpiUtil.dip2px(context, 15.0f);
    }
}
