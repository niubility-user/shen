package com.jingdong.common.recommend;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.recommend.ui.homerecommend.HomeRecommendContentLayout;

/* loaded from: classes6.dex */
public class ScrollDispatchHelper {
    public static final String TAG = "ScrollDispatchHelper";
    private int mLastXIntercept;
    private int mLastYIntercept;
    private RecyclerView mParentView;
    private int mTouchSlop;

    /* loaded from: classes6.dex */
    public interface ScrollDispatchChild {
        void allChildToTop();

        boolean canChildScrollVertically(int i2);

        void childScrollBy(int i2, int i3);

        ViewParent getChildParent();

        int getChildTop();

        int getTopSpace();

        void onParentScroll(int i2);

        void onSelfScroll(int i2);

        void scrollStateChange(int i2);

        void setTabSpreadState(boolean z);

        void setTopSpace(int i2);

        void stopScroll();
    }

    /* loaded from: classes6.dex */
    public interface ScrollDispatchParent {
        ScrollDispatchChild getChildView();
    }

    public ScrollDispatchHelper(RecyclerView recyclerView, Context context) {
        this.mParentView = recyclerView;
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.recommend.ScrollDispatchHelper.1
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView2, int i2) {
                    ScrollDispatchChild childView;
                    super.onScrollStateChanged(recyclerView2, i2);
                    if (!(ScrollDispatchHelper.this.mParentView instanceof ScrollDispatchParent) || (childView = ((ScrollDispatchParent) ScrollDispatchHelper.this.mParentView).getChildView()) == null || childView.getChildParent() == null) {
                        return;
                    }
                    childView.scrollStateChange(i2);
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView2, int i2, int i3) {
                    ScrollDispatchChild childView;
                    super.onScrolled(recyclerView2, i2, i3);
                    if (!(ScrollDispatchHelper.this.mParentView instanceof ScrollDispatchParent) || (childView = ((ScrollDispatchParent) ScrollDispatchHelper.this.mParentView).getChildView()) == null || childView.getChildParent() == null || childView.getChildTop() <= childView.getTopSpace()) {
                        return;
                    }
                    childView.allChildToTop();
                }
            });
        }
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private void dealChildSpreadState() {
        ScrollDispatchChild childView;
        RecyclerView recyclerView = this.mParentView;
        if (!(recyclerView instanceof ScrollDispatchParent) || (childView = ((ScrollDispatchParent) recyclerView).getChildView()) == null) {
            return;
        }
        if (childView.getChildParent() == null) {
            childView.setTabSpreadState(true);
        } else if (childView.getChildTop() > childView.getTopSpace()) {
            childView.setTabSpreadState(true);
        } else {
            childView.setTabSpreadState(false);
        }
    }

    private void onChildScrolling(int i2, int[] iArr) {
        ScrollDispatchChild childView;
        int i3;
        RecyclerView recyclerView = this.mParentView;
        if (!(recyclerView instanceof ScrollDispatchParent) || (childView = ((ScrollDispatchParent) recyclerView).getChildView()) == null || childView.getChildParent() == null) {
            return;
        }
        int childTop = childView.getChildTop();
        int topSpace = childView.getTopSpace();
        if (childTop <= topSpace) {
            if (i2 < 0 && !childView.canChildScrollVertically(i2)) {
                iArr[1] = i2;
                this.mParentView.scrollBy(0, i2);
            }
        } else if (i2 >= 0 && (i3 = childTop - topSpace) <= i2) {
            iArr[1] = i3;
            this.mParentView.scrollBy(0, i3);
        } else {
            iArr[1] = i2;
            this.mParentView.scrollBy(0, i2);
        }
        childView.onSelfScroll(i2);
    }

    private void onParentScrolling(int i2, int[] iArr) {
        RecyclerView recyclerView = this.mParentView;
        if (recyclerView instanceof ScrollDispatchParent) {
            ScrollDispatchChild childView = ((ScrollDispatchParent) recyclerView).getChildView();
            if (childView != null && childView.getChildParent() != null) {
                if (shouldChildScroll(i2)) {
                    childView.childScrollBy(0, i2);
                    iArr[1] = i2;
                } else {
                    int childTop = childView.getChildTop() - childView.getTopSpace();
                    if (i2 > childTop) {
                        iArr[1] = i2 - childTop;
                    }
                }
                if (childView instanceof HomeRecommendContentLayout) {
                    childView.onParentScroll(i2);
                }
            }
            if (childView == null || (childView instanceof HomeRecommendContentLayout)) {
                return;
            }
            childView.onParentScroll(i2);
        }
    }

    private boolean shouldChildScroll(int i2) {
        ScrollDispatchChild childView;
        RecyclerView recyclerView = this.mParentView;
        if (!(recyclerView instanceof ScrollDispatchParent) || (childView = ((ScrollDispatchParent) recyclerView).getChildView()) == null || childView.getChildParent() == null || childView.getChildTop() > childView.getTopSpace()) {
            return false;
        }
        return i2 > 0 || childView.canChildScrollVertically(i2);
    }

    public boolean dispatchNestedPreScroll(View view, int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        int i5;
        int i6;
        if (i2 == 0 && i3 == 0) {
            if (iArr2 != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
            }
            return false;
        }
        if (iArr2 != null) {
            view.getLocationInWindow(iArr2);
            i5 = iArr2[0];
            i6 = iArr2[1];
        } else {
            i5 = 0;
            i6 = 0;
        }
        if (iArr == null) {
            iArr = new int[2];
        }
        iArr[0] = 0;
        iArr[1] = 0;
        onNestedPreScroll(view, i2, i3, iArr);
        if (iArr2 != null) {
            view.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i5;
            iArr2[1] = iArr2[1] - i6;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public boolean isChildConsumeTouch(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if ((motionEvent.getAction() & 255) != 2) {
            this.mLastXIntercept = x;
            this.mLastYIntercept = y;
            return false;
        }
        int i2 = x - this.mLastXIntercept;
        int i3 = y - this.mLastYIntercept;
        if (Math.abs(i3) <= Math.abs(i2) || Math.abs(i3) <= this.mTouchSlop) {
            return false;
        }
        return shouldChildScroll(i3);
    }

    public boolean onNestedPreFling(View view, float f2, float f3) {
        ScrollDispatchChild childView;
        RecyclerView recyclerView = this.mParentView;
        if ((recyclerView instanceof ScrollDispatchParent) && (childView = ((ScrollDispatchParent) recyclerView).getChildView()) != null && childView.getChildParent() != null) {
            if (childView.getChildTop() <= childView.getTopSpace()) {
                if (f3 < 0.0f) {
                    this.mParentView.fling(0, (int) f3);
                    return true;
                }
            } else if (f3 > 0.0f) {
                this.mParentView.fling(0, (int) f3);
                return true;
            }
        }
        return false;
    }

    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        dealChildSpreadState();
        if (view instanceof ScrollDispatchParent) {
            onParentScrolling(i3, iArr);
        } else {
            onChildScrolling(i3, iArr);
        }
    }

    public void onNestedScroll(View view, int i2, int i3, int i4, int i5, int i6) {
        if ((view instanceof RecyclerView) && i6 == 1 && !view.canScrollVertically(1)) {
            ((RecyclerView) view).stopScroll();
        }
    }

    public boolean onStartNestedScroll(View view) {
        return view instanceof ScrollDispatchChild;
    }
}
