package com.facebook.react.views.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.touch.OnInterceptTouchEventListener;
import com.facebook.react.touch.ReactHitSlopView;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ReactZIndexedViewGroup;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.RootViewUtil;
import com.facebook.react.uimanager.ViewGroupDrawingOrderHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.scroll.ReactHorizontalScrollView;
import com.facebook.react.views.scroll.ReactScrollView;
import com.facebook.react.views.view.ReactViewBackgroundDrawable;
import com.facebook.yoga.YogaConstants;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ReactViewGroup extends ViewGroup implements ReactInterceptingViewGroup, ReactClippingViewGroup, ReactPointerEventsView, ReactHitSlopView, ReactZIndexedViewGroup {
    private static final int ARRAY_CAPACITY_INCREMENT = 12;
    private static final int DEFAULT_BACKGROUND_COLOR = 0;
    private static final ViewGroup.LayoutParams sDefaultLayoutParam = new ViewGroup.LayoutParams(0, 0);
    private static final Rect sHelperRect = new Rect();
    @Nullable
    private View[] mAllChildren;
    private int mAllChildrenCount;
    private float mBackfaceOpacity;
    private String mBackfaceVisibility;
    @Nullable
    private ChildrenLayoutChangeListener mChildrenLayoutChangeListener;
    @Nullable
    private Rect mClippingRect;
    private final ViewGroupDrawingOrderHelper mDrawingOrderHelper;
    boolean mEnableZindex;
    @Nullable
    private Rect mHitSlopRect;
    private int mLayoutDirection;
    private boolean mNeedsOffscreenAlphaCompositing;
    int mOnInterceptTouchEvent;
    @Nullable
    private OnInterceptTouchEventListener mOnInterceptTouchEventListener;
    @Nullable
    private String mOverflow;
    @Nullable
    private Path mPath;
    private PointerEvents mPointerEvents;
    @Nullable
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    private boolean mRemoveClippedSubviews;
    private int point_x;
    private int point_y;
    ViewGroup v;

    /* loaded from: classes12.dex */
    public static final class ChildrenLayoutChangeListener implements View.OnLayoutChangeListener {
        private final ReactViewGroup mParent;

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            if (this.mParent.getRemoveClippedSubviews()) {
                this.mParent.updateSubviewClipStatus(view);
            }
        }

        private ChildrenLayoutChangeListener(ReactViewGroup reactViewGroup) {
            this.mParent = reactViewGroup;
        }
    }

    public ReactViewGroup(Context context) {
        super(context);
        this.mRemoveClippedSubviews = false;
        this.mAllChildren = null;
        this.mPointerEvents = PointerEvents.AUTO;
        this.mNeedsOffscreenAlphaCompositing = false;
        this.mBackfaceOpacity = 1.0f;
        this.mBackfaceVisibility = ViewProps.VISIBLE;
        this.mOnInterceptTouchEvent = 0;
        this.mEnableZindex = false;
        this.point_x = 0;
        this.point_y = 0;
        setClipChildren(false);
        this.mDrawingOrderHelper = new ViewGroupDrawingOrderHelper(this);
    }

    private void addInArray(View view, int i2) {
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        int i3 = this.mAllChildrenCount;
        int length = viewArr.length;
        if (i2 == i3) {
            if (length == i3) {
                View[] viewArr2 = new View[length + 12];
                this.mAllChildren = viewArr2;
                System.arraycopy(viewArr, 0, viewArr2, 0, length);
                viewArr = this.mAllChildren;
            }
            int i4 = this.mAllChildrenCount;
            this.mAllChildrenCount = i4 + 1;
            viewArr[i4] = view;
        } else if (i2 < i3) {
            if (length == i3) {
                View[] viewArr3 = new View[length + 12];
                this.mAllChildren = viewArr3;
                System.arraycopy(viewArr, 0, viewArr3, 0, i2);
                System.arraycopy(viewArr, i2, this.mAllChildren, i2 + 1, i3 - i2);
                viewArr = this.mAllChildren;
            } else {
                System.arraycopy(viewArr, i2, viewArr, i2 + 1, i3 - i2);
            }
            viewArr[i2] = view;
            this.mAllChildrenCount++;
        } else {
            throw new IndexOutOfBoundsException("index=" + i2 + " count=" + i3);
        }
    }

    private void dispatchOverflowDraw(Canvas canvas) {
        boolean z;
        float f2;
        float f3;
        float f4;
        Path path;
        String str = this.mOverflow;
        if (str != null) {
            str.hashCode();
            if (!str.equals(ViewProps.HIDDEN)) {
                if (str.equals(ViewProps.VISIBLE) && (path = this.mPath) != null) {
                    path.rewind();
                    return;
                }
                return;
            }
            float width = getWidth();
            float height = getHeight();
            ReactViewBackgroundDrawable reactViewBackgroundDrawable = this.mReactBackgroundDrawable;
            if (reactViewBackgroundDrawable != null) {
                RectF directionAwareBorderInsets = reactViewBackgroundDrawable.getDirectionAwareBorderInsets();
                float f5 = directionAwareBorderInsets.top;
                if (f5 > 0.0f || directionAwareBorderInsets.left > 0.0f || directionAwareBorderInsets.bottom > 0.0f || directionAwareBorderInsets.right > 0.0f) {
                    f4 = directionAwareBorderInsets.left + 0.0f;
                    f3 = f5 + 0.0f;
                    width -= directionAwareBorderInsets.right;
                    height -= directionAwareBorderInsets.bottom;
                } else {
                    f3 = 0.0f;
                    f4 = 0.0f;
                }
                float fullBorderRadius = this.mReactBackgroundDrawable.getFullBorderRadius();
                float borderRadiusOrDefaultTo = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo(fullBorderRadius, ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_LEFT);
                float borderRadiusOrDefaultTo2 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo(fullBorderRadius, ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_RIGHT);
                float borderRadiusOrDefaultTo3 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo(fullBorderRadius, ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_LEFT);
                float borderRadiusOrDefaultTo4 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo(fullBorderRadius, ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_RIGHT);
                if (Build.VERSION.SDK_INT >= 17) {
                    boolean z2 = this.mLayoutDirection == 1;
                    float borderRadius = this.mReactBackgroundDrawable.getBorderRadius(ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_START);
                    float borderRadius2 = this.mReactBackgroundDrawable.getBorderRadius(ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_END);
                    float borderRadius3 = this.mReactBackgroundDrawable.getBorderRadius(ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_START);
                    borderRadiusOrDefaultTo4 = this.mReactBackgroundDrawable.getBorderRadius(ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_END);
                    if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(getContext())) {
                        float f6 = YogaConstants.isUndefined(borderRadius) ? borderRadiusOrDefaultTo : borderRadius;
                        if (!YogaConstants.isUndefined(borderRadius2)) {
                            borderRadiusOrDefaultTo2 = borderRadius2;
                        }
                        if (!YogaConstants.isUndefined(borderRadius3)) {
                            borderRadiusOrDefaultTo3 = borderRadius3;
                        }
                        if (YogaConstants.isUndefined(borderRadiusOrDefaultTo4)) {
                            borderRadiusOrDefaultTo4 = borderRadiusOrDefaultTo4;
                        }
                        float f7 = z2 ? borderRadiusOrDefaultTo2 : f6;
                        if (z2) {
                            borderRadiusOrDefaultTo2 = f6;
                        }
                        float f8 = z2 ? borderRadiusOrDefaultTo4 : borderRadiusOrDefaultTo3;
                        if (z2) {
                            borderRadiusOrDefaultTo4 = borderRadiusOrDefaultTo3;
                        }
                        borderRadiusOrDefaultTo = f7;
                        borderRadiusOrDefaultTo3 = f8;
                    } else {
                        borderRadiusOrDefaultTo = z2 ? borderRadius2 : borderRadius;
                        if (!z2) {
                            borderRadius = borderRadius2;
                        }
                        float f9 = z2 ? borderRadiusOrDefaultTo4 : borderRadius3;
                        if (!z2) {
                            borderRadius3 = borderRadiusOrDefaultTo4;
                        }
                        if (YogaConstants.isUndefined(borderRadiusOrDefaultTo)) {
                            borderRadiusOrDefaultTo = borderRadiusOrDefaultTo;
                        }
                        if (!YogaConstants.isUndefined(borderRadius)) {
                            borderRadiusOrDefaultTo2 = borderRadius;
                        }
                        if (!YogaConstants.isUndefined(f9)) {
                            borderRadiusOrDefaultTo3 = f9;
                        }
                        borderRadiusOrDefaultTo4 = !YogaConstants.isUndefined(borderRadius3) ? borderRadius3 : borderRadiusOrDefaultTo4;
                    }
                }
                if (borderRadiusOrDefaultTo > 0.0f || borderRadiusOrDefaultTo2 > 0.0f || borderRadiusOrDefaultTo4 > 0.0f || borderRadiusOrDefaultTo3 > 0.0f) {
                    if (this.mPath == null) {
                        this.mPath = new Path();
                    }
                    this.mPath.rewind();
                    this.mPath.addRoundRect(new RectF(f4, f3, width, height), new float[]{Math.max(borderRadiusOrDefaultTo - directionAwareBorderInsets.left, 0.0f), Math.max(borderRadiusOrDefaultTo - directionAwareBorderInsets.top, 0.0f), Math.max(borderRadiusOrDefaultTo2 - directionAwareBorderInsets.right, 0.0f), Math.max(borderRadiusOrDefaultTo2 - directionAwareBorderInsets.top, 0.0f), Math.max(borderRadiusOrDefaultTo4 - directionAwareBorderInsets.right, 0.0f), Math.max(borderRadiusOrDefaultTo4 - directionAwareBorderInsets.bottom, 0.0f), Math.max(borderRadiusOrDefaultTo3 - directionAwareBorderInsets.left, 0.0f), Math.max(borderRadiusOrDefaultTo3 - directionAwareBorderInsets.bottom, 0.0f)}, Path.Direction.CW);
                    canvas.clipPath(this.mPath);
                    f2 = f4;
                    z = true;
                } else {
                    f2 = f4;
                    z = false;
                }
            } else {
                z = false;
                f2 = 0.0f;
                f3 = 0.0f;
            }
            if (z) {
                return;
            }
            canvas.clipRect(new RectF(f2, f3, width, height));
        }
    }

    private ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable(getContext());
            Drawable background = getBackground();
            updateBackgroundDrawable(null);
            if (background == null) {
                updateBackgroundDrawable(this.mReactBackgroundDrawable);
            } else {
                updateBackgroundDrawable(new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, background}));
            }
            if (Build.VERSION.SDK_INT >= 17) {
                boolean isRTL = I18nUtil.getInstance().isRTL(getContext());
                this.mLayoutDirection = isRTL ? 1 : 0;
                this.mReactBackgroundDrawable.setResolvedLayoutDirection(isRTL ? 1 : 0);
            }
        }
        return this.mReactBackgroundDrawable;
    }

    private ViewGroup getScrollView() {
        int i2 = this.mOnInterceptTouchEvent;
        if (i2 == 1 || i2 == 3) {
            return getScrollViewV();
        }
        if (i2 == 2 || i2 == 5) {
            return getScrollViewH();
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0032, code lost:
        if ((r0.getParent() instanceof com.facebook.react.views.scroll.ReactHorizontalScrollView) != false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x003a, code lost:
        if ((r0.getParent() instanceof androidx.viewpager.widget.ViewPager) == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0042, code lost:
        return (android.view.ViewGroup) r0.getParent();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.ViewGroup getScrollViewH() {
        /*
            r3 = this;
            r0 = r3
        L1:
            r1 = 0
            if (r0 == 0) goto L2a
            android.view.ViewParent r2 = r0.getParent()
            if (r2 == 0) goto L2a
            android.view.ViewParent r2 = r0.getParent()
            boolean r2 = r2 instanceof com.facebook.react.views.scroll.ReactHorizontalScrollView
            if (r2 != 0) goto L2a
            android.view.ViewParent r2 = r0.getParent()
            boolean r2 = r2 instanceof androidx.viewpager.widget.ViewPager
            if (r2 != 0) goto L2a
            android.view.ViewParent r2 = r0.getParent()
            boolean r2 = r2 instanceof android.view.View
            if (r2 != 0) goto L23
            return r1
        L23:
            android.view.ViewParent r0 = r0.getParent()
            android.view.View r0 = (android.view.View) r0
            goto L1
        L2a:
            if (r0 == 0) goto L43
            android.view.ViewParent r2 = r0.getParent()
            boolean r2 = r2 instanceof com.facebook.react.views.scroll.ReactHorizontalScrollView
            if (r2 != 0) goto L3c
            android.view.ViewParent r2 = r0.getParent()
            boolean r2 = r2 instanceof androidx.viewpager.widget.ViewPager
            if (r2 == 0) goto L43
        L3c:
            android.view.ViewParent r0 = r0.getParent()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            return r0
        L43:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewGroup.getScrollViewH():android.view.ViewGroup");
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x002a, code lost:
        if ((r0.getParent() instanceof com.facebook.react.views.scroll.ReactScrollView) == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0032, code lost:
        return (android.view.ViewGroup) r0.getParent();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.ViewGroup getScrollViewV() {
        /*
            r3 = this;
            r0 = r3
        L1:
            r1 = 0
            if (r0 == 0) goto L22
            android.view.ViewParent r2 = r0.getParent()
            if (r2 == 0) goto L22
            android.view.ViewParent r2 = r0.getParent()
            boolean r2 = r2 instanceof com.facebook.react.views.scroll.ReactScrollView
            if (r2 != 0) goto L22
            android.view.ViewParent r2 = r0.getParent()
            boolean r2 = r2 instanceof android.view.View
            if (r2 != 0) goto L1b
            return r1
        L1b:
            android.view.ViewParent r0 = r0.getParent()
            android.view.View r0 = (android.view.View) r0
            goto L1
        L22:
            if (r0 == 0) goto L33
            android.view.ViewParent r2 = r0.getParent()
            boolean r2 = r2 instanceof com.facebook.react.views.scroll.ReactScrollView
            if (r2 == 0) goto L33
            android.view.ViewParent r0 = r0.getParent()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            return r0
        L33:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewGroup.getScrollViewV():android.view.ViewGroup");
    }

    private int indexOfChildInAllChildren(View view) {
        int i2 = this.mAllChildrenCount;
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        for (int i3 = 0; i3 < i2; i3++) {
            if (viewArr[i3] == view) {
                return i3;
            }
        }
        return -1;
    }

    private void removeFromArray(int i2) {
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        int i3 = this.mAllChildrenCount;
        if (i2 == i3 - 1) {
            int i4 = i3 - 1;
            this.mAllChildrenCount = i4;
            viewArr[i4] = null;
        } else if (i2 >= 0 && i2 < i3) {
            System.arraycopy(viewArr, i2 + 1, viewArr, i2, (i3 - i2) - 1);
            int i5 = this.mAllChildrenCount - 1;
            this.mAllChildrenCount = i5;
            viewArr[i5] = null;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    private void updateBackgroundDrawable(Drawable drawable) {
        super.setBackground(drawable);
    }

    private void updateClippingToRect(Rect rect) {
        Assertions.assertNotNull(this.mAllChildren);
        int i2 = 0;
        for (int i3 = 0; i3 < this.mAllChildrenCount; i3++) {
            updateSubviewClipStatus(rect, i3, i2);
            if (this.mAllChildren[i3].getParent() == null) {
                i2++;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x005d, code lost:
        if (r7 != false) goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateSubviewClipStatus(android.graphics.Rect r7, int r8, int r9) {
        /*
            r6 = this;
            android.view.View[] r0 = r6.mAllChildren
            java.lang.Object r0 = com.facebook.infer.annotation.Assertions.assertNotNull(r0)
            android.view.View[] r0 = (android.view.View[]) r0
            r0 = r0[r8]
            android.graphics.Rect r1 = com.facebook.react.views.view.ReactViewGroup.sHelperRect
            int r2 = r0.getLeft()
            int r3 = r0.getTop()
            int r4 = r0.getRight()
            int r5 = r0.getBottom()
            r1.set(r2, r3, r4, r5)
            int r2 = r1.left
            int r3 = r1.top
            int r4 = r1.right
            int r1 = r1.bottom
            boolean r7 = r7.intersects(r2, r3, r4, r1)
            android.view.animation.Animation r1 = r0.getAnimation()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L3b
            boolean r1 = r1.hasEnded()
            if (r1 != 0) goto L3b
            r1 = 1
            goto L3c
        L3b:
            r1 = 0
        L3c:
            if (r7 != 0) goto L4b
            android.view.ViewParent r4 = r0.getParent()
            if (r4 == 0) goto L4b
            if (r1 != 0) goto L4b
            int r8 = r8 - r9
            super.removeViewsInLayout(r8, r3)
            goto L5f
        L4b:
            if (r7 == 0) goto L5d
            android.view.ViewParent r1 = r0.getParent()
            if (r1 != 0) goto L5d
            int r8 = r8 - r9
            android.view.ViewGroup$LayoutParams r7 = com.facebook.react.views.view.ReactViewGroup.sDefaultLayoutParam
            super.addViewInLayout(r0, r8, r7, r3)
            r6.invalidate()
            goto L5f
        L5d:
            if (r7 == 0) goto L60
        L5f:
            r2 = 1
        L60:
            if (r2 == 0) goto L71
            boolean r7 = r0 instanceof com.facebook.react.uimanager.ReactClippingViewGroup
            if (r7 == 0) goto L71
            com.facebook.react.uimanager.ReactClippingViewGroup r0 = (com.facebook.react.uimanager.ReactClippingViewGroup) r0
            boolean r7 = r0.getRemoveClippedSubviews()
            if (r7 == 0) goto L71
            r0.updateClippingRect()
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewGroup.updateSubviewClipStatus(android.graphics.Rect, int, int):void");
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        this.mDrawingOrderHelper.handleAddView(view);
        setChildrenDrawingOrderEnabled(this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder());
        try {
            super.addView(view, i2, layoutParams);
        } catch (Exception e2) {
            FLog.e("ReactViewGroup", "addView Exception:" + e2);
        }
    }

    public void addViewWithSubviewClippingEnabled(View view, int i2) {
        addViewWithSubviewClippingEnabled(view, i2, sDefaultLayoutParam);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        try {
            dispatchOverflowDraw(canvas);
            super.dispatchDraw(canvas);
        } catch (NullPointerException e2) {
            FLog.e(ReactConstants.TAG, "NullPointerException when executing ViewGroup.dispatchDraw method", e2);
        } catch (StackOverflowError e3) {
            RootView rootView = RootViewUtil.getRootView(this);
            if (rootView != null) {
                rootView.handleException(e3);
            } else if (getContext() instanceof ReactContext) {
                ((ReactContext) getContext()).handleException(new IllegalViewOperationException("StackOverflowException", this, e3));
            } else {
                throw e3;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(23)
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        try {
            super.dispatchProvideStructure(viewStructure);
        } catch (NullPointerException e2) {
            FLog.e(ReactConstants.TAG, "NullPointerException when executing dispatchProvideStructure", e2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSetPressed(boolean z) {
    }

    public void enableInterceptTouchEvent(boolean z) {
        ViewGroup scrollView = getScrollView();
        if (scrollView != null) {
            scrollView.requestDisallowInterceptTouchEvent(z);
        }
    }

    public void enableZindex(boolean z) {
        this.mEnableZindex = z;
    }

    public int getAllChildrenCount() {
        return this.mAllChildrenCount;
    }

    @VisibleForTesting
    public int getBackgroundColor() {
        if (getBackground() != null) {
            return ((ReactViewBackgroundDrawable) getBackground()).getColor();
        }
        return 0;
    }

    public View getChildAtWithSubviewClippingEnabled(int i2) {
        return ((View[]) Assertions.assertNotNull(this.mAllChildren))[i2];
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        return this.mDrawingOrderHelper.getChildDrawingOrder(i2, i3);
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void getClippingRect(Rect rect) {
        rect.set(this.mClippingRect);
    }

    @Override // com.facebook.react.touch.ReactHitSlopView
    @Nullable
    public Rect getHitSlopRect() {
        return this.mHitSlopRect;
    }

    @Nullable
    public String getOverflow() {
        return this.mOverflow;
    }

    @Override // com.facebook.react.uimanager.ReactPointerEventsView
    public PointerEvents getPointerEvents() {
        return this.mPointerEvents;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }

    @Override // com.facebook.react.uimanager.ReactZIndexedViewGroup
    public int getZIndexMappedChildIndex(int i2) {
        return this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder() ? this.mDrawingOrderHelper.getChildDrawingOrder(getChildCount(), i2) : i2;
    }

    public boolean getZindexflag() {
        return this.mEnableZindex;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return this.mNeedsOffscreenAlphaCompositing;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        PointerEvents pointerEvents;
        if (motionEvent.getAction() == 0) {
            this.point_x = (int) motionEvent.getX();
            this.point_y = (int) motionEvent.getY();
            ViewGroup scrollView = getScrollView();
            if (scrollView != null) {
                int i2 = this.mOnInterceptTouchEvent;
                if (i2 == 3) {
                    ((ReactScrollView) scrollView).setInterceptScrollEnabled(true);
                } else if (i2 == 5) {
                    ((ReactHorizontalScrollView) scrollView).setInterceptScrollEnabled(true);
                } else {
                    requestDisallowInterceptTouchEvent(true);
                }
                return super.onInterceptTouchEvent(motionEvent);
            }
        } else if (motionEvent.getAction() == 2) {
            if (getScrollView() != null) {
                if (Math.abs(((int) motionEvent.getY()) - this.point_y) > Math.abs(((int) motionEvent.getX()) - this.point_x)) {
                    requestDisallowInterceptTouchEvent(false);
                    return super.onInterceptTouchEvent(motionEvent);
                }
                return super.onInterceptTouchEvent(motionEvent);
            }
        } else if (motionEvent.getAction() == 1) {
            this.point_x = 0;
            this.point_y = 0;
            ViewGroup scrollView2 = getScrollView();
            if (scrollView2 != null) {
                int i3 = this.mOnInterceptTouchEvent;
                if (i3 == 3) {
                    ((ReactScrollView) scrollView2).setInterceptScrollEnabled(false);
                } else if (i3 == 5) {
                    ((ReactHorizontalScrollView) scrollView2).setInterceptScrollEnabled(false);
                } else {
                    requestDisallowInterceptTouchEvent(false);
                }
                return super.onInterceptTouchEvent(motionEvent);
            }
        } else {
            ViewGroup scrollView3 = getScrollView();
            if (scrollView3 != null) {
                int i4 = this.mOnInterceptTouchEvent;
                if (i4 == 3) {
                    ((ReactScrollView) scrollView3).setInterceptScrollEnabled(false);
                } else if (i4 == 5) {
                    ((ReactHorizontalScrollView) scrollView3).setInterceptScrollEnabled(false);
                } else {
                    requestDisallowInterceptTouchEvent(false);
                }
                return super.onInterceptTouchEvent(motionEvent);
            }
        }
        OnInterceptTouchEventListener onInterceptTouchEventListener = this.mOnInterceptTouchEventListener;
        if ((onInterceptTouchEventListener != null && onInterceptTouchEventListener.onInterceptTouchEvent(this, motionEvent)) || (pointerEvents = this.mPointerEvents) == PointerEvents.NONE || pointerEvents == PointerEvents.BOX_ONLY) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(i2, i3);
        setMeasuredDimension(View.MeasureSpec.getSize(i2), View.MeasureSpec.getSize(i3));
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        ReactViewBackgroundDrawable reactViewBackgroundDrawable;
        if (Build.VERSION.SDK_INT < 17 || (reactViewBackgroundDrawable = this.mReactBackgroundDrawable) == null) {
            return;
        }
        reactViewBackgroundDrawable.setResolvedLayoutDirection(this.mLayoutDirection);
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        PointerEvents pointerEvents = this.mPointerEvents;
        return (pointerEvents == PointerEvents.NONE || pointerEvents == PointerEvents.BOX_NONE) ? false : true;
    }

    public void removeAllViewsWithSubviewClippingEnabled() {
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mAllChildren);
        for (int i2 = 0; i2 < this.mAllChildrenCount; i2++) {
            this.mAllChildren[i2].removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        }
        removeAllViewsInLayout();
        this.mAllChildrenCount = 0;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        this.mDrawingOrderHelper.handleRemoveView(view);
        setChildrenDrawingOrderEnabled(this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder());
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i2) {
        this.mDrawingOrderHelper.handleRemoveView(getChildAt(i2));
        setChildrenDrawingOrderEnabled(this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder());
        super.removeViewAt(i2);
    }

    public void removeViewWithSubviewClippingEnabled(View view) {
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        view.removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        int indexOfChildInAllChildren = indexOfChildInAllChildren(view);
        if (this.mAllChildren[indexOfChildInAllChildren].getParent() != null) {
            int i2 = 0;
            for (int i3 = 0; i3 < indexOfChildInAllChildren; i3++) {
                if (this.mAllChildren[i3].getParent() == null) {
                    i2++;
                }
            }
            super.removeViewsInLayout(indexOfChildInAllChildren - i2, 1);
        }
        removeFromArray(indexOfChildInAllChildren);
    }

    @Override // android.view.View, android.view.ViewParent
    @SuppressLint({"MissingSuperCall"})
    public void requestLayout() {
    }

    public void setBackfaceVisibility(String str) {
        this.mBackfaceVisibility = str;
        setBackfaceVisibilityDependantOpacity();
    }

    public void setBackfaceVisibilityDependantOpacity() {
        if (this.mBackfaceVisibility.equals(ViewProps.VISIBLE)) {
            setAlpha(this.mBackfaceOpacity);
            return;
        }
        float rotationX = getRotationX();
        float rotationY = getRotationY();
        if (rotationX >= -90.0f && rotationX < 90.0f && rotationY >= -90.0f && rotationY < 90.0f) {
            setAlpha(this.mBackfaceOpacity);
        } else {
            setAlpha(0.0f);
        }
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        throw new UnsupportedOperationException("This method is not supported for ReactViewGroup instances");
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        if (i2 == 0 && this.mReactBackgroundDrawable == null) {
            return;
        }
        getOrCreateReactViewBackground().setColor(i2);
    }

    public void setBorderColor(int i2, float f2, float f3) {
        getOrCreateReactViewBackground().setBorderColor(i2, f2, f3);
    }

    public void setBorderRadius(float f2) {
        ReactViewBackgroundDrawable orCreateReactViewBackground = getOrCreateReactViewBackground();
        orCreateReactViewBackground.setRadius(f2);
        if (Build.VERSION.SDK_INT < 18) {
            int i2 = orCreateReactViewBackground.hasRoundedBorders() ? 1 : 2;
            if (i2 != getLayerType()) {
                setLayerType(i2, null);
            }
        }
    }

    public void setBorderStyle(@Nullable String str) {
        getOrCreateReactViewBackground().setBorderStyle(str);
    }

    public void setBorderWidth(int i2, float f2) {
        getOrCreateReactViewBackground().setBorderWidth(i2, f2);
    }

    public void setHitSlopRect(@Nullable Rect rect) {
        this.mHitSlopRect = rect;
    }

    public void setNeedsOffscreenAlphaCompositing(boolean z) {
        this.mNeedsOffscreenAlphaCompositing = z;
    }

    public void setOnInterceptTouchEvent(int i2) {
        this.mOnInterceptTouchEvent = i2;
    }

    @Override // com.facebook.react.touch.ReactInterceptingViewGroup
    public void setOnInterceptTouchEventListener(OnInterceptTouchEventListener onInterceptTouchEventListener) {
        this.mOnInterceptTouchEventListener = onInterceptTouchEventListener;
    }

    public void setOpacityIfPossible(float f2) {
        this.mBackfaceOpacity = f2;
        setBackfaceVisibilityDependantOpacity();
    }

    public void setOverflow(String str) {
        this.mOverflow = str;
        invalidate();
    }

    public void setPointerEvents(PointerEvents pointerEvents) {
        this.mPointerEvents = pointerEvents;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void setRemoveClippedSubviews(boolean z) {
        if (z == this.mRemoveClippedSubviews) {
            return;
        }
        this.mRemoveClippedSubviews = z;
        if (z) {
            Rect rect = new Rect();
            this.mClippingRect = rect;
            ReactClippingViewGroupHelper.calculateClippingRect(this, rect);
            int childCount = getChildCount();
            this.mAllChildrenCount = childCount;
            this.mAllChildren = new View[Math.max(12, childCount)];
            this.mChildrenLayoutChangeListener = new ChildrenLayoutChangeListener();
            for (int i2 = 0; i2 < this.mAllChildrenCount; i2++) {
                View childAt = getChildAt(i2);
                this.mAllChildren[i2] = childAt;
                childAt.addOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
            }
            updateClippingRect();
            return;
        }
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        Assertions.assertNotNull(this.mChildrenLayoutChangeListener);
        for (int i3 = 0; i3 < this.mAllChildrenCount; i3++) {
            this.mAllChildren[i3].removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        }
        getDrawingRect(this.mClippingRect);
        updateClippingToRect(this.mClippingRect);
        this.mAllChildren = null;
        this.mClippingRect = null;
        this.mAllChildrenCount = 0;
        this.mChildrenLayoutChangeListener = null;
    }

    public void setTranslucentBackgroundDrawable(@Nullable Drawable drawable) {
        updateBackgroundDrawable(null);
        if (this.mReactBackgroundDrawable != null && drawable != null) {
            updateBackgroundDrawable(new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, drawable}));
        } else if (drawable != null) {
            updateBackgroundDrawable(drawable);
        }
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            Assertions.assertNotNull(this.mClippingRect);
            Assertions.assertNotNull(this.mAllChildren);
            ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
            updateClippingToRect(this.mClippingRect);
        }
    }

    @Override // com.facebook.react.uimanager.ReactZIndexedViewGroup
    public void updateDrawingOrder() {
        this.mDrawingOrderHelper.update();
        setChildrenDrawingOrderEnabled(this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder());
        invalidate();
    }

    void addViewWithSubviewClippingEnabled(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        addInArray(view, i2);
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (this.mAllChildren[i4].getParent() == null) {
                i3++;
            }
        }
        updateSubviewClipStatus(this.mClippingRect, i2, i3);
        view.addOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
    }

    public void setBorderRadius(float f2, int i2) {
        ReactViewBackgroundDrawable orCreateReactViewBackground = getOrCreateReactViewBackground();
        orCreateReactViewBackground.setRadius(f2, i2);
        if (Build.VERSION.SDK_INT < 18) {
            int i3 = orCreateReactViewBackground.hasRoundedBorders() ? 1 : 2;
            if (i3 != getLayerType()) {
                setLayerType(i3, null);
            }
        }
    }

    public void updateSubviewClipStatus(View view) {
        if (!this.mRemoveClippedSubviews || getParent() == null) {
            return;
        }
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        Rect rect = sHelperRect;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        if (this.mClippingRect.intersects(rect.left, rect.top, rect.right, rect.bottom) != (view.getParent() != null)) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.mAllChildrenCount; i3++) {
                View[] viewArr = this.mAllChildren;
                if (viewArr[i3] == view) {
                    updateSubviewClipStatus(this.mClippingRect, i3, i2);
                    return;
                }
                if (viewArr[i3].getParent() == null) {
                    i2++;
                }
            }
        }
    }
}
