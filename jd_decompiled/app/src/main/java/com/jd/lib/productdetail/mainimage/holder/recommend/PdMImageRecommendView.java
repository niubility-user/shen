package com.jd.lib.productdetail.mainimage.holder.recommend;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductListInfo;
import com.jd.lib.productdetail.core.entitys.PreferentialRecommendTabItemEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PreferentialRecommendItemEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendEntity;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.abmta.ABMtaUtils;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.JdSdk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes15.dex */
public class PdMImageRecommendView extends RelativeLayout implements com.jd.lib.productdetail.mainimage.dialog.a {

    /* renamed from: g  reason: collision with root package name */
    public Context f4921g;

    /* renamed from: h  reason: collision with root package name */
    public int f4922h;

    /* renamed from: i  reason: collision with root package name */
    public com.jd.lib.productdetail.mainimage.dialog.a f4923i;

    /* renamed from: j  reason: collision with root package name */
    public PdMImageRecommendItemView f4924j;

    /* renamed from: k  reason: collision with root package name */
    public PdMImageRecommendItemView f4925k;

    /* renamed from: l  reason: collision with root package name */
    public SimpleDraweeView f4926l;

    /* renamed from: m  reason: collision with root package name */
    public WareImageRecommendEntity f4927m;

    /* renamed from: n  reason: collision with root package name */
    public LinearLayout f4928n;
    public WareBusinessUnitMainImageEntity o;
    public PdMainImagePresenter p;

