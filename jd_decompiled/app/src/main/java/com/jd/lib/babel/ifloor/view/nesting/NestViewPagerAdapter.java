package com.jd.lib.babel.ifloor.view.nesting;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

/* loaded from: classes13.dex */
public abstract class NestViewPagerAdapter extends PagerAdapter {
    public abstract RecyclerView getCurrent(int i2);

    public abstract boolean isChildOnTop(int i2);

    public abstract void setChildOnTop(int i2);
}
