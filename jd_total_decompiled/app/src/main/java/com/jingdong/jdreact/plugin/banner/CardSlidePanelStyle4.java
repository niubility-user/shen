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
import com.facebook.react.views.view.ReactViewGroup;
import com.jingdong.jdreact.plugin.banner.JDCustomCardbannerItemViewManager;
import com.jingdong.jdreact.plugin.banner.ui.CardSwitchListener;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes13.dex */
public class CardSlidePanelStyle4 extends ReactViewGroup {
    public static final int AUTOFLIPPER_DEFAULT = 0;
    public static final int AUTOFLIPPER_OFF = 2;
    public static final int AUTOFLIPPER_ON = 1;
    private static final int AUTO_FLIPPER_MESSAGE = 1001;
    private static final int AUTO_REFRESH_MESSAGE = 1002;
    private static final float SCALE_STEP = 0.254f;
    public static String TYPE_BANNER_PAGER = "coverFlow";
    public static String TYPE_CYCLE_PAGER = "linear";
    public static final int VANISH_TYPE_LEFT = 0;
    public static final int VANISH_TYPE_RIGHT = 1;
    private static int X_DISTANCE_THRESHOLD = 30;
    private static final int X_VEL_THRESHOLD = 800;
    private int MAX_SLIDE_DISTANCE_LINKAGE;
    private int allHeight;
    private int allWidth;
    private int autoflipper;
    private int bottomMarginTop;
    View.OnClickListener btnListener;
    private boolean btnLock;
    private CardSwitchListener cardSwitchListener;
    int changed;
    private int childWith;
    private Point downPoint;
    private int index;
    private int initCenterViewX;
    private int initCenterViewY;
    private int isShowing;
    private int itemMarginTop;
    private int leftRightSpace;
    private int mActionDownPosition;
    private int mClickPosition;
    private final ViewDragHelper mDragHelper;
    private Handler mHander;
    private int mTimeInteval;
    private int mTouchSlop;
    private boolean mTouched;
    private int marginLeftRight;
    private String mode;
    private GestureDetectorCompat moveDetector;
    int position;
    private List<Status> releasedViewList;
    private boolean scaleXY;
    private boolean shouldflipper;
    private View topLayout;
    private List<JDCustomCardbannerItemViewManager.CardItemView3> viewList;
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
            return CardSlidePanelStyle4.this.itemMarginTop;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return 256;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            CardSlidePanelStyle4.this.onViewPosChangedRight(view, i4);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            CardSlidePanelStyle4.this.mTouched = false;
            CardSlidePanelStyle4.this.animToSide(view, (int) f2, (int) f3);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            if (CardSlidePanelStyle4.this.viewList == null || CardSlidePanelStyle4.this.viewList.size() <= 1 || view.getVisibility() != 0 || view.getScaleX() <= 0.746f || CardSlidePanelStyle4.this.btnLock) {
                return false;
            }
            CardSlidePanelStyle4.this.viewList.indexOf(view);
            return true;
        }
    }

    /* loaded from: classes13.dex */
    class MoveDetector extends GestureDetector.SimpleOnGestureListener {
        MoveDetector() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            return Math.abs(f3) + Math.abs(f2) > ((float) CardSlidePanelStyle4.this.mTouchSlop);
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

    public CardSlidePanelStyle4(Context context) {
        this(context, null);
    }

    private void ajustLinkageViewItem(float f2, int i2, boolean z, int i3) {
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get(((r6.size() - 1) / 2) + i2);
        if (i2 != this.mClickPosition) {
            cardItemView3.layout(cardItemView3.getLeft() + i3, cardItemView3.getTop(), cardItemView3.getWidth() + cardItemView3.getLeft() + i3, cardItemView3.getHeight() + this.itemMarginTop);
            cardItemView3.meansureall();
        }
        if (this.mode.equals(TYPE_BANNER_PAGER)) {
            cardItemView3.setScaleY(f2);
            if (this.scaleXY) {
                cardItemView3.setScaleX(f2);
            }
        }
    }

    private void ajustLinkageViewItemPosition(int i2, boolean z, int i3) {
        if (i2 == this.mClickPosition) {
            return;
        }
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get(((r5.size() - 1) / 2) + i2);
        cardItemView3.layout(cardItemView3.getLeft() + i3, cardItemView3.getTop(), cardItemView3.getWidth() + cardItemView3.getLeft() + i3, cardItemView3.getHeight() + this.itemMarginTop);
        cardItemView3.meansureall();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animToSide(View view, int i2, int i3) {
        int i4;
        CardSwitchListener cardSwitchListener;
        int i5 = this.initCenterViewX;
        int i6 = this.initCenterViewY;
        boolean z = true;
        int size = (this.viewList.size() - 1) / 2;
        this.viewList.get(size).getLeft();
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = (JDCustomCardbannerItemViewManager.CardItemView3) view;
        int left = this.viewList.get(size).getLeft() - this.initCenterViewX;
        int top = this.viewList.get(size).getTop() - this.initCenterViewY;
        int i7 = this.leftRightSpace;
        int i8 = this.marginLeftRight + i7 + (this.mClickPosition * (this.childWith + i7));
        if (i2 > 800 && Math.abs(i3) < i2 * 3.0f) {
            int i9 = this.childWith;
            int i10 = i8 + i9 + this.leftRightSpace;
            int left2 = (i3 * (i9 + cardItemView3.getLeft())) / i2;
            i5 = i10;
            z = false;
            i6 = cardItemView3.getTop() + left2;
            i4 = 1;
        } else {
            if (i2 < -800) {
                int i11 = -i2;
                if (Math.abs(i3) < i11 * 3.0f) {
                    int i12 = this.childWith;
                    int left3 = (i3 * (i12 + cardItemView3.getLeft())) / i11;
                    i5 = i8 - (this.leftRightSpace + i12);
                    i6 = cardItemView3.getTop() + left3;
                    i4 = 0;
                }
            }
            if (left > X_DISTANCE_THRESHOLD && Math.abs(top) < left * 3.0f) {
                int i13 = this.childWith;
                i5 = this.leftRightSpace + i8 + i13;
                i6 = ((top * (i13 + this.initCenterViewX)) / left) + this.initCenterViewY;
                i4 = 1;
            } else {
                if (left < (-X_DISTANCE_THRESHOLD)) {
                    int i14 = -left;
                    if (Math.abs(top) < i14 * 3.0f) {
                        int i15 = this.childWith;
                        i5 = i8 - (this.leftRightSpace + i15);
                        i6 = ((top * (i15 + this.initCenterViewX)) / i14) + this.initCenterViewY;
                        i4 = 0;
                    }
                }
                i4 = -1;
            }
            z = false;
        }
        int i16 = this.allHeight;
        if (i6 <= i16 && i6 < (-i16) / 2) {
            int i17 = (-i16) / 2;
        }
        if (i5 == i8) {
            onViewPosChangedRight(cardItemView3, 0 - cardItemView3.getLeft());
            cardItemView3.offsetLeftAndRight(0 - cardItemView3.getLeft());
            return;
        }
        Status status = new Status();
        status.left = z;
        status.v = cardItemView3;
        this.releasedViewList.add(status);
        if (this.mDragHelper.smoothSlideViewTo(cardItemView3, i5, this.itemMarginTop)) {
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
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = (JDCustomCardbannerItemViewManager.CardItemView3) this.releasedViewList.get(0).v;
        if (this.isShowing >= this.viewList.size()) {
            this.isShowing -= this.viewList.size();
        }
        int i2 = this.isShowing;
        if (i2 - 1 >= 0) {
            this.isShowing = i2 - 1;
        } else {
            this.isShowing = (i2 - 1) + this.viewList.size();
        }
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView32 = this.viewList.get(r0.size() - 1);
        if (this.changed == 0) {
            int i3 = this.itemMarginTop;
            int i4 = this.childWith;
            cardItemView32.layout(-(((((this.viewList.size() - 1) / 2) * this.childWith) + this.leftRightSpace) - this.marginLeftRight), i3, (-(((((this.viewList.size() - 1) / 2) * i4) + this.leftRightSpace) - this.marginLeftRight)) + i4, this.itemMarginTop + cardItemView32.getHeight());
        }
        this.changed = 0;
        this.viewList.remove(cardItemView32);
        this.viewList.add(0, cardItemView32);
        this.releasedViewList.remove(0);
        resizeLayout();
        CardSwitchListener cardSwitchListener = this.cardSwitchListener;
        if (cardSwitchListener != null) {
            cardSwitchListener.onViewScroll(1.0d);
            int i5 = this.isShowing + this.index;
            if (i5 >= this.viewList.size()) {
                i5 -= this.viewList.size();
            }
            this.cardSwitchListener.onShow(i5);
        }
        if (this.shouldflipper) {
            this.mHander.removeMessages(1001);
            this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
        }
    }

    private void orderViewStackleft() {
        this.position = 0;
        if (this.releasedViewList.size() == 0) {
            if (this.shouldflipper) {
                this.mHander.removeMessages(1001);
                this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
                return;
            }
            return;
        }
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = (JDCustomCardbannerItemViewManager.CardItemView3) this.releasedViewList.get(0).v;
        if (!this.releasedViewList.get(0).left) {
            orderViewStackRight();
            return;
        }
        if (this.isShowing >= this.viewList.size()) {
            this.isShowing -= this.viewList.size();
        }
        if (this.isShowing + 1 < this.viewList.size()) {
            this.isShowing++;
        } else {
            this.isShowing = (this.isShowing + 1) - this.viewList.size();
        }
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView32 = this.viewList.get(0);
        if (this.changed == 0) {
            cardItemView32.layout((((this.viewList.size() - 1) / 2) * this.childWith) + (this.leftRightSpace * 3) + this.marginLeftRight, this.itemMarginTop, ((((this.viewList.size() - 1) / 2) + 1) * this.childWith) + (this.leftRightSpace * 3) + this.marginLeftRight, this.itemMarginTop + cardItemView32.getHeight());
        }
        this.changed = 0;
        this.viewList.remove(cardItemView32);
        this.viewList.add(cardItemView32);
        this.releasedViewList.remove(0);
        resizeLayout();
        CardSwitchListener cardSwitchListener = this.cardSwitchListener;
        if (cardSwitchListener != null) {
            cardSwitchListener.onViewScroll(-1.0d);
            int i2 = this.isShowing + this.index;
            if (i2 >= this.viewList.size()) {
                i2 -= this.viewList.size();
            }
            this.cardSwitchListener.onShow(i2);
        }
        if (this.shouldflipper) {
            this.mHander.removeMessages(1001);
            this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
        }
    }

    private void processLinkageView(View view, boolean z, int i2) {
        int size = (this.viewList.size() - 1) / 2;
        int left = this.viewList.get(size).getLeft();
        this.viewList.get(size).getTop();
        int i3 = left - this.initCenterViewX;
        float f2 = i3 / this.MAX_SLIDE_DISTANCE_LINKAGE;
        if (f2 > 1.0f) {
            f2 = 1.0f;
        } else if (f2 < -1.0f) {
            f2 = -1.0f;
        }
        float abs = 1.0f - (Math.abs(f2) * SCALE_STEP);
        float f3 = 1.0f - ((f2 < 0.0f ? 1.0f : 1.0f - f2) * SCALE_STEP);
        float f4 = 1.0f - ((f2 < 0.0f ? f2 + 1.0f : 1.0f) * SCALE_STEP);
        for (int i4 = 0; i4 < size - 1; i4++) {
            ajustLinkageViewItemPosition(i4 - size, z, i2);
        }
        if (this.viewList.size() >= 3) {
            ajustLinkageViewItem(f3, -1, z, i2);
        }
        ajustLinkageViewItem(abs, 0, z, i2);
        if (this.viewList.size() > 2) {
            ajustLinkageViewItem(f4, 1, z, i2);
        } else if (this.viewList.size() == 2) {
            if (this.changed == 1) {
                ajustLinkageViewItem(f3, 1, z, i2);
            } else {
                ajustLinkageViewItem(f4, 1, z, i2);
            }
        }
        for (int i5 = size + 2; i5 < this.viewList.size(); i5++) {
            ajustLinkageViewItemPosition(i5 - size, z, i2);
        }
        if (this.viewList.size() < 5 && this.viewList.size() > 2) {
            List<JDCustomCardbannerItemViewManager.CardItemView3> list = this.viewList;
            JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = list.get(list.size() / 2);
            if ((cardItemView3.getLeft() - this.leftRightSpace) - cardItemView3.getWidth() >= -5 && this.changed == 0) {
                List<JDCustomCardbannerItemViewManager.CardItemView3> list2 = this.viewList;
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView32 = list2.get(list2.size() - 1);
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView33 = this.viewList.get(0);
                this.changed++;
                cardItemView32.layout((cardItemView33.getLeft() - this.leftRightSpace) - cardItemView32.getWidth(), this.itemMarginTop, cardItemView33.getLeft() - this.leftRightSpace, this.itemMarginTop + cardItemView32.getHeight());
                cardItemView32.meansureall();
            } else if (cardItemView3.getRight() + this.leftRightSpace + cardItemView3.getWidth() < getWidth() + 5 && this.changed == 0) {
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView34 = this.viewList.get(0);
                List<JDCustomCardbannerItemViewManager.CardItemView3> list3 = this.viewList;
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView35 = list3.get(list3.size() - 1);
                this.changed += 2;
                cardItemView34.layout(cardItemView35.getRight() + this.leftRightSpace, this.itemMarginTop, cardItemView35.getRight() + this.leftRightSpace + cardItemView34.getWidth(), this.itemMarginTop + cardItemView34.getHeight());
                cardItemView34.meansureall();
            }
            if ((cardItemView3.getLeft() - this.leftRightSpace) - cardItemView3.getWidth() < -5 && this.changed == 1) {
                List<JDCustomCardbannerItemViewManager.CardItemView3> list4 = this.viewList;
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView36 = list4.get(list4.size() - 1);
                List<JDCustomCardbannerItemViewManager.CardItemView3> list5 = this.viewList;
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView37 = list5.get(list5.size() - 2);
                this.changed--;
                cardItemView36.layout(cardItemView37.getRight() + this.leftRightSpace, this.itemMarginTop, cardItemView37.getRight() + this.leftRightSpace + cardItemView36.getWidth(), this.itemMarginTop + cardItemView36.getHeight());
            } else if (cardItemView3.getRight() + this.leftRightSpace + cardItemView3.getWidth() >= getWidth() + 5 && this.changed == 2) {
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView38 = this.viewList.get(0);
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView39 = this.viewList.get(1);
                this.changed -= 2;
                cardItemView38.layout((cardItemView39.getLeft() - this.leftRightSpace) - cardItemView38.getWidth(), this.itemMarginTop, cardItemView39.getLeft() - this.leftRightSpace, this.itemMarginTop + cardItemView38.getHeight());
                cardItemView38.meansureall();
            }
        } else if (this.viewList.size() == 2) {
            if (i3 < 0 && this.changed == 1) {
                List<JDCustomCardbannerItemViewManager.CardItemView3> list6 = this.viewList;
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView310 = list6.get(list6.size() - 1);
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView311 = this.viewList.get(0);
                this.changed--;
                cardItemView310.layout(cardItemView311.getRight() + this.leftRightSpace, this.itemMarginTop, cardItemView311.getRight() + this.leftRightSpace + cardItemView310.getWidth(), this.itemMarginTop + cardItemView310.getHeight());
                cardItemView310.meansureall();
            } else if (i3 > 0 && this.changed == 0) {
                List<JDCustomCardbannerItemViewManager.CardItemView3> list7 = this.viewList;
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView312 = list7.get(list7.size() - 1);
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView313 = this.viewList.get(0);
                this.changed++;
                cardItemView312.layout((cardItemView313.getLeft() - this.leftRightSpace) - cardItemView312.getWidth(), this.itemMarginTop, cardItemView313.getLeft() - this.leftRightSpace, this.itemMarginTop + cardItemView312.getHeight());
                cardItemView312.meansureall();
            }
        }
    }

    private void vanishOnBtnClick(int i2) {
        int i3;
        CardSwitchListener cardSwitchListener;
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get(0);
        if (cardItemView3.getVisibility() != 0 || this.releasedViewList.contains(cardItemView3)) {
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
            status.v = cardItemView3;
            this.releasedViewList.add(status);
            if (this.mDragHelper.smoothSlideViewTo(cardItemView3, i3, this.initCenterViewY + (this.allHeight / 2))) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }
        if (i2 < 0 || (cardSwitchListener = this.cardSwitchListener) == null) {
            return;
        }
        cardSwitchListener.onCardVanish(this.isShowing, i2);
    }

    public void clearStatus() {
        if (this.mDragHelper.getViewDragState() == 2) {
            this.mDragHelper.abort();
        }
        this.viewList.clear();
        View view = this.topLayout;
        if (view != null) {
            removeView(view);
        }
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
        int i2 = this.autoflipper;
        if (i2 != 0) {
            if (z && i2 == 1) {
                this.shouldflipper = true;
                this.mHander.removeMessages(1001);
                this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
                return;
            }
            this.shouldflipper = false;
            this.mHander.removeMessages(1001);
        } else if (this.mode.equals(TYPE_BANNER_PAGER)) {
            this.shouldflipper = false;
        } else if (z) {
            this.shouldflipper = true;
            this.mHander.removeMessages(1001);
            this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
        } else {
            this.shouldflipper = false;
            this.mHander.removeMessages(1001);
        }
    }

    public void inflateAgain() {
        this.mHander.removeMessages(1002);
        this.mHander.sendEmptyMessageDelayed(1002, 50L);
    }

    public void inflateAgainNow() {
        this.isShowing = 0;
        inflatelayout(6);
        if (this.viewList.size() != 0) {
            resizeLayout();
        }
    }

    public void inflatelayout(int i2) {
        if (getChildCount() <= 0) {
            return;
        }
        addView(new JDCustomCardbannerItemViewManager.CardItemView3(getContext()), new ViewGroup.LayoutParams((getWidth() - (this.leftRightSpace * 2)) - (this.marginLeftRight * 2), getHeight()));
        this.viewList.clear();
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (i3 == childCount - 1) {
                this.topLayout = childAt;
                ((JDCustomCardbannerItemViewManager.CardItemView3) childAt).setParentView(this);
                this.topLayout.setOnClickListener(this.btnListener);
                this.topLayout.bringToFront();
            } else {
                int i4 = i3 - ((childCount - 2) / 2);
                if (i4 < 0) {
                    i4 = (i4 + childCount) - 1;
                }
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = (JDCustomCardbannerItemViewManager.CardItemView3) getChildAt(i4);
                cardItemView3.setParentView(this);
                cardItemView3.setTag(Integer.valueOf(i3 + 1));
                this.viewList.add(cardItemView3);
            }
        }
        if (this.index > this.viewList.size()) {
            this.index = this.viewList.size();
        }
        if (this.index != 0 && this.viewList.size() > 0) {
            ArrayList arrayList = new ArrayList();
            int size = this.viewList.size();
            for (int i5 = this.index; i5 < size; i5++) {
                arrayList.add(this.viewList.get(i5));
            }
            for (int i6 = 0; i6 < this.index; i6++) {
                arrayList.add(this.viewList.get(i6));
            }
            this.viewList = arrayList;
        }
        CardSwitchListener cardSwitchListener = this.cardSwitchListener;
        if (cardSwitchListener != null) {
            cardSwitchListener.onShow(this.index + 0);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int i2;
        boolean shouldInterceptTouchEvent = this.mDragHelper.shouldInterceptTouchEvent(motionEvent);
        boolean onTouchEvent = this.moveDetector.onTouchEvent(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            int x = (int) motionEvent.getX();
            this.mActionDownPosition = x;
            if (x < this.marginLeftRight && x >= 0) {
                this.mClickPosition = -1;
            } else if (x > getWidth() - this.marginLeftRight && (i2 = this.mActionDownPosition) >= 0 && i2 < getWidth()) {
                this.mClickPosition = 1;
            } else {
                this.mClickPosition = 0;
            }
            if (this.mDragHelper.getViewDragState() == 2) {
                this.mDragHelper.abort();
            }
            this.mHander.removeMessages(1001);
            this.mTouched = true;
            orderViewStackleft();
            this.mDragHelper.processTouchEvent(motionEvent);
        } else if (actionMasked == 2) {
            this.mTouched = true;
        } else if (actionMasked != 1 && actionMasked != 3) {
            this.mTouched = false;
        } else {
            this.mTouched = false;
            if (this.shouldflipper) {
                this.mHander.removeMessages(1001);
                this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
            }
        }
        if (shouldInterceptTouchEvent && onTouchEvent) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return shouldInterceptTouchEvent && onTouchEvent;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.viewList.size() == 0) {
            inflatelayout(6);
        }
        resizeLayout();
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
    protected void onMeasure(int i2, int i3) {
        measureChildren(i2, i3);
        setMeasuredDimension(ViewGroup.resolveSizeAndState(View.MeasureSpec.getSize(i2), i2, 0), ViewGroup.resolveSizeAndState(View.MeasureSpec.getSize(i3), i3, 0));
        this.allWidth = getMeasuredWidth();
        this.allHeight = getMeasuredHeight();
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
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

    public void resizeLayout() {
        int size = this.viewList.size();
        int i2 = (size - 1) / 2;
        for (int i3 = 0; i3 < size; i3++) {
            JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get(i3);
            cardItemView3.getMeasuredHeight();
            int width = (getWidth() - cardItemView3.getMeasuredWidth()) / 2;
            int i4 = i3 - i2;
            int width2 = getWidth();
            int i5 = this.marginLeftRight;
            int i6 = this.leftRightSpace;
            int i7 = width2 - ((i5 + i6) * 2);
            int i8 = ((i6 + i7) * i4) + i5 + i6;
            ViewGroup.LayoutParams layoutParams = cardItemView3.getLayoutParams();
            layoutParams.width = i7;
            cardItemView3.setLayoutParams(layoutParams);
            cardItemView3.layout(i8, this.itemMarginTop, (getWidth() + i8) - ((this.marginLeftRight + this.leftRightSpace) * 2), getHeight());
            if (i4 > 1 || i4 < -1) {
                i4 = 1;
            }
            cardItemView3.meansureall();
            if (this.mode.equals(TYPE_BANNER_PAGER)) {
                float abs = 1.0f - (Math.abs(i4) * SCALE_STEP);
                cardItemView3.setScaleY(abs);
                if (this.scaleXY) {
                    cardItemView3.setScaleX(abs);
                }
            }
        }
        View view = this.topLayout;
        if (view != null) {
            view.layout(0, 0, 0, 0);
        }
        this.initCenterViewX = this.marginLeftRight + this.leftRightSpace;
        this.initCenterViewY = this.itemMarginTop;
        this.childWith = getWidth() - ((this.marginLeftRight + this.leftRightSpace) * 2);
        if (this.shouldflipper) {
            this.mHander.removeMessages(1001);
            this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
        }
    }

    public void setAutoFliper(int i2) {
        this.autoflipper = i2;
        this.shouldflipper = i2 == 1;
    }

    public void setCardSwitchListener(CardSwitchListener cardSwitchListener) {
        this.cardSwitchListener = cardSwitchListener;
    }

    public void setInitIndex(int i2) {
        this.index = i2;
    }

    public void setMode(String str) {
        if (str == null) {
            return;
        }
        if (str != null && str.equals("linear")) {
            setPadingMargin(0, 0);
        }
        this.mode = str;
        int i2 = this.autoflipper;
        if (i2 == 0) {
            if (str.equals(TYPE_BANNER_PAGER)) {
                this.shouldflipper = false;
                return;
            } else if (this.mode.equals(TYPE_CYCLE_PAGER)) {
                this.shouldflipper = true;
                return;
            } else {
                this.shouldflipper = false;
                this.mode = TYPE_BANNER_PAGER;
                return;
            }
        }
        this.shouldflipper = i2 == 1;
    }

    public void setPadingMargin(int i2, int i3) {
        this.marginLeftRight = i2;
        this.leftRightSpace = i3;
    }

    public void setScaleXY(boolean z) {
        this.scaleXY = z;
    }

    public void setTimeInteval(int i2) {
        this.mTimeInteval = i2;
    }

    public void showfilpper() {
        List<JDCustomCardbannerItemViewManager.CardItemView3> list = this.viewList;
        if (list == null || list.size() < 2) {
            return;
        }
        Status status = new Status();
        status.left = true;
        int size = (this.viewList.size() - 1) / 2;
        status.v = this.viewList.get(size);
        this.releasedViewList.add(status);
        if (this.mDragHelper.smoothSlideViewTo(this.viewList.get(size), (-this.viewList.get(0).getWidth()) + this.marginLeftRight, this.itemMarginTop)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void showfilpperleft() {
        if (this.viewList.size() <= 0) {
            return;
        }
        Status status = new Status();
        status.left = false;
        int size = (this.viewList.size() - 1) / 2;
        status.v = this.viewList.get(size);
        this.releasedViewList.add(status);
        if (this.mDragHelper.smoothSlideViewTo(this.viewList.get(size), this.viewList.get(0).getWidth() + (this.leftRightSpace * 2) + this.marginLeftRight, this.itemMarginTop)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public CardSlidePanelStyle4(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CardSlidePanelStyle4(Context context, AttributeSet attributeSet, int i2) {
        super(context);
        this.viewList = new ArrayList();
        this.releasedViewList = new ArrayList();
        this.initCenterViewX = 0;
        this.initCenterViewY = 0;
        this.allWidth = 0;
        this.allHeight = 0;
        this.childWith = 0;
        this.MAX_SLIDE_DISTANCE_LINKAGE = 200;
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
        this.mClickPosition = 0;
        this.mHander = new Handler() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle4.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i3 = message.what;
                if (i3 == 1001) {
                    if (!CardSlidePanelStyle4.this.shouldflipper || CardSlidePanelStyle4.this.viewList.size() <= 0 || CardSlidePanelStyle4.this.mTouched) {
                        return;
                    }
                    CardSlidePanelStyle4.this.showfilpper();
                    return;
                }
                if (i3 == 1002) {
                    CardSlidePanelStyle4.this.inflateAgainNow();
                }
                super.handleMessage(message);
            }
        };
        this.btnListener = new View.OnClickListener() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle4.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CardSlidePanelStyle4.this.cardSwitchListener != null) {
                    int i3 = CardSlidePanelStyle4.this.isShowing + CardSlidePanelStyle4.this.index;
                    if (i3 >= CardSlidePanelStyle4.this.viewList.size()) {
                        i3 -= CardSlidePanelStyle4.this.viewList.size();
                    }
                    if (CardSlidePanelStyle4.this.mActionDownPosition != 0) {
                        if (CardSlidePanelStyle4.this.mActionDownPosition >= CardSlidePanelStyle4.this.marginLeftRight || CardSlidePanelStyle4.this.mActionDownPosition < 0) {
                            if (CardSlidePanelStyle4.this.mActionDownPosition <= CardSlidePanelStyle4.this.getWidth() - CardSlidePanelStyle4.this.marginLeftRight || CardSlidePanelStyle4.this.mActionDownPosition < 0 || CardSlidePanelStyle4.this.mActionDownPosition >= CardSlidePanelStyle4.this.getWidth() || CardSlidePanelStyle4.this.viewList.size() <= 0) {
                                CardSlidePanelStyle4.this.cardSwitchListener.onItemClick(view, i3);
                                return;
                            } else {
                                CardSlidePanelStyle4.this.showfilpper();
                                return;
                            }
                        }
                        CardSlidePanelStyle4.this.showfilpperleft();
                        return;
                    }
                    CardSlidePanelStyle4.this.cardSwitchListener.onItemClick(view, i3);
                }
            }
        };
        this.position = 0;
        this.changed = 0;
        this.mActionDownPosition = 0;
        this.mTouched = false;
        this.shouldflipper = false;
        this.autoflipper = 0;
        this.index = 0;
        this.mTimeInteval = 3000;
        this.mode = TYPE_BANNER_PAGER;
        this.scaleXY = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.card);
        setMotionEventSplittingEnabled(false);
        this.itemMarginTop = (int) obtainStyledAttributes.getDimension(R.styleable.card_itemMarginTop, this.itemMarginTop);
        this.bottomMarginTop = (int) obtainStyledAttributes.getDimension(R.styleable.card_bottomMarginTop, this.bottomMarginTop);
        this.yOffsetStep = (int) obtainStyledAttributes.getDimension(R.styleable.card_yOffsetStep, this.yOffsetStep);
        this.xOffsetStep = (int) obtainStyledAttributes.getDimension(R.styleable.card_xOffsetStep, this.xOffsetStep);
        this.MAX_SLIDE_DISTANCE_LINKAGE = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card__left_right_step);
        if (this.mode.equals(TYPE_CYCLE_PAGER)) {
            this.marginLeftRight = 0;
            this.leftRightSpace = 0;
        } else if (this.mode.equals(TYPE_BANNER_PAGER)) {
            this.marginLeftRight = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card_left_right_padding);
            this.leftRightSpace = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card__left_right_space);
        }
        X_DISTANCE_THRESHOLD = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card_step_length);
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
