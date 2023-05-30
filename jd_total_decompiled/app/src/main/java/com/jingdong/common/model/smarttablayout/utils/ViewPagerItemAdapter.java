package com.jingdong.common.model.smarttablayout.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.SparseArrayCompat;
import androidx.viewpager.widget.PagerAdapter;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class ViewPagerItemAdapter extends PagerAdapter {
    private final SparseArrayCompat<WeakReference<View>> holder;
    private final LayoutInflater inflater;
    private final ViewPagerItems pages;

    public ViewPagerItemAdapter(ViewPagerItems viewPagerItems) {
        this.pages = viewPagerItems;
        this.holder = new SparseArrayCompat<>(viewPagerItems.size());
        this.inflater = LayoutInflater.from(viewPagerItems.getContext());
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        this.holder.remove(i2);
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.pages.size();
    }

    public View getPage(int i2) {
        WeakReference<View> weakReference = this.holder.get(i2);
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i2) {
        return getPagerItem(i2).getTitle();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public float getPageWidth(int i2) {
        return getPagerItem(i2).getWidth();
    }

    protected ViewPagerItem getPagerItem(int i2) {
        return (ViewPagerItem) this.pages.get(i2);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        View initiate = getPagerItem(i2).initiate(this.inflater, viewGroup);
        viewGroup.addView(initiate);
        this.holder.put(i2, new WeakReference<>(initiate));
        return initiate;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return obj == view;
    }
}
