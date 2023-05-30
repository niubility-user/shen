package com.jingdong.common.unification.jdbottomdrawer;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.Scroller;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.unification.jdbottomdrawer.content.ContentScrollView;

/* loaded from: classes6.dex */
public class JDBottomDrawer extends FrameLayout {
    private static final float DRAG_SPEED_MULTIPLIER = 1.2f;
    private static final int DRAG_SPEED_SLOP = 30;
    private static final int FLING_VELOCITY_SLOP = 80;
    private static final int MAX_SCROLL_DURATION = 400;
    private static final int MIN_SCROLL_DURATION = 100;
    private static final int MOTION_DISTANCE_SLOP = 10;
    private static final float SCROLL_TO_CLOSE_OFFSET_FACTOR = 0.5f;
    private static final float SCROLL_TO_EXIT_OFFSET_FACTOR = 0.8f;
    private static final String TAG = JDBottomDrawer.class.getSimpleName();
    private final AbsListView.OnScrollListener associatedListViewListener;
    private final RecyclerView.OnScrollListener associatedRecycleViewListener;
    private InnerStatus currentInnerStatus;
    private int exitOffset;
    private GestureDetector gestureDetector;
    private final GestureDetector.OnGestureListener gestureListener;
    private boolean isAllowHorizontalScroll;
    private boolean isAllowPointerIntercepted;
    private boolean isCloseDraggable;
    private boolean isCurrentPointerIntercepted;
    private boolean isDraggable;
    private boolean isEnable;
    private boolean isSupportExit;
    private float lastDownX;
    private float lastDownY;
    private Status lastFlingStatus;
    private float lastX;
    private float lastY;
    private ContentScrollView.OnScrollChangedListener mOnScrollChangedListener;
    private ContentScrollView mScrollView;
    private int maxOffset;
    public int minOffset;
    private OnScrollChangedListener onScrollChangedListener;
    private Scroller scroller;

    /* renamed from: com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer$5  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$unification$jdbottomdrawer$JDBottomDrawer$InnerStatus;

        static {
            int[] iArr = new int[InnerStatus.values().length];
            $SwitchMap$com$jingdong$common$unification$jdbottomdrawer$JDBottomDrawer$InnerStatus = iArr;
            try {
                iArr[InnerStatus.CLOSED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$jdbottomdrawer$JDBottomDrawer$InnerStatus[InnerStatus.OPENED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$unification$jdbottomdrawer$JDBottomDrawer$InnerStatus[InnerStatus.EXIT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public enum InnerStatus {
        EXIT,
        OPENED,
        CLOSED,
        MOVING,
        SCROLLING
    }

    /* loaded from: classes6.dex */
    public interface OnScrollChangedListener {
        void onChildScroll(int i2);

        void onScrollFinished(Status status);

        void onScrollProgressChanged(float f2);
    }

    /* loaded from: classes6.dex */
    public enum Status {
        EXIT,
        OPENED,
        CLOSED
    }

