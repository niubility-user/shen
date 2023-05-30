package com.jingdong.app.mall.bundle.dolphinlib.common.adapter;

import android.widget.RelativeLayout;
import androidx.viewpager.widget.PagerAdapter;

/* loaded from: classes19.dex */
public abstract class AbstractDolphinViewPagerAdapter extends PagerAdapter {
    public abstract RelativeLayout getCurrent(int i2);

    public abstract boolean isChildOnTop(int i2);

    public abstract void setChildOnTop(int i2);
}
