package com.jingdong.app.mall.home.deploy.view.layout.banner2x4;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorBannerItem;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.state.dark.DarkMaskImageView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.jdsdk.utils.NetUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class BannerAdapter extends PagerAdapter {

    /* renamed from: c  reason: collision with root package name */
    private Context f8889c;
    private CarouseFigureImageAdapterListener d;
    private final int a = d.d(30);
    private final int b = d.d(16);

    /* renamed from: e  reason: collision with root package name */
    private int f8890e = 0;

    /* renamed from: f  reason: collision with root package name */
    private int f8891f = 0;

    /* renamed from: g  reason: collision with root package name */
    private boolean f8892g = true;

    /* renamed from: h  reason: collision with root package name */
    private ConcurrentHashMap<Integer, FigureLayout> f8893h = new ConcurrentHashMap<>();

    /* loaded from: classes4.dex */
    public interface CarouseFigureImageAdapterListener {
        MallFloorBannerItem a(int i2);

        int getCount();

        JDDisplayImageOptions getJDDisplayImageOptions();

        void onClick(int i2);
    }

    /* loaded from: classes4.dex */
    public class FigureLayout {
        BannerItemLayout a;
        View b;

        FigureLayout(BannerAdapter bannerAdapter, BannerItemLayout bannerItemLayout, DarkMaskImageView darkMaskImageView, View view) {
            this.a = bannerItemLayout;
            this.b = view;
        }
    }

    public BannerAdapter(Context context, CarouseFigureImageAdapterListener carouseFigureImageAdapterListener) {
        this.f8889c = context;
        this.d = carouseFigureImageAdapterListener;
    }

    private void a(FigureLayout figureLayout, MallFloorBannerItem mallFloorBannerItem) {
        View view;
        if (figureLayout == null || mallFloorBannerItem == null || (view = figureLayout.b) == null) {
            return;
        }
        view.setVisibility("ad".equals(mallFloorBannerItem.sourceTag) ? 0 : 8);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.bottomMargin = d.d(12);
            marginLayoutParams.rightMargin = d.d(12);
            view.setLayoutParams(marginLayoutParams);
        }
    }

    private FigureLayout b(int i2) {
        FigureLayout figureLayout;
        this.d.a(i2);
        ConcurrentHashMap<Integer, FigureLayout> concurrentHashMap = this.f8893h;
        if (concurrentHashMap == null || concurrentHashMap.size() <= 0 || (figureLayout = this.f8893h.get(Integer.valueOf(i2))) == null) {
            BannerItemLayout bannerItemLayout = new BannerItemLayout(this.f8889c);
            bannerItemLayout.setContentDescription(this.f8889c.getString(R.string.home_obstacle_free));
            bannerItemLayout.g();
            bannerItemLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.banner2x4.BannerAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BannerAdapter.this.e(view.getId());
                }
            });
            DarkMaskImageView darkMaskImageView = new DarkMaskImageView(this.f8889c);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.a, this.b);
            darkMaskImageView.setId(R.id.mallfloor_banner_adtag);
            darkMaskImageView.setImageResource(R.drawable.home_icon_tag_advert);
            layoutParams.addRule(11, -1);
            layoutParams.addRule(12, -1);
            bannerItemLayout.addView(darkMaskImageView, layoutParams);
            FigureLayout figureLayout2 = new FigureLayout(this, bannerItemLayout, bannerItemLayout.e(), darkMaskImageView);
            this.f8893h.put(Integer.valueOf(i2), figureLayout2);
            return figureLayout2;
        }
        return figureLayout;
    }

    private void c(FigureLayout figureLayout, int i2, JDDisplayImageOptions jDDisplayImageOptions) {
        MallFloorBannerItem a = this.d.a(i2);
        if (figureLayout == null || figureLayout.a == null || a == null) {
            return;
        }
        a(figureLayout, a);
        figureLayout.a.c(a, i2);
    }

    public void d(boolean z) {
        ConcurrentHashMap<Integer, FigureLayout> concurrentHashMap = this.f8893h;
        if (concurrentHashMap == null || concurrentHashMap.size() < 1) {
            return;
        }
        if (z && this.f8892g) {
            return;
        }
        this.f8892g = z;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    public void e(int i2) {
        if (!NetUtils.isNetworkAvailable() || this.d == null) {
            return;
        }
        f.r0(this, "banner click position = " + i2);
        this.d.onClick(i2);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        CarouseFigureImageAdapterListener carouseFigureImageAdapterListener = this.d;
        if (carouseFigureImageAdapterListener == null) {
            return 0;
        }
        return carouseFigureImageAdapterListener.getCount();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -1;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i2) {
        FigureLayout b;
        try {
            b = b(i2);
            b.a.setId(i2);
            viewGroup.addView(b.a);
            c(b, i2, this.d.getJDDisplayImageOptions());
        } catch (Exception unused) {
            b = b(i2);
        }
        return b.a;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void onPageSelected(int i2) {
        ConcurrentHashMap<Integer, FigureLayout> concurrentHashMap = this.f8893h;
        if (concurrentHashMap == null || concurrentHashMap.size() < 1) {
            return;
        }
        this.f8891f = this.f8890e;
        for (Map.Entry<Integer, FigureLayout> entry : this.f8893h.entrySet()) {
            MallFloorBannerItem a = this.d.a(this.f8891f);
            if (a == null) {
                return;
            }
            int intValue = entry.getKey().intValue();
            FigureLayout value = entry.getValue();
            if (intValue == this.f8891f) {
                value.a.c(a, i2);
            }
        }
        this.f8890e = i2;
    }
}
