package com.jingdong.app.mall.home.floor.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.jingdong.app.mall.home.floor.view.widget.b;
import com.jingdong.app.mall.home.o.a.f;
import java.util.List;

/* loaded from: classes4.dex */
public class IconViewPagerAdapter extends PagerAdapter {
    private List<b> a;

    public IconViewPagerAdapter(List<b> list) {
        this.a = list;
    }

    public List<b> d() {
        return this.a;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        f.n(obj);
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    /* renamed from: e */
    public b instantiateItem(ViewGroup viewGroup, int i2) {
        b bVar = this.a.get(i2);
        if (bVar instanceof View) {
            View view = (View) bVar;
            viewGroup.addView(view);
            view.setTag(Integer.valueOf(i2));
        }
        return bVar;
    }

    public void f(List<b> list) {
        this.a = list;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<b> list = this.a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
