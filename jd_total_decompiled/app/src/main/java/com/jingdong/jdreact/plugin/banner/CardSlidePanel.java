package com.jingdong.jdreact.plugin.banner;

import android.annotation.SuppressLint;
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

@SuppressLint({"HandlerLeak", "NewApi", "ClickableViewAccessibility"})
/* loaded from: classes13.dex */
public class CardSlidePanel extends ViewGroup {
    private static final int AUTO_FLIPPER_MESSAGE = 1001;
    private static final int MAX_SLIDE_DISTANCE_LINKAGE = 500;
    private static final float SCALE_STEP = 0.1f;
    public static final int VANISH_TYPE_LEFT = 0;
    public static final int VANISH_TYPE_RIGHT = 1;
    private static final int X_DISTANCE_THRESHOLD = 30;
    private static final int X_VEL_THRESHOLD = 800;
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
    private final ViewDragHelper mDragHelper;
    private Handler mHander;
    int mMaxnum;
    private int mTimeInteval;
    private int mTouchSlop;
    private GestureDetectorCompat moveDetector;
    int position;
    private List<Status> releasedViewList;
    private View rightBtn;
    private int rigthpadding;
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
            return CardSlidePanel.this.itemMarginTop;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return 256;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            CardSlidePanel.this.onViewPosChangedRight(view, i4);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            CardSlidePanel.this.animToSide(view, (int) f2, (int) f3);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            if (CardSlidePanel.this.dataList == null || CardSlidePanel.this.dataList.size() == 0 || view.getVisibility() != 0 || view.getScaleX() <= 0.9f || CardSlidePanel.this.btnLock || CardSlidePanel.this.viewList.indexOf(view) > 1) {
                return false;
            }
            CardSlidePanel.this.getParent().requestDisallowInterceptTouchEvent(true);
            return true;
        }
    }

    /* loaded from: classes13.dex */
    class MoveDetector extends GestureDetector.SimpleOnGestureListener {
        MoveDetector() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            return Math.abs(f3) + Math.abs(f2) > ((float) CardSlidePanel.this.mTouchSlop);
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

    public CardSlidePanel(Context context) {
        this(context, null);
    }

    private void ajustLinkageViewItem(int i2, float f2, int i3, boolean z) {
        int i4 = this.xOffsetStep;
        int i5 = i4 * i3;
        float f3 = 1.0f - (i3 * SCALE_STEP);
        int i6 = i3 - 1;
        int i7 = i4 * i6;
        float f4 = 1.0f - (i6 * SCALE_STEP);
        if (z) {
            int i8 = i3 + 1;
            i7 = i4 * i8;
            f4 = 1.0f - (i8 * SCALE_STEP);
        }
        float f5 = f3 + ((f4 - f3) * f2);
        CardItemView cardItemView = this.viewList.get(i2 + i3);
        cardItemView.offsetLeftAndRight((((int) (i5 + ((i7 - i5) * f2))) - cardItemView.getLeft()) + this.initCenterViewX);
        cardItemView.setScaleX(f5);
        cardItemView.setScaleY(f5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void animToSide(View view, int i2, int i3) {
        int i4;
        boolean z;
        int i5;
        CardSwitchListener cardSwitchListener;
        int i6 = this.initCenterViewX;
        int i7 = this.initCenterViewY;
        int left = view.getLeft() - this.initCenterViewX;
        int top = view.getTop() - this.initCenterViewY;
        if (i2 > 800 && Math.abs(i3) < i2 * 3.0f) {
            i6 = this.allWidth;
            i7 = ((i3 * (this.childWith + view.getLeft())) / i2) + view.getTop();
        } else {
            if (i2 < -800) {
                int i8 = -i2;
                if (Math.abs(i3) < i8 * 3.0f) {
                    int i9 = this.childWith;
                    int top2 = view.getTop() + ((i3 * (i9 + view.getLeft())) / i8);
                    i6 = -i9;
                    z = true;
                    i7 = top2;
                    i4 = 0;
                    i5 = this.allHeight;
                    if (i7 <= i5 && i7 < (-i5) / 2) {
                        int i10 = (-i5) / 2;
                    }
                    if (i6 != this.initCenterViewX) {
                        onViewPosChangedRight(view, 0 - view.getLeft());
                        view.offsetLeftAndRight(0 - view.getLeft());
                        return;
                    }
                    Status status = new Status();
                    status.left = z;
                    status.v = this.viewList.get(1);
                    this.releasedViewList.add(status);
                    CardItemView cardItemView = this.viewList.get(0);
                    if (cardItemView.getRight() >= 0) {
                        if (z) {
                            this.mDragHelper.smoothSlideViewTo(cardItemView, i6, this.itemMarginTop);
                        } else if (this.mDragHelper.smoothSlideViewTo(view, getWidth(), this.itemMarginTop)) {
                            ViewCompat.postInvalidateOnAnimation(this);
                        }
                    }
                    if (z && this.mDragHelper.smoothSlideViewTo(this.viewList.get(1), i6, this.itemMarginTop)) {
                        ViewCompat.postInvalidateOnAnimation(this);
                    }
                    if (i4 < 0 || (cardSwitchListener = this.cardSwitchListener) == null) {
                        return;
                    }
                    cardSwitchListener.onCardVanish(this.isShowing, i4);
                    return;
                }
            }
            if (left > 30 && Math.abs(top) < left * 3.0f) {
                i6 = this.allWidth;
                i7 = ((top * (this.childWith + this.initCenterViewX)) / left) + this.initCenterViewY;
            } else {
                if (left < -30) {
                    int i11 = -left;
                    if (Math.abs(top) < i11 * 3.0f) {
                        int i12 = this.childWith;
                        i6 = -i12;
                        i7 = ((top * (i12 + this.initCenterViewX)) / i11) + this.initCenterViewY;
                        i4 = 0;
                        z = true;
                        i5 = this.allHeight;
                        if (i7 <= i5) {
                            int i102 = (-i5) / 2;
                        }
                        if (i6 != this.initCenterViewX) {
                        }
                    }
                }
                i4 = -1;
                z = false;
                i5 = this.allHeight;
                if (i7 <= i5) {
                }
                if (i6 != this.initCenterViewX) {
                }
            }
        }
        i4 = 1;
        z = false;
        i5 = this.allHeight;
        if (i7 <= i5) {
        }
        if (i6 != this.initCenterViewX) {
        }
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
        int i3 = this.itemMarginTop;
        cardItemView.layout(-cardItemView.getWidth(), i3, 0, cardItemView.getHeight() + i3);
        cardItemView.setScaleX(1.0f);
        cardItemView.setScaleY(1.0f);
        for (int size = this.viewList.size() - 1; size > 0; size--) {
            CardItemView cardItemView2 = this.viewList.get(size);
            cardItemView2.setAlpha(1.0f);
            cardItemView2.bringToFront();
        }
        this.viewList.get(0).bringToFront();
        cardItemView.bringToFront();
        this.topLayout.bringToFront();
        int i4 = this.isShowing - 1;
        if (i4 < 0) {
            i4 += this.dataList.size();
        }
        cardItemView.fillData(this.dataList.get(i4));
        this.viewList.remove(cardItemView);
        this.viewList.add(0, cardItemView);
        this.releasedViewList.remove(0);
        if (this.topLayout != null) {
            this.viewList.get(1).getBottom();
            View view = this.topLayout;
            view.layout(0, this.itemMarginTop, view.getWidth(), this.itemMarginTop + this.topLayout.getHeight());
        }
        CardSwitchListener cardSwitchListener = this.cardSwitchListener;
        if (cardSwitchListener != null) {
            cardSwitchListener.onShow(this.isShowing);
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
            CardItemView cardItemView = this.viewList.get(1);
            cardItemView.offsetLeftAndRight((-cardItemView.getLeft()) - getWidth());
            for (int size = this.viewList.size() - 1; size > 0; size--) {
                CardItemView cardItemView2 = this.viewList.get(size);
                cardItemView2.setAlpha(1.0f);
                cardItemView2.bringToFront();
            }
            this.topLayout.bringToFront();
            CardItemView cardItemView3 = this.viewList.get(0);
            int i2 = this.mMaxnum - 3;
            if (i2 > this.dataList.size()) {
                i2 = this.dataList.size() - 1;
            }
            int i3 = this.isShowing + i2;
            if (i3 < this.dataList.size()) {
                cardItemView3.fillData(this.dataList.get(i3));
            } else {
                List<CardDataItem> list = this.dataList;
                cardItemView3.fillData(list.get(i3 - list.size()));
            }
            int i4 = (this.mMaxnum - 3) - 1;
            if (i4 > this.dataList.size()) {
                i4 = this.dataList.size() - 1;
            }
            cardItemView3.offsetLeftAndRight((this.xOffsetStep * i4) - cardItemView3.getLeft());
            float f2 = 1.0f - (i4 * SCALE_STEP);
            cardItemView3.setScaleY(f2);
            cardItemView3.setScaleX(f2);
            int i5 = this.isShowing - 1;
            if (i5 < 0) {
                i5 += this.dataList.size();
            }
            this.dataList.get(i5);
            this.viewList.remove(cardItemView3);
            this.viewList.add(cardItemView3);
            this.releasedViewList.remove(0);
            CardSwitchListener cardSwitchListener = this.cardSwitchListener;
            if (cardSwitchListener != null) {
                cardSwitchListener.onShow(this.isShowing);
            }
            if (this.topLayout != null) {
                this.viewList.get(1).getBottom();
                View view = this.topLayout;
                view.layout(0, this.itemMarginTop, view.getWidth(), this.itemMarginTop + this.topLayout.getHeight());
            }
            this.mHander.removeMessages(1001);
            this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
        }
    }

    private void processLinkageView(View view, boolean z) {
        float abs = (Math.abs(view.getTop() - this.initCenterViewY) + Math.abs(view.getLeft() - this.initCenterViewX)) / 500.0f;
        int i2 = 0;
        float f2 = abs;
        while (i2 < this.mMaxnum - 4) {
            f2 = abs - (i2 * SCALE_STEP);
            if (f2 < 0.0f) {
                f2 = 0.0f;
            } else if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            i2++;
            ajustLinkageViewItem(1, f2, i2, z);
        }
        List<CardItemView> list = this.viewList;
        CardItemView cardItemView = list.get(list.size() - 1);
        if (this.viewList.size() - 1 != this.dataList.size()) {
            cardItemView.setAlpha(f2);
        }
    }

    private void processLinkageViewRight(View view, boolean z) {
        float abs = ((Math.abs(view.getTop() - this.initCenterViewY) + Math.abs(view.getLeft() - this.initCenterViewX)) / 500.0f) - SCALE_STEP;
        float f2 = abs;
        for (int i2 = 0; i2 < this.mMaxnum - 4; i2++) {
            f2 = abs - (i2 * SCALE_STEP);
            if (f2 < 0.0f) {
                f2 = 0.0f;
            } else if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            ajustLinkageViewItem(1, f2, i2, z);
        }
        List<CardItemView> list = this.viewList;
        list.get(list.size() - 1).setAlpha(f2);
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

    public void fillData(List<CardDataItem> list) {
        fillData(list, 6, 0);
    }

    public boolean hasInit() {
        if (this.viewList.size() > 0) {
            return true;
        }
        return this.layouted;
    }

    public void inflatelayout(int i2) {
        List<CardDataItem> list = this.dataList;
        if (list == null || list.size() == 0) {
            return;
        }
        removeAllViews();
        for (int i3 = 0; i3 < i2; i3++) {
            CardItemView cardItemView = new CardItemView(getContext());
            addView(cardItemView, new ViewGroup.LayoutParams(getWidth(), getHeight()));
            cardItemView.setPadding(0, 0, this.rigthpadding, 0);
        }
        measureChildren(this.allWidth, this.allHeight);
        this.viewList.clear();
        int childCount = getChildCount();
        View childAt = getChildAt(childCount - 1);
        this.topLayout = childAt;
        childAt.setOnClickListener(this.btnListener);
        for (int i4 = childCount - 2; i4 >= 0; i4--) {
            CardItemView cardItemView2 = (CardItemView) getChildAt(i4);
            cardItemView2.setParentView(this);
            cardItemView2.setTag(Integer.valueOf(i4 + 1));
            this.viewList.add(cardItemView2);
        }
        List<CardItemView> list2 = this.viewList;
        list2.get(list2.size() - 1).setAlpha(0.0f);
        int size = this.viewList.size();
        CardItemView cardItemView3 = this.viewList.get(0);
        List<CardDataItem> list3 = this.dataList;
        cardItemView3.fillData(list3.get(list3.size() - 1));
        cardItemView3.setVisibility(0);
        for (int i5 = 1; i5 < size; i5++) {
            CardItemView cardItemView4 = this.viewList.get(i5);
            int i6 = this.index + i5;
            if (i6 > this.dataList.size()) {
                List<CardDataItem> list4 = this.dataList;
                cardItemView4.fillData(list4.get((i6 - list4.size()) - 1));
            } else {
                cardItemView4.fillData(this.dataList.get(i6 - 1));
            }
            cardItemView4.setVisibility(0);
        }
        int i7 = this.index;
        this.isShowing = i7;
        CardSwitchListener cardSwitchListener = this.cardSwitchListener;
        if (cardSwitchListener != null) {
            cardSwitchListener.onShow(i7);
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
            this.mDragHelper.abort();
            this.mHander.removeMessages(1001);
            orderViewStackleft();
            this.mDragHelper.processTouchEvent(motionEvent);
        }
        return shouldInterceptTouchEvent && onTouchEvent;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.viewList.size() == 0) {
            inflatelayout(this.mMaxnum);
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

    /* JADX WARN: Removed duplicated region for block: B:17:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onViewPosChangedRight(View view, int i2) {
        boolean z;
        boolean z2;
        if (this.viewList.indexOf(view) + 2 > this.viewList.size()) {
            return;
        }
        CardItemView cardItemView = this.viewList.get(0);
        CardItemView cardItemView2 = this.viewList.get(1);
        if (cardItemView.getRight() > 0) {
            if (this.releasedViewList.size() != 0) {
                int i3 = this.position + i2;
                this.position = i3;
                if (i3 < 0 && !this.releasedViewList.get(0).left) {
                    z2 = true;
                    if (!z2) {
                        cardItemView.offsetLeftAndRight(this.position);
                        this.position = 0;
                    }
                }
            } else {
                this.position = i2;
            }
            z2 = false;
            if (!z2) {
            }
        } else {
            cardItemView.layout(cardItemView.getLeft() + i2, this.itemMarginTop, cardItemView.getLeft() + i2 + getWidth(), this.itemMarginTop + cardItemView.getHeight());
        }
        if (view.getLeft() > 0) {
            processLinkageViewRight(view, view.getLeft() >= 0);
            return;
        }
        if (this.releasedViewList.size() != 0) {
            int i4 = this.position + i2;
            this.position = i4;
            if (i4 > 0 && this.releasedViewList.get(0).left) {
                z = true;
                if (!z) {
                    cardItemView2.layout(cardItemView2.getLeft() + this.position, this.itemMarginTop, cardItemView2.getLeft() + this.position + getWidth(), this.itemMarginTop + cardItemView2.getHeight());
                    cardItemView2.setAlpha(1.0f - ((Math.abs(cardItemView2.getLeft()) * 1.2f) / cardItemView2.getWidth()));
                    this.position = 0;
                }
                processLinkageView(view, view.getLeft() >= 0);
            }
        } else {
            this.position = i2;
        }
        z = false;
        if (!z) {
        }
        processLinkageView(view, view.getLeft() >= 0);
    }

    public void reLayout() {
        this.viewList.clear();
        inflatelayout(this.mMaxnum);
        resizeLayout();
    }

    public void resizeLayout() {
        int size = this.viewList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            CardItemView cardItemView = this.viewList.get(i2);
            int height = getHeight();
            if (i2 == 0) {
                cardItemView.layout(0, this.itemMarginTop, getWidth() + 0, height);
                cardItemView.offsetLeftAndRight(-getWidth());
            } else {
                int i4 = i2 - 1;
                cardItemView.layout(0, this.itemMarginTop, getWidth() + 0, height);
                int i5 = this.xOffsetStep;
                int i6 = i5 * i4;
                float f2 = 1.0f - (i4 * SCALE_STEP);
                int i7 = this.mMaxnum;
                if (i4 > (i7 - 1) - 3) {
                    i6 = i5 * ((i7 - 1) - 3);
                    f2 = 1.0f - (((float) ((i7 - 1) - 3)) * SCALE_STEP);
                }
                cardItemView.offsetLeftAndRight(i6);
                cardItemView.setScaleX(f2);
                cardItemView.setScaleY(f2);
            }
            i2++;
            i3 = height;
        }
        if (this.topLayout != null) {
            this.viewList.get(1).getBottom();
            this.topLayout.layout(0, this.itemMarginTop, getWidth(), this.itemMarginTop + i3);
        }
        this.initCenterViewX = this.viewList.get(1).getLeft();
        this.initCenterViewY = this.viewList.get(1).getTop();
        this.childWith = this.viewList.get(1).getMeasuredWidth();
        this.mHander.removeMessages(1001);
        this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
    }

    public void setCardSwitchListener(CardSwitchListener cardSwitchListener) {
        this.cardSwitchListener = cardSwitchListener;
    }

    public void setTimeInteval(int i2) {
        this.mTimeInteval = i2;
    }

    public void showfilpper() {
        Status status = new Status();
        status.left = true;
        List<CardItemView> list = this.viewList;
        if (list == null || list.size() < 2) {
            return;
        }
        status.v = this.viewList.get(1);
        this.releasedViewList.add(status);
        if (this.mDragHelper.smoothSlideViewTo(this.viewList.get(1), -getWidth(), this.itemMarginTop)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public CardSlidePanel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void fillData(List<CardDataItem> list, int i2, int i3) {
        this.dataList = list;
        this.mMaxnum = i2;
        if (i2 > list.size()) {
            this.mMaxnum = list.size();
        }
        int i4 = this.mMaxnum;
        this.rigthpadding *= i4;
        this.mMaxnum = i4 + 3;
        this.index = i3;
    }

    public CardSlidePanel(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.viewList = new ArrayList();
        this.releasedViewList = new ArrayList();
        this.initCenterViewX = 0;
        this.initCenterViewY = 0;
        this.allWidth = 0;
        this.allHeight = 0;
        this.childWith = 0;
        this.itemMarginTop = 0;
        this.bottomMarginTop = 40;
        this.yOffsetStep = 40;
        this.xOffsetStep = 50;
        this.mTouchSlop = 5;
        this.rigthpadding = 0;
        this.isShowing = 0;
        this.btnLock = false;
        this.downPoint = new Point();
        this.mTimeInteval = 3000;
        this.mHander = new Handler() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanel.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1001) {
                    if (CardSlidePanel.this.shouldflipper) {
                        CardSlidePanel.this.showfilpper();
                        return;
                    }
                    return;
                }
                super.handleMessage(message);
            }
        };
        this.btnListener = new View.OnClickListener() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanel.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CardSlidePanel.this.cardSwitchListener != null) {
                    CardSlidePanel.this.cardSwitchListener.onItemClick(view, CardSlidePanel.this.isShowing);
                }
            }
        };
        this.position = 0;
        this.layouted = false;
        this.index = 0;
        this.mMaxnum = 4;
        this.shouldflipper = true;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.card);
        this.itemMarginTop = (int) obtainStyledAttributes.getDimension(R.styleable.card_itemMarginTop, this.itemMarginTop);
        this.bottomMarginTop = (int) obtainStyledAttributes.getDimension(R.styleable.card_bottomMarginTop, this.bottomMarginTop);
        this.xOffsetStep = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card_offset);
        this.rigthpadding = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card_paddingright);
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
