package com.jingdong.jdreact.plugin.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import com.jingdong.jdreact.plugin.banner.ui.CardSwitchListener;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes13.dex */
public class CycleViewPager extends ViewGroup {
    private static final int AUTO_FLIPPER_MESSAGE = 1001;
    private static final float SCALE_STEP = 0.254f;
    public static final int VANISH_TYPE_LEFT = 0;
    public static final int VANISH_TYPE_RIGHT = 1;
    private static final int X_DISTANCE_THRESHOLD = 30;
    private static final int X_VEL_THRESHOLD = 800;
    private int MAX_SLIDE_DISTANCE_LINKAGE;
    private int allHeight;
    private int allWidth;
    private int bottomMarginTop;
    View.OnClickListener btnListener;
    private boolean btnLock;
    private CardSwitchListener cardSwitchListener;
    private int childWith;
    private List<CardDataItem> dataList;
    private Point downPoint;
    int index;
    private int initCenterViewX;
    private int initCenterViewY;
    private int isShowing;
    private int itemMarginTop;
    boolean layouted;
    private View leftBtn;
    private int leftRightSpace;
    private final ViewDragHelper mDragHelper;
    private Handler mHander;
    private int mTimeInteval;
    private int mTouchSlop;
    private int marginLeftRight;
    private GestureDetectorCompat moveDetector;
    int position;
    private List<Status> releasedViewList;
    private View rightBtn;
    private boolean shouldflipper;
    private View topLayout;
    private List<CardItemView> viewList;
    private int xOffsetStep;
    private int yOffsetStep;

