package com.jingdong.app.mall.home.floor.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.f.a.a;

/* loaded from: classes4.dex */
public abstract class BaseListItemPagerAdapter<P extends com.jingdong.app.mall.home.r.f.a.a> extends PagerAdapter {
    protected Context a;
    protected P b;

    /* renamed from: c  reason: collision with root package name */
    protected b f9679c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b bVar = BaseListItemPagerAdapter.this.f9679c;
            if (bVar != null) {
                bVar.a(view);
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface b {
        void a(View view);
    }

    protected View d() {
        HomeDraweeView homeDraweeView = new HomeDraweeView(this.a);
        homeDraweeView.setLayoutParams(new LinearLayout.LayoutParams(this.b.R(), this.b.Q()));
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        e.d(homeDraweeView, this.b.P());
        homeDraweeView.setOnClickListener(new a());
        return homeDraweeView;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        f.n(obj);
        viewGroup.removeView((View) obj);
    }

    protected abstract View e();

    protected abstract View f(int i2);

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.b.U();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        View f2;
        if (this.b.X() && i2 == 0) {
            f2 = d();
        } else if (this.b.W() && i2 == getCount() - 1) {
            f2 = e();
        } else if (this.b.X()) {
            f2 = f(i2 - 1);
        } else {
            f2 = f(i2);
        }
        viewGroup.addView(f2);
        return f2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
