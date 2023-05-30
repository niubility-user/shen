package com.jd.lib.productdetail.mainimage.old;

import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.AbBuriedExpLabelsEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicAnchorEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendRankEntity;
import com.jd.lib.productdetail.mainimage.bean.PdImageEventCode;
import com.jd.lib.productdetail.mainimage.bean.PdMImageEventEntity;
import com.jd.lib.productdetail.mainimage.old.PdMainImageAnchorView;
import com.jd.lib.productdetail.mainimage.presenter.PdImagePagePosViewManager;
import com.jingdong.common.abmta.ABMtaUtils;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes15.dex */
public class k0 implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ WareBusinessMagicAnchorEntity f5177g;

    /* renamed from: h */
    public final /* synthetic */ PdMainImageAnchorView.BigImageAnchorAdapter f5178h;

    public k0(PdMainImageAnchorView.BigImageAnchorAdapter bigImageAnchorAdapter, WareBusinessMagicAnchorEntity wareBusinessMagicAnchorEntity) {
        this.f5178h = bigImageAnchorAdapter;
        this.f5177g = wareBusinessMagicAnchorEntity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity;
        List<WareBusinessMagicHeadPicInfoEntity> list;
        List<WareBusinessMagicHeadPicInfoEntity> list2;
        if (this.f5178h.d != null) {
            Object tag = view.getTag();
            if (!(tag instanceof Integer) || this.f5178h.f5135f == null) {
                return;
            }
            int intValue = ((Integer) tag).intValue();
            WareBusinessMagicAnchorEntity wareBusinessMagicAnchorEntity = this.f5177g;
            if (wareBusinessMagicAnchorEntity != null && TextUtils.equals(wareBusinessMagicAnchorEntity.type, "details")) {
                this.f5178h.f5135f.viewCallBackMutableLiveData.setValue(new PdMImageEventEntity(PdImageEventCode.JUMPTODETAIL, null));
            } else {
                this.f5178h.f5135f.mTempData.put(PdImagePagePosViewManager.TEMP_BIG_IMAGE_MAT_CLICK, "1");
                PdMainImageAnchorView.BigImageAnchorAdapter bigImageAnchorAdapter = this.f5178h;
                bigImageAnchorAdapter.d.onSelect(intValue, bigImageAnchorAdapter.b.get(intValue));
            }
            PdMainImageAnchorView.BigImageAnchorAdapter bigImageAnchorAdapter2 = this.f5178h;
            WareBusinessMagicAnchorEntity wareBusinessMagicAnchorEntity2 = bigImageAnchorAdapter2.b.get(intValue);
            int i2 = intValue + 1;
            bigImageAnchorAdapter2.getClass();
            if (wareBusinessMagicAnchorEntity2 == null || (wareBusinessUnitMainImageEntity = bigImageAnchorAdapter2.f5134e) == null || wareBusinessUnitMainImageEntity.extMap == null) {
                return;
            }
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("main_type", (Object) Integer.valueOf(bigImageAnchorAdapter2.f5134e.extMap.magicHeadPicType));
            jDJSONObject.put("type", (Object) wareBusinessMagicAnchorEntity2.type);
            jDJSONObject.put("categoryId3", (Object) bigImageAnchorAdapter2.f5135f.getCategoryId(2));
            jDJSONObject.put("frame", (Object) ("" + i2));
            if (TextUtils.equals(wareBusinessMagicAnchorEntity2.type, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_COMMENT_BANG_DAN) && (list2 = bigImageAnchorAdapter2.f5134e.magicHeadPicInfo) != null && list2.get(wareBusinessMagicAnchorEntity2.index) != null && bigImageAnchorAdapter2.f5134e.magicHeadPicInfo.get(wareBusinessMagicAnchorEntity2.index).bizData != null && bigImageAnchorAdapter2.f5134e.magicHeadPicInfo.get(wareBusinessMagicAnchorEntity2.index).bizData.bangDanInfo != null) {
                WareImageRecommendRankEntity wareImageRecommendRankEntity = bigImageAnchorAdapter2.f5134e.magicHeadPicInfo.get(wareBusinessMagicAnchorEntity2.index).bizData.bangDanInfo;
                jDJSONObject.put("rankid", (Object) wareImageRecommendRankEntity.rankId);
                jDJSONObject.put("touchstone_expids", (Object) wareImageRecommendRankEntity.bangDanExperiment);
                jDJSONObject.put("rank_type", (Object) "-100");
                jDJSONObject.put("clkSrv", (Object) "-100");
            } else if (TextUtils.equals(wareBusinessMagicAnchorEntity2.type, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_COMMENT_GOLD_RANK_BANG_DAN) && (list = bigImageAnchorAdapter2.f5134e.magicHeadPicInfo) != null && list.get(wareBusinessMagicAnchorEntity2.index) != null && bigImageAnchorAdapter2.f5134e.magicHeadPicInfo.get(wareBusinessMagicAnchorEntity2.index).bizData != null && bigImageAnchorAdapter2.f5134e.magicHeadPicInfo.get(wareBusinessMagicAnchorEntity2.index).bizData.bangDanInfo != null) {
                WareImageRecommendRankEntity wareImageRecommendRankEntity2 = bigImageAnchorAdapter2.f5134e.magicHeadPicInfo.get(wareBusinessMagicAnchorEntity2.index).bizData.bangDanInfo;
                jDJSONObject.put("rankid", (Object) wareImageRecommendRankEntity2.rankId);
                jDJSONObject.put("rank_type", (Object) wareImageRecommendRankEntity2.rankType);
                jDJSONObject.put("clkSrv", (Object) wareImageRecommendRankEntity2.clkSrv);
                jDJSONObject.put("touchstone_expids", (Object) wareImageRecommendRankEntity2.bangDanExperiment);
            } else {
                jDJSONObject.put("rankid", (Object) "-100");
                jDJSONObject.put("touchstone_expids", (Object) "-100");
                jDJSONObject.put("rank_type", (Object) "-100");
                jDJSONObject.put("clkSrv", (Object) "-100");
            }
            HashMap<String, String> a = PdMainImageAnchorView.a(bigImageAnchorAdapter2.f5134e.magicHeadAbTouchStone);
            AbBuriedExpLabelsEntity abBuriedExpLabelsEntity = bigImageAnchorAdapter2.f5134e.abBuriedExpLabels;
            if (abBuriedExpLabelsEntity != null) {
                PdMainImageAnchorView.b(abBuriedExpLabelsEntity.recommendAbtest, a);
            }
            bigImageAnchorAdapter2.f5135f.mtaClick("Productdetail_GuideAccess", ABMtaUtils.joinJsonParamsWithAbTest(jDJSONObject.toJSONString(), a));
        }
    }
}