    public PdMImageRecommendView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4921g = context;
    }

    @Override // com.jd.lib.productdetail.mainimage.dialog.a
    public void a() {
    }

    @Override // com.jd.lib.productdetail.mainimage.dialog.a
    public void a(Object obj) {
        com.jd.lib.productdetail.mainimage.dialog.a aVar = this.f4923i;
        if (aVar != null) {
            aVar.a(obj);
        }
    }

    public void b(PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo, WareImageRecommendEntity wareImageRecommendEntity, WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity) {
        Map<String, List<PreferentialRecommendItemEntity>> map;
        List<PreferentialRecommendTabItemEntity> list;
        this.o = wareBusinessUnitMainImageEntity;
        if (pdPreferentialRecommendProductListInfo != null && (map = pdPreferentialRecommendProductListInfo.itemEntitiesMap) != null && map.size() >= 2 && (list = pdPreferentialRecommendProductListInfo.tabItemEntities) != null && list.size() >= 2 && pdPreferentialRecommendProductListInfo.tabItemEntities.get(0) != null && pdPreferentialRecommendProductListInfo.tabItemEntities.get(1) != null && !TextUtils.isEmpty(pdPreferentialRecommendProductListInfo.tabItemEntities.get(0).title) && !TextUtils.isEmpty(pdPreferentialRecommendProductListInfo.tabItemEntities.get(1).title)) {
            c(false, wareImageRecommendEntity);
            d();
            Map<String, List<PreferentialRecommendItemEntity>> map2 = pdPreferentialRecommendProductListInfo.itemEntitiesMap;
            for (int i2 = 0; i2 < 2; i2++) {
                PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity = pdPreferentialRecommendProductListInfo.tabItemEntities.get(i2);
                if (preferentialRecommendTabItemEntity != null) {
                    List<PreferentialRecommendItemEntity> list2 = map2.get(preferentialRecommendTabItemEntity.tabId);
                    if (i2 == 0) {
                        this.f4924j.e(this.f4922h);
                        this.f4924j.b(list2, preferentialRecommendTabItemEntity.tabId, preferentialRecommendTabItemEntity.title, wareImageRecommendEntity, wareBusinessUnitMainImageEntity);
                    }
                    if (i2 == 1) {
                        this.f4925k.e(this.f4922h);
                        this.f4925k.b(list2, preferentialRecommendTabItemEntity.tabId, preferentialRecommendTabItemEntity.title, wareImageRecommendEntity, wareBusinessUnitMainImageEntity);
                    }
                }
            }
            List<PreferentialRecommendTabItemEntity> list3 = pdPreferentialRecommendProductListInfo.tabItemEntities;
            try {
                JDJSONArray jDJSONArray = new JDJSONArray();
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < 2; i3++) {
                    PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity2 = list3.get(i3);
                    if (preferentialRecommendTabItemEntity2 != null) {
                        arrayList.addAll(map2.get(preferentialRecommendTabItemEntity2.tabId));
                    }
                }
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    PreferentialRecommendItemEntity preferentialRecommendItemEntity = (PreferentialRecommendItemEntity) arrayList.get(i4);
                    if (preferentialRecommendItemEntity != null) {
                        JDJSONObject jDJSONObject = new JDJSONObject();
                        jDJSONObject.put("categoryid", (Object) preferentialRecommendItemEntity.tabId);
                        jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) preferentialRecommendItemEntity.skuId);
                        jDJSONObject.put("is_purchase_price", (Object) Integer.valueOf((!TextUtils.equals(preferentialRecommendItemEntity.realPriceNum, "1") || TextUtils.isEmpty(preferentialRecommendItemEntity.realPrice)) ? 0 : 1));
                        jDJSONObject.put("touchstone_expids", (Object) new ArrayList());
                        jDJSONArray.add(jDJSONObject);
                    }
                }
                ABMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplication().getApplicationContext(), "Productdetail_MainPhotoCategoryExpo", null, PDHelper.getPageId("com.jd.lib.productdetail.ProductDetailActivity"), "com.jd.lib.productdetail.ProductDetailActivity", this.o.extMap.skuId, jDJSONArray.toJSONString(), this.o.extMap.shopId, null, this.p.getMainImageParams().mSkuTag, null, new HashMap());
                return;
            } catch (Exception unused) {
                return;
            }
        }
        c(true, wareImageRecommendEntity);
        d();
    }

    public final void c(boolean z, WareImageRecommendEntity wareImageRecommendEntity) {
        this.f4927m = wareImageRecommendEntity;
        if (z) {
            this.f4924j.setVisibility(8);
            this.f4925k.setVisibility(8);
            if (wareImageRecommendEntity != null) {
                JDImageUtils.displayImage(wareImageRecommendEntity.default_image, this.f4926l);
            }
            this.f4926l.setVisibility(0);
            if (this.f4922h == 1) {
                this.f4928n.setVisibility(8);
            } else {
                this.f4928n.setVisibility(0);
            }
            if (this.o != null) {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("touchstone_expids", (Object) new ArrayList());
                this.p.mtaExposure("Productdetail_MainPhotoDDExpo", jDJSONObject.toJSONString());
                return;
            }
            return;
        }
        this.f4924j.setVisibility(0);
        this.f4925k.setVisibility(0);
        this.f4926l.setVisibility(8);
        this.f4928n.setVisibility(8);
    }

    public final void d() {
        if (this.f4922h == 1) {
            this.f4926l.setClickable(false);
            this.f4924j.setClickable(false);
            this.f4925k.setClickable(false);
            this.f4928n.setClickable(false);
            return;
        }
        this.f4926l.setClickable(true);
        this.f4924j.setClickable(true);
        this.f4925k.setClickable(true);
        this.f4928n.setClickable(true);
    }

    public void e(WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity) {
        PdMImageRecommendItemView pdMImageRecommendItemView = this.f4924j;
        if (pdMImageRecommendItemView != null) {
            pdMImageRecommendItemView.c(wareBusinessMagicHeadPicInfoEntity);
        }
        PdMImageRecommendItemView pdMImageRecommendItemView2 = this.f4925k;
        if (pdMImageRecommendItemView2 != null) {
            pdMImageRecommendItemView2.c(wareBusinessMagicHeadPicInfoEntity);
        }
    }

    public void f(PdMainImagePresenter pdMainImagePresenter) {
        this.p = pdMainImagePresenter;
        PdMImageRecommendItemView pdMImageRecommendItemView = this.f4924j;
        if (pdMImageRecommendItemView != null) {
            pdMImageRecommendItemView.d(pdMainImagePresenter);
        }
        PdMImageRecommendItemView pdMImageRecommendItemView2 = this.f4925k;
        if (pdMImageRecommendItemView2 != null) {
            pdMImageRecommendItemView2.d(pdMainImagePresenter);
        }
    }

    public void g(int i2) {
        this.f4922h = i2;
    }

    public void h(com.jd.lib.productdetail.mainimage.dialog.a aVar) {
        this.f4923i = aVar;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4924j = (PdMImageRecommendItemView) findViewById(R.id.product_item_layout1);
        this.f4925k = (PdMImageRecommendItemView) findViewById(R.id.product_item_layout2);
        this.f4926l = (SimpleDraweeView) findViewById(R.id.pd_top_default_image);
        this.f4928n = (LinearLayout) findViewById(R.id.lib_pd_holder_topimage_item_recommend_content_btn_ok);
        this.f4924j.f(this);
        this.f4925k.f(this);
        this.f4926l.setOnClickListener(new g(this));
        this.f4928n.setOnClickListener(new h(this));
    }
}
