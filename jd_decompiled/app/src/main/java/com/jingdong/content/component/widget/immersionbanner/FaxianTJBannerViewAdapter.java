package com.jingdong.content.component.widget.immersionbanner;

import android.view.View;
import android.view.ViewGroup;
import com.jd.lib.un.basewidget.widget.banner.recycle.RecyclingPagerAdapter;

/* loaded from: classes12.dex */
public abstract class FaxianTJBannerViewAdapter extends RecyclingPagerAdapter {
    private static final int MAX = 400;
    private boolean isLoop = true;
    private int mid;
    private int offset;

    @Override // androidx.viewpager.widget.PagerAdapter
    public final int getCount() {
        int itemCount = getItemCount() < 0 ? 0 : getItemCount();
        if (this.isLoop) {
            if (itemCount == 0) {
                return 0;
            }
            if (itemCount == 1) {
                this.mid = 0;
                return 1;
            }
            this.mid = 200;
            int i2 = 200 % itemCount;
            this.offset = i2;
            this.mid = 200 - i2;
            return 400;
        }
        return itemCount;
    }

    public abstract int getItemCount();

    public abstract View getItemView(int i2, View view, ViewGroup viewGroup);

    public final int getMid() {
        return this.mid;
    }

    public final int getRealPosition(int i2) {
        if (this.isLoop) {
            if (getItemCount() <= 1) {
                return 0;
            }
            return i2 % getItemCount();
        }
        return i2;
    }

    @Override // com.jd.lib.un.basewidget.widget.banner.recycle.RecyclingPagerAdapter
    public final View getView(int i2, View view, ViewGroup viewGroup) {
        return getItemView(getRealPosition(i2), view, viewGroup);
    }

    public void isSupportLoop(boolean z) {
        this.isLoop = z;
    }
}
