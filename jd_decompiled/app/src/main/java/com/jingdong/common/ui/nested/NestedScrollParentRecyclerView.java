package com.jingdong.common.ui.nested;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent2;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.recommend.ScrollDispatchHelper;

/* loaded from: classes6.dex */
public class NestedScrollParentRecyclerView extends RecyclerView implements NestedScrollingParent2, ScrollDispatchHelper.ScrollDispatchParent {
    private ScrollDispatchHelper.ScrollDispatchChild childView;
    private ScrollDispatchHelper scrollHelper;

    public NestedScrollParentRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        ScrollDispatchHelper scrollDispatchHelper;
        if (getScrollState() != 0 && (scrollDispatchHelper = this.scrollHelper) != null) {
            return scrollDispatchHelper.dispatchNestedPreScroll(this, i2, i3, iArr, iArr2, i4);
        }
        return super.dispatchNestedPreScroll(i2, i3, iArr, iArr2, i4);
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchParent
    public ScrollDispatchHelper.ScrollDispatchChild getChildView() {
        return this.childView;
    }

    @Override // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return 2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            ScrollDispatchHelper scrollDispatchHelper = this.scrollHelper;
            if (scrollDispatchHelper == null || !scrollDispatchHelper.isChildConsumeTouch(motionEvent)) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return false;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(@NonNull View view, int i2, int i3, @NonNull int[] iArr, int i4) {
        ScrollDispatchHelper scrollDispatchHelper = this.scrollHelper;
        if (scrollDispatchHelper != null) {
            scrollDispatchHelper.onNestedPreScroll(view, i2, i3, iArr);
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(@NonNull View view, int i2, int i3, int i4, int i5, int i6) {
        ScrollDispatchHelper scrollDispatchHelper = this.scrollHelper;
        if (scrollDispatchHelper != null) {
            scrollDispatchHelper.onNestedScroll(view, i2, i3, i4, i5, i6);
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i2, int i3) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i2, int i3) {
        ScrollDispatchHelper scrollDispatchHelper = this.scrollHelper;
        if (scrollDispatchHelper != null) {
            return scrollDispatchHelper.onStartNestedScroll(view2);
        }
        return false;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(@NonNull View view, int i2) {
    }

    public void setNestedChildView(ScrollDispatchHelper.ScrollDispatchChild scrollDispatchChild) {
        this.childView = scrollDispatchChild;
    }

    public NestedScrollParentRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedScrollParentRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.scrollHelper = new ScrollDispatchHelper(this, context);
    }
}
