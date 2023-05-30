package com.jd.lib.productdetail.mainimage.holder.recommend;

import android.view.View;
import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;

/* loaded from: classes15.dex */
public class PdMImageRecommendNewViewHolder extends PdMainImageBaseHolder implements com.jd.lib.productdetail.mainimage.dialog.a {
    public PdMImageRecommendNewView B;

    public PdMImageRecommendNewViewHolder(@NonNull View view, View view2) {
        super(view, view2);
    }

    @Override // com.jd.lib.productdetail.mainimage.dialog.a
    public void a(Object obj) {
        if (obj instanceof View) {
            o((View) obj);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
        if (view instanceof PdMImageRecommendNewView) {
            this.B = (PdMImageRecommendNewView) view;
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void j(PdMainImagePresenter pdMainImagePresenter) {
        super.j(pdMainImagePresenter);
        PdMImageRecommendNewView pdMImageRecommendNewView = this.B;
        if (pdMImageRecommendNewView != null) {
            pdMImageRecommendNewView.d(pdMainImagePresenter);
            this.B.f(this);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void n() {
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        PdMImageRecommendNewView pdMImageRecommendNewView = this.B;
        if (pdMImageRecommendNewView == null || (extMapEntity = this.v.extMap) == null || extMapEntity.category == null) {
            return;
        }
        int i2 = PdMImageRecommendItemView.s;
        pdMImageRecommendNewView.e(2);
        PdMImageRecommendNewView pdMImageRecommendNewView2 = this.B;
        String valueOf = String.valueOf(this.v.extMap.category.fstId);
        String valueOf2 = String.valueOf(this.v.extMap.category.sndId);
        String valueOf3 = String.valueOf(this.v.extMap.category.thdId);
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity2 = this.v.extMap;
        String str = extMapEntity2.shopId;
        String str2 = extMapEntity2.skuId;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity = this.r.bizData;
        pdMImageRecommendNewView2.b(valueOf, valueOf2, valueOf3, str, str2, wareBuinessUnitMainImageBizDataEntity != null ? wareBuinessUnitMainImageBizDataEntity.bangDanInfo : null);
    }

    @Override // com.jd.lib.productdetail.mainimage.dialog.a
    public void a() {
        s();
    }
}
