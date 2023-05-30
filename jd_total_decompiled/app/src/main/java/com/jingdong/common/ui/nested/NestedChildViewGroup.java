package com.jingdong.common.ui.nested;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ScrollDispatchHelper;
import com.jingdong.common.recommend.ui.PagerSlidingTabStrip;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class NestedChildViewGroup extends LinearLayout implements ScrollDispatchHelper.ScrollDispatchChild {
    public int currentIndex;
    private ArrayList<RecyclerView> mChildViews;
    protected PagerSlidingTabStrip mSlidingTabStrip;
    protected ViewPager mViewPager;

    /* loaded from: classes6.dex */
    public class NestedPagerAdapter extends PagerAdapter {
        public NestedPagerAdapter() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
            if (obj instanceof View) {
                viewGroup.removeView((View) obj);
            }
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return NestedChildViewGroup.this.mChildViews.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object obj) {
            return -2;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        @Nullable
        public CharSequence getPageTitle(int i2) {
            return super.getPageTitle(i2);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i2) {
            viewGroup.addView((View) NestedChildViewGroup.this.mChildViews.get(i2));
            return NestedChildViewGroup.this.mChildViews.get(i2);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public NestedChildViewGroup(@NonNull Context context) {
        super(context);
        this.mChildViews = new ArrayList<>();
        this.currentIndex = 0;
    }

    private void createViewContent() {
        createViewPager();
        createTabSliding();
    }

    public void addChildView(RecyclerView recyclerView) {
        if (this.mViewPager == null) {
            createViewContent();
        }
        this.mChildViews.add(recyclerView);
        if (this.mViewPager.getAdapter() != null) {
            this.mViewPager.getAdapter().notifyDataSetChanged();
        }
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void allChildToTop() {
        for (int i2 = 0; i2 < this.mChildViews.size(); i2++) {
            this.mChildViews.get(i2).scrollToPosition(0);
        }
    }

    protected void beforeOnPageSelected(int i2) {
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public boolean canChildScrollVertically(int i2) {
        if (indexOfChild(this.mViewPager) == -1 || getCurrentChildView() == null) {
            return false;
        }
        return getCurrentChildView().canChildScrollVertically(i2);
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void childScrollBy(int i2, int i3) {
        if (getCurrentChildView() != null) {
            getCurrentChildView().childScrollBy(i2, i3);
        }
    }

    protected void createTabSliding() {
        PagerSlidingTabStrip pagerSlidingTabStrip = new PagerSlidingTabStrip(getContext());
        this.mSlidingTabStrip = pagerSlidingTabStrip;
        pagerSlidingTabStrip.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.mSlidingTabStrip.setTabPaddingLeftRight(0);
        this.mSlidingTabStrip.setIndicatorHeight(0);
        this.mSlidingTabStrip.setViewPager(this.mViewPager);
        addView(this.mSlidingTabStrip, 0);
    }

    protected void createViewPager() {
        if (this.mViewPager != null) {
            return;
        }
        ViewPager viewPager = new ViewPager(getContext());
        this.mViewPager = viewPager;
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.mViewPager.setAdapter(createViewPagerAdapter());
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jingdong.common.ui.nested.NestedChildViewGroup.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f2, int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                NestedChildViewGroup nestedChildViewGroup = NestedChildViewGroup.this;
                nestedChildViewGroup.beforeOnPageSelected(nestedChildViewGroup.currentIndex);
                NestedChildViewGroup.this.currentIndex = i2;
            }
        });
        addView(this.mViewPager);
    }

    public PagerAdapter createViewPagerAdapter() {
        return new NestedPagerAdapter();
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public ViewParent getChildParent() {
        return getParent();
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public int getChildTop() {
        return getTop();
    }

    public ScrollDispatchHelper.ScrollDispatchChild getCurrentChildView() {
        if (this.currentIndex < this.mChildViews.size()) {
            return (ScrollDispatchHelper.ScrollDispatchChild) this.mChildViews.get(this.currentIndex);
        }
        return null;
    }

    public RecyclerView getCurrentView() {
        if (this.currentIndex < this.mChildViews.size()) {
            return this.mChildViews.get(this.currentIndex);
        }
        return null;
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public int getTopSpace() {
        return 0;
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void onParentScroll(int i2) {
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void onSelfScroll(int i2) {
        if (getCurrentChildView() != null) {
            getCurrentChildView().onSelfScroll(i2);
        }
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void scrollStateChange(int i2) {
        if (getCurrentChildView() != null) {
            getCurrentChildView().scrollStateChange(i2);
        }
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void setTabSpreadState(boolean z) {
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void setTopSpace(int i2) {
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void stopScroll() {
        if (getCurrentChildView() != null) {
            getCurrentChildView().stopScroll();
        }
    }

    public NestedChildViewGroup(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mChildViews = new ArrayList<>();
        this.currentIndex = 0;
    }

    public NestedChildViewGroup(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mChildViews = new ArrayList<>();
        this.currentIndex = 0;
    }

    @RequiresApi(api = 21)
    public NestedChildViewGroup(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mChildViews = new ArrayList<>();
        this.currentIndex = 0;
    }

    public NestedChildViewGroup(RecyclerView recyclerView, BaseActivity baseActivity) {
        super(baseActivity);
        this.mChildViews = new ArrayList<>();
        this.currentIndex = 0;
        if (getLayoutParams() == null) {
            setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        }
        setOrientation(1);
    }
}
