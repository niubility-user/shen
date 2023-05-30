package com.jingdong.jdreact.plugin.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import com.facebook.react.views.view.ReactViewGroup;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdreact.plugin.banner.JDCustomCardbannerItemViewManager;
import com.jingdong.jdreact.plugin.banner.JDCustomCardbannerWithOutTouchViewManager2;
import com.jingdong.jdreact.plugin.banner.JDCustomImageViewManager;
import com.jingdong.jdreact.plugin.banner.ui.CardSwitchListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes13.dex */
public class CardSlidePanelStyle5 extends ReactViewGroup {
    public static final int AUTOFLIPPER_DEFAULT = 0;
    public static final int AUTOFLIPPER_OFF = 2;
    public static final int AUTOFLIPPER_ON = 1;
    private static final int AUTO_FLIPPER_MESSAGE = 1001;
    private static final int AUTO_REFRESH_MESSAGE = 1002;
    private static float SCALE_STEP = 0.254f;
    private static float SCALE_STEP_LEFT = 0.127f;
    public static String TYPE_BANNER_PAGER = "coverFlow";
    public static String TYPE_CYCLE_PAGER = "linear";
    public static final int VANISH_TYPE_LEFT = 0;
    public static final int VANISH_TYPE_RIGHT = 1;
    private static int X_DISTANCE_THRESHOLD = 10;
    private static final int X_VEL_THRESHOLD = 800;
    private int MAX_SLIDE_DISTANCE_LINKAGE;
    private int allHeight;
    private int allWidth;
    private int autoflipper;
    JDCustomCardbannerItemViewManager.CardItemView3 backGroundView;
    private int backHeight;
    private int bottomMarginTop;
    private boolean btnLock;
    private CardSwitchListener cardSwitchListener;
    int changed;
    private int childWith;
    private int count;
    private boolean cycle;
    private Point downPoint;
    JDCustomImageViewManager.JDReactSimpleDraweeView draweeView;
    JDCustomImageViewManager.JDReactSimpleDraweeView draweeView1;
    JDCustomImageViewManager.JDReactSimpleDraweeView fdraweeView;
    int forgroudColor;
    private int index;
    private int initCenterViewX;
    private int initCenterViewY;
    private boolean isDraging;
    private int isShowing;
    private int itemMarginBttom;
    private int itemMarginTop;
    Bitmap left;
    private int leftRightSpace;
    private int mActionDownPosition;
    private float mAlpha;
    ArrayList<JDCustomCardbannerItemViewManager.CardItemView3> mArrayList;
    private int mBlur;
    private int mClickPosition;
    private final ViewDragHelper mDragHelper;
    PipelineDraweeControllerBuilder mDraweeControllerBuilder;
    private Handler mHander;
    private int mMoreButtonMarginLeft;
    private int mMoreButtonMarginRight;
    private JDCustomCardbannerItemViewManager.CardItemView3 mMoreButtonView;
    private int mMoreButtonWidth;
    private boolean mMoreShown;
    private MotionEvent mMotionEvent;
    private JDCustomCardbannerWithOutTouchViewManager2.OnMoreDrag mOnMoreDrag;
    private String mResizeMode;
    private int mRotate;
    private ImageView.ScaleType mScaleType;
    private int mTimeInteval;
    private int mTouchSlop;
    private int marginLeftSpace;
    private int marginRightSpace;
    private String mode;
    private GestureDetectorCompat moveDetector;
    private int paddingLeft;
    private int paddingRight;
    int position;
    private List<Status> releasedViewList;
    Bitmap right;
    private boolean scaleXY;
    private boolean shouldflipper;
    private View topLayout;
    private List<JDCustomCardbannerItemViewManager.CardItemView3> viewList;
    private int xOffsetStep;
    private int yOffsetStep;

    /* loaded from: classes13.dex */
    public class BannerTag {
        public int index;
        public boolean isMore = false;
        public JDCustomImageViewManager.JDReactSimpleDraweeView view;

        public BannerTag() {
        }
    }

