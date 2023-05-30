package com.jingdong.app.mall.home.floor.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.i.j;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.f;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorBannerItem;
import com.jingdong.app.mall.home.floor.view.widget.BannerFrameLayout;
import com.jingdong.app.mall.home.state.dark.DarkMaskImageView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.jdsdk.utils.NetUtils;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class CarouseFigureLayoutPagerAdapter extends PagerAdapter {

    /* renamed from: j  reason: collision with root package name */
    private static JDDisplayImageOptions f9682j;

    /* renamed from: c  reason: collision with root package name */
    private Typeface f9683c;
    private Context d;

    /* renamed from: e  reason: collision with root package name */
    private c f9684e;
    private final int a = com.jingdong.app.mall.home.floor.common.d.d(30);
    private final int b = com.jingdong.app.mall.home.floor.common.d.d(16);

    /* renamed from: f  reason: collision with root package name */
    private int f9685f = 0;

    /* renamed from: g  reason: collision with root package name */
    private int f9686g = 0;

    /* renamed from: h  reason: collision with root package name */
    private boolean f9687h = true;

    /* renamed from: i  reason: collision with root package name */
    private ConcurrentHashMap<Integer, d> f9688i = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CarouseFigureLayoutPagerAdapter.this.i(view.getId());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements e {
        final /* synthetic */ int a;

        b(int i2) {
            this.a = i2;
        }

        @Override // com.jingdong.app.mall.home.floor.view.adapter.CarouseFigureLayoutPagerAdapter.e
        public boolean a() {
            return this.a == CarouseFigureLayoutPagerAdapter.this.f9685f;
        }
    }

    /* loaded from: classes4.dex */
    public interface c {
        MallFloorBannerItem a(int i2);

        void b(int i2, boolean z);

        float c();

        int getCount();

        JDDisplayImageOptions getJDDisplayImageOptions();

        void onClick(int i2);
    }

    /* loaded from: classes4.dex */
    public class d {
        BannerFrameLayout a;
        View b;

        /* renamed from: c  reason: collision with root package name */
        RelativeLayout f9690c;
        SimpleDraweeView d;

        /* renamed from: e  reason: collision with root package name */
        GradientTextView f9691e;

        /* renamed from: f  reason: collision with root package name */
        AtomicBoolean f9692f = new AtomicBoolean(false);

        d(CarouseFigureLayoutPagerAdapter carouseFigureLayoutPagerAdapter, BannerFrameLayout bannerFrameLayout, DarkMaskImageView darkMaskImageView, View view, RelativeLayout relativeLayout, SimpleDraweeView simpleDraweeView, GradientTextView gradientTextView) {
            this.a = bannerFrameLayout;
            this.b = view;
            this.f9690c = relativeLayout;
            this.d = simpleDraweeView;
            this.f9691e = gradientTextView;
        }
    }

    /* loaded from: classes4.dex */
    public interface e {
        boolean a();
    }

    static {
        JDDisplayImageOptions resetViewBeforeLoading = f.a().resetViewBeforeLoading(true);
        int i2 = R.drawable.home_icon_label_banner;
        f9682j = resetViewBeforeLoading.showImageOnFail(i2).showImageOnLoading(i2).showImageForEmptyUri(i2);
    }

    public CarouseFigureLayoutPagerAdapter(Context context, c cVar) {
        this.d = context;
        this.f9683c = FontsUtil.getTypeFace(context, 4098);
        this.f9684e = cVar;
    }

    private void b(d dVar, MallFloorBannerItem mallFloorBannerItem) {
        if (dVar == null || dVar.f9690c == null) {
            return;
        }
        if ("1".equals(mallFloorBannerItem.showLabel) && !TextUtils.isEmpty(mallFloorBannerItem.labelText)) {
            dVar.f9690c.setVisibility(0);
            com.jingdong.app.mall.home.floor.ctrl.e.f(mallFloorBannerItem.labelImg, dVar.d, f9682j);
            if (dVar.f9691e != null) {
                int[] o = m.o(mallFloorBannerItem.labelTextColor, -7714027);
                dVar.f9691e.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(22));
                dVar.f9691e.setText(mallFloorBannerItem.labelText);
                dVar.f9691e.setTextGradient(GradientTextView.GradientType.LeftToRight, o);
                if (m.z(o)) {
                    dVar.f9691e.getPaint().setFakeBoldText(true);
                    return;
                } else {
                    dVar.f9691e.getPaint().setFakeBoldText(false);
                    return;
                }
            }
            return;
        }
        dVar.f9690c.setVisibility(8);
    }

    private void c(d dVar, MallFloorBannerItem mallFloorBannerItem) {
        View view;
        if (dVar == null || mallFloorBannerItem == null || (view = dVar.b) == null) {
            return;
        }
        view.setVisibility("ad".equals(mallFloorBannerItem.sourceTag) ? 0 : 8);
        int d2 = com.jingdong.app.mall.home.floor.common.d.d(14);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            int d3 = com.jingdong.app.mall.home.floor.common.d.d(21);
            marginLayoutParams.bottomMargin = d2 + com.jingdong.app.mall.home.floor.common.d.d(h.v - h.u);
            marginLayoutParams.rightMargin = com.jingdong.app.mall.home.floor.common.d.d(14) + d3;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    private boolean d(d dVar, MallFloorBannerItem mallFloorBannerItem, int i2) {
        if (mallFloorBannerItem.isCache.booleanValue()) {
            return false;
        }
        String str = mallFloorBannerItem.videoId;
        if (TextUtils.isEmpty(str)) {
            this.f9684e.b(i2, false);
            return false;
        } else if (!f(mallFloorBannerItem)) {
            dVar.a.u();
            return false;
        } else if (dVar.f9692f.get()) {
            return false;
        } else {
            String md5 = Md5Encrypt.md5(str);
            String g2 = j.g("bannerBgVideo", PreDownLoadManager.VIDEO_SKU_SUFFIX, md5);
            File file = TextUtils.isEmpty(g2) ? null : new File(g2);
            String c2 = com.jingdong.app.mall.home.floor.common.i.h.c(file, md5, com.jingdong.app.mall.home.floor.common.i.h.a);
            if (TextUtils.isEmpty(c2)) {
                return false;
            }
            dVar.f9692f.set(true);
            boolean t = dVar.a.t(new BannerFrameLayout.e(file, c2, str));
            c cVar = this.f9684e;
            if (cVar != null) {
                cVar.b(i2, true);
            }
            return t;
        }
    }

    private d e(int i2) {
        d dVar;
        MallFloorBannerItem a2 = this.f9684e.a(i2);
        ConcurrentHashMap<Integer, d> concurrentHashMap = this.f9688i;
        if (concurrentHashMap == null || concurrentHashMap.size() <= 0 || (dVar = this.f9688i.get(Integer.valueOf(i2))) == null) {
            BannerFrameLayout bannerFrameLayout = new BannerFrameLayout(this.d);
            bannerFrameLayout.setContentDescription(this.d.getString(R.string.home_obstacle_free));
            bannerFrameLayout.s(!TextUtils.isEmpty(a2.videoId), i2, this.f9684e.c());
            bannerFrameLayout.setOnClickListener(new a());
            bannerFrameLayout.x(new b(i2));
            DarkMaskImageView darkMaskImageView = new DarkMaskImageView(this.d);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.a, this.b);
            darkMaskImageView.setId(R.id.mallfloor_banner_adtag);
            darkMaskImageView.setImageResource(R.drawable.home_icon_tag_advert);
            layoutParams.addRule(11, -1);
            layoutParams.addRule(12, -1);
            bannerFrameLayout.addView(darkMaskImageView, layoutParams);
            RelativeLayout relativeLayout = new RelativeLayout(this.d);
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.d);
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            homeDraweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
            GradientTextView gradientTextView = new GradientTextView(this.d);
            relativeLayout.addView(homeDraweeView, new RelativeLayout.LayoutParams(com.jingdong.app.mall.home.floor.common.d.d(160), com.jingdong.app.mall.home.floor.common.d.d(36)));
            gradientTextView.setGravity(17);
            gradientTextView.setMaxLines(1);
            gradientTextView.setEllipsize(TextUtils.TruncateAt.END);
            gradientTextView.setTypeface(this.f9683c);
            gradientTextView.setPadding(com.jingdong.app.mall.home.floor.common.d.d(8), com.jingdong.app.mall.home.floor.common.d.d(2), com.jingdong.app.mall.home.floor.common.d.d(10), 0);
            relativeLayout.addView(gradientTextView, new RelativeLayout.LayoutParams(com.jingdong.app.mall.home.floor.common.d.d(160), com.jingdong.app.mall.home.floor.common.d.d(36)));
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(com.jingdong.app.mall.home.floor.common.d.d(160), com.jingdong.app.mall.home.floor.common.d.d(36));
            layoutParams2.setMargins(com.jingdong.app.mall.home.floor.common.d.d(24), 0, 0, 0);
            layoutParams2.addRule(10, -1);
            layoutParams2.addRule(9, -1);
            bannerFrameLayout.addView(relativeLayout, layoutParams2);
            d dVar2 = new d(this, bannerFrameLayout, bannerFrameLayout.m(), darkMaskImageView, relativeLayout, homeDraweeView, gradientTextView);
            this.f9688i.put(Integer.valueOf(i2), dVar2);
            return dVar2;
        }
        return dVar;
    }

    private boolean f(MallFloorBannerItem mallFloorBannerItem) {
        if (TextUtils.isEmpty(mallFloorBannerItem.videoId)) {
            return false;
        }
        int m2 = m.m("HOME_BANNER_VIDEO_DAILY_TIME" + mallFloorBannerItem.videoId);
        int i2 = mallFloorBannerItem.videoLimit;
        return i2 <= 0 || i2 > m2;
    }

    private void g(d dVar, int i2, JDDisplayImageOptions jDDisplayImageOptions) {
        BannerFrameLayout bannerFrameLayout;
        MallFloorBannerItem a2 = this.f9684e.a(i2);
        if (dVar == null || (bannerFrameLayout = dVar.a) == null || a2 == null) {
            return;
        }
        bannerFrameLayout.i(a2);
        c(dVar, a2);
        b(dVar, a2);
        dVar.a.k(a2, i2, d(dVar, a2, i2));
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        c cVar = this.f9684e;
        if (cVar != null) {
            return cVar.getCount();
        }
        return 0;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -1;
    }

    public void h(boolean z) {
        ConcurrentHashMap<Integer, d> concurrentHashMap = this.f9688i;
        if (concurrentHashMap == null || concurrentHashMap.size() < 1) {
            return;
        }
        if (z && this.f9687h) {
            return;
        }
        this.f9687h = z;
        d dVar = this.f9688i.get(Integer.valueOf(this.f9685f));
        if (dVar != null) {
            dVar.a.w(z);
        }
    }

    public void i(int i2) {
        if (!NetUtils.isNetworkAvailable() || this.f9684e == null) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.r0(this, "banner click position = " + i2);
        this.f9684e.onClick(i2);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i2) {
        d e2;
        try {
            e2 = e(i2);
            e2.a.setId(i2);
            viewGroup.addView(e2.a);
            g(e2, i2, this.f9684e.getJDDisplayImageOptions());
        } catch (Exception unused) {
            e2 = e(i2);
        }
        return e2.a;
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
        ConcurrentHashMap<Integer, d> concurrentHashMap = this.f9688i;
        if (concurrentHashMap == null || concurrentHashMap.size() < 1) {
            return;
        }
        this.f9686g = this.f9685f;
        for (Map.Entry<Integer, d> entry : this.f9688i.entrySet()) {
            MallFloorBannerItem a2 = this.f9684e.a(this.f9686g);
            if (a2 == null) {
                return;
            }
            int intValue = entry.getKey().intValue();
            d value = entry.getValue();
            value.a.w(intValue == i2);
            if (intValue == this.f9686g && !f(a2)) {
                value.a.u();
                value.a.k(a2, i2, false);
            }
        }
        this.f9685f = i2;
    }
}
