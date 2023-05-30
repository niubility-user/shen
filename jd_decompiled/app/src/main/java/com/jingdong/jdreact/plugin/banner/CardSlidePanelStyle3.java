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
public class CardSlidePanelStyle3 extends ReactViewGroup {
    public static final int AUTOFLIPPER_DEFAULT = 0;
    public static final int AUTOFLIPPER_OFF = 2;
    public static final int AUTOFLIPPER_ON = 1;
    private static final int AUTO_FLIPPER_MESSAGE = 1001;
    private static final int AUTO_REFRESH_MESSAGE = 1002;
    public static String TYPE_BANNER_PAGER = "coverFlow";
    public static String TYPE_CYCLE_PAGER = "linear";
    public static final int VANISH_TYPE_LEFT = 0;
    public static final int VANISH_TYPE_RIGHT = 1;
    private static int X_DISTANCE_THRESHOLD = 30;
    private static final int X_VEL_THRESHOLD = 800;
    private int MAX_SLIDE_DISTANCE_LINKAGE;
    private float SCALE_STEP;
    private float SCALE_STEP_LEFT;
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
    private boolean fixLeftAndRight;
    private int index;
    private int initCenterViewX;
    private int initCenterViewY;
    private int isShowing;
    private int itemMarginTop;
    private int leftRightSpace;
    private int mActionDownPosition;
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
    private boolean shouldflipper;
    private View topLayout;
    private List<JDCustomCardbannerItemViewManager.CardItemView3> viewList;
    private int xOffsetStep;
    private int yOffsetStep;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class DragHelperCallback extends ViewDragHelper.Callback {
        private DragHelperCallback() {
            CardSlidePanelStyle3.this = r1;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i2, int i3) {
            return i2;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i2, int i3) {
            return CardSlidePanelStyle3.this.itemMarginTop;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return 256;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            CardSlidePanelStyle3.this.onViewPosChangedRight(view, i4);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            CardSlidePanelStyle3.this.mTouched = false;
            CardSlidePanelStyle3.this.animToSide(view, (int) f2, (int) f3);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            if (CardSlidePanelStyle3.this.viewList == null || CardSlidePanelStyle3.this.viewList.size() <= 1 || view.getVisibility() != 0 || view.getScaleX() <= 1.0f - CardSlidePanelStyle3.this.SCALE_STEP || CardSlidePanelStyle3.this.btnLock) {
                return false;
            }
            CardSlidePanelStyle3.this.viewList.indexOf(view);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class MoveDetector extends GestureDetector.SimpleOnGestureListener {
        MoveDetector() {
            CardSlidePanelStyle3.this = r1;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            return Math.abs(f3) + Math.abs(f2) > ((float) CardSlidePanelStyle3.this.mTouchSlop);
        }
    }

    /* loaded from: classes13.dex */
    public class Status {
        boolean left;
        View v;

        Status() {
            CardSlidePanelStyle3.this = r1;
        }
    }

    public CardSlidePanelStyle3(Context context) {
        this(context, null);
    }

    private void ajustLinkageViewItem(float f2, int i2, boolean z, int i3) {
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get(((r6.size() - 1) / 2) + i2);
        cardItemView3.layout(cardItemView3.getLeft() + i3, cardItemView3.getTop(), cardItemView3.getWidth() + cardItemView3.getLeft() + i3, cardItemView3.getHeight() + this.itemMarginTop);
        cardItemView3.meansureall();
        if (this.mode.equals(TYPE_BANNER_PAGER)) {
            cardItemView3.setScaleY(f2);
            if (this.fixLeftAndRight) {
                cardItemView3.setScaleX(f2);
            }
        }
    }

    private void ajustLinkageViewItemPosition(int i2, boolean z, int i3) {
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get(((r5.size() - 1) / 2) + i2);
        cardItemView3.layout(cardItemView3.getLeft() + i3, cardItemView3.getTop(), cardItemView3.getWidth() + cardItemView3.getLeft() + i3, cardItemView3.getHeight() + this.itemMarginTop);
        cardItemView3.meansureall();
    }

    public void animToSide(View view, int i2, int i3) {
        int i4;
        CardSwitchListener cardSwitchListener;
        int i5 = this.initCenterViewX;
        int i6 = this.initCenterViewY;
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = (JDCustomCardbannerItemViewManager.CardItemView3) view;
        int left = cardItemView3.getLeft() - this.initCenterViewX;
        int top = cardItemView3.getTop() - this.initCenterViewY;
        boolean z = true;
        if (i2 > 800 && Math.abs(i3) < i2 * 3.0f) {
            int i7 = this.childWith;
            int i8 = this.leftRightSpace + i7;
            int left2 = (i3 * (i7 + cardItemView3.getLeft())) / i2;
            i5 = i8;
            z = false;
            i6 = cardItemView3.getTop() + left2;
            i4 = 1;
        } else {
            if (i2 < -800) {
                int i9 = -i2;
                if (Math.abs(i3) < i9 * 3.0f) {
                    int i10 = this.childWith;
                    int left3 = (i3 * (i10 + cardItemView3.getLeft())) / i9;
                    i5 = -(this.leftRightSpace + i10);
                    i6 = cardItemView3.getTop() + left3;
                    i4 = 0;
                }
            }
            if (left > X_DISTANCE_THRESHOLD && Math.abs(top) < left * 3.0f) {
                int i11 = this.childWith;
                i5 = i11 + this.leftRightSpace;
                i6 = ((top * (i11 + this.initCenterViewX)) / left) + this.initCenterViewY;
                i4 = 1;
            } else {
                if (left < (-X_DISTANCE_THRESHOLD)) {
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
        if (((JDCustomCardbannerItemViewManager.CardItemView3) this.releasedViewList.get(0).v).getLeft() == this.initCenterViewX) {
            this.releasedViewList.remove(0);
            return;
        }
        if (this.isShowing >= this.viewList.size()) {
            this.isShowing -= this.viewList.size();
        }
        int i2 = this.isShowing;
        if (i2 - 1 >= 0) {
            this.isShowing = i2 - 1;
        } else {
            this.isShowing = (i2 - 1) + this.viewList.size();
        }
        List<JDCustomCardbannerItemViewManager.CardItemView3> list = this.viewList;
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = list.get(list.size() - 1);
        if (this.changed == 0) {
            int i3 = this.itemMarginTop;
            int i4 = this.childWith;
            cardItemView3.layout(-(((((this.viewList.size() - 1) / 2) * this.childWith) + this.leftRightSpace) - this.marginLeftRight), i3, (-(((((this.viewList.size() - 1) / 2) * i4) + this.leftRightSpace) - this.marginLeftRight)) + i4, this.itemMarginTop + cardItemView3.getHeight());
        }
        this.changed = 0;
        boolean z = this.topLayout.getLeft() != 0;
        this.topLayout.layout(0, 0, getWidth(), getHeight());
        this.topLayout.bringToFront();
        this.viewList.remove(cardItemView3);
        this.viewList.add(0, cardItemView3);
        this.releasedViewList.remove(0);
        if (z) {
            resizeLayout();
        }
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
            }
        } else if (((JDCustomCardbannerItemViewManager.CardItemView3) this.releasedViewList.get(0).v).getLeft() == this.initCenterViewX) {
            this.releasedViewList.remove(0);
        } else if (!this.releasedViewList.get(0).left) {
            orderViewStackRight();
        } else {
            if (this.isShowing >= this.viewList.size()) {
                this.isShowing -= this.viewList.size();
            }
            if (this.isShowing + 1 < this.viewList.size()) {
                this.isShowing++;
            } else {
                this.isShowing = (this.isShowing + 1) - this.viewList.size();
            }
            JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get(0);
            if (this.changed == 0) {
                cardItemView3.layout((((this.viewList.size() - 1) / 2) * this.childWith) + (this.leftRightSpace * 3) + this.marginLeftRight, this.itemMarginTop, ((((this.viewList.size() - 1) / 2) + 1) * this.childWith) + (this.leftRightSpace * 3) + this.marginLeftRight, this.itemMarginTop + cardItemView3.getHeight());
            }
            this.changed = 0;
            boolean z = this.topLayout.getLeft() != 0;
            this.topLayout.layout(0, 0, getWidth(), getHeight());
            this.topLayout.bringToFront();
            this.viewList.remove(cardItemView3);
            this.viewList.add(cardItemView3);
            this.releasedViewList.remove(0);
            if (z) {
                resizeLayout();
            }
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
    }

    private void processLinkageView(View view, boolean z, int i2) {
        int left = view.getLeft();
        view.getTop();
        int i3 = left - this.initCenterViewX;
        float f2 = i3 / this.MAX_SLIDE_DISTANCE_LINKAGE;
        if (f2 > 1.0f) {
            f2 = 1.0f;
        } else if (f2 < -1.0f) {
            f2 = -1.0f;
        }
        float abs = 1.0f - (this.SCALE_STEP * Math.abs(f2));
        Math.abs(f2);
        float f3 = f2 < 0.0f ? 1.0f : 1.0f - f2;
        float f4 = this.SCALE_STEP;
        float f5 = 1.0f - (f4 * f3);
        float f6 = 1.0f - (this.SCALE_STEP_LEFT * f3);
        float f7 = 1.0f - (f4 * (f2 < 0.0f ? f2 + 1.0f : 1.0f));
        int size = (this.viewList.size() - 1) / 2;
        for (int i4 = 0; i4 < size - 1; i4++) {
            ajustLinkageViewItemPosition(i4 - size, z, i2);
        }
        if (this.viewList.size() >= 3) {
            ajustLinkageViewItem(f6, -1, z, i2);
        }
        ajustLinkageViewItem(abs, 0, z, i2);
        if (this.viewList.size() > 2) {
            ajustLinkageViewItem(f7, 1, z, i2);
        } else if (this.viewList.size() == 2) {
            if (this.changed == 1) {
                ajustLinkageViewItem(f5, 1, z, i2);
            } else {
                ajustLinkageViewItem(f7, 1, z, i2);
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
                cardItemView36.meansureall();
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

    public void fixLeftAndRight(boolean z) {
        this.fixLeftAndRight = z;
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
        boolean shouldInterceptTouchEvent = this.mDragHelper.shouldInterceptTouchEvent(motionEvent);
        boolean onTouchEvent = this.moveDetector.onTouchEvent(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mActionDownPosition = (int) motionEvent.getX();
            if (this.mDragHelper.getViewDragState() == 2) {
                this.mDragHelper.abort();
            }
            this.mHander.removeMessages(1001);
            this.mTouched = true;
            orderViewStackleft();
            this.mDragHelper.processTouchEvent(motionEvent);
        } else if (actionMasked == 2) {
            this.mTouched = true;
        } else if (actionMasked == 1 || actionMasked == 3) {
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

    /* JADX WARN: Removed duplicated region for block: B:40:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onViewPosChangedRight(android.view.View r4, int r5) {
        /*
            r3 = this;
            java.util.List<com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle3$Status> r0 = r3.releasedViewList
            int r0 = r0.size()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L30
            int r0 = r3.position
            int r0 = r0 + r5
            r3.position = r0
            if (r0 >= 0) goto L1f
            java.util.List<com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle3$Status> r5 = r3.releasedViewList
            java.lang.Object r5 = r5.get(r2)
            com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle3$Status r5 = (com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle3.Status) r5
            boolean r5 = r5.left
            if (r5 != 0) goto L1f
        L1d:
            r5 = 1
            goto L33
        L1f:
            int r5 = r3.position
            if (r5 <= 0) goto L32
            java.util.List<com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle3$Status> r5 = r3.releasedViewList
            java.lang.Object r5 = r5.get(r2)
            com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle3$Status r5 = (com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle3.Status) r5
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
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle3.onViewPosChangedRight(android.view.View, int):void");
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
                float abs = 1.0f - (this.SCALE_STEP * Math.abs(i4));
                cardItemView3.setScaleY(abs);
                if (this.fixLeftAndRight) {
                    cardItemView3.setScaleX(abs);
                }
            }
        }
        this.topLayout.layout(0, this.itemMarginTop, getWidth(), getHeight());
        this.initCenterViewX = this.topLayout.getLeft();
        this.initCenterViewY = this.topLayout.getTop();
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

    public void setRightHeight(float f2) {
        this.SCALE_STEP = f2;
    }

    public void setTimeInteval(int i2) {
        this.mTimeInteval = i2;
    }

    public void setleftHeight(float f2) {
        this.SCALE_STEP_LEFT = f2;
    }

    public void showfilpper() {
        List<JDCustomCardbannerItemViewManager.CardItemView3> list;
        if (this.topLayout == null || (list = this.viewList) == null || list.size() < 2) {
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

    public CardSlidePanelStyle3(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CardSlidePanelStyle3(Context context, AttributeSet attributeSet, int i2) {
        super(context);
        this.viewList = new ArrayList();
        this.releasedViewList = new ArrayList();
        this.initCenterViewX = 0;
        this.initCenterViewY = 0;
        this.allWidth = 0;
        this.allHeight = 0;
        this.childWith = 0;
        this.SCALE_STEP = 0.254f;
        this.SCALE_STEP_LEFT = 0.254f;
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
        this.mHander = new Handler() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle3.1
            {
                CardSlidePanelStyle3.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i3 = message.what;
                if (i3 == 1001) {
                    if (!CardSlidePanelStyle3.this.shouldflipper || CardSlidePanelStyle3.this.mTouched) {
                        return;
                    }
                    CardSlidePanelStyle3.this.showfilpper();
                    return;
                }
                if (i3 == 1002) {
                    CardSlidePanelStyle3.this.inflateAgainNow();
                }
                super.handleMessage(message);
            }
        };
        this.btnListener = new View.OnClickListener() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle3.2
            {
                CardSlidePanelStyle3.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CardSlidePanelStyle3.this.cardSwitchListener != null) {
                    int i3 = CardSlidePanelStyle3.this.isShowing + CardSlidePanelStyle3.this.index;
                    if (i3 >= CardSlidePanelStyle3.this.viewList.size()) {
                        i3 -= CardSlidePanelStyle3.this.viewList.size();
                    }
                    if (CardSlidePanelStyle3.this.mActionDownPosition != 0) {
                        if (CardSlidePanelStyle3.this.mActionDownPosition >= CardSlidePanelStyle3.this.marginLeftRight || CardSlidePanelStyle3.this.mActionDownPosition < 0) {
                            if (CardSlidePanelStyle3.this.mActionDownPosition <= CardSlidePanelStyle3.this.getWidth() - CardSlidePanelStyle3.this.marginLeftRight || CardSlidePanelStyle3.this.mActionDownPosition < 0 || CardSlidePanelStyle3.this.mActionDownPosition >= CardSlidePanelStyle3.this.getWidth()) {
                                CardSlidePanelStyle3.this.cardSwitchListener.onItemClick(view, i3);
                                return;
                            } else {
                                CardSlidePanelStyle3.this.showfilpper();
                                return;
                            }
                        }
                        CardSlidePanelStyle3.this.showfilpperleft();
                        return;
                    }
                    CardSlidePanelStyle3.this.cardSwitchListener.onItemClick(view, i3);
                }
            }
        };
        this.position = 0;
        this.changed = 0;
        this.mActionDownPosition = 0;
        this.shouldflipper = false;
        this.autoflipper = 0;
        this.index = 0;
        this.mTouched = false;
        this.mTimeInteval = 3000;
        this.fixLeftAndRight = false;
        this.mode = TYPE_BANNER_PAGER;
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