    /* loaded from: classes13.dex */
    private class DragHelperCallback extends ViewDragHelper.Callback {
        private DragHelperCallback() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i2, int i3) {
            if (CardSlidePanelStyle5.this.cycle) {
                return i2;
            }
            int i4 = CardSlidePanelStyle5.this.isShowing + CardSlidePanelStyle5.this.index;
            if (i4 >= CardSlidePanelStyle5.this.viewList.size()) {
                i4 -= CardSlidePanelStyle5.this.viewList.size();
            }
            int size = (CardSlidePanelStyle5.this.viewList.size() - 1) / 2;
            View view2 = (View) CardSlidePanelStyle5.this.viewList.get(size);
            int width = view2.getWidth();
            int left = view2.getLeft() + i3;
            view2.getRight();
            if (i4 == 0) {
                if (CardSlidePanelStyle5.this.count != 1 || CardSlidePanelStyle5.this.mMoreButtonWidth == 0 || left <= (((CardSlidePanelStyle5.this.getWidth() - width) - CardSlidePanelStyle5.this.mMoreButtonWidth) - CardSlidePanelStyle5.this.mMoreButtonMarginLeft) - CardSlidePanelStyle5.this.mMoreButtonMarginRight) {
                    if (CardSlidePanelStyle5.this.count != 1 || CardSlidePanelStyle5.this.mMoreButtonWidth == 0 || left >= ((((CardSlidePanelStyle5.this.getWidth() - width) - CardSlidePanelStyle5.this.mMoreButtonWidth) - CardSlidePanelStyle5.this.mMoreButtonMarginLeft) - CardSlidePanelStyle5.this.mMoreButtonMarginRight) - CardSlidePanelStyle5.this.mMoreButtonWidth) {
                        if (CardSlidePanelStyle5.this.count == 1 && CardSlidePanelStyle5.this.mMoreButtonWidth != 0) {
                            return i2;
                        }
                        if (CardSlidePanelStyle5.this.mMoreButtonWidth == 0 || left <= CardSlidePanelStyle5.this.marginLeftSpace + CardSlidePanelStyle5.this.leftRightSpace) {
                            if (CardSlidePanelStyle5.this.mMoreButtonWidth == 0 && left > (CardSlidePanelStyle5.this.marginLeftSpace + width) - CardSlidePanelStyle5.this.marginLeftSpace) {
                                return i2 - (((left - CardSlidePanelStyle5.this.marginLeftSpace) - width) + CardSlidePanelStyle5.this.marginLeftSpace);
                            }
                        } else {
                            return i2 - ((left - CardSlidePanelStyle5.this.marginLeftSpace) + CardSlidePanelStyle5.this.leftRightSpace);
                        }
                    } else {
                        if (CardSlidePanelStyle5.this.mOnMoreDrag != null && !CardSlidePanelStyle5.this.mMoreShown) {
                            CardSlidePanelStyle5.this.mOnMoreDrag.onMoreShown();
                            CardSlidePanelStyle5.this.mMoreShown = true;
                        }
                        return i2 + ((((((CardSlidePanelStyle5.this.getWidth() - width) - CardSlidePanelStyle5.this.mMoreButtonWidth) - CardSlidePanelStyle5.this.mMoreButtonMarginLeft) - CardSlidePanelStyle5.this.mMoreButtonMarginRight) - CardSlidePanelStyle5.this.mMoreButtonWidth) - left);
                    }
                } else {
                    return i2 - (left - ((((CardSlidePanelStyle5.this.getWidth() - width) - CardSlidePanelStyle5.this.mMoreButtonWidth) - CardSlidePanelStyle5.this.mMoreButtonMarginLeft) - CardSlidePanelStyle5.this.mMoreButtonMarginRight));
                }
            }
            if (i4 == 1) {
                int left2 = ((View) CardSlidePanelStyle5.this.viewList.get(size - 1)).getLeft() + i3;
                if (CardSlidePanelStyle5.this.mMoreButtonWidth == 0 || left2 <= CardSlidePanelStyle5.this.marginLeftSpace + CardSlidePanelStyle5.this.leftRightSpace) {
                    if (CardSlidePanelStyle5.this.mMoreButtonWidth == 0 && left2 > (CardSlidePanelStyle5.this.marginLeftSpace + width) - CardSlidePanelStyle5.this.marginLeftSpace) {
                        return i2 - (((left2 - CardSlidePanelStyle5.this.marginLeftSpace) - width) + CardSlidePanelStyle5.this.marginLeftSpace);
                    }
                } else {
                    return i2 - ((left2 - CardSlidePanelStyle5.this.marginLeftSpace) + CardSlidePanelStyle5.this.leftRightSpace);
                }
            }
            if (i4 == CardSlidePanelStyle5.this.count - 1) {
                if (CardSlidePanelStyle5.this.mMoreButtonWidth == 0 || left >= ((((CardSlidePanelStyle5.this.getWidth() - width) - CardSlidePanelStyle5.this.mMoreButtonWidth) - CardSlidePanelStyle5.this.mMoreButtonMarginLeft) - CardSlidePanelStyle5.this.mMoreButtonMarginRight) - CardSlidePanelStyle5.this.mMoreButtonWidth) {
                    if (CardSlidePanelStyle5.this.mMoreButtonWidth == 0 && left < (CardSlidePanelStyle5.this.marginLeftSpace - width) + CardSlidePanelStyle5.this.marginRightSpace) {
                        return i2 + ((CardSlidePanelStyle5.this.marginLeftSpace - left) - width) + CardSlidePanelStyle5.this.marginRightSpace;
                    }
                } else {
                    if (CardSlidePanelStyle5.this.mOnMoreDrag != null && !CardSlidePanelStyle5.this.mMoreShown) {
                        CardSlidePanelStyle5.this.mOnMoreDrag.onMoreShown();
                        CardSlidePanelStyle5.this.mMoreShown = true;
                    }
                    return i2 + ((((((CardSlidePanelStyle5.this.getWidth() - width) - CardSlidePanelStyle5.this.mMoreButtonWidth) - CardSlidePanelStyle5.this.mMoreButtonMarginLeft) - CardSlidePanelStyle5.this.mMoreButtonMarginRight) - CardSlidePanelStyle5.this.mMoreButtonWidth) - left);
                }
            }
            if (i4 == CardSlidePanelStyle5.this.count - 2) {
                int left3 = ((View) CardSlidePanelStyle5.this.viewList.get(size + 1)).getLeft() + i3;
                if (CardSlidePanelStyle5.this.mMoreButtonWidth == 0 || left3 >= ((((CardSlidePanelStyle5.this.getWidth() - width) - CardSlidePanelStyle5.this.mMoreButtonWidth) - CardSlidePanelStyle5.this.mMoreButtonMarginLeft) - CardSlidePanelStyle5.this.mMoreButtonMarginRight) - CardSlidePanelStyle5.this.mMoreButtonWidth) {
                    return (CardSlidePanelStyle5.this.mMoreButtonWidth != 0 || left3 >= (CardSlidePanelStyle5.this.marginLeftSpace - width) + CardSlidePanelStyle5.this.marginRightSpace) ? i2 : i2 + ((CardSlidePanelStyle5.this.marginLeftSpace - left3) - width) + CardSlidePanelStyle5.this.marginRightSpace;
                }
                if (CardSlidePanelStyle5.this.mOnMoreDrag != null && !CardSlidePanelStyle5.this.mMoreShown) {
                    CardSlidePanelStyle5.this.mOnMoreDrag.onMoreShown();
                    CardSlidePanelStyle5.this.mMoreShown = true;
                }
                return i2 + ((((((CardSlidePanelStyle5.this.getWidth() - width) - CardSlidePanelStyle5.this.mMoreButtonWidth) - CardSlidePanelStyle5.this.mMoreButtonMarginLeft) - CardSlidePanelStyle5.this.mMoreButtonMarginRight) - CardSlidePanelStyle5.this.mMoreButtonWidth) - left3);
            }
            return i2;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i2, int i3) {
            return CardSlidePanelStyle5.this.itemMarginTop;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return 256;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            CardSlidePanelStyle5.this.onViewPosChangedRight(view, i4);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            CardSlidePanelStyle5.this.animToSide(view, (int) f2, (int) f3);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            if (CardSlidePanelStyle5.this.viewList == null || CardSlidePanelStyle5.this.btnLock || !(view instanceof JDCustomCardbannerItemViewManager.CardItemView3) || view.getTag() == null) {
                return false;
            }
            int indexOf = CardSlidePanelStyle5.this.viewList.indexOf(view);
            CardSlidePanelStyle5 cardSlidePanelStyle5 = CardSlidePanelStyle5.this;
            cardSlidePanelStyle5.mClickPosition = indexOf - ((cardSlidePanelStyle5.viewList.size() - 1) / 2);
            if (indexOf == -1) {
                CardSlidePanelStyle5.this.mClickPosition = 1;
            }
            return true;
        }
    }

    /* loaded from: classes13.dex */
    class MoveDetector extends GestureDetector.SimpleOnGestureListener {
        MoveDetector() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            return Math.abs(f3) + Math.abs(f2) > ((float) CardSlidePanelStyle5.this.mTouchSlop);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class Status {
        boolean block;
        boolean last;
        boolean left;
        View v;

        Status() {
        }
    }

    public CardSlidePanelStyle5(Context context) {
        this(context, null);
    }

    private void ajustLinkageViewItem(float f2, int i2, boolean z, int i3) {
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get(((r6.size() - 1) / 2) + i2);
        if (i2 != this.mClickPosition) {
            cardItemView3.layout(cardItemView3.getLeft() + i3, cardItemView3.getTop(), cardItemView3.getWidth() + cardItemView3.getLeft() + i3, cardItemView3.getHeight() + this.itemMarginTop);
            cardItemView3.meansureall(this.paddingLeft, this.paddingRight);
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
        cardItemView3.meansureall(this.paddingLeft, this.paddingRight);
    }

    private void ajustLinkageViewMore(float f2, JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3, int i2, boolean z) {
        cardItemView3.layout(cardItemView3.getLeft() + i2, cardItemView3.getTop(), cardItemView3.getWidth() + cardItemView3.getLeft() + i2, cardItemView3.getHeight() + this.itemMarginTop);
        cardItemView3.meansureall(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0165  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void animToSide(View view, int i2, int i3) {
        int i4;
        int i5;
        boolean z;
        boolean z2;
        int i6;
        int i7;
        int i8;
        CardSwitchListener cardSwitchListener;
        boolean z3 = true;
        int size = (this.viewList.size() - 1) / 2;
        this.viewList.get(size).getLeft();
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = (JDCustomCardbannerItemViewManager.CardItemView3) view;
        int left = this.viewList.get(size).getLeft() - this.initCenterViewX;
        int top = this.viewList.get(size).getTop() - this.initCenterViewY;
        int i9 = this.isShowing + this.index;
        if (i9 >= this.viewList.size()) {
            i9 -= this.viewList.size();
        }
        int i10 = this.leftRightSpace;
        int i11 = this.mClickPosition;
        int i12 = this.marginLeftSpace + i10 + ((this.childWith + i10) * i11);
        if (this.mMoreButtonWidth != 0 && i9 == this.count - 1 && i11 == 1) {
            i12 = (getWidth() - this.mMoreButtonMarginRight) - this.mMoreButtonWidth;
        }
        boolean z4 = false;
        if (left > X_DISTANCE_THRESHOLD && Math.abs(top) < left * 3.0f) {
            i5 = this.childWith + i12 + this.leftRightSpace;
            if (this.mMoreButtonWidth != 0 && i9 == this.count - 1 && this.mClickPosition == 1) {
                i5 = ((getWidth() + this.childWith) - this.marginRightSpace) + this.mMoreButtonMarginLeft;
            }
            i4 = ((top * (this.childWith + this.initCenterViewX)) / left) + this.initCenterViewY;
            if (cardItemView3.getLeft() > i5) {
                z = false;
                z2 = true;
            } else {
                z = false;
                z2 = false;
            }
            i6 = 1;
        } else {
            if (left < (-X_DISTANCE_THRESHOLD)) {
                int i13 = -left;
                if (Math.abs(top) < i13 * 3.0f) {
                    int i14 = this.childWith;
                    int i15 = i12 - (this.leftRightSpace + i14);
                    if (i9 == this.count - 2 && (i7 = this.mMoreButtonWidth) != 0) {
                        i15 = ((((this.marginRightSpace - i7) - this.mMoreButtonMarginLeft) - this.mMoreButtonMarginRight) - i14) + i12;
                    }
                    i4 = ((top * (i14 + this.initCenterViewX)) / i13) + this.initCenterViewY;
                    i5 = i15;
                    if (cardItemView3.getLeft() < i15) {
                        z = true;
                        z2 = false;
                    } else {
                        z = true;
                        z2 = true;
                    }
                    i6 = 0;
                }
            }
            orderViewStackleft();
            return;
        }
        if (!this.cycle) {
            if (i9 == this.count - 1 && z) {
                if (z) {
                    if (this.mMoreButtonWidth != 0 && this.mClickPosition != 1) {
                        int width = ((getWidth() - this.mMoreButtonWidth) - this.mMoreButtonMarginLeft) - this.mMoreButtonMarginRight;
                        int i16 = this.childWith;
                        i12 = (width - i16) + (this.mClickPosition * (i16 + this.leftRightSpace));
                    }
                    z3 = false;
                    z2 = false;
                    z4 = true;
                }
            } else if (i9 == 0 && !z && !z) {
                z2 = true;
                z4 = true;
            }
            i8 = this.allHeight;
            if (i4 <= i8 && i4 < (-i8) / 2) {
                int i17 = (-i8) / 2;
            }
            Status status = new Status();
            status.left = z3;
            status.v = cardItemView3;
            status.last = z2;
            status.block = z4;
            this.releasedViewList.clear();
            this.releasedViewList.add(status);
            if (this.mDragHelper.smoothSlideViewTo(cardItemView3, i12, this.itemMarginTop)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            if (i6 >= 0 || (cardSwitchListener = this.cardSwitchListener) == null) {
            }
            cardSwitchListener.onCardVanish(this.isShowing, i6);
            return;
        }
        z3 = z;
        i12 = i5;
        i8 = this.allHeight;
        if (i4 <= i8) {
            int i172 = (-i8) / 2;
        }
        Status status2 = new Status();
        status2.left = z3;
        status2.v = cardItemView3;
        status2.last = z2;
        status2.block = z4;
        this.releasedViewList.clear();
        this.releasedViewList.add(status2);
        if (this.mDragHelper.smoothSlideViewTo(cardItemView3, i12, this.itemMarginTop)) {
        }
        if (i6 >= 0) {
        }
    }

    private void hideSomeItem() {
        if (this.cycle) {
            return;
        }
        int i2 = this.isShowing + this.index;
        if (i2 >= this.viewList.size()) {
            i2 -= this.viewList.size();
        }
        int size = (this.viewList.size() - 1) / 2;
        if (i2 == 1) {
            int i3 = size - 2;
            if (i3 < 0) {
                i3 += this.viewList.size();
            }
            this.viewList.get(i3).setVisibility(4);
        } else if (i2 == 2) {
            int i4 = size - 3;
            if (i4 < 0) {
                i4 += this.viewList.size();
            }
            this.viewList.get(i4).setVisibility(0);
        }
        if (i2 == this.viewList.size() - 2) {
            int i5 = size + 2;
            if (i5 >= this.viewList.size()) {
                i5 -= this.viewList.size();
            }
            this.viewList.get(i5).setVisibility(4);
        } else if (i2 == this.viewList.size() - 3) {
            int i6 = size + 3;
            if (i6 >= this.viewList.size()) {
                i6 -= this.viewList.size();
            }
            this.viewList.get(i6).setVisibility(0);
        }
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
            cardItemView32.layout(-(((((this.viewList.size() - 1) / 2) * this.childWith) + this.leftRightSpace) - this.marginLeftSpace), i3, (-(((((this.viewList.size() - 1) / 2) * i4) + this.leftRightSpace) - this.marginLeftSpace)) + i4, this.itemMarginTop + cardItemView32.getHeight());
            cardItemView32.meansureall(this.paddingLeft, this.paddingRight);
        }
        this.changed = 0;
        this.viewList.remove(cardItemView32);
        this.viewList.add(0, cardItemView32);
        this.releasedViewList.remove(0);
        JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView = this.draweeView;
        boolean z = jDReactSimpleDraweeView.left;
        JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView2 = this.draweeView1;
        jDReactSimpleDraweeView.left = jDReactSimpleDraweeView2.left;
        jDReactSimpleDraweeView2.left = z;
        removeView(jDReactSimpleDraweeView);
        addView(this.draweeView, 0, new ViewGroup.LayoutParams(getWidth(), getHeight()));
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
        hideSomeItem();
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
        if (this.releasedViewList.get(0).block) {
            this.releasedViewList.remove(0);
            resizeLayout();
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
            JDCustomCardbannerItemViewManager.CardItemView3 cardItemView32 = this.viewList.get(0);
            if (this.changed == 0) {
                cardItemView32.layout((((this.viewList.size() - 1) / 2) * this.childWith) + (this.leftRightSpace * 3) + this.marginLeftSpace, this.itemMarginTop, ((((this.viewList.size() - 1) / 2) + 1) * this.childWith) + (this.leftRightSpace * 3) + this.marginLeftSpace, this.itemMarginTop + cardItemView32.getHeight());
                cardItemView32.meansureall(this.paddingLeft, this.paddingRight);
            }
            this.changed = 0;
            this.viewList.remove(cardItemView32);
            this.viewList.add(cardItemView32);
            this.releasedViewList.remove(0);
            removeView(this.draweeView);
            addView(this.draweeView, 0, new ViewGroup.LayoutParams(getWidth(), getHeight()));
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
            hideSomeItem();
            if (this.shouldflipper) {
                this.mHander.removeMessages(1001);
                this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
            }
        }
    }

    private void processLinkageView(View view, boolean z, int i2) {
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3;
        int size = (this.viewList.size() - 1) / 2;
        int left = this.viewList.get(size).getLeft();
        this.viewList.get(size).getTop();
        int i3 = left - this.initCenterViewX;
        int i4 = this.isShowing + this.index;
        if (i4 >= this.viewList.size()) {
            i4 -= this.viewList.size();
        }
        if (this.mMoreButtonWidth != 0 && i4 == this.count - 1) {
            i3 = this.viewList.get(size).getLeft() - ((((getWidth() - this.childWith) - this.mMoreButtonWidth) - this.mMoreButtonMarginLeft) - this.mMoreButtonMarginRight);
        }
        int i5 = this.mMoreButtonWidth;
        if (i5 != 0 && i4 == this.count - 1) {
            this.MAX_SLIDE_DISTANCE_LINKAGE = Math.abs((((this.childWith + i5) + this.mMoreButtonMarginLeft) + this.mMoreButtonMarginRight) - this.marginRightSpace);
        } else if (i5 == 0 || i4 != this.count - 2) {
            if (i5 != 0) {
                this.MAX_SLIDE_DISTANCE_LINKAGE = this.childWith;
            }
        } else if (i3 < 0) {
            this.MAX_SLIDE_DISTANCE_LINKAGE = Math.abs((((this.childWith + i5) + this.mMoreButtonMarginLeft) + this.mMoreButtonMarginRight) - this.marginRightSpace);
        }
        float f2 = i3 / this.MAX_SLIDE_DISTANCE_LINKAGE;
        if (f2 > 1.0f) {
            f2 = 1.0f;
        } else if (f2 < -1.0f) {
            f2 = -1.0f;
        }
        float abs = 1.0f - (SCALE_STEP * Math.abs(f2));
        float abs2 = 1.0f - (SCALE_STEP_LEFT * Math.abs(f2));
        float f3 = f2 < 0.0f ? 1.0f : 1.0f - f2;
        float f4 = 1.0f - (SCALE_STEP * f3);
        float abs3 = 1.0f - (SCALE_STEP_LEFT * Math.abs(f3));
        float f5 = 1.0f - (SCALE_STEP * (f2 < 0.0f ? f2 + 1.0f : 1.0f));
        for (int i6 = 0; i6 < size - 1; i6++) {
            ajustLinkageViewItemPosition(i6 - size, z, i2);
        }
        if (this.viewList.size() >= 3) {
            ajustLinkageViewItem(abs3, -1, z, i2);
        }
        if (i3 < 0) {
            abs = abs2;
        }
        ajustLinkageViewItem(abs, 0, z, i2);
        if (this.viewList.size() > 2) {
            ajustLinkageViewItem(f5, 1, z, i2);
        } else if (this.viewList.size() == 2) {
            if (this.changed == 1) {
                ajustLinkageViewItem(f4, 1, z, i2);
            } else {
                ajustLinkageViewItem(f5, 1, z, i2);
            }
        }
        int i7 = this.count;
        if (i7 >= 1 && ((this.mClickPosition != 1 || i4 != i7 - 1) && (cardItemView3 = this.mMoreButtonView) != null && cardItemView3.getParent() != null)) {
            int i8 = this.count;
            if (i4 == i8 - 1) {
                ajustLinkageViewMore(f5, this.mMoreButtonView, i2, true);
            } else if (i4 == i8 - 2) {
                ajustLinkageViewMore(f5, this.mMoreButtonView, i2, false);
            }
        }
        for (int i9 = size + 2; i9 < this.viewList.size(); i9++) {
            ajustLinkageViewItemPosition(i9 - size, z, i2);
        }
        if (this.viewList.size() < 5 && this.viewList.size() > 2) {
            List<JDCustomCardbannerItemViewManager.CardItemView3> list = this.viewList;
            JDCustomCardbannerItemViewManager.CardItemView3 cardItemView32 = list.get(list.size() / 2);
            if ((cardItemView32.getLeft() - this.leftRightSpace) - cardItemView32.getWidth() >= -5 && this.changed == 0) {
                List<JDCustomCardbannerItemViewManager.CardItemView3> list2 = this.viewList;
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView33 = list2.get(list2.size() - 1);
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView34 = this.viewList.get(0);
                this.changed++;
                cardItemView33.layout((cardItemView34.getLeft() - this.leftRightSpace) - cardItemView33.getWidth(), this.itemMarginTop, cardItemView34.getLeft() - this.leftRightSpace, this.itemMarginTop + cardItemView33.getHeight());
                cardItemView33.meansureall(this.paddingLeft, this.paddingRight);
            } else if (cardItemView32.getRight() + this.leftRightSpace + cardItemView32.getWidth() < getWidth() + 5 && this.changed == 0) {
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView35 = this.viewList.get(0);
                List<JDCustomCardbannerItemViewManager.CardItemView3> list3 = this.viewList;
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView36 = list3.get(list3.size() - 1);
                this.changed += 2;
                cardItemView35.layout(cardItemView36.getRight() + this.leftRightSpace, this.itemMarginTop, cardItemView36.getRight() + this.leftRightSpace + cardItemView35.getWidth(), this.itemMarginTop + cardItemView35.getHeight());
                cardItemView35.meansureall(this.paddingLeft, this.paddingRight);
            }
            if ((cardItemView32.getLeft() - this.leftRightSpace) - cardItemView32.getWidth() < -5 && this.changed == 1) {
                List<JDCustomCardbannerItemViewManager.CardItemView3> list4 = this.viewList;
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView37 = list4.get(list4.size() - 1);
                List<JDCustomCardbannerItemViewManager.CardItemView3> list5 = this.viewList;
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView38 = list5.get(list5.size() - 2);
                this.changed--;
                cardItemView37.layout(cardItemView38.getRight() + this.leftRightSpace, this.itemMarginTop, cardItemView38.getRight() + this.leftRightSpace + cardItemView37.getWidth(), this.itemMarginTop + cardItemView37.getHeight());
                cardItemView37.meansureall(this.paddingLeft, this.paddingRight);
            } else if (cardItemView32.getRight() + this.leftRightSpace + cardItemView32.getWidth() >= getWidth() + 5 && this.changed == 2) {
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView39 = this.viewList.get(0);
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView310 = this.viewList.get(1);
                this.changed -= 2;
                cardItemView39.layout((cardItemView310.getLeft() - this.leftRightSpace) - cardItemView39.getWidth(), this.itemMarginTop, cardItemView310.getLeft() - this.leftRightSpace, this.itemMarginTop + cardItemView39.getHeight());
                cardItemView39.meansureall(this.paddingLeft, this.paddingRight);
            }
        } else if (this.viewList.size() == 2) {
            if (i3 < 0 && this.changed == 1) {
                List<JDCustomCardbannerItemViewManager.CardItemView3> list6 = this.viewList;
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView311 = list6.get(list6.size() - 1);
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView312 = this.viewList.get(0);
                this.changed--;
                cardItemView311.layout(cardItemView312.getRight() + this.leftRightSpace, this.itemMarginTop, cardItemView312.getRight() + this.leftRightSpace + cardItemView311.getWidth(), this.itemMarginTop + cardItemView311.getHeight());
                cardItemView311.meansureall(this.paddingLeft, this.paddingRight);
            } else if (i3 > 0 && this.changed == 0) {
                List<JDCustomCardbannerItemViewManager.CardItemView3> list7 = this.viewList;
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView313 = list7.get(list7.size() - 1);
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView314 = this.viewList.get(0);
                this.changed++;
                cardItemView313.layout((cardItemView314.getLeft() - this.leftRightSpace) - cardItemView313.getWidth(), this.itemMarginTop, cardItemView314.getLeft() - this.leftRightSpace, this.itemMarginTop + cardItemView313.getHeight());
                cardItemView313.meansureall(this.paddingLeft, this.paddingRight);
            }
        }
    }

    private Bitmap setdraweeviewBackground(int i2, final JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView, Bitmap bitmap) {
        JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView2;
        Object tag = this.viewList.get(i2).getTag();
        if (tag != null && this.viewList.get(i2).getVisibility() != 0) {
            if (jDReactSimpleDraweeView != null) {
                jDReactSimpleDraweeView.setImageBitmap(null);
                jDReactSimpleDraweeView.mBitmapCached = null;
            }
            return null;
        }
        if (jDReactSimpleDraweeView != null) {
            jDReactSimpleDraweeView.setAlpha(this.mAlpha);
        }
        if (jDReactSimpleDraweeView != null && bitmap != null && !bitmap.isRecycled()) {
            jDReactSimpleDraweeView.setImageBitmap(bitmap);
            Bitmap bitmap2 = jDReactSimpleDraweeView.mBitmapCached;
            if (bitmap2 != null) {
                bitmap2.isRecycled();
            }
            jDReactSimpleDraweeView.mBitmapCached = bitmap;
            return null;
        }
        if (tag != null && (jDReactSimpleDraweeView2 = ((BannerTag) tag).view) != null) {
            Drawable drawable = jDReactSimpleDraweeView2.getDrawable(new JDCustomImageViewManager.BitMapCallback() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle5.2
                @Override // com.jingdong.jdreact.plugin.banner.JDCustomImageViewManager.BitMapCallback
                public void onResult(Drawable drawable2) {
                    if (drawable2 == null || !(drawable2 instanceof BitmapDrawable)) {
                        return;
                    }
                    JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView3 = jDReactSimpleDraweeView;
                    if (jDReactSimpleDraweeView3 != null) {
                        jDReactSimpleDraweeView3.setScaleType(CardSlidePanelStyle5.this.mScaleType);
                    }
                    Bitmap bitmap3 = ((BitmapDrawable) drawable2).getBitmap();
                    if (CardSlidePanelStyle5.this.mBlur > 1) {
                        Bitmap doBlur = FastBlur.doBlur(bitmap3, CardSlidePanelStyle5.this.mBlur, false);
                        JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView4 = jDReactSimpleDraweeView;
                        if (jDReactSimpleDraweeView4 != null) {
                            jDReactSimpleDraweeView4.setImageBitmap(doBlur);
                        }
                        JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView5 = jDReactSimpleDraweeView;
                        if (jDReactSimpleDraweeView5 != null) {
                            Bitmap bitmap4 = jDReactSimpleDraweeView5.mBitmapCached;
                            if (bitmap4 != null && !bitmap4.isRecycled()) {
                                bitmap4.recycle();
                            }
                            jDReactSimpleDraweeView.mBitmapCached = doBlur;
                            return;
                        }
                        return;
                    }
                    JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView6 = jDReactSimpleDraweeView;
                    if (jDReactSimpleDraweeView6 != null) {
                        jDReactSimpleDraweeView6.setImageBitmap(bitmap3);
                    }
                }
            });
            if (drawable != null && (drawable instanceof BitmapDrawable)) {
                if (jDReactSimpleDraweeView != null) {
                    jDReactSimpleDraweeView.setScaleType(this.mScaleType);
                }
                Bitmap bitmap3 = ((BitmapDrawable) drawable).getBitmap();
                int i3 = this.mBlur;
                if (i3 > 1) {
                    Bitmap doBlur = FastBlur.doBlur(bitmap3, i3, false);
                    if (jDReactSimpleDraweeView != null) {
                        jDReactSimpleDraweeView.setImageBitmap(doBlur);
                        Bitmap bitmap4 = jDReactSimpleDraweeView.mBitmapCached;
                        if (bitmap4 != null) {
                            bitmap4.isRecycled();
                        }
                        jDReactSimpleDraweeView.mBitmapCached = doBlur;
                    }
                    return doBlur;
                } else if (jDReactSimpleDraweeView != null) {
                    jDReactSimpleDraweeView.setImageBitmap(bitmap3);
                }
            } else if (jDReactSimpleDraweeView != null) {
                jDReactSimpleDraweeView.setImageBitmap(null);
                jDReactSimpleDraweeView.mBitmapCached = null;
            }
        }
        return null;
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
        JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView = this.draweeView;
        if (jDReactSimpleDraweeView != null) {
            removeView(jDReactSimpleDraweeView);
        }
        JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView2 = this.draweeView1;
        if (jDReactSimpleDraweeView2 != null) {
            removeView(jDReactSimpleDraweeView2);
        }
        JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView3 = this.fdraweeView;
        if (jDReactSimpleDraweeView3 != null) {
            removeView(jDReactSimpleDraweeView3);
        }
        View view = this.topLayout;
        if (view != null) {
            removeView(view);
        }
        Iterator<JDCustomCardbannerItemViewManager.CardItemView3> it = this.mArrayList.iterator();
        while (it.hasNext()) {
            removeView(it.next());
        }
        this.mArrayList.clear();
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

    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        if (this.mDraweeControllerBuilder == null) {
            this.mDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
        return this.mDraweeControllerBuilder;
    }

    public JDCustomImageViewManager.JDReactSimpleDraweeView getDraweeView(ViewGroup viewGroup) {
        JDCustomImageViewManager.JDReactSimpleDraweeView draweeView;
        if (viewGroup != null && viewGroup.getChildCount() != 0) {
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt instanceof JDCustomImageViewManager.JDReactSimpleDraweeView) {
                    return (JDCustomImageViewManager.JDReactSimpleDraweeView) childAt;
                }
                if ((childAt instanceof ViewGroup) && (draweeView = getDraweeView((ViewGroup) childAt)) != null) {
                    return draweeView;
                }
            }
        }
        return null;
    }

    public void inflateAgain() {
        this.mHander.removeMessages(1002);
        this.mHander.sendEmptyMessageDelayed(1002, 50L);
    }

    public void inflateAgainNow() {
        this.isShowing = 0;
        this.left = null;
        this.right = null;
        inflatelayout(6);
        if (this.viewList.size() != 0) {
            resizeLayout();
        }
    }

    public void inflatelayout(int i2) {
        if (getChildCount() <= 0) {
            return;
        }
        this.count = getChildCount();
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = (JDCustomCardbannerItemViewManager.CardItemView3) getChildAt(0);
        this.backGroundView = cardItemView3;
        if (cardItemView3 != null) {
            removeView(cardItemView3);
            this.count--;
        }
        if (this.mMoreButtonWidth != 0) {
            JDCustomCardbannerItemViewManager.CardItemView3 cardItemView32 = (JDCustomCardbannerItemViewManager.CardItemView3) getChildAt(getChildCount() - 1);
            this.mMoreButtonView = cardItemView32;
            cardItemView32.setTag(new Object());
            removeView(this.mMoreButtonView);
            this.count--;
        }
        if (!this.cycle && this.count < 5) {
            for (int i3 = 0; i3 < 5 - this.count; i3++) {
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView33 = new JDCustomCardbannerItemViewManager.CardItemView3(getContext());
                this.mArrayList.add(cardItemView33);
                addView(cardItemView33);
            }
        }
        addView(new JDCustomCardbannerItemViewManager.CardItemView3(getContext()), new ViewGroup.LayoutParams(((getWidth() - (this.leftRightSpace * 2)) - this.marginLeftSpace) - this.marginRightSpace, getHeight()));
        this.viewList.clear();
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (i4 == childCount - 1) {
                this.topLayout = childAt;
                ((JDCustomCardbannerItemViewManager.CardItemView3) childAt).setParentView(this);
                this.topLayout.bringToFront();
            } else {
                int i5 = i4 - ((childCount - 2) / 2);
                if (i5 < 0) {
                    i5 = (i5 + childCount) - 1;
                }
                JDCustomCardbannerItemViewManager.CardItemView3 cardItemView34 = (JDCustomCardbannerItemViewManager.CardItemView3) getChildAt(i5);
                cardItemView34.setParentView(this);
                cardItemView34.setAlpha(1.0f);
                cardItemView34.setVisibility(0);
                JDCustomImageViewManager.JDReactSimpleDraweeView draweeView = getDraweeView(cardItemView34);
                if (draweeView != null) {
                    BannerTag bannerTag = new BannerTag();
                    bannerTag.index = i4;
                    bannerTag.view = draweeView;
                    cardItemView34.setTag(bannerTag);
                }
                this.viewList.add(cardItemView34);
            }
        }
        int i6 = this.index;
        int i7 = this.count;
        if (i6 >= i7) {
            this.index = i7 - 1;
        }
        if (this.index < 0) {
            this.index = 0;
        }
        if (this.index != 0 && this.viewList.size() > 0) {
            ArrayList arrayList = new ArrayList();
            int size = this.viewList.size();
            for (int i8 = this.index; i8 < size; i8++) {
                arrayList.add(this.viewList.get(i8));
            }
            for (int i9 = 0; i9 < this.index; i9++) {
                arrayList.add(this.viewList.get(i9));
            }
            this.viewList = arrayList;
        }
        int size2 = (this.viewList.size() - 1) / 2;
        if (!this.cycle) {
            int i10 = this.index;
            if (i10 == 0) {
                this.viewList.get(size2 - 1).setVisibility(4);
            } else if (i10 == this.count) {
                this.viewList.get(size2 + 1).setVisibility(4);
            }
        }
        CardSwitchListener cardSwitchListener = this.cardSwitchListener;
        if (cardSwitchListener != null) {
            cardSwitchListener.onShow(this.index + 0);
        }
        addView(this.backGroundView, 0);
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView35 = this.mMoreButtonView;
        if (cardItemView35 != null && cardItemView35.getParent() == null) {
            addView(this.mMoreButtonView);
        }
        this.fdraweeView = new JDCustomImageViewManager.JDReactSimpleDraweeView(getContext());
        addView(this.fdraweeView, 0, new ViewGroup.LayoutParams(getWidth(), getHeight()));
        this.draweeView = new JDCustomImageViewManager.JDReactSimpleDraweeView(getContext());
        addView(this.draweeView, 0, new ViewGroup.LayoutParams(getWidth(), getHeight()));
        this.draweeView1 = new JDCustomImageViewManager.JDReactSimpleDraweeView(getContext());
        addView(this.draweeView1, 0, new ViewGroup.LayoutParams(getWidth(), getHeight()));
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
        this.mMotionEvent = motionEvent;
        if (motionEvent.getActionMasked() == 0) {
            this.mMoreShown = false;
            this.mActionDownPosition = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (y < this.itemMarginTop || y > getHeight() - this.itemMarginBttom) {
                return false;
            }
            JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3 = this.viewList.get((this.viewList.size() - 1) / 2);
            if (this.mActionDownPosition < cardItemView3.getLeft() && this.mActionDownPosition >= 0) {
                this.mClickPosition = -1;
            } else if (this.mActionDownPosition > cardItemView3.getRight() && (i2 = this.mActionDownPosition) >= 0 && i2 < getWidth()) {
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
        if (shouldInterceptTouchEvent && onTouchEvent) {
            NativeGestureUtil.notifyNativeGestureStarted(this, this.mMotionEvent);
        }
        return shouldInterceptTouchEvent && onTouchEvent;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.viewList.size() == 0) {
            inflatelayout(6);
        }
        if (this.viewList.size() != 0) {
            resizeLayout();
        }
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
        int y;
        this.mMotionEvent = motionEvent;
        if (motionEvent.getAction() != 0 || ((y = (int) motionEvent.getY()) >= this.itemMarginTop && y <= getHeight() - this.itemMarginBttom)) {
            if (motionEvent.getAction() == 1 && this.isDraging) {
                this.isDraging = false;
            }
            try {
                this.mDragHelper.processTouchEvent(motionEvent);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onViewPosChangedRight(View view, int i2) {
        boolean z;
        double alpha;
        if (this.releasedViewList.size() != 0) {
            this.position += i2;
            boolean z2 = this.releasedViewList.get(0).last;
            if ((this.position < 0 && !this.releasedViewList.get(0).last) || (this.position > 0 && this.releasedViewList.get(0).last)) {
                z = true;
                if (z) {
                    processLinkageView(view, view.getLeft() >= 0, this.position);
                    if (this.releasedViewList.size() >= 0) {
                        double d = this.childWith;
                        int size = (this.viewList.size() - 1) / 2;
                        double left = (this.viewList.get(size).getLeft() - this.marginLeftSpace) - this.leftRightSpace;
                        int i3 = this.isShowing + this.index;
                        if (i3 >= this.viewList.size()) {
                            i3 -= this.viewList.size();
                        }
                        int i4 = this.mMoreButtonWidth;
                        if (i4 != 0 && i3 == this.count - 1) {
                            int left2 = this.viewList.get(size).getLeft();
                            int width = getWidth();
                            int i5 = this.childWith;
                            int i6 = this.mMoreButtonWidth;
                            int i7 = this.mMoreButtonMarginLeft;
                            int i8 = this.mMoreButtonMarginRight;
                            double d2 = left2 - ((((width - i5) - i6) - i7) - i8);
                            d = Math.abs((((i5 + i6) + i7) + i8) - this.marginRightSpace);
                            left = d2;
                        } else if (i4 != 0 && i3 == this.count - 2) {
                            d = Math.abs((((this.childWith + i4) + this.mMoreButtonMarginLeft) + this.mMoreButtonMarginRight) - this.marginRightSpace);
                        }
                        JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView = this.draweeView1;
                        if (jDReactSimpleDraweeView.mBitmapCached == null) {
                            if (left <= 0.0d) {
                                jDReactSimpleDraweeView.left = true;
                                if (i3 + 1 != this.count || this.cycle) {
                                    setdraweeviewBackground(size + 1, jDReactSimpleDraweeView, this.right);
                                }
                            } else {
                                jDReactSimpleDraweeView.left = false;
                                if (i3 != 0 || this.cycle) {
                                    setdraweeviewBackground(size - 1, jDReactSimpleDraweeView, this.left);
                                }
                            }
                        } else if (left < 0.0d && !jDReactSimpleDraweeView.left) {
                            jDReactSimpleDraweeView.left = true;
                            if (i3 + 1 != this.count || this.cycle) {
                                setdraweeviewBackground(size + 1, jDReactSimpleDraweeView, this.right);
                            }
                        } else if (left > 0.0d && jDReactSimpleDraweeView.left) {
                            jDReactSimpleDraweeView.left = false;
                            if (i3 != 0 || this.cycle) {
                                setdraweeviewBackground(size - 1, jDReactSimpleDraweeView, this.left);
                            }
                        }
                        double abs = Math.abs(left) / d;
                        alpha = 1.0d;
                        double d3 = this.mAlpha;
                        Double.isNaN(d3);
                        double d4 = (1.0d - abs) * d3;
                        double d5 = d4 >= 0.0d ? d4 : 0.0d;
                        if (d5 <= 1.0d) {
                            alpha = d5;
                        }
                    } else {
                        alpha = this.draweeView.getAlpha() / 2.0f;
                    }
                    float f2 = (float) alpha;
                    this.draweeView.setAlpha(f2);
                    this.draweeView1.setAlpha(this.mAlpha - f2);
                    this.cardSwitchListener.onViewScroll(alpha);
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

    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v27, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r6v29 */
    /* JADX WARN: Type inference failed for: r6v30 */
    /* JADX WARN: Type inference failed for: r6v31 */
    /* JADX WARN: Type inference failed for: r6v32 */
    public void resizeLayout() {
        ?? r6;
        int i2;
        JDCustomCardbannerItemViewManager.CardItemView3 cardItemView3;
        int size = this.viewList.size();
        if (size < 3) {
            return;
        }
        int i3 = (size - 1) / 2;
        int i4 = this.isShowing + this.index;
        if (i4 >= this.viewList.size()) {
            i4 -= this.viewList.size();
        }
        int width = ((getWidth() - (this.leftRightSpace * 2)) - this.marginRightSpace) - this.marginLeftSpace;
        int i5 = 0;
        for (int i6 = 0; i6 < size; i6++) {
            JDCustomCardbannerItemViewManager.CardItemView3 cardItemView32 = this.viewList.get(i6);
            cardItemView32.getMeasuredHeight();
            int width2 = (getWidth() - cardItemView32.getMeasuredWidth()) / 2;
            int i7 = i6 - i3;
            int i8 = this.count;
            if (i8 >= 2 && this.mMoreButtonWidth != 0) {
                if (i4 == i8 - 1 && i7 == 1) {
                    JDCustomCardbannerItemViewManager.CardItemView3 cardItemView33 = this.mMoreButtonView;
                    if (cardItemView33 != null) {
                        if (cardItemView33.getParent() == null) {
                            addView(this.mMoreButtonView);
                        }
                        if (this.mMoreButtonView.getParent() != null) {
                            i5 = i5 + width + this.marginLeftSpace + this.mMoreButtonMarginLeft;
                            this.mMoreButtonView.layout(i5, this.itemMarginTop, this.mMoreButtonWidth + i5, getHeight() - this.itemMarginBttom);
                            cardItemView32.meansureall(0);
                        }
                    }
                } else if (i4 == i8 - 2 && i7 == 2) {
                    JDCustomCardbannerItemViewManager.CardItemView3 cardItemView34 = this.mMoreButtonView;
                    if (cardItemView34 != null) {
                        if (cardItemView34.getParent() == null) {
                            addView(this.mMoreButtonView);
                        }
                        if (this.mMoreButtonView.getParent() != null) {
                            i5 = i5 + width + this.marginLeftSpace + this.mMoreButtonMarginLeft;
                            this.mMoreButtonView.layout(i5, this.itemMarginTop, this.mMoreButtonWidth + i5, getHeight() - this.itemMarginBttom);
                            cardItemView32.meansureall(0);
                        }
                    }
                } else if (i4 != i8 - 2 && i4 != i8 - 1 && this.mMoreButtonView.getParent() != null) {
                    this.mMoreButtonView.layout(0, 0, 0, 0);
                }
            }
            int i9 = this.marginLeftSpace;
            int i10 = this.leftRightSpace;
            int i11 = i9 + i10;
            if (i5 == 0) {
                i2 = (this.mMoreButtonWidth == 0 || i4 != this.count - 1) ? ((i10 + width) * i7) + i11 : ((((((i10 + width) * i7) + getWidth()) - this.mMoreButtonWidth) - this.mMoreButtonMarginRight) - this.mMoreButtonMarginLeft) - width;
            } else {
                i2 = i10 + width + i5;
            }
            cardItemView32.layout(i2, this.itemMarginTop, (((getWidth() + i2) - (this.leftRightSpace * 2)) - this.marginRightSpace) - this.marginLeftSpace, getHeight() - this.itemMarginBttom);
            int i12 = (i7 > 1 || i7 < -1) ? 1 : i7;
            cardItemView32.meansureall(this.paddingLeft, this.paddingRight);
            if (this.mode.equals(TYPE_BANNER_PAGER)) {
                float abs = 1.0f - (SCALE_STEP * Math.abs(i12));
                if (i7 < 0) {
                    abs = 1.0f - (SCALE_STEP_LEFT * Math.abs(i12));
                }
                cardItemView32.setScaleY(abs);
                if (this.scaleXY) {
                    cardItemView32.setScaleX(abs);
                }
            }
            int i13 = this.count;
            if (i13 == 1 && this.mMoreButtonWidth != 0 && i4 == i13 - 1 && i7 == 0 && (cardItemView3 = this.mMoreButtonView) != null) {
                if (cardItemView3.getParent() == null) {
                    addView(this.mMoreButtonView);
                }
                if (this.mMoreButtonView.getParent() != null) {
                    i2 = i2 + width + this.marginLeftSpace;
                    this.mMoreButtonView.layout(i2, this.itemMarginTop, this.mMoreButtonWidth + i2, getHeight() - this.itemMarginBttom);
                }
            }
            i5 = i2;
        }
        this.topLayout.layout(0, 0, 0, 0);
        this.draweeView = (JDCustomImageViewManager.JDReactSimpleDraweeView) getChildAt(1);
        this.draweeView1 = (JDCustomImageViewManager.JDReactSimpleDraweeView) getChildAt(0);
        JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView = (JDCustomImageViewManager.JDReactSimpleDraweeView) getChildAt(2);
        this.fdraweeView = jDReactSimpleDraweeView;
        jDReactSimpleDraweeView.layout(0, 0, getWidth(), getHeight());
        this.fdraweeView.setBackgroundColor(this.forgroudColor);
        this.draweeView.layout(0, 0, getWidth(), getHeight());
        this.draweeView1.layout(0, 0, getWidth(), getHeight());
        this.backGroundView.layout(0, getHeight() - this.backHeight, getWidth(), getHeight());
        this.backGroundView.meansureall();
        this.draweeView.setAlpha(this.mAlpha);
        this.draweeView1.setAlpha(0.0f);
        this.draweeView.setPivotX(r1.getWidth() / 2);
        this.draweeView.setPivotY(r1.getHeight() / 2);
        this.draweeView.setRotation(this.mRotate);
        this.draweeView1.setPivotX(r1.getWidth() / 2);
        this.draweeView1.setPivotY(r1.getHeight() / 2);
        this.draweeView1.setRotation(this.mRotate);
        this.draweeView.setScaleType(this.mScaleType);
        this.draweeView1.setScaleType(this.mScaleType);
        JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView2 = this.draweeView;
        if (jDReactSimpleDraweeView2.mBitmapCached == null) {
            setdraweeviewBackground(i3, jDReactSimpleDraweeView2, null);
        }
        Bitmap bitmap = this.draweeView1.mBitmapCached;
        if (bitmap != null && !bitmap.isRecycled()) {
            JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView3 = this.draweeView1;
            if (!jDReactSimpleDraweeView3.left) {
                this.right = jDReactSimpleDraweeView3.mBitmapCached;
                Bitmap bitmap2 = this.left;
                if (bitmap2 != null) {
                    bitmap2.isRecycled();
                }
                r6 = 1;
                r6 = 1;
                if (i4 - 1 == 0 && !this.cycle) {
                    this.left = null;
                } else {
                    this.left = setdraweeviewBackground(i3 - 1, null, null);
                }
            } else {
                r6 = 1;
                r6 = 1;
                this.left = jDReactSimpleDraweeView3.mBitmapCached;
                Bitmap bitmap3 = this.right;
                if (bitmap3 != null) {
                    bitmap3.isRecycled();
                }
                if (i4 + 1 == this.count && !this.cycle) {
                    this.right = null;
                } else {
                    this.right = setdraweeviewBackground(i3 + 1, null, null);
                }
            }
        } else {
            r6 = 1;
            this.left = setdraweeviewBackground(i3 - 1, null, null);
            this.right = setdraweeviewBackground(i3 + 1, null, null);
        }
        JDCustomImageViewManager.JDReactSimpleDraweeView jDReactSimpleDraweeView4 = this.draweeView1;
        jDReactSimpleDraweeView4.mBitmapCached = null;
        jDReactSimpleDraweeView4.setImageBitmap(null);
        this.draweeView.left = r6;
        this.draweeView1.left = r6;
        if (this.mMoreButtonWidth != 0 && this.isShowing == this.count - r6) {
            this.initCenterViewX = (((getWidth() - this.mMoreButtonWidth) - this.mMoreButtonMarginRight) - this.mMoreButtonMarginLeft) - width;
        } else {
            this.initCenterViewX = this.marginLeftSpace + this.leftRightSpace;
        }
        this.initCenterViewY = this.itemMarginTop;
        this.childWith = ((getWidth() - (this.leftRightSpace * 2)) - this.marginRightSpace) - this.marginLeftSpace;
        if (this.shouldflipper) {
            this.mHander.removeMessages(1001);
            this.mHander.sendEmptyMessageDelayed(1001, this.mTimeInteval);
        }
    }

    @Override // android.view.View
    public void setAlpha(float f2) {
        this.mAlpha = f2;
    }

    public void setAutoFliper(int i2) {
        this.autoflipper = i2;
        this.shouldflipper = i2 == 1;
    }

    public void setBackHeight(int i2) {
        this.backHeight = i2;
    }

    public void setBackgroundResizeMode(String str) {
        this.mResizeMode = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.mResizeMode.equals("center_crop")) {
            this.mScaleType = ImageView.ScaleType.CENTER_CROP;
        } else if (this.mResizeMode.equals("fit_xy")) {
            this.mScaleType = ImageView.ScaleType.FIT_XY;
        } else if (this.mResizeMode.equals(DYConstants.DY_CENTER)) {
            this.mScaleType = ImageView.ScaleType.CENTER;
        } else if (this.mResizeMode.equals("center_inside")) {
            this.mScaleType = ImageView.ScaleType.CENTER_INSIDE;
        } else if (this.mResizeMode.equals("fit_center")) {
            this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        } else if (this.mResizeMode.equals("fit_end")) {
            this.mScaleType = ImageView.ScaleType.FIT_END;
        } else if (this.mResizeMode.equals("fit_start")) {
            this.mScaleType = ImageView.ScaleType.FIT_START;
        }
    }

    public void setBlur(int i2) {
        this.mBlur = i2;
    }

    public void setCardSwitchListener(CardSwitchListener cardSwitchListener) {
        this.cardSwitchListener = cardSwitchListener;
    }

    public void setCyle(boolean z) {
        this.cycle = z;
        if (z) {
            this.mMoreButtonWidth = 0;
        }
    }

    public void setInitIndex(int i2) {
        this.index = i2;
    }

    public void setMode(String str) {
        if (str == null) {
            return;
        }
        if (str != null && str.equals("linear")) {
            setPadingMargin(0, 0, 0, 0, 0, 0);
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

    public void setMoreButtonListener(JDCustomCardbannerWithOutTouchViewManager2.OnMoreDrag onMoreDrag) {
        this.mOnMoreDrag = onMoreDrag;
    }

    public void setPadingMargin(int i2, int i3, int i4, int i5, int i6, int i7) {
        this.marginLeftSpace = i2;
        this.marginRightSpace = i3;
        this.paddingLeft = i4;
        this.paddingRight = i5;
        this.itemMarginTop = i6;
        this.itemMarginBttom = i7;
    }

    public void setRightHeight(float f2) {
        SCALE_STEP = f2;
    }

    public void setRotate(int i2) {
        this.mRotate = i2;
    }

    public void setScaleXY(boolean z) {
        this.scaleXY = z;
    }

    public void setTimeInteval(int i2) {
        this.mTimeInteval = i2;
    }

    public void setforgroundColor(int i2) {
        this.forgroudColor = i2;
    }

    public void setleftHeight(float f2) {
        SCALE_STEP_LEFT = f2;
    }

    public void showMoreButton(int i2, int i3, int i4) {
        if (this.cycle) {
            return;
        }
        this.mMoreButtonWidth = i2;
        this.mMoreButtonMarginLeft = i3;
        this.mMoreButtonMarginRight = i4;
    }

    public void showfilpper() {
        List<JDCustomCardbannerItemViewManager.CardItemView3> list = this.viewList;
        if (list == null || list.size() < 2) {
            return;
        }
        Status status = new Status();
        status.left = true;
        status.last = true;
        int size = (this.viewList.size() - 1) / 2;
        status.v = this.viewList.get(size);
        this.releasedViewList.clear();
        this.releasedViewList.add(status);
        if (this.mDragHelper.smoothSlideViewTo(this.viewList.get(size), (-this.viewList.get(0).getWidth()) + this.marginLeftSpace, this.itemMarginTop)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void showfilpperleft() {
        if (this.viewList.size() <= 0) {
            return;
        }
        Status status = new Status();
        status.left = false;
        status.last = true;
        int size = (this.viewList.size() - 1) / 2;
        status.v = this.viewList.get(size);
        this.releasedViewList.clear();
        this.releasedViewList.add(status);
        if (this.mDragHelper.smoothSlideViewTo(this.viewList.get(size), this.viewList.get(0).getWidth() + (this.leftRightSpace * 2) + this.marginLeftSpace, this.itemMarginTop)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public CardSlidePanelStyle5(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CardSlidePanelStyle5(Context context, AttributeSet attributeSet, int i2) {
        super(context);
        this.viewList = new ArrayList();
        this.releasedViewList = new ArrayList();
        this.initCenterViewX = 0;
        this.initCenterViewY = 0;
        this.allWidth = 0;
        this.allHeight = 0;
        this.childWith = 0;
        this.MAX_SLIDE_DISTANCE_LINKAGE = 180;
        this.marginLeftSpace = 20;
        this.paddingLeft = 0;
        this.paddingRight = 0;
        this.marginRightSpace = 60;
        this.leftRightSpace = 0;
        this.itemMarginTop = 200;
        this.itemMarginBttom = 0;
        this.bottomMarginTop = 40;
        this.yOffsetStep = 40;
        this.xOffsetStep = 50;
        this.mTouchSlop = 5;
        this.isShowing = 0;
        this.btnLock = false;
        this.downPoint = new Point();
        this.mClickPosition = 0;
        this.mHander = new Handler() { // from class: com.jingdong.jdreact.plugin.banner.CardSlidePanelStyle5.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i3 = message.what;
                if (i3 == 1001) {
                    if (CardSlidePanelStyle5.this.shouldflipper) {
                        CardSlidePanelStyle5.this.showfilpper();
                        return;
                    }
                    return;
                }
                if (i3 == 1002) {
                    CardSlidePanelStyle5.this.inflateAgainNow();
                }
                super.handleMessage(message);
            }
        };
        this.isDraging = false;
        this.position = 0;
        this.changed = 0;
        this.mActionDownPosition = 0;
        this.mArrayList = new ArrayList<>();
        this.mScaleType = ImageView.ScaleType.FIT_XY;
        this.shouldflipper = false;
        this.autoflipper = 0;
        this.mRotate = 0;
        this.mBlur = 0;
        this.mAlpha = 0.5f;
        this.index = 0;
        this.count = 0;
        this.backHeight = 0;
        this.mMoreButtonWidth = 0;
        this.mMoreButtonMarginLeft = 100;
        this.mMoreButtonMarginRight = 100;
        this.mMoreShown = false;
        this.cycle = false;
        this.scaleXY = false;
        this.mode = TYPE_BANNER_PAGER;
        this.mTimeInteval = 3000;
        this.forgroudColor = 1299477620;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.card);
        setMotionEventSplittingEnabled(false);
        this.itemMarginTop = (int) obtainStyledAttributes.getDimension(R.styleable.card_itemMarginTop, this.itemMarginTop);
        this.bottomMarginTop = (int) obtainStyledAttributes.getDimension(R.styleable.card_bottomMarginTop, this.bottomMarginTop);
        this.yOffsetStep = (int) obtainStyledAttributes.getDimension(R.styleable.card_yOffsetStep, this.yOffsetStep);
        this.xOffsetStep = (int) obtainStyledAttributes.getDimension(R.styleable.card_xOffsetStep, this.xOffsetStep);
        this.MAX_SLIDE_DISTANCE_LINKAGE = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card__left_right_step);
        if (this.mode.equals(TYPE_CYCLE_PAGER)) {
            this.marginLeftSpace = 0;
            this.marginRightSpace = 0;
            this.leftRightSpace = 0;
        } else if (this.mode.equals(TYPE_BANNER_PAGER)) {
            this.marginRightSpace = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card_left_right_padding);
            this.marginLeftSpace = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card__left_right_space) * 2;
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
