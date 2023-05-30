package com.jingdong.common.jdmiaosha.view.nestedrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes5.dex */
public abstract class ParentRecyclerView extends RecyclerView implements NestedScrollingParent {
    private boolean allowNestScroll;
    private float ceilingLevel;
    private View dyView;
    private FlingHelper helper;
    public boolean isCeilingByScrollHeight;
    public final ItemHeightRecorder itemHeightRecorder;
    private float lastY;
    private ParentSrcollListener mParentScrollListener;
    private OnNestFlingListener onNestFlingListener;
    private boolean startFling;
    public int stopScrollWhenRemain;
    private int totalDy;
    public int totalScrollRange;
    private int velocityY;

    public ParentRecyclerView(Context context) {
        super(context);
        this.itemHeightRecorder = new ItemHeightRecorder();
        this.stopScrollWhenRemain = -1;
        this.ceilingLevel = 0.0f;
        this.totalScrollRange = -1;
        this.isCeilingByScrollHeight = false;
        this.onNestFlingListener = new OnNestFlingListener() { // from class: com.jingdong.common.jdmiaosha.view.nestedrecyclerview.ParentRecyclerView.1
            @Override // com.jingdong.common.jdmiaosha.view.nestedrecyclerview.OnNestFlingListener
            public boolean canParentScroll() {
                return !ParentRecyclerView.this.isInBottom();
            }

            @Override // com.jingdong.common.jdmiaosha.view.nestedrecyclerview.OnNestFlingListener
            public void onNestFling(int i2, int i3) {
                ParentRecyclerView.this.setInBottom(false);
                ParentRecyclerView.this.fling(i2, i3);
            }
        };
        initView(context);
    }

    static /* synthetic */ int access$312(ParentRecyclerView parentRecyclerView, int i2) {
        int i3 = parentRecyclerView.totalDy + i2;
        parentRecyclerView.totalDy = i3;
        return i3;
    }

