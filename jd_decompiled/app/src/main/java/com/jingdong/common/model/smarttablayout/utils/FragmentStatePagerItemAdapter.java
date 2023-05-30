package com.jingdong.common.model.smarttablayout.utils;

import android.view.ViewGroup;
import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class FragmentStatePagerItemAdapter extends FragmentStatePagerAdapter {
    private final SparseArrayCompat<WeakReference<Fragment>> holder;
    private final FragmentPagerItems pages;

    public FragmentStatePagerItemAdapter(FragmentManager fragmentManager, FragmentPagerItems fragmentPagerItems) {
        super(fragmentManager);
        this.pages = fragmentPagerItems;
        this.holder = new SparseArrayCompat<>(fragmentPagerItems.size());
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        this.holder.remove(i2);
        super.destroyItem(viewGroup, i2, obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.pages.size();
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i2) {
        return getPagerItem(i2).instantiate(this.pages.getContext(), i2);
    }

    public Fragment getPage(int i2) {
        WeakReference<Fragment> weakReference = this.holder.get(i2);
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

    protected FragmentPagerItem getPagerItem(int i2) {
        return (FragmentPagerItem) this.pages.get(i2);
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        Object instantiateItem = super.instantiateItem(viewGroup, i2);
        if (instantiateItem instanceof Fragment) {
            this.holder.put(i2, new WeakReference<>((Fragment) instantiateItem));
        }
        return instantiateItem;
    }
}
