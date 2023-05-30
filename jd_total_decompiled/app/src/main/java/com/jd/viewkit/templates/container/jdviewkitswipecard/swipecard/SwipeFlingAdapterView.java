package com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.jdviewkit.R;
import com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingListener;
import java.util.ArrayList;

/* loaded from: classes18.dex */
public class SwipeFlingAdapterView extends BaseFlingAdapterView {
    protected int LAST_OBJECT_IN_STACK;
    protected int MAX_VISIBLE;
    private int MIN_ADAPTER_STACK;
    private float ROTATION_DEGREES;
    protected float SCALE_STEP;
    private int animDuration;
    private Runnable animRunIn;
    private Runnable animRunOut;
    private ArrayList<View> cacheItems;
    private SwipeFlingListener flingCardListener;
    protected int initLeft;
    protected int initTop;
    private boolean isAnimRunning;
    public boolean isNeedSwipe;
    private View mActiveCard;
    private Adapter mAdapter;
    private AdapterDataSetObserver mDataSetObserver;
    private onFlingListener mFlingListener;
    private boolean mInLayout;
    private float mLastMotionX;
    private float mLastMotionY;
    private OnItemClickListener mOnItemClickListener;
    protected int offsetStep;
    float scale;
    protected boolean showOtherInBottom;
    protected boolean showOtherWithAlpha;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public class AdapterDataSetObserver extends DataSetObserver {
        private AdapterDataSetObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            SwipeFlingAdapterView.this.requestLayout();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            SwipeFlingAdapterView.this.requestLayout();
        }
    }

    /* loaded from: classes18.dex */
    public interface OnItemClickListener {
        void onItemClicked(MotionEvent motionEvent, View view, Object obj);
    }

    /* loaded from: classes18.dex */
    public interface onFlingListener {
        void onAdapterAboutToEmpty(int i2);

        void onLeftCardExit(Object obj);

        void onRightCardExit(Object obj);

        void onScroll(float f2, float f3);

        void removeFirstObjectInAdapter(boolean z);
    }

    public SwipeFlingAdapterView(Context context) {
        this(context, null);
    }

    private View getViewForPosition(int i2) {
        View view;
        int size = this.cacheItems.size();
        for (int i3 = 0; i3 < size && i2 >= 0; i3++) {
            View view2 = this.cacheItems.get(i3);
            try {
                int i4 = R.id.BABELVK_INTERNAL_CONTENT_ID;
                if (view2.getTag(i4) != null && ((Integer) view2.getTag(i4)).intValue() == i2) {
                    this.cacheItems.remove(view2);
                    view2.setTranslationX(0.0f);
                    view2.setScaleX(1.0f);
                    view2.setScaleY(1.0f);
                    view2.setAlpha(1.0f);
                    return view2;
                }
            } catch (Exception unused) {
            }
        }
        if (size <= 0 || (view = this.cacheItems.get(0)) == null) {
            return null;
        }
        view.setTranslationX(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setAlpha(1.0f);
        this.cacheItems.remove(view);
        return view;
    }

    private void layoutChildren(int i2, int i3) {
        while (i2 < Math.min(i3, this.MAX_VISIBLE)) {
            int itemViewType = this.mAdapter.getItemViewType(i2);
            View view = this.mAdapter.getView(i2, getViewForPosition(itemViewType), this);
            view.setTag(R.id.BABELVK_INTERNAL_CONTENT_ID, Integer.valueOf(itemViewType));
            if (view.getVisibility() != 8) {
                makeAndAddView(view, i2, false);
                this.LAST_OBJECT_IN_STACK = i2;
            }
            i2++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    @TargetApi(14)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void makeAndAddView(View view, int i2, boolean z) {
        int width;
        int i3;
        int i4;
        int height;
        int i5;
        int i6;
        view.setTranslationX(0.0f);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (z) {
            addViewInLayout(view, -1, layoutParams, true);
        } else {
            addViewInLayout(view, 0, layoutParams, true);
        }
        if (view.isLayoutRequested()) {
            view.measure(AdapterView.getChildMeasureSpec(getWidthMeasureSpec(), getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), AdapterView.getChildMeasureSpec(getHeightMeasureSpec(), getPaddingTop() + getPaddingBottom() + layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height));
        } else {
            cleanupLayoutState(view);
        }
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i7 = layoutParams.gravity;
        if (i7 == -1) {
            i7 = 8388659;
        }
        int i8 = i7 & 112;
        int absoluteGravity = Gravity.getAbsoluteGravity(i7, Build.VERSION.SDK_INT > 16 ? getLayoutDirection() : 0) & 7;
        if (absoluteGravity == 1) {
            width = ((((getWidth() + getPaddingLeft()) - getPaddingRight()) - measuredWidth) / 2) + layoutParams.leftMargin;
            i3 = layoutParams.rightMargin;
        } else if (absoluteGravity != 8388613) {
            i4 = getPaddingLeft() + layoutParams.leftMargin;
            if (i8 != 16) {
                height = ((((getHeight() + getPaddingTop()) - getPaddingBottom()) - measuredHeight) / 2) + layoutParams.topMargin;
                i5 = layoutParams.bottomMargin;
            } else if (i8 != 80) {
                i6 = getPaddingTop() + layoutParams.topMargin;
                view.layout(i4, i6, measuredWidth + i4, measuredHeight + i6);
                if (z) {
                    return;
                }
                adjustChildView(view, i2);
                return;
            } else {
                height = (getHeight() - getPaddingBottom()) - measuredHeight;
                i5 = layoutParams.bottomMargin;
            }
            i6 = height - i5;
            view.layout(i4, i6, measuredWidth + i4, measuredHeight + i6);
            if (z) {
            }
        } else {
            width = (getWidth() + getPaddingRight()) - measuredWidth;
            i3 = layoutParams.rightMargin;
        }
        i4 = width - i3;
        if (i8 != 16) {
        }
        i6 = height - i5;
        view.layout(i4, i6, measuredWidth + i4, measuredHeight + i6);
        if (z) {
        }
    }

    private void removeAndAddToCache(int i2) {
        while (getChildCount() - i2 > 0) {
            View childAt = getChildAt(0);
            removeViewInLayout(childAt);
            this.cacheItems.add(childAt);
        }
    }

    private void requestParentDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private void setTopView() {
        if (getChildCount() > 0) {
            View childAt = getChildAt(this.LAST_OBJECT_IN_STACK);
            this.mActiveCard = childAt;
            if (childAt == null || this.mFlingListener == null) {
                return;
            }
            SwipeFlingListener swipeFlingListener = new SwipeFlingListener(childAt, this.mAdapter.getItem(0), new SwipeFlingListener.FlingListener() { // from class: com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingAdapterView.3
                @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingListener.FlingListener
                public void leftExit(Object obj) {
                    SwipeFlingAdapterView.this.mFlingListener.onLeftCardExit(obj);
                }

                @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingListener.FlingListener
                public void onCardExited(boolean z) {
                    SwipeFlingAdapterView.this.isAnimRunning = false;
                    SwipeFlingAdapterView swipeFlingAdapterView = SwipeFlingAdapterView.this;
                    swipeFlingAdapterView.removeViewInLayout(swipeFlingAdapterView.mActiveCard);
                    SwipeFlingAdapterView.this.cacheItems.add(SwipeFlingAdapterView.this.mActiveCard);
                    SwipeFlingAdapterView.this.mActiveCard = null;
                    SwipeFlingAdapterView.this.mFlingListener.removeFirstObjectInAdapter(z);
                }

                @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingListener.FlingListener
                public void onClick(MotionEvent motionEvent, View view, Object obj) {
                    if (SwipeFlingAdapterView.this.mOnItemClickListener != null) {
                        SwipeFlingAdapterView.this.mOnItemClickListener.onItemClicked(motionEvent, view, obj);
                    }
                }

                @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingListener.FlingListener
                public void onScroll(float f2, float f3) {
                    SwipeFlingAdapterView.this.adjustChildrenOfUnderTopView(f2);
                    SwipeFlingAdapterView.this.mFlingListener.onScroll(f2, f3);
                }

                @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingListener.FlingListener
                public void rightExit(Object obj) {
                    SwipeFlingAdapterView.this.mFlingListener.onRightCardExit(obj);
                }

                @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingListener.FlingListener
                public void swipe(boolean z) {
                    if (SwipeFlingAdapterView.this.mAdapter.getCount() > 1 && !SwipeFlingAdapterView.this.isAnimRunning) {
                        SwipeFlingAdapterView.this.isAnimRunning = true;
                        if (z) {
                            SwipeFlingAdapterView.this.swipeLeft();
                        } else {
                            SwipeFlingAdapterView.this.swipeRight();
                        }
                    }
                }
            });
            this.flingCardListener = swipeFlingListener;
            setOnTouchListener(swipeFlingListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void adjustChildView(View view, int i2) {
        if (i2 <= -1 || i2 >= this.MAX_VISIBLE) {
            return;
        }
        if (i2 > 3) {
            i2 = 3;
        }
        if (this.showOtherInBottom) {
            view.offsetTopAndBottom(this.offsetStep * i2);
        } else {
            view.offsetLeftAndRight(this.offsetStep * i2);
        }
        float f2 = i2;
        view.setScaleX(1.0f - (this.SCALE_STEP * f2));
        view.setScaleY(1.0f - (this.SCALE_STEP * f2));
        if (this.showOtherWithAlpha) {
            view.setAlpha(1.0f - ((this.SCALE_STEP * f2) * ((int) Math.pow(2.0d, (double) (i2 - 1)))));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void adjustChildrenOfUnderTopView(float f2) {
        int i2;
        int childCount = getChildCount();
        int i3 = 1;
        if (childCount > 1) {
            if (childCount == 2) {
                i2 = this.LAST_OBJECT_IN_STACK - 1;
            } else if (childCount == 3) {
                i2 = this.LAST_OBJECT_IN_STACK - 2;
                i3 = 2;
            } else {
                i2 = this.LAST_OBJECT_IN_STACK - 3;
                i3 = 3;
            }
            float abs = Math.abs(f2);
            while (i2 < this.LAST_OBJECT_IN_STACK) {
                View childAt = getChildAt(i2);
                float f3 = i3;
                float f4 = f3 - abs;
                int i4 = (int) (this.offsetStep * f4);
                if (this.showOtherInBottom) {
                    childAt.offsetTopAndBottom((i4 - childAt.getTop()) + this.initTop);
                } else {
                    childAt.offsetLeftAndRight((i4 - childAt.getLeft()) + this.initLeft);
                }
                float f5 = this.SCALE_STEP;
                childAt.setScaleX((1.0f - (f5 * f3)) + (f5 * abs));
                float f6 = this.SCALE_STEP;
                childAt.setScaleY((1.0f - (f3 * f6)) + (f6 * abs));
                if (this.showOtherWithAlpha) {
                    childAt.setAlpha(1.0f - ((this.SCALE_STEP * f4) * ((int) Math.pow(2.0d, (double) (i3 - 1)))));
                }
                i2++;
                i3--;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 2 && Math.abs(x - this.mLastMotionX) < Math.abs(y - this.mLastMotionY)) {
                requestParentDisallowInterceptTouchEvent(false);
            }
        } else {
            requestParentDisallowInterceptTouchEvent(true);
        }
        this.mLastMotionX = x;
        this.mLastMotionY = y;
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new FrameLayout.LayoutParams(getContext(), attributeSet);
    }

    @Override // android.widget.AdapterView
    public Adapter getAdapter() {
        return this.mAdapter;
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.BaseFlingAdapterView
    public /* bridge */ /* synthetic */ int getHeightMeasureSpec() {
        return super.getHeightMeasureSpec();
    }

    @Override // android.widget.AdapterView
    public View getSelectedView() {
        return this.mActiveCard;
    }

    public SwipeFlingListener getTopCardListener() throws NullPointerException {
        SwipeFlingListener swipeFlingListener = this.flingCardListener;
        if (swipeFlingListener != null) {
            return swipeFlingListener;
        }
        throw new NullPointerException("flingCardListener is null");
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.BaseFlingAdapterView
    public /* bridge */ /* synthetic */ int getWidthMeasureSpec() {
        return super.getWidthMeasureSpec();
    }

    public void init(Context context, Adapter adapter) {
        if (context instanceof onFlingListener) {
            this.mFlingListener = (onFlingListener) context;
            if (context instanceof OnItemClickListener) {
                this.mOnItemClickListener = (OnItemClickListener) context;
            }
            setAdapter(adapter);
            return;
        }
        throw new RuntimeException("Activity does not implement SwipeFlingAdapterView.onFlingListener");
    }

    @Override // android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        onFlingListener onflinglistener;
        View view;
        super.onLayout(z, i2, i3, i4, i5);
        Adapter adapter = this.mAdapter;
        if (adapter == null) {
            return;
        }
        this.mInLayout = true;
        int count = adapter.getCount();
        if (count == 0) {
            removeAndAddToCache(0);
        } else {
            View childAt = getChildAt(this.LAST_OBJECT_IN_STACK);
            View view2 = this.mActiveCard;
            if (view2 != null && childAt != null && childAt == view2) {
                removeAndAddToCache(1);
                layoutChildren(1, count);
            } else {
                removeAndAddToCache(0);
                layoutChildren(0, count);
                setTopView();
            }
        }
        this.mInLayout = false;
        if (this.initTop == 0 && this.initLeft == 0 && (view = this.mActiveCard) != null) {
            this.initTop = view.getTop();
            this.initLeft = this.mActiveCard.getLeft();
        }
        if (count >= this.MIN_ADAPTER_STACK || (onflinglistener = this.mFlingListener) == null) {
            return;
        }
        onflinglistener.onAdapterAboutToEmpty(count);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mInLayout) {
            return;
        }
        super.requestLayout();
    }

    @Override // android.widget.AdapterView
    public void setAdapter(Adapter adapter) {
        AdapterDataSetObserver adapterDataSetObserver;
        this.mActiveCard = null;
        Adapter adapter2 = this.mAdapter;
        if (adapter2 != null && (adapterDataSetObserver = this.mDataSetObserver) != null) {
            adapter2.unregisterDataSetObserver(adapterDataSetObserver);
            this.mDataSetObserver = null;
        }
        this.mAdapter = adapter;
        if (adapter != null && this.mDataSetObserver == null) {
            AdapterDataSetObserver adapterDataSetObserver2 = new AdapterDataSetObserver();
            this.mDataSetObserver = adapterDataSetObserver2;
            this.mAdapter.registerDataSetObserver(adapterDataSetObserver2);
        }
        requestLayout();
    }

    public void setFlingListener(onFlingListener onflinglistener) {
        this.mFlingListener = onflinglistener;
    }

    public void setIsNeedSwipe(boolean z) {
        this.isNeedSwipe = z;
    }

    public void setMaxVisible(int i2) {
        this.MAX_VISIBLE = i2;
    }

    public void setMinStackInAdapter(int i2) {
        this.MIN_ADAPTER_STACK = i2;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.BaseFlingAdapterView, android.widget.AdapterView
    public /* bridge */ /* synthetic */ void setSelection(int i2) {
        super.setSelection(i2);
    }

    public void swipeLeft() {
        getTopCardListener().selectLeft(this.animDuration);
        this.scale = 0.0f;
        postDelayed(this.animRunOut, 0L);
    }

    public void swipeRight() {
        View view = this.mAdapter.getView(this.mAdapter.getCount() - 1, getViewForPosition(-1), this);
        view.setTag(R.id.BABELVK_INTERNAL_CONTENT_ID, null);
        if (view.getVisibility() != 8) {
            makeAndAddView(view, -1, true);
            view.setTranslationX(-GlobalManage.getInstance().getScreenWidth());
        }
        this.LAST_OBJECT_IN_STACK = getChildCount() - 1;
        setTopView();
        getTopCardListener().selectRight(this.animDuration);
        this.scale = 1.0f;
        postDelayed(this.animRunIn, 0L);
    }

    public SwipeFlingAdapterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SwipeFlingAdapterView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isNeedSwipe = true;
        this.SCALE_STEP = 0.04f;
        this.MAX_VISIBLE = 4;
        this.LAST_OBJECT_IN_STACK = 0;
        this.scale = 1.0f;
        this.cacheItems = new ArrayList<>();
        this.MIN_ADAPTER_STACK = 6;
        this.ROTATION_DEGREES = 2.0f;
        this.mInLayout = false;
        this.mActiveCard = null;
        this.animDuration = 300;
        this.isAnimRunning = false;
        this.animRunIn = new Runnable() { // from class: com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingAdapterView.1
            @Override // java.lang.Runnable
            public void run() {
                SwipeFlingAdapterView swipeFlingAdapterView = SwipeFlingAdapterView.this;
                swipeFlingAdapterView.adjustChildrenOfUnderTopView(swipeFlingAdapterView.scale);
                SwipeFlingAdapterView.this.mFlingListener.onScroll(SwipeFlingAdapterView.this.scale, 0.0f);
                SwipeFlingAdapterView swipeFlingAdapterView2 = SwipeFlingAdapterView.this;
                float f2 = swipeFlingAdapterView2.scale;
                if (f2 > 0.0f) {
                    float f3 = f2 - 0.125f;
                    swipeFlingAdapterView2.scale = f3;
                    if (f3 < 0.0f) {
                        swipeFlingAdapterView2.scale = 0.0f;
                    }
                    swipeFlingAdapterView2.postDelayed(this, swipeFlingAdapterView2.animDuration / 30);
                }
            }
        };
        this.animRunOut = new Runnable() { // from class: com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingAdapterView.2
            @Override // java.lang.Runnable
            public void run() {
                SwipeFlingAdapterView swipeFlingAdapterView = SwipeFlingAdapterView.this;
                swipeFlingAdapterView.adjustChildrenOfUnderTopView(swipeFlingAdapterView.scale);
                SwipeFlingAdapterView.this.mFlingListener.onScroll(SwipeFlingAdapterView.this.scale, 0.0f);
                SwipeFlingAdapterView swipeFlingAdapterView2 = SwipeFlingAdapterView.this;
                float f2 = swipeFlingAdapterView2.scale;
                if (f2 < 1.0f) {
                    float f3 = f2 + 0.125f;
                    swipeFlingAdapterView2.scale = f3;
                    if (f3 > 1.0f) {
                        swipeFlingAdapterView2.scale = 1.0f;
                    }
                    swipeFlingAdapterView2.postDelayed(this, swipeFlingAdapterView2.animDuration / 30);
                }
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BebelVKSwipeFlingAdapterView, i2, 0);
        this.MAX_VISIBLE = obtainStyledAttributes.getInt(R.styleable.BebelVKSwipeFlingAdapterView_babelvk_max_visible, this.MAX_VISIBLE);
        this.MIN_ADAPTER_STACK = obtainStyledAttributes.getInt(R.styleable.BebelVKSwipeFlingAdapterView_babelvk_min_adapter_stack, this.MIN_ADAPTER_STACK);
        this.ROTATION_DEGREES = obtainStyledAttributes.getFloat(R.styleable.BebelVKSwipeFlingAdapterView_babelvk_rotation_degrees, this.ROTATION_DEGREES);
        this.SCALE_STEP = obtainStyledAttributes.getFloat(R.styleable.BebelVKSwipeFlingAdapterView_babelvk_scale_step, this.SCALE_STEP);
        this.offsetStep = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.BebelVKSwipeFlingAdapterView_babelvk_offset_step, 0);
        this.showOtherInBottom = obtainStyledAttributes.getBoolean(R.styleable.BebelVKSwipeFlingAdapterView_babelvk_show_other_in_bottom, false);
        this.showOtherWithAlpha = obtainStyledAttributes.getBoolean(R.styleable.BebelVKSwipeFlingAdapterView_babelvk_show_other_with_alpha, true);
        obtainStyledAttributes.recycle();
    }
}