    /* loaded from: classes13.dex */
    private class DragHelperCallback extends ViewDragHelper.Callback {
        private DragHelperCallback() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i2, int i3) {
            return i2;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i2, int i3) {
            return CycleViewPager.this.itemMarginTop;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return 256;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            CycleViewPager.this.onViewPosChangedRight(view, i4);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            CycleViewPager.this.animToSide(view, (int) f2, (int) f3);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            if (CycleViewPager.this.dataList == null || CycleViewPager.this.dataList.size() == 0 || view.getVisibility() != 0 || view.getScaleX() <= 0.746f || CycleViewPager.this.btnLock) {
                return false;
            }
            CycleViewPager.this.viewList.indexOf(view);
            return true;
        }
    }

    /* loaded from: classes13.dex */
    class MoveDetector extends GestureDetector.SimpleOnGestureListener {
        MoveDetector() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            return Math.abs(f3) + Math.abs(f2) > ((float) CycleViewPager.this.mTouchSlop);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class Status {
        boolean left;
        View v;

        Status() {
        }
    }

    public CycleViewPager(Context context) {
        this(context, null);
    }

    private void ajustLinkageViewItem(float f2, int i2, boolean z, int i3) {
        List<CardItemView> list = this.viewList;
        CardItemView cardItemView = list.get((list.size() / 2) + i2);
        cardItemView.layout(cardItemView.getLeft() + i3, cardItemView.getTop(), cardItemView.getWidth() + cardItemView.getLeft() + i3, cardItemView.getHeight() + this.itemMarginTop);
    }

    private void ajustLinkageViewItemPosition(int i2, boolean z, int i3) {
        List<CardItemView> list = this.viewList;
        CardItemView cardItemView = list.get((list.size() / 2) + i2);
        cardItemView.layout(cardItemView.getLeft() + i3, cardItemView.getTop(), cardItemView.getWidth() + cardItemView.getLeft() + i3, cardItemView.getHeight() + this.itemMarginTop);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animToSide(View view, int i2, int i3) {
        int i4;
        CardSwitchListener cardSwitchListener;
        int i5 = this.initCenterViewX;
        int i6 = this.initCenterViewY;
        CardItemView cardItemView = (CardItemView) view;
        int left = cardItemView.getLeft() - this.initCenterViewX;
        int top = cardItemView.getTop() - this.initCenterViewY;
        boolean z = true;
        if (i2 > 800 && Math.abs(i3) < i2 * 3.0f) {
            int i7 = this.childWith;
            int i8 = this.leftRightSpace + i7;
            int left2 = (i3 * (i7 + cardItemView.getLeft())) / i2;
            i5 = i8;
            z = false;
            i6 = cardItemView.getTop() + left2;
            i4 = 1;
        } else {
            if (i2 < -800) {
                int i9 = -i2;
                if (Math.abs(i3) < i9 * 3.0f) {
                    int i10 = this.childWith;
                    int left3 = (i3 * (i10 + cardItemView.getLeft())) / i9;
                    i5 = -(this.leftRightSpace + i10);
                    i6 = cardItemView.getTop() + left3;
                    i4 = 0;
                }
            }
            if (left <= 30 || Math.abs(top) >= left * 3.0f) {
                if (left < -30) {
                    int i11 = -left;
                    if (Math.abs(top) < i11 * 3.0f) {
                        int i12 = this.childWith;
                        i5 = -(this.leftRightSpace + i12);
                        i6 = ((top * (i12 + this.initCenterViewX)) / i11) + this.initCenterViewY;
                        i4 = 0;
                    }
                }
                i4 = -1;
            } else {
                int i13 = this.childWith;
                i5 = i13 + this.leftRightSpace;
                i6 = ((top * (i13 + this.initCenterViewX)) / left) + this.initCenterViewY;
                i4 = 1;
            }
            z = false;
        }
        int i14 = this.allHeight;
        if (i6 <= i14 && i6 < (-i14) / 2) {
            int i15 = (-i14) / 2;
        }
        if (i5 == this.initCenterViewX) {
            onViewPosChangedRight(cardItemView, 0 - cardItemView.getLeft());
            cardItemView.offsetLeftAndRight(0 - cardItemView.getLeft());
            return;
        }
        Status status = new Status();
        status.left = z;
        status.v = cardItemView;
        this.releasedViewList.add(status);
        if (this.mDragHelper.smoothSlideViewTo(cardItemView, i5, this.itemMarginTop)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        if (i4 < 0 || (cardSwitchListener = this.cardSwitchListener) == null) {
            return;
        }
        cardSwitchListener.onCardVanish(this.isShowing, i4);
    }

    private void orderViewStackRight() {
        if (this.releasedViewList.size() == 0) {
            return;
        }
        if (((CardItemView) this.releasedViewList.get(0).v).getLeft() == this.initCenterViewX) {
            this.releasedViewList.remove(0);
            return;
        }
        if (this.isShowing >= this.dataList.size()) {
            this.isShowing -= this.dataList.size();
        }
        int i2 = this.isShowing;
        if (i2 - 1 >= 0) {
            this.isShowing = i2 - 1;
        } else {
            this.isShowing = (i2 - 1) + this.dataList.size();
        }
        List<CardItemView> list = this.viewList;
        CardItemView cardItemView = list.get(list.size() - 1);
        int i3 = this.childWith;
        int i4 = this.leftRightSpace;
        int i5 = this.marginLeftRight;
        int i6 = this.itemMarginTop;
        cardItemView.layout(-(((i3 * 2) + i4) - i5), i6, (-(((i3 * 2) + i4) - i5)) + i3, cardItemView.getHeight() + i6);
        boolean z = this.topLayout.getLeft() != 0;
        this.topLayout.layout(0, 0, getWidth(), getHeight());
        this.topLayout.bringToFront();
        int size = this.isShowing - (this.viewList.size() / 2);
        if (size < 0) {
            size += this.dataList.size();
        }
        List<CardDataItem> list2 = this.dataList;
        cardItemView.fillData(list2.get(size % list2.size()));
        this.viewList.remove(cardItemView);
        this.viewList.add(0, cardItemView);
        this.releasedViewList.remove(0);
        if (z) {
            resizeLayout();
        }
        CardSwitchListener cardSwitchListener = this.cardSwitchListener;
        if (cardSwitchListener != null) {
            cardSwitchListener.onViewScroll(1.0d);
            this.cardSwitchListener.onShow(this.isShowing);
        }
        this.mHander.removeMessages(1001);
        this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
    }

    private void orderViewStackleft() {
        this.position = 0;
        if (this.releasedViewList.size() == 0) {
            return;
        }
        if (((CardItemView) this.releasedViewList.get(0).v).getLeft() == this.initCenterViewX) {
            this.releasedViewList.remove(0);
        } else if (!this.releasedViewList.get(0).left) {
            orderViewStackRight();
        } else {
            if (this.isShowing >= this.dataList.size()) {
                this.isShowing -= this.dataList.size();
            }
            if (this.isShowing + 1 < this.dataList.size()) {
                this.isShowing++;
            } else {
                this.isShowing = (this.isShowing + 1) - this.dataList.size();
            }
            CardItemView cardItemView = this.viewList.get(0);
            int i2 = this.childWith;
            int i3 = this.leftRightSpace;
            int i4 = this.marginLeftRight;
            int i5 = this.itemMarginTop;
            cardItemView.layout((i2 * 2) + (i3 * 3) + i4, i5, (i2 * 3) + (i3 * 3) + i4, cardItemView.getHeight() + i5);
            boolean z = this.topLayout.getLeft() != 0;
            this.topLayout.layout(0, 0, getWidth(), getHeight());
            this.topLayout.bringToFront();
            int size = this.isShowing + (this.viewList.size() / 2);
            if (size < this.dataList.size()) {
                List<CardDataItem> list = this.dataList;
                cardItemView.fillData(list.get(size % list.size()));
            } else {
                List<CardDataItem> list2 = this.dataList;
                cardItemView.fillData(list2.get((size - list2.size()) % this.dataList.size()));
            }
            this.viewList.remove(cardItemView);
            this.viewList.add(cardItemView);
            this.releasedViewList.remove(0);
            if (z) {
                resizeLayout();
            }
            CardSwitchListener cardSwitchListener = this.cardSwitchListener;
            if (cardSwitchListener != null) {
                cardSwitchListener.onViewScroll(-1.0d);
                this.cardSwitchListener.onShow(this.isShowing);
            }
            this.mHander.removeMessages(1001);
            this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
        }
    }

    private void processLinkageView(View view, boolean z, int i2) {
        int left = view.getLeft();
        view.getTop();
        float f2 = (left - this.initCenterViewX) / this.MAX_SLIDE_DISTANCE_LINKAGE;
        if (f2 > 1.0f) {
            f2 = 1.0f;
        } else if (f2 < -1.0f) {
            f2 = -1.0f;
        }
        float abs = 1.0f - (Math.abs(f2) * SCALE_STEP);
        float f3 = 1.0f - ((f2 < 0.0f ? 1.0f : 1.0f - f2) * SCALE_STEP);
        float f4 = 1.0f - ((f2 < 0.0f ? f2 + 1.0f : 1.0f) * SCALE_STEP);
        int size = this.viewList.size() / 2;
        for (int i3 = 0; i3 < size - 1; i3++) {
            ajustLinkageViewItemPosition(i3 - size, z, i2);
        }
        ajustLinkageViewItem(f3, -1, z, i2);
        ajustLinkageViewItem(abs, 0, z, i2);
        ajustLinkageViewItem(f4, 1, z, i2);
        for (int i4 = size + 2; i4 < this.viewList.size(); i4++) {
            ajustLinkageViewItemPosition(i4 - size, z, i2);
        }
    }

    private void vanishOnBtnClick(int i2) {
        int i3;
        CardSwitchListener cardSwitchListener;
        CardItemView cardItemView = this.viewList.get(0);
        if (cardItemView.getVisibility() != 0 || this.releasedViewList.contains(cardItemView)) {
            return;
        }
        if (i2 == 0) {
            i3 = (-this.childWith) - 100;
        } else {
            i3 = i2 == 1 ? this.allWidth + 100 : 0;
        }
        if (i3 != 0) {
            Status status = new Status();
            status.left = false;
            status.v = cardItemView;
            this.releasedViewList.add(status);
            if (this.mDragHelper.smoothSlideViewTo(cardItemView, i3, this.initCenterViewY + (this.allHeight / 2))) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }
        if (i2 < 0 || (cardSwitchListener = this.cardSwitchListener) == null) {
            return;
        }
        cardSwitchListener.onCardVanish(this.isShowing, i2);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        } else if (this.mDragHelper.getViewDragState() == 0) {
            orderViewStackleft();
            this.btnLock = false;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            this.downPoint.x = (int) motionEvent.getX();
            this.downPoint.y = (int) motionEvent.getY();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void enableflip(boolean z) {
        if (!z) {
            this.shouldflipper = false;
            this.mHander.removeMessages(1001);
            return;
        }
        this.shouldflipper = true;
        this.mHander.removeMessages(1001);
        this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
    }

    public void fillData(List<CardDataItem> list, int i2) {
        this.dataList = list;
        this.index = i2;
    }

    public boolean hasInit() {
        if (this.viewList.size() > 0) {
            return true;
        }
        return this.layouted;
    }

    public void inflatelayout(int i2) {
        removeAllViews();
        List<CardDataItem> list = this.dataList;
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            addView(new CardItemView(getContext(), 3), new ViewGroup.LayoutParams((getWidth() - (this.leftRightSpace * 2)) - (this.marginLeftRight * 2), getHeight()));
        }
        measureChildren((this.allWidth - (this.leftRightSpace * 2)) - (this.marginLeftRight * 2), this.allHeight);
        this.viewList.clear();
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (i4 == childCount - 1) {
                this.topLayout = childAt;
                ((CardItemView) childAt).setParentView(this);
                this.topLayout.setOnClickListener(this.btnListener);
            } else {
                CardItemView cardItemView = (CardItemView) childAt;
                cardItemView.setParentView(this);
                cardItemView.setTag(Integer.valueOf(i4 + 1));
                this.viewList.add(cardItemView);
            }
        }
        this.isShowing = this.index;
        int size = this.viewList.size();
        int i5 = size / 2;
        this.dataList.size();
        for (int i6 = 0; i6 < size; i6++) {
            int abs = Math.abs((i6 - i5) + this.isShowing) % this.dataList.size();
            CardItemView cardItemView2 = this.viewList.get(i6);
            cardItemView2.fillData(this.dataList.get(abs));
            cardItemView2.setVisibility(0);
        }
        CardSwitchListener cardSwitchListener = this.cardSwitchListener;
        if (cardSwitchListener != null) {
            cardSwitchListener.onShow(0);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean shouldInterceptTouchEvent = this.mDragHelper.shouldInterceptTouchEvent(motionEvent);
        boolean onTouchEvent = this.moveDetector.onTouchEvent(motionEvent);
        if (motionEvent.getActionMasked() == 0) {
            if (this.mDragHelper.getViewDragState() == 2) {
                this.mDragHelper.abort();
            }
            this.mHander.removeMessages(1001);
            orderViewStackleft();
            this.mDragHelper.processTouchEvent(motionEvent);
        }
        if (shouldInterceptTouchEvent) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return shouldInterceptTouchEvent && onTouchEvent;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.viewList.size() == 0) {
            inflatelayout(6);
            this.layouted = true;
        }
        resizeLayout();
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        measureChildren(i2, i3);
        setMeasuredDimension(ViewGroup.resolveSizeAndState(View.MeasureSpec.getSize(i2), i2, 0), ViewGroup.resolveSizeAndState(View.MeasureSpec.getSize(i3), i3, 0));
        this.allWidth = getMeasuredWidth();
        this.allHeight = getMeasuredHeight();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            this.mDragHelper.processTouchEvent(motionEvent);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onViewPosChangedRight(android.view.View r4, int r5) {
        /*
            r3 = this;
            java.util.List<com.jingdong.jdreact.plugin.banner.CycleViewPager$Status> r0 = r3.releasedViewList
            int r0 = r0.size()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L30
            int r0 = r3.position
            int r0 = r0 + r5
            r3.position = r0
            if (r0 >= 0) goto L1f
            java.util.List<com.jingdong.jdreact.plugin.banner.CycleViewPager$Status> r5 = r3.releasedViewList
            java.lang.Object r5 = r5.get(r2)
            com.jingdong.jdreact.plugin.banner.CycleViewPager$Status r5 = (com.jingdong.jdreact.plugin.banner.CycleViewPager.Status) r5
            boolean r5 = r5.left
            if (r5 != 0) goto L1f
        L1d:
            r5 = 1
            goto L33
        L1f:
            int r5 = r3.position
            if (r5 <= 0) goto L32
            java.util.List<com.jingdong.jdreact.plugin.banner.CycleViewPager$Status> r5 = r3.releasedViewList
            java.lang.Object r5 = r5.get(r2)
            com.jingdong.jdreact.plugin.banner.CycleViewPager$Status r5 = (com.jingdong.jdreact.plugin.banner.CycleViewPager.Status) r5
            boolean r5 = r5.left
            if (r5 == 0) goto L32
            goto L1d
        L30:
            r3.position = r5
        L32:
            r5 = 0
        L33:
            if (r5 != 0) goto L5a
            int r5 = r4.getLeft()
            if (r5 >= 0) goto L3c
            r1 = 0
        L3c:
            int r5 = r3.position
            r3.processLinkageView(r4, r1, r5)
            int r5 = r3.childWith
            int r0 = r3.leftRightSpace
            int r5 = r5 + r0
            double r0 = (double) r5
            int r4 = r4.getLeft()
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            java.lang.Double.isNaN(r0)
            double r4 = r4 / r0
            com.jingdong.jdreact.plugin.banner.ui.CardSwitchListener r0 = r3.cardSwitchListener
            r0.onViewScroll(r4)
            r3.position = r2
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.banner.CycleViewPager.onViewPosChangedRight(android.view.View, int):void");
    }

    public void reLayout() {
        this.viewList.clear();
        inflatelayout(6);
        resizeLayout();
    }

    public void resizeLayout() {
        int size = this.viewList.size();
        if (size % 2 != 0) {
            int i2 = size / 2;
            int i3 = 0;
            int i4 = 0;
            while (i3 < size) {
                CardItemView cardItemView = this.viewList.get(i3);
                int measuredHeight = cardItemView.getMeasuredHeight();
                int width = (getWidth() - cardItemView.getMeasuredWidth()) / 2;
                int i5 = i3 - i2;
                int width2 = getWidth();
                int i6 = this.marginLeftRight;
                int i7 = this.leftRightSpace;
                int i8 = width2 - ((i6 + i7) * 2);
                int i9 = ((i7 + i8) * i5) + i6 + i7;
                ViewGroup.LayoutParams layoutParams = cardItemView.getLayoutParams();
                layoutParams.width = i8;
                cardItemView.setLayoutParams(layoutParams);
                cardItemView.layout(i9, this.itemMarginTop, (getWidth() + i9) - ((this.marginLeftRight + this.leftRightSpace) * 2), this.itemMarginTop + measuredHeight);
                if (i5 <= 1) {
                }
                i3++;
                i4 = measuredHeight;
            }
            this.topLayout.layout(0, this.itemMarginTop, getWidth(), this.itemMarginTop + i4);
            this.initCenterViewX = this.topLayout.getLeft();
            this.initCenterViewY = this.topLayout.getTop();
            this.childWith = getWidth() - ((this.marginLeftRight + this.leftRightSpace) * 2);
            this.mHander.removeMessages(1001);
            this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
            return;
        }
        throw new RuntimeException("count error");
    }

    public void setCardSwitchListener(CardSwitchListener cardSwitchListener) {
        this.cardSwitchListener = cardSwitchListener;
    }

    public void setTimeInteval(int i2) {
        this.mTimeInteval = i2;
    }

    public void showfilpper() {
        if (this.topLayout == null) {
            return;
        }
        Status status = new Status();
        status.left = true;
        status.v = this.topLayout;
        this.releasedViewList.add(status);
        if (this.mDragHelper.smoothSlideViewTo(this.topLayout, -getWidth(), this.itemMarginTop)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public CycleViewPager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CycleViewPager(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.viewList = new ArrayList();
        this.releasedViewList = new ArrayList();
        this.initCenterViewX = 0;
        this.initCenterViewY = 0;
        this.allWidth = 0;
        this.allHeight = 0;
        this.childWith = 0;
        this.MAX_SLIDE_DISTANCE_LINKAGE = 300;
        this.marginLeftRight = 60;
        this.leftRightSpace = 40;
        this.itemMarginTop = 0;
        this.bottomMarginTop = 40;
        this.yOffsetStep = 40;
        this.xOffsetStep = 50;
        this.mTouchSlop = 5;
        this.isShowing = 0;
        this.btnLock = false;
        this.downPoint = new Point();
        this.mHander = new Handler() { // from class: com.jingdong.jdreact.plugin.banner.CycleViewPager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1001) {
                    if (CycleViewPager.this.shouldflipper) {
                        CycleViewPager.this.showfilpper();
                        return;
                    }
                    return;
                }
                super.handleMessage(message);
            }
        };
        this.btnListener = new View.OnClickListener() { // from class: com.jingdong.jdreact.plugin.banner.CycleViewPager.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CycleViewPager.this.cardSwitchListener != null) {
                    CycleViewPager.this.cardSwitchListener.onItemClick(view, CycleViewPager.this.isShowing);
                }
            }
        };
        this.position = 0;
        this.layouted = false;
        this.index = 0;
        this.mTimeInteval = 3000;
        this.shouldflipper = true;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.card);
        this.itemMarginTop = (int) obtainStyledAttributes.getDimension(R.styleable.card_itemMarginTop, this.itemMarginTop);
        this.bottomMarginTop = (int) obtainStyledAttributes.getDimension(R.styleable.card_bottomMarginTop, this.bottomMarginTop);
        this.yOffsetStep = (int) obtainStyledAttributes.getDimension(R.styleable.card_yOffsetStep, this.yOffsetStep);
        this.xOffsetStep = (int) obtainStyledAttributes.getDimension(R.styleable.card_xOffsetStep, this.xOffsetStep);
        this.MAX_SLIDE_DISTANCE_LINKAGE = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card__left_right_step);
        this.marginLeftRight = 0;
        this.leftRightSpace = 0;
        ViewDragHelper create = ViewDragHelper.create(this, 10.0f, new DragHelperCallback());
        this.mDragHelper = create;
        create.setEdgeTrackingEnabled(8);
        obtainStyledAttributes.recycle();
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(context, new MoveDetector());
        this.moveDetector = gestureDetectorCompat;
        gestureDetectorCompat.setIsLongpressEnabled(false);
    }
}
