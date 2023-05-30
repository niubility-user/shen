package com.jingdong.common.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import androidx.core.view.ViewCompat;
import androidx.core.widget.EdgeEffectCompat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* loaded from: classes6.dex */
public class HorizontalListView extends AdapterView<ListAdapter> {
    private static final String BUNDLE_ID_CURRENT_X = "BUNDLE_ID_CURRENT_X";
    private static final String BUNDLE_ID_PARENT_STATE = "BUNDLE_ID_PARENT_STATE";
    private static final float FLING_DEFAULT_ABSORB_VELOCITY = 30.0f;
    private static final float FLING_FRICTION = 0.009f;
    private static final int INSERT_AT_END_OF_LIST = -1;
    private static final int INSERT_AT_START_OF_LIST = 0;
    protected ListAdapter mAdapter;
    private DataSetObserver mAdapterDataObserver;
    private boolean mBlockTouchAction;
    private OnScrollStateChangedListener.ScrollState mCurrentScrollState;
    protected int mCurrentX;
    private int mCurrentlySelectedAdapterIndex;
    private boolean mDataChanged;
    private Runnable mDelayedLayout;
    private int mDisplayOffset;
    private Drawable mDivider;
    private int mDividerWidth;
    private EdgeEffectCompat mEdgeGlowLeft;
    private EdgeEffectCompat mEdgeGlowRight;
    protected Scroller mFlingTracker;
    private GestureDetector mGestureDetector;
    private final GestureListener mGestureListener;
    private boolean mHasNotifiedRunningLowOnData;
    private int mHeightMeasureSpec;
    private boolean mIsParentVerticiallyScrollableViewDisallowingInterceptTouchEvent;
    private int mLeftViewAdapterIndex;
    private int mMaxX;
    protected int mNextX;
    private View.OnClickListener mOnClickListener;
    private OnScrollStateChangedListener mOnScrollStateChangedListener;
    private Rect mRect;
    private List<Queue<View>> mRemovedViewsCache;
    private Integer mRestoreX;
    private int mRightViewAdapterIndex;
    private RunningOutOfDataListener mRunningOutOfDataListener;
    private int mRunningOutOfDataThreshold;
    private View mViewBeingTouched;