    public JDBottomDrawer(Context context) {
        super(context);
        GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                if (f3 > 80.0f) {
                    Status status = JDBottomDrawer.this.lastFlingStatus;
                    Status status2 = Status.OPENED;
                    if (status.equals(status2) && (-JDBottomDrawer.this.getScrollY()) > JDBottomDrawer.this.maxOffset) {
                        JDBottomDrawer.this.lastFlingStatus = Status.EXIT;
                        JDBottomDrawer.this.scrollToExit();
                    } else {
                        JDBottomDrawer.this.scrollToOpen();
                        JDBottomDrawer.this.lastFlingStatus = status2;
                    }
                    return true;
                } else if (f3 >= 80.0f || JDBottomDrawer.this.getScrollY() > (-JDBottomDrawer.this.maxOffset)) {
                    if (f3 >= 80.0f || JDBottomDrawer.this.getScrollY() <= (-JDBottomDrawer.this.maxOffset)) {
                        return false;
                    }
                    JDBottomDrawer.this.scrollToClose();
                    JDBottomDrawer.this.lastFlingStatus = Status.CLOSED;
                    return true;
                } else {
                    JDBottomDrawer.this.scrollToOpen();
                    JDBottomDrawer.this.lastFlingStatus = Status.OPENED;
                    return true;
                }
            }
        };
        this.gestureListener = simpleOnGestureListener;
        this.associatedListViewListener = new AbsListView.OnScrollListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer.2
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                JDBottomDrawer.this.updateListViewScrollState(absListView);
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
                JDBottomDrawer.this.updateListViewScrollState(absListView);
            }
        };
        this.associatedRecycleViewListener = new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer.3
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                JDBottomDrawer.this.updateRecycleViewScrollState(recyclerView);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                JDBottomDrawer.this.updateRecycleViewScrollState(recyclerView);
            }
        };
        this.lastFlingStatus = Status.CLOSED;
        this.isCloseDraggable = true;
        this.isEnable = true;
        this.isSupportExit = false;
        this.isAllowHorizontalScroll = true;
        this.isDraggable = true;
        this.isAllowPointerIntercepted = true;
        this.isCurrentPointerIntercepted = false;
        this.currentInnerStatus = InnerStatus.OPENED;
        this.maxOffset = 0;
        this.minOffset = 0;
        this.exitOffset = 0;
        if (Build.VERSION.SDK_INT >= 11) {
            this.scroller = new Scroller(getContext(), null, true);
        } else {
            this.scroller = new Scroller(getContext());
        }
        this.gestureDetector = new GestureDetector(getContext(), simpleOnGestureListener);
        this.mOnScrollChangedListener = new ContentScrollView.OnScrollChangedListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer.4
            @Override // com.jingdong.common.unification.jdbottomdrawer.content.ContentScrollView.OnScrollChangedListener
            public void onScrollChanged(int i2, int i3, int i4, int i5) {
                if (JDBottomDrawer.this.mScrollView == null) {
                    return;
                }
                if (JDBottomDrawer.this.onScrollChangedListener != null) {
                    JDBottomDrawer.this.onScrollChangedListener.onChildScroll(i5);
                }
                if (JDBottomDrawer.this.mScrollView.getScrollY() == 0) {
                    JDBottomDrawer.this.setDraggable(true);
                } else {
                    JDBottomDrawer.this.setDraggable(false);
                }
            }
        };
    }

    private void completeMove() {
        float f2 = -((this.maxOffset - this.minOffset) * 0.5f);
        if (getScrollY() > f2) {
            scrollToClose();
        } else if (this.isSupportExit) {
            int i2 = this.exitOffset;
            float f3 = -(((i2 - r2) * 0.8f) + this.maxOffset);
            if (getScrollY() <= f2 && getScrollY() > f3) {
                scrollToOpen();
            } else {
                scrollToExit();
            }
        } else {
            scrollToOpen();
        }
    }

    private boolean disposeEdgeValue(int i2) {
        if (this.isSupportExit) {
            if (i2 > 0 || getScrollY() < (-this.minOffset)) {
                return i2 >= 0 && getScrollY() <= (-this.exitOffset);
            }
            return true;
        } else if (i2 > 0 || getScrollY() < (-this.minOffset)) {
            return i2 >= 0 && getScrollY() <= (-this.maxOffset);
        } else {
            return true;
        }
    }

    private void initFromAttributes(Context context, AttributeSet attributeSet) {
        int dimensionPixelOffset;
        int dimensionPixelOffset2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.JDBottomDrawer);
        int i2 = R.styleable.JDBottomDrawer_maxOffset;
        if (obtainStyledAttributes.hasValue(i2) && (dimensionPixelOffset2 = obtainStyledAttributes.getDimensionPixelOffset(i2, this.maxOffset)) != getScreenHeight()) {
            this.maxOffset = getScreenHeight() - dimensionPixelOffset2;
        }
        int i3 = R.styleable.JDBottomDrawer_minOffset;
        if (obtainStyledAttributes.hasValue(i3)) {
            this.minOffset = obtainStyledAttributes.getDimensionPixelOffset(i3, this.minOffset);
        }
        int i4 = R.styleable.JDBottomDrawer_exitOffset;
        if (obtainStyledAttributes.hasValue(i4) && (dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(i4, getScreenHeight())) != getScreenHeight()) {
            this.exitOffset = getScreenHeight() - dimensionPixelOffset;
        }
        int i5 = R.styleable.JDBottomDrawer_allowHorizontalScroll;
        if (obtainStyledAttributes.hasValue(i5)) {
            this.isAllowHorizontalScroll = obtainStyledAttributes.getBoolean(i5, true);
        }
        int i6 = R.styleable.JDBottomDrawer_isSupportExit;
        if (obtainStyledAttributes.hasValue(i6)) {
            this.isSupportExit = obtainStyledAttributes.getBoolean(i6, true);
        }
        int i7 = R.styleable.JDBottomDrawer_stateMode;
        if (obtainStyledAttributes.hasValue(i7)) {
            int integer = obtainStyledAttributes.getInteger(i7, 0);
            if (integer == 0) {
                setToOpen();
            } else if (integer == 1) {
                setToClosed();
            } else if (integer != 2) {
                setToClosed();
            } else {
                setToExit();
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void onScrollFinished(Status status) {
        OnScrollChangedListener onScrollChangedListener = this.onScrollChangedListener;
        if (onScrollChangedListener != null) {
            onScrollChangedListener.onScrollFinished(status);
        }
    }

    private void onScrollProgressChanged(float f2) {
        OnScrollChangedListener onScrollChangedListener = this.onScrollChangedListener;
        if (onScrollChangedListener != null) {
            onScrollChangedListener.onScrollProgressChanged(f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateListViewScrollState(AbsListView absListView) {
        if (absListView.getChildCount() == 0) {
            setDraggable(true);
        } else if (absListView.getFirstVisiblePosition() == 0 && absListView.getChildAt(0).getTop() == absListView.getPaddingTop()) {
            setDraggable(true);
        } else {
            setDraggable(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRecycleViewScrollState(RecyclerView recyclerView) {
        if (recyclerView.getChildCount() == 0) {
            setDraggable(true);
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            if (((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() == 0 && recyclerView.getChildAt(0).getTop() == recyclerView.getPaddingTop()) {
                setDraggable(true);
            } else {
                setDraggable(false);
            }
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.scroller.isFinished() || !this.scroller.computeScrollOffset()) {
            return;
        }
        int currY = this.scroller.getCurrY();
        boolean z = false;
        scrollTo(0, currY);
        if (this.isSupportExit && currY == (-this.exitOffset)) {
            z = true;
        }
        if (currY != (-this.minOffset) && currY != (-this.maxOffset) && !z) {
            invalidate();
        } else {
            this.scroller.abortAnimation();
        }
    }

    public Status getCurrentStatus() {
        int i2 = AnonymousClass5.$SwitchMap$com$jingdong$common$unification$jdbottomdrawer$JDBottomDrawer$InnerStatus[this.currentInnerStatus.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return Status.OPENED;
                }
                return Status.EXIT;
            }
            return Status.OPENED;
        }
        return Status.CLOSED;
    }

    public int getScreenHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getContext() instanceof Activity) {
            ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        int i2 = 0;
        try {
            int identifier = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                i2 = getContext().getResources().getDimensionPixelSize(identifier);
            }
        } catch (Exception unused) {
        }
        return displayMetrics.heightPixels - i2;
    }

    public boolean isAllowHorizontalScroll() {
        return this.isAllowHorizontalScroll;
    }

    public boolean isDraggable() {
        return this.isDraggable;
    }

    public boolean isSupportExit() {
        return this.isSupportExit;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isEnable) {
            if (this.isCloseDraggable || this.currentInnerStatus != InnerStatus.CLOSED) {
                if (this.isDraggable || this.currentInnerStatus != InnerStatus.CLOSED) {
                    int action = motionEvent.getAction();
                    if (action != 0) {
                        if (action != 1) {
                            if (action == 2) {
                                if (this.isAllowPointerIntercepted) {
                                    if (this.isCurrentPointerIntercepted) {
                                        return true;
                                    }
                                    int y = (int) (motionEvent.getY() - this.lastDownY);
                                    int x = (int) (motionEvent.getX() - this.lastDownX);
                                    if (Math.abs(y) < 10) {
                                        return false;
                                    }
                                    if (Math.abs(y) < Math.abs(x) && this.isAllowHorizontalScroll) {
                                        this.isAllowPointerIntercepted = false;
                                        this.isCurrentPointerIntercepted = false;
                                        return false;
                                    }
                                    InnerStatus innerStatus = this.currentInnerStatus;
                                    if (innerStatus == InnerStatus.CLOSED) {
                                        if (y < 0) {
                                            return false;
                                        }
                                    } else if (innerStatus == InnerStatus.OPENED && !this.isSupportExit && y > 0) {
                                        return false;
                                    }
                                    this.isCurrentPointerIntercepted = true;
                                    return true;
                                }
                                return false;
                            } else if (action != 3) {
                                return false;
                            }
                        }
                        this.isAllowPointerIntercepted = true;
                        this.isCurrentPointerIntercepted = false;
                        if (this.currentInnerStatus == InnerStatus.MOVING) {
                            return true;
                        }
                    } else {
                        this.lastX = motionEvent.getX();
                        float y2 = motionEvent.getY();
                        this.lastY = y2;
                        this.lastDownX = this.lastX;
                        this.lastDownY = y2;
                        this.isAllowPointerIntercepted = true;
                        this.isCurrentPointerIntercepted = false;
                        if (!this.scroller.isFinished()) {
                            this.scroller.forceFinished(true);
                            this.currentInnerStatus = InnerStatus.MOVING;
                            this.isCurrentPointerIntercepted = true;
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.isCurrentPointerIntercepted) {
            this.gestureDetector.onTouchEvent(motionEvent);
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        int y = (int) ((motionEvent.getY() - this.lastY) * DRAG_SPEED_MULTIPLIER);
                        int signum = ((int) Math.signum(y)) * Math.min(Math.abs(y), 30);
                        if (disposeEdgeValue(signum)) {
                            return true;
                        }
                        this.currentInnerStatus = InnerStatus.MOVING;
                        int scrollY = getScrollY() - signum;
                        int i2 = this.minOffset;
                        if (scrollY >= (-i2)) {
                            scrollTo(0, -i2);
                        } else {
                            int i3 = this.maxOffset;
                            if (scrollY <= (-i3) && !this.isSupportExit) {
                                scrollTo(0, -i3);
                            } else {
                                scrollTo(0, scrollY);
                            }
                        }
                        this.lastY = motionEvent.getY();
                        return true;
                    } else if (action != 3) {
                        return false;
                    }
                }
                if (this.currentInnerStatus == InnerStatus.MOVING) {
                    completeMove();
                    return true;
                }
                return false;
            }
            this.lastY = motionEvent.getY();
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        super.scrollTo(i2, i3);
        int i4 = this.maxOffset;
        if (i4 == this.minOffset) {
            return;
        }
        if ((-i3) <= i4) {
            onScrollProgressChanged((r1 - r0) / (i4 - r0));
        } else {
            onScrollProgressChanged((r1 - i4) / (i4 - this.exitOffset));
        }
        if (i3 == (-this.minOffset)) {
            InnerStatus innerStatus = this.currentInnerStatus;
            InnerStatus innerStatus2 = InnerStatus.CLOSED;
            if (innerStatus != innerStatus2) {
                this.currentInnerStatus = innerStatus2;
                onScrollFinished(Status.CLOSED);
            }
        } else if (i3 == (-this.maxOffset)) {
            InnerStatus innerStatus3 = this.currentInnerStatus;
            InnerStatus innerStatus4 = InnerStatus.OPENED;
            if (innerStatus3 != innerStatus4) {
                this.currentInnerStatus = innerStatus4;
                onScrollFinished(Status.OPENED);
            }
        } else if (this.isSupportExit && i3 == (-this.exitOffset)) {
            InnerStatus innerStatus5 = this.currentInnerStatus;
            InnerStatus innerStatus6 = InnerStatus.EXIT;
            if (innerStatus5 != innerStatus6) {
                this.currentInnerStatus = innerStatus6;
                onScrollFinished(Status.EXIT);
            }
        }
    }

    public void scrollToClose() {
        int i2;
        int i3;
        if (this.currentInnerStatus == InnerStatus.CLOSED || this.maxOffset == this.minOffset || (i3 = (-getScrollY()) - (i2 = this.minOffset)) == 0) {
            return;
        }
        this.currentInnerStatus = InnerStatus.SCROLLING;
        this.scroller.startScroll(0, getScrollY(), 0, i3, Math.abs((i3 * 300) / (this.maxOffset - i2)) + 100);
        invalidate();
    }

    public void scrollToExit() {
        int i2;
        int i3;
        if (!this.isSupportExit || this.currentInnerStatus == InnerStatus.EXIT || this.exitOffset == this.maxOffset || (i3 = (-getScrollY()) - (i2 = this.exitOffset)) == 0) {
            return;
        }
        this.currentInnerStatus = InnerStatus.SCROLLING;
        this.scroller.startScroll(0, getScrollY(), 0, i3, Math.abs((i3 * 300) / (i2 - this.maxOffset)) + 100);
        invalidate();
    }

    public void scrollToOpen() {
        int i2;
        int i3;
        if (this.currentInnerStatus == InnerStatus.OPENED || this.maxOffset == this.minOffset || (i3 = (-getScrollY()) - (i2 = this.maxOffset)) == 0) {
            return;
        }
        this.currentInnerStatus = InnerStatus.SCROLLING;
        this.scroller.startScroll(0, getScrollY(), 0, i3, Math.abs((i3 * 300) / (i2 - this.minOffset)) + 100);
        invalidate();
    }

    public void setAllowHorizontalScroll(boolean z) {
        this.isAllowHorizontalScroll = z;
    }

    public void setAssociatedListView(AbsListView absListView) {
        absListView.setOnScrollListener(this.associatedListViewListener);
        updateListViewScrollState(absListView);
        if (this.minOffset != 0) {
            absListView.getLayoutParams().height = getScreenHeight() - this.minOffset;
            absListView.requestLayout();
        }
    }

    public void setAssociatedRecycleView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(this.associatedRecycleViewListener);
        updateRecycleViewScrollState(recyclerView);
        if (this.minOffset != 0) {
            recyclerView.getLayoutParams().height = getScreenHeight() - this.minOffset;
            recyclerView.requestLayout();
        }
    }

    public void setAssociatedScrollView(ContentScrollView contentScrollView) {
        this.mScrollView = contentScrollView;
        contentScrollView.setScrollbarFadingEnabled(false);
        this.mScrollView.setOnScrollChangeListener(this.mOnScrollChangedListener);
        if (this.minOffset != 0) {
            this.mScrollView.getLayoutParams().height = getScreenHeight() - this.minOffset;
            this.mScrollView.requestLayout();
        }
    }

    public void setCloseDraggable(boolean z) {
        this.isCloseDraggable = z;
    }

    public void setDraggable(boolean z) {
        this.isDraggable = z;
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }

    public void setExitOffset(int i2) {
        this.exitOffset = getScreenHeight() - i2;
    }

    public void setIsSupportExit(boolean z) {
        this.isSupportExit = z;
    }

    public void setMaxOffset(int i2) {
        this.maxOffset = getScreenHeight() - i2;
    }

    public void setMinOffset(int i2) {
        this.minOffset = i2;
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }

    public void setToClosed() {
        scrollTo(0, -this.minOffset);
        this.currentInnerStatus = InnerStatus.CLOSED;
        this.lastFlingStatus = Status.CLOSED;
    }

    public void setToExit() {
        if (this.isSupportExit) {
            scrollTo(0, -this.exitOffset);
            this.currentInnerStatus = InnerStatus.EXIT;
        }
    }

    public void setToOpen() {
        scrollTo(0, -this.maxOffset);
        this.currentInnerStatus = InnerStatus.OPENED;
        this.lastFlingStatus = Status.OPENED;
    }

    public void showOrHide() {
        InnerStatus innerStatus = this.currentInnerStatus;
        if (innerStatus == InnerStatus.OPENED) {
            scrollToClose();
        } else if (innerStatus == InnerStatus.CLOSED) {
            scrollToOpen();
        }
    }

    public JDBottomDrawer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                if (f3 > 80.0f) {
                    Status status = JDBottomDrawer.this.lastFlingStatus;
                    Status status2 = Status.OPENED;
                    if (status.equals(status2) && (-JDBottomDrawer.this.getScrollY()) > JDBottomDrawer.this.maxOffset) {
                        JDBottomDrawer.this.lastFlingStatus = Status.EXIT;
                        JDBottomDrawer.this.scrollToExit();
                    } else {
                        JDBottomDrawer.this.scrollToOpen();
                        JDBottomDrawer.this.lastFlingStatus = status2;
                    }
                    return true;
                } else if (f3 >= 80.0f || JDBottomDrawer.this.getScrollY() > (-JDBottomDrawer.this.maxOffset)) {
                    if (f3 >= 80.0f || JDBottomDrawer.this.getScrollY() <= (-JDBottomDrawer.this.maxOffset)) {
                        return false;
                    }
                    JDBottomDrawer.this.scrollToClose();
                    JDBottomDrawer.this.lastFlingStatus = Status.CLOSED;
                    return true;
                } else {
                    JDBottomDrawer.this.scrollToOpen();
                    JDBottomDrawer.this.lastFlingStatus = Status.OPENED;
                    return true;
                }
            }
        };
        this.gestureListener = simpleOnGestureListener;
        this.associatedListViewListener = new AbsListView.OnScrollListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer.2
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                JDBottomDrawer.this.updateListViewScrollState(absListView);
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
                JDBottomDrawer.this.updateListViewScrollState(absListView);
            }
        };
        this.associatedRecycleViewListener = new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer.3
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                JDBottomDrawer.this.updateRecycleViewScrollState(recyclerView);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                JDBottomDrawer.this.updateRecycleViewScrollState(recyclerView);
            }
        };
        this.lastFlingStatus = Status.CLOSED;
        this.isCloseDraggable = true;
        this.isEnable = true;
        this.isSupportExit = false;
        this.isAllowHorizontalScroll = true;
        this.isDraggable = true;
        this.isAllowPointerIntercepted = true;
        this.isCurrentPointerIntercepted = false;
        this.currentInnerStatus = InnerStatus.OPENED;
        this.maxOffset = 0;
        this.minOffset = 0;
        this.exitOffset = 0;
        if (Build.VERSION.SDK_INT >= 11) {
            this.scroller = new Scroller(getContext(), null, true);
        } else {
            this.scroller = new Scroller(getContext());
        }
        this.gestureDetector = new GestureDetector(getContext(), simpleOnGestureListener);
        this.mOnScrollChangedListener = new ContentScrollView.OnScrollChangedListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer.4
            @Override // com.jingdong.common.unification.jdbottomdrawer.content.ContentScrollView.OnScrollChangedListener
            public void onScrollChanged(int i2, int i3, int i4, int i5) {
                if (JDBottomDrawer.this.mScrollView == null) {
                    return;
                }
                if (JDBottomDrawer.this.onScrollChangedListener != null) {
                    JDBottomDrawer.this.onScrollChangedListener.onChildScroll(i5);
                }
                if (JDBottomDrawer.this.mScrollView.getScrollY() == 0) {
                    JDBottomDrawer.this.setDraggable(true);
                } else {
                    JDBottomDrawer.this.setDraggable(false);
                }
            }
        };
        initFromAttributes(context, attributeSet);
    }

    public JDBottomDrawer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                if (f3 > 80.0f) {
                    Status status = JDBottomDrawer.this.lastFlingStatus;
                    Status status2 = Status.OPENED;
                    if (status.equals(status2) && (-JDBottomDrawer.this.getScrollY()) > JDBottomDrawer.this.maxOffset) {
                        JDBottomDrawer.this.lastFlingStatus = Status.EXIT;
                        JDBottomDrawer.this.scrollToExit();
                    } else {
                        JDBottomDrawer.this.scrollToOpen();
                        JDBottomDrawer.this.lastFlingStatus = status2;
                    }
                    return true;
                } else if (f3 >= 80.0f || JDBottomDrawer.this.getScrollY() > (-JDBottomDrawer.this.maxOffset)) {
                    if (f3 >= 80.0f || JDBottomDrawer.this.getScrollY() <= (-JDBottomDrawer.this.maxOffset)) {
                        return false;
                    }
                    JDBottomDrawer.this.scrollToClose();
                    JDBottomDrawer.this.lastFlingStatus = Status.CLOSED;
                    return true;
                } else {
                    JDBottomDrawer.this.scrollToOpen();
                    JDBottomDrawer.this.lastFlingStatus = Status.OPENED;
                    return true;
                }
            }
        };
        this.gestureListener = simpleOnGestureListener;
        this.associatedListViewListener = new AbsListView.OnScrollListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer.2
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i22, int i3, int i4) {
                JDBottomDrawer.this.updateListViewScrollState(absListView);
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i22) {
                JDBottomDrawer.this.updateListViewScrollState(absListView);
            }
        };
        this.associatedRecycleViewListener = new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer.3
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i22) {
                JDBottomDrawer.this.updateRecycleViewScrollState(recyclerView);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i22, int i3) {
                JDBottomDrawer.this.updateRecycleViewScrollState(recyclerView);
            }
        };
        this.lastFlingStatus = Status.CLOSED;
        this.isCloseDraggable = true;
        this.isEnable = true;
        this.isSupportExit = false;
        this.isAllowHorizontalScroll = true;
        this.isDraggable = true;
        this.isAllowPointerIntercepted = true;
        this.isCurrentPointerIntercepted = false;
        this.currentInnerStatus = InnerStatus.OPENED;
        this.maxOffset = 0;
        this.minOffset = 0;
        this.exitOffset = 0;
        if (Build.VERSION.SDK_INT >= 11) {
            this.scroller = new Scroller(getContext(), null, true);
        } else {
            this.scroller = new Scroller(getContext());
        }
        this.gestureDetector = new GestureDetector(getContext(), simpleOnGestureListener);
        this.mOnScrollChangedListener = new ContentScrollView.OnScrollChangedListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer.4
            @Override // com.jingdong.common.unification.jdbottomdrawer.content.ContentScrollView.OnScrollChangedListener
            public void onScrollChanged(int i22, int i3, int i4, int i5) {
                if (JDBottomDrawer.this.mScrollView == null) {
                    return;
                }
                if (JDBottomDrawer.this.onScrollChangedListener != null) {
                    JDBottomDrawer.this.onScrollChangedListener.onChildScroll(i5);
                }
                if (JDBottomDrawer.this.mScrollView.getScrollY() == 0) {
                    JDBottomDrawer.this.setDraggable(true);
                } else {
                    JDBottomDrawer.this.setDraggable(false);
                }
            }
        };
        initFromAttributes(context, attributeSet);
    }
}
