package com.jingdong.jdreact.plugin.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import com.jingdong.jdreact.plugin.utils.FrescoUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes13.dex */
public class CardSlidePanelStyle2 extends ViewGroup {
    private static final int AUTO_FLIPPER_MESSAGE = 1001;
    private static final float SCALE_STEP = 0.254f;
    public static final int VANISH_TYPE_LEFT = 0;
    public static final int VANISH_TYPE_RIGHT = 1;
    private static final int X_VEL_THRESHOLD = 800;
    private int MAX_SLIDE_DISTANCE_LINKAGE;
    private int X_DISTANCE_THRESHOLD;
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
    private int mActionDownPosition;
    private Drawable mBgIconDrawable;
    private final ViewDragHelper mDragHelper;
    private Handler mHander;
    private int mTimeInteval;
    private int mTouchSlop;
    private int marginLeftRight;
    private GestureDetectorCompat moveDetector;
    int position;
    private List<Status> releasedViewList;
    private View rightBtn;
    private boolean scaleXY;
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
            return CardSlidePanelStyle2.this.itemMarginTop;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return 256;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            CardSlidePanelStyle2.this.onViewPosChangedRight(view, i4);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            CardSlidePanelStyle2.this.animToSide(view, (int) f2, (int) f3);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            if (CardSlidePanelStyle2.this.dataList == null || CardSlidePanelStyle2.this.dataList.size() == 0 || view.getVisibility() != 0 || view.getScaleX() <= 0.746f || CardSlidePanelStyle2.this.btnLock) {
                return false;
            }
            CardSlidePanelStyle2.this.viewList.indexOf(view);
            return true;
        }
    }

    /* loaded from: classes13.dex */
    class MoveDetector extends GestureDetector.SimpleOnGestureListener {
        MoveDetector() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            return Math.abs(f3) + Math.abs(f2) > ((float) CardSlidePanelStyle2.this.mTouchSlop);
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

    public CardSlidePanelStyle2(Context context) {
        this(context, null);
    }

    private void ajustLinkageViewItem(float f2, int i2, boolean z, int i3) {
        List<CardItemView> list = this.viewList;
        CardItemView cardItemView = list.get((list.size() / 2) + i2);
        cardItemView.layout(cardItemView.getLeft() + i3, cardItemView.getTop(), cardItemView.getWidth() + cardItemView.getLeft() + i3, cardItemView.getHeight() + this.itemMarginTop);
        cardItemView.setScaleY(f2);
        if (this.scaleXY) {
            cardItemView.setScaleX(f2);
        }
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
            if (left > this.X_DISTANCE_THRESHOLD && Math.abs(top) < left * 3.0f) {
                int i11 = this.childWith;
                i5 = i11 + this.leftRightSpace;
                i6 = ((top * (i11 + this.initCenterViewX)) / left) + this.initCenterViewY;
                i4 = 1;
            } else {
                if (left < (-this.X_DISTANCE_THRESHOLD)) {
                    int i12 = -left;
                    if (Math.abs(top) < i12 * 3.0f) {
                        int i13 = this.childWith;
                        i5 = -(this.leftRightSpace + i13);
                        i6 = ((top * (i13 + this.initCenterViewX)) / i12) + this.initCenterViewY;
                        i4 = 0;
                    }
                }
                i4 = -1;
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

    private void setItemBackground(final CardItemView cardItemView) {
        if (cardItemView == null) {
            return;
        }
        Drawable drawable = this.mBgIconDrawable;
        if (drawable != null) {
            cardItemView.setBackgroundDrawable(drawable);
        } else {
            FrescoUtil.loadImage(getContext(), "https://img30.360buyimg.com/njmobilecms/jfs/t1/22451/39/4883/24155/5c37084aEae94a999/501e454ef4d4461f.png", new FrescoUtil.ImageLoadListener() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle2.3
                @Override // com.jingdong.jdreact.plugin.utils.FrescoUtil.ImageLoadListener
                public void onLoadFailed(String str, Throwable th) {
                }

                @Override // com.jingdong.jdreact.plugin.utils.FrescoUtil.ImageLoadListener
                public void onLoadSuccess(String str, Bitmap bitmap) {
                    CardSlidePanelStyle2.this.mBgIconDrawable = new BitmapDrawable(bitmap);
                    CardSlidePanelStyle2.this.post(new Runnable() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle2.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                            cardItemView.setBackgroundDrawable(CardSlidePanelStyle2.this.mBgIconDrawable);
                        }
                    });
                }
            });
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
        List<CardDataItem> list = this.dataList;
        if (list == null || list.size() == 0) {
            return;
        }
        removeAllViews();
        for (int i3 = 0; i3 < i2; i3++) {
            addView(new CardItemView(getContext(), 0), new ViewGroup.LayoutParams((getWidth() - (this.leftRightSpace * 2)) - (this.marginLeftRight * 2), getHeight()));
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
                setItemBackground(cardItemView);
                cardItemView.setParentView(this);
                cardItemView.setTag(Integer.valueOf(i4 + 1));
                this.viewList.add(cardItemView);
            }
        }
        int size = this.viewList.size();
        int i5 = size / 2;
        this.isShowing = this.index;
        for (int i6 = 0; i6 < size; i6++) {
            int i7 = (i6 - i5) + this.isShowing;
            if (this.dataList.size() < 3) {
                i7 = Math.abs(i7) % this.dataList.size();
            } else if (i7 < 0) {
                i7 += this.dataList.size();
            }
            CardItemView cardItemView2 = this.viewList.get(i6);
            cardItemView2.fillData(this.dataList.get(i7));
            cardItemView2.setVisibility(0);
        }
        CardSwitchListener cardSwitchListener = this.cardSwitchListener;
        if (cardSwitchListener != null) {
            cardSwitchListener.onShow(this.isShowing);
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
            this.mActionDownPosition = (int) motionEvent.getX();
            if (this.mDragHelper.getViewDragState() == 2) {
                this.mDragHelper.abort();
            }
            orderViewStackleft();
            this.mDragHelper.processTouchEvent(motionEvent);
        }
        if (shouldInterceptTouchEvent && onTouchEvent) {
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
    */
    public void onViewPosChangedRight(View view, int i2) {
        boolean z;
        if (this.releasedViewList.size() != 0) {
            int i3 = this.position + i2;
            this.position = i3;
            if ((i3 < 0 && !this.releasedViewList.get(0).left) || (this.position > 0 && this.releasedViewList.get(0).left)) {
                z = true;
                if (z) {
                    processLinkageView(view, view.getLeft() >= 0, this.position);
                    double d = this.childWith + this.leftRightSpace;
                    double left = view.getLeft();
                    Double.isNaN(left);
                    Double.isNaN(d);
                    this.cardSwitchListener.onViewScroll(left / d);
                    this.position = 0;
                    return;
                }
                return;
            }
        } else {
            this.position = i2;
        }
        z = false;
        if (z) {
        }
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
                if (i5 > 1 || i5 < -1) {
                    i5 = 1;
                }
                float abs = 1.0f - (Math.abs(i5) * SCALE_STEP);
                cardItemView.setScaleY(abs);
                if (this.scaleXY) {
                    cardItemView.setScaleX(abs);
                }
                i3++;
                i4 = measuredHeight;
            }
            this.topLayout.layout(0, this.itemMarginTop, getWidth(), this.itemMarginTop + i4);
            this.initCenterViewX = this.topLayout.getLeft();
            this.initCenterViewY = this.topLayout.getTop();
            this.childWith = getWidth() - ((this.marginLeftRight + this.leftRightSpace) * 2);
            return;
        }
        throw new RuntimeException("count error");
    }

    public void setCardSwitchListener(CardSwitchListener cardSwitchListener) {
        this.cardSwitchListener = cardSwitchListener;
    }

    public void setScaleXY(boolean z) {
        this.scaleXY = z;
    }

    public void setTimeInteval(int i2) {
        this.mTimeInteval = i2;
    }

    public void showfilpper() {
        List<CardItemView> list = this.viewList;
        if (list == null || list.size() < 2) {
            return;
        }
        Status status = new Status();
        status.left = true;
        status.v = this.topLayout;
        this.releasedViewList.add(status);
        if (this.mDragHelper.smoothSlideViewTo(this.topLayout, -(this.viewList.get(0).getWidth() + this.leftRightSpace), this.itemMarginTop)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void showfilpperleft() {
        if (this.viewList.size() <= 0) {
            return;
        }
        Status status = new Status();
        status.left = false;
        status.v = this.topLayout;
        this.releasedViewList.add(status);
        if (this.mDragHelper.smoothSlideViewTo(this.topLayout, this.viewList.get(0).getWidth() + this.leftRightSpace, this.itemMarginTop)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public CardSlidePanelStyle2(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CardSlidePanelStyle2(Context context, AttributeSet attributeSet, int i2) {
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
        this.itemMarginTop = 10;
        this.bottomMarginTop = 40;
        this.yOffsetStep = 40;
        this.xOffsetStep = 50;
        this.mTouchSlop = 5;
        this.X_DISTANCE_THRESHOLD = 80;
        this.isShowing = 0;
        this.btnLock = false;
        this.downPoint = new Point();
        this.mHander = new Handler() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle2.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1001) {
                    super.handleMessage(message);
                }
            }
        };
        this.btnListener = new View.OnClickListener() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle2.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CardSlidePanelStyle2.this.cardSwitchListener != null) {
                    if (CardSlidePanelStyle2.this.mActionDownPosition != 0) {
                        if (CardSlidePanelStyle2.this.mActionDownPosition >= CardSlidePanelStyle2.this.marginLeftRight || CardSlidePanelStyle2.this.mActionDownPosition < 0) {
                            if (CardSlidePanelStyle2.this.mActionDownPosition <= CardSlidePanelStyle2.this.getWidth() - CardSlidePanelStyle2.this.marginLeftRight || CardSlidePanelStyle2.this.mActionDownPosition < 0 || CardSlidePanelStyle2.this.mActionDownPosition >= CardSlidePanelStyle2.this.getWidth()) {
                                CardSlidePanelStyle2.this.cardSwitchListener.onItemClick(view, CardSlidePanelStyle2.this.isShowing);
                                return;
                            } else {
                                CardSlidePanelStyle2.this.showfilpper();
                                return;
                            }
                        }
                        CardSlidePanelStyle2.this.showfilpperleft();
                        return;
                    }
                    CardSlidePanelStyle2.this.cardSwitchListener.onItemClick(view, CardSlidePanelStyle2.this.isShowing);
                }
            }
        };
        this.position = 0;
        this.mActionDownPosition = 0;
        this.layouted = false;
        this.index = 0;
        this.mTimeInteval = 3000;
        this.scaleXY = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.card);
        this.itemMarginTop = (int) obtainStyledAttributes.getDimension(R.styleable.card_itemMarginTop, this.itemMarginTop);
        this.bottomMarginTop = (int) obtainStyledAttributes.getDimension(R.styleable.card_bottomMarginTop, this.bottomMarginTop);
        this.yOffsetStep = (int) obtainStyledAttributes.getDimension(R.styleable.card_yOffsetStep, this.yOffsetStep);
        this.xOffsetStep = (int) obtainStyledAttributes.getDimension(R.styleable.card_xOffsetStep, this.xOffsetStep);
        this.MAX_SLIDE_DISTANCE_LINKAGE = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card__left_right_step);
        this.marginLeftRight = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card_left_right_padding);
        this.leftRightSpace = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card__left_right_space);
        this.X_DISTANCE_THRESHOLD = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card_step_length);
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
