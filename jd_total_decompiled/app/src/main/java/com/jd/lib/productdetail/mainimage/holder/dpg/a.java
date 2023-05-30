package com.jd.lib.productdetail.mainimage.holder.dpg;

import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PDTopReocommendEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.AppStaticInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.utils.OpenAppUtils;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;

/* loaded from: classes15.dex */
public class a implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ PdMImageAnchorLayout f4804g;

    public a(PdMImageAnchorLayout pdMImageAnchorLayout) {
        this.f4804g = pdMImageAnchorLayout;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity;
        AppStaticInfo appStaticInfo;
        PdMImageAnchorLayout pdMImageAnchorLayout = this.f4804g;
        if (pdMImageAnchorLayout.f4803n == null || (wareBusinessUnitMainImageEntity = pdMImageAnchorLayout.u) == null || wareBusinessUnitMainImageEntity.extMap == null) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("matchid", (Object) Integer.valueOf(this.f4804g.f4803n.matchId));
        if (!TextUtils.isEmpty(this.f4804g.s)) {
            jDJSONObject.put("source", (Object) this.f4804g.s);
        }
        jDJSONObject.put("touchstone_expids", (Object) this.f4804g.f4803n.expIds);
        this.f4804g.t.mtaClick("Productdetail_PhotoMatchEntrance", jDJSONObject.toJSONString());
        if (TextUtils.equals(this.f4804g.s, "2")) {
            this.f4804g.t.jumpToPage.setValue(Boolean.TRUE);
            PdMImageAnchorLayout pdMImageAnchorLayout2 = this.f4804g;
            PDBaseDeepLinkHelper.gotoMWithUrl(pdMImageAnchorLayout2.f4798i, pdMImageAnchorLayout2.f4803n.linkUrl);
        } else if (TextUtils.equals(this.f4804g.f4803n.jumpType, "2")) {
            PDTopReocommendEntity pDTopReocommendEntity = new PDTopReocommendEntity();
            pDTopReocommendEntity.pid = "10501";
            pDTopReocommendEntity.formType = "vrDPG";
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity2 = this.f4804g.u;
            if (wareBusinessUnitMainImageEntity2 != null && (appStaticInfo = wareBusinessUnitMainImageEntity2.extMap.appStaticInfo) != null && !TextUtils.isEmpty(appStaticInfo.recommendPopTiltle)) {
                pDTopReocommendEntity.layerTitle = this.f4804g.u.extMap.appStaticInfo.recommendPopTiltle;
            } else {
                pDTopReocommendEntity.layerTitle = this.f4804g.f4798i.getString(R.string.lib_pd_image_recommend_match_product);
            }
            PdMImageAnchorLayout pdMImageAnchorLayout3 = this.f4804g;
            PdMainImagePresenter pdMainImagePresenter = pdMImageAnchorLayout3.t;
            if (pdMainImagePresenter != null && pdMainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
                pdMainImagePresenter.toDetailPage.setValue(Boolean.TRUE);
                return;
            }
            com.jd.lib.productdetail.mainimage.dialog.a aVar = pdMImageAnchorLayout3.o;
            if (aVar != null) {
                aVar.a(pDTopReocommendEntity);
            }
        } else {
            this.f4804g.t.jumpToPage.setValue(Boolean.TRUE);
            PdMImageAnchorLayout pdMImageAnchorLayout4 = this.f4804g;
            OpenAppUtils.openMatchBuyFlutterNew(pdMImageAnchorLayout4.f4798i, pdMImageAnchorLayout4.u.extMap.skuId, PDUtils.longToString(pdMImageAnchorLayout4.f4803n.matchId), "4");
        }
    }
}
