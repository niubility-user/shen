package com.jingdong.app.mall.bundle.dolphinlib.common.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.bundle.dolphinlib.common.adapter.AbstractDolphinViewPagerAdapter;

/* loaded from: classes19.dex */
public class DolphinViewPagerContainer extends LinearLayout {
    private AbstractDolphinViewPagerAdapter adapter;
    private DolphinViewPager viewPager;

    public DolphinViewPagerContainer(Context context) {
        super(context);
        if (context != null) {
            setOrientation(1);
            DolphinViewPager dolphinViewPager = new DolphinViewPager(context);
            this.viewPager = dolphinViewPager;
            dolphinViewPager.setPadding(0, 0, 0, 0);
            addView(this.viewPager);
        }
    }

    public AbstractDolphinViewPagerAdapter getAdapter() {
        return this.adapter;
    }

    public RelativeLayout getCurrent() {
        AbstractDolphinViewPagerAdapter abstractDolphinViewPagerAdapter = this.adapter;
        if (abstractDolphinViewPagerAdapter != null) {
            return abstractDolphinViewPagerAdapter.getCurrent(this.viewPager.getCurrentItem());
        }
        return null;
    }

    public DolphinViewPager getViewPager() {
        return this.viewPager;
    }

    public boolean isChildOnTop() {
        AbstractDolphinViewPagerAdapter abstractDolphinViewPagerAdapter = this.adapter;
        if (abstractDolphinViewPagerAdapter != null) {
            return abstractDolphinViewPagerAdapter.isChildOnTop(this.viewPager.getCurrentItem());
        }
        return true;
    }

    public void scrollToTop() {
        AbstractDolphinViewPagerAdapter abstractDolphinViewPagerAdapter = this.adapter;
        if (abstractDolphinViewPagerAdapter != null) {
            abstractDolphinViewPagerAdapter.setChildOnTop(this.viewPager.getCurrentItem());
        }
    }

    public void setAdapter(AbstractDolphinViewPagerAdapter abstractDolphinViewPagerAdapter) {
        this.adapter = abstractDolphinViewPagerAdapter;
        this.viewPager.setAdapter(abstractDolphinViewPagerAdapter);
    }
}
