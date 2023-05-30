package com.jd.lib.babel.ifloor.view.nesting;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes13.dex */
public class ParentRecyclerView extends RecyclerView implements NestedScrollingParent {
    private boolean allowNestScroll;
    private FlingHelper helper;
    private float lastY;
    private OnNestFlingListener onNestFlingListener;
    private boolean startFling;
    private int totalDy;
    private int velocityY;

    public ParentRecyclerView(Context context) {
        super(context);
        this.onNestFlingListener = new OnNestFlingListener() { // from class: com.jd.lib.babel.ifloor.view.nesting.ParentRecyclerView.1
            @Override // com.jd.lib.babel.ifloor.view.nesting.OnNestFlingListener
            public boolean canParentScroll() {
                return !ParentRecyclerView.this.isInBottom();
            }

            @Override // com.jd.lib.babel.ifloor.view.nesting.OnNestFlingListener
            public void onNestFling(int i2, int i3) {
                ParentRecyclerView.this.setInBottom(false);
                ParentRecyclerView.this.fling(i2, i3);
            }
        };
        initView(context);
    }

    static /* synthetic */ int access$412(ParentRecyclerView parentRecyclerView, int i2) {
        int i3 = parentRecyclerView.totalDy + i2;
        parentRecyclerView.totalDy = i3;
        return i3;
    }

    private void initView(Context context) {
        this.helper = new FlingHelper(context);
        addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jd.lib.babel.ifloor.view.nesting.ParentRecyclerView.2
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
                if (!ParentRecyclerView.this.allowNestScroll || i3 == 0) {
                    ParentRecyclerView.this.setInBottom(false);
                    return;
                }
                if (ParentRecyclerView.this.startFling) {
                    ParentRecyclerView.this.totalDy = 0;
                }
                ParentRecyclerView.access$412(ParentRecyclerView.this, i3);
                if (ParentRecyclerView.this.startFling) {
                    ParentRecyclerView.this.startFling = false;
                }
                boolean isInBottom = ParentRecyclerView.this.isInBottom();
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
            }
        });
    }

    private boolean isChildOnTop() {
        View childAt = getChildAt(getChildCount() - 1);
        if (childAt == null || !(childAt instanceof NestViewPagerView)) {
            return true;
        }
        return ((NestViewPagerView) childAt).isChildOnTop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isInBottom() {
        return true ^ ViewCompat.canScrollVertically(this, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setInBottom(boolean z) {
        if (getLayoutManager() instanceof INestLayoutManager) {
            ((INestLayoutManager) getLayoutManager()).setInBottom(z);
        }
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

    public NestRecyclerView getChild() {
        RecyclerView current;
        View childAt = getChildAt(getChildCount() - 1);
        if (childAt == null || !(childAt instanceof NestViewPagerView) || (current = ((NestViewPagerView) childAt).getCurrent()) == null || !(current instanceof NestRecyclerView)) {
            return null;
        }
        return (NestRecyclerView) current;
    }

    @Override // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return 0;
    }

    public boolean isNestCanScorll() {
        return (getLayoutManager().canScrollVertically() || isChildOnTop()) ? false : true;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        post(new Runnable() { // from class: com.jd.lib.babel.ifloor.view.nesting.ParentRecyclerView.3
            @Override // java.lang.Runnable
            public void run() {
                ParentRecyclerView.this.velocityY = 0;
                if (ParentRecyclerView.this.allowNestScroll) {
                    ParentRecyclerView.this.setInBottom(false);
                }
            }
        });
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

    public void setAllowNestScroll(boolean z) {
        this.allowNestScroll = z;
    }

    public ParentRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onNestFlingListener = new OnNestFlingListener() { // from class: com.jd.lib.babel.ifloor.view.nesting.ParentRecyclerView.1
            @Override // com.jd.lib.babel.ifloor.view.nesting.OnNestFlingListener
            public boolean canParentScroll() {
                return !ParentRecyclerView.this.isInBottom();
            }

            @Override // com.jd.lib.babel.ifloor.view.nesting.OnNestFlingListener
            public void onNestFling(int i2, int i3) {
                ParentRecyclerView.this.setInBottom(false);
                ParentRecyclerView.this.fling(i2, i3);
            }
        };
        initView(context);
    }

    public boolean isChildOnTop(View view) {
        return !view.canScrollVertically(-1);
    }

    public ParentRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.onNestFlingListener = new OnNestFlingListener() { // from class: com.jd.lib.babel.ifloor.view.nesting.ParentRecyclerView.1
            @Override // com.jd.lib.babel.ifloor.view.nesting.OnNestFlingListener
            public boolean canParentScroll() {
                return !ParentRecyclerView.this.isInBottom();
            }

            @Override // com.jd.lib.babel.ifloor.view.nesting.OnNestFlingListener
            public void onNestFling(int i22, int i3) {
                ParentRecyclerView.this.setInBottom(false);
                ParentRecyclerView.this.fling(i22, i3);
            }
        };
        initView(context);
    }
}
