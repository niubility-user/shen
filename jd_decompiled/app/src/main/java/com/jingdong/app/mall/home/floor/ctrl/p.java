package com.jingdong.app.mall.home.floor.ctrl;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.view.adapter.CarouseFigureLayoutPagerAdapter;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBanner;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorBannerItem;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.placeholder.JDPlaceholderDrawable;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class p {
    private Context a;
    private ConcurrentHashMap<Integer, Boolean> b = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ MallFloorBanner f9479g;

        /* renamed from: h */
        final /* synthetic */ ArrayList f9480h;

        /* renamed from: i */
        final /* synthetic */ float f9481i;

        a(MallFloorBanner mallFloorBanner, ArrayList arrayList, float f2) {
            p.this = r1;
            this.f9479g = mallFloorBanner;
            this.f9480h = arrayList;
            this.f9481i = f2;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            p.this.g(this.f9479g, this.f9480h, this.f9481i);
        }
    }

    /* loaded from: classes4.dex */
    public class b implements CarouseFigureLayoutPagerAdapter.c {
        final /* synthetic */ ArrayList a;
        final /* synthetic */ MallFloorBanner b;

        /* renamed from: c */
        final /* synthetic */ float f9483c;

        /* loaded from: classes4.dex */
        class a implements HttpGroup.CustomOnAllListener {

            /* renamed from: g */
            final /* synthetic */ String f9484g;

            a(b bVar, String str) {
                this.f9484g = str;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                com.jingdong.app.mall.home.r.c.a.y("Home_FPicAdsSuccess", this.f9484g, "");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Throwable exception = httpError.getException();
                StringBuilder sb = new StringBuilder();
                sb.append(this.f9484g.concat(CartConstant.KEY_YB_INFO_LINK));
                sb.append(httpError.getErrorCode());
                sb.append(CartConstant.KEY_YB_INFO_LINK);
                sb.append(exception == null ? "" : exception.getMessage());
                sb.append(CartConstant.KEY_YB_INFO_LINK);
                com.jingdong.app.mall.home.r.c.a.y("Home_FPicAdsFail", sb.toString(), "");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        }

        b(ArrayList arrayList, MallFloorBanner mallFloorBanner, float f2) {
            p.this = r1;
            this.a = arrayList;
            this.b = mallFloorBanner;
            this.f9483c = f2;
        }

        @Override // com.jingdong.app.mall.home.floor.view.adapter.CarouseFigureLayoutPagerAdapter.c
        public MallFloorBannerItem a(int i2) {
            return (MallFloorBannerItem) this.a.get(i2);
        }

        @Override // com.jingdong.app.mall.home.floor.view.adapter.CarouseFigureLayoutPagerAdapter.c
        public void b(int i2, boolean z) {
            p.this.b.put(Integer.valueOf(i2), Boolean.valueOf(z));
        }

        @Override // com.jingdong.app.mall.home.floor.view.adapter.CarouseFigureLayoutPagerAdapter.c
        public float c() {
            return this.f9483c;
        }

        public float d() {
            return this.b.getDisplayRatio();
        }

        @Override // com.jingdong.app.mall.home.floor.view.adapter.CarouseFigureLayoutPagerAdapter.c
        public int getCount() {
            return this.a.size();
        }

        @Override // com.jingdong.app.mall.home.floor.view.adapter.CarouseFigureLayoutPagerAdapter.c
        public JDDisplayImageOptions getJDDisplayImageOptions() {
            return f.a().resetViewBeforeLoading(true).showImageForEmptyUri(new JDPlaceholderDrawable(21)).showImageOnLoading(new JDPlaceholderDrawable(21)).showImageOnFail(new JDPlaceholderDrawable(21)).setPlaceholder(21);
        }

        @Override // com.jingdong.app.mall.home.floor.view.adapter.CarouseFigureLayoutPagerAdapter.c
        public void onClick(int i2) {
            MallFloorBannerItem mallFloorBannerItem;
            if (com.jingdong.app.mall.home.floor.common.i.l.k() || (mallFloorBannerItem = (MallFloorBannerItem) this.a.get(i2)) == null) {
                return;
            }
            String str = i2 + "";
            com.jingdong.app.mall.home.r.f.a.f fVar = (com.jingdong.app.mall.home.r.f.a.f) this.b.getPresenter();
            com.jingdong.app.mall.home.r.c.b R = fVar.R();
            float t0 = fVar.t0(R, mallFloorBannerItem.clickUrl, SystemClock.elapsedRealtime() - MallFloorBanner.getCurrentBannerSelectedTime());
            com.jingdong.app.mall.home.r.c.a.y("Home_FPicAds", "", R.toString());
            if ((TextUtils.isEmpty(mallFloorBannerItem.clickUrl) || (mallFloorBannerItem.isCache.booleanValue() && i2 == 0)) ? false : true) {
                com.jingdong.app.mall.home.o.a.f.v0(com.jingdong.app.mall.home.o.a.f.g(fVar, t0, mallFloorBannerItem.clickUrl), new a(this, str));
            }
            p.this.d(i2, mallFloorBannerItem, d());
        }
    }

    public void d(int i2, MallFloorBannerItem mallFloorBannerItem, float f2) {
        JumpEntity jump;
        if (com.jingdong.app.mall.home.floor.common.i.l.k() || (jump = mallFloorBannerItem.getJump()) == null) {
            return;
        }
        com.jingdong.app.mall.home.floor.common.i.l.e(this.a, jump);
        new com.jingdong.app.mall.home.q.a("\u9996\u7126\u70b9\u51fb", mallFloorBannerItem.clkLog).b();
        String e2 = !TextUtils.isEmpty(mallFloorBannerItem.videoId) ? e(i2) : "0";
        String str = jump.getSrv() + CartConstant.KEY_YB_INFO_LINK + e2;
        HashMap hashMap = new HashMap(8);
        hashMap.put(JDMtaUtils.ABTKEY, mallFloorBannerItem.abt);
        hashMap.put("extension_id", mallFloorBannerItem.extension_id);
        com.jingdong.app.mall.home.r.c.b bVar = null;
        try {
            if (!TextUtils.isEmpty(jump.getSrvJson())) {
                bVar = com.jingdong.app.mall.home.r.c.b.c(jump.getSrvJson());
                bVar.a("cache", mallFloorBannerItem.isCache.booleanValue() ? "1" : "0");
                bVar.a(CartConstant.KEY_LENGTH, Float.valueOf(f2));
                bVar.a(DeeplinkProductDetailHelper.LAYER_STYLE, e2);
            }
        } catch (Exception e3) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e3);
        }
        com.jingdong.app.mall.home.r.c.a.u("Home_FocusPic", str, bVar != null ? bVar.toString() : "", RecommendMtaUtils.Home_PageId, hashMap, "");
    }

    public void g(MallFloorBanner mallFloorBanner, ArrayList<MallFloorBannerItem> arrayList, float f2) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        mallFloorBanner.setCarouseFigureImageAdapterListener(new b(arrayList, mallFloorBanner, f2));
    }

    public String e(int i2) {
        Boolean bool;
        ConcurrentHashMap<Integer, Boolean> concurrentHashMap = this.b;
        return (concurrentHashMap == null || (bool = concurrentHashMap.get(Integer.valueOf(i2))) == null || !bool.booleanValue()) ? "0" : "1";
    }

    public void f(MallFloorBanner mallFloorBanner, com.jingdong.app.mall.home.r.f.a.f fVar) {
        if (Log.D) {
            Log.d("NewCarouselFigureViewCtrl", "NewCarouselFigureViewCtrl => init");
        }
        this.a = mallFloorBanner.getContext();
        this.b.clear();
        ArrayList<MallFloorBannerItem> T = fVar.T();
        if (T == null || T.size() < 1) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.E0(new a(mallFloorBanner, T, fVar.S()));
    }
}
