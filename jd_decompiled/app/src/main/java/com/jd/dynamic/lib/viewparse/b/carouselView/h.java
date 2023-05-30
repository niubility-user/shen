package com.jd.dynamic.lib.viewparse.b.carouselView;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jd.dynamic.lib.views.ItemView;

/* loaded from: classes13.dex */
public class h extends PagerAdapter implements ViewPager.OnPageChangeListener, o {

    /* renamed from: g */
    ViewPager f2351g;

    /* renamed from: h */
    TextView f2352h;

    /* renamed from: i */
    f f2353i;

    /* renamed from: j */
    private int f2354j;

    /* renamed from: k */
    private View f2355k;

    public h(FragmentActivity fragmentActivity, ViewPager viewPager, f fVar) {
        boolean z = fragmentActivity instanceof Activity;
        this.f2353i = fVar;
        this.f2351g = viewPager;
        viewPager.setOffscreenPageLimit(2);
        this.f2351g.setOnPageChangeListener(this);
    }

    @Override // com.jd.dynamic.lib.viewparse.b.carouselView.o
    /* renamed from: a */
    public View getF2357g() {
        View view = this.f2355k;
        if (view != null) {
            return view;
        }
        return null;
    }

    public void b(int i2) {
        this.f2354j = i2;
        this.f2351g.setCurrentItem(i2, true);
    }

    public void c(TextView textView) {
        this.f2352h = textView;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i2, @NonNull Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        f fVar = this.f2353i;
        if (fVar != null) {
            return fVar.u();
        }
        return 0;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(@NonNull Object obj) {
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i2) {
        int u = i2 % this.f2353i.u();
        f fVar = this.f2353i;
        ItemView j2 = fVar.j(fVar.b(u));
        if (j2 != null) {
            View parse = j2.parse();
            j2.bindData(parse, this.f2353i.k(u));
            viewGroup.addView(parse);
            return parse;
        }
        return super.instantiateItem(viewGroup, i2);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        if (this.f2352h != null) {
            this.f2352h.setText("" + (i2 + 1));
        }
        this.f2354j = i2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(@NonNull ViewGroup viewGroup, int i2, @NonNull Object obj) {
        if (obj instanceof View) {
            this.f2355k = (View) obj;
        }
        super.setPrimaryItem(viewGroup, i2, obj);
    }
}
