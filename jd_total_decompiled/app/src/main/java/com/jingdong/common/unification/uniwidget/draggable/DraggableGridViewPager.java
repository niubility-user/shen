package com.jingdong.common.unification.uniwidget.draggable;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Scroller;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.VelocityTrackerCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class DraggableGridViewPager extends ViewGroup {
    private static final long ANIMATION_DURATION = 150;
    private static final int CLOSE_ENOUGH = 2;
    private static final int DEFAULT_COL_COUNT = 4;
    private static final int DEFAULT_GRID_GAP = 8;
    private static final int DEFAULT_ROW_COUNT = 4;
    private static final long EDGE_HOLD_DURATION = 1200;
    private static final int EDGE_LFET = 0;
    private static final int EDGE_RIGHT = 1;
    private static final int INVALID_POINTER = -1;
    private static final long LONG_CLICK_DURATION = 300;
    private static final int MAX_SETTLE_DURATION = 300;
    private static final int MIN_DISTANCE_FOR_FLING = 0;
    private static final int MIN_FLING_VELOCITY = 200;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "DraggableGridViewPager";
    private static final boolean USE_CACHE = false;
    private static final Interpolator sInterpolator = new Interpolator() { // from class: com.jingdong.common.unification.uniwidget.draggable.DraggableGridViewPager.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    };
    private boolean isFirstMove;
    private boolean isLastMove;
    private int isLastNotMoveSize;
    private int mActivePointerId;
    private Adapter mAdapter;
    private boolean mCalledSuper;
    private int mCloseEnough;
    private int mColCount;
    private int mCurItem;
    private final DataSetObserver mDataSetObserver;
    private int mEdgeSize;
    private final Runnable mEndScrollRunnable;
    private int mFlingDistance;
    private int mGridGap;
    private int mGridHeight;
    private int mGridWidth;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    private boolean mIsUnableToDrag;
    private long mLastDownTime;
    private int mLastDragged;
    private int mLastEdge;
    private long mLastEdgeTime;
    private float mLastMotionX;
    private float mLastMotionY;
    private int mLastPosition;
    private int mLastTarget;
    private int mMaxOverScrollSize;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    private AdapterView.OnItemLongClickListener mOnItemLongClickListener;
    private OnPageChangeListener mOnPageChangeListener;
    private OnRearrangeListener mOnRearrangeListener;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private int mPageCount;
    private int mPageSize;
    private int mRowCount;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private ArrayList<Integer> newPositions;

    /* loaded from: classes6.dex */
    public interface OnPageChangeListener {
        void onPageScrollStateChanged(int i2);

        void onPageScrolled(int i2, float f2, int i3);

        void onPageSelected(int i2);
    }

    /* loaded from: classes6.dex */
    public interface OnRearrangeListener {
        void onRearrange(int i2, int i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.jingdong.common.unification.uniwidget.draggable.DraggableGridViewPager.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        int curItem;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.curItem);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.curItem = parcel.readInt();
        }
    }

    /* loaded from: classes6.dex */
    public static class SimpleOnPageChangeListener implements OnPageChangeListener {
        @Override // com.jingdong.common.unification.uniwidget.draggable.DraggableGridViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
        }

        @Override // com.jingdong.common.unification.uniwidget.draggable.DraggableGridViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
        }

        @Override // com.jingdong.common.unification.uniwidget.draggable.DraggableGridViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
        }
    }

    public DraggableGridViewPager(Context context) {
        super(context);
        this.mColCount = 4;
        this.mRowCount = 4;
        this.mPageSize = 4 * 4;
        this.mDataSetObserver = new DataSetObserver() { // from class: com.jingdong.common.unification.uniwidget.draggable.DraggableGridViewPager.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                DraggableGridViewPager.this.dataSetChanged();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                DraggableGridViewPager.this.dataSetChanged();
            }
        };
        this.mActivePointerId = -1;
        this.mLastPosition = -1;
        this.mLastDownTime = Long.MAX_VALUE;
        this.mLastDragged = -1;
        this.mLastTarget = -1;
        this.mLastEdge = -1;
        this.mLastEdgeTime = Long.MAX_VALUE;
        this.newPositions = new ArrayList<>();
        this.mEndScrollRunnable = new Runnable() { // from class: com.jingdong.common.unification.uniwidget.draggable.DraggableGridViewPager.3
            @Override // java.lang.Runnable
            public void run() {
                DraggableGridViewPager.this.setScrollState(0);
            }
        };
        this.mScrollState = 0;
        this.isLastMove = true;
        this.isFirstMove = true;
        this.isLastNotMoveSize = 1;
        initDraggableGridViewPager();
    }

    private void animateDragged() {
        int i2 = this.mLastDragged;
        if (i2 >= 0) {
            View childAt = getChildAt(i2);
            Rect rect = new Rect(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
            rect.inset((-rect.width()) / 20, (-rect.height()) / 20);
            childAt.measure(View.MeasureSpec.makeMeasureSpec(rect.width(), 1073741824), View.MeasureSpec.makeMeasureSpec(rect.height(), 1073741824));
            childAt.layout(rect.left, rect.top, rect.right, rect.bottom);
            AnimationSet animationSet = new AnimationSet(true);
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.9091f, 1.0f, 0.9091f, 1.0f, childAt.getWidth() / 2.0f, childAt.getHeight() / 2.0f);
            scaleAnimation.setDuration(150L);
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.5f);
            alphaAnimation.setDuration(150L);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(alphaAnimation);
            animationSet.setFillEnabled(true);
            animationSet.setFillAfter(true);
            childAt.clearAnimation();
            childAt.startAnimation(animationSet);
        }
    }

    private void animateGap(int i2) {
        int i3 = 0;
        while (i3 < getChildCount()) {
            View childAt = getChildAt(i3);
            int i4 = this.mLastDragged;
            if (i3 != i4) {
                int i5 = (i4 >= i2 || i3 < i4 + 1 || i3 > i2) ? (i2 >= i4 || i3 < i2 || i3 >= i4) ? i3 : i3 + 1 : i3 - 1;
                int intValue = this.newPositions.get(i3).intValue() != -1 ? this.newPositions.get(i3).intValue() : i3;
                if (intValue == i5) {
                    continue;
                } else if (!this.isFirstMove && intValue == 0) {
                    return;
                } else {
                    if (!this.isLastMove && intValue >= getChildCount() - this.isLastNotMoveSize) {
                        return;
                    }
                    Rect rectByPosition = getRectByPosition(intValue);
                    Rect rectByPosition2 = getRectByPosition(i5);
                    rectByPosition.offset(-childAt.getLeft(), -childAt.getTop());
                    rectByPosition2.offset(-childAt.getLeft(), -childAt.getTop());
                    TranslateAnimation translateAnimation = new TranslateAnimation(rectByPosition.left, rectByPosition2.left, rectByPosition.top, rectByPosition2.top);
                    translateAnimation.setDuration(150L);
                    translateAnimation.setFillEnabled(true);
                    translateAnimation.setFillAfter(true);
                    childAt.clearAnimation();
                    childAt.startAnimation(translateAnimation);
                    this.newPositions.set(i3, Integer.valueOf(i5));
                }
            }
            i3++;
        }
    }

    private void completeScroll(boolean z) {
        if (this.mScrollState == 2) {
            setScrollingCacheEnabled(false);
            this.mScroller.abortAnimation();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            if (scrollX != currX || scrollY != currY) {
                scrollTo(currX, currY);
            }
            if (z) {
                ViewCompat.postOnAnimation(this, this.mEndScrollRunnable);
            } else {
                this.mEndScrollRunnable.run();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dataSetChanged() {
        for (int i2 = 0; i2 < getChildCount() && i2 < this.mAdapter.getCount(); i2++) {
            View childAt = getChildAt(i2);
            View view = this.mAdapter.getView(i2, childAt, this);
            if (view != childAt) {
                removeViewAt(i2);
                addView(view, i2);
            }
        }
        for (int childCount = getChildCount(); childCount < this.mAdapter.getCount(); childCount++) {
            addView(this.mAdapter.getView(childCount, null, this));
        }
        while (getChildCount() > this.mAdapter.getCount()) {
            removeViewAt(getChildCount() - 1);
        }
    }

    private int determineTargetPage(int i2, float f2, int i3, int i4) {
        if (Math.abs(i4) <= this.mFlingDistance || Math.abs(i3) <= this.mMinimumVelocity) {
            return (int) (i2 + f2 + (i2 >= this.mCurItem ? 0.4f : 0.6f));
        }
        return i3 > 0 ? i2 : i2 + 1;
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private int getEdgeByXY(int i2, int i3) {
        if (i2 < this.mEdgeSize) {
            return 0;
        }
        return i2 >= getWidth() - this.mEdgeSize ? 1 : -1;
    }

    private int getPositionByXY(int i2, int i3) {
        int i4;
        int i5;
        int i6 = this.mPaddingLeft;
        int i7 = this.mGridWidth;
        int i8 = this.mGridGap;
        int i9 = (i2 - i6) / (i7 + i8);
        int i10 = this.mPaddingTop;
        int i11 = this.mGridHeight;
        int i12 = (i3 - i10) / (i11 + i8);
        if (i2 < i6 || i2 >= i6 + ((i7 + i8) * i9) + i7 || i3 < i10 || i3 >= i10 + ((i8 + i11) * i12) + i11 || i9 < 0 || i9 >= (i4 = this.mColCount) || i12 < 0 || i12 >= this.mRowCount || (i5 = (this.mCurItem * this.mPageSize) + (i12 * i4) + i9) < 0 || i5 >= getChildCount()) {
            return -1;
        }
        return i5;
    }

    private Rect getRectByPosition(int i2) {
        int i3 = this.mPageSize;
        int i4 = i2 / i3;
        int i5 = this.mColCount;
        int i6 = (i2 % i3) % i5;
        int i7 = (i2 % i3) / i5;
        int width = (getWidth() * i4) + this.mPaddingLeft;
        int i8 = this.mGridWidth;
        int i9 = this.mGridGap;
        int i10 = width + (i6 * (i8 + i9));
        int i11 = this.mPaddingTop + (i7 * (this.mGridHeight + i9));
        return new Rect(i10, i11, this.mGridWidth + i10, this.mGridHeight + i11);
    }

    private int getTargetByXY(int i2, int i3) {
        int positionByXY = getPositionByXY(i2, i3);
        if (positionByXY < 0) {
            return -1;
        }
        Rect rectByPosition = getRectByPosition(positionByXY);
        rectByPosition.inset(rectByPosition.width() / 4, rectByPosition.height() / 4);
        rectByPosition.offset((-getWidth()) * (positionByXY / this.mPageSize), 0);
        if (rectByPosition.contains(i2, i3)) {
            return positionByXY;
        }
        return -1;
    }

    private void initDraggableGridViewPager() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        setChildrenDrawingOrderEnabled(true);
        Context context = getContext();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float density = BaseInfo.getDensity();
        this.mGridGap = (int) (8.0f * density);
        this.mPaddingLeft = getPaddingLeft();
        this.mPaddingTop = getPaddingTop();
        this.mPaddingRight = getPaddingRight();
        this.mScroller = new Scroller(context, sInterpolator);
        this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.mMinimumVelocity = (int) (200.0f * density);
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mFlingDistance = (int) (0.0f * density);
        this.mCloseEnough = (int) (density * 2.0f);
    }

    private void onItemClick(int i2) {
        AdapterView.OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(null, getChildAt(i2), i2, i2 / this.mColCount);
        }
    }

    private boolean onItemLongClick(int i2) {
        AdapterView.OnItemLongClickListener onItemLongClickListener = this.mOnItemLongClickListener;
        if (onItemLongClickListener != null) {
            return onItemLongClickListener.onItemLongClick(null, getChildAt(i2), i2, i2 / this.mColCount);
        }
        return false;
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mActivePointerId) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.mLastMotionX = MotionEventCompat.getX(motionEvent, i2);
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, i2);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private boolean pageScrolled(int i2) {
        if (this.mPageCount <= 0) {
            this.mCalledSuper = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.mCalledSuper) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        int width = getWidth();
        int i3 = i2 / width;
        int i4 = i2 - (i3 * width);
        this.mCalledSuper = false;
        onPageScrolled(i3, i4 / width, i4);
        if (this.mCalledSuper) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private boolean performDrag(float f2) {
        float f3 = this.mLastMotionX - f2;
        this.mLastMotionX = f2;
        float scrollX = getScrollX() + f3;
        int width = getWidth();
        float f4 = width * 0;
        float f5 = width * (this.mPageCount - 1);
        if (scrollX < f4) {
            scrollX = f4 - Math.min(f4 - scrollX, this.mMaxOverScrollSize);
        } else if (scrollX > f5) {
            scrollX = Math.min(scrollX - f5, this.mMaxOverScrollSize) + f5;
        }
        OKLog.d("pageSize", this.mPageSize + LangUtils.SINGLE_SPACE + this.mEdgeSize + LangUtils.SINGLE_SPACE + this.mPageCount);
        if (this.mPageCount == 1) {
            scrollX = 0.0f;
        }
        int i2 = (int) scrollX;
        this.mLastMotionX += scrollX - i2;
        scrollTo(i2, getScrollY());
        pageScrolled(i2);
        return false;
    }

    private void rearrange() {
        OKLog.d(TAG, "rearrange lastDragged " + this.mLastDragged + "  lastTarget " + this.mLastTarget);
        if (this.mLastDragged >= 0) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                getChildAt(i2).clearAnimation();
            }
            int i3 = this.mLastTarget;
            if (i3 >= 0 && this.mLastDragged != i3 && ((this.isLastMove || i3 < getChildCount() - this.isLastNotMoveSize) && (this.isFirstMove || this.mLastTarget != 0))) {
                View childAt = getChildAt(this.mLastDragged);
                removeViewAt(this.mLastDragged);
                addView(childAt, this.mLastTarget);
                OnRearrangeListener onRearrangeListener = this.mOnRearrangeListener;
                if (onRearrangeListener != null) {
                    onRearrangeListener.onRearrange(this.mLastDragged, this.mLastTarget);
                }
            }
            this.mLastDragged = -1;
            this.mLastTarget = -1;
            requestLayout();
            invalidate();
        }
    }

    private void requestParentDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private void scrollToItem(int i2, boolean z, int i3, boolean z2) {
        OnPageChangeListener onPageChangeListener;
        OnPageChangeListener onPageChangeListener2;
        OKLog.d(TAG, "scrollToItem  " + i2 + LangUtils.SINGLE_SPACE + z + LangUtils.SINGLE_SPACE + z2);
        int width = getWidth() * i2;
        if (z) {
            smoothScrollTo(width, 0, i3);
            if (!z2 || (onPageChangeListener2 = this.mOnPageChangeListener) == null) {
                return;
            }
            onPageChangeListener2.onPageSelected(i2);
            return;
        }
        if (z2 && (onPageChangeListener = this.mOnPageChangeListener) != null) {
            onPageChangeListener.onPageSelected(i2);
        }
        completeScroll(false);
        scrollTo(width, 0);
        pageScrolled(width);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScrollState(int i2) {
        if (this.mScrollState == i2) {
            return;
        }
        this.mScrollState = i2;
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i2);
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.mScrollingCacheEnabled != z) {
            this.mScrollingCacheEnabled = z;
        }
    }

    private void triggerSwipe(int i2) {
        int i3;
        int i4;
        if (i2 == 0 && (i4 = this.mCurItem) > 0) {
            setCurrentItem(i4 - 1, true);
        } else if (i2 != 1 || (i3 = this.mCurItem) >= this.mPageCount - 1) {
        } else {
            setCurrentItem(i3 + 1, true);
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            if (scrollX != currX || scrollY != currY) {
                scrollTo(currX, currY);
                if (!pageScrolled(currX)) {
                    this.mScroller.abortAnimation();
                    scrollTo(0, currY);
                }
            }
            ViewCompat.postInvalidateOnAnimation(this);
            return;
        }
        completeScroll(true);
    }

    float distanceInfluenceForSnapDuration(float f2) {
        Double.isNaN(f2 - 0.5f);
        return (float) Math.sin((float) (r0 * 0.4712389167638204d));
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        int i4 = this.mLastDragged;
        return i4 == -1 ? i3 : i3 == i2 + (-1) ? i4 : i3 >= i4 ? i3 + 1 : i3;
    }

    public int getColCount() {
        return this.mColCount;
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    public int getGridGap() {
        return this.mGridGap;
    }

    public int getPageCount() {
        int childCount = getChildCount();
        return ((childCount + r1) - 1) / this.mPageSize;
    }

    public int getRowCount() {
        return this.mRowCount;
    }

    public void isFirstMove(boolean z) {
        this.isFirstMove = z;
    }

    public void isLastMove(boolean z) {
        this.isLastMove = z;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        Adapter adapter = this.mAdapter;
        if (adapter != null) {
            adapter.unregisterDataSetObserver(this.mDataSetObserver);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float f2;
        int action = motionEvent.getAction() & 255;
        if (action != 3 && action != 1) {
            if (action != 0) {
                if (this.mIsBeingDragged || this.mLastDragged >= 0) {
                    return true;
                }
                if (this.mIsUnableToDrag) {
                    return false;
                }
            }
            if (action == 0) {
                float x = motionEvent.getX();
                this.mInitialMotionX = x;
                this.mLastMotionX = x;
                float y = motionEvent.getY();
                this.mInitialMotionY = y;
                this.mLastMotionY = y;
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                this.mIsUnableToDrag = false;
                this.mScroller.computeScrollOffset();
                if (this.mScrollState == 2 && Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.mCloseEnough) {
                    this.mScroller.abortAnimation();
                    this.mIsBeingDragged = true;
                    requestParentDisallowInterceptTouchEvent(true);
                    setScrollState(1);
                } else {
                    completeScroll(false);
                    this.mIsBeingDragged = false;
                }
                this.mLastDragged = -1;
            } else if (action == 2) {
                int i2 = this.mActivePointerId;
                if (i2 != -1) {
                    int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i2);
                    float x2 = MotionEventCompat.getX(motionEvent, findPointerIndex);
                    float f3 = x2 - this.mLastMotionX;
                    float abs = Math.abs(f3);
                    float y2 = MotionEventCompat.getY(motionEvent, findPointerIndex);
                    float abs2 = Math.abs(y2 - this.mInitialMotionY);
                    int i3 = this.mTouchSlop;
                    if (abs > i3 && abs * 0.5f > abs2) {
                        this.mIsBeingDragged = true;
                        requestParentDisallowInterceptTouchEvent(true);
                        setScrollState(1);
                        if (f3 > 0.0f) {
                            f2 = this.mInitialMotionX + this.mTouchSlop;
                        } else {
                            f2 = this.mInitialMotionX - this.mTouchSlop;
                        }
                        this.mLastMotionX = f2;
                        this.mLastMotionY = y2;
                        setScrollingCacheEnabled(true);
                    } else if (abs2 > i3) {
                        this.mIsUnableToDrag = true;
                    }
                    if (this.mIsBeingDragged && performDrag(x2)) {
                        ViewCompat.postInvalidateOnAnimation(this);
                    }
                }
            } else if (action == 6) {
                onSecondaryPointerUp(motionEvent);
            }
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent);
            return this.mIsBeingDragged;
        }
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        this.mActivePointerId = -1;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        this.mPageCount = ((childCount + r5) - 1) / this.mPageSize;
        int width = (getWidth() - this.mPaddingLeft) - this.mPaddingRight;
        int i6 = this.mColCount;
        this.mGridWidth = (width - ((i6 - 1) * this.mGridGap)) / i6;
        int height = getHeight();
        this.mGridHeight = height;
        int min = Math.min(this.mGridWidth, height);
        this.mGridHeight = min;
        this.mGridWidth = min;
        this.mMaxOverScrollSize = min / 2;
        this.mEdgeSize = min / 2;
        this.newPositions.clear();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            Rect rectByPosition = getRectByPosition(i7);
            childAt.measure(View.MeasureSpec.makeMeasureSpec(rectByPosition.width(), 1073741824), View.MeasureSpec.makeMeasureSpec(rectByPosition.height(), 1073741824));
            childAt.layout(rectByPosition.left, rectByPosition.top, rectByPosition.right, rectByPosition.bottom);
            this.newPositions.add(-1);
        }
        int i8 = this.mCurItem;
        if (i8 <= 0 || i8 >= this.mPageCount) {
            return;
        }
        this.mCurItem = 0;
        setCurrentItem(i8);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    protected void onPageScrolled(int i2, float f2, int i3) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i2, f2, i3);
        }
        this.mCalledSuper = true;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mCurItem = savedState.curItem;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.curItem = this.mCurItem;
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int positionByXY;
        boolean z = false;
        if ((motionEvent.getAction() != 0 || motionEvent.getEdgeFlags() == 0) && this.mPageCount > 0) {
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent);
            int action = motionEvent.getAction() & 255;
            if (action == 0) {
                OKLog.d(TAG, "onTouchDown");
                this.mScroller.abortAnimation();
                float x = motionEvent.getX();
                this.mInitialMotionX = x;
                this.mLastMotionX = x;
                float y = motionEvent.getY();
                this.mInitialMotionY = y;
                this.mLastMotionY = y;
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                if (!this.mIsBeingDragged && this.mScrollState == 0) {
                    this.mLastPosition = getPositionByXY((int) this.mLastMotionX, (int) this.mLastMotionY);
                } else {
                    this.mLastPosition = -1;
                }
                if (this.mLastPosition >= 0) {
                    this.mLastDownTime = System.currentTimeMillis();
                } else {
                    this.mLastDownTime = Long.MAX_VALUE;
                }
                this.mLastDragged = -1;
            } else if (action == 1) {
                OKLog.d(TAG, "onTouchUp  " + this.mLastDragged + LangUtils.SINGLE_SPACE + this.mLastPosition);
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                float x2 = MotionEventCompat.getX(motionEvent, findPointerIndex);
                float y2 = MotionEventCompat.getY(motionEvent, findPointerIndex);
                if (this.mLastDragged >= 0) {
                    rearrange();
                } else if (this.mIsBeingDragged) {
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
                    int xVelocity = (int) VelocityTrackerCompat.getXVelocity(velocityTracker, this.mActivePointerId);
                    int width = getWidth();
                    setCurrentItemInternal(determineTargetPage(getScrollX() / width, (r3 - (r4 * width)) / width, xVelocity, (int) (x2 - this.mInitialMotionX)), true, true, xVelocity);
                    this.mActivePointerId = -1;
                    endDrag();
                } else if (this.mLastPosition >= 0 && (positionByXY = getPositionByXY((int) x2, (int) y2)) == this.mLastPosition) {
                    onItemClick(positionByXY);
                }
            } else if (action == 2) {
                OKLog.d(TAG, DYConstants.DY_ON_TOUCH_MOVE);
                if (!this.isLastMove && this.mLastPosition >= getChildCount() - this.isLastNotMoveSize) {
                    return false;
                }
                if (!this.isFirstMove && this.mLastPosition == 0) {
                    return false;
                }
                int findPointerIndex2 = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                float x3 = MotionEventCompat.getX(motionEvent, findPointerIndex2);
                float y3 = MotionEventCompat.getY(motionEvent, findPointerIndex2);
                int i2 = this.mLastDragged;
                if (i2 >= 0) {
                    View childAt = getChildAt(i2);
                    int i3 = (int) x3;
                    int scrollX = (getScrollX() + i3) - (childAt.getWidth() / 2);
                    int i4 = (int) y3;
                    int scrollY = (getScrollY() + i4) - (childAt.getHeight() / 2);
                    childAt.layout(scrollX, scrollY, childAt.getWidth() + scrollX, childAt.getHeight() + scrollY);
                    if (this.mScrollState == 0) {
                        int targetByXY = getTargetByXY(i3, i4);
                        if (targetByXY != -1 && this.mLastTarget != targetByXY) {
                            animateGap(targetByXY);
                            this.mLastTarget = targetByXY;
                        }
                        int edgeByXY = getEdgeByXY(i3, i4);
                        int i5 = this.mLastEdge;
                        if (i5 == -1) {
                            if (edgeByXY != i5) {
                                this.mLastEdge = edgeByXY;
                                this.mLastEdgeTime = System.currentTimeMillis();
                            }
                        } else if (edgeByXY != i5) {
                            this.mLastEdge = -1;
                        } else if (System.currentTimeMillis() - this.mLastEdgeTime >= EDGE_HOLD_DURATION) {
                            performHapticFeedback(0);
                            triggerSwipe(edgeByXY);
                            this.mLastEdge = -1;
                        }
                    }
                } else if (!this.mIsBeingDragged) {
                    float abs = Math.abs(x3 - this.mLastMotionX);
                    float abs2 = Math.abs(y3 - this.mLastMotionY);
                    if (abs > this.mTouchSlop && abs > abs2) {
                        this.mIsBeingDragged = true;
                        requestParentDisallowInterceptTouchEvent(true);
                        float f2 = this.mInitialMotionX;
                        this.mLastMotionX = x3 - f2 > 0.0f ? f2 + this.mTouchSlop : f2 - this.mTouchSlop;
                        this.mLastMotionY = y3;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                    }
                }
                if (this.mIsBeingDragged) {
                    z = false | performDrag(x3);
                } else if (this.mLastPosition >= 0) {
                    int positionByXY2 = getPositionByXY((int) x3, (int) y3);
                    if (positionByXY2 == this.mLastPosition) {
                        if (System.currentTimeMillis() - this.mLastDownTime >= LONG_CLICK_DURATION) {
                            if (onItemLongClick(positionByXY2)) {
                                performHapticFeedback(0);
                                this.mLastDragged = this.mLastPosition;
                                requestParentDisallowInterceptTouchEvent(true);
                                this.mLastTarget = -1;
                                animateDragged();
                                this.mLastPosition = -1;
                            }
                            this.mLastDownTime = Long.MAX_VALUE;
                        }
                    } else {
                        this.mLastPosition = -1;
                    }
                }
            } else if (action == 3) {
                OKLog.d(TAG, "onTouchCancel  " + this.mLastDragged + LangUtils.SINGLE_SPACE + this.mLastPosition);
                if (this.mLastDragged >= 0) {
                    rearrange();
                } else if (this.mIsBeingDragged) {
                    scrollToItem(this.mCurItem, true, 0, false);
                    this.mActivePointerId = -1;
                    endDrag();
                }
            } else if (action == 5) {
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.mLastMotionX = MotionEventCompat.getX(motionEvent, actionIndex);
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
            } else if (action == 6) {
                onSecondaryPointerUp(motionEvent);
                this.mLastMotionX = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId));
            }
            if (z) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            return true;
        }
        return false;
    }

    public void setAdapter(Adapter adapter) {
        Adapter adapter2 = this.mAdapter;
        if (adapter2 != null) {
            adapter2.unregisterDataSetObserver(this.mDataSetObserver);
            removeAllViews();
            this.mCurItem = 0;
            scrollTo(0, 0);
        }
        this.mAdapter = adapter;
        if (adapter != null) {
            adapter.registerDataSetObserver(this.mDataSetObserver);
            for (int i2 = 0; i2 < this.mAdapter.getCount(); i2++) {
                addView(this.mAdapter.getView(i2, null, this));
            }
        }
    }

    public void setColCount(int i2) {
        if (i2 < 1) {
            i2 = 1;
        }
        this.mColCount = i2;
        this.mPageSize = i2 * this.mRowCount;
        requestLayout();
    }

    public void setCurrentItem(int i2) {
        setCurrentItemInternal(i2, false, false);
    }

    void setCurrentItemInternal(int i2, boolean z, boolean z2) {
        setCurrentItemInternal(i2, z, z2, 0);
    }

    public void setGridGap(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.mGridGap = i2;
        requestLayout();
    }

    public void setIsLastNotMoveSize(int i2) {
        this.isLastNotMoveSize = i2;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    public void setOnRearrangeListener(OnRearrangeListener onRearrangeListener) {
        this.mOnRearrangeListener = onRearrangeListener;
    }

    public void setRowCount(int i2) {
        if (i2 < 1) {
            i2 = 1;
        }
        this.mRowCount = i2;
        this.mPageSize = this.mColCount * i2;
        requestLayout();
    }

    void smoothScrollTo(int i2, int i3) {
        smoothScrollTo(i2, i3, 0);
    }

    public void setCurrentItem(int i2, boolean z) {
        setCurrentItemInternal(i2, z, false);
    }

    void setCurrentItemInternal(int i2, boolean z, boolean z2, int i3) {
        int i4 = this.mPageCount;
        if (i4 <= 0) {
            setScrollingCacheEnabled(false);
        } else if (!z2 && this.mCurItem == i2) {
            setScrollingCacheEnabled(false);
        } else {
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 >= i4) {
                i2 = i4 - 1;
            }
            boolean z3 = this.mCurItem != i2;
            this.mCurItem = i2;
            scrollToItem(i2, z, i3, z3);
        }
    }

    void smoothScrollTo(int i2, int i3, int i4) {
        int abs;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i5 = i2 - scrollX;
        int i6 = i3 - scrollY;
        if (i5 == 0 && i6 == 0) {
            completeScroll(false);
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int width = getWidth();
        int i7 = width / 2;
        float f2 = width;
        float f3 = i7;
        float distanceInfluenceForSnapDuration = f3 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (Math.abs(i5) * 1.0f) / f2)) * f3);
        int abs2 = Math.abs(i4);
        if (abs2 > 0) {
            abs = Math.round(Math.abs(distanceInfluenceForSnapDuration / abs2) * 1000.0f) * 4;
        } else {
            abs = (int) (((Math.abs(i5) / f2) + 1.0f) * 100.0f);
        }
        this.mScroller.startScroll(scrollX, scrollY, i5, i6, Math.min(abs, 300));
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public DraggableGridViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mColCount = 4;
        this.mRowCount = 4;
        this.mPageSize = 4 * 4;
        this.mDataSetObserver = new DataSetObserver() { // from class: com.jingdong.common.unification.uniwidget.draggable.DraggableGridViewPager.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                DraggableGridViewPager.this.dataSetChanged();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                DraggableGridViewPager.this.dataSetChanged();
            }
        };
        this.mActivePointerId = -1;
        this.mLastPosition = -1;
        this.mLastDownTime = Long.MAX_VALUE;
        this.mLastDragged = -1;
        this.mLastTarget = -1;
        this.mLastEdge = -1;
        this.mLastEdgeTime = Long.MAX_VALUE;
        this.newPositions = new ArrayList<>();
        this.mEndScrollRunnable = new Runnable() { // from class: com.jingdong.common.unification.uniwidget.draggable.DraggableGridViewPager.3
            @Override // java.lang.Runnable
            public void run() {
                DraggableGridViewPager.this.setScrollState(0);
            }
        };
        this.mScrollState = 0;
        this.isLastMove = true;
        this.isFirstMove = true;
        this.isLastNotMoveSize = 1;
        initDraggableGridViewPager();
    }

    public DraggableGridViewPager(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mColCount = 4;
        this.mRowCount = 4;
        this.mPageSize = 4 * 4;
        this.mDataSetObserver = new DataSetObserver() { // from class: com.jingdong.common.unification.uniwidget.draggable.DraggableGridViewPager.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                DraggableGridViewPager.this.dataSetChanged();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                DraggableGridViewPager.this.dataSetChanged();
            }
        };
        this.mActivePointerId = -1;
        this.mLastPosition = -1;
        this.mLastDownTime = Long.MAX_VALUE;
        this.mLastDragged = -1;
        this.mLastTarget = -1;
        this.mLastEdge = -1;
        this.mLastEdgeTime = Long.MAX_VALUE;
        this.newPositions = new ArrayList<>();
        this.mEndScrollRunnable = new Runnable() { // from class: com.jingdong.common.unification.uniwidget.draggable.DraggableGridViewPager.3
            @Override // java.lang.Runnable
            public void run() {
                DraggableGridViewPager.this.setScrollState(0);
            }
        };
        this.mScrollState = 0;
        this.isLastMove = true;
        this.isFirstMove = true;
        this.isLastNotMoveSize = 1;
        initDraggableGridViewPager();
    }
}
