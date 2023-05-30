package com.jd.lib.babel.ifloor.view.nesting;

import android.content.Context;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes13.dex */
public class NestViewPagerView extends LinearLayout {
    protected NestViewPagerAdapter adapter;
    protected ViewPager viewPager;

    public NestViewPagerView(Context context) {
        super(context);
        setOrientation(1);
        ViewPagerFixed viewPagerFixed = new ViewPagerFixed(context);
        this.viewPager = viewPagerFixed;
        addView(viewPagerFixed);
    }

    public RecyclerView getCurrent() {
        NestViewPagerAdapter nestViewPagerAdapter = this.adapter;
        if (nestViewPagerAdapter != null) {
            return nestViewPagerAdapter.getCurrent(this.viewPager.getCurrentItem());
        }
        return null;
    }

    public boolean isChildOnTop() {
        NestViewPagerAdapter nestViewPagerAdapter = this.adapter;
        if (nestViewPagerAdapter != null) {
            return nestViewPagerAdapter.isChildOnTop(this.viewPager.getCurrentItem());
        }
        return true;
    }

    public void scrollToTop() {
        NestViewPagerAdapter nestViewPagerAdapter = this.adapter;
        if (nestViewPagerAdapter != null) {
            nestViewPagerAdapter.setChildOnTop(this.viewPager.getCurrentItem());
        }
    }
}
