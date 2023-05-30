package com.jingdong.app.mall.home.floor.view.widget.bubblebanner936;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.f.a.h;
import java.util.ArrayDeque;
import java.util.Queue;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class BubbleAdapter extends PagerAdapter {
    private Context a;
    private Queue<BubbleBannerLayout> b = new ArrayDeque();

    /* renamed from: c  reason: collision with root package name */
    private h f10199c;

    public BubbleAdapter(Context context, h hVar) {
        this.a = context;
        this.f10199c = hVar;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i2, @NonNull Object obj) {
        if (obj instanceof BubbleBannerLayout) {
            f.n(obj);
            BubbleBannerLayout bubbleBannerLayout = (BubbleBannerLayout) obj;
            this.b.offer(bubbleBannerLayout);
            viewGroup.removeView(bubbleBannerLayout);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return (this.f10199c.Y() == 0 || this.f10199c.Y() == 1) ? this.f10199c.Y() : this.f10199c.Y() * this.f10199c.X();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NotNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i2) {
        BubbleBannerLayout poll = this.b.poll();
        if (poll == null) {
            poll = new BubbleBannerLayout(this.a, this.f10199c);
        }
        viewGroup.addView(poll, new ViewPager.LayoutParams());
        poll.a(i2);
        return poll;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }
}
