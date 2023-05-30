package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.un.basewidget.widget.banner.BannerAdapter;
import com.jd.lib.un.basewidget.widget.banner.BannerView;
import com.jd.lib.un.basewidget.widget.banner.page.PageView;
import com.jingdong.sdk.lib.puppetlayout.R;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Iterate;

/* loaded from: classes8.dex */
public class CarouselCreator extends com.jingdong.sdk.lib.puppetlayout.h.a {

    /* renamed from: k  reason: collision with root package name */
    private BannerView f15229k;

    /* renamed from: l  reason: collision with root package name */
    private CarouselAdapter f15230l = null;

    /* loaded from: classes8.dex */
    public static class CarouselAdapter extends BannerAdapter {
        private JDJSONArray a;
        private JDJSONObject b;

        /* renamed from: c  reason: collision with root package name */
        private Iterate f15231c;
        private PuppetContext d;

        public CarouselAdapter(Iterate iterate, PuppetContext puppetContext) {
            this.f15231c = iterate;
            this.d = puppetContext;
        }

        public void a(JDJSONObject jDJSONObject) {
            this.b = jDJSONObject;
        }

        public void b(JDJSONArray jDJSONArray) {
            this.a = jDJSONArray;
        }

        @Override // com.jd.lib.un.basewidget.widget.banner.BannerAdapter
        public int getItemCount() {
            JDJSONArray jDJSONArray = this.a;
            if (jDJSONArray != null) {
                return jDJSONArray.size();
            }
            return 0;
        }

        @Override // com.jd.lib.un.basewidget.widget.banner.BannerAdapter
        public View getItemView(int i2, View view, ViewGroup viewGroup) {
            Iterate iterate;
            ViewGroup viewGroup2;
            if (viewGroup == null || (iterate = this.f15231c) == null || iterate.itemViewBase == null) {
                return null;
            }
            if (view == null) {
                viewGroup2 = iterate.createItemView(viewGroup.getContext(), this.d);
            } else {
                viewGroup2 = (ViewGroup) view;
            }
            JDJSONObject jDJSONObject = this.b;
            if (jDJSONObject != null) {
                this.f15231c.bindData(viewGroup2, jDJSONObject);
            }
            if (this.a.optJSONObject(i2) != null) {
                this.f15231c.bindItemData(viewGroup2, this.a.optJSONObject(i2), false, false, i2);
            } else if (this.a.optString(i2) != null) {
                this.f15231c.bindItemData(viewGroup2, this.a.optString(i2), false, false, i2);
            }
            return viewGroup2;
        }
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void d(Context context) {
        BannerView bannerView = new BannerView(context);
        this.f15229k = bannerView;
        bannerView.isSupportAutoScroll(false);
        this.a = this.f15229k;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void m(JDJSONObject jDJSONObject, JDJSONArray jDJSONArray, Iterate iterate) {
        if (jDJSONObject == null && jDJSONArray == null) {
            return;
        }
        CarouselAdapter carouselAdapter = this.f15230l;
        if (carouselAdapter == null) {
            CarouselAdapter carouselAdapter2 = new CarouselAdapter(iterate, this.b);
            this.f15230l = carouselAdapter2;
            carouselAdapter2.b(jDJSONArray);
            this.f15230l.a(jDJSONObject);
            this.f15229k.setAdapter((BannerAdapter) this.f15230l);
        } else {
            carouselAdapter.b(jDJSONArray);
            this.f15230l.a(jDJSONObject);
            this.f15230l.notifyDataSetChanged();
        }
        try {
            PageView pageView = (PageView) this.f15229k.getTag(R.id.com_jd_sdk_lib_puppetlayout_indicator_2);
            if (pageView != null) {
                pageView.setBannerView(this.f15229k);
            }
        } catch (Exception e2) {
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public boolean q(String str, String str2, String str3) {
        if (super.q(str, str2, str3)) {
            return true;
        }
        if ("AutoPager".equals(str)) {
            if (!"1".equals(str2) && !DYConstants.DY_TRUE.equals(str2)) {
                this.f15229k.isSupportAutoScroll(false);
            } else {
                this.f15229k.isSupportAutoScroll(true);
            }
            return true;
        }
        if (!"AutoPagerDuration".equals(str) && "AutoPagerTime".equals(str)) {
            try {
                long longValue = Long.valueOf(str2).longValue();
                if (longValue != -1) {
                    this.f15229k.setSlideInterval((int) (longValue * 1000));
                }
            } catch (NumberFormatException e2) {
                if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                    e2.printStackTrace();
                }
            }
        }
        return true;
    }
}
