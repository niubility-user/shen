package com.jd.lib.productdetail.mainimage.holder.video;

import android.view.View;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.old.k;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImageParams;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.presenter.PdVideoContainer;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;

/* loaded from: classes15.dex */
public class j implements k.m {
    public final /* synthetic */ PdMVideoViewHolder a;

    public j(PdMVideoViewHolder pdMVideoViewHolder) {
        this.a = pdMVideoViewHolder;
    }

    public void a(View view) {
        PdMVideoViewHolder pdMVideoViewHolder;
        com.jd.lib.productdetail.mainimage.old.k kVar;
        String str;
        com.jd.lib.productdetail.mainimage.old.k kVar2;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        WareBusinessUnitMainImageEntity.ExtMapEntity.CategoryEntity categoryEntity;
        PdVideoContainer pdVideoContainer;
        if (!PDUtils.repeatClick() || (kVar = (pdMVideoViewHolder = this.a).E) == null) {
            return;
        }
        int i2 = kVar.f5168l;
        if (i2 == 2) {
            PdMVideoViewHolder.G(pdMVideoViewHolder);
        } else if (i2 == 1) {
            if (kVar.o) {
                PdMainImagePresenter pdMainImagePresenter = pdMVideoViewHolder.f4654n;
                if (pdMainImagePresenter != null && (pdVideoContainer = pdMainImagePresenter.pdVideoContainer) != null) {
                    pdVideoContainer.isClickVideoPlay.setValue(Boolean.TRUE);
                }
                this.a.O();
                return;
            }
            PdMainImagePresenter pdMainImagePresenter2 = pdMVideoViewHolder.f4654n;
            if (pdMainImagePresenter2 != null) {
                try {
                    JDJSONObject parseObject = JDJSON.parseObject(pdMainImagePresenter2.getMainImageParams().dJTemplateType);
                    if (parseObject != null) {
                        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.a.v;
                        if (wareBusinessUnitMainImageEntity != null && (extMapEntity = wareBusinessUnitMainImageEntity.extMap) != null && (categoryEntity = extMapEntity.category) != null) {
                            PDUtils.setFloorCid(parseObject, String.valueOf(categoryEntity.fstId), String.valueOf(this.a.v.extMap.category.sndId), String.valueOf(this.a.v.extMap.category.thdId));
                        }
                        PdMainImageParams pdMainImageParams = this.a.f4654n.mainImageParams;
                        if (pdMainImageParams != null) {
                            PDUtils.setFloorPriceJson(parseObject, pdMainImageParams.floorPriceMta);
                            PdMVideoViewHolder pdMVideoViewHolder2 = this.a;
                            PDUtils.setCardInfo(parseObject, pdMVideoViewHolder2.f4654n.mainImageParams.brandId, "bpMainImage", pdMVideoViewHolder2.f4652l);
                        }
                    }
                    str = JDJSON.toJSONString(parseObject);
                } catch (Exception unused) {
                    str = this.a.f4654n.getMainImageParams().dJTemplateType;
                }
                this.a.f4654n.mtaClick("Productdetail_Photo", str, "2", true);
                PdMVideoViewHolder pdMVideoViewHolder3 = this.a;
                if (pdMVideoViewHolder3.f4654n.imageFromType != PdImageFromType.PRODUCTDETAIL_MINI && (kVar2 = pdMVideoViewHolder3.E) != null && kVar2.C()) {
                    this.a.E.y(false);
                }
            }
            this.a.m(true, true, false);
        }
    }
}
