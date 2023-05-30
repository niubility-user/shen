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
public class CardSlidePanelStyle6 extends ReactViewGroup {
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
    private int mMarginRight;
    private int mMaxnum;
    private int mTimeInteval;
    private int mTouchSlop;
    private int marginLeftRight;
    private GestureDetectorCompat moveDetector;
    int position;
    private List<Status> releasedViewList;
    private boolean shouldflipper;
    private List<JDCustomCardbannerItemViewManager.CardItemView3> tempViewList;
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
            return CardSlidePanelStyle6.this.itemMarginTop;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return 256;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            CardSlidePanelStyle6.this.onViewPosChangedRight(view, i4);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            CardSlidePanelStyle6.this.animToSide(view, (int) f2, (int) f3);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            if (CardSlidePanelStyle6.this.viewList == null || CardSlidePanelStyle6.this.viewList.size() <= 1 || view.getVisibility() != 0 || view.getScaleX() <= 1.0f - CardSlidePanelStyle6.this.SCALE_STEP || CardSlidePanelStyle6.this.btnLock) {
                return false;
            }
            CardSlidePanelStyle6.this.viewList.indexOf(view);
            return true;
        }
    }

    /* loaded from: classes13.dex */
    class MoveDetector extends GestureDetector.SimpleOnGestureListener {
        MoveDetector() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            return Math.abs(f3) + Math.abs(f2) > ((float) CardSlidePanelStyle6.this.mTouchSlop);
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

    public CardSlidePanelStyle6(Context context) {
        this(context, null);
    }

    private void ajustLinkageViewItem(int i2, float f2, int i3, boolean z) {
        int i4 = this.xOffsetStep;
        int i5 = i4 * i3;
        float f3 = this.SCALE_STEP;
        float f4 = 1.0f - (i3 * f3);
        int i6 = i3 - 1;
        int i7 = i4 * i6;
        float f5 = 1.0f - (i6 * f3);
        if (z) {
            int i8 = i3 + 1;
            i7 = i4 * i8;
            f5 = 1.0f - (f3 * i8);
        }
        int i9 = (int) (i5 + ((i7 - i5) * f2));
        float f6 = f4 + ((f5 - f4) * f2);
        int i10 = i2 + i3;
        if (i10 >= this.viewList.size()) {
            return;
        }
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get(i10);
        cardItemView3.offsetLeftAndRight((i9 - cardItemView3.getLeft()) + this.initCenterViewX);
        cardItemView3.setScaleX(f6);
        cardItemView3.setScaleY(f6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void animToSide(android.view.View r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle6.animToSide(android.view.View, int, int):void");
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
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView32 = this.viewList.get(r0.size() - 2);
        if (this.changed == 0) {
            int i3 = this.itemMarginTop;
            cardItemView32.layout(-cardItemView32.getWidth(), i3, 0, cardItemView32.getHeight() + i3);
        }
        this.changed = 0;
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView33 = this.viewList.get(r0.size() - 1);
        this.viewList.remove(cardItemView33);
        this.viewList.add(0, cardItemView33);
        for (int size = this.viewList.size() - 1; size >= 0; size--) {
            JDCustomCardbannerItemViewManager.CardItemView3 cardItemView34 = this.viewList.get(size);
            cardItemView34.setAlpha(1.0f);
            cardItemView34.bringToFront();
        }
        this.topLayout.bringToFront();
        resizeLayout();
        this.releasedViewList.remove(0);
        CardSwitchListener cardSwitchListener = this.cardSwitchListener;
        if (cardSwitchListener != null) {
            cardSwitchListener.onViewScroll(1.0d);
            int i4 = this.isShowing + this.index;
            if (i4 >= this.viewList.size()) {
                i4 -= this.viewList.size();
            }
            this.cardSwitchListener.onShow(i4);
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
            if (this.viewList.isEmpty()) {
                return;
            }
            JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get(r1.size() - 1);
            if (this.changed == 0) {
                int i2 = this.itemMarginTop;
                cardItemView3.layout(-cardItemView3.getWidth(), i2, 0, cardItemView3.getHeight() + i2);
            }
            this.changed = 0;
            JDCustomCardbannerItemViewManager.CardItemView3 cardItemView32 = this.viewList.get(0);
            this.viewList.remove(cardItemView32);
            this.viewList.add(cardItemView32);
            for (int size = this.viewList.size() - 1; size >= 0; size--) {
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView33 = this.viewList.get(size);
                cardItemView33.setAlpha(1.0f);
                cardItemView33.bringToFront();
            }
            this.topLayout.bringToFront();
            this.releasedViewList.remove(0);
            resizeLayout();
            CardSwitchListener cardSwitchListener = this.cardSwitchListener;
            if (cardSwitchListener != null) {
                cardSwitchListener.onViewScroll(-1.0d);
                int i3 = this.isShowing + this.index;
                if (i3 >= this.viewList.size()) {
                    i3 -= this.viewList.size();
                }
                this.cardSwitchListener.onShow(i3);
            }
            if (this.shouldflipper) {
                this.mHander.removeMessages(1001);
                this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
            }
        }
    }

    private void processLinkageView(View view, boolean z) {
        int left = view.getLeft() - this.initCenterViewX;
        float abs = Math.abs(left) / this.MAX_SLIDE_DISTANCE_LINKAGE;
        float f2 = abs;
        for (int i2 = 1; i2 <= this.mMaxnum; i2++) {
            f2 = abs - (i2 * this.SCALE_STEP);
            if (f2 < 0.0f) {
                f2 = 0.0f;
            } else if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            ajustLinkageViewItem(0, f2, i2, z);
        }
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get(0);
        cardItemView3.setAlpha(1.0f - f2);
        float width = left / getWidth();
        cardItemView3.layout((int) ((getWidth() - this.mMarginRight) * width), 0, (int) ((width + 1.0f) * (getWidth() - this.mMarginRight)), getHeight());
    }

    private void processLinkageViewRight(View view, boolean z) {
        int left = view.getLeft() - this.initCenterViewX;
        float abs = (Math.abs(left) / this.MAX_SLIDE_DISTANCE_LINKAGE) - this.SCALE_STEP;
        float f2 = abs;
        for (int i2 = 0; i2 < this.mMaxnum - 1; i2++) {
            f2 = abs - (i2 * this.SCALE_STEP);
            if (f2 < 0.0f) {
                f2 = 0.0f;
            } else if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            ajustLinkageViewItem(0, f2, i2, z);
        }
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get(r9.size() - 1);
        cardItemView3.setAlpha(f2);
        float width = (left * 2.0f) / getWidth();
        if (width > 1.0f) {
            width = 1.0f;
        }
        cardItemView3.layout((int) ((width - 1.0f) * (getWidth() - this.mMarginRight)), 0, (int) (width * (getWidth() - this.mMarginRight)), getHeight());
        cardItemView3.setScaleX(1.0f);
        cardItemView3.setScaleY(1.0f);
        cardItemView3.bringToFront();
    }

    public void clearStatus() {
        if (this.mDragHelper.getViewDragState() == 2) {
            this.mDragHelper.abort();
        }
        View view = this.topLayout;
        if (view != null) {
            removeView(view);
        }
        removeAllViews();
        int size = this.tempViewList.size();
        for (int i2 = 0; i2 < size; i2++) {
            addView(this.tempViewList.get(i2));
        }
        this.viewList.clear();
        this.tempViewList.clear();
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
        if (z && this.autoflipper == 1) {
            this.shouldflipper = true;
            this.mHander.removeMessages(1001);
            this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
            return;
        }
        this.shouldflipper = false;
        this.mHander.removeMessages(1001);
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
        addView(new JDCustomCardbannerItemViewManager.CardItemView3(getContext()), new ViewGroup.LayoutParams(getWidth(), getHeight()));
        this.tempViewList.clear();
        this.viewList.clear();
        int childCount = getChildCount();
        if (this.mMaxnum > childCount) {
            this.mMaxnum = childCount;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (i3 == childCount - 1) {
                this.topLayout = childAt;
                ((JDCustomCardbannerItemViewManager.CardItemView3) childAt).setParentView(this);
                this.topLayout.setOnClickListener(this.btnListener);
            } else {
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = (JDCustomCardbannerItemViewManager.CardItemView3) getChildAt(i3);
                cardItemView3.setParentView(this);
                cardItemView3.setTag(Integer.valueOf(i3 + 1));
                this.tempViewList.add(cardItemView3);
            }
        }
        if (this.index > this.tempViewList.size()) {
            this.index = this.tempViewList.size();
        }
        if (this.index < 0) {
            this.index = 0;
        }
        if (this.tempViewList.size() > 0) {
            ArrayList arrayList = new ArrayList();
            int size = this.tempViewList.size();
            for (int i4 = this.index; i4 < size; i4++) {
                arrayList.add(this.tempViewList.get(i4));
            }
            for (int i5 = 0; i5 < this.index; i5++) {
                arrayList.add(this.tempViewList.get(i5));
            }
            this.viewList = arrayList;
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView32 = this.viewList.get(size2);
                cardItemView32.setAlpha(1.0f);
                cardItemView32.bringToFront();
            }
            this.topLayout.bringToFront();
            CardSwitchListener cardSwitchListener = this.cardSwitchListener;
            if (cardSwitchListener != null) {
                cardSwitchListener.onShow(this.index + 0);
            }
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
        if (motionEvent.getActionMasked() == 0) {
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
            orderViewStackleft();
            this.mDragHelper.processTouchEvent(motionEvent);
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
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onViewPosChangedRight(android.view.View r4, int r5) {
        /*
            r3 = this;
            java.util.List<com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle6$Status> r0 = r3.releasedViewList
            int r0 = r0.size()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L30
            int r0 = r3.position
            int r0 = r0 + r5
            r3.position = r0
            if (r0 >= 0) goto L1f
            java.util.List<com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle6$Status> r5 = r3.releasedViewList
            java.lang.Object r5 = r5.get(r2)
            com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle6$Status r5 = (com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle6.Status) r5
            boolean r5 = r5.left
            if (r5 != 0) goto L1f
        L1d:
            r5 = 1
            goto L33
        L1f:
            int r5 = r3.position
            if (r5 <= 0) goto L32
            java.util.List<com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle6$Status> r5 = r3.releasedViewList
            java.lang.Object r5 = r5.get(r2)
            com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle6$Status r5 = (com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle6.Status) r5
            boolean r5 = r5.left
            if (r5 == 0) goto L32
            goto L1d
        L30:
            r3.position = r5
        L32:
            r5 = 0
        L33:
            if (r5 != 0) goto L69
            int r5 = r4.getLeft()
            if (r5 <= 0) goto L46
            int r5 = r4.getLeft()
            if (r5 >= 0) goto L42
            r1 = 0
        L42:
            r3.processLinkageViewRight(r4, r1)
            goto L50
        L46:
            int r5 = r4.getLeft()
            if (r5 >= 0) goto L4d
            r1 = 0
        L4d:
            r3.processLinkageView(r4, r1)
        L50:
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
        L69:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle6.onViewPosChangedRight(android.view.View, int):void");
    }

    public void resizeLayout() {
        int size = this.viewList.size();
        for (int i2 = 0; i2 < size; i2++) {
            JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get(i2);
            cardItemView3.layout(0, this.itemMarginTop, (getWidth() + 0) - this.mMarginRight, getHeight());
            cardItemView3.meansureall(0);
            int i3 = this.xOffsetStep;
            int i4 = i3 * i2;
            float f2 = this.SCALE_STEP;
            float f3 = 1.0f - (i2 * f2);
            int i5 = this.mMaxnum;
            if (i2 > i5 - 1) {
                i4 = (i5 - 1) * i3;
                f3 = 1.0f - (f2 * ((float) (i5 - 1)));
            }
            cardItemView3.offsetLeftAndRight(i4);
            cardItemView3.setScaleX(f3);
            cardItemView3.setScaleY(f3);
            cardItemView3.setAlpha(1.0f);
        }
        View view = this.topLayout;
        if (view != null) {
            view.layout(0, 0, getWidth(), getHeight());
        }
        this.initCenterViewX = 0;
        this.initCenterViewY = 0;
        this.childWith = this.viewList.get(0).getMeasuredWidth();
        this.mHander.removeMessages(1001);
        this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
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

    public void setMarginRight(int i2) {
        this.mMarginRight = i2;
    }

    public void setMaxShowNumber(int i2) {
        this.mMaxnum = i2;
    }

    public void setMode(String str) {
    }

    public void setOffsetStep(int i2) {
        this.xOffsetStep = i2;
    }

    public void setScal(float f2) {
        this.SCALE_STEP = f2;
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
        status.v = this.viewList.get((this.viewList.size() - 1) / 2);
        this.releasedViewList.add(status);
        if (this.mDragHelper.smoothSlideViewTo(this.topLayout, -getWidth(), this.itemMarginTop)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void showfilpperleft() {
        if (this.viewList.size() <= 0) {
            return;
        }
        Status status = new Status();
        status.left = false;
        status.v = this.viewList.get((this.viewList.size() - 1) / 2);
        this.releasedViewList.add(status);
        if (this.mDragHelper.smoothSlideViewTo(this.topLayout, getWidth(), this.itemMarginTop)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public CardSlidePanelStyle6(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CardSlidePanelStyle6(Context context, AttributeSet attributeSet, int i2) {
        super(context);
        this.viewList = new ArrayList();
        this.tempViewList = new ArrayList();
        this.releasedViewList = new ArrayList();
        this.initCenterViewX = 0;
        this.initCenterViewY = 0;
        this.allWidth = 0;
        this.allHeight = 0;
        this.childWith = 0;
        this.SCALE_STEP = 0.054f;
        this.MAX_SLIDE_DISTANCE_LINKAGE = 200;
        this.marginLeftRight = 0;
        this.leftRightSpace = 0;
        this.itemMarginTop = 0;
        this.bottomMarginTop = 40;
        this.yOffsetStep = 40;
        this.xOffsetStep = 50;
        this.mTouchSlop = 5;
        this.isShowing = 0;
        this.btnLock = false;
        this.downPoint = new Point();
        this.mClickPosition = 0;
        this.mHander = new Handler() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle6.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i3 = message.what;
                if (i3 == 1001) {
                    if (!CardSlidePanelStyle6.this.shouldflipper || CardSlidePanelStyle6.this.viewList.size() <= 0) {
                        return;
                    }
                    CardSlidePanelStyle6.this.showfilpper();
                    return;
                }
                if (i3 == 1002) {
                    CardSlidePanelStyle6.this.inflateAgainNow();
                }
                super.handleMessage(message);
            }
        };
        this.btnListener = new View.OnClickListener() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle6.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CardSlidePanelStyle6.this.cardSwitchListener != null) {
                    int i3 = CardSlidePanelStyle6.this.isShowing + CardSlidePanelStyle6.this.index;
                    if (i3 >= CardSlidePanelStyle6.this.viewList.size()) {
                        i3 -= CardSlidePanelStyle6.this.viewList.size();
                    }
                    if (CardSlidePanelStyle6.this.mActionDownPosition != 0) {
                        if (CardSlidePanelStyle6.this.mActionDownPosition <= CardSlidePanelStyle6.this.getWidth() - CardSlidePanelStyle6.this.mMarginRight || CardSlidePanelStyle6.this.mActionDownPosition < 0 || CardSlidePanelStyle6.this.mActionDownPosition >= CardSlidePanelStyle6.this.getWidth() || CardSlidePanelStyle6.this.viewList.size() <= 0) {
                            CardSlidePanelStyle6.this.cardSwitchListener.onItemClick(view, i3);
                            return;
                        } else {
                            CardSlidePanelStyle6.this.showfilpper();
                            return;
                        }
                    }
                    CardSlidePanelStyle6.this.cardSwitchListener.onItemClick(view, i3);
                }
            }
        };
        this.position = 0;
        this.changed = 0;
        this.mActionDownPosition = 0;
        this.shouldflipper = false;
        this.autoflipper = 1;
        this.mMaxnum = 4;
        this.index = 0;
        this.mMarginRight = 250;
        this.mTimeInteval = 3000;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.card);
        this.itemMarginTop = (int) obtainStyledAttributes.getDimension(R.styleable.card_itemMarginTop, this.itemMarginTop);
        this.bottomMarginTop = (int) obtainStyledAttributes.getDimension(R.styleable.card_bottomMarginTop, this.bottomMarginTop);
        this.yOffsetStep = (int) obtainStyledAttributes.getDimension(R.styleable.card_yOffsetStep, this.yOffsetStep);
        this.xOffsetStep = (int) obtainStyledAttributes.getDimension(R.styleable.card_xOffsetStep, this.xOffsetStep);
        this.MAX_SLIDE_DISTANCE_LINKAGE = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card__left_right_step);
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
