package com.jingdong.app.mall.utils.ui.view;

import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

/* loaded from: classes4.dex */
public class LoopPagerAdapterWrapper extends PagerAdapter {
    private PagerAdapter a;
    private SparseArray<a> b = new SparseArray<>();

    /* renamed from: c  reason: collision with root package name */
    private boolean f11980c;

    /* loaded from: classes4.dex */
    static class a {
        Object a;

        public a(Object obj) {
            this.a = obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoopPagerAdapterWrapper(PagerAdapter pagerAdapter) {
        this.a = pagerAdapter;
    }

    private int e() {
        return 1;
    }

    private int f() {
        return (e() + getRealCount()) - 1;
    }

    public PagerAdapter d() {
        return this.a;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        int e2 = e();
        int f2 = f();
        PagerAdapter pagerAdapter = this.a;
        int i3 = ((pagerAdapter instanceof FragmentPagerAdapter) || (pagerAdapter instanceof FragmentStatePagerAdapter)) ? i2 : i(i2);
        if (this.f11980c && (i2 == e2 || i2 == f2)) {
            this.b.put(i2, new a(obj));
        } else {
            this.a.destroyItem(viewGroup, i3, obj);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void finishUpdate(ViewGroup viewGroup) {
        this.a.finishUpdate(viewGroup);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(boolean z) {
        this.f11980c = z;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.a.getCount() + 2;
    }

    public int getRealCount() {
        return this.a.getCount();
    }

    public int h(int i2) {
        return i2 + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int i(int i2) {
        int realCount = getRealCount();
        if (realCount == 0) {
            return 0;
        }
        int i3 = (i2 - 1) % realCount;
        return i3 < 0 ? i3 + realCount : i3;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        a aVar;
        PagerAdapter pagerAdapter = this.a;
        int i3 = ((pagerAdapter instanceof FragmentPagerAdapter) || (pagerAdapter instanceof FragmentStatePagerAdapter)) ? i2 : i(i2);
        if (this.f11980c && (aVar = this.b.get(i2)) != null) {
            this.b.remove(i2);
            return aVar.a;
        }
        return this.a.instantiateItem(viewGroup, i3);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return this.a.isViewFromObject(view, obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void notifyDataSetChanged() {
        this.b = new SparseArray<>();
        super.notifyDataSetChanged();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        this.a.restoreState(parcelable, classLoader);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Parcelable saveState() {
        return this.a.saveState();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(ViewGroup viewGroup, int i2, Object obj) {
        this.a.setPrimaryItem(viewGroup, i2, obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void startUpdate(ViewGroup viewGroup) {
        this.a.startUpdate(viewGroup);
    }
}
