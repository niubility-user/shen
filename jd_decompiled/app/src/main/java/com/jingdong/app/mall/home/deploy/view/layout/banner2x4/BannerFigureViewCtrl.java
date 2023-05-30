package com.jingdong.app.mall.home.deploy.view.layout.banner2x4;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jingdong.app.mall.home.deploy.view.layout.banner2x4.BannerAdapter;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorBannerItem;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.q.a;
import com.jingdong.app.mall.home.r.c.b;
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

/* loaded from: classes4.dex */
public class BannerFigureViewCtrl {
    private Context a;

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2, MallFloorBannerItem mallFloorBannerItem, float f2) {
        JumpEntity jump;
        if (l.k() || (jump = mallFloorBannerItem.getJump()) == null) {
            return;
        }
        l.e(this.a, jump);
        new a("\u9996\u7126\u70b9\u51fb", mallFloorBannerItem.clkLog).b();
        String str = jump.getSrv() + CartConstant.KEY_YB_INFO_LINK + "0";
        HashMap hashMap = new HashMap(8);
        hashMap.put(JDMtaUtils.ABTKEY, mallFloorBannerItem.abt);
        hashMap.put("extension_id", mallFloorBannerItem.extension_id);
        b bVar = null;
        try {
            if (!TextUtils.isEmpty(jump.getSrvJson())) {
                bVar = b.c(jump.getSrvJson());
                bVar.a("cache", mallFloorBannerItem.isCache.booleanValue() ? "1" : "0");
                bVar.a(CartConstant.KEY_LENGTH, Float.valueOf(f2));
                bVar.a(DeeplinkProductDetailHelper.LAYER_STYLE, "0");
            }
        } catch (Exception e2) {
            f.s0(this, e2);
        }
        com.jingdong.app.mall.home.r.c.a.u("Home_FocusPic", str, bVar != null ? bVar.toString() : "", RecommendMtaUtils.Home_PageId, hashMap, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final DBanner2x4 dBanner2x4, final DBanner2x4Model dBanner2x4Model, final ArrayList<MallFloorBannerItem> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        dBanner2x4.G(new BannerAdapter.CarouseFigureImageAdapterListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.banner2x4.BannerFigureViewCtrl.2
            @Override // com.jingdong.app.mall.home.deploy.view.layout.banner2x4.BannerAdapter.CarouseFigureImageAdapterListener
            public MallFloorBannerItem a(int i2) {
                return (MallFloorBannerItem) arrayList.get(i2);
            }

            public float b() {
                return dBanner2x4.C();
            }

            @Override // com.jingdong.app.mall.home.deploy.view.layout.banner2x4.BannerAdapter.CarouseFigureImageAdapterListener
            public int getCount() {
                return arrayList.size();
            }

            @Override // com.jingdong.app.mall.home.deploy.view.layout.banner2x4.BannerAdapter.CarouseFigureImageAdapterListener
            public JDDisplayImageOptions getJDDisplayImageOptions() {
                return com.jingdong.app.mall.home.floor.ctrl.f.a().resetViewBeforeLoading(true).showImageForEmptyUri(new JDPlaceholderDrawable(21)).showImageOnLoading(new JDPlaceholderDrawable(21)).showImageOnFail(new JDPlaceholderDrawable(21)).setPlaceholder(21);
            }

            @Override // com.jingdong.app.mall.home.deploy.view.layout.banner2x4.BannerAdapter.CarouseFigureImageAdapterListener
            public void onClick(int i2) {
                MallFloorBannerItem mallFloorBannerItem;
                if (l.k() || (mallFloorBannerItem = (MallFloorBannerItem) arrayList.get(i2)) == null) {
                    return;
                }
                final String str = i2 + "";
                b z0 = dBanner2x4Model.z0();
                float e2 = BannerExpoUtil.e(dBanner2x4Model, dBanner2x4, mallFloorBannerItem.clickUrl, SystemClock.elapsedRealtime() - DBanner2x4.A());
                com.jingdong.app.mall.home.r.c.a.y("Home_FPicAds", "", z0.toString());
                if ((TextUtils.isEmpty(mallFloorBannerItem.clickUrl) || (mallFloorBannerItem.isCache.booleanValue() && i2 == 0)) ? false : true) {
                    f.v0(BannerExpoUtil.a(dBanner2x4Model, e2, mallFloorBannerItem.clickUrl), new HttpGroup.CustomOnAllListener(this) { // from class: com.jingdong.app.mall.home.deploy.view.layout.banner2x4.BannerFigureViewCtrl.2.1
                        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                        public void onEnd(HttpResponse httpResponse) {
                            com.jingdong.app.mall.home.r.c.a.y("Home_FPicAdsSuccess", str, "");
                        }

                        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                        public void onError(HttpError httpError) {
                            Throwable exception = httpError.getException();
                            StringBuilder sb = new StringBuilder();
                            sb.append(str.concat(CartConstant.KEY_YB_INFO_LINK));
                            sb.append(httpError.getErrorCode());
                            sb.append(CartConstant.KEY_YB_INFO_LINK);
                            sb.append(exception == null ? "" : exception.getMessage());
                            sb.append(CartConstant.KEY_YB_INFO_LINK);
                            com.jingdong.app.mall.home.r.c.a.y("Home_FPicAdsFail", sb.toString(), "");
                        }

                        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                        public void onProgress(int i3, int i4) {
                        }

                        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                        public void onStart() {
                        }
                    });
                }
                BannerFigureViewCtrl.this.c(i2, mallFloorBannerItem, b());
            }
        });
    }

    public void d(final DBanner2x4 dBanner2x4, final DBanner2x4Model dBanner2x4Model) {
        if (Log.D) {
            Log.d("NewCarouselFigureViewCtrl", "NewCarouselFigureViewCtrl => init");
        }
        this.a = dBanner2x4.getContext();
        final ArrayList<MallFloorBannerItem> A0 = dBanner2x4Model.A0();
        if (A0 == null || A0.size() < 1) {
            return;
        }
        f.E0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.deploy.view.layout.banner2x4.BannerFigureViewCtrl.1
            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                BannerFigureViewCtrl.this.e(dBanner2x4, dBanner2x4Model, A0);
            }
        });
    }
}