    /* loaded from: classes6.dex */
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return HorizontalListView.this.onDown(motionEvent);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            return HorizontalListView.this.onFling(motionEvent, motionEvent2, f2, f3);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            HorizontalListView.this.unpressTouchedChild();
            int childIndex = HorizontalListView.this.getChildIndex((int) motionEvent.getX(), (int) motionEvent.getY());
            if (childIndex < 0 || HorizontalListView.this.mBlockTouchAction) {
                return;
            }
            View childAt = HorizontalListView.this.getChildAt(childIndex);
            AdapterView.OnItemLongClickListener onItemLongClickListener = HorizontalListView.this.getOnItemLongClickListener();
            if (onItemLongClickListener != null) {
                int i2 = HorizontalListView.this.mLeftViewAdapterIndex + childIndex;
                HorizontalListView horizontalListView = HorizontalListView.this;
                if (onItemLongClickListener.onItemLongClick(horizontalListView, childAt, i2, horizontalListView.mAdapter.getItemId(i2))) {
                    HorizontalListView.this.performHapticFeedback(0);
                }
            }
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            HorizontalListView.this.requestParentListViewToNotInterceptTouchEvents(Boolean.TRUE);
            HorizontalListView.this.setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_TOUCH_SCROLL);
            HorizontalListView.this.unpressTouchedChild();
            HorizontalListView horizontalListView = HorizontalListView.this;
            horizontalListView.mNextX += (int) f2;
            horizontalListView.updateOverscrollAnimation(Math.round(f2));
            HorizontalListView.this.requestLayout();
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            HorizontalListView.this.unpressTouchedChild();
            AdapterView.OnItemClickListener onItemClickListener = HorizontalListView.this.getOnItemClickListener();
            int childIndex = HorizontalListView.this.getChildIndex((int) motionEvent.getX(), (int) motionEvent.getY());
            if (childIndex >= 0 && !HorizontalListView.this.mBlockTouchAction) {
                View childAt = HorizontalListView.this.getChildAt(childIndex);
                int i2 = HorizontalListView.this.mLeftViewAdapterIndex + childIndex;
                if (onItemClickListener != null) {
                    HorizontalListView horizontalListView = HorizontalListView.this;
                    onItemClickListener.onItemClick(horizontalListView, childAt, i2, horizontalListView.mAdapter.getItemId(i2));
                    return true;
                }
            }
            if (HorizontalListView.this.mOnClickListener == null || HorizontalListView.this.mBlockTouchAction) {
                return false;
            }
            HorizontalListView.this.mOnClickListener.onClick(HorizontalListView.this);
            return false;
        }
    }

    @TargetApi(11)
    /* loaded from: classes6.dex */
    private static final class HoneycombPlus {
        static {
            if (Build.VERSION.SDK_INT < 11) {
                throw new RuntimeException("Should not get to HoneycombPlus class unless sdk is >= 11!");
            }
        }

        private HoneycombPlus() {
        }

        public static void setFriction(Scroller scroller, float f2) {
            if (scroller != null) {
                scroller.setFriction(f2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(14)
    /* loaded from: classes6.dex */
    public static final class IceCreamSandwichPlus {
        static {
            if (Build.VERSION.SDK_INT < 14) {
                throw new RuntimeException("Should not get to IceCreamSandwichPlus class unless sdk is >= 14!");
            }
        }

        private IceCreamSandwichPlus() {
        }

        public static float getCurrVelocity(Scroller scroller) {
            return scroller.getCurrVelocity();
        }
    }

    /* loaded from: classes6.dex */
    public interface OnScrollStateChangedListener {

        /* loaded from: classes6.dex */
        public enum ScrollState {
            SCROLL_STATE_IDLE,
            SCROLL_STATE_TOUCH_SCROLL,
            SCROLL_STATE_FLING
        }

        void onScrollStateChanged(ScrollState scrollState);
    }

    /* loaded from: classes6.dex */
    public interface RunningOutOfDataListener {
        void onRunningOutOfData();
    }

    public HorizontalListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFlingTracker = new Scroller(getContext());
        GestureListener gestureListener = new GestureListener();
        this.mGestureListener = gestureListener;
        this.mRemovedViewsCache = new ArrayList();
        this.mDataChanged = false;
        this.mRect = new Rect();
        this.mViewBeingTouched = null;
        this.mDividerWidth = 0;
        this.mDivider = null;
        this.mRestoreX = null;
        this.mMaxX = Integer.MAX_VALUE;
        this.mRunningOutOfDataListener = null;
        this.mRunningOutOfDataThreshold = 0;
        this.mHasNotifiedRunningLowOnData = false;
        this.mOnScrollStateChangedListener = null;
        this.mCurrentScrollState = OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE;
        this.mBlockTouchAction = false;
        this.mIsParentVerticiallyScrollableViewDisallowingInterceptTouchEvent = false;
        this.mAdapterDataObserver = new DataSetObserver() { // from class: com.jingdong.common.ui.HorizontalListView.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                HorizontalListView.this.mDataChanged = true;
                HorizontalListView.this.mHasNotifiedRunningLowOnData = false;
                HorizontalListView.this.unpressTouchedChild();
                HorizontalListView.this.invalidate();
                HorizontalListView.this.requestLayout();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                HorizontalListView.this.mHasNotifiedRunningLowOnData = false;
                HorizontalListView.this.unpressTouchedChild();
                HorizontalListView.this.reset();
                HorizontalListView.this.invalidate();
                HorizontalListView.this.requestLayout();
            }
        };
        this.mDelayedLayout = new Runnable() { // from class: com.jingdong.common.ui.HorizontalListView.3
            @Override // java.lang.Runnable
            public void run() {
                HorizontalListView.this.requestLayout();
            }
        };
        this.mEdgeGlowLeft = new EdgeEffectCompat(context);
        this.mEdgeGlowRight = new EdgeEffectCompat(context);
        this.mGestureDetector = new GestureDetector(context, gestureListener);
        bindGestureDetector();
        initView();
        setWillNotDraw(false);
        if (Build.VERSION.SDK_INT >= 11) {
            HoneycombPlus.setFriction(this.mFlingTracker, FLING_FRICTION);
        }
    }

    private void addAndMeasureChild(View view, int i2) {
        addViewInLayout(view, i2, getLayoutParams(view), true);
        measureChild(view);
    }

    private void bindGestureDetector() {
        setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.ui.HorizontalListView.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return HorizontalListView.this.mGestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    private float determineFlingAbsorbVelocity() {
        if (Build.VERSION.SDK_INT >= 14) {
            return IceCreamSandwichPlus.getCurrVelocity(this.mFlingTracker);
        }
        return 30.0f;
    }

    private void determineIfLowOnData() {
        ListAdapter listAdapter;
        if (this.mRunningOutOfDataListener == null || (listAdapter = this.mAdapter) == null || listAdapter.getCount() - (this.mRightViewAdapterIndex + 1) >= this.mRunningOutOfDataThreshold || this.mHasNotifiedRunningLowOnData) {
            return;
        }
        this.mHasNotifiedRunningLowOnData = true;
        this.mRunningOutOfDataListener.onRunningOutOfData();
    }

    private boolean determineMaxX() {
        View rightmostChild;
        if (isLastItemInAdapter(this.mRightViewAdapterIndex) && (rightmostChild = getRightmostChild()) != null) {
            int i2 = this.mMaxX;
            int right = (this.mCurrentX + (rightmostChild.getRight() - getPaddingLeft())) - getRenderWidth();
            this.mMaxX = right;
            if (right < 0) {
                this.mMaxX = 0;
            }
            if (this.mMaxX != i2) {
                return true;
            }
        }
        return false;
    }

    private void drawDivider(Canvas canvas, Rect rect) {
        Drawable drawable = this.mDivider;
        if (drawable != null) {
            drawable.setBounds(rect);
            this.mDivider.draw(canvas);
        }
    }

    private void drawDividers(Canvas canvas) {
        int childCount = getChildCount();
        Rect rect = this.mRect;
        rect.top = getPaddingTop();
        Rect rect2 = this.mRect;
        rect2.bottom = rect2.top + getRenderHeight();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (i2 != childCount - 1 || !isLastItemInAdapter(this.mRightViewAdapterIndex)) {
                View childAt = getChildAt(i2);
                rect.left = childAt.getRight();
                rect.right = childAt.getRight() + this.mDividerWidth;
                if (rect.left < getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                }
                if (rect.right > getWidth() - getPaddingRight()) {
                    rect.right = getWidth() - getPaddingRight();
                }
                drawDivider(canvas, rect);
                if (i2 == 0 && childAt.getLeft() > getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                    rect.right = childAt.getLeft();
                    drawDivider(canvas, rect);
                }
            }
        }
    }

    private void drawEdgeGlow(Canvas canvas) {
        EdgeEffectCompat edgeEffectCompat = this.mEdgeGlowLeft;
        if (edgeEffectCompat != null && !edgeEffectCompat.isFinished() && isEdgeGlowEnabled()) {
            int save = canvas.save();
            int height = getHeight();
            canvas.rotate(-90.0f, 0.0f, 0.0f);
            canvas.translate((-height) + getPaddingBottom(), 0.0f);
            this.mEdgeGlowLeft.setSize(getRenderHeight(), getRenderWidth());
            if (this.mEdgeGlowLeft.draw(canvas)) {
                invalidate();
            }
            canvas.restoreToCount(save);
            return;
        }
        EdgeEffectCompat edgeEffectCompat2 = this.mEdgeGlowRight;
        if (edgeEffectCompat2 == null || edgeEffectCompat2.isFinished() || !isEdgeGlowEnabled()) {
            return;
        }
        int save2 = canvas.save();
        int width = getWidth();
        canvas.rotate(90.0f, 0.0f, 0.0f);
        canvas.translate(getPaddingTop(), -width);
        this.mEdgeGlowRight.setSize(getRenderHeight(), getRenderWidth());
        if (this.mEdgeGlowRight.draw(canvas)) {
            invalidate();
        }
        canvas.restoreToCount(save2);
    }

    private void fillList(int i2) {
        View rightmostChild = getRightmostChild();
        fillListRight(rightmostChild != null ? rightmostChild.getRight() : 0, i2);
        View leftmostChild = getLeftmostChild();
        fillListLeft(leftmostChild != null ? leftmostChild.getLeft() : 0, i2);
    }

    private void fillListLeft(int i2, int i3) {
        int i4;
        while ((i2 + i3) - this.mDividerWidth > 0 && (i4 = this.mLeftViewAdapterIndex) >= 1) {
            int i5 = i4 - 1;
            this.mLeftViewAdapterIndex = i5;
            View view = this.mAdapter.getView(i5, getRecycledView(i5), this);
            addAndMeasureChild(view, 0);
            i2 -= this.mLeftViewAdapterIndex == 0 ? view.getMeasuredWidth() : this.mDividerWidth + view.getMeasuredWidth();
            this.mDisplayOffset -= i2 + i3 == 0 ? view.getMeasuredWidth() : view.getMeasuredWidth() + this.mDividerWidth;
        }
    }

    private void fillListRight(int i2, int i3) {
        while (i2 + i3 + this.mDividerWidth < getWidth() && this.mRightViewAdapterIndex + 1 < this.mAdapter.getCount()) {
            int i4 = this.mRightViewAdapterIndex + 1;
            this.mRightViewAdapterIndex = i4;
            if (this.mLeftViewAdapterIndex < 0) {
                this.mLeftViewAdapterIndex = i4;
            }
            View view = this.mAdapter.getView(i4, getRecycledView(i4), this);
            addAndMeasureChild(view, -1);
            i2 += (this.mRightViewAdapterIndex == 0 ? 0 : this.mDividerWidth) + view.getMeasuredWidth();
            determineIfLowOnData();
        }
    }

    private View getChild(int i2) {
        int i3 = this.mLeftViewAdapterIndex;
        if (i2 < i3 || i2 > this.mRightViewAdapterIndex) {
            return null;
        }
        getChildAt(i2 - i3);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getChildIndex(int i2, int i3) {
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            getChildAt(i4).getHitRect(this.mRect);
            if (this.mRect.contains(i2, i3)) {
                return i4;
            }
        }
        return -1;
    }

    private ViewGroup.LayoutParams getLayoutParams(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams == null ? new ViewGroup.LayoutParams(-2, -1) : layoutParams;
    }

    private View getLeftmostChild() {
        return getChildAt(0);
    }

    private View getRecycledView(int i2) {
        int itemViewType = this.mAdapter.getItemViewType(i2);
        if (isItemViewTypeValid(itemViewType)) {
            return this.mRemovedViewsCache.get(itemViewType).poll();
        }
        return null;
    }

    private int getRenderHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private int getRenderWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private View getRightmostChild() {
        return getChildAt(getChildCount() - 1);
    }

    private void initView() {
        this.mLeftViewAdapterIndex = -1;
        this.mRightViewAdapterIndex = -1;
        this.mDisplayOffset = 0;
        this.mCurrentX = 0;
        this.mNextX = 0;
        this.mMaxX = Integer.MAX_VALUE;
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
    }

    private void initializeRecycledViewCache(int i2) {
        this.mRemovedViewsCache.clear();
        for (int i3 = 0; i3 < i2; i3++) {
            this.mRemovedViewsCache.add(new LinkedList());
        }
    }

    private boolean isEdgeGlowEnabled() {
        ListAdapter listAdapter = this.mAdapter;
        return (listAdapter == null || listAdapter.isEmpty() || this.mMaxX <= 0) ? false : true;
    }

    private boolean isItemViewTypeValid(int i2) {
        return i2 < this.mRemovedViewsCache.size();
    }

    private boolean isLastItemInAdapter(int i2) {
        return i2 == this.mAdapter.getCount() - 1;
    }

    private void measureChild(View view) {
        int makeMeasureSpec;
        ViewGroup.LayoutParams layoutParams = getLayoutParams(view);
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.mHeightMeasureSpec, getPaddingTop() + getPaddingBottom(), layoutParams.height);
        int i2 = layoutParams.width;
        if (i2 > 0) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(makeMeasureSpec, childMeasureSpec);
    }

    private void positionChildren(int i2) {
        int childCount = getChildCount();
        if (childCount > 0) {
            int i3 = this.mDisplayOffset + i2;
            this.mDisplayOffset = i3;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                int paddingLeft = getPaddingLeft() + i3;
                int paddingTop = getPaddingTop();
                childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
                i3 += childAt.getMeasuredWidth() + this.mDividerWidth;
            }
        }
    }

    private void recycleView(int i2, View view) {
        int itemViewType = this.mAdapter.getItemViewType(i2);
        if (isItemViewTypeValid(itemViewType)) {
            this.mRemovedViewsCache.get(itemViewType).offer(view);
        }
    }

    private void releaseEdgeGlow() {
        EdgeEffectCompat edgeEffectCompat = this.mEdgeGlowLeft;
        if (edgeEffectCompat != null) {
            edgeEffectCompat.onRelease();
        }
        EdgeEffectCompat edgeEffectCompat2 = this.mEdgeGlowRight;
        if (edgeEffectCompat2 != null) {
            edgeEffectCompat2.onRelease();
        }
    }

    private void removeNonVisibleChildren(int i2) {
        View leftmostChild = getLeftmostChild();
        while (leftmostChild != null && leftmostChild.getRight() + i2 <= 0) {
            this.mDisplayOffset += isLastItemInAdapter(this.mLeftViewAdapterIndex) ? leftmostChild.getMeasuredWidth() : this.mDividerWidth + leftmostChild.getMeasuredWidth();
            recycleView(this.mLeftViewAdapterIndex, leftmostChild);
            removeViewInLayout(leftmostChild);
            this.mLeftViewAdapterIndex++;
            leftmostChild = getLeftmostChild();
        }
        View rightmostChild = getRightmostChild();
        while (rightmostChild != null && rightmostChild.getLeft() + i2 >= getWidth()) {
            recycleView(this.mRightViewAdapterIndex, rightmostChild);
            removeViewInLayout(rightmostChild);
            this.mRightViewAdapterIndex--;
            rightmostChild = getRightmostChild();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestParentListViewToNotInterceptTouchEvents(Boolean bool) {
        if (this.mIsParentVerticiallyScrollableViewDisallowingInterceptTouchEvent != bool.booleanValue()) {
            for (View view = this; view.getParent() instanceof View; view = (View) view.getParent()) {
                if ((view.getParent() instanceof ListView) || (view.getParent() instanceof ScrollView)) {
                    view.getParent().requestDisallowInterceptTouchEvent(bool.booleanValue());
                    this.mIsParentVerticiallyScrollableViewDisallowingInterceptTouchEvent = bool.booleanValue();
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        initView();
        removeAllViewsInLayout();
        requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentScrollState(OnScrollStateChangedListener.ScrollState scrollState) {
        OnScrollStateChangedListener onScrollStateChangedListener;
        if (this.mCurrentScrollState != scrollState && (onScrollStateChangedListener = this.mOnScrollStateChangedListener) != null) {
            onScrollStateChangedListener.onScrollStateChanged(scrollState);
        }
        this.mCurrentScrollState = scrollState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unpressTouchedChild() {
        View view = this.mViewBeingTouched;
        if (view != null) {
            view.setPressed(false);
            refreshDrawableState();
            this.mViewBeingTouched = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOverscrollAnimation(int i2) {
        if (this.mEdgeGlowLeft == null || this.mEdgeGlowRight == null) {
            return;
        }
        int i3 = this.mCurrentX + i2;
        Scroller scroller = this.mFlingTracker;
        if (scroller == null || scroller.isFinished()) {
            if (i3 < 0) {
                this.mEdgeGlowLeft.onPull(Math.abs(i2) / getRenderWidth());
                if (this.mEdgeGlowRight.isFinished()) {
                    return;
                }
                this.mEdgeGlowRight.onRelease();
            } else if (i3 > this.mMaxX) {
                this.mEdgeGlowRight.onPull(Math.abs(i2) / getRenderWidth());
                if (this.mEdgeGlowLeft.isFinished()) {
                    return;
                }
                this.mEdgeGlowLeft.onRelease();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawEdgeGlow(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSetPressed(boolean z) {
    }

    @Override // android.view.View
    protected float getLeftFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        int i2 = this.mCurrentX;
        if (i2 == 0) {
            return 0.0f;
        }
        if (i2 < horizontalFadingEdgeLength) {
            return i2 / horizontalFadingEdgeLength;
        }
        return 1.0f;
    }

    @Override // android.view.View
    protected float getRightFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        int i2 = this.mCurrentX;
        int i3 = this.mMaxX;
        if (i2 == i3) {
            return 0.0f;
        }
        if (i3 - i2 < horizontalFadingEdgeLength) {
            return (i3 - i2) / horizontalFadingEdgeLength;
        }
        return 1.0f;
    }

    @Override // android.widget.AdapterView
    public View getSelectedView() {
        return getChild(this.mCurrentlySelectedAdapterIndex);
    }

    protected boolean onDown(MotionEvent motionEvent) {
        int childIndex;
        this.mBlockTouchAction = !this.mFlingTracker.isFinished();
        this.mFlingTracker.forceFinished(true);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
        unpressTouchedChild();
        if (!this.mBlockTouchAction && (childIndex = getChildIndex((int) motionEvent.getX(), (int) motionEvent.getY())) >= 0) {
            View childAt = getChildAt(childIndex);
            this.mViewBeingTouched = childAt;
            if (childAt != null) {
                childAt.setPressed(true);
                refreshDrawableState();
            }
        }
        return true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDividers(canvas);
    }

    protected boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        this.mFlingTracker.fling(this.mNextX, 0, (int) (-f2), 0, 0, this.mMaxX, 0, 0);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING);
        requestLayout();
        return true;
    }

    @Override // android.widget.AdapterView, android.view.ViewGroup, android.view.View
    @SuppressLint({"WrongCall"})
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (this.mAdapter == null) {
            return;
        }
        invalidate();
        if (this.mDataChanged) {
            int i6 = this.mCurrentX;
            initView();
            removeAllViewsInLayout();
            this.mNextX = i6;
            this.mDataChanged = false;
        }
        Integer num = this.mRestoreX;
        if (num != null) {
            this.mNextX = num.intValue();
            this.mRestoreX = null;
        }
        if (this.mFlingTracker.computeScrollOffset()) {
            this.mNextX = this.mFlingTracker.getCurrX();
        }
        int i7 = this.mNextX;
        if (i7 < 0) {
            this.mNextX = 0;
            if (this.mEdgeGlowLeft.isFinished()) {
                this.mEdgeGlowLeft.onAbsorb((int) determineFlingAbsorbVelocity());
            }
            this.mFlingTracker.forceFinished(true);
            setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
        } else {
            int i8 = this.mMaxX;
            if (i7 > i8) {
                this.mNextX = i8;
                if (this.mEdgeGlowRight.isFinished()) {
                    this.mEdgeGlowRight.onAbsorb((int) determineFlingAbsorbVelocity());
                }
                this.mFlingTracker.forceFinished(true);
                setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
            }
        }
        int i9 = this.mCurrentX - this.mNextX;
        removeNonVisibleChildren(i9);
        fillList(i9);
        positionChildren(i9);
        this.mCurrentX = this.mNextX;
        if (determineMaxX()) {
            onLayout(z, i2, i3, i4, i5);
        } else if (this.mFlingTracker.isFinished()) {
            if (this.mCurrentScrollState == OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING) {
                setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
            }
        } else {
            ViewCompat.postOnAnimation(this, this.mDelayedLayout);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        this.mHeightMeasureSpec = i3;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.mRestoreX = Integer.valueOf(bundle.getInt(BUNDLE_ID_CURRENT_X));
            super.onRestoreInstanceState(bundle.getParcelable(BUNDLE_ID_PARENT_STATE));
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_ID_PARENT_STATE, super.onSaveInstanceState());
        bundle.putInt(BUNDLE_ID_CURRENT_X, this.mCurrentX);
        return bundle;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            Scroller scroller = this.mFlingTracker;
            if (scroller == null || scroller.isFinished()) {
                setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
            }
            requestParentListViewToNotInterceptTouchEvents(Boolean.FALSE);
            releaseEdgeGlow();
        } else if (motionEvent.getAction() == 3) {
            unpressTouchedChild();
            releaseEdgeGlow();
            requestParentListViewToNotInterceptTouchEvents(Boolean.FALSE);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void scrollTo(int i2) {
        Scroller scroller = this.mFlingTracker;
        int i3 = this.mNextX;
        scroller.startScroll(i3, 0, i2 - i3, 0);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING);
        requestLayout();
    }

    public void setDivider(Drawable drawable) {
        this.mDivider = drawable;
        if (drawable != null) {
            setDividerWidth(drawable.getIntrinsicWidth());
        } else {
            setDividerWidth(0);
        }
    }

    public void setDividerWidth(int i2) {
        this.mDividerWidth = i2;
        requestLayout();
        invalidate();
    }

    @Override // android.widget.AdapterView, android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnScrollStateChangedListener(OnScrollStateChangedListener onScrollStateChangedListener) {
        this.mOnScrollStateChangedListener = onScrollStateChangedListener;
    }

    public void setRunningOutOfDataListener(RunningOutOfDataListener runningOutOfDataListener, int i2) {
        this.mRunningOutOfDataListener = runningOutOfDataListener;
        this.mRunningOutOfDataThreshold = i2;
    }

    @Override // android.widget.AdapterView
    public void setSelection(int i2) {
        this.mCurrentlySelectedAdapterIndex = i2;
    }

    @Override // android.widget.AdapterView
    public ListAdapter getAdapter() {
        return this.mAdapter;
    }

    @Override // android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        ListAdapter listAdapter2 = this.mAdapter;
        if (listAdapter2 != null) {
            listAdapter2.unregisterDataSetObserver(this.mAdapterDataObserver);
        }
        if (listAdapter != null) {
            this.mHasNotifiedRunningLowOnData = false;
            this.mAdapter = listAdapter;
            listAdapter.registerDataSetObserver(this.mAdapterDataObserver);
        }
        initializeRecycledViewCache(this.mAdapter.getViewTypeCount());
        reset();
    }
}
