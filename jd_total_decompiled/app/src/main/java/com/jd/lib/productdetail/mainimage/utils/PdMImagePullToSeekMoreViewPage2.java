package com.jd.lib.productdetail.mainimage.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;

/* loaded from: classes15.dex */
public class PdMImagePullToSeekMoreViewPage2 extends PullToRefreshBase<ViewPager2> {
    public float a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f5242c;
    public PDMainImageMoreViewNew d;

    public PdMImagePullToSeekMoreViewPage2(Context context) {
        super(context);
        this.a = 1.0f;
    }

    public ViewPager2 a(Context context) {
        ViewPager2 viewPager2 = new ViewPager2(context);
        viewPager2.setOrientation(0);
        viewPager2.setOverScrollMode(2);
        return viewPager2;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public BaseLoadingLayout createLoadingLayout(Context context, PullToRefreshBase.Mode mode, TypedArray typedArray) {
        if (this.d == null) {
            this.d = new PDMainImageMoreViewNew(context);
        }
        return this.d;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public /* bridge */ /* synthetic */ ViewPager2 createRefreshableView(Context context, AttributeSet attributeSet) {
        return a(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        getParent().requestDisallowInterceptTouchEvent(true);
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.b = (int) motionEvent.getRawX();
            this.f5242c = (int) motionEvent.getRawY();
        } else if (action == 1) {
            this.a = 1.0f;
        } else if (action == 2) {
            float abs = Math.abs(rawY - this.f5242c) / Math.abs(rawX - this.b);
            if (this.a > abs) {
                this.a = abs;
            }
            getParent().requestDisallowInterceptTouchEvent(this.a <= 0.5f);
        } else if (action == 3) {
            this.a = 1.0f;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.HORIZONTAL;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public boolean isReadyForPullEnd() {
        ViewPager2 refreshableView = getRefreshableView();
        RecyclerView.Adapter adapter = refreshableView.getAdapter();
        return adapter != null && refreshableView.getCurrentItem() == adapter.getItemCount() - 1;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public boolean isReadyForPullStart() {
        return false;
    }

    public void setContentSlideSize(int i2) {
        PDMainImageMoreViewNew pDMainImageMoreViewNew = this.d;
        if (pDMainImageMoreViewNew != null) {
            pDMainImageMoreViewNew.a(i2);
        }
    }

    public PdMImagePullToSeekMoreViewPage2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 1.0f;
    }
}
