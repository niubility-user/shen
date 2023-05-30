package com.jd.manto.center.viewholder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.jd.manto.center.R;
import com.jd.manto.center.adapter.SuperConfigPagerAdapter;
import com.jd.manto.center.c;
import com.jd.manto.center.carouseview.SuperCarouseView;
import com.jd.manto.center.k.e;
import com.jd.manto.center.k.h;
import com.jd.manto.center.model.entity.Banner;
import com.jd.manto.center.model.entity.MantoDiscoveryBean;

/* loaded from: classes17.dex */
public class BannerViewholder extends MantoBaseViewholder {
    private ViewGroup a;
    private SuperCarouseView b;

    /* renamed from: c  reason: collision with root package name */
    private LinearLayout f6547c;
    private View d;

    /* loaded from: classes17.dex */
    class a implements SuperConfigPagerAdapter.b {
        final /* synthetic */ Context a;

        a(BannerViewholder bannerViewholder, Context context) {
            this.a = context;
        }

        @Override // com.jd.manto.center.adapter.SuperConfigPagerAdapter.b
        public void a(Banner banner, int i2) {
            if (banner != null) {
                if (!TextUtils.isEmpty(banner.appid)) {
                    c.d(this.a, banner.appid, banner.appType, "1007");
                    com.jd.manto.center.h.b.e(this.a, banner.appid, banner.h5Url, i2);
                } else if (!TextUtils.isEmpty(banner.openAppUrl)) {
                    e.b(this.a, banner.openAppUrl);
                    com.jd.manto.center.h.b.e(this.a, banner.appid, banner.openAppUrl, i2);
                } else if (TextUtils.isEmpty(banner.h5Url)) {
                } else {
                    e.a(this.a, banner.h5Url);
                    com.jd.manto.center.h.b.e(this.a, banner.appid, banner.h5Url, i2);
                }
            }
        }
    }

    /* loaded from: classes17.dex */
    class b implements SuperCarouseView.b {
        b() {
        }

        @Override // com.jd.manto.center.carouseview.SuperCarouseView.b
        public void onPageSelect(int i2) {
            BannerViewholder.this.e(i2);
        }
    }

    public BannerViewholder(View view) {
        super(view);
        this.a = (ViewGroup) view.findViewById(R.id.carouse_slide_pic_container);
        this.b = (SuperCarouseView) view.findViewById(R.id.carouse_slide_pic_list);
        this.f6547c = (LinearLayout) view.findViewById(R.id.carouse_slide_pic_cursor);
    }

    private void d(Context context, boolean z, int i2) {
        if (!z) {
            h.b(this.f6547c);
            return;
        }
        LinearLayout linearLayout = this.f6547c;
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        for (int i3 = 0; i3 < i2; i3++) {
            View view = new View(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(com.jd.manto.center.k.c.a(9.0f), com.jd.manto.center.k.c.a(3.0f));
            if (i3 == 0) {
                layoutParams.setMargins(0, 0, com.jd.manto.center.k.c.a(3.0f), 0);
            } else if (i3 == i2 - 1) {
                layoutParams.setMargins(com.jd.manto.center.k.c.a(3.0f), 0, 0, 0);
            } else {
                layoutParams.setMargins(com.jd.manto.center.k.c.a(3.0f), 0, com.jd.manto.center.k.c.a(3.0f), 0);
            }
            view.setLayoutParams(layoutParams);
            h.k(view, R.drawable.manto_center_discovery_banner_indicator_unselect_bg);
            this.f6547c.addView(view);
        }
        SuperCarouseView superCarouseView = this.b;
        e(superCarouseView != null ? superCarouseView.f() : 0);
        h.l(this.f6547c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i2) {
        View childAt;
        if (this.f6547c == null) {
            return;
        }
        View view = this.d;
        if (view != null && view.getLayoutParams() != null) {
            this.d.getLayoutParams().width = com.jd.manto.center.k.c.a(9.0f);
            this.d.requestLayout();
            h.k(this.d, R.drawable.manto_center_discovery_banner_indicator_unselect_bg);
        }
        if (this.f6547c.getChildCount() <= 0 || i2 >= this.f6547c.getChildCount() || (childAt = this.f6547c.getChildAt(i2)) == null || childAt.getLayoutParams() == null) {
            return;
        }
        childAt.getLayoutParams().width = com.jd.manto.center.k.c.a(15.0f);
        childAt.requestLayout();
        h.k(childAt, R.drawable.manto_center_discovery_banner_indicator_select_bg);
        this.d = childAt;
    }

    @Override // com.jd.manto.center.viewholder.MantoBaseViewholder
    public void b(Context context, MantoDiscoveryBean mantoDiscoveryBean) {
        if (mantoDiscoveryBean == null || com.jd.manto.center.k.b.b(mantoDiscoveryBean.banner)) {
            return;
        }
        boolean z = mantoDiscoveryBean.banner.size() > 1;
        d(context, z, mantoDiscoveryBean.banner.size());
        this.a.setClipChildren(true);
        this.b.i(true);
        this.b.g(context, this.a, com.jd.manto.center.k.c.a(110.0f), 0, 0, z, z, null);
        this.b.h(new SuperConfigPagerAdapter(context, mantoDiscoveryBean.banner, z, new a(this, context)), new b());
    }
}