    private void handleCeiling() {
        if (this.stopScrollWhenRemain > 0) {
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                int scrollY = this.itemHeightRecorder.getScrollY(((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition(), getChildAt(0));
                int totalScrollRange = getTotalScrollRange();
                float f2 = scrollY / (totalScrollRange - this.stopScrollWhenRemain);
                this.ceilingLevel = f2;
                if (this.isCeilingByScrollHeight) {
                    if (Float.compare(f2, 1.0f) == 1) {
                        scrollBy(0, -(scrollY - (totalScrollRange - this.stopScrollWhenRemain)));
                    }
                    if (Float.compare(this.ceilingLevel, 0.0f) == -1) {
                        scrollBy(0, -(scrollY - (totalScrollRange - this.stopScrollWhenRemain)));
                    }
                }
            }
        }
    }

    private void initView(Context context) {
        this.helper = new FlingHelper(context);
        this.dyView = new View(context);
        addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.jdmiaosha.view.nestedrecyclerview.ParentRecyclerView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                if (i2 == 0) {
                    ParentRecyclerView.this.velocityY = 0;
                    if (ParentRecyclerView.this.allowNestScroll) {
                        ParentRecyclerView parentRecyclerView = ParentRecyclerView.this;
                        parentRecyclerView.setInBottom(parentRecyclerView.isInBottom());
                    }
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                if (ParentRecyclerView.this.allowNestScroll) {
                    if (ParentRecyclerView.this.startFling) {
                        ParentRecyclerView.this.totalDy = 0;
                    }
                    ParentRecyclerView.access$312(ParentRecyclerView.this, i3);
                    if (ParentRecyclerView.this.startFling) {
                        ParentRecyclerView.this.startFling = false;
                    }
                    boolean isInBottom = ParentRecyclerView.this.isInBottom();
                    if (ParentRecyclerView.this.mParentScrollListener != null) {
                        ParentRecyclerView.this.mParentScrollListener.ceilingStateChange(isInBottom, ParentRecyclerView.this.ceilingLevel);
                        if (ParentRecyclerView.this.dyView != null) {
                            ParentRecyclerView.this.dyView.scrollBy(0, i3);
                            ParentRecyclerView.this.mParentScrollListener.parentSrcollDy(ParentRecyclerView.this.dyView.getScrollY());
                        }
                    }
                    if (isInBottom && ParentRecyclerView.this.velocityY != 0) {
                        double splineFlingDistance = ParentRecyclerView.this.helper.getSplineFlingDistance(ParentRecyclerView.this.velocityY);
                        if (splineFlingDistance > ParentRecyclerView.this.totalDy) {
                            FlingHelper flingHelper = ParentRecyclerView.this.helper;
                            double d = ParentRecyclerView.this.totalDy;
                            Double.isNaN(d);
                            ParentRecyclerView.this.childFling(flingHelper.getVelocityByDistance(splineFlingDistance - d));
                        }
                    }
                    ParentRecyclerView.this.setInBottom(isInBottom);
                    return;
                }
                ParentRecyclerView.this.setInBottom(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isInBottom() {
        handleCeiling();
        if (this.isCeilingByScrollHeight && this.stopScrollWhenRemain > 0) {
            return this.ceilingLevel >= 1.0f;
        }
        boolean z = !ViewCompat.canScrollVertically(this, 1);
        if (z) {
            this.ceilingLevel = 1.0f;
        }
        return z;
    }

    public void childFling(int i2) {
        NestRecyclerView child = getChild();
        if (child != null) {
            child.fling(0, i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f2, float f3) {
        NestRecyclerView child = getChild();
        if (this.allowNestScroll && isInBottom() && child != null && !isChildOnTop(child)) {
            child.fling(0, (int) f3);
            return true;
        }
        return super.dispatchNestedPreFling(f2, f3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean fling(int i2, int i3) {
        boolean fling = super.fling(i2, i3);
        if (this.allowNestScroll && fling && i3 > 0) {
            this.startFling = true;
            this.velocityY = i3;
        } else {
            this.velocityY = 0;
        }
        return fling;
    }

    public abstract NestRecyclerView getChild();

    @Override // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return 0;
    }

    public int getTotalScrollRange() {
        if (this.totalScrollRange == -1) {
            int itemCount = getAdapter() != null ? getAdapter().getItemCount() - 1 : 0;
            this.totalScrollRange = 0;
            for (int i2 = 0; i2 < itemCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt != null) {
                    this.totalScrollRange += childAt.getHeight();
                    ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                    if (layoutParams instanceof RecyclerView.LayoutParams) {
                        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
                        int i3 = this.totalScrollRange + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin;
                        this.totalScrollRange = i3;
                        this.totalScrollRange = i3 + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin;
                        int bottomDecorationHeight = getLayoutManager().getBottomDecorationHeight(childAt);
                        int topDecorationHeight = getLayoutManager().getTopDecorationHeight(childAt);
                        int i4 = this.totalScrollRange + bottomDecorationHeight;
                        this.totalScrollRange = i4;
                        this.totalScrollRange = i4 + topDecorationHeight;
                    }
                }
            }
            this.totalScrollRange += itemCount == 3 ? DPIUtil.dip2px(10.0f) : 0;
        }
        return this.totalScrollRange;
    }

    public abstract boolean isChildOnTop();

    public boolean isChildOnTop(View view) {
        if (view == null) {
            return true;
        }
        return !view.canScrollVertically(-1);
    }

    public boolean isNestCanScorll() {
        try {
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (layoutManager != null) {
                if (!layoutManager.canScrollVertically()) {
                    if (!isChildOnTop()) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        if (view instanceof NestRecyclerView) {
            ((NestRecyclerView) view).setOnNestFlingListener(this.onNestFlingListener);
            return false;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view, float f2, float f3) {
        if (isInBottom() || f3 == 0.0f) {
            return false;
        }
        fling(0, (int) f3);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        boolean isInBottom = isInBottom();
        boolean isChildOnTop = isChildOnTop(view);
        if (isInBottom && isChildOnTop && i3 < 0) {
            setInBottom(false);
            scrollBy(0, i3);
            iArr[1] = i3;
        }
        if (isInBottom) {
            return;
        }
        scrollBy(0, i3);
        iArr[1] = i3;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view, View view2, int i2) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(View view) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        NestRecyclerView child;
        if (this.allowNestScroll && isInBottom() && (child = getChild()) != null) {
            int y = (int) (this.lastY - motionEvent.getY());
            if (y <= 0 && isChildOnTop(child)) {
                setInBottom(false);
            } else {
                child.onNestedScroll(this, 0, 0, 0, y);
            }
        }
        if (motionEvent.getActionMasked() == 1) {
            setInBottom(false);
        }
        this.lastY = motionEvent.getY();
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void scrollToPosition(int i2) {
        View view;
        super.scrollToPosition(i2);
        if (i2 != 0 || (view = this.dyView) == null) {
            return;
        }
        view.scrollTo(0, 0);
    }

    public void setAllowNestScroll(boolean z) {
        this.allowNestScroll = z;
    }

    public void setInBottom(boolean z) {
        if (getLayoutManager() instanceof INestLayoutManager) {
            ((INestLayoutManager) getLayoutManager()).setInBottom(z);
        }
    }

    public void setParentSrcollListener(ParentSrcollListener parentSrcollListener) {
        this.mParentScrollListener = parentSrcollListener;
    }

    public ParentRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.itemHeightRecorder = new ItemHeightRecorder();
        this.stopScrollWhenRemain = -1;
        this.ceilingLevel = 0.0f;
        this.totalScrollRange = -1;
        this.isCeilingByScrollHeight = false;
        this.onNestFlingListener = new OnNestFlingListener() { // from class: com.jingdong.common.jdmiaosha.view.nestedrecyclerview.ParentRecyclerView.1
            @Override // com.jingdong.common.jdmiaosha.view.nestedrecyclerview.OnNestFlingListener
            public boolean canParentScroll() {
                return !ParentRecyclerView.this.isInBottom();
            }

            @Override // com.jingdong.common.jdmiaosha.view.nestedrecyclerview.OnNestFlingListener
            public void onNestFling(int i2, int i3) {
                ParentRecyclerView.this.setInBottom(false);
                ParentRecyclerView.this.fling(i2, i3);
            }
        };
        initView(context);
    }

    public ParentRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.itemHeightRecorder = new ItemHeightRecorder();
        this.stopScrollWhenRemain = -1;
        this.ceilingLevel = 0.0f;
        this.totalScrollRange = -1;
        this.isCeilingByScrollHeight = false;
        this.onNestFlingListener = new OnNestFlingListener() { // from class: com.jingdong.common.jdmiaosha.view.nestedrecyclerview.ParentRecyclerView.1
            @Override // com.jingdong.common.jdmiaosha.view.nestedrecyclerview.OnNestFlingListener
            public boolean canParentScroll() {
                return !ParentRecyclerView.this.isInBottom();
            }

            @Override // com.jingdong.common.jdmiaosha.view.nestedrecyclerview.OnNestFlingListener
            public void onNestFling(int i22, int i3) {
                ParentRecyclerView.this.setInBottom(false);
                ParentRecyclerView.this.fling(i22, i3);
            }
        };
        initView(context);
    }
}
